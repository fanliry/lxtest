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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
    //��ȡ�û���
	function GetUser(){
		var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_user.jsp','','dialogWidth=700px;dialogHeight=440px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		document.getElementById("op_man_id").value = tem[0];
	 		document.getElementById("op_man_name").value = tem[2];
		}
	}
	
	//����  
	function addData(){
		//ȡ�ð��������Ϣ
		getTool.getShiftconfig({callback:function(result){
			if(result.length > 0){
				var result = showModalDialog(ac + "baseShiftAction&flag=2",'','dialogWidth=450px;dialogHeight=320px');
				if(result != null && result.length > 1){
					var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
					list.location.href = ac + "baseShiftAction&method=ricoExecAdd" + result + "&linerow=" + linerow;
				}
			}else{
				alert("�������úð�ε���Ϣ��\n������ϵͳ���� -> ���Ĭ����Ϣ����");
			}
		}});
	}
	
	//��ѯ
	function searchData(){
		var op_man_name = document.getElementById('op_man_name').value;
		var in_out_type = document.getElementById('in_out_type').value;
		
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
		condition = "&op_man_id=" + op_man_name + "&in_out_type=" + in_out_type + "&linerow=" + linerow;
		
		list.location.href = ac + "baseShiftAction&flag=1" + condition;	
	}
	
	//�޸�
	function updateData(){
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
		var result = showModalDialog(ac + "baseShiftAction&flag=3&id="+id, "", "dialogWidth=450px; dialogHeight=320px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
			list.location.href = ac + "baseShiftAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
-->
</script>
</head>
<body>

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ��ι���</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li>
			<li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li>
			<!--<li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li>-->
			<li class="tubiao4"><a href="#" onclick="addData()">���</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100" align="right">�����ˣ�</td>
	      <td class="x" width="250">
	      	<input name="op_man_id" type="hidden">
      		<input type="text" name="op_man_name" size="22" maxlength="50" readonly>
      		<input name="moreBtn" type="button" class="button_select" value="��" onClick="GetUser()">
      	  </td>
	      <td class="y1" width="100" align="right">�������ͣ�</td>
	      <td>
	      	<select name="in_out_type" style="width:163px">
	      	  <option value="">--��ѡ��--</option>
         	  <option value="0">��/����</option>
         	  <option value="1">���</option>
         	  <option value="2">����</option>
      		</select>
      	  </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/shift/base_shift_list.jsp" 
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

</body>
</html>

