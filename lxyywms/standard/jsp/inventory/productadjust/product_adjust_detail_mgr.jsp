<%@ page contentType="text/html; charset=GB2312"%>
<%
	String id = request.getParameter("id"); 
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	//��ѯ
	function queryData(id)
	{	
	    var lt1="",lt2="",lt3="",lt4="",lt5="",lt6="",lt7="",lt8="",lt9="",lt10="",lt11="",lt12="";
	    if(document.getElementById("lt1").value == 1){
	        lt1= document.getElementById("lotatt1").value;
	    }
	    if(document.getElementById("lt2").value == 1){
	        lt2= document.getElementById("lotatt2").value;
	    }
	    if(document.getElementById("lt3").value == 1){
	        lt3= document.getElementById("lotatt3").value;
	    }
	    if(document.getElementById("lt4").value == 1){
	        lt4= document.getElementById("lotatt4").value;
	    }
	    if(document.getElementById("lt5").value == 1){
	        lt5= document.getElementById("lotatt5").value;
	    }
	    if(document.getElementById("lt6").value == 1){
	        lt6= document.getElementById("lotatt6").value;
	    }
	    if(document.getElementById("lt7").value == 1){
	        lt7= document.getElementById("lotatt7").value;
	    }
	    if(document.getElementById("lt8").value == 1){
	        lt8= document.getElementById("lotatt8").value;
	    }
	    if(document.getElementById("lt9").value == 1){
	        lt9= document.getElementById("lotatt9").value;
	    }
	    if(document.getElementById("lt10").value == 1){
	        lt10= document.getElementById("lotatt10").value;
	    }
	    if(document.getElementById("lt11").value == 1){
	        lt11= document.getElementById("lotatt11").value;
	    }
	    if(document.getElementById("lt12").value == 1){
	        lt12= document.getElementById("lotatt12").value;
	    }
	    var warehouseid = document.getElementById("warehouseid").value;
	    var whAreaId = document.getElementById("whAreaId").value;
	    var cargoAreaId = document.getElementById("cargoAreaId").value;
	    var customer_id = document.getElementById("customer_id").value;
	    var fmpackid = document.getElementById("fmpackid").value;
	    var fmpunit = document.getElementById("fmpunit").value;
	    var productid = document.getElementById("productid").value;
	    var fmtraycode = document.getElementById("fmtraycode").value;
	    var lotid = document.getElementById("lotid").value;
	    
	    if(warehouseid == "" || warehouseid.length <1)  
       	{
       		alert("���ֿ⡿����Ϊ��!");
       		return; 
       	}
	    var actionStr = "BMSService?actionCode=productAdjustAction&method=ricoExecInventoryQuery&warehouseid=" + warehouseid
	    +"&whAreaId="+ whAreaId+"&cargoAreaId="+ cargoAreaId+"&customer_id="+ customer_id+"&fmpackid="+ fmpackid+"&fmpunit="+ fmpunit+"&productid="+ productid+"&fmtraycode="+ fmtraycode+"&lotid="+ lotid
	    +"&lt1="+ lt1+"&lt2="+ lt2+"&lt3="+ lt3+"&lt4="+ lt4+"&lt5="+ lt5+"&lt6="+ lt6+"&lt7="+ lt7+"&lt8="+ lt8+"&lt9="+ lt9+"&lt10="+ lt10+"&lt11="+ lt11+"&lt12="+ lt12;
		window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;
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
						window.myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecDeleteDetail&id=" + id+"&deleteStr="+deleteName;		
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
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_select.jsp",'','dialogWidth=800px;dialogHeight=600px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		var temStr = result.split("|");

		   		document.getElementById("inventoryId").value = temStr[0];
		   		document.getElementById("whAreaName").value = temStr[1]; //����
		   		document.getElementById("skuId").value = temStr[2];//��Ʒid
		   		document.getElementById("skuName").value = temStr[3];//��Ʒ��
		   		document.getElementById("locid").value = temStr[4];//��λ
		   		document.getElementById("traycode").value = temStr[5];//��������
		   		document.getElementById("qty").value = temStr[6];//�������
		   		document.getElementById("qtyAllocated").value = temStr[7];//��������
		   		document.getElementById("qtyHold").value = temStr[8];//��������
		   		document.getElementById("qtyUsable").value = temStr[9];//��������
		   		document.getElementById("cubic").value = temStr[10];//���
		   		document.getElementById("grossweight").value = temStr[11];//ë��
		   		document.getElementById("unit").value = temStr[12];//��λ
		   		document.getElementById("netweight").value = temStr[13];//����
		   		sequenceMgr.getSkuId(temStr[2],callSku);
		   	}
		   	    document.getElementById("toqty").value = "";
                document.getElementById("togrossweight").value = "";
		   		document.getElementById("tocubic").value = "";
		   		document.getElementById("tonetweight").value = "";
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
	     
		 var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecAddDetail"
				 + "&id="+id
				 + "&inventoryid=" + document.getElementById("inventoryId").value
				 + "&status=" + document.getElementById("status").value
    			 + "&toqty=" + document.getElementById("toqty").value
    			 + "&togrossweight=" + document.getElementById("togrossweight").value
    			 + "&tocubic=" + document.getElementById("tocubic").value
    			 + "&tonetweight=" + document.getElementById("tonetweight").value
			    window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;	
				document.getElementById("inventoryId").value = "";
		   		document.getElementById("whAreaName").value = ""; //����
		   		document.getElementById("skuId").value = "";//��Ʒid
		   		document.getElementById("skuName").value = "";//��Ʒ��
		   		document.getElementById("locid").value = "";//��λ
		   		document.getElementById("traycode").value = "";//��������
		   		document.getElementById("qty").value = "";//�������
		   		document.getElementById("qtyAllocated").value = "";//��������
		   		document.getElementById("qtyHold").value = "";//��������
		   		document.getElementById("qtyUsable").value = "";//��������
		   		document.getElementById("cubic").value = "";//���
		   		document.getElementById("grossweight").value = "";//ë��
		   		document.getElementById("unit").value = "";//��λ
		   		document.getElementById("netweight").value ="";//����
		   		
		   		document.getElementById("toqty").value="";
		   		document.getElementById("togrossweight").value="";
				document.getElementById("tocubic").value="";
				document.getElementById("tonetweight").value="";
	   		
	}
	
	//��ϸ���ݼ���
	function checkDetailData()
	{
		var inventoryId = Trim(document.getElementById("inventoryId").value);
		var toqty = Trim(document.getElementById("toqty").value);
    	var togrossweight = document.getElementById("togrossweight").value;
    	var tocubic = document.getElementById("tocubic").value;
    	var tonetweight = document.getElementById("tonetweight").value;
		
		
		//��������
		var qtyAllocated = document.getElementById("qtyAllocated").value;
		if(qtyAllocated > 0)
		{
			alert("����ȡ���������ܵ���!");
       		return false; 
		}
		if(inventoryId == "" || inventoryId.length < 1)
		{
			alert("����桿����Ϊ��!");
			return false;
		}
		if(toqty == null || toqty.length <1)
		{
			alert("��Ŀ������������Ϊ��!");
			return false;
		}
		if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("��Ŀ��������������������������!");
			return false;
		}
		if(togrossweight!=null && togrossweight.length>0 && !isFloat(togrossweight,"+","0"))
		{
			alert("��Ŀ��ë�ء�������������������!");
			return false;
		}
		if(tocubic!=null && tocubic.length>0 && !isFloat(tocubic,"+","0"))
		{
			alert("��Ŀ�������������������������!");
			return false;
		}
		if(tonetweight!=null && tonetweight.length>0 && !isFloat(tonetweight,"+","0"))
		{
			alert("��Ŀ�꾻�ء�������������������!");
			return false;
		}
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
	    //����
  		function viewLot()
  		{
  				//ɾ����
  				var tableObj = document.getElementById("marginTable");
  				var listtableObj = list.document.getElementById("marginTable");	
  				
  				for(var i=tableObj.rows.length;i>2;i--)
				{
					tableObj.deleteRow(i-1);
				}
				for(var i=listtableObj.rows.length;i>2;i--)
				{
					listtableObj.deleteRow(i-1);
				}
  		  
  			var lotId = document.getElementById("lotid").value;
  			clearzero();
  			if(lotId != "")
  			{
  				selectView.getBaseLotDetailList(lotId, {callback:function(data){viewLotDetail(data);}});
  			}	
  			
  			list.inic(lotId);
  		}
  		//��ʾ������ϸ
  		function viewLotDetail(data)
  		{
  			////���ӱ����
  			var tableObj = document.getElementById("marginTable");	
  			var listtableObj = list.document.getElementById("marginTable");	
			if(data != null)
			{
				for(var j = 0; j < (data.length / 5); j++)
				{
					//����һ��
					var newRowObj = tableObj.insertRow(tableObj.rows.length);
					var listnewRowObj = listtableObj.insertRow(listtableObj.rows.length);
				
					for(var i = (j*5); i < ((j+1)*5) && i < data.length ; i++)
					{
							var newLot=newRowObj.insertCell(newRowObj.cells.length);
							newLot.setAttribute( "className", "yx1");	
							newLot.innerHTML = "<div align='right'>"+data[i].strName+"��</div>"; 

							var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
							newLotValue.setAttribute( "className", "yx");
							newLotValue.innerHTML = "<div align='left'><input type='text' size='15' name='"+data[i].strId+"'></div>";
							
							var listnewLot=listnewRowObj.insertCell(listnewRowObj.cells.length);
							listnewLot.setAttribute( "className", "yx1");	
							listnewLot.innerHTML = "<div align='right'>"+data[i].strName+"��</div>"; 

							var listnewLotValue=listnewRowObj.insertCell(listnewRowObj.cells.length);
							listnewLotValue.setAttribute( "className", "yx");
							listnewLotValue.innerHTML = "<div align='left'><input type='text' size='15' name='"+data[i].strId+"'></div>";
							 switch (parseInt(data[i].strId.substring(6)))
							 {
								case 1:
								document.getElementById("lt1").value=1;
								break;
								case 2:
								document.getElementById("lt2").value=1;
								break;
								case 3:
								document.getElementById("lt3").value=1;
								break;
								case 4:
								document.getElementById("lt4").value=1;
								break;
								case 5:
								document.getElementById("lt5").value=1;
								break;
								case 6:
								document.getElementById("lt6").value=1;
								break;
								case 7:
								document.getElementById("lt7").value=1;
								break;
								case 8:
								document.getElementById("lt8").value=1;
								break;
								case 9:
								document.getElementById("lt9").value=1;
								break;
								case 10:
								document.getElementById("lt10").value=1;
								break;
								case 11:
								document.getElementById("lt11").value=1;
								break;
								case 12:
								document.getElementById("lt12").value=1;
								break;
							 }
					}
				}
				
			}
  		 }
  		 function clearzero(){
  		     document.getElementById("lt1").value=0;
  		     document.getElementById("lt2").value=0;
  		     document.getElementById("lt3").value=0;
  		     document.getElementById("lt4").value=0;
  		     document.getElementById("lt5").value=0;
  		     document.getElementById("lt6").value=0;
  		     document.getElementById("lt7").value=0;
  		     document.getElementById("lt8").value=0;
  		     document.getElementById("lt9").value=0;
  		     document.getElementById("lt10").value=0;
  		     document.getElementById("lt11").value=0;
  		     document.getElementById("lt12").value=0;
  		 }
  		 function getAreaList(){
  		     DWREngine.setAsync(false);
  		     var warehouseid = document.getElementById("warehouseid").value;
  		     selectAreaList("", "whAreaId", warehouseid, "1");
  		     selectAreaList("", "cargoAreaId", warehouseid, "2");
  		     DWREngine.setAsync(true);
  		 }
	//ҳ���¼
	function OnLoad(){	
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
		selectObject("", "lotid", "4");
		DWREngine.setAsync(true);	
		clearzero();
	}	
-->
</script>
</head>
<body onload="javascript:OnLoad();">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25">
        <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã������� -&gt; ��Ʒ���� -&gt; ��Ʒ��������ϸ</div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	           <li><input type="button" value="����ѯ"  style="width:100px;"  onclick="queryData('<%=id%>');" class="button_search"></li>
	        </ul>
	      </div>
	   </div>
	  </td>
   </tr>
 </table>

 <!-- ======== ��ϸ��Ϣ��ʼ ======== -->
 <form id="myform2" name="myform2" method="post">
 <input type="hidden" id="MMId" name="MMId" value="<%=id%>">
 <input type="hidden" id="inventoryId" name="inventoryId" value="">
 <table width="98%"  id="marginTable" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
      <td class="yx1" align="right"><div align="right">�ֿ⣺</div></td>
	  <td class="yx"><select id="warehouseid" onChange="getAreaList()" style="width:110px;"><option value=""></option></select></td>
	  <td class="yx1" align="right"><div align="right">������</div></td>
      <td class="yx"><select name="whAreaId"  style="width:110px;"><option value=""></option></select></td>
	  <td class="yx1" align="right">������</td>
	  <td class="yx"><select name="cargoAreaId"  style="width:110px;"><option value=""></option></select></td> 
	  <td class="yx1"><div align="right">FM�ͻ���</div></td>
      <td class="yx"><div align="left">
                      <input type="hidden" name="customer_id" />
                      <input type="text" name="customer_name"  size="15" class="input_readonly"/>
	                  <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></div>
	  </td>
      <td class="yx1"><div align="right">FM��װID��</div></td>
      <td class="yx"><div align="left"><input type="text" name="packname"  size="15" ><input type="hidden" name="fmpackid" value=""/></div></td>
   </tr>
   <tr>
     
     <td class="yx1"><div align="right">FM������λ��</div></td>
     <td class="yx"><select name="eunit" id="fmpunit"  style="width:110px;">
	            <option>--��ѡ��--</option>
	          </select></td>
     <td class="yx1"><div align="right">FM��Ʒ��</div></td>
     <td class="yx"><div align="left"><input type="text" name="product_name"  size="15" class="input_readonly"/>
          	 <input type="hidden" name="productid" value=""/>
          	 <input name="moreBtn3" type="button" class="button_select" value="��" onclick="openProductPackeJust('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp?','800','520','productid','product_name', 'fmpackid', 'packname', '2','fmpunit', 'lotid');" /></div></td>
     <td class="yx1"><div align="right">FM�������룺</div></td>
     <td class="yx"><div align="left"><input type="text" name="fmtraycode"  size="15"></div></td>
     <td class="yx1"><div align="right">FM�������ԣ�</div></td>
     <td class="yx" colspan="3"><select id="lotid" onchange="viewLot();"  style="width:110px;"><option value=""></option></select>
            <input type="hidden" name="lt1">	
     	    <input type="hidden" name="lt2">
     	    <input type="hidden" name="lt3">
     	    <input type="hidden" name="lt4">
     	    <input type="hidden" name="lt5">
     	    <input type="hidden" name="lt6">
     	    <input type="hidden" name="lt7">
     	    <input type="hidden" name="lt8">
     	    <input type="hidden" name="lt9">
     	    <input type="hidden" name="lt10">
     	    <input type="hidden" name="lt11">
     	    <input type="hidden" name="lt12">
     </td>
   </tr>
 </table>
 </form>
 <!-- ======== ��ѯ�б�ʼ ======== -->
 <table width="98%" height="200" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
	 <td class="outer_td">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_list_detail.jsp" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
   <tr>
	  <td height="25">
	  	<iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=50&flag=Y"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	  </td>
	</tr>
 </table>
 <!-- ======== ��ѯ�б�ʼ ======== -->
 <table width="98%" height="400" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
	 <td class="outer_td">
	   <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_mgr.jsp" frameborder="0" width="100%" height="100%"  scrolling="yes">
	   </iframe>
	 </td>
   </tr>
 </table>
</body>
</html>