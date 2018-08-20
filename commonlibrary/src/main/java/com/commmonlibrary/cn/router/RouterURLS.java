package com.commmonlibrary.cn.router;

/**
 * @Created by TOME .
 * @时间 2018/4/26 10:20
 * @描述 ${路由地址}
 */
// 注意事项！！！  /模块/页面名称   或  /模块/子模块../页面名称   至少两级  例子 /user/login /user/forgetpwd
//url 第一次相同会报错!!!
public interface RouterURLS {

    String LAUNCH = "/main/launch";
    String MAIN_MAIN = "/main/main";
    String USER_LOGIN = "/user/login";



}
