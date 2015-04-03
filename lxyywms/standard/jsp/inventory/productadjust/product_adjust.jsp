<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script>
    //查询
	function queryData()
	{
	    
		var actionStr = "BMSService?actionCode=productAdjustAction"
        		 + "&customerid=" + document.getElementById("customer_id").value
				 + "&status=" + document.getElementById("status").value;
		Loading();	
		window.list.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	//增加
	function addData()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_adjust_header_add.jsp",'','dialogWidth=500px;dialogHeight=300px');
		
		if(result != null && result.length > 1)
		{
			window.list.location.href="<%=request.getContextPath()%>/"+result;
			window.close();
		}
	}
	//删除
	function delData()
	{
			   var deleteName = getDelCheckName();   
		        if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要删除的库存调整单");
		        }  
		        else{
					var del = confirm("确定删除所选库存调整单");
					if(del){
						window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=productAdjustAction&method=ricoExecDelete&deleteStr="+deleteName;		
					}
		        }
	}
	//获得选择的选择框的值
	function getDelCheckName()
	{
		var strDel = '';
		var length = list.myform.elements.length;
		for(i=0;i<length;i++){
			 if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true){
			      strDel = strDel + list.myform.elements[i].value + ',';
			 }
		}
		return strDel;
	}
	//修改
	function updateData()
	{
			if(updateAble())
			{		
				var param = getCheckValue();
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=productAdjustAction&method=ricoExecQuery&id="+param;
				var result = showModalDialog(strUrl,'','dialogWidth=700px;dialogHeight=400px');
			   	if(result != null && result.length > 1)
			   	{
			   		list.location.href="<%=request.getContextPath()%>/"+result;
			   		window.close();
			   	}
		   	} 
	 }
	 //获取单个复选框的值
	   function getCheckValue()
	   {
	   	 var value = "";
	  		var length = list.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true)
			    {
			         value = list.myform.elements[i].value;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
	   //修改数据时调用
		function updateAble(){
		 	var icount = getCheckCount();
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
		function getCheckCount()   
	  	{   
	  		var counter = 0;

	  		var length = list.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			    }
			}
	  		
	  		
	  		return counter;
	   }
	   //明细
	function detailData()
	{
		if(updateAble())
		{		
			var param = getCheckValue();
			
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_adjust_detail_mgr.jsp?id="+param,'','dialogWidth=1100px;dialogHeight=700px');
			queryData();
			
		}
	}
	//重置
	function resetData(formName) 
	{
	    var formObj = document.forms[formName]; 
	    var formEl = formObj.elements; 
	    for (var i=0; i<formEl.length; i++) 
	    {
	        var element = formEl[i]; 
	        if (element.type == 'submit') { continue; } 
	        if (element.type == 'reset') { continue; } 
	        if (element.type == 'button') { continue; } 
	 
	        if (element.type == 'text') { element.value = ''; } 
	        if (element.type == 'hidden') { element.value = ''; } 
	        if (element.type == 'textarea') { element.value = ''; } 
	        if (element.type == 'checkbox') { element.checked = false; } 
	        if (element.type == 'radio') { element.checked = false; } 
	        if (element.type == 'select-multiple') { element.selectedIndex = -1; } 
	        if (element.type == 'select-one') { element.selectedIndex = -1; } 
	    }
	}
	//调整(可以调整多个库存调整单(不是多个明细))
	function moveBatch()
	{
		 var deleteName = getDelCheckName1();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要调整的库存调整单");
		        }  
		        else{
					var del = confirm("确定调整所选库存调整单");
					if(del){
						list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=productAdjustAction&method=ricoExecAdjust&deleteStr="+deleteName;	 	
					}
		        }
	}
	//获得选择的选择框的值
	function getDelCheckName1()
	{
			var strDel = '';
			
			var length = list.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true)
			    {
			    	var status = list.myform.elements[i].id;
			    	if(status != "" && status=="1")
			    	{
			    		alert("调整单"+list.myform.elements[i].alt+"已完成,无需再次调整,请重新选择!");
			    		return;
			    	}
			        strDel = strDel + list.myform.elements[i].value + ','; 
			    }
			}
			return strDel;
	}
</script>
</head>
<body>
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
	     <div class="wz" >
	      <div id="dqwz" class="f_l">当前位置：<span>库存管理</span> &gt;&gt; <span>货品调整</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	           <li class="tubiao2"><a href="#" onclick="moveBatch()">调整</a></li>
	           <li class="tubiao2"><a href="#" onclick="detailData()">明细</a></li>
	           <li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li>
	           <li class="tubiao3"><a href="#" onclick="delData()">删除</a></li>
	           <li class="tubiao4"><a href="#" onclick="addData()">添加</a></li>
	           <li class="tubiao2"><a href="#" onclick="resetData('myform')">重置</a></li>
	           <li class="tubiao1"><a href="#" onclick="queryData()">查询</a></li>
	        </ul>
	      </div>
	    </div>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 		<form id="myform" name="myform" method="post" action="">	
			    <table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
	              <tr>
	                <td width="60" class="y1"><div align="right">货主：</div></td>
	                <td width="290" class="x" >
	                  <input type="text" name="customer_name"  readonly="readonly"  style="height:18px;width:200px;"/>
	                  <input type="hidden" name="customer_id" />
	                  <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></td>
	                <td class="y1" width="60"><div align="right">状态：</div></td>
	                <td  width=""><div align="left">
	                  <select  name="status" style="width:100px;">
	                    <option value="">-- 请选择 --</option>
		                <option value="0">创建</option>
		                <option value="1">完成</option>
	                  </select></div>
	                </td>
	             </tr>
	            </table> 
            </form>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 
     </td>
   </tr>
   <tr>
     <td height="100%"> 
     
  <!-- ***** 调度作业信息及作业详细*****-->
 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
           <td height="18" class="title">库存调整单
                </td>
       </tr>
	   <tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_adjustheader_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=5&flag=Y"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		货品调整单明细
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="myIframe2" name="myIframe2" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_adjustdetail_list.jsp"></iframe>
		 </td>
	   </tr>
	 </table>
 <!-- *******************************-->
 
 
 
     </td>
   </tr>
 </table>

</div>

<!-- 页面加载层（start） -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- 页面加载层（end） -->  

</body>
</html>
