
//取出批次属性的显示设置，只读
function createLotSet(tableObj, lotsettype, rows, cols, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){

	for(var i=tableObj.rows.length; i>rows; i--){
			tableObj.deleteRow(i-1);
	}
	if(lotsettype != ""){
		
		lotTool.getLotSet(lotsettype, {callback:function(data){viewLotSetReadonly(data, tableObj, cols, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);}});
	}
}	
		
//显示批次属性的显示设置，只读
function viewLotSetReadonly(data, tableObj, cols, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){
  				
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
				var strFlag = data[i].islot;  // 是否显示 Y N
				
				var nameDiv = "";
				var nameInput = "";
				var nameValue = "";
				
				var lotattValue = "";//值
				if(strCode=='lotatt1')
				{	nameDiv = "lotatt001";	nameInput = "lotatt1"; nameValue = "lotvalue001"; lotattValue=lotatt1;}
				else if(strCode=='lotatt2')
				{	nameDiv = "lotatt002";	nameInput = "lotatt2"; nameValue = "lotvalue002"; lotattValue=lotatt2;}
				else if(strCode=='lotatt3')
				{	nameDiv = "lotatt003";	nameInput = "lotatt3"; nameValue = "lotvalue003"; lotattValue=lotatt3;}
				else if(strCode=='lotatt4')
				{	nameDiv = "lotatt004";	nameInput = "lotatt4"; nameValue = "lotvalue004"; lotattValue=lotatt4;}
				else if(strCode=='lotatt5')
				{	nameDiv = "lotatt005";	nameInput = "lotatt5"; nameValue = "lotvalue005"; lotattValue=lotatt5;}
				else if(strCode=='lotatt6')
				{	nameDiv = "lotatt006";	nameInput = "lotatt6"; nameValue = "lotvalue006"; lotattValue=lotatt6;}
				else if(strCode=='lotatt7')
				{	nameDiv = "lotatt007";	nameInput = "lotatt7"; nameValue = "lotvalue007"; lotattValue=lotatt7;}
				else if(strCode=='lotatt8')
				{	nameDiv = "lotatt008";	nameInput = "lotatt8"; nameValue = "lotvalue008"; lotattValue=lotatt8;}
				else if(strCode=='lotatt9')
				{	nameDiv = "lotatt009";	nameInput = "lotatt9"; nameValue = "lotvalue009"; lotattValue=lotatt9;}
				else if(strCode=='lotatt10')
				{	nameDiv = "lotatt010";	nameInput = "lotatt10"; nameValue = "lotvalue010"; lotattValue=lotatt10;}
				else if(strCode=='lotatt11')
				{	nameDiv = "lotatt011";	nameInput = "lotatt11"; nameValue = "lotvalue011"; lotattValue=lotatt11;}
				else if(strCode=='lotatt12')
				{	nameDiv = "lotatt012";	nameInput = "lotatt12"; nameValue = "lotvalue012"; lotattValue=lotatt12;}
			
				//禁用的不返回
				if(strFlag != "N"){
						
					var newLot = newRowObj.insertCell(newRowObj.cells.length);
					newLot.setAttribute("className", cssName1);		
					newLot.innerHTML = "<div align='right'>" + data[i].lotname + "：</div>"; 
			
					var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
					newLotValue.setAttribute("className", cssName2);
					
					newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+lotattValue+"'  style='width:117px;'  class='input_readonly' readonly ></div>";
					
				}
			}
		}
	}
}	

//返回用户输入的批次属性值
function getattsinput(){
	var attcode = "";
	var retstr = "";
	for(var i=1; i<13; i++){
		attcode = "lotatt"+i;
		if(document.getElementById(attcode)!=null){
			retstr += "<input type='hidden' name='" + attcode + "' value='" + document.getElementById(attcode).value + "'>";
		}
	}
	return retstr;
}
