<%@ page contentType="text/html; charset=GBK"%>
<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
	
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>

<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var sp_flag = false;
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

	
	//**********************************************************
	//��������
	function saveData(warehouseid)
	{
		LockButton();
		
		//��������
		var traycode = document.getElementById("tray_code").value;
		//����ID
		var whAreaId = document.getElementById("whAreaId").value;
		//�ϼܷ�ʽ
		var putmode = "";
		
		//Ʒ��
		var product_id = document.getElementById("product_id").value;
		//��װ
		var pack_id = document.getElementById("pack_id").value;
		//��λ
		var eunit = document.getElementById("eunit").value;
		//����
		var num = document.getElementById("num").value;
		var netweight = document.getElementById("netweight").value;
		var weight = document.getElementById("weight").value;
		
		//��������ID
		var lotid  = document.getElementById("lotid").value;		
		var lotatt1  = document.getElementById("lotatt1").value; 	//��������1
		var lotatt2  = document.getElementById("lotatt2").value; 	//��������2
		var lotatt3  = document.getElementById("lotatt3").value; 	//��������3
		var lotatt4  = document.getElementById("lotatt4").value; 	//��������4
		var lotatt5  = document.getElementById("lotatt5").value; 	//��������5
		var lotatt6  = document.getElementById("lotatt6").value; 	//��������6
		var lotatt7  = document.getElementById("lotatt7").value; 	//��������7
		var lotatt8  = document.getElementById("lotatt8").value; 	//��������8
		var lotatt9  = document.getElementById("lotatt9").value; 	//��������9
		var lotatt10  = document.getElementById("lotatt10").value; //��������10
		var lotatt11  = document.getElementById("lotatt11").value; //��������11
		var lotatt12  = document.getElementById("lotatt12").value; //��������12
		
		if(tray_code != null && tray_code.length>1 && tray_code.length != 8)
		{
			alert("���������롿��Ϊ8λ��Y-000000 ��");
			UnLockButton();
			return;
		}
		//��֤�����Ƿ����
		if(tray_code != null && tray_code.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isTrayCodeUse(tray_code, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
		}
		
		if(product_id == null || product_id.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		//�������
		if(num == null || num.length < 1 || !IsFloat(num)){
				alert("������������Ϊ����ֻ��Ϊ���֣�");
				UnLockButton();
				return;
		}
		if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("�����ء�ֻ��Ϊ���֣�");
				UnLockButton();
				return;
		}else if(netweight == null || netweight.length < 1)
		{
				netweight = "0";
		}
		
		if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("��ë�ء�ֻ��Ϊ���֣�");
				UnLockButton();
				return;
		}else if(weight == null || weight.length < 1)
		{
				weight = "0";
		}
		
		
		//��������ֵ��֤
		if(checkLotatt()==false){
			UnLockButton();
			return;
		}
		
		condition = "&productid=" + product_id + "&packid=" + pack_id + "&eunit=" + eunit + "&num=" + num + "&weight=" + weight + "&netweight=" + netweight + "&volume=0"; 
		//************************************************

			if(confirm("��ȷ���Ƿ������ϼ�����"))
			{		
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=rfJobAction";
				//������
				var msg = "<input type=hidden name='lotid' value='"+lotid+"'>"
						+ "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
						+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
						+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
						+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
						+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
						+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
						+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
						+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
						+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
						+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
						+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
						+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";

				formx1.innerHTML = msg;
				formx1.action = strUrl + "&method=addricoExec&traycode=" + traycode + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&putmode=" + putmode + condition;
				document.formx1.submit();
					
			}else{
				UnLockButton();
			}
	
	}
	
	
	
	
	
	
	

	
	//ҳ���½
	function onLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
		selectAreaList('', 'whAreaId', '<%=strWarehouseID%>', "1")// ����
	}
-->
</script>
</head>

<body onload="onLoad();">
 <table id="tbb"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
 <table width="100%">
   <tr>
     <td width="20%"></td>
     <td width="60%" align="center" class="font_006699_bold_12">RF�������</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" id="tray_code" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">�ϼܿ�����</td>
     <td class="TD_ADD"><input type="hidden" id="warehouseid" value="<%=strWarehouseID%>">
     		<select name="whAreaId"  style="width:162px;" >
	            <option>--��ѡ��--</option>
	           </select> 
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
     <td class="TD_ADD">
     	<input type="text" name="product_name" class="input_readonly" readonly="readonly" /> 
     	<input type="hidden" name="product_id" class="input_readonly">
     	<input name="moreBtn3" type="button" class="button_select" value="��" onclick="openProductPacke('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520','product_id','product_name', 'pack_id', 'pack_name', '1','eunit', 'lotid');" />
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;װ��</td>
     <td class="TD_ADD">
     	<input type="text" id="pack_name" class="input_readonly" readonly="readonly" maxlength="10"> 
     	<input type="hidden" name="pack_id" class="input_readonly">
     	
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
     <td class="TD_ADD">
     	<select name="eunit" id="eunit" style="width:100px;" class="input_readonly" >
	            <option>--��ѡ��--</option>
	        </select>
     
      </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
     <td class="TD_ADD"><input type="text" id="num" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">ë&nbsp;&nbsp;&nbsp;&nbsp;�أ�</td>
     <td class="TD_ADD"><input type="text" id="weight" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�أ�</td>
     <td class="TD_ADD"><input type="text" id="netweight" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   
   <tr>
     <td class="TD_ADD" height="5" colspan="2">
     	<input type="hidden" name="lotid" value=""/>
     </td>
   </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt001" align="right">��������1��</div></td>
          <td class="TD_ADD"><div id="lotvalue001" align="left">
          	<input type="text" name="lotatt1" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt002" align="right">��������2��</div></td>
          <td class="TD_ADD"><div id="lotvalue002" align="left">
          	<input type="text" name="lotatt2" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt003" align="right">��������3��</div></td>
          <td class="TD_ADD" ><div id="lotvalue003" align="left">
          	<input type="text" name="lotatt3" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt004" align="right">��������4��</div></td>
          <td class="TD_ADD"><div id="lotvalue004" align="left">
          	<input type="text" name="lotatt4" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt005" align="right">��������5��</div></td>
          <td class="TD_ADD"><div id="lotvalue005" align="left">
          	<input type="text" name="lotatt5" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt006" align="right">��������6��</div></td>
          <td class="TD_ADD" ><div id="lotvalue006" align="left">
          	<input type="text" name="lotatt6" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt007" align="right">��������7��</div></td>
          <td class="TD_ADD"><div id="lotvalue007" align="left">
          	<input type="text" name="lotatt7" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt008" align="right">��������8��</div></td>
          <td class="TD_ADD"><div id="lotvalue008" align="left">
          	<input type="text" name="lotatt8" size="16"    style="height:18px;width:100px;"/></div> 
           </td>
        </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt009" align="right">��������9��</div></td>
          <td class="TD_ADD" ><div id="lotvalue009" align="left">
          	<input type="text" name="lotatt9" size="16"    style="height:18px;width:100px;"/></div>
          </td>
        </tr>
        
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt010" align="right">��������10��</div></td>
          <td class="TD_ADD"><div id="lotvalue010" align="left">
          	<input type="text" name="lotatt10" size="16"   style="height:18px;width:100px;"/></div>
            
          </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt011" align="right">��������11��</div></td>
          <td class="TD_ADD"><div id="lotvalue011" align="left">
          	<input type="text" name="lotatt11" size="16"    style="height:18px;width:100px;"/></div>  
           </td>
         </tr>
        <tr>
          <td align="right" class="TD_ADD"><div id="lotatt012" align="right">��������12��</div></td>
          <td class="TD_ADD" ><div id="lotvalue012" align="left">
          	<input type="text" name="lotatt12" size="16" style="height:18px;width:100px;"/></div>
          </td>
        </tr>
   
   

   <tr>
     <td align="center" height="10" colspan="2"></td>
   </tr>
   
   <tr>
     <td class="TD_ADD" align="center" colspan="2">

       <input type="button" name="add" class="BUTTON_STYLE1" value="����" onClick="saveData('<%=strWarehouseID%>');">
     </td>
    </tr>
 </table>
 

<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
