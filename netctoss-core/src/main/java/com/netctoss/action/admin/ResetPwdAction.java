package com.netctoss.action.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.admin.IAdminDAO;

public class ResetPwdAction {

    //input
    private String ids;
    //output
    private Map<String, Object> info =
            new HashMap<String, Object>();

    public String execute() {
        IAdminDAO dao = DAOFactory.getAdminDAO();
        try {
            if (ids != null && ids.length() > 0) {
                String[] idArray = ids.split(",");
                List<Integer> list =
                        new ArrayList<Integer>();
                for (String id : idArray) {
                    list.add(Integer.valueOf(id));
                }
                dao.resetPassword(list);
            }

            info.put("success", true);
            info.put("message", "密码重置成功.");
        } catch (DAOException e) {
            e.printStackTrace();
            info.put("success", false);
            info.put("message", "系统错误，请联系系统管理员.");
        }
        return "success";
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

}
