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
	function Change(obj)
	{
		obj.style.backgroundColor = "#33FF33";
		if(oldobj != null){
			oldobj.style.backgroundColor = "";	
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
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#33FF33";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
</script>

</head>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="200%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��װ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ԭ��</div></td>   
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ԭ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ջ���λ</div></td>
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
      	
      	
      
      	String packid;              /* ��װ */
        String eunit;               /* ��λ */
        double rejectednum;         /* �������� */
        double holdnum;             /* �������� */
        String m_strRejectCodeText; //���ձ�����ʾ����
        String m_strHoldCodeText;   //���������ʾ����
      	
      	double recnum;			//��С��λ�������ջ���
      	double reweight;		//�ջ�����
      	double renetweight;		//�ջ�����
      	String reclocation;		//�ջ���λ



      	String linestatus;      //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
      	
        String m_strProviderName;         // ��Ӧ��
        String m_strStatusName;           // ״̬��
        String m_strProductName;          // ��Ʒ
      	
      	String m_strPackName;       //��װ����
        String m_strUnitName;       //��λ����
      	
		for(int i = 0; i < ls.size(); i++)
		{
			inDetail = (InboundReceiptDetail)ls.get(i);
			 instockdetailid = inDetail.getReincoicedetailid();	//����ջ�����ϸID
	      	 instockid = inDetail.getReinvoiceid();			//����ջ����ݱ��
	      	 productid = inDetail.getProductid();			//Ʒ�����
	      	 invoicenum = inDetail.getInvoicenum();		//��С��λ����
		  	 weight = inDetail.getWeight();			//����
	      	 netweight = inDetail.getNetweight();			//����
	      	 
	      	 packid = inDetail.getPackid();              /* ��װ */
         	 eunit  = inDetail.getEunit();               /* ��λ */
         	 rejectednum = inDetail.getRejectednum();         /* �������� */
         	 holdnum = inDetail.getHoldnum();             /* �������� */
         	 m_strRejectCodeText = inDetail.getM_strRejectCodeText(); //���ձ�����ʾ����
         	 m_strHoldCodeText = inDetail.getM_strHoldCodeText();   //���������ʾ����
	      	 

	      	 recnum = inDetail.getRecnum();			//��С��λ�������ջ���
	      	 reweight = inDetail.getReweight();			//�ջ�����
	      	 renetweight = inDetail.getRenetweight();		//�ջ�����
	      	 reclocation = inDetail.getReclocation();		//�ջ���λ
	   
	      	 linestatus = inDetail.getLinestatus();         //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
	      	 
         	m_strStatusName = inDetail.getM_strStatusName();            // ״̬��
         	m_strProductName = inDetail.getM_strProductName();          // ��Ʒ
         	
         	m_strPackName = inDetail.getM_strPackName();       //��װ����
         	m_strUnitName = inDetail.getM_strUnitName();       //��λ����
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(recnum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(reweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(renetweight)%></td>
			 <td class="TD_LIST" align="center"><%=nbf.format(rejectednum)%></td>
			 <td class="TD_LIST" align="center"><%=m_strRejectCodeText == null ? "" : m_strRejectCodeText%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(holdnum)%></td>
		     <td class="TD_LIST" align="center"><%=m_strHoldCodeText == null ? "" : m_strHoldCodeText%></td>
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
      <td height="100%" colspan="<%=16 + iLine%>" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
  
 </table>
 </div>
</body>
</html>
