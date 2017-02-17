package com.lz.shiro;

import com.lz.mapper.RoleMapper;
import com.lz.mapper.UserMapper;
import com.lz.pojo.Role;
import com.lz.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiroDbRealm extends AuthorizingRealm{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        获取当前登录的对象
        User user= (User) principalCollection.getPrimaryPrincipal();
//        获取对象的角色
        List<Role> roleList=roleMapper.findByUserId(user.getId());
        if(!roleList.isEmpty()){
            SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
            for(Role r:roleList){
                authorizationInfo.addRole(r.getRolename());
            }
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username=token.getUsername();
        User user=userMapper.findByUserName(username);
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        return null;
    }
}
