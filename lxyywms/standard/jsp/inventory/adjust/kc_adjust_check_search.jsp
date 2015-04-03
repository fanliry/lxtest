<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<%
String path = request.getContextPath();
String strTime = StrTools.getCurrDateTime(8);
%>

<html>
  <head>
    
    <title>浙江刚玉物流仓库管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>

  </head>
  <script type="text/javascript">
  var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
  function changeDel()
  {
	  var id = document.getElementById("inouttype").value;
	  if(id!=null&&id==1){
		detail.location.href="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_indetail.jsp";
	  }else{
		detail.location.href="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_outdetail.jsp";
		  }
  }
  function queryData()
  {
     var checkType = document.getElementById("inouttype").value;
     var fmDate = document.getElementById("fmDate").value;
     var toDate = document.getElementById("toDate").value;
     //var kccheckid = document.getElementById("kccheckid").value;
     
     var condition = "&checkType="+checkType+"&fmDate="+fmDate+"&toDate="+toDate;//+"&kccheckid="+kccheckid;  
     list.location.href=strUrl+"InventoryCheckSearchAction&method=ricoExecAdjust"+condition;
  }
  
  function savaDate(){
		 var id = "" ;
		 var k=0;
         var check_ids = list.document.getElementsByName("check_id" );
         for( var i=0; i<check_ids.length; i++){
             if(check_ids[i].checked){
                 id = check_ids[i].value;
                 k++;
                  break;
            }
        }
         if( k == 0 ){
            alert( "请选择一条记录！" );
             return;
        } else if(k != 1){
            alert( "只能选择一条记录！" );
             return;
        }
			window.returnValue=id;
			window.close();		
	}
  
  function OnLoad()
  {
   // changeDel();
  }
  
  </script>
  <body onLoad="OnLoad()">
  <div class="con_bk" >
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td height="5">
  <!-- ======== 当前位置 ======== -->
  <div class="wz">
  <div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 盘点查询</span></div>
  <div  class="f_r" id="caozuo"> 
  	<ul>
  		<li class="tubiao1" style="width:60px;"><a href="#" onclick="savaDate();">确定</a></li>
  		<li class="tubiao1" style="width:60px;"><a href="#" onclick="queryData();">查询</a></li>
  	</ul>
  </div>
  </div>
  </td></tr>
  <tr><td height="5"></td></tr>
  <tr><td>
  <table  width="99%" height="5"  border="0" align="center" cellpadding="0" cellspacing="0"class="table_add"><tr>
  <td class="y1" align="right" width="100">盘点类型：</td><td class="x"><select id="inouttype">
	 <option value="0">盘盈记录</option>
	 <option value="1">盘亏记录</option>
	 </select></td>
  <td class="y1" align="right" width="100">时间：</td> <td class="y"><input id="fmDate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"/>-<input id="toDate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"/></td>
  <!-- <td class="yx1"><div align="right">盘点单编码：</div></td><td class="yx"><div align="left"><input type="text" name="kccheckid" ></div></td>  -->
  </tr>
 </table>
 </td></tr>
 <tr><td height="5"></td></tr>
 <tr><td height="100%">
 <!-- ====单据list===== -->
 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
 <!-- =====单据信息===== -->
 <tr><td height="18" class="title">单据信息</td></tr>
 <tr>
 	<td height="280"> 
 	<iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="yes" src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_check_search_list.jsp"></iframe>
 	</td>
 </tr>
 <tr>
	<td height="25">
	 <iframe id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	</td>
 </tr>
 <!-- ===单据明细===== -->
 <%--<tr><td height="18" class="title">单据明细</td></tr>
 
 <tr><td height="180">
 <iframe id="detail" name="detail"width="100%" height="100%" frameborder="0" scrolling="yes" src="<%=request.getContextPath()%>/standard/jsp/inventory/checksearch/kc_check_search_indetail.jsp"></iframe>
 </td></tr> --%>
 </table>
 </td></tr>
  </table>
  </div>
  </body>
</html>
