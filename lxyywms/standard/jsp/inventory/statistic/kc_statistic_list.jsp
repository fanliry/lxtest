<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null && list_items !=""){
		items = list_items.split(",");
		itemscount = items.length;
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
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="130%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
<%	
	for(int i=0; i<itemscount; i++){
%>
     <td class="TD_LIST_TITLE"><div class="list_title"><%=items[i]%></div></td>
<%
	}
%>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存箱数</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存毛重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存净重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存体积</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结毛重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结净重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">冻结体积</div></td>
   </tr>
<%
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
		String whArea;				//库区
		String punit;				//单位
		double pnum;            	//库存数量
		long boxnum;          		//库存箱数
		double pweight;         	//库存毛重
     	double pnetweight;      	//库存净重
     	double pvolume;         	//库存体积
		double holdnum;         	//冻结数量
		double holdweight;      	//冻结毛重
		double holdnetweight;      	//冻结净重
		double holdvolume;         	//冻结体积

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
			ob = (Object[])ls.get(i);
  	 		whArea = ob[0] == null ? "" : ob[0].toString();			//库区
  	 		punit = ob[itemscount+1] == null ? "" : ob[itemscount+1].toString();			//单位
  	 		pnum = Double.parseDouble(ob[itemscount+2].toString());			//库存数量
  	 		boxnum = Long.parseLong(ob[itemscount+3].toString());				//库存箱数
  	 		pweight = Double.parseDouble(ob[itemscount+4].toString());        	//库存毛重
  	 		pnetweight = Double.parseDouble(ob[itemscount+5].toString());     	//库存净重
  	 		pvolume = Double.parseDouble(ob[itemscount+6].toString());        	//库存体积
  	 		holdnum = Double.parseDouble(ob[itemscount+7].toString());			//冻结数量
  	 		holdweight = Double.parseDouble(ob[itemscount+8].toString());     	//冻结毛重
			holdnetweight = Double.parseDouble(ob[itemscount+9].toString());  //冻结净重
			holdvolume = Double.parseDouble(ob[itemscount+10].toString());     //冻结体积
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea%></td>
<%
	for(int t=0; t<itemscount; t++){
%>
     <td class="TD_LIST" align="center"><%=ob[1+t] == null ? "" : ob[1+t].toString()%></td>
<%
	}
%>
     <td class="TD_LIST" align="center"><%=punit%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
     <td class="TD_LIST" align="center"><%=boxnum%></td>
     <td class="TD_LIST" align="center"><%=pweight%></td>
     <td class="TD_LIST" align="center"><%=pnetweight%></td>
     <td class="TD_LIST" align="center"><%=pvolume%></td>
     <td class="TD_LIST" align="center"><%=holdnum%></td>
     <td class="TD_LIST" align="center"><%=holdweight%></td>
     <td class="TD_LIST" align="center"><%=holdnetweight%></td>
     <td class="TD_LIST2" align="center"><%=holdvolume%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=12 + itemscount%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
