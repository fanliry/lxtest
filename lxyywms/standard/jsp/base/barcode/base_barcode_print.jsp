<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style media=print>
<!--     
	.Noprint{display:none;}/*�ñ���ʽ�ڴ�ӡʱ���طǴ�ӡ��Ŀ*/    
	.PageNext{page-break-after:   always;}/*���Ʒ�ҳ*/
-->      
</style> 
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var code = null;
	//������ҳ��ӡ��ҳüҳ��   
	function PageSetup_Null(){     
		try{     
			var Wsh = new ActiveXObject("WScript.Shell");     
			HKEY_Key="header";     
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");     
			HKEY_Key="footer";     
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");   
		}     
		catch(e){}     
	}
	function Greate(){
		var code = document.getElementById("code").value
		if(code == null || code.length < 1){
			alert("�����ݣ�");
		}
		else{
			var info = "";
			var width = document.getElementById("width").value;
			var height = document.getElementById("height").value;
			var aem = code.split(",");
			for(var i=0; i<aem.length; i++){
		 		//info += "<img src=\"<%=request.getContextPath()%>/barcode?msg=" + aem[i] 
				//	+ "&type=code128&fmt=jpeg\" height='" + height + "px' width=' " + width + "px' />";
					
				if (info == "") {
		 		info += "<img src=\"<%=request.getContextPath()%>/barcode?msg=" + aem[i] 
					+ "&type=code128&fmt=jpeg\" height='" + height + "px' width='" + width + "px' />";
				} else {
				info += "<br><img src=\"<%=request.getContextPath()%>/barcode?msg=" + aem[i] 
					+ "&type=code128&fmt=jpeg\" height='" + height + "px' width='" + width + "px' />";
					}	
					
					
			}
			img.innerHTML = info;
		}
	}
	function OnLoad(ids){
		//document.getElementById("code").value = window.dialogArguments;
		document.getElementById("code").value = ids;
	}
-->
</script>
</head>
<%
	String strIds = "";
	if(request.getParameter("ids") != null)
	{
		strIds = request.getParameter("ids");
	}
%>
<body onLoad="OnLoad('<%=strIds%>')" style="overflow: auto;">
  <br>
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="right" width="100px" rowspan="2">���룺</td>
     <td class="yx" rowspan="2"><textarea rows="2" style="width:300px;height:40px" id="code" readonly class="input_readonly"></textarea>
     <td class="yx1" align="right" width="100px">��</td>
     <td class="xx1"><input name="width" size="5" maxlength="5" value="200"></td>
   </tr>
   <tr>
     <td class="yx1" align="right" width="100px">�ߣ�</td>
     <td class="xx1"><input name="height" size="5" maxlength="5" value="120"></td>
   </tr>
   <tr>
     <td colspan="6" align="center">
     <!-- 
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
     -->   
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       
       <input onclick="Greate()" type="button" name="button1" value="��������" class="BUTTON_STYLE1">
       
       <input onclick="document.all.WebBrowser.ExecWB(8,1);" type="button" name="button6" value="ҳ������" class="BUTTON_STYLE1">
       <input onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1);" type="button" name="button7" value="��ӡԤ��" class="BUTTON_STYLE1">
       
       <input onclick="PageSetup_Null();window.print()" type="button" name="button1" value="��ӡ����" class="BUTTON_STYLE1">
       
       <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 <br>
 <div align="center" id="img"></div>
</body>
</html>
