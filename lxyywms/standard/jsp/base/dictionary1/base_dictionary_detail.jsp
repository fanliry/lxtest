<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseType"%>
<%@ page import="java.util.List"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	
	//���õ�ѡ���Ƿ�ѡ��
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
	
-->
</script>
</head>
<body>

  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
    <td class="TD_LIST_TITLE1" width="30"><div class="list_title">
      	<input type="checkbox" name="check_all" onclick="CheckAll()" class="input_checkbox"></div></td>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�б�����</div></td>
    </tr>
<%
    List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		BaseType type = null;
		String typeid;			// ����ID
    	String selectText;		// �����б���ʾ����
 		String selectValue;		// �����б�ֵ
 		
		for(int i=0; i<ls.size(); i++){
		
			type = (BaseType)ls.get(i); 
			selectText = type.getSelecttext();
			selectValue = type.getSelectvalue();
			typeid = type.getTypeid();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData();">
     <td class="TD_LIST1" align="center"><input name="check_id" type="checkbox" value="<%=typeid%>" class="input_checkbox" onclick="setSel(<%=i%>)"></td>
     <td class="TD_LIST2" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=selectText == null ? "" : selectText%></td>
    
   </tr>
<%
        }
    }else {
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="3" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>

