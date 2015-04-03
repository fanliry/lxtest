//显示下拉列表框的值
function selectObject(id, obj, flag)
{
	//id:表示默认选择的值,没有就为"";
	//obj:为下拉框的名
	//flag:
	if(flag == "1")//仓库
	{
		selectView.getWarehouseList(id, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "2")//库区
	{
		selectView.getWhAreaList(id, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "3")//货区
	{
		selectView.getAreaList(id, {callback:function(data){viewSelect(data,obj);}});
		
	}
	else if(flag == "4")//批次属性
	{
		//selectView.getBaseLotList(id, {callback:function(data){viewSelect(data,obj);}});
		selectView.getBaseLotList(id, {callback:function(data){viewSelect(data,obj);}});
	}
	else if(flag == "5")//所有类型值
	{
		selectView.getTypeParentList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "6")//所有父物品类别值
	{
		selectView.getPtTypeParentList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "11")//获得未收货完成的收货单
	{
		getTool.getInBoundInvoiceId(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "12")//获得可以上架的收货单
	{
		getTool.getPutawayInBoundInvoiceId(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "13")//获得可以发货确认的出库单
	{
		selectView.getOutInvoiceId(id, {callback:function(data){viewSelect(data,obj);}});
	}
	else if(flag == "14")//获得可以盘点申请单
	{
		selectView.getcheckReqLs(id, {callback:function(data){viewSelect(data,obj);}});
	}
	//else if(flag == "6")//FDYDTY产品类型
	//{
	//	judgmentTool.getFDYDTY(id, {callback:function(data){viewSelect(data,obj);}});
	//}else if(flag == "7")//选择产品
	//{
	//	judgmentTool.getProduct(id, {callback:function(data){viewSelect(data,obj);}});
	//}
	else if(flag == "31")//获得上架规则的规则方式
	{
		selectView.getRuleConfig(id, "1", {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "32")//获得分配规则的规则方式
	{
		selectView.getRuleConfig(id, "2", {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "33")//获得补货规则的规则方式
	{
		selectView.getRuleConfig(id, "3", {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "phmc")//批号类型 不要删 
	{
		selectView.getBaseBatchList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "workshop")//生产车间 不要删 
	{
		selectView.getWorkShopList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "bumen")//部门 不要删 
	{
		selectView.getBranchMeansList(id, {callback:function(data){viewSelect(data,obj);}});
	}else if(flag == "wplb")//物品状态，不要删
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
    var op0=new Option("--请选择--");        
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


//根据类型代码获取该类型的列表
function selectType(id, obj, code)
{
	selectView.getBaseTypeList(id, code, {callback:function(data){viewSelect(data,obj);}});
}
//根据类型代码获取该类型的列表
function selectTypeother(id, obj, code)
{
	selectView.getBaseTypeListother(id, code, {callback:function(data){viewSelect(data,obj);}});
}

//设置多个类型(类型的值一致)
function selectTypes(id, obj, code, inum)
{
	selectView.getBaseTypeList(id,code,{callback:function(data){viewSelectType(data,obj, inum);}});
}

function viewSelectType(data, obj, inum)
{
	for(var j = 1; j <= inum; j++)
	{
		//一个下拉框
		var g =document.getElementById(obj+j);
		while(g.options.length > 0) 
		{
	       	g.remove(g.options.length-1);
	    }
	    var op0=new Option("----- 请选择 ----");        
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

//根据仓库获得库区，货区的列表
function selectAreaList(id, obj, warehouseid, flag)
{
	if(flag == "1")//库区
	{
		selectView.getWhAreaList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "2")//货区
	{
		selectView.getCargoAreaList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "3")
	{
		selectView.getWhAreaVirtualList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
	}
}
//获取货位的排
function selectPlatoon(obj,platoon)
{
	
		selectView.getPlatoon({callback:function(data){viewSelect(data,obj);}});
		
	
}
////获取货位的列
function selectColumn(obj,column)
{
	
		selectView.getColumn({callback:function(data){viewSelect(data,obj);}});
		
	
}
//获取货位的层
function selectFloor(obj,floor)
{
	
		selectView.getFloor({callback:function(data){viewSelect(data,obj);}});
		
	
}
//根据仓库获得库区，货区的列表 (不是平库 例如 暂存区) 立体库
function selectAreaListNotTem(id, obj, warehouseid, flag)
{
	if(flag == "1")//库区
	{
		
		selectView.getWhAreaNotTemList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}else if(flag == "2")//货区
	{
		selectView.getCargoAreaList(id, warehouseid, {callback:function(data){viewSelect(data,obj);}});
		
	}
}
//根据仓库ID获得暂存区
function selectZCAreaList(obj, warehouseid,whAreaId)
{	
	selectView.getZCWhAreaList(warehouseid,whAreaId, {callback:function(data){viewSelect(data,obj);}});
}

//根据库区获得巷道的列表
function selectAlleyList(id, obj, whAreaId)
{
	selectView.getAlleyList(id, whAreaId, {callback:function(data){viewSelect(data,obj);}});
}

//根据包装获得单位列表
function selectinoutUnit(id, obj, packageid, inout)
{
	selectView.getInOutUnit(id, packageid, inout, {callback:function(data){viewSelect(data,obj);}});
}

//指定规则的列表
function selectRules(id, obj, warehouseid, flag)
{
	selectView.getRules(id, warehouseid, flag, {callback:function(data){viewSelect(data,obj);}});
}

//获得可以发货确认的B客户的出库单
function getBoutInvoiceId(id, aoutid, obj)
{
	selectView.getBOutInvoiceId(id, aoutid, {callback:function(data){viewSelect(data,obj);}});
}

