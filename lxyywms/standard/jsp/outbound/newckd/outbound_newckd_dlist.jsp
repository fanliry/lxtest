<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
    //���е�������
	List ls = (List)request.getAttribute("invoicedetail");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
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
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(the){
	
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			//�ı䱳��ɫ
			if(i==the){
				check_ids[i].checked = true;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}else{
				check_ids[i].checked = false;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
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
	function OnLoad(){
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%"  id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
   </tr>
<%
	
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//���ⵥ��ϸ  
		
		String outstockdetailid;//���ⵥ��ϸID
      	String outstockid;		//���ⵥ�ݱ��
      	String productCode; //��Ʒ����
      	String productid;		//Ʒ��
      	String packid;          //��װ 
        String eunit;           //��λ 
      	double invoicenum;		//����
	  	double weight;			//����
      	
      	
      	double assnum;			//��������
      	double assweight;		//��������
		double sendnum;//��������
		double sendweight;//��������
   
        String m_strStatusName;     // ״̬��
        String m_strProductName;    // ��Ʒ
      	String m_strPackName;       // ��װ����
        String m_strUnitName;       // ��λ����
        
        String printdate; //��������
		String lotinfo; //����
      	
		for(int i = 0; i < ls.size(); i++)
		{
			outDetail = (OutboundInvoiceDetail)ls.get(i);
			
			 outstockdetailid = outDetail.getOutstockdetailid();//���ⵥ��ϸID
	      	 outstockid = outDetail.getOutstockid();			//���ⵥ�ݱ��
	         productCode = outDetail.getM_strProductCode();//��Ʒ����
	      	 productid = outDetail.getProductid();			//Ʒ��
	      	 invoicenum = outDetail.getInvoicenum();		//����
		  	 weight = outDetail.getWeight();				//����
	      	 
	      	 packid = outDetail.getPackid();              	/* ��װ */
         	 eunit  = outDetail.getPkgunit();               /* ��λ */
	      	 assnum = outDetail.getAssignnum();				//��������
      	 	 assweight = outDetail.getAssignweight();		//��������

 			sendnum = outDetail.getSendnum();//��������
 			sendweight = outDetail.getSweight();//��������
      	 	
         	m_strStatusName = outDetail.getM_strStatusName();   // ״̬��
         	m_strProductName = outDetail.getM_strProductName(); // ��Ʒ
         	
         	m_strPackName = outDetail.getM_strPackName();       //��װ����
         	m_strUnitName = outDetail.getM_strUnitName();       //��λ���� 
         	
         	printdate= outDetail.getPrintdate();
         	lotinfo = outDetail.getLotinfo();
 %>
	         <tr class="list_normal_tr" onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
		     <td class="TD_LIST" align="center"><input onClick="setSel(<%=i%>)" type="radio" name="check_id" class="input_checkbox" value="<%=outstockdetailid%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(sendnum)%></span></td>
		   </tr>	       	 
<%
	      	 
		}
		
	}
%>

   <tr>
      <td height="100%" colspan="10" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
  
 </table>
 </div>
</body>
</html>
