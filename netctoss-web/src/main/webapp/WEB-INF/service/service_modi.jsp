<%@page pageEncoding="UTF-8" isELIgnored="false" %>
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
    //显示资费详细信息
    function showDetail(flag, a) {
      var detailDiv = a.parentNode.getElementsByTagName("div")[0];
      if (flag) {
        detailDiv.style.display = "block";
      } else {
        detailDiv.style.display = "none";
      }
    }
    //分页
    function goPage(page) {
      var form = document.getElementById('serviceList');
      form.action = "serviceList.action?page=" + page;
      form.submit();
    }
  </script>
  <script type="text/javascript">
    //暂停
    function setPauseState(id) {
      var r = window.confirm("确定要暂停此业务帐号吗?");
      if (r) {
        //发送ajax请求
        $.post(
            "service_pause.action",//请求url
            {"id": id},//提交参数
            function (data) {//data是服务器返回的数据
              if (data) {
                $('#operate_result_info span').text("暂停成功！");
                document.getElementById("operate_result_info").style.display = "block";
              } else {
                $('#operate_result_info span').text("暂停失败！数据并发错误。");
                document.getElementById("operate_result_info").style.display = "block";
              }
            }
        );
      } else {
        return;
      }
    }
    //开通业务
    function setStartState(id, accountId) {
      var r = window.confirm("确定要开通此业务帐号吗?");
      if (r) {
        //发送ajax请求
        $.post(
            "service_start.action",
            {"id": id, "accountId": accountId},
            function (data) {
              if (data) {
                $('#operate_result_info span').text('开通成功！');
                document.getElementById("operate_result_info").style.display = "block";
              } else {
                $('#operate_result_info span').text('开通失败，其帐务帐号为暂停或删除状态！');
                document.getElementById("operate_result_info").style.display = "block";
              }
            }
        );
      } else {
        return;
      }
    }
    //删除业务
    function deleteAccount(id) {
      var r = window.confirm("确定要删除此业务账号吗？删除后将不能恢复。");
      if (r) {
        //发送ajax请求
        $.post(
            "service_del.action",
            {"id": id},
            function (data) {
              if (data) {
                $('#operate_result_info span').text('删除成功！');
                document.getElementById("operate_result_info").style.display = "block";
              } else {
                $('#operate_result_info span').text('删除失败！数据并发错误。');
                document.getElementById("operate_result_info").style.display = "block";
              }
            }
        );
      } else {
        return;
      }
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
    <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
    <li><a href="../account/account_list.html" class="account_off"></a></li>
    <li><a href="../service/service_list.html" class="service_on"></a></li>
    <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
    <li><a href="../report/report_list.html" class="report_off"></a></li>
    <li><a href="../user/user_info.html" class="information_off"></a></li>
    <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
  </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<!--主要区域开始-->
<div id="main">
  <!--保存操作的提示信息-->
  <div id="save_result_info" class="save_fail">资费修改失败！数据并发错误。</div>
  <s:form action="service_modi" method="post" cssClass="main_form" theme="simple">
    <!--必填项-->
    <div class="text_info clearfix"><span>业务账号ID：</span></div>
    <div class="input_info">
      <s:textfield name="service.id" cssClass="readonly" readonly="true">
      </s:textfield>
    </div>
    <div class="text_info clearfix"><span>OS 账号：</span></div>
    <div class="input_info">
      <s:textfield name="service.osUsername" cssClass="readonly" readonly="true">
      </s:textfield>
    </div>
    <div class="text_info clearfix"><span>服务器 IP：</span></div>
    <div class="input_info">
      <s:textfield name="service.unixHost" cssClass="readonly" readonly="true">
      </s:textfield>
    </div>
    <div class="text_info clearfix"><span>资费类型：</span></div>
    <div class="input_info">
      <s:select name="service.cost.id" list="costs" listKey="id" listValue="name"
                cssClass="width150">
      </s:select>
      <div id="costMsg" class="validate_msg_long"></div>
    </div>
    <!--操作按钮-->
    <div class="button_info clearfix">
      <input type="submit" value="保存" class="btn_save"/>
      <input type="button" value="取消" class="btn_save" onclick="location.href='serviceList.action'"/>
    </div>


    <p>业务说明：<br/>
      1、修改资费后，点击保存，并未真正修改数据库中的数据；<br/>
      2、提交操作到消息队列；<br/>
      3、每月月底由程序自动完成业务所关联的资费；</p>

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
