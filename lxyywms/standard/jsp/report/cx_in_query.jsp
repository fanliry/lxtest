<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean reportInjobQuery = false; //��ѯ
	boolean reportInjobReport = false; //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("reportInjobQuery") != null){
			reportInjobQuery = true;
		}
		if(hsPopedom.get("reportInjobReport") != null){
			reportInjobReport = true;
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
		document.tempForm.action = strUrl + "CXInOutJobAction&flag=3" + condition;
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
		
		var indate_from = document.getElementById("indate_from").value;	//������ڿ�ʼ
		var indate_to = document.getElementById("indate_to").value;		//������ڽ���
		var invoicetype = document.getElementById("invoicetype").value;	//��ҵ��Դ
		
		//var owner_id = document.getElementById("owner_id").value;	//����
		//var customer_id = document.getElementById("customer_id").value;	//�ͻ�
		var package_id= document.getElementById("package_id").value;	//Ʒ��
		var tray_code = document.getElementById("tray_code").value;		//��������
		
		var boundstockid = document.getElementById("boundstockid").value;	//���ݺ�
		var intype = document.getElementById("intype").value;	    //��������
		
		
		var lotid = document.getElementById("lotid").value//����
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyId=" + cargoAlleyId
			 + "&boundstockid=" + boundstockid + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&intype=" + intype + "&invoicetype=" + invoicetype
			 + "&package_id=" + package_id + "&tray_code=" + tray_code+ "&groupinfo=" + groupinfo+ "&lotid=" + lotid;
		
		
		//��������ֵ����������ڷ�Χ�ģ���ʽ���£�2012-08-30|2012-08-31
		
		
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		
		
		list.formx1.action = "<%=request.getContextPath()%>/BMSService?actionCode=CXInOutJobAction&flag=1" + condition;
		
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//�����post�����ύ�Ĳ���������Ҫͳ�Ƶ���Ŀ���������ԵĲ�ѯ����
	function getPostCon(){
	
		//ͳ������
		var total_items = "";
		if(document.getElementById("chk_product").checked){
			total_items += ", sto.productid";
		}
		if(document.getElementById("chk_owner").checked){
			total_items += ", sto.ownerId";
		}
		var lotatt;
		for(var i=1; i<13; i++){
			lotatt = document.getElementById("chk_lotatt" + i);
			if(lotatt!=null && lotatt.checked){
				total_items += ", sto.lotatt" + i;
			}
		}

		var lotid = document.getElementById("lotid").value
		//��������ֵ����������ڷ�Χ�ģ���ʽ���£�2012-08-30|2012-08-31
		
		//����  0����ʾ���õģ�1����ʾ��ȷ���Ϳ��ѯ�� 2����ʾ����Ҫȡ��Χ��
		
		//������
		var msg = "<input type=hidden name='total_items' value='"+total_items+"'>";
					
		return msg;
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		selectType('', 'intype', 'rkdj');
		selectType('', 'invoicetype', 'zyly');		//��ҵ��Դ
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//ѡ�����κ���ʾ�����ε���ϸ����
	function viewLot(){
	
		var tableObj = document.getElementById("marginTable");
		var lotid = document.getElementById("lotid").value;
		
		//3�ǲ�ѯ����������, 4��ÿ����ʾ��td��
		createLotDetail(tableObj, lotid, 4, 4);
		
	}
    //ͳ�Ʒ�������
	var groupinfo = "", info = ""; // groupinfoΪ --customerid|ownerId,1|2,�ͻ�|����--
	function More()
	{
		var result = showModalDialog(strUrl + "CXInOutJobAction&method=ricoExecGroup&flag=1", "", "dialogWidth=500px;dialogHeight=400px");
		groupinfo = result;
		if(result != null && result.length > 0)
		{
				var aem = result.split(",");	//������ͬ����
				var bem = aem[2].split("|"); // ����������ͬ���ݻ���Ϊ��Ԫ
				info = "";
				for(var i=0; i<bem.length; i++)
				{
					info += bem[i]+"/";
				}
				p_remind.innerHTML = '&nbsp;&nbsp;&nbsp;<input onclick="More()" type="button" name="button" value="����>>" class="BUTTON_STYLE1">'+info.substring(0, info.length-1);
		}else{
				p_remind.innerHTML = '&nbsp;&nbsp;&nbsp;<input onclick="More()" type="button" name="button" value="����>>" class="BUTTON_STYLE1">&nbsp';
		}
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>��ѯͳ�� &gt;&gt; �����ˮ��ѯ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(reportInjobReport){%><li class="tubiao1"><a href="#" onclick="report()">����</a></li><%}%>
			<%if(reportInjobQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
        <tr>
	   	  <td class="yx1" align="right">ͳ�Ʒ���������</td>
	      <td class="yx" colspan="7">
	         <div id="p_remind" style="color: red; ">&nbsp;&nbsp;&nbsp;<input onclick="More()" type="button" name="button" value="����>>" class="BUTTON_STYLE1"></div>
		  </td>
 	 	</tr>
	    <tr>
	      <td class="yx1" width="100" align="right">�ֿ⣺</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">�����</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--��ѡ��--</option></select></td>
     	  <td class="yx1" width="100px" align="right">���ţ�</td>
		  <td class="yx"><input name="boundstockid" type="text" size="15"></td>
	    </tr>
	    <tr>
	      
          <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
     	  <td class="yx1" align="right">������ڣ�</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" width="100" align="right">�������ͣ�</td>
          <td class="yx"><select name="intype" id="intype"  style="width:117px;">
            <option>---��ѡ��---</option>
          </select></td>
	      <td class="yx1" align="right">��ҵ��Դ��</td>
	      <td class="xx1"><select id=invoicetype  style="width:117px;"><option value=""></option></select></td>
	    </tr>
	    <tr>
	      
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	     
	   	  <td class="yx1" align="right">���ţ�</td>
     	 <td class="yx"><input name="lotid" type="text" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/report/cx_in_query_list.jsp" 
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
