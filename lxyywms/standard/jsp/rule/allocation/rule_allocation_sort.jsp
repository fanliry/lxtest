<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.rule.RuleAllocationDetail"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
  <!--
  	var check_ids = document.getElementsByName("check_id");
	var newsorts = document.getElementsByName("newsort");
	
	//设置单选框是否选中
	function setSel(i){

		check_ids[i].checked = true;
						
		//改变背景色
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//获取单选框个数
	function isChecked()   
  	{   
  		for(var i=0; i<check_ids.length; i++)
  		{
	  		if(check_ids[i].checked)
	  		{
	  			return true;
	  		}
  		}
  		alert("请选中一条数据！");
  		return false;
   }
   
   	//重新排序
	function doSort(){
		for(var i=0; i<check_ids.length; i++){
			newsorts[i].value = i+1;
		}
	}
	
	//上移
	function moveUp(){
		
		if(isChecked()){
		
			for(var i=0; i<check_ids.length; i++){
	  		
		  		if(check_ids[i].checked){

		  			//获取表格行的引用
		  			var _row = check_ids[i].parentNode.parentNode;  
		  			
		  			//如果不是第一行，则与上一行交换顺序 
					if(_row.previousSibling && i!=0){
						
						swapNode(_row, _row.previousSibling); 
						doSort();		//重新排序
					}
					
		  			break;
		  		}
	  		}
  		}
	}
	
	
	//下移
	function moveDown(){ 
	
		if(isChecked()){
			for(var i=0; i<check_ids.length; i++){
	  		
		  		if(check_ids[i].checked){

		  			//获取表格行的引用
		  			var _row = check_ids[i].parentNode.parentNode;  
		  			
		  			//如果不是最后一行，则与下一行交换顺序 
					if(_row.nextSibling && i!=check_ids.length-1){
						
						swapNode(_row, _row.nextSibling); 
						doSort();		//重新排序
					}
					
		  			break;
		  		}
	  		}
		}
	}
	
	//定义通用的函数交换两个结点的位置 
	function swapNode(node1,node2){ 
		
		//获取父结点 
		var _parent=node1.parentNode; 		
		
		//获取两个结点的相对位置 
		var _t1=node1.nextSibling; 
		var _t2=node2.nextSibling; 
		
		//将node2插入到原来node1的位置 
		if(_t1){
			_parent.insertBefore(node2,_t1);
		}else {
			_parent.appendChild(node2);
		} 
		
		//将node1插入到原来node2的位置 
		if(_t2){
			_parent.insertBefore(node1,_t2); 
		}else {
			_parent.appendChild(node1)
		}; 
	} 
	
	
	//保存
	function saveNewSort()
	{
		var detailids = "";
		var sorts = "";
		
		for(var i=0; i<check_ids.length; i++){
			detailids += check_ids[i].value + ",";		//上架规则详细ID + ","
			sorts += newsorts[i].value + ",";			//顺序 + ","
		}
		
        window.returnValue = "&detailids=" + detailids + "&sorts=" + sorts;
		window.close();
	}
  -->
</script>
</head>

<body>
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：业务规则 -&gt; 分配规则 -&gt; 修改优先顺位</div></td>
    </tr>
  </table>
  
  <table><tr><td height="10"></td></tr></table> 

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
	<tr>
	  <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">选择</div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">优先顺位</div></td>
	  <!--<td width="60px" class="TD_LIST_TITLE"><div class="list_title">是否有效</div></td>-->
	  <td class="TD_LIST_TITLE"><div class="list_title">规则方法</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">清仓</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">拆零</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">拣货位超量分配</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">自动产生补货任务</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">存储位拣选</div></td>
	  <td class="TD_LIST_TITLE2"><div class="list_title">拆包</div></td>
	</tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
		
  	 	for(int i = 0; i < ls.size(); i++) {
  	 	
  	 		RuleAllocationDetail allocationDetail = (RuleAllocationDetail)ls.get(i);  
  	 		
  	 		String allocationid = allocationDetail.getAllocationid(); 			//分配规则ID
  	 	    String allocationdetailid = allocationDetail.getAllocationdetailid(); 	//分配规则详细ID
  	 	    int sort = allocationDetail.getSort();								//优先顺位
  	 	    String enableflag = allocationDetail.getEnableflag();				//有效
  	 	    String ruleconfigname = allocationDetail.getRuleconfigname(); 		//规则配置名称
  	 	    String clearloc_flag = allocationDetail.getClearloc_flag();			//清仓
  	 	    String apart_flag = allocationDetail.getApart_flag();				//拆零
  	 	    String over_flag = allocationDetail.getOver_flag();					//拣货位超量分配
  	 	    String auto_flag = allocationDetail.getAuto_flag();					//自动产生补货任务
  	 	    String bulkpick_flag = allocationDetail.getBulkpick_flag();			//存储位拣选
  	 	    String part_flag = allocationDetail.getPart_flag();					//拆包
  	 	    
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(this.rowIndex-1)">
	  <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=allocationdetailid%>"></td>
      <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=sort%>" size="2" readonly class="input_readonly"></td>
	  <!--<td class="TD_LIST" align="center"><%=enableflag == null ? "" : enableflag%></td>-->
	  <td class="TD_LIST" align="center"><%=ruleconfigname == null ? "" : ruleconfigname%></td>
	  <td class="TD_LIST" align="center"><%=clearloc_flag == null ? "" : clearloc_flag%></td>
	  <td class="TD_LIST" align="center"><%=apart_flag == null ? "" : apart_flag%></td>
	  <td class="TD_LIST" align="center"><%=over_flag == null ? "" : over_flag%></td>
	  <td class="TD_LIST" align="center"><%=auto_flag == null ? "" : auto_flag%></td>
	  <td class="TD_LIST" align="center"><%=bulkpick_flag == null ? "" : bulkpick_flag%></td>
	  <td class="TD_LIST2" align="center"><%=part_flag == null ? "" : part_flag%></td>
	</tr>
<%
 		}	
	 }
%>
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
  </table>
  <table><tr><td height="10"></td></tr></table> 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onClick="moveUp()" value="上移" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onClick="moveDown()" value="下移" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onclick="saveNewSort()" id="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>

</body>
</html>
