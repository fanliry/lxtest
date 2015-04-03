<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null && list_items !=""){
		items = list_items.split(",");
		itemscount = items.length;
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="130%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
<%	
	for(int i=0; i<itemscount; i++){
%>
     <td class="TD_LIST_TITLE"><div class="list_title"><%=items[i]%></div></td>
<%
	}
%>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ë��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��澻��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���Ά��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
   </tr>
<%
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
		String whArea;				//����
		String punit;				//��λ
		double pnum;            	//�������
		long boxnum;          		//�������
		double pweight;         	//���ë��
     	double pnetweight;      	//��澻��
     	double pvolume;         	//������
		double holdnum;         	//��������
		double holdweight;      	//����ë��
		double holdnetweight;      	//���Ά��
		double holdvolume;         	//�������

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
			ob = (Object[])ls.get(i);
  	 		whArea = ob[0] == null ? "" : ob[0].toString();			//����
  	 		punit = ob[itemscount+1] == null ? "" : ob[itemscount+1].toString();			//��λ
  	 		pnum = Double.parseDouble(ob[itemscount+2].toString());			//�������
  	 		boxnum = Long.parseLong(ob[itemscount+3].toString());				//�������
  	 		pweight = Double.parseDouble(ob[itemscount+4].toString());        	//���ë��
  	 		pnetweight = Double.parseDouble(ob[itemscount+5].toString());     	//��澻��
  	 		pvolume = Double.parseDouble(ob[itemscount+6].toString());        	//������
  	 		holdnum = Double.parseDouble(ob[itemscount+7].toString());			//��������
  	 		holdweight = Double.parseDouble(ob[itemscount+8].toString());     	//����ë��
			holdnetweight = Double.parseDouble(ob[itemscount+9].toString());  //���Ά��
			holdvolume = Double.parseDouble(ob[itemscount+10].toString());     //�������
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea%></td>
<%
	for(int t=0; t<itemscount; t++){
%>
     <td class="TD_LIST" align="center"><%=ob[1+t] == null ? "" : ob[1+t].toString()%></td>
<%
	}
%>
     <td class="TD_LIST" align="center"><%=punit%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
     <td class="TD_LIST" align="center"><%=boxnum%></td>
     <td class="TD_LIST" align="center"><%=pweight%></td>
     <td class="TD_LIST" align="center"><%=pnetweight%></td>
     <td class="TD_LIST" align="center"><%=pvolume%></td>
     <td class="TD_LIST" align="center"><%=holdnum%></td>
     <td class="TD_LIST" align="center"><%=holdweight%></td>
     <td class="TD_LIST" align="center"><%=holdnetweight%></td>
     <td class="TD_LIST2" align="center"><%=holdvolume%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=12 + itemscount%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
