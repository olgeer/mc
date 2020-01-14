package com.ucsmy.mc.common.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import com.ucsmy.mc.common.entity.RolePermission;
import com.ucsmy.mc.common.entity.UserBasic;
import com.ucsmy.mc.common.entity.UserRole;
import com.ucsmy.mc.common.exmapper.ExUserBasicMapper;
import com.ucsmy.mc.common.exmapper.ExUserRoleMapper;
import com.ucsmy.mc.common.service.CommonRolePermissionService;
import com.ucsmy.mc.util.paging.PageContext;

/** 
 * @ClassName: LdapUserDetailsContextMapper 
 * @Description: TODO Ldap认证成功之后加载角色权限信息
 * @author: ucs_chenchengteng
 * @date: 2017年2月28日 下午2:07:09
 * @version: V1.0     
 */
@Component("ldapUserDetailsContextMapper")
public class LdapUserDetailsContextMapper implements UserDetailsContextMapper {

	private static final Logger log = LoggerFactory.getLogger(LdapUserDetailsContextMapper.class);
	@Resource
	private ExUserBasicMapper exUserBasicMapper;

	@Resource
	private ExUserRoleMapper exUserRoleMapper;
	
	@Resource(name="commonRolePermissionServiceImpl")
	private CommonRolePermissionService commonRolePermissionService;
    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
            Collection<? extends GrantedAuthority> authorities) {
        
        try{
        	PageContext.getContext().setPagination(false);
			UserBasic userBasic = exUserBasicMapper.selectUserBasicByUserAccount(username);

			if(userBasic == null) {
				log.info("用户名为{}的用户不存在", username);
				return null;
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("usbaId", userBasic.getUsbaId());
			List<UserRole> userRoles = exUserRoleMapper.selectUserRoleByUsbaId(map1);
			
			List<GrantedAuthority> authoritiesCustom = new ArrayList<GrantedAuthority>();	
			for(UserRole userRole: userRoles) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("roleId", userRole.getRoleId());
				List<RolePermission> rolePermissions = commonRolePermissionService.getRolePermissionByRoleId(map2);
				for(RolePermission rolePermission: rolePermissions) {
					authoritiesCustom.add(new SimpleGrantedAuthority(rolePermission.getExPermResource()));
				}
			}
			authoritiesCustom.add(new SimpleGrantedAuthority("ROLE_USER"));
			userBasic.setUsbaGrantedAuthoritys(authoritiesCustom);
			return userBasic;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
    	@SuppressWarnings("unused")
		UserBasic autoUser = (UserBasic) user;
        /*ctx.setAttributeValue("givenName", autoUser.getFirstName());
        ctx.setAttributeValue("sn", autoUser.getLastName());
        ctx.setAttributeValue("mail", autoUser.getEmail());
        ctx.setAttributeValue("password", autoUser.getPassword());
        ctx.setAttributeValue("uid", autoUser.getUsername());*/
    }
}