<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.rule.RuleReplenish" %>
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
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td class="TD_LIST_TITLE1" width="50px" nowrap><div class="list_title">
        <input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">�к�</div></td>
      <td width="130px" class="TD_LIST_TITLE" nowrap><div class="list_title">������������</div></td>
      <td width="100px" class="TD_LIST_TITLE" nowrap><div class="list_title">�����ֿ�</div></td>
	  <td width="130px" class="TD_LIST_TITLE" nowrap><div class="list_title">������ʽ</div></td>
	  <td width="100px" class="TD_LIST_TITLE" nowrap><div class="list_title">���������(��)</div></td>
	  <td width="100px" class="TD_LIST_TITLE" nowrap><div class="list_title">���������(��)</div></td>
	  <td width="160px" class="TD_LIST_TITLE" nowrap><div class="list_title">�������С������(��/��)</div></td>
	  <td width="100px" class="TD_LIST_TITLE" nowrap><div class="list_title">��������(��)</div></td>
	  <td width="100px" class="TD_LIST_TITLE" nowrap><div class="list_title">��������(��)</div></td>
	  <td width="150px" class="TD_LIST_TITLE" nowrap><div class="list_title">������С������(��)</div></td>
	  <td width="120px" class="TD_LIST_TITLE" nowrap><div class="list_title">��/���������(��)</div></td>
	  <td width="120px" class="TD_LIST_TITLE" nowrap><div class="list_title">��/���������(��)</div></td>
	  <td width="160px" class="TD_LIST_TITLE" nowrap><div class="list_title">��/�������С������(��)</div></td>
	  <td width="130px" class="TD_LIST_TITLE2" nowrap><div class="list_title">��ע</div></td>
	</tr>

<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
	  	 for(int i = 0; i < ls.size(); i++){
	  	 	
	  	 	RuleReplenish repRule = (RuleReplenish)ls.get(i); 

  	 	    String replenishid = repRule.getReplenishid(); 		//�����������
  	 	    String descr = repRule.getDescr();     				//����
  	 	    String warehousename = repRule.getWarehousename();  //�����ֿ���
 			String ruleconfigname = repRule.getRuleconfigname();//������ʽ
	 	
  	 		int fEA_Lowerlimit = (int)repRule.getEa_lowerlimit();
  	 		int fEA_Uplimit = (int)repRule.getEa_uplimit();
  	 		int fEA_Minreplenishqty = (int)repRule.getEa_minreplenishqty();
  	 		int fCS_Lowerlimit = (int)repRule.getCs_lowerlimit();
  	 		int fCS_Uplimit = (int)repRule.getCs_uplimit();
  	 		int fCS_Minreplenishqty = (int)repRule.getCs_minreplenishqty();
  	 		int fOT_Lowerlimit = (int)repRule.getOt_lowerlimit();
  	 		int fOT_Uplimit = (int)repRule.getOt_uplimit();
  	 		int fOT_Minreplenishqty = (int)repRule.getOt_minreplenishqty();
  	 		
  	 		String remark = repRule.getRemark();
	  	 		
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=replenishid%>');">
	  <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=replenishid%>" class="input_checkbox" onclick="setSel(<%=i%>)"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=descr == null ? "" : descr%></td>
	  <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
	  <td class="TD_LIST" align="center"><%=ruleconfigname == null ? "" : ruleconfigname%></td>
	  <td class="TD_LIST" align="center"><%=fEA_Lowerlimit%></td>
	  <td class="TD_LIST" align="center"><%=fEA_Uplimit%></td>
	  <td class="TD_LIST" align="center"><%=fEA_Minreplenishqty%></td>
	  <td class="TD_LIST" align="center"><%=fCS_Lowerlimit%></td>
	  <td class="TD_LIST" align="center"><%=fCS_Uplimit%></td>
	  <td class="TD_LIST" align="center"><%=fCS_Minreplenishqty%></td>
	  <td class="TD_LIST" align="center"><%=fOT_Lowerlimit%></td>
	  <td class="TD_LIST" align="center"><%=fOT_Uplimit%></td>
	  <td class="TD_LIST" align="center"><%=fOT_Minreplenishqty%></td>
	  <td class="TD_LIST2" align="center"><%=remark == null ? "" : remark%></td>
	</tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="13" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
</body>
</html>
