<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null){
		items = list_items.split(",");
		itemscount = items.length;
	}
%>
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
	
	//��Excel����
	function openexcel()
	{
		alert(window.opener.condition);
		alert(window.opener.getPostCon());
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
	 <td height="40" colspan="<%=12 + itemscount%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;ͳ&nbsp;��&nbsp;��&nbsp;��</div></td>
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
     <td class="list_title_td">����</td>
<%	
	for(int i=0; i<itemscount; i++){
%>
     <td class="list_title_td"><%=items[i]%></td>
<%
	}
%>
     <td class="list_title_td">��λ</td>
     <td class="list_title_td">�������</td>
     <td class="list_title_td">�������</td>
     <td class="list_title_td">���ë��</td>
     <td class="list_title_td">��澻��</td>
     <td class="list_title_td">������</td>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">����ë��</td>
     <td class="list_title_td">���Ά��</td>
     <td class="list_title_td">�������</td>
   </tr>
   
<%

	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		Object[] ob = null;
		String whArea;				//����
		String punit;				//��λ
		double pnum;            	//�������
		long boxnum;          		//�������
		double pweight;         	//���ë��
     	double pnetweight;      	//��澻��
     	double pvolume;         	//������
		double holdnum;         	//��������
		double holdweight;      	//����ë��
		double holdnetweight;      	//���Ά��
		double holdvolume;         	//�������
		
		//�ϼ���
		double pnum_sum = 0;            	//�������
		long boxnum_sum = 0;          		//�������
		double pweight_sum = 0;         	//���ë��
     	double pnetweight_sum = 0;      	//��澻��
     	double pvolume_sum = 0;         	//������
		double holdnum_sum = 0;         	//��������
		double holdweight_sum = 0;      	//����ë��
		double holdnetweight_sum = 0;      	//���Ά��
		double holdvolume_sum = 0;         	//�������

  	 	for(int i=0; i<ls.size(); i++) {
  	 	
			ob = (Object[])ls.get(i);
  	 		whArea = ob[0] == null ? "" : ob[0].toString();			//����
  	 		punit = ob[1] == null ? "" : ob[1].toString();			//��λ
  	 		
  	 		pnum = Double.parseDouble(ob[2].toString());			//�������
  	 		boxnum = Long.parseLong(ob[3].toString());				//�������
  	 		pweight = Double.parseDouble(ob[4].toString());        	//���ë��
  	 		pnetweight = Double.parseDouble(ob[5].toString());     	//��澻��
  	 		pvolume = Double.parseDouble(ob[6].toString());        	//������
  	 		holdnum = Double.parseDouble(ob[7].toString());			//��������
  	 		holdweight = Double.parseDouble(ob[8].toString());     	//����ë��
			holdnetweight = Double.parseDouble(ob[9].toString());  //���Ά��
			holdvolume = Double.parseDouble(ob[10].toString());     //�������
  	 		
  	 		//�ϼ���
			pnum_sum += pnum;            		//�������
			boxnum_sum += boxnum;          		//�������
			pweight_sum += pweight;         	//���ë��
     		pnetweight_sum += pnetweight;      	//��澻��
     		pvolume_sum += pvolume;         	//������
			holdnum_sum += holdnum;         	//��������
			holdweight_sum += holdweight;      	//����ë��
			holdnetweight_sum += holdnetweight; //���Ά��
			holdvolume_sum += holdvolume;       //�������
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=whArea%></td>
<%
	for(i=0; i<itemscount; i++){
%>
     <td class="list_normal_td"><%=ob[11+i] == null ? "" : ob[11+i].toString()%></td>
<%
	}
%>
     <td class="list_normal_td"><%=punit%></td>
     <td class="list_normal_td"><%=pnum%></td>
     <td class="list_normal_td"><%=boxnum%></td>
     <td class="list_normal_td"><%=pweight%></td>
     <td class="list_normal_td"><%=pnetweight%></td>
     <td class="list_normal_td"><%=pvolume%></td>
     <td class="list_normal_td"><%=holdnum%></td>
     <td class="list_normal_td"><%=holdweight%></td>
     <td class="list_normal_td"><%=holdnetweight%></td>
     <td class="list_normal_td"><%=holdvolume%></td>
   </tr>
   
<%
		}
%>
   <tr class="list_normal_tr">
   <tr>
     <td class="list_normal_td"></td>
     <td class="list_normal_td" style="font-weight: bold;">�ϼ�</td>
<%
	for(int i=0; i<itemscount; i++){
%>
     <td class="list_normal_td">&nbsp;</td>
<%
	}
%>	 		 
     <td class="list_normal_td">&nbsp;</td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pnum_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=boxnum_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pnetweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=pvolume_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdnum_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdnetweight_sum%></td>
     <td class="list_normal_td" style="font-weight: bold;"><%=holdvolume_sum%></td>
   </tr> 
<%
	}
%>
 </table>

</body>
</html>
