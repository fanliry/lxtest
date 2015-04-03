<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryAdjustDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.ricosoft.common.dao.dataSource.EntityDAO" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<%@ page import="com.wms3.bms.standard.service.BMSService" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%
	HashMap<String, List> hsLot = (HashMap<String, List>)session.getAttribute("viewLot");
	List lsLot = hsLot.get("adjustkc");
%>
<html>
<head>
<title>�ִ����͹���ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
  <script type="text/javascript">
  <!--
	 //ȫѡ
    function selectAll()
	{
		var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
			if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_all'){
				 state = document.myform.elements[i].checked;
				 break;
			}
		}
		for(i=0;i<length;i++){
			 if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_id'){
				   document.myform.elements[i].checked=state;
			 }
		}
	}
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	}
  -->
  </script>
</head>

<body onload="javascript:OnLoad();">
<%
	List list = null;
	if(request.getAttribute("exResultList") != null)
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
  <table width="" height="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_table">
	<tr class="list_title_tr">
	  <td width="40" class="TD_LIST_TITLE"><div class="list_title">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll()"></div>
	  </td>
	  <td width="100" class="TD_LIST_TITLE"><div class="list_title">�ͻ�</div></td>
	  <td width="120" class="TD_LIST_TITLE"><div class="list_title">��Ʒ</div></td>
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
	  <td width="80" class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">���</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">ë��</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">����</div></td> 
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    EntityDAO dao = BMSService.getm_EntityDAO(); 
    
    
        //����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();        
		nbf.setMinimumFractionDigits(2); 
		String lotatt;  			//��������
        for(int i=0; i<list.size(); i++)
        {
           InventoryStorage inventory   = (InventoryStorage)list.get(i);
           String strId = inventory.getInventoryid()!=null?inventory.getInventoryid():"";//ID
           String strCustomerId = inventory.getOwnerId();//�ͻ�ID
           String strSkuId = inventory.getProductid()!=null?inventory.getProductid():"";//��ƷID
           String strLocId = inventory.getCargoSpaceId()!=null?inventory.getCargoSpaceId():"";//��λID
           int qty = (int)inventory.getPnum();//�������
           double qtyAllocated = (double)inventory.getAssignnum();//��������
           int qtyHold = (int)inventory.getHoldnum();//��������
           String cubic = nbf.format(inventory.getPvolume());//���
           String grossweight = nbf.format(inventory.getPweight());//ë��
           String netweight = nbf.format(inventory.getPnetweight());//����
            
           String strSkuName = inventory.getProductName()!=null?inventory.getProductName():""; //��Ʒ��
           String strCustomerName = inventory.getOwnerName();//�ͻ���
           
            double qtyUsable = qty - qtyAllocated - qtyHold;//��������  
            String strUnit = inventory.getPunit();//��λ
            String whAreaName = inventory.getWhAreaName()!=null?inventory.getWhAreaName():"";
            String traycode = inventory.getTraycode()!=null?inventory.getTraycode():"";
            
           String strParam = strId+"|"+whAreaName+"|"+strSkuId+"|"+strSkuName+"|"+strLocId+"|"+traycode+"|"+qty+"|"+qtyAllocated+"|"+qtyHold+"|"+qtyUsable+"|"+cubic+"|"+grossweight+"|"+strUnit+"|"+netweight;			
%>	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1">
	    <input type="checkbox" name="checkbox_id" value="<%=strId%>" class="input_checkbox" id="<%=strParam%>">
	  </td>
	  <td class="TD_LIST1"><%=strCustomerName == null ? "" : strCustomerName%></td>
	  <td class="TD_LIST1"><%=strSkuName == null ? "" : strSkuName%></td>
	   <%
	if(lsLot != null){
		for(int k = 0; k < iLine; k++){
			lotatt = "";		//��������
			lotSet = (BaseLotSet)lsLot.get(k);
			if(lotSet!=null && lotSet.getLotid()!=null && lotSet.getLotid().length()>=7){
			    switch(Integer.parseInt(lotSet.getLotid().substring(6, lotSet.getLotid().length())))
				{
					 case 1:lotatt=inventory.getLotatt1();break;
					 case 2:lotatt=inventory.getLotatt2();break;
					 case 3:lotatt=inventory.getLotatt3();break;
					 case 4:lotatt=inventory.getLotatt4();break;
					 case 5:lotatt=inventory.getLotatt5();break;
					 case 6:lotatt=inventory.getLotatt6();break;
					 case 7:lotatt=inventory.getLotatt7();break;
					 case 8:lotatt=inventory.getLotatt8();break;
					 case 9:lotatt=inventory.getLotatt9();break;
					 case 10:lotatt=inventory.getLotatt10();break;
					 case 11:lotatt=inventory.getLotatt11();break;
					 case 12:lotatt=inventory.getLotatt12();break;
				}
			}			
%>
	 		 <td class="TD_LIST" align="center"><%=lotatt!=null?lotatt:""%></td>
<%
		}
	}
%>
	  <td class="TD_LIST1"><%=strLocId == null ? "" : strLocId%></td>
	  <td class="TD_LIST1"><%=qty%></td>
	  <td class="TD_LIST1"><%=qtyAllocated%></td>
	  <td class="TD_LIST1"><%=qtyHold%></td>
	  <td class="TD_LIST1"><%=cubic%></td>
	  <td class="TD_LIST1"><%=grossweight%></td>
	  <td class="TD_LIST1"><%=netweight%></td>
	  
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
	<tr height="100%">
	  <td height="100%" colspan="<%=10 + iLine%>" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
