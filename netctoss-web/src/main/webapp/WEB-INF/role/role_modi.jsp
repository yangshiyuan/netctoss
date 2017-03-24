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
    //保存成功的提示消息
    function showResult() {
      showResultDiv(true);
      window.setTimeout("showResultDiv(false);", 3000);
    }
    function showResultDiv(flag) {
      var divResult = document.getElementById("save_result_info");
      if (flag)
        divResult.style.display = "block";
      else
        divResult.style.display = "none";
    }
    //提示是否保存成功
    $(function () {
      var status = '${status}';
      if (status == 'ok') {
        $('#save_result_info').text('保存成功！');
        showResult();
      } else if (status == 'error') {
        $('#save_result_info').text('保存失败，角色名称重复！');
        showResult();
      }
    });

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
  <!--保存操作后的提示信息：成功或者失败-->
  <div id="save_result_info" class="save_success">保存成功！</div>
  <s:form action="role_modi.action" method="post" cssClass="main_form" theme="simple">
    <s:hidden name="role.id"></s:hidden>
    <div class="text_info clearfix"><span>角色名称：</span></div>
    <div class="input_info">
      <s:textfield name="role.name" cssClass="width200"></s:textfield>
      <span class="required">*</span>
      <div class="validate_msg_medium error_msg">不能为空，且为20长度的字母、数字和汉字的组合</div>
    </div>
    <div class="text_info clearfix"><span>设置权限：</span></div>
    <div class="input_info_high">
      <div class="input_info_scroll">
        <ul>
          <s:iterator value="privileges" var="p">
            <li><input type="checkbox" name="ids" id="${p.id}" value="${p.id }"/>${p.name }</li>
          </s:iterator>
        </ul>
      </div>
      <span class="required">*</span>
      <div class="validate_msg_tiny">至少选择一个权限</div>
    </div>
    <div class="button_info clearfix">
      <input type="submit" value="保存" class="btn_save"/>
      <input type="button" value="取消" class="btn_save"/>
    </div>
  </s:form>
</div>
<!--主要区域结束-->
<div id="footer">
  <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
  <p>版权所有(C)加拿大达内IT培训集团公司 </p>
</div>
</body>
</html>
