<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var searchCondition = null;	//����ʱ���ѯ��������ȥ�������⻭���ϵĲ�ѯ�������޸ĺ󣬲�������ѯ��ȥ���ɵ��ݣ�
	function LockButton(){
		document.getElementById("search").disabled = true;
		document.getElementById("detail").disabled = true;
		document.getElementById("add").disabled = true;
	}
	function UnLockButton(){
		document.getElementById("search").disabled = false;
		document.getElementById("detail").disabled = false;
		document.getElementById("add").disabled = false;
	}
	
	//��ѯ
	function searchData()
	{
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var indate = document.getElementById("indate").value;
		var isinvoice = document.getElementById("isinvoice").value;
		var customer_id = document.getElementById("customer_id").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		if(indate == null || indate.length < 1){
			alert("�����ڡ�����Ϊ�գ�");
			condition = null;
			return;
		}
		
		Loading();
		condition = "&warehouseid=" +��warehouseid + "&whAreaId=" +��whAreaId + "&indate=" + indate + "&isinvoice=" + isinvoice + "&owner_id=" + customer_id;
		searchCondition = warehouseid + "|" + whAreaId + "|" + indate + "|" + isinvoice + "|" + customer_id + "|end" ;
		list.location.href = strUrl + "inBoundAction&flag=1" + condition;
	}
	
	//��ϸ
	function searchDetail(){
		var k = 0;
		var key = "";
		var check_ids = list.document.getElementsByName("check_id");
		var keys = list.document.getElementsByName("detail_key");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				key = keys[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("��ϸ����������ֻ��ѡ��һ����¼��");
			return;
		}
		key = encodeURIComponent(encodeURIComponent(key));	//�����ַ�,���ĵ�ʱ��
		showModalDialog(strUrl + "inBoundAction&flag=2&key=" + key + condition, "", "dialogWidth=1200px;dialogHeight=500px;scrollbars=auto;");
	}
	
	//���ɵ��� 
	function addInvoice()
	{	
		LockButton();
		var key = "";
		var check_ids = list.document.getElementsByName("check_id");
		var keys = list.document.getElementsByName("detail_key");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				key += keys[i].value + ",";
			}
		}
		if(key == ""){
			alert("������ѡ��һ����¼��");
			UnLockButton();
			return;
		}
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var indate = document.getElementById("indate").value;
		var isinvoice = document.getElementById("isinvoice").value;
		var customer_id = document.getElementById("customer_id").value;
			
		key =  key.substring(0, key.length-1);
			
		if(confirm("��ȷ������ѡ��(����:"+indate+")���ɵ��ݣ�")){
				
			//������
			var msg = "<input type=hidden name='actionCode' value='inBoundAction'>"
					+ "<input type=hidden name='method' value='ricoExecCreate'>"
					+ "<input type=hidden name='searchCondition' value='"+searchCondition+"'>"
					+ "<input type=hidden name='key' value='"+key+"'>";
			list.formx1.innerHTML = msg;
			list.document.formx1.submit(); 	
				
		}else{
			UnLockButton();
		}	
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
-->
</script>
</head>

<body onLoad="OnLoad()">

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; �½���ⵥ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()" id="search">��ѯ</a></li>
			<li class="tubiao2"><a href="#" onclick="searchDetail()" id="detail">��ϸ</a></li>
			<li class="tubiao4" style="width:83px"><a href="#" onclick="addInvoice()" id="add">���ɵ���</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="100" align="right">�ֿ⣺</td>
	      <td class="x"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100" align="right">������</td>
     	  <td class="x"><select id="whAreaId" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100px" align="right">���ڣ�</td>
	      <td class="x">
	      	<input id="indate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"></td>
	      <td class="y1" align="right" width="100px">������</td>
	      <td class="x">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	      <td class="y1" width="100px" align="right">�Ƿ񿪵���</td>
	      <td><select id="isinvoice"><option value="">--��ѡ��--</option><option value="Y">��</option><option value="N">��</option></select></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/newin/inbound_newin_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
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