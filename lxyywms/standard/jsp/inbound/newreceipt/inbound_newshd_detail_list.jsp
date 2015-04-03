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
	function showDetail(obj,instockdetailid,instockid,productid,productname,packid,packname,eunit,invoicenum,weight,netweight,volume,price,skustatus,skustatus_descr,reclocation,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		if(obj.cells.item(0).getElementsByTagName("input")[0].checked == false)
		{
				obj.style.backgroundColor = "";	
				
				parent.form1.reset(); //����
				parent.lotreset();	  //����
		}else{
					obj.style.backgroundColor = "#99CCFF";
					parent.document.getElementById("invoicedetailid").value = instockdetailid;//������ϸID
		    		parent.document.getElementById("invoiceid").value = instockid;//����ID
					parent.document.getElementById("productid").value = productid;//��ƷID
					parent.document.getElementById("product_name").value = productname;//��Ʒ��
		    		parent.document.getElementById("packid").value = packid;	//��װID
		        	parent.document.getElementById("packname").value = packname;//��װ��
		
					parent.document.getElementById("invoicenum").value = parseFloat(invoicenum).toFixed(2);
					parent.document.getElementById("weight").value = parseFloat(weight).toFixed(2);
					parent.document.getElementById("netweight").value = parseFloat(netweight).toFixed(2);
					parent.document.getElementById("volume").value = parseFloat(volume).toFixed(2);
					parent.document.getElementById("price").value = parseFloat(price).toFixed(2);
					
					parent.document.getElementById("skustatus_descr").value = skustatus_descr;//״̬����
					parent.document.getElementById("reclocation").value = reclocation;//�ջ�����
					parent.selectType(skustatus, 'skustatus', 'skustatus'); //��Ʒ״̬
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
		

	}
	

	
	
	
	
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){		
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();" >
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��װ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�����</div></td>
     
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>     
     
     
   </tr>
<%
	List ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundReceiptDetail inDetail = null;//�ջ�������ϸ 
		String instockdetailid;	//����ջ�����ϸID
      	String instockid;		//����ջ����ݱ��
      	String productid;		//Ʒ�����
      	double invoicenum;		//��С��λ����
	  	double weight;			//����
      	double netweight;		//����
      	double volume;			//���
      	double price;			//�۸�
      	String eunit;			//��λ
      	
      	String skustatus;           /* ��Ʒ״̬���� */
        String skustatus_descr;     /* ��Ʒ״̬���� */

      	double recnum;			//��С��λ�������ջ���
      	double reweight;		//�ջ�����
      	double renetweight;		//�ջ�����
      	String reclocation;		//�ջ���λ

		String packid;              /* ��װ */
        double rejectednum;         /* �������� */
        double holdnum;             /* �������� */
        String m_strRejectCodeText; //���ձ�����ʾ����
        String m_strHoldCodeText;   //���������ʾ����
      	

        String m_strStatusName;     // ״̬��
        String m_strProductName;    // ��Ʒ
        String m_strPackName;       //��װ����
        String m_strUnitName;       //��λ����
        
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
        

      	String linestatus;      //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
		for(int i = 0; i < ls.size(); i++)
		{
			inDetail = (InboundReceiptDetail)ls.get(i); 
			 instockdetailid = inDetail.getReincoicedetailid();	//����ջ�����ϸID
	      	 instockid = inDetail.getReinvoiceid();			//����ջ����ݱ��
	      	 productid = inDetail.getProductid();			//Ʒ�����
	      	 invoicenum = inDetail.getInvoicenum();		//��С��λ����
		  	 weight = inDetail.getWeight();			//����
	      	 netweight = inDetail.getNetweight();			//����
	      	 volume  = inDetail.getVolume();			//���
      	 	 price = inDetail.getPrice();			//�۸� 

	      	 eunit = inDetail.getEunit();				//��λ
	      	 packid = inDetail.getPackid();              /* ��װ */
         	 rejectednum = inDetail.getRejectednum();         /* �������� */
         	 holdnum = inDetail.getHoldnum();             /* �������� */
         	 m_strRejectCodeText = inDetail.getM_strRejectCodeText(); //���ձ�����ʾ����
         	 m_strHoldCodeText = inDetail.getM_strHoldCodeText();   //���������ʾ����

	      	 recnum = inDetail.getRecnum();			//��С��λ�������ջ���
	      	 reweight = inDetail.getReweight();			//�ջ�����
	      	 renetweight = inDetail.getRenetweight();		//�ջ�����
	      	 reclocation = inDetail.getReclocation();		//�ջ���λ

	      	 linestatus = inDetail.getLinestatus();         //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
	      	 skustatus = inDetail.getSkustatus();           /* ��Ʒ״̬���� */
             skustatus_descr = inDetail.getSkustatus_descr();     /* ��Ʒ״̬���� */

         	 m_strStatusName = inDetail.getM_strStatusName();            // ״̬��
         	 m_strProductName = inDetail.getM_strProductName();          // ��Ʒ
         	
         	 m_strPackName = inDetail.getM_strPackName();       //��װ����
         	 m_strUnitName = inDetail.getM_strUnitName();       //��λ����
         	
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
	      	 
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''" onclick="showDetail(this,'<%=instockdetailid%>','<%=instockid%>','<%=productid%>','<%=m_strProductName%>','<%=packid%>','<%=m_strPackName%>','<%=eunit%>','<%=invoicenum%>','<%=weight%>','<%=netweight%>','<%=volume%>','<%=price%>','<%=skustatus%>','<%=skustatus_descr%>','<%=reclocation%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>')">
		     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>

		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=reclocation == null ? "" : reclocation%></td>
		     
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
		     
		   </tr>	       	 
<%
	      	 
		}
		
	}
%>

   <tr>
     <td height="100%" colspan="<%=5 + iLine%>"></td>
   </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
