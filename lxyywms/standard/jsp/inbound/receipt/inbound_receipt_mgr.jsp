
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//����ID
	String invoiceid = request.getParameter("invoiceid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("button_add").disabled = false;
	}
 	//��������
	function saveData()
	{
		LockButton();
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		//������ϸID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		
		
		//�ջ�����
		var invoicenum = document.getElementById("renum").value;
		//ʣ���ջ�����
		var reinvoicenum = document.getElementById("realnum").value;	
		//�ջ�ë��
		var weight = document.getElementById("reweight").value;
		//ʣ���ջ�ë��
		var reweight = document.getElementById("realweight").value;
		
		//�ջ�����
		var netweight = document.getElementById("renetweight").value;
		//ʣ���ջ�����
		var renetweight = document.getElementById("realnetweight").value;
		//�ջ����
		var volume = document.getElementById("revolume").value;
		//ʣ���ջ����
		var revolume = document.getElementById("realvolume").value;
		
		//��������
		var rejectednum = document.getElementById("rejectednum").value;
		//��������
		var holdnum = document.getElementById("holdnum").value;
		//���մ���
		var rejectcode = document.getElementById("rejectcode").value;
		//����ԭ��
		var rejectreason = document.getElementById("rejectreason").value;
		//�������
		var holdcode = document.getElementById("holdcode").value;
		//����ԭ��
		var holdreason = document.getElementById("holdreason").value;
		
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
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("��������ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("���ջ�����������Ϊ����ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}
		if(parseFloat(invoicenum) > parseFloat(reinvoicenum)){
			alert("���ջ�����(" + invoicenum + ")�����ܴ��ڵ��ݡ�ʣ���ջ�����(" + reinvoicenum + ")����");
			UnLockButton();
			return;
		}
		if(rejectednum != null && rejectednum.length > 0 && !IsFloat(rejectednum)){
			alert("������������ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(rejectednum == null || rejectednum.length < 1)
		{
			rejectednum = "0";
		}
		if(holdnum != null && holdnum.length > 0 && !IsFloat(holdnum)){
			alert("������������ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(holdnum == null || holdnum.length < 1)
		{
			holdnum = "0";
		}
		
		if((parseFloat(invoicenum) + parseFloat(rejectednum) + parseFloat(holdnum) )  == 0){ //���ջ�����Ϊ0ʱ����ʾҪ�����������������
			alert("���ջ�����(" + invoicenum + ")��+ ����������(" + rejectednum + ")�� + ����������(" + holdnum + ")��ֻ�ܴ��ڡ�0����");
			UnLockButton();
			return;
		}
		
		if((parseFloat(invoicenum) + parseFloat(rejectednum) + parseFloat(holdnum) )  > parseFloat(reinvoicenum)){ //���ջ�����Ϊ0ʱ����ʾҪ�����������������
			alert("���ջ�����(" + invoicenum + ")��+ ����������(" + rejectednum + ")�� + ����������(" + holdnum + ")�����ܴ��ڡ�ʣ���ջ�����(" + reinvoicenum + ")����");
			UnLockButton();
			return;
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
			alert("��ë�ء�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(weight == null || weight.length < 1)
		{
			weight = "0";
		}
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
			alert("�����ء�ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}else if(netweight == null || netweight.length < 1)
		{
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
		//��������ֵ��֤
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}
		
		
		
		var details = "&invoicedetailid=" + invoicedetailid + "&num=" + invoicenum + "&weight=" + weight + "&netweight=" + netweight + "&volume=" + volume+
					 "&rejectednum=" + rejectednum + "&rejectcode=" + rejectcode + "&rejectreason=" + rejectreason + 
					 "&holdnum=" + holdnum + "&holdcode=" + holdcode + "&holdreason=" + holdreason;	

		//************************************************
		if(confirm("����ջ�����Ϊ"+invoicenum+",��ȷ���Ƿ��ջ���"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction";
			//myIframe.location.href = strUrl + "&flag=2&invoiceid="+ invoiceid + "&tspace=" + reclocation + detail;
			
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
			myIframe.formx1.innerHTML = msg;
			myIframe.formx1.action = strUrl + "&method=receipt&invoiceid="+ invoiceid + "&lotid=" + lotid + details;
			myIframe.document.formx1.submit();
			
			document.form1.reset();//����		
			lotreset();	  //����	
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_mgr_task.jsp";
			
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
	
	function OnLoad()
	{
		selectType('', 'rejectcode', 'rejectcode');
		selectType('', 'holdcode', 'holdcode');
	}
</script>
  </head>
  
  <body  onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �ջ� -&gt; ����ϸ�ջ�</div></td>
   </tr>
  </table>
	
 <!-- ======== ������ϸ�б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="185">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=1&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ������ϸ�б���� ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== �ջ���ϸ�б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top"  height="185" > <!-- ��Ҫ�к��ŵĹ��������߶���Ϊ185���� û����165 -->
					<iframe id="detail" src="<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_mgr_task.jsp"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!-- ======== �ջ���ϸ�б���� ======== -->
 
<table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        
        <tr>
          <td width="13%" align="right" class="yx1"  >Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="product_name" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;װ��</td>
          <td width="20%" class="yx">
	          <input type="text" name="packname" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
          <td width="19%" class="xx1">
	          <select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--��ѡ��--</option>
	        </select>
          </td>
        </tr>
        <tr>
          <td width="13%" align="right" class="yx1"  >����������</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="invoicenum" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">���ջ�������</td>
          <td width="20%" class="yx">
	          <input type="text" name="recnum" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>���ջ�������</td>
          <td width="19%" class="xx1">
	          <input type="text" name="renum"   style="height:18px;width:100px;"/>
	          <input type="hidden" name="realnum" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>���ջ����أ�</td>
          <td class="yx">
          	<input type="text" name="renetweight" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="realnetweight" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>���ջ�ë�أ�</td>
          <td class="yx">
          	<input type="text" name="reweight" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="realweight" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>���ջ������</td>
          <td class="xx1">
          	<input type="text" name="revolume" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="realvolume" size="16"   style="height:18px;width:100px;"/>
          </td>
        </tr>
        
        <tr>
          <td  align="right" class="yx1">����������</td>
          <td  class="yx" >
          	 <input type="text" name="rejectednum"    style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">����ԭ����룺</td>
          <td  class="yx">
	          <select name="rejectcode" id="rejectcode" style="width:100px;" >
	            <option>--��ѡ��--</option>
	        </select>
          </td>
          <td  align="right" class="yx1">����ԭ��������</td>
          <td  class="xx1">
	          <input type="text" name="rejectreason" value=""   style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td  align="right" class="yx1"  >����������</td>
          <td  class="yx" >
          	 <input type="text" name="holdnum"    style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">����ԭ����룺</td>
          <td  class="yx">
	          <select name="holdcode" id="holdcode" style="width:100px;" >
	            <option>--��ѡ��--</option>
	        </select>
          </td>
          <td  align="right" class="yx1">����ԭ��������</td>
          <td  class="xx1">
	          <input type="text" name="holdreason" value=""   style="height:18px;width:100px;"/>
          </td>
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
        		<input type="hidden" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
        	</TD>
        </tr>
        
        
        
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;�ջ�" onClick="saveData();"  />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>

  </body>
</html>
