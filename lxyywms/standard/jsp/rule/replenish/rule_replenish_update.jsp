<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.rule.RuleReplenish" %>
<%
	RuleReplenish replenishRule = (RuleReplenish)request.getAttribute("replenishRule");

	String replenishid = replenishRule.getReplenishid();		//��������ID
	String warehouseid = replenishRule.getWarehouseid();		//�����ֿ�ID
	String descr = replenishRule.getDescr();					//����
	String ruleconfigid = replenishRule.getRuleconfigid();		//������ʽ
	 	
	int fEA_Lowerlimit = (int)replenishRule.getEa_lowerlimit();
	int fEA_Uplimit = (int)replenishRule.getEa_uplimit();
	int fEA_Minreplenishqty = (int)replenishRule.getEa_minreplenishqty();
	int fCS_Lowerlimit = (int)replenishRule.getCs_lowerlimit();
	int fCS_Uplimit = (int)replenishRule.getCs_uplimit();
	int fCS_Minreplenishqty = (int)replenishRule.getCs_minreplenishqty();
	int fOT_Lowerlimit = (int)replenishRule.getOt_lowerlimit();
	int fOT_Uplimit = (int)replenishRule.getOt_uplimit();
	int fOT_Minreplenishqty = (int)replenishRule.getOt_minreplenishqty();
  	 		
  	String remark = replenishRule.getRemark();
	
	String uom = "��";
	if(ruleconfigid != null && ruleconfigid.trim().equals("003003"))
	{
		uom = "��";
	}
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
		var descr = document.getElementById("descr").value;
		var warehouseid = document.getElementById("warehouseid").value;
		var ruleconfigid = document.getElementById("ruleconfigid").value;
		var remark = document.getElementById("remark").value;
		
		if(descr == null || descr.length < 1){
		
			alert("������������Ϊ�գ�");
			return;
		}
		if(strlen(descr) > 200){
		
			alert("��������������");
			return;
		}

       	if(ruleconfigid == "" || ruleconfigid.length <1){
       	
       		alert("��������ʽ������Ϊ��!");
       		return; 
       	}
       	
       	if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
			
				alert("����ע��������");
				return;
			}
		}
       	
       	var ea_lowerlimit = document.getElementById("ea_lowerlimit").value;
        var ea_uplimit    = document.getElementById("ea_uplimit").value;
        var ea_minreplenishqty = document.getElementById("ea_minreplenishqty").value;
        var cs_lowerlimit = document.getElementById("cs_lowerlimit").value;
        var cs_uplimit = document.getElementById("cs_uplimit").value;
        var cs_minreplenishqty = document.getElementById("cs_minreplenishqty").value;
        var ot_lowerlimit = document.getElementById("ot_lowerlimit").value;
        var ot_uplimit = document.getElementById("ot_uplimit").value;
        var ot_minreplenishqty = document.getElementById("ot_minreplenishqty").value;
       	
       	if(ea_lowerlimit != null && ea_lowerlimit.length > 0 && ea_lowerlimit != 0){

			if(!numChar(ea_lowerlimit)){
				alert("������������ޡ�ֻ��Ϊ��������0��");
				return;
			}
		}
		if(ea_uplimit != null && ea_uplimit.length > 0 && ea_uplimit != 0){

			if(!numChar(ea_uplimit)){
				alert("������������ޡ�ֻ��Ϊ��������0��");
				return;
			}
		}
		if(ea_minreplenishqty != null && ea_minreplenishqty.length > 0 && ea_minreplenishqty != 0){

			if(!numChar(ea_minreplenishqty)){
				alert("�����������С��������ֻ��Ϊ��������0��");
				return;
			}
		}
		
		if(cs_lowerlimit != null && cs_lowerlimit.length > 0 && cs_lowerlimit != 0){

			if(!numChar(cs_lowerlimit)){
				alert("�����������ޡ�ֻ��Ϊ��������0��");
				return;
			}
		}
		if(cs_uplimit != null && cs_uplimit.length > 0 && cs_uplimit != 0){

			if(!numChar(cs_uplimit)){
				alert("�����������ޡ�ֻ��Ϊ��������0��");
				return;
			}
		}
		if(cs_minreplenishqty != null && cs_minreplenishqty.length > 0 && cs_minreplenishqty != 0){

			if(!numChar(cs_minreplenishqty)){
				alert("����������С��������ֻ��Ϊ��������0��");
				return;
			}
		}

		if(ot_lowerlimit != null && ot_lowerlimit.length > 0 && ot_lowerlimit != 0){

			if(!numChar(ot_lowerlimit)){
				alert("����/����������ޡ�ֻ��Ϊ��������0��");
				return;
			}
		}
		if(ot_uplimit != null && ot_uplimit.length > 0 && ot_uplimit != 0){

			if(!numChar(ot_uplimit)){
				alert("����/����������ޡ�ֻ��Ϊ��������0��");
				return;
			}
		}		
		if(ot_minreplenishqty != null && ot_minreplenishqty.length > 0 && ot_minreplenishqty != 0){

			if(!numChar(ot_minreplenishqty)){
				alert("����/���������С��������ֻ��Ϊ��������0��");
				return;
			}
		}
		condition = "&replenishid=<%=replenishid%>" + "&warehouseid=" + warehouseid + "&descr=" + descr
				 + "&ruleconfigid=" + ruleconfigid + "&remark=" + remark 
				 + "&ea_lowerlimit=" + ea_lowerlimit + "&ea_uplimit=" + ea_uplimit + "&ea_minreplenishqty=" + ea_minreplenishqty
				 + "&cs_lowerlimit=" + cs_lowerlimit + "&cs_uplimit=" + cs_uplimit + "&cs_minreplenishqty=" + cs_minreplenishqty 
				 + "&ot_lowerlimit=" + ot_lowerlimit + "&ot_uplimit=" + ot_uplimit + "&ot_minreplenishqty=" + ot_minreplenishqty;
													
		window.returnValue = condition;
		window.close();
	}
	
	//����ʽ�仯��ʱ��
	function selectUom()
	{
		var ruleconfigid =  document.getElementById("ruleconfigid").value;
		if(ruleconfigid != "" && ruleconfigid == "003003")  
       	{
       		document.getElementById("eauom").innerHTML= "��";
       	}else
       	{
       		document.getElementById("eauom").innerHTML= "��";
       	}
	}
	
	//ҳ���½
	function OnLoad(){
		selectObject("<%=warehouseid%>", "warehouseid", "1");
		selectObject("<%=ruleconfigid%>", "ruleconfigid", "33");
	}
-->
</script>
</head>

<body onLoad="OnLoad();">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ҵ����� -&gt; �������� -&gt; ���²�������</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>������</td>
     <td class="xx1" colspan="3"><input type="text" id="descr" size="30" maxlength="200" value="<%=descr%>"></td>
   </tr>
   <tr>
      <td class="yx1" align="right">�����ֿ⣺</td>
      <td class="yx"><select name="warehouseid"><option value=""></option></select></td>
      <td class="yx1" align="right"><span class="red">*</span>������ʽ��</td>
     <td class="xx1"><select id="ruleconfigid" onchange="selectUom();"><option value=""></option></select></td>
    </tr>
   <tr>
     <td class="y1" width="100px" align="right">��ע��</td>
     <td colspan="3"><input type="text" id="remark" size="50" maxlength="200" value="<%=remark%>"></td>
   </tr>
 </table>

 <table><tr><td height="10"></td></tr></table> 

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center">����</td>
     <td class="yx1" align="center">����</td>
     <td class="yx1" align="center">����</td>
     <td class="x1" align="center">��С��������</td>  
  </tr>
   <tr>
     <td class="yx" align="center">���������</td>
     <td class="yx" align="center"><input type="text" name="ea_lowerlimit" size="15" maxlength="10" value="<%=fEA_Lowerlimit%>">��</td>
     <td class="yx" align="center"><input type="text" name="ea_uplimit" size="15" maxlength="10" value="<%=fEA_Uplimit%>">��</td>
     <td class="xx1" align="center">
     	<input type="text" name="ea_minreplenishqty" size="15" maxlength="10" value="<%=fEA_Minreplenishqty%>"><label id="eauom"><%=uom%></label></td>
  </tr>
  <tr>
     <td class="yx" align="center">��������</td>
     <td class="yx" align="center"><input type="text" name="cs_lowerlimit" size="15" maxlength="10" value="<%=fCS_Lowerlimit%>">��</td>
     <td class="yx" align="center"><input type="text" name="cs_uplimit" size="15" maxlength="10" value="<%=fCS_Uplimit%>">��</td>
     <td class="xx1" align="center"><input type="text" name="cs_minreplenishqty" size="15" maxlength="10" value="<%=fCS_Minreplenishqty%>">��</td>
  </tr>
  <tr>
     <td class="x" align="center">��/���������</td>
     <td class="x" align="center"><input type="text" name="ot_lowerlimit" size="15" maxlength="10" value="<%=fOT_Lowerlimit%>">��</td>
     <td class="x" align="center"><input type="text" name="ot_uplimit" size="15" maxlength="10" value="<%=fOT_Uplimit%>">��</td>
     <td align="center"><input type="text" name="ot_minreplenishqty" size="15" maxlength="10" value="<%=fOT_Minreplenishqty%>">��</td>
  </tr>
 </table> 

 <table><tr><td height="10"></td></tr></table> 

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
         <input type="button" name="saveBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_add" onclick="saveData()">
         <input type="button" name="cancelBtn" value="�ر�" class="BUTTON_STYLE1" onClick="window.close()">
     </td>
   </tr>
 </table>

</body>
</html>