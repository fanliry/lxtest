<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	//ѡ��
	function GetSelect(){
		var value = null;
		var radio_ids = left.document.getElementsByName("check_id");
		for(var i=0; i<radio_ids.length; i++){
			if(radio_ids[i].checked == true){
				value = radio_ids[i].value;
				break;
			}	
		}
		return value;
	}
	//����
	function Add(type){
		if(type == 1){
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_add.jsp", 
				"", "dialogWidth=400px;dialogHeight=200px");
			if(result != null && result.length > 1){
				condition = result;
				left.location.href = ac + "baseBatchAction&method=ricoExecAddBatch" + condition;
			}
		}
		else if(type == 2){
			var id = GetSelect();
			if(id == null){
				alert("��ѡ��һ�����μ�¼��");
				return;
			}
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_mean_add.jsp", id, "dialogWidth=400px;dialogHeight=300px");
			if(result != null && result.length > 1){
				condition = result;
				mean.location.href = ac + "baseBatchAction&method=ricoExecAddBatchmean" + condition;
			}
		}
		else{
			var id = GetSelect();
			if(id == null){
				alert("��ѡ��һ�����μ�¼��");
				return;
			}
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_rule_add.jsp", id, "dialogWidth=400px;dialogHeight=250px");
			if(result != null && result.length > 1){
				condition = result;
				rule.location.href = ac + "baseBatchAction&method=ricoExecAddBatchrule" + condition;
			}
		}
	}
	//ɾ��
	function Delete(type){
		var leftsel = GetSelect();
		if(type == 1){

			if(leftsel == null || leftsel == ""){
				alert("��ѡ��һ�����μ�¼��");
				return;
			}
			else if(confirm("��ȷ��ɾ��ѡ�е�������Ϣ��")){
				var batchid = (leftsel.split(","))[0];
				left.location.href = ac + "baseBatchAction&method=ricoExecDeleteBatch&id=" + batchid;
				mean.location.href = "<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_mean.jsp";
				rule.location.href = "<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_rule.jsp";
			}
		}
		else if(type == 2){
			var ids = "";
			var check_ids = mean.document.getElementsByName("check_id");
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked == true){
					ids += check_ids[i].value + ",";
				}
			}
			if(ids == ""){
				alert("����ѡ��Ҫɾ���������¼��");
				return;
			}
			else if(confirm("��ȷ��ɾ��ѡ�е�������Ϣ��")){
				var batchid = (leftsel.split(","))[0];
				condition = "&ids=" + ids + "&batchid=" + batchid;
				mean.location.href = ac + "baseBatchAction&method=ricoExecDeleteBatchmean" + condition;
			}
		}
		else{
			var ids = "";
			var check_ids = rule.document.getElementsByName("check_id");
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked == true){
					ids += check_ids[i].value + ",";
				}
			}
			if(ids == ""){
				alert("����ѡ��Ҫɾ���Ĺ����¼��");
				return;
			}
			else if(confirm("��ȷ��ɾ��ѡ�еĹ�����Ϣ��")){
				var batchid = (leftsel.split(","))[0];
				condition = "&ids=" + ids + "&batchid=" + batchid;
				rule.location.href = ac + "baseBatchAction&method=ricoExecDeleteBatchrule" + condition;
			}
		}	
	}
	//�޸�
	function Update(type){
		var leftsel = GetSelect();
		if(type == 1){
			if(leftsel == null || leftsel == ""){
				alert("��ѡ��һ�����μ�¼��");
			}else{
				var batchid = (leftsel.split(","))[0];
				var result = showModalDialog(ac + "baseBatchAction&flag=2&id="+batchid, "", "dialogWidth=350px;dialogHeight=200px");
				if(result != null && result.length > 1){
					condition = result;
					left.location.href = ac + "baseBatchAction&method=ricoExecEditBatch" + condition;
				}
			}
		}
	}

	//ҳ���¼
	function OnLoad(){
		left.location.href = ac + "baseBatchAction&flag=1";
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ���ι���</span></div>
	  </div>
 
    </td></tr>
 
    <tr>
      <td height="100%"> 
     
 <table width="99%" height="100%" border="0" align="center" cellpadding="5" cellspacing="0">
   <tr>     
     <td>
       <input onclick="Add(1)" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;���" class="button_add">
       <input onclick="Delete(1)" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete">
       <input onclick="Update(1)" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;�޸�" class="button_edit">
     </td>
     <td height="25" class="font_006699_bold_12">��������</td>
     <td align="right">
       <input onclick="Add(2)" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;���" class="button_add">
       <input onclick="Delete(2)" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete">
     </td>
   </tr>
   <tr height="50%">
	 <td width="40%" rowspan="3" height="100%">
	 	<iframe id="left" name="left" width="100%" height="100%" frameborder="0" scrolling="no"
	 		src="<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_left.jsp"></iframe>
	 </td>
	 <td colspan="2">
	 	<iframe id="mean" name="mean" width="100%" height="100%" frameborder="0" scrolling="auto"
	 		src="<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_mean.jsp"></iframe>
	 </td>
   </tr>
   <tr>
     <td height="25" class="font_006699_bold_12">���ι���</td>
     <td align="right">
       <input onclick="Add(3)" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;���" class="button_add">
       <input onclick="Delete(3)" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete">
     </td>
   </tr>
   <tr height="50%">
	 <td colspan="2">
	 	<iframe id="rule" name="rule" width="100%" height="100%" frameborder="0" scrolling="auto"
	 		src="<%=request.getContextPath()%>/standard/jsp/base/batch/base_batch_rule.jsp"></iframe>
	 </td>
   </tr>
 </table>
 
     </td>
   </tr>
 </table>
</div>

</body>
</html>
