package com.netctoss.interceptor;

import java.util.Map;

import com.netctoss.entity.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 登陆检查拦截器
 */
public class LoginInterceptor
        implements Interceptor {

    public void destroy() {

    }

    public void init() {

    }

    public String intercept(ActionInvocation ai)
            throws Exception {
        //1.从Session中取admin
        Map<String, Object> session =
                ai.getInvocationContext().getSession();
        Admin admin = (Admin) session.get("admin");
        //2.判断是否登陆成功
        if (admin == null) {
            //如果admin为空，说明没有登陆成功，转到登陆页面
            return "login";
        } else {
            //不为空，说明登陆成功
            return ai.invoke();
        }
    }

}
