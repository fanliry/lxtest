<%@page import="com.ricosoft.common.tools.StrTools"%>
<%@ page contentType="text/html; charset=GBK"%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
	String warehouseid = request.getParameter("warehouseid");
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css"rel="stylesheet" type="text/css" />
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript">	
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
    //��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//��������Ƿ�Ϊ������
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function OnOk() {
		var backmsg = null;
		var check_ids = list.document.getElementsByName("check_id");
		for ( var i = 0; i < check_ids.length; i++) {
			if (check_ids[i].checked) {
				backmsg = check_ids[i].value;
				break;
			}
		}
		if (backmsg == null || backmsg == "") {
			alert("��ѡ��һ��������Ϣ��");
		} else {
		    var ids = "";
		    var reserve2 ="";
		    var sotype ="";
			var check_ids = detail.document.getElementsByName("check_id");//������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ
			//var sotypes   = detail.document.getElementsByName("sotype");//SO ����
			var checkNums = detail.document.getElementsByName("checkNum");//����
			var k = 0;
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked == true){
				    if(checkNums[i].value == null || (checkNums[i].value).length < 1 || !IsFloat(checkNums[i].value)){
						alert("������������Ϊ�� �� ֻ��Ϊ���֣�");
						return;
					}
					ids += check_ids[i].value + "&"+checkNums[i].value+"&"+ ",";
					reserve2=check_ids[i].alt;
					//sotype =sotypes[i].value;
					k++;
				}
			}
			//alert(sotype);
			if(ids == ""){
				alert("��ѡ��һ����ⶩ����ϸ��");
				return;
			}else{
				ids = ids.substring(0, ids.length-1);
			}
			if(sotype=="2"){
			 if(reserve2=="Y"){//����������Ͳ��ϾͰ�so���ͱ��Ϊ5.�߱߳��ⵥ
				sotype="5";
			 }
			}
			//ids=sotype+"/"+ids;
			window.returnValue =ids; //������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ&����&��������1=ֵ|��������2=ֵ|��������3=ֵ
    		window.close();
		}
	}
	//˫��
	function ondbClickDo(strVar) {
		window.returnValue = strVar;
		window.close();
	}
	//��ѯ
	function queryData() {
		var soid = document.getElementById("soid").value;
		var customerid = document.getElementById("customer_id").value;//�ͻ�id
		var createdate = document.getElementById("indate").value;//��������ʱ��
		var sotype = document.getElementById("sotype").value;	//��������
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		var condition = "&m_strCustomerId="+ customerid + "&m_strDownTime=" + createdate
					  + "&m_strSaleFormId=" + soid + "&m_strSaleFormType=" + sotype + "&linerow=" + linerow;
		list.location.href = ac + "SaleFormAction&flag=1" + condition;
	}
	function onLoad()
	{
		selectType('','sotype','ddlx');		//��������
	}
</script>
</head>
<body onload="onLoad();">
	<div class="con_bk">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>
					<table width="98%" align="center" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ѡ����ⶩ��</div>
							</td>
						</tr>
					</table>

					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="table_add">
						<tr>
							<td class="yx1" width="100"><div align="right">�ͻ����ƣ�</div></td>
							<td class="yx">	
								<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       							<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');"></td>
						     <td class="yx1" width="100px" align="right">���ڣ�</td>
	     					 <td class="xx1">
	      						<input id="indate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> 
						</tr>
						<tr>
							<td class="yx1" width="100"><div align="right">���۶����ţ�</div></td>
							<td class="yx">	
       							<input id="soid" type="text" value="" class=""></td>
						     <td class="yx1" width="100px" align="right">�������ͣ�</td>
	     					 <td class="xx1">
	      						<select id="sotype"><option>---��ѡ��---</option></select></td>	 
						</tr>
						<tr>
							<td height="30" colspan="4" align="center" valign="bottom"><input type="button" value="��ѯ" class="button_search" onClick="queryData()"> 
								<input type="button" value="ȷ��" class="button_add" onClick="OnOk()"> 
								<input type="button" value="�ر�" class="button_delete" onClick="window.close();" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="100%">
					<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="18" class="title">���ⶩ����Ϣ</td>
						</tr>
						<tr>
							<td height="180"><iframe id="list" name="list" width="100%"
									height="100%" frameborder="0" scrolling="yes"
									src="<%=request.getContextPath()%>/standard/jsp/outbound/so/outbound_so_list.jsp"></iframe>
							</td>
						</tr>
						<tr>
							<td height="25"><iframe id="page" name="page"
									src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"
									width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
							</td>
						</tr>
						<tr>
							<td valign="top" class="title" height="18">���ⶩ����ϸ��Ϣ</td>
						</tr>
						<tr>
							<td><iframe id="detail" name="detail"
									width="100%" height="100%" frameborder="0" scrolling="yes"
									src="<%=request.getContextPath()%>/standard/jsp/outbound/so/outbound_so_detail.jsp"></iframe>
							</td>
						</tr>
					</table>
					 <!-- *******************************-->
				</td>
			</tr>
		</table>

	</div>

	<!-- ҳ����ز㣨start�� -->
	<div id="loader_container"
		style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
		<div id="loader">
			<div align="center">ҳ�����ڼ��� ...</div>
			<div id="loader_bg">
				<div id="progress"></div>
			</div>
		</div>
	</div>
	<!-- ҳ����ز㣨end�� -->

</body>
</html>
