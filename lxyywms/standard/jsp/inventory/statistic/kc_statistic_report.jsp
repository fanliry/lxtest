<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null){
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
		alert(window.opener.condition);
		alert(window.opener.getPostCon());
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

 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=12 + itemscount%>" valign="bottom"><div align="center" class="style2">库&nbsp;存&nbsp;统&nbsp;计&nbsp;报&nbsp;表</div></td>
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
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr class="list_title_tr">
     <td class="list_title_td">行号</td>
     <td class="list_title_td">库区</td>
<%	
	for(int i=0; i<itemscount; i++){
%>
     <td class="list_title_td"><%=items[i]%></td>
<%
	}
%>
     <td class="list_title_td">单位</td>
     <td class="list_title_td">库存数量</td>
     <td class="list_title_td">库存箱数</td>
     <td class="list_title_td">库存毛重</td>
     <td class="list_title_td">库存净重</td>
     <td class="list_title_td">库存体积</td>
     <td class="list_title_td">冻结数量</td>
     <td class="list_title_td">冻结毛重</td>
     <td class="list_title_td">冻结净重</td>
     <td class="list_title_td">冻结体积</td>
   </tr>
   
<%

	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
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
		
		//合计行
		double pnum_sum = 0;            	//库存数量
		long boxnum_sum = 0;          		//库存箱数
		double pweight_sum = 0;         	//库存毛重
     	double pnetweight_sum = 0;      	//库存净重
     	double pvolume_sum = 0;         	//库存体积
		double holdnum_sum = 0;         	//冻结数量
		double holdweight_sum = 0;      	//冻结毛重
		double holdnetweight_sum = 0;      	//冻结净重
		double holdvolume_sum = 0;         	//冻结体积

  	 	for(int i=0; i<ls.size(); i++) {
  	 	
			ob = (Object[])ls.get(i);
  	 		whArea = ob[0] == null ? "" : ob[0].toString();			//库区
  	 		punit = ob[1] == null ? "" : ob[1].toString();			//单位
  	 		
  	 		pnum = Double.parseDouble(ob[2].toString());			//库存数量
  	 		boxnum = Long.parseLong(ob[3].toString());				//库存箱数
  	 		pweight = Double.parseDouble(ob[4].toString());        	//库存毛重
  	 		pnetweight = Double.parseDouble(ob[5].toString());     	//库存净重
  	 		pvolume = Double.parseDouble(ob[6].toString());        	//库存体积
  	 		holdnum = Double.parseDouble(ob[7].toString());			//冻结数量
  	 		holdweight = Double.parseDouble(ob[8].toString());     	//冻结毛重
			holdnetweight = Double.parseDouble(ob[9].toString());  //冻结净重
			holdvolume = Double.parseDouble(ob[10].toString());     //冻结体积
  	 		
  	 		//合计行
			pnum_sum += pnum;            		//库存数量
			boxnum_sum += boxnum;          		//库存箱数
			pweight_sum += pweight;         	//库存毛重
     		pnetweight_sum += pnetweight;      	//库存净重
     		pvolume_sum += pvolume;         	//库存体积
			holdnum_sum += holdnum;         	//冻结数量
			holdweight_sum += holdweight;      	//冻结毛重
			holdnetweight_sum += holdnetweight; //冻结净重
			holdvolume_sum += holdvolume;       //冻结体积
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=whArea%></td>
<%
	for(i=0; i<itemscount; i++){
%>
     <td class="list_normal_td"><%=ob[11+i] == null ? "" : ob[11+i].toString()%></td>
<%
	}
%>
     <td class="list_normal_td"><%=punit%></td>
     <td class="list_normal_td"><%=pnum%></td>
     <td class="list_normal_td"><%=boxnum%></td>
     <td class="list_normal_td"><%=pweight%></td>
     <td class="list_normal_td"><%=pnetweight%></td>
     <td class="list_normal_td"><%=pvolume%></td>
     <td class="list_normal_td"><%=holdnum%></td>
     <td class="list_normal_td"><%=holdweight%></td>
     <td class="list_normal_td"><%=holdnetweight%></td>
     <td class="list_normal_td"><%=holdvolume%></td>
   </tr>
   
<%
		}
%>
   <tr class="list_normal_tr">
   <tr>
     <td class="list_normal_td"></td>
     <td class="list_normal_td" style="font-weight: bold;">合计</td>
<%
	for(int i=0; i<itemscount; i++){
%>
     <td class="list_normal_td">&nbsp;</td>
<%
	}
%>	 		 
     <td class="list_normal_td">&nbsp;</td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pnum_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=boxnum_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pnetweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pvolume_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdnum_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdnetweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdvolume_sum%></td>
   </tr> 
<%
	}
%>
 </table>

</body>
</html>
