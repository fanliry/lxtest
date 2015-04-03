<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean outboundJobmgrQuery = false; 
	boolean outboundJobmgrCancel = false; 
	boolean outboundJobmgrComfirm = false; 
	boolean outboundJobmgrFinish = false; 
	boolean outboundJobmgrSD = false; 
	boolean outboundJobmgr = false; 
	boolean outboundJobmgrUploading = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("outboundJobmgrQuery") != null){
			outboundJobmgrQuery = true;
		}
		if(hsPopedom.get("outboundJobmgrCancel") != null){
			outboundJobmgrCancel = true;
		}
		if(hsPopedom.get("outboundJobmgrComfirm") != null){
			outboundJobmgrComfirm = true;
		}
		if(hsPopedom.get("outboundJobmgrFinish") != null){
			outboundJobmgrFinish = true;
		}
		if(hsPopedom.get("outboundJobmgrSD") != null){
			outboundJobmgrSD = true;
		}
		if(hsPopedom.get("outboundJobmgr") != null){
			outboundJobmgr = true;
		}
		if(hsPopedom.get("outboundJobmgrUploading") != null){
			outboundJobmgrUploading = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var Virtualwhid = document.getElementById("Virtualwhid").value;		//�߼�����id
		var productid = document.getElementById("package_id").value;	//Ʒ��
		var indate = document.getElementById("indate").value;			//��ҵ����
		var jobid = document.getElementById("jobid").value;				//��ҵ��
		var status = document.getElementById("status").value;			//��ҵ״̬
		var taskid = document.getElementById("taskid").value;		    //��������ID
		var traycode = document.getElementById("traycode").value;		//��������
		var linerow = page.document.getElementById("lineviewrow").value;//ÿҳ��ʾ����	
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		Loading();
		//"&shift_id=" + shift_id
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&productid=" + productid 
			 + "&indate=" + indate + "&jobid=" + jobid + "&status=" + status + "&traycode=" + traycode
			 + "&linerow=" + linerow + "&Virtualwhid=" + Virtualwhid + "&taskid=" + taskid;
			 
		list.location.href = strUrl + "outBoundJobAction&flag=1" + condition;
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_detail.jsp";
	}
	
	//1.�ֶ���� 2.����
	function updateData(flag){
		
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids = check_ids[i].value;
				k++;
			}
		}
		if(k == 0){
			alert("��ѡ��һ����¼��");
		}else if(k != 1){
			alert("��ѡ��һ����¼��");
		}else{
			if(flag == 4){	//�ֶ����(�����)
			    
			    var msg;
				DWREngine.setAsync(false);
				judgmentTool.isTaskJob(ids, Show);
				DWREngine.setAsync(true);
				function Show(value){
					msg = value;
				}
				if(msg != "Y"){
					alert(msg);
					return;
				}
		
				list.location.href = strUrl + "outBoundJobAction&method=ricoExecFinish&jobids=" + ids;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_detail.jsp";
			
			}else if(flag == 5){ //��ҵ����
			
				list.location.href = strUrl + "outBoundJobAction&method=ricoExecCancel&jobids=" + ids;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_detail.jsp";
			}else if(flag == 6){ //ֱ�ӳ��⣨�ݴ�����
			    var msg;
				DWREngine.setAsync(false);
				judgmentTool.isNotTaskJob(ids, Show);
				DWREngine.setAsync(true);
				function Show(value){
					msg = value;
				}
				if(msg != "Y"){
					alert(msg);
					return;
				}
				list.location.href = strUrl + "outBoundJobAction&method=ricoExecFinishToTem&jobids=" + ids;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_detail.jsp";
			}
		}
	}
	
	//������ҵ״̬
	function updateStatus(flag){
	
		var jobid = "";
		checd_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<checd_ids.length; i++){
		
			if(checd_ids[i].checked == true){
			
				jobid += checd_ids[i].value + ",";
			}
		}
		if(jobid == ""){
		
			alert("��ѡ����ҵ��¼��");
			return;
		}
		jobid = jobid.substring(0, jobid.length-1);
		
		if(flag == 1){ //��������
		
			condition = "&priority=0" + "&jobids=" + jobid ;
		}else if(flag == 2){	//�趨���ȼ�
		
			var priority = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_priority.jsp", "", 
				"dialogWidth=400px; dialogHeight=200px; scroll=no;");
			if(priority == null || priority.length < 1){
				return;
				
			}else{
				condition = "&priority=" + priority + "&jobids=" + jobid ;
			}
			
		}
		list.location.href = strUrl + "outBoundJobAction&method=ricoExecUpdPriority" + condition;
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_detail.jsp";
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
		selectAreaList("", "Virtualwhid", warehouseid, "3");
		
		selectType('1', 'status', 'zyzt');			//��ҵ״̬
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������� &gt;&gt; ��ҵ����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(outboundJobmgr){%><li class="tubiao2" style="width:85px"><a href="#" onclick="updateStatus(1)">��������</a></li><%}%>
		  	<%if(outboundJobmgrSD){%><li class="tubiao2" style="width:93px"><a href="#" onclick="updateStatus(2)">�趨���ȼ�</a></li><%}%>
		  	<%if(outboundJobmgrFinish){%><li class="tubiao2" style="width:130px"><a href="#" onclick="updateData(4)">�ֶ����(�����)</a></li><%}%>
		  	<%if(outboundJobmgrComfirm){%><li class="tubiao2" style="width:120px"><a href="#" onclick="updateData(6)">ֱ�ӳ���(�ݴ�)</a></li><%}%>
		  	<%if(outboundJobmgrCancel){%><li class="tubiao3" style="width:85px"><a href="#" onclick="updateData(5)">��ҵ����</a></li><%}%>
			<%if(outboundJobmgrQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
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
     	  	<select id="Virtualwhid" style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name"  style="width:117px;" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  
            	type="button" value="��" class="button_select">
	      </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">���ڣ�</td>
	      <td class="yx"><input id="indate" type="text" style="width:117px;" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"></td>
	      <td class="yx1" align="right">��ҵ�ţ�</td>
	      <td class="yx"><input type="text" id="jobid" style="width:117px;" ></td>
	      <td class="yx1" align="right">��ҵ״̬��</td>
	      <td class="yx"><select id="status" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" align="right">�������룺</td>
	      <td class="yx"><input name="traycode" type="text" style="width:117px;" ></td>
	    </tr>
	    <tr>
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
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">��ҵ��<span id="jobdid" style="color: red;font-weight:bold;"></span>����ϸ��Ϣ</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/job/outbound_job_detail.jsp"></iframe>
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