<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean outboundExceptQuery = false; //��ѯ
	boolean outboundExceptComfirm = false; //��Ϊ����λ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("outboundExceptQuery") != null){
			outboundExceptQuery = true;
		}
		if(hsPopedom.get("outboundExceptComfirm") != null){
			outboundExceptComfirm = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var indate_from = document.getElementById("indate_from").value;	//������ڿ�ʼ
		var indate_to = document.getElementById("indate_to").value;		//������ڽ���
		var is_do = document.getElementById("is_do").checked==true?"Y":"N";
		
		condition = "&warehouseid=" + warehouseid + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&is_do=" + is_do;
		list.formx1.action = strUrl + "NeedCheckAction&flag=3" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	//���� �ĳ�����λ״̬
	function fullspace(){     
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("��ѡ��Ҫ����ļ�¼��");
			return;
		}
		if(confirm("��ȷ���ǶѶ���鱨�� ��������Ϊ����λ��")){
			list.location.href = strUrl + "NeedCheckAction&flag=4&ids=" + ids+condition;
		}
	}
	//ҳ���¼
	function OnLoad(){
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; �쳣����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		     <%if(outboundExceptComfirm){%><li class="tubiao1"  style="width:90px"><a href="#" onclick="fullspace()">��Ϊ����λ</a></li><%}%>
			 <%if(outboundExceptQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">�ֿ⣺</td>
	      <td class="yx"><select name="warehouseid" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" align="right">���ڣ�</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" width="100" align="right">�Ƿ���</td>
          <td class="yx"><input type="checkbox" name="is_do" class="input_checkbox"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/exception/outbound_exception_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
 
</body>
</html>
