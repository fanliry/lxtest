<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>

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
		var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecDetail&id=" + str;
		
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
	  <td class="TD_LIST_TITLE"><div class="list_title">
	    �к�</div>
	  </td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>  
	  <td class="TD_LIST_TITLE"><div class="list_title">����ԭ��</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">ԭ������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���ʱ��</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td> 

	  <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�����</div></td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
        for(int i=0; i<list.size(); i++)
        {
        	InventoryAdjustHeader adjust = (InventoryAdjustHeader)list.get(i);
        	String strId = adjust.getAdjustid();		//ID
        	String strwharehouseid = adjust.getWarehouseid();		//�ֿ�
        	String strWhname = adjust.getWarehousename();
        	String strAdjusttypename = adjust.getAdjusttypename();//��������
        	String strAdjusttype = adjust.getAdjusttype();//��������
        	String strEdittime = adjust.getCreatetime();	//����ʱ��
        	String strAdjustTime = adjust.getAdjusttime();	//����ʱ��
        	String strReasonCode = adjust.getAdjustreasonname();//����ԭ��
        	String strReasonDescr = adjust.getReasondiscr();//ԭ������
            String strStatusName = adjust.getAdjuststatusname();//����״̬
            String strAuditTime = adjust.getAuditdate();//���ʱ��
            String strAuditMan = adjust.getAuditmanname();//�����
            String strCreateMan = adjust.getCreatemanname();//������
            String strStatusid = adjust.getStatus();
        	String values = strId+"&wharehouseid="+strwharehouseid+"&adjusttype="+strAdjusttype+"&status="+strStatusid;
%>
	<tr  onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''"  onclick="getDetail('<%=strId%>')">
	  <td class="TD_LIST1">
	    <input type="checkbox" name="checkbox_id" value="<%=values%>"  class="input_checkbox" alt="<%=i+1%>">
	  </td>
	  <td class="TD_LIST1" align="center"><%=strId%></td>
	  <td class="TD_LIST1" align="center"><%=strAdjusttypename == null ? "&nbsp;" : strAdjusttypename%></td>
	  <td class="TD_LIST1" align="center"><%=strStatusName == null ? "&nbsp;" : strStatusName%><input type="hidden" name="status" value="<%=strStatusid %>"></td>
	  <td class="TD_LIST1" align="center"><%=strWhname == null ? "&nbsp;" : strWhname%></td>
	  <td class="TD_LIST1" align="center"><%=strReasonCode == null ? "&nbsp;" : strReasonCode%></td> 
	  <td class="TD_LIST1" align="center"><%=strReasonDescr == null ? "&nbsp;" : strReasonDescr%></td> 
	  <td class="TD_LIST1" align="center"><%=strEdittime == null ? "&nbsp;" : strEdittime%></td> 
	  <td class="TD_LIST1" align="center"><%=strAuditTime == null ? "&nbsp;" : strAuditTime%></td> 
	  <td class="TD_LIST1" align="center"><%=strAdjustTime == null ? "&nbsp;" : strAdjustTime%></td> 
	  <td class="TD_LIST1" align="center"><%=strCreateMan == null ? "&nbsp;" : strCreateMan%></td> 
	  <td class="TD_LIST1" align="center"><%=strAuditMan == null ? "&nbsp;" : strAuditMan%></td> 
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
    <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
