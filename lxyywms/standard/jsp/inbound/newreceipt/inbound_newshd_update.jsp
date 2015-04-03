<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	InboundReceiptHeader inBound = null; 
	if(request.getAttribute("invoice") != null){
		inBound = (InboundReceiptHeader)request.getAttribute("invoice"); 
	}
		String instockid = "";      //��ⵥ�ݱ��
        String intype = "";         //�������
		String warehouseid = "";    //�ֿ���
		
		 String	ownerid  = "";    /*����*/
	     String arrivestarttime = ""; /*Ԥ�ڵ���ʱ��(from)*/
	     String arriveendtime = "";   /*Ԥ�ڵ���ʱ��(to)*/
	     String shipperid = "";   /*��������Ϣ*/
	     String supplierid  = ""; /*��Ӧ����Ϣ*/
	     String receiveloc = "";  /*�ջ���̨*/
	     String customsno  = "";  /*���ر�����*/
	     String reserve1 = "";    /*Ԥ���ֶ�1*/
	     String reserve2 = "";    /*Ԥ���ֶ�2*/
	     String remark = "";      /*��ע*/
	     String ownername = "";       //����
	     String shippername = "";     //��������Ϣ
	     String suppliername  = "";   //��Ӧ����Ϣ
		
		if(inBound != null)
		{
			instockid = inBound.getReinvoiceid();     //��ⵥ�ݱ��
	        intype = inBound.getIntype();         	//�������
	        warehouseid = inBound.getWarehouseid(); //�ֿ���
		 	
		 	  ownerid  = inBound.getOwnerid();    /*����*/
		      arrivestarttime = inBound.getArrivestarttime(); /*Ԥ�ڵ���ʱ��(from)*/
		      arriveendtime = inBound.getArriveendtime();   /*Ԥ�ڵ���ʱ��(to)*/
		      shipperid = inBound.getShipperid();   /*��������Ϣ*/
		      supplierid  = inBound.getSupplierid(); /*��Ӧ����Ϣ*/
		      receiveloc = inBound.getReceiveloc();  /*�ջ���̨*/
		      customsno  = inBound.getCustomsno();  /*���ر�����*/
		      reserve1 = inBound.getReserve1();    /*Ԥ���ֶ�1*/
		      reserve2 = inBound.getReserve2();    /*Ԥ���ֶ�2*/
		      remark = inBound.getRemark();      /*��ע*/
		      ownername  = inBound.getOwnername();       //����
		      shippername = inBound.getShippername();     //��������Ϣ
		      suppliername = inBound.getSuppliername();   //��Ӧ����Ϣ
	    }
	
%>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
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
		
		document.getElementById("add").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}	
	//��������
	function saveData()
	{
		LockButton();
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//����
		var intype = document.getElementById("intype").value;
		//����
		var customer_id = document.getElementById("customer_id").value;
		
		//Ԥ�ڵ���ʱ��
		var arrivestarttime = document.getElementById("arrivestarttime").value;
		//Ԥ�ڵ���ʱ��
		var arriveendtime = document.getElementById("arriveendtime").value;
		//������
		var shipperid = document.getElementById("shipperid").value;
		
		
		//��Ӧ��
		var supplierid = document.getElementById("supplierid").value;
		//�ջ���̨
		var receiveloc = document.getElementById("receiveloc").value;
		//���ر�����
		var customsno = document.getElementById("customsno").value;
		//�û��Զ���1
		var reserve1  = document.getElementById("reserve1").value;
		//�û��Զ���2
		var reserve2  = document.getElementById("reserve2").value;
		//��ע
		var remark  = document.getElementById("remark").value;
		
		if(invoiceid == null || invoiceid.length < 1 ){
			alert("���ջ���������Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(warehouseid == null || warehouseid.length < 1){
			alert("���ֿ⡿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(intype == null || intype.length < 1){
			alert("��������͡�����Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(customer_id == null || customer_id.length < 1 ){
			alert("������������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		var condition = "&invoiceid=" + invoiceid + "&intype=" + intype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + "&arrivestarttime=" + arrivestarttime + "&arriveendtime="+arriveendtime + 
					"&shipperid=" + shipperid + "&supplierid=" + supplierid + "&receiveloc=" + receiveloc + "&customsno=" + customsno + "&reserve1=" + reserve1+ "&reserve2=" + reserve2+ "&remark=" + remark;
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=updateRicoExec";
			window.returnValue = strUrl + condition;
    		window.close();
				
		}else{
			UnLockButton();
		}
	}
		
		
		
		
		function OnLoad()
		{
			selectObject('<%=warehouseid%>', 'warehouseid', '1');
			selectType('<%=intype%>', 'intype', 'rkdj');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �½��ջ��� -&gt; �޸�</div></td>
   </tr>
  </table>
	<FORM>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"><span class="red">*</span>��&nbsp;��&nbsp;�ţ�</td>
          <td width="20%" class="yx">
          		<input type="text" name="invoiceid"  value="<%=instockid%>" readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
          <td width="20%" class="yx" >
          	<select name="warehouseid" id="warehouseid" style="width:100px;">
            	<option>--��ѡ��--</option>
            </select>
          
          </td>
          <td width="13%" align="right" class="yx1"><span class="red">*</span>�������ͣ�</td>
          <td width="21%" class="yx"><select name="intype" id="intype"  style="width:100px;">
            <option>---��ѡ��---</option>
          </select></td>

        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td class="yx">
          	<input type="text" name="customer_name" value="<%=ownername == null ? "" : ownername%>"  readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="customer_id" value="<%=ownerid == null ? "" : ownerid%>"/>
            <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></td>
          <td align="right" class="yx1">Ԥ�ڵ���ʱ��ӣ�</td>
          <td class="yx">
          		
          		<input name="arrivestarttime" type="text" value="<%=arrivestarttime == null ? "" : arrivestarttime%>"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">Ԥ�ڵ���ʱ�䵽��</td>
          <td class="xx1">
          	<input name="arriveendtime" type="text" value="<%=arriveendtime == null ? "" : arriveendtime%>"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">��&nbsp;��&nbsp;�ˣ�</td>
          <td class="yx">
          	<input type="text" name="shippername" value="<%=shippername == null ? "" : shippername%>" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="shipperid" value="<%=shipperid == null ? "" : shipperid%>" />
            <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=3','800','520','shipperid','shippername');" />
          </td>
          <td align="right" class="yx1">��&nbsp;Ӧ&nbsp;�̣�</td>
          <td class="yx">
          	 <input type="text" name="suppliername" value="<%=suppliername == null ? "" : suppliername%>" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
             <input type="hidden" name="supplierid" value="<%=supplierid == null ? "" : supplierid%>" />
             <input name="moreBtn2" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=2','800','520','supplierid','suppliername');" />
          </td>
          <td align="right" class="yx1">�ջ���̨��</td>
          <td class="xx1" >
            <input type="text" name="receiveloc"  value="<%=receiveloc == null ? "" : receiveloc%>"   style="height:18px;width:100px;"/>
            
         </td> 
        </tr>

        <tr>
          <td align="right" class="yx1">���ر����ţ�</td>
          <td class="yx">
          	<input type="text" name="customsno"  value="<%=customsno == null ? "" : customsno%>" style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">�û��Զ���1��</td>
          <td class="yx">
          		<input type="text" name="reserve1" value="<%=reserve1 == null ? "" : reserve1%>"  style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">�û��Զ���2��</td>
          <td class="xx1" ><input type="text" name="reserve2" value="<%=reserve2 == null ? "" : reserve2%>"  style="height:18px;width:100px;"/></td>
        </tr>
        <tr>
     		<td class="yx1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</div></td>
     		<td class="xx1" colspan="5"><div align="left"><textarea name="remark" cols="105" rows="3"><%=remark == null ? "" : remark%></textarea></div></td>
   		</tr>
        <tr >
        	<TD colspan="6" height="20"></TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="add" type="button" class="button_edit" id="add" value="&nbsp;&nbsp;����" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
	</FORM>
  </body>
</html>
