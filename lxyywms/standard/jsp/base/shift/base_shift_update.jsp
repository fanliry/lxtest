<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseShift" %>
<%
	BaseShift shift = (BaseShift)request.getAttribute("shift");  
	String shiftid = shift.getM_strShiftId();			//���ID
	String shiftname = shift.getM_strShiftName();	//�������
	String inouttype = shift.getM_strIn_Out_Type();	//���������
	String inoutname = "";
	if(inouttype.equals("1")){
		inoutname = "���";
	}else if(inouttype.equals("2")){
		inoutname = "����";
	}
	String opman = shift.getM_strOp_Man_Id();		//������
	String onduty = shift.getM_strOndutyMen();		//������Ա
	
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script>
<!--
	//��ȡ�û���
	function GetUser()
	{
		var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_user.jsp','','dialogWidth=700px;dialogHeight=440px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		document.getElementById("op_man_id").value = tem[0];
	 		document.getElementById("op_man_name").value = tem[2];
	 		}
	}
	
	//��ȡ����û���
	function GetUsers(){
	     var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_users.jsp','','dialogWidth=700px;dialogHeight=440px');
	     var str = "";
	     if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		if(tem != null){
	 		 for(var i=0;i<tem.length;i++){
	 		   str += tem[i]+",";
	 		 }
	 		}
	 	}
	 	 if(str != null){
		   str = str.substring(0, str.length-1);
		 }
	 	 if(document.getElementById("onduty").value == ""){
	 	 	document.getElementById("onduty").value += str;
	 	 }else{
	 	 	document.getElementById("onduty").value += "," + str;
	 	 }
	}
	
	//���� 
	function Save()
	{
		var op_man_name = document.getElementById("op_man_name").value;
		var onduty = document.getElementById("onduty").value;
		if(op_man_name == "" || op_man_name.length < 1)
		{
			alert("�������ˡ�����Ϊ�գ�");
			return;
		}
		if(onduty == "" || onduty.length < 1)
		{
			alert("��������Ա������Ϊ�գ�");
			return;
		}
		var backmsg = "&op_man_name=" + op_man_name + "&onduty=" + onduty + "&id=<%=shiftid%>";
		
		window.returnValue = backmsg;
		window.close();
	}
	
	//��ʼ�����б�
	function onLoad()
	{
		
	}
-->
</script>
</head>

<body onload="onLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��ι��� -&gt; �޸İ����Ϣ</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" align="right" width="100px">�������</td>
      <td class="xx1">
     	<input name="shiftname" type="text" size="20" value="<%=shiftname%>" readonly class="input_readonly"></td>
    </tr> 
    <%if(inouttype.equals("1") || inouttype.equals("2")) {%>
    <tr>
      <td class="yx1" align="right">������ͣ�</td>
      <td class="xx1">
     	<input name="inouttype" type="text" size="20" value="<%=inoutname%>" readonly class="input_readonly"></td>
    </tr> 
    <%}%>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�����ˣ�</td>
      <td class="xx1">
        <input name="op_man_id" type="hidden"><input name="op_man_name" type="text" size="16" readonly value="<%=opman%>">
        <input name="moreBtn" type="button" class="button_select" value="��" onClick="GetUser()">
      </td>
    </tr>
    <tr>
	  <td class="yx1" align="right"><span class="red">*</span>������Ա�� </td>
	  <td class="xx1" align="left"><textarea rows="2" style="width:300px;height:40px" id="onduty"><%=onduty%></textarea> 
	    <input name="moreBtn" type="button" class="button_select" value="��" onClick="GetUsers()"></td>   
	</tr>	
    <tr>
      <td height="27" colspan="4" align="center">
        <input onClick="Save()"type="button" name="button1" value="����" class="BUTTON_STYLE1">
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>