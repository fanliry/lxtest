<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
  	
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
			
			queryDetail(check_ids[i].value);
		}
	}
	
	//��ѯ��ϸ
	function queryDetail(id){
	    parent.document.getElementById("dckdid").innerHTML = id;
		parent.detail.location.href = ac + "inBoundPoAction&flag=7&poid=" + id;
	}
	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}
			else{
				alert(back_msg);
			}
		}
	}

</script>

<body onLoad="OnLoad()">
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
	    <td width="50" class="TD_LIST_TITLE1"><div class="list_title">�к�</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">���ݺ�</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">��Ӧ��</div></td>
		<td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
   </tr>
<%
	List<InboundPoHeader> ls = (List<InboundPoHeader>)request.getAttribute("exResultList");
		if(ls != null && ls.size() > 0) {
			InboundPoHeader poHeader = null;
			String poid;// PO Id
			String potype="";// PO ���� 
			String postatus="";//PO ״̬0-����
			String createtime="";//PO����ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
			String createdate="";//PO�������� ���ڸ�ʽ��yyyy-MM-dd 
			String createmanid="";//PO������
			String warehouseid="";//�ֿ��� 
			String customerid="";//�ͻ�ID
			String arrivestarttime="";//Ԥ�ڵ���ʱ��(from) ʱ���ʽ��yyyy-MM-dd hh:mm:ss 
			String arriveendtime="";//Ԥ�ڵ���ʱ��(to) ʱ���ʽ��yyyy-MM-dd hh:mm:ss
			String shipperid="";//������
			String supplierid="";//��Ӧ��
			String reserve1= ""; //Ӫ������
			String SupplierName="";
			for(int i=0;i<ls.size();i++){
				poHeader = ls.get(i);
				poid = poHeader.getPoid();
				potype = poHeader.getPotypeName();
				postatus = poHeader.getPostatus();
				createdate = poHeader.getCreatedate();
				createtime = poHeader.getCreatetime();
				createmanid = poHeader.getCreatemanid();
				warehouseid = poHeader.getWarehouseid();
				customerid = poHeader.getCustomerName();
				if(customerid==null){
				   customerid= "";
				}
				shipperid = poHeader.getShipperid();
				SupplierName = poHeader.getSupplierName();
				supplierid =poHeader.getSupplierid();
				if(supplierid==null){
				   supplierid= "";
				}
				reserve1 = poHeader.getReserve1();
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=poid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=poid%></td>
	 <td class="TD_LIST" align="center"><%=potype == null? "" : potype %></td>
	 <td class="TD_LIST" align="center"><%=SupplierName == null? "" : SupplierName%></td>
	 <td class="TD_LIST" align="center"><%=createdate%></td>
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="5" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>
