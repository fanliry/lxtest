<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>�û�����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/userGroupTree.js"></script>
	<SCRIPT type = "text/javascript"  language=JavaScript src="<%=request.getContextPath()%>/standard/js/md5.js"></SCRIPT>
<script type="text/javascript">
<!--
	//����
	function saveData()
	{
		  	
        	var password1 = document.getElementById("password").value;
        	var password2;
        	if(password1 !=undefined && password1.length>0)
        	{
        	    	//password2=password1.calcMD5();//���ܱ���
        	    	password2=password1;
        	    	
        	    	var flag = "N";
        	    	
        	    	if(document.getElementById("iflag").checked == true)
        	    	{
        	    		flag = "Y";
        	    	}
        	    	
        	    	var strParam = "BMSService?actionCode=userInfoMgr&method=ricoExecAdd" 
        							+ "&userCode=" + document.getElementById("userCode").value
        							+ "&userName=" + document.getElementById("userName").value 
        							+ "&password=" + password2 
        							+ "&tel=" + document.getElementById("tel").value 
          							+ "&email=" + document.getElementById("Email").value 
          							+ "&handset=" + document.getElementById("handset").value
          							+ "&address=" + document.getElementById("address").value
	          						+ "&flag="+flag
	          						+ "&userGroup="+document.getElementById("userGroup").value;
									     	   	
		        	window.returnValue=strParam;
		        	
		        	window.close();
		        	    	
        	}else
        	{
        	    	alert("���벻��Ϊ��!");
        	}  
	}
	function checkData()
	{
		var p1 = document.getElementById("password").value.trim();
		var p2 = document.getElementById("password2").value.trim();
		
		var userCode = document.getElementById("userCode").value.trim();
		var userName = document.getElementById("userName").value.trim();

		if(userCode == null || userCode.length <1)
		{
			alert("�û����벻��Ϊ��!");
			return false;
		}
		if(userName == null || userName.length <1)
		{
			alert("�û�������Ϊ��!");
			return false;
		}
		
		if(p1 != null && p2 != null)
		{
			if(p1 != p2)
			{
				alert("�����������벻ͬ!");
				return false;
			}
		}else 
		{	
			alert("���벻��Ϊ��!");
			return false;
		}
		return true;
	}
	  String.prototype.trim = function()
	{
		return this.replace(/^\s*|\s*$/g,"");
	}
	function getGroup(id)
	{
		userGroupTree.getUserGroupList(id, viewGroup);
	}
	function viewGroup(data)
	{
		
		var g =document.getElementById("userGroup");
		while(g.options.length > 0) 
		{
	       	g.remove(g.options.length-1);
	    }
	    var op0=new Option("--��ѡ��--");        
	    g.options.add(op0);
	    op0.value="";
		for(var i = 0; i < data.length; i++)
		{
			//alert(data[i].strId + '  |  '+data[i].strName);
			var op1=new Option(data[i].strName);        
	    	g.options.add(op1);
	    	op1.value=data[i].strId;
	    	
		}
	}

-->
</script>
</head>

<body onload="getGroup('');">
<form method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã��û����� -&gt; �����û�</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">

   <tr>
     <td class="yx1" width="20%"><div align="right">�û����룺</div></td>
     <td class="yx" width="30%"><div align="left"><input type="text" name="userCode" size="20" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
  	 <td class="yx1" width="20%"><div align="right">�û�����</div></td>
     <td class="xx1" width="30%"><div align="left"><input type="text" name="userName" size="20" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">�û����룺</div></td>
     <td class="yx"><div align="left"><input type="password" name="password" size="21" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
  	 <td class="yx1"><div align="right">ȷ�����룺</div></td>
     <td class="xx1"><div align="left"><input type="password" name="password2" size="21" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">�û��飺</div></td>
     <td class="yx"><div align="left">
     	<select name="userGroup">
              <option value="">    </option>
              
                  	 
      </select> 
     </div></td>
  	 <td class="yx1"><div align="right">���ã�</div></td>
     <td class="xx1"><div align="left">
     <input name="iflag" id="iflag" type="checkbox" checked> </div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">�ֻ����룺</div></td>
     <td class="yx"><div align="left"><input type="text" name="handset" size="20" maxlength="40"></div></td>
  	 <td class="yx1"><div align="right">��ϵ�绰��</div></td>
     <td class="xx1"><div align="left"><input type="text" name="tel" size="20" maxlength="40"></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">Email��</div></td>
     <td class="yx"><div align="left"><input type="text" name="Email" size="20" maxlength="40"> </div></td>
  	 <td class="yx1"><div align="right">��ϵ��ַ��</div></td>
     <td class="xx1"><div align="left"><input type="text" name="address" size="20" maxlength="40"> </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" class="add_td2">
       <div align="center">
         <input type="button" name="saveBtn" value="����" class="button_style" onclick="if(checkData()!=false){saveData();}">
         <input type="reset" name="resetBtn" value="����" class="button_style">&nbsp;
         <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE"></div>
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>