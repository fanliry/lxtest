<%@ page contentType="text/html; charset=GBK"%>
<%
	//�ͻ�ID
	String customerid = request.getParameter("customerid");
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
  <!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
	function OnOk(){
		var backmsg = null;
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i< check_ids.length; i++){
		 	if(check_ids[i].checked){
				backmsg = check_ids[i].value;
				break;
			}
		}
		if(backmsg==null || backmsg==""){
			alert("��ѡ��һ����¼��");
		}else{
			window.returnValue = backmsg;
			window.close();
		}
	} 
	
	//˫��
	function ondbClickDo(strVar)
	{
		window.returnValue = strVar;
		window.close();
	}
	
	//��ѯ
	function queryData()
	{
		var productname = document.getElementById("productname").value;
		var productcode = document.getElementById("productcode").value;
		var linerow = list.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����
		
		var condition = "&productname=" + productname + "&productcode=" + productcode + "&linerow=" + linerow;
		
		list.location.href = ac + "baseProductAction&flag=3" + condition;
	}
-->
</script>
</head>

<body> 
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td>
     
	  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ѡ����Ʒ</div></td>
		</tr>
	  </table>
    
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	    <tr>
	      <td class="yx1" width="130"><div align="right">��Ʒ���ƣ�</div></td>
	      <td class="yx"><input type="text" id="productname" size="20"></td>
	      <td class="yx1" width="130"><div align="right">��Ʒ���룺</div></td>
	      <td class="xx1"><input type="text" id="productcode" size="20"></td>
	    </tr>  
	    <tr>
          <td height="30" colspan="4" align="center" valign="bottom">
			<input type="button" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search" onClick="queryData()">
            <input type="button" value="ȷ��" class="BUTTON_STYLE1" onClick="OnOk()">
           	<input type="button" value="�ر�" class="BUTTON_STYLE1" onClick="window.close();" />
          </td>
        </tr>
	  </table>

	</td></tr>
	<tr><td height="5"></td></tr>
	<tr><td valign="top" height="100%">
	 
	  <table width="98%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td>
	 	    <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="auto"
				src="<%=request.getContextPath()%>/standard/jsp/common/common_product_list.jsp" ></iframe>
	      </td>
	    </tr> 
 	  </table>
 	
    </td></tr>
  </table>
 
</body>
</html>
