<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseBatchrule"%>

<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//ȫѡ
    function CheckAll()
	{
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++)
		{
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
<body>
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
        <input type="checkbox" name="check_all" class="input_checkbox" onClick="CheckAll()">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��ʼλ��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">����λ��</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">������Ϣ</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls!=null)
	{
		BaseBatchrule batchrule;
		String batchruleid;		//���ι���ID
		//String batchid;		//����ID
		String rulename;		//��������
		String ruleinfo;		//����
		Integer startpos;		//��ʼλ��
		Integer endpos;			//����λ��
		
		for(int i=0; i<ls.size(); i++)
		{
			batchrule = (BaseBatchrule)ls.get(i);
			batchruleid = batchrule.getBatchruleid();
			rulename = batchrule.getRulename();
			ruleinfo = batchrule.getRuleinfo();
			startpos = batchrule.getStartpos();
			endpos = batchrule.getEndpos();
%>   
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
       <input type="checkbox" name="check_id" value="<%=batchruleid%>" class="input_checkbox" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=rulename%></td>
     <td class="TD_LIST" align="center"><%=startpos%></td>
     <td class="TD_LIST" align="center"><%=endpos%></td>
     <td class="TD_LIST2" align="center"><%=ruleinfo==null?"":ruleinfo%></td>
   </tr>
<%
		}
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