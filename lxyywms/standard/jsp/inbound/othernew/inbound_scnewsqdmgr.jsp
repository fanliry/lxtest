<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inbound_otsqdmgrclose = false;    //�ر����뵥
	boolean inbound_otsqdmgrinvalid = false;     //����
	boolean inbound_otsqdmgrprint = false; //��ӡ���뵥
	boolean inbound_otsqdmgrprintbind = false;     //��ӡ�󶨼�¼
	boolean inbound_otsqdmgrquery = false; //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inbound_otsqdmgrclose") != null){
			inbound_otsqdmgrclose = true;
		}
		if(hsPopedom.get("inbound_otsqdmgrinvalid") != null){
			inbound_otsqdmgrinvalid = true;
		}
		if(hsPopedom.get("inbound_otsqdmgrprint") != null){
			inbound_otsqdmgrprint = true;
		}
		if(hsPopedom.get("inbound_otsqdmgrprintbind") != null){
			inbound_otsqdmgrprintbind = true;
		}
		if(hsPopedom.get("inbound_otsqdmgrquery") != null){
			inbound_otsqdmgrquery = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
	String strUserCode = (String)session.getAttribute("userCode");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>�㽭���������ֿ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
<!--����Ӧ�߶ȵ�div-->
 	var condition = null;
 	//���
 	function audit(){
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
 	    var msg;
		DWREngine.setAsync(false);
		judgmentTool.isAudit(id,'<%=strUserCode%>', Show);
		DWREngine.setAsync(true);
		function Show(value){
			msg = value;
		}
		if(msg != "Y"){
			alert(msg);
			return;
		}
		
 	    var warehouseid = document.getElementById("warehouseid").value;//�ֿ�ID
		var instatus = document.getElementById("instatus").value;//����״̬
		var productName = document.getElementById("productName").value;//��Ʒ����
		var lotinfo = document.getElementById("lotinfo").value;//����
		var invoicetype = document.getElementById("invoicetype").value;//�������
		
		var starttime = document.getElementById("start_time").value;//��ʼ����
		var endtime = document.getElementById("end_time").value;//��������
 		var condition = "&warehouseid=" + warehouseid + "&instockid=" + id + "&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo+ "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=3&invoiceid="+id+condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/scnew/inbound_scnewsqdmgrde_list.jsp";
 	}
 	//ȡ�����
 	function cancelaudit(){
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
		
 	    var msg;
		DWREngine.setAsync(false);
		judgmentTool.isCancelAudit(id, Show);
		DWREngine.setAsync(true);
		function Show(value){
			msg = value;
		}
		if(msg != "Y"){
			alert(msg);
			return;
		}
		
 	    var warehouseid = document.getElementById("warehouseid").value;//�ֿ�ID
		var instatus = document.getElementById("instatus").value;//����״̬
		var productName = document.getElementById("productName").value;//��Ʒ����
		var lotinfo = document.getElementById("lotinfo").value;//����
		var invoicetype = document.getElementById("invoicetype").value;//�������
		
		var starttime = document.getElementById("start_time").value;//��ʼ����
		var endtime = document.getElementById("end_time").value;//��������
 		var condition = "&warehouseid=" + warehouseid + "&instockid=" + id +"&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo+ "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=4&invoiceid="+id+condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/scnew/inbound_scnewsqdmgrde_list.jsp";
 	}
    //�������뵥
    function deleteinvoice(){
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
 	    var msg;
		DWREngine.setAsync(false);
		judgmentTool.isDeleteAudit(id, Show);
		DWREngine.setAsync(true);
		function Show(value){
			msg = value;
		}
		if(msg != "Y"){
			alert(msg);
			return;
		}
		
 	    var warehouseid = document.getElementById("warehouseid").value;//�ֿ�ID
		var instatus = document.getElementById("instatus").value;//����״̬
		var productName = document.getElementById("productName").value;//��Ʒ����
		var lotinfo = document.getElementById("lotinfo").value;//����
		var invoicetype = document.getElementById("invoicetype").value;//�������
		
		var starttime = document.getElementById("start_time").value;//��ʼ����
		var endtime = document.getElementById("end_time").value;//��������
 		var condition = "&warehouseid=" + warehouseid + "&instockid=" + id +"&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo+ "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=5&invoiceid="+id+condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/scnew/inbound_scnewsqdmgrde_list.jsp";
 	}
 	//�ر����뵥
 	function closeinvoice(){
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
 	    var msg;
		DWREngine.setAsync(false);
		judgmentTool.isColseAudit(id, Show);
		DWREngine.setAsync(true);
		function Show(value){
			msg = value;
		}
		if(msg != "Y"){
			alert(msg);
			return;
		}
		
 	    var warehouseid = document.getElementById("warehouseid").value;//�ֿ�ID
		var instatus = document.getElementById("instatus").value;//����״̬
		var productName = document.getElementById("productName").value;//��Ʒ����
		var lotinfo = document.getElementById("lotinfo").value;//����
		var invoicetype = document.getElementById("invoicetype").value;//�������
		
		var starttime = document.getElementById("start_time").value;//��ʼ����
		var endtime = document.getElementById("end_time").value;//��������
 		var condition = "&warehouseid=" + warehouseid + "&instockid=" + id +"&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo+ "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=6&invoiceid="+id+condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/scnew/inbound_scnewsqdmgrde_list.jsp";
 	}
 	//��ӡ
	function printinvoice(){
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
		
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  	    window.open("<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=7&instockid="+id,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=800px;dialogHeight=600px;");
	}
	//��ӡ�󶨼�¼
	function printbindrecord(){
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
		
 	    var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);	
  	    window.open("<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=8&instockid="+id,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=800px;dialogHeight=600px;");
	}
 	
 	//��ѯ
 	function queryinvoice()
 	{
 		
 		
		var warehouseid = document.getElementById("warehouseid").value;//�ֿ�ID
		var instockid = document.getElementById("instockid").value;//���ݺ�
		var instatus = document.getElementById("instatus").value;//����״̬
		var productName = document.getElementById("productName").value;//��Ʒ����
		var lotinfo = document.getElementById("lotinfo").value;//����
		var invoicetype = document.getElementById("invoicetype").value;//�������
		
		var starttime = document.getElementById("start_time").value;//��ʼ����
		var endtime = document.getElementById("end_time").value;//��������
		
 		var condition = "&warehouseid=" + warehouseid + "&instockid=" + instockid + "&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo + "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=OtherInBoundRequestAction&flag=1"+condition;
 		Loading();
 		list.location.href = strUrl + condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/othernew/inbound_scnewsqdmgrde_list.jsp";	
 	}
 
 	function OnLoad()
	{
		selectObject('', 'warehouseid', '1');
		selectType('', 'instatus', 'rksqdstatus');
		selectTypeother('3', 'invoicetype', 'rkdj');
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
	     <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã�<span>������</span> &gt;&gt; <span>����������</span> &gt;&gt; <span>���뵥����</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	          <%if(inbound_otsqdmgrquery){%><li class="tubiao1"><a href="#" onclick="queryinvoice();">��ѯ</a></li><%}%>
	          <%if(inbound_otsqdmgrprintbind){%><li class="tubiao2" style="width:100px"><a href="#" onclick="printbindrecord();">��ӡ�󶨼�¼</a></li><%}%>
	          <%if(inbound_otsqdmgrprint){%><li class="tubiao2" style="width:100px"><a href="#" onclick="printinvoice();">��ӡ���뵥</a></li><%}%>
	          <%if(inbound_otsqdmgrinvalid){%><li class="tubiao3"><a href="#" onclick="deleteinvoice();">����</a></li><%}%>
	          <%if(inbound_otsqdmgrclose){%><li class="tubiao2" style="width:90px"><a href="#" onclick="closeinvoice();">�ر����뵥</a></li><%}%>
	          <!--<li class="tubiao4" style="width:90px"><a href="#" onclick="cancelaudit();">ȡ�����</a></li>
	          <li class="tubiao4"><a href="#" onclick="audit();">���</a></li> -->
	        </ul>
	      </div>
	    </div>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>			
 <table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
              <tr>
                <td width="80"  class="yx1"><div align="right">�ջ��ֿ⣺</div></td>
                <td width="150" class="yx"><div align="left">
                  <select id="warehouseid" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select></div>
                </td>
                <td width="80" class="yx1" align="right"><div align="right">���뵥�ţ�</div></td>
                <td width="150"class="yx">
                  <input type="text" name="instockid"  style="height:18px;width:100px;"/>
                </td>
                <td class="yx1" width="80"><div align="right">���ڣ�</div></td>
                <td class="xx1"  ><div align="left">
                  <input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  -<input name="end_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" /> 
                </td>
             </tr>
             <tr>
             	<td width="80"  class="yx1"><div align="right">����״̬��</div></td>
                <td width="150" class="yx"><div align="left">
                  	<select id="instatus" style="width:100px;">
                       <option value="">--��ѡ��--</option>
                    </select>
                </td>
                <td width="80" class="yx1"><div align="right">��Ʒ���ƣ�</div></td>
                <td width="150" class="yx" >
                	<input type="text" name="productName"   style="height:18px;width:100px;"/>
                </td>  
                <td width="80" class="yx1"><div align="right">���ţ�</div></td>
                <td width="150" class="yx" >
                	<input type="text" name="lotinfo"   style="height:18px;width:100px;"/>
                </td>   
              </tr> 
              <tr>
             	<td width="80" class="yx1" align="right"><div align="right">������ͣ�</div></td>
                <td width="150"class="yx">
                  <select id="invoicetype" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select>
                </td>
                <td></td>
                <td></td>  
                <td></td>
                <td></td>   
              </tr> 
</table> 
            
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 
     </td>
   </tr>
   <tr>
     <td height="100%"> 
     
  <!-- ***** ������ҵ��Ϣ����ҵ��ϸ*****-->
 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<!-- 	   <tr> -->
<!--            <td height="18" class="title">���뵥��Ϣ -->
<!--                 </td> -->
<!--        </tr> -->
	   <tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/othernew/inbound_scnewsqdmgr_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		���뵥��ϸ��Ϣ
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="tableheight2" name="tableheight2" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/othernew/inbound_scnewsqdmgrde_list.jsp"></iframe>
		 </td>
	   </tr>
	 </table>
 <!-- *******************************-->
 
 
 
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
