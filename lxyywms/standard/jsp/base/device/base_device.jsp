<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseDeviceAdd = false;    //���
	boolean baseDeviceDelete = false; //ɾ��
	boolean baseDeviceUpdate = false; //�޸�
	boolean baseDeviceQuery = false;  //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseDeviceAdd") != null){
			baseDeviceAdd = true;
		}
		if(hsPopedom.get("baseDeviceDelete") != null){
			baseDeviceDelete = true;
		}
		if(hsPopedom.get("baseDeviceUpdate") != null){
			baseDeviceUpdate = true;
		}
		if(hsPopedom.get("baseDeviceQuery") != null){
			baseDeviceQuery = true;
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
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//����
	function addData(){
		var time = new Date(); 
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/device/base_device_add.jsp",'','dialogWidth=500px;dialogHeight=250px');
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseDeviceAction&method=ricoExecAdd" + result;
	   	}
	}
	//��ѯ
	function searchData()
	{
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value;
		var devicetype = document.getElementById("devicetype").value;
		
		var condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&devicetype=" + devicetype;
		
		list.location.href = ac + "baseDeviceAction&flag=1" + condition;
	}
	
	//����ѡ��ֵ
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		
		return(id);
	}
	
	//�޸�
	function updateData(){
		var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "baseDeviceAction&flag=2&id="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseDeviceAction&method=ricoExecEdit" + result;
		}
	}
	
	//ɾ��
	function deleteData(){
		var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		if(confirm("ȷ��Ҫɾ���ü�¼��")){
			list.location.href = ac + "baseDeviceAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	function OnLoad(){
		//ͬ��
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		var typevalue = "sblx";		//�豸���͵�ֵ	
		selectType("", "devicetype", typevalue);
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
-->
</script>
</head>
<body onload="OnLoad()"> 

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �豸����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseDeviceQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
			<%if(baseDeviceUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li><%}%>
			<%if(baseDeviceDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li><%}%>
			<%if(baseDeviceAdd){%><li class="tubiao4"><a href="#" onclick="addData()">���</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="100" ><div align="right">�����ֿ⣺</div></td>
	      <td class="x" width="200"><select id="warehouseid" onChange="getWhAreaList()"><option value=""></option></select></td>
	      <td class="y1" width="100" ><div align="right">����������</div></td>
	      <td class="x" width="200"><select name="whAreaId"><option value=""></option></select></td>
	      <td class="y1" width="100"><div align="right">�豸���ͣ�</div></td>
	      <td><select name="devicetype"><option value=""></option></select></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/device/base_device_list.jsp"
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>
 
</body>
</html>
