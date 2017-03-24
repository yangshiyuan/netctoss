package com.netctoss.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

/**
 * 所有Action的父类，可以将通用的代码
 * 提取出来放到父类中。
 * 这里，我们让BaseAction实现接口SessionAware，
 * 那么继承与BaseAction的Action就相当于实现了
 * 这个接口。在实例化Action之后，Struts2会自动
 * 调用setSession方法把session注入给Action，
 * 这里我们利用全局变量session来接收，Action
 * 可以在调用业务方法时使用它来调用session。
 */
public class BaseAction implements SessionAware {

    protected Map<String, Object> session;

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
