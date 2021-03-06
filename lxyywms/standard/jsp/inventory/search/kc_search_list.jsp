<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>
<%
   
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:120%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table  id="theTable" width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">逻辑库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库位编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库位名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进厂编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位/库存状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">物品状态</div></td>
   </tr>
<%
	
	
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
	    InventoryStorage storage = null;
	    BaseCargospace space = null;
		String whArea;				//库区
 		String cargoSpace;			//货位编码
 		String cargoSpaceName;		//货位名
 	 	String product;				//产品
 	 	String lotinfo;		    	//进厂编号
 	 	String instockid;           //入库单
 	 	String requestid;           //申请单
 	 	String traycode;        	//托盘条码
		String punit;				//单位
		double pnum;            	//库存数量
		String indate;             	//入库时间
		String intype;             	//入库方式 1.联机 2.脱机
        String productcode;         //产品编码
        String printdate;         //生产日期
        String csstauts;         //库存状态
        String productstatus;     //物品状态
        
        String Virtualwhname = "";
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 	    ob = (Object[])ls.get(i);
  	 		storage = (InventoryStorage)ob[0];
  	 		space = (BaseCargospace)ob[1];
  	 		whArea = storage.getWhAreaName();		//库区
  	 		cargoSpace = storage.getCargoSpaceId(); //货位编码
  	 		cargoSpaceName = storage.getCargoSpaceName();//货位名
  	 		product = storage.getProductName();		//产品
  	 		lotinfo = storage.getLotinfo();		//进厂编号
  	 		traycode = storage.getTraycode();		//托盘条码
  	 		punit = storage.getPunitname();			//单位
  	 		productcode = storage.getProductcode(); //产品编码		
			pnum = storage.getPnum();           //库存数量
			indate = storage.getIndate();       //入库时间
  	 		intype = storage.getIntype();		//入库方式 1.联机 2.脱机
  	 		requestid = storage.getRequestid();
  	 		instockid = storage.getInstockid();
  	 		
  	 		printdate = storage.getProductdate(); //生产日期
  	 		csstauts = space.getCsstatusname()+"/"+storage.getStatusName();
  	 		
  	 		productstatus = storage.getProductStatusName();
  	 		traycode = storage.getTraycode();
  	 		
  	 		Virtualwhname = storage.getVirtualwhname();
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=Virtualwhname == null ? "" : Virtualwhname%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace == null ? "" : cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceName == null ? "" : cargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=indate == null ? "" : indate%></td>
     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
     <td class="TD_LIST" align="center"><%=csstauts == null ? "" : csstauts%></td>
     <td class="TD_LIST" align="center"><%=productstatus == null ? "" : productstatus%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=16%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
