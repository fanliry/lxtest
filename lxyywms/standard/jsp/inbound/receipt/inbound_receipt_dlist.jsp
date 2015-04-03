<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptDetail" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newshd");//新建收货单->显示收货单详细	
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
</script>

</head>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="200%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">到货数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">到货毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">到货净重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货净重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">拒收数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">拒收原因</div></td>   
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">冻结数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">冻结原因</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货库位</div></td>
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
	List ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundReceiptDetail inDetail = null;//收货单据详细  
		String instockdetailid;	//入库收货单详细ID
      	String instockid;		//入库收货单据编号
      	String productid;		//品名规格
      	double invoicenum;		//最小单位数量
	  	double weight;			//重量
      	double netweight;		//净重
      	
      	
      
      	String packid;              /* 包装 */
        String eunit;               /* 单位 */
        double rejectednum;         /* 拒收数量 */
        double holdnum;             /* 冻结数量 */
        String m_strRejectCodeText; //拒收编码显示内容
        String m_strHoldCodeText;   //冻结编码显示内容
      	
      	double recnum;			//最小单位数量（收货）
      	double reweight;		//收货重量
      	double renetweight;		//收货净重
      	String reclocation;		//收货库位



      	String linestatus;      //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
      	
        String m_strProviderName;         // 供应商
        String m_strStatusName;           // 状态名
        String m_strProductName;          // 产品
      	
      	String m_strPackName;       //包装名称
        String m_strUnitName;       //单位名称
      	
		for(int i = 0; i < ls.size(); i++)
		{
			inDetail = (InboundReceiptDetail)ls.get(i);
			 instockdetailid = inDetail.getReincoicedetailid();	//入库收货单详细ID
	      	 instockid = inDetail.getReinvoiceid();			//入库收货单据编号
	      	 productid = inDetail.getProductid();			//品名规格
	      	 invoicenum = inDetail.getInvoicenum();		//最小单位数量
		  	 weight = inDetail.getWeight();			//重量
	      	 netweight = inDetail.getNetweight();			//净重
	      	 
	      	 packid = inDetail.getPackid();              /* 包装 */
         	 eunit  = inDetail.getEunit();               /* 单位 */
         	 rejectednum = inDetail.getRejectednum();         /* 拒收数量 */
         	 holdnum = inDetail.getHoldnum();             /* 冻结数量 */
         	 m_strRejectCodeText = inDetail.getM_strRejectCodeText(); //拒收编码显示内容
         	 m_strHoldCodeText = inDetail.getM_strHoldCodeText();   //冻结编码显示内容
	      	 

	      	 recnum = inDetail.getRecnum();			//最小单位数量（收货）
	      	 reweight = inDetail.getReweight();			//收货重量
	      	 renetweight = inDetail.getRenetweight();		//收货净重
	      	 reclocation = inDetail.getReclocation();		//收货库位
	   
	      	 linestatus = inDetail.getLinestatus();         //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
	      	 
         	m_strStatusName = inDetail.getM_strStatusName();            // 状态名
         	m_strProductName = inDetail.getM_strProductName();          // 产品
         	
         	m_strPackName = inDetail.getM_strPackName();       //包装名称
         	m_strUnitName = inDetail.getM_strUnitName();       //单位名称
 %>
	         <tr class="list_normal_tr" onmouseover="this.style.backgroundColor='#CCFF00'" onmouseout="this.style.backgroundColor=''" onclick="Change(this)">
		     <td class="TD_LIST" align="center"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(recnum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(reweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(renetweight)%></td>
			 <td class="TD_LIST" align="center"><%=nbf.format(rejectednum)%></td>
			 <td class="TD_LIST" align="center"><%=m_strRejectCodeText == null ? "" : m_strRejectCodeText%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(holdnum)%></td>
		     <td class="TD_LIST" align="center"><%=m_strHoldCodeText == null ? "" : m_strHoldCodeText%></td>
		     <td class="TD_LIST" align="center"><%=reclocation == null ? "" : reclocation%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = inDetail.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(inDetail)%></td>
<%
		}
	}
%>		     
		   </tr>	       	 
<%
	      	 
		}
		
	}
%>

   <tr>
      <td height="100%" colspan="<%=16 + iLine%>" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
  
 </table>
 </div>
</body>
</html>
