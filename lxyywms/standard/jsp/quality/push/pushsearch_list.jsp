<%@page import="com.wms3.bms.standard.entity.quality.Release"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap" %>
<%
	String strTime = StrTools.getCurrDateTime(8);
	String strWarehouseId = (String) request.getSession(false).getAttribute("warehouseid");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�㽭���������ֿ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>	
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != ""){
		 	if(back_msg != "Y")
		 	{
			    alert(back_msg);
		 	}else
		 	{
		 		alert("�����ɹ�");
		 	}
		}
	}

</script>
</head>
<body   onload="javascript:OnLoad();">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="ty" width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
  
   <tr height="10px">
     <td width="50" class="TD_LIST_TITLE"align="center"><div class="list_title">�к�
     </div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">�������</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">�ʼ쵥��</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ʱ��</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">������</div></td>
   </tr>
   <%
   		List ls = (List)request.getAttribute("exResultList");
   
   		Release stask = null;
		String Releaseid;       //����ID
	 	String Productid;      	//��ƷID
	 	String lotinfo;			//�������� (�������)
	 	String Qualityid ;		//�ʼ쵥��
	 	String createtime; 		//����ʱ��   ʱ���ʽ��yyyy-MM-dd hh:mm:ss
	 	String opManId;    		//������
	 	
		//��������
	    String Productcode;     //��Ʒ����
	    String ProductName;   	//��Ʒ����
    
   		if(ls != null && ls.size() > 0)
   		{
   			for(int i = 0; i < ls.size(); i++)
   			{
   				Release release = (Release)ls.get(i);
   				lotinfo = release.getLotinfo();
   				ProductName = release.getProductName();
   				Productcode = release.getProductcode();
   				Qualityid = release.getQualityid();
   				createtime = release.getCreatetime();
   				opManId = release.getOpManId();

    %>
   <tr onclick="">
	<td class="TD_LIST1" align="center"><%=i+1%></td>
   	<td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
   	<td class="TD_LIST" align="center"><%=ProductName == null ? "" : ProductName%></td>
   	<td class="TD_LIST" align="center"><%=Productcode == null ? "" : Productcode%></td>
   	<td class="TD_LIST" align="center"><%=Qualityid == null ? "" : Qualityid%></td>
   	<td class="TD_LIST" align="center"><%=createtime == null ? "" : createtime%></td>
   	<td class="TD_LIST" align="center"><%=opManId == null ? "" : opManId%></td>

   </tr>
   <%
   			}
   		}else{
			session.removeAttribute("paging");
		}
    %>
    <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
 </div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
