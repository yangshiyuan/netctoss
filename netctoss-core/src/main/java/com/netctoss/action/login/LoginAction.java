package com.netctoss.action.login;

import com.netctoss.action.BaseAction;
import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.ILoginDAO;
import com.netctoss.entity.Admin;

public class LoginAction
        extends BaseAction {

    //input
    private String adminCode;
    private String password;
    private String code;
    //output
    private String errorMsg; //提示信息

    public String execute() {
        //判断验证码输入是否正确
        String imageCode = (String)
                session.get("imageCode");
        if (code == null
                || imageCode == null
                || !code.equalsIgnoreCase(imageCode)) {
            errorMsg = "验证码输入有误！";
            return "fail";
        }

        ILoginDAO dao = DAOFactory.getLoginDAO();
        try {
            //判断用户名是否存在
            Admin admin = dao.findByCode(adminCode);
            if (admin == null) {
                //不存在，给予提示
                errorMsg = "账号不存在！";
                return "fail";
            } else {
                //判断密码是否正确
                String pwd = admin.getPassword();
                if (!pwd.equals(password)) {
                    errorMsg = "密码错误！";
                    return "fail";
                }
            }
            //    到这，说明登陆成功
            session.put("admin", admin);
        } catch (DAOException e) {
            e.printStackTrace();
            return "error";
        }

        return "success";
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
