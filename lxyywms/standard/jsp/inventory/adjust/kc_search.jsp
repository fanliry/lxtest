<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<%
    String strTime =  StrTools.getCurrDateTime(8);
    String strWarehouseID = request.getParameter("wharehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
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
		document.tempForm.target=name;
		document.tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
		document.tempForm.fireEvent("onsubmit");	
		document.tempForm.submit();	
	}
	function openWindow(name)  
    {  
      window.open('about:blank',name,'height=800, width=1200, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
    }
	
	//��ѯ
	function searchData(){
	
		//var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		//var cargoAlleyId = document.getElementById("cargoAlleyId").value;//���
		var platoon = document.getElementById("platoon").value;			//��
		var column = document.getElementById("column").value;			//��
		var floor = document.getElementById("floor").value;				//��
		
		var lotnumber = document.getElementById("lotnumber").value;	//����
		var package_id= document.getElementById("package_id").value;	//Ʒ��
		var tray_code = document.getElementById("tray_code").value;		//��������
		var requestid = document.getElementById("requestid").value;		//���뵥
		
		var productstatus = document.getElementById("productstatus").value;	//��Ʒ״̬
		var csstatus = document.getElementById("csstatus").value;		//���״̬
		var indate_from = document.getElementById("indate_from").value;	//��������
		var indate_to = document.getElementById("indate_to").value;		//��������
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
		
		condition = "&warehouseid=" +"<%=strWarehouseID%>" + "&whAreaId=" +whAreaId + "&platoon=" +platoon + 
			"&column=" +column + "&floor=" +floor + "&lotnumber=" + lotnumber + "&package_id=" +package_id + "&tray_code=" +tray_code+ "&requestid=" +requestid+ "&productstatus=" +productstatus+ "&status=" +csstatus+ "&indate_from=" +indate_from+ "&indate_to=" +indate_to;
			
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		//list.location.href = strUrl + "inventoryAction&flag=5" + condition;
		list.formx1.action = strUrl + "inventoryAction&flag=5" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	function savaDate(){
		 var id = "" ;
		 var k=0;
         var check_ids = list.document.getElementsByName("checkbox_id" );
         for( var i=0; i<check_ids.length; i++){
             if(check_ids[i].checked){
                 id = check_ids[i].value;
                 k++;
                  break;
            }
             
        }
         if( k == 0 ){
            alert( "��ѡ��һ����¼��" );
             return;
        } else if(k != 1){
            alert( "ֻ��ѡ��һ����¼��" );
             return;
        }
			window.returnValue=id;
			window.close();		
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//��Ʒ״̬
		selectType('', 'productstatus', 'wpzt');
		//���״̬
		selectType('2', 'csstatus', 'hwzt');
		
		//����
		//var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", "<%=strWarehouseID%>", "1");
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
    <tr> <td height="25"><div class="font_001F56_bold_12">&nbsp;��ǰλ�ã������� -&gt; �������� -&gt; ��������ϸ</div></td></tr>
	  <tr>  <td>
      <div class="font_001F56_12" align="right">
	      <input type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search" onclick="searchData()">&nbsp;
          <input type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;ȷ��" class="button_search" onclick="savaDate()">&nbsp;
	  </div>
    </td></tr>
<tr><td height="5"></td></tr>
    <tr><td>
     <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
          <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" style="width:117px;"><option value=""></option></select></td>
          <td class="yx1" width="100" align="right">��λ��</td>
	   	  <td class="yx">
	   	    <input type="text" id="platoon" size="2">��
	   	    <input type="text" id="column" size="2">��
	   	    <input type="text" id="floor" size="2">��
	   	  </td>
	   	  <td class="yx1" align="right">�������룺</td>
     	  <td class="xx1"><input name="tray_code" type="text" size="15"></td>	  
	    </tr>
	    <tr>
	      <td class="yx1" align="right">���ţ�</td>
	      <td class="yx">
	      	<input type="text" name="lotnumber"  size="15">
	      </td>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
         <td class="yx1" align="right">���뵥��</td>
	      <td class="xx1">
	      	<input type="text" name="requestid"  size="15">
	      </td>
	    </tr>
	    <tr>
	      <td class="y1" align="right">��λ״̬��</td>
	      <td class="x">
	      	<select id="csstatus" style="width:110px;">
                <option value="">--��ѡ��--</option>
              </select>
	      </td>
	      <td class="y1" align="right">��Ʒ״̬��</td>
          <td class="x">
              <select id="productstatus" style="width:110px;">
                <option value="">--��ѡ��--</option>
              </select>
           </td>
	     <td class="y1" align="right">������ڣ�</td>
	     <td colspan = "3">
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_search_list.jsp" 
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
