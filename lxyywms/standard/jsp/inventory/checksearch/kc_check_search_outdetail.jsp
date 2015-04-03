<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;		//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  		//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newckd");//�½����ⵥ->��ʾ���ⵥ��ϸ	
		}
	}
%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
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
</SCRIPT>
</head>

<body onLoad="OnLoad()">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="theTable" width="130%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��״̬</div></td> 
     <td class="TD_LIST_TITLE"><div class="list_title">��װ</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">���侻��</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE2" align="center"><div class="list_title">��������</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail detail = null;	//���ⵥ��ϸ  
		
		String outstockdetailid;	// ���ⵥ��ϸID
      	String outstockid;			// ���ⵥ�ݱ��
      	String linestatus;     		// ��״̬��
        String product;    			// ��Ʒ
      	String pack;       			// ��װ����
        String unit;       			// ��λ����
        
      	double invoicenum;		//����
	  	double weight;			//����
      	double netweight;		//����
      	double assnum;			//��������
      	double assweight;		//��������
      	double assnetweight;	//���侻��
		double sendnum;         //��������
   		double snetweight;      //��������
    	double sweight;         //��������
        
		
		for(int i = 0; i < ls.size(); i++){
			detail = (OutboundInvoiceDetail)ls.get(i);
			
			outstockdetailid = detail.getOutstockdetailid();//���ⵥ��ϸID
	      	outstockid = detail.getOutstockid();			//���ⵥ�ݱ��
	      	linestatus = detail.getM_strStatusName();   	//״̬��
         	product = detail.getM_strProductName(); 		// ��Ʒ
         	pack = detail.getM_strPackName();       		//��װ����
         	unit = detail.getM_strUnitName();       		//��λ����  
         	
	      	invoicenum = detail.getInvoicenum();		//����
		  	weight = detail.getWeight();				//����
	      	netweight = detail.getNetweight();			//����
	      	
	      	assnum = detail.getAssignnum();				//��������
      	 	assweight = detail.getAssignweight();		//��������
      	 	assnetweight = detail.getAssignnetweight();	//���侻��
      	 	
      	 	sendnum = detail.getSendnum();				//��������
      	 	sweight = detail.getSweight();				//��������
      	 	snetweight = detail.getSnetweight();		//��������
         	
		
%>
  <!-- onclick="setSel(<%=i%>)" -->
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" >
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=linestatus==null?"":linestatus%></td>
     <td class="TD_LIST" align="center"><%=pack==null?"":pack%></td>
     <td class="TD_LIST" align="center"><%=unit==null?"":unit%></td>
 <%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = detail.getClass().getDeclaredField(lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(detail)==null?"":(String)field.get(detail)%></td>
<%
		}
	}
%>    
     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
     
      <td class="TD_LIST" align="center"><%=nbf.format(assnum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(assweight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(assnetweight)%></td>
     
      <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(sweight)%></td>
     <td class="TD_LIST2" align="center"><%=nbf.format(snetweight)%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="<%=14 + iLine%>" class="TD_last_LIST" valign="top">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
     </td>
   </tr>
   </tbody> 
 </table>
 </div>
</body>
</html>