<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean InoutControlUpdate = false; //�޸�

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("InoutControlUpdate") != null){
			InoutControlUpdate = true;
		}
    }
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	//�޸�
	function Update()
	{
		var key = null;
		var check_ids = left.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				key = check_ids[i].value;
				break;
			}
		}
		if(key == null)
		{
			alert("����ѡ��һ����¼��");
			return;
		}else
		{
			condition = "&ids="+key;
			left.location.href = ac + "inoutControlAction&method1=update&flag=3" + condition;
		}
	}
	//ҳ���¼
	function OnLoad()
	{
		left.location.href = ac + "inoutControlAction&method1=search&flag=5";
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div class="con_bk">
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ����⿨������</span></div>
	  </div>
     </td>
   </tr>
   <tr>
     <td height="100%"> 
		 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr>
		     <td width="300" rowspan="4">
			 	<iframe id="left" name="left" width="100%" height="100%" frameborder="0" scrolling="yes"
			 		src="<%=request.getContextPath()%>/standard/jsp/base/outset/out_set_left.jsp"></iframe>
			 </td>
			  <td width="5"></td>
		     <td align="right">
		       <%if(InoutControlUpdate){%><input type="button" value="�޸�" class="button" onClick="Update()"><%}%>
		     </td>
		   </tr>
		   <tr height="100%">
			 <td width="5"></td>
			 <td colspan="2">
			 </td>
		   </tr>
		 </table>
     </td>
   </tr>
 </table>
   </div>
</body>
</html>