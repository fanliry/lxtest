<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
	OutboundInvoiceDetail outInvoiceDetail = (OutboundInvoiceDetail)request.getAttribute("outInvoiceDetail");
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>库存详细</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>

	function Change(obj, inum)
	{
	   
    	var pinum = parent.inum.innerHTML;//数量
    	
    	var oldobj1 = obj.parentNode.parentNode.cells.item(10).getElementsByTagName('INPUT')[0];
    	
    	var oldobj2 = obj.parentNode.parentNode.cells.item(10).getElementsByTagName('INPUT')[1];
    	//var selectnum = 0;
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
			oldobj1.disabled="disabled";
    		//parent.inum.innerHTML = parseInt(selectnum) + parseInt(oldobj1.value)+ parseInt(pinum);//数量
    		parent.inum.innerHTML =  parseFloat(pinum) + parseFloat(oldobj1.value);//数量
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
			oldobj1.disabled="";
    		//parent.inum.innerHTML = parseInt(selectnum) - parseInt(oldobj1.value)+ parseInt(pinum);//数量
    		parent.inum.innerHTML =  parseFloat(pinum) - parseFloat(oldobj1.value);//数量
    		
		}
	}
	function Change1(obj, inum)
	{
    	var pinum = parent.inum.innerHTML;//数量
    	var o = obj.parentNode.parentNode.cells.item(0).getElementsByTagName('INPUT')[0];
    	var oldobj = obj.parentNode.getElementsByTagName('INPUT')[1];
		if(o.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
    		parent.inum.innerHTML = parseFloat(pinum)-(parseFloat(oldobj.value)-parseFloat(obj.value));//数量
    		parent.synum.innerHTML = (parent.a.innerHTML-parent.b.innerHTML) - (parseFloat(pinum)-(parseFloat(oldobj.value)-parseFloat(obj.value)));//未分配数量
		}
		oldobj.value = obj.value;
	}

	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		var fpnums = document.getElementsByName("fpnum");
		
		var temp = 0;
		var pinum = parent.inum.innerHTML;//数量
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			 
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
				fpnums[i].disabled="disabled";
				temp = parseFloat(temp) + parseFloat(fpnums[i].value);
				parent.inum.innerHTML = parseFloat(temp);
		    	
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
				fpnums[i].disabled="";
				parent.inum.innerHTML = "0";
				
			}
		}
	}
	
	function OnLoad(){
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){	
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();" >
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
<table width="100%" id="tbone" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">托盘</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title" width="100">数量</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品状态</div></td>
   </tr>
<%
	/* ss */
	
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		Object[] obj = null;  //库存统计信息
		
		InventoryStorage sto = null;
		
		String whAreaId;       //库区ID
		String whAreaName;	   //库区名称
		
		String cargoSpaceId;   //货位ID
		String cargoSpaceName; //货位名称
		String lotInfo; 	//批号
		String traycode;       //托盘条码
		
      	String productid;		//品名ID
      	String productname;		//品名
      	String productCode;	//产品编码
      	String printdate;//生产日期
      	String eunit;			//单位ID
      	String eunitname;		//单位名称
      	String indate;//入库时间
      	double pnum;				//库存数量  
      	double num;
      	double unassignnum;					//未分配数量   =  开单数量 - 已分配数量
		
      	
      	double invoicenum;          //开单数量
      	double preassignnum;        //预配数量
      	double assignnum;           //分配数量
      	double picknum;             //拣货数量 对作业复核时 复核成功 进行 同时修改其拣货数量
      	double sendnum;             //发货数量

      	
	  	double weight;			//库存剩余重量
      	double netweight;		//库存剩余净重
      	double volume;			//库存剩余体积
      	
      	String productStatus;//物品状态
      	String productStatusName;//物品状态名
      	
      	String ownerid;			//货主ID
      	String ownername;       //货主
      	String meg; 
      	String strKey = "";	
      	String inventoryId;
		for(int i = 0; i < ls.size(); i++)
		{
			 obj = (Object[])ls.get(i);
			 sto = (InventoryStorage)obj[0];
			 
			 whAreaId = sto.getWhAreaId();
			 whAreaName = sto.getWhAreaName();
			 cargoSpaceId = sto.getCargoSpaceId();//货位ID
			 cargoSpaceName = sto.getCargoSpaceName();//货位名称
			 traycode = sto.getTraycode();//托盘条码
			 productid = sto.getProductid();//品名ID
			 productname = sto.getProductName();//品名
			 eunit = sto.getPunit();//单位ID
			 eunitname = sto.getPunitname();//单位名称

			 pnum = sto.getPnum();//库存数量  
			 num = sto.getPnum() - sto.getAssignnum(); //库存剩余数量（可分配数量）  = 托盘产品数量 - 已分配数量
			

			
			 invoicenum = outInvoiceDetail.getInvoicenum();          //开单数量
			 preassignnum = outInvoiceDetail.getPreassignnum();        //预配数量
			 assignnum = outInvoiceDetail.getAssignnum();           //分配数量
			 picknum = outInvoiceDetail.getPicknum();             //拣货数量 对作业复核时 复核成功 进行 同时修改其拣货数量
			 sendnum = outInvoiceDetail.getSendnum();             //发货数量
			 
		     unassignnum = invoicenum - assignnum;		  //未分配数量   =  开单数量 - 已分配数量
		    
			 netweight = sto.getPweight();//库存重量
			 volume = sto.getPvolume();//库存体积  
			 inventoryId = sto.getInventoryid();//库存ID
			 productCode = sto.getProductcode();//产品编码
			 printdate = sto.getProductdate();//生产日期
			 ownerid = sto.getOwnerId();//货主ID
			 ownername = sto.getOwnerName(); //货主
			 lotInfo = sto.getLotinfo();//批号
			 indate = sto.getIndate();//入库时间
			 productStatus =sto.getProductstatus();//产品状态
			 productStatusName=sto.getProductStatusName();//物品状态名
      	 	 strKey =  productid + "|" + ownerid + "|";
      	 		meg = cargoSpaceId+":"+traycode+"["+inventoryId+","+num;
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''" >
		     <td class="TD_LIST" align="center"><input onClick="Change(this,'<%=num%>')" type="checkbox" name="check_id" class="input_checkbox" value="<%=meg%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=whAreaName%></td>
		     <td class="TD_LIST" align="center"><%=cargoSpaceName%></td>
		     <td class="TD_LIST" align="center"><%=traycode%></td>
		     <td class="TD_LIST" align="center"><%=lotInfo%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=productname%></td>
		     <td class="TD_LIST" align="center"><%=printdate%></td>
		     <td class="TD_LIST" align="center"><%=eunitname%></td>
		     <td class="TD_LIST" align="center" style="width:100px;"><input type='text' name='num' style="width:100px;text-align:center;" value='<%=nbf.format(num)%>' disabled></td>
			 
			 <td class="TD_LIST" align="center" style="width:100px;">
			 <input onchange="Change1(this,'<%=num%>')" type='text' name='fpnum' style="width:100px;text-align:center;" value='<%=num%>'>
			 <input type='hidden' name='oldnum'  value='<%=unassignnum%>'>
			 </td>
		     <td class="TD_LIST" align="center"><%=productStatusName==null?"":productStatusName%></td>	     		     
		   </tr>	       	 
<%	      	 
		}
	}
%>

   <tr>
     <td height="100%" colspan="8" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
   </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
