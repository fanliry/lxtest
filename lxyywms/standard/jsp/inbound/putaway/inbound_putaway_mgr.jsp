
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//����ID
	String invoiceid = request.getParameter("invoiceid");
	//�ֿ�ID
	String warehouseid = request.getParameter("warehouseid");
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
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
		document.getElementById("button_save").disabled = true;	
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("button_save").disabled = false;
	}
	//��������
	function addData()
	{
		var transid = myIframe.document.getElementById("transid").value;//�ջ���¼��
		if(transid == null || transid.length < 1){
			alert("���ջ���¼������Ϊ�գ�");
			return;
		}else{
			var proudctname = myIframe.document.getElementById("product_name").value;//��Ʒ
			var packname = myIframe.document.getElementById("packname").value;//��װ
			var unitname = myIframe.document.getElementById("eunit").value;//��λ

			var knum = myIframe.document.getElementById("num").value;	 			//���ϼ�����
			var knetweight = myIframe.document.getElementById("netweight").value;	//���ϼܾ���
			var kweight = myIframe.document.getElementById("weight").value;	 		//���ϼ�ë��
			
			var num = myIframe.document.getElementById("renum").value;				//�ϼ�����
			var netweight = myIframe.document.getElementById("renetweight").value;	//�ϼܾ���
			var weight = myIframe.document.getElementById("reweight").value;		//�ϼ�ë��
			
			//��������ID
			var lotid  = myIframe.document.getElementById("lotid").value;		
			var lotatt1  = myIframe.document.getElementById("lotatt1").value; 	//��������1
			var lotatt2  = myIframe.document.getElementById("lotatt2").value; 	//��������2
			var lotatt3  = myIframe.document.getElementById("lotatt3").value; 	//��������3
			var lotatt4  = myIframe.document.getElementById("lotatt4").value; 	//��������4
			var lotatt5  = myIframe.document.getElementById("lotatt5").value; 	//��������5
			var lotatt6  = myIframe.document.getElementById("lotatt6").value; 	//��������6
			var lotatt7  = myIframe.document.getElementById("lotatt7").value; 	//��������7
			var lotatt8  = myIframe.document.getElementById("lotatt8").value; 	//��������8
			var lotatt9  = myIframe.document.getElementById("lotatt9").value; 	//��������9
			var lotatt10  = myIframe.document.getElementById("lotatt10").value; //��������10
			var lotatt11  = myIframe.document.getElementById("lotatt11").value; //��������11
			var lotatt12  = myIframe.document.getElementById("lotatt12").value; //��������12
			
			//�������
			if(num == null || num.length < 1 || !IsFloat(num)){
				alert("���ϼ�����������Ϊ����ֻ��Ϊ���֣�");
				return;
			}
			if(parseFloat(num) > parseFloat(knum)){
				alert("���ϼ�����(" + num + ")�����ܴ����ջ���¼�ġ����ϼ�����(" + knum + ")����");
				return;
			}
			if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("���ϼܾ��ء�ֻ��Ϊ���֣�");
				return;
			}else if(netweight == null || netweight.length < 1)
			{
				netweight = "0";
			}
			if(parseFloat(netweight) > parseFloat(knetweight)){
				alert("���ϼܾ���(" + netweight + ")�����ܴ����ջ���¼�ġ����ϼܾ���(" + knetweight + ")����");
				return;
			}
			
			if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("���ϼ�ë�ء�ֻ��Ϊ���֣�");
				return;
			}else if(weight == null || weight.length < 1)
			{
				weight = "0";
			}
			if(parseFloat(weight) > parseFloat(kweight)){
				alert("���ϼ�ë��(" + weight + ")�����ܴ����ջ���¼�ġ����ϼ�ë��(" + kweight + ")����");
				return;
			}
			//��������ֵ��֤
			if(myIframe.checkLotatt()==false){
				return;
			}
			
			//���ӵ������*******************************************
			//����һ��
			var objtb = list.document.getElementById("puttb");
			var newRow = null;	//��
			var newCell = null; //��
			
			//����ջ���¼�Ƿ����ظ�
			for(var i=1; i < objtb.rows.length; i++)//���ı����в����ж�
			{
				var rowtransid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
				if(transid == rowtransid){
					alert("���ջ���¼���Ѵ��ڣ���ѡɾ����Ӧ�ջ���¼�����ӣ�");
					return;
				}
			}
			
			//����һ��
	        newRow = objtb.insertRow(objtb.rows.length);
	        newRow.setAttribute("onmouseover", function(){this.bgColor='#E2E8EA'});
			newRow.setAttribute("onmouseout", function(){this.bgColor=''});
			//�к�
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute( "className", "TD_LIST1"); 
			newCell.setAttribute( "align", "center");
			newCell.innerHTML  = "<div align='center'>"+(objtb.rows.length - 1)+ "<input type='hidden' name='key' value='"+transid+"'></div>";
			//���ٺ�
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute( "className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+transid+"</div>";
			//Ʒ��
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+proudctname+"</div>";
			
			//��װ
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+packname+"</div>";
			
			//��λ
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+unitname+"</div>";
			
			//����
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+num+"<input type='hidden' name='nu' value='"+num+"'></div>";
			
			//����
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+netweight+"<input type='hidden' name='netwei' value='"+netweight+"'></div>";
			
			//ë��
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+weight+"<input type='hidden' name='wei' value='"+weight+"'></div>";
			
			//����1
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt1+"<input type='hidden' name='lot1' value='"+lotatt1+"'></div>";
			
			//����2
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt2+"<input type='hidden' name='lot2' value='"+lotatt2+"'></div>";
			
			//����3
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt3+"<input type='hidden' name='lot3' value='"+lotatt3+"'></div>";
			
			//����4
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt4+"<input type='hidden' name='lot4' value='"+lotatt4+"'></div>";
			
			//����5
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt5+"<input type='hidden' name='lot5' value='"+lotatt5+"'></div>";
			
			//����6
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt6+"<input type='hidden' name='lot6' value='"+lotatt6+"'></div>";
			
			//����7
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt7+"<input type='hidden' name='lot7' value='"+lotatt7+"'></div>";
			
			//����8
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt8+"<input type='hidden' name='lot8' value='"+lotatt8+"'></div>";
			
			//����9
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt9+"<input type='hidden' name='lot9' value='"+lotatt9+"'></div>";
			
			//����10
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt10+"<input type='hidden' name='lot10' value='"+lotatt10+"'></div>";
			
			//����11
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt11+"<input type='hidden' name='lot11' value='"+lotatt11+"'></div>";
			
			//����12
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+lotatt12+"<input type='hidden' name='lot12' value='"+lotatt12+"'></div>";
			
			//ɾ��
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST2");
			newCell.setAttribute( "align", "center");
			newCell.setAttribute("onclick", function (){DeleteRow(newRow)});
			newCell.innerHTML = "<input type='button' name='details' class='BUTTON_STYLE1' value='ɾ��'>";
		
		}	
	}
	//ɾ����
	function DeleteRow(obj)
	{
		var tb = list.document.getElementById("puttb");
		if(tb.rows.length > 1)
		{
			tb.deleteRow(obj.rowIndex);
		}
		else
		{
			alert("ҳ�������ݼ�¼��");
		}
 	}

	
 	//��������
	function saveData(invoiceid)
	{
		LockButton();
		
		//��������
		var tray_code = document.getElementById("traycode").value;
		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//����ID
		var whAreaId = document.getElementById("whAreaId").value;
		//�ϼܷ�ʽ
		var putmode = document.getElementById("putmode").value;
		
		if(tray_code != null && tray_code.length>1 && tray_code.length != 8)
		{
			alert("���������롿��Ϊ8λ��Y-000000 ��");
			UnLockButton();
			return;
		}
		if(putmode == null || putmode.length < 1){
			alert("���ϼܷ�ʽ������Ϊ�գ�");
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
		
		//if(whAreaId == null || whAreaId.length < 1){
		//	alert("������������Ϊ�գ�");
		//	UnLockButton();
		//	return;
		//}
		
		//������
		var msg = "";
		var transrows = 0;	//�м�������
		//���
		var objtb = list.document.getElementById("puttb");
		for(var i=1; i<objtb.rows.length; i++){
			transrows = transrows + 1;
			var transid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
			var num = objtb.rows.item(i).cells.item(5).getElementsByTagName("input")[0].value;
			var netweight = objtb.rows.item(i).cells.item(6).getElementsByTagName("input")[0].value;
			var weiht = objtb.rows.item(i).cells.item(7).getElementsByTagName("input")[0].value;
			
			var lotatt1 = objtb.rows.item(i).cells.item(8).getElementsByTagName("input")[0].value;
			var lotatt2 = objtb.rows.item(i).cells.item(9).getElementsByTagName("input")[0].value;
			var lotatt3 = objtb.rows.item(i).cells.item(10).getElementsByTagName("input")[0].value;
			var lotatt4 = objtb.rows.item(i).cells.item(11).getElementsByTagName("input")[0].value;
			var lotatt5 = objtb.rows.item(i).cells.item(12).getElementsByTagName("input")[0].value;
			var lotatt6 = objtb.rows.item(i).cells.item(13).getElementsByTagName("input")[0].value;
			var lotatt7 = objtb.rows.item(i).cells.item(14).getElementsByTagName("input")[0].value;
			var lotatt8 = objtb.rows.item(i).cells.item(15).getElementsByTagName("input")[0].value;
			var lotatt9 = objtb.rows.item(i).cells.item(16).getElementsByTagName("input")[0].value;
			var lotatt10 = objtb.rows.item(i).cells.item(17).getElementsByTagName("input")[0].value;
			var lotatt11 = objtb.rows.item(i).cells.item(18).getElementsByTagName("input")[0].value;
			var lotatt12 = objtb.rows.item(i).cells.item(19).getElementsByTagName("input")[0].value;
			
			msg = msg +"<input type=hidden name='"+transrows+"transid' value='"+transid+"'>"
					+ "<input type=hidden name='"+transrows+"num' value='"+num+"'>"
					+ "<input type=hidden name='"+transrows+"netweight' value='"+netweight+"'>"
					+ "<input type=hidden name='"+transrows+"weiht' value='"+weiht+"'>"
					+ "<input type=hidden name='"+transrows+"volume' value='0'>"
					+ "<input type=hidden name='"+transrows+"lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt12' value='"+lotatt12+"'>";
		}
		//alert(transrows);
		//************************************************
		if(transrows > 0){
			if(confirm("��ȷ���Ƿ������ϼ�����"))
			{		
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction";
	
				//������
				msg = msg + "<input type=hidden name='transrows' value='"+transrows+"'>";
				list.formx1.innerHTML = msg;
				list.formx1.action = strUrl + "&method=createPutaway&invoiceid="+ invoiceid + "&traycode=" + tray_code + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId;
				list.document.formx1.submit();
					
			}else{
				UnLockButton();
			}
		}else{
			alert("��������Ҫ�ϼܵ���Ϣ��");
			UnLockButton();
		}
	}
	
	function OnLoad()
	{
		selectObject('<%=warehouseid%>', 'warehouseid', '1');// �ֿ�
		selectAreaList('', 'whAreaId', '<%=warehouseid%>', "1");// ����
		//�ϼܷ�ʽ
		selectType('PL', 'putmode', 'putmode');
	}
</script>
  </head>
  
  <body onload="javascript:OnLoad();" >
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �ϼ� -&gt; ���ջ���¼�ϼ�</div></td>
   </tr>
  </table>
	
 <!-- ======== �ջ��б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="185">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=putawayAction&flag=1&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== �ջ��б���� ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
            
        <tr>
          <td  align="right" class="yx1"  >�������룺</td>
          <td  class="yx" >
          	 <input type="text" name="traycode"    style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">��&nbsp;&nbsp;&nbsp;�⣺</td>
          <td  class="yx">
	          <select name="warehouseid"  style="width:100px;" >
	            <option>--��ѡ��--</option>
	           </select>
          </td>
          <td  align="right" class="yx1">��&nbsp;&nbsp;&nbsp;����</td>
          <td  class="yx">
	           <select name="whAreaId"  style="width:100px;" >
	            <option>--��ѡ��--</option>
	           </select>
          </td>
          <td  align="right" class="yx1"><span class="red">*</span>�ϼܷ�ʽ��</td>
          <td  class="xx1">
	           <select name="putmode"  style="width:100px;" >
	            <option>--��ѡ��--</option>
	           </select>
          </td>
        </tr>    
            
        <tr>
          <td height="25" colspan="8" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;����" onClick="addData();"  />
            &nbsp;&nbsp;&nbsp;&nbsp;
            
            <input name="button_save" type="button" class="button_edit" id="button" value="&nbsp;&nbsp;�����ϼ�" onclick="saveData('<%=invoiceid%>');" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
<table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== �ϼ��б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top" height="350" > 
					<iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/putaway/inbound_putaway_mgr_put.jsp"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!-- ======== �ϼ��б���� ======== -->
 

  </body>
</html>
