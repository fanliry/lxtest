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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/carton/base_carton_add.jsp",'',
			'dialogWidth=500px;dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
	   		list.location.href = ac + "baseCartonAction&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   	}
	}
	
	//��ѯ
	function searchData()
	{
		var cartonid = document.getElementById("cartonid").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
		var condition = "&cartonid=" + cartonid + "&linerow=" + linerow;
		
		list.location.href = ac + "baseCartonAction&flag=1" + condition;
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
		
		var result = showModalDialog(ac + "baseCartonAction&flag=2&cartonid="+id, "", "dialogWidth=500px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			list.location.href = ac + "baseCartonAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
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
			list.location.href = ac + "baseCartonAction&method=ricoExecDelete&ids=" + ids.substring(0, ids.length-1) + "&linerow=" + linerow;
		}
	}
	
	//��ӡ��ǩ
	function printData(){
	
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
		
		var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/carton/base_carton_label.jsp?CartonId="+id;
		showModalDialog(strUrl,'','dialogWidth=350px;dialogHeight=240px');
		
		//var result = showModalDialog(strUrl,'','dialogWidth=350px;dialogHeight=240px');
		//if(result != null && result.length > 1){
		
		//  	myIframe.location.href="<%=request.getContextPath()%>/"+result;
		//   	window.close();
		//}
	}
	
	function OnLoad(){

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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ��ת��</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li>
			<li class="tubiao2" style="width:85px;"><a href="#" onclick="printData()">��ӡ��ǩ</a></li>
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
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100"><div align="right">װ����룺</div></td>
	      <td><input type="text" id="cartonid" size="25"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/carton/base_carton_list.jsp" 
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
