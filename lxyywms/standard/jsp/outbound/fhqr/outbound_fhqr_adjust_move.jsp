<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.DecimalFormat" %>
<%
	//����С��2λ
	//NumberFormat nbf=NumberFormat.getNumberInstance();     
	//nbf.setMinimumFractionDigits(2);
	//nbf.setMaximumFractionDigits(2); 
	
	DecimalFormat nbf =new DecimalFormat("#0.00"); 
	InoutJob job = null;
	InoutJobdetail jobDetail = null;
	if(request.getAttribute("job") != null){
		job = (InoutJob)request.getAttribute("job");
	}
	if(request.getAttribute("jobDetail") != null){
		jobDetail = (InoutJobdetail)request.getAttribute("jobDetail");
	}
	String strDetailId = null;		// ��ҵ��ϸID
	String strTrayCode = null;		// ����
	String strBoxCode = null;		// ������
	String strProductCode = null;	// ��Ʒ����
	String strProductName = null;	// Ʒ��
	String lotinfo = "";//�������
	String printdate = "";//��������
	double fPNum =0;					// ����
	if(job != null){
		strTrayCode = job.getTraycode();		// ����
	}
	if(jobDetail != null){
		strDetailId = jobDetail.getJobdetailid();		 // ��ҵ��ϸID
	 	strProductName = jobDetail.getM_strProductName();// ��Ʒ��
	 	strProductCode = jobDetail.getProductcode();	 // ��Ʒ����
	 	lotinfo = jobDetail.getLotinfo();
	 	printdate = jobDetail.getPrintdate();
	 	if((jobDetail.getJobnum()-jobDetail.getAssignnum()) == 0)//��ҵ���������������ͬ
	 	{
	 		fPNum = jobDetail.getJobnum();			// ����
	 	}else{
		  	fPNum = jobDetail.getJobnum()-jobDetail.getAssignnum();			// ����
	 	}
	}
%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>

<script type="text/javascript">
<!--

	//ȷ��
	function Ok(detailid){
		var num = document.getElementById("num").value;				//����
		var backmsg = "&num=" + num;
		window.returnValue = backmsg;
		window.close();
	}
	
-->
</script>
</head>

<body>

 <table width="98%" height="27" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">��ǰ���������ݵ���</td>
   </tr>
 </table>
     
 <table id="tab1" width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr> 
     <td class="TD_ADD"><div align="right">���̣�</div></td>
     <td class="TD_ADD"><input type="text" name="traycode" value="<%=strTrayCode == null ? "" : strTrayCode%>" style="width:150px;background-color: #EDDFFD;" readonly></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">Ʒ����</div></td>
     <td class="TD_ADD"><input type="text" name="packaging_name" value="<%=strProductName == null ? "" : strProductName%>" style="background-color: #EDDFFD;width:150px;" readonly></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">�������룺</div></td>
     <td class="TD_ADD"><input type="text" name="lotinfo" value="<%=lotinfo == null ? "" : lotinfo%>" style="background-color: #EDDFFD;width:150px;" readonly></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">�������ڣ�</div></td>
     <td class="TD_ADD"><input type="text" name="printdate" value="<%=printdate == null ? "" : printdate%>" style="background-color: #EDDFFD;width:150px;" readonly></td>
   </tr>
   </table>
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100"><div align="right">������</div></td>
     <td class="xx1">
     	<input type="text" name="num" size="24" value="<%=nbf.format(fPNum)%>" style="width:100px;" >
     </td>
   </tr>
   <tr>
     <td  align="center" colspan="2">
       <input type="button" name="save" class="button" onClick="Ok('<%=strDetailId%>')" value="ȷ��">
       <input type="button" name="cancel" class="button" onClick="window.close()" value="ȡ��">
     </td>
   </tr>
 </table>

</body>
</html>