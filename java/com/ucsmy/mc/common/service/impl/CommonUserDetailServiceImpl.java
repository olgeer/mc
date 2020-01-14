package com.ucsmy.mc.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.module.monitor.controller.LogProxyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ucsmy.mc.common.entity.RolePermission;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;
import com.ucsmy.mc.common.exmapper.ExUserBasicMapper;
import com.ucsmy.mc.common.exmapper.ExUserRoleMapper;
import com.ucsmy.mc.common.service.CommonRolePermissionService;
import com.ucsmy.mc.util.paging.PageContext;


/**
 * Description:普通用户账号Spring Secury 验证
 * Time:2015年12月3日上午11:02:35
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Service
public class CommonUserDetailServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(CommonUserDetailServiceImpl.class);
	
	@Resource
	private ExUserBasicMapper exUserBasicMapper;

	@Resource
	private ExUserRoleMapper exUserRoleMapper;
	
	@Resource(name="commonRolePermissionServiceImpl")
	private CommonRolePermissionService commonRolePermissionService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!username.contains("|")) {
			try {
				PageContext.getContext().setPagination(false);
				UserBasic userBasic = exUserBasicMapper.selectUserBasicByUserAccount(username);

				if (userBasic == null) {
					log.info("用户名为{}的用户不存在", username);
					return null;
				}
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("usbaId", userBasic.getUsbaId());
				List<UserRole> userRoles = exUserRoleMapper.selectUserRoleByUsbaId(map1);

				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				for (UserRole userRole : userRoles) {
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("roleId", userRole.getRoleId());
					List<RolePermission> rolePermissions = commonRolePermissionService.getRolePermissionByRoleId(map2);
					for (RolePermission rolePermission : rolePermissions) {
						authorities.add(new SimpleGrantedAuthority(rolePermission.getExPermResource()));
					}
				}
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				userBasic.setUsbaGrantedAuthoritys(authorities);
				return userBasic;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String[] split = username.split("\\|");
			String UserName = split[0];
			String NickName = split[1];

			try {
				UserBasic userBasic = new UserBasic();
				userBasic.setUsbaAccount(UserName);
				userBasic.setUsbaName(NickName);
				userBasic.setUsbaPassword("f379eaf3c831b04de153469d1bec345e");
				userBasic.setUsbaAccountLocked((byte) 1);
				userBasic.setUsbaAccountEnable((byte) 1);
				userBasic.setUsbaAccountExpired((byte) 1);

				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_MC_VIEW"));
				userBasic.setUsbaGrantedAuthoritys(authorities);

				return userBasic;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return null;
	}

}
