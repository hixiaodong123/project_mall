package com.cskaoyan.mall.bean.base;

/**
 * @description: 登录信息返回
 * @author: Lime
 * @create: 2019-08-16 13:11
 **/

public class UserInfo
{
    String avatar;//头像图片地址
    String name;
    String[] perms;
    String[] roles;

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getPerms()
    {
        return perms;
    }

    public void setPerms(String[] perms)
    {
        this.perms = perms;
    }

    public String[] getRoles()
    {
        return roles;
    }

    public void setRoles(String[] roles)
    {
        this.roles = roles;
    }
}
