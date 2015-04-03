<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 

	HashMap hsPopedom = null;
	boolean inboundInMgrQuery = false;    //��ѯ
	boolean inboundInMgrComfirm = false;     //����
	boolean inboundInMgrCancel = false;     
	boolean inboundInMgrPrint = false;     
	boolean inboundInMgrUploading = false;     
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundInMgrQuery") != null){
			inboundInMgrQuery = true;
		}
		if(hsPopedom.get("inboundInMgrComfirm") != null){
			inboundInMgrComfirm = true;
		}
		if(hsPopedom.get("inboundInMgrCancel") != null){
			inboundInMgrCancel = true;
		}
		if(hsPopedom.get("inboundInMgrPrint") != null){
			inboundInMgrPrint = true;
		}
		if(hsPopedom.get("inboundInMgrUploading") != null){
			inboundInMgrUploading = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var customer_id = document.getElementById("customer_id").value;	//����
		var instatus = document.getElementById("instatus").value;		//����״̬
		var intype = document.getElementById("intype").value;			//�������
		var indate_from = document.getElementById("indate_from").value;	//���ڿ�ʼ
		var indate_to = document.getElementById("indate_to").value;		//���ڽ���
		//var shift_id = document.getElementById("shift_id").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		Loading();
		//"&shift_id=" + shift_id
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&owner_id=" + customer_id + "&instatus=" + instatus
			 + "&intype=" + intype + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&linerow=" + linerow;
		list.location.href = strUrl + "inBoundAction&method=ricoExecSearchRkd&flag=1" + condition;
	}
	
	//ȷ�� ��� ����
	function updateData(flag){
		
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids += check_ids[i].value + ",";
				k++;
			}
		}
		if(ids == ""){
			alert("��ѡ��һ�ŵ��ݣ�");
			return;
		}else{
			ids = ids.substring(0, ids.length-1);
		}
		
		var msg = "";
		if(flag == "1"){	//�������
		
			if(k != 1){
				alert("ֻ��ѡ��һ�ŵ��ݣ�");
			}else{
			
				msg = "��ȷ������ѡ���ݽ�����ˣ�";
				if(confirm(msg)){
				
					list.location.href = strUrl + "inBoundAction&method=ricoExecAudit&instockids=" + ids + condition;
					detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
				}
			}
		}else if(flag == "2"){	//����ȷ��
		
			if(k != 1){
				alert("ֻ��ѡ��һ�ŵ��ݣ�");
			}else{
			
				msg = "��ȷ������ѡ���ݽ���ȷ�ϣ�";
				if(confirm(msg)){
				
					list.location.href = strUrl + "inBoundAction&method=ricoExecConfirm&instockids=" + ids + condition;
					detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
				}
			}
		}else if(flag == "3"){	//��������
		
			msg = "��ȷ������ѡ���ݽ������ϣ��������Ϻ�ɶԵ��������ҵ�������ɵ��ݣ�";
			if(confirm(msg)){
			
				list.location.href = strUrl + "inBoundAction&method=ricoExecCancel&instockids=" + ids + condition;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
			}
		}else if(flag == "4"){	//�����ϴ�
		
		}else{
		
			alert("û�ж���Ĳ�����");
			return;
		}
	}
	
	//������ϸ����
	function updateDetailData(){
		
		var ids = "";
		var check_ids = detail.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids += check_ids[i].value + ",";
				k++;
			}
		}
		if(ids == ""){
			alert("��ѡ��һ����ϸ��¼��");
			return;
		}else if(k == check_ids.length){
			alert("Ҫ����ȫ������ϸ���ݣ����������ϵ��ݡ���");
			return;
		}else{
			ids = ids.substring(0, ids.length-1);
		}
		
		var msg = "��ȷ������ѡ��ϸ���ݽ������ϣ��������Ϻ�ɶԵ��������ҵ�������ɵ��ݣ�";
		if(confirm(msg)){
		
			detail.location.href = strUrl + "inBoundAction&method=ricoExecCancelDetail&instockdetailids=" + ids;
		}
	}
	
	//��ӡ
	function printData(){
		
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("��ѡ��һ�ŵ��ݣ�");
			return;
		}else if(k != 1){
			alert("ֻ��ѡ��һ�ŵ��ݣ�");
			return;
		}
				
		var actionStr =strUrl + "inBoundAction&method=ricoExecReportRKD&instockid="+id;
	    		
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  			
		window.open(actionStr,'newwindow','width=1000,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
	}
	
	
	function Mupload(){
		
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		selectType('', 'intype', 'rklx');			//�������
		selectType('', 'instatus', 'rkdstatus');	//����״̬
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

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ��ⵥ����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<%if(inboundInMgrUploading){%> <li class="tubiao8" style="width:50px;"><a href="#" onclick="Mupload();">�ϴ�</a></li><%}%>
		  	<%if(inboundInMgrPrint){%> <li class="tubiao8" style="width:80px;"><a href="#" onclick="printData();">���ݴ�ӡ</a></li><%}%>
		  	<!--<li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(4);">�����ϴ�/a></li>-->
		  	<!--<li class="tubiao3" style="width:80px;"><a href="#" onclick="updateDetailData();">��ϸ����</a></li>-->
		  	<!-- <li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(1);">�������</a></li> -->
		  	<%if(inboundInMgrCancel){%> <li class="tubiao3" style="width:80px;"><a href="#" onclick="updateData(3);">��������</a></li><%}%>
		  	<%if(inboundInMgrComfirm){%> <li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(2);">����ȷ��</a></li><%}%>
			<%if(inboundInMgrQuery){%> <li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">�ֿ⣺</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:110px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" style="width:110px;"><option value=""></option></select></td>
	      <td class="yx1" align="right" width="100px">������</td>
	      <td class="xx1">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	    </tr>
	    <tr>
	      <td class="y1" width="100" align="right">����״̬��</td>
	      <td class="x"><select id=instatus style="width:110px;"><option value=""></option></select></td>
	      <td class="y1" width="100px" align="right">������ͣ�</td>
	      <td class="x"><select id="intype" style="width:110px;"><option value=""></option></select></td>
	      <td class="y1" width="100px" align="right">���ڣ�</td>
	      <td >
	      	<input id="indate_from" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> -
	      	<input id="indate_to" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
<!-- 		  <tr>
            <td valign="bottom" class="title" height="18px">��ⵥ��Ϣ</td>
          </tr> -->
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">��ⵥ��ϸ��Ϣ</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp"></iframe>
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
 
</body>
</html>