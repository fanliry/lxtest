<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptDetail" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;	//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  	//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newshd");//�½��ջ���->��ʾ�ջ�����ϸ	
		}
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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
		var oldobj = null;
		function showDetail(obj,instockdetailid,instockid,productid,productname,packid,packname,eunit,invoicenum,weight,netweight,volume,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12, dhnum, shnum)
		{
			parent.lotreset();	  //����
			if(obj.cells.item(0).getElementsByTagName("input")[0].checked == false)
			{
					obj.style.backgroundColor = "";	
					
					parent.form1.reset(); //����
					
			}else{
						obj.style.backgroundColor = "#99CCFF";
						parent.document.getElementById("invoicedetailid").value = instockdetailid;//������ϸID
			    		parent.document.getElementById("invoiceid").value = instockid;//����ID
						parent.document.getElementById("product_name").value = productname;//��Ʒ��
			        	parent.document.getElementById("packname").value = packname;//��װ��
			        	//ʣ������ = �������� - �ջ����� - �������� - ��������
			        	parent.document.getElementById("realnum").value = invoicenum;		//ʣ������
						parent.document.getElementById("realweight").value = weight;		//ʣ������
						parent.document.getElementById("realnetweight").value = netweight;	//ʣ������
						parent.document.getElementById("realvolume").value = volume;		//ʣ������
						
						parent.document.getElementById("renum").value = invoicenum;		//ʣ������
						parent.document.getElementById("reweight").value = weight;		//ʣ������
						parent.document.getElementById("renetweight").value = netweight;//ʣ������
						parent.document.getElementById("revolume").value = volume;		//ʣ������
						
						parent.document.getElementById("invoicenum").value = dhnum;	//��������
						parent.document.getElementById("recnum").value = shnum;		//���ջ�����
						
						parent.document.getElementById("rejectednum").value = "0";	//��������
						parent.document.getElementById("holdnum").value = "0";		//��������

						parent.selectinoutUnit(eunit, 'eunit', packid, '1');	//��λ
						parent.document.getElementById("lotid").value = lotid;	//��������	
						parent.setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//����
							
			}
			
			if(oldobj != null){	
				if(oldobj.cells.item(0).getElementsByTagName("input")[0].checked == false)
				{
					oldobj.style.backgroundColor = "";	
	
				}
			}
			oldobj = obj;
			
			//��ѯ�ջ���ҵ
			showjob(instockdetailid);
		}

	function showjob(detailid)
	{
		parent.detail.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=2&invoicedetailid="+detailid;
	}
	
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�ջ��ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
	
</script>

</head>

<body onload="javascript:OnLoad();">

	<table width="200%" height="143" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr>
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��װ</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��λ</div></td>
     
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">�ջ�����</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">�ջ�ë��</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">�ջ�����</div></td>
     
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">���ձ���</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����ԭ��</div></td>
     
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����ԭ��</div></td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE3" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">�ջ�����</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundReceiptDetail inDetail = null;//�ջ�������ϸ 
		String instockdetailid;	//�ջ�����ϸID
      	String instockid;		//�ջ����ݱ��
      	String productid;		//Ʒ�����
      	String packname;		//��װ
      	String packid;			//��װ
      	String eunit;			//��λ��
      	String eunitid;			//��λ
      	double invoicenum;		//��С��λ����
	  	double weight;			//����
      	double netweight;		//����
      	double volume;			//���
      
      	double recnum;			//��С��λ�������ջ���
      	double reweight;		//�ջ�����
      	double renetweight;		//�ջ�����
      	double revolume;			//���
      	String reclocation;		//�ջ���λ
      	
      	String rejectcode;          //����ԭ����� 
     	String rejectreason;    	//����ԭ������ 
     	double rejectednum;			//��������
     	String holdcode;            //����ԭ����� 
     	String holdreason;          //����ԭ������ 
     	double holdnum;				//��������


      	String linestatus;      //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
      	
        String m_strStatusName;           // ״̬��
        String m_strProductName;          // ��Ʒ
        String lotid;            	//��������ID 
        String lotatt1;  			//��������1
     	String lotatt2;  			// ��������2
     	String lotatt3;  			// ��������3
     	String lotatt4;  			// ��������4
     	String lotatt5;  			// ��������5
     	String lotatt6;  			// ��������6
     	String lotatt7;  			// ��������7
     	String lotatt8;  			// ��������8
     	String lotatt9;  			// ��������9
     	String lotatt10;  			// ��������10
     	String lotatt11;  			// ��������11
     	String lotatt12;  			// ��������12
      	
      	
		for(int i = 0; i < ls.size(); i++)
		{
			inDetail = (InboundReceiptDetail)ls.get(i);
			 instockdetailid = inDetail.getReincoicedetailid();	//�ջ�����ϸID
	      	 instockid = inDetail.getReinvoiceid();			//�ջ����ݱ��
	      	 productid = inDetail.getProductid();			//Ʒ�����
	      	 packname = inDetail.getM_strPackName();		//��װ
	      	 packid = inDetail.getPackid();					//��װ
	      	 eunit = inDetail.getM_strUnitName();			//��λ
	      	 eunitid = inDetail.getEunit();					//��λ
	      	 
	      	 invoicenum = inDetail.getInvoicenum();			//��С��λ����
		  	 weight = inDetail.getWeight();					//����
	      	 netweight = inDetail.getNetweight();			//����
	      	 volume = inDetail.getVolume();					//���
	      	 
	      	 recnum = inDetail.getRecnum();				//��С��λ�������ջ���
	      	 reweight = inDetail.getReweight();			//�ջ�����
	      	 renetweight = inDetail.getRenetweight();	//�ջ�����
	      	 revolume = inDetail.getRevolume();			//�ջ����
	      	 reclocation = inDetail.getReclocation();	//�ջ���λ
	      	 
	      	 linestatus = inDetail.getLinestatus();     //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
	      	 
         	 m_strStatusName = inDetail.getM_strStatusName();            // ״̬��
         	 m_strProductName = inDetail.getM_strProductName();          // ��Ʒ
         	 
         	 rejectcode = inDetail.getRejectcode();         //����ԭ����� 
     	 	 rejectreason = inDetail.getRejectreason();    	//����ԭ������ 
     	 	 rejectednum = inDetail.getRejectednum();		//��������
     	 	 holdcode = inDetail.getHoldcode();             //����ԭ����� 
     	 	 holdreason = inDetail.getHoldreason();         //����ԭ������ 
     	     holdnum = inDetail.getHoldnum();				//��������
         	 
         	 lotid = inDetail.getLotid();            	// ��������ID
         	 lotatt1 = inDetail.getLotatt1();  			// ��������1
     	 	 lotatt2 = inDetail.getLotatt2();  			// ��������2
     	 	 lotatt3 = inDetail.getLotatt3();			// ��������3
     	 	 lotatt4 = inDetail.getLotatt4();  			// ��������4
     	 	 lotatt5 = inDetail.getLotatt5();  			// ��������5
     	 	 lotatt6 = inDetail.getLotatt6();  			// ��������6
     	 	 lotatt7 = inDetail.getLotatt7();  			// ��������7
     	 	 lotatt8 = inDetail.getLotatt8();  			// ��������8
     	 	 lotatt9 = inDetail.getLotatt9();  			// ��������9
     	 	 lotatt10 = inDetail.getLotatt10();  		// ��������10
     	 	 lotatt11 = inDetail.getLotatt11();  		// ��������11
     	 	 lotatt12 = inDetail.getLotatt12();  		// ��������12
	      	 
	      	 //��ʣ�ջ�����
	      	 double realnum = invoicenum - recnum - rejectednum - holdnum;
	      	 //��ʣ�ջ�ë��
	      	 double realweight = weight - reweight;
	      	 //��ʣ�ջ�����
	      	 double realnetweight = netweight - renetweight;
	      	 //��ʣ�ջ����
	      	 double realvolume = volume - revolume;
	      	 
 %>
	         <tr class="list_normal_tr" onmouseover="this.bgColor='#CCFF00'" onmouseout="bgColor=''" onclick="showDetail(this,'<%=instockdetailid%>','<%=instockid%>','<%=productid%>','<%=m_strProductName%>','<%=packid%>','<%=packname%>','<%=eunitid%>','<%=realnum%>','<%=realweight%>','<%=realnetweight%>','<%=realvolume%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>','<%=invoicenum%>','<%=recnum%>')">
		     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=packname%></td>
		     <td class="TD_LIST" align="center"><%=eunit%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(recnum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(reweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(renetweight)%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(rejectednum)%></td>
		     <td class="TD_LIST" align="center"><%=rejectcode == null ? "" : rejectcode%></td>
		     <td class="TD_LIST" align="center"><%=rejectreason == null ? "" : rejectreason%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(holdnum)%></td>
		     <td class="TD_LIST" align="center"><%=holdcode == null ? "" : holdcode%></td>
		     <td class="TD_LIST" align="center"><%=holdreason == null ? "" : holdreason%></td>
		     
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = inDetail.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(inDetail)%></td>
<%
		}
	}
%>				  
		     <td class="TD_LIST2" align="center"><%=reclocation%></td>
		   </tr>	       	 
<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("inboundPage1");
	}
%>

   <tr>
     <td class="TD_last_LIST" height="100%" colspan="<%=18 + iLine%>" valign="top">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
     </td>
   </tr>
  
 </table>
 <!-- ======== ��ҳ��ǩ ======== -->
	<%@ include file="include/page1.jsp" %>
	
	
	<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
