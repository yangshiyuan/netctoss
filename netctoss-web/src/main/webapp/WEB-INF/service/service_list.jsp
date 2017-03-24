<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>达内－NetCTOSS</title>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css"/>
  <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css"/>
  <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
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
            {"id": id},
            function (data) {
              alert(data)
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
<div id="main">
  <s:form id="serviceList" action="serviceList" method="post" theme="simple">
    <!--查询-->
    <div class="search_add">
      <div>OS 账号：
        <s:textfield name="osUsername" cssClass="width100 text_search"></s:textfield>
      </div>
      <div>服务器 IP：
        <s:textfield name="unixHost" cssClass="width100 text_search"></s:textfield>
      </div>
      <div>身份证：
        <s:textfield name="idcardNo" cssClass="text_search"></s:textfield>
      </div>
      <div>状态：
        <s:select name="status" list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}"
                  cssClass="select_search">
        </s:select>
      </div>
      <div>
        <input type="submit" value="搜索" class="btn_search"/>
      </div>
      <input type="button" value="增加" class="btn_add" onclick="location.href='toAddService.action';"/>
    </div>
    <!--删除的操作提示-->
    <div id="operate_result_info" class="operate_success">
      <img src="images/close.png" onclick="this.parentNode.style.display='none';"/>
      <span> 删除成功！</span>
    </div>
    <!--数据区域：用表格展示数据-->
    <div id="data">
      <table id="datalist">
        <tr>
          <th class="width50">业务ID</th>
          <th class="width70">账务账号ID</th>
          <th class="width150">身份证</th>
          <th class="width70">姓名</th>
          <th>OS 账号</th>
          <th class="width50">状态</th>
          <th class="width100">服务器 IP</th>
          <th class="width100">资费</th>
          <th class="width200">详情</th>
        </tr>
        <s:iterator value="serviceVos" var="s">
          <tr>
            <td><a href="service_detail.action?id=${s.id }" title="查看明细">${s.id }</a></td>
            <td>${s.accountId }</td>
            <td>${s.idcardNo }</td>
            <td>${s.realName }</td>
            <td>${s.osUserName }</td>
            <td>
              <s:if test="#s.status == 0">
                开通
              </s:if>
              <s:elseif test="#s.status == 1">
                暂停
              </s:elseif>
              <s:else>
                删除
              </s:else>
            </td>
            <td>${s.unixHost }</td>
            <td>
              <a class="summary" onmouseover="showDetail(true,this);"
                 onmouseout="showDetail(false,this);">${s.costName }</a>
              <!--浮动的详细信息-->
              <div id="costDescr" class="detail_info">
                  ${s.descr }
              </div>
            </td>
            <s:if test="#s.status == 0">
              <td class="td_modi">
                <input type="button" value="暂停" class="btn_pause"
                       onclick="location.href='service_pause.action?id=${s.id }';"/>
                <input type="button" value="修改" class="btn_modify"
                       onclick="location.href='service_load.action?id=${s.id }';"/>
                <input type="button" value="删除" class="btn_delete"
                       onclick="location.href='service_del.action?id=${s.id }';"/>
              </td>
            </s:if>
            <s:elseif test="#s.status == 1">
              <td class="td_modi">
                <input type="button" value="开通" class="btn_pause" onclick="setStartState(${s.id},${s.accountId });"/>
                <input type="button" value="修改" class="btn_modify"
                       onclick="location.href='service_load.action?id=${s.id }';"/>
                <input type="button" value="删除" class="btn_delete"
                       onclick="location.href='service_del.action?id=${s.id }';"/>
              </td>
            </s:elseif>
            <!--
                        <s:else>
                         <td class="td_modi">
                            <input type="button" value="开通" class="btn_pause"/>
                            <input type="button" value="修改" class="btn_modify"  />
                            <input type="button" value="删除" class="btn_delete" />
                         </td>
                        </s:else>  
                         -->

          </tr>
        </s:iterator>
      </table>
      <p>业务说明：<br/>
        1、创建即开通，记载创建时间；<br/>
        2、暂停后，记载暂停时间；<br/>
        3、重新开通后，删除暂停时间；<br/>
        4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br/>
        5、业务账号不设计修改密码功能，由用户自服务功能实现；<br/>
        6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
    </div>
    <!--分页-->
    <div id="pages">
      <s:if test="totalPage > 1">
        <a href="javascript:;" onclick="goPage(1);">首页</a>
        <s:if test="page > 1">
          <a href="javascript:;" onclick="goPage(${page-1 });">上一页</a>
        </s:if>
        <s:else>
          <a href="javascript:;">上一页</a>
        </s:else>
        <s:iterator value="new int[totalPage]" status="s">
          <s:if test="page == #s.count">
            <a href="javascript:;" class="current_page" onclick="goPage(${s.count });">${s.count }</a>
          </s:if>
          <s:else>
            <a href="javascript:;" onclick="goPage(${s.count });">${s.count }</a>
          </s:else>
        </s:iterator>
        <s:if test="page < totalPage">
          <a href="javascript:;" onclick="goPage(${page+1 });">下一页</a>
        </s:if>
        <s:else>
          <a href="javascript:;">下一页</a>
        </s:else>
        <a href="javascript:;" onclick="goPage(${totalPage })" ;>末页</a>
      </s:if>
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
