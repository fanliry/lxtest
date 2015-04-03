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
	function showDetail(obj,instockdetailid,instockid,productid,productname,packid,packname,eunit,invoicenum,weight,netweight,volume,price,skustatus,skustatus_descr,reclocation,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		if(obj.cells.item(0).getElementsByTagName("input")[0].checked == false)
		{
				obj.style.backgroundColor = "";	
				
				parent.form1.reset(); //重置
				parent.lotreset();	  //重置
		}else{
					obj.style.backgroundColor = "#99CCFF";
					parent.document.getElementById("invoicedetailid").value = instockdetailid;//单据详细ID
		    		parent.document.getElementById("invoiceid").value = instockid;//单据ID
					parent.document.getElementById("productid").value = productid;//产品ID
					parent.document.getElementById("product_name").value = productname;//产品名
		    		parent.document.getElementById("packid").value = packid;	//包装ID
		        	parent.document.getElementById("packname").value = packname;//包装名
		
					parent.document.getElementById("invoicenum").value = parseFloat(invoicenum).toFixed(2);
					parent.document.getElementById("weight").value = parseFloat(weight).toFixed(2);
					parent.document.getElementById("netweight").value = parseFloat(netweight).toFixed(2);
					parent.document.getElementById("volume").value = parseFloat(volume).toFixed(2);
					parent.document.getElementById("price").value = parseFloat(price).toFixed(2);
					
					parent.document.getElementById("skustatus_descr").value = skustatus_descr;//状态描述
					parent.document.getElementById("reclocation").value = reclocation;//收货库区
					parent.selectType(skustatus, 'skustatus', 'skustatus'); //产品状态
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
		

	}
	

	
	
	
	
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
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
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();" >
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">包装</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">到货个数</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">到货毛重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">到货净重</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">收货库区</div></td>
     
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
      	double volume;			//体积
      	double price;			//价格
      	String eunit;			//单位
      	
      	String skustatus;           /* 产品状态代码 */
        String skustatus_descr;     /* 产品状态描述 */

      	double recnum;			//最小单位数量（收货）
      	double reweight;		//收货重量
      	double renetweight;		//收货净重
      	String reclocation;		//收货库位

		String packid;              /* 包装 */
        double rejectednum;         /* 拒收数量 */
        double holdnum;             /* 冻结数量 */
        String m_strRejectCodeText; //拒收编码显示内容
        String m_strHoldCodeText;   //冻结编码显示内容
      	

        String m_strStatusName;     // 状态名
        String m_strProductName;    // 产品
        String m_strPackName;       //包装名称
        String m_strUnitName;       //单位名称
        
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
        

      	String linestatus;      //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
		for(int i = 0; i < ls.size(); i++)
		{
			inDetail = (InboundReceiptDetail)ls.get(i); 
			 instockdetailid = inDetail.getReincoicedetailid();	//入库收货单详细ID
	      	 instockid = inDetail.getReinvoiceid();			//入库收货单据编号
	      	 productid = inDetail.getProductid();			//品名规格
	      	 invoicenum = inDetail.getInvoicenum();		//最小单位数量
		  	 weight = inDetail.getWeight();			//重量
	      	 netweight = inDetail.getNetweight();			//净重
	      	 volume  = inDetail.getVolume();			//体积
      	 	 price = inDetail.getPrice();			//价格 

	      	 eunit = inDetail.getEunit();				//单位
	      	 packid = inDetail.getPackid();              /* 包装 */
         	 rejectednum = inDetail.getRejectednum();         /* 拒收数量 */
         	 holdnum = inDetail.getHoldnum();             /* 冻结数量 */
         	 m_strRejectCodeText = inDetail.getM_strRejectCodeText(); //拒收编码显示内容
         	 m_strHoldCodeText = inDetail.getM_strHoldCodeText();   //冻结编码显示内容

	      	 recnum = inDetail.getRecnum();			//最小单位数量（收货）
	      	 reweight = inDetail.getReweight();			//收货重量
	      	 renetweight = inDetail.getRenetweight();		//收货净重
	      	 reclocation = inDetail.getReclocation();		//收货库位

	      	 linestatus = inDetail.getLinestatus();         //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
	      	 skustatus = inDetail.getSkustatus();           /* 产品状态代码 */
             skustatus_descr = inDetail.getSkustatus_descr();     /* 产品状态描述 */

         	 m_strStatusName = inDetail.getM_strStatusName();            // 状态名
         	 m_strProductName = inDetail.getM_strProductName();          // 产品
         	
         	 m_strPackName = inDetail.getM_strPackName();       //包装名称
         	 m_strUnitName = inDetail.getM_strUnitName();       //单位名称
         	
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
	      	 
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''" onclick="showDetail(this,'<%=instockdetailid%>','<%=instockid%>','<%=productid%>','<%=m_strProductName%>','<%=packid%>','<%=m_strPackName%>','<%=eunit%>','<%=invoicenum%>','<%=weight%>','<%=netweight%>','<%=volume%>','<%=price%>','<%=skustatus%>','<%=skustatus_descr%>','<%=reclocation%>','<%=lotid%>','<%=lotatt1%>','<%=lotatt2%>','<%=lotatt3%>','<%=lotatt4%>','<%=lotatt5%>','<%=lotatt6%>','<%=lotatt7%>','<%=lotatt8%>','<%=lotatt9%>','<%=lotatt10%>','<%=lotatt11%>','<%=lotatt12%>')">
		     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strPackName == null ? "" : m_strPackName%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>

		     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
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
     <td height="100%" colspan="<%=5 + iLine%>"></td>
   </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
