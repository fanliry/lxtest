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
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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
	//��������Ƿ�Ϊ����
	function IsNum(num) {
		var reNum = /^\d*$/;
		return (reNum.test(num));
	}

	function querydata() {
 		var productId = document.getElementById("package_id").value; //Ʒ��
		var lotinfo = document.getElementById("lotinfo").value; //����
		var Qualityid = document.getElementById("Qualityid").value; //�ʼ쵥��
		var createtime = document.getElementById("createtime").value; //ʱ��
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
							��ǰλ�ã�<span>��������</span> &gt;&gt; <span>���м�¼��ѯ<a href="#" onclick="querydata();">��ѯ</a></span>
						</div>
						<div class="f_r" id="caozuo">
							<ul>
									<%if(pushSearchQuery){%><li class="tubiao1"><a href="#" onclick="querydata();">��ѯ</a></li><%}%>
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
							<td class="yx1" align="right">Ʒ����</td>
							<td class="yx"><input type="hidden" name="package_id"><input
								type="text" name="package_name" readonly> <input
								onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"
								type="button" value="��" class="button_select"></td>
							<td class="yx1"><div align="right">���ţ�</div></td>
							<td class="yx"><input type="text" name="lotinfo">&nbsp;</td>
							<td class="yx1"><div align="right">�ʼ쵥�ţ�</div></td>
							<td class="yx"><input type="text" name="Qualityid">&nbsp;</td>
							<td class="yx1" align="right">ʱ�䣺</td>
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

 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
