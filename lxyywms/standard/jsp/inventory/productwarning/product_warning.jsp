<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean productWarningQuery = false; //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("productWarningQuery") != null){
			productWarningQuery = true;
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
	
	//����
	
	function report(){
	
		if(condition == null){
			alert("���Ȳ�ѯ��");
			return;
		}
		var name="kc_search";
		
		document.tempForm.action = strUrl + "inventoryAction&flag=2" + condition;
		//document.tempForm.target=name;
		//document.tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
		//document.tempForm.fireEvent("onsubmit");	
		document.tempForm.submit();	
	}
	function openWindow(name)  
    {  
      window.open('about:blank',name,'height=800, width=1200, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
    }
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//���
		var platoon = document.getElementById("platoon").value;			//��
		var column = document.getElementById("column").value;			//��
		var floor = document.getElementById("floor").value;				//��
		
		var lotnumber = document.getElementById("lotnumber").value;	//����
		var package_id= document.getElementById("package_id").value;	//Ʒ��
		var tray_code = document.getElementById("tray_code").value;		//��������
		
		var productstatus = document.getElementById("productstatus").value;	//��Ʒ״̬
		var csstatus = document.getElementById("csstatus").value;		//���״̬
		var indate_from = document.getElementById("indate_from").value;	//��������
		var indate_to = document.getElementById("indate_to").value;		//��������
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
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
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&cargoAlleyId=" +cargoAlleyId + "&platoon=" +platoon + 
			"&column=" +column + "&floor=" +floor + "&lotnumber=" + lotnumber + "&productid=" +package_id + "&tray_code=" +tray_code+ "&productstatus=" +productstatus+ "&status=" +csstatus+ "&indate_from=" +indate_from+ "&indate_to=" +indate_to;
			
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		
		list.formx1.action = strUrl + "inventoryAction&flag=1" + condition;
		
		list.document.formx1.submit();
		
		Loading();	
	}
	

	//Ԥ����ѯ
	function prowarn(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//���
		var platoon = document.getElementById("platoon").value;			//��
		var column = document.getElementById("column").value;			//��
		var floor = document.getElementById("floor").value;				//��
		
		var lotnumber = document.getElementById("lotnumber").value;	//����
		var package_id= document.getElementById("package_id").value;	//Ʒ��
		var tray_code = document.getElementById("tray_code").value;		//��������
		
		var productstatus = document.getElementById("productstatus").value;	//��Ʒ״̬
		var csstatus = document.getElementById("csstatus").value;		//���״̬
		var indate_from = document.getElementById("indate_from").value;	//��������
		var indate_to = document.getElementById("indate_to").value;		//��������
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
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
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&cargoAlleyId=" +cargoAlleyId + "&platoon=" +platoon + 
			"&column=" +column + "&floor=" +floor + "&lotnumber=" + lotnumber + "&productid=" +package_id + "&tray_code=" +tray_code+"&productstatus=" +productstatus+ "&status=" +csstatus+ "&indate_from=" +indate_from+ "&indate_to=" +indate_to;
			
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		
		list.formx1.action = strUrl + "inventoryAction&flag=11" + condition;
		
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//ҳ���¼
	function OnLoad(){
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		//��Ʒ״̬
		selectType('', 'productstatus', 'wpzt');
		//��Ʒ״̬
		selectType('2', 'csstatus', 'hwzt');
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		prowarn();
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ��ƷԤ��</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1" ><input onclick="prowarn();" type="button" id="btn1" value="��ƷԤ����ѯ" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'" style="width: 97px;"></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">�ֿ⣺</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">�����</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--��ѡ��--</option></select></td>
     	  <td class="yx1" width="100" align="right">��λ��</td>
	   	  <td class="yx">
	   	    <input type="text" id="platoon" size="2">��
	   	    <input type="text" id="column" size="2">��
	   	    <input type="text" id="floor" size="2">��
	   	  </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">������ţ�</td>
	      <td class="yx">
	      	<input type="text" name="lotnumber"  size="15">
	      </td>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	      <td class="yx1" align="right">�������룺</td>
     	  <td class="yx" colspan = "3"><input name="tray_code" type="text" size="25"></td>

	    </tr>
	    <tr>
	      <td class="yx1" align="right">��λ״̬��</td>
	      <td class="yx">
	      	<select id="csstatus" style="width:110px;">
                <option value="">--��ѡ��--</option>
              </select>
	      </td>
	      <td class="yx1" align="right">��Ʒ״̬��</td>
          <td class="yx">
              <select id="productstatus" style="width:110px;">
                <option value="">--��ѡ��--</option>
              </select>
           </td>
	     <td class="yx1" align="right">������ڣ�</td>
	     <td class="yx" colspan = "3">
	      	<input id="indate_from" type="text" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text"  onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	    </tr>
	    
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/productwarning/product_warning_list.jsp" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=120&flag=Y" 
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