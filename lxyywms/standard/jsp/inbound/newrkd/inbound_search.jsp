<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inboundRkdQuery = false;    //��ѯ
	boolean inboundRkdCreate = false;     //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundRkdQuery") != null){
			inboundRkdQuery = true;
		}
		if(hsPopedom.get("inboundRkdCreate") != null){
			inboundRkdCreate = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
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
	var condition = null;
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//���
		var cargospaceid = document.getElementById("cargospace_id").value;	//��λid
		
		var startTime = document.getElementById("start_time").value;		//�������
		var endTime = document.getElementById("end_time").value;		//�������
		var productdate = document.getElementById("productdate").value;		//��Ʒ��������
		
		
		var productid= document.getElementById("package_id").value;	//Ʒ��
		var traycode = document.getElementById("tray_code").value;		//��������
		var lotinfo = document.getElementById("lotinfo").value;		//����
		
		var isopen = document.getElementById("isopen").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyId=" + cargoAlleyId + "&cargospaceid=" + cargospaceid
			 + "&startTime=" + startTime+ "&endTime=" + endTime+ "&productdate=" + productdate + "&productid=" + productid + "&traycode=" + traycode + "&lotinfo=" + lotinfo + "&isopen=" + isopen;
	
		list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecSearchJobCollection" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		//selectType('1', 'invoicetype', 'zyly');		//��ҵ��Դ
		DWREngine.setAsync(true);
		searchData();
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	function Mupload(){
		
	}
	
	//��ӡ
	function report(){
		if(condition == null){
			alert("���Ȳ�ѯ��");
			return;
		}
		
		document.tempForm.action = strUrl + "inBoundJobAction&method=ricoExecPrint" + condition;
		
		document.tempForm.submit();	
	}
	
	//1.���ɵ��� 2.������������
	function Add(type){
		
		var alts = "";
		var keys = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				keys += check_ids[i].value + ",";
				alts += check_ids[i].alt + ",";
			}
		}
		if(type == 1){//1.���ɵ��� 
			if(keys == ""){
				alert("������ѡ��һ����¼��");
				return;
			}
			
			if(confirm("��ȷ������ѡ�����ɵ��ݣ�")){
				//alert(keys);
				list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecCreate" + "&flag=1" + condition;
				list.document.getElementById("keys").value = keys;
				list.document.getElementById("condition").value = condition;
				list.document.formx1.submit();
			}
			else{
				return;
			}
			
		}else {//2.������������
			var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
			alts = alts.substring(0, alts.length-1);
			var inss = alts.split(",");
			if(inss.length != 1){
				alert("�������ɵ��ݣ�ֻ��ѡ��һ��");
				return;
			}
			
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/newrkd/inbound_search_part.jsp?warehouseid="+warehouseid+"&jobtype="+alts, "", 
				"dialogWidth=1100px;dialogHeight=500px;scroll=no");
			if(result != null && result.length > 0){
				//alert(result);
				list.location.href = strUrl + "inBoundJobAction&method=ricoExecCreatePart&warehouseid="+warehouseid+"&keys="+result+"&jobtype="+alts;
			}
		}
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; �½���ⵥ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(inboundRkdQuery){%> <li><input style="width:50px" onclick="searchData();" type="button" id="btn8" value="��ѯ" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li><%}%>
		  	<%if(inboundRkdCreate){%> <li><input style="width: 88px;" onclick="Add(1);" type="button" id="btn1" value="������ⵥ" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li><%}%>
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
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;" ><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">�����</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--��ѡ��--</option></select></td>
     	  <td class="yx1" width="100px" align="right">��λ��</td>
	      <td class="xx1">
		    <input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid='+document.getElementById('warehouseid').value+'&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       			type="button" value="��" class="button_select">
          </td>
	    </tr>
	    <tr>
     	  <td class="yx1" align="right">������ڣ�</td>
	      <td class="yx">
	      	<input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  -<input name="end_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" /> 
	      </td>
	       <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	   	  <td class="yx1" align="right">������ţ�</td>
     	  <td class="xx1"><input name="lotinfo" type="text" size="15"></td>
	    </tr>
	    <tr>
	     <td class="yx1" align="right">�������ڣ�</td>
	      <td class="yx">
	      	<input name="productdate" type="text" size="13"  onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
	      </td>
     	  <td class="yx1" align="right">�Ƿ񿪵���</td>
	      <td class="yx" colspan="5">
	      	<select name="isopen" style="width:117px;">
	      		<option value="">---��ѡ��---</option>
	      		<option value="Y">�ѿ���</option>
	      		<option value="N" selected>δ����</option>
	      	</select>
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/newrkd/inbound_search_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
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
 
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'>
 	<input type="hidden" value="" id="keys">
 </FORM>
</body>
</html>
