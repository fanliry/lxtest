<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime = StrTools.getCurrDateTime(8);
	String strWarehouseId = (String) request.getSession(false)
			.getAttribute("warehouseid");
	
	HashMap hsPopedom = null;
	boolean pushSearchQuery = false; 

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("pushSearchQuery") != null){
			pushSearchQuery = true;
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
    var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	//检查数量是否为数字
	function IsNum(num) {
		var reNum = /^\d*$/;
		return (reNum.test(num));
	}

	function querydata() {
 		var productId = document.getElementById("package_id").value; //品名
		var lotinfo = document.getElementById("lotinfo").value; //批号
		var Qualityid = document.getElementById("Qualityid").value; //质检单号
		var createtime = document.getElementById("createtime").value; //时间
		var maxLine = page.document.getElementById("lineviewrow").value;

		condition = "&productId=" + productId + "&lotinfo=" + lotinfo
				+ "&Qualityid=" + Qualityid + "&createtime=" + createtime
				+ "&maxLine=" + maxLine;
		
		list.formx1.action = strUrl + "QualityNewAction&method=ricoExec" + condition;
		
		list.document.formx1.submit();
		
		Loading();	

	}
	function OnLoad() {
		querydata();
	}
</script>
</head>

<body onload="javascript:OnLoad();">
	<div class="con_bk">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>
					<div class="wz">
						<div id="dqwz" class="f_l">
							当前位置：<span>质量管理</span> &gt;&gt; <span>放行记录查询<a href="#" onclick="querydata();">查询</a></span>
						</div>
						<div class="f_r" id="caozuo">
							<ul>
									<%if(pushSearchQuery){%><li class="tubiao1"><a href="#" onclick="querydata();">查询</a></li><%}%>
							</ul>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td height="5"></td>
			</tr>
			<tr>
				<td>
					<table id="querycondition" width="99%" border="0" align="center"
						cellpadding="0" cellspacing="0" class="table1">

						<tr>
							<td class="yx1" align="right">品名：</td>
							<td class="yx"><input type="hidden" name="package_id"><input
								type="text" name="package_name" readonly> <input
								onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"
								type="button" value="…" class="button_select"></td>
							<td class="yx1"><div align="right">批号：</div></td>
							<td class="yx"><input type="text" name="lotinfo">&nbsp;</td>
							<td class="yx1"><div align="right">质检单号：</div></td>
							<td class="yx"><input type="text" name="Qualityid">&nbsp;</td>
							<td class="yx1" align="right">时间：</td>
							<td class="yx"><input id="createtime" type="text"
								value="<%=strTime%>" onfocus="calendar();" class="Wdate"
								style="width: 100px;"></td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="5"></td>
						</tr>
					</table>

				</td>
			</tr>
			<tr>
				<td height="100%" valign="top">

					<table width="99%" height="100%" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td><iframe id="list" name="list" width="100%" height="100%"
									frameborder="0" scrolling="yes"
									src="<%=request.getContextPath()%>/standard/jsp/quality/push/pushsearch_list.jsp"></iframe>
							</td>
						</tr>
						<tr>
							<td height="25"><iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y"
									width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</div>

 <!-- 页面加载层（start） -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- 页面加载层（end） -->  
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
