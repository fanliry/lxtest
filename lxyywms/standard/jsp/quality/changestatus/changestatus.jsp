<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean quality_query = false; //��ѯ
	boolean quality_pass = false; //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("quality_query") != null){
			quality_query = true;
		}
		if(hsPopedom.get("quality_pass") != null){
			quality_pass = true;
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
	function openWindow(name)  
    {  
      window.open('about:blank',name,'height=800, width=1200, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
    }
	
	//��ѯ
	function searchData(){

		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whareaid = document.getElementById("whareaid").value;		//����
		var lotnumber = document.getElementById("lotnumber").value;     //����
		var requestid = document.getElementById("requestid").value;	    //���뵥id
		var productid = document.getElementById("package_id").value;	    //��Ʒid
		var productstatus = document.getElementById("productstatus").value;		//��Ʒ״̬
		var productCode = document.getElementById("productCode").value;		//��Ʒ����
		
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" +��warehouseid + "&whareaid=" +��whareaid + "&lotnumber=" +��lotnumber + "&requestid=" +��requestid + 
			"&productid=" +��productid + "&productstatus=" +��productstatus+ "&productCode=" +��productCode ;
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		list.formx1.action = strUrl + "inventoryQualityAction&flag=1" + condition;
		list.document.formx1.submit();	
		Loading();	
	}
	
	function openShow(){
		var check_ids = list.document.getElementsByName("check_id");
		var inventoryids="";
		var count = 0;
	    for(var i=0; i<check_ids.length; i++){
		   if(check_ids[i].checked){
				inventoryids += check_ids[i].value;
				count++;
		   }
	    }
	    if (count == 0) {
	    	  alert("��ѡ����Ҫ�����ļ�¼��");
	    	  return;
	    }
	    if (count >1) {
	    	  alert("һ��ֻ��ѡ��һ����¼��");
	    	  return;
	    }
	    var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/quality/changestatus/change.jsp',inventoryids,'dialogWidth=400px;dialogHeight=200px');	
	    
	    if(result != null && result.length > 1)
		{
			list.location.href=result;
		}
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		selectType('', 'wpzt', 'wpzt');
		DWREngine.setAsync(true);
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>�ʼ���� &gt;&gt; ״̬ת��</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(quality_pass){%><li class="tubiao2"><a href="#" onclick="openShow()">����</a></li><%}%>
			<%if(quality_query){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
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
     	  <td class="yx" ><select id="whareaid" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100" align="right">��Ʒ���룺</td>
	   	  <td class="yx">
	   	    <input type="text" name="productCode" size="20">
	   	  </td>
	   	   <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	      </td>
	    </tr>
	    <tr>
	      <td class="y1" width="100" align="right">���ţ�</td>
	   	  <td class="x">
	   	    <input type="text" name="lotnumber" size="20">
	   	  </td>
	      <td class="y1" align="right">������뵥��</td>
 	      <td class="x"><input name="requestid" type="text" size="15"></td>
	     
     	  <td class="y1" align="right"  width="100">��Ʒ״̬��</td>
     	  <td class="" colspan="3"><select name="productstatus" id="wpzt"  style="width:117px;">
            <option>---��ѡ��---</option>
          </select></td>
          
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/quality/changestatus/changestatus_list.jsp" 
		  		width="100%" height="100%" scrolling="yes" frameborder="0"></iframe>
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
