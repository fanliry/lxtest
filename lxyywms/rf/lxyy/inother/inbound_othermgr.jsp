<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
	String strWarehouseID = (String)request.getParameter("whid");
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/yldSelectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/customs/yld/js/yldselectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lotsearch.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//��ѯ
	function searchData(){
		var potype = document.getElementById("potype").value;			//��������
		var start_time = document.getElementById("createdate_from").value;	//����
		var linerow = ""; //page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
		
		condition = "&potype=" + potype + "&start_time=" + start_time + "&linerow=" + linerow;
		list.location.href = strUrl + "inBoundPoAction&flag=6&RFflag=rf" + condition;
		
	}
	
	//˫��
	function ondbClickDo(strVar)
	{
		
		window.returnValue = strVar;
		window.close();
	}
	
	//ҳ���¼
	function OnLoad(){		
		selectType('', 'potype', 'potype');
		//��ʾ�������������õĲ�ѯ����
		//var tableObj = document.getElementById('marginTable');
		//3����֮ǰ������, 4��ÿ����ʾ��td��
		//createLotSearch(tableObj, 'S-ckdmgr', 2, 4); 
	}
	
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 <input type="hidden" id="lt1">	
 <input type="hidden" id="lt2">
 <input type="hidden" id="lt3">
 <input type="hidden" id="lt4">
 <input type="hidden" id="lt5">
 <input type="hidden" id="lt6">
 <input type="hidden" id="lt7">
 <input type="hidden" id="lt8">
 <input type="hidden" id="lt9">
 <input type="hidden" id="lt10">
 <input type="hidden" id="lt11">
 <input type="hidden" id="lt12">
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������� &gt;&gt; PO������</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(true){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" align="right">���ڣ�</td>
	      <td class="yx">
	      	<input id="createdate_from" type="text"  value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	      <td class="yx1" align="right">���ͣ�</td>
	      <td class="yx"><div class="select_search"><select id="potype" style="width:117px;" class="select_search"><option value=""></option></select></div></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
	      <tr>
	        <td height="100px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/rf/lxyy/inother/inbound_othermgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">PO����<span id="dckdid" style="color: red;font-weight:bold;"></span>����ϸ��Ϣ</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/rf/lxyy/inother/inbound_othermgr_detail.jsp"></iframe>
		    </td>
	      </tr>
	    </table>
		
	  </td>
    </tr>
  </table>  	
</div>

	<!-- ҳ����ز㣨start�� -->
	<div id="loader_container"
		style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
		<div id="loader">
			<div align="center">ҳ�����ڼ��� ...</div>
			<div id="loader_bg">
				<div id="progress"></div>
			</div>
		</div>
	</div>
	<!-- ҳ����ز㣨end�� -->
 
</body>
</html>