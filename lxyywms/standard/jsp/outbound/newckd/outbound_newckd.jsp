<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
    String strTime =  StrTools.getCurrDateTime(8); 
	HashMap hsPopedom = null;
	boolean outboundNewsqdQuery = false;    
	boolean outboundNewsqdAdd = false;     
	boolean outboundNewsqdAddSo = false; 
	boolean outboundNewsqdProduct = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("outboundNewsqdQuery") != null){
			outboundNewsqdQuery = true;
		}
		if(hsPopedom.get("outboundNewsqdAdd") != null){
			outboundNewsqdAdd = true;
		}
		if(hsPopedom.get("outboundNewsqdAddSo") != null){
			outboundNewsqdAddSo = true;
		}
		if(hsPopedom.get("outboundNewsqdProduct") != null){
			outboundNewsqdProduct = true;
		}
    }
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
var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
 	var condition = null;
 	//���ӳ��ⵥ
 	function addinvoice()
 	{
 		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_add.jsp","","dialogWidth=800px;dialogHeight=250px;");
		if(result != null && result.length > 0)
		{
			list.location.href = result;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";
		}		
 	}
 	//���������ӳ��ⵥ
 	function ddaddinvoice()
 	{
 		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_ckd_add.jsp","","dialogWidth=850px;dialogHeight=600px;");
		if(result != null && result.length > 0)
		{
		    //ÿҳ��ʾ����
		    var linerow = page.document.getElementById("lineviewrow").value;
			list.location.href = result+"&linerow="+linerow;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";
		}
 	}
 	//���³��ⵥ
 	function updateinvoice()
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
 	
 		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=3&invoiceid="+id,"","dialogWidth=800px;dialogHeight=250px;");
		if(result != null && result.length > 0)
		{
			list.location.href = result;
		}
		queryinvoice();
 	}
 	//���ӳ��ⵥ��ϸ
 	function addinvoicedetail()
 	{
 		var k = 0;
		var id = "";
		var ownerid = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				ownerid = check_ids[i].alt;
				k++;
			}
		}
		if( k == 0 ){
			alert("��ѡ��һ�ų��ⵥ�ݣ�");
			return;
		}else if(k != 1){
			alert("ֻ��ѡ��һ�ų��ⵥ�ݣ�");
			return;
		}
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_detail.jsp?invoiceid="+id,"new","dialogWidth=800px;dialogHeight=600px;");
		if(result != null && result.length > 0)
		{
			
		}
		list.queryDetail(id);
 	}
 	

 	
	//����
 	function assign()
 	{
 		var k = 0;
		var id = "";
		var check_ids = tableheight2.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
				break;
			}
		}
		if( k == 0 ){
			alert("��ѡ��һ�����ⵥ��ϸ��");
			return;
		}else if(k != 1){
			alert("ֻ��ѡ��һ�����ⵥ��ϸ��");
			return;
		}	    		
		var WLeft = Math.ceil((window.screen.width-1100)/2);
  		var WTop  = Math.ceil((window.screen.height-800)/2);
  			
		showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=5&invoicedetailid="+id,'newwindow',"dialogWidth=1000px;dialogHeight=600px;");
 	}
 	
 	//��ѯ
 	function queryinvoice()
 	{
 		//����״̬
 		var outstatus = document.getElementById("outstatus").value;;
 		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//����
		var outtype = document.getElementById("outtype").value;
		//�ͻ�
		var customer_id = document.getElementById("customer_id").value;
		//��ʼ����
		var start_time = document.getElementById("start_time").value;
		//��������
		var end_time = document.getElementById("end_time").value;
		//���ⵥ��
		var outstockid = document.getElementById("outstockid").value;
	
		
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		
 		var condition = "&linerow=" + linerow + "&outstatus=" + outstatus + "&outtype=" + outtype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + 
					    "&start_time=" + start_time + "&end_time=" + end_time + "&outno=" + outstockid;
		
 		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=1";
 		
 		Loading();
 		
 		list.location.href = strUrl + condition;
 		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";	
 	}
 
 
 
 	function OnLoad()
	{
		selectObject('', 'warehouseid', '1');
		selectType('', 'outtype', 'ckdj');
		selectType('0', 'outstatus', 'ckdzt');
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
	     <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã�<span>�������</span> &gt;&gt; <span>�½����ⵥ</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	          <%if(outboundNewsqdAddSo){%><li class="tubiao4" style="width:88px;"><a href="#" onclick="ddaddinvoice();">����������</a></li><%}%>
	          <%if(outboundNewsqdProduct){%><li class="tubiao2" style="width:90px;"><a href="#" onclick="addinvoicedetail();">�����Ʒ</a></li><%}%>
	          <%if(outboundNewsqdAdd){%><li class="tubiao4"><a href="#" onclick="addinvoice();">����</a></li><%}%>
	          <%if(outboundNewsqdQuery){%><li class="tubiao1"><a href="#" onclick="queryinvoice();">��ѯ</a></li><%}%>
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
                <td width="150" class="yx"><div align="left">
                  <select id="warehouseid" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select></div>
                </td>
                <td width="80" class="yx1" align="right"><div align="right">����״̬��</div></td>
                <td width="150"class="yx">
                  <select id="outstatus" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select>
                </td>
                <td class="yx1" width="60"><div align="right">�������ͣ�</div></td>
                <td class="xx1"  ><div align="left">
                  <select id="outtype" style="width:100px;">
                    <option value="">--��ѡ��--</option>
                  </select></div>
                </td>
             </tr>
             <tr>
             	<td width="80"  class="y1"><div align="right">��&nbsp;��&nbsp;�ˣ�</div></td>
                <td width="150" class="x"><div align="left">
                  	<input type="text" name="customer_name"  readonly="readonly"  style="height:18px;width:100px;"/>
                  	<input type="hidden" name="customer_id" />
                  	<input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');" />
                </div>
                </td>
                <td width="80" class="y1"><div align="right">���ⵥ�ţ�</div></td>
                <td width="200" class="x" >
                	<input type="text" name="outstockid"   style="height:18px;width:100px;"/>
                </td>
                <td width="60" class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ڣ�</div></td>
                <td>
                	
                  	<input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
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
<!-- 						<tr> -->
<!-- 							<td height="18" class="title">���ⵥ������Ϣ</td> -->
<!-- 						</tr> -->
		<tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		���ⵥ��<span id="dckdid" style="color: red;font-weight:bold;"></span>����ϸ��Ϣ
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="detail" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp"></iframe>
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