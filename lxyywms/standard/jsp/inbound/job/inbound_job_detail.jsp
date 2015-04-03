<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="java.lang.reflect.Field" %>
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
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</SCRIPT>
</head>

<body  onload="javascript:OnLoad();">
 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div>
     </td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">进厂编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">原厂编号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">物品状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">供应商</div></td>
	 
   </tr>
   </thead>  
   <tbody> 
<%
	if(ls != null && ls.size()>0){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InoutJobdetail detail = null;
		String productCode = null;
		String m_strProductName = null;	 //品名
		String lotinfo = null;	         //进厂编号
		String lotinfo2 = null;	         //原厂编号
		String supplier = null;	         //供应商
		String printdate = null;	     //生产日期
		String m_strUnitName = null;	//单位
		double jobnum = 0;			    //数量
		String productStatus = null;//物品状态
		
		
		for(int i = 0; i < ls.size(); i++){
			detail = (InoutJobdetail)ls.get(i);
			productCode = detail.getProductcode();
			m_strProductName = detail.getM_strProductName();
			lotinfo = detail.getLotinfo();
			lotinfo2 = detail.getLotinfo2();
			printdate = detail.getPrintdate();
			m_strUnitName = detail.getM_strUnitName();
			jobnum = detail.getJobnum();
			productStatus = detail.getProductStatusName();
			supplier = detail.getSupplierName();
			
%>
   	 <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
     <td class="TD_LIST" align="center"><%=lotinfo2==null?"":lotinfo2%></td>
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=productStatus==null?"":productStatus%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td> 
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=supplier==null?"":supplier%></td> 
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="6" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>