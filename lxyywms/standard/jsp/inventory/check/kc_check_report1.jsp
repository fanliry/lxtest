<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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
       <input type="button" name="readyBtn" value="��ӡԤ��" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="ҳ������" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="��ӡ" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="�ر�" class="BUTTON_STYLE1" onclick="window.close()">
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=15%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;��&nbsp;��</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== �б�ʼ ======== -->  	
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr class="list_title_tr">
     <td class="list_title_td">�к�</td>
     <td class="list_title_td">���������</td>
     <td class="list_title_td">�����</td>
     <td class="list_title_td">����</td>
     <td class="list_title_td">��λ</td>
     <td class="list_title_td">״̬</td>
     <td class="list_title_td">Ʒ��</td>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">��Ʒ����</td>
     <td class="list_title_td">�������</td>
     <td class="list_title_td">����</td>
     <td class="list_title_td">��������</td>
   </tr>
   
<%

	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InventoryCheckTask checktask = null;
		String taskid;			//ID
		String status;			//״̬
		String cargospace;		//��λ
		String wharea;			//����
		String product;			//��Ʒ
		String owner;			//����
		String tyaycode;		//��������
    	String productcode;     //��Ʒ����
		String prindtate;		//��������
		String punit;		    //��λ
		String lotinfo;		    //����
		double num;				//�������
		
		for(int i = 0; i < ls.size(); i++){
			checktask = (InventoryCheckTask)ls.get(i);
			
			taskid = checktask.getTaskid();				//ID
			status = checktask.getStatusName();			//״̬
			cargospace = checktask.getCargoSpaceName();	//��λ
			wharea = checktask.getWhAreaName();			//����
			product = checktask.getProductName();		//��Ʒ
			tyaycode = checktask.getTraycode();			//��������
    		productcode = checktask.getProductcode(); 	//��Ʒ����
			prindtate = checktask.getCreatetime();		//����ʱ��
			num = checktask.getNum();					//�������
			punit = checktask.getPunit(); //��λ
			lotinfo = checktask.getLotinfo(); //����
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><div align="center"> 
	  		<img src="<%=request.getContextPath()%>/barcode?msg=<%=taskid%>&type=code128&fmt=jpeg" height="20px" width=120px /></div></td>
     <td class="list_normal_td"><%=taskid==null?"":taskid%></td>
     <td class="list_normal_td"><%=wharea==null?"":wharea%></td>
     <td class="list_normal_td"><%=cargospace==null?"":cargospace%></td>
     <td class="list_normal_td"><%=status==null?"":status%></td>
     <td class="list_normal_td"><%=product==null?"":product%></td>
     <td class="list_normal_td"><%=tyaycode==null?"":tyaycode%></td>
     <td class="list_normal_td"><%=productcode==null?"":productcode%></td>
     <td class="list_normal_td"></td>
     <td class="list_normal_td"><%=punit==null?"":punit%></td>
     <td class="list_normal_td"><%=lotinfo==null?"":lotinfo%></td>
   </tr>
   
<%
		}
	}
%>
 </table>
 
</body>
</html>
