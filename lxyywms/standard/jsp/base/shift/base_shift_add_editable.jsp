<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseShiftconfig" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseShift" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
	//���������Ϣ
	BaseShiftconfig shiftconfig = (BaseShiftconfig)request.getAttribute("shiftconfig");
	String timespace = shiftconfig.getTimespace();	//��μ��ʱ��
	String type = shiftconfig.getType();			//�������
	String inout = shiftconfig.getInout();			//���������
	String op_man1 = shiftconfig.getOpman1();		//�װฺ����
	String onduty1 = shiftconfig.getOnduty1();		//�װ൱����Ա
	String op_man2 = shiftconfig.getOpman2();		//�Ұฺ����
	String onduty2 = shiftconfig.getOnduty2();		//�Ұ൱����Ա
	String op_man3 = shiftconfig.getOpman3();		//���ฺ����
	String onduty3 = shiftconfig.getOnduty3();		//���൱����Ա
	String op_man4 = shiftconfig.getOpman4();		//���ฺ����
	String onduty4 = shiftconfig.getOnduty4();		//���൱����Ա
	
	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
	Date lasttime = null;
	
	//�����	
	BaseShift lastshift = (BaseShift)request.getAttribute("lastshift");  
	String lastshiftid = "";		//���id
	String lastshiftname = "";		//�����
	String lastshifttime = "";		//���ʱ��
	String lastdaynight = "";		//�װࡢҹ��
	String lastshiftTurn = "";		//�ס��ҡ�������
	String shifttime = "";
	String daynight = "";
	
	if(lastshift != null){
		lastshiftid = lastshift.getM_strShiftId();
		lastshiftname = lastshift.getM_strShiftName();
		lastshifttime = lastshift.getM_strShiftDate();
		lastdaynight = lastshift.getM_strDayNight();
		lastshiftTurn = lastshift.getM_strThreenum();
		
		lasttime = dfs.parse(lastshifttime);
		
		if(timespace.equals("12")){
	    	if(lastdaynight.equals("��")){
	    		//�����һ����ǰװ࣬���ھ�һ�� 
	    		shifttime = lastshifttime;	
	    		daynight = "ҹ";
	    	}else if(lastdaynight.equals("ҹ")){
	    		//�����һ�����ҹ�࣬���ھ�����һ���
				shifttime = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
				daynight = "��";
			}
		} else if(timespace.equals("24")){
			//ʱ������24�Ļ�������24Сʱ
			shifttime = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
		}
	}
	
	//����� ���
	BaseShift lastshiftin = (BaseShift)request.getAttribute("lastshiftin");  
	String lastshiftidin = "";		//���id
	String lastshiftnamein = "";	//�����
	String lastshifttimein = "";	//���ʱ��
	String lastdaynightin = "";		//�װࡢҹ��
	String lastshiftTurnin = "";	//�ס��ҡ�������
	String shifttimein = "";
	String daynightin = "";
	
	if(lastshiftin != null){
		lastshiftidin = lastshiftin.getM_strShiftId();
		lastshiftnamein = lastshiftin.getM_strShiftName();
		lastshifttimein = lastshiftin.getM_strShiftDate();
		lastdaynightin = lastshiftin.getM_strDayNight();
		lastshiftTurnin = lastshiftin.getM_strThreenum();

    	lasttime = dfs.parse(lastshifttimein);
    	
	    //�����12Сʱ����
	    if(timespace.equals("12")){
	    	if(lastdaynightin.equals("��")){
	    		//�����һ����ǰװ࣬���ھ�һ�� 
	    		shifttimein = lastshifttimein;	
	    		daynightin = "ҹ";
	    	}else if(lastdaynightin.equals("ҹ")){
	    		//�����һ�����ҹ�࣬���ھ�����һ���
				shifttimein = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
				daynightin = "��";
			}
		} else if(timespace.equals("24")){
			//ʱ������24�Ļ�������24Сʱ
			shifttimein = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
		}
	}
	
	//����� ����
	BaseShift lastshiftout = (BaseShift)request.getAttribute("lastshiftout");  
	String lastshiftidout = "";		//���id
	String lastshiftnameout = "";	//�����
	String lastshifttimeout = "";	//���ʱ��
	String lastdaynightout = "";	//�װࡢҹ��
	String lastshiftTurnout = "";	//�ס��ҡ�������
	String shifttimeout = "";
	String daynightout = "";
	
	if(lastshiftout != null){
		lastshiftidout = lastshiftout.getM_strShiftId();		//���id
		lastshiftnameout = lastshiftout.getM_strShiftName();	//�����
		lastshifttimeout = lastshiftout.getM_strShiftDate();	//���ʱ��
		lastdaynightout = lastshiftout.getM_strDayNight();	//�װࡢҹ��
		lastshiftTurnout = lastshiftout.getM_strThreenum();	//�ס��ҡ�������
	
    	lasttime = dfs.parse(lastshifttimeout);
    	
	    //�����12Сʱ����
	    if(timespace.equals("12")){
	    	if(lastdaynightout.equals("��")){
	    		//�����һ����ǰװ࣬���ھ�һ�� 
	    		shifttimeout = lastshifttimeout;	
	    		daynightout = "ҹ";
	    	}else if(lastdaynightout.equals("ҹ")){
	    		//�����һ�����ҹ�࣬���ھ�����һ���
				shifttimeout = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
				daynightout = "��";
			}
		} else if(timespace.equals("24")){
			//ʱ������24�Ļ�������24Сʱ
			shifttimeout = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
		}
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script>
<!--
	//��ȡ�û���
	function GetUser()
	{
		var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_user.jsp','','dialogWidth=700px;dialogHeight=440px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		document.getElementById("op_man_id").value = tem[0];
	 		document.getElementById("op_man_name").value = tem[2];
	 		}
	}
	
	//��ȡ����û���
	function GetUsers(){
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
		 if(document.getElementById("onduty").value == ""){
	 	 	document.getElementById("onduty").value += str;
	 	 }else{
	 	 	document.getElementById("onduty").value += "," + str;
	 	 }
	}
	
	//����η����ı�ʱִ�еĲ���
	function shiftOnchage(){
		
		//ȡ�ö�Ӧ��εĸ����˺͵�����Ա
		setOpman(document.getElementById("shift").value);
	}
	
	//����������ͷ����ı�ʱִ�еĲ���
	function inoutOnchange(){
	
	    var in_out_type = document.getElementById("in_out_type").value;
	    var obj = document.getElementById("shift");
	    var obj1 = document.getElementsByName("daynight"); 
	    
	    if(in_out_type != null && in_out_type == '1'){ //������͵İ���б�
	    	
	    	//�����
	    	document.getElementById("lastShiftId").value = "<%=lastshiftidin%>";	
	    	document.getElementById("lastShiftName").value = "<%=lastshiftnamein%>";
	    	
	    	//��/ҹ
			if("<%=daynightin%>" == "��"){
				obj1[0].checked = true;
			}else if("<%=daynightin%>" == "ҹ"){
				obj1[1].checked = true;
			}
	    	
	    	//�������
	    	document.getElementById("indate").value = "<%=shifttimein%>";
	    	
	    	//���ð�ε�Ĭ��ֵ(���һ����ε���һ���)
			for(i=0; i<obj.options.length; i++){
				if(obj.options[i].value == "<%=lastshiftTurnin%>"){   
					if(i == obj.options.length-1){
						obj.options[1].selected = true;
					} else {  
						obj.options[i+1].selected = true;
					}
					break;
				}
			}
			
			//���ö�Ӧ��εĸ����˺͵�����Ա
			setOpman(document.getElementById("shift").value);
	    	
	    }else if(in_out_type != null && in_out_type == '2'){ //�������͵İ���б�
	    
	    	//�����
	    	document.getElementById("lastShiftId").value = "<%=lastshiftidout%>";
	    	document.getElementById("lastShiftName").value = "<%=lastshiftnameout%>";
	    	
	    	//��/ҹ
			if("<%=daynightout%>" == "��"){
				obj1[0].checked = true;
			}else if("<%=daynightout%>" == "ҹ"){
				obj1[1].checked = true;
			}
	    	
	    	//�������
	    	document.getElementById("indate").value = "<%=shifttimeout%>";
	    	
	    	//���ð�ε�Ĭ��ֵ(���һ����ε���һ���)
			for(i=0; i<obj.options.length; i++){
				if(obj.options[i].value == "<%=lastshiftTurnout%>"){   
					if(i == obj.options.length-1){
						obj.options[1].selected = true;
					} else {  
						obj.options[i+1].selected = true;
					}
					break;
				}
			}
			
			//���ö�Ӧ��εĸ����˺͵�����Ա
			setOpman(document.getElementById("shift").value);
	    	
	    }else{
	    	//�����
	    	document.getElementById("lastShiftId").value = "";
	    	document.getElementById("lastShiftName").value = "";
	    	
	    	//��/ҹ
	    	
	    	//�������
	    	document.getElementById("indate").value = "";
	    	
	    	//���
	    	obj.options[0].selected = true;
	    	
	    	//���ö�Ӧ��εĸ����˺͵�����Ա
			setOpman(document.getElementById("shift").value);
	    	
	    }
	}
	
	//���ö�Ӧ��εĸ����˺͵�����Ա��������������úõ�ֵ��
	function setOpman(lastshiftTurn){
	
		var opman = "";
		var onduty = "";
		
		if(lastshiftTurn=="��"){
			opman = "<%=op_man1%>";
	 		onduty = "<%=onduty1%>";
	 		
		}else if(lastshiftTurn=="��"){
			opman = "<%=op_man2%>";
	 		onduty = "<%=onduty2%>";
	 		
		}else if(lastshiftTurn=="��"){
			opman = "<%=op_man3%>";
	 		onduty = "<%=onduty3%>";
	 		
		}else if(lastshiftTurn=="��"){
			opman = "<%=op_man4%>";
	 		onduty = "<%=onduty4%>";
		}
		
		document.getElementById("op_man_name").value = opman;
	 	document.getElementById("onduty").value = onduty;
	}
	
		
	//����  
	function onSave()
	{
		var lastShiftId = document.getElementById("lastShiftId").value;
		var op_man_name = document.getElementById("op_man_name").value;
		var shift = document.getElementById("shift").value;
		var indate = document.getElementById("indate").value;
		var onduty = document.getElementById("onduty").value;
		var daynight = "";
		var shiftname = indate;
		var in_out_type = 0;
		
		//���������
		if("<%=inout%>"=="Y"){
			in_out_type = document.getElementById("in_out_type").value;			
			if(in_out_type == "" || in_out_type.length < 1)
			{
				alert("����������͡�����Ϊ�գ�");
				return;
			}
		}
		
		//�װ� ҹ��
		if("<%=timespace%>"==12){
			var objRadio = document.getElementsByName("daynight"); 
			if(objRadio[0].checked){
				daynight = objRadio[0].value;
			}else if(objRadio[1].checked){
				daynight = objRadio[1].value;
			}
			
			if(daynight == "" || daynight.length < 1)
			{
				alert("����/ҹ������Ϊ�գ�");
				return;
			}
			shiftname += daynight;
		}

		if(indate == "" || indate.length < 1)
		{
			alert("��������ڡ�����Ϊ�գ�");
			return;
		}
		
		if(shift == "" || shift.length < 1)
		{
			alert("����Ρ�����Ϊ�գ�");
			return;
		}
		shiftname += shift;
		
		if(op_man_name == "" || op_man_name.length < 1)
		{
			alert("�������ˡ�����Ϊ�գ�");
			return;
		}

		if(onduty == "" || onduty.length < 1)
		{
			alert("��������Ա������Ϊ�գ�");
			return;
		}
		
		var backmsg = "&lastShiftId=" + lastShiftId + "&daynight=" + daynight + "&indate=" + indate + "&shift=" + shift 
			+ "&op_man_name=" + op_man_name + "&onduty=" + onduty + "&shiftname=" + shiftname + "&inouttype=" + in_out_type;
		
		window.returnValue = backmsg;
		window.close();
	}
	
	function onLoad(){
	
		var obj = document.getElementById("shift");
	    var obj1 = document.getElementsByName("daynight"); 
		var shiftTurn = "<%=lastshiftTurn%>";
		var inouttype = "<%=inout%>";
		var daynight = "<%=daynight%>";
		
		//�����������ʱ������Ĭ��ֵ
		if(inouttype == "N"){
			//�����
	    	document.getElementById("lastShiftId").value = "<%=lastshiftid%>";	
	    	document.getElementById("lastShiftName").value = "<%=lastshiftname%>";
	    	
	    	//��/ҹ
			if(daynight == "��"){
				obj1[0].checked = true;
			}else if("<%=daynight%>" == "ҹ"){
				obj1[1].checked = true;
			}
	    	
	    	//�������
			document.getElementById("indate").value = "<%=shifttime%>";
	    	
	    	//���
	    	if(shiftTurn == ""){
				obj.options[0].selected = true;
			}else{
				for(i=0; i<obj.options.length; i++){
					if(obj.options[i].value == shiftTurn){   
						if(i == obj.options.length-1){
							obj.options[1].selected = true;
						} else {  
							obj.options[i+1].selected = true;
						}
						break;
					}
				}
			}
			
			//ȡ�ö�Ӧ��εĸ����˺͵�����Ա
			setOpman(document.getElementById("shift").value);
		}
	}

-->
</script>
</head>

<body onload="onLoad()">

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��ι��� -&gt; ���������Ϣ</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <%if(inout.equals("Y")) {%>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>������ͣ�</td>
      <td class="xx1">
     	<select name="in_out_type" style="width:130px" onchange="inoutOnchange();">
	      <option value="">--��ѡ��--</option>
	      <option value="1">���</option>
	      <option value="2">����</option>
	    </select>
	  </td>
    </tr>
    <%}%>
    <tr>
      <td class="yx1" align="right" width="100px">����Σ�</td>
      <td class="xx1">
        <input name="lastShiftId" type="hidden" size="20" value="">
     	<input name="lastShiftName" type="text" size="20" value="" readonly class="input_readonly"></td>
    </tr> 
    <%if(timespace.equals("12")) {%>
    <tr id="trTime">
      <td class="yx1" align="right"><span class="red">*</span>��/ҹ��</td>
      <td class="xx1">
	    <input type="radio" name="daynight" value="��"/>��
	    &nbsp;<input type="radio" name="daynight" value="ҹ" />ҹ
	  </td>
    </tr>
    <%}%>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>������ڣ�</td>
      <td class="xx1"><input name="indate" type="text" size="20" onfocus="calendar();" class="Wdate" class="input_readonly" readonly value=""></td>
    </tr>   
    <tr>
	  <td class="yx1" align="right"><span class="red">*</span>��Σ�</td>
      <td class="xx1">
     	<select name="shift" style="width:130px" onChange="shiftOnchage()">
     	  <option value="">--��ѡ��--</option>
     	  <option value="��">��</option>
     	  <option value="��">��</option>
     	  <%if(Integer.parseInt(type)>2) {%><option value="��">��</option><%}%>
     	  <%if(Integer.parseInt(type)>3) {%><option value="��">��</option><%}%>
     	</select>
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�����ˣ�</td>
      <td class="xx1">
        <input name="op_man_id" type="hidden"><input name="op_man_name" type="text" size="16" readonly>
        <input name="moreBtn" type="button" class="button_select" value="��" onClick="GetUser()">
      </td>
    </tr>
	<tr>
	  <td class="yx1" align="right"><span class="red">*</span>������Ա�� </td>
	  <td class="xx1" align="left">
	  	<textarea rows="2" style="width:300px;height:40px" id="onduty"></textarea>
	  	<input name="moreBtn" type="button" class="button_select" value="��" onClick="GetUsers()"></td>   
	</tr>	
    <tr>
      <td height="27" colspan="2" align="center">
        <input onClick="onSave()"type="button" name="button1" value="����" class="BUTTON_STYLE1">
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
   
</body>
</html>

