<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%
        // --customerid|ownerId,1|2,客户|货主--
	    String groupinfo = (String)request.getAttribute("groupinfo");
	    String aem[] = null;
	    String bem[] = null;
        if(groupinfo != null && groupinfo.trim().length()>0){ //获取字符字段
				
				aem = groupinfo.split(",");
				bem = aem[2].split("\\|");// 属性名
		}
		
	//表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
	
	var HKEY_Root,HKEY_Path,HKEY_Key;
	HKEY_Root="HKEY_CURRENT_USER";
	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	
	//设置网页打印的页眉页脚为空
	function PageSetup_Null()     
	{
		try
		{
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}
		catch(e){}
	}
	//设置网页打印的页眉页脚为默认值
	function PageSetup_Default()
	{
	  	try
	    {
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&b页00码,&p/&P");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
	    }     
		catch(e){}
	}
	
	//打开Excel报表
	function openexcel()
	{
		
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		
		var classname = "com.wms3.bms.standard.action.report.query.CXOUTQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=出库流水查询.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
		document.tempForm1.submit();	
		//alert(window.opener.condition);
		//alert(window.opener.getLotattCon());
	}
	
-->
</script>
<style media="print">
<!--
	.Noprint{display:none;}<!--用本样式在打印时隐藏非打印项目-->
	.PageNext{page-break-after: always;}<!--控制分页-->
-->
</style> 
<style type="text/css">
<!--
	.style2 {
		font-size: 24px; 
		font-weight: bold; 
		font-family: "楷体_GB2312";
	}
-->
</style>
</head>

<body>


 <!-- ======== 功能按钮开始 ======== -->
 <form id="myform1" name="myform1" method="post">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="Noprint">
   <tr height="30">
     <td><div align="left">
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       <input type="button" name="readyBtn" value="excel报表" class="BUTTON_STYLE1" onclick="openexcel();">&nbsp;
       <input type="button" name="readyBtn" value="打印预览" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="页面设置" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="打印" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="关闭" class="BUTTON_STYLE1" onclick="window.close()">
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== 功能按钮结束 ======== -->
 
<%
	int iLine = 0;	//显示的批次属性个数
	
%>

 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=18 + iLine%>" valign="bottom"><div align="center" class="style2">出&nbsp;存&nbsp;流&nbsp;水&nbsp;查&nbsp;询</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
 </table>
 <!-- ======== 标题结束 ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== 列表开始 ======== -->  	
 
  <table width="100%"  id="table" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
<%	
   if(bem != null && bem.length>0){ //获取分组字段
		for(int i=0; i<bem.length; i++){
%>
	     <td class="TD_LIST_TITLE"><div class="list_title"><%=bem[i]%></div></td>
<%
		}
	}else{
%>
		<td class="TD_LIST_TITLE" ><div class="list_title">产品编码</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
          <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">客户</div></td> 
         <td class="TD_LIST_TITLE" ><div class="list_title">单据编号</div></td> 
         <td class="TD_LIST_TITLE" ><div class="list_title">单据类型</div></td>
          <td class="TD_LIST_TITLE" ><div class="list_title">作业创建日期</div></td>
<%	
    }		
%>
	
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">体积</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">重量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">净重</div></td>
   </tr>
<%
	
	
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
		String createTime = null;   //创建日期
		double pnum;            	//数量
		double volume;          	//体积
		double pweight;         	//重量
     	double pnetweight;      	//净重

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
			ob = (Object[])ls.get(i);
  	 		if(bem != null && bem.length>0){ //分组之后
  	 		    pnum = Double.parseDouble(ob[bem.length].toString());			    //数量
  	 		    volume = Double.parseDouble(ob[bem.length+1].toString());		    //体积
  	 		    pweight = Double.parseDouble(ob[bem.length+2].toString());        	//毛重
  	 		    pnetweight = Double.parseDouble(ob[bem.length+3].toString());     	//净重
  	 		}else{ //没有分组
  	 			
  	 		    pnum = Double.parseDouble(ob[8].toString());			//数量
  	 		    volume = Double.parseDouble(ob[9].toString());		    //体积
  	 		    pweight = Double.parseDouble(ob[10].toString());        	//毛重
  	 		    pnetweight = Double.parseDouble(ob[11].toString());     	//净重
  	 		}
 		
			%>
			   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
			     <td class="TD_LIST1" align="center"><%=i+1%></td>
			<%	
			   if(bem != null && bem.length>0){ //获取分组字段
					for(int j=0; j<bem.length; j++){
			%>
				     <td class="TD_LIST"  align="center"><%=ob[j] == null ? "" : ob[j].toString()%></td>
			<%
					}
				}else{ //获取默认
			%>
			         <td class="TD_LIST"  align="center"><%=ob[0] == null ? "" : ob[0].toString()%></td>  
			         <td class="TD_LIST"  align="center"><%=ob[1] == null ? "" : ob[1].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[2] == null ? "" : ob[2].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[3] == null ? "" : ob[3].toString()%></td>  
			         <td class="TD_LIST"  align="center"><%=ob[4] == null ? "" : ob[4].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[5] == null ? "" : ob[5].toString()%></td> 
			          <td class="TD_LIST"  align="center"><%=ob[6] == null ? "" : ob[6].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[7] == null ? "" : ob[7].toString()%></td>
			<%	
			    }		
			%>
				 
			     <td class="TD_LIST" align="center"><%=pnum%></td>
			     <td class="TD_LIST" align="center"><%=volume%></td>
			     <td class="TD_LIST" align="center"><%=pweight%></td>
			     <td class="TD_LIST" align="center"><%=pnetweight%></td>
			   </tr>
			<%
		}
     }else{
		session.removeAttribute("paging");
	 }
%>
   <tr>
     <td height="100%" colspan="<%=4 + (bem!=null&&bem.length>0?bem.length:3)%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>

 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
