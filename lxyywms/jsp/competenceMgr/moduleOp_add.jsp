<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>����ģ��</title>
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
        function saveModuleInfo(parentId,parentCode,lvel){
        	var strParam = "modOpCode="+document.getElementById("modOpCode").value
        							+ "&modOpName="+document.getElementById("modOpName").value
        							+ "&parentId="+parentId
        							+ "&parentCode="+parentCode
        							+ "&lvl="+lvel
        							+ "&parentName="+document.getElementById("parentName").value
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

  String strParentId ="";
  String strParentCode ="";
  String strParentName = "";
  String strLvel = "";

   request.setCharacterEncoding("iso-8859-1");  

      	if(request.getParameter("parentId") != null)
      	{
      		strParentId = (String)request.getParameter("parentId");
      	}      		
		if(request.getParameter("parentCode") != null){
      		strParentCode = (String)request.getParameter("parentCode");
      	}      		
 	   if(request.getParameter("parentName") != null){
      			strParentName = (String)request.getParameter("parentName");
      		}
      		  strParentName = new String(strParentName.getBytes("ISO-8859-1"),"gb2312");	    		
		if(request.getParameter("modOpLevel") != null){
      		strLvel = (String)request.getParameter("modOpLevel");
      	} 
 %>
<form method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ģ����� -&gt; ����ģ��</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td height="25" class="yx1"><div align="right">ģ����룺</div></td>
     <td class="yx"><div align="left"><input name="modOpCode" type="text" value="" size="16"></div></td>
     <td class="yx1"><div align="right">ģ������</div></td>
     <td class="xx1"><div align="left"><input name="modOpName" type="text" size="30" value="">
     </div></td>
   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">��ģ������</div></td>
     <td class="yx"><div align="left"><input name="parentName" type="text"  readonly value="<%=strParentName%>" size="16"></div></td>
     <td class="yx1"><div align="right">ģ�����ͣ�</div></td>
     <td class="xx1"><div align="left"><input name="selectModType" type="text" size="30" value=""></div></td>
   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">ģ�������ַ��</div></td>
     <td class="xx1" colspan="3"><div align="left"><input name="modOpUrl" value=""  type="text" size="60"></div></td>

   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">ģ��Ŀ�ĵ�ַ��</div></td>
     <td class="xx1" colspan="3"><div align="left">
       <input name="targetUrl" type="text" value="" size="60">
       </div></td>
     
   </tr>
   <tr>
     <td height="25" class="yx1"><div align="right">����ִ�е�ַ��</div></td>
     <td class="xx1" colspan="3"><div align="left">
       <input name="failUrl" type="text" value="" size="60">
       </div></td>
     
   </tr>
      <tr>
     <td height="25" class="yx1"><div align="right">��ע��</div></td>
     <td class="xx1" colspan="3"><div align="left">
       <textarea name="remark" cols="45"></textarea>
       </div></td>
     
   </tr>
   <tr>
     <td height="27" colspan="4" class="add_td2">
       <div align="center">
       <input name="button1"  type="button"  value="����" onClick="if(check() != false){saveModuleInfo('<%=strParentId%>','<%=strParentCode%>','<%=strLvel%>');}" class="button_style">
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