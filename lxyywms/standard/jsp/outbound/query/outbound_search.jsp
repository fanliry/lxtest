<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean outboundSchQuery = false; //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("outboundSchQuery") != null){
			outboundSchQuery = true;
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
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//���
		var cargospaceid = document.getElementById("cargospace_id").value;	//��λid
		
		var onlinetype = document.getElementById("onlinetype").value;	//����ģʽ
		var indate_from = document.getElementById("indate_from").value;	//�������ڿ�ʼ
		var indate_to = document.getElementById("indate_to").value;		//�������ڽ���
		
		var customerid = document.getElementById("customer_id").value;	//�ͻ�
		var productid = document.getElementById("package_id").value;	//Ʒ��
		var tray_code = document.getElementById("tray_code").value;		//��������
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&cargoAlleyId=" +cargoAlleyId + "&cargospaceid=" +cargospaceid
			 + "&onlinetype=" +onlinetype + "&indate_from=" +indate_from + "&indate_to=" + indate_to 
			 + "&customerid=" + customerid + "&productid=" +productid + "&traycode=" +tray_code;
		
		
		
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		
		condition += "&linerow=" + linerow;
			
// 		list.location.href = strUrl + "inventoryAction&flag=3" + condition;
		

					
// 		list.formx1.innerHTML = "";
		list.formx1.action = strUrl + "outBoundJobAction&method=ricoExecSearchJob" + condition;
		list.document.formx1.submit();
		
		Loading();	
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
		
		//��������
		//selectLot("", "lotid");
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//���ݿ������������б�
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "cargoAlleyId", whAreaId);
	}
	
	//ѡ�����κ���ʾ�����ε���ϸ����
	function viewLot(){
	
		var tableObj = document.getElementById("marginTable");
		var lotid = document.getElementById("lotid").value;
		
		//3�ǲ�ѯ����������, 4��ÿ����ʾ��td��
		createLotDetail(tableObj, lotid, 3, 4);
		
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������� &gt;&gt; �����ѯ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(outboundSchQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
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
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">�����</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--��ѡ��--</option></select></td>
     	  <td class="yx1" width="100px" align="right">��λ��</td>
	      <td class="xx1">
		    <input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid='+document.getElementById('warehouseid').value+'&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       			type="button" value="��" class="button_select">
          </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">����ģʽ��</td>
     	  <td class="yx">
     	  	<select id="onlinetype" style="width:117px;"><option value="">--��ѡ��--</option><option value="1">����</option><option value="2">�ѻ�</option></select>
     	  </td>
     	  <td class="yx1" align="right">�������ڣ�</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	      <td class="yx1" align="right">�ͻ���</td>
	      <td class="xx1">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/query/outbound_search_list.jsp" 
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
