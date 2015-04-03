
var vheight = window.screen.height-200;
var vwidth =  window.screen.width-200;
var WLeft = Math.ceil((window.screen.width-800)/2);
var WTop  = Math.ceil((window.screen.height-600)/2);
//1:open
function winopen(file){	
	file=encodeURI(file);
	window.open(file,"","height="+vheight+",width="+vwidth+",top ="+WTop+",left="+WLeft+",toolbar=no,location=no,scrollbars=yes,status=no,menubar=no,resizable=yes");
}
//2:showModalDialog
function openCustomer(url, width, height) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 0)
	{
	 	var tem = result.split("|");
	 	document.getElementById("customer_id").value = tem[0];
	 	document.getElementById("customer_name").value = tem[1];
	}else
	{
		document.getElementById("customer_id").value = "";
	 	document.getElementById("customer_name").value = "";
	}	
}
function openProduct(url, width, height) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		
		document.getElementById("package_id").value = tem[0];
		document.getElementById("package_name").value = tem[1];
	}else
	{
		document.getElementById("package_id").value = "";
		document.getElementById("package_name").value = "";
	}	
}
function openDepartment(url, width, height, id, name) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];
		document.getElementById(name).value = tem[1];
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
	}	
}
function openProductIdNameAndPunit(url, width, height) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById("package_id").value = tem[0];
		document.getElementById("package_name").value = tem[1];
		
		document.getElementById("punit").value = tem[6];
	}else
	{
		document.getElementById("package_id").value = "";
		document.getElementById("package_name").value = "";
		document.getElementById("punit").value = "";
	}	
}
function openPackage(url, width, height) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById("package_id").value = tem[0];
		document.getElementById("package_name").value = tem[1];
	}else
	{
		document.getElementById("package_id").value = "";
		document.getElementById("package_name").value = "";
	}	
}
//3:showModalDialog
function openCustomer1(url, width, height, id, name) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 0)
	{
	 	var tem = result.split("|");
	 	document.getElementById(id).value = tem[0];
	 	document.getElementById(name).value = tem[1];
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
	}	
}
function openCustomer2(url, width, height, id, name, address) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 0)
	{
	 	var tem = result.split("|");
	 	document.getElementById(id).value = tem[0];
	 	document.getElementById(name).value = tem[1];
	 	document.getElementById(address).value = tem[2];
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
		document.getElementById(address).value = "";
	}	
}
function openProduct1(url, width, height, id, name) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];
		document.getElementById(name).value = tem[1];
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
	}	
}
function openUser(url, width, height, id, name) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];
		document.getElementById(name).value = tem[2];
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
	}	
}
function openLot(url, width, height) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById("lot_id").value = tem[0];
		document.getElementById("lot_name").value = tem[1];
	}else
	{
		document.getElementById("lot_id").value = "";
		document.getElementById("lot_name").value = "";
	}	
}

//新增收货单详细,选择物品
function openProductPacke(url, width, height, id, name, packageid, packagename, inout, unitobj, lotid) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];			//产品ID
		document.getElementById(name).value = tem[1];		//产品名
		document.getElementById(packageid).value = tem[2];	//包装ID
		document.getElementById(packagename).value = tem[3];//包装名
		document.getElementById(lotid).value = tem[4];		//批次类型ID
		//根据包装ID获得单位
		selectinoutUnit('', unitobj, tem[2], inout);
		//批次类型ID获得批次属性 tem[4]
		lotTool.getLotDetails(tem[4], {callback:function(data){viewLotatt(data);}});
		
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
		document.getElementById(packageid).value = "";
		document.getElementById(packagename).value = "";
		document.getElementById(lotid).value = "";
	}	
}
//入库管理--》生产入库管理 建申请单,选择物品
function openProductPacke(url, width, height) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];			//产品ID
		document.getElementById(name).value = tem[1];		//产品名
	}	
}
//新增物品调整单选择物品
function openProductPackeJust(url, width, height, id, name, packageid, packagename, inout, unitobj, lotid) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];			//产品ID
		document.getElementById(name).value = tem[1];		//产品名
		document.getElementById(packageid).value = tem[2];	//包装ID
		document.getElementById(packagename).value = tem[3];//包装名
		DWREngine.setAsync(false);
		//根据包装ID获得单位
		selectinoutUnit('', unitobj, tem[2], inout);
		//根据该产品获取相应的批次属性
		selectObject(tem[4], "lotid", "4");
		//批次类型ID获得批次属性 tem[4]
		viewLot();
		DWREngine.setAsync(true);
		
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
		document.getElementById(packageid).value = "";
		document.getElementById(packagename).value = "";
		document.getElementById(lotid).value = "";
		
		DWREngine.setAsync(true);
		//根据包装ID获得单位
		selectinoutUnit('', unitobj, "", inout);
		//根据该产品获取相应的批次属性
		selectObject("", "lotid", "4");
		//批次类型ID获得批次属性
		viewLot();
		DWREngine.setAsync(true);
	}	
}
//货品调整明细
function openProductPackeDetail(url, width, height, id, name, packageid, packagename, inout, unitobj) 
{
	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById(id).value = tem[0];			//产品ID
		document.getElementById(name).value = tem[1];		//产品名
		document.getElementById(packageid).value = tem[2];	//包装ID
		document.getElementById(packagename).value = tem[3];//包装名
		//根据包装ID获得单位
		selectinoutUnit('', unitobj, tem[2], inout);
		
	}else
	{
		document.getElementById(id).value = "";
		document.getElementById(name).value = "";
		document.getElementById(packageid).value = "";
		document.getElementById(packagename).value = "";
	}	
}
function basLocation(obj, url, flag)
{
	if(!flag)
	{
		flag = "0";
	}
	var strUrl=url + '/standard/jsp/common/common_cargospace.jsp?flag='+flag;
	var result = showModalDialog(strUrl,'','dialogWidth=750px;dialogHeight=480px');	
	if(result != null && result.length > 0)
	{
		 var tempParam = result;

		 var temStr = tempParam.split("|");
		 		
		 document.getElementById(obj).value = temStr[0];
	}
	
}
function viewLotatt(data)
{
	for(var i = 0; i < data.length; i++)
	{
		var strCode = data[i].m_strAttcode;
		var strName = data[i].m_strAttname;
		var strFlag = data[i].m_strLotatt_flag;  // 1-必输,2-禁用,3-可选
		var strType = data[i].m_strLottype;		 // 1-日期,2-文本 3-下拉
		var strSelectCode = data[i].m_strLottypevalue;	//下拉用的类型代码
		
		var isNeed = "";
		if(strFlag=="1")
		{
			isNeed = "<FONT COLOR='red'>*&nbsp;</FONT>";
		}
		
		var nameDiv = "";
		var nameInput = "";
		var nameValue = "";
		
		if(strCode=='lotatt1')
		{	nameDiv = "lotatt001";	nameInput = "lotatt1"; nameValue = "lotvalue001";}
		else if(strCode=='lotatt2')
		{	nameDiv = "lotatt002";	nameInput = "lotatt2"; nameValue = "lotvalue002";}
		else if(strCode=='lotatt3')
		{	nameDiv = "lotatt003";	nameInput = "lotatt3"; nameValue = "lotvalue003";}
		else if(strCode=='lotatt4')
		{	nameDiv = "lotatt004";	nameInput = "lotatt4"; nameValue = "lotvalue004";}
		else if(strCode=='lotatt5')
		{	nameDiv = "lotatt005";	nameInput = "lotatt5"; nameValue = "lotvalue005";}
		else if(strCode=='lotatt6')
		{	nameDiv = "lotatt006";	nameInput = "lotatt6"; nameValue = "lotvalue006";}
		else if(strCode=='lotatt7')
		{	nameDiv = "lotatt007";	nameInput = "lotatt7"; nameValue = "lotvalue007";}
		else if(strCode=='lotatt8')
		{	nameDiv = "lotatt008";	nameInput = "lotatt8"; nameValue = "lotvalue008";}
		else if(strCode=='lotatt9')
		{	nameDiv = "lotatt009";	nameInput = "lotatt9"; nameValue = "lotvalue009";}
		else if(strCode=='lotatt10')
		{	nameDiv = "lotatt010";	nameInput = "lotatt10"; nameValue = "lotvalue010";}
		else if(strCode=='lotatt11')
		{	nameDiv = "lotatt011";	nameInput = "lotatt11"; nameValue = "lotvalue011";}
		else if(strCode=='lotatt12')
		{	nameDiv = "lotatt012";	nameInput = "lotatt12"; nameValue = "lotvalue012";}
		//修改批次属性的名称	
		document.all(nameDiv).innerHTML = isNeed + strName + "：";
		if(strFlag=="1" || strFlag=="3") //1-必输,2-禁用,3-可选
		{
				document.all(nameInput).disabled = false;
				document.all(nameDiv).style.color = "";
				document.all(nameInput).className = "";
				
			if(strType == 1){	//日期类型
				document.all(nameValue).innerHTML = "<input type='text' name='"+data[i].m_strAttcode + "' onFocus='calendar()' class='Wdate' style='height:21px;width:100px;'>";
			}else if(strType == 3){//下拉框
					var strOptions = "";
					//获取下拉框内容
					DWREngine.setAsync(false);
					selectView.getBaseTypeList("", strSelectCode, {callback:function(newdata){
						for(var k = 0; k < newdata.length; k++){
							    	strOptions += "<option value='" + newdata[k].strId + "'>" + newdata[k].strName + "</option>";
								}
						}})
					DWREngine.setAsync(true);
					document.all(nameValue).innerHTML = "<select name='" + data[i].m_strAttcode + "' style='width:100px;'><option value=''>--请选择--</option>" + strOptions + "</select>";	
			}				
		}else
		{
				document.all(nameDiv).style.color = "#999999";
				document.all(nameInput).disabled = true;
				document.all(nameInput).className = "input_readonly1";
		}
		
	}
}


//选择货位
function openCargospace(url, width, height) {

	var result = showModalDialog(url,'','dialogWidth='+width+'px;dialogHeight='+height+'px');
	if(result != null && result.length > 1)
	{
		var tem = result.split("|");
		document.getElementById("cargospace_id").value = tem[0];
		document.getElementById("cargospace_name").value = tem[1];
	}else
	{
		document.getElementById("cargospace_id").value = "";
		document.getElementById("cargospace_name").value = "";
	}	
}