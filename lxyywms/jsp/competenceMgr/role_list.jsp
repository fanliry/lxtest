<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ricosoft.entity.competenceMgr.RoleInfo" %>



<html>
<head>
  <title>����ϵͳ</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
  <script type="text/javascript">
  <!--
   //ȫѡ
    function selectAll()
	{
		var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
			if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_all'){
				 state = document.myform.elements[i].checked;
				 break;
			}
		}
		for(i=0;i<length;i++){
			 if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_id'){
				   document.myform.elements[i].checked=state;
			 }
		}
	}
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("checkbox_all").checked;
		var check_ids = document.getElementsByName("checkbox_id");
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

		var check_ids = document.getElementsByName("checkbox_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
  -->
  </script>
</head>

<body onload="javascript:parent.myIframe1.location.reload();">
<%
	List ls = null;
	if(request.getAttribute("exResultList") != null)
	{
		ls = (List)request.getAttribute("exResultList");
	}
	
%>
<form id="myform" name="myform" method="post" action="">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td width="12%" class="TD_LIST_TITLE"><div class="list_title">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" onclick="CheckAll()">ȫѡ
	  </div></td>
	  <td width="18%" class="TD_LIST_TITLE"><div class="list_title">�к�</div></td>
	  <td width="30%" class="TD_LIST_TITLE"><div class="list_title">��ɫ��</div></td>
	  <td width="40%" class="x1"><div class="list_title">����</div></td>
	</tr>
	  <%
	  	if(ls != null && !ls.isEmpty())
	  	{
	  	 	for(int i = 0; i < ls.size(); i++) 
	  	 	{
	  	 		RoleInfo role = (RoleInfo)ls.get(i);  
	  	 	    String strId = role.getM_strRoleId(); 		//����
	  			String strRoleName = role.getM_strRoleName();//��ɫ��
	  			String strDescr = role.getM_strRemark();	//����
	  			
	  			String strParam = "id=" + strId+ "&rolename=" + strRoleName + "&descr=" + (strDescr == null ? "" : strDescr);
	  	
	  	%>
	  		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
			  <td class="TD_LIST1" align="center">
			    <input type="checkbox" name="checkbox_id" value="<%=strId%>" class="input_checkbox" id="<%=strParam%>" onclick="setSel(<%=i%>)">
			  </td>
			  <td class="TD_LIST1" align="center"><%=i+1%></td>
			  <td class="TD_LIST1" align="center"><%=strRoleName == null ? "" : strRoleName%></td>
			  <td class="TD_LIST1" align="center"><%=strDescr == null ? "" : strDescr%></td>
			</tr>
	  	<%
	  	 	}
	  	 }else
	  	 {
	  	 	session.removeAttribute("paging");
	  	 }
	  %>
	

		<!-- ==== ���һ�У����У��������� ==== -->
	 <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>
</form>

</body>
</html>
