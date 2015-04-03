<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("shtrans");//新建收货单->显示收货单详细	
		}
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
	var oldobj = null;
	function Change(obj)
	{
		obj.style.backgroundColor = "#33FF33";
		if(oldobj != null){
			oldobj.style.backgroundColor = "";	
		}
		oldobj = obj;
	}
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#33FF33";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert(back_msg);
			}else{
				alert(back_msg);
			}
		}
	}
	
</script>

</head>

<body onload="javascript:OnLoad();">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="200%" height="260" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">跟踪号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货净重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">上架数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">上架毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">上架净重</div></td>

<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundReceiptTransaction receiptTrans = null;//收货记录   
		String transid;			//收货记录ID
		String inreceiptid;		//收货单ID
      	String productid;		//品名规格
      	double num;				//数量
	  	double weight;			//重量
      	double netweight;		//净重
      	double ptnum;			//上架数量
	  	double ptweight;		//上架重量
      	double ptnetweight;		//上架净重
      	
        String strStatusName = "";	// 状态名
        String m_strProductName;    // 产品
      	
      	String m_strPackName;       //包装名称
        String m_strUnitName;       //单位名称
      	
		for(int i = 0; i < ls.size(); i++)
		{
			receiptTrans = (InboundReceiptTransaction)ls.get(i);
			transid = receiptTrans.getTransreceiptid();		//记录ID
			inreceiptid = receiptTrans.getReinvoiceid();	//收货单号
	      	productid = receiptTrans.getProductid();		//品名规格
	      	num = receiptTrans.getRecnum();					//数量
		  	weight = receiptTrans.getReweight();			//重量
	      	netweight = receiptTrans.getRenetweight();		//净重
	      	
	      	ptnum = receiptTrans.getPucnum();			//上架数量
	  	 	ptweight = receiptTrans.getPuweight();		//上架重量
      	 	ptnetweight = receiptTrans.getPunetweight();//上架净重
	      	 
         	m_strProductName = receiptTrans.getM_strProductName(); // 产品
         	
         	m_strPackName = receiptTrans.getM_strPackName();       //包装名称
         	m_strUnitName = receiptTrans.getM_strUnitName();       //单位名称
         	
         	strStatusName = receiptTrans.getStrStatusName();	   //状态名称
			
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><%=i+1%><input onClick="Change(this)" type="radio" name="check_id" class="input_checkbox" value="<%=transid%>"></td>
		     <td class="TD_LIST" align="center"><%=strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=transid%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(ptnum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(ptweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(ptnetweight)%></td>
		     
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = receiptTrans.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(receiptTrans)%></td>
<%
		}
	}
%>		     
		   </tr>	       	 
<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("inboundPageCancel");
	}
%>

   <tr>
      <td height="100%" colspan="<%=12 + iLine%>" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
  
 </table>
 <!-- ======== 分页标签 ======== -->
	<%@ include file="include/pagecancel.jsp"  %>
</div>
 <FORM action="" method='post' name='formx1'  style='display:none'></FORM>
 
</body>
</html>
