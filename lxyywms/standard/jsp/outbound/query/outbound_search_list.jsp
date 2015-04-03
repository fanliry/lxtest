<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%

%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="130%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进场编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业类型</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">巷道</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生成时间</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">操作员</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">联机模式</div></td>
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
		
		Object[] obj = null;
		InoutJob job = null;
		InoutJobdetail detail = null;
		
		String product = null;		// 品名
		String owerner = null;      // 货主
		String pack = null;			// 包装
		String punit = null;		// 单位
		String boxcode = null;		// 箱条码
		double jobnum = 0;			// 数量
		double volume = 0;         	// 体积
 		double weight = 0;         	// 重量
		double netweight = 0;		// 净重
		

     	
     	String jobid;      		//作业号
     	String status;       	//作业状态
     	String jobtype;         //作业类型
     	String whArea;  		//库区
     	String alley;    		//巷道
     	String cargospace;     	//货位
     	String traycode;     	//托盘条码
     	String createtime;  	//生成时间
     	String opMan;  			//操作员
     	String invoicetype;  	//作业来源
     	String onLineType;  	//联机模式
     	
     	//小计
		double jobnum_sum = 0;			// 数量
		double volume_sum = 0;         	// 体积
 		double weight_sum = 0;         	// 重量
		double netweight_sum = 0;		// 净重
		
		String  lotinfo = "";
		String  printdate = "";
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			detail = (InoutJobdetail)obj[1];
			
			lotinfo = detail.getLotinfo();
			printdate = detail.getPrintdate();
			product = detail.getM_strProductName();		// 品名
			owerner = detail.getM_strOwnerName();   	// 货主
			pack = detail.getM_strPackName();			// 包装
			punit = detail.getM_strUnitName();			// 单位
			boxcode = detail.getBoxcode();				// 箱条码
			jobnum = detail.getJobnum();				// 数量
			volume = detail.getVolume();         		// 体积
 			weight = detail.getWeight();         		// 重量
			netweight = detail.getNetweight();			// 净重
			
			
     	 	
     	 	jobid = job.getJobid();      			//作业号
     		status = job.getStatusName();       	//作业状态
     		jobtype = job.getJobtypeName();     	//作业类型
     		whArea = job.getTcargoWhareaName();		//库区
     		alley = job.getTcargoAlleyName();		//巷道
     		cargospace = job.getTcargoSpaceName(); 	//货位
     		traycode = job.getTraycode();     		//托盘条码
     		createtime = job.getCreatetime();  		//生成时间
     		opMan = job.getOpMan();  				//操作员
     		invoicetype = job.getInvoicetype();  	//作业来源
     		onLineType = job.getOnLineType();  		//联机模式
     		
     		//小计
			jobnum_sum += jobnum;			// 数量
			volume_sum += volume;         	// 体积
 			weight_sum += weight;         	// 重量
			netweight_sum += netweight;		// 净重
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=punit==null?"":punit%></td>
     <td class="TD_LIST" align="center"><%=(int)jobnum%></td>
     <td class="TD_LIST" align="center"><%=jobid%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=whArea==null?"":whArea%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=createtime%></td>   
     <td class="TD_LIST" align="center"><%=opMan%></td>
     <td class="TD_LIST2" align="center"><%=onLineType=="1"?"联机":"脱机"%></td>
   </tr>
<%
		}
%>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;">单页小计</td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=(int)jobnum_sum%></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
   </tr>
<%
	}
%>  
   <tr>
     <td height="100%" colspan="<%=16%>" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>