<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;

	function updateData(){
		var obj = document.getElementsByName("timespace");	//��εļ��ʱ��
		var shift_timespace = document.getElementsByName("timespace").value;
		if(obj[0].checked){
			shift_timespace = obj[0].value;
		}else if(obj[1].checked){
			shift_timespace = obj[1].value;
		}
		
		obj = document.getElementsByName("type");			//�������
		var shift_type = "";
		if(obj[0].checked){
			shift_type = obj[0].value;
		}else if(obj[1].checked){
			shift_type = obj[1].value;
		}else if(obj[2].checked){
			shift_type = obj[2].value;
		}
		
		var obj = document.getElementsByName("inout");	//��ε��������
		var shift_inout = "";
		if(obj[0].checked){
			shift_inout = obj[0].value;
		}else if(obj[1].checked){
			shift_inout = obj[1].value;
		}
		
		var op_man1 = document.getElementById("op_man_name1").value;	//�װฺ����
		var onduty1 = document.getElementById("onduty1").value;			//�װ൱����Ա
		var op_man2 = document.getElementById("op_man_name2").value;	//�Ұฺ����
		var onduty2 = document.getElementById("onduty2").value;			//�Ұ൱����Ա
		var op_man3 = document.getElementById("op_man_name3").value;	//���ฺ����
		var onduty3 = document.getElementById("onduty3").value;			//���൱����Ա
		var op_man4 = document.getElementById("op_man_name4").value;	//���ฺ����
		var onduty4 = document.getElementById("onduty4").value;			//���൱����Ա
		
		condition = "&shift_timespace=" + shift_timespace + "&shift_type=" + shift_type + "&shift_inout=" + shift_inout
			+ "&op_man1=" + op_man1 + "&onduty1=" + onduty1 + "&op_man2=" + op_man2 + "&onduty2=" + onduty2
			+ "&op_man3=" + op_man3 + "&onduty3=" + onduty3+ "&op_man4=" + op_man4 + "&onduty4=" + onduty4;
		window.location.href = ac + "baseShiftAction&method=ricoExecEditSet" + condition;
	}
	
	//��ȡ�û���
	function GetUser(i)
	{
		var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_user.jsp','','dialogWidth=700px;dialogHeight=440px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		if(i==1){
	 			document.getElementById("op_man_id1").value = tem[0];
	 			document.getElementById("op_man_name1").value = tem[2];
	 		}else if(i==2){
	 			document.getElementById("op_man_id2").value = tem[0];
	 			document.getElementById("op_man_name2").value = tem[2];
	 		}else if(i==3){
	 			document.getElementById("op_man_id3").value = tem[0];
	 			document.getElementById("op_man_name3").value = tem[2];
	 		}else if(i==4){
	 			document.getElementById("op_man_id4").value = tem[0];
	 			document.getElementById("op_man_name4").value = tem[2];
	 		}
	 		
	 	}
	}
	
	//��ȡ����û���
	function GetUsers(index){
	     var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_users.jsp','','dialogWidth=700px;dialogHeight=440px');
	     var str = "";
	     if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		if(tem != null){
	 		 for(var i=0;i<tem.length;i++){
	 		   str += tem[i]+",";
	 		 }
	 		}
	 	}
	 	if(str != null){
		  str = str.substring(0, str.length-1);
		}

	 	if(index==1){
 			document.getElementById("onduty1").value = str;
 		}else if(index==2){
 			document.getElementById("onduty2").value = str;
 		}else if(index==3){
 			document.getElementById("onduty3").value = str;
 		}else if(index==4){
 			document.getElementById("onduty4").value = str;
 		}
	}
	
	function setBtn(i){

	 	if(i==2){
 			document.getElementById("op_man_id3").value = "";
	 		document.getElementById("op_man_name3").value = "";
	 		document.getElementById("onduty3").value = "";
	 		document.getElementById("btnop3").disabled = true;
	 		document.getElementById("btnduty3").disabled = true;
	 		
	 		document.getElementById("op_man_id4").value = "";
	 		document.getElementById("op_man_name4").value = "";
	 		document.getElementById("onduty4").value = "";
	 		document.getElementById("btnop4").disabled = true;
	 		document.getElementById("btnduty4").disabled = true;
	 		
 		}else if(i==3){
	 		document.getElementById("btnop3").disabled = false;
	 		document.getElementById("btnduty3").disabled = false;
 		
 			document.getElementById("op_man_id4").value = "";
	 		document.getElementById("op_man_name4").value = "";
	 		document.getElementById("onduty4").value = "";
	 		document.getElementById("btnop4").disabled = true;
	 		document.getElementById("btnduty4").disabled = true;
	 		
 		}else if(i==4){
 			document.getElementById("btnop3").disabled = false;
	 		document.getElementById("btnduty3").disabled = false;
	 		
	 		document.getElementById("btnop4").disabled = false;
	 		document.getElementById("btnduty4").disabled = false;
 		}
	}
	
	function onLoad(){

		//ȡ�ð��������Ϣ
		DWREngine.setAsync(false);
		getTool.getShiftconfig({callback:function(result){
			if(result.length > 0){
				var tem = result.split("|");
				//���ʱ����
				var obj1 = document.getElementsByName("timespace");
				if(tem[0]==12){
					obj1[0].checked = true;
				}else if(tem[0]==24){
					obj1[1].checked = true;
				}
				
				//�������
				var obj2 = document.getElementsByName("type");			
				if(tem[1]==2){
					obj2[0].checked = true;
					document.getElementById("btnop3").disabled = true;
			 		document.getElementById("btnduty3").disabled = true;
			 		document.getElementById("btnop4").disabled = true;
			 		document.getElementById("btnduty4").disabled = true;
				}else if(tem[1]==3){
					obj2[1].checked = true;
					document.getElementById("btnop4").disabled = true;
			 		document.getElementById("btnduty4").disabled = true;
				}else if(tem[1]==4){
					obj2[2].checked = true;
				}
				
				//�������
				var obj3 = document.getElementsByName("inout");
				if(tem[2]=="Y"){
					obj3[0].checked = true;
				}else if(tem[2]=="N"){
					obj3[1].checked = true;
					
				}
				
				//�װฺ���˺͵�����Ա
				document.getElementById("op_man_name1").value = tem[3];
 				document.getElementById("onduty1").value = tem[4];
				
				//�Ұฺ���˺͵�����Ա
				document.getElementById("op_man_name2").value = tem[5];
 				document.getElementById("onduty2").value = tem[6];
 				
 				//���ฺ���˺͵�����Ա
 				document.getElementById("op_man_name3").value = tem[7];
 				document.getElementById("onduty3").value = tem[8];
 				
 				//���ฺ���˺͵�����Ա
 				document.getElementById("op_man_name4").value = tem[9];
 				document.getElementById("onduty4").value = tem[10];
	    	}
		}});
		DWREngine.setAsync(true);
	}
-->	
</script>
</head>

<body onload="onLoad()">

<div class="con_bk">
      
  <!-- ======== ��ǰλ�� ======== -->
  <div class="wz">
	<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ���Ĭ����Ϣ����</span></div>
	<div id="caozuo" class="f_r">
	  <ul>
		<li class="tubiao2"><a href="#" onclick="updateData()">����</a></li>
	  </ul>
	</div>
  </div>
		
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td height="5px"></td>
	</tr>
  </table>
      
  <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
    <tr>
      <td width="150" class="yx1" align="right">��/ҹ��</td>
      <td class="xx1">
      	<input type="radio" name="timespace" value="12"/>&nbsp;12Сʱ��&nbsp;&nbsp;&nbsp;&nbsp;
   		<input type="radio" name="timespace" value="24"/>&nbsp;24Сʱ��&nbsp;</td>
    </tr>
    <tr>
      <td class="yx1" align="right">������֣�</td>
      <td class="xx1">
      	<label><input type="radio" name="type" value="2" onclick="setBtn(2);"/>&nbsp;2���ƣ�����&nbsp;</label>
   		<label><input type="radio" name="type" value="3" onclick="setBtn(3);"/>&nbsp;3���ƣ����ұ� &nbsp;</label>
   		<label><input type="radio" name="type" value="4" onclick="setBtn(4);"/>&nbsp;4���ƣ����ұ���&nbsp;</label>
    </tr>
    <tr>
      <td class="y1" align="right">�Ƿ�������/���⣺</td>
      <td>
      	<label><input type="radio" name="inout" value="Y"/>&nbsp;��&nbsp;</label>
   		<label><input type="radio" name="inout" value="N"/>&nbsp;�� &nbsp;</label>
    </tr>
  </table> 
		
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td height="10px"></td>
	</tr>
  </table>
		  
  <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
    <tr>
      <td width="150" class="yx1" align="right">�װฺ���ˣ�</td>
      <td class="xx1">
        <input id="op_man_id1" type="hidden"><input id="op_man_name1" type="text" size="16" readonly>
        <input name="btnop1" type="button" class="button_select" value="��" onClick="GetUser(1)">
      </td>
    </tr>
    <tr>
      <td class="y2" align="right">�װ൱����Ա��</td>
      <td>
        <textarea rows="2" style="width:550px;height:40px" id="onduty1" readonly></textarea>
        <input name="btnduty1" type="button" class="button_select" value="��" onClick="GetUsers(1)">
      </td>
    </tr>
  </table>
		 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td height="10px"></td>
	</tr>
  </table>
  
  <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
    <tr>
      <td width="150" class="yx1" align="right">�Ұฺ���ˣ�</td>
      <td class="xx1">
        <input id="op_man_id2" type="hidden"><input id="op_man_name2" type="text" size="16" readonly>
        <input name="btnop2" type="button" class="button_select" value="��" onClick="GetUser(2)">
      </td>
    </tr>
    <tr>
      <td class="y2" align="right">�Ұ൱����Ա��</td>
      <td>
        <textarea rows="2" style="width:550px;height:40px" id="onduty2" readonly></textarea>
        <input name="btnduty2" type="button" class="button_select" value="��" onClick="GetUsers(2)">
      </td>
    </tr>
  </table>
		 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td height="10px"></td>
	</tr>
  </table>
  
  <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
    <tr>
      <td width="150" class="yx1" align="right">���ฺ���ˣ�</td>
      <td class="xx1">
        <input id="op_man_id3" type="hidden"><input id="op_man_name3" type="text" size="16" readonly>
        <input name="btnop3" type="button" class="button_select" value="��" onClick="GetUser(3)">
      </td>
    </tr>
    <tr>
      <td class="y2" align="right">���൱����Ա��</td>
      <td>
        <textarea rows="2" style="width:550px;height:40px" id="onduty3" readonly></textarea>
        <input name="btnduty3" type="button" class="button_select" value="��" onClick="GetUsers(3)">
      </td>
    </tr>
  </table>		  

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td height="10px"></td>
	</tr>
  </table>
  
  <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
    <tr>
      <td width="150" class="yx1" align="right">���ฺ���ˣ�</td>
      <td class="xx1">
        <input id="op_man_id4" type="hidden"><input id="op_man_name4" type="text" size="16" readonly>
        <input name="btnop4" type="button" class="button_select" value="��" onClick="GetUser(4)">
      </td>
    </tr>
    <tr>
      <td class="y2" align="right">���൱����Ա��</td>
      <td>
        <textarea rows="2" style="width:550px;height:40px" id="onduty4" readonly></textarea>
        <input name="btnduty4" type="button" class="button_select" value="��" onClick="GetUsers(4)">
      </td>
    </tr>
  </table>			 	
</div>

</body>
</html>
