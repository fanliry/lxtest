<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	//����
	function Add(){
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/customer_add.jsp", "", "dialogWidth=600px; dialogHeight=300px; scroll=no");
		if(result != null && result.length > 1){
			list.location.href = ac + "customerMgrAction&methoder=add&flag=1" + result;
		}
	}
	//ɾ��
	function Delete(){
		var ids = "";
		var check_is = list.document.getElementsByName("check_id");
		for(var i=0; i<check_is.length; i++){
			if(check_is[i].checked){
				ids += check_is[i].value + ",";
			}
		}
		if(ids == ""){
			alert("ɾ��ʱ����ѡ��һ����¼��");
			return;
		}
		condition = "&ids=" + ids;
		if(confirm("��ȷ��ɾ����ѡ��¼��")){
			list.location.href = ac + "customerMgrAction&methoder=delete&flag=1" + condition;
		}
	}
	//�޸�
	function Update(){
		var id = null;
		var k = 0;
		var check_is = list.document.getElementsByName("check_id");
		for(var i=0; i<check_is.length; i++){
			if(check_is[i].checked){
				id = check_is[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("�޸�ʱֻ���ұ���ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "customerMgrAction&methoder=search&flag=6&customer_id=" + id , "", "dialogWidth=600px; dialogHeight=300px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "customerMgrAction&methoder=update&flag=1" + result;
		}
	}
	//��ѯ
	function Search(){
		var short_name = document.getElementById("short_name").value;
		var out_id = document.getElementById("out_id").value;
		
		condition = "&short_name=" + short_name + "&out_id=" + out_id;
		
		Loading();
		
		list.location.href = ac + "customerMgrAction&methoder=search&flag=5" + condition;
	}
-->
</script>
</head>

<body>

 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
 <table width="98%" height="25" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="98%" class="font_006699_bold_12">��ǰλ�ã�������Ϣ -&gt; �ֵ����� -&gt; �ͻ�����</td>
   </tr>
 </table>
 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" align="right" width="100">�ͻ���ƣ�</td>
     <td class="TD_MGR_TOP" width="150"><input type="text" name="short_name" size="15"></td>
     <td class="TD_MGR_TOP" align="right" width="100">˫���ţ�</td>
     <td class="TD_MGR_TOP"><input type="text" name="out_id" size="15"></td>
   </tr>
   <tr>
     <td class="TD_MGR_TOP" align="right" colspan="6">
       <input type="button" value="����" class="BUTTON_STYLE1" onclick="Add()">
       <!-- 
       <input type="button" value="ɾ��" class="BUTTON_STYLE1" onclick="Delete()">
       -->
       <input type="button" value="�޸�" class="BUTTON_STYLE1" onclick="Update()">
       <input type="button" value="��ѯ" class="BUTTON_STYLE1" onclick="Search()">
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
     <td height="100%">
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/customer_list.jsp"></iframe>
     </td>
   </tr>
   <tr>
     <td height="25">
       <iframe id="page" name="page" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=30&flag=N"></iframe>
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