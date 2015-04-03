<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
    String strUserCode = (String)session.getAttribute("userCode");
    
    HashMap hsPopedom = null;
	boolean kcKcadjustQuery = false; 
	boolean kcKcadjustCancel = false; 
	boolean kcKcadjustAddH = false; 
	boolean kcKcadjustAddD = false; 
	boolean kcKcadjustDel = false; 
	boolean kcKcadjustSH = false; 
	boolean kcKcadjustUpdate = false; 
	boolean kcKcadjustAdjust = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kcKcadjustQuery") != null){
			kcKcadjustQuery = true;
		}
		if(hsPopedom.get("kcKcadjustCancel") != null){
			kcKcadjustCancel = true;
		}
		if(hsPopedom.get("kcKcadjustAddH") != null){
			kcKcadjustAddH = true;
		}
		if(hsPopedom.get("kcKcadjustAddD") != null){
			kcKcadjustAddD = true;
		}
		if(hsPopedom.get("kcKcadjustDel") != null){
			kcKcadjustDel = true;
		}
		if(hsPopedom.get("kcKcadjustSH") != null){
			kcKcadjustSH = true;
		}
		if(hsPopedom.get("kcKcadjustUpdate") != null){
			kcKcadjustUpdate = true;
		}
		if(hsPopedom.get("kcKcadjustAdjust") != null){
			kcKcadjustAdjust = true;
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
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
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
    //��ѯ
	function queryData()
	{
	       var strwarehouseid = document.getElementById("warehouseid").value;
	       var strstatus = document.getElementById("adjstatus").value;
		   var stradjtype = document.getElementById("adjtype").value;
		   var strreasoncode = document.getElementById("reasoncode").value;
		   var actionStr = "BMSService?actionCode=inventoryAdjustAction"
						 + "&warehouseid="+strwarehouseid
						 + "&status="+strstatus
						 + "&adjusttype="+stradjtype
						 + "&reasoncode="+strreasoncode
		Loading();	
		window.list.location.href = "<%=request.getContextPath()%>/" + actionStr;
		myIframe2.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";	
	}
	//����
	function addData()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_header_add.jsp",'','dialogWidth=500px;dialogHeight=300px');
		
		if(result != null && result.length > 1)
		{
			window.list.location.href="<%=request.getContextPath()%>/"+result;
			window.close();
		}
	}
	//ɾ��
	function delData()
	{
			   var deleteName = getDelCheckName();   
		        if(deleteName == null||deleteName.length <1){
		        	confirm("��ѡ����Ҫɾ���Ŀ�������");
		        }  
		        else{
					var del = confirm("ȷ��ɾ����ѡ��������");
					if(del){
						window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecDelete&deleteStr="+deleteName;		
					}
		        }
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
	//�޸�
	function updateData()
	{
			if(updateAble())
			{	
				var param = getCheckValue();
				var trim = param.split("&");
				if(trim[3].split("=")[1]!="0"){ 
				alert("���½�״̬�µĵ����������޸ģ�");
				return;			
				}			
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecQuery&id="+param;
				var result = showModalDialog(strUrl,'','dialogWidth=700px;dialogHeight=400px');
			   	if(result != null && result.length > 1)
			   	{
			   		list.location.href="<%=request.getContextPath()%>/"+result;
			   		window.close();
			   	}
		   	} 
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
			 
			 var stradjtype = document.getElementById("adjtype").value;
			 var param = getCheckValue();
			 if(stradjtype==1){//�����쳣����
				 var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_detail_mgr_outboundExce.jsp?id="+param,'','dialogWidth=750px;dialogHeight=550px');
			 }else if(stradjtype==2){//����쳣����
				 var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_detail_mgr_inboundExce.jsp?id="+param,'','dialogWidth=750px;dialogHeight=550px');
			 }else if(stradjtype == 3){//�̵����
				 var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_detail_mgr_check.jsp?id="+param,'','dialogWidth=750px;dialogHeight=550px');
			 }else if(stradjtype == 4){//�����Ϣ����
				 var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_detail_mgr.jsp?id="+param,'','dialogWidth=750px;dialogHeight=550px');
			 }else{
				 alert("��ѡ�񡾵������͡�");
			 }
			 
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
					
			  		var stradjtype = document.getElementById("adjtype").value;
			  		 if(stradjtype==1){//�����쳣����
					 		var deleteName = getDelCheckName1();
					        if(deleteName == null||deleteName.length <1){
					        	confirm("��ѡ����Ҫ�����ĳ����쳣������");
					        }  
					        else{
								var del = confirm("ȷ��������ѡ�����쳣������");
								if(del){
									window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecAdjustOutboundExce&deleteStr="+deleteName;	 	
								}
					        }
					        
					 }else if(stradjtype==2){//����쳣����
						 var deleteName = getDelCheckName1();
					 	 if(deleteName == null||deleteName.length <1){
					        	confirm("��ѡ����Ҫ����������쳣������");
					        }  
					        else{
								var del = confirm("ȷ��������ѡ����쳣������");
								if(del){
									window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecAdjustInboundExce&deleteStr="+deleteName;	 	
								}
					        }
						 
							
					 }else if(stradjtype == 3){//�̵����
						 
						 var deleteName = getDelCheckName1();
					 	 if(deleteName == null||deleteName.length <1){
					        	confirm("��ѡ����Ҫ�������̵������");
					        }  
					        else{
								var del = confirm("ȷ��������ѡ�̵������");
								if(del){
									window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecAdjustCheckExce&deleteStr="+deleteName;	 	
								}
					        }
						 
						
					 }else if(stradjtype == 4){//�����Ϣ����
						 var deleteName = getDelCheckName1();
					 	 if(deleteName == null||deleteName.length <1){
					        	confirm("��ѡ����Ҫ�����Ŀ����Ϣ������");
					        }  
					        else{
								var del = confirm("ȷ��������ѡ�����Ϣ������");
								if(del){
									window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecAdjustInventoryinfoExce&deleteStr="+deleteName;	 	
								}
					        }
						 
					 }else{
						 alert("��ѡ�񡾵������͡�");
					 }
	}
	//���ѡ���ѡ����ֵ
	function getDelCheckName1()
	{
			var strDel = '';
			var length = list.myform.elements.length;
			for(i=0;i<length;i++){
			    if(list.myform.elements[i].name!='checkbox_all'&&list.myform.elements[i].type=='checkbox'&& list.myform.elements[i].checked == true)
			    {
			    	var status = list.myform.elements[i+1].value;
			    	if(status!=null&&status=="1"){//�����
			    		strDel = strDel + list.myform.elements[i].value + ',';
			    	}else if(status!=null&&status=="0"){//����״̬��δ���
			    		alert("��������"+list.myform.elements[i].alt+"��û�����,���ܵ���!");
			    		list.myform.elements[i].checked = false; 
			    	}else{//�����
			    		alert("��������"+list.myform.elements[i].alt+"�������,���ܵ���!");
			    		list.myform.elements[i].checked = false; 
			    	}
			    }
			}
			return strDel;
	}
	//���
	function audit()
	{
		var deleteName = getDelCheckName();   
	    if(deleteName == null||deleteName.length <1){
		    alert("��ѡ����Ҫ��˵Ŀ�������");
	    }  
		   else{
				var del = confirm("ȷ�������ѡ��������");
				if(del){
				window.list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecAuditAjustHeader&deleteStr="+deleteName;		
			}
		 }
	}
function Onload(){
	//�ֿ�
	DWREngine.setAsync(false);
	selectObject("", "warehouseid", "1");	
	selectType("3", 'adjtype', 'tzlx');
	selectType("", 'reasoncode', 'tzyy');
	selectType("", 'adjstatus', 'tzzt');
	DWREngine.setAsync(true);
}
</script>
</head>
<body onload="javascript:Onload();">
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
	     <div class="wz" >
	      <div id="dqwz" class="f_l">��ǰλ�ã�<span>������</span> &gt;&gt; <span>������</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	           <%if(kcKcadjustAdjust){%><li class="tubiao2" ><a href="#" onclick="moveBatch()">����</a></li><%}%>
	           <%if(kcKcadjustUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li><%}%>
	           <%if(kcKcadjustSH){%><li class="tubiao2"><a href="#" onclick="audit()">���</a></li><%}%>
	           <%if(kcKcadjustDel){%><li class="tubiao3" style="width:90px;" ><a href="#" onclick="delData()">ɾ��������</a></li><%}%>
	           <%if(kcKcadjustAddD){%><li class="tubiao2"  style="width:80px;"><a href="#" onclick="detailData()">�����ϸ</a></li><%}%>
	           <%if(kcKcadjustAddH){%><li class="tubiao4"  style="width:80px;"><a href="#" onclick="addData()">��ӵ�ͷ</a></li><%}%>
	           <%if(kcKcadjustCancel){%><li class="tubiao2"><a href="#" onclick="resetData('myform')">����</a></li><%}%>
	           <%if(kcKcadjustQuery){%><li class="tubiao1"><a href="#" onclick="queryData()">��ѯ</a></li><%}%>
	        </ul>
	      </div>
	    </div>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  	 <tr>
     <td height="5"></td>
   	</tr>
 	</table>
 		<form id="myform" name="myform" method="post" action="">	
			   <table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
	             <tr>
	               <td class="y1" width="100" align="right">�ֿ⣺</td>
                   <td class="x"><select name="warehouseid" style="width:117px;"><option value=""></option></select></td>
                   
                   <td class="y1" align="right"><font color="red">*&nbsp;</font>�������ͣ�</td>
	      		   <td class="x">
	      	  		 <select id="adjtype" name="adjtype" style="width:117px;" onchange="queryData();">
                		 <option value="">--��ѡ��--</option>
             		 </select>
	      		   </td>
                   
	               <td class="y1" align="right">����״̬��</td>
	      		   <td class="x">
	      	  		 <select id="adjstatus" name="adjstatus" style="width:117px;">
                		 <option value="">--��ѡ��--</option>
             		 </select>
	      		   </td>
	               
	      		   <td class="y1" align="right">����ԭ��</td>
	               <td >
	               <select id="reasoncode" style="width:117px;">
                		<option value="">--��ѡ��--</option>
                   </select>
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
           <td height="18" class="title">��������
                </td>
       </tr> -->
	   <tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	    <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=5&flag=Y"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              	����������ϸ
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="myIframe2" name="myIframe2" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp"></iframe>
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
