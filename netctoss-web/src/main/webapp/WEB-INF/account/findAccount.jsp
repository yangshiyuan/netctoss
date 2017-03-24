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
    //删除 <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(<s:property value="id"/>);" />
    function deleteAccount(id) {
      var r = window.confirm("确定要删除此账务账号吗？\r\n删除后将不能恢复，且会删除其下属的所有业务账号。");
      if (!r) {
        return;
      }
      $.post("deleteAccount", {"id": id},
          function (data) {
            var ok = data;
            if (ok) {
              alert("删除成功！");
              var currPage = $("#currPage").val();
              toPage(currPage);
            } else {
              alert("删除失败！");
            }
          }
      );
      //        <action name="deleteAccount"  class="deleteAccountAction"  method="deleteAccount">
      //  	    <result name="success" type="json"> <param name="root">ok</param>   </result>
      //     </action>
      document.getElementById("operate_result_info").style.display = "block";

    }
    //开通或暂停
    function setState() {
      var r = window.confirm("确定要开通此账务账号吗？");
      document.getElementById("operate_result_info").style.display = "block";
    }
    //分页请求
    function toPage(currPage) {
      document.getElementById("currPage").value = currPage;
      document.forms[0].submit();
    }
    //开通
    function start(id) {
      var r = window.confirm("确定要开通此账务账号吗？");
      if (!r) {
        return;
      }
      $.post(
          "startAccount",
          {"id": id},
          function (data) {
            var ok = data;
            if (ok) {
              alert("开通成功！");
              var currPage = $("#currPage").val();
              toPage(currPage);
            } else {
              alert("开通失败！");
            }
          }
      );
    }
    //暂停账号
    function pause(id) {
      var r = window.confirm("确定要暂停此账务账号吗？");
      if (!r) {
        return;
      }
      $.post(
          "pauseAccount",
          {"id": id},
          function (data) {
            var ok = data;
            if (ok) {
              alert("暂停成功！");
              var currPage = $("#currPage").val();
              toPage(currPage);
            } else {
              alert("暂停失败！");
            }
          }
      );
    }

    //查询具体信息
    function goAccountDetail(id, page) {
      alert("aaa");
      var form = document.getElementById('searchForm');
      alert(form);
      form.action = "showAccount.action?id=" + id + "&page=" + page;
      form.submit();
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
  <form action="findAccount" method="post">
    <!-- 追加隐藏的文本框，可以提交当前页 -->
    <s:hidden name="page" id="currPage"></s:hidden>
    <!--查询-->
    <div class="search_add">
      <div>
        身份证：
        <s:textfield name="idcardNo" cssClass="text_search"></s:textfield>
      </div>
      <div>
        姓名：
        <s:textfield name="realName" cssClass="width70 text_search"></s:textfield>
      </div>
      <div>
        登录名：
        <s:textfield name="loginName" cssClass="text_search"></s:textfield>
      </div>
      <div>
        状态：
        <s:select name="status" list="#{'-1':'全部','0':'开通','1':'暂停','2':'删除'}" cssClass="select_search">
        </s:select>
      </div>
      <div><input type="button" value="搜索" class="btn_search" onclick="toPage(1)"/></div>
      <input type="button" value="增加" class="btn_add" onclick="location.href='addAccount.action';"/>
    </div>
    <!--删除等的操作提示-->
    <div id="operate_result_info" class="operate_success">
      <img src="../images/close.png" onclick="this.parentNode.style.display='none';"/>
      删除成功，且已删除其下属的业务账号！
    </div>
    <!--数据区域：用表格展示数据-->
    <div id="data">
      <table id="datalist">
        <tr>
          <th>账号ID</th>
          <th>姓名</th>
          <th class="width150">身份证</th>
          <th>登录名</th>
          <th>状态</th>
          <th class="width100">创建日期</th>
          <th class="width150">上次登录时间</th>
          <th class="width200"></th>
        </tr>
        <s:iterator value="accounts">
          <tr>
            <td><s:property value="id"/></td>
            <td><a href="showAccount.action?id=<s:property value="id"/>"><s:property value="realName"/></a></td>

            <td><s:property value="idcardNo"/></td>
            <td><s:property value="loginName"/></td>
            <td>
              <s:if test="status==0">开通</s:if>
              <s:elseif test="status==1">暂停</s:elseif>
              <s:else>删除</s:else>
            </td>
            <td><s:property value="createDate"/></td>
            <td><s:property value="lastLoginTime"/></td>
            <td class="td_modi">
              <s:if test="status==0">
                <input type="button" value="暂停" class="btn_pause" onclick="pause(<s:property value="id"/>);"/>
                <input type="button" value="修改" class="btn_modify"
                       onclick="location.href='account_modi.action?id=<s:property value="id"/>';"/>
                <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(<s:property value="id"/>);"/>
              </s:if>
              <s:elseif test="status==1">
                <input type="button" value="开通" class="btn_start" onclick="start(<s:property value="id"/>);"/>
                <input type="button" value="修改" class="btn_modify"
                       onclick="location.href='account_modi.action?id=<s:property value="id"/>';"/>
                <input type="button" value="删除" class="btn_delete" onclick="deleteAccount(<s:property value="id"/>);"/>
              </s:elseif>
              <s:else>

              </s:else>
            </td>
          </tr>
        </s:iterator>
      </table>
      <p>业务说明：<br/>
        1、创建则开通，记载创建时间；<br/>
        2、暂停后，记载暂停时间；<br/>
        3、重新开通后，删除暂停时间；<br/>
        4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br/>
        5、暂停账务账号，同时暂停下属的所有业务账号；<br/>
        6、暂停后重新开通账务账号，并不同时开启下属的所有业务账号，需要在业务账号管理中单独开启；<br/>
        7、删除账务账号，同时删除下属的所有业务账号。</p>
    </div>
    <!--分页-->
    <div id="pages">
      <a href="javascript:toPage(1);">首页</a>

      <s:if test="page==1">
        <a href="#">上一页</a>
      </s:if>
      <s:else>
        <a href="javascript:toPage(<s:property value="page-1"/>);">上一页</a>
      </s:else>

      <s:iterator begin="1" end="totalPage" var="p">
        <s:if test="#p==page">
          <a href="javascript:toPage(<s:property/>);" class="current_page"><s:property/></a>
        </s:if>
        <s:else>
          <a href="javascript:toPage(<s:property/>);"><s:property/></a>
        </s:else>
      </s:iterator>

      <s:if test="page==totalPage">
        <a href="#">下一页</a>
      </s:if>
      <s:else>
        <a href="javascript:toPage(<s:property value="page+1"/>);">下一页</a>
      </s:else>

      <a href="javascript:toPage(<s:property value="totalPage"/>);">末页</a>
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
