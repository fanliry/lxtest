<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.text.NumberFormat" %>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendartime.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lotsearch.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
 <script>
    var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
 	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//��������Ƿ�Ϊ������
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
 	//����
	function assignData()
	{
	    var key = null;
		var check_ids = list.document.getElementsByName("check_id");
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
		}else{
		    var info =  key.split("|");
		    var detailid = info[0];
		    var Virtualwhid = info[1];
		    var productid = info[2];
		    var lotinfo = info[3];
		    var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/lotinfopick/zc_kc.jsp?detailid="+detailid+"&Virtualwhid="+Virtualwhid+"&productid="+productid+"&lotinfo="+lotinfo, "","dialogWidth=800px;dialogHeight=600px;");
			if(result != null && result.length > 0){ ////���id|��������,���id|��������
			  list.location.href = strUrl + "outBoundAction&method=pickZcProduct&detailid="+detailid+"&result="+result;
			}
		}
	}
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
  	function OnLoad()
	{	
		list.location.href = strUrl + "outBoundAction&flag=lotinfopick";
	}
 </script>
</head>
  <body onload="javascript:OnLoad();">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td><div class="font_001F56_bold_12">��ǰλ�ã������ż������</div></td>
     <td align="right" >
            <input name="button_delete" type="button" class="button_add" id="button_delete" value="&nbsp;&nbsp;����" onclick="assignData();" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
     </td>
   </tr>
  </table>
</td>
   </tr>
   <tr>
   	 <td height="10">
   	 </td>
   </tr>
   <tr>
     <td height="100%"> 
  <!-- ======== ����б���ʼ ======== -->
 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/lotinfopick/lotinfopick_assgin_list.jsp" scrolling="auto" frameborder="0" width="100%" height="100%">  
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ����б����� ======== -->
     </td>
   </tr>
 </table>
  </body>
</html>