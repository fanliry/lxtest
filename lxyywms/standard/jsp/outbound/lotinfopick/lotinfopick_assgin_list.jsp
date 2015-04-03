<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%
    List ls = (List)request.getAttribute("exResultList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>库存详细</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script>

	//设置多选框是否选中
	function setSel(the){
	
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			//改变背景色
			if(i==the){
				check_ids[i].checked = true;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}else{
				check_ids[i].checked = false;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}

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
	}
</script>
</head>
<body  onload="javascript:OnLoad();" >
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
<table width="100%" id="tbone" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">需数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">客户</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">代码</div></td>
   </tr>
<%
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		Object[] detailInfo = null;  //单据产品信息
		String saleno = "";
		String detailid = "";
		double noassign = 0;
		String customerName = "";
		String lotinfo = "";
		String productCode = "";
		String printdate = "";
		String productid = "";
		String Virtualwhid = "";
		String info = "";
		for(int i = 0; i < ls.size(); i++)
		{
			  detailInfo = (Object[])ls.get(i);  //单号，单据详细id，客户，批号，产品代码，数量，生产日期，产品id,逻辑库区
			  saleno = (String)detailInfo[0];
			  detailid = (String)detailInfo[1];
			  customerName = (String)detailInfo[2];
			  lotinfo = (String)detailInfo[3];
			  productCode = (String)detailInfo[4];
			  noassign = ((Double)detailInfo[5]).doubleValue();
			  printdate = (String)detailInfo[6];
			  productid = (String)detailInfo[7];
			  Virtualwhid = (String)detailInfo[8];
			  
			  info = detailid+"|"+Virtualwhid+"|"+productid+"|"+lotinfo+"|";
 %>
	         <tr class="list_normal_tr" onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
		     <td class="TD_LIST" align="center"><input onClick="setSel(<%=i%>)" type="radio" name="check_id" class="input_checkbox" value="<%=info%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=saleno%></td>
		     <td class="TD_LIST" align="center"><%=noassign%></td>
		     <td class="TD_LIST" align="center"><%=customerName==null?"":customerName%></td>
		     <td class="TD_LIST" align="center"><%=lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td> 		     
		   </tr>	       	 
<%	      	 
		}
	}
%>
   <tr>
     <td height="100%" colspan="6" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
   </tr>
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
