<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>
	function Change(obj)
	{
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
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
		var actionStr = "BMSService?actionCode=receiptAction&flag=4&invoiceid=" + strId;
		window.parent.tableheight2.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	
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

<body  id="table_body" onload="javascript:OnLoad();">
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">单据号</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">单据状态</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">单据类型</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">货主</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">创建人</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">创建时间</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">供应商</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">承运人</div></td>
     <td  class="TD_LIST_TITLE2"><div class="list_title">海关备案号</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		InboundReceiptHeader inBound = null;  
		String instockid;      //入库单据编号
        String instatus;       //单据状态 0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架6-作废
        String intype;         //入库类型
		String ownername;      //货主	
        String createdate;     //单据生成时间
        String createmanid;    //单据生成人员编号
        String suppliername ;   //供应商信息
        String shippername;     //承运人信息
        String customsno;       //海关备案号
        
        String m_strStatusName;          // 状态名
        String m_strTypeName;            // 类型名
        String createman;                // 单据生成人员
        String warehouseid;				 //仓库ID

		for(int i = 0; i < ls.size(); i++)
		{
			inBound = (InboundReceiptHeader)ls.get(i);
			instockid = inBound.getReinvoiceid();   //入库单据编号
	        instatus = inBound.getInstatus();       //单据状态 0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架6-作废
	        intype = inBound.getIntype();         	//入库类型
			ownername = inBound.getOwnername();     //货主
	        createdate = inBound.getCreatetime();   //单据生成时间
	        createmanid = inBound.getCreatemanid(); //单据生成人员编号
	        suppliername = inBound.getSuppliername() ; //供应商信息
         	shippername = inBound.getShippername();    //承运人信息
         	customsno = inBound.getCustomsno();     //海关备案号
         	warehouseid = inBound.getWarehouseid();	//仓库ID
	        
	        
	        m_strStatusName = inBound.getM_strStatusName();    // 状态名
         	m_strTypeName = inBound.getM_strTypeName();        // 类型名
         	createman = inBound.getCreateman();                // 单据生成人员
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="getDetail('<%=instockid%>', this)">
     <td class="TD_LIST1" align="center"><input onClick="Change(this)" type="checkbox" name="check_id" class="input_checkbox" alt="<%=warehouseid%>" value="<%=instockid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
     <td class="TD_LIST" align="center"><%=m_strTypeName%></td>
     <td class="TD_LIST" align="center"><%=ownername == null? "&nbsp" : ownername%></td>
     <td class="TD_LIST" align="center"><%=createman%></td>   
     <td class="TD_LIST" align="center"><%=createdate%></td>
     <td class="TD_LIST" align="center"><%=suppliername == null? "&nbsp" : suppliername%></td>   
     <td class="TD_LIST" align="center"><%=shippername == null? "&nbsp" : shippername%></td>
     <td class="TD_LIST2" align="center"><%=customsno == null? "&nbsp" : customsno%></td>
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
