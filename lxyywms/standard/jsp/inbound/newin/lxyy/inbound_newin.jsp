<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inbound_scnewrkdquery = false;    //��ѯ
	boolean inbound_scnewrkdsave = false;     //������ⵥ��
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inbound_scnewrkdquery") != null){
			inbound_scnewrkdquery = true;
		}
		if(hsPopedom.get("inbound_scnewrkdsave") != null){
			inbound_scnewrkdsave = true;
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
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//��ѯ
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var lotinfo = document.getElementById("lotinfo").value;		//����
		var starttime = document.getElementById("starttime").value;	//��ʼ����
		var endtime = document.getElementById("endtime").value;			//��������
		var instockid = document.getElementById("instockid").value;	//���뵥��
		var isform = document.getElementById("is_form").value;	//�󶨼�¼״̬���Ƿ�������ⵥ��
		var invoicetype = document.getElementById("invoicetype").value;	//�������
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		Loading();
		condition = "&warehouseid=" +��warehouseid + "&lotinfo=" + lotinfo + "&starttime=" + starttime + "&endtime=" +��endtime
			 + "&instockid=" + instockid + "&isform=" + isform + "&invoicetype=" + invoicetype;
		list.location.href = strUrl + "InBoundAction&flag=1" + condition;
	}
	
	
	//������ⵥ
	function saveData(){
	    var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var lotinfo = document.getElementById("lotinfo").value;		//����
		var starttime = document.getElementById("starttime").value;	//��ʼ����
		var endtime = document.getElementById("endtime").value;			//��������
		var instockid = document.getElementById("instockid").value;	//���뵥��
		var isform = document.getElementById("is_form").value;	//�󶨼�¼״̬���Ƿ�������ⵥ��
		var invoicetype = document.getElementById("invoicetype").value;	//�������
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("����ѡ�������ֿ⡿!");
			return;
		}
		
		var keys = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				keys += check_ids[i].value + ",";
			}
		}
		if(keys == ""){
			alert("������ѡ��һ����¼��");
			return;
		}
		condition =  keys.substring(0, keys.length-1)+"&warehouseid=" +��warehouseid + "&lotinfo=" + lotinfo + "&starttime=" + starttime + "&endtime=" +��endtime
			 + "&instockid=" + instockid + "&isform=" + isform + "&invoicetype=" + invoicetype;
		list.location.href = strUrl + "InBoundAction&method=ricoExecCreate&condition="+condition;			
	}
	
	//ҳ���¼
	function OnLoad(){
		
		//�ֿ�
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		selectType('9', 'invoicetype', 'rkdj');
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ���������� &gt;&gt; �½���ⵥ</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<%if(inbound_scnewrkdsave){%><li class="tubiao4" style="width:100px;"><a href="#" onclick="saveData();">������ⵥ��</a></li><%}%>
			<%if(inbound_scnewrkdquery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
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
	      <td class="yx1" width="100" align="right">���ţ�</td>
	      <td class="yx"><input type="text" name="lotinfo"   style="height:18px;width:110px;"/></td>
	      <td class="yx1" width="100px" align="right">���ڣ�</td>
	      <td class="xx1">
	        <input id="starttime" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> -
	      	<input id="endtime" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	    </tr>
	    <tr>
	      <td class="y1" align="right">���뵥�ţ�</td>
	      <td class="x">
	      	<input type="text" name="instockid"   style="height:18px;width:110px;"/>
	      </td>
	      <td class="y1" align="right">�Ƿ����ɵ��ݣ�</td>
          <td class="x"><select name="is_form" style="width:110px;"><option value="">--��ѡ��--</option><option value="2">��</option><option value="1" selected>��</option></select></td>
	      <td class="y1" align="right">������ͣ�</td>
          <td class="x">
              <select id="invoicetype" style="width:110px;" disabled>
                <option value="">--��ѡ��--</option>
              </select>
           </td>
	    </tr>
      </table> 
		
    </td></tr>
    <tr>
      <td valign="top" height="100%">
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
<!-- 		  <tr> -->
<!--             <td valign="bottom" class="title" height="10px">>>>�󶨼�¼��Ϣ��</td> -->
<!--           </tr> -->
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/newin/lxyy/inbound_newin_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=10&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
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