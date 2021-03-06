﻿<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>达内－NetCTOSS</title>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
  <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
  <script language="javascript" type="text/javascript">
    function deleteRole(id) {
      var r = window.confirm("确定要删除此角色吗？");
      if (!r) {
        return;
      }
      $.post(
          "role_del",
          {"id": id},
          function (data) {
            var ok = data
            if (ok) {
              $('#operate_result_info span').text('删除成功！');
              document.getElementById("operate_result_info").style.display = "block";
            } else {
              $('#operate_result_info span').text('删除错误！该角色被使用，不能删除。');
              document.getElementById("operate_result_info").style.display = "block";
            }
          }
      );

    }
  </script>
</head>
<body>
<!--Logo区域开始-->
<div id="header">
  <img src="../images/logo.png" alt="logo" class="left"/>
  <a href="#">[退出]</a>
</div>
<!--Logo区域结束-->
<!--导航区域开始-->
<div id="navi">
  <ul id="menu">
    <li><a href="../index.html" class="index_off"></a></li>
    <li><a href="../role/role_list.html" class="role_off"></a></li>
    <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
    <li><a href="../fee/fee_list.html" class="fee_on"></a></li>
    <li><a href="../account/account_list.html" class="account_off"></a></li>
    <li><a href="../service/service_list.html" class="service_off"></a></li>
    <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
    <li><a href="../report/report_list.html" class="report_off"></a></li>
    <li><a href="../user/user_info.html" class="information_off"></a></li>
    <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
  </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
  <form action="" method="">
    <!--查询-->
    <div class="search_add">
      <input type="button" value="增加" class="btn_add" onclick="location.href='role_index.action';"/>
    </div>
    <!--删除的操作提示-->
    <div id="operate_result_info" class="operate_success">
      <img src="images/close.png" onclick="this.parentNode.style.display='none';"/>
      <span>删除成功！</span>
    </div> <!--删除错误！该角色被使用，不能删除。-->
    <!--数据区域：用表格展示数据-->
    <div id="data">
      <table id="datalist">
        <tr>
          <th>角色 ID</th>
          <th>角色名称</th>
          <th class="width600">拥有的权限</th>
          <th class="td_modi"></th>
        </tr>
        <s:iterator value="roles" var="r">
          <tr>
            <td>${r.id }</td>
            <td>${r.name }</td>
            <td>${r.privilegeNames }</td>
            <td>
              <input type="button" value="修改" class="btn_modify"
                     onclick="location.href='role_load.action?id=${r.id }';"/>
              <input type="button" value="删除" class="btn_delete"
                     onclick="location.href='role_del.action?id=${r.id }';"/>
            </td>
          </tr>
        </s:iterator>
      </table>
    </div>
    <!--分页-->
    <div id="pages">
      <s:if test="page > 1">
        <a href="javascript:;" onclick="location.href='roleList.action?page=${page-1 }'">上一页</a>
      </s:if>
      <s:else>
        <a href="javascript:;">上一页</a>
      </s:else>
      <s:iterator value="new int[totalPage]" status="i">
        <s:if test="#i.count == page">
          <a href="javascript:;" class="current_page"
             onclick="location.href='roleList.action?page=${i.count }'">${i.count }</a>
        </s:if>
        <s:else>
          <a href="javascript:;" onclick="location.href='roleList.action?page=${i.count }'">${i.count }</a>
        </s:else>
      </s:iterator>
      <s:if test="page < totalPage">
        <a href="javascript:;" onclick="location.href='roleList.action?page=${page+1 }'">下一页</a>
      </s:if>
      <s:else>
        <a href="javascript:;">下一页</a>
      </s:else>
    </div>
  </form>
</div>
<!--主要区域结束-->
<div id="footer">
  <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
  <p>版权所有(C)加拿大达内IT培训集团公司 </p>
</div>
</body>
</html>
