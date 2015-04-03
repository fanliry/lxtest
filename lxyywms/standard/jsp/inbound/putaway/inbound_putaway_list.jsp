<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>
	function Change(obj)
	{
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
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
	
	//��ȡ��ϸ
	var oldobj = null;
    function getDetail(strId, obj)
	{
		obj.style.backgroundColor = "#99CCFF";
		if(oldobj != null){
			if(oldobj.cells.item(0).getElementsByTagName("input")[0].checked == false)
			{
				oldobj.style.backgroundColor = "";	
			}
		}
		oldobj = obj;
		var actionStr = "BMSService?actionCode=receiptAction&flag=4&invoiceid=" + strId;
		window.parent.tableheight2.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert(back_msg);
			}else{
				alert(back_msg);
			}
		}
	}

</script>

<body  id="table_body" onload="javascript:OnLoad();">
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�
     </div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">���ݺ�</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">����״̬</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">��Ӧ��</div></td>
     <td  class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td  class="TD_LIST_TITLE2"><div class="list_title">���ر�����</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		InboundReceiptHeader inBound = null;  
		String instockid;      //��ⵥ�ݱ��
        String instatus;       //����״̬ 0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�6-����
        String intype;         //�������
		String ownername;      //����	
        String createdate;     //��������ʱ��
        String createmanid;    //����������Ա���
        String suppliername ;   //��Ӧ����Ϣ
        String shippername;     //��������Ϣ
        String customsno;       //���ر�����
        
        String m_strStatusName;          // ״̬��
        String m_strTypeName;            // ������
        String createman;                // ����������Ա
        String warehouseid;				 //�ֿ�ID

		for(int i = 0; i < ls.size(); i++)
		{
			inBound = (InboundReceiptHeader)ls.get(i);
			instockid = inBound.getReinvoiceid();   //��ⵥ�ݱ��
	        instatus = inBound.getInstatus();       //����״̬ 0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�6-����
	        intype = inBound.getIntype();         	//�������
			ownername = inBound.getOwnername();     //����
	        createdate = inBound.getCreatetime();   //��������ʱ��
	        createmanid = inBound.getCreatemanid(); //����������Ա���
	        suppliername = inBound.getSuppliername() ; //��Ӧ����Ϣ
         	shippername = inBound.getShippername();    //��������Ϣ
         	customsno = inBound.getCustomsno();     //���ر�����
         	warehouseid = inBound.getWarehouseid();	//�ֿ�ID
	        
	        
	        m_strStatusName = inBound.getM_strStatusName();    // ״̬��
         	m_strTypeName = inBound.getM_strTypeName();        // ������
         	createman = inBound.getCreateman();                // ����������Ա
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="getDetail('<%=instockid%>', this)">
     <td class="TD_LIST1" align="center"><input onClick="Change(this)" type="checkbox" name="check_id" class="input_checkbox" alt="<%=warehouseid%>" value="<%=instockid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
     <td class="TD_LIST" align="center"><%=m_strTypeName%></td>
     <td class="TD_LIST" align="center"><%=ownername == null? "&nbsp" : ownername%></td>
     <td class="TD_LIST" align="center"><%=createman%></td>   
     <td class="TD_LIST" align="center"><%=createdate%></td>
     <td class="TD_LIST" align="center"><%=suppliername == null? "&nbsp" : suppliername%></td>   
     <td class="TD_LIST" align="center"><%=shippername == null? "&nbsp" : shippername%></td>
     <td class="TD_LIST2" align="center"><%=customsno == null? "&nbsp" : customsno%></td>
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
