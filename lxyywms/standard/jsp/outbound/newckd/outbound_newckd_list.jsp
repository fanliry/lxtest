<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script>
var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	function Change(obj)
	{
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
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
	
	//查询详细
	function queryDetail(id) {
		parent.document.getElementById("dckdid").innerHTML = id;
		parent.detail.location.href = ac+ "outBoundAction&flag=2&invoiceid=" + id;
	}
	
	//获取明细
	var oldobj = null;
    function getDetail(strId, obj)
	{
		obj.style.backgroundColor = "#99CCFF";
		if(oldobj != null){
			if(oldobj.cells.item(0).getElementsByTagName("input")[0].checked == false)
			{
				oldobj.style.backgroundColor = "";	
			}
		}
		oldobj = obj;
		var actionStr = "BMSService?actionCode=outBoundAction&flag=2&invoiceid=" + strId;
		window.parent.tableheight2.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				var invoiceid = "<%=request.getAttribute("InvoicedId")==null?"":request.getAttribute("InvoicedId")%>";
				alert("生成单据[" + invoiceid + "]成功！");
			}else{
				alert(back_msg);
			}
		}
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}

</script>

<body  id="table_body" onload="javascript:OnLoad();">
 <table width="100%"  id="theTable" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">单据号</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">销售单号</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">单据状态</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">单据类型</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">车牌</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">车位</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">客户</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">部门</div></td>
   </tr>
<%
	
	if(ls != null && ls.size() > 0) 
	{
		OutboundInvoiceHeader outBound = null; 
		String outstockid;      //出库单据编号
        String outstatus;       //出库单状态 1:开单；2：审核 3:发货确认 4:作废
        String outtype;         //出库类型
		String vehicleno;		//车牌
      	String vehiclepos;		//车位
      	String sendplatform;	//发货月台
        String uploadflag;     	//上传标志0-已上传（默认）1-未上传
        String customername;	//收货人
        String departName;		//部门
        String address;		//收货人地址
        String saleno="";
		for(int i = 0; i < ls.size(); i++)
		{
			outBound = (OutboundInvoiceHeader)ls.get(i);
			outstockid = outBound.getOutstockid();     	//出库单据编号
	        outstatus = outBound.getM_strOutStatusName();       	//出库单状态 1:开单；2：审核 3:发货确认 4:作废
	        outtype = outBound.getM_strOutTypeName();         	//出库单据类型
			vehicleno = outBound.getVehicleno();		//车牌
      	 	vehiclepos = outBound.getVehiclepos();		//车位
      	 	sendplatform = outBound.getSendplatform();	//发货月台
	        uploadflag = outBound.getIsupload();   		//上传标志0-已上传（默认）1-未上传
			customername = outBound.getCustomername();	//客户
			departName = outBound.getDepartName();      //部门
			address = outBound.getAddress();//收货人地址
			
			saleno = outBound.getSaleno();
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center"><input onClick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=outstockid%>" alt="<%=customername == null ? "" : customername%>" ><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=outstockid%></td>
     <td class="TD_LIST" align="center"><%=saleno%></td>
     <td class="TD_LIST" align="center"><%=outstatus%></td>
     <td class="TD_LIST" align="center"><%=outtype%></td>
     <td class="TD_LIST" align="center"><%=vehicleno == null ? "" : vehicleno%></td>
     <td class="TD_LIST" align="center"><%=vehiclepos == null ? "" : vehiclepos%></td>   
     <td class="TD_LIST" align="center"><%=customername == null ? "" : customername%></td>
     <td class="TD_LIST2" align="center"><%=departName == null ? "" : departName%></td>
   </tr>

  
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
	<tr>
      <td height="100%" colspan="9" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>

</body>
</html>
