<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%
        // --customerid|ownerId,1|2,�ͻ�|����--
	    String groupinfo = (String)request.getAttribute("groupinfo");
	    String aem[] = null;
	    String bem[] = null;
        if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
				
				aem = groupinfo.split(",");
				bem = aem[2].split("\\|");// ������
		}
		
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
		
		var classname = "com.wms3.bms.standard.action.report.query.CXOUTQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=������ˮ��ѯ.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
		document.tempForm1.submit();	
		//alert(window.opener.condition);
		//alert(window.opener.getLotattCon());
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
 
<%
	int iLine = 0;	//��ʾ���������Ը���
	
%>

 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=18 + iLine%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;ˮ&nbsp;��&nbsp;ѯ</div></td>
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
 
  <table width="100%"  id="table" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
<%	
   if(bem != null && bem.length>0){ //��ȡ�����ֶ�
		for(int i=0; i<bem.length; i++){
%>
	     <td class="TD_LIST_TITLE"><div class="list_title"><%=bem[i]%></div></td>
<%
		}
	}else{
%>
		<td class="TD_LIST_TITLE" ><div class="list_title">��Ʒ����</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
          <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">�ͻ�</div></td> 
         <td class="TD_LIST_TITLE" ><div class="list_title">���ݱ��</div></td> 
         <td class="TD_LIST_TITLE" ><div class="list_title">��������</div></td>
          <td class="TD_LIST_TITLE" ><div class="list_title">��ҵ��������</div></td>
<%	
    }		
%>
	
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
   </tr>
<%
	
	
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
		String createTime = null;   //��������
		double pnum;            	//����
		double volume;          	//���
		double pweight;         	//����
     	double pnetweight;      	//����

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
			ob = (Object[])ls.get(i);
  	 		if(bem != null && bem.length>0){ //����֮��
  	 		    pnum = Double.parseDouble(ob[bem.length].toString());			    //����
  	 		    volume = Double.parseDouble(ob[bem.length+1].toString());		    //���
  	 		    pweight = Double.parseDouble(ob[bem.length+2].toString());        	//ë��
  	 		    pnetweight = Double.parseDouble(ob[bem.length+3].toString());     	//����
  	 		}else{ //û�з���
  	 			
  	 		    pnum = Double.parseDouble(ob[8].toString());			//����
  	 		    volume = Double.parseDouble(ob[9].toString());		    //���
  	 		    pweight = Double.parseDouble(ob[10].toString());        	//ë��
  	 		    pnetweight = Double.parseDouble(ob[11].toString());     	//����
  	 		}
 		
			%>
			   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
			     <td class="TD_LIST1" align="center"><%=i+1%></td>
			<%	
			   if(bem != null && bem.length>0){ //��ȡ�����ֶ�
					for(int j=0; j<bem.length; j++){
			%>
				     <td class="TD_LIST"  align="center"><%=ob[j] == null ? "" : ob[j].toString()%></td>
			<%
					}
				}else{ //��ȡĬ��
			%>
			         <td class="TD_LIST"  align="center"><%=ob[0] == null ? "" : ob[0].toString()%></td>  
			         <td class="TD_LIST"  align="center"><%=ob[1] == null ? "" : ob[1].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[2] == null ? "" : ob[2].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[3] == null ? "" : ob[3].toString()%></td>  
			         <td class="TD_LIST"  align="center"><%=ob[4] == null ? "" : ob[4].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[5] == null ? "" : ob[5].toString()%></td> 
			          <td class="TD_LIST"  align="center"><%=ob[6] == null ? "" : ob[6].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[7] == null ? "" : ob[7].toString()%></td>
			<%	
			    }		
			%>
				 
			     <td class="TD_LIST" align="center"><%=pnum%></td>
			     <td class="TD_LIST" align="center"><%=volume%></td>
			     <td class="TD_LIST" align="center"><%=pweight%></td>
			     <td class="TD_LIST" align="center"><%=pnetweight%></td>
			   </tr>
			<%
		}
     }else{
		session.removeAttribute("paging");
	 }
%>
   <tr>
     <td height="100%" colspan="<%=4 + (bem!=null&&bem.length>0?bem.length:3)%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>

 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
