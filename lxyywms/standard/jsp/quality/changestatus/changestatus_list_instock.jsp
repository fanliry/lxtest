<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//��ѯ��ϸ
	function queryDetail(ids){
		showModalDialog(strUrl + "inventoryQualityAction&flag=3&instockid=" + ids, "", "dialogWidth=1200px;dialogHeight=400px;");
	}
	
	function OnLoad(){
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">

<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ⵥ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){ 
		
		String warehouseid;      //�ֿ�ID
		String wharea;      	//����
    	String instockid;  	    //��ⵥ
    	String product;       	//��Ʒ
    	String productstandard; //��Ʒ���
    	String productstatus;        //��Ʒ״̬
    	double pnum;            //����
    	String productid;
    	String key;
     	//С��
		double pnum_sum = 0;	// ����
		Object[] ob = null;
		for(int i = 0; i < ls.size(); i++){
			
			ob = (Object[])ls.get(i);
			
			warehouseid = ob[0] == null ? "":ob[0].toString();      //�ֿ�ID
			wharea = ob[1] == null ? "":ob[1].toString();      	//����
	    	instockid = ob[2] == null ? "":ob[2].toString();  	    //��ⵥ
	    	product = ob[3] == null ? "":ob[3].toString();       	//��Ʒ
	    	productstandard = ob[4] == null ? "":ob[4].toString(); //��Ʒ���
	        productstatus = ob[5] == null ? "":ob[5].toString();        //��Ʒ״̬
	    	pnum = ob[6] == null ? 0.0:Double.parseDouble(ob[6].toString());            //����
	    	productid = ob[7] == null ? "":ob[7].toString();       	//��Ʒ
     		key = instockid +"&productid="+ productid;
     		//С��
			pnum_sum += pnum;			// ����
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ondblclick="queryDetail('<%=key%>')">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseid%></td>
     <td class="TD_LIST" align="center"><%=wharea%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=product%></td>   
     <td class="TD_LIST" align="center"><%=productstandard%></td>
     <td class="TD_LIST" align="center"><%=productstatus%></td>
     <td class="TD_LIST" align="center"><%=(int)pnum%></td>
   </tr>
<%
		}
%>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;">��ҳС��</td>
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST1" align="center"></td>  
	 <td class="TD_LIST" align="center"></td>
	 <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=(int)pnum_sum%></td>
   </tr>
<%
	}
%>  
   <tr>
     <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>