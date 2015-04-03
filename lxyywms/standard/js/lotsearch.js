
//取出批次属性的查询设置
function createLotSearch(tableObj, lotsearchtype, rows, cols){

	for(var i=tableObj.rows.length; i>rows; i--){
			tableObj.deleteRow(i-1);
	}

	if(lotsearchtype != ""){
		
		lotTool.getLotSearch(lotsearchtype, {callback:function(data){viewLotSearch(data, tableObj, cols);}});
	}
}	
		
//显示批次属性的查询设置
function viewLotSearch(data, tableObj, cols){
  				
	if(data!= null){

		for(var j=0; j<=parseInt(data.length/cols); j++){
		
			var cssName1 = "yx1";
			var cssName2 = "yx";
			
			//增加一行
			var newRowObj = tableObj.insertRow(tableObj.rows.length);
				
			for(var i=(j*cols); i<((j+1)*cols); i++){
				
				if(j==(parseInt(data.length/cols))){	//最后一行
					cssName1 = "y1";
					if(i==(j+1)*cols-1){	//最后一列
						cssName2 = "";
					}else{
						cssName2 = "x";
					}
				}else{
					if(i==(j+1)*cols-1){	//最后一列
						cssName2 = "xx1";
					}
				}
				if(i==data.length){
					return;
				}
				
				var strCode = data[i].lotid;
				var strFlag = data[i].islot;  			 // 是否显示 Y N
				var strType = data[i].lotformat;		 // 1-日期,2-文本  3-下拉
				var lt = ""; //属性  0：表示禁用的，1：表示精确或糊涂查询的 2：表示日期要取范围的 
				if(strCode=='lotatt1')
				{	lt="lt1";}
				else if(strCode=='lotatt2')
				{	lt="lt2";}
				else if(strCode=='lotatt3')
				{	lt="lt3";}
				else if(strCode=='lotatt4')
				{	lt="lt4";}
				else if(strCode=='lotatt5')
				{	lt="lt5";}
				else if(strCode=='lotatt6')
				{	lt="lt6";}
				else if(strCode=='lotatt7')
				{	lt="lt7";}
				else if(strCode=='lotatt8')
				{	lt="lt8";}
				else if(strCode=='lotatt9')
				{	lt="lt9";}
				else if(strCode=='lotatt10')
				{	lt="lt10";}
				else if(strCode=='lotatt11')
				{	lt="lt11";}
				else if(strCode=='lotatt12')
				{	lt="lt12";}
				//禁用的不返回
				if(strFlag != "N"){
					
					if(strType == 1  || strType == 4 ){	//日期类型
					
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						if(data[i].lotsearch == 3){	//范围查询
							document.getElementById(lt).value = "2";//范围查询
							if(strType == 1){	//日期类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "_from' onFocus='calendar()' class='Wdate' style='width:85px;'>-<input type='text' name='"+data[i].lotid + "_to' onFocus='calendar()' class='Wdate' style='width:85px;'></div>";
							}else if(strType == 4 ){//时间类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "_from' onclick='calendartime(this)' class='Wdate' style='width:170px;'>-<input type='text' name='"+data[i].lotid + "_to' onclick='calendartime(this)' class='Wdate' style='width:170px;'></div>";
							}
						}else{
							document.getElementById(lt).value = "1";
							if(strType == 1){	//日期类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' onFocus='calendar()' class='Wdate' style='width:117px;'></div>";
							}else if(strType == 4 ){//时间类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' onclick='calendartime(this)' class='Wdate' style='width:170px;'></div>";
							}
						}
						
					}else if(strType == 2){	//文本类型
						
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						document.getElementById(lt).value = "1";
						if(data[i].lotsearch == 2){	//模糊查询
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' style='width:117px;' maxlength='50' class='molike'></div>";
						}else{
							
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' style='width:117px;' maxlength='50'></div>";
						}
						
					}else if(strType == 3){	//下拉框

						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						//newLotValue.innerHTML = "<div align='left'><select name='" + data[i].lotid + "' style='width:117px;'><option value=''></option></select></div>";
						var strOptions = "";
						
						//获取下拉框内容
						DWREngine.setAsync(false);
						selectView.getBaseTypeList("", data[i].lotformatvalue, {callback:function(newdata){
							for(var k = 0; k < newdata.length; k++){
						    	strOptions += "<option value='" + newdata[k].strId + "'>" + newdata[k].strName + "</option>";
							}
						}})
						DWREngine.setAsync(true);
						newLotValue.innerHTML = "<select name='" + data[i].lotid + "' style='width:117px;'><option value=''>--请选择--</option>" + strOptions + "</select>";
						document.getElementById(lt).value = "1";
					}
				}else{
					document.getElementById(lt).value = "0";
				}
			}
		}
	}
}	

//返回批次属性的查询条件
function getLotattCon(){

	//批次属性值，如果是日期范围的，格式如下：2012-08-30|2012-08-31
	var lotatt1 = ""; //批次属性1
	var lotatt2 = ""; //批次属性2
	var lotatt3 = ""; //批次属性3
	var lotatt4 = ""; //批次属性4
	var lotatt5 = ""; //批次属性5
	var lotatt6 = ""; //批次属性6
	var lotatt7 = ""; //批次属性7
	var lotatt8 = ""; //批次属性8
	var lotatt9 = ""; //批次属性9
	var lotatt10 = "";//批次属性10
	var lotatt11 = "";//批次属性11
	var lotatt12 = "";//批次属性12
	//属性  0：表示禁用的，1：表示精确或糊涂查询的 2：表示日期要取范围的
	var lt1 = document.getElementById("lt1").value; //批次1的属性
	if(lt1 != null && lt1.length >0){
		if(lt1 == "0"){
			lotatt1 = "";
		}else if(lt1 == "2"){
			lotatt1 = document.getElementById("lotatt1_from").value + "|" + document.getElementById("lotatt1_to").value;
		}else{
			lotatt1 = document.getElementById("lotatt1").value;
		}	
	}else{ //没有值
		lotatt1 = "";
	}
	
	var lt2 = document.getElementById("lt2").value; //批次2的属性
	if(lt2 != null && lt2.length >0){
		if(lt2 == "0"){
			lotatt2 = "";
		}else if(lt2 == "2"){
			lotatt2 = document.getElementById("lotatt2_from").value + "|" + document.getElementById("lotatt2_to").value;
		}else{
			lotatt2 = document.getElementById("lotatt2").value;
		}	
	}else{ //没有值
		lotatt2 = "";
	}
	
	var lt3 = document.getElementById("lt3").value; //批次3的属性
	if(lt3 != null && lt3.length >0){
		if(lt3 == "0"){
			lotatt3 = "";
		}else if(lt3 == "2"){
			lotatt3 = document.getElementById("lotatt3_from").value + "|" + document.getElementById("lotatt3_to").value;
		}else{
			lotatt3 = document.getElementById("lotatt3").value;
		}	
	}else{ //没有值
		lotatt3 = "";
	}
	
	var lt4 = document.getElementById("lt4").value; //批次4的属性
	if(lt4 != null && lt4.length >0){
		if(lt4 == "0"){
			lotatt4 = "";
		}else if(lt4 == "2"){
			lotatt4 = document.getElementById("lotatt4_from").value + "|" + document.getElementById("lotatt4_to").value;
		}else{
			lotatt4 = document.getElementById("lotatt4").value;
		}	
	}else{ //没有值
		lotatt4 = "";
	}
	
	var lt5 = document.getElementById("lt5").value; //批次5的属性
	if(lt5 != null && lt5.length >0){
		if(lt5 == "0"){
			lotatt5 = "";
		}else if(lt5 == "2"){
			lotatt5 = document.getElementById("lotatt5_from").value + "|" + document.getElementById("lotatt5_to").value;
		}else{
			lotatt5 = document.getElementById("lotatt5").value;
		}	
	}else{ //没有值
		lotatt5 = "";
	}
	
	var lt6 = document.getElementById("lt6").value; //批次6的属性
	if(lt6 != null && lt6.length >0){
		if(lt6 == "0"){
			lotatt6 = "";
		}else if(lt6 == "2"){
			lotatt6 = document.getElementById("lotatt6_from").value + "|" + document.getElementById("lotatt6_to").value;
		}else{
			lotatt6 = document.getElementById("lotatt6").value;
		}	
	}else{ //没有值
		lotatt6 = "";
	}
	
	var lt7 = document.getElementById("lt7").value; //批次7的属性
	if(lt7 != null && lt7.length >0){
		if(lt7 == "0"){
			lotatt7 = "";
		}else if(lt7 == "2"){
			lotatt7 = document.getElementById("lotatt7_from").value + "|" + document.getElementById("lotatt7_to").value;
		}else{
			lotatt7 = document.getElementById("lotatt7").value;
		}	
	}else{ //没有值
		lotatt7 = "";
	}
	
	var lt8 = document.getElementById("lt8").value; //批次8的属性
	if(lt8 != null && lt8.length >0){
		if(lt8 == "0"){
			lotatt8 = "";
		}else if(lt8 == "2"){
			lotatt8 = document.getElementById("lotatt8_from").value + "|" + document.getElementById("lotatt8_to").value;
		}else{
			lotatt8 = document.getElementById("lotatt8").value;
		}	
	}else{ //没有值
		lotatt8 = "";
	}
	
	var lt9 = document.getElementById("lt9").value; //批次9的属性
	if(lt9 != null && lt9.length >0){
		if(lt9 == "0"){
			lotatt9 = "";
		}else if(lt9 == "2"){
			lotatt9 = document.getElementById("lotatt9_from").value + "|" + document.getElementById("lotatt9_to").value;
		}else{
			lotatt9 = document.getElementById("lotatt9").value;
		}	
	}else{ //没有值
		lotatt9 = "";
	}
	
	var lt10 = document.getElementById("lt10").value; //批次10的属性
	if(lt10 != null && lt10.length >0){
		if(lt10 == "0"){
			lotatt10 = "";
		}else if(lt10 == "2"){
			lotatt10 = document.getElementById("lotatt10_from").value + "|" + document.getElementById("lotatt10_to").value;
		}else{
			lotatt10 = document.getElementById("lotatt10").value;
		}	
	}else{ //没有值
		lotatt10 = "";
	}
	
	var lt11 = document.getElementById("lt11").value; //批次11的属性
	if(lt11 != null && lt11.length >0){
		if(lt11 == "0"){
			lotatt11 = "";
		}else if(lt11 == "2"){
			lotatt11 = document.getElementById("lotatt11_from").value + "|" + document.getElementById("lotatt11_to").value;
		}else{
			lotatt11 = document.getElementById("lotatt11").value;
		}	
	}else{ //没有值
		lotatt11 = "";
	}
	
	var lt12 = document.getElementById("lt12").value; //批次12的属性
	if(lt12 != null && lt12.length >0){
		if(lt12 == "0"){
			lotatt12 = "";
		}else if(lt12 == "2"){
			lotatt12 = document.getElementById("lotatt12_from").value + "|" + document.getElementById("lotatt12_to").value;
		}else{
			lotatt12 = document.getElementById("lotatt12").value;
		}	
	}else{ //没有值
		lotatt12 = "";
	}
	
	//有中文  
	var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
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
				
	return msg;
}

//根据批次1-12的值显示批次的查询条件
function createLotSearchView(tableObj, lotsearchtype, rows, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){

	for(var i=tableObj.rows.length; i>rows; i--){
			tableObj.deleteRow(i-1);
	}

	if(lotsearchtype != ""){
		
		lotTool.getLotSearch(lotsearchtype, {callback:function(data){viewLotSearchView(data, tableObj, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);}});
	}
}
function viewLotSearchView(data, tableObj, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){		
	if(data!= null){
		for(var j=0; j<=parseInt(data.length/cols); j++){
		
			var cssName1 = "yx1";
			var cssName2 = "yx";
			
			//增加一行
			var newRowObj = tableObj.insertRow(tableObj.rows.length);
				
			for(var i=(j*cols); i<((j+1)*cols); i++){
				
				if(j==(parseInt(data.length/cols))){	//最后一行
					cssName1 = "y1";
					if(i==(j+1)*cols-1){	//最后一列
						cssName2 = "";
					}else{
						cssName2 = "x";
					}
				}else{
					if(i==(j+1)*cols-1){	//最后一列
						cssName2 = "xx1";
					}
				}
				if(i==data.length){
					return;
				}
				var strCode = data[i].lotid;
				var strFlag = data[i].islot;  			 // 是否显示 Y N
				var strType = data[i].lotformat;		 // 1-日期,2-文本  3-下拉 4-时间
				var lt = ""; //属性  0：表示禁用的，1：表示精确或糊涂查询的 2：表示日期要取范围的 
				var strValue = "";//值
				if(strCode=='lotatt1')
				{	lt="lt1";strValue=lotatt1;}
				else if(strCode=='lotatt2')
				{	lt="lt2";strValue=lotatt2;}
				else if(strCode=='lotatt3')
				{	lt="lt3";strValue=lotatt3;}
				else if(strCode=='lotatt4')
				{	lt="lt4";strValue=lotatt4;}
				else if(strCode=='lotatt5')
				{	lt="lt5";strValue=lotatt5;}
				else if(strCode=='lotatt6')
				{	lt="lt6";strValue=lotatt6;}
				else if(strCode=='lotatt7')
				{	lt="lt7";strValue=lotatt7;}
				else if(strCode=='lotatt8')
				{	lt="lt8";strValue=lotatt8;}
				else if(strCode=='lotatt9')
				{	lt="lt9";strValue=lotatt9;}
				else if(strCode=='lotatt10')
				{	lt="lt10";strValue=lotatt10;}
				else if(strCode=='lotatt11')
				{	lt="lt11";strValue=lotatt11;}
				else if(strCode=='lotatt12')
				{	lt="lt12";strValue=lotatt12;}
	
				//禁用的不返回
				if(strFlag != "N"){
					
					if(strType == 1 || strType == 4 ){	//日期类型、时间
					
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						if(data[i].lotsearch == 3){	//范围查询
							document.getElementById(lt).value = "2";//范围查询
							if(strType == 1){	//日期类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "_from' value='"+strValue+"' onFocus='calendar()' class='Wdate' style='width:85px;'>-<input type='text' name='"+data[i].lotid + "_to' value='"+strValue+"' onFocus='calendar()' class='Wdate' style='width:85px;'></div>";
							}else if(strType == 4 ){//时间类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "_from' value='"+strValue+"' onclick='calendartime(this)' class='Wdate' style='width:170px;'>-<input type='text' name='"+data[i].lotid + "_to' value='"+strValue+"' onclick='calendartime(this)' class='Wdate' style='width:170px;'></div>";
							}
						}else{
							document.getElementById(lt).value = "1";
							if(strType == 1){	//日期类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' onFocus='calendar()' class='Wdate' style='width:117px;'></div>";
							}else if(strType == 4 ){//时间类型
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' onclick='calendartime(this)' class='Wdate' style='width:170px;'></div>";
							}
						}
						
					}else if(strType == 2){	//文本类型
						
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						document.getElementById(lt).value = "1";
						if(data[i].lotsearch == 2){	//模糊查询
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' style='width:117px;' maxlength='50' class='molike'></div>";
						}else{
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' style='width:117px;' maxlength='50'></div>";
						}
						
					}else if(strType == 3){	//下拉框

						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						//newLotValue.innerHTML = "<div align='left'><select name='" + data[i].lotid + "' style='width:117px;'><option value=''></option></select></div>";
						var strOptions = "";
						
						//获取下拉框内容
						DWREngine.setAsync(false);
						selectView.getBaseTypeList(strValue, data[i].lotformatvalue, {callback:function(newdata){
							for(var k = 0; k < newdata.length; k++){
						    	strOptions += "<option value='" + newdata[k].strId + "'>" + newdata[k].strName + "</option>";
							}
						}})
						DWREngine.setAsync(true);
						newLotValue.innerHTML = "<select name='" + data[i].lotid + "' style='width:117px;'><option value=''>--请选择--</option>" + strOptions + "</select>";
						document.getElementById(lt).value = "1";
					}
				}else{
					document.getElementById(lt).value = "0";
				}
			}
		}
	}
}
