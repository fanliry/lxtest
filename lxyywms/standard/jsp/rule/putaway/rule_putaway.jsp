<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
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
	
  	//*********上架规则*******************************************************************************
	//查询
	function queryData(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		var descr = document.getElementById("descr").value;
		condition = "&flag=1" + "&warehouseid=" + warehouseid + "&descr=" + descr;
		list.location.href = ac + "putawayRuleAction" + condition;
	}
  		
	//增加上架规则
	function addData()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/rule/putaway/rule_putaway_add.jsp",'','dialogWidth=400px;dialogHeight=200px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		condition = "&method=ricoExecAdd" + result;
	   		list.location.href = ac + "putawayRuleAction" + condition;
	   	}
	}
				
	//删除上架规则
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
		    		alert("标准规则不能删除!");
		    		checkbox_ids[i].checked = false; 
		    	}else
		    	{
		         	putawayids += id + ",";
		    	}
			}
		}
 		if(putawayids.length <1)
		{
			alert("请选择您要删除的数据项!");
		}else
		{
			condition = "&method=ricoExecDelete" + "&ids=" + putawayids;
			if(confirm("确定删除？"))
			{
				list.location.href = ac + "putawayRuleAction" + condition;
			}
		}	
	}

	//修改上架规则
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
	
	//双击
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
	
	//修改数据时调用
	function updateAble(flg){

	 	var icount = getCheckCount(flg);
		if(icount<1){
			alert("请选择您要的数据项!");
		  	return false;
		}
		if(icount>1){
		 	alert("只能选择一行数据!");
		 	return false;
		}
		return true;
	}
	
	//获取复选框个数
	function getCheckCount(flg)   
  	{   
  		var counter = 0;
  		var checkbox_ids;
  		
  		if(flg=="1"){
  			checkbox_ids = window.list.document.getElementsByName("check_id");
  		}else if(flg=="2"){	//明细
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
   
   //获取单个复选框的值
   function getCheckValue(flg)
   {
   	 	var value = "";
   	 	var checkbox_ids;
   	 	
  		if(flg=="1"){
  			checkbox_ids = window.list.document.getElementsByName("check_id");
  		}else if(flg=="2"){	//明细
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
	
	//*****上架规则明细************************************************************************8	
	//删除上架规则详细
	function delDetailData()
	{     
        var ids = "";
		var checkbox_ids = window.detail.document.getElementsByName("check_id");
		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked){

	    		//规则详细ID +"|" + 批次属性值ID +"|" + 上架规则ID
	         	ids += checkbox_ids[i].value + ",";
		    }
		} 
        
        if(ids.length <1){
        	confirm("请选择所要删除的上架规则详细?");
        }  
        else{
        	condition = "&method=ricoExecDeleteDetail" + "&ids=" + ids;
			if(confirm("确定删除所选上架规则详细？"))
			{
				detail.location.href = ac + "putawayRuleAction" + condition;
			}
        }			
	}
	
	//增加上架规则详细
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
				alert("上架规则不能为空!");
			}
		}
		
	}
	
	//修改上架规则详细
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
	
	//双击上架规则明细
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
	
	//修改优先顺位
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
	
	//页面加载
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
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>规则管理 &gt;&gt; 上架规则</span></div>
		<div id="caozuo" class="f_r" style="width:650px;">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="queryData()">查询</a></li>
			<li class="tubiao2" style="width:105px;"><a href="#" onclick="editSort()">修改优先顺位</a></li>
			<li class="tubiao2" style="width:85px;"><a href="#" onclick="updateDetailData()">修改详细</a></li>
			<li class="tubiao3" style="width:85px;"><a href="#" onclick="delDetailData()">删除详细</a></li>
			<li class="tubiao4" style="width:85px;"><a href="#" onclick="addDetailData()">添加详细</a></li>
			<li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li>
			<li class="tubiao3"><a href="#" onclick="delData()">删除</a></li>
			<li class="tubiao4"><a href="#" onclick="addData()">添加</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="120" ><div align="right">所属仓库：</div></td>
	      <td class="x" width="200"><select id="warehouseid"><option value=""></option></select></td>
	      <td class="y1" width="120" align="right">上架规则描述：</td>
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
            <td valign="bottom" class="title" height="20px">上架规则明细信息</td>
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
