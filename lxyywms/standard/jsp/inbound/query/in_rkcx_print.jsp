<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
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
<title>��ⵥ��ӡ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
	
	var HKEY_Root,HKEY_Path,HKEY_Key;
	HKEY_Root="HKEY_CURRENT_USER";
	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	
	//������ҳ��ӡ��ҳüҳ��Ϊ��
	function PageSetup_Null()     
	{
		try
		{
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}
		catch(e){}
	}
	//������ҳ��ӡ��ҳüҳ��ΪĬ��ֵ
	function PageSetup_Default()
	{
	  	try
	    {
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&bҳ00��,&p/&P");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
	    }     
		catch(e){}
	}
	
	
	
	//��Excel����
	function openexcel()
	{
		
		
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		
		var classname = "com.wms3.bms.standard.action.report.inbound.inbound_print_rkQueryExcel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=����ѯ.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
		document.tempForm1.submit();
		
	}
-->
</script>
<style media="print">
<!--
	.Noprint{display:none;}<!--�ñ���ʽ�ڴ�ӡʱ���طǴ�ӡ��Ŀ-->
	.PageNext{page-break-after: always;}<!--���Ʒ�ҳ-->
-->
</style> 
<style type="text/css">
<!--
	.style2 {
		font-size: 24px; 
		font-weight: bold; 
		font-family: "����_GB2312";
		color: #000000;
	}
	.style2 {
		font-weight: bold; 
		color: #000000;
	}
-->
</style>
</head>

<body >

 <!-- ======== ���ܰ�ť��ʼ ======== -->
 <form id="myform1" name="myform1" method="post">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="Noprint">
   <tr height="30">
     <td><div align="left">
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       <input type="button" name="readyBtn" value="excel����" class="BUTTON_STYLE1" onclick="openexcel();">&nbsp;
       <input type="button" name="readyBtn" value="��ӡԤ��" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="ҳ������" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="��ӡ" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="�ر�" class="BUTTON_STYLE1" onclick="window.close()">
       
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== ���ܰ�ť���� ======== -->
 
 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;ҩ&nbsp;ҵ&nbsp;(&nbsp;��&nbsp;��&nbsp;)&nbsp;��&nbsp;��&nbsp;��&nbsp;˾</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">����ѯ</div></td>
   </tr>
   <tr>
   	<td colspan="4"><div align="center" id="img"></div></td>
   </tr>
   
  
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== �����б�ʼ ======== -->
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
     <td class="list_title_td" align="center" width="50">�к�</td>
     <td class="list_title_td" align="center">��ҵ��</td>
     <td class="list_title_td" align="center">��ҵ����</td>
     <td class="list_title_td" align="center">��Ʒ����</td>
      <td class="list_title_td" align="center">Ʒ��</td>
     <td class="list_title_td" align="center">��������</td>
     <td class="list_title_td" align="center">��������</td>
     <td class="list_title_td" align="center">��λ</td>
     <td class="list_title_td" align="center">����</td>
     <td class="list_title_td" align="center">�ֿ�</td>
    <td class="list_title_td" align="center">���</td>
     <td class="list_title_td" align="center">��λ</td>
     <td class="list_title_td" align="center">��������</td>
     <td class="list_title_td" align="center">���ͻ���</td>
     <td class="list_title_td" align="center">����ʱ��</td>
     <td class="list_title_td" align="center">���ʱ��</td>
     <td class="list_title_td" align="center">��ҵ��Դ</td>
   </tr>
<%
	if(ls != null && ls.size()>0){
		
		Object[] obj = null;
		InoutJob job = null;
		InoutJobdetail detail = null;
     	
     	String jobid;      	//��ҵ��
     	String jobtype;    //��ҵ���� ����������ҵ����Ϊ ������͵�hlֵ��
     	String productCode;//��Ʒ����
     	String m_strProductName = null;	 //Ʒ��
		String lotinfo = null;	         //��������
		String printdate = null;	     //��������
		String m_strUnitName = null;	//��λ
		double jobnum = 0;			    //����
		String warehouseName;  	//�ֿ�
     	String alley;    		//���
     	String cargospace;     	//��λ
     	String traycode;     	//��������
     	String snumber;    		//���ͺ�
     	String createtime;  	//����ʱ��
     	String finishtime; 		//���ʱ��
     	String invoicetype;//1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
     	
     	
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			detail = (InoutJobdetail)obj[1];
			
			jobid = job.getJobid();      			//��ҵ��
     		jobtype = job.getJobtypeName();
			productCode = detail.getProductcode();
			m_strProductName = detail.getM_strProductName();
			lotinfo = detail.getLotinfo();
			printdate = detail.getPrintdate();
			m_strUnitName = detail.getM_strUnitName();
			jobnum = detail.getJobnum();
			
     		warehouseName = job.getWarehousename();	//�ֿ�
     		alley = job.getTcargoAlleyName();		//���
     		cargospace = job.getTcargoSpaceName(); 	//��λ
     		traycode = job.getTraycode();     		//��������
     		snumber = job.getSnumber();    			//���ͺ�
     		createtime = job.getCreatetime();  		//����ʱ��
     		finishtime = job.getFinishtime(); 		//���ʱ��
     		invoicetype = job.getInvoicetypename();
		
%>
	      <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td> 
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td>
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=warehouseName==null?"":warehouseName%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=snumber==null?"":snumber%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td>
     <td class="TD_LIST" align="center"><%=finishtime==null?"":finishtime%></td>
     <td class="TD_LIST" align="center"><%=invoicetype==null?"":invoicetype%></td>
   </tr>
<%
		}
	}
%>  
 </table>
 
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>