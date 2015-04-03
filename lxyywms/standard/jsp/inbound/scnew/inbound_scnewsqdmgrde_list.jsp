<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail" %>
<%@ page import="java.util.HashMap" %>
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
	function Change(obj)
	{
		obj.style.backgroundColor = "#33FF33";
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
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
 <table width="100%" height="100%" id="table" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ƻ�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ״̬</div></td>
   </tr>
<%
	ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundRequestInvoiceDetail inDetail = null;//��ⵥ��ϸ 
		String instockdetailid;	//������뵥��ϸid
      	String instockid;		//������뵥��
      	String productid;		//Ʒ��id
      	String printdate;		//��������
	  	double plannum;			//�ƻ�����
      	double bindingnum;		//������
      	String lotinfo;         //������Ϣ
      	String productCode;		//��Ʒ����
      	String productName;     //��Ʒ����
      	String productStatus;	//��Ʒ״̬
        String m_strUnitName="";    //��λ
      	
		for(int i = 0; i < ls.size(); i++)
		{
			 inDetail = (InboundRequestInvoiceDetail)ls.get(i);
			 instockdetailid = inDetail.getInstockdetailid();	
			 instockid = inDetail.getInstockid();	
			 productid = inDetail.getProductid();	
			 printdate = inDetail.getPrintdate();	
			 plannum = inDetail.getPlannum();	
			 bindingnum = inDetail.getBindingnum();	
			 lotinfo = inDetail.getLotinfo();	
			 productName = inDetail.getProductName();
			 productCode = inDetail.getProductCode();
			 productStatus = inDetail.getProductStatusName();
			 m_strUnitName = inDetail.getPunit();
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
		     <td class="TD_LIST" align="center"><%=productName == null ? "" : productName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="TD_LIST" align="center"><%=plannum%></td>
		     <td class="TD_LIST" align="center"><%=bindingnum%></td>  
		     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=productStatus == null ? "" : productStatus%></td> 
		   </tr>	       	 
<%
	      	 
		}
	}
%>
   <tr>
      <td height="100%" colspan="7" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>
</body>
</html>
