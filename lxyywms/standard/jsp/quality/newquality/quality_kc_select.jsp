 <%@ page contentType="text/html; charset=GBK"%>
<%
	String warehouseid = "";
	if(request.getParameter("warehouseId") != null){
		warehouseid = request.getParameter("warehouseId");
	}

	String whAreaId = "";
	if(request.getParameter("whAreaId") != null){
		whAreaId = request.getParameter("whAreaId");
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	function OnOk(){
	    var myValue = new Array();
		var j = 0;
		var back_msg = "";
		
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
			
				var strDel =check_ids[i].value;
				var cs = strDel.split("|");
				myValue[j] = cs;
				j++;			
			}
		}
		
		if(myValue.length > 0)
		{
			window.returnValue=myValue;
        	window.close();
		}else
		{
			alert("��ѡ���¼!");
		}

	}
	
	//��ѯ
	function queryData(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value;
		var cargoAreaId = document.getElementById("cargoAreaId").value;
		var platoon = document.getElementById("platoon").value;
		var column = document.getElementById("column").value;
		var floor = document.getElementById("floor").value;
		var productid = document.getElementById("package_id").value;	//Ʒ��
		var traycode = document.getElementById("traycode").value;
		var ownerid = document.getElementById("owner_id").value;
		
		if("<%=warehouseid%>" != warehouseid){
			 alert("ѡ��ֿⲻһ�£�");
			 return;
		}
		if(warehouseid=="" || whAreaId==""){
			alert("��ѡ��ֿ�Ϳ�����");
			return;
		}

		if(platoon!=null && platoon!=""){
			if(!numChar(platoon) || platoon<=0){
				alert("���š������Ǵ����������!");
				return false;
			}
		}
		
		if(column!=null && column!=""){
			if(!numChar(column) || column<=0){
				alert("���С������Ǵ����������!");
				return false;
			}
		}
		
		if(floor!=null && floor!=""){
			if(!numChar(floor) || floor<=0){
				alert("���㡿�����Ǵ����������!");
				return false;
			}
		}
		//ÿҳ��ʾ����
		var linerow = list.document.getElementById("lineviewrow").value;		
		condition = "&warehouseid=" +��warehouseid + "&whAreaId=" +��whAreaId + "&cargoAreaId=" +��cargoAreaId + "&platoon=" +��platoon 
				+ "&column=" +��column + "&floor=" +��floor + "&productid=" +��productid + "&traycode=" + traycode + "&ownerid=" + ownerid
			    + "&linerow=" + linerow;
			    //ÿҳ��ʾ����			
		list.formx1.innerHTML = getLotattCon();
		list.formx1.action = strUrl + "qualityAction" + condition;
		list.document.formx1.submit();		
		Loading();	
	}
	
	//���ݲֿ��ÿ������������б�
	function getAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
	}
	//�����������ԵĲ�ѯ����
	function getLotattCon(){
	
		var lotid = document.getElementById("lotid").value
		//��������ֵ����������ڷ�Χ�ģ���ʽ���£�2012-08-30|2012-08-31
		var lotatt1 = ""; //��������1
		var lotatt2 = ""; //��������2
		var lotatt3 = ""; //��������3
		var lotatt4 = ""; //��������4
		var lotatt5 = ""; //��������5
		var lotatt6 = ""; //��������6
		var lotatt7 = ""; //��������7
		var lotatt8 = ""; //��������8
		var lotatt9 = ""; //��������9
		var lotatt10 = "";//��������10
		var lotatt11 = "";//��������11
		var lotatt12 = "";//��������12
		//����  0����ʾ���õģ�1����ʾ��ȷ���Ϳ��ѯ�� 2����ʾ����Ҫȡ��Χ��
		var lt1 = document.getElementById("lt1").value; //����1������
		if(lt1 != null && lt1.length >0){
			if(lt1 == "0"){
				lotatt1 = "";
			}else if(lt1 == "2"){
				lotatt1 = document.getElementById("lotatt1_from").value + "|" + document.getElementById("lotatt1_to").value;
			}else{
				lotatt1 = document.getElementById("lotatt1").value;
			}	
		}else{ //û��ֵ
			lotatt1 = "";
		}
		
		var lt2 = document.getElementById("lt2").value; //����2������
		if(lt2 != null && lt2.length >0){
			if(lt2 == "0"){
				lotatt2 = "";
			}else if(lt2 == "2"){
				lotatt2 = document.getElementById("lotatt2_from").value + "|" + document.getElementById("lotatt2_to").value;
			}else{
				lotatt2 = document.getElementById("lotatt2").value;
			}	
		}else{ //û��ֵ
			lotatt2 = "";
		}
		
		var lt3 = document.getElementById("lt3").value; //����3������
		if(lt3 != null && lt3.length >0){
			if(lt3 == "0"){
				lotatt3 = "";
			}else if(lt3 == "2"){
				lotatt3 = document.getElementById("lotatt3_from").value + "|" + document.getElementById("lotatt3_to").value;
			}else{
				lotatt3 = document.getElementById("lotatt3").value;
			}	
		}else{ //û��ֵ
			lotatt3 = "";
		}
		
		var lt4 = document.getElementById("lt4").value; //����4������
		if(lt4 != null && lt4.length >0){
			if(lt4 == "0"){
				lotatt4 = "";
			}else if(lt4 == "2"){
				lotatt4 = document.getElementById("lotatt4_from").value + "|" + document.getElementById("lotatt4_to").value;
			}else{
				lotatt4 = document.getElementById("lotatt4").value;
			}	
		}else{ //û��ֵ
			lotatt4 = "";
		}
		
		var lt5 = document.getElementById("lt5").value; //����5������
		if(lt5 != null && lt5.length >0){
			if(lt5 == "0"){
				lotatt5 = "";
			}else if(lt5 == "2"){
				lotatt5 = document.getElementById("lotatt5_from").value + "|" + document.getElementById("lotatt5_to").value;
			}else{
				lotatt5 = document.getElementById("lotatt5").value;
			}	
		}else{ //û��ֵ
			lotatt5 = "";
		}
		
		var lt6 = document.getElementById("lt6").value; //����6������
		if(lt6 != null && lt6.length >0){
			if(lt6 == "0"){
				lotatt6 = "";
			}else if(lt6 == "2"){
				lotatt6 = document.getElementById("lotatt6_from").value + "|" + document.getElementById("lotatt6_to").value;
			}else{
				lotatt6 = document.getElementById("lotatt6").value;
			}	
		}else{ //û��ֵ
			lotatt6 = "";
		}
		
		var lt7 = document.getElementById("lt7").value; //����7������
		if(lt7 != null && lt7.length >0){
			if(lt7 == "0"){
				lotatt7 = "";
			}else if(lt7 == "2"){
				lotatt7 = document.getElementById("lotatt7_from").value + "|" + document.getElementById("lotatt7_to").value;
			}else{
				lotatt7 = document.getElementById("lotatt7").value;
			}	
		}else{ //û��ֵ
			lotatt7 = "";
		}
		
		var lt8 = document.getElementById("lt8").value; //����8������
		if(lt8 != null && lt8.length >0){
			if(lt8 == "0"){
				lotatt8 = "";
			}else if(lt8 == "2"){
				lotatt8 = document.getElementById("lotatt8_from").value + "|" + document.getElementById("lotatt8_to").value;
			}else{
				lotatt8 = document.getElementById("lotatt8").value;
			}	
		}else{ //û��ֵ
			lotatt8 = "";
		}
		
		var lt9 = document.getElementById("lt9").value; //����9������
		if(lt9 != null && lt9.length >0){
			if(lt9 == "0"){
				lotatt9 = "";
			}else if(lt9 == "2"){
				lotatt9 = document.getElementById("lotatt9_from").value + "|" + document.getElementById("lotatt9_to").value;
			}else{
				lotatt9 = document.getElementById("lotatt9").value;
			}	
		}else{ //û��ֵ
			lotatt9 = "";
		}
		
		var lt10 = document.getElementById("lt10").value; //����10������
		if(lt10 != null && lt10.length >0){
			if(lt10 == "0"){
				lotatt10 = "";
			}else if(lt10 == "2"){
				lotatt10 = document.getElementById("lotatt10_from").value + "|" + document.getElementById("lotatt10_to").value;
			}else{
				lotatt10 = document.getElementById("lotatt10").value;
			}	
		}else{ //û��ֵ
			lotatt10 = "";
		}
		
		var lt11 = document.getElementById("lt11").value; //����11������
		if(lt11 != null && lt11.length >0){
			if(lt11 == "0"){
				lotatt11 = "";
			}else if(lt11 == "2"){
				lotatt11 = document.getElementById("lotatt11_from").value + "|" + document.getElementById("lotatt11_to").value;
			}else{
				lotatt11 = document.getElementById("lotatt11").value;
			}	
		}else{ //û��ֵ
			lotatt11 = "";
		}
		
		var lt12 = document.getElementById("lt12").value; //����12������
		if(lt12 != null && lt12.length >0){
			if(lt12 == "0"){
				lotatt12 = "";
			}else if(lt12 == "2"){
				lotatt12 = document.getElementById("lotatt12_from").value + "|" + document.getElementById("lotatt12_to").value;
			}else{
				lotatt12 = document.getElementById("lotatt12").value;
			}	
		}else{ //û��ֵ
			lotatt12 = "";
		}
		
		//������  
		var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
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
					+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>"
					+ "<input type=hidden name='lotid' value='"+lotid+"'>";
					
		return msg;
	}
	//ѡ�����κ���ʾ�����ε���ϸ����
	function viewLot(){
	
		var tableObj = document.getElementById("marginTable");
		var lotid = document.getElementById("lotid").value;
		
		//2�ǲ�ѯ����������, 4��ÿ����ʾ��td��
		createLotDetail(tableObj, lotid, 3, 4);
		
	}
	//ҳ���¼
	function OnLoad(){	
		//ͬ��
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		if("<%=warehouseid%>" != ""){
			document.getElementById("warehouseid").value = "<%=warehouseid%>";
			
		}
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
	
		DWREngine.setAsync(true);
		
		if("<%=whAreaId%>" != ""){
			document.getElementById("whAreaId").value = "<%=whAreaId%>";
		}
		selectLot("", "lotid");     //��������
     	
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" name="condition">
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ѡ���λ</div></td>
    </tr>
  </table>
 
  <table id="marginTable" width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" align="right"><div align="right">�ֿ⣺</div></td>
	  <td class="yx"><select id="warehouseid" onChange="getAreaList()" style="width:150px;"><option value=""></option></select></td>
	  <td class="yx1" align="right"><div align="right">������</div></td>
      <td class="yx"><select name="whAreaId" style="width:150px;"><option value=""></option></select></td>
	  <td class="yx1" align="right">������</td>
	  <td class="yx"><select name="cargoAreaId" style="width:150px;"><option value=""></option></select></td> 
	  <td class="yx1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</div></td>
                <td class="yx">
          	     <input type="text" name="owner_name"  readonly="readonly" class="input_readonly"  style="height:18px;width:100px;"/>
                 <input type="hidden" name="owner_id" />
                 <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520','owner_id','owner_name');" />
                 </td>
	</tr>
	<tr>
   	  <td class="yx1" align="right">��λ��</td>
   	  <td class="yx" style="width:150px;">
   	    <input type="text" name="platoon" size="3" maxlength="2">��
   	    <input type="text" name="column" size="3" maxlength="2">��
   	    <input type="text" name="floor" size="3" maxlength="2">��
   	  </td>
   	   <td class="y1" align="right">Ʒ����</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  
            	type="button" value="��" class="button_select">
	      </td>
	  <td class="yx1" align="right">�������룺</td>
   	  <td class="yx" style="width:150px;">
   	    <input type="text" name="traycode" size="15">
   	  </td>
	  <td class="yx1" align="right">�������ԣ�</td>
     	  <td class="xx1"><select id="lotid" onchange="viewLot();" ><option value=""></option></select>
     	  	<input type="hidden" name="lt1">	
     	    <input type="hidden" name="lt2">
     	    <input type="hidden" name="lt3">
     	    <input type="hidden" name="lt4">
     	    <input type="hidden" name="lt5">
     	    <input type="hidden" name="lt6">
     	    <input type="hidden" name="lt7">
     	    <input type="hidden" name="lt8">
     	    <input type="hidden" name="lt9">
     	    <input type="hidden" name="lt10">
     	    <input type="hidden" name="lt11">
     	    <input type="hidden" name="lt12">
     	 </td>
    </tr>	 
    
  </table>

    </td></tr>
    <tr> <td height="5" colspan="8" align="center" valign="bottom" ></td></tr>
    <tr>
      <td height="15" colspan="8" align="center" valign="bottom" >
        <input type="button" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search" onClick="queryData()">
            <input type="button" value="ȷ��" class="BUTTON_STYLE1" onClick="OnOk()">
           	<input type="button" value="�ر�" class="BUTTON_STYLE1" onClick="window.close();" />
      </td>
    </tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      	 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/quality/newquality/quality_kc_list.jsp"
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		   <!--��ҳ��ѯ 
          <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>-->
		
		</table>
	  </td>
    </tr>   
  </table> 
 
 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
