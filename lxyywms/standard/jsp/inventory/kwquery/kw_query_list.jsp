<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace" %>
<%
   
    //���е�������
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//�ı䱳��
    function Change(obj){
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td valign="top" height="100%">
	
  <table width="100%" id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
     	�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��λ��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��λ״̬</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�����</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">������</div></td>
    </tr>
<%
	
	if(ls != null && ls.size() > 0)
	{
		BaseCargospace cargospace = null;
		String strCargo_Space_Id = null;
		String strCargo_Space_Name = null;
		String strCsstatus = null;
		String strInLock = null;
		String strOutLock = null;
		String strCargo_Alley_Name = null;
		String strCarGo_Area_Nmae = null;
		String strWarehouseName = null;
		String strWh_Area_Nmae = null;
		
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 		cargospace = (BaseCargospace)ls.get(i);
  	 		
  	 		strCargo_Space_Id = cargospace.getCargoSpaceId();
			strCargo_Space_Name = cargospace.getCargoSpaceName();
			strCsstatus = cargospace.getCsstatusname();
			strInLock = cargospace.getInlock();
			strOutLock = cargospace.getOutlock();
			strCargo_Alley_Name = cargospace.getCargoAlleyName();
			strCarGo_Area_Nmae = cargospace.getCargoAreaName();
			strWarehouseName = cargospace.getWarehousename();
			strWh_Area_Nmae = cargospace.getWhAreaName();
 
%>
   <tr onMouseOver="this.bgColor='#E3F2FF'" onMouseOut="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=strCargo_Space_Id == null ? "" : strCargo_Space_Id%></td>
     <td class="TD_LIST" align="center"><%=strCargo_Space_Name == null ? "" : strCargo_Space_Name%></td>
     <td class="TD_LIST" align="center"><%=strCsstatus == null ? "" : strCsstatus%></td>
     <td class="TD_LIST" align="center"><%=strCarGo_Area_Nmae == null ? "" : strCarGo_Area_Nmae%></td>
     <td class="TD_LIST" align="center"><%=strCargo_Alley_Name == null ? "" : strCargo_Alley_Name%></td>
     <td class="TD_LIST" align="center"><%=strWh_Area_Nmae == null ? "" : strWh_Area_Nmae%></td>
     <td class="TD_LIST" align="center"><%=strWarehouseName == null ? "" : strWarehouseName%></td>
     <td class="TD_LIST" align="center"><%=strInLock == null ? "" : strInLock%></td>
     <td class="TD_LIST2" align="center"><%=strOutLock == null ? "" : strOutLock%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
 
 
</body>
</html>
