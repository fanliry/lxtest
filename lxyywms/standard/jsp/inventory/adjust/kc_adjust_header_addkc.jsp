<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	//String id = request.getParameter("id"); 
	String wharehouseid = request.getParameter("wharehouseid"); 
%>
<html>
<head>
<title>���ӿ�������</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	//����
	function saveData()
	{
	   var warehouseid = document.getElementById("warehouseid").value;
	   var strwhareaid = document.getElementById("whAreaId").value;//����
	   var strcargospaceid = document.getElementById("cargospace_id").value;//��λ
	   var strpackageid = document.getElementById("package_id").value;//��Ʒid
	   var strprintdate = document.getElementById("indate_from").value;//��������
	   var strpunit= document.getElementById("punit").value;//��λ
	   var strlotid = document.getElementById("lotid").value;//��������
	   var strlotinfo= document.getElementById("lotinfo").value;//������Ϣ
	   var strtraycode= document.getElementById("traycode").value;//��������
	   var strpnum= document.getElementById("pnum").value;//ϵͳ����
	   if(strwhareaid == null ||  strwhareaid.length<1)
		{
			alert("������������Ϊ��");
			return;
		}
	   if(strcargospaceid == null ||  strcargospaceid.length<1)
		{
			alert("����λ������Ϊ��");
			return;
		}
		if(strpackageid == null || strpackageid.length<1)
		{
			alert("����Ʒ������Ϊ��");
			return;
		}
		if(strprintdate == null || strprintdate.length<1)
		{
			alert("���������ڡ�����Ϊ��");
			return;
		}
		if(strlotid == null || strlotid.length<1)
		{
			alert("���������͡�����Ϊ��");
			return;
		}
		if(strlotinfo == null || strlotinfo.length<1)
		{
			alert("��������Ϣ������Ϊ��");
			return;
		}
		if(strtraycode == null || strtraycode.length<1)
		{
			alert("���������롿����Ϊ��");
			return;
		}
		if(strpnum == null || strpnum.length<1)
		{
			alert("������������Ϊ��");
			return;
		}
		
		var strParam = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecAddDetail" 
			                + "&flag=3"	
			                + "&warehouseid="+warehouseid
			                + "&whareaid="+strwhareaid
				            + "&cargospaceid="+strcargospaceid
        					+ "&packageid="+strpackageid
        					+ "&printdate="+strprintdate
        					+ "&punit="+strpunit
							+ "&lotid=" + strlotid
							+ "&lotinfo="+strlotinfo
        					+ "&traycode="+strtraycode
							+ "&pnum=" + strpnum;    
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
		return true;
	}
	function basCustomer(obj, url, obj1)
    {
		var strUrl=url + '/jsp/selectPages/customer_select.jsp';
		var result = showModalDialog(strUrl,'','dialogWidth=650px;dialogHeight=440px');	
		if(result != null && result.length > 0)
		{
			 var tempParam = result;
	
			 var temStr = tempParam.split("|");
			 		
			 document.getElementById(obj).value = temStr[0];
			 if(obj1)
			 {
			 	document.getElementById(obj1).value = temStr[1];
			 }
		}
    }
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	//ҳ���¼
	function OnLoad(){
		//�ֿ�
		DWREngine.setAsync(false);
		//selectObject("<%=wharehouseid%>", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//����
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectType('', 'punit', 'punit');
		selectObject("", "lotid", "phmc");	
	}
-->
</script>
</head>
<%
	String strTime = StrTools.getCurrDateTime(8);
%>
<body onLoad="OnLoad()">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt;������ -&gt; ���ӿ��</div></td>
   </tr>
 </table>
 <form>
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1" width="100" align="right">�ֿ⣺</td>
	 <td class="yx"><input id="warehouseid" type="text" value="<%=wharehouseid%>" style="width:117px;" disabled></td>    
	 <td class="yx1" width="100" align="right">������</td>
     <td class="yx"><select id="whAreaId"style="width:117px;"><option value=""></option></select></td>   	  
     <td class="yx1" width="100px" align="right">��λ��</td>
	 <td class="xx1">
		    <input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" style="width:117px;" readonly>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid='+document.getElementById('warehouseid').value+'&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       			type="button" value="��" class="button_select">
     </td>
     </tr> 
     <tr>
	 <td class="yx1" align="right">Ʒ����</td>
	 <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" style="width:117px;" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	 </td>
     <td class="yx1" align="right">�������ڣ�</td>
	 <td class="yx">
	     <input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;">
	 </td>
	  <td class="yx1" align="right">��λ��</td>
	  <td class="xx1">
	      	<select id="punit" name="punit" style="width:117px;">
                <option value="">--��ѡ��--</option>
              </select>
	      </td>
	  </tr>
      <tr>
	  <td class="yx1" align="right">�������ͣ�</td>
	  <td class="yx">
	      	<select id="lotid" style="width:117px;">
                <option value="">--��ѡ��--</option>
              </select>
	  </td>
	  <td class="yx1" align="right">������Ϣ��</td>
      <td class="yx"><div align="left">
     		<input type="text" size="10" style="width:117px;" name="lotinfo">    
      </div></td>
     <td class="yx1"><div align="right">�������룺</div></td>
     <td class="xx1"><div align="left">
     		<input type="text" style="width:117px;" size="10" name="traycode">    
     </div></td>
     </tr>
     <tr>
     <td class="yx1"><div align="right">���������</div></td>
     <td class="xx1" colspan="5"><div align="left">
     		<input type="text" style="width:117px;" size="20" name="pnum">    
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="6" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;����" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
</body>
</html>