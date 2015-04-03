<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseCustomerAdd = false;    //���
	boolean baseCustomerDelete = false; //ɾ��
	boolean baseCustomerUpdate = false; //�޸�
	boolean baseCustomerExcel = false; //excel����
	boolean baseCustomerQuery = false; //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseCustomerAdd") != null){
			baseCustomerAdd = true;
		}
		if(hsPopedom.get("baseCustomerDelete") != null){
			baseCustomerDelete = true;
		}
		if(hsPopedom.get("baseCustomerUpdate") != null){
			baseCustomerUpdate = true;
		}
		if(hsPopedom.get("baseCustomerExcel") != null){
			baseCustomerExcel = true;
		}
		if(hsPopedom.get("baseCustomerQuery") != null){
			baseCustomerQuery = true;
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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/customer/base_customer_add.jsp",'',
			'dialogWidth=500px;dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
	   		list.location.href = ac + "baseCustomerAction&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   	}
	}
	
	//��ѯ
	function searchData()
	{
		var customername = document.getElementById("customername").value;
		var customertype = document.getElementById("customertype").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
		var condition = "&customername=" + customername + "&customertype=" + customertype + "&linerow=" + linerow;
		
		list.location.href = ac + "baseCustomerAction&flag=1" + condition;
	}
	
	//����ѡ��ֵ
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id += check_ids[i].value + ",";
			}
		}
		
		return(id);
	}
	
	//�޸�
	function updateData(){
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				k++;
				id = check_ids[i].value
			}
		}
		if(k != 1){
			alert("��ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "baseCustomerAction&flag=2&id="+id, "", "dialogWidth=500px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			list.location.href = ac + "baseCustomerAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	//ɾ��
	function deleteData(){
		var ids = getChkValue();
		if(ids == ""){
			alert("������ѡ��һ����¼��");
			return;
		}
		if(confirm("ȷ��Ҫɾ���ü�¼��")){
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			list.location.href = ac + "baseCustomerAction&method=ricoExecDelete&ids=" + ids.substring(0, ids.length-1) + "&linerow=" + linerow;
		}
	}
	
	//EXCEL����
	function importData(){
		
		var filename = document.getElementById("filename");
		if(filename.value==null || filename.value.length <1){
			alert("��ָ��Ҫ�����EXCEL�ļ���");
			return;
		}
		if(confirm("�Ƿ���EXCEL�ļ���")){
			//IE8���ڰ�ȫ�ԵĿ��ǣ��ϴ��ļ�ʱ��������ʵ�ı����ļ�·�������ԡ�C:\fakepath\��ȡ��֮
			filename.select();   
			var realpath = document.selection.createRange().text;  
			
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			list.location.href = ac + "readFileAction&flag=1&fileType=excel&path=" + realpath + "&linerow=" + linerow;
		}
	}
	
	function OnLoad(){
		var typevalue = "khlx";		//�������͵�ֵ	
		selectType("", "customertype", typevalue);
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �ͻ�����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseCustomerQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
			<!--<%if(baseCustomerExcel){%><li class="tubiao2" style="width:90px;"><a href="#" onclick="importData()">EXCEL����</a></li><%}%>-->
			<%if(baseCustomerUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li><%}%>
			<%if(baseCustomerDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li><%}%>
			<%if(baseCustomerAdd){%><li class="tubiao4"><a href="#" onclick="addData()">���</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100"><div align="right">�ͻ����ƣ�</div></td>
	      <td class="x" width="250"><input type="text" id="customername" size="25"></td>
	      <td class="y1" width="100"><div align="right">�ͻ����ࣺ</div></td>
	      <td class="x" width="150"><select id="customertype"><option value=""></option></select></td>
	      <td class="y1" width="100"><div align="right">EXCEL�ļ���</div></td>
	      <td><input type="file" name="filename" class="input_readonly"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/customer/base_customer_list.jsp" 
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
