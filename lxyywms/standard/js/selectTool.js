//��ʾ�����б���ֵ
function selectObject(id, obj, flag)
{
	//id:��ʾĬ��ѡ���ֵ,û�о�Ϊ"";
	//obj:Ϊ���������
	//flag:
	if(flag == "1")//�ֿ�
	{
		selectView.getWarehouseList(id, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "2")//����
	{
		selectView.getWhAreaList(id, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "3")//����
	{
		selectView.getAreaList(id, {callback:function(data){viewSelect(data,obj);}});
		
	}
	else if(flag == "4")//��������
	{
		//selectView.getBaseLotList(id, {callback:function(data){viewSelect(data,obj);}});
		selectView.getBaseLotList(id, {callback:function(data){viewSelect(data,obj);}});
	}
	else if(flag == "5")//��������ֵ
	{
		selectView.getTypeParentList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "6")//���и���Ʒ���ֵ
	{
		selectView.getPtTypeParentList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "11")//���δ�ջ���ɵ��ջ���
	{
		getTool.getInBoundInvoiceId(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "12")//��ÿ����ϼܵ��ջ���
	{
		getTool.getPutawayInBoundInvoiceId(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "13")//��ÿ��Է���ȷ�ϵĳ��ⵥ
	{
		selectView.getOutInvoiceId(id, {callback:function(data){viewSelect(data,obj);}});
	}
	else if(flag == "14")//��ÿ����̵����뵥
	{
		selectView.getcheckReqLs(id, {callback:function(data){viewSelect(data,obj);}});
	}
	//else if(flag == "6")//FDYDTY��Ʒ����
	//{
	//	judgmentTool.getFDYDTY(id, {callback:function(data){viewSelect(data,obj);}});
	//}else if(flag == "7")//ѡ���Ʒ
	//{
	//	judgmentTool.getProduct(id, {callback:function(data){viewSelect(data,obj);}});
	//}
	else if(flag == "31")//����ϼܹ���Ĺ���ʽ
	{
		selectView.getRuleConfig(id, "1", {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "32")//��÷������Ĺ���ʽ
	{
		selectView.getRuleConfig(id, "2", {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "33")//��ò�������Ĺ���ʽ
	{
		selectView.getRuleConfig(id, "3", {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "phmc")//�������� ��Ҫɾ 
	{
		selectView.getBaseBatchList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "workshop")//�������� ��Ҫɾ 
	{
		selectView.getWorkShopList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "bumen")//���� ��Ҫɾ 
	{
		selectView.getBranchMeansList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "wplb")//��Ʒ״̬����Ҫɾ
	{
		selectView.getProductStatus(id, {callback:function(data){viewSelect(data,obj);}});
	}
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


//�������ʹ����ȡ�����͵��б�
function selectType(id, obj, code)
{
	selectView.getBaseTypeList(id, code, {callback:function(data){viewSelect(data,obj);}});
}
//�������ʹ����ȡ�����͵��б�
function selectTypeother(id, obj, code)
{
	selectView.getBaseTypeListother(id, code, {callback:function(data){viewSelect(data,obj);}});
}

//���ö������(���͵�ֵһ��)
function selectTypes(id, obj, code, inum)
{
	selectView.getBaseTypeList(id,code,{callback:function(data){viewSelectType(data,obj, inum);}});
}

function viewSelectType(data, obj, inum)
{
	for(var j = 1; j <= inum; j++)
	{
		//һ��������
		var g =document.getElementById(obj+j);
		while(g.options.length > 0) 
		{
	       	g.remove(g.options.length-1);
	    }
	    var op0=new Option("----- ��ѡ�� ----");        
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
}

//���ݲֿ��ÿ������������б�
function selectAreaList(id, obj, warehouseid, flag)
{
	if(flag == "1")//����
	{
		selectView.getWhAreaList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "2")//����
	{
		selectView.getCargoAreaList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "3")
	{
		selectView.getWhAreaVirtualList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
	}
}
//��ȡ��λ����
function selectPlatoon(obj,platoon)
{
	
		selectView.getPlatoon({callback:function(data){viewSelect(data,obj);}});
		
	
}
////��ȡ��λ����
function selectColumn(obj,column)
{
	
		selectView.getColumn({callback:function(data){viewSelect(data,obj);}});
		
	
}
//��ȡ��λ�Ĳ�
function selectFloor(obj,floor)
{
	
		selectView.getFloor({callback:function(data){viewSelect(data,obj);}});
		
	
}
//���ݲֿ��ÿ������������б� (����ƽ�� ���� �ݴ���) �����
function selectAreaListNotTem(id, obj, warehouseid, flag)
{
	if(flag == "1")//����
	{
		
		selectView.getWhAreaNotTemList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "2")//����
	{
		selectView.getCargoAreaList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}
}
//���ݲֿ�ID����ݴ���
function selectZCAreaList(obj, warehouseid,whAreaId)
{	
	selectView.getZCWhAreaList(warehouseid,whAreaId, {callback:function(data){viewSelect(data,obj);}});
}

//���ݿ������������б�
function selectAlleyList(id, obj, whAreaId)
{
	selectView.getAlleyList(id, whAreaId, {callback:function(data){viewSelect(data,obj);}});
}

//���ݰ�װ��õ�λ�б�
function selectinoutUnit(id, obj, packageid, inout)
{
	selectView.getInOutUnit(id, packageid, inout, {callback:function(data){viewSelect(data,obj);}});
}

//ָ��������б�
function selectRules(id, obj, warehouseid, flag)
{
	selectView.getRules(id, warehouseid, flag, {callback:function(data){viewSelect(data,obj);}});
}

//��ÿ��Է���ȷ�ϵ�B�ͻ��ĳ��ⵥ
function getBoutInvoiceId(id, aoutid, obj)
{
	selectView.getBOutInvoiceId(id, aoutid, {callback:function(data){viewSelect(data,obj);}});
}

