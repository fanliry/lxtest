<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>

<html>
<head>
<title>���ӿ�������</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
<!--
	//����
	function saveData()
	{
	
	   var strcustomerid = document.getElementById("customer_id").value;
	   var strstatus = document.getElementById("status").value;
	   var strreasoncode = document.getElementById("reasoncode").value;
	   var strreasondiscr = document.getElementById("reasondiscr").value;
	   var strdoctype = document.getElementById("doctype").value;
	   if(strcustomerid == null || strcustomerid.length<1)
		{
			alert("���ͻ�����Ϊ��");
			return;
		}
		if(strreasoncode == null || strreasoncode.length<1)
		{
			alert("��ԭ����롿��Ϊ��");
			return;
		}
		var strParam = "BMSService?actionCode=productAdjustAction&method=ricoExecAdd" 
        					+ "&customerid="+strcustomerid
        					+ "&status="+strstatus
        					+ "&reasoncode="+strreasoncode
        					+ "&doctype="+strdoctype
							+ "&reasondiscr=" + strreasondiscr;     
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
		return true;
	}
	function basCustomer(obj, url, obj1)
    {
		var strUrl=url + '/jsp/selectPages/customer_select.jsp';
		var result = showModalDialog(strUrl,'','dialogWidth=650px;dialogHeight=440px');	
		if(result != null && result.length > 0)
		{
			 var tempParam = result;
	
			 var temStr = tempParam.split("|");
			 		
			 document.getElementById(obj).value = temStr[0];
			 if(obj1)
			 {
			 	document.getElementById(obj1).value = temStr[1];
			 }
		}
    }
    function OnLoad()
	{
		selectType('2', 'doctype', 'dzlx');
	}
-->
</script>
</head>
<%
	String strTime = StrTools.getCurrDateTime(5);
%>
<body   onload="javascript:OnLoad();">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt;������ -&gt; ���ӿ�������</div></td>
   </tr>
 </table>
 <form>
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1"><div align="right">�ͻ���</div></td>
     <td class="yx"><div align="left">
            <input type="text" name="customer_name" class="input_readonly" size="15"/>
     		<input type="hidden" name="customer_id">
	        <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" />
	 </div></td>
	 <td class="yx1" width="60"><div align="right">��֤���ͣ�</div></td>
            <td class="yx"  width="" disabled><div align="left">
               <select id="doctype" style="width:130px;">
               <option value="">--��ѡ��--</option>
            </select></div>
     </td>
     
   </tr>
   <tr>
     <td class="yx1"><div align="right">״̬��</div></td>
     <td class="yx"><div align="left" disabled>
     <select name="status" class="input_readonly" style="width:130px;">
	         <option value="0" selected="selected">����</option>
	 </select>
     </div></td>
     <td  class="yx1"><div align="right">ԭ����룺</div></td>
     <td  class="yx"><div align="left">
     	<select name="reasoncode"  style="width:130px;">
	         <option value="">--��ѡ��--</option>
	         <option value="1">����</option>
	         <option value="2">��Ʒ��������</option>
	 </select>
	      
	  </div></td>
     
   </tr>
   <tr>
      <td class="yx1"><div align="right">ԭ��</div></td>
      <td class="yx" colspan="3"><div align="left">
     		<input type="text" size="17" name="reasondiscr">    
      </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;����" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
</body>
</html>