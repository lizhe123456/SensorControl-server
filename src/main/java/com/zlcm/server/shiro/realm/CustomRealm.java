package com.zlcm.server.shiro.realm;

import com.zlcm.server.model.upms.UpmsPermission;
import com.zlcm.server.model.upms.UpmsRole;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.service.UpmsApiService;
import com.zlcm.server.util.MD5Utils;
import com.zlcm.server.util.PropertiesFileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UpmsApiService upmsApiService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);

        //当前用户所有角色
        List<UpmsRole> upmsRoles = upmsApiService.selectUpmsRoleByUpmsUserId(ucenterUser.getUser_id());
        Set<String> roles = new HashSet<>();
        for (UpmsRole upmsRole : upmsRoles){
            if (StringUtils.isNotBlank(upmsRole.getName())){
                roles.add(upmsRole.getName());
            }
        }

        //当前用户所有权限
        List<UpmsPermission> upmsPermissions =
                upmsApiService.selectUpmsPermissionByUpmsUserId(ucenterUser.getUser_id());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())){
                permissions.add(upmsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }
    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // client无密认证
//        String upmsType = PropertiesFileUtil.getInstance("").get("");
//        if ("client".equals(upmsType)){
//            return new SimpleAuthenticationInfo(username, password, getName());
//        }
        // 查询用户信息
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);

        if (ucenterUser == null) {
            throw new UnknownAccountException();
        }
        if (!ucenterUser.getPassword().equals(MD5Utils.MD5(password + ucenterUser.getSalt()))){
            throw new IncorrectCredentialsException();
        }
        if (ucenterUser.getLocked() == 1){
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(ucenterUser,password,this.getName());
        return simpleAuthenticationInfo;
    }
}
