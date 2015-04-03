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
	
  	//*********分配规则*******************************************************************************
	//查询
	function queryData(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		var descr = document.getElementById("descr").value;
		condition = "&flag=1" + "&warehouseid=" + warehouseid + "&descr=" + descr;
		list.location.href = ac + "allocationRuleAction" + condition;
	}
  		
	//增加分配规则
	function addData()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/rule/allocation/rule_allocation_add.jsp",
			'','dialogWidth=400px;dialogHeight=200px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		condition = "&method=ricoExecAdd" + result;
	   		list.location.href = ac + "allocationRuleAction" + condition;
	   	}
	}
				
	//删除分配规则
	function delData()
	{     
		var allocationids = "";
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
		         	allocationids += id + ",";
		    	}
			}
		}
 		if(allocationids.length <1)
		{
			alert("请选择您要删除的数据项!");
		}else
		{
			condition = "&method=ricoExecDelete" + "&ids=" + allocationids;
			if(confirm("确定删除？"))
			{
				list.location.href = ac + "allocationRuleAction" + condition;
			}
		}	
	}

	//修改分配规则
   	function updateData()
	{
		if(updateAble("1"))
		{
			var array = getCheckValue("1").split("|");
			condition = "&allocationid=" + array[0];
			var result = showModalDialog(ac + "allocationRuleAction&flag=3"+condition,'','dialogWidth=400px;dialogHeight=200px');
		   	if(result != null && result.length > 1)
		   	{
		   		condition = "&method=ricoExecEdit" + result;
		   		list.location.href = ac + "allocationRuleAction" + condition;
		   	}
	   	}
	}
	
	//双击
   	function ondbClickDo(strVar)
	{
		condition = "&allocationid=" + strVar;
		var result = showModalDialog(ac + "allocationRuleAction&flag=3"+condition,'','dialogWidth=400px;dialogHeight=200px');
		
		if(result != null && result.length > 1)
		{
			condition = "&method=ricoExecEdit" + result;
		   	list.location.href = ac + "allocationRuleAction" + condition;
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
	
	//*****分配规则明细************************************************************************8	
	//删除分配规则详细
	function delDetailData()
	{     
        var ids = "";
		var checkbox_ids = window.detail.document.getElementsByName("check_id");

		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked){
				
	    		//规则详细ID +"|" + 分配规则ID
	         	ids += checkbox_ids[i].value + ",";
		    }
		} 
        
        if(ids.length <1){
        	confirm("请选择所要删除的分配规则详细?");
        }  
        else{
        	condition = "&method=ricoExecDeleteDetail" + "&ids=" + ids;
			if(confirm("确定删除所选分配规则详细？"))
			{
				detail.location.href = ac + "allocationRuleAction" + condition;
			}
        }			
	}
	
	//增加分配规则详细
	function addDetailData()
	{
		if(updateAble("1"))
		{
			var array = getCheckValue("1").split("|");
			var allocationid = array[0];
			
			if(allocationid != "")
			{					
			   	var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/rule/allocation/rule_allocation_detail_add.jsp?allocationid=" + allocationid+"&warehouseid="+array[1],
			   		'','dialogWidth=700px;dialogHeight=350px');
	   		
			   	if(result != null && result.length > 1)
			   	{
			   		condition = "&method=ricoExecAddDetail" + result;
			   		detail.location.href = ac + "allocationRuleAction" + condition;
			   	}
	   	
			}else
			{
				alert("分配规则不能为空!");
			}
		}
		
	}
	
	//修改分配规则详细
	function updateDetailData()
	{
		if(updateAble("2"))
		{
			var array = getCheckValue("2").split("|");
			
			condition = "&allocationdetailid=" + array[0] +"&allocationid=" + array[1];
			
		   	var result = showModalDialog(ac + "allocationRuleAction&flag=4"+condition,'','dialogWidth=700px;dialogHeight=350px');
		   	
		   	if(result != null && result.length > 1)
		   	{
		   		condition = "&method=ricoExecEditDetail" + result;
		   		detail.location.href = ac + "allocationRuleAction" + condition;
		   	}
	   	}
	}
	
	//修改优先顺位
   	function editSort()
	{
		if(updateAble("1"))
		{
			var array = getCheckValue("1").split("|");
			condition = "&allocationid=" + array[0];
			var result = showModalDialog(ac + "allocationRuleAction&flag=5"+condition,'','dialogWidth=800px;dialogHeight=500px');
		   	if(result != null && result.length > 1)
		   	{
		   		condition = "&method=ricoExecEditSorts&allocationid=" + array[0] + result;
		   		detail.location.href = ac + "allocationRuleAction" + condition;
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
		<div id="dqwz" class="f_l">当前位置：<span>规则管理 &gt;&gt; 分配规则</span></div>
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
	      <td class="y1" width="120" align="right">分配规则描述：</td>
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
	               src="<%=request.getContextPath()%>/standard/jsp/rule/allocation/rule_allocation_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
            <td valign="bottom" class="title" height="20px">分配规则明细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/rule/allocation/rule_allocation_detail.jsp"></iframe>
		    </td>
	      </tr>
	    </table>
	    
	  </td>
    </tr>
  </table>  
</div>
</body>
</html>
