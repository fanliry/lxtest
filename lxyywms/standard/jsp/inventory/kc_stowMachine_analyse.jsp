<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
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
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	function Search(){
		var inout = document.getElementById("inout").value;
		var warehouseid = document.getElementById("warehouseid").value;
		
		if(warehouseid == null || warehouseid.length <1)
		{
			alert("���ֿ⡿����Ϊ��!");
			return false;
		}
		
		condition = "&inout=" +��inout+"&warehouseid=" +��warehouseid;
		
		list.location.href = ac + "InAnalysisAction&flag=2" + condition;
	}
	function OnLoad(){
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
	}
-->
</script>
</head>
<body  onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>KPI���� -&gt; �Ѷ�����ɷ���</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="Search()">����</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
        <tr>
	      <td class="y1" width="100" align="right">�ֿ⣺</td>
	      <td class="x"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100" align="right">����⣺</td>
	      <td><select name="inout"  style="width:117px;"><option value="1">���</option><option value="2">����</option></select></td>
	    </tr> 
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=InAnalysisAction&flag=2&inout=1&warehouseid=001" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
	  </td>
    </tr>
    <tr><td align="center"><strong><Font color="red">��ѯ10���Ѷ����ǰû��ִ������������ҵ����</Font></strong></td></tr>
  </table>  	
</div>
</html>
