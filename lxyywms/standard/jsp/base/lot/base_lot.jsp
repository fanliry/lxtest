<%@ page contentType="text/html; charset=GBK"%>
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
	
	//��ѯ
	function queryData()
	{
		var descr = document.getElementById("descr").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
		condition = "&flag=1" + "&descr=" + descr + "&linerow=" + linerow;
		list.location.href = ac + "baseLotAction" + condition;
	}
		
	//����
	function addData(){
	
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/lot/base_lot_add.jsp", 
			'', 'dialogWidth=800px;dialogHeight=500px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
	   		condition = "&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   		list.location.href = ac + "baseLotAction" + condition;
	   	}
	   	condition = "";
	}
	
	//ɾ��
	function delData()
	{
		var lotids = "";
		var checkbox_ids = window.list.document.getElementsByName("check_id");
		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked)
			{
				var lotid = checkbox_ids[i].value;
				lotids += lotid + ",";
			}
		}
		if(lotids.length <1)
		{
			alert("��ѡ����Ҫɾ����������!");
		  	return false;
		}
		else
		{
			condition = "&method=ricoExecDelete" + "&ids="+lotids;
			if(confirm("ȷ��ɾ����"))
			{
				var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
				list.location.href = ac + "baseLotAction" + condition + "&linerow=" + linerow;
			}
		}
		return true;
	}
	
	//�޸�
	function updateData()
	{
		if(updateAble())
		{
			var checkbox_ids = window.list.document.getElementsByName("check_id");
			for(var i=0; i<checkbox_ids.length; i++)
			{
				if(checkbox_ids[i].checked)
				{
					var lotid = checkbox_ids[i].value;
					condition = "&lotid=" + lotid;
					break;
				}
			}
			var result = showModalDialog(ac+"baseLotAction&flag=2"+condition,'','dialogWidth=800px;dialogHeight=500px');
		   	if(result != null && result.length > 1)
		   	{
		   		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
		   		condition = "&method=ricoExecEdit" + result + "&linerow=" + linerow;
		   		list.location.href = ac + "baseLotAction" + condition;
		   	}
	   	}
	}
	
	//˫��
	function ondbClickDo(strVar)
	{
		condition = "&lotid=" + strVar;

		var result = showModalDialog(ac+"baseLotAction&flag=2"+condition,'','dialogWidth=800px;dialogHeight=500px');
		if(result != null && result.length > 1)
		{
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
			condition = "&method=ricoExecEdit" + result + "&linerow=" + linerow;
		   	list.location.href = ac + "baseLotAction" + condition;
		}
	}
	
	//�޸�����ʱ����
	function updateAble(){
	 	var icount = getCheckCount();
		if(icount<1){
			alert("��ѡ����Ҫ�޸ĵ�������!");
		  	return false;
		}
		if(icount>1){
		 	alert("�޸�ʱֻ��ѡ��һ������!");
		 	return false;
		}
		return true;
	}
	
	//��ȡ��ѡ�����
	function getCheckCount()   
  	{   
  		var counter = 0;
  		var checkbox_ids = window.list.document.getElementsByName("check_id");
  		for(var i=0; i<checkbox_ids.length; i++)
  		{
	  		if(checkbox_ids[i].checked)
	  		{
	  			counter = counter + 1;
	  		}
  		}
  		return counter;
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �������Թ���</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="queryData()">��ѯ</a></li>
			<li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li>
			<li class="tubiao3"><a href="#" onclick="delData()">ɾ��</a></li>
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
	      <td class="y1" width="100" align="right">��������������</td>
	      <td><input type="text" id="descr" size="30" maxlength="50"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/lot/base_lot_list.jsp" 
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