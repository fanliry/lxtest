<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>���</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
<!--
		//��ѯ
  		function query()
  		{
  			var customerid  = document.getElementById("customer_id").value; 	//�ͻ�
  			var packageid = document.getElementById("package_id").value;			//��Ʒ
  			var lotid = document.getElementById("lotid").value;		//��������
  			var tray_code = document.getElementById("tray_code").value;		//��������
  			var locid = document.getElementById("locid").value;			//��λ

            Loading();
            
  			myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecInventoryQuery"+
  									"&customerid="+customerid+"&packageid="+packageid+"&lotid="+lotid+"&tray_code="+tray_code+
  									"&locid="+locid;	
  				
  		}
  		//ȷ��
	   function updateData()
	   {
			if(updateAble())
			{		
				var param = getCheckValue();
				
				window.returnValue=param;
				window.close();
		   	}
		}
  		//����ʱ����
		function updateAble()
		{
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

	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			    }
			}
	  		
	  		
	  		return counter;
	   }
	   //��ȡ������ѡ���ֵ
	   function getCheckValue()
	   {
	   	 var value = "";
	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].id;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
		   	//ҳ���¼
		function OnLoad(){
			//��������
			selectLot("", "lotid");
			parent.RemoveLoading();
		    parent.page.location.reload();
		}

  	
-->
</script>
</head>

<body  onLoad="OnLoad()">

 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �������� -&gt; ��������ϸ</div></td>
    <td>
      <div class="font_001F56_12" align="right">
	      <input type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search" onclick="query()">&nbsp;
         <input type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;ȷ��" class="button_search" onclick="updateData()">&nbsp;
	  </div>
    </td>
  </tr>
</table>
 <form id="myform1" name="myform1" method="post" action="">
	 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1"><div align="right">�ͻ�:</div></td>
	     <td class="yx"><div align="left">
	       <input type="text" name="customer_name"  class="input_readonly"/>
	       <input type="hidden" name="customer_id" />
	       <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" />
	     </div></td>
	     <td class="yx1"><div align="right">��Ʒ��</div></td>
	     <td class="yx"><div align="left">
	       	<input type="hidden" name="package_id"><input type="text" name="package_name" class="input_readonly"/>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	     </div></td>
	     <td class="yx1"><div align="right">��λ��</div></td>
	     <td class="yx"><div align="left">
	        <input type="text" name="locid" size="18" class="input_readonly">
	       <input onclick="basLocation('locid', '<%=request.getContextPath()%>');" type="button" name="moreloc" value="��" class="button_select">
	     </div></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�������룺</td>
     	  <td class="yx"><input name="tray_code" type="text" size="17"></td>
	   	  <td class="yx1" align="right">�������ԣ�</td>
     	  <td class="xx1"  colspan="3"><select id="lotid"  style="width:130px;"><option value=""></option></select>
     	  </td>
	   </tr>
	 </table>
	 </form>
    <table width="100%" height="15"  border="0" cellpadding="0" cellspacing="0">
     <tr><td></td></tr>
    </table>

<!-- ======== �б�ʼ ======== -->
 <table width="98%" height="500" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
	 <td class="outer_td">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_search_list.jsp" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
   <tr>
	    <td height="25">
	  	<iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=50&flag=Y"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	    </td>
	</tr>
 </table>
 <!-- ========�б���� ======== -->
<!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
</body>
</html>