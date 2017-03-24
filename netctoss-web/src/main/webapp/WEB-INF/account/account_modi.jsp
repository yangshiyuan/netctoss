<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>达内－NetCTOSS</title>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
  <script language="javascript" type="text/javascript">
    //保存成功的提示信息
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

    //显示修改密码的信息项
    function showPwd(chkObj) {
      if (chkObj.checked)
        document.getElementById("divPwds").style.display = "block";
      else
        document.getElementById("divPwds").style.display = "none";
    }
  </script>
  <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
  <script type="text/javascript">
    //验证两次输入的新密码是否相同
    $(function () {
      $('#repeateNewPassword').blur(function () {
        //获得新密码
        var newPassword = $('#newPassword').val();
        //获得重复的新密码
        var repeateNewPassword = $('#repeateNewPassword').val();
        if (newPassword == repeateNewPassword) {
          $('#repeateNewPasswordMsg').html('两次输入的密码相同');
        } else {
          $('#repeateNewPasswordMsg').html('两次输入的密码不相同,请重新输入！');
        }
      });
    });
    //显示修改是否成功
    $(function () {
      //获得返回的信息
      var flag = '${flag}';
      if (flag == 'ok') {
        $('#save_result_info').html('保存成功');
        showResult();
      } else if (flag == 'error') {
        $('#save_result_info').html('保存失败，请稍后重试！');
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
  <!--保存成功或者失败的提示消息-->
  <div id="save_result_info" class="save_fail">保存失败，旧密码错误！</div>
  <s:form action="account_update" cssClass="main_form" method="post" theme="simple">
    <!--必填项-->
    <div class="text_info clearfix"><span>账务账号ID：</span></div>
    <div class="input_info">
      <s:textfield id="id" name="account.id" cssClass="readonly" readonly="true"></s:textfield>
    </div>
    <div class="text_info clearfix"><span>姓名：</span></div>
    <div class="input_info">
      <s:textfield name="account.realName"></s:textfield>
      <span class="required">*</span>
      <div class="validate_msg_long error_msg">20长度以内的汉字、字母和数字的组合</div>
    </div>
    <div class="text_info clearfix"><span>身份证：</span></div>
    <div class="input_info">
      <s:textfield name="account.idcardNo" cssClass="readonly" readonly="true"></s:textfield>
    </div>
    <div class="text_info clearfix"><span>登录账号：</span></div>
    <div class="input_info">
      <s:textfield name="account.loginName" cssClass="readonly" readonly="true"></s:textfield>
      <div class="change_pwd">
        <input id="chkModiPwd" type="checkbox" name="modiPwd" value="1" onclick="showPwd(this);"/>
        <label for="chkModiPwd">修改密码</label>
      </div>
    </div>
    <!--修改密码部分-->
    <div id="divPwds">
      <div class="text_info clearfix"><span>旧密码：</span></div>
      <div class="input_info">
        <s:textfield name="oldPassword" id="oldPassword"></s:textfield>
        <span class="required">*</span>
        <div id="oldPasswordMsg" class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
      </div>
      <div class="text_info clearfix"><span>新密码：</span></div>
      <div class="input_info">
        <s:textfield name="newPassword" id="newPassword"></s:textfield>
        <span class="required">*</span>
        <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
      </div>
      <div class="text_info clearfix"><span>重复新密码：</span></div>
      <div class="input_info">
        <s:textfield name="repeateNewPassword" id="repeateNewPassword"></s:textfield>
        <span class="required">*</span>
        <div id="repeateNewPasswordMsg" class="validate_msg_long">两次密码必须相同</div>
      </div>
    </div>
    <div class="text_info clearfix"><span>电话：</span></div>
    <div class="input_info">
      <s:textfield name="account.telephone" cssClass="width200"></s:textfield>
      <span class="required">*</span>
      <div class="validate_msg_medium error_msg">正确的电话号码格式：手机或固话</div>
    </div>
    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
    <div class="input_info">
      <s:textfield name="recommenderIdcardNo" cssClass="width200"></s:textfield>
      <div class="validate_msg_long error_msgs">正确的身份证号码格式</div>
    </div>
    <div class="text_info clearfix"><span>生日：</span></div>
    <div class="input_info">
      <s:textfield name="account.birthdate" cssClass="readonly" readonly="true"></s:textfield>
    </div>
    <div class="text_info clearfix"><span>Email：</span></div>
    <div class="input_info">
      <s:textfield name="account.email" cssClass="width200"></s:textfield>
      <div class="validate_msg_medium">50长度以内，合法的 Email 格式</div>
    </div>
    <div class="text_info clearfix"><span>职业：</span></div>
    <div class="input_info">
      <s:select name="account.occupation" list="#{'0':'干部','1':'学生','2':'技术人员','3':'其他'}">
      </s:select>
    </div>
    <div class="text_info clearfix"><span>性别：</span></div>
    <div class="input_info fee_type">
      <s:select name="account.gender" list="#{'0':'女','1':'男'}">
      </s:select>
    </div>
    <div class="text_info clearfix"><span>通信地址：</span></div>
    <div class="input_info">
      <s:textfield name="account.mailAddress" cssClass="width350"></s:textfield>
      <div class="validate_msg_tiny">50长度以内</div>
    </div>
    <div class="text_info clearfix"><span>邮编：</span></div>
    <div class="input_info">
      <s:textfield name="account.zipcode"></s:textfield>
      <div class="validate_msg_long">6位数字</div>
    </div>
    <div class="text_info clearfix"><span>QQ：</span></div>
    <div class="input_info">
      <s:textfield name="account.qq"></s:textfield>
      <div class="validate_msg_long">5到13位数字</div>
    </div>
    <!--操作按钮-->
    <div class="button_info clearfix">
      <input type="submit" value="保存" class="btn_save"/>
      <input type="button" value="取消" class="btn_save" onclick="accountList.action"/>
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
