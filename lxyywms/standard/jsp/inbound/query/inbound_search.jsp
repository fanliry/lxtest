<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inboundRkQuery = false;    
	boolean inboundRkReport = false;     
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundRkQuery") != null){
			inboundRkQuery = true;
		}
		if(hsPopedom.get("inboundRkReport") != null){
			inboundRkReport = true;
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
		
		var onlinetype = document.getElementById("onlinetype").value;	//���ģʽ
		var indate_from = document.getElementById("indate_from").value;	//������ڿ�ʼ
		var indate_to = document.getElementById("indate_to").value;		//������ڽ���
		var isback = document.getElementById("isback").value;			//�Ƿ����
		var invoicetype = "";//document.getElementById("invoicetype").value;	//��ҵ��Դ
		
		var instockid = document.getElementById("instockid").value;	//��ⵥ��
		var productid= document.getElementById("package_id").value;	//Ʒ��
		var traycode = document.getElementById("tray_code").value;		//��������
		var lotinfo = document.getElementById("lotinfo").value;		//����
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyId=" + cargoAlleyId + "&cargospaceid=" + cargospaceid
			 + "&onlinetype=" + onlinetype + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&isback=" + isback + "&invoicetype=" + invoicetype
			 + "&instockid=" + instockid + "&productid=" + productid + "&traycode=" + traycode + "&lotinfo=" + lotinfo;
	
		list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecSearchJob" + condition;
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
	//��ӡ
	function report(){
		if(condition == null){
			alert("���Ȳ�ѯ��");
			return;
		}
		
		document.tempForm.action = strUrl + "inBoundJobAction&method=ricoExecPrint" + condition;
		
		document.tempForm.submit();	
	}
	
	function Mupload(){
		
	}
	
	//1.���ɵ��� 2.������������
	function Add(type){
		if(type == 1){
			var alts = "";
			var keys = "";
			var check_ids = list.document.getElementsByName("check_id");
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked){
					alts += check_ids[i].alt + ",";
					keys += check_ids[i].value + ",";
				}
			}
			if(alts == ""){
				alert("������ѡ��һ����¼��");
				return;
			}
						
			condition =  alts.substring(0, alts.length-1);
			var tmp = "";
			var flagbl= "";
			var theArray = condition.split(",");
			if(theArray.length>1){
				for(var j=0; j < theArray.length; j++){
					var theArrayson = theArray[j].split("|");
					if(j == 0){
						tmp = theArrayson[1];
					}
					if(tmp != theArrayson[1]){
						flagbl = theArrayson[0];
						break;
					}
				}
			}
			
			if(flagbl.length>0){
				alert("��ѡ��["+flagbl+"]���������У����Ͳ���ͬ����������һ����ⵥ!");
				return;
			}
			
			if(confirm("��ȷ������ѡ�����ɵ��ݣ�")){
				//alert(keys);
				list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecCreate&keys=" + keys;
				list.document.formx1.submit();
			}
			else{
			}
		}
<%-- 		else {
			var result = showModalDialog("<%=request.getContextPath()%>/jsp/report/shift_in_part_add.jsp", "", 
				"dialogWidth=1100px;dialogHeight=500px;scroll=no");
			if(result != null && result.length > 0){
				list.location.href = ac + "ShiftInStaAction&method=add&flag=4&keys=" + result;
			}
		} --%>
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ����ѯ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(inboundRkQuery){%> <li><input style="width:50px" onclick="searchData();" type="button" id="btn8" value="��ѯ" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li><%}%>
		  	<!-- <li><input style="width: 135px;" onclick="Add(2);" type="button" id="btn1" value="��������������ⵥ" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li>
		  	<li><input style="width: 88px;" onclick="Add(1);" type="button" id="btn1" value="������ⵥ" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li> -->
		  	<%if(inboundRkReport){%><li><input style="width:50px;" onclick="report();" type="button" id="btn1" value="����" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li><%}%>
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
     	  <td class="yx1" width="100px" align="right">��λ��</td>
	      <td class="xx1">
		    <input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid='+document.getElementById('warehouseid').value+'&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       			type="button" value="��" class="button_select">
          </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">���ģʽ��</td>
     	  <td class="yx">
     	  	<select id="onlinetype" style="width:117px;"><option value="">--��ѡ��--</option><option value="1">����</option><option value="2">�ѻ�</option></select>
     	  </td>
     	  <td class="yx1" align="right">������ڣ�</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" align="right">�Ƿ������</td>
	      <td class="yx">
         	<select id="isback" style="width:117px;"><option value="">--��ѡ��--</option><option value="Y">����</option><option value="N">�ǻ���</option></select>
       	  </td>
	       <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">��ⵥ�ţ�</td>
	      <td class="yx"><input type="text" name="instockid"   style="height:18px;width:110px;"/></td>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	      <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	   	  <td class="yx1" align="right">���ţ�</td>
     	  <td class="yx"><input name="lotinfo" type="text" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/query/inbound_search_list.jsp" 
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
