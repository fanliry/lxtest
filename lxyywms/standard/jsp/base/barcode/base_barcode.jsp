<%@ page contentType="text/html; charset=GBK"%>


<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ������ά��</span></div>
	  </div>
	  
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td height="100%"> 

	  <table width="98%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		  <td width="20%" rowspan="2">
			<iframe id="left" name="left" width="100%" height="100%" frameborder="0" scrolling="no"
				src="<%=request.getContextPath()%>/standard/jsp/base/barcode/base_barcode_left.jsp"></iframe>
		  </td>
		  <td width="80%">
			<iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="auto"
				src="<%=request.getContextPath()%>/standard/jsp/base/barcode/base_barcode_list.jsp"></iframe>
		  </td>
		</tr>
		<tr>
    	  <td height="25px">
    	  	<iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		  </td>
   		</tr>
	  </table>
	  
     </td>
   </tr>
 </table>
</div>
 
</body>
</html>
