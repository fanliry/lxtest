<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLot" %>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
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
-->
</script>
</head>

<body onload="javascript:parent.page.location.reload();">

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="100px"><div class="list_title">
       <input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������Դ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƿ�Ĭ����������</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">��ע</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		BaseLot lot;
		String lotid;		//�������Դ���
		String descr;		//����
		String isdefault;	//�Ƿ�Ĭ����������
		String remark;		//��ע
		
		for(int i=0; i<ls.size(); i++)
		{
			lot = (BaseLot)ls.get(i);
			
			lotid = lot.getM_strLotid();
			descr = lot.getM_strDescr();
			isdefault = lot.getM_strIsdefault();
			remark = lot.getM_strRemark();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=lotid%>');">
     <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=lotid%>" class="input_checkbox" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=lotid%></td>
     <td class="TD_LIST" align="center"><%=descr==null ? "":descr%></td>
     <td class="TD_LIST" align="center"><%=isdefault==null ? "":isdefault%></td>
     <td class="TD_LIST2" align="center"><%=remark==null ? "":remark%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="5" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>
