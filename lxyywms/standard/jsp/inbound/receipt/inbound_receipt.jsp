<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
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
	
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
<!--����Ӧ�߶ȵ�div-->
 	var condition = null;

 	//�ջ�
 	function addinvoicereceipt()
 	{
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
 		showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_mgr.jsp?invoiceid="+id,"","dialogWidth=800px;dialogHeight=710px;");

 	}
 	
 	function cancelreceipt()
 	{
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
 		showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_cancel.jsp?invoiceid="+id,"","dialogWidth=800px;dialogHeight=480px;");
 		
 	}
 	
 
 	//��ӡ����
	function printInvoice()
	{
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
		
		var actionStr = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&flag=2"
	    		+ "&invoiceid="+id;
	    		
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  			
		window.open(actionStr,'newwindow',"'width=800,height=600,left="+WLeft+",top="+WTop+"',scrollbars=yes");
	   	
	}
	//��ӡ�ջ�����
	function printTask()
	{
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
		
		var actionStr = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=3"
	    		+ "&invoiceid="+id;
	    		
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  			
		window.open(actionStr,'newwindow',"'width=900,height=600,left="+WLeft+",top="+WTop+"',scrollbars=yes");
	   	
	}
	
	
 	//��ѯ
 	function queryinvoice()
 	{
 		
 		//����״̬
		var status = document.getElementById("instatus").value;
 		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//����
		var intype = document.getElementById("intype").value;
		//����
		var customer_id = document.getElementById("customer_id").value;
		//��ʼ����
		var start_time = document.getElementById("start_time").value;
		//��������
		var end_time = document.getElementById("end_time").value;

		
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		
		
		
 		var condition = "&linerow=" + linerow + "&instatus=" + status + "&intype=" + intype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + 
					    "&start_time=" + start_time + "&end_time=" + end_time;
		
 		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&flag=re1";
 		
 		Loading();
 		
 		list.location.href = strUrl + condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_dlist.jsp";	
 	}
 
 
 
 	function OnLoad()
	{
		selectObject('', 'warehouseid', '1');
		selectType('', 'intype', 'rkdj');
		selectType('', 'instatus', 'rkdstatus');
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
	     <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã�<span>������</span> &gt;&gt; <span>�ջ�</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	          <li class="tubiao2" style="width:90px;"><a href="#" onclick="cancelreceipt();">ȡ���ջ�</a></li>
	          <li class="tubiao2" style="width:100px;"><a href="#" onclick="printTask();">��ӡ�ջ�����</a></li>
	          <li class="tubiao2" style="width:90px;"><a href="#" onclick="addinvoicereceipt();">�������ջ�</a></li>
	          <li class="tubiao2" style="width:90px;"><a href="#" onclick="printInvoice();">��ӡ�ջ���</a></li>
	          <li class="tubiao1"><a href="#" onclick="queryinvoice();">��ѯ</a></li>
	          
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
                <td width="80"  class="yx1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</div></td>
                <td width="150" class="yx">
                <div align="left">
                  <select id="warehouseid" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select></div>
                </td>
                <td width="80" class="yx1" align="right"><div align="right">����״̬��</div></td>
                <td width="150" class="yx">
                	<div align="left">
                  <select id="instatus" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select></div>
                </td>
                <td class="yx1" width="60"><div align="right">������ͣ�</div></td>
                <td class="xx1"  width=""><div align="left">
                  <select id="intype" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select></div>
                </td>
             </tr>
             <tr>
             	
                <td width="80" class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</div></td>
                <td width="380" class="x" colspan="3">
                  <input type="text" name="customer_name"  readonly="readonly"  style="height:18px;width:200px;"/>
                  <input type="hidden" name="customer_id" />
                  <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></td>
                
                <td width="60" class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ڣ�</div></td>
                <td ><input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  -<input name="end_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  </td>     
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
	   <tr>
           <td height="18" class="title">�ջ�����Ϣ
                </td>
       </tr>
	   <tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		�ջ�����ϸ��Ϣ
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="tableheight2" name="tableheight2" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/receipt/inbound_receipt_dlist.jsp"></iframe>
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