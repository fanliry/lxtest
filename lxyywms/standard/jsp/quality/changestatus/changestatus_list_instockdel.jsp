<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail"%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

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
     <td class="TD_LIST_TITLE"><div class="list_title">��ⵥ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">ʵ������</div></td>

   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){ 
		
    	String instockid;  	    //��ⵥ
    	String product;       	//��Ʒ����
    	String traycode;        //��������
    	String lotnumber;        //����
        double innum;			    //�������
        double getnum;			    //ʵ������
     	
     	//С��
		double pnum_sum = 0;	// ����
		InboundDetail indDetail = null;
		for(int i = 0; i < ls.size(); i++){
			
			indDetail = (InboundDetail)ls.get(i);
			
			instockid = indDetail.getInstockid() == null ? "":indDetail.getInstockid();      //��ⵥ
		    product = indDetail.getProductName() == null ? "" : indDetail.getProductName();       	//��Ʒ����
	    	traycode = indDetail.getTraycode() == null ? "" : indDetail.getTraycode();        //��������
	    	lotnumber = indDetail.getLotinfo() == null ? "" : indDetail.getLotinfo();;        //��������
	        innum = indDetail.getInnum();			    //�������
	        getnum = indDetail.getGetnum();			    //ʵ������
     		
     		//С��
			pnum_sum += getnum;			// ����
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ondblclick="queryDetail('<%=i%>')">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=product%></td>
     <td class="TD_LIST" align="center"><%=traycode%></td>
     <td class="TD_LIST" align="center"><%=lotnumber%></td>   
     <td class="TD_LIST" align="center"><%=innum%></td>
     <td class="TD_LIST" align="center"><%=getnum%></td>
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
     <td class="TD_LIST" align="center" style="color: red;"><%=(int)pnum_sum%></td>
   </tr>
<%
	}
%>  
   <tr>
     <td height="100%" colspan="7" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>