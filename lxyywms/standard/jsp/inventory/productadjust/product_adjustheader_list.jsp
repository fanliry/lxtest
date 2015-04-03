<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryTransferHeader" %>

<html>
<head>
  <title>�ִ����͹���ϵͳ</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript">
  <!--
	//��ȡ��ϸ
    function getDetail(str)
	{
		var actionStr = "BMSService?actionCode=productAdjustAction&method=ricoExecDetail&id=" + str;
		
		window.parent.myIframe2.location.href = "<%=request.getContextPath()%>/" + actionStr;
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
  -->
  </script>
</head>

<body  onload="javascript:OnLoad();">
<%
	List list = null;
	if(request.getAttribute("exResultList") != null)
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
   <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td width="5%" class="TD_LIST_TITLE"><div class="list_title">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll('myform','checkbox_all','checkbox_id')">�к�</div>
	  </td>
	  <td width="10%"  class="TD_LIST_TITLE"><div class="list_title">���������</div></td>
	  <td width="10%"  class="TD_LIST_TITLE"><div class="list_title">��֤����</div></td>
	  <td width="15%"  class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">�ͻ����</div></td>  
	  <td width="18%" class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
	  <td width="17%"  class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td> 
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">����ԭ��</div></td>
	  <td width="15%"  class="TD_LIST_TITLE"><div class="list_title">ԭ������</div></td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
        for(int i=0; i<list.size(); i++)
        {
        	InventoryTransferHeader adjust = (InventoryTransferHeader)list.get(i);
        	String strId = adjust.getTransferid();		//ID
        	String strCustomer = adjust.getCustomerid();		//�ͻ�
        	String strStatus = adjust.getStatus();		//״̬
        	String strEdittime = adjust.getCreatetime();	//����ʱ��
        	String strAdjustTime = adjust.getTransfertime();	//����ʱ��
        	String strReasonCode = adjust.getReasoncode().trim().equals("1") ? "����" : "��Ʒ��������";//����ԭ��
        	String strReasonDescr = adjust.getReasondiscr();//ԭ������
        	String strDocType = adjust.getDoctypeName();//��֤����
            String strStatusName = strStatus.trim().equals("0") ? "����" : "���";
%>
	<tr  onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''"  onclick="getDetail('<%=strId%>')">
	  <td class="TD_LIST1">
	    <input type="checkbox" name="checkbox_id" value="<%=strId%>" id="<%=strStatus%>"  class="input_checkbox" alt="<%=i+1%>">
	  </td>
	  <td class="TD_LIST1"><%=strId%></td>
	  <td class="TD_LIST1"><%=strDocType == null ? "&nbsp;" : strDocType%></td>
	  <td class="TD_LIST1"><%=strStatusName == null ? "&nbsp;" : strStatusName%></td>
	  <td class="TD_LIST1"><%=strCustomer == null ? "&nbsp;" : strCustomer%></td>
	  <td class="TD_LIST1"><%=strEdittime == null ? "&nbsp;" : strEdittime%></td>
	  <td class="TD_LIST1"><%=strAdjustTime == null ? "&nbsp;" : strAdjustTime%></td> 
	  <td class="TD_LIST1"><%=strReasonCode == null ? "&nbsp;" : strReasonCode%></td> 
	  <td class="TD_LIST1"><%=strReasonDescr == null ? "&nbsp;" : strReasonDescr%></td> 
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
    <tr height="100%">
	  <td height="100%" colspan="9" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
