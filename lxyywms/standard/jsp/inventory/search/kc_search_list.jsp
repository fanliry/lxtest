<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>
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
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:120%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table  id="theTable" width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�߼�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ/���״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ״̬</div></td>
   </tr>
<%
	
	
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
	    InventoryStorage storage = null;
	    BaseCargospace space = null;
		String whArea;				//����
 		String cargoSpace;			//��λ����
 		String cargoSpaceName;		//��λ��
 	 	String product;				//��Ʒ
 	 	String lotinfo;		    	//�������
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
        
        String Virtualwhname = "";
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 	    ob = (Object[])ls.get(i);
  	 		storage = (InventoryStorage)ob[0];
  	 		space = (BaseCargospace)ob[1];
  	 		whArea = storage.getWhAreaName();		//����
  	 		cargoSpace = storage.getCargoSpaceId(); //��λ����
  	 		cargoSpaceName = storage.getCargoSpaceName();//��λ��
  	 		product = storage.getProductName();		//��Ʒ
  	 		lotinfo = storage.getLotinfo();		//�������
  	 		traycode = storage.getTraycode();		//��������
  	 		punit = storage.getPunitname();			//��λ
  	 		productcode = storage.getProductcode(); //��Ʒ����		
			pnum = storage.getPnum();           //�������
			indate = storage.getIndate();       //���ʱ��
  	 		intype = storage.getIntype();		//��ⷽʽ 1.���� 2.�ѻ�
  	 		requestid = storage.getRequestid();
  	 		instockid = storage.getInstockid();
  	 		
  	 		printdate = storage.getProductdate(); //��������
  	 		csstauts = space.getCsstatusname()+"/"+storage.getStatusName();
  	 		
  	 		productstatus = storage.getProductStatusName();
  	 		traycode = storage.getTraycode();
  	 		
  	 		Virtualwhname = storage.getVirtualwhname();
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=Virtualwhname == null ? "" : Virtualwhname%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace == null ? "" : cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceName == null ? "" : cargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=indate == null ? "" : indate%></td>
     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
     <td class="TD_LIST" align="center"><%=csstauts == null ? "" : csstauts%></td>
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
     <td height="100%" colspan="<%=16%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
