<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
	HashMap hsPopedom = null;
	boolean inboundPOQuery = false; //��ѯ
	boolean inboundPOPrint = false; //����
	boolean inboundPOUploading = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundPOQuery") != null){
			inboundPOQuery = true;
		}
		if(hsPopedom.get("inboundPOPrint") != null){
			inboundPOPrint = true;
		}
		if(hsPopedom.get("inboundPOUploading") != null){
			inboundPOUploading = true;
		}
	}
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
		var customerid = document.getElementById("customer_id").value;	//�ͻ�
		var poid = document.getElementById("poid").value;	//PO����
		var start_time = document.getElementById("createdate_from").value;	//���ڿ�ʼ
		var end_time = document.getElementById("createdate_to").value;		//���ڽ���
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
		
		Loading();
		//"&shift_id=" + shift_id
		condition =  "&poid=" + poid
			 + "&potype=" + potype + "&start_time=" + start_time + "&end_time=" + end_time + "&linerow=" + linerow+"&customerid=" + customerid;
		list.formx1.innerHTML = getLotattCon();
		list.formx1.action = strUrl + "inBoundPoAction&flag=6" + condition;
		list.document.formx1.submit();
	}
	
	function Mupload(){
		
	}
	
	//��ӡ
	function printData(){
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("��ѡ��һ�ŵ��ݣ�");
			return;
		}else if(k != 1){
			alert("ֻ��ѡ��һ�ŵ��ݣ�");
			return;
		}
				
		var actionStr =strUrl + "inBoundPoAction&method=ricoExecPrint&poid="+id;
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
		window.open(actionStr,'newwindow','width=1000,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; PO����������</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<%-- <%if(inboundPOUploading){%><li class="tubiao8" style="width:50px;"><a href="#" onclick="Mupload();">�ϴ�</a></li><%}%>
		  	<%if(inboundPOPrint){%><li class="tubiao8" style="width:80px;"><a href="#" onclick="printData();">���ݴ�ӡ</a></li><%}%> --%>
			<%if(inboundPOQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
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
	      	<input id="createdate_from" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> -
	      	<input id="createdate_to" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	      <td class="yx1" width="100px" align="right">�������ͣ�</td>
	      <td class="yx"><div class="select_search"><select id="potype" style="width:117px;" class="select_search"><option value=""></option></select></div></td>
	      <td class="yx1" align="right">PO���ţ�</td>
          <td class="yx"><input type="text" id="poid" style="width: 117px;"></td>
	    
	      <td class="yx1"align="right">�ͻ���</td>
	      <td class="xx">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
            <td valign="bottom" class="title" height="18px">PO����Ϣ </td>
          </tr>
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/inothermgr/inbound_othermgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">PO����<span id="dckdid" style="color: red;font-weight:bold;"></span>����ϸ��Ϣ</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/inothermgr/inbound_othermgr_detail.jsp"></iframe>
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