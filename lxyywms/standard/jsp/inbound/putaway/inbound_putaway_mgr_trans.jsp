<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	List ls = (List)request.getAttribute("exResultList");
	InboundReceiptTransaction receiptTrans = null;//�ջ���¼
	
	String transid = "";		    //���׺�
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
	if(ls != null && ls.size() > 0) 
	{
		receiptTrans = (InboundReceiptTransaction)ls.get(0);
	}
	if(receiptTrans != null)
	{
		transid = receiptTrans.getTransreceiptid();		      //���׺�
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
	
	function viewTrans(packid,eunit,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		selectinoutUnit(eunit, 'eunit', packid, '1');	//��λ		
		setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//����
					
	}
</script>

</head>

<body onload="javascript:viewTrans('<%=strPackid%>','<%=punit%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>');">

	 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add1" >
        <tr>
          <td width="13%" align="right" class="yx1"  >��&nbsp;��&nbsp;�ţ�</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="transid" class="input_readonly" value="<%=transid%>" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td width="20%" class="yx">
	          <input type="text" name=product_name class="input_readonly" value="<%=m_strProductName%>"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td> 
          <td width="19%" class="xx1">
	          <select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--��ѡ��--</option>
	        </select>
          </td>
        </tr>
        <tr>
          <td width="13%" align="right" class="yx1"  >���ϼ�������</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="vnum" class="input_readonly" value="<%=nbf.format(num)%>" readonly  style="height:18px;width:100px;"/> 
          	 <input type="hidden" name="num" class="input_readonly" value="<%=num%>" readonly  style="height:18px;width:100px;"/> 
          </td>
          <td width="15%" align="right" class="yx1">���ϼܾ��أ�</td>
          <td width="20%" class="yx">
	          <input type="text" name="vnetweight" class="input_readonly" value="<%=nbf.format(netweight)%>"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="netweight" class="input_readonly" value="<%=netweight%>"  readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">���ϼ�ë�أ�</td>
          <td width="19%" class="xx1">
	          <input type="text" name="vweight"  class="input_readonly" value="<%=nbf.format(weight)%>"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="weight"  class="input_readonly" value="<%=weight%>"  readonly  style="height:18px;width:100px;"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>���ϼ�������</td>
          <td class="yx">
          	<input type="text" name="renum" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>���ϼܾ��أ�</td>
          <td class="yx">
          	<input type="text" name="renetweight" size="16"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>���ϼ�ë�أ�</td>
          <td class="xx1">
          	<input type="text" name="reweight" size="16"   style="height:18px;width:100px;"/>
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
      </table>
		 <!-- ======== ��ҳ��ǩ ======== -->
	<%@ include file="include/page4.jsp" %>
</body>
</html>
