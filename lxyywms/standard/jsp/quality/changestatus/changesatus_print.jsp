<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryQualityResult" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail" %>
<%
InventoryQualityResult result = (InventoryQualityResult)request.getAttribute("result"); 
	//String instockid = 	(String)request.getAttribute("instockid"); 

	 String m_strCheckResultId = null;			/*�ʼ�����ID*/	
	 String m_strLotNumber=null;  		    /*����*/
	 String m_strOpManId=null;				/*������ID*/
	 String m_strCreateDate=null;   			/*��������*/
	 String m_strNum=null;					/*����*/
	 
	
	if(result != null){
		m_strCheckResultId = result.getM_strCheckResultId();
		m_strOpManId = result.getM_strOpManId();
		m_strCreateDate = result.getM_strCreateDate();
		m_strLotNumber = result.getM_strLotNumber();
		m_strNum = result.getM_strNum();
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
		
		var classname = "com.wms3.bms.standard.action.report.quality.changestatus_print_Excel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=���м�¼��ѯ.xls&fileType=excel&classname= " + classname +"&instockid="+"<%=m_strCheckResultId%>";
		
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

<body>

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
 <table width="70%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;ҩ&nbsp;ҵ&nbsp;(&nbsp;��&nbsp;��&nbsp;)&nbsp;��&nbsp;��&nbsp;��&nbsp;˾</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">���м�¼��ѯ</div></td>
   </tr>
   <tr>
   	<td colspan="6"><div align="center" id="img"></div></td>
   </tr>
   <tr>
   	<td colspan="6" height="10" ></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">���е��ţ�<%=m_strCheckResultId==null?"":m_strCheckResultId%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">������Ա��<%=m_strOpManId==null?"":m_strOpManId%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�������ڣ�<%=m_strCreateDate==null?"":m_strCreateDate%></div></td>
   
	 <td height="20"><div align="left"  class="list_title_tdpr">���ţ�<%=m_strLotNumber==null?"":m_strLotNumber%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">������<%=m_strNum==null?"":m_strNum%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr"></div></td>
   </tr>
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== �����б�ʼ ======== --> 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("exResultList") != null)
	{
		list = (List)request.getAttribute("exResultList");
	}
 %>
   <tr class="list_title_tr">
   	 <td class="list_title_td">�к�</td>
     <td class="list_title_td">Ʒ��</td>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">��Ʒ��״̬</td>
     <td class="list_title_td">��Ʒԭ״̬</td>
     <td class="list_title_td">��λ��</td>
     <td class="list_title_td">����</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InventoryQualityResultDetail detail=null;
        String strDId="";
        String strProductName="";
        String strCreatetime="";
        String strNewProductStatus="";
        String strOldProductStatus="";
        String strClientName="";
        String strCarspaceid="";
        double iProductNum;
        for(int i=0; i<list.size(); i++)
        {
             detail = (InventoryQualityResultDetail)list.get(i); 
             strDId = detail.getM_strCheckResultId();        //״̬ת������
        	 strProductName = detail.getM_strProductName();  //��Ʒ��
        	 strCreatetime = detail.getM_strPrintDate();     //��������
        	 strNewProductStatus = detail.getM_strNewProductStatusName();	 //����Ʒ״̬
        	 strOldProductStatus = detail.getM_strOldProductStatusName();	 //ԭ��Ʒ״̬
        	 strClientName = detail.getM_strLotNumber();    //����
        	 strCarspaceid = detail.getM_strCarspaceid();    //��λ��
        	 iProductNum = detail.getM_iProductNum();	     //����	
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td"><%=strProductName==null?"":strProductName%></td>
      <td class="list_normal_td"><%=strCreatetime==null?"":strCreatetime%></td>
	  <td class="list_normal_td"><%=strNewProductStatus==null?"":strNewProductStatus%></td>
	  <td class="list_normal_td"><%=strOldProductStatus==null?"":strOldProductStatus%></td>
      
      <td class="list_normal_td"><%=strCarspaceid==null?"":strCarspaceid%></td>
      <td class="list_normal_td"><%=nbf.format(iProductNum)%></td>
   </tr>
<%
        }
    }
%>	
 </table>
  
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>