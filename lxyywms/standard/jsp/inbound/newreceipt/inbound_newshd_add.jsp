<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
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
		var intype = document.getElementById("intype").value;
		//����
		var customer_id = document.getElementById("customer_id").value;
		
		//Ԥ�ڵ���ʱ��
		var arrivestarttime = document.getElementById("arrivestarttime").value;
		//Ԥ�ڵ���ʱ��
		var arriveendtime = document.getElementById("arriveendtime").value;
		//������
		var shipperid = document.getElementById("shipperid").value;
		
		
		//��Ӧ��
		var supplierid = document.getElementById("supplierid").value;
		//�ջ���̨
		var receiveloc = document.getElementById("receiveloc").value;
		//���ر�����
		var customsno = document.getElementById("customsno").value;
		//�û��Զ���1
		var reserve1  = document.getElementById("reserve1").value;
		//�û��Զ���2
		var reserve2  = document.getElementById("reserve2").value;
		//��ע
		var remark  = document.getElementById("remark").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("���ֿ⡿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(intype == null || intype.length < 1){
			alert("��������͡�����Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(customer_id == null || customer_id.length < 1 ){
			alert("������������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		
		var condition = "&intype=" + intype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + "&arrivestarttime=" + arrivestarttime + "&arriveendtime="+arriveendtime + 
					"&shipperid=" + shipperid + "&supplierid=" + supplierid + "&receiveloc=" + receiveloc + "&customsno=" + customsno + "&reserve1=" + reserve1+ "&reserve2=" + reserve2+ "&remark=" + remark;
		
		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundREAction&method=addRicoExec";
			window.returnValue = strUrl + condition;
    		window.close();
				
		}else{
			UnLockButton();
		}
	}
		
		
		
		
		function OnLoad()
		{
			selectObject('', 'warehouseid', '1');
			selectType('', 'intype', 'rkdj');
		}
	</script>
	
  </head>
  
  <body onload="javascript:OnLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �½��ջ��� -&gt; ����</div></td>
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
          <td width="20%" class="yx"><select name="intype" id="intype"  style="width:100px;">
            <option>---��ѡ��---</option>
          </select></td>
          <td width="15%" align="right" class="yx1"><span class="red">*</span>����״̬��</td>
          <td width="19%" class="xx1"><select name="instatus" id="instatus"  style="width:100px;">
            <option selected="selected">�½�</option>
          </select></td>
        </tr>
        <tr>
          <td align="right" class="yx1"><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td class="yx">
          	<input type="text" name="customer_name"  readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="customer_id" />
            <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520');" /></td>
          <td align="right" class="yx1">Ԥ�ڵ���ʱ��ӣ�</td>
          <td class="yx">
          		
          		<input name="arrivestarttime" type="text"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
          <td align="right" class="yx1">Ԥ�ڵ���ʱ�䵽��</td>
          <td class="xx1">
          	<input name="arriveendtime" type="text"   onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
          </td>
        </tr>
        <tr>
          <td align="right" class="yx1">��&nbsp;��&nbsp;�ˣ�</td>
          <td class="yx">
          	<input type="text" name="shippername" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
            <input type="hidden" name="shipperid" />
            <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=3','800','520','shipperid','shippername');" />
          </td>
          <td align="right" class="yx1">��&nbsp;Ӧ&nbsp;�̣�</td>
          <td class="yx">
          	 <input type="text" name="suppliername" size="16" readonly="readonly"  style="height:18px;width:100px;"/>
             <input type="hidden" name="supplierid" />
             <input name="moreBtn2" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=2','800','520','supplierid','suppliername');" />
          </td>
          <td align="right" class="yx1">�ջ���̨��</td>
          <td class="xx1" >
            <input type="text" name="receiveloc"    style="height:18px;width:100px;"/>
            
         </td>
        </tr>
        <tr>
          <td align="right" class="yx1">���ر����ţ�</td>
          <td class="yx">
          	<input type="text" name="customsno"  style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">�û��Զ���1��</td>
          <td class="yx">
          		<input type="text" name="reserve1"  style="height:18px;width:100px;"/>
          </td>
          <td align="right" class="yx1">�û��Զ���2��</td>
          <td class="xx1" ><input type="text" name="reserve2"  style="height:18px;width:100px;"/></td>
        </tr>
        <tr>
     		<td class="yx1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</div></td>
     		<td class="xx1" colspan="5"><div align="left"><textarea name="remark" cols="105" rows="3"></textarea></div></td>
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
