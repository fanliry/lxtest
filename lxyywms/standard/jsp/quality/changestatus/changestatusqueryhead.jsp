<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryQualityResult" %>
<%
   
    //表中的列排序
	List list = (List)request.getAttribute("exResultList");
	int len = 0;
	if(list!=null && list.size()>0){
	  len = list.size();
	}
%>
<html>
<head>
  <title>仓储配送管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
  <script type="text/javascript">
  <!--
	//获取明细
    function getDetail(str)
	{
		var actionStr = "BMSService?actionCode=inventoryQualityAction&method=ricoExecDetail&id=" + str;
		window.parent.myIframe2.location.href = "<%=request.getContextPath()%>/" + actionStr;
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
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
  -->
  </script>
</head>

<body  onload="javascript:OnLoad();">
<form id="myform" name="myform" method="post" action="">
   <table width="100%" height="100%" id="table"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td width="5%" class="TD_LIST_TITLE"><div class="list_title">
	    <!-- <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll('myform','checkbox_all','checkbox_id')"> -->行号</div>
	  </td>
	  <td width="10%"  class="TD_LIST_TITLE"><div class="list_title">放行单号</div></td>
	  <td width="10%"  class="TD_LIST_TITLE"><div class="list_title">操作人员</div></td>
	  <td width="15%"  class="TD_LIST_TITLE"><div class="list_title">单据日期</div></td>
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">批号</div></td>  
	  <td width="18%" class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
	 
	</tr>
<%
    if(list!=null && !list.isEmpty()){
        for(int i=0; i<list.size(); i++)
        {
        	InventoryQualityResult adjust = (InventoryQualityResult)list.get(i);
        	String strId = adjust.getM_strCheckResultId();		//ID
        	String strCustomer = adjust.getM_strOpManId();		//操作人员
        	String strCreateDate = adjust.getM_strCreateDate();		//单据日期
        	String strLotNumber = adjust.getM_strLotNumber();	        //批号
        	String strNum = adjust.getM_strNum();	//数量
        	
%>
	<tr  onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''"  onclick="getDetail('<%=strId%>')">
	  <td class="TD_LIST1" align="center">
	    <input type="checkbox" name="checkbox_id" value="<%=strId%>" id="<%=strId%>"  class="input_checkbox" alt="<%=i+1%>">
	  </td>
	  <td class="TD_LIST1" align="center"><%=strId%></td>
	  <td class="TD_LIST1" align="center"><%=strCustomer == null ? "&nbsp;" : strCustomer%></td>
	  <td class="TD_LIST1" align="center"><%=strCreateDate == null ? "&nbsp;" : strCreateDate%></td>
	  <td class="TD_LIST1" align="center"><%=strLotNumber == null ? "&nbsp;" : strLotNumber%></td>
	  <td class="TD_LIST1" align="center"><%=strNum  == null ? "&nbsp;" : strNum%></td> 
	  
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
    <tr height="100%">
	  <td height="100%" colspan="7" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
