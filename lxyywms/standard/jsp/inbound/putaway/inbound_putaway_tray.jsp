<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	//һ���̿ɷŶ�������
	int oneTray = (Integer)request.getAttribute("onetray");//һ���̿ɷŶ�������
	//������������
	int iTrayNum = (Integer)request.getAttribute("traynum");//���̵���������
	//�ջ���¼
	InboundReceiptTransaction receiptTrans = (InboundReceiptTransaction)request.getAttribute("trans");//�ջ���¼
	
	String transid = "";		    //���׺�
	String strWarehouseID = "";	 //�ֿ�ID
    String m_strProductName = "";// ��Ʒ
    String m_strPackName = "";	// ��װ
    String strPackid = "";		//��װID
	String punit = "";          //��λ
    double num = 0;            //����
    double weight = 0;         //����
    double netweight = 0;      //���� 
    
    String lotid = "";            	//��������ID 
    String lotatt1 = "";  			//��������1
    String lotatt2 = "";  			// ��������2
    String lotatt3 = "";  			// ��������3
    String lotatt4 = "";  			// ��������4
    String lotatt5 = "";  			// ��������5
    String lotatt6 = "";  			// ��������6
    String lotatt7 = "";  			// ��������7
    String lotatt8 = "";  			// ��������8
    String lotatt9 = "";  			// ��������9
    String lotatt10 = "";  			// ��������10
    String lotatt11 = "";  			// ��������11
    String lotatt12 = "";  			// ��������12  

	//����С��2λ
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2);  

	if(receiptTrans != null)
	{
		transid = receiptTrans.getTransreceiptid();		      //���׺�	
		strWarehouseID = receiptTrans.getWarehouseid();		  //�ֿ�ID
     	m_strProductName = receiptTrans.getM_strProductName();// ��Ʒ
     	m_strPackName = receiptTrans.getM_strPackName();	  // ��װ
     	strPackid = receiptTrans.getPackid();
	 	punit = receiptTrans.getEunit();           //��λ
     	num = receiptTrans.getRecnum() - receiptTrans.getPucnum();         //����   �ջ�����-�ϼ�����
     	weight = receiptTrans.getReweight() - receiptTrans.getPuweight();  //����  �ջ�����-�ϼ�����
     	netweight = receiptTrans.getRenetweight() - receiptTrans.getPunetweight();//����  �ջ�����-�ϼܾ���
    
		lotid = receiptTrans.getLotid();            	// ��������ID 
        lotatt1 = receiptTrans.getLotatt1();  			// ��������1
     	lotatt2 = receiptTrans.getLotatt2();  			// ��������2
     	lotatt3 = receiptTrans.getLotatt3();			// ��������3
     	lotatt4 = receiptTrans.getLotatt4();  			// ��������4
     	lotatt5 = receiptTrans.getLotatt5();  			// ��������5
     	lotatt6 = receiptTrans.getLotatt6();  			// ��������6
     	lotatt7 = receiptTrans.getLotatt7();  			// ��������7
     	lotatt8 = receiptTrans.getLotatt8();  			// ��������8
     	lotatt9 = receiptTrans.getLotatt9();  			// ��������9
     	lotatt10 = receiptTrans.getLotatt10();  		// ��������10
     	lotatt11 = receiptTrans.getLotatt11();  		// ��������11
     	lotatt12 = receiptTrans.getLotatt12();  		// ��������12
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
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
	
	/*������ť*/
	function LockButton(){
		
		document.getElementById("add").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}
	
	//����
	function addData(traynum){
		LockButton();
		var transid = document.getElementById("transid").value;//�ջ���¼��
		var whAreaId = document.getElementById("whAreaId").value;		//�ϼܿ���ID
		
		//if(whAreaId == null || whAreaId.length < 1){
		//	alert("���ϼܿ���������Ϊ�գ�");
		//	UnLockButton();
		//	return;
		//}
		
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
		var lotatt10  = document.getElementById("lotatt10").value; //��������10
		var lotatt11  = document.getElementById("lotatt11").value; //��������11
		var lotatt12  = document.getElementById("lotatt12").value; //��������12
		
		//������
		var msg = "<input type=hidden name='transid' value='"+transid+"'>"
				+ "<input type=hidden name='whAreaId' value='"+whAreaId+"'>"
				+ "<input type=hidden name='lotid' value='"+lotid+"'>"
				+ "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
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
		
		if(confirm("��ȷ�����"+traynum+"�У�"))
		{		
			window.returnValue = msg;
    		window.close();	
		}else{
			UnLockButton();
		}
	}
	
	
	function viewTrans(packid,eunit,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		//selectinoutUnit(eunit, 'eunit', packid, '1');	//��λ		
		setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//����
					
	}
</script>

</head>

<body onload="javascript:viewTrans('<%=strPackid%>','<%=punit%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>');selectAreaList('', 'whAreaId', '<%=strWarehouseID%>', '1');">
	 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   		<tr>
     		<td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �ϼ� -&gt; ����</div></td>
   		</tr>
     </table>
	 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  >��&nbsp;��&nbsp;�ţ�</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="transid" class="input_readonly" value="<%=transid%>" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td width="20%" class="yx">
	          <input type="text" name=product_name class="input_readonly" value="<%=m_strProductName%>"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">�ϼܿ�����</td> 
          <td width="19%" class="xx1">
	          <select name="whAreaId"  style="width:100px;" >
	            <option>--��ѡ��--</option>
	           </select> 
          </td>
        </tr>
        <tr>
     		<td height="5" colspan="6">
     			<input type="hidden" name="packname" value="<%=m_strPackName%>"/>
     			<input type="hidden" name="lotid" value="<%=lotid%>"/>
     		</td>
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
     		<td height="10" colspan="6">

     		</td>
   		</tr>
   		
   		<tr>
   		  
          <td  align="right" class="yx1_top"  >�ϼ�������</td>
          <td  class="yx_top" >
          	 <input type="text" name="num" class="input_readonly" value="<%=nbf.format(num)%>" readonly   style="height:18px;width:50px;"/> 
          </td>
          <td  align="right" class="yx1_top"  >��������������</td>
          <td  class="xx1_top" >
          	 <input type="text" name="itraynum" class="input_readonly" value="<%=iTrayNum%>" readonly   style="height:18px;width:50px;"/> 
          </td>
          <td  align="right" class="yx1_top"  >���̿ɷ�������</td>
          <td  class="xx1_top" >
	          <input type="text" name="onetray" class="input_readonly" value="<%=oneTray%>"  readonly  style="height:18px;width:50px;"/>
          </td>
        </tr>
   		
   		<tr>
     		<td height="10" colspan="6">

     		</td>
   		</tr>
   		 
        <tr>
          <td height="25" colspan="6" align="center" >
          <input name="add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;����" onClick="addData('<%=iTrayNum%>');"  />
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>

</body>
</html>
