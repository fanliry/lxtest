<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<%@ page import="java.util.HashMap" %>
<%
String path = request.getContextPath();
String strTime = StrTools.getCurrDateTime(8);

    HashMap hsPopedom = null;
	boolean kcChecksearchQuery = false; //��ѯ
	boolean kcChecksearchReport = false; //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kcChecksearchQuery") != null){
			kcChecksearchQuery = true;
		}
		if(hsPopedom.get("kcChecksearchReport") != null){
			kcChecksearchReport = true;
		}
    }
%>

<html>
  <head>
    
    <title>�㽭���������ֿ����ϵͳ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>

  </head>
  <script type="text/javascript">
  var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
  var condition = null;
  function changeDel()
  {
	  var id = document.getElementById("inouttype").value;
	  if(id!=null&&id==1){
		detail.location.href="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_indetail.jsp";
	  }else{
		detail.location.href="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_outdetail.jsp";
		  }
  }
    //����
	function report(){
	
		if(condition == null){
			alert("���Ȳ�ѯ��");
			return;
		}
		var name="check_search";
		document.tempForm.action = strUrl + "InventoryCheckSearchAction&method=ricoExecReport&flag=1" + condition;
		document.tempForm.submit();
	}
  function queryData()
  {
     var checkType = document.getElementById("inouttype").value;
     var fmDate = document.getElementById("fmDate").value;
     var toDate = document.getElementById("toDate").value;
     condition = "&checkType="+checkType+"&fmDate="+fmDate+"&toDate="+toDate;  
     list.location.href=strUrl+"InventoryCheckSearchAction"+condition;
  }
  function OnLoad()
  {
   // changeDel();
  }
  
  </script>
  <body onLoad="OnLoad()">
  <div class="con_bk" >
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td height="5">
  <!-- ======== ��ǰλ�� ======== -->
  <div class="wz">
  <div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; �̵��ѯ</span></div>
  <div  class="f_r" id="caozuo"> 
  <ul>
  <%if(kcChecksearchReport){%><li class="tubiao2"><a href="#" onClick="report()">����</a></li><%}%>
  <%if(kcChecksearchQuery){%><li class="tubiao1" style="width:60px;"><a href="#" onclick="queryData();">��ѯ</a></li><%}%>
  </ul></div>
  </div>
  </td></tr>
  <tr><td height="5"></td></tr>
  <tr><td>
  <table  width="99%" height="5"  border="0" align="center" cellpadding="0" cellspacing="0"class="table_add"><tr>
  <td class="y1" align="right" width="100">�̵����ͣ�</td><td class="x"><select id="inouttype" onchange="changeDel()">
	 <option value="0">��ӯ��¼</option>
	 <option value="1">�̿���¼</option>
	 </select></td>
  <td class="y1" align="right" width="100">ʱ�䣺</td> <td class="y"><input id="fmDate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"/>-<input id="toDate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"/></td> 
  </tr>
 </table>
 </td></tr>
 <tr><td height="5"></td></tr>
 <tr><td height="100%">
 <!-- ====����list===== -->
 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
 <!-- =====������Ϣ===== -->
<!--  <tr><td height="18" class="title">������Ϣ</td></tr> -->
 <tr>
 	<td height="280"> 
 	<iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="yes" src="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_list.jsp"></iframe>
 	</td>
 </tr>
 <tr>
	<td height="25">
	 <iframe id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	</td>
 </tr>
 <!-- ===������ϸ===== -->
 <%--<tr><td height="18" class="title">������ϸ</td></tr>
 
 <tr><td height="180">
 <iframe id="detail" name="detail"width="100%" height="100%" frameborder="0" scrolling="yes" src="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_indetail.jsp"></iframe>
 </td></tr> --%>
 </table>
 </td></tr>
  </table>
  </div>
   <!-- ҳ����ز㣨end�� -->  
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
  </body>
</html>
