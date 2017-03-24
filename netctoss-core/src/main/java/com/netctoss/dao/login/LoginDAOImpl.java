package com.netctoss.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.netctoss.dao.DAOException;
import com.netctoss.entity.Admin;
import com.netctoss.util.DBUtil;

public class LoginDAOImpl implements ILoginDAO {

    public Admin findByCode(String adminCode)
            throws DAOException {
        if (adminCode == null)
            return null;

        String sql = "select * from admin_info " +
                "where admin_code=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setString(1, adminCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = createAdmin(rs);
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(
                    "查询管理员失败！", e);
        } finally {
            DBUtil.closeConnection();
        }

        return null;
    }

    private Admin createAdmin(ResultSet rs) throws SQLException {
        Admin a = new Admin();
        a.setId(rs.getInt("id"));
        a.setAdminCode(rs.getString("admin_code"));
        a.setPassword(rs.getString("password"));
        a.setName(rs.getString("name"));
        a.setTelephone(rs.getString("telephone"));
        a.setEmail(rs.getString("email"));
        a.setEnrollDate(rs.getDate("enrolldate"));
        return a;
    }

    public static void main(String[] args)
            throws Exception {
        ILoginDAO dao = new LoginDAOImpl();
        Admin a = dao.findByCode("lhh");
        System.out.println(a.getId() + "  " +
                a.getName());
    }

}
