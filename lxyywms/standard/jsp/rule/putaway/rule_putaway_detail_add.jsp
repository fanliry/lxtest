<%@ page contentType="text/html; charset=GBK"%>
<%
	String putawayid = request.getParameter("putawayid");		//�ϼܹ���ID
	String warehouseid = request.getParameter("warehouseid");	//�����ֿ�ID
%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	//����
	function saveDetailData()
	{
		var sort =  document.getElementById("sort").value;						//����˳λ
		var ruleconfigid = document.getElementById("ruleconfigid").value;		//���򷽷�
		var enableflag = document.getElementById("enableflag").checked?"Y":"N";	//�Ƿ񼤻�
		
		var tozone = document.getElementById("tozone").value;				//Ŀ�����
        var tolocationid = document.getElementById("cargospace_id").value;	//Ŀ���λ
        
        var loc_restrict = document.getElementById("loc_restrict").value;	//��λ����1
        
        var loc_usage1 = document.getElementById("loc_usage1").value;	//��λʹ��
        var loc_usage2 = document.getElementById("loc_usage2").value;
        var loc_usage3 = document.getElementById("loc_usage3").value;
        var loc_usage4 = document.getElementById("loc_usage4").value;
        var loc_usage5 = document.getElementById("loc_usage5").value;
        					
        var loc_handle1 = document.getElementById("loc_handle1").value;	//�洢����
        var loc_handle2 = document.getElementById("loc_handle2").value;
        var loc_handle3 = document.getElementById("loc_handle3").value;
        var loc_handle4 = document.getElementById("loc_handle4").value;
        var loc_handle5 = document.getElementById("loc_handle5").value;
        
        var lotid = document.getElementById("lotid").value;
		var lotatt1 = document.getElementById("lotatt1").value;	//����
		var lotatt2 = document.getElementById("lotatt2").value;
		var lotatt3 = document.getElementById("lotatt3").value;
		var lotatt4 = document.getElementById("lotatt4").value;
		var lotatt5 = document.getElementById("lotatt5").value;
		var lotatt6 = document.getElementById("lotatt6").value;
		var lotatt7 = document.getElementById("lotatt7").value;
		var lotatt8 = document.getElementById("lotatt8").value;
		var lotatt9 = document.getElementById("lotatt9").value;
		var lotatt10 = document.getElementById("lotatt10").value;
		var lotatt11 = document.getElementById("lotatt11").value;
		var lotatt12 = document.getElementById("lotatt12").value;
		
		var remark = document.getElementById("remark").value;	//��ע
		
		if(sort == null || sort.length < 1)
		{
			alert("������˳λ������Ϊ�գ�");
			return;
		}else if(!numChar(sort) || sort==0)
       	{
       		alert("������˳λ��ֻ��Ϊ�����������!");
       		return; 
       	}
       	
		if(ruleconfigid == "" || ruleconfigid.length <1)  
       	{
       		alert("�����򷽷�������Ϊ��!");
       		return; 
       	}
       	
       	if(lotatt1 != null && lotatt1.length > 0){
		
			if(strlen(lotatt1) > 50){
				alert("������1��������");
				return;
			}
		}
       	if(lotatt2 != null && lotatt2.length > 0){
		
			if(strlen(lotatt2) > 50){
				alert("������2��������");
				return;
			}
		}
		if(lotatt3 != null && lotatt3.length > 0){
		
			if(strlen(lotatt3) > 50){
				alert("������3��������");
				return;
			}
		}
       	if(lotatt4 != null && lotatt4.length > 0){
		
			if(strlen(lotatt4) > 50){
				alert("������4��������");
				return;
			}
		}
		if(lotatt5 != null && lotatt5.length > 0){
		
			if(strlen(lotatt5) > 50){
				alert("������5��������");
				return;
			}
		}
		if(lotatt6 != null && lotatt6.length > 0){
		
			if(strlen(lotatt6) > 50){
				alert("������6��������");
				return;
			}
		}
		if(lotatt7 != null && lotatt7.length > 0){
		
			if(strlen(lotatt7) > 50){
				alert("������7��������");
				return;
			}
		}
		if(lotatt8 != null && lotatt8.length > 0){
		
			if(strlen(lotatt8) > 50){
				alert("������8��������");
				return;
			}
		}
		if(lotatt9 != null && lotatt9.length > 0){
		
			if(strlen(lotatt9) > 50){
				alert("������9��������");
				return;
			}
		}
		if(lotatt10 != null && lotatt10.length > 0){
		
			if(strlen(lotatt10) > 50){
				alert("������10��������");
				return;
			}
		}
		if(lotatt11 != null && lotatt11.length > 0){
		
			if(strlen(lotatt11) > 50){
				alert("������11��������");
				return;
			}
		}
		if(lotatt12 != null && lotatt12.length > 0){
		
			if(strlen(lotatt12) > 50){
				alert("������12��������");
				return;
			}
		}
		
		if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
				alert("����ע��������");
				return;
			}
		}
		
		var condition = "&putawayid=<%=putawayid%>&sort=" + sort + "&ruleconfigid=" + ruleconfigid + "&enableflag=" + enableflag + "&remark=" + remark
			+ "&tozone=" + tozone + "&tolocationid=" + tolocationid
			//��λ����
			+ "&loc_restrict=" + loc_restrict
			//��λʹ��
			+ "&loc_usage1=" + loc_usage1 + "&loc_usage2=" + loc_usage2 + "&loc_usage3=" + loc_usage3 
			+ "&loc_usage4=" + loc_usage4 + "&loc_usage5=" + loc_usage5
			//�洢����
        	+ "&loc_handle1=" + loc_handle1 + "&loc_handle2=" + loc_handle2 + "&loc_handle3=" + loc_handle3 
			+ "&loc_handle4=" + loc_handle4 + "&loc_handle5=" + loc_handle5;
        	//����	
			//+ "&lotid=" + lotid + "&lotatt1=" + lotatt1 + "&lotatt2=" + lotatt2 + "&lotatt3=" + lotatt3 + "&lotatt4=" + lotatt4
		    //+ "&lotatt5=" + lotatt5 + "&lotatt6=" + lotatt6 + "&lotatt7=" + lotatt7 + "&lotatt8=" + lotatt8
		    //+ "&lotatt9=" + lotatt9 + "&lotatt10=" + lotatt10 + "&lotatt11=" + lotatt11 + "&lotatt12=" + lotatt12;

	    var msg = "<input type=hidden name='lotid' value='"+lotid+"'>"
				+ "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
				+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
				+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
				+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
				+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
				+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
				+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
				+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
				+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
				+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
				+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
				+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
		
		var array = new Array();	
		array[0] = condition;
		array[1] = msg;
        window.returnValue = array;
		window.close();
	}
	
	//ѡ���������Ժ�
	function getLotAtts(obj){

		lotTool.getLotDetails(obj.value, {callback:function(data){
			if(data !=null){
				var tempstr;
				for(var i=0; i<data.length; i++){
					tempstr = "lotname" + (i+1);
					document.getElementById(tempstr).innerHTML = data[i].m_strAttname + "��";		//������������
					if(data[i].m_strLotatt_flag == '2'){	//����
						document.getElementById("lotatt" + (i+1)).disabled = true;
						document.getElementById("lotatt" + (i+1)).value = "";
					}else{
						document.getElementById("lotatt" + (i+1)).disabled = false;
					}
				}
			}}});
	}
	
	
	//ҳ���½
	function OnLoad(){
	
		selectObject("", "ruleconfigid", "31");					//���򷽷�
		selectAreaList("", "tozone", "<%=warehouseid%>", "1");	//Ŀ�����
		selectType("", "loc_restrict", "kwxz");					//��λ����
		selectTypes("", "loc_usage", "kwsy", 5);				//��λʹ��
		selectTypes("", "loc_handle", "cclx", 5);				//�洢����
		selectLot("", "lotid");									//��������
		
		//�������˳λ
		selectView.getRuleNextSort("<%=putawayid%>", "1", {callback:function(data){
			if(data == 0){
				alert("����˳λȡ�ó���");
			}else{
				document.getElementById("sort").value = data;
			}
		}});
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
<form id="myForm" method="post" action="">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; �ϼܹ��� -&gt; �����ϼܹ�����ϸ</div></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">����˳λ��</td>
     <td class="yx"><input type="text" id="sort" size="2" maxlength="2" class="input_readonly" readonly>
       <input type="checkbox" id="enableflag" class="input_checkbox" checked="checked">��Ч</td>
     <td class="yx1" align="right"><span class="red">*</span>���򷽷���</td>
     <td class="xx1"><select id="ruleconfigid"><option></option></select></td>
   </tr>
   <tr>
   	 <td class="y1" align="right">Ŀ�������</td>
     <td class="x"><select id="tozone" style="width:130px;"><option></option></select></td>
     <td class="y1" align="right">Ŀ���λ��</td>
     <td>
     	<input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('tozone').value,'850','550');" 
       		type="button" value="��" class="button_select">
     </td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>  
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="y1" align="right">��λ���ƣ�</td>
     <td colspan="5"><select id="loc_restrict"><option></option></select></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>  
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">��λʹ�ã�</td>
     <td class="yx"><select id="loc_usage1" style="width:130px;"><option></option></select></td>
     <td class="yx"><select id="loc_usage2" style="width:130px;"><option></option></select></td>
     <td class="yx"><select id="loc_usage3" style="width:130px;"><option></option></select></td>
     <td class="yx"><select id="loc_usage4" style="width:130px;"><option></option></select></td>
     <td class="xx1"><select id="loc_usage5" style="width:130px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="y1" align="right">�洢���ͣ�</td>
     <td class="x"><select id="loc_handle1" style="width:130px;"><option></option></select></td>
     <td class="x"><select id="loc_handle2" style="width:130px;"><option></option></select></td>
     <td class="x"><select id="loc_handle3" style="width:130px;"><option></option></select></td>
     <td class="x"><select id="loc_handle4" style="width:130px;"><option></option></select></td>
     <td><select id="loc_handle5" style="width:130px;"><option></option></select></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>  
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right">�������ԣ�</td>
     <td class="xx1" colspan="5"><select id="lotid" onchange="getLotAtts(this)"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="yx1" width="100px" align="right" id="lotname1">��������1��</td>
     <td class="yx"><input type="text" id="lotatt1" size="20" maxlength="50"></td>
     <td class="yx1" width="100px" align="right" id="lotname2">��������2��</td>
     <td class="yx"><input type="text" id="lotatt2" size="20" maxlength="50"></td>
     <td class="yx1" width="100px" align="right" id="lotname3">��������3��</td>
     <td class="xx1"><input type="text" id="lotatt3" size="20" maxlength="50"></td>
   </tr>
   <tr>
     <td class="yx1" align="right" id="lotname4">��������4��</td>
     <td class="yx"><input type="text" id="lotatt4" size="20" maxlength="50"></td>
     <td class="yx1" align="right" id="lotname5">��������5��</td>
     <td class="yx"><input type="text" id="lotatt5" size="20" maxlength="50"></td>
     <td class="yx1" align="right" id="lotname6">��������6��</td>
     <td class="xx1"><input type="text" id="lotatt6" size="20" maxlength="50"></td>
   </tr>
   <tr>
     <td class="yx1" align="right" id="lotname7">��������7��</td>
     <td class="yx"><input type="text" id="lotatt7" size="20" maxlength="50"></td>
     <td class="yx1" align="right" id="lotname8">��������8��</td>
     <td class="yx"><input type="text" id="lotatt8" size="20" maxlength="50"></td>
     <td class="yx1" align="right" id="lotname9">��������9��</td>
     <td class="xx1"><input type="text" id="lotatt9" size="20" maxlength="50"></td>
   </tr>
   <tr>
     <td class="y1" align="right" id="lotname10">��������10��</td>
     <td class="x"><input type="text" id="lotatt10" size="20" maxlength="50"></td>
     <td class="y1" align="right" id="lotname11">��������11��</td>
     <td class="x"><input type="text" id="lotatt11" size="20" maxlength="50"></td>
     <td class="y1" align="right" id="lotname12">��������12��</td>
     <td><input type="text" id="lotatt12" size="20" maxlength="50"></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table> 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">��ע��</td>
     <td class="xx1" colspan="5"><textarea id="remark" cols="83" rows="3"></textarea></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="saveDetailData()" id="add" value="&nbsp;&nbsp;&nbsp;������ϸ" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>

</body>
</html>