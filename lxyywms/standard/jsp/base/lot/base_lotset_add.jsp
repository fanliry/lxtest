<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	var condition = "";
	//�����Ƿ�Ϊ�����ַ�
	function CheckC(str)
	{
		var ts, tscode;
		for(var i=0; i<str.length; i++) 
		{ 
			ts = str.substring(i); 
			tscode = str.charCodeAt(i); 
			if(tscode >= 19968) 
			{
				return false;
			}
		}
		return true;
	}
	
	//����
	function Save(){
	
		var lottype = document.getElementById("lottype").value;	//����
		var remark = document.getElementById("remark").value;	//��ע
	
		var lotname1 = document.getElementById("lotname1").value;		//������������
		var lotid1 = document.getElementById("lotid1").value;			//��������ID
		var islot1 = document.getElementById("islot1").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq1 = document.getElementById("lotseq1").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��

		var lotname2 = document.getElementById("lotname2").value;		//������������
		var lotid2 = document.getElementById("lotid2").value;			//��������ID
		var islot2 = document.getElementById("islot2").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq2 = document.getElementById("lotseq2").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��

		var lotname3 = document.getElementById("lotname3").value;		//������������
		var lotid3 = document.getElementById("lotid3").value;			//��������ID
		var islot3 = document.getElementById("islot3").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq3 = document.getElementById("lotseq3").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname4 = document.getElementById("lotname4").value;		//������������
		var lotid4 = document.getElementById("lotid4").value;			//��������ID
		var islot4 = document.getElementById("islot4").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq4 = document.getElementById("lotseq4").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname5 = document.getElementById("lotname5").value;		//������������
		var lotid5 = document.getElementById("lotid5").value;			//��������ID
		var islot5 = document.getElementById("islot5").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq5 = document.getElementById("lotseq5").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname6 = document.getElementById("lotname6").value;		//������������
		var lotid6 = document.getElementById("lotid6").value;			//��������ID
		var islot6 = document.getElementById("islot6").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq6 = document.getElementById("lotseq6").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname7 = document.getElementById("lotname7").value;		//������������
		var lotid7 = document.getElementById("lotid7").value;			//��������ID
		var islot7 = document.getElementById("islot7").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq7 = document.getElementById("lotseq7").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname8 = document.getElementById("lotname8").value;		//������������
		var lotid8 = document.getElementById("lotid8").value;			//��������ID
		var islot8 = document.getElementById("islot8").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq8 = document.getElementById("lotseq8").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname9 = document.getElementById("lotname9").value;		//������������
		var lotid9 = document.getElementById("lotid9").value;			//��������ID
		var islot9 = document.getElementById("islot9").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq9 = document.getElementById("lotseq9").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname10 = document.getElementById("lotname10").value;		//������������
		var lotid10 = document.getElementById("lotid10").value;			//��������ID
		var islot10 = document.getElementById("islot10").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq10 = document.getElementById("lotseq10").value;		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname11 = document.getElementById("lotname11").value;		//������������
		var lotid11 = document.getElementById("lotid11").value;			//��������ID
		var islot11 = document.getElementById("islot11").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq11 = document.getElementById("lotseq11").value;		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotname12 = document.getElementById("lotname12").value;		//������������
		var lotid12 = document.getElementById("lotid12").value;			//��������ID
		var islot12 = document.getElementById("islot12").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq12 = document.getElementById("lotseq12").value;		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		if(lottype == null || lottype.length < 1)
		{
			alert("�����͡�����Ϊ�գ�");
			return;
		}
		if(remark != null && remark.length > 0)
		{
			if(strlen(remark) > 200)
			{
				alert("����ע��������");
				return;
			}
		}
		
		if(lotid1 != null && lotid1.length > 0){
			if(!CheckC(lotid1))
			{
				alert("�����Դ���1�������������ģ�");
				return;
			}
			
		}
		if(lotseq1 == null || lotseq1.length < 1){
			alert("��˳��1������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq1)){
				alert("��˳��1��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid2 != null && lotid2.length > 0){
			if(!CheckC(lotid2))
			{
				alert("�����Դ���2�������������ģ�");
				return;
			}
			
		}
		if(lotseq2 == null || lotseq2.length < 1){
			alert("��˳��2������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq2)){
				alert("��˳��2��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid3 != null && lotid3.length > 0){
			if(!CheckC(lotid3))
			{
				alert("�����Դ���3�������������ģ�");
				return;
			}
			
		}
		if(lotseq3 == null || lotseq3.length < 1){
			alert("��˳��3������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq3)){
				alert("��˳��3��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid4 != null && lotid4.length > 0){
			if(!CheckC(lotid4))
			{
				alert("�����Դ���4�������������ģ�");
				return;
			}
			
		}
		if(lotseq4 == null || lotseq4.length < 1){
			alert("��˳��4������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq4)){
				alert("��˳��4��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid5 != null && lotid5.length > 0){
			if(!CheckC(lotid5))
			{
				alert("�����Դ���5�������������ģ�");
				return;
			}
			
		}
		if(lotseq5 == null || lotseq5.length < 1){
			alert("��˳��5������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq5)){
				alert("��˳��5��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid6 != null && lotid6.length > 0){
			if(!CheckC(lotid6))
			{
				alert("�����Դ���6�������������ģ�");
				return;
			}
			
		}
		if(lotseq6 == null || lotseq6.length < 1){
			alert("��˳��6������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq6)){
				alert("��˳��6��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid7 != null && lotid7.length > 0){
			if(!CheckC(lotid7))
			{
				alert("�����Դ���7�������������ģ�");
				return;
			}
			
		}
		if(lotseq7 == null || lotseq7.length < 1){
			alert("��˳��7������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq7)){
				alert("��˳��7��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid8 != null && lotid8.length > 0){
			if(!CheckC(lotid8))
			{
				alert("�����Դ���8�������������ģ�");
				return;
			}
			
		}
		if(lotseq8 == null || lotseq8.length < 1){
			alert("��˳��8������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq8)){
				alert("��˳��8��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid9 != null && lotid9.length > 0){
			if(!CheckC(lotid9))
			{
				alert("�����Դ���9�������������ģ�");
				return;
			}
			
		}
		if(lotseq9 == null || lotseq9.length < 1){
			alert("��˳��9������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq9)){
				alert("��˳��9��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid10 != null && lotid10.length > 0){
			if(!CheckC(lotid10))
			{
				alert("�����Դ���10�������������ģ�");
				return;
			}
			
		}
		if(lotseq10 == null || lotseq10.length < 1){
			alert("��˳��10������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq10)){
				alert("��˳��10��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid11 != null && lotid11.length > 0){
			if(!CheckC(lotid11))
			{
				alert("�����Դ���11�������������ģ�");
				return;
			}
			
		}
		if(lotseq11 == null || lotseq11.length < 1){
			alert("��˳��11������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq11)){
				alert("��˳��11��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid12 != null && lotid12.length > 0){
			if(!CheckC(lotid12))
			{
				alert("�����Դ���12�������������ģ�");
				return;
			}
			
		}
		if(lotseq12 == null || lotseq12.length < 1){
			alert("��˳��12������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq12)){
				alert("��˳��12��ֻ���������֣�");
				return;
			}
		}

		condition = "&lottype=" + lottype + "&remark=" + remark 
			 + "&lotname1=" + lotname1 + "&lotid1=" + lotid1 + "&islot1=" + islot1 + "&lotseq1=" + lotseq1 
			 + "&lotname2=" + lotname2 + "&lotid2=" + lotid2 + "&islot2=" + islot2 + "&lotseq2=" + lotseq2
			 + "&lotname3=" + lotname3 + "&lotid3=" + lotid3 + "&islot3=" + islot3 + "&lotseq3=" + lotseq3 
			 + "&lotname4=" + lotname4 + "&lotid4=" + lotid4 + "&islot4=" + islot4 + "&lotseq4=" + lotseq4 
			 + "&lotname5=" + lotname5 + "&lotid5=" + lotid5 + "&islot5=" + islot5 + "&lotseq5=" + lotseq5
			 + "&lotname6=" + lotname6 + "&lotid6=" + lotid6 + "&islot6=" + islot6 + "&lotseq6=" + lotseq6
			 + "&lotname7=" + lotname7 + "&lotid7=" + lotid7 + "&islot7=" + islot7 + "&lotseq7=" + lotseq7
			 + "&lotname8=" + lotname8 + "&lotid8=" + lotid8 + "&islot8=" + islot8 + "&lotseq8=" + lotseq8
			 + "&lotname9=" + lotname9 + "&lotid9=" + lotid9 + "&islot9=" + islot9 + "&lotseq9=" + lotseq9
			 + "&lotname10=" + lotname10 + "&lotid10=" + lotid10 + "&islot10=" + islot10 + "&lotseq10=" + lotseq10
			 + "&lotname11=" + lotname11 + "&lotid11=" + lotid11 + "&islot11=" + islot11 + "&lotseq11=" + lotseq11
			 + "&lotname12=" + lotname12 + "&lotid12=" + lotid12 + "&islot12=" + islot12 + "&lotseq12=" + lotseq12;
	
		window.returnValue = condition;
		window.close();
	}
	
	//ѡ���������Ժ�
	function getLotAtts(obj){
		lotTool.getLotDetails(obj.value, {callback:function(data){
			var tempstr;
			for(var i=0; i<data.length; i++){
				tempstr = "lotname" + (i+1);
				document.getElementById(tempstr).value = data[i].m_strAttname;		//������������
			}
		}});
		
	}
	
	//��½ҳ��
	function OnLoad(){
		//ͬ��
		DWREngine.setAsync(false);
		selectType('', 'lottype', 'pclx');	//����
		selectLot("", "lot");				//��������
		DWREngine.setAsync(true);
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã��������� -&gt; ������������</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>���ͣ�</td>
     <td class="yx"><select id="lottype"><option value=""></option></select></td>
     <td class="yx1" width="100px" align="right">�������ԣ�</td>
     <td class="xx1"><select id="lot" onchange="getLotAtts(this)"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="y2" align="right">��ע��</td>
     <td colspan="3"><textarea id="remark" cols="70" rows="3"></textarea></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center">��������</td>
     <td class="yx1" align="center">���Դ���</td>
     <td class="yx1" align="center">�Ƿ�ʹ��</td>
     <td class="x1" align="center">˳��</td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname1" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid1" size="15" maxlength="15" value="lotatt1" disabled></td>
     <td class="yx" align="center"><select id="islot1" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq1" size="15" maxlength="2" value="1"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname2" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid2" size="15" maxlength="15" value="lotatt2" disabled></td>
     <td class="yx" align="center"><select id="islot2" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq2" size="15" maxlength="2" value="2"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname3" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid3" size="15" maxlength="15" value="lotatt3" disabled></td>
     <td class="yx" align="center"><select id="islot3" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq3" size="15" maxlength="2" value="3"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname4" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid4" size="15" maxlength="15" value="lotatt4" disabled></td>
     <td class="yx" align="center"><select id="islot4" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq4" size="15" maxlength="2" value="4"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname5" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid5" size="15" maxlength="15" value="lotatt5" disabled></td>
     <td class="yx" align="center"><select id="islot5" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq5" size="15" maxlength="2" value="5"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname6" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid6" size="15" maxlength="15" value="lotatt6" disabled></td>
     <td class="yx" align="center"><select id="islot6" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq6" size="15" maxlength="2" value="6"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname7" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid7" size="15" maxlength="15" value="lotatt7" disabled></td>
     <td class="yx" align="center"><select id="islot7" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq7" size="15" maxlength="2" value="7"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname8" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid8" size="15" maxlength="15" value="lotatt8" disabled></td>
     <td class="yx" align="center"><select id="islot8" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq8" size="15" maxlength="2" value="8"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname9" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid9" size="15" maxlength="15" value="lotatt9" disabled></td>
     <td class="yx" align="center"><select id="islot9" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq9" size="15" maxlength="2" value="9"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname10" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid10" size="15" maxlength="15" value="lotatt10" disabled></td>
     <td class="yx" align="center"><select id="islot10" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq10" size="15" maxlength="2" value="10"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname11" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid11" size="15" maxlength="15" value="lotatt11" disabled></td>
     <td class="yx" align="center"><select id="islot11" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq11" size="15" maxlength="2" value="11"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname12" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid12" size="15" maxlength="15" value="lotatt12" disabled></td>
     <td class="yx" align="center"><select id="islot12" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq12" size="15" maxlength="2" value="12"></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="Save()" id="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>