<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String warehouseid = (String)request.getParameter("warehouseid");
	String jobtype = (String)request.getParameter("jobtype");

	HashMap hsPopedom = null;
	boolean inbound_rkquery = false;    //��ѯ
	boolean inbound_rkreport = false;     //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inbound_rkquery") != null){
			inbound_rkquery = true;
		}
		if(hsPopedom.get("inbound_rkreport") != null){
			inbound_rkreport = true;
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
		
		var warehouseid = "<%=warehouseid %>";
		var productid= document.getElementById("package_id").value;	//Ʒ��
		var indate = document.getElementById("indate").value;		//�������
		var intype = document.getElementById("intype").value;		//�������
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		if(intype == null || intype.length < 1){
			alert("����ѡ��������͡�!");
			return;
		}else if(intype == "hl"){
			alert("������ҵ����������ⵥ!");
			return;
		}
		condition = "&warehouseid=" + warehouseid + "&indate=" + indate 
					+ "&productid=" + productid+ "&intype=" + intype;
		
		list.formx1.action = strUrl + "inBoundJobAction&flag=4" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//ҳ���¼
	function OnLoad(){
		selectType('<%=jobtype %>', 'intype', 'rklx');			//�������
	}

	
	//1.���ɵ��� 2.������������
	function Ok(){
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("������ѡ��һ����¼��");
			return;
		}
		else if(confirm("��ȷ������ѡ��¼���ɵ��ݣ�")){
			window.returnValue = ids.substring(0, ids.length-1);
	    	window.close();
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ����������ⵥ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li><input style="width: 50px" onclick="searchData();" type="button" id="btn3" value="��ѯ" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li>
		  	<li><input style="width: 50px;" onclick="Ok();" type="button" id="btn2" value="ȷ��" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>

     	  <td class="yx1" align="right">������ڣ�</td>
	      <td class="yx">
	      	<input id="indate" type="text" value="" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="TD_ADD" align="right">������ͣ�</td>
	      	<td class="TD_ADD"><select id="intype" style="width:163px;" ><option value=""></option></select></td>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/newrkd/inbound_search_part_list.jsp" 
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
 	<input type="hidden" value="">
 </FORM>
</body>
</html>
