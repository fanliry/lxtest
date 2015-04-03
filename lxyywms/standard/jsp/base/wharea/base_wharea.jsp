<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseWhareaAdd = false;    //���
	boolean baseWhareaDelete = false; //ɾ��
	boolean baseWhareaUpdate = false; //�޸�
	boolean baseWhareaQuery = false;  //��ѯ
	boolean baseWhareaBelongTo = false;  //���������ڿ���
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseWhareaAdd") != null){
			baseWhareaAdd = true;
		}
		if(hsPopedom.get("baseWhareaDelete") != null){
			baseWhareaDelete = true;
		}
		if(hsPopedom.get("baseWhareaUpdate") != null){
			baseWhareaUpdate = true;
		}
		if(hsPopedom.get("baseWhareaQuery") != null){
			baseWhareaQuery = true;
		}
		if(hsPopedom.get("baseWhareaBelongTo") != null){
			baseWhareaBelongTo = true;
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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/wharea/base_wharea_add.jsp",'','dialogWidth=500px;dialogHeight=250px');
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseWhareaAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//��ѯ
	function searchData(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaName = document.getElementById("whAreaName").value;
		
		var condition = "&warehouseid=" + warehouseid + "&whAreaName=" + whAreaName;
		
		list.location.href = ac + "baseWhareaAction&flag=1" + condition;
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
	
	//���ؿ�������
	function getChkValue1(){
		var whAreaType = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				whAreaType = check_ids[i].alt;
			}
		}
		return(whAreaType);
	}
	
	//�޸�
	function updateData(){
		var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "baseWhareaAction&flag=2&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaAction&method=ricoExecEdit" + result;
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
			list.location.href = ac + "baseWhareaAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	//����ΪĬ���ջ���
	function setDefault(){
		var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		if(confirm("һ���ֿ�ֻ������һ��Ĭ���ջ�����\t\nȷ��Ҫ���øÿ���ΪĬ���ջ�����")){
			list.location.href = ac + "baseWhareaAction&method=ricoExecSetDefault&whAreaId=" + id;
		}
	}
	
	//���������ڿ���
	function setBelongTo(){
	    var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		var whAreaType = getChkValue1();
		if(whAreaType!=9){
			alert("�������Ͳ����ݴ�����");
			return;
		}
		var result = showModalDialog(ac + "baseWhareaAction&flag=3&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaAction&method=ricoExecSetBelongTo" + result;
		}
	}
	
	function OnLoad(){
		selectObject("", "warehouseid", "1");
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �����������</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseWhareaQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
			<%if(baseWhareaUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li><%}%>
			<%if(baseWhareaDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li><%}%>
			<%if(baseWhareaAdd){%><li class="tubiao4"><a href="#" onclick="addData()">���</a></li><%}%>
			<%if(baseWhareaBelongTo){%><li class="tubiao2" style="width:110px;"><a href="#" onclick="setBelongTo()">���������ڿ���</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%" border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100" ><div align="right">�����ֿ⣺</div></td>
	      <td class="x" width="250"><select id="warehouseid"><option value=""></option></select></td>
	      <td class="y1" width="100"><div align="right">�������ƣ�</div></td>
	      <td><input type="text" id="whAreaName" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/wharea/base_wharea_list.jsp"
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
