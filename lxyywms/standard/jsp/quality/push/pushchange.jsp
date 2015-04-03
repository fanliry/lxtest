<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime = StrTools.getCurrDateTime(8);
	
	HashMap hsPopedom = null;
	boolean pushChangeComfirm = false; 

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("pushChangeComfirm") != null){
			pushChangeComfirm = true;
		}
    }
%>

<html>
<head>
<title>放行系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/md5.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	function onload()
	{
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

	function OK()
	{
		var productId = document.getElementById("package_id").value;	//品名
		var lotinfo = document.getElementById("lotinfo").value;		//批号
		var Qualityid = document.getElementById("Qualityid").value;		//质检单号

		if(productId == null || productId.length <1)
		{
			alert("品名不能为空!");
			return;
		}
		
		if(lotinfo == null || lotinfo.length <1)
		{
			alert("批号不能为空!");
			return;
		}
		if(Qualityid == null || Qualityid.length <1)
		{
			alert("质检单号不能为空!");
			return;
		}
		
		window.location.href="<%=request.getContextPath()%>/BMSService?actionCode=QualityNewAction&method=ricoExecPush&productId="+productId+
							"&lotinfo="+lotinfo+"&Qualityid="+Qualityid;
	}
-->
</script>
</head>

<body onload="onload();">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="5">
   <div class="wz">
	  <div id="dqwz" class="f_l">当前位置：<span>质量管理 &gt;&gt; 放行</span></div>
	  </div>
</td>
   </tr>
  <tr>
    <td valign="top" colspan="3">
	
	
	<table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
      <tr><td></td></tr>
    </table>
	
	
	 
	 <!-- ======== 修改密码 ======== -->
	 <form name="myform" method="post">
	   
		<table align="center" border="0" cellpadding="1" cellspacing="1" width="400" class="TABLE_ADD" >
		    <tr class="add_tr">
		    	<td height="30" class="xx1" colspan=2 ><div align="center">放行</div></td>
		    </tr>
		    <tr>
     	  <td class="yx1" align="right">品名：</td>
	      <td class="yx">
            	<input type="hidden" name="package_id"><input type="text" name="package_name" readonly>
          		<input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select"><font color="red"> *</font>
	      </td>
		    </tr>
			<tr>
			    <td class="yx1"><div align="right">批号：</div></td>
			    <td class="yx">
				<input type="text" name="lotinfo" >&nbsp;<font color="red">*&nbsp;</font></td>
			</tr>
			<tr>	
				<td class="yx1" ><div align="right">质检单号：</div></td>
			    <td class="yx" >
				<input type="text" name="Qualityid" >&nbsp;<font color="red">*&nbsp;</font></td>
		    </tr>
		    <tr>
			  <td class="xx1" align="center" colspan=2 >
				<div align="center">
		          <%if(pushChangeComfirm){%><input type="button" name="saveBtn" value="确定 " class="button_style" onclick="OK();">&nbsp;&nbsp;&nbsp;
		          <input type="reset" name="resetBtn" value="重置" class="button_style"><%}%>
        </div>
			  </td>
			</tr>
		    <tr>
			  <td  align="center" colspan=2 ><font color="red">注:*为必填内容</font></td>
			</tr>
			
	    </table>													
	    
	 </form>
	 <!-- ======== 修改密码 ======== -->
	 
	
	</td>
    <td width="9" background="<%=request.getContextPath()%>/standard/images/wds_main_03.gif"></td>
  </tr>
</table>
</body>
</html>
