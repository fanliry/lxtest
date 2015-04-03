<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseBatch"%>

<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var check_ids = document.getElementsByName("check_id");
	
	
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
	
	//单击
	function Detail(i){
	
		setSel(i);
		var ids = check_ids[i].value.split(",");
		var id = ids[0];
		
		parent.mean.location.href = ac + "baseBatchAction&flag=3&id=" + id;
		parent.rule.location.href = ac + "baseBatchAction&flag=5&id=" + id;
	}
	
	//双击修改行
	function upd(i){

		var ids = check_ids[i].value.split(",");
		var id = ids[0];
		var result = showModalDialog(ac + "baseBatchAction&flag=2&id="+id, "", "dialogWidth=400px; dialogHeight=200px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseBatchAction&method=ricoExecEditBatch" + result;
		}
	}
 -->
</script>
</head>
<body>

  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">批次代码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">批次名称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">长度</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">启用</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls!=null)
	{
		BaseBatch batch = null; 
		String batchid;			//批次ID
		String batchname;		//批次名称
		Integer batchlength;	//长度
		String useflag;			//是否启用 Y:是 N.否
		String param;
		
		for(int i=0; i<ls.size(); i++)
		{
			batch = (BaseBatch)ls.get(i);
			batchid = batch.getBatchid();
			batchname = batch.getBatchname();
			batchlength = batch.getBatchlength();
			useflag = batch.getUseflag();
			
			param = batchid + "," + batchlength;
%>   
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" onClick="Detail(<%=i%>)"  ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" value="<%=param%>" class="input_checkbox"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=batchid == null ? "" : batchid%></td>
     <td class="TD_LIST" align="center"><%=batchname == null ? "" : batchname%></td>
     <td class="TD_LIST" align="center"><%=batchlength == null ? "" : batchlength%></td>
     <td class="TD_LIST2" align="center"><%=useflag == null ? "" : useflag%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="5" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>