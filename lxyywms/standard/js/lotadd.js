
//ȡ���������Ե���������
function createLotAdd(tableObj, lotsearchtype, rows, cols){

	for(var i=tableObj.rows.length; i>rows; i--){
			tableObj.deleteRow(i-1);
	}

	if(lotsearchtype != ""){
		
		lotTool.getLotAdd(lotsearchtype, {callback:function(data){viewLotAdd(data, tableObj, cols);}});
	}
}	
		
//��ʾ�������Ե���������
function viewLotAdd(data, tableObj, cols){
  				
	if(data!= null){

		for(var j=0; j<=parseInt(data.length/cols); j++){
		
			var cssName1 = "yx1";
			var cssName2 = "yx";
			
			//����һ��
			var newRowObj = tableObj.insertRow(tableObj.rows.length);
				
			for(var i=(j*cols); i<((j+1)*cols); i++){
				
				if(j==(parseInt(data.length/cols))){	//���һ��
					cssName1 = "y1";
					if(i==(j+1)*cols-1){	//���һ��
						cssName2 = "";
					}else{
						cssName2 = "x";
					}
				}else{
					if(i==(j+1)*cols-1){	//���һ��
						cssName2 = "xx1";
					}
				}
				if(i==data.length){
					return;
				}
				var strCode = data[i].lotid;
				var strFlag = data[i].islot;  			 // �Ƿ���ʾ Y N
				var strType = data[i].lotformat;		 // 1-����,2-�ı�  3-����
				var lotatt_flag = data[i].lotatt_flag;  // 1-����,2-����,3-��ѡ
				
				var isNeed = "";
				if(lotatt_flag=="1")
				{
					isNeed = "<FONT COLOR='red'>*&nbsp;</FONT>";
				}
				
				var lt = ""; //����  
				if(strCode=='lotatt1')
				{	lt="lotatt001";}
				else if(strCode=='lotatt2')
				{	lt="lotatt002";}
				else if(strCode=='lotatt3')
				{	lt="lotatt003";}
				else if(strCode=='lotatt4')
				{	lt="lotatt004";}
				else if(strCode=='lotatt5')
				{	lt="lotatt005";}
				else if(strCode=='lotatt6')
				{	lt="lotatt006";}
				else if(strCode=='lotatt7')
				{	lt="lotatt007";}
				else if(strCode=='lotatt8')
				{	lt="lotatt008";}
				else if(strCode=='lotatt9')
				{	lt="lotatt009";}
				else if(strCode=='lotatt10')
				{	lt="lotatt010";}
				else if(strCode=='lotatt11')
				{	lt="lotatt011";}
				else if(strCode=='lotatt12')
				{	lt="lotatt012";}
	
				//���õĲ�����
				if(strFlag != "N"){
					
					if(strType == 1){	//��������
					
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div id='"+lt+"' align='right'>" + isNeed + data[i].lotname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' onFocus='calendar()' class='Wdate' style='width:117px;'></div>";
						
						
					}else if(strType == 2){	//�ı�����
						
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div id='"+lt+"' align='right'>" + isNeed + data[i].lotname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						
						
						newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' style='width:117px;'></div>";
						
						
					}else if(strType == 3){	//������

						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div id='"+lt+"' align='right'>" + isNeed + data[i].lotname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						//newLotValue.innerHTML = "<div align='left'><select name='" + data[i].lotid + "' style='width:117px;'><option value=''></option></select></div>";
						var strOptions = "";
						
						//��ȡ����������
						DWREngine.setAsync(false);
						selectView.getBaseTypeList("", data[i].lotformatvalue, {callback:function(newdata){
							for(var k = 0; k < newdata.length; k++){
						    	strOptions += "<option value='" + newdata[k].strId + "'>" + newdata[k].strName + "</option>";
							}
						}})
						DWREngine.setAsync(true);
						newLotValue.innerHTML = "<select name='" + data[i].lotid + "' style='width:117px;'><option value=''>--��ѡ��--</option>" + strOptions + "</select>";
						
						
						
					}
				}else{
					//document.getElementById(lt).value = "0";
				}
			}
		}
	}
}	

//�����û��������������ֵ
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
//��������1-12��ֵ��ʾ����
function createLotAddView(tableObj, lotsearchtype, rows, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){

	for(var i=tableObj.rows.length; i>rows; i--){
			tableObj.deleteRow(i-1);
	}

	if(lotsearchtype != ""){
		
		lotTool.getLotSearch(lotsearchtype, {callback:function(data){viewLotAddView(data, tableObj, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);}});
	}
}
function viewLotAddView(data, tableObj, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){		
	if(data!= null){
		for(var j=0; j<=parseInt(data.length/cols); j++){
		
			var cssName1 = "yx1";
			var cssName2 = "yx";
			
			//����һ��
			var newRowObj = tableObj.insertRow(tableObj.rows.length);
				
			for(var i=(j*cols); i<((j+1)*cols); i++){
				
				if(j==(parseInt(data.length/cols))){	//���һ��
					cssName1 = "y1";
					if(i==(j+1)*cols-1){	//���һ��
						cssName2 = "";
					}else{
						cssName2 = "x";
					}
				}else{
					if(i==(j+1)*cols-1){	//���һ��
						cssName2 = "xx1";
					}
				}
				if(i==data.length){
					return;
				}
				var strCode = data[i].lotid;
				var strFlag = data[i].islot;  			 // �Ƿ���ʾ Y N
				var strType = data[i].lotformat;		 // 1-����,2-�ı�  3-���� 4-ʱ��
				var lotatt_flag = data[i].lotatt_flag;  // 1-����,2-����,3-��ѡ
				
				var isNeed = "";
				if(lotatt_flag=="1")
				{
					isNeed = "<FONT COLOR='red'>*&nbsp;</FONT>";
				}
				
				var lt = ""; //����  
				var strValue = "";//ֵ
				if(strCode=='lotatt1')
				{	lt="lotatt001";strValue=lotatt1;}
				else if(strCode=='lotatt2')
				{	lt="lotatt002";strValue=lotatt2;}
				else if(strCode=='lotatt3')
				{	lt="lotatt003";strValue=lotatt3;}
				else if(strCode=='lotatt4')
				{	lt="lotatt004";strValue=lotatt4;}
				else if(strCode=='lotatt5')
				{	lt="lotatt005";strValue=lotatt5;}
				else if(strCode=='lotatt6')
				{	lt="lotatt006";strValue=lotatt6;}
				else if(strCode=='lotatt7')
				{	lt="lotatt007";strValue=lotatt7;}
				else if(strCode=='lotatt8')
				{	lt="lotatt008";strValue=lotatt8;}
				else if(strCode=='lotatt9')
				{	lt="lotatt009";strValue=lotatt9;}
				else if(strCode=='lotatt10')
				{	lt="lotatt010";strValue=lotatt10;}
				else if(strCode=='lotatt11')
				{	lt="lotatt011";strValue=lotatt11;}
				else if(strCode=='lotatt12')
				{	lt="lotatt012";strValue=lotatt12;}
	
				//���õĲ�����
				if(strFlag != "N"){
					
					if(strType == 1 || strType == 4 ){	//�������͡�ʱ��
					
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div  id='"+lt+"'  align='right'>"  + isNeed+ data[i].lotname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						if(data[i].lotsearch == 3){	//��Χ��ѯ
							
							if(strType == 1){	//��������
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "_from' value='"+strValue+"' onFocus='calendar()' class='Wdate' style='width:85px;'>-<input type='text' name='"+data[i].lotid + "_to' value='"+strValue+"' onFocus='calendar()' class='Wdate' style='width:85px;'></div>";
							}else if(strType == 4 ){//ʱ������
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "_from' value='"+strValue+"' onclick='calendartime(this)' class='Wdate' style='width:170px;'>-<input type='text' name='"+data[i].lotid + "_to' value='"+strValue+"' onclick='calendartime(this)' class='Wdate' style='width:170px;'></div>";
							}
						}else{
							
							if(strType == 1){	//��������
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' onFocus='calendar()' class='Wdate' style='width:117px;'></div>";
							}else if(strType == 4 ){//ʱ������
								newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' onclick='calendartime(this)' class='Wdate' style='width:170px;'></div>";
							}
						}
						
					}else if(strType == 2){	//�ı�����
						
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div  id='"+lt+"'  align='right'>" + isNeed +  data[i].lotname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						
						if(data[i].lotsearch == 2){	//ģ����ѯ
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' style='width:117px;' maxlength='50' class='molike'></div>";
						}else{
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].lotid + "' value='"+strValue+"' style='width:117px;' maxlength='50'></div>";
						}
						
					}else if(strType == 3){	//������

						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", cssName1);		
						newLot.innerHTML = "<div  id='"+lt+"'  align='right'>" + isNeed + data[i].lotname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", cssName2);
						//newLotValue.innerHTML = "<div align='left'><select name='" + data[i].lotid + "' style='width:117px;'><option value=''></option></select></div>";
						var strOptions = "";
						
						//��ȡ����������
						DWREngine.setAsync(false);
						selectView.getBaseTypeList(strValue, data[i].lotformatvalue, {callback:function(newdata){
							for(var k = 0; k < newdata.length; k++){
						    	strOptions += "<option value='" + newdata[k].strId + "'>" + newdata[k].strName + "</option>";
							}
						}})
						DWREngine.setAsync(true);
						newLotValue.innerHTML = "<select name='" + data[i].lotid + "' style='width:117px;'><option value=''>--��ѡ��--</option>" + strOptions + "</select>";
						//document.getElementById(lt).value = "1";
					}
				}else{
					//document.getElementById(lt).value = "0";
				}
			}
		}
	}
}
