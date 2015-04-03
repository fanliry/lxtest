<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCarton" %>
<%
	BaseCarton carton = (BaseCarton)request.getAttribute("carton");  
	String cartonid = carton.getCartonid();			//装箱代码
	String cartontype = carton.getCartontype();		//装箱类型
	String descr = carton.getDescr();				//装箱描述
	double boxleng = carton.getBoxleng();			//长
	double boxwidth = carton.getBoxwidth();			//宽
	double boxheight = carton.getBoxheight();		//高
	double maxcube = carton.getMaxcube();			//最大体积
	double maxweight = carton.getMaxweight();		//最大重量
	double maxcount = carton.getMaxcount();			//最大数量
	double selfweight = carton.getSelfweight();		//自重
	double cartonpercent = carton.getCartonpercent();//装箱百分比
	String remark = carton.getRemark();				//备注
%>
<html>
<head>
<title>修改周转箱信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	//保存周转箱信息
	function OnOk(){
		var cartonid = document.getElementById("cartonid").value;
		var descr = document.getElementById("descr").value;			//装箱描述
		var boxleng = document.getElementById("boxleng").value;		//长
		var boxwidth = document.getElementById("boxwidth").value;	//宽
		var boxheight = document.getElementById("boxheight").value;	//高
		var maxcube = document.getElementById("maxcube").value;		//最大体积
		var maxweight = document.getElementById("maxweight").value;	//最大重量
		var maxcount = document.getElementById("maxcount").value;	//最大数量
		var selfweight = document.getElementById("selfweight").value;	//自重
	    var cartonpercent = document.getElementById("cartonpercent").value;	//装箱百分比
	    var remark = document.getElementById("remark").value;		// 备注
		
		//装箱描述
		if(descr == null || descr.length <1)
		{
			alert("【装箱描述】不能为空!");
			return;
		}else{
			if(strlen(descr) > 100){
				alert("【装箱描述】过长!");
				return;
			}
		}
		//长
		if(boxleng != null && boxleng.length > 0 && boxleng != 0)
		{
			if(!isDig(boxleng))
			{
				alert("【长】只能为正浮点数或0！");
				return;
			}
		}
		
		//宽
		if(boxwidth != null && boxwidth.length > 0 && boxwidth != 0)
		{
			if(!isDig(boxwidth))
			{
				alert("【宽】只能为正浮点数或0！");
				return;
			}
		}
		
		//高
		if(boxheight != null && boxheight.length > 0 && boxheight != 0)
		{
			if(!isDig(boxheight))
			{
				alert("【高】只能为正浮点数或0！");
				return;
			}
		}
		//最大体积
		if(maxcube != null && maxcube.length > 0 && maxcube != 0)
		{
			if(!isDig(maxcube))
			{
				alert("【最大体积】只能为正浮点数或0！");
				return;
			}
		}
		//最大重量
		if(maxweight != null && maxweight.length > 0 && maxweight != 0)
		{
			if(!isDig(maxweight))
			{
				alert("【最大重量】只能为正浮点数或0！");
				return;
			}
		}
		//最大数量
		if(maxcount!=null && maxcount.length>0 && maxcount != 0)
		{
			if(!numChar(maxcount)){
				alert("【最大数量】必须是数字!");
				return;
			}
		}
		//自重
		if(selfweight != null && selfweight.length > 0 && selfweight != 0)
		{
			if(!isDig(selfweight))
			{
				alert("【自重】只能为正浮点数或0！");
				return;
			}
		}
		//装箱百分比
		if(cartonpercent != null && cartonpercent.length > 0 && cartonpercent != 0 && cartonpercent > 1)
		{
			if(!isDig(cartonpercent))
			{
				alert("【装箱百分比】只能为小于1的正浮点数或0！");
				return;
			}
		}
		//备注
		if(remark!=null && remark.length>0)
		{
			if(strlen(remark) > 50){
				alert("【备注】过长!");
				return;
			}
		}

		var back_msg = "&cartonid=" + cartonid + "&descr=" + descr + "&boxleng=" + boxleng + "&boxwidth=" + boxwidth + "&boxheight=" + boxheight
			 + "&maxcube=" + maxcube + "&maxweight=" + maxweight + "&maxcount=" + maxcount + "&selfweight=" + selfweight
			 + "&cartonpercent=" + cartonpercent + "&remark=" + remark;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	}

-->
</script>
</head>

<body>
 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 周转箱 -&gt; 修改周转箱信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>装箱描述：</td>
      <td class="xx1" colspan="3">
      	<input type="hidden" id="cartonid" value="<%=cartonid%>"><input type="text" id="descr" maxlength="100" size="35" value="<%=descr%>">
      </td>
    </tr> 
    <tr>
      <td class="yx1" align="right">长：</td>
      <td class=yx><input type="text" id="boxleng" maxlength="14" size="14" value="<%=boxleng%>"></td>
      <td width="100px" class="yx1" align="right">最大体积：</td>
      <td class="xx1"><input type="text" id="maxcube" maxlength="14" size="14" value="<%=maxcube%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">宽：</td>
      <td class="yx"><input type="text" id="boxwidth" maxlength="14" size="14" value="<%=boxwidth%>"></td>
      <td class="yx1" align="right">最大重量：</td>
      <td class="xx1"><input type="text" id="maxweight" maxlength="14" size="14" value="<%=maxweight%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">高：</td>
      <td class="yx"><input type="text" id="boxheight" maxlength="14" size="14" value="<%=boxheight%>"></td>
      <td class="yx1" align="right">最大数量：</td>
      <td class="xx1"><input type="text" id="maxcount" maxlength="14" size="14" value="<%=(int)maxcount%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">自重：</td>
      <td class="yx"><input type="text" id="selfweight" maxlength="14" size="14" value="<%=selfweight%>"></td>
      <td class="yx1" align="right">装箱百分比：</td>
      <td class="xx1"><input type="text" id="cartonpercent" maxlength="14" size="14" value="<%=cartonpercent%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">备注：</td>
      <td class="xx1" colspan="3"><input type="text" id="remark" maxlength="50" size="35"value="<%=remark==null?"":remark%>"></td>
    </tr>
    <tr>
      <td class="TD_MGR_TOP" align="center" colspan="4">
       <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
       <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>