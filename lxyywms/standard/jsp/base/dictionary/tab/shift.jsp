<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/load.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/load.js"></script>
<script>
<!--
	var ac = "<%=request.getContextPath()%>/rmsService?actionCode=";
	var condition = null;
    //��ȡ�û���
	function GetUser(){
		var result = showModalDialog('<%=request.getContextPath()%>/jsp/dictionary/tab/chooseUser/popup.jsp','','dialogWidth=700px;dialogHeight=400px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		document.getElementById("op_man_id").value = tem[0];
	 		document.getElementById("op_man_name").value = tem[2];
		}
	}
	//����  
	function Add(){
		var result = showModalDialog("<%=request.getContextPath()%>/jsp/dictionary/tab/shift_add.jsp",'','dialogWidth=300px;dialogHeight=200px');
		if(result != null && result.length > 1){
			list.location.href = ac + "addtbjobnumber" + result;
		}
	}
	//��ѯ
	function Search(){
		var op_man_id = document.getElementById('op_man_id').value;
		var in_out_type = document.getElementById('in_out_type').value;
		
		condition = "&op_man_id=" + op_man_id + "&in_out_type=" + in_out_type;
		
		list.location.href = ac + "ShiftAction&method=search&flag=11" + condition;	
	}
	//ɾ��
	function Delete(){     
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("��ѡ��Ҫɾ���ļ�¼��");
			return;
		}
		if(confirm("��ȷ��ɾ����ѡ��¼��")){
			list.location.href = ac + "ShiftAction&method=delete&ids=" + ids.substring(0, ids.length);
		}
	}
	//�޸�
	function Update(){
		var id = "";
		var k = 0;
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("�޸�ʱֻ��ѡ���ұ���ѡ��һ����¼��");
			return;
		}
		var result = showModalDialog(ac + "ShiftAction&method=search&flag=7&shift_id=" + id,'','dialogWidth=300px;dialogHeight=200px');
		if(result != null && result.length > 1){
			list.location.href = ac + "ShiftAction&method=update&flag=1" + result;
		}		
	}
-->
</script>
</head>

<body>

 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25" class="font_006699_bold_12">��ǰλ�ã�������Ϣ -&gt; �ֵ����� -&gt; ��ι���</td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" width="100" align="right">�����ˣ� </td>
     <td class="TD_MGR_TOP" width="150">
       <input name="op_man_id" type="hidden"><input name="op_man_name" type="text" size="16" value="" readonly>
       <input name="moreBtn" type="button" class="button_select" value="��" onClick="GetUser()">
     </td>
     <td class="TD_MGR_TOP" width="100" align="right">������ͣ�</td>
     <td class="TD_MGR_TOP">
       <select name="in_out_type"><option value="">--��ѡ��--</option>
         <option value="1">���</option><option value="2">����</option>
       </select>
     </td>
   </tr>
   <tr>
     <td colspan="4" class="TD_MGR_TOP" align="right">
       <input type="button" value="���" class="BUTTON_STYLE1" onclick="Add()">
       <input type="button" value="��ѯ" class="BUTTON_STYLE1" onclick="Search()">
       <input type="button" value="�޸�" class="BUTTON_STYLE1" onclick="Update()">
       <input type="button" value="ɾ��" class="BUTTON_STYLE1" onclick="Delete()">
     </td>
   </tr>
 </table>
 
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 
     </td>
   </tr>
   <tr>
     <td height="100%"> 
     
 <table width="98%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td valign="top">
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/jsp/dictionary/tab/shift_list.jsp"></iframe>
     </td>
   </tr>
   <tr>
     <td height="25">
       <iframe id="page" name="page" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/jsp/page/page2.jsp"></iframe>
     </td>
   </tr>
 </table>
 
      </td>
   </tr>
 </table>
 
 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
 
</body>
</html>

