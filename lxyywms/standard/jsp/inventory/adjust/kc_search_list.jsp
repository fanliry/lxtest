<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	function OnLoad1(){
		parent.RemoveLoading();
		parent.page.location.href="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?flag=Y" ;
	}
-->
</script>
</head>

<body onLoad="OnLoad1()">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ⵥ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���뵥��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ״̬</div></td>
   </tr>
<%
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
	    InventoryStorage storage = null;
	    BaseCargospace space = null;
		String whArea;				//����
 		String cargoSpace;			//��λ
 	 	String product;				//��Ʒ
 	 	String lotnumber;		    //����
 	 	String instockid;           //��ⵥ
 	 	String requestid;           //���뵥
 	 	String traycode;        	//��������
		String punit;				//��λ
		double pnum;            	//�������
		String indate;             	//���ʱ��
		String intype;             	//��ⷽʽ 1.���� 2.�ѻ�
        String productcode;         //��Ʒ����
        String printdate;         //��������
        String csstauts;         //���״̬
        String productstatus;     //��Ʒ״̬
        String values;
        String productid;
        String whAreaid;
        String cargoSpaceid;
        String inventoryid;  //���id
        String kcstauts;
        String lotid;
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 	    ob = (Object[])ls.get(i);
  	 		storage = (InventoryStorage)ob[0];
  	 		space = (BaseCargospace)ob[1];
  	 		whAreaid = storage.getWhAreaId();
  	 		cargoSpaceid = storage.getCargoSpaceId();
  	 		whArea = storage.getWhAreaName();		//����
  	 		cargoSpace = storage.getCargoSpaceName();//��λ
  	 		product = storage.getProductName();		//��Ʒ
  	 		lotnumber = storage.getLotinfo();		//����
  	 		traycode = storage.getTraycode();		//��������
  	 		punit = storage.getPunitname();			//��λ
  	 		productcode = storage.getProductcode(); //��Ʒ����		
			pnum = storage.getPnum();           //�������
			indate = storage.getIndate();       //���ʱ��
  	 		intype = storage.getIntype();		//��ⷽʽ 1.���� 2.�ѻ�
  	 		requestid = storage.getRequestid();
  	 		instockid = storage.getInstockid();
  	 		productid = storage.getProductid();
  	 		printdate = storage.getProductdate(); //��������
  	 		csstauts = space.getCsstatusname(); 
  	 		productstatus = storage.getProductStatusName();
  	 	    punit = storage.getPunit();
  	 	    inventoryid = storage.getInventoryid();
  	 	    kcstauts = storage.getStatusName();
  	 	    lotid = storage.getLotid();
  	 		values=  whArea+"|"+cargoSpace+"|"+product+"|"+printdate+"|"+punit+"|"+lotid+"|"+lotnumber+"|"+traycode+"|"+pnum+"|"+inventoryid+"|"+productid;
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
  <td class="TD_LIST1" align="center"> <input type="checkbox" name="checkbox_id" value="<%=values%>" class="input_checkbox" id="checkbox_id">
     <%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace == null ? "" : cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=kcstauts == null ? "" : kcstauts%></td>
     
     <td class="TD_LIST" align="center"><%=instockid == null ? "" : instockid%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=indate == null ? "" : indate%></td>
     <td class="TD_LIST" align="center"><%=lotnumber == null ? "" : lotnumber%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
     <td class="TD_LIST" align="center"><%=csstauts == null ? "" : csstauts%></td>
     <td class="TD_LIST" align="center"><%=requestid == null ? "" : requestid%></td> 
     <td class="TD_LIST" align="center"><%=productstatus == null ? "" : productstatus%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=13%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
