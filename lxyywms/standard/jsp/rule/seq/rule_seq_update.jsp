<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseSeq" %>
<%
	BaseSeq seq = (BaseSeq)request.getAttribute("seq");  
	String seqId = seq.getSeqId();   		//序列ID
 	String seqType = seq.getSeqType(); 		//序列类型
  	String seqValue = seq.getSeqValue();	//值
 	String seqRemark = seq.getSeqRemark();	//描述
  	String seqPrefix = seq.getSeqPrefix();	//前缀
  	int icodelength = seq.getIcodelength();	//位数
%>
<html>
<head>
<title>修改序列号信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	//保存修改序列号信息
	function OnOk(){
		var seqType = document.getElementById("seqType").value;		//序列类型
		var seqRemark = document.getElementById("seqRemark").value;	//描述
		var seqPrefix = document.getElementById("seqPrefix").value;	//前缀
		var icodelength = document.getElementById("icodelength").value;	//位数
		var seqValue = document.getElementById("seqValue").value;	//值
		
		if(seqRemark!=null && seqRemark.length>0){
			if(strlen(seqRemark) > 32){
				alert("【描述】过长!");
				return;
			}
		}
		
		if(seqPrefix == null || seqPrefix.length <1){
			alert("【前缀】不能为空!");
			return;
		}
		
		if(icodelength == null || icodelength.length <1){
			alert("【位数】不能为空!");
			return;
		}else{
			if(!numChar(icodelength)){
				alert("【位数】只能为正整数！");
				return;
			}
		}

		var back_msg = "&seqId=<%=seqId%>" + "&seqType=" + seqType + "&seqRemark=" + seqRemark + "&seqPrefix=" + seqPrefix
			 + "&icodelength=" + icodelength + "&seqValue=" + seqValue;
		
    	window.returnValue = back_msg;
    	window.close();
	}
-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：规则管理 -&gt; 序列号 -&gt; 修改序列号信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>     
      <td width="120px" class="yx1" align="right">类型：</td>
      <td class="xx1"><input type="text" id="seqType" value="<%=seqType==null?"":seqType%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right">描述：</td>
      <td class="xx1"><input type="text" id="seqRemark" value="<%=seqRemark==null?"":seqRemark%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>前缀：</td>
      <td class="xx1">
        <input type="text" id="seqPrefix" value="<%=seqPrefix==null?"":seqPrefix%>">
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>位数：</td>
      <td class="xx1">
        <input type="text" id="icodelength" value="<%=icodelength%>">
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right">当前值：</td>
      <td class="xx1">
        <input type="text" id="seqValue" value="<%=seqValue==null?"":seqValue%>" class="input_readonly" readonly>
      </td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>