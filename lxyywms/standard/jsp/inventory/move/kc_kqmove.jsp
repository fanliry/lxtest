<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%

    HashMap hsPopedom = null;
	boolean kc_kcmovequery = false; //��ѯ
	boolean kc_kcmovereset = false; //����
	boolean kc_kcmovezc = false; //�ƿ⵽�ݴ�
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kc_kcmovequery") != null){
			kc_kcmovequery = true;
		}
		if(hsPopedom.get("kc_kcmovereset") != null){
			kc_kcmovereset = true;
		}
		if(hsPopedom.get("kc_kcmovezc") != null){
			kc_kcmovezc = true;
		}
    }
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
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
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var outinvoiceid = "";
	var condition = null;
	
	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//���
		var platoon = document.getElementById("platoon").value;			//��
		var column = document.getElementById("column").value;			//��
		var floor = document.getElementById("floor").value;				//��
		var package_id= document.getElementById("package_id").value;	//Ʒ��
		var tray_code = document.getElementById("tray_code").value;		//��������
		var productcode = document.getElementById("productcode").value;		//��Ʒ����
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("��ѡ�������");
			return;
		}
		if(platoon != null && !IsNum(platoon)){
			alert("���š�����Ϊ�����֣�");
			return;
		}
		if(column != null && !IsNum(column)){
			alert("���С�����Ϊ�����֣�");
			return;
		}
		if(floor != null && !IsNum(floor)){
			alert("���㡿����Ϊ�����֣�");
			return;
		}
		
		condition = "&warehouseid=" +��warehouseid + "&whAreaId=" +��whAreaId + "&cargoAlleyId=" +��cargoAlleyId + "&platoon=" +��platoon + 
			"&column=" +��column + "&floor=" +��floor + "&package_id=" +��package_id + "&tray_code=" +��tray_code+ "&productcode=" +��productcode;
			
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		list.formx1.action = strUrl + "inventoryMoveAction&flag=1" + condition;
		list.document.formx1.submit();
		Loading();	
	}
	
	function move(){
		    var k = 0;
			var ids = "";
			var check_ids = list.document.getElementsByName("check_id");
			var warehouseid = document.getElementById("warehouseid").value;
			
			if(warehouseid == null || warehouseid.length < 1){
				alert("����ѡ�������ֿ⡿!");
				return;
			}
			
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked){
					ids += check_ids[i].value+"|";
				}
			}
			if(ids!=null && ids.length > 0){
				ids = ids.substring(0, ids.length-1);//ȥ�������ġ�|��
			}
			
			if(ids == ""){
				alert("������ѡ��һ����¼��");
				return;
			}
			
			var WLeft = Math.ceil((window.screen.width-1100)/2);
	  		var WTop  = Math.ceil((window.screen.height-800)/2);
			
			window.open(strUrl+"inventoryMoveAction&flag=2&inventoryid="+ids+"&warehouseid"+warehouseid,'newwindow',"width=800,height=400,left="+WLeft+",top="+WTop+"'");
	
	}
	//����
	function resetData(formName) 
	{
	    var formObj = document.forms[formName]; 
	    var formEl = formObj.elements; 
	    for (var i=0; i<formEl.length; i++) 
	    {
	        var element = formEl[i]; 
	        if (element.type == 'submit') { continue; } 
	        if (element.type == 'reset') { continue; } 
	        if (element.type == 'button') { continue; } 
	 
	        if (element.type == 'text') { element.value = ''; } 
	        if (element.type == 'hidden') { element.value = ''; } 
	        if (element.type == 'textarea') { element.value = ''; } 
	        if (element.type == 'checkbox') { element.checked = false; } 
	        if (element.type == 'radio') { element.checked = false; } 
	        if (element.type == 'select-multiple') { element.selectedIndex = -1; } 
	        if (element.type == 'select-one') { element.selectedIndex = 0; } 
	    }
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		
		selectAreaListNotTem('001001', 'whAreaId', warehouseid, "1");// �������
		
		
		
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaListNotTem('001001', 'whAreaId', warehouseid, "1");// �������
		//selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//���ݿ������������б�
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "cargoAlleyId", whAreaId);
	}
	
	
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ����ƶ�</span></div>
		<div id="caozuo" class="f_r" align="right">
		   <%if(kc_kcmovequery){%><input type="button" style=width:55 class="button_search" value="&nbsp;&nbsp;&nbsp;��ѯ" onclick="searchData()"/><%}%>
		   <%if(kc_kcmovereset){%><input type="reset" style=width:55 class="button_reset" onclick="resetData('myform')" value="&nbsp;&nbsp;&nbsp;����"/><%}%>
		   <%if(kc_kcmovezc){%><input type="button" style=width:110 class="button_edit" value="&nbsp;&nbsp;&nbsp;�ƶ����ݴ�" onClick="move()"/><%}%>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <form id="myform" name="myform" method="post" action="">	
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">�ֿ⣺</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">�����</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--��ѡ��--</option></select></td>
     	  <td class="yx1" width="100" align="right">��λ��</td>
	   	  <td class="xx1">
	   	    <input type="text" id="platoon" size="2">��
	   	    <input type="text" id="column" size="2">��
	   	    <input type="text" id="floor" size="2">��
	   	  </td>
	    </tr>
	    <tr>
	     
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	      <td class="yx1" align="right">��Ʒ���룺</td>
     	  <td class="yx"><input name="productcode" type="text" size="15"></td>
	      <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	   	 
	    </tr>
      </table> 
		</form>
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" name="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/move/kc_move_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
