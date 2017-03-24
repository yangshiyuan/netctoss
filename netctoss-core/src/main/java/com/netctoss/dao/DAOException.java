package com.netctoss.dao;

/**
 * 自定义异常，
 * 当我们自己写业务代码，捕获到异常时，
 * 将异常信息包装成自定义异常来抛出。
 * 这样，我们的同事在捕获到这类异常时，
 * 会知道异常已经被自己的同事处理过了，
 * 他不需要再次处理，抛出即可。
 */
public class DAOException extends Exception {

    public DAOException(String msg, Throwable t) {
        super(msg, t);
    }

}
