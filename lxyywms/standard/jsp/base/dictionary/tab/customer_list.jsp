<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.ClientFile"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#EFEFEF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	//�ı䱳��
    function Change(obj){
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#EFEFEF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
	//ҳ���¼
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
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

<body onLoad="OnLoad()">
<div style="width: 100%; height: 100%; overflow:auto;">
 <table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="25"><input type="checkbox" name="check_all" onclick="CheckAll()" class="input_checkbox"></td>
     <td class="TD_LIST_TITLE" align="center" width="50">�к�</td>
     <td class="TD_LIST_TITLE" align="center">�ͻ����</td> 
     <td class="TD_LIST_TITLE" align="center">ȫ��</td>
	 <td class="TD_LIST_TITLE" align="center">��ϵ��</td>
	 <td class="TD_LIST_TITLE" align="center">��ϵ�绰</td>     
     <td class="TD_LIST_TITLE" align="center">����</td> 
     <td class="TD_LIST_TITLE" align="center">��ַ</td>
     <td class="TD_LIST_TITLE" align="center">��������</td>
     <td class="TD_LIST_TITLE" align="center">�Ƿ�����</td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
		ClientFile cus = null;
		String s0,s1,s2,s3,s4,s5,s6,s7,s8;
		for(int i = 0; i < ls.size(); i++){
			cus = (ClientFile)ls.get(i);
	    	
	    	s0 = cus.getM_clientFileId();	/*����*/
			s1 = cus.getM_clientName();		/*�ͻ����*/
			s2 = cus.getM_fullName();		/*ȫ��*/
			s3 = cus.getM_linkman();			/*��ϵ��*/
			s4 = cus.getM_phone();			/*��ϵ�绰*/
			s5 = cus.getM_fax();				/*����*/
			s6 = cus.getM_address();			/*��ַ*/
			s7 = cus.getM_strOutId();
			s8 = cus.getM_reveal();

%>	
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=s0%>" onClick="Change(this)"></td>
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=s1==null?"":s1%></td>
     <td class="TD_LIST" align="center"><%=s2==null?"":s2%></td>
     <td class="TD_LIST" align="center"><%=s3==null?"":s3%></td>
     <td class="TD_LIST" align="center"><%=s4==null?"":s4%></td>
     <td class="TD_LIST" align="center"><%=s5==null?"":s5%></td>
     <td class="TD_LIST" align="center"><%=s6==null?"":s6%></td>
     <td class="TD_LIST" align="center"><%=s7==null?"":s7%></td>
     <td class="TD_LIST" align="center"><%=s8==null?"":s8%></td>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="10" class="TD_LIST" valign="top">
       <div style="color: red;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
</body>
</html>