<%@page import="com.wms3.bms.standard.entity.outerInterface.SaleForm"%>
<%@page import="com.wms3.bms.standard.entity.outerInterface.SaleFormDetail"%>
<%@page import="java.util.List"%>
<%@ page import="java.lang.reflect.Field" %>
<%@ page contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>出库订单信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />


  </head>
  <script type="text/javascript">
  var num=0; //计数器
  var flag;//Y表示是消耗性料件 
  //设置多选框是否选中
	function setSel(obj,i){	
		if(obj.value == undefined){		
			var check_ids = document.getElementsByName("check_id");
			var sotypes = document.getElementsByName("sotype");
			if(check_ids[i].checked){
				check_ids[i].checked = false;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
				num--;
			}else{
				
				check_ids[i].checked = true;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
/* 				if(sotypes[i].value=="2"){
				if(num==0){
				  if("Y"==check_ids[i].alt){
					flag="Y";
				
				  }else{
					flag="N";
					
				  }
				}else{
					 if(flag!=check_ids[i].alt){
						 alert("此明细类型不同！");
						 check_ids[i].checked = false;
						 check_ids[i].parentNode.parentNode.style.backgroundColor = "";
						 num--;
					 }
				}} */
				num++;
			}

		}else{
			if(obj.checked){
				obj.checked = false;
			}else{
				obj.checked = true;
			}
		}
	}
  </script>
  <body>
	<table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
		<tr>
			<td class="TD_LIST_TITLE" width="50"><div class="list_title">行号</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">状态 </div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">产品</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">品号</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">规格</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">已使用数量</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">开单数量</div></td>
		</tr>
<%
	List<SaleFormDetail> ls = (List<SaleFormDetail>)request.getAttribute("exResultList");
		SaleForm soHeader = (SaleForm)request.getAttribute("header");
			
			String m_strSaleFormId = "";	//销售订单号
			String m_strSaleFormTypeName = "";	//单据类型
			String m_strCustomerid = "";		//客户
			String m_strCustomername = "";		//客户名称
			String m_strDownTime = "";		//单据下载时间
			String m_strIsOut = "";			//是否出过货
			String m_strIsFinish = "";		//是否完成出货
			String m_strIsInvalid = "";		//是否无效
		
		if(ls != null && ls.size() > 0 && soHeader != null) {

			m_strSaleFormId = soHeader.getM_strSaleFormId();
			m_strSaleFormTypeName = soHeader.getM_strSaleFormTypeName();
			m_strCustomerid = soHeader.getM_strCustomerId();
			m_strCustomername = soHeader.getM_strCustomerName();
			m_strDownTime = soHeader.getM_strDownTime();
			m_strIsOut = soHeader.getM_strIsOut();
			m_strIsFinish = soHeader.getM_strIsFinish();
			m_strIsInvalid = soHeader.getM_strIsInvalid();
			
			//--------------------------------//
			
			SaleFormDetail soDetail = null;
			String m_strSaleFormDetailId;
			//String m_strSaleFormId; 		//销售单id
			String m_strPackageId;  		//物品代码
			String m_strPrintDate;  		//打印日期
			String m_strBatch;  			//批号
			String m_strProductStatus;  	//状态
			double m_iSaleNum;					//数量
			double m_iOutNum;					//发出数量
			double nouseponum;
			
			String m_strPackageName;		//产品名称
			String m_strUnit;				//单位
			String m_strProductStatusName;	//物品状态
			String spec;					//产品规格
			
			String spaceid = "0000"; //(占位)
			
			String strValue="";	//订单详细id&外来单号&客户id&客户名&货位&品名&单位
			
			for(int i=0;i<ls.size();i++){
				soDetail = (SaleFormDetail)ls.get(i);
			    
				m_strSaleFormDetailId = soDetail.getM_strSaleFormDetailId();
				//m_strSaleFormId = soDetail.getM_strSaleFormId(); 					//销售单id
				m_strPackageId = soDetail.getM_strPackageId();  					//物品代码
				m_strPrintDate = soDetail.getM_strPrintDate();  					//打印日期
				m_strBatch = soDetail.getM_strBatch();  							//批号
				m_strProductStatus = soDetail.getM_strProductStatus();  			//状态
				
				m_strPackageName = soDetail.getM_strPackageName();					//产品名称
				m_strUnit = soDetail.getM_strUnit();								//单位
				m_strProductStatusName = soDetail.getM_strProductStatusName();		//物品状态
				spec = soDetail.getSpec();
				
			    m_iSaleNum = soDetail.getM_iSaleNum();
			    m_iOutNum = soDetail.getM_iOutNum();
				nouseponum = m_iSaleNum - m_iOutNum;
				//订单详细id&外来单号&客户id&客户名&货位&品名&单位
				strValue =m_strSaleFormDetailId+"&"+m_strSaleFormId+"&"+m_strCustomerid+"&"+m_strCustomername+"&"+spaceid+"&"+m_strPackageName+"&"+m_strPackageId+"&"+m_strUnit+"&"+spec;
				
%>
		<tr   onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(this,<%=i%>)">
			<td class="TD_LIST" align="center"><input  type="checkbox"  onclick="setSel(this,<%=i%>)" name="check_id" class="input_checkbox"  value="<%=strValue%>" ><%=i+1%></td>
			<td class="TD_LIST"><%=m_strProductStatusName %></td>
			<td class="TD_LIST"><%=m_strPackageName %></td>
			<td class="TD_LIST"><%=m_strPackageId %></td>
			<td class="TD_LIST"><%=spec %></td>
			<td class="TD_LIST"><%=m_strUnit %></td>
			<td class="TD_LIST"><%=m_iSaleNum %></td>
			<td class="TD_LIST"><input type="hidden" name="nouseNum" value="<%=m_iOutNum %>"/><%=m_iOutNum %></td>
			<td class="TD_LIST"><input type="text" name="checkNum" value="<%=nouseponum %>" ></input></td>
	  </tr>			
<%			}
		}else{
			session.removeAttribute("commpaging");
			}
%>
   <tr>
     <td height="100%" colspan="<%=8 %>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
	
		
	</table>
</body>
</html>
