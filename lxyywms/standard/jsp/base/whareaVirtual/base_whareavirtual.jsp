<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean basewhareavirtualAdd = false;    //���
	boolean basewhareavirtualDelete = false; //ɾ��
	boolean basewhareavirtualUpdate = false; //�޸�
	boolean basewhareavirtualQuery = false;  //��ѯ
	boolean basewhareavirtualSetDef = false;  //�����������������
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("basewhareavirtualAdd") != null){
			basewhareavirtualAdd = true;
		}
		if(hsPopedom.get("basewhareavirtualDelete") != null){
			basewhareavirtualDelete = true;
		}
		if(hsPopedom.get("basewhareavirtualUpdate") != null){
			basewhareavirtualUpdate = true;
		}
		if(hsPopedom.get("basewhareavirtualSetDef") != null){
			basewhareavirtualSetDef = true;
		}
		if(hsPopedom.get("basewhareavirtualQuery") != null){
			basewhareavirtualQuery = true;
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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/whareaVirtual/base_whareavirtual_add.jsp",'','dialogWidth=500px;dialogHeight=250px');
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//��ѯ
	function searchData(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaName = document.getElementById("whAreaName").value;
		
		var condition = "&warehouseid=" + warehouseid + "&whAreaName=" + whAreaName;
		
		list.location.href = ac + "baseWhareaVirtualAction&flag=1" + condition;
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
		
		var result = showModalDialog(ac + "baseWhareaVirtualAction&flag=2&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecEdit" + result;
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
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecDelete&id=" + id;
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
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecSetDefault&whAreaId=" + id;
		}
	}
	
	//���������ڿ���
	function setBelongTo(){
	    var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		var result = showModalDialog(ac + "baseWhareaVirtualAction&flag=3&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecSetBelongTo" + result;
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
			<%if(basewhareavirtualQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
			<%if(basewhareavirtualUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li><%}%>
			<%if(basewhareavirtualDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li><%}%>
			<%if(basewhareavirtualAdd){%><li class="tubiao4"><a href="#" onclick="addData()">���</a></li><%}%>
			<%if(basewhareavirtualSetDef){%><li class="tubiao2" style="width:137px;"><a href="#" onclick="setBelongTo()">�����������������</a></li><%}%>
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp"
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
