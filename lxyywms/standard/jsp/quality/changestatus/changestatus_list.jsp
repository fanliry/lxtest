<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.HashMap"%>
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
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//��ѯ��ϸ
	function queryDetail(i){
		//var stokeys = document.getElementsByName("check_id");
		showModalDialog(strUrl + "inventoryQualityAction&flag=2" + i, "", "dialogWidth=1200px;dialogHeight=500px;");
	}
	
	function Change(obj)
	{
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
	
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
     <td class="TD_LIST_TITLE1" width="30"><div class="list_title">ѡ��</div></td>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���뵥��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
	    Object[] ob = null;
	    String warehouseid;	//�ֿ�ID
        String warehouseName;       //�ֿ�
        String whAreaID;	//����ID
		String whArea;				//����
		String productID;	//��ƷID
		String productCode;//��Ʒ����
 	 	String productName;				//��Ʒ��
 	 	String requestid;		    //���뵥��
 	 	String lotInfo;        	//����
		
		String productStatus;	    //��Ʒ״̬
		String productStatusName;//��Ʒ״̬��
		double pnum;            	//�������
	    String value;
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{ 	 	  
 	 	    ob = (Object[])ls.get(i);
 	 	    warehouseid = ob[0] == null ? "" : ob[0].toString(); //�ֿ�ID
 	 	    warehouseName = ob[1] == null ? "" : ob[1].toString(); //�ֿ�
 	 	    whAreaID = ob[2] == null ? "" : ob[2].toString();//����id
 	 	    whArea = ob[3] == null ? "" : ob[3].toString();			//����
 	 	    requestid = ob[4] == null ? "" : ob[4].toString();		//���뵥
 	 	    lotInfo = ob[5] == null ? "" : ob[5].toString();		//����
 	 	    
 	 	    productID = ob[6] == null ? "" : ob[6].toString();	//��ƷID
 	 	    productCode = ob[7] == null ? "" : ob[7].toString();		//��Ʒ
 	 	  	productName = ob[8] == null ? "" : ob[8].toString();		//��Ʒ
 	 	    
  	 		productStatus = ob[9] == null ? "" : ob[9].toString();		//��Ʒ״̬	
  	 		productStatusName= ob[10] == null ? "" : ob[10].toString();		//��Ʒ״̬��	
  	 		pnum = ob[11] == null ? 0.0 : Double.parseDouble(ob[11].toString());		//�������
  	 		
  	 		
  	 		//�ֿ�id|����id|���뵥id|����|��Ʒid|��Ʒ״̬
  	 		value = "&warehouseid=" + warehouseid + "&whareaid=" + whAreaID +"&requestid="+ requestid  +"&lotnumber=" + lotInfo 
  	 		+"&productid=" + productID  +"&productstatus=" + productStatus;
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ondblclick="queryDetail('<%=value%>')">
     <td class="TD_LIST" align="center"><input onClick="Change(this)" type="checkbox" name="check_id" class="input_checkbox" value="<%=value%>"/></td>
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseName%></td>
     <td class="TD_LIST" align="center"><%=whArea%></td>
     <td class="TD_LIST" align="center"><%=requestid%></td>
     <td class="TD_LIST" align="center"><%=lotInfo%></td>
     <td class="TD_LIST" align="center"><%=productCode%></td>
     <td class="TD_LIST" align="center"><%=productName%></td>
     <td class="TD_LIST" align="center"><%=productStatusName%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=9%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
