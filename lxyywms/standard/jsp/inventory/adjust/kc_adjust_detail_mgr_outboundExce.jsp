<%@ page contentType="text/html; charset=GB2312"%>
<%
	String id = request.getParameter("id"); //������ID
	String wharehouseid = request.getParameter("wharehouseid"); 
%>
<html>
<head>
<title>����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/sequenceMgr.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>

<script type="text/javascript">
<!--
	//��ѯ
	function queryData(id)
	{	
	    var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecDetail&id=" + id;	 
		window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	//��ӿ����Ϣ
	function addData(){
		 var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_header_addkc.jsp?wharehouseid="+"<%=wharehouseid%>",'','dialogWidth=800px;dialogHeight=300px');
			if(result != null && result.length > 1)
		   	{
		 var actionStr = result+"&id="+"<%=id%>";
	     window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;	
		   	}
	}
	//ɾ��
	function delData()
	{
		  var deleteName = getDelCheckName();  
		  var id = document.getElementById("MMId").value;
		   if(deleteName == null||deleteName.length <1){
		        	confirm("��ѡ����Ҫɾ���Ŀ���������ϸ");
		    }else{
					var del = confirm("ȷ��ɾ����ѡ����������ϸ");
					if(del){
						window.myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecOutDeleteDetail&id=" + id+"&deleteStr="+deleteName;		
					}
		    }
	}
	//���ѡ���ѡ����ֵ
	function getDelCheckName()
	{
			var strDel = '';
			
			var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true){
			         strDel = strDel + myIframe.myform.elements[i].value + ',';
			    }
			}
			return strDel;
	}
	//����ѯ
	function inventoryData()
	{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_outbound_exception.jsp?wharehouseid="+"<%=wharehouseid%>",'','dialogWidth=800px;dialogHeight=600px');
		   	
		   	if(result != null && result.length > 0)
		   	{	
		   		
		   		var temStr = result.split("|");
		   		
		   	
		   		document.getElementById("inventoryid").value = temStr[0];//�쳣��id
		   		
		   		document.getElementById("cargoSpace").value = temStr[1];//�쳣��id
		   	}
	}
	function callSku(data)
	{
		if(data != null)
		{
			document.getElementById("skulength").value = data.length;
			document.getElementById("skuwidth").value = data.width;
			document.getElementById("skuhigh").value = data.height;
			document.getElementById("skugrossweight").value = data.weight;
			
		}
		
	}
	function sumCubic()
	{
		var toqty = document.getElementById("toqty").value;
		if(toqty == "" || toqty.length <1)  
       	{
       		alert("��Ŀ������������Ϊ��!");
       		return false; 
       	}
		if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("��Ŀ��������������������������!");
			return false;
		}
		var skulength = document.getElementById("skulength").value;
		var skuwidth = document.getElementById("skuwidth").value;
		var skuhigh = document.getElementById("skuhigh").value;
		var grossweight = document.getElementById("skugrossweight").value;
		var netweight = document.getElementById("skunetweight").value;
		var cublic = (skulength*skuwidth*skuhigh).toFixed(6);
		
		document.getElementById("tocubic").value = (toqty*cublic).toFixed(6);
		document.getElementById("togrossweight").value = (toqty*grossweight).toFixed(6);
		document.getElementById("tonetweight").value = (toqty*netweight).toFixed(6);
	}
	
	
	
	
	

	//������ϸ
	function saveDetailData(id)
	{	
		
		 var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecAddDetailOutboundExce"
				 + "&id="+id
				 + "&inventoryid=" + document.getElementById("inventoryid").value;
			    window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	
	//��ϸ���ݼ���
	function checkDetailData()
	{  
		var inventoryId = Trim(document.getElementById("inventoryid").value);
		//var tonum = Trim(document.getElementById("tonum").value);
		if(inventoryId == "" || inventoryId.length < 1)
		{
			alert("�������쳣������Ϊ��!");
			return false;
		}
		/*
		if(tonum == null || tonum.length <1)
		{
			alert("��Ŀ������������Ϊ��!");
			return false;
		}
		if(tonum!=null && tonum.length>0 && !isFloat(tonum,"+","0"))
		{
			alert("��Ŀ��������������������������!");
			return false;
		}
		*/
	}

		//�޸�
	   function updateData()
	   {
			if(updateAble())
			{		
				var param = getCheckValue();
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecQueryDetail&id="+param;
				var result = showModalDialog(strUrl,'','dialogWidth=800px;dialogHeight=400px');
			   	if(result != null && result.length > 1)
			   	{
			   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
			 
			   	}
		   	}
		}
		//�޸�����ʱ����
		function updateAble(){
		 	var icount = getCheckCount();
			if(icount<1){
				alert("��ѡ����Ҫ��������!");
			  	return false;
			}
			if(icount>1){
			 	alert("ֻ��ѡ��һ������!");
			 	return false;
			}
			return true;
		}
		//��ȡ��ѡ�����
		function getCheckCount()   
	  	{   
	  		var counter = 0;
	  		var length = myIframe.myform.elements.length;
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			    }
			}
	  		return counter;
	   }
	   //��ȡ������ѡ���ֵ
	   function getCheckValue()
	   {
	   	    var value = "";
	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].value;
		  				break; 
			   }
			}
	  		return value;
	   }	
-->
</script>
</head>
<body onload="javascript:queryData('<%=id%>');">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �������� -&gt; ��������ϸ</div></td>
   </tr>
 </table>

 <!-- ======== ��ϸ��Ϣ��ʼ ======== -->
 <form id="myform2" name="myform2" method="post">
 <input type="hidden" id="MMId" name="MMId" value="<%=id%>">
 <input type="hidden" id="inventoryId" name="inventoryId" value="">
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td width="15%" class="yx1"><div align="left"><font color="red">*&nbsp;</font>�����쳣��<input type="hidden" name="inventoryid" ></div></td>
     <td width="85%" class="xx1"><div align="left">
	        <input type="hidden" name="inventoryid" size="15" readonly="readonly" class="input_readonly">
	        <input type="text" name="cargoSpace" size="15" readonly="readonly" class="input_readonly">
            <input onclick="inventoryData();" type="button" name="moreSku" value="��" class="button_select">
     </div></td>
   </tr>
  
     
   <tr>
     <td height="27" colspan="2">
       <div align="center">
         <input type="button" name="saveDetailBtn" value="������ϸ" class="BUTTON_STYLE1" onclick="if(checkDetailData()!=false){saveDetailData('<%=id%>');}">&nbsp;
         <input type="button" name="delDetailBtn" value="ɾ����ϸ" class="BUTTON_STYLE1" onclick="delData()">&nbsp;
        <!--  <input type="button" name="updateDetailBtn" value="�޸���ϸ" class="BUTTON_STYLE1" onclick="updateData();">&nbsp; -->
         <input type="button" name="updateDetailBtn" value="��ѯ" class="BUTTON_STYLE1" onclick="queryData('<%=id%>');">&nbsp;
         <input type="button" name="resetDetailBtn" value="�ر�" class="BUTTON_STYLE1" onclick="javascript:window.close();"></div>
     </td>
   </tr>
 </table>
 </form>
 <!-- ======== ��ϸ��Ϣ���� ======== -->
 
 
 <table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== ��ϸ�б�ʼ ======== -->
 <table width="98%" height="400" border="0" align="center" cellpadding="0" cellspacing="0" >
   <tr>
	 <td>
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp" frameborder="0" width="100%" height="100%"  scrolling="yes">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ϸ�б���� ======== -->
 
 
</body>
</html>