<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean receipt_qtrkd = false;   		
	boolean checkup = false;   		
	boolean backflowIn = false;   		
	boolean directReceipt = false;   		
	boolean search = false;   		
	boolean receipt_cprkd = false;   		
	boolean receipt_yflrkd = false;   		
	boolean receipt_xtrkd = false;   		
	boolean receipt_tlrkd = false;   		
	boolean receipt_cjrkd = false;   		
	boolean injob_safeguard = false;   		
	boolean receipt_bcrkd_1 = false;   		
	boolean receipt_cgrkd = false;   		
	boolean lotinfopick_assgin = false;   		


	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom"); 
	}
	if(hsPopedom != null){
		if(hsPopedom.get("receipt_qtrkd") != null){
			receipt_qtrkd = true;
		}
		if(hsPopedom.get("checkup") != null){
			checkup = true;
		}
		if(hsPopedom.get("backflowIn") != null){
			backflowIn = true;
		}
		if(hsPopedom.get("directReceipt") != null){
			directReceipt = true;
		}
		if(hsPopedom.get("search") != null){
			search = true;
		}
		if(hsPopedom.get("receipt_cprkd") != null){
			receipt_cprkd = true;
		}
		if(hsPopedom.get("receipt_yflrkd") != null){
			receipt_yflrkd = true;
		}
		if(hsPopedom.get("receipt_xtrkd") != null){
			receipt_xtrkd = true;
		}
		if(hsPopedom.get("receipt_tlrkd") != null){
			receipt_tlrkd = true;
		}
		if(hsPopedom.get("receipt_cjrkd") != null){
			receipt_cjrkd = true;
		}
		if(hsPopedom.get("injob_safeguard") != null){
			injob_safeguard = true;
		}
		if(hsPopedom.get("receipt_bcrkd_1") != null){
			receipt_bcrkd_1 = true;
		}
		if(hsPopedom.get("receipt_cgrkd") != null){
			receipt_cgrkd = true;
		}
		if(hsPopedom.get("lotinfopick_assgin") != null){
			lotinfopick_assgin = true;
		}
	}
	String strWarehouseID = (String)request.getAttribute("warehouseid");
	if(strWarehouseID == null || strWarehouseID.trim().length()<1)
	{
		strWarehouseID = request.getParameter("warehouseid");
		if(strWarehouseID == null || strWarehouseID.equals("null")){
			strWarehouseID = (String)session.getAttribute("warehouseid");
		}
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ�RF����ϵͳ</title>
<style>
body{ margin:0; padding:0; font-family:"����",Verdana, Arial;font-size:12px; color:#000000; text-align:center;
 min-height:1%; line-height:150%; background:url(<%=request.getContextPath()%>/standard/images/login_bg2.jpg) repeat-x; background-color:#5cbcff;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	function Goto(flag){  
		
		if(flag == 11) { //�������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_qtrkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 12) { //����
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/checkup.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 14) { //�������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/backflowIn.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 15){  //ֱ�ӳ���
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/directReceipt.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 16) { //��ѯ
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/search.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 17) { //��Ʒ���
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_cprkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 19) { //ԭ�������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_yflrkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 20) { //�������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_xtrkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 21) { //�������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_tlrkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 22) { //������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_cjrkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 23) { //���ά��
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/injob_safeguard.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 24) { //�������1
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_bcrkd_1.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 25) { //�ֹ����
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/receipt_cgrkd.jsp?warehouseid=<%=strWarehouseID%>";
		}else if(flag == 26) { //�����ż������
			window.location.href = "<%=request.getContextPath()%>/rf/lxyy/RFlotinfopick/lotinfopick_assgin.jsp?warehouseid=<%=strWarehouseID%>";
		}else {
			alert("δ����Ĳ�����");
		}
	}
-->
</script>
</head>

<body>
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="table1">
   <tr>
     <td class="TD_LIST_TITLE"><div class="list_title">RF���˵�(<a href="<%=request.getContextPath()%>/rf/login.jsp">����</a>)</div></td>
   </tr>

  <%
 	if(receipt_cprkd)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;��Ʒ���&nbsp;" onClick="Goto(17)"  style="width:130px"></td>
   </tr>
     <%
 	}
 	if(receipt_bcrkd_1)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�������&nbsp;" onClick="Goto(24)"  style="width:130px"></td>
   </tr>
     <%
 	}
 	if(receipt_yflrkd)
	{
%> 
      <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;ԭ�������&nbsp;" onClick="Goto(19)"  style="width:130px"></td>
   </tr>
     <%
 	}
 	if(receipt_xtrkd)
	{
%> 
      <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�������&nbsp;" onClick="Goto(20)"  style="width:130px"></td>
   </tr>
     <%
 	}
 	if(receipt_tlrkd)
	{
%> 
      <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�������&nbsp;" onClick="Goto(21)"  style="width:130px"></td>
   </tr>
  <%
 	}
 	if(receipt_qtrkd)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�������&nbsp;" onClick="Goto(11)"  style="width:130px"></td>
   </tr>
     <%
 	}
 	if(receipt_cgrkd)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�ֹ����&nbsp;" onClick="Goto(25)"  style="width:130px"></td>
   </tr>
  <%
 	}
 	if(directReceipt)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;ֱ�ӳ���&nbsp;" onClick="Goto(15)"  style="width:130px"></td>
   </tr>
  <%
 	}
 	if(checkup)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;��    ��&nbsp;" onClick="Goto(12)" style="width:130px"></td>
   </tr>
  <%
 	}
    if(backflowIn)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�������&nbsp;" onClick="Goto(14)" style="width:130px"></td>
   </tr>
  <%
 	}
    if(search)
	{
%> 
 <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;����ѯ&nbsp;" onClick="Goto(16)" style="width:130px"></td>
  </tr>
    <%
 	}
 	if(injob_safeguard)
	{
%> 
 <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;���ά��&nbsp;" onClick="Goto(23)" style="width:130px"></td>
  </tr>
    <%
 	}
 	if(lotinfopick_assgin)
	{
%> 
   <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;�����ż������&nbsp;" onClick="Goto(26)" style="width:130px"></td>
  </tr>
    <%
 	}
 	if(receipt_cjrkd)
	{
%> 
<!--  <tr>  
     <td align="center"  class="xx1" ><input type="button" value="&nbsp;������&nbsp;" onClick="Goto(22)" style="width:130px"></td>
  </tr> -->
  <%
 	}
 %>
</table>
</body>
</html>
