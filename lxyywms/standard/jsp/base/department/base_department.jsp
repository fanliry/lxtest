<%@ page contentType="text/html; charset=GBK"%>
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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/department/base_department_add.jsp",'',
			'dialogWidth=550px;dialogHeight=250px');
			
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseDepartmentAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//��ѯ
	function searchData()
	{
		var departmentname = document.getElementById("departmentname").value;
		var departmenttype = document.getElementById("departmenttype").value;
		
		var condition = "&departmentname=" + departmentname + "&departmenttype=" + departmenttype;
		
		list.location.href = ac + "baseDepartmentAction&flag=1" + condition;
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
		
		var result = showModalDialog(ac + "baseDepartmentAction&flag=2&id="+id, "", "dialogWidth=550px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseDepartmentAction&method=ricoExecEdit" + result;
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
			list.location.href = ac + "baseDepartmentAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	function OnLoad(){
		var typevalue = "bmlx";		//�������͵�ֵ	
		selectType("", "departmenttype", typevalue);
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ���Ź���</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li>
			<li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li>
			<li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li>
			<li class="tubiao4"><a href="#" onclick="addData()">���</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%" border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="100" ><div align="right">�������ƣ�</div></td>
	      <td class="x" width="250"><input type="text" id="departmentname" size="25"></td>
	      <td class="y1" width="100"><div align="right">���ŷ��ࣺ</div></td>
	      <td><select id="departmenttype"><option value=""></option></select></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/department/base_department_list.jsp"
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
