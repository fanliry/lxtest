<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	//String id = request.getParameter("id"); 
	String wharehouseid = request.getParameter("wharehouseid"); 
%>
<html>
<head>
<title>增加库存调整单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	//保存
	function saveData()
	{
	   var warehouseid = document.getElementById("warehouseid").value;
	   var strwhareaid = document.getElementById("whAreaId").value;//库区
	   var strcargospaceid = document.getElementById("cargospace_id").value;//货位
	   var strpackageid = document.getElementById("package_id").value;//产品id
	   var strprintdate = document.getElementById("indate_from").value;//生产日期
	   var strpunit= document.getElementById("punit").value;//单位
	   var strlotid = document.getElementById("lotid").value;//批号类型
	   var strlotinfo= document.getElementById("lotinfo").value;//批号信息
	   var strtraycode= document.getElementById("traycode").value;//托盘条码
	   var strpnum= document.getElementById("pnum").value;//系统数量
	   if(strwhareaid == null ||  strwhareaid.length<1)
		{
			alert("【库区】不能为空");
			return;
		}
	   if(strcargospaceid == null ||  strcargospaceid.length<1)
		{
			alert("【货位】不能为空");
			return;
		}
		if(strpackageid == null || strpackageid.length<1)
		{
			alert("【产品】不能为空");
			return;
		}
		if(strprintdate == null || strprintdate.length<1)
		{
			alert("【生产日期】不能为空");
			return;
		}
		if(strlotid == null || strlotid.length<1)
		{
			alert("【批次类型】不能为空");
			return;
		}
		if(strlotinfo == null || strlotinfo.length<1)
		{
			alert("【批次信息】不能为空");
			return;
		}
		if(strtraycode == null || strtraycode.length<1)
		{
			alert("【托盘条码】不能为空");
			return;
		}
		if(strpnum == null || strpnum.length<1)
		{
			alert("【数量】不能为空");
			return;
		}
		
		var strParam = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecAddDetail" 
			                + "&flag=3"	
			                + "&warehouseid="+warehouseid
			                + "&whareaid="+strwhareaid
				            + "&cargospaceid="+strcargospaceid
        					+ "&packageid="+strpackageid
        					+ "&printdate="+strprintdate
        					+ "&punit="+strpunit
							+ "&lotid=" + strlotid
							+ "&lotinfo="+strlotinfo
        					+ "&traycode="+strtraycode
							+ "&pnum=" + strpnum;    
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
		return true;
	}
	function basCustomer(obj, url, obj1)
    {
		var strUrl=url + '/jsp/selectPages/customer_select.jsp';
		var result = showModalDialog(strUrl,'','dialogWidth=650px;dialogHeight=440px');	
		if(result != null && result.length > 0)
		{
			 var tempParam = result;
	
			 var temStr = tempParam.split("|");
			 		
			 document.getElementById(obj).value = temStr[0];
			 if(obj1)
			 {
			 	document.getElementById(obj1).value = temStr[1];
			 }
		}
    }
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	//页面登录
	function OnLoad(){
		//仓库
		DWREngine.setAsync(false);
		//selectObject("<%=wharehouseid%>", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectType('', 'punit', 'punit');
		selectObject("", "lotid", "phmc");	
	}
-->
</script>
</head>
<%
	String strTime = StrTools.getCurrDateTime(8);
%>
<body onLoad="OnLoad()">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt;库存调整 -&gt; 增加库存</div></td>
   </tr>
 </table>
 <form>
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1" width="100" align="right">仓库：</td>
	 <td class="yx"><input id="warehouseid" type="text" value="<%=wharehouseid%>" style="width:117px;" disabled></td>    
	 <td class="yx1" width="100" align="right">库区：</td>
     <td class="yx"><select id="whAreaId"style="width:117px;"><option value=""></option></select></td>   	  
     <td class="yx1" width="100px" align="right">货位：</td>
	 <td class="xx1">
		    <input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" style="width:117px;" readonly>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid='+document.getElementById('warehouseid').value+'&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       			type="button" value="…" class="button_select">
     </td>
     </tr> 
     <tr>
	 <td class="yx1" align="right">品名：</td>
	 <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" style="width:117px;" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	 </td>
     <td class="yx1" align="right">生产日期：</td>
	 <td class="yx">
	     <input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;">
	 </td>
	  <td class="yx1" align="right">单位：</td>
	  <td class="xx1">
	      	<select id="punit" name="punit" style="width:117px;">
                <option value="">--请选择--</option>
              </select>
	      </td>
	  </tr>
      <tr>
	  <td class="yx1" align="right">批号类型：</td>
	  <td class="yx">
	      	<select id="lotid" style="width:117px;">
                <option value="">--请选择--</option>
              </select>
	  </td>
	  <td class="yx1" align="right">批号信息：</td>
      <td class="yx"><div align="left">
     		<input type="text" size="10" style="width:117px;" name="lotinfo">    
      </div></td>
     <td class="yx1"><div align="right">托盘条码：</div></td>
     <td class="xx1"><div align="left">
     		<input type="text" style="width:117px;" size="10" name="traycode">    
     </div></td>
     </tr>
     <tr>
     <td class="yx1"><div align="right">库存数量：</div></td>
     <td class="xx1" colspan="5"><div align="left">
     		<input type="text" style="width:117px;" size="20" name="pnum">    
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="6" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;保存" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
</body>
</html>