<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean wpCheckqueryQuery = false; //��ѯ
	boolean wpCheckqueryReport = false; //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("wpCheckqueryQuery") != null){
			wpCheckqueryQuery = true;
		}
		if(hsPopedom.get("wpCheckqueryReport") != null){
			wpCheckqueryReport = true;
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
		
		document.tempForm.action = strUrl + "inventoryAction&flag=21" + condition;
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
		var Virtualwhid = document.getElementById("Virtualwhid").value;		//�߼�����id
		
		var lotinfo = document.getElementById("lotinfo").value;			//�������
		var package_id= document.getElementById("package_id").value;	//Ʒ��
		
		var productstatus = document.getElementById("productstatus").value;	//��Ʒ״̬
		var indate_from = document.getElementById("indate_from").value;	//��������
		var indate_to = document.getElementById("indate_to").value;		//��������
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&Virtualwhid=" + Virtualwhid + "&lotinfo=" + lotinfo + "&productid=" + package_id + "&productstatus=" +productstatus+ "&indate_from=" +indate_from+ "&indate_to=" +indate_to;
			
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;

		list.formx1.action = strUrl + "inventoryAction&flag=12" + condition;
		
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
		//selectType("", "producttype", "producttype");
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "Virtualwhid", warehouseid, "3");
		
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ��Ʒ��ѯ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(wpCheckqueryReport){%><li class="tubiao2"><a href="#" onClick="report()">����</a></li><%}%>
			<%if(wpCheckqueryQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
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
     	  <td class="yx"><select id="whAreaId" onChange="" style="width:117px;"><option value=""></option></select></td>
	    	<td class="yx1" align="right">�߼�������</td>
     	  	<td class="yx">
     	  	<select id="Virtualwhid" style="width:117px;"><option value=""></option></select>
     	  	</td>
     	  <td class="yx1" align="right">������ţ�</td>
	      <td class="yx">
	      	<input type="text" name="lotinfo"  size="15">
	      </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	      <td class="yx1" align="right">��Ʒ״̬��</td>
          <td class="yx">
              <select id="productstatus" style="width:117px;">
                <option value="">--��ѡ��--</option>
              </select>
           </td>
	     <td class="yx1" align="right">�������ڣ�</td>
	     <td class="yx">
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/search/wp_search_list.jsp" 
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
