<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//���ⵥ��ID
	String invoiceid = request.getParameter("invoiceid");
	//�ͻ�ID
	String customerid = request.getParameter("customerid");
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

 <script>
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
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
	/*������ť*/
	function LockButton(){
		
		document.getElementById("button_add").disabled = true;
		document.getElementById("button_edit").disabled = true;
		document.getElementById("button_delete").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("button_add").disabled = false;
		document.getElementById("button_edit").disabled = false;
		document.getElementById("button_delete").disabled = false;
	}
 	//������ⵥ��ϸ����
	function saveData()
	{
		//���ⵥ��ID
		var invoiceid = document.getElementById("invoiceid").value;
		//Ʒ��ID
		var productid = document.getElementById("productid").value;
		//��Ʒ����
		var productCode = document.getElementById("productCode").value;
		//��λ
		var eunit = document.getElementById("eunit").value;
		//��������
		var invoicenum = document.getElementById("invoicenum").value;
		//���
		var spec = document.getElementById("spec").value;
		
		//�߼�����
		var Virtualwhid = document.getElementById("Virtualwhid").value;
		//��������
		var indate = document.getElementById("indate").value;
		//����
		var lotinfo = document.getElementById("lotinfo").value;
		
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ⵥ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(productCode == null || productCode.length < 1){
			alert("����Ʒ���롿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(eunit == null || eunit.length < 1){
			alert("����λ������Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("������������Ϊ����ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}
		
		if(spec == null || spec.length < 1 ){
			alert("����񡿲���Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(Virtualwhid == null || Virtualwhid.length < 1 ){
			alert("���߼�����������Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(lotinfo == null || lotinfo.length < 1 ){
			alert("�����š�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		var condition = "&invoiceid="+ invoiceid + "&productid=" + productid +"&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&spec=" + spec +"&productcode=" + productCode+"&Virtualwhid=" + Virtualwhid+"&printdate=" + indate+"&lotinfo=" + lotinfo; 
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=addDetailRicoExec";
			window.returnValue = list.formx1.action = strUrl + condition;
			list.document.formx1.submit();
			//form1.reset();//����		
		}else{
			UnLockButton();
		}
	}
	
	//�޸ĳ��ⵥ��ϸ����
	function updateData()
	{
		//���ⵥ����ϸID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		//Ʒ��ID
		var productid = document.getElementById("productid").value;
		//��λ
		var eunit = document.getElementById("eunit").value;
		//����
		var invoicenum = document.getElementById("invoicenum").value;
		//���
		var spec = document.getElementById("spec").value;
		
		//����
		//var netweight = document.getElementById("netweight").value;
		//���
		//var volume = document.getElementById("volume").value;
		//����
		//var lotinfo = document.getElementById("lotinfo").value;
		//��Ʒ����
		var productCode = document.getElementById("productCode").value;
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("�����ⵥ��ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(productCode == null || productCode.length < 1){
			alert("����Ʒ���롿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(spec == null && spec.length < 1 ){
			alert("����񡿲���Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(eunit == null || eunit.length < 1){
			alert("����λ������Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("������������Ϊ����ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}
		
		
		
		var condition = "&detailid="+ invoicedetailid + "&productid=" + productid + "&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&spec=" + spec +"&productcode=" + productCode;
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=updateDetailRicoExec"; 
			list.formx1.action = strUrl + condition;
			list.document.formx1.submit(); 
			form1.reset();//����
		}
	}
	//��������
	function lotreset()
	{
		document.getElementById("lotatt1").value = ""; 	//��������1
		document.getElementById("lotatt2").value = ""; 	//��������2
		document.getElementById("lotatt3").value = ""; 	//��������3
		document.getElementById("lotatt4").value = ""; 	//��������4
		document.getElementById("lotatt5").value = ""; 	//��������5
		document.getElementById("lotatt6").value = ""; 	//��������6
		document.getElementById("lotatt7").value = ""; 	//��������7
		document.getElementById("lotatt8").value = ""; 	//��������8
		document.getElementById("lotatt9").value = ""; 	//��������9
		document.getElementById("lotatt10").value = ""; //��������10
		document.getElementById("lotatt11").value = ""; //��������11
		document.getElementById("lotatt12").value = ""; //��������12
	}
	
	//ɾ��������ϸ
	function deleteData()
	{
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("��ѡ��Ҫ���ϵļ�¼��");
			return;
		}
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ݡ�����Ϊ�գ�");
			return;
		}
		//************************************************
		if(confirm("��ȷ��ɾ����ѡ��¼��"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=deleteRicoExec&flag=2";
			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&detailids=" + ids;
			
			form1.reset();//����
				
		}
	}
	function Select()
	{
		var result;
		result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp",'','dialogWidth=800px;dialogHeight=520px');
		
	    if(result != null && result.length > 0)
        {
        	result = result.split(",");
        	for(var i = 0; i < result.length; i++)
        	{
        	    
        		var cs = result[i].split("|");
			    	              //��Ʒid        ��Ʒ��           ��λ     
			    var checkvaleid = cs[0] + "," + cs[1] + "," + cs[6];	
			    document.getElementById("product_name").value=cs[1];
			    document.getElementById("productid").value =cs[0];
			    document.getElementById("productCode").value =cs[7];
			    document.getElementById("spec").value = cs[8];
			    
			    selectType(cs[6], "eunit", "punit");
        	}
        }
	}
	//ҳ���¼
	function OnLoad(){
		selectAreaList("", "Virtualwhid", "", "3");
	}
 </script>
</head>
  <body onLoad="OnLoad()">
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; �½����ⵥ -&gt; ��ϸ</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="product_name" class="input_readonly"    style="height:18px;width:100px;"/>
          	 <input type="hidden" name="productid" value=""  style="height:18px;width:100px;"/>
          	 <input name="moreBtn3" type="button" class="button_select" value="��" onclick="Select()" />
          </td>
          <!-- <td width="15%" align="right" class="yx1"><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;װ��</td>
          <td width="20%" class="yx">
              <input type="hidden" name="packid"    style="height:18px;width:100px;"/>
	          <input type="text" name="packname"  class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td> -->
          <td align="right" class="yx1">��Ʒ���룺</td>
          <td class="yx">
          		<input type="text" name="productCode" class="input_readonly" size="16" style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>���ⵥ�ţ�</td>
          <td width="19%" class="xx1">
	          <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
          <td class="yx">
          		<select name="eunit" id="eunit" style="width:100px;" disabled>
	            <option>--��ѡ��--</option>
	          </select>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>����������</td>
          <td class="yx">
          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
          <td class="xx1" >
          	<input type="text" name="spec" size="16" class="input_readonly" style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td class="yx1" align="right"><span class="red">*</span>�߼�������</td>
     	  <td class="yx">
     	   <select id="Virtualwhid" style="width:117px;"><option value=""></option></select>
     	  </td>
          <td align="right" class="yx1">������ڣ�</td>
          <td class="yx">
          	<input id="indate" type="text" onfocus="calendar();" class="Wdate" style="width:100px;">
          </td>
          <td align="right" class="yx1"><span class="red">*</span>������ţ�</td>
          <td class="xx1">
          	<input type="text" name="lotinfo" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
		<tr>
        	 <TD colspan="6" height="10">
        		<input type="hidden" name="invoicedetailid" />
        	</TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input style="width: 80px;" name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;������ϸ" onClick="saveData();" />
            <!--<input style="width: 80px;" name="button_edit" type="button" class="button_edit" id="button_edit" value="&nbsp;&nbsp;�޸���ϸ" onclick="updateData();" />  -->
            <input style="width: 80px;" name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;ɾ����ϸ" onclick="deleteData();" />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== ��ϸ�б�ʼ ======== -->
 <table width="98%" height="420" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=4&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ϸ�б���� ======== -->

  </body>
</html>
