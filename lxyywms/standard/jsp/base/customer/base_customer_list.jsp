<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCustomer" %>
<html>
<head>
<title>�ͻ���Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//�ı䱳��ɫ
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		
	}
	
	//�޸�˫����
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseCustomerAction&flag=2&id="+id, "", "dialogWidth=500px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = parent.page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			location.href = ac + "baseCustomerAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	function OnLoad(){

		parent.page.location.reload();
		
		//��������ļ���ֵ
		var obj = parent.document.getElementById("filename");
		obj.outerHTML ? obj.outerHTML+="" : obj.value="";

		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width: 100%; height: 100%; overflow-x:scroll;overflow-y:auto;position:absolute;">
  <table width="120%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr style="position:relative;top:expression(this.offsetParent.scrollTop);">
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ͻ����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ͻ����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ͻ�ȫ��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ͻ�����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��ϵ��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��ϵ�绰</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��ַ</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�ϼܹ���</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��·��</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">��װ</div></td>
      <!--td class="TD_LIST_TITLE2"><div class="list_title">�Ƿ�����</div></td-->
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseCustomer customer = null; 
		
		String customerid;			// �ͻ����
	    String shortname;			// �ͻ����
	    String customername;		// �ͻ�ȫ��
	    String customertypename;	// �ͻ�������
	    String contact;				// ��ϵ��
	    String phone;				// ��ϵ�绰
	    String fax;					// ����
	    String address;				// ��ַ
	    String bank;				// ��������
	    //String useflag;			// �Ƿ�����
	    String putaway;				// �ϼܹ���
	    String allocation;			// �������
	    String replenish;			// ��������
		String linenumber;			// ��·��
		String pkgdesc;				// ��װ����

		for(int i=0; i<ls.size(); i++){
			customer = (BaseCustomer)ls.get(i); 
                        
			customerid = customer.getCustomerid();		
			shortname = customer.getShortname();	
			customername = customer.getCustomername();	
			customertypename = customer.getCustomertypename();		
			contact = customer.getContact();
			phone = customer.getPhone();
			fax = customer.getFax();
			address = customer.getAddress();
			bank = customer.getBank();
			//useflag = customer.getUseflag();
		    putaway = customer.getPutawayname();			// �ϼܹ���
		    allocation = customer.getAllocationname();		// �������
		    replenish = customer.getReplenishname();		// ��������
			linenumber = customer.getLinenumber();
			pkgdesc = customer.getPkgdesc();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" class="input_checkbox" value="<%=customerid%>" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=customerid == null ? "" : customerid%></td>
     <td class="TD_LIST" align="center"><%=shortname == null ? "" : shortname%></td>
     <td class="TD_LIST" align="center"><%=customername == null ? "" : customername%></td>
     <td class="TD_LIST" align="center"><%=customertypename == null ? "" : customertypename%></td>
     <td class="TD_LIST" align="center"><%=contact == null ? "" : contact%></td>
     <td class="TD_LIST" align="center"><%=phone == null ? "" : phone%></td>
     <td class="TD_LIST" align="center"><%=fax == null ? "" : fax%></td>
     <td class="TD_LIST" align="center"><%=address == null ? "" : address%></td>
     <td class="TD_LIST" align="center"><%=bank == null ? "" : bank%></td>
     <td class="TD_LIST" align="center"><%=putaway == null ? "" : putaway%></td>
     <td class="TD_LIST" align="center"><%=allocation == null ? "" : allocation%></td>
     <td class="TD_LIST" align="center"><%=replenish == null ? "" : replenish%></td>
     <td class="TD_LIST" align="center"><%=linenumber == null ? "" : linenumber%></td>
     <td class="TD_LIST2" align="center"><%=pkgdesc == null ? "" : pkgdesc%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="15" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   
 </table>
</div>
</body>
</html>