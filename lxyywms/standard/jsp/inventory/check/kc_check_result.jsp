<%@ page contentType="text/html; charset=GBK"%>
<%
	String requestid = request.getParameter("requestid"); 
%>
<html>
<head>
<title>�̵���</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">

	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//��ѯ����
	function queryTask(){
	
		condition = "&requestid=<%=requestid%>";
		list.location.href = ac + "inventoryCheckAction&flag=6" + condition;
	}
	
	//��ѯ���
	function queryResult(){
		detail.location.href = ac + "inventoryCheckAction&flag=7"+"&requestid=<%=requestid%>";
	}
	
	//ˢ������
	function refrshData(){
	
		queryTask();
	}
	
	//�������
	function saveData(){
	
		var counter = 0;
		var checkbox_ids = list.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("��ѡ��һ���̵�����!");
		  	return false;
		}
		
		var checknum = document.getElementById("checknum").value;	//�̵�����
		
		if(checknum==null || checknum.length==0 || !numChar(checknum)){
			alert("���̵�����������������!");
			return;
		}
		
		var key = id.split("|");
		condition = "&taskid=" + key[0] + "&checknum=" + checknum ;
		detail.location.href = ac + "inventoryCheckAction&method=ricoExecAddResult" + condition;
	}
	//��ѯ
	function queryData(){
	
		var platoon = document.getElementById("platoon").value;	//��
		var column = document.getElementById("column").value;	//��
		var floor = document.getElementById("floor").value;	    //��
		var traycode = document.getElementById("traycode").value;//��������
		
		condition = "&platoon=" + platoon + "&column=" + column + "&floor=" + floor + "&traycode=" + traycode+"&requestid=<%=requestid%>";
		list.location.href =  ac + "inventoryCheckAction&flag=6" + condition;
	}
	//�޸Ľ��
	function updateData(){
	
		var counter = 0;
		var checkbox_ids = detail.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("��ѡ��һ���̵���!");
		  	return false;
		}
		
		var checknum = document.getElementById("checknum").value;	//�̵�����
		
		if(checknum==null || checknum.length==0 || !numChar(checknum)){
			alert("���̵�����������������!");
			return;
		}
		
		var key = id.split("|");
		condition = "&checkid=" + key[0] + "&checknum=" + checknum;
		detail.location.href = ac + "inventoryCheckAction&method=ricoExecEditResult" + condition;
	}
	
	
	//ҳ���½
	function OnLoad(){
		
		queryTask();
	}


</script>
</head>

<body onLoad="OnLoad()">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25">
         <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã�<span>������ -&gt; ����̵� -&gt; �̵���</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	         <li class="tubiao1" style="width:60px;"><a href="#" onclick="queryData();">��ѯ</a></li>
	        </ul>
	      </div>
	    </div>
     </td>
   </tr>
 </table>
 <table id="marginTable" width="98%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
        <tr>
          <td class="yx1" width="100" align="right">��λ��</td>
	      <td class="yx">
	   	    <input type="text" id="platoon" size="2">��
	   	    <input type="text" id="column" size="2">��
	   	    <input type="text" id="floor" size="2">��
	   	  </td>
	      <td class="yx1" width="100" align="right">�������룺</td>
     	  <td class="yx"><input name="traycode" type="text" size="20"></td>
	    </tr>
	  </table> 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td height="250">
		<iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes" 
			src="<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_result_list.jsp"></iframe>
	 </td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>
 <form id="myform" name="myform" method="post">
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td width="100px" class="yx1" align="right">�̵�������</td>
     <td class="xx1"><input type="text" id="checknum" size="20" value="0"></td>
   </tr>
   <tr>
     <td height="27" colspan="4">
       <div align="center">
         <input type="button" name="updateDetailBtn" value="��ѯ" class="BUTTON_STYLE1" onclick="queryResult();">&nbsp;
         <input type="button" name="saveDetailBtn" value="�������" class="BUTTON_STYLE1" onclick="saveData();">&nbsp;
         <input type="button" name="updateDetailBtn" value="�޸Ľ��" class="BUTTON_STYLE1" onclick="updateData();">&nbsp;  
         <!-- <input type="button" name="updateDetailBtn" value="ˢ������" class="BUTTON_STYLE1" onclick="refrshData();">&nbsp;  -->
         <input type="button" name="resetDetailBtn" value="�ر�" class="BUTTON_STYLE1" onclick="javascript:window.close();">
       </div>
     </td>
   </tr>
 </table>
 </form>
 
 <table><tr><td height="10"></td></tr></table>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
	 <td height="180px">
	   <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	   		src="<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_result_detail.jsp"></iframe>
	 </td>
   </tr>
 </table>

</body>
</html>