<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	List lsLot = (List)request.getAttribute("lsLot");
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
  	var check_ids = document.getElementsByName("check_id");
  	var check_names = document.getElementsByName("names");
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
		var detailnames = "";
		var sorts = "";
		for(var i=0;i< check_ids.length;i++)   
		{
		    if(check_ids[i].checked)
		    {
			    detailids += check_ids[i].value + "|";		    //分组ID + ","
			    detailnames += check_names[i].value + "|";		//分组属性名 + ","
				sorts += newsorts[i].value + "|";			    //顺序 + ","
		    }
		}
		if(detailids!=null && detailids.length > 0){
		   detailids = detailids.substring(0, detailids.length-1)+ ",";
		}
		if(detailnames!=null && detailnames.length > 0){
		   detailnames = detailnames.substring(0, detailnames.length-1);
		}
		if(sorts!=null && sorts.length > 0){
		   sorts = sorts.substring(0, sorts.length-1)+ ",";
		}
		
        window.returnValue = detailids  + sorts + detailnames;
		window.close();
	}
  -->
</script>
</head>

<body>
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：查询统计 -&gt; 入库流水查询 -&gt; 统计分组属性</div></td>
    </tr>
  </table>
  
  <table><tr><td height="10"></td></tr></table> 

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
	<tr>
	  <td class="TD_LIST_TITLE1" width="30px"><div class="list_title">选择</div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">分组顺序</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">属性名称</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">属性编码</div></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="productcode"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=1%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="产品编码" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="productcode" readonly class="input_readonly"></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="productid"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=2%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="品名" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="productid" readonly class="input_readonly"></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="lotinfo"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=3%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="批号" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="lotinfo" readonly class="input_readonly"></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="printdate"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=4%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="生产日期" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="productDate" readonly class="input_readonly"></td>
	</tr>
	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="boundstockid"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=5%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="单据编号" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="boundstockid" readonly class="input_readonly"></td>
	</tr>
	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="jobtype"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=6%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="单据类型" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="jobtype" readonly class="input_readonly"></td>
	</tr>
	
	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="createtime"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=7%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="作业创建日期" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="createtime" readonly class="input_readonly"></td>
	</tr>
	
	
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);	  
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=lotSet.getLotid()%>"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=k+5%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="<%=lotSet.getLotname() == null ? "" : lotSet.getLotname()%>" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="<%=lotSet.getLotid()%>" readonly class="input_readonly"></td>
	</tr>
<%
 		}	
	 }
%>
  </table>
  <table><tr><td height="10"></td></tr></table> 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onClick="moveUp()" value="上移" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onClick="moveDown()" value="下移" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onclick="saveNewSort()" id="add" value="&nbsp;&nbsp;&nbsp;确定" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>

</body>
</html>
