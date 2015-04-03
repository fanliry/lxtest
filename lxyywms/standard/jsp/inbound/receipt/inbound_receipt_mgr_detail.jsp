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
		function showDetail(obj,instockdetailid,instockid,productid,productname,packid,packname,eunit,invoicenum,weight,netweight,volume,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12, dhnum, shnum)
		{
			parent.lotreset();	  //重置
			if(obj.cells.item(0).getElementsByTagName("input")[0].checked == false)
			{
					obj.style.backgroundColor = "";	
					
					parent.form1.reset(); //重置
					
			}else{
						obj.style.backgroundColor = "#99CCFF";
						parent.document.getElementById("invoicedetailid").value = instockdetailid;//单据详细ID
			    		parent.document.getElementById("invoiceid").value = instockid;//单据ID
						parent.document.getElementById("product_name").value = productname;//产品名
			        	parent.document.getElementById("packname").value = packname;//包装名
			        	//剩余数量 = 到货数量 - 收货数量 - 拒收数量 - 冻结数量
			        	parent.document.getElementById("realnum").value = invoicenum;		//剩余数量
						parent.document.getElementById("realweight").value = weight;		//剩余数量
						parent.document.getElementById("realnetweight").value = netweight;	//剩余数量
						parent.document.getElementById("realvolume").value = volume;		//剩余数量
						
						parent.document.getElementById("renum").value = invoicenum;		//剩余数量
						parent.document.getElementById("reweight").value = weight;		//剩余数量
						parent.document.getElementById("renetweight").value = netweight;//剩余数量
						parent.document.getElementById("revolume").value = volume;		//剩余数量
						
						parent.document.getElementById("invoicenum").value = dhnum;	//到货数量
						parent.document.getElementById("recnum").value = shnum;		//已收货数量
						
						parent.document.getElementById("rejectednum").value = "0";	//拒收数量
						parent.document.getElementById("holdnum").value = "0";		//冻结数量

						parent.selectinoutUnit(eunit, 'eunit', packid, '1');	//单位
						parent.document.getElementById("lotid").value = lotid;	//批次类型	
						parent.setLotValue(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//批次
							
			}
			
			if(oldobj != null){	
				if(oldobj.cells.item(0).getElementsByTagName("input")[0].checked == false)
				{
					oldobj.style.backgroundColor = "";	
	
				}
			}
			oldobj = obj;
			
			//查询收货作业
			showjob(instockdetailid);
		}

	function showjob(detailid)
	{
		parent.detail.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=2&invoicedetailid="+detailid;
	}
	
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("收货成功！");
			}else{
				alert(back_msg);
			}
		}
	}
	
</script>

</head>

<body onload="javascript:OnLoad();">

	<table width="200%" height="143" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr>
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">单位</div></td>
     
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">到货个数</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">到货毛重</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">到货净重</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">收货个数</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">收货毛重</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">收货净重</div></td>
     
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">拒收数量</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">拒收编码</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">拒收原因</div></td>
     
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">冻结数量</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">冻结编码</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">冻结原因</div></td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE3" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">收货库区</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundReceiptDetail inDetail = null;//收货单据详细 
		String instockdetailid;	//收货单详细ID
      	String instockid;		//收货单据编号
      	String productid;		//品名规格
      	String packname;		//包装
      	String packid;			//包装
      	String eunit;			//单位名
      	String eunitid;			//单位
      	double invoicenum;		//最小单位数量
	  	double weight;			//重量
      	double netweight;		//净重
      	double volume;			//体积
      
      	double recnum;			//最小单位数量（收货）
      	double reweight;		//收货重量
      	double renetweight;		//收货净重
      	double revolume;			//体积
      	String reclocation;		//收货库位
      	
      	String rejectcode;          //拒收原因代码 
     	String rejectreason;    	//拒收原因描述 
     	double rejectednum;			//拒收数量
     	String holdcode;            //冻结原因代码 
     	String holdreason;          //冻结原因描述 
     	double holdnum;				//冻结数量


      	String linestatus;      //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
      	
        String m_strStatusName;           // 状态名
        String m_strProductName;          // 产品
        String lotid;            	//批次类型ID 
        String lotatt1;  			//批次属性1
     	String lotatt2;  			// 批次属性2
     	String lotatt3;  			// 批次属性3
     	String lotatt4;  			// 批次属性4
     	String lotatt5;  			// 批次属性5
     	String lotatt6;  			// 批次属性6
     	String lotatt7;  			// 批次属性7
     	String lotatt8;  			// 批次属性8
     	String lotatt9;  			// 批次属性9
     	String lotatt10;  			// 批次属性10
     	String lotatt11;  			// 批次属性11
     	String lotatt12;  			// 批次属性12
      	
      	
		for(int i = 0; i < ls.size(); i++)
		{
			inDetail = (InboundReceiptDetail)ls.get(i);
			 instockdetailid = inDetail.getReincoicedetailid();	//收货单详细ID
	      	 instockid = inDetail.getReinvoiceid();			//收货单据编号
	      	 productid = inDetail.getProductid();			//品名规格
	      	 packname = inDetail.getM_strPackName();		//包装
	      	 packid = inDetail.getPackid();					//包装
	      	 eunit = inDetail.getM_strUnitName();			//单位
	      	 eunitid = inDetail.getEunit();					//单位
	      	 
	      	 invoicenum = inDetail.getInvoicenum();			//最小单位数量
		  	 weight = inDetail.getWeight();					//重量
	      	 netweight = inDetail.getNetweight();			//净重
	      	 volume = inDetail.getVolume();					//体积
	      	 
	      	 recnum = inDetail.getRecnum();				//最小单位数量（收货）
	      	 reweight = inDetail.getReweight();			//收货重量
	      	 renetweight = inDetail.getRenetweight();	//收货净重
	      	 revolume = inDetail.getRevolume();			//收货体积
	      	 reclocation = inDetail.getReclocation();	//收货库位
	      	 
	      	 linestatus = inDetail.getLinestatus();     //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
	      	 
         	 m_strStatusName = inDetail.getM_strStatusName();            // 状态名
         	 m_strProductName = inDetail.getM_strProductName();          // 产品
         	 
         	 rejectcode = inDetail.getRejectcode();         //拒收原因代码 
     	 	 rejectreason = inDetail.getRejectreason();    	//拒收原因描述 
     	 	 rejectednum = inDetail.getRejectednum();		//拒收数量
     	 	 holdcode = inDetail.getHoldcode();             //冻结原因代码 
     	 	 holdreason = inDetail.getHoldreason();         //冻结原因描述 
     	     holdnum = inDetail.getHoldnum();				//冻结数量
         	 
         	 lotid = inDetail.getLotid();            	// 批次类型ID
         	 lotatt1 = inDetail.getLotatt1();  			// 批次属性1
     	 	 lotatt2 = inDetail.getLotatt2();  			// 批次属性2
     	 	 lotatt3 = inDetail.getLotatt3();			// 批次属性3
     	 	 lotatt4 = inDetail.getLotatt4();  			// 批次属性4
     	 	 lotatt5 = inDetail.getLotatt5();  			// 批次属性5
     	 	 lotatt6 = inDetail.getLotatt6();  			// 批次属性6
     	 	 lotatt7 = inDetail.getLotatt7();  			// 批次属性7
     	 	 lotatt8 = inDetail.getLotatt8();  			// 批次属性8
     	 	 lotatt9 = inDetail.getLotatt9();  			// 批次属性9
     	 	 lotatt10 = inDetail.getLotatt10();  		// 批次属性10
     	 	 lotatt11 = inDetail.getLotatt11();  		// 批次属性11
     	 	 lotatt12 = inDetail.getLotatt12();  		// 批次属性12
	      	 
	      	 //还剩收货数量
	      	 double realnum = invoicenum - recnum - rejectednum - holdnum;
	      	 //还剩收货毛重
	      	 double realweight = weight - reweight;
	      	 //还剩收货净重
	      	 double realnetweight = netweight - renetweight;
	      	 //还剩收货体积
	      	 double realvolume = volume - revolume;
	      	 
 %>
	         <tr class="list_normal_tr" onmouseover="this.bgColor='#CCFF00'" onmouseout="bgColor=''" onclick="showDetail(this,'<%=instockdetailid%>','<%=instockid%>','<%=productid%>','<%=m_strProductName%>','<%=packid%>','<%=packname%>','<%=eunitid%>','<%=realnum%>','<%=realweight%>','<%=realnetweight%>','<%=realvolume%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>','<%=invoicenum%>','<%=recnum%>')">
		     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=packname%></td>
		     <td class="TD_LIST" align="center"><%=eunit%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(recnum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(reweight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(renetweight)%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(rejectednum)%></td>
		     <td class="TD_LIST" align="center"><%=rejectcode == null ? "" : rejectcode%></td>
		     <td class="TD_LIST" align="center"><%=rejectreason == null ? "" : rejectreason%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(holdnum)%></td>
		     <td class="TD_LIST" align="center"><%=holdcode == null ? "" : holdcode%></td>
		     <td class="TD_LIST" align="center"><%=holdreason == null ? "" : holdreason%></td>
		     
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
		     <td class="TD_LIST2" align="center"><%=reclocation%></td>
		   </tr>	       	 
<%
	      	 
		}
		
	}else
	{
		session.removeAttribute("inboundPage1");
	}
%>

   <tr>
     <td class="TD_last_LIST" height="100%" colspan="<%=18 + iLine%>" valign="top">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
     </td>
   </tr>
  
 </table>
 <!-- ======== 分页标签 ======== -->
	<%@ include file="include/page1.jsp" %>
	
	
	<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
