<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	HashMap<String, List> hsLot = null;		//所有要显示的批次
	List<BaseLotSet> lsLot = null;  		//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newin");	//新建收货单->显示收货单详细	
		}
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	function OnLoad(){
	
		parent.RemoveLoading();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("生成单据成功！");
			}
			else{
				alert(back_msg);
			}
			
			parent.UnLockButton();
		}
	}
	
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width: 100%; height: 100%; overflow-x:scroll;overflow-y:auto;position:absolute;">
 <table width="120%" height="100%" border="0" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr style="position:relative;top:expression(this.offsetParent.scrollTop);">
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">选择</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业类型</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货主</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">来源单据号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%> 
     <td class="TD_LIST_TITLE"><div class="list_title">总毛重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">总净重</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">总数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">完成数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">未完成数量</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">未开单数量</div></td>
    </tr> 
<%
	int iNum = 0;
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();     
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		Object[] obj = null;
		
		String productid = ""; 			//品名
		String productName = ""; 		//品名
		String jobtype = "";			//作业类型
		String jobtypeName = "";		//作业类型
		String ownerid = ""; 			//货主
		String ownerName = ""; 			//货主
		String sinvoiceid = ""; 		//来源单据号
		String packid = "";      		//包装ID
 		String punit = "";          	//单位
 		String lotid = "";        		//批次类型ID
 		String invoicetype = "";		//作业来源
 	
		Double iCountNum = 0.0;	 		//总数量
		Double iFinishNum = 0.0;		//完成数量
		Double iNoFinishNum = 0.0;		//未完成数量
		Double iNoVoiceNum = 0.0;		//未开单数据
		
		Double fGrossWeight = 0.0;		//毛重
		Double fNetWeight = 0.0;		//净重
		Double fvolume = 0.0;			//体积
		
		String strKey = "";	
		
		//小计合计用
		String tempProduct = null;		//物品
		String tempJobtype = null;		//作业类型	 
		
		int k=0;
		int m1=0,m2=0,m3=0,m4=0,n1=0,n2=0,n3=0,n4=0,a1=0,a2=0,a3=0,a4=0;
		double m5 = 0, m6 = 0,n5 = 0, n6 = 0,a5 = 0, a6 = 0;
		
		for(int i=0; i<ls.size(); i++)
		{
			obj = (Object[])ls.get(i);
			productid = obj[0] == null ? "" : obj[0].toString();	//品名
			productName = obj[1] == null ? "" : obj[1].toString();	//品名
			jobtype = obj[2] == null ? "" : obj[2].toString(); 		//作业类型
			jobtypeName = obj[3] == null ? "" : obj[3].toString(); 	//作业类型
			ownerid = obj[4] == null ? "" : obj[4].toString();		//货主
			ownerName = obj[5] == null ? "" : obj[5].toString();	//货主
			sinvoiceid = obj[6] == null ? "" : obj[6].toString();	//来源单据号
			packid = obj[19] == null ? "" : obj[19].toString();     //包装ID
 			punit = obj[20] == null ? "" : obj[20].toString();      //单位
 			lotid = obj[21] == null ? "" : obj[21].toString();      //批次类型ID
			invoicetype = obj[22] == null ? "" : obj[22].toString();//作业来源
			
			fvolume = (Double)obj[7];		// 体积
			fGrossWeight = (Double)obj[8];	// 毛重
		 	fNetWeight = (Double)obj[9];	// 净重
			iCountNum       = (Double)obj[10];	//总数量
			iFinishNum      = (Double)obj[14];	//完成数量
			iNoFinishNum    = iCountNum-iFinishNum;	//未完成数量
			iNoVoiceNum     = (Double)obj[18];	//未开单数据	
			 
			strKey =  productid + "|" + jobtype + "|" + ownerid + "|" + sinvoiceid + "|" + packid + "|" + punit + "|" + lotid + "|" + invoicetype + "|";
			
			//相同物品小计,相同作业类型合计
			if(tempProduct == null)
			{
				tempProduct = productName;
			}else if(!tempProduct.equals(productName))
			{
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">小计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%>   
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=m4%></td>
   </tr>
<%
				m1 = m2 = m3 = m4 = 0;
				m5 = m6 = 0;
				tempProduct = productName;
	   		}
	   		if(tempJobtype == null)
	   		{
	   			tempJobtype = jobtypeName;
	   		}
	   		else if(tempProduct.equals(productName) && !tempJobtype.equals(jobtypeName))
	   		{
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">合计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%>  
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=n4%></td>
   </tr>
<%
	   			n1 = n2 = n3 = n4 = 0;
	   			n5 = n6 = 0;
	   			tempJobtype = jobtypeName;
	   			
	   		}else if(!tempProduct.equals(productName) && !tempJobtype.equals(jobtypeName))
	   		{
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">小计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%> 
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=m4%></td>
   </tr>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">合计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%> 
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=n4%></td>
   </tr>
<%
	   			m1 = m2 = m3 = m4 = 0;
	   			n1 = n2 = n3 = n4 = 0;
	   			m5 = m6 = 0;
	   			n5 = n6 = 0;
	   			
				tempProduct = productName;
				tempJobtype = jobtypeName;
	   		}
	   		m1 += iCountNum.intValue();
			m2 += iFinishNum.intValue();
			m3 += iCountNum.intValue()-iFinishNum.intValue();
			m4 += iNoVoiceNum.intValue();
			m5 += fGrossWeight;
			m6 += fNetWeight;
			
			
			n1 += iCountNum.intValue();
			n2 += iFinishNum.intValue();
			n3 += iCountNum.intValue()-iFinishNum.intValue();
			n4 += iNoVoiceNum.intValue();
			n5 += fGrossWeight;
			n6 += fNetWeight;
			
			a1 += iCountNum.intValue();
			a2 += iFinishNum.intValue();
			a3 += iCountNum.intValue()-iFinishNum.intValue();
			a4 += iNoVoiceNum.intValue();
			a5 += fGrossWeight;
			a6 += fNetWeight;
%>

   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.searchDetail()">
   	 <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" class="input_checkbox" value="" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobtypeName%></td>
	 <td class="TD_LIST" align="center"><%=ownerName%></td>
	 <td class="TD_LIST" align="center"><%=sinvoiceid%></td>
     <td class="TD_LIST" align="center"><%=productName%></td>
<%
	if(lsLot != null){
		String lotatt =""; 
		for(k = 0; k < iLine; k++){
			lotatt = obj[23+k] == null ? "" : obj[23+k].toString();		//批次属性
			strKey += lotatt + "|";
%>
	 		 <td class="TD_LIST" align="center"><%=lotatt%></td>
<%
		}
		strKey += "end|";
	}
%>
     <td class="TD_LIST" align="center"><%=nbf.format(fGrossWeight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(fNetWeight)%></td>
     <td class="TD_LIST" align="center"><%=iCountNum.intValue()%></td>
     <td class="TD_LIST" align="center"><%=iFinishNum.intValue()%></td>
     <td class="TD_LIST" align="center"><%=iNoFinishNum.intValue()%></td>
     <td class="TD_LIST2" align="center"><%=iNoVoiceNum.intValue()%><input type="hidden" name="detail_key" value="<%=strKey%>"></td>
   </tr>		
<%
		}
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">小计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=m4%></td>
   </tr>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">合计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%> 
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=n4%></td>
   </tr>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">总计</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST" align="center"></td>
<%
		}
	}
%>
     <td align="center" class="TD_LIST" style="color: red;"><%=nbf.format(a5)%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=nbf.format(a6)%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=a1%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=a2%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=a3%></td>
     <td align="center" class="TD_LIST2" style="color: red;"><%=a4%></td>
   </tr>
<%
		iNum = a3;
	}
%>
   <tr>
     <td height="100%" colspan="<%=16 + iLine%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
 <input name="num" type="hidden" value="<%=iNum%>">
 
  <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
