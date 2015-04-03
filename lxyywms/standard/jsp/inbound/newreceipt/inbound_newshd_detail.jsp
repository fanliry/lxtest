
<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//�ջ�����ID
	String invoiceid = request.getParameter("invoiceid");
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
 	//��������
	function saveData()
	{
		LockButton();
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		//Ʒ��ID
		var productid = document.getElementById("productid").value;
		//��װ
		var packageid = document.getElementById("packid").value;
		//��λ
		var eunit = document.getElementById("eunit").value;
		//��������
		var invoicenum = document.getElementById("invoicenum").value;
		//ë��
		var weight = document.getElementById("weight").value;
		//����
		var netweight = document.getElementById("netweight").value;
		//���
		var volume = document.getElementById("volume").value;
		//����
		var price = document.getElementById("price").value;
		//�ջ�����
		var reclocation  = document.getElementById("reclocation").value;
		//��Ʒ״̬
		var skustatus  = document.getElementById("skustatus").value;
		//״̬����
		var skustatus_descr  = document.getElementById("skustatus_descr").value;
		//��������ID
		var lotid  = document.getElementById("lotid").value;
		
		var lotatt1  = document.getElementById("lotatt1").value; 	//��������1
		var lotatt2  = document.getElementById("lotatt2").value; 	//��������2
		var lotatt3  = document.getElementById("lotatt3").value; 	//��������3
		var lotatt4  = document.getElementById("lotatt4").value; 	//��������4
		var lotatt5  = document.getElementById("lotatt5").value; 	//��������5
		var lotatt6  = document.getElementById("lotatt6").value; 	//��������6
		var lotatt7  = document.getElementById("lotatt7").value; 	//��������7
		var lotatt8  = document.getElementById("lotatt8").value; 	//��������8
		var lotatt9  = document.getElementById("lotatt9").value; 	//��������9
		var lotatt10  = document.getElementById("lotatt10").value; 	//��������10
		var lotatt11  = document.getElementById("lotatt11").value; 	//��������11
		var lotatt12  = document.getElementById("lotatt12").value; 	//��������12		
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ݡ�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(packageid == null || packageid.length < 1){
			alert("����װ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(eunit == null || eunit.length < 1){
			alert("����λ������Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("����������������Ϊ����ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
			alert("��ë�ء�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(weight == null || weight.length < 1){
			weight = "0";
		}
		
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
			alert("�����ء�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(netweight == null || netweight.length < 1){
			netweight = "0";
		}	
		
		
		if(volume != null && volume.length > 0 && !IsFloat(volume)){
			alert("�������ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(volume == null || volume.length < 1)
		{
			volume = "0";
		} 		
		

		if(price != null && price.length > 0 && !IsFloat(price)){
			alert("���۸�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(price == null || price.length < 1)
		{
			price = "0";
		}
		

		
		//��������ֵ��֤
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}
		
		var condition = "&invoiceid="+ invoiceid + "&productid=" + productid + "&packid=" + packageid + "&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&weight=" + weight + "&netweight=" + netweight + "&volume=" + volume + "&price=" + price + "&reclocation=" + reclocation +
					"&skustatus=" + skustatus + "&skustatus_descr=" + skustatus_descr + "&lotid=" + lotid;// + 
					//"&lotatt1=" + lotatt1 + "&lotatt2=" + lotatt2 + "&lotatt3=" + lotatt3 + "&lotatt4=" + lotatt4 + 
					//"&lotatt5=" + lotatt5 + "&lotatt6=" + lotatt6 + "&lotatt7=" + lotatt7 + "&lotatt8=" + lotatt8 +
					//"&lotatt9=" + lotatt9 + "&lotatt10=" + lotatt10 + "&lotatt11=" + lotatt11 + "&lotatt12=" + lotatt12;
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=addDetailRicoExec";
			//������
			var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
			list.formx1.innerHTML = msg;
			list.formx1.action = strUrl + condition;
			list.document.formx1.submit();
			
			form1.reset();//����		
			lotreset();	  //����
		}else{
			UnLockButton();
		}
	}
	
	//�޸�����
	function updateData()
	{
		LockButton();
		//������ϸID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		//Ʒ��ID
		var productid = document.getElementById("productid").value;
		//��װ
		var packageid = document.getElementById("packid").value;
		//��λ
		var eunit = document.getElementById("eunit").value;
		//��������
		var invoicenum = document.getElementById("invoicenum").value;
		//ë��
		var weight = document.getElementById("weight").value;
		//����
		var netweight = document.getElementById("netweight").value;
		//���
		var volume = document.getElementById("volume").value;
		//����
		var price = document.getElementById("price").value;
		
		//�ջ�����
		var reclocation  = document.getElementById("reclocation").value;
		//��Ʒ״̬
		var skustatus  = document.getElementById("skustatus").value;
		//״̬����
		var skustatus_descr  = document.getElementById("skustatus_descr").value;
		//��������ID
		var lotid  = document.getElementById("lotid").value;
		
		var lotatt1  = document.getElementById("lotatt1").value; 	//��������1
		var lotatt2  = document.getElementById("lotatt2").value; 	//��������2
		var lotatt3  = document.getElementById("lotatt3").value; 	//��������3
		var lotatt4  = document.getElementById("lotatt4").value; 	//��������4
		var lotatt5  = document.getElementById("lotatt5").value; 	//��������5
		var lotatt6  = document.getElementById("lotatt6").value; 	//��������6
		var lotatt7  = document.getElementById("lotatt7").value; 	//��������7
		var lotatt8  = document.getElementById("lotatt8").value; 	//��������8
		var lotatt9  = document.getElementById("lotatt9").value; 	//��������9
		var lotatt10  = document.getElementById("lotatt10").value; 	//��������10
		var lotatt11  = document.getElementById("lotatt11").value; 	//��������11
		var lotatt12  = document.getElementById("lotatt12").value; 	//��������12
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("��������ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(productid == null || productid.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(packageid == null || packageid.length < 1){
			alert("����װ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(eunit == null || eunit.length < 1){
			alert("����λ������Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("����������������Ϊ����ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
			alert("��ë�ء�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(weight == null || weight.length < 1){
			weight = "0";
		}
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
			alert("�����ء�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(netweight == null || netweight.length < 1){
			netweight = "0";
		}
		if(volume != null && volume.length > 0 && !IsFloat(volume)){
			alert("�������ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(volume == null || volume.length < 1){
			volume = "0";
		}
		if(price != null && price.length > 0 && !IsFloat(price)){
			alert("���۸�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(price == null || price.length < 1){
			price = "0";
		}
		
		//��������ֵ��֤
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}

		var condition = "&detailid="+ invoicedetailid + "&productid=" + productid + "&packid=" + packageid + "&eunit=" + eunit + "&invoicenum="+invoicenum + 
					"&weight=" + weight + "&netweight=" + netweight + "&volume=" + volume + "&price=" + price + "&reclocation=" + reclocation +
					"&skustatus=" + skustatus + "&skustatus_descr=" + skustatus_descr + "&lotid=" + lotid ;//+ 
					//"&lotatt1=" + lotatt1 + "&lotatt2=" + lotatt2 + "&lotatt3=" + lotatt3 + "&lotatt4=" + lotatt4 + 
					//"&lotatt5=" + lotatt5 + "&lotatt6=" + lotatt6 + "&lotatt7=" + lotatt7 + "&lotatt8=" + lotatt8 +
					//"&lotatt9=" + lotatt9 + "&lotatt10=" + lotatt10 + "&lotatt11=" + lotatt11 + "&lotatt12=" + lotatt12;//encodeURIComponent()
		
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=updateDetailRicoExec"; 
			
			//������
			var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
			list.formx1.innerHTML = msg;
			
			list.formx1.action = strUrl + condition;
			list.document.formx1.submit(); 
			form1.reset();//����
			lotreset();	  //����
				
		}else{
			UnLockButton();
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
		LockButton();
		
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
			UnLockButton();
			return;
		}
		
		//************************************************
		if(confirm("��ȷ��ɾ����ѡ��¼��"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=deleteRicoExec&flag=2";
			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&detailids=" + ids;
			
			form1.reset();//����
			lotreset();	  //����
				
		}else{
			UnLockButton();
		}
	}
	
	function openProduct(url, width, height, productid, product_name) 
	{
		var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
		if(result != null && result.length > 1)
		{
			var tem = result.split("|");
			document.getElementById("productid").value = tem[0];
			document.getElementById("product_name").value = tem[1];
			
			document.getElementById("packid").value = tem[2];
			
			//��ʾ��λ
			
			
		}else
		{
			document.getElementById("package_id").value = "";
			document.getElementById("package_name").value = "";
		}	
	}
 
 
  	function OnLoad()
	{

		selectType('', 'skustatus', 'skustatus');//��Ʒ״̬
	}
 </script>
</head>
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �½��ջ��� -&gt; ��ϸ</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="product_name" class="input_readonly"    style="height:18px;width:100px;"/>
          	 <input type="hidden" name="productid" value=""  style="height:18px;width:100px;"/>
          	 <input name="moreBtn3" type="button" class="button_select" value="��" onclick="openProductPacke('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520','productid','product_name', 'packid', 'packname', '1','eunit', 'lotid');" />
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;װ��</td>
          <td width="20%" class="yx">
              <input type="hidden" name="packid"    style="height:18px;width:100px;"/>
	          <input type="text" name="packname"  class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>��&nbsp;��&nbsp;�ţ�</td>
          <td width="19%" class="xx1">
	          <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
          <td class="yx">
          		<select name="eunit" id="eunit" style="width:100px;" >
	            <option>--��ѡ��--</option>
	          </select>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>����������</td>
          <td class="yx">
          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">ë&nbsp;&nbsp;&nbsp;&nbsp;�أ�</td>
          <td class="xx1">
          	<input type="text" name="weight" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;�أ�</td>
          <td class="yx">
          	<input type="text" name="netweight" size="16"   style="height:18px;width:100px;"/>
            
          </td>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td class="yx">
          	<input type="text" name="volume" size="16"    style="height:18px;width:100px;"/>  
           </td>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;�ۣ�</td>
          <td class="xx1" >
          	<input type="text" name="price" size="16"    style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">�ջ�������</td>
          <td class="yx"><input type="text" name="reclocation"   style="height:18px;width:100px;"/></td>
          <td align="right" class="yx1">��Ʒ״̬��</td>
          <td class="yx">
          		<select name="skustatus" style="width:100px;" >
	            <option >--��ѡ��--</option>
	          </select>
          </td>
          <td align="right" class="yx1">״̬������</td>
          <td class="xx1" ><input type="text" name="skustatus_descr"   style="height:18px;width:100px;"/></td>
        </tr>
        
        <tr>
     		<td height="5" colspan="6"><input type="hidden" name="lotid"/></td>
   		</tr>
        
        <tr>
          <td align="right" class="yx1_top"><div id="lotatt001" align="right">��������1��</div></td>
          <td class="yx_top"><div id="lotvalue001" align="left">
          	<input type="text" name="lotatt1" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1_top"><div id="lotatt002" align="right">��������2��</div></td>
          <td class="yx_top"><div id="lotvalue002" align="left">
          	<input type="text" name="lotatt2" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
          <td align="right" class="yx1_top"><div id="lotatt003" align="right">��������3��</div></td>
          <td class="xx1_top" ><div id="lotvalue003" align="left">
          	<input type="text" name="lotatt3" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="yx1"><div id="lotatt004" align="right">��������4��</div></td>
          <td class="yx"><div id="lotvalue004" align="left">
          	<input type="text" name="lotatt4" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1"><div id="lotatt005" align="right">��������5��</div></td>
          <td class="yx"><div id="lotvalue005" align="left">
          	<input type="text" name="lotatt5" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
          <td align="right" class="yx1"><div id="lotatt006" align="right">��������6��</div></td>
          <td class="xx1" ><div id="lotvalue006" align="left">
          	<input type="text" name="lotatt6" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="yx1"><div id="lotatt007" align="right">��������7��</div></td>
          <td class="yx"><div id="lotvalue007" align="left">
          	<input type="text" name="lotatt7" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1"><div id="lotatt008" align="right">��������8��</div></td>
          <td class="yx"><div id="lotvalue008" align="left">
          	<input type="text" name="lotatt8" size="16"    style="height:18px;width:100px;"/></div> 
           </td>
          <td align="right" class="yx1"><div id="lotatt009" align="right">��������9��</div></td>
          <td class="xx1" ><div id="lotvalue009" align="left">
          	<input type="text" name="lotatt9" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="yx1"><div id="lotatt010" align="right">��������10��</div></td>
          <td class="yx"><div id="lotvalue010" align="left">
          	<input type="text" name="lotatt10" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
          <td align="right" class="yx1"><div id="lotatt011" align="right">��������11��</div></td>
          <td class="yx"><div id="lotvalue011" align="left">
          	<input type="text" name="lotatt11" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
          <td align="right" class="yx1"><div id="lotatt012" align="right">��������12��</div></td>
          <td class="xx1" ><div id="lotvalue012" align="left">
          	<input type="text" name="lotatt12" size="16" style="height:18px;width:100px;"/></div>
          </td>
        </tr>
 
		<tr>
        	<TD colspan="6" height="10">
        		<input type="hidden" name="invoicedetailid" />
        	</TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;������ϸ" onClick="saveData();" />
            <input name="button_edit" type="button" class="button_edit" id="button_edit" value="&nbsp;&nbsp;�޸���ϸ" onclick="updateData();" />
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;ɾ����ϸ" onclick="deleteData();" />
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
 <table width="98%" height="270" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&flag=6&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ϸ�б���� ======== -->

  </body>
</html>
