<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
  <!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
	//��ѯ
	function queryData(){

		left.location.href = ac + "dictionaryAction&flag=1";
	}
	
	//����
	function addData(){
		var id = getChkValue(left);
		if(id == ""){
			alert("��ѡ��һ�����ͼ�¼��");
			return;
		}
		
		var ae = id.split("|");
		result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/dictionary/base_dictionary_detail_add.jsp?typevalue=" + ae[1] + "&typename=" + ae[0],
			'','dialogWidth=500px;dialogHeight=200px');
	   	if(result != null && result.length > 0){
	   		right.location.href= ac + "dictionaryAction&flag=5" + result; 
	   	}
	}
	
	//�޸�
	function updateData(){
	
		var id = getChkValue(right);
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "dictionaryAction&flag=3&typeId="+id, "", "dialogWidth=550px;dialogHeight=170px;");
		if(result != null && result.length > 0){
			right.location.href= ac + "dictionaryAction&flag=4" + result;
        }
	}
	
	//ɾ��
	function delData(){
	
		var id = getChkValue(right);
		if(id == ""){
			alert("��ѡ��һ�������б��¼��");
			return;
		}
		
		if(confirm("��ȷ��ɾ����") == true){

			right.location.href = ac + "dictionaryAction&flag=6&typeId=" + id;
		}
	}
	
	//����ѡ��ֵ
	function getChkValue(flag){
		var id = "";
		var check_ids = flag.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		return(id);
	}

	//ҳ�����
	function OnLoad(){
		queryData();
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �ֵ�����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li>
			<li class="tubiao3"><a href="#" onclick="delData()">ɾ��</a></li>
			<li class="tubiao4"><a href="#" onclick="addData()">���</a></li>
		  </ul>
		</div>
	  </div>		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		
		<table width="99%" height="100%" border="0" align="center" cellpadding="5" cellspacing="0">
		  <tr>
		    <td width="20%">
	          <iframe id="left" src="<%=request.getContextPath()%>/standard/jsp/base/dictionary/base_dictionary_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  	<td width="80%">
	          <iframe id="right" src="<%=request.getContextPath()%>/standard/jsp/base/dictionary/base_dictionary_detail.jsp" 
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