<%@ page contentType="text/html; charset=GBK"%>
<%
 	//����ID
    String strOutBoundId = request.getParameter("outid");

 %>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";	
	var outId="";//A�ͻ�����ID
	function ChangeOp(flag)
    {
    	if (outId == null || outId.length < 1) {
			alert("A�ͻ�����Ϊ�գ���ѡ��һ�ŵ��ݣ�");
			return;
		}
    	//A�ͻ�����ID
    	if(flag == "1")
    	{
    		down.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc.jsp?aoutid="+outId;
    	}else if(flag == "2"){
    		down.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_B.jsp?aoutid="+outId;
    	}
    }
	//****************************************************
	function OnLoad() 
	{
		outId = window.dialogArguments;
		document.getElementById("warehouse_out_id").value = outId;
		var back_msg = "<%=request.getAttribute("back_msg") == null ? "" : (String)request.getAttribute("back_msg")%>";
		if (back_msg != "") 
		{
			if(back_msg == "Y")
			{
				alert("�����ɹ�!");
			}else
			{
				alert(back_msg);
			}
		}
		
	}
	//����
	function moveDown()
	{
		//1: ����������ҵ��ϸID
		var detailid = "";
		var k=0;
		var check_ids = up.document.getElementsByName("check_id");
		if(check_ids.length == 0)
		{
			alert("A�ͻ������޼�¼���������!");
			return;
		}else
		{
			for(i=0; i<check_ids.length; i++)
			{
				if(check_ids[i].checked)
				{
					detailid = detailid + check_ids[i].value + ",";
					k+=1;
				}
			}
		}
		if(k == 0)
		{
			alert("��ѡ��һ���������¼��һ����¼�����޸������������������������ơ�");
			return;
		}else{
			detailid = detailid.substring(0, detailid.length-1);
		}
		var msg = "ȷ�����Ƶ�";
		var select = document.getElementById("atb").checked;
		//��ѡ�����Ϊ��A�ͻ�-> B�ͻ�
		var bdetailid = "";	//B�ͻ�������ϸID
		var bvoiceid = "";	//B�ͻ�����ID
		var warehouseid = "";//�ֿ�ID
		var zcwhareaid = ""; //�ݴ���ID
		if(select)
		{
			//B�ͻ��ĵ�����ϸID
			var bcheck_ids = down.list.document.getElementsByName("check_id");	
			for(var i=0; i<bcheck_ids.length; i++)
			{
				if(bcheck_ids[i].checked == true){
					bdetailid = bcheck_ids[i].value ;
					bvoiceid = bcheck_ids[i].id ;
					break;
				}
			}
			if(bdetailid == ""){
				alert("��ѡ��B�ͻ���һ����¼��");
				return;
			}
			msg+="B�ͻ��У�";
		}else{
			msg+="�ݴ����У�";
			warehouseid = down.document.getElementById("warehouseid").value;//�ֿ�ID
		 	zcwhareaid = down.document.getElementById("zcwhareaid").value;  //�ݴ���ID
		 	if(zcwhareaid == ""){
				alert("�ݴ�������Ϊ�գ�");
				return;
			}
		}
		if(k == 1){//������¼����
			var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=4&jobdetailid="+detailid, '', 
				"dialogWidth=500px;dialogHeight=600px; ");
			if(result != null && result.length > 0) 
			{
				if(select)//A�ͻ�->B�ͻ�
				{
					up.location.href = strUrl + "sendAction&method=ricoExecMove&detailid="+detailid+"&flag=2&binvoiceid=" + bvoiceid + "&binvoicedetailid="+ bdetailid + result;
				}else{//A�ͻ�->�ݴ���
					up.location.href = strUrl + "sendAction&method=ricoExecMove&detailid="+detailid+"&flag=1&warehouseid=" + warehouseid + "&whAreaId=" + zcwhareaid + result;
				}
			}	
		}else{//������¼
			if(confirm(msg)) 
			{
				if(select)//A�ͻ�->B�ͻ�
				{
					up.location.href = strUrl + "sendAction&method=ricoExecBatchMove&detailids="+detailid+"&flag=2&binvoiceid=" + bvoiceid + "&binvoicedetailid="+ bdetailid;
				}else{//A�ͻ�->�ݴ���
					up.location.href = strUrl + "sendAction&method=ricoExecBatchMove&detailids="+detailid+"&flag=1&warehouseid=" + warehouseid + "&whAreaId=" + zcwhareaid;
				}	
			}
		}
	}
	//�رմ����¼�
	window.onbeforeunload  =  function()   
    { 
    	window.returnValue = document.getElementById("warehouse_out_id").value;
    }
    
    //��������������������ѯ
    function Search()
    {
    	if (outId == null || outId.length < 1) {
			alert("A�ͻ�����Ϊ�գ���ѡ��һ�ŵ��ݣ�");
			return;
		}
		var traycode = document.getElementById("traycode").value;
		var boxcode = document.getElementById("boxcode").value;
		if((traycode == null || traycode.length < 1) && (boxcode == null || boxcode.length < 1)){
			alert("���������롿�������롿����Ϊ�գ�");
			return;
		}
		up.location.href = strUrl + "sendAction&flag=3&invoiceid=" + outId + "&traycode=" + traycode + "&boxcode=" + boxcode;
    }
    

-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" name="warehouse_out_id">
 
 
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td>
     
 <table width="98%" height="27" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">��ǰλ�ã�������� -&gt; ����ȷ�� -&gt; ����</td>
     <td align="right" width="280">
     	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
		   <tr>
		     <td class="y1" align="right" width="70">����ѡ��</td>
		     <td  align="left" width="200">
		       <input type="radio" id="atz" name="radio" class="input_radio" onClick="ChangeOp(1)" checked>A�ͻ�&hArr;�ݴ���
		       <input type="radio" id="atb" name="radio" class="input_radio" onClick="ChangeOp(2)" >A�ͻ�&hArr;B�ͻ�
		     </td>
		   </tr>
		 </table>
     </td>
   </tr>
 </table>
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr >
     <td class="y1" width="70"><div align="right">�������룺</div></td>
     <td class="x" width="80"><div align="left"><input name="traycode" type="text" size="10"></div></td>
     <td class="y1" width="65"><div align="right">�����룺</div></td>
     <td class="x" width="80"><div align="left"><input type="text" name="boxcode" size="10"></div></td> 
     <td  height="27" colspan="4"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;
       <input onclick="Search()" type="button" name="queryBtn" value="��ѯ" class="button_style">&nbsp;&nbsp;
   
     </div></td> 
   </tr>
 </table>
     </td>
   </tr>
   <tr>
     <td height="100%"> 
       
  <table width="98%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td width="84%" height="50%">

 <table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td height="50%">
       <iframe id="up" name="up" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_jobdetail.jsp"></iframe>
     </td>
   </tr>
   <tr>
     <td align="center">
       <input name="button" type="image" class="button" height="15" width="70" src="<%=request.getContextPath()%>/standard/images/arrow_down.png" onclick="moveDown();">
       <input type="button" name="button" class="BUTTON_STYLE1" value="�ر�" onclick="window.close()">
     </td>
   </tr>
   <tr>
     <td height="50%">
       <iframe id="down" name="down" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc.jsp?aoutid=<%=strOutBoundId%>"></iframe>
     </td>
   </tr>
 </table>

     </td>
   </tr>
 </table>    

     </td>
   </tr>
 </table>    
 
</body>
</html>
 