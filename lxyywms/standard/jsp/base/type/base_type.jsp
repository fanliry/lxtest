<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseTypeAdd = false;    //���
	boolean baseTypeUpdate = false; //�޸�
	boolean baseTypeUpdate1 = false; //����ϵͳ����
	boolean baseTypeDelete = false;  //ɾ��
	boolean baseTypeQuery = false;    //��ѯ
	boolean baseTypeAdd_Detail = false; //�������
	boolean baseTypeUpdate_Detail = false; //�޸�����
	boolean baseTypeDelete_Detail = false;  //ɾ������
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseTypeAdd") != null){
			baseTypeAdd = true;
		}
		if(hsPopedom.get("baseTypeUpdate") != null){
			baseTypeUpdate = true;
		}
		if(hsPopedom.get("baseTypeUpdate1") != null){
			baseTypeUpdate1 = true;
		}
		if(hsPopedom.get("baseTypeDelete") != null){
			baseTypeDelete = true;
		}
		if(hsPopedom.get("baseTypeQuery") != null){
			baseTypeQuery = true;
		}
		if(hsPopedom.get("baseTypeAdd_Detail") != null){
			baseTypeAdd_Detail = true;
		}
		if(hsPopedom.get("baseTypeUpdate_Detail") != null){
			baseTypeUpdate_Detail = true;
		}
		if(hsPopedom.get("baseTypeDelete_Detail") != null){
			baseTypeDelete_Detail = true;
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
</head>

<body>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--

	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	var result = "";
	
	//����ѡ��ֵ
	function getChkValue(list){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		return(id);
	}
	
	//��ѯ
	function searchData(){
	    
		var typename = document.getElementById("typename").value;
		var typevalue = document.getElementById("typevalue").value;
		
		condition = "&typename=" + typename + "&typevalue=" + typevalue;
    	left.location.href = ac + "baseTypeAction&flag=1&" + condition;
    	right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/type/base_type_list_right.jsp";
	}
	
	//����
	function addData(flg){
		if(flg == 1){	//��������
		
			result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/type/base_type_add.jsp",'','dialogWidth=500px;dialogHeight=200px');
		   	if(result != null && result.length > 0){
		   		left.location.href = ac + "baseTypeAction&method=ricoExecAdd&flag=1" + result;
		  		right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/type/base_type_list_right.jsp";
		   	}
		}else if(flg == 2){	//�������������б�ֵ
		
			var id = getChkValue(left);
			if(id == ""){
				alert("��ѡ��һ�����ͼ�¼��");
				return;
			}
			
			var ae = id.split("|");
			result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/type/base_type_addList.jsp?typevalue=" + ae[1] + "&typename=" + ae[0],
				'','dialogWidth=500px;dialogHeight=200px');
		   	if(result != null && result.length > 0){
		   		right.location.href= ac + "baseTypeAction&method=ricoExecAdd&flag=2" + result; 
		   	}
		}
	}
	
	//�޸�
	function updateData(flg){
		
		if(flg == 1 || flg == 3){
		
			var id = getChkValue(left);
			if(id == ""){
				alert("��ѡ��һ�����ͼ�¼��");
				return;
			}
			var ae = id.split("|");
		
			if(flg == 1){	//�޸�����
				result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/type/base_type_update.jsp?typevalue=" + ae[1] + "&typename=" + ae[0], 
					"", "dialogWidth=500px;dialogHeight=200px;");
				
				if(result != null && result.length > 0){
					left.location.href = ac + "baseTypeAction&method=ricoExecEdit&flag=1" + result;
					right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/type/base_type_list_right.jsp";
				}
			}else if(flg == 3){	//��Ϊϵͳ����
				left.location.href = ac + "baseTypeAction&method=ricoExecEdit&flag=3&typevalue=" + ae[1];
			}
			
		}else if(flg == 2){	//�޸����������б�ֵ
			
			var id = getChkValue(right);
			if(id == ""){
				alert("��ѡ��һ�������б��¼��");
				return;
			}
			result = showModalDialog(ac + "baseTypeAction&flag=3&typeid=" + id,'','dialogWidth=500px;dialogHeight=200px');
			
		   	if(result != null && result.length > 1){
		   		right.location.href= ac + "baseTypeAction&method=ricoExecEdit&flag=2" + result; 
		   	}
		}
	}
	
	//ɾ��
	function deleteData(flg){
	
		if(flg == 1){	//ɾ������
		
			var id = getChkValue(left);
			if(id == ""){
				alert("��ѡ��һ�����ͼ�¼��");
				return;
			}
			
			var ae = id.split("|");
			if(confirm("��ȷ��ɾ����") == true){
  
				left.location.href = ac + "baseTypeAction&method=ricoExecDelete&flag=1&typevalue=" + ae[1];
				right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/type/base_type_list_right.jsp";
			}
			
		}else if(flg == 2){	//ɾ�����������б�ֵ
		
			var id = getChkValue(right);
			if(id == ""){
				alert("��ѡ��һ�������б��¼��");
				return;
			}
			
			if(confirm("��ȷ��ɾ����") == true){
  
				right.location.href = ac + "baseTypeAction&method=ricoExecDelete&flag=2&typeid=" + id;
			}
		}
	}
	
	
-->
</script>
<body>

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ���͹���</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<!--<li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li>-->
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100"><div align="right">��������</div></td>
	      <td class="x" width="250"><input type="text" name="typename" size="22" maxlength="50"></td>
	      <td class="y1" width="100"><div align="right">���ʹ��룺</div></td>
	      <td><input type="text" name="typevalue" size="22" maxlength="50"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr height="25px">
		    <td align="right">
		      <%if(baseTypeAdd){%><input onclick="addData(1)" type="button" value="&nbsp;&nbsp;&nbsp;���" class="button_add" id="button1"><%}%>
		      <%if(baseTypeUpdate){%><input onclick="updateData(1)" type="button" value="&nbsp;&nbsp;&nbsp;�޸�" class="button_edit" id="button2"> <%}%>
		      <%if(baseTypeUpdate1){%><input onclick="updateData(3)" style="width:100px;" type="button" value="&nbsp;&nbsp;��Ϊϵͳ����" class="button_edit" id="button3"> <%}%>
		      <%if(baseTypeDelete){%><input onclick="deleteData(1)" type="button" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete" id="button4"><%}%>
		      <%if(baseTypeQuery){%><input onclick="searchData()" type="button" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search" id="button"><%}%>
		    </td>
		    <td width="5px"></td>
		    <td align="right">
		      <%if(baseTypeAdd_Detail){%><input onclick="addData(2)" type="button" value="&nbsp;&nbsp;&nbsp;���" class="button_add" id="button5"><%}%>
		      <%if(baseTypeUpdate_Detail){%><input onclick="updateData(2)" type="button" value="&nbsp;&nbsp;&nbsp;�޸�" class="button_edit" id="button6"><%}%>
		      <%if(baseTypeDelete_Detail){%><input onclick="deleteData(2)" type="button" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete" id="button7"> <%}%>
		    </td>
		  </tr>
		  <tr>
		    <td valign="top">
	          <iframe id="left" src="<%=request.getContextPath()%>/standard/jsp/base/type/base_type_list_left.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  	<td width="5px"></td>
		    <td>
	          <iframe id="right" src="<%=request.getContextPath()%>/standard/jsp/base/type/base_type_list_right.jsp" 
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

