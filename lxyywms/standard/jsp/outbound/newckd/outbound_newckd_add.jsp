<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
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
		
		document.getElementById("add").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}	
	//��������
	function saveData()
	{
		LockButton();
		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//����
		var outtype = document.getElementById("outtype").value;
		
		//����
		//var owner_id = document.getElementById("owner_id").value;
				
		var owner_id= "";
		//�ͻ�
		var customer_id = document.getElementById("customer_id").value;
		//��ַ
		var address = ""; //document.getElementById("address").value;
		
		//����
		var vehicleno = document.getElementById("vehicleno").value;
		//��λ
		var vehiclepos = document.getElementById("vehiclepos").value;
		//����
		var departid  = document.getElementById("departid").value;
		
		//���ȼ�
		//var inpriority = document.getElementById("inpriority").value;
		//������̨
		var sendplatform = "";
		//����λ
		var setposition = "";
		//���۵���
		var saleno = "";

		//Ҫ�󷢻�ʱ��
		var requestdate  = ""; //document.getElementById("requestdate").value;
		//Ԥ�ڷ���ʱ��
		var expectdate  = ""; //document.getElementById("expectdate").value;	
		//��������
		var formdate  = document.getElementById("formdate").value;
		
		
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("���ֿ⡿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(outtype == null || outtype.length < 1){
			alert("���������͡�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		/*
		if(customer_id == null || customer_id.length < 1){
			alert("���ջ��ˡ�����Ϊ�գ�");
			UnLockButton();
			return;
		}*/
		
		var condition = "&out_type=" + outtype + "&warehouseid=" + warehouseid + "&vehicleno=" + vehicleno + "&vehiclepos=" + vehiclepos + "&owner_id=" + owner_id + "&customer_id=" + customer_id + "&address=" + address +  
					"&sendplatform=" + sendplatform + "&setposition=" + setposition  + "&requestdate=" + requestdate + "&expectdate=" + expectdate + "&formdate=" + formdate+ "&departid=" + departid+ "&saleno=" + saleno;
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=addRicoExec";
			window.returnValue = strUrl + condition;
    		window.close();
				
		}else{
			UnLockButton();
		}
	}
		
		
		
		
		function OnLoad()
		{
			selectObject('', 'warehouseid', '1');
			selectType('', 'outtype', 'ckdj');
			selectType('', 'vehiclepos', 'carpos');
			//selectObject('', 'departid', 'bumen');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; �½����� -&gt; ����</div></td>
   </tr>
  </table>
	<FORM>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="13%" align="right" class="yx1"  ><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
          <td width="18%" class="yx" >
          	<select name="warehouseid" id="warehouseid" style="width:100px;">
            	<option>--��ѡ��--</option>
            </select>
          
          </td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>�������ͣ�</td>
          <td width="20%" class="yx"><select name="outtype" id="outtype"  style="width:100px;">
            <option>---��ѡ��---</option>
          </select></td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>����״̬��</td>
          <td width="19%" class="xx1"><select name="instatus" id="instatus" readonly="readonly"  style="width:100px;">
            <option selected="selected">����</option>
          </select></td>
        </tr>
        <tr>
    	<td align="right" class="yx1">
			<span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;�ţ�
		</td>
		<td class="xx1">
			<input type="text" id="departname" readonly="readonly"
				class="input_readonly" style="height: 18px; width: 100px;" />
			<input type="hidden" id="departid" />
			<input name="moreBtn" type="button" class="button_select"
				value="��"
				onclick="openDepartment('<%=request.getContextPath()%>/standard/jsp/common/common_department.jsp','800','520','departid','departname');" />
		</td>
		<td align="right" class="yx1">
			<span class="red">*</span><span id="changename">��&nbsp;&nbsp;&nbsp;&nbsp;����</span>
		</td>
		<td class="yx" colspan="3">
			<input type="text" name="customer_name" id="customer_name"
				readonly="readonly" class="input_readonly"
				style="height: 18px; width: 100px;" />
			<input type="hidden" id="customer_id" />
			<input name="moreBtn" type="button" class="button_select"
				value="��"
				onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=','800','520');" />
		</td>
<!--           <td align="right" class="yx1">�ջ���ַ��</td>
          <td class="xx1">
          	<input type="text" name="address"    style="height:18px;width:100px;"/>
          </td> -->
        </tr>
      <tr>
          <td align="right" class="yx1">�������ڣ�</td>
          <td class="yx">
          	<input name="formdate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
          <td class="yx">
          		<select name="vehiclepos" style="width:100px;">
                  <option value="">--��ѡ��--</option>
                </select>
          </td>
          <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�</td>
          <td class="yx">
          	<input type="text" name="vehicleno"    style="height:18px;width:100px;"/>
          </td>
           <!-- <td align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td class="yx">
          	<input type="text" name="owner_name"  readonly="readonly" class="input_readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="owner_id" />
            <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520','owner_id','owner_name');" />
           </td>-->
        </tr>  
        <!--<tr>
          <td align="right" class="yx1">������̨��</td>
          <td class="yx">
          	<input type="text" name="sendplatform"   style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">��&nbsp;��&nbsp;λ��</td>
          <td class="yx">
          	 <input type="text" name="setposition"   style="height:18px;width:100px;"/>
         </td>
          <td align="right" class="yx1">���۵��ţ�</td>
          <td class="xx1" >
            <input type="text" name="saleno" size="16"   style="height:18px;width:100px;"/>
            
         </td>
        </tr>  -->
        <tr>
<%--           <td align="right" class="yx1">�������ڣ�</td>
          <td class="yx">
          	<input name="formdate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td> --%>
<%--           <td align="right" class="yx1">Ҫ�󷢻�ʱ�䣺</td>
          <td class="yx">
          		<input name="requestdate" type="text" size="13" value="<%=strTime%>"  onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">Ԥ�ڷ���ʱ�䣺</td>
          <td class="xx1" >
          		<input name="expectdate" type="text" size="13" value="<%=strTime%>"  onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td> --%>
        </tr>
        
        <tr >
        	<TD colspan="6" height="20"></TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;����" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
	</FORM>
  </body>
</html>
