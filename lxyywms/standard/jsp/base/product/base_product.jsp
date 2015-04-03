<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseProductAdd = false;    //添加
	boolean baseProductDelete = false; //删除
	boolean baseProductUpdate = false; //修改
	boolean baseProductQuery = false;  //查询
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseProductAdd") != null){
			baseProductAdd = true;
		}
		if(hsPopedom.get("baseProductDelete") != null){
			baseProductDelete = true;
		}
		if(hsPopedom.get("baseProductUpdate") != null){
			baseProductUpdate = true;
		}
		if(hsPopedom.get("baseProductQuery") != null){
			baseProductQuery = true;
		}
    }
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

	//查询
	function searchData()
	{
		var productname = document.getElementById("productname").value;
		var productcode = document.getElementById("productcode").value;
		var producttype = document.getElementById("producttype").value;
		
		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
		var condition = "&productcode=" + productcode +"&productname=" + productname +"&producttype=" + producttype + "&linerow=" + linerow;
		list.location.href = ac + "baseProductAction&flag=1" + condition;
	}
		
	//增加
	function addData(){
		var time = new Date(); 
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/product/base_product_add.jsp",
			'','dialogWidth=750px;dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
	   		list.location.href = ac + "baseProductAction&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   	}
	}
	
	//返回选中值
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
	
	//修改
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
			alert("请选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "baseProductAction&flag=2&id="+id, "", "dialogWidth=750px;dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
			list.location.href = ac + "baseProductAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	//删除
	function deleteData(){
		var ids = getChkValue();
		if(ids == ""){
			alert("请至少选择一条记录！");
			return;
		}
		if(confirm("请查阅库存中是否存在该产品 如果有则点击取消 否则可以点击确定删除？")){
			var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
			list.location.href = ac + "baseProductAction&method=ricoExecDelete&ids=" + ids.substring(0, ids.length-1) + "&linerow=" + linerow;
		}
	}	
	
	//----转到物品类别管理----
	function goType()
	{
		var strUrl = '<%=request.getContextPath()%>/standard/jsp/base/producttype/base_producttype.jsp';
		window.location.href = strUrl;
	}
	
	//页面登录
	function OnLoad(){
		
		selectType("", "producttype", "producttype");
		
	}
-->
</script>
</head>
<body onLoad="OnLoad()"> 

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 产品管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseProductQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
			<%if(baseProductUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li><%}%>
			<%if(baseProductDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">删除</a></li><%}%>
			<%if(baseProductAdd){%><li class="tubiao4"><a href="#" onclick="addData()">添加</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100px" align="right">产品名称：</td>
	      <td><input type="text" id="productname" size="21"></td>
	      <td class="y1" width="100px" align="right">产品编码：</td>
	      <td><input type="text" id="productcode" size="21"></td>
	      <td class="yx1" align="right">产品类别：</td>
      		<td class="yx"><select id="producttype" style="width:117px;">
      			<option value="">---请选择---</option>
      		</select></td>
	     <!-- <td class="y1" width="100px" align="right">EXCEL文件：</td>
	      <td><input type="file" name="filename" class="input_readonly"></td> --> 
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/product/base_product_list.jsp"
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
