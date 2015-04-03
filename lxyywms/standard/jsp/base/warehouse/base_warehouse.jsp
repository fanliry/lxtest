<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseWarehouseAdd = false;    //添加
	boolean baseWarehouseDelete = false; //删除
	boolean baseWarehouseUpdate = false; //修改
	boolean baseWarehouseQuery = false;  //查询
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseWarehouseAdd") != null){
			baseWarehouseAdd = true;
		}
		if(hsPopedom.get("baseWarehouseDelete") != null){
			baseWarehouseDelete = true;
		}
		if(hsPopedom.get("baseWarehouseUpdate") != null){
			baseWarehouseUpdate = true;
		}
		if(hsPopedom.get("baseWarehouseQuery") != null){
			baseWarehouseQuery = true;
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
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//查询
	function Search(){
	
		var warehousename = document.getElementById("warehousename").value;
		var condition = "&warehousename=" + warehousename;

		list.location.href = ac + "baseWarehouseAction&flag=1" + condition;
	}
	
	//增加
	function Add(){
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/warehouse/base_warehouse_add.jsp",'',
			'dialogWidth=600px; dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		list.location.href= ac + "baseWarehouseAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//修改
	function Update(){
	
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "baseWarehouseAction&flag=2&warehouseid="+id, "", "dialogWidth=600px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWarehouseAction&method=ricoExecEdit" + result;
		}
	}
	
	//删除
	function Delete(){
	
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		if(confirm("确认要删除该记录？")){
			list.location.href = ac + "baseWarehouseAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	//返回选中值
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		
		return(id);
	}
	
-->
</script>
</head>

<body>

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 仓库管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseWarehouseQuery){%><li class="tubiao1"><a href="#" onclick="Search();">查询</a></li><%}%>
			<%if(baseWarehouseUpdate){%><li class="tubiao2"><a href="#" onclick="Update();">修改</a></li><%}%>
			<%if(baseWarehouseDelete){%><li class="tubiao3"><a href="#" onclick="Delete();">删除</a></li><%}%>
			<%if(baseWarehouseAdd){%><li class="tubiao4"><a href="#" onclick="Add();">添加</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">  
	    <tr>
	      <td class="y1" width="100" ><div align="right">仓库名称：</div></td>
	      <td class="x"><input type="text" id="warehousename" size="20"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
        <table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/warehouse/base_warehouse_list.jsp" 
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
