<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
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
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">

	
	function OnLoad(){
	
		parent.RemoveLoading();
		//parent.page.location.reload();
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
// 		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="180%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" align="center" width="50px"><div class="list_title">选择</div></td>
     <td class="TD_LIST_TITLE" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业类型</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">具体批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">巷道</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">输送机号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生成时间</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">完成时间</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库单号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库申请单</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	if(ls != null && ls.size()>0){
		
		Object[] obj = null;
		InoutJob job = null;
		InoutJobdetail detail = null;
     	
     	String jobid;      	//作业号
     	String jobtype;    //作业类型 （回流的作业类型为 入库类型的hl值）
     	String jobtypeid;
     	String productCode;//产品编码
     	String m_strProductName = null;	 //品名
		String lotinfo = null;	         //具体批号
		String printdate = null;	     //生产日期
		String m_strUnitName = null;	//单位
		double jobnum = 0;			    //数量
		String warehouseName;  	//仓库
     	String alley;    		//巷道
     	String cargospace;     	//货位
     	String traycode;     	//托盘条码
     	String snumber;    		//输送号
     	String createtime;  	//生成时间
     	String finishtime; 		//完成时间
     	String invoicetype;//1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
     	
     	String boundstockid="";
     	String boundrequeststockid="";
     	String alt="";
     	String key="";
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			detail = (InoutJobdetail)obj[1];
			
			jobid = job.getJobid();      			//作业号
			jobtypeid = job.getJobtype();
     		jobtype = job.getJobtypeName();
			productCode = detail.getProductcode();
			m_strProductName = detail.getM_strProductName();
			lotinfo = detail.getLotinfo();
			printdate = detail.getPrintdate();
			m_strUnitName = detail.getM_strUnitName();
			jobnum = detail.getJobnum();
			
     		warehouseName = job.getWarehousename();	//仓库
     		alley = job.getTcargoAlleyName();		//巷道
     		cargospace = job.getTcargoSpaceName(); 	//货位
     		traycode = job.getTraycode();     		//托盘条码
     		snumber = job.getSnumber();    			//输送号
     		createtime = job.getCreatetime();  		//生成时间
     		finishtime = job.getFinishtime(); 		//完成时间
     		invoicetype = job.getInvoicetypename();
     		
     		boundstockid = job.getBoundstockid();
			boundrequeststockid = job.getBoundrequeststockid();
			
			alt = (i+1) + "|" +  jobtypeid;
			key = jobid;
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
   	 <td class="TD_LIST1" align="center" width="50px"><input type="checkbox" name="check_id" alt="<%=alt %>" value="<%=key %>" class="input_radio" onClick=""></td>
     <td class="TD_LIST" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td> 
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td>
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=warehouseName==null?"":warehouseName%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=snumber==null?"":snumber%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td>
     <td class="TD_LIST" align="center"><%=finishtime==null?"":finishtime%></td>
     <td class="TD_LIST2" align="center"><%=boundstockid==null?"":boundstockid%></td>  
     <td class="TD_LIST" align="center"><%=boundrequeststockid==null?"":boundrequeststockid%></td> 
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="18" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>