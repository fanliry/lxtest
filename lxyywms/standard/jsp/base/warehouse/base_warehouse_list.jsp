<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWarehouse" %>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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

		var result = showModalDialog(ac + "baseWarehouseAction&flag=2&warehouseid="+id, "", "dialogWidth=600px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseWarehouseAction&method=ricoExecEdit" + result;
		}
	}
-->
</script>
</head>

<body>
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ֿ����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ֿ��ַ</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ֿ����Ա</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��ϵ��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��ϵ�绰</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">ERP����</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">�Ƿ�ǰ�ֿ�</div></td>
    </tr>
   
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseWarehouse wh = null;
		
		String warehouseid;			// �ֿ�ID
        String warehousename;		// �ֿ���
        String whaddress;			// �ֿ��ַ
        String whmgrman;			// �ֿ����Ա
        String whlinkman;			// ��ϵ��
        String whlinktel;			// ��ϵ�绰
        String warehousetypename;	// �ֿ�������
        String erpcode;				// ��ӦERP�Ĵ���
        String iscurrent;			//�Ƿ�ǰ�ֿ�
    
		for(int i=0; i<ls.size(); i++){

			wh = (BaseWarehouse)ls.get(i);
			warehouseid = wh.getWarehouseid();
			warehousename = wh.getWarehousename();
			whaddress = wh.getWhaddress();
			whmgrman = wh.getWhmgrman();
			whlinkman = wh.getWhlinkman();
			whlinktel = wh.getWhlinktel();
			warehousetypename = wh.getWarehousetypename();
			erpcode = wh.getErpcode();
			iscurrent = wh.getIscurrent().equals("Y")?"��":"";	
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>);" ondblclick="upd(<%=i%>)">
      <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=warehouseid%>"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=warehouseid == null ? "" : warehouseid%></td>
      <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
      <td class="TD_LIST" align="center"><%=whaddress == null ? "" : whaddress%></td>
      <td class="TD_LIST" align="center"><%=whmgrman == null ? "" : whmgrman%></td>
      <td class="TD_LIST" align="center"><%=whlinkman == null ? "" : whlinkman%></td>
      <td class="TD_LIST" align="center"><%=whlinktel == null ? "" : whlinktel%></td>
      <td class="TD_LIST" align="center"><%=erpcode == null ? "" : erpcode%></td>
      <td class="TD_LIST2" align="center"><%=iscurrent == null ? "" : iscurrent%></td>
    </tr>
<%
        }
    }
%>
    <tr>
      <td height="100%" colspan="9" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>

  </table>

</body>
</html>
