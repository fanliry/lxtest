<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>���ӿͻ���Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
		var shortname = document.getElementById("shortname").value;
		var customername = document.getElementById("customername").value;
		var customertype = document.getElementById("customertype").value;
		var contact = document.getElementById("contact").value;
		var phone = document.getElementById("phone").value;
		var fax = document.getElementById("fax").value;
		var address = document.getElementById("address").value;
		var bank = document.getElementById("bank").value;
	    var putawayid = document.getElementById("putawayid").value;			// �ϼܹ���ID
	    var allocationid = document.getElementById("allocationid").value;	// �������ID
	    var replenishid = document.getElementById("replenishid").value;		// ��������ID
		var linenumber = document.getElementById("linenumber").value;
	    var pakageid = document.getElementById("package_id").value;			// ��װID
		
		if(customername == null || customername.length <1)
		{
			alert("���ͻ�ȫ�ơ�����Ϊ��!");
			return;
		}
		if(phone!=null && phone.length>0)
		{
			if(strlen(phone) > 20){
				alert("����ϵ�绰������!");
				return;
			}
		}
		
		if(fax!=null && fax.length>0)
		{
			if(strlen(fax) > 20){
				alert("�����桿����!");
				return;
			}
		}
		
		if(bank!=null && bank.length>0)
		{
			if(strlen(bank) > 50){
				alert("���������С�����!");
				return;
			}
		}
		
		if(linenumber!=null && linenumber.length>0)
		{
			if(!numChar(linenumber)){
				alert("����·�š�����������!");
				return;
			}
		}

		var back_msg = "&shortname=" + shortname + "&customername=" + customername + "&customertype=" + customertype
			 + "&contact=" + contact + "&phone=" + phone + "&fax=" + fax + "&address=" + address + "&bank=" + bank 
			 + "&putawayid=" + putawayid + "&allocationid=" + allocationid + "&replenishid=" + replenishid
			 + "&linenumber=" + linenumber + "&pakageid=" + pakageid;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
			
		selectType("", "customertype", "khlx");		//�ͻ�����
		selectRules("", "putawayid", "", "1");		//�ϼܹ���
		selectRules("", "allocationid", "",  "2");	//�������
		selectRules("", "replenishid", "", "3");	//��������
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; �ͻ����� -&gt; �����ͻ���Ϣ</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">�ͻ���ƣ�</td>
      <td class="xx1" colspan="3"><input type="text" name="shortname" maxlength="25" size="25"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�ͻ�ȫ�ƣ�</td>
      <td class="xx1" colspan="3"><input type="text" name="customername" maxlength="50" size="50"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�ͻ����ࣺ</td>
      <td class="xx1" colspan="3"><select name="customertype"><option value=""></option></select></td>
    </tr> 
    <tr>
      <td width="100px" class="yx1" align="right">��ϵ�ˣ�</td>
      <td class="xx1"><input type="text" name="contact" maxlength="10" size="15"></td>
      <td width="100px" class="yx1" align="right">��ϵ�绰��</td>
      <td class="xx1"><input type="text" name="phone" maxlength="20" size="15"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">���棺</td>
      <td class="xx1" colspan="3"><input type="text" name="fax" maxlength="20" size="15"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�������У�</td>
      <td class="xx1" colspan="3"><input type="text" name="bank" maxlength="50" size="25"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��ַ��</td>
      <td class="xx1" colspan="3"><input type="text" name="address" maxlength="50" size="50"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�ϼܹ���</td>
      <td class="xx1"><select id="putawayid" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
      <td class="yx1" align="right">�������</td>
      <td class="xx1"><select id="allocationid" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��������</td>
      <td class="xx1"><select id="replenishid" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
      <td class="yx1" align="right">��·�ţ�</td>
      <td class="xx1"><input type="text" name="linenumber" maxlength="5" size="5"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��װ��</td>
      <td class="yx" colspan="3">
     	<input type="hidden" id="package_id"><input type="text" id="package_name" size="24" readonly>
       	<input onClick="openPackage('<%=request.getContextPath()%>/standard/jsp/common/common_package.jsp','850','550');" type="button" value="��" class="button_select">
      </td>
      
    </tr>
    <tr>
      <td height="27" colspan="4" align="center">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>