<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseType"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//���õ�ѡ���Ƿ�ѡ��
	function setSel(i){
		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		
		var ae = check_ids[i].value.split("|");
		//����������ֵ
		parent.right.location.href= ac + "baseTypeAction&flag=2&typevalue=" + ae[1];
		
		//ϵͳ�����ܸ���
		if(ae[2] == "Y"){
			parent.document.getElementById("button2").disabled = true;
			parent.document.getElementById("button3").disabled = true;
			parent.document.getElementById("button4").disabled = true;
			parent.document.getElementById("button5").disabled = true;
			parent.document.getElementById("button6").disabled = true;
			parent.document.getElementById("button7").disabled = true;
		}else{
			parent.document.getElementById("button2").disabled = false;
			parent.document.getElementById("button3").disabled = false;
			parent.document.getElementById("button4").disabled = false;
			parent.document.getElementById("button5").disabled = false;
			parent.document.getElementById("button6").disabled = false;
			parent.document.getElementById("button7").disabled = false;
		}
		
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
      <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">����ֵ</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">�Ƿ�ϵͳ����</div></td>
    </tr>
<%
    List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		BaseType type = null;

   		String typeName;		// ������
   		String typeValue;		// ����ֵ
    	String typelevel;		// ���͵ļ���ϵͳ������ǲ��ܸ��µģ�
    	  
    	String param;
    	 	
		for(int i=0; i<ls.size(); i++){
		
			type = (BaseType)ls.get(i); 
                        
            typeName = type.getTypename();
			typeValue = type.getTypevalue();
			typelevel = type.getTypelevel();
			
			param = typeName + "|" + typeValue + "|" + typelevel;
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData(1);">
   	 <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=param%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=typeName == null ? "" : typeName%></td>
     <td class="TD_LIST" align="center"><%=typeValue == null ? "" : typeValue%></td>
     <td class="TD_LIST2" align="center"><%=typelevel == null ? "" : typelevel%></td>
   </tr>
<%
        }
    }else {
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="4" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>

