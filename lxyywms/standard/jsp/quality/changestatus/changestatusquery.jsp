<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean quality_recordquery = false; //��ѯ
	boolean quality_recordreport = false; //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("quality_recordquery") != null){
			quality_recordquery = true;
		}
		if(hsPopedom.get("quality_recordreport") != null){
			quality_recordreport = true;
		}
    }
%>
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
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script>
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
    //��ѯ
	function queryData()
	{
	    var lotnumber=document.getElementById("lotnumber").value;
		var actionStr = "BMSService?actionCode=inventoryQualityAction"
        		 + "&lotnumber=" + lotnumber
        		 + "&fromdate=" + document.getElementById("from").value
        		 + "&todate=" + document.getElementById("to").value+ "&flag=4";
		Loading();	
		window.list.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	//���ѡ���ѡ����ֵ
	function getDelCheckName()
	{
		var strDel = '';
		var length = list.myform.elements.length;
		for(i=0;i<length;i++){
			 if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true){
			      strDel = strDel + list.myform.elements[i].value + ',';
			 }
		}
		return strDel;
	}

	 //��ȡ������ѡ���ֵ
	   function getCheckValue()
	   {
	   	 var value = "";
	  		var length = list.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true)
			    {
			         value = list.myform.elements[i].value;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
	   //�޸�����ʱ����
		function updateAble(){
		 	var icount = getCheckCount();
			if(icount<1){
				alert("��ѡ����Ҫ��������!");
			  	return false;
			}
			if(icount>1){
			 	alert("ֻ��ѡ��һ������!");
			 	return false;
			}
			return true;
		}
		//��ȡ��ѡ�����
		function getCheckCount()   
	  	{   
	  		var counter = 0;

	  		var length = list.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			    }
			}
	  		
	  		
	  		return counter;
	   }
	   //��ϸ
	function detailData()
	{
		if(updateAble())
		{		
			var param = getCheckValue();
			
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/productadjust/product_adjust_detail_mgr.jsp?id="+param,'','dialogWidth=1100px;dialogHeight=700px');
			queryData();
			
		}
	}
	//����
	function resetData(formName) 
	{
	    var formObj = document.forms[formName]; 
	    var formEl = formObj.elements; 
	    for (var i=0; i<formEl.length; i++) 
	    {
	        var element = formEl[i]; 
	        if (element.type == 'submit') { continue; } 
	        if (element.type == 'reset') { continue; } 
	        if (element.type == 'button') { continue; } 
	 
	        if (element.type == 'text') { element.value = ''; } 
	        if (element.type == 'hidden') { element.value = ''; } 
	        if (element.type == 'textarea') { element.value = ''; } 
	        if (element.type == 'checkbox') { element.checked = false; } 
	        if (element.type == 'radio') { element.checked = false; } 
	        if (element.type == 'select-multiple') { element.selectedIndex = -1; } 
	        if (element.type == 'select-one') { element.selectedIndex = -1; } 
	    }
	}
	//����(���Ե��������������(���Ƕ����ϸ))
	function moveBatch()
	{
		 var deleteName = getDelCheckName1();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("��ѡ����Ҫ�����Ŀ�������");
		        }  
		        else{
					var del = confirm("ȷ��������ѡ��������");
					if(del){
						list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=productAdjustAction&method=ricoExecAdjust&deleteStr="+deleteName;	 	
					}
		        }
	}
	//���ѡ���ѡ����ֵ
	function getDelCheckName1()
	{
			var strDel = '';
			
			var length = list.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(list.myform.elements[i].name!='checkbox_all'&& list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked== true)
			    {
			    	var status = list.myform.elements[i].id;
			    	if(status != "" && status=="1")
			    	{
			    		alert("������"+list.myform.elements[i].alt+"�����,�����ٴε���,������ѡ��!");
			    		return;
			    	}
			        strDel = strDel + list.myform.elements[i].value + ','; 
			    }
			}
			return strDel;
	}
	
		//��ӡ
	function report(){
		var k = 0;
		var id = " ";
		var check_ids = list.document.getElementsByName("checkbox_id");
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
		document.tempForm.action = strUrl + "inventoryQualityAction&method=ricoExecPrint&invoiceid="+id;
		document.tempForm.submit();	
  		
	}
	
	function upData(){
	    
	}
</script>
</head>
<body>
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
	     <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã�<span>�ʼ����</span> &gt;&gt; <span>״̬ת������ѯ</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	           <!-- <li class="tubiao2"><a href="#" onclick="upData()">�ϴ� </a></li>-->
	           <%if(quality_recordreport){%><li class="tubiao1"><a href="#" onclick="report()">����</a></li><%}%>
	           <%if(quality_recordquery){%><li class="tubiao1"><a href="#" onclick="queryData()">��ѯ</a></li><%}%>
	        </ul>
	      </div>
	    </div>
 		<form id="myform" name="myform" method="post" action="">	
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
			    <table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
	              <tr>
	                <td width="60" class="y1"><div align="right">���ڣ�</div></td>
	                <td width="290" class="x" >
	                  <div align='left'><input type='text' name='from' onFocus='calendar()' class='Wdate' style='height:21px;width:100px;'>-<input type='text' name='to' onFocus='calendar()' class='Wdate' style='height:21px;width:100px;'></div></td>
	                <td class="y1" width="60"><div align="right">���ţ�</div></td>
	                <td>
	                  <input type="text" name="lotnumber" size="20">
	                </td>
	             </tr>
	            </table> 
            </form>
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
<!-- 	   <tr>
           <td height="18" class="title">״̬ת����
                </td>
       </tr> -->
	   <tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/quality/changestatus/changestatusqueryhead.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		״̬ת������ϸ
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="myIframe2" name="myIframe2" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/quality/changestatus/changestatusquerydetail.jsp"></iframe>
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
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
