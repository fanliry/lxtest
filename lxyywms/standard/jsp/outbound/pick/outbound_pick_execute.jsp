
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//����ID
	String invoiceid = request.getParameter("invoiceid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
  <script>
 	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//��������Ƿ�Ϊ������
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
	/*������ť*/
	function LockButton(){
		document.getElementById("button_add").disabled = true;	
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		document.getElementById("button_add").disabled = false;
	}
 	//���
	function saveData(invoiceid)
	{
		LockButton();
		//��ҵID
		var jobid = document.getElementById("jobid").value;
		//��ҵ״̬
		var status = document.getElementById("status").value;
		//��ҵ��ϸID
		var jobdetailid = document.getElementById("jobdetailid").value;
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		
		if(jobid == null || jobid.length < 1){
			alert("����ҵ��š�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(jobdetailid == null || jobdetailid.length < 1){
			alert("����ҵ��ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(status == null || status.length < 1){
			alert("����ҵ��ϸ״̬������Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(status == "0"){
			alert("����ҵ��ϸ״̬����Ϊ�½�״̬��");
			UnLockButton();
			return;
		}
		
		//************************************************
		if(confirm("��ȷ���Ƿ�����"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=pickAction";
			myIframe.location.href = strUrl + "&method=executePick&invoiceid="+ invoiceid + "&jobdetailid=" + jobdetailid ;
			
		}else{
			UnLockButton();
		}
	}

</script>
  </head>
  
  <body >
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; ��� -&gt; ִ�м��</div></td>
   </tr>
  </table>
	
 <!-- ======== ��ҵ�б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="425">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=pickAction&flag=3&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ҵ�б���� ======== -->
 <!-- ======== ��ҵ��ϸ�б�ʼ ======== 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top"  height="185" >  ��Ҫ�к��ŵĹ��������߶���Ϊ185���� û����165 
					<iframe id="detail" src="<%=request.getContextPath()%>/standard/jsp/outbound/pick/outbound_pick_execute_jobdetail.jsp"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
  ======== ��ҵ��ϸ�б���� ======== -->
 
<table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        
        <tr>
          <td width="13%" align="right" class="yx1">��ҵ��ţ�</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="jobid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          	 <input type="hidden" name="status" >
          </td>
          <td width="15%" align="right" class="yx1">���������</td>
          <td width="20%" class="yx">
	          <input type="text" name="whareaname" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="whareaId" >
          </td>
          <td width="15%" align="right" class="yx1">�����λ��</td>
          <td width="19%" class="xx1">
	          <input type="text" name="spacename" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="spaceid" >
          </td>
        </tr>
        <tr>
          <td  align="right" class="yx1">��ϸ��ţ�</td>
          <td  class="yx" >
          	 <input type="text" name="jobdetailid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">���������</td>
          <td  class="yx">
	          <input type="text" name="num" class="input_readonly"  readonly  style="height:18px;width:100px;"/>

          </td>
          <td  align="right" class="yx"></td>
          <td  class="xx1">
	         
          </td>
        </tr>

		<tr>
        	<TD colspan="6" height="10">
        		<input type="hidden" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
        	</TD>
        </tr>
        
        
        
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;ִ�м��" onClick="saveData('<%=invoiceid%>');"  />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>

  </body>
</html>
