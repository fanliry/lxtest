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
	function setSel(i){
		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		
		//�ı䱳��ɫ
		for(var i=0; i<check_ids.length; i++){
			
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
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�б�����</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">�б�ֵ</div></td>
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
			typeid = type.getTypeid();
			selectText = type.getSelecttext();
			selectValue = type.getSelectvalue();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData(2);">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=typeid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=selectText == null ? "" : selectText%></td>
     <td class="TD_LIST2" align="center"><%=selectValue == null ? "" : selectValue%></td>
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

