<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	
</SCRIPT>
</head>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="40px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">任务号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">创建时间</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InventoryCheckTask checktask = null;
		
		String taskid;			//ID
		String status;			//状态
		String cargospace;		//货位
		String wharea;			//库区
		String product;			//产品
		String lotnumber;	    //货主
		String tyaycode;		//托盘条码
    	String productcode;     //产品条码
		String createtime;		//创建时间
		double num;				//库存数量
		
		for(int i = 0; i < ls.size(); i++){
			checktask = (InventoryCheckTask)ls.get(i);
			
			taskid = checktask.getTaskid();				//ID
			status = checktask.getStatusName();			//状态
			cargospace = checktask.getCargoSpaceName();	//货位
			wharea = checktask.getWhAreaName();			//库区
			product = checktask.getProductName();		//产品
			lotnumber = checktask.getLotinfo();	    //批号
			tyaycode = checktask.getTraycode();			//托盘条码
    		productcode = checktask.getProductcode(); 	//产品条码
			createtime = checktask.getCreatetime();		//创建时间
			num = checktask.getNum();					//库存数量
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=taskid==null?"":taskid%></td>
     <td class="TD_LIST" align="center"><%=wharea==null?"":wharea%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>    
	 <td class="TD_LIST" align="center"><%=tyaycode==null?"":tyaycode%></td>
     <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
     <td class="TD_LIST" align="center"><%=(int)num%></td>
     <td class="TD_LIST2" align="center"><%=createtime==null?"":createtime%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="<%=11%>" valign="top" class="TD_last_LIST">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
</body>
</html>