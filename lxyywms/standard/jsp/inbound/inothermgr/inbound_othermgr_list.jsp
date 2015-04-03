<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
  	
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
			
			queryDetail(check_ids[i].value);
		}
	}
	
	//查询详细
	function queryDetail(id){
	    parent.document.getElementById("dckdid").innerHTML = id;
		parent.detail.location.href = ac + "inBoundPoAction&flag=7&poid=" + id;
	}
	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
			}
		}
	}

</script>

<body onLoad="OnLoad()">
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
	    <td width="50" class="TD_LIST_TITLE1"><div class="list_title">行号</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">单据号</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">类型</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">供应商</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">创建时间</div></td>
   </tr>
<%
	List<InboundPoHeader> ls = (List<InboundPoHeader>)request.getAttribute("exResultList");
		if(ls != null && ls.size() > 0) {
			InboundPoHeader poHeader = null;
			String poid;// PO Id
			String potype="";// PO 类型 
			String postatus="";//PO 状态0-开单
			String createtime="";//PO创建时间 时间格式：yyyy-MM-dd hh:mm:ss
			String createdate="";//PO创建日期 日期格式：yyyy-MM-dd 
			String createmanid="";//PO创建人
			String warehouseid="";//仓库编号 
			String customerid="";//客户ID
			String arrivestarttime="";//预期到货时间(from) 时间格式：yyyy-MM-dd hh:mm:ss 
			String arriveendtime="";//预期到货时间(to) 时间格式：yyyy-MM-dd hh:mm:ss
			String shipperid="";//承运人
			String supplierid="";//供应商
			String reserve1= ""; //营运中心
			String SupplierName="";
			for(int i=0;i<ls.size();i++){
				poHeader = ls.get(i);
				poid = poHeader.getPoid();
				potype = poHeader.getPotypeName();
				postatus = poHeader.getPostatus();
				createdate = poHeader.getCreatedate();
				createtime = poHeader.getCreatetime();
				createmanid = poHeader.getCreatemanid();
				warehouseid = poHeader.getWarehouseid();
				customerid = poHeader.getCustomerName();
				if(customerid==null){
				   customerid= "";
				}
				shipperid = poHeader.getShipperid();
				SupplierName = poHeader.getSupplierName();
				supplierid =poHeader.getSupplierid();
				if(supplierid==null){
				   supplierid= "";
				}
				reserve1 = poHeader.getReserve1();
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=poid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=poid%></td>
	 <td class="TD_LIST" align="center"><%=potype == null? "" : potype %></td>
	 <td class="TD_LIST" align="center"><%=SupplierName == null? "" : SupplierName%></td>
	 <td class="TD_LIST" align="center"><%=createdate%></td>
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="5" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>
