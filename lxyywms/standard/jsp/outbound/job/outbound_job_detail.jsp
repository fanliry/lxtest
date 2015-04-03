<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
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
 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div>
     </td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进场编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">是否复合</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">分配数量</div></td>
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
	
		InoutJobdetail detail = null;
		String jobdetailid = null;	// 作业详细ID
		String productcode=null;//产品编码
		String product = null;		// 品名
		String isFH = null;			//是否复合
		String owerner = null;      // 货主
		String pack = null;			// 包装
		String punit = null;		// 单位
		String boxcode = null;		// 箱条码
		double jobnum = 0;			// 库存数量
		double jobfpnum = 0;			// 分配数量
		double volume = 0;         	// 体积
 		double weight = 0;         	// 重量
		double netweight = 0;		// 净重
		String isinvoice;      		// 是否已生成单据 Y是  N否
		String printdate; //生产日期
		String lotinfo="";
		
		for(int i = 0; i < ls.size(); i++){
			detail = (InoutJobdetail)ls.get(i);
			
			jobdetailid = detail.getJobdetailid();		// 作业详细ID
			productcode = detail.getProductcode();
			product = detail.getM_strProductName();		// 品名
			isFH = detail.getIsreview();	//是否复合
			owerner = detail.getM_strOwnerName();   	// 货主
			pack = detail.getM_strPackName();			// 包装
			punit = detail.getM_strUnitName();			// 单位
			boxcode = detail.getBoxcode();				// 箱条码
			jobnum = detail.getJobnum();				// 数量
			jobfpnum = detail.getAssignnum();           //分配作业
			volume = detail.getVolume();         		// 体积
 			weight = detail.getWeight();         		// 重量
			netweight = detail.getNetweight();			// 净重
			isinvoice = detail.getIsinvoice();			// 是否已生成单据 Y是  N否
			printdate= detail.getPrintdate();
			lotinfo = detail.getLotinfo();
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
     <td class="TD_LIST" align="center"><%=isFH==null?"":isFH%></td>
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td> 
     <td class="TD_LIST" align="center"><%=punit==null?"":punit%></td> 
     <td class="TD_LIST" align="center"><%=(int)jobnum%></td>
     <td class="TD_LIST" align="center"><%=(int)jobfpnum%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="9" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>