<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inboundJobAdd = false;    
	boolean inboundJobMainten = false;    
	boolean inboundJobQuery = false;
	boolean inboundJobCancel = false;     
	boolean inboundJobFinish = false; 
	boolean inboundJobComfirm = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundJobAdd") != null){
			inboundJobAdd = true;
		}
		if(hsPopedom.get("inboundJobMainten") != null){
			inboundJobMainten = true;
		}
		if(hsPopedom.get("inboundJobQuery") != null){
			inboundJobQuery = true;
		}
		if(hsPopedom.get("inboundJobCancel") != null){
			inboundJobCancel = true;
		}
		if(hsPopedom.get("inboundJobFinish") != null){
			inboundJobFinish = true;
		}
		if(hsPopedom.get("inboundJobComfirm") != null){
			inboundJobComfirm = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var searchCondition = null;
	
	/*������ť*/
	function LockButton(){
		document.getElementById("btn1").disabled = true;
		document.getElementById("btn2").disabled = true;
		//document.getElementById("btn3").disabled = true;
		document.getElementById("btn4").disabled = true;
		document.getElementById("btn5").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		document.getElementById("btn1").disabled = false;
		document.getElementById("btn2").disabled = false;
		//document.getElementById("btn3").disabled = false;
		document.getElementById("btn4").disabled = false;
		document.getElementById("btn5").disabled = false;
	}
	
	function Mupload(){
		
	}
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var Virtualwhid = document.getElementById("Virtualwhid").value;		//�߼�����id
		var indate = document.getElementById("indate").value;			//��ҵ����
		var jobid = document.getElementById("jobid").value;				//��ҵ��
		var instockid = document.getElementById("instockid").value;	    //��ⵥ��
		var status = document.getElementById("status").value;			//��ҵ״̬
		var traycode = document.getElementById("traycode").value;		//��������
		var isback = document.getElementById("isback").value;			//�Ƿ����
		var productid = document.getElementById("package_id").value;	//Ʒ��
		var lotinfo = document.getElementById("lotinfo").value;		    //������Ϣ
		var taskid = document.getElementById("taskid").value;		    //��������ID
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		Loading();
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&indate=" + indate + "&jobid=" + jobid 
			 + "&instockid=" + instockid + "&status=" + status + "&traycode=" + traycode + "&isback=" + isback 
			 + "&productid=" + productid + "&lotinfo=" + lotinfo + "&taskid=" + taskid + "&Virtualwhid=" + Virtualwhid;
		searchCondition = condition;
		list.location.href = strUrl + "inBoundJobAction&flag=1" + condition;
		
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
	}
	
	//1.�ֶ���� 2.����
	function updateData(flag){
		LockButton();
	    var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var indate = document.getElementById("indate").value;			//��ҵ����
		var jobid = document.getElementById("jobid").value;				//��ҵ��
		var instockid = document.getElementById("instockid").value;	    //��ⵥ��
		var status = document.getElementById("status").value;			//��ҵ״̬
		var traycode = document.getElementById("traycode").value;		//��������
		var isback = document.getElementById("isback").value;			//�Ƿ����
		var productid = document.getElementById("package_id").value;	//Ʒ��
		var lotinfo = document.getElementById("lotinfo").value;		    //������Ϣ
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&indate=" + indate + "&jobid=" + jobid 
			 + "&instockid=" + instockid + "&status=" + status + "&traycode=" + traycode + "&isback=" + isback 
			 + "&productid=" + productid + "&lotinfo=" + lotinfo;
		
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		var onetraycodels = list.document.getElementsByName("traycode");
		var onetraycode = "";
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				onetraycode = onetraycodels[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("��ѡ��һ����ҵ��");
			UnLockButton();
			return;
		}else if(k != 1){
		    alert("ֻ��ѡ��һ����ҵ��");
		    UnLockButton();
			return;
		}
		if(flag == 4){	//�ֶ����
		
		    var msg;
			DWREngine.setAsync(false);
			judgmentTool.finishJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			list.location.href = strUrl + "inBoundJobAction&method=ricoExecFinish&jobids=" + id+condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
			
		}else if(flag == 5){ //��ҵ��ʼ��
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.initializeJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			
			list.location.href = strUrl + "inBoundJobAction&method=ricoExecinitialize&jobids=" + id+condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}else if(flag == 6){ //�ƿ⵽�ݴ�����ֻ����ⵥδִ����ҵ��
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.WaitJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			list.location.href = strUrl + "inBoundJobAction&flag=3&jobid=" + id + condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}else if(flag == 7){ //������ҵ
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.cancelJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			
			list.location.href = strUrl + "inBoundJobAction&method=ricoExecCancel&jobids=" + id+condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}else if(flag == 8){ //���ά��
			if(onetraycode != null && onetraycode.length > 0)
			{
				var returnValue = window.showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=InjobSafeguardAction&flag=1&isWho=pc&jobid="+id+"&traycode="+onetraycode,"","dialogWidth=800px;dialogHeight=600px;");
				if(returnValue != null){
					list.location.href = strUrl + returnValue;
				}else{
					UnLockButton();
				}
				//list.location.href = strUrl + "inBoundJobAction&flag=1" + searchCondition;
			}
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}
	}
	
	//�������
	function rkadd(){
		var whid = document.getElementById("warehouseid").value;	//�ֿ�
		var returnValue = window.showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/job/injob_rkadd.jsp?whid="+whid,"","dialogWidth=800px;dialogHeight=600px;");
		if(returnValue != null){
			list.location.href = strUrl + returnValue;
		}else{
			UnLockButton();
		}
		
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "Virtualwhid", warehouseid, "3");
		
		selectType('2', 'status', 'zyzt');			//��ҵ״̬
		DWREngine.setAsync(true);
		searchData();
		
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ��ҵ����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(inboundJobComfirm){%><li class="tubiao4" ><input onclick="updateData(6)" type="button" id="btn5" value="�ƿ⵽�ݴ�" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 85px;"></li><%}%>
		  	<%if(inboundJobFinish){%><li class="tubiao2" ><input onclick="updateData(4);" type="button" id="btn4" value="�ֶ����" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 72px;"></li><%}%>
		    <!--<%if(inboundJobFinish){%><li class="tubiao3" ><input onclick="updateData(5)" type="button" id="btn3" value="�����ҵ��ʼ��" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 110px;"></li><%}%>-->
		  	<%if(inboundJobCancel){%><li class="tubiao2" ><input onclick="updateData(7)" type="button" id="btn2" value="������ҵ" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 75px;"></li><%}%>
			<%if(inboundJobQuery){%><li class="tubiao1" ><input onclick="searchData();" type="button" id="btn1" value="��ѯ" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'" style="width: 50px;"></li><%}%>
		  	<%if(inboundJobMainten){%><li class="tubiao2" ><input onclick="updateData(8);" type="button" id="btn4" value="���ά��" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 72px;"></li><%}%>
		  	<%if(inboundJobAdd){%><li class="tubiao2" ><input onclick="rkadd();" type="button" id="btn4" value="�������" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 72px;"></li><%}%>
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
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">������</td>
     	  <td class="yx"><select id="whAreaId" style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" align="right">�߼�������</td>
     	  	<td class="yx">
     	  	<select id="Virtualwhid" style="width:117px;"><option value=""></option></select>
     	  	</td>
	      <td class="yx1" align="right">��ҵ�ţ�</td>
	      <td class="xx1"><input type="text" id="jobid" size="15" ></td>
	    </tr>
        <tr>
	      <td class="yx1" align="right">��ⵥ�ţ�</td>
	      <td class="yx"><input type="text" name="instockid"   style="height:18px;width:110px;"/></td>
	      <td class="yx1" align="right">��ҵ״̬��</td>
	      <td class="yx"><select id="status" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" align="right">�������룺</td>
	      <td class="yx"><input name="traycode" type="text" size="15"></td>
	      <td class="yx1" align="right">�Ƿ������</td>
	      <td class="xx1">
         	<select id="isback" style="width:117px;"><option value="">--��ѡ��--</option><option value="Y">����</option><option value="N">�ǻ���</option></select>
       	  </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  
            	type="button" value="��" class="button_select">
	      </td>
	      <td class="yx1" width="100" align="right">������ţ�</td>
	      <td class="yx"><input type="text" name="lotinfo" style="height:18px;width:110px;"/></td>
		  <td class="yx1" align="right">���ڣ�</td>
	      <td class="yx"><input id="indate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"></td>
	      <td class="yx1" width="100" align="right">���Ⱥţ�</td>
	      <td class="yx"><input type="text" name="taskid" style="height:18px;width:110px;"/></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
<!-- 		  <tr> -->
<!--             <td valign="bottom" class="title" height="18px">��ҵ��Ϣ</td> -->
<!--           </tr> -->
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">
          	  ��ҵ��<span id="jobidzo" style="color: red;font-weight:bold;"></span>����ϸ��Ϣ
            </td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp"></iframe>
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