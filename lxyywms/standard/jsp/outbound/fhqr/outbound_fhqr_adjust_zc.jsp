<%@ page contentType="text/html; charset=GBK"%>
<%
 	//A�ͻ�����ID
    String strOutBoundId = request.getParameter("aoutid");
    String warehouseid = request.getParameter("warehouseid");

 %>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>


<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";

 //****************************************************
 //��ʾ�ݴ���
 function viewstorage()
 {
    var warehouseid = document.getElementById("warehouseid").value;
    var traycode = document.getElementById("traycode").value;
    var zcwhareaid = document.getElementById("zcwhareaid").value;
	Loading();
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=1";
	list.location.href = strUrl + "&warehouseid=" + warehouseid+ "&traycode=" + traycode+ "&zcwhareaid=" + zcwhareaid;	
 }
 function DZCAreaList(){
 	var warehouseid = document.getElementById("warehouseid").value;
 	var whAreaId = document.getElementById("whAreaId").value;
    selectZCAreaList('zcwhareaid', warehouseid,whAreaId);//�ݴ���
 }
 function OnLoad()
 {	
	DWREngine.setAsync(false);
	selectObject('<%=warehouseid%>', 'warehouseid', '1');
	DWREngine.setAsync(true);
	var warehouseid = document.getElementById("warehouseid").value;
	selectAreaList("", "whAreaId", warehouseid, "1");
 }

-->
</script>
</head>

<body onload="OnLoad();">
 <input type="hidden" name="warehouse_out_id">
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td height="100%"> 
 <table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr height="20">
     <td width="10%">
   		  <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
		   <tr >
		   	 <td class="x" width="50"><span id="n_remind" class="title" style="color: red; ">�ݴ�:</span></td>
		     <td class="x" width="80"><div align="left">
		     	<select name="warehouseid"  style="width:100px;" onchange="" disabled>
                    <option value="">--��ѡ��--</option>
                  </select>
		     </div></td> 
		     <td class="y1" width="100" align="right">������</td>
     	     <td class="x"><select id="whAreaId" onChange="DZCAreaList();" style="width:117px;"><option value=""></option></select></td>
		     <td class="y1" width="70"><div align="right">��&nbsp;��&nbsp;����</div></td>
		     <td class="x" width="80"><div align="left">
		     	<select name="zcwhareaid"  style="width:100px;" disabled>
                    <option value="">--��ѡ��--</option>
                  </select>
		     </div></td>
		     <td class="y1" width="70"><div align="right">�������룺</div></td>
                <td class="x" width="80"><div align="left"><input name="traycode" type="text" size="10"></div></td>
		     <td  height="27"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;
		       <input onclick="viewstorage()" type="button" name="queryBtn" value="��ѯ" class="button_style">&nbsp;&nbsp;
		     </div></td> 
		   </tr>
		 </table>	
   	</td>
   </tr>
   <tr>
     <td height="100%" >
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
         src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc_list.jsp"></iframe>
     </td>
   </tr>
 </table>
     </td>
   </tr>
 </table>    
<!-- ҳ����ز㣨start�� -->
<div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
	<div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
</div>
<!-- ҳ����ز㣨end�� --> 
</body>
</html>
 