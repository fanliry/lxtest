<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseAlley" %>
<html>
<head>
<title>�����Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//���õ�ѡ���Ƿ�ѡ��
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
						
		//�ı䱳��ɫ
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//�޸�˫����
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseAlleyAction&flag=2&cargoAlleyId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseAlleyAction&method=ricoExecEdit" + result;
		}
	}
	
	//���ݲֿ��ÿ������б�
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null ? "" : (String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�����ֿ�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">�Ƿ�˫����λ���</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		BaseAlley alley = null; 
		String cargoAlleyId;		// ���ID
		String cargoAlleyName;		// �����
		String whAreaName;			// ������
		String warehousename;		// �ֿ���
		String inlock;				// �����
		String outlock;				// ������
		String istwin;				// �Ƿ�˫����λ���

		for(int i=0; i<ls.size(); i++){
			alley = (BaseAlley)ls.get(i); 
                        
			cargoAlleyId = alley.getCargoAlleyId();	
			cargoAlleyName = alley.getCargoAlleyName();		
			whAreaName = alley.getWhAreaName();	
			warehousename = alley.getWarehousename();
			inlock = alley.getInlock();
			outlock = alley.getOutlock();
			istwin = alley.getIstwin();
			
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=cargoAlleyId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=cargoAlleyId == null ? "" : cargoAlleyId%></td>
     <td class="TD_LIST" align="center"><%=cargoAlleyName == null ? "" : cargoAlleyName%></td>
     <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
     <td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
     <td class="TD_LIST" align="center"><%=inlock == null ? "" : inlock%></td>
     <td class="TD_LIST" align="center"><%=outlock == null ? "" : outlock%></td>
     <td class="TD_LIST2" align="center"><%=istwin == null ? "" : istwin%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="7" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>