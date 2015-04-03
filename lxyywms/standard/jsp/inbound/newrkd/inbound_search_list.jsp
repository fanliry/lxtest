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
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
			}
		}
		
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
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" align="center" width="50px"><div class="list_title">选择</div></td>
     <td class="TD_LIST_TITLE" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">逻辑库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业类型</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进场编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">状态</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">总数量</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">完成数量</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">未完成数量</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">未开单数量</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	double num = 0;
	if(ls != null && ls.size()>0){
		
		Object[] obj = null;
     	
     	String jobtype = "";    //作业类型 （回流的作业类型为 入库类型的hl值）
     	String jobtypeid = "";
     	String productid = "";//产品编码
     	String m_strProductName = "";	 //品名
		String lotinfo = "";	         //具体批号
		String printdate = "";	     //生产日期
		String m_strUnitName = "";	//单位
		String warehouseName = "";  	//仓库
		String whArea = "";
		String Virtualwhid = "";//逻辑库区
		String VirtualwhName = "";
		
		String productStatusName = "";
		String productStatus = "";
		
		String warehouseid = "";
		String whAreaid = "";
     	
		double jobnum = 0;			    //总数量
		double finishnum = 0;
		double nofinishnum = 0;
		double noinvoicenum = 0;
     	
     	String key="";
     	String alt="";
     	
     	String oldproductid = null;  //上一个品名
     	String oldjobtypeid = null;  //上一个作业类型
     	
     	double unitJobum = 0;         //小计总数
     	double unitFinishnum = 0;     //小计完成数
     	double unitNofinishnum = 0;   //小计未完成数
     	double unitNoinvoicenum = 0;    //小计未开单数
		
     	double addJobum = 0;         //合计总数
     	double addFinishnum = 0;     //合计完成数
     	double addNofinishnum = 0;   //合计未完成数
     	double addNoinvoicenum = 0;  //合计未开单数
     	
     	double allJobum = 0;         //总计总数
     	double allFinishnum = 0;     //总计完成数
     	double allNofinishnum = 0;   //总计未完成数
     	double allNoinvoicenum = 0;    //总计未开单数
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			jobtype = obj[1] == null ? "" : obj[1].toString();    //作业类型 （回流的作业类型为 入库类型的hl值）
	     	jobtypeid = obj[0] == null ? "" : obj[0].toString();
	     	productid = obj[2] == null ? "" : obj[2].toString();//产品编码
	     	m_strProductName = obj[3] == null ? "" : obj[3].toString();	 //品名
			printdate = obj[4] == null ? "" : obj[4].toString();	     //生产日期
			m_strUnitName = obj[6] == null ? "" : obj[6].toString();	//单位
			warehouseName = obj[8] == null ? "" : obj[8].toString();  	//仓库
			whArea = obj[10] == null ? "" : obj[10].toString();
			
			warehouseid = obj[7] == null ? "" : obj[7].toString();  	//仓库id
			whAreaid = obj[9] == null ? "" : obj[9].toString();
	     	
			jobnum = Double.parseDouble(obj[11] == null ? "0" : obj[11].toString());			    //总数量
			finishnum = Double.parseDouble(obj[12] == null ? "0" : obj[12].toString());
			nofinishnum = jobnum - finishnum;
			noinvoicenum = Double.parseDouble(obj[13] == null ? "0" : obj[13].toString());
			lotinfo = obj[14] == null ? "" : obj[14].toString(); //进场编号
			Virtualwhid = obj[15] == null ? "" : obj[15].toString();  //逻辑库区
			VirtualwhName = obj[16] == null ? "" : obj[16].toString();  //逻辑库区名
			productStatus = obj[17] == null ? "" : obj[17].toString();  
			productStatusName = obj[18] == null ? "" : obj[18].toString();  
			key = warehouseid + "|" + whAreaid + "|" + Virtualwhid+"|"+jobtypeid + "|" + productid + "|" + printdate+ "|" + lotinfo+ "|";
			alt = jobtypeid;
			
			if(oldproductid == null)
			{
				oldproductid = productid;
			}else if(!oldproductid.equals(productid))
			{
%>
			   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">小计</td>
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
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNoinvoicenum%></td>
			   </tr>
<%
				
		     	unitJobum = 0;         //小计总数
		     	unitFinishnum = 0;     //小计完成数
		     	unitNofinishnum = 0;   //小计未完成数
		     	unitNoinvoicenum = 0;    //小计未开单数
		     	
			}
			
			if(oldjobtypeid == null)
			{
				oldjobtypeid = jobtypeid;
			}else if(!oldproductid.equals(productid) && oldjobtypeid.equals(jobtypeid)){
			  	oldproductid = productid;
			}else if(!oldproductid.equals(productid) && !oldjobtypeid.equals(jobtypeid))
			{
%>
			
			   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">合计</td>
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
			     <td class="TD_LIST" align="center" style="color: red;"><%=addJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNoinvoicenum%></td>
			   </tr>
<%
		     	addJobum = 0;         //合计总数
		     	addFinishnum = 0;     //合计完成数
		     	addNofinishnum = 0;   //合计未完成数
		     	addNoinvoicenum = 0;  //合计未开单数
		     	oldjobtypeid = jobtypeid;
		     	oldproductid = productid;
			}
			else if(oldproductid.equals(productid) && !oldjobtypeid.equals(jobtypeid))
			{
%>
				<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">小计</td>
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
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNoinvoicenum%></td>
			   </tr>
			   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">合计</td>
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
			     <td class="TD_LIST" align="center" style="color: red;"><%=addJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNoinvoicenum%></td>
			   </tr>
<%
		     	unitJobum = 0;         //小计总数
		     	unitFinishnum = 0;     //小计完成数
		     	unitNofinishnum = 0;   //小计未完成数
		     	unitNoinvoicenum = 0;    //小计未开单数
		     	oldproductid = productid;
		     	
		     	addJobum = 0;         //合计总数
		     	addFinishnum = 0;     //合计完成数
		     	addNofinishnum = 0;   //合计未完成数
		     	addNoinvoicenum = 0;  //合计未开单数
		     	oldjobtypeid = jobtypeid;
			}
			
			unitJobum += jobnum;         //小计总数
	     	unitFinishnum += finishnum;     //小计完成数
	     	unitNofinishnum += nofinishnum;   //小计未完成数
	     	unitNoinvoicenum += noinvoicenum;    //小计未开单数
	     	
	     	addJobum += jobnum;         //合计总数
	     	addFinishnum += finishnum;     //合计完成数
	     	addNofinishnum += nofinishnum;   //合计未完成数
	     	addNoinvoicenum += noinvoicenum;  //合计未开单数
	     	
	     	allJobum += jobnum;         //总计总数
	     	allFinishnum += finishnum;     //总计完成数
	     	allNofinishnum += nofinishnum;   //总计未完成数
	     	allNoinvoicenum += noinvoicenum;    //总计未开单数
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
   	 <td class="TD_LIST1" align="center" width="50px"><input type="checkbox" name="check_id" alt="<%=alt %>" value="<%=key %>" class="input_radio" onClick=""></td>
     <td class="TD_LIST" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseName==null?"":warehouseName%></td>
     <td class="TD_LIST" align="center"><%=whArea==null?"":whArea%></td>
     <td class="TD_LIST" align="center"><%=VirtualwhName==null?"":VirtualwhName%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=productid==null?"":productid%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td> 
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td>
     <td class="TD_LIST" align="center"><%=productStatusName==null?"":productStatusName%></td>
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=finishnum%></td>
     <td class="TD_LIST" align="center"><%=nofinishnum%></td>
     <td class="TD_LIST" align="center"><%=noinvoicenum%></td>
   </tr>
<%
		}
%>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
	   	 <td class="TD_LIST1" align="center" width="50px"></td>
	     <td class="TD_LIST" align="center" width="50px" style="color: red;">小计</td>
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
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitJobum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitFinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitNofinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitNoinvoicenum%></td>
	   </tr>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
	   	 <td class="TD_LIST1" align="center" width="50px"></td>
	     <td class="TD_LIST" align="center" width="50px" style="color: red;">合计</td>
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
	     <td class="TD_LIST" align="center" style="color: red;"><%=addJobum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addFinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addNofinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addNoinvoicenum%></td>
	   </tr>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
	   	 <td class="TD_LIST1" align="center" width="50px"></td>
	     <td class="TD_LIST" align="center" width="50px" style="color: red;">总计</td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td> 
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"><br></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allJobum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allFinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allNofinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allNoinvoicenum%></td>
	   </tr>
<%
		num = allNofinishnum;
	}
%>  
   <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'>
<input type="hidden" name="keys" value="">
<input type="hidden" name="condition" value="">
</FORM>
<input name="num" type="hidden" value="<%=num%>">
</body>
</html>