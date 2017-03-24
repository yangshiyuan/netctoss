﻿<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>达内－NetCTOSS</title>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
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
  <s:form cssClass="main_form" theme="simple">
    <div class="text_info clearfix"><span>资费ID：</span></div>
    <div class="input_info">
      <s:textfield name="cost.id" cssClass="readonly" readonly="true"></s:textfield>
    </div>
    <div class="text_info clearfix"><span>资费名称：</span></div>
    <div class="input_info">
      <s:textfield name="cost.name" cssClass="readonly" readonly="true"></s:textfield>
    </div>
    <div class="text_info clearfix"><span>资费状态：</span></div>
    <div class="input_info">
      <s:select name="cost.status" cssClass="readonly" disabled="true"
                list="#{'0':'暂停','1':'开通','2':'删除'}">
      </s:select>
    </div>
    <div class="text_info clearfix"><span>资费类型：</span></div>
    <div class="input_info fee_type">
      <s:radio name="cost.costType" list="#{'0':'包月','1':'套餐','2':'计时'}" disabled="true">
      </s:radio>
    </div>
    <div class="text_info clearfix"><span>基本时长：</span></div>
    <div class="input_info">
      <s:textfield name="cost.baseDuration" cssClass="readonly" readonly="true">
      </s:textfield>
      <span>小时</span>
    </div>
    <div class="text_info clearfix"><span>基本费用：</span></div>
    <div class="input_info">
      <s:textfield name="cost.baseCost" cssClass="readonly" readonly="true">
      </s:textfield>
      <span>元</span>
    </div>
    <div class="text_info clearfix"><span>单位费用：</span></div>
    <div class="input_info">
      <s:textfield name="cost.unitCost" cssClass="readonly" readonly="true">
      </s:textfield>
      <span>元/小时</span>
    </div>
    <div class="text_info clearfix"><span>创建时间：</span></div>
    <div class="input_info">
      <s:textfield name="cost.createTime" cssClass="readonly" readonly="true">
      </s:textfield>
    </div>
    <div class="text_info clearfix"><span>启动时间：</span></div>
    <div class="input_info">
      <s:textfield name="cost.startTime" cssClass="readonly" readonly="true">
      </s:textfield>

    </div>
    <div class="text_info clearfix"><span>资费说明：</span></div>
    <div class="input_info_high">
      <s:textarea name="cost.descr" cssClass="width300 height70 readonly" readonly="true">
      </s:textarea>
    </div>
    <div class="button_info clearfix">
      <input type="button" value="返回" class="btn_save" onclick="location.href='feeList.action';"/>
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
