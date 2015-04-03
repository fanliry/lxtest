<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
   
    //���е�������
	List ls = (List)request.getAttribute("exResultList");
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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
	
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//�ı䱳��ɫ
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(i){
	
		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
	//˫������ʾ��ʾ������ϸ����Ӧ����ҵ��ϸ
	function upd(i){
		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	    showModalDialog(ac + "outBoundAction&method=ricoExecSearchRkd&flag=1&outstockdetailid=" + id, "", "dialogWidth=900px; dialogHeight=450px; scroll=auto");
	}
		
	function OnLoad(){
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
</script>

</head>

<body onload="javascript:OnLoad();">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%" height="100%"   id="table"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
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
      	double netweight;		//����
      	
      	
      	double assnum;			//��������
      	double assweight;		//��������
      	double assnetweight;	//���侻��
      	
      	double sendnum;         //��������
   		double snetweight;      //��������
    	double sweight;         //��������

   
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
	      	 netweight = outDetail.getNetweight();			//����
	      	 
	      	 packid = outDetail.getPackid();              	/* ��װ */
         	 eunit  = outDetail.getPkgunit();               /* ��λ */
	      	 assnum = outDetail.getAssignnum();				//��������
      	 	 assweight = outDetail.getAssignweight();		//��������
      	 	 assnetweight = outDetail.getAssignnetweight();	//���侻��

 
         	m_strStatusName = outDetail.getM_strStatusName();   // ״̬��
         	m_strProductName = outDetail.getM_strProductName(); // ��Ʒ
         	
         	m_strPackName = outDetail.getM_strPackName();       //��װ����
         	m_strUnitName = outDetail.getM_strUnitName();       //��λ���� 
         	
         	printdate= outDetail.getPrintdate();
         	lotinfo = outDetail.getLotinfo();
         	
         	sendnum = outDetail.getSendnum();				//��������
      	 	sweight = outDetail.getSweight();				//��������
      	 	snetweight = outDetail.getSnetweight();		//��������
 %>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)" >
     <td class="TD_LIST1" align="center" width="50px">
       <input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=outstockdetailid%>"><%=i+1%>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
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
