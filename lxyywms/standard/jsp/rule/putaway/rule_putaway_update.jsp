 <%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.rule.RulePutaway"%>
<%
	RulePutaway putawayRule = (RulePutaway)request.getAttribute("putawayRule");

	String putawayid = putawayRule.getPutawayid();		//�ϼܹ���ID
	String warehouse = putawayRule.getWarehousename();	//�����ֿ�
	String descr = putawayRule.getDescr();				//����
  	String remark = putawayRule.getRemark();			//��ע
%>
<html>
<head>
<title>���²�������</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function saveData()
	{
		var descr =  document.getElementById("descr").value;
		var remark = document.getElementById("remark").value;
		
		if(descr == null || descr.length < 1){
		
			alert("������������Ϊ�գ�");
			return;
		}
		if(strlen(descr) > 200){
		
			alert("��������������");
			return;
		}

       	if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
			
				alert("����ע��������");
				return;
			}
		}
		
		condition = "&putawayid=<%=putawayid%>" + "&descr=" + descr + "&remark=" + remark;
		window.returnValue = condition;
		window.close();
	}
	
-->
</script>
</head>
<body>

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ҵ����� -&gt; �ϼܹ��� -&gt; �޸��ϼܹ���</div></td>
   </tr>
 </table>

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>������</td>
     <td class="xx1"><input type="text" id="descr" size="30" maxlength="200" value="<%=descr%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">Ŀ��ֿ⣺</td>
     <td class="xx1"><input type="text" id="warehouse" size="30" readonly class="input_readonly" value="<%=warehouse==null?"":warehouse%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��ע��</td>
     <td class="xx1"><textarea id="remark" cols="83" rows="3"><%=remark == null ? "" : remark%></textarea></td>
   </tr>
   <tr>
     <td height="27" colspan="2" align="center">
         <input type="button" name="saveBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_add" onclick="saveData()">
         <input type="button" name="cancelBtn" value="�ر�" class="BUTTON_STYLE1" onClick="window.close()">
     </td>
   </tr>
 </table>

</body>
</html>