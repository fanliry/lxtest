<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script>
<script type="text/javascript">
<!--
	var obj1 = null;
	function setOnBackground(obj)
	{
		obj.style.backgroundColor = "#33FF33";
		if(obj1 != null)
		{
			obj1.style.backgroundColor ="";			
		}
		obj1 = obj; 	
	}
	function OnLoad(){

		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}
-->
</script>
</head>
<body  onLoad="OnLoad()">
 <table width="100%"   id="table" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="30"><div class="list_title">�к�</div></td>
     <!-- <td class="TD_LIST_TITLE" align="center"><div class="list_title">��ҵ��ϸ</div></td> -->
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��ҵ״̬</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��ҵ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�Ƿ񸴺�</div></td>  
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
   </tr>
<%
	if (ls != null && ls.size()>0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();     
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		Object[] obj = null;
      	InoutJob job = null;			// ��ҵ 
		InoutJobdetail jobDetail = null;// ��ҵ��ϸ  
		String strJobDetailId = "";		// ��ҵ��ϸID
		String linestatus = "";			// ��״̬
		String strJobId = "";			// ��ҵ��
		String strCargoAreaName = "";	// ����
		String strCargoSpaceName = "";	// ��λ
		String strProductName = "";;	// ��Ʒ
		String isFH = "";	//�Ƿ񸴺� 
		String strTrayCode = "";		// ��������
		double fPNum =0;				// ����
		double fAssPNum =0;				// ��������	
		for (int i=0; i<ls.size(); i++) 
		{
 			obj = (Object[])ls.get(i);
 			job = (InoutJob)obj[0];							// ��ҵ 
		    jobDetail = (InoutJobdetail)obj[1];				// ��ҵ��ϸ	
			strJobDetailId = jobDetail.getJobdetailid();	// ��ҵ��ϸID
		 	linestatus = job.getStatusName();			    // ��ҵ״̬
		 	strJobId = job.getJobid();						// ��ҵ��ID
		 	strCargoAreaName = job.getScargoWhareaName();	// ����
		 	strCargoSpaceName = job.getScargoSpaceName();	// ��λ
		 	strProductName = jobDetail.getM_strProductName();// ��Ʒ��
		 	isFH = jobDetail.getIsreview();	//	�Ƿ񸴺�
		 	strTrayCode = job.getTraycode();				 // ��������
		 	fPNum = jobDetail.getJobnum();					 // ����
		 	fAssPNum = jobDetail.getAssignnum();			 // ��������	  	
%>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" onmousedown="setOnBackground(this);">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <!--<td class="TD_LIST" align="center"><%=strJobDetailId == null ? "" : strJobDetailId%></td>-->
     <td class="TD_LIST" align="center"><%=linestatus == null ? "" : linestatus%></td>
     <td class="TD_LIST" align="center"><%=strJobId == null ? "" : strJobId%></td>
     <td class="TD_LIST" align="center"><%=strCargoAreaName == null ? "" : strCargoAreaName%></td>
     <td class="TD_LIST" align="center"><%=strCargoSpaceName == null ? "" : strCargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=strProductName == null ? "" : strProductName%></td>
     <td class="TD_LIST" align="center"><%=isFH == null ? "" : isFH%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(fPNum)%></td>
     <td class="TD_LIST" align="center" <%=fAssPNum != fPNum ? "style='background-color:#AABB45' " : ""%>><%=nbf.format(fAssPNum)%></td>
     <td class="TD_LIST" align="center"><%=strTrayCode == null ? "" : strTrayCode%></td>
   </tr>
<%			
		}

	}
%>
   <tr>
     <td height="100%" colspan="10"  valign="top">
       <div style="color: red; font-size:12;margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
     
</body>
</html>
