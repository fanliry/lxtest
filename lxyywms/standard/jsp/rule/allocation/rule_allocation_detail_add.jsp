<%@ page contentType="text/html; charset=GBK"%>
<%
	String allocationid = request.getParameter("allocationid");		//�������ID
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
<script type="text/javascript">
<!--
	//����
	function saveDetailData()
	{
		var sort =  document.getElementById("sort").value;						//����˳λ
		var ruleconfigid = document.getElementById("ruleconfigid").value;		//���򷽷�
		var enableflag = document.getElementById("enableflag").checked?"Y":"N";	//�Ƿ񼤻�
		
		var clearloc_flag = document.getElementById("clearloc_flag").checked?"Y":"N";	//�Ƿ����
    	var apart_flag = document.getElementById("apart_flag").checked?"Y":"N";			//�Ƿ����
    	var over_flag = document.getElementById("over_flag").checked?"Y":"N";    		//�Ƿ���λ��������
    	var auto_flag = document.getElementById("auto_flag").checked?"Y":"N";    		//�Ƿ��Զ�������������
    	var bulkpick_flag = document.getElementById("bulkpick_flag").checked?"Y":"N";   //�Ƿ�洢λ��ѡ
    	var part_flag = document.getElementById("part_flag").checked?"Y":"N";    		//�Ƿ���
    	var part_allocation_flag = document.getElementById("part_allocation_flag").checked?"Y":"N";//�Ƿ������ַ���
    	var unit_flag = document.getElementById("unit_flag").value;				//������λ
    	
    	var tozone = document.getElementById("tozone").value;					//����
        var tolocationid = document.getElementById("cargospace_id").value;		//��λ
        var toalleys = "";		//���
        var alleys = document.getElementsByName("chkAlley");
        for(var i=0; i<alleys.length; i++){
        	if(alleys[i].checked){
        		toalleys+=alleys[i].value + ",";
        	}
        }
		
		var remark = document.getElementById("remark").value;	//��ע
		
		if(sort == null || sort.length < 1){
		
			alert("������˳λ������Ϊ�գ�");
			return;
		}else if(!numChar(sort) || sort==0){
       	
       		alert("������˳λ��ֻ��Ϊ�����������!");
       		return; 
       	}
       	
		if(ruleconfigid == "" || ruleconfigid.length <1){
       	
       		alert("�����򷽷�������Ϊ��!");
       		return; 
       	}
       	
       	if(unit_flag == "" || unit_flag.length <1){
       		alert("��������λ������Ϊ��!");
       		return; 
       	}
		
		if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
				alert("����ע��������");
				return;
			}
		}
		
		var condition = "&allocationid=<%=allocationid%>&sort=" + sort + "&ruleconfigid=" + ruleconfigid
		    + "&enableflag=" + enableflag + "&remark=" + remark
			+ "&clearloc_flag=" + clearloc_flag + "&apart_flag=" + apart_flag + "&over_flag=" + over_flag
			+ "&auto_flag=" + auto_flag + "&bulkpick_flag=" + bulkpick_flag + "&part_flag=" + part_flag
		    + "&part_allocation_flag=" + part_allocation_flag + "&unit_flag=" + unit_flag
			+ "&tozone=" + tozone + "&tolocationid=" + tolocationid + "&toalleys=" + toalleys.substring(0, toalleys.length-1);
									
        window.returnValue = condition;
		window.close();
	}
	
	//ҳ���½
	function OnLoad(){
	
		selectAreaList("", "tozone", "<%=warehouseid%>", "1");	//Ŀ�����
		selectObject("", "ruleconfigid", "32");			//���򷽷�
		selectType('1', 'unit_flag', 'allounit');		//������λ
		
		//�������˳λ
		selectView.getRuleNextSort("<%=allocationid%>", "2", {callback:function(data){
			if(data == 0){
				alert("����˳λȡ�ó���");
			}else{
				document.getElementById("sort").value = data;
			}
		}});
	}
	
	//���ݿ������������б�
	function getAlleyList(){
	
		var tozone = document.getElementById("tozone").value;	//����
		selectView.getAlleyList("", tozone, {callback:function(data){
			
			var ss="&nbsp;";
			for(var i = 0; i < data.length; i++){
		    	ss += data[i].strName + "<input type='checkbox' name='chkAlley' value='"+ data[i].strId + "'/>&nbsp;";
			}
			
			document.getElementById("alleyarea").innerHTML = ss;
		
		}});
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
<form id="myForm" method="post" action="">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; ������� -&gt; �������������ϸ</div></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td width="120px" class="yx1" align="right">����˳λ��</td>
     <td class="yx"><input type="text" id="sort" size="2" maxlength="2" class="input_readonly" readonly>
       <input type="checkbox" id="enableflag" class="input_checkbox" checked="checked">��Ч</td>
     <td width="120px" class="yx1" align="right"><span class="red">*</span>���򷽷���</td>
     <td class="xx1"><select id="ruleconfigid"><option></option></select></td>
   </tr>
   <tr>
   	 <td class="yx1" align="right">������</td>
     <td class="yx"><select id="tozone" style="width:130px;" onChange="getAlleyList()"><option></option></select></td>
     <td class="yx1" align="right">��λ��</td>
     <td class="xx1">
     	<input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('tozone').value,'850','550');" 
       		type="button" value="��" class="button_select">
     </td>
   </tr>
   <tr height="50px">
   	 <td class="yx1" align="right">�����</td>
     <td class="xx1" colspan="3"><div id="alleyarea">&nbsp;</div></td>
   </tr>
   <tr>
   	 <td class="yx1" align="right">��֣�</td>
     <td class="yx"><input type="checkbox" id="clearloc_flag" class="input_checkbox"></td>
     <td class="yx1" align="right">���㣺</td>
     <td class="xx1"><input type="checkbox" id="apart_flag" class="input_checkbox" ></td>
   </tr>
   <tr>
   	 <td class="yx1" align="right">���λ�������䣺</td>
     <td class="yx"><input type="checkbox" id="over_flag" class="input_checkbox"></td>
     <td class="yx1" align="right">�Զ�������������</td>
     <td class="xx1"><input type="checkbox" id="auto_flag" class="input_checkbox" ></td>
   </tr>
   <tr>
   	 <td class="yx1" align="right">�洢λ��ѡ��</td>
     <td class="yx"><input type="checkbox" id="bulkpick_flag" class="input_checkbox"></td>
     <td class="yx1" align="right">�����</td>
     <td class="xx1"><input type="checkbox" id="part_flag" class="input_checkbox" ></td>
   </tr>
   <tr>
   	 <td class="yx1" align="right">���ַ��䣺</td>
     <td class="yx"><input type="checkbox" id="part_allocation_flag" class="input_checkbox"></td>
     <td class="yx1" align="right"><span class="red">*</span>������λ��</td>
     <td class="xx1"><select id="unit_flag"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��ע��</td>
     <td class="xx1" colspan="3"><textarea id="remark" rows="3"></textarea></td>
   </tr>
   <tr>
     <td height="27" align="center"  colspan="4">
        <input type="button" onclick="saveDetailData()" id="add" value="&nbsp;&nbsp;&nbsp;������ϸ" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>

</body>
</html>