<%@ page contentType="text/html; charset=GBK"%>
<%

	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<style>
html { overflow-y:hidden; }
</style>
<script>
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("上架成功！");
			}else{
				alert(back_msg);
			}
			//获取收货单ID
			var invoiceid = "<%=request.getAttribute("invoiceid")==null?"":request.getAttribute("invoiceid")%>";
			if(invoiceid != "")
			{
				parent.myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction&flag=1&invoiceid=" + invoiceid;
			}
		}
	}
	
</script>
</head>

<body onload="javascript:OnLoad();">
 <table id="puttb" width="150%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr height="20">
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">跟踪号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">净重</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">毛重</div></td> 
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次1</div></td> 
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次2</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次3</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次4</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次5</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次6</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次7</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次8</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次9</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次10</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次11</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">批次12</div></td>
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">操作</div></td>
   </tr>


 </table>
 
<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
