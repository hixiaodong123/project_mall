package com.cskaoyan.mall.realm;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = null;
        MallToken mallToken = (MallToken) authenticationToken;
        String type = mallToken.getType();
        if ("admin".equals(type)) {
            Admin admin = adminMapper.queryUserByName(username);
            password = admin.getPassword();
        } else if ("user".equals(type)) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUsernameEqualTo(username);
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() == 0) return null;
            User user = users.get(0);
            password = user.getPassword();
        }
        return new SimpleAuthenticationInfo(username, password, "customRealm");
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        int[] ids = adminMapper.selectRolesIdByName(username);
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        ArrayList<String> permissions = new ArrayList<>();
        for (int id : ids) {
            List<String> list = permissionMapper.queryPermissionsByRolesId(id);
            permissions.add(String.valueOf(list));
        }
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    public void clearCache(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
