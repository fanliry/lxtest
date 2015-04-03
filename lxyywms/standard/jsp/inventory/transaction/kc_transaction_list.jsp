<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.InventoryTransaction"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="java.lang.reflect.Field"%>
<%
	HashMap<String, List> hsLot = null;		//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  	    //��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("kcjy");	//����̵���ʾ
		}
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵID</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FMƷ�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM�ͻ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM��������</div></td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title">FM<%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>

     <td class="TD_LIST_TITLE"><div class="list_title">FM����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TOƷ�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO�ͻ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO��������</div></td>
<%
//	int iLine = 0;	//��ʾ���������Ը���
	//BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title">TO<%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE"><div class="list_title">TO����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������Ա</div></td>
     
   </tr>
<%
	List<InventoryTransaction> ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		InventoryTransaction iTransaction = null;
		String transactiontype;		//��������
		String transstatus;			//����״̬
		String doctype;				//��֤����
		String docid;				//��֤ID
		String doclineno;			//��֤�к�
		String fmcargo_space_id;	//FM��λID
		String fmwh_area_id;		//FM����ID
		String fmcustomerid;		//FM�ͻ�ID
		String fmpackid;			//FM��װID
		String fmpunit;				//FM������λ
		String fmproductid;			//FM��ƷID
		String tocargo_space_id;	//TO��λID
		String towh_area_id;		//TO����ID
		String tocustomerid;		//TO�ͻ�ID
		String topackid;		//TO��װID
		String topunit;			//TO������λ
		String toproductid;		//TO��ƷID
		String totraycode;		//TO��������
		String injobid;			//��ҵID
		String injobetailid;		//��ҵ��ϸID
		String transactiontime;		//����ʱ��
		String createmanid;		//������
		String inventoryid;		//���ID
		double fmnum;			//FM�������
		double fmvolume;		//FM������
		double fmweight;		//FM�������
		double fmnetweight;		//FM��澻��
		double tonetweight;		//TO��澻��
		double toweight;		//TO�������
		double tonum;			//TO�������
		String reasoncode;		//ԭ�����
		String reason;			//ԭ��
		double tovolume;		//TO������
		String fmtraycode;		//FM��������
		String warehouseid;		//�ֿ�ID
	    
	    //�����ֶ�
	    String boxcode;            //������
	    String productcode;        //��Ʒ����

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 		iTransaction = ls.get(i);
  	 		warehouseid = iTransaction.getWarehouseid();				//�ֿ�ID
  	 		transactiontype = iTransaction.getTransactiontype();		//��������
  			transstatus = iTransaction.getTransstatus();			    //����״̬
  			doctype = iTransaction.getDoctype();						//��֤����
  			docid = iTransaction.getDocid();							//��֤ID
  			doclineno = iTransaction.getDoclineno();					//��֤�к�
  			fmcargo_space_id = iTransaction.getFmcargo_space_name();		//FM��λ��
  			fmwh_area_id = iTransaction.getFmwh_area_name();				//FM����ID
  			fmcustomerid = iTransaction.getFmcustomername();				//FM�ͻ�ID
  			fmpackid = iTransaction.getFmpackid();						//FM��װID
  			fmpunit = iTransaction.getFmpunit();						//FM������λ
  			fmnum = iTransaction.getFmnum();							//FM�������
  			fmproductid = iTransaction.getFmproductname();				//FM��ƷID
 			fmvolume = iTransaction.getFmvolume();						//FM������
  			fmweight = iTransaction.getFmweight();						//FM�������
  			tocargo_space_id = iTransaction.getTocargo_space_name();		//TO��λID
  			towh_area_id = iTransaction.getTowh_area_name();				//TO����ID
  			tocustomerid = iTransaction.getTocustomername();				//TO�ͻ�ID
  			topackid = iTransaction.getTopackid();						//TO��װID
  			topunit = iTransaction.getTopunit();						//TO������λ
  			toproductid = iTransaction.getToproductname();				//TO��ƷID
  			totraycode = iTransaction.getTotraycode();					//TO��������
  			injobid = iTransaction.getInjobid();						//��ҵID
  			injobetailid = iTransaction.getInjobetailid();				//��ҵ��ϸID
  			transactiontime = iTransaction.getTransactiontime();		//����ʱ��
  			createmanid = iTransaction.getCreatemanid();				//������
  			inventoryid = iTransaction.getInventoryid();				//���ID
  			fmnetweight = iTransaction.getFmnetweight();				//FM��澻��
  			tonetweight = iTransaction.getTonetweight();				//TO��澻��
  			toweight = iTransaction.getToweight();						//TO�������
  			tonum = iTransaction.getTonum();							//TO�������
  			reasoncode = iTransaction.getReasoncode();					//ԭ�����
  			reason = iTransaction.getReason();							//ԭ��
  			tovolume = iTransaction.getTovolume();						//TO������
  			fmtraycode = iTransaction.getFmtraycode();					//FM��������  
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=injobid%></td>
     <td class="TD_LIST"><%=transactiontime%></td>
     <td class="TD_LIST" align="center"><%=fmwh_area_id%></td>
     <td class="TD_LIST" align="center"><%=fmcargo_space_id%></td>
     <td class="TD_LIST" align="center"><%=fmproductid%></td>
     <td class="TD_LIST" align="center"><%=fmcustomerid%></td>
     <td class="TD_LIST" align="center"><%=fmtraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("fm"+lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>  
     <td class="TD_LIST" align="center"><%=fmnum%></td>
     <td class="TD_LIST" align="center"><%=towh_area_id%></td>
     <td class="TD_LIST" align="center"><%=tocargo_space_id%></td>
     <td class="TD_LIST" align="center"><%=toproductid%></td>
     <td class="TD_LIST" align="center"><%=tocustomerid%></td>
     <td class="TD_LIST" align="center"><%=totraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("to"+lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>
     <td class="TD_LIST" align="center"><%=tonum%></td>
     <td class="TD_LIST" align="center"><%=createmanid%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=16 + (iLine+iLine)%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
