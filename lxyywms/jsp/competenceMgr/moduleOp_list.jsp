<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ricosoft.entity.competenceMgr.ModuleOpInfo" %>
<html>
<head>
  <title>仓储配送管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
  <script type="text/javascript">
  <!--
   //全选
    function selectAll()
	{
		var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
			if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_all'){
				 state = document.myform.elements[i].checked;
				 break;
			}
		}
		for(i=0;i<length;i++){
			 if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_id'){
				   document.myform.elements[i].checked=state;
			 }
		}
	}
//全选
	function CheckAll(){
		var state = document.getElementById("checkbox_all").checked;
		var check_ids = document.getElementsByName("checkbox_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;		
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("checkbox_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
  -->
  </script>
</head>

<!-- <body onload="javascript:parent.myIframe1.location.reload();parent.viewTree();"> -->
<body onload="javascript:parent.myIframe1.location.reload();">
<%
	List ls = null;
	if(request.getAttribute("exResultList") != null)
	{
		ls = (List)request.getAttribute("exResultList");
	}
	
%>
<form id="myform" name="myform" method="post" action="">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr class="list_title_tr">
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" onclick="CheckAll()">全选
	  </div></td>
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">行号</div></td>
	  <td width="20%" class="TD_LIST_TITLE"><div class="list_title">模块代码</div></td>
	  <td width="30%" class="TD_LIST_TITLE"><div class="list_title">模块名</div></td>
	  <td width="30%" class="TD_LIST_TITLE"><div class="list_title">父模块名</div></td>
	</tr>
	  <%
	  	if(ls != null && !ls.isEmpty())
	  	{
	  	 	for(int i = 0; i < ls.size(); i++) 
	  	 	{
	  	 		ModuleOpInfo modOp = (ModuleOpInfo)ls.get(i);   
				String strModuleOpId = modOp.getM_strModuleOpId();
	 			String strModOpCode = modOp.getM_strModOpCode();
	 			String strModOpName = modOp.getM_strModOpName();
	 			String strpModOpName = modOp.getM_strpModOpName();
	 			
	 			String strParentId = modOp.getM_strParentId();
	 			String strpModOpCode = modOp.getM_strpModOpCode();
	 			String strMType = modOp.getM_strMType();
	 			String strModOpUrl = modOp.getM_strModOpUrl();
	 			String strLinked = modOp.getM_strLinked();
	 			String strTargetModOpUrl = modOp.getM_strTargetUrl();
	 			String strModOpLevel = modOp.getM_strModOpLevel();
	 			String strFailUrl = modOp.getM_strFailUrl();
	 			String strModOpTradeCode = modOp.getM_strTradeCode();
	 			String strRemark = modOp.getM_strRemark();
	  			
	  			   String param = "moduleOpId="+strModuleOpId+
                        					"&parentId="+strParentId+
                        					"&pmodOpCode="+strpModOpCode+
                        					"&pModOpName="+strpModOpName+
                        					"&mType="+strMType+
                        					"&modOpCode="+strModOpCode+
                        					"&modOpName="+strModOpName+
                        					"&modOpUrl="+strModOpUrl+
                        					"&selectLinked="+strLinked+
                        					"&targetUrl="+strTargetModOpUrl+
                        					"&modOpTradeCode="+strModOpTradeCode+
                        					"&failUrl="+strFailUrl+
                        					"&modOpLevel="+strModOpLevel+
                        					"&remark="+strRemark;
                        	
	  	%>
	  		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" align="center" onclick="setSel(<%=i%>)">
			  <td class="TD_LIST1">
			    <input type="checkbox" name="checkbox_id" value="<%=strModuleOpId%>" class="input_checkbox" id="<%=param%>" onclick="setSel(<%=i%>)">
			  </td>
			  <td class="TD_LIST1"><%=i+1%></td>
			  <td class="TD_LIST1"><%=strModOpCode == null ? "" : strModOpCode%></td>
			  <td class="TD_LIST1"><%=strModOpName == null ? "" : strModOpName%></td>
			  <td class="TD_LIST1"><%=strpModOpName == null ? "" : strpModOpName%></td>
			</tr>
	  	<%
	  	 	}
	  	 }else
	  	 {
	  	 	session.removeAttribute("paging");
	  	 }
	  %>
		<!-- ==== 最后一行（空行），必须有 ==== -->
	 <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>

</body>
</html>
