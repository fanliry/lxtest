	<%@ page contentType="text/html; charset=GB2312"%>
	<%@ page import="com.ricosoft.common.tools.StrTools" %>
	<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
	<%@page import="java.util.List" %>
	<%@page import="java.util.ArrayList"%>
	<%
		String strWarehouseId =(String)request.getAttribute("strWarehouseId");
		String ids = (String)request.getAttribute("ids");
		String strTime = StrTools.getCurrDateTime(5);
		
	%>
	<html>
	<head>
	<title>��ϸ</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript">
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	function viewLot(lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){
		
			var tableObj = document.getElementById("tab1");

			//2�ǲ�ѯ����������, 4��ÿ����ʾ��td��
			createLotDetail1(tableObj, lotid, 4, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);	
		}
	
	
	function saveData(ids)
	{
		
		var strParam = strUrl+"inventoryMoveAction&&method=ricoExecAdd&flag=4" 
        					+ "&ids="+ids      //ID
        					+"&strTime="+"<%=strTime%>"
        					+"&warehouseid="+"<%=strWarehouseId%>"
        					+ "&toWarehouseid="+document.getElementById("warehouseid").value  //����ֿ�ID
        					+ "&toWhAreaId="+document.getElementById("whAreaId").value  //�������Id
        					+ "&toCargospaceId="+document.getElementById("cargospace_id").value  //����ֿ�����
        					+ "&toCargospaceName="+document.getElementById("cargospace_name").value  //�����λ
        					+ "&reasoncode="+document.getElementById("reasoncode").value  //�ƿ�ԭ��
        					+ "&remark="+document.getElementById("remark").value; //��ע
        window.location.href=strParam;
		window.close();
	}
	function checkDetailData(qty)
	{
         	
       	//��������
		var qtyHold = document.getElementById("qtyHold").value;
		if(qtyHold > 0)
		{
			alert("����ȡ�����������ƶ�!");
       		return false; 
		}
		var toqty = document.getElementById("toqty").value;
		if(toqty == "" || toqty.length <1)  
       	{
       		alert("���ƿ�����������Ϊ�գ�");
       		return false; 
       	}
       	if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("���ƿ���������������������!");
			return false;
		}
		if(!parseFloat(toqty) > 0)
       	{
       		alert("���ƿ�����������Ϊ��!");
			return false;
       	}
       	if(parseFloat(toqty) > parseFloat(qty))
       	{
       		alert("���ƿ����������ܴ��ڡ����������!");
			return false;
       	}
       	var toLocId = document.getElementById("locid").value;
		if(toLocId == "" || toLocId.length <1)  
       	{
       		alert("���ƿ��λ������Ϊ�գ�");
       		return false; 
       	}
       	//ԭ��λ��Ŀ���λ������ͬ
		var locid = document.getElementById("cargospace_id").value;
		if(locid == toLocId)
       	{
       		alert("��ԭ��λ���롾Ŀ���λ��������ͬ!");
			return false;
       	}
		
		return true;
	}
	
	//���ݲֿ��ȡ�ݴ���
	function getZCArea(){
		var warehouseid = document.getElementById("warehouseid").value;
		selectZCAreaList("whAreaId", warehouseid);
	}
	
	
	
	function onload(){
		
		var fromWarehouseid ="<%=strWarehouseId%>" ;
		
			//�ֿ�
		DWREngine.setAsync(false);
		selectObject(fromWarehouseid, "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectType('', 'reasoncode', 'ykyy');			//����
		
		selectZCAreaList("whAreaId", warehouseid);
	}
	</script>
</head>


	<body onload="javascript:onload();">
	<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �����ƶ� -&gt; �����ƶ���ϸ</div></td>
	   </tr>
   </table>	
   <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
	   <tr><td></td></tr>
   </table>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƴ��ֿ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƴ�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƴ���λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƶ�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
   </tr>
	<%  
		String userCode = (String)request.getSession(false).getAttribute("userCode");
		String userName = (String)request.getSession(false).getAttribute("userName");
		
		
		
		InventoryStorage invent = null; 
		List<InventoryStorage> lsIstorage = null;
		if(request.getAttribute("inventory") != null)
		{
			lsIstorage = (List<InventoryStorage>)request.getAttribute("inventory");  
		}
		
		String strId = "";//ID
		String strCustomerId = "";    //�ͻ�
		String  strWareId  = "";      //�ֿ�ID
		String  strWarehouse = ""; //�ֿ�����
		String strWhAreId = "";	//����Id
		String  strWhAreName  = "";   //����
		String strSkuId = "";         //��Ʒid
	    String strSkuName = "";       //��Ʒ�������� 
	    String strProductDate = "";		//��Ʒ��������
		String strLotnum = "";        //����
		String strLocId = ""; //��λID
		String strLocName = "";         //��λ
		String strTraceId = "";       //��������
		int qty = 0;                  //�������
		int qtyHold = 0;              //��������
		int qtyAllocated = 0;         //��������
		double cubic = 0.0;           //���
	    double grossweight = 0.0;     //ë��
	    String strPackUnit = "";      //��λ
	    int qtyUsable = 0;            //��������  
	
		if(lsIstorage != null)
		{
			for(int i=0;i<lsIstorage.size();i++){
			invent = lsIstorage.get(i);
			
			strId = invent.getInventoryid();             //���ID
			strWareId = invent.getWarehouseid();         //�ֿ�ID
			strWarehouse =invent.getWarehouseName();	//�ֿ�����
			strWhAreId = invent.getWhAreaId();//����id
			strWhAreName  = invent.getWhAreaName();      //����
			strCustomerId = invent.getOwnerId();         //�ͻ�
			strSkuId = invent.getProductid();            //��Ʒid
			strSkuName = invent.getProductName();        //��Ʒ�������� 
			strProductDate = invent.getIndate();	//��Ʒ��������
		   
			strLotnum = invent.getLotinfo();              //����
			strLocId = invent.getCargoSpaceId();//��λID
			strLocName = invent.getCargoSpaceName();       //��λ
			strTraceId = invent.getTraycode();           //��������
			qty = (int)invent.getPnum();                 //�������
			qtyHold = (int)invent.getHoldnum();          //��������
			qtyAllocated = (int)invent.getAssignnum();   //��������
			cubic = invent.getPvolume();                 //���
		    grossweight = invent.getPweight();           //ë��
		    strPackUnit = invent.getPunitname();         //����λ
		    qtyUsable = qty - qtyAllocated - qtyHold;;   //��������  
	%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=strWarehouse==null?"":strWarehouse%></td>
     <td class="TD_LIST" align="center"><%=strWhAreName==null?"":strWhAreName%></td>
     <td class="TD_LIST" align="center"><%=strLocName==null?"":strLocName%></td>
     <td class="TD_LIST" align="center"><%=strTime==null?"":strTime%></td>
     <td class="TD_LIST" align="center"><%=strTraceId==null?"":strTraceId%></td>
     <td class="TD_LIST" align="center"><%=strPackUnit==null?"":strPackUnit%></td>
     <td class="TD_LIST" align="center"><%=strLotnum==null?"":strLotnum%></td>
     <td class="TD_LIST" align="center"><%=userName==null?"":userName%></td>
     <td class="TD_LIST" align="center"><%=strProductDate==null?"":strProductDate%></td>
   </tr>
   <%
		}
			
	}else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(lsIstorage != null && lsIstorage.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
</table>
	
	 <!-- ======== ��ϸ��Ϣ��ʼ ======== -->
	
	 <!-- ======== ��ϸ��Ϣ���� ======== -->
	<table width="98%" border="0" height="10"  align="center" cellpadding="0" class="x3"> 
    <tr><td></td></tr>
    </table>  
	 <!-- ======== ��ϸ��Ϣ��ʼ ======== -->
     
	 <form id="myform1" name="myform1" method="post">
	 <table width="98%" border="0" align="center" cellpadding="0" class="table_add">
	  <!--   <tr>
     		<td height="15" colspan="6" class="yx"><input type="hidden" name="lotid"/></td>
   	   </tr>-->
	   <tr>
	   	<td width="100" align="right" class="yx1" >����ֿ⣺</td>
	     <td  class="yx">
	     	<select name="warehouseid" onChange="getZCArea()" style="width:117px;" disabled="disabled"><option value=""></option></select>
	     </td>
	     <td width="100" align="right" class="yx1">���������</td>
	     <td class="yx">
		    <select id="whAreaId" style="width:117px;" disabled="disabled"><option value=""></option></select>
          </td>
           <td width="100" align="right" class="yx1" >�����λ��</td>
	     <td  class="xx1">
	     	<input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="20" readonly  style="height:18px;width:100px";>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/inventory/move/kc_cargospace.jsp?warehouseid=<%=strWareId %>','850','550');" 
       			type="button" value="��" class="button_select">
	     </td>
	    
	   </tr>
	   <tr >
	    
	     <td width="100" align="right" class="yx1">�ƿ�ԭ��</td>
	     <td class="yx">
			<select name="reasoncode">
		         <option value="">--��ѡ��--</option>
		    </select>
		</td>
		<td width="100" align="right" class="yx1">�ƿ�ʱ�䣺</td>
	     <td class="xx1"><input type="text" name="moveTime" size="20" maxlength="12" value="<%=strTime == null ? "" : strTime%>" readonly class="input_readonly"></td>
	     
	     <td width="100" align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
	     <td class="xx1"><input type="text" name="remark" size="20" maxlength="12"></td>
	   </tr>

	 <tr>
        	<TD colspan="6" height="10" class="yx">
        		<input type="hidden" name="invoicedetailid" />
        	</TD>
     </tr>
	 <tr>
	     <td height="27" colspan="8" align="center" >
	        <input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;�ƶ�" onClick="saveData('<%=ids%>')"/><!-- onClick="if(checkDetailData('<%=qty%>')!=false){saveData('<%=strId%>');}" -->
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
         </td>
    </tr>
	 </table>	
	 </form>
	
	 <!-- ========  ======== -->

	</body>
	</html>