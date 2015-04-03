<%@ page contentType="text/html; charset=GB2312"%>
<%
	String id = request.getParameter("id"); 
%>
<html>
<head>
<title>增加</title>
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
	//查询
	function queryData(id)
	{	
	    var actionStr = "BMSService?actionCode=productAdjustAction&method=ricoExecDetail&id="+parent.document.getElementById("MMId").value;
		Iframe1.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	//删除
	function delData()
	{
		  var deleteName = getDelCheckName();  
		  var id = parent.document.getElementById("MMId").value;
		   if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要删除的库存调整单详细");
		    }else{
					var del = confirm("确定删除所选库存调整单详细");
					if(del){
						Iframe1.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=productAdjustAction&method=ricoExecDeleteDetail&id=" + id+"&deleteStr="+deleteName;		
					}
		    }
	}
	//获得选择的选择框的值
	function getDelCheckName()
	{
			var strDel = '';
			
			var length = Iframe1.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(Iframe1.myform.elements[i].type=='checkbox'&& Iframe1.myform.elements[i].checked== true){
			         strDel = strDel + Iframe1.myform.elements[i].value + ',';
			    }
			}
			return strDel;
	}
	//库存查询
	function inventoryData()
	{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_select.jsp",'','dialogWidth=800px;dialogHeight=600px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		var temStr = result.split("|");

		   		document.getElementById("inventoryId").value = temStr[0];
		   		document.getElementById("whAreaName").value = temStr[1]; //库区
		   		document.getElementById("skuId").value = temStr[2];//产品id
		   		document.getElementById("skuName").value = temStr[3];//产品名
		   		document.getElementById("locid").value = temStr[4];//库位
		   		document.getElementById("traycode").value = temStr[5];//托盘条码
		   		document.getElementById("qty").value = temStr[6];//库存数量
		   		document.getElementById("qtyAllocated").value = temStr[7];//分配数量
		   		document.getElementById("qtyHold").value = temStr[8];//冻结数量
		   		document.getElementById("qtyUsable").value = temStr[9];//可用数量
		   		document.getElementById("cubic").value = temStr[10];//体积
		   		document.getElementById("grossweight").value = temStr[11];//毛重
		   		document.getElementById("unit").value = temStr[12];//单位
		   		document.getElementById("netweight").value = temStr[13];//净重

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
       		alert("【目标数量】不能为空!");
       		return false; 
       	}
		if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("【目标数量】必须是正浮点数或零!");
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
	//新增明细
	function saveDetailData()
	{
	        var counter = 0;
	        var param = "";
	  		var length = parent.myIframe.myform.elements.length;
	  		var lt1="",lt2="",lt3="",lt4="",lt5="",lt6="",lt7="",lt8="",lt9="",lt10="",lt11="",lt12="";
			for(i=0;i<length;i++)
			{
			    if(parent.myIframe.myform.elements[i].type=='checkbox'&& parent.myIframe.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			         param = parent.myIframe.myform.elements[i].value+",";
			    }
			}
			if(counter<1){
				alert("请选择您要的数据项!");
			  	return;
			}
		    if(parent.document.getElementById("lt1").value == 1){
		        lt1= document.getElementById("lotatt1").value;
		    }
		    if(parent.document.getElementById("lt2").value == 1){
		        lt2= document.getElementById("lotatt2").value;
		    }
		    if(parent.document.getElementById("lt3").value == 1){
		        lt3= document.getElementById("lotatt3").value;
		    }
		    if(parent.document.getElementById("lt4").value == 1){
		        lt4= document.getElementById("lotatt4").value;
		    }
		    if(parent.document.getElementById("lt5").value == 1){
		        lt5= document.getElementById("lotatt5").value;
		    }
		    if(parent.document.getElementById("lt6").value == 1){
		        lt6= document.getElementById("lotatt6").value;
		    }
		    if(parent.document.getElementById("lt7").value == 1){
		        lt7= document.getElementById("lotatt7").value;
		    }
		    if(parent.document.getElementById("lt8").value == 1){
		        lt8= document.getElementById("lotatt8").value;
		    }
		    if(parent.document.getElementById("lt9").value == 1){
		        lt9= document.getElementById("lotatt9").value;
		    }
		    if(parent.document.getElementById("lt10").value == 1){
		        lt10= document.getElementById("lotatt10").value;
		    }
		    if(parent.document.getElementById("lt11").value == 1){
		        lt11= document.getElementById("lotatt11").value;
		    }
		    if(parent.document.getElementById("lt12").value == 1){
		        lt12= document.getElementById("lotatt12").value;
		    }
	    
		 var actionStr = "BMSService?actionCode=productAdjustAction&method=ricoExecAddDetail"
		         + "&id="+parent.document.getElementById("MMId").value
				 + "&param="+param
				 + "&customer_id=" + document.getElementById("customer_id").value
    			 + "&productid=" + document.getElementById("productid").value
    			 + "&topackid=" + document.getElementById("topackid").value
    			 + "&topunit=" + document.getElementById("topunit").value
    			 + "&totraycode=" + document.getElementById("totraycode").value
    			 + "&Lotatt1=" + lt1
    			 + "&Lotatt2=" + lt2
    			 + "&Lotatt3=" + lt3
    			 + "&Lotatt4=" + lt4
    			 + "&Lotatt5=" + lt5
    			 + "&Lotatt6=" + lt6
    			 + "&Lotatt7=" + lt7
    			 + "&Lotatt8=" + lt8
    			 + "&Lotatt9=" + lt9
    			 + "&Lotatt10=" + lt10
    			 + "&Lotatt11=" + lt11
    			 + "&Lotatt12=" + lt12
			    Iframe1.location.href = "<%=request.getContextPath()%>/" + actionStr;		
	   		
	}
		//修改数据时调用
		function updateAble(){
		 	var icount = getCheckCount();
			if(icount<1){
				alert("请选择您要的数据项!");
			  	return false;
			}
			if(icount>1){
			 	alert("只能选择一行数据!");
			 	return false;
			}
			return true;
		}
		//获取复选框个数
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
	   //获取单个复选框的值
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
	    //批次
  		function viewLot()
  		{
  				//删除行
  				var tableObj = document.getElementById("marginTable");
  				
  				for(var i=tableObj.rows.length;i>2;i--)
				{
					tableObj.deleteRow(i-1);
				}
  		
  			var lotId = document.getElementById("lotid").value;
  			if(lotId != "")
  			{
  				selectView.getBaseLotDetailList(lotId, {callback:function(data){viewLotDetail(data);}});
  			}	
  		}
  		function inic(lotid){
  		    selectObject(lotid, "lotid", "4");
  		    var xx = document.getElementById("lotid");
  		    xx.disabled = true;
  		}
  		function inicunit(unitid,packageid){
  		    selectinoutUnit(unitid, 'topunit', packageid, 2);
  		}
		//页面登录
		function OnLoad(){	
			selectObject("", "lotid", "4");
		}	
-->
</script>
</head>
<body onload="javascript:OnLoad();">
 
 <!-- ======== 明细信息开始 ======== -->
 <form id="myform3" name="myform3" method="post">
 <table width="100%"  id="marginTable" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr> 
	  <td class="yx1"><div align="right">TO客户：</div></td>
      <td class="yx"><div align="left">
                      <input type="hidden" name="customer_id" />
                      <input type="text" name="customer_name"  size="15" class="input_readonly"/>
	                  <input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></div>
	  </td>
	  <td class="yx1"><div align="right">TO产品：</div></td>
      <td class="yx"><div align="left"><input type="text" name="product_name"  size="15" class="input_readonly"/>
          	 <input type="hidden" name="productid" value=""/>
          	 <input name="moreBtn3" type="button" class="button_select" value="…" onclick="openProductPackeDetail('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp?','800','520','productid','product_name', 'topackid', 'packname', '2','topunit');" /></div></td>
      <td class="yx1"><div align="right">TO包装ID：</div></td>
      <td class="yx"><div align="left"><input type="text" name="packname"  size="15" ><input type="hidden" name="topackid" value=""/></div></td>
      <td class="yx1"><div align="right">TO计量单位：</div></td>
      <td class="yx"><select name="eunit" id="topunit"  style="width:110px;">
	            <option>--请选择--</option>
	          </select></td>
      <td class="yx1"><div align="right">TO托盘条码：</div></td>
      <td class="yx"><div align="left"><input type="text" name="totraycode"  size="15"></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">批次属性：</div></td>
     <td class="yx" colspan="10"><select id="lotid" style="width:110px;"><option  value=""></option></select></td>
     
   </tr>
 </table>
 </form>
 <!-- ======== 查询列表开始 ======== -->
 <table width="100%" height="200" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
	 <td class="yx">
         <div align="center">
         <input type="button" name="saveDetailBtn" value="新增明细" class="BUTTON_STYLE1" onclick="saveDetailData()">&nbsp;
         <input type="button" name="delDetailBtn" value="删除明细" class="BUTTON_STYLE1" onclick="delData()">&nbsp;
         <input type="button" name="updateDetailBtn" value="查询" class="BUTTON_STYLE1" onclick="queryData('<%=id%>');">&nbsp;
         <input type="button" name="resetDetailBtn" value="关闭" class="BUTTON_STYLE1" onclick="javascript:window.close();"></div>
     </td>
   </tr>
   <tr>
	 <td class="outer_td">
	   <iframe id="Iframe1" src="<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_adjustdetail_list.jsp" frameborder="0" width="100%" height="100%"  scrolling="yes">
	   </iframe>
	 </td>
   </tr>
 </table>
</body>
</html>