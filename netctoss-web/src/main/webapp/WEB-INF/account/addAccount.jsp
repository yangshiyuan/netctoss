<%@page pageEncoding="utf-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>达内－NetCTOSS</title>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
  <script language="javascript" type="text/javascript" src="../js/jquery-1.4.3.js"></script>
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

    //显示选填的信息项
    function showOptionalInfo(imgObj) {
      var div = document.getElementById("optionalInfo");
      if (div.className == "hide") {
        div.className = "show";
        imgObj.src = "../images/hide.png";
      }
      else {
        div.className = "hide";
        imgObj.src = "../images/show.png";
      }
    }
    function checkIdcardNo(idcardNo) {
      //1.校验身份证号是否为空
      if (idcardNo == null || idcardNo == "") {
        document.getElementById("idcardNoMsg").innerHTML = "请输入身份证号！";
        return;
      }
      //2.校验身份证号的格式
      var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
      if (!reg.test(idcardNo)) {
        document.getElementById("idcardNoMsg").innerHTML = "请输入正确的身份证号！";
        return;
      }
      //3.从身份证号中截取出生日
      var year = idcardNo.substring(6, 10);
      var month = idcardNo.substring(10, 12);
      var day = idcardNo.substring(12, 14);
      var birthday = year + "-" + month + "-" + day;
      document.getElementById("birthday").value = birthday;

      document.getElementById("idcardNoMsg").innerHTML = "正确的身份证号码格式";
    }
    $(function () {
      //页面加载完执行下面的内容
      $("#recommenderIdcardNo").blur(function () {
        var idcardNo = $(this).val();
        //校验身份证号的格式
        var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
        if (!reg.test(idcardNo)) {
          $("#recommenderIdcardNoMsg").text("请输入正确的身份证号！");
          return;
        }
        //根据推荐人身份证号查询推荐人的数据
        $.post(
            "searchAccount",
            {"idcardNo": idcardNo},
            function (data) {
              var account = data;
              if (account == null) {
                $("#recommenderIdcardNoMsg").text("不存在该推荐人，请重新输入！");
              } else {
                $("#recommenderId").val(account.id);
                $("#recommenderIdcardNoMsg").text("正确的身份证号码格式");
              }
            }
        );
      });
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
    <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
    <li><a href="../account/account_list.html" class="account_on"></a></li>
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
  <div id="main">
    <!--保存成功或者失败的提示消息-->
    <div id="save_result_info" class="save_fail">保存失败，该身份证已经开通过账务账号！</div>
    <s:form action="account_add" method="post" cssClass="main_form" theme="simple">
      <!--必填项-->
      <div class="text_info clearfix"><span>姓名：</span></div>
      <div class="input_info">
        <s:textfield name="account.realName"></s:textfield>
        <span class="required">*</span>
        <div class="validate_msg_long">20长度以内的汉字、字母和数字的组合</div>
      </div>
      <div class="text_info clearfix"><span>身份证：</span></div>
      <div class="input_info">
        <s:textfield name="account.idcardNo"></s:textfield>
        <span class="required">*</span>
        <div class="validate_msg_long">正确的身份证号码格式</div>
      </div>
      <div class="text_info clearfix"><span>登录账号：</span></div>
      <div class="input_info">
        <s:textfield name="account.loginName"></s:textfield>
        <span class="required">*</span>
        <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
      </div>
      <div class="text_info clearfix"><span>密码：</span></div>
      <div class="input_info">
        <input type="password" name="account.loginPasswd"/>
        <span class="required">*</span>
        <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
      </div>
      <div class="text_info clearfix"><span>重复密码：</span></div>
      <div class="input_info">
        <input type="password"/>
        <span class="required">*</span>
        <div class="validate_msg_long">两次密码必须相同</div>
      </div>
      <div class="text_info clearfix"><span>电话：</span></div>
      <div class="input_info">
        <s:textfield name="account.telephone" cssClass="width200">
        </s:textfield>
        <span class="required">*</span>
        <div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
      </div>
      <!--可选项-->
      <div class="text_info clearfix"><span>可选项：</span></div>
      <div class="input_info">
        <img src="images/show.png" alt="展开" onclick="showOptionalInfo(this);"/>
      </div>
      <div id="optionalInfo" class="hide">
        <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
        <div class="input_info">
          <s:textfield name="recommender_idcardNo"></s:textfield>
          <div class="validate_msg_long">正确的身份证号码格式</div>
        </div>
        <div class="text_info clearfix"><span>生日：</span></div>
        <div class="input_info">
          <s:textfield name="account.birthdate" cssClass="readonly" readonly="true"></s:textfield>
        </div>
        <div class="text_info clearfix"><span>Email：</span></div>
        <div class="input_info">
          <s:textfield name="account.email" cssClass="width350">
          </s:textfield>
          <div class="validate_msg_tiny">50长度以内，合法的 Email 格式</div>
        </div>
        <div class="text_info clearfix"><span>职业：</span></div>
        <div class="input_info">
          <s:select name="account.occupation" list="#{'0':'干部','1':'学生','2':'技术人员','3':'其他'}"
          >
          </s:select>
        </div>
        <div class="text_info clearfix"><span>性别：</span></div>
        <div class="input_info fee_type">
          <s:radio name="account.gender" list="#{'0':'女','1':'男'}"></s:radio>
        </div>
        <div class="text_info clearfix"><span>通信地址：</span></div>
        <div class="input_info">
          <s:textfield name="account.mailAddress" cssClass="width350">
          </s:textfield>
          <div class="validate_msg_tiny">50长度以内</div>
        </div>
        <div class="text_info clearfix"><span>邮编：</span></div>
        <div class="input_info">
          <s:textfield name="account.zipcode" cssClass="width350">
          </s:textfield>
          <div class="validate_msg_long">6位数字</div>
        </div>
        <div class="text_info clearfix"><span>QQ：</span></div>
        <div class="input_info">
          <s:textfield name="account.qq" cssClass="width350">
          </s:textfield>
          <div class="validate_msg_long">5到13位数字</div>
        </div>
      </div>
      <!--操作按钮-->
      <div class="button_info clearfix">
        <input type="submit" value="保存" class="btn_save"/>
        <input type="button" value="取消" class="btn_save"/>
      </div>
    </s:form>
  </div>
  <!--主要区域结束-->
  <div id="footer">
    <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
    <br/>
    <span>版权所有(C)加拿大达内IT培训集团公司 </span>
  </div>
</body>
</html>
