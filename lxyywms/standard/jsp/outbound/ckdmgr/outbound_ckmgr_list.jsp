<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>

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
	function queryDetail(id) {
		parent.document.getElementById("dckdid").innerHTML = id;
		parent.detail.location.href = ac+ "outBoundAction&flag=7&invoiceid=" + id;
	}

	function OnLoad() {
		
		parent.RemoveLoading();
		parent.page.location.reload();

		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				var invoiceid = "<%=request.getAttribute("InvoicedId")==null?"":request.getAttribute("InvoicedId")%>";
				alert("���ɵ���[" + invoiceid + "]�ɹ���");
			}else{
				alert(back_msg);
			}
		}
		
	}

</script>

<body onLoad="OnLoad()">
 <table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�
     </div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">���ݺ�</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">���۵���</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">����״̬</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">�ͻ�</div></td>
     <!-- <td  class="TD_LIST_TITLE"><div class="list_title">�ջ��˵�ַ</div></td> -->
     <td  class="TD_LIST_TITLE"><div class="list_title">����</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		OutboundInvoiceHeader outBound = null; 
		String outstockid;      //���ⵥ�ݱ��
        String outstatus;       //���ⵥ״̬ 1:������2����� 3:����ȷ�� 4:����
        String outtype;         //��������
		String vehicleno;		//����
      	String vehiclepos;		//��λ
      	String sendplatform;	//������̨
        String uploadflag;     	//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
        String customername;	//�ջ���
        String departName;		//����
        String address;		//�ջ��˵�ַ
        
        String saleno="";
		for(int i = 0; i < ls.size(); i++)
		{
			outBound = (OutboundInvoiceHeader)ls.get(i);
			outstockid = outBound.getOutstockid();     	//���ⵥ�ݱ��
	        outstatus = outBound.getM_strOutStatusName();       	//���ⵥ״̬ 1:������2����� 3:����ȷ�� 4:����
	        outtype = outBound.getM_strOutTypeName();         	//���ⵥ������
			vehicleno = outBound.getVehicleno();		//����
      	 	vehiclepos = outBound.getVehiclepos();		//��λ
      	 	sendplatform = outBound.getSendplatform();	//������̨
	        uploadflag = outBound.getIsupload();   		//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
			customername = outBound.getCustomername();	//�ͻ�
			departName = outBound.getDepartName();      //����
			address = outBound.getAddress();//�ջ��˵�ַ
			
			saleno = outBound.getSaleno();
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
     <input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=outstockid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=outstockid%></td>
     <td class="TD_LIST" align="center"><%=saleno%></td>
     <td class="TD_LIST" align="center"><%=outstatus%></td>
     <td class="TD_LIST" align="center"><%=outtype%></td>
     <td class="TD_LIST" align="center"><%=vehicleno == null ? "" : vehicleno%></td>
     <td class="TD_LIST" align="center"><%=vehiclepos == null ? "" : vehiclepos%></td>   
     <td class="TD_LIST" align="center"><%=customername == null ? "" : customername%></td>
     <td class="TD_LIST2" align="center"><%=departName == null ? "" : departName%></td>
   </tr>

  
<%			
		}
	}
	else{
		session.removeAttribute("paging");
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