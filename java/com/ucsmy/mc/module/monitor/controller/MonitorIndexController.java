package com.ucsmy.mc.module.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ucs_mawenzhong on 2017/5/15.
 */
@Controller
public class MonitorIndexController {
    private static Logger logger = LoggerFactory.getLogger(MonitorIndexController.class);

    @Autowired
    protected AuthenticationManager authenticationManager;

    @RequestMapping("/monitor_index")
    public String toMonitorIndex(Model model, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ("anonymousUser".equals(authentication.getPrincipal())) {
            String token = request.getParameter("ssoToken");
            String ip = request.getRemoteAddr();

            if (logger.isDebugEnabled()) {
                logger.debug("login in params: token=" + token + ", ip=" + ip);
            }

            JSONObject returnData = null;

            try {
                String info = LogProxyController.post("/uspapi/SSO/GetSSOLoginInfo?ssotoken=" + token, "");

                if (logger.isDebugEnabled()) {
                    logger.debug("info : " + info);
                }

                JSONObject jsonObject = JSON.parseObject(info);
                returnData = jsonObject.getJSONObject("ReturnData");
                JSONObject ssoLogin = returnData.getJSONObject("SSOLogin");

                String UserName = ssoLogin.getString("UserName");
                String LoginIP = ssoLogin.getString("LoginIP");
                String NickName = ssoLogin.getString("NickName");

                if (!ip.equals(LoginIP)) {
                    logger.error("sourceIp=" + ip + "   loginIP=" + LoginIP);
                    throw new RuntimeException("源IP和认证服务器IP不一致");
                }

                if (StringUtils.isBlank(UserName)) {
                    throw new RuntimeException("认证失败");
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        UserName + "|" + NickName, "666666");


                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                Authentication authenticatedUser = authenticationManager
                        .authenticate(authenticationToken);

                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            } catch (Exception e) {
                logger.error("Authentication failed: " + e.getMessage());

                String LoginUrl = returnData.getString("LoginUrl");
                model.addAttribute("LoginUrl", LoginUrl);
                return "/monitor/login_fail";
            }
        }


        return "/monitor/index";
    }

}
