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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
  <!--
  	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
  	//*********�ϼܹ���*******************************************************************************
	//��ѯ
	function queryData(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		var descr = document.getElementById("descr").value;
		condition = "&flag=1" + "&warehouseid=" + warehouseid + "&descr=" + descr;
		list.location.href = ac + "putawayRuleAction" + condition;
	}
  		
	//�����ϼܹ���
	function addData()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/rule/putaway/rule_putaway_add.jsp",'','dialogWidth=400px;dialogHeight=200px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		condition = "&method=ricoExecAdd" + result;
	   		list.location.href = ac + "putawayRuleAction" + condition;
	   	}
	}
				
	//ɾ���ϼܹ���
	function delData()
	{     
		var putawayids = "";
		var array,id;
		var checkbox_ids = window.list.document.getElementsByName("check_id");
		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked)
			{
				array = checkbox_ids[i].value.split("|");
				id = array[0];
				if(id != "" && id=="STANDARD")
		    	{
		    		alert("��׼������ɾ��!");
		    		checkbox_ids[i].checked = false; 
		    	}else
		    	{
		         	putawayids += id + ",";
		    	}
			}
		}
 		if(putawayids.length <1)
		{
			alert("��ѡ����Ҫɾ����������!");
		}else
		{
			condition = "&method=ricoExecDelete" + "&ids=" + putawayids;
			if(confirm("ȷ��ɾ����"))
			{
				list.location.href = ac + "putawayRuleAction" + condition;
			}
		}	
	}

	//�޸��ϼܹ���
   	function updateData()
	{
		if(updateAble("1"))
		{
			var array = getCheckValue("1").split("|");
			condition = "&putawayid=" + array[0];
			var result = showModalDialog(ac + "putawayRuleAction&flag=3"+condition,'','dialogWidth=400px;dialogHeight=200px');
		   	if(result != null && result.length > 1)
		   	{
		   		condition = "&method=ricoExecEdit" + result;
		   		list.location.href = ac + "putawayRuleAction" + condition;
		   	}
	   	}
	}
	
	//˫��
   	function ondbClickDo(strVar)
	{
		condition = "&putawayid=" + strVar;
		var result = showModalDialog(ac + "putawayRuleAction&flag=3"+condition,'','dialogWidth=400px;dialogHeight=200px');
		
		if(result != null && result.length > 1)
		{
			condition = "&method=ricoExecEdit" + result;
		   	list.location.href = ac + "putawayRuleAction" + condition;
		}
	}
	
	//�޸�����ʱ����
	function updateAble(flg){

	 	var icount = getCheckCount(flg);
		if(icount<1){
			alert("��ѡ����Ҫ��������!");
		  	return false;
		}
		if(icount>1){
		 	alert("ֻ��ѡ��һ������!");
		 	return false;
		}
		return true;
	}
	
	//��ȡ��ѡ�����
	function getCheckCount(flg)   
  	{   
  		var counter = 0;
  		var checkbox_ids;
  		
  		if(flg=="1"){
  			checkbox_ids = window.list.document.getElementsByName("check_id");
  		}else if(flg=="2"){	//��ϸ
  			checkbox_ids = window.detail.document.getElementsByName("check_id");
  		}
  		 
  		for(var i=0; i<checkbox_ids.length; i++)
  		{
	  		if(checkbox_ids[i].checked)
	  		{
	  			counter = counter + 1;
	  		}
  		}
  		return counter;
   }
   
   //��ȡ������ѡ���ֵ
   function getCheckValue(flg)
   {
   	 	var value = "";
   	 	var checkbox_ids;
   	 	
  		if(flg=="1"){
  			checkbox_ids = window.list.document.getElementsByName("check_id");
  		}else if(flg=="2"){	//��ϸ
  			checkbox_ids = window.detail.document.getElementsByName("check_id");
  		}
	
		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked)
			{
				value = checkbox_ids[i].value;
				break;
			}
		}
  		
  		return value;
   }
	
	//*****�ϼܹ�����ϸ************************************************************************8	
	//ɾ���ϼܹ�����ϸ
	function delDetailData()
	{     
        var ids = "";
		var checkbox_ids = window.detail.document.getElementsByName("check_id");
		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked){

	    		//������ϸID +"|" + ��������ֵID +"|" + �ϼܹ���ID
	         	ids += checkbox_ids[i].value + ",";
		    }
		} 
        
        if(ids.length <1){
        	confirm("��ѡ����Ҫɾ�����ϼܹ�����ϸ?");
        }  
        else{
        	condition = "&method=ricoExecDeleteDetail" + "&ids=" + ids;
			if(confirm("ȷ��ɾ����ѡ�ϼܹ�����ϸ��"))
			{
				detail.location.href = ac + "putawayRuleAction" + condition;
			}
        }			
	}
	
	//�����ϼܹ�����ϸ
	function addDetailData()
	{
		if(updateAble("1"))
		{
			var array = getCheckValue("1").split("|");
			var putawayid = array[0];
			
			if(putawayid != "")
			{					
			   	var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/rule/putaway/rule_putaway_detail_add.jsp?putawayid="+array[0]+"&warehouseid="+array[1],
			   		'','dialogWidth=850px;dialogHeight=450px');
	   		
			   	if(result != null)
			   	{
			   		//condition = "&method=ricoExecAddDetail" + result;
			   		//detail.location.href = ac + "putawayRuleAction" + condition;

					detail.formx1.innerHTML = result[1];
					detail.formx1.action = ac + "putawayRuleAction&method=ricoExecAddDetail" + result[0];
					detail.document.formx1.submit();
			   	}
	   	
			}else
			{
				alert("�ϼܹ�����Ϊ��!");
			}
		}
		
	}
	
	//�޸��ϼܹ�����ϸ
	function updateDetailData()
	{
		if(updateAble("2"))
		{
			var array = getCheckValue("2").split("|");
			
			condition = "&putawaydetailid=" + array[0];
		   	var result = showModalDialog(ac + "putawayRuleAction&flag=4"+condition,'','dialogWidth=850px;dialogHeight=450px');
		   	
		   	if(result != null)
		   	{
		   		//condition = "&method=ricoExecEditDetail" + result;
		   		//detail.location.href = ac + "putawayRuleAction" + condition;
		   		
	   			detail.formx1.innerHTML = result[1];
				detail.formx1.action = ac + "putawayRuleAction&method=ricoExecEditDetail" + result[0];
				detail.document.formx1.submit();
		   	}
	   	}
	}
	
	//˫���ϼܹ�����ϸ
	function ondbClickDoDetail(strVar)
	{
		condition = "&putawaydetailid=" + strVar;
		var result = showModalDialog(ac + "putawayRuleAction&flag=4"+condition,'','dialogWidth=850px;dialogHeight=450px');
		
		if(result != null)
		{
	   		//condition = "&method=ricoExecEditDetail" + result;
	   		//detail.location.href = ac + "putawayRuleAction" + condition;
	   		
	   		detail.formx1.innerHTML = result[1];
			detail.formx1.action = ac + "putawayRuleAction&method=ricoExecEditDetail" + result[0];
			detail.document.formx1.submit();
		}
	}
	
	//�޸�����˳λ
   	function editSort()
	{
		if(updateAble("1"))
		{
			var array = getCheckValue("1").split("|");
			condition = "&putawayid=" + array[0];
			var result = showModalDialog(ac + "putawayRuleAction&flag=5"+condition,'','dialogWidth=850px;dialogHeight=450px');
		   	if(result != null && result.length > 1)
		   	{
		   		condition = "&method=ricoExecEditSorts&putawayid=" + array[0] + result;
		   		detail.location.href = ac + "putawayRuleAction" + condition;
		   	}
	   	}
	}
	
	//ҳ�����
    function OnLoad(){
		selectObject("", "warehouseid", "1");
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������� &gt;&gt; �ϼܹ���</span></div>
		<div id="caozuo" class="f_r" style="width:650px;">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="queryData()">��ѯ</a></li>
			<li class="tubiao2" style="width:105px;"><a href="#" onclick="editSort()">�޸�����˳λ</a></li>
			<li class="tubiao2" style="width:85px;"><a href="#" onclick="updateDetailData()">�޸���ϸ</a></li>
			<li class="tubiao3" style="width:85px;"><a href="#" onclick="delDetailData()">ɾ����ϸ</a></li>
			<li class="tubiao4" style="width:85px;"><a href="#" onclick="addDetailData()">�����ϸ</a></li>
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
	      <td class="y1" width="120" ><div align="right">�����ֿ⣺</div></td>
	      <td class="x" width="200"><select id="warehouseid"><option value=""></option></select></td>
	      <td class="y1" width="120" align="right">�ϼܹ���������</td>
	      <td><input type="text" id="descr" size="25" maxlength="50"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
        <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/rule/putaway/rule_putaway_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
            <td valign="bottom" class="title" height="20px">�ϼܹ�����ϸ��Ϣ</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/rule/putaway/rule_putaway_detail.jsp"></iframe>
		    </td>
	      </tr>
	    </table>
	    
	  </td>
    </tr>
  </table>  
</div>
</body>
</html>
