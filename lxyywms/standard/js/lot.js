//��ȡ�������Ե��б�
function selectLot(id, obj)
{
	lotTool.getAllLots(id, {callback:function(data){viewSelect(data,obj);}});
}


function viewSelect(data, obj){

	var g =document.getElementById(obj);
	while(g.options.length > 0) 
	{
       	g.remove(g.options.length-1);
    }
    var op0=new Option("--��ѡ��--");        
    g.options.add(op0);
    op0.value="";
	for(var i = 0; i < data.length; i++)
	{
		var op1=new Option(data[i].strName);        
    	g.options.add(op1);
    	op1.value=data[i].strId;
    	if(data[i].strFlag == "1")
    	{
    		op1.selected=true;
    	}	
	}
}

//ȡ�����ε���ϸ����
function createLotDetail(tableObj, lotid, rows, cols){

	for(var i=tableObj.rows.length; i>rows; i--){
			tableObj.deleteRow(i-1);
	}

	if(lotid != ""){
		
		lotTool.getLotDetails(lotid, {callback:function(data){viewLotDetail(data, tableObj, cols);}});
	}
}	
		
//��ʾ������ϸ
function viewLotDetail(data, tableObj, cols){
  					
	if(data!= null){
			
		for(var j=0; j<(data.length/cols); j++){
				
			//����һ��
			var newRowObj = tableObj.insertRow(tableObj.rows.length);
				
			for(var i=(j*cols); i<((j+1)*cols); i++){
			
				var strCode = data[i].m_strAttcode;
				var strFlag = data[i].m_strLotatt_flag;  // 1-����,2-����,3-��ѡ
				var strType = data[i].m_strLottype;		 // 1-����,2-�ı�  3-����
				var lt = ""; //����  0����ʾ���õģ�1����ʾ��ȷ���Ϳ��ѯ�� 2����ʾ����Ҫȡ��Χ�� 
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
	
				//���õĲ�����
				if(strFlag != 2){
					
					if(strType == 1){	//��������
					
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", "yx1");		
						newLot.innerHTML = "<div align='right'>" + data[i].m_strAttname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", "yx");
						if(data[i].m_strLotsearch == 3){	//��Χ��ѯ
							document.getElementById(lt).value = "2";//��Χ��ѯ
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].m_strAttcode + "_from' onFocus='calendar()' class='Wdate' style='height:21px;width:85px;'>-<input type='text' name='"+data[i].m_strAttcode + "_to' onFocus='calendar()' class='Wdate' style='height:21px;width:85px;'></div>";
						}else{
							document.getElementById(lt).value = "1";
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].m_strAttcode + "' onFocus='calendar()' class='Wdate' style='height:21px;width:100px;'></div>";
						}
						
					}else if(strType == 2){	//�ı�����
						
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", "yx1");		
						newLot.innerHTML = "<div align='right'>" + data[i].m_strAttname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", "yx");
						document.getElementById(lt).value = "1";
						if(data[i].m_strLotsearch == 2){	//ģ����ѯ
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].m_strAttcode + "' size='15' maxlength='50' class='molike'></div>";
						}else{
							newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].m_strAttcode + "' size='15' maxlength='50'></div>";
						}
						
					}else if(strType == 3){	//������

						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", "yx1");		
						newLot.innerHTML = "<div align='right'>" + data[i].m_strAttname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", "yx");
						//newLotValue.innerHTML = "<div align='left'><select name='" + data[i].m_strAttcode + "' style='width:117px;'><option value=''></option></select></div>";
						var strOptions = "";
						
						//��ȡ����������
						DWREngine.setAsync(false);
						selectView.getBaseTypeList("", data[i].m_strLottypevalue, {callback:function(newdata){
							for(var k = 0; k < newdata.length; k++){
						    	strOptions += "<option value='" + newdata[k].strId + "'>" + newdata[k].strName + "</option>";
							}
						}})
						DWREngine.setAsync(true);
						newLotValue.innerHTML = "<select name='" + data[i].m_strAttcode + "' style='width:117px;'><option value=''>--��ѡ��--</option>" + strOptions + "</select>";
						document.getElementById(lt).value = "1";
					}
				}else{
					document.getElementById(lt).value = "0";
				}
			}
		}
	}
}	
//��ʾ����ֵ1
function createLotDetail1(tableObj, lotid, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){

	if(lotid != ""){
		
		lotTool.getLotDetails(lotid, {callback:function(data){viewLotDetail1(data, tableObj, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);}});
	}
}
//��ʾ������ϸ1
function viewLotDetail1(data, tableObj, cols, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){
  					
	if(data!= null){
			
		for(var j=0; j<(data.length/cols); j++){
				
			//����һ��
			var newRowObj = tableObj.insertRow(tableObj.rows.length);
				
			for(var i=(j*cols); i<((j+1)*cols); i++){
			
				var strCode = data[i].m_strAttcode;
				var strFlag = data[i].m_strLotatt_flag;  // 1-����,2-����,3-��ѡ
				var strType = data[i].m_strLottype;		 // 1-����,2-�ı�  3-����
				var lt = ""; //ֵ
				if(strCode=='lotatt1')
				{	lt=lotatt1;}
				else if(strCode=='lotatt2')
				{	lt=lotatt2;}
				else if(strCode=='lotatt3')
				{	lt=lotatt3;}
				else if(strCode=='lotatt4')
				{	lt=lotatt4;}
				else if(strCode=='lotatt5')
				{	lt=lotatt5;}
				else if(strCode=='lotatt6')
				{	lt=lotatt6;}
				else if(strCode=='lotatt7')
				{	lt=lotatt7;}
				else if(strCode=='lotatt8')
				{	lt=lotatt8;}
				else if(strCode=='lotatt9')
				{	lt=lotatt9;}
				else if(strCode=='lotatt10')
				{	lt=lotatt10;}
				else if(strCode=='lotatt11')
				{	lt=lotatt11;}
				else if(strCode=='lotatt12')
				{	lt=lotatt12;}
	
				//���õĲ�����
				if(strFlag != 2){
					
					if(strType == 1){	//��������
					
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", "yx1");		
						newLot.innerHTML = "<div align='right'>" + data[i].m_strAttname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", "yx");
						
						newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].m_strAttcode + "' value='"+lt+"'  style='height:21px;width:100px;' readonly class='input_readonly'></div>";
						
						
					}else if(strType == 2){	//�ı�����
						
						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", "yx1");		
						newLot.innerHTML = "<div align='right'>" + data[i].m_strAttname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", "yx");
						
						newLotValue.innerHTML = "<div align='left'><input type='text' name='"+data[i].m_strAttcode + "'  value='"+lt+"'   size='15' maxlength='50' readonly class='input_readonly'></div>";
						
						
					}else if(strType == 3){	//������

						var newLot = newRowObj.insertCell(newRowObj.cells.length);
						newLot.setAttribute("className", "yx1");		
						newLot.innerHTML = "<div align='right'>" + data[i].m_strAttname + "��</div>"; 
				
						var newLotValue=newRowObj.insertCell(newRowObj.cells.length);
						newLotValue.setAttribute("className", "yx");
						
						newLotValue.innerHTML = "<select name='" + data[i].m_strAttcode + "' style='width:100px;' disabled class='input_readonly'><option value='" + lt + "'>" + lt + "</option></select>";

					}
				}
			}
		}
	}
}			


function setLotValue(lotid, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
{
	lotTool.getLotDetails(lotid, {callback:function(data){setViewLotatt(data, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);}});
}
function setViewLotatt(data, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
{
	for(var i = 0; i < data.length; i++)
	{
		var strCode = data[i].m_strAttcode;
		var strName = data[i].m_strAttname;
		var strFlag = data[i].m_strLotatt_flag;  // 1-����,2-����,3-��ѡ
		var strType = data[i].m_strLottype;		 // 1-����,2-�ı�  3-����
		var strSelectCode = data[i].m_strLottypevalue;	//�����õ����ʹ���
		
		var isNeed = "";
		if(strFlag=="1")
		{
			isNeed = "<FONT COLOR='red'>*&nbsp;</FONT>";
		}
		
		var nameDiv = "";
		var nameInput = "";
		var nameValue = "";
		
		var lotattValue = "";//ֵ
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
		//�޸��������Ե�����	
		document.all(nameDiv).innerHTML = isNeed + strName + "��";
		if(strFlag=="1" || strFlag=="3") //1-����,2-����,3-��ѡ
		{
				document.all(nameInput).disabled = false;
				document.all(nameDiv).style.color = "";
				document.all(nameInput).className = "";

			if(strType == 1){	//��������
				document.all(nameValue).innerHTML = "<input type='text' name='"+data[i].m_strAttcode + "' value='"+lotattValue+"' onFocus='calendar()' class='Wdate' style='height:21px;width:100px;'>";
			}else if(strType == 3){//������	
					var strOptions = "";
					//��ȡ����������
					DWREngine.setAsync(false);
					selectView.getBaseTypeList(lotattValue, strSelectCode, {callback:function(newdata){
						for(var k = 0; k < newdata.length; k++){
									var sele = "";
									if(newdata[k].strFlag == "1")
    								{
    									sele = "selected='selected'";
    								}
							    	strOptions += "<option value='" + newdata[k].strId + "' " + sele + "  >" + newdata[k].strName + "</option>";
								}
						}})
					DWREngine.setAsync(true);
					document.all(nameValue).innerHTML = "<select name='" + data[i].m_strAttcode + "' style='width:100px;'><option value=''>--��ѡ��--</option>" + strOptions + "</select>";
							
				
			}else{
					document.all(nameValue).innerHTML = "<input type='text' name='"+data[i].m_strAttcode + "' value='"+lotattValue+"'  style='height:18px;width:100px;'>";
				}				
		}else
		{
				document.all(nameDiv).style.color = "#999999";
				document.all(nameInput).disabled = true;
				document.all(nameInput).className = "input_readonly1";
		}
		
	}
}

	//��һ����������ֵ�ļ���
	function checkOneLotatt(v1,v2)
	{
		var lotatt = document.getElementById(v1).value;
		var isneeded;
		var divName = document.all(v2).innerHTML;
		if(divName.indexOf("</FONT>") > 0)//Ҫ��֤
		{
			isneeded = "needed";
		}
		if(isneeded!=null && isneeded=="needed")
		{
			divName = divName.substring(divName.indexOf("</FONT>")+7, divName.length-1);
		}
		else
		{
			divName = divName.substring(0, divName.length-1);
		}
		
		if(isneeded!=null && isneeded=="needed")
		{
			if(lotatt == null || lotatt.length <1)
			{
				alert("��" + divName + "������Ϊ��!");
				return false;
			}
		}
		
		if(lotatt.indexOf(" ")!=-1)
		{
			alert("��" + divName + "�����ܰ����ո�");
			return false;
		}
		
		return true;
	}
	//����������������ֵ
	function checkLotatt()
	{
		for(var i=1; i<13; i++)
		{
			if(i<10)
			{
				if(!checkOneLotatt("lotatt"+i,"lotatt00"+i))
				{
					return false;
				}
			}else
			{
				if(!checkOneLotatt("lotatt"+i,"lotatt0"+i))
				{
					return false;
				}
			}
		}
	}

//����ֻ��
function setLotValueReadonly(lotid, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
{
	lotTool.getLotDetails(lotid, {callback:function(data){setViewLotattReadonly(data, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);}});
}
function setViewLotattReadonly(data, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
{
	for(var i = 0; i < data.length; i++)
	{
		var strCode = data[i].m_strAttcode;
		var strName = data[i].m_strAttname;
		var strFlag = data[i].m_strLotatt_flag;  // 1-����,2-����,3-��ѡ
		var strType = data[i].m_strLottype;		 // 1-����,2-�ı�  3-����
		var strSelectCode = data[i].m_strLottypevalue;	//�����õ����ʹ���
		
		var isNeed = "";
		if(strFlag=="1")
		{
			isNeed = "<FONT COLOR='red'>*&nbsp;</FONT>";
		}
		
		var nameDiv = "";
		var nameInput = "";
		var nameValue = "";
		
		var lotattValue = "";//ֵ
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
		//�޸��������Ե�����	
		document.all(nameDiv).innerHTML = isNeed + strName + "��";
		if(strFlag=="1" || strFlag=="3") //1-����,2-����,3-��ѡ
		{
				document.all(nameInput).disabled = false;
				document.all(nameDiv).style.color = "";
				document.all(nameInput).className = "";

			if(strType == 1){	//��������
				document.all(nameValue).innerHTML = "<input type='text' name='"+data[i].m_strAttcode + "' value='"+lotattValue+"'  class='input_readonly' readonly style='height:21px;width:100px;'>";
			}else if(strType == 3){//������	
					var strOptions = "";
					//��ȡ����������
					DWREngine.setAsync(false);
					selectView.getBaseTypeList(lotattValue, strSelectCode, {callback:function(newdata){
						for(var k = 0; k < newdata.length; k++){
									var sele = "";
									if(newdata[k].strFlag == "1")
    								{
    									sele = "selected='selected'";
    								}
							    	strOptions += "<option value='" + newdata[k].strId + "' " + sele + "  >" + newdata[k].strName + "</option>";
								}
						}})
					DWREngine.setAsync(true);
					document.all(nameValue).innerHTML = "<select name='" + data[i].m_strAttcode + "' style='width:100px;'  class='input_readonly' disabled ><option value=''>--��ѡ��--</option>" + strOptions + "</select>";
							
				
			}else{
					document.all(nameValue).innerHTML = "<input type='text' name='"+data[i].m_strAttcode + "' value='"+lotattValue+"'  style='height:18px;width:100px;'  class='input_readonly' readonly >";
				}				
		}else
		{
				document.all(nameDiv).style.color = "#999999";
				document.all(nameInput).disabled = true;
				document.all(nameInput).className = "input_readonly1";
		}
		
	}
}