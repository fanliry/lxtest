<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>�޸�ģ��</title>
	  <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
</head>

<body>
<script>
        //����ģ����Ϣ		
        function saveModuleInfo(strId){
        	var strParam = "BMSService?actionCode=moduleOpMgr&method=ricoExecUpdate" +"&modOpId="+strId 
        							+ "&modOpName="+document.getElementById("modOpName").value
          							+ "&selectModType=" + document.getElementById("selectModType").value 
          							+ "&modOpUrl=" + document.getElementById("modOpUrl").value
          							+ "&targetUrl="+ document.getElementById("targetUrl").value
          							+ "&failUrl="+ document.getElementById("failUrl").value
          							+ "&remark="+ document.getElementById("remark").value;       	
          						
          	window.returnValue=strParam;
        	window.close();
        }
</script>
 <%
  String strId ="";
  String strParentId ="";
  String strParentCode ="";
  String strParentName = "";
  
  String strmType = "";
  String strModOpCode = "";
  String strModOpName = "";
  
  String strLinked= "";
  String strModOpUrl= "";
  String strModOpLevel="";
  
  String strModOpTradeCode="";
  String strTargetUrl="";
  String strFailUrl = "";
  String strRemark="";
  
   request.setCharacterEncoding("iso-8859-1");  

		if(request.getParameter("moduleOpId") != null)
		{
      		strId = (String)request.getParameter("moduleOpId");
      	} 
      	if(request.getParameter("parentId") != null)
      	{
      			strParentId = (String)request.getParameter("parentId");
      	}      		
		if(request.getParameter("pmodOpCode") != null){
      		strParentCode = (String)request.getParameter("pmodOpCode");
      	}      		
 	   if(request.getParameter("pModOpName") != null){
      			strParentName = (String)request.getParameter("pModOpName");
      		}
      		  strParentName = new String(strParentName.getBytes("ISO-8859-1"),"gb2312");	    		

      	if(request.getParameter("mType") != null){
      			strmType = (String)request.getParameter("mType");
      	}    
      		if(request.getParameter("modOpCode") != null){
      			strModOpCode = (String)request.getParameter("modOpCode");
      		}      		
      		if(request.getParameter("modOpName") != null){
      			strModOpName = (String)request.getParameter("modOpName");
      		}  	
      		strModOpName = new String(strModOpName.getBytes("ISO-8859-1"),"gb2312");	
      		if(request.getParameter("selectLinked") != null){
      			strLinked = (String)request.getParameter("selectLinked");
      		}   
      		if(request.getParameter("modOpUrl") != null){
      			strModOpUrl = (String)request.getParameter("modOpUrl");
      		}    
      		strModOpUrl = new String(strModOpUrl.getBytes("ISO-8859-1"),"gb2312");    		
      		if(request.getParameter("targetUrl") != null){
      			strModOpLevel = (String)request.getParameter("targetUrl");
      		}      		
      		if(request.getParameter("modOpTradeCode") != null){
      			strModOpTradeCode = (String)request.getParameter("modOpTradeCode");
      		}  
      		strModOpTradeCode = new String(strModOpTradeCode.getBytes("ISO-8859-1"),"gb2312");       		
      		
      		if(request.getParameter("targetUrl") != null){
      			strTargetUrl = (String)request.getParameter("targetUrl");
      		} 
      		strTargetUrl = new String(strTargetUrl.getBytes("ISO-8859-1"),"gb2312"); 
      		     		
      		if(request.getParameter("failUrl") != null){
      			strFailUrl = (String)request.getParameter("failUrl");
      		}    
      		strFailUrl = new String(strFailUrl.getBytes("ISO-8859-1"),"gb2312");   		

      		if(request.getParameter("remark") != null){
      			strRemark = (String)request.getParameter("remark");
      		}     
      		strRemark = new String(strRemark.getBytes("ISO-8859-1"),"gb2312");   	 		

    if(strModOpName == null)
   {
   			strModOpName = "";
   }
 	    if(strmType == null || strmType.trim().equals("null"))
   {
   			strmType = "";
   }
       if(strModOpUrl == null || strModOpUrl.trim().equals("null"))
   {
   			strModOpUrl = "";
   }
       if(strTargetUrl == null || strTargetUrl.trim().equals("null"))
   {
   			strTargetUrl = "";
   }
       if(strFailUrl == null || strFailUrl.trim().equals("null"))
   {
   			strFailUrl = "";
   }
    if(strRemark == null || strRemark.trim().equals("null"))
   {
   			strRemark = "";
   }
   
   
 
 %>
<form method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã���ɫ���� -&gt; ����ģ��</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td height="25" class="yx1"><div align="right">ģ����룺</div></td>
     <td class="xx1"><div align="left"><input name="modOpCode" type="text" size="16" class="input_readonly" readonly value="<%=strModOpCode%>"></div></td>
     <td class="yx"><div align="right">ģ������</div></td>
     <td class="xx1"><div align="left"><input name="modOpName" type="text" size="30" value="<%=strModOpName%>">
     </div></td>
   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">��ģ������</div></td>
     <td class="xx1"><div align="left"><input name="parentName" type="text" class="input_readonly" readonly value="<%=strParentName%>" size="16"></div></td>
     <td class="yx"><div align="right">ģ�����ͣ�</div></td>
     <td class="xx1"><div align="left"><input name="selectModType" type="text" size="30" value="<%=strmType%>"></div></td>
   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">ģ�������ַ��</div></td>
     <td class="xx1" colspan="3"><div align="left"><input name="modOpUrl"  value="<%=strModOpUrl%>"   type="text" size="60"></div></td>

   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">ģ��Ŀ�ĵ�ַ��</div></td>
     <td class="xx1" colspan="3"><div align="left">
       <input name="targetUrl" type="text" value="<%=strTargetUrl%>" size="60">
       </div></td>
     
   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">����ִ�е�ַ��</div></td>
     <td class="xx1" colspan="3"><div align="left">
       <input name="failUrl" type="text" value="<%=strFailUrl%>" size="60">
       </div></td>
     
   </tr>
      <tr>
     <td height="25" class="yx1"><div align="right">��ע��</div></td>
     <td class="xx1" colspan="3"><div align="left">
       <textarea name="remark" cols="45"><%=strRemark%></textarea>
       </div></td>
     
   </tr>
   <tr>
     <td height="27" colspan="4" class="add_td1">
       <div align="center">
       <input name="button1"  type="button"  value="����" onClick="if(check() != false){saveModuleInfo('<%=strId%>');}" class="button_style">
          <input type="reset" name="Submit2" value="����" class="button_style">&nbsp;
         <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE">
       
       </div>
     </td>
   </tr>
 </table>
 
 </form>
 <script language="javascript">
	String.prototype.trim = function()
	{
		return this.replace(/^\s*|\s*$/g,"");
	}
	function check()
	{
		var modOpCode = document.getElementById("modOpCode").value.trim();
		var modOpName = document.getElementById("modOpName").value;
	
		if(modOpCode == null || modOpCode.length <1)
		{
			alert("ģ����벻��Ϊ��!");
			return false;
		}
		if(modOpCode.length >20)
		{
			alert("ģ�����̫��!");
			return false;
		}
		if(modOpName == null || modOpName.length <1)
		{
			alert("ģ��������Ϊ��!");
			return false;
		}
		if(modOpName.length >200)
		{
			alert("ģ����̫��!");
			return false;
		}

	
	}
</script> 
</body>
</html>