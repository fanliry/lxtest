<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.rule.RuleAllocationDetail"%>
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
		
			if(!check_ids[i].disabled){
			
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
	}
	
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		
		if(!check_ids[i].disabled){
			if(check_ids[i].checked){
				check_ids[i].checked = false;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}else{
				check_ids[i].checked = true;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
		}
	}
  -->
</script>
</head>

<body>
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td class="TD_LIST_TITLE1" width="30px"><div class="list_title">
	  	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox"></div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">����˳λ</div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">��Ч</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���򷽷�</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Ŀ�����</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Ŀ���λ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Ŀ�����</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">������λ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���λ��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�Զ�������������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�洢λ��ѡ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
	  <td class="TD_LIST_TITLE2"><div class="list_title">���ַ���</div></td>
	</tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
		
  	 	for(int i = 0; i < ls.size(); i++) {
  	 	
  	 		RuleAllocationDetail allocationDetail = (RuleAllocationDetail)ls.get(i);  
  	 		
  	 		String allocationid = allocationDetail.getAllocationid(); 			//�������ID
  	 	    String allocationdetailid = allocationDetail.getAllocationdetailid(); 	//���������ϸID
  	 	    int sort = allocationDetail.getSort();								//����˳λ
  	 	    String enableflag = allocationDetail.getEnableflag();				//��Ч
  	 	    String ruleconfigname = allocationDetail.getRuleconfigname(); 		//������������
  	 	    String tozone = allocationDetail.getTozonename();					//Ŀ�����
  	 	    String tolocation = allocationDetail.getTolocationname();			//Ŀ���λ
  	 	    String toalleys = allocationDetail.getToalleys();					//Ŀ��������ɶ���
  	 	    String unit = allocationDetail.getUnitflagname();					//������λ
  	 	    String clearloc_flag = allocationDetail.getClearloc_flag();			//���
  	 	    String apart_flag = allocationDetail.getApart_flag();				//����
  	 	    String over_flag = allocationDetail.getOver_flag();					//���λ��������
  	 	    String auto_flag = allocationDetail.getAuto_flag();					//�Զ�������������
  	 	    String bulkpick_flag = allocationDetail.getBulkpick_flag();			//�洢λ��ѡ
  	 	    String part_flag = allocationDetail.getPart_flag();					//���
  	 	    String part_allocation_flag = allocationDetail.getPart_allocation_flag();//���ַ���
  	 	    
  	 	    String param = allocationdetailid + "|" + allocationid;
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateDetailData();">
	  <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=param%>" class="input_checkbox" onclick="setSel(<%=i%>);" <%if(allocationid.equals("STANDARD")){%>disabled<%}%>></td>
      <td class="TD_LIST" align="center"><%=sort%></td>
	  <td class="TD_LIST" align="center"><%=enableflag == null ? "" : enableflag%></td>
	  <td class="TD_LIST" align="center"><%=ruleconfigname == null ? "" : ruleconfigname%></td>
	  <td class="TD_LIST" align="center"><%=tozone == null ? "" : tozone%></td>
	  <td class="TD_LIST" align="center"><%=tolocation == null ? "" : tolocation%></td>
	  <td class="TD_LIST" align="center"><%=toalleys == null ? "" : toalleys%></td>
	  <td class="TD_LIST" align="center"><%=unit == null ? "" : unit%></td>
	  <td class="TD_LIST" align="center"><%=clearloc_flag == null ? "" : clearloc_flag%></td>
	  <td class="TD_LIST" align="center"><%=apart_flag == null ? "" : apart_flag%></td>
	  <td class="TD_LIST" align="center"><%=over_flag == null ? "" : over_flag%></td>
	  <td class="TD_LIST" align="center"><%=auto_flag == null ? "" : auto_flag%></td>
	  <td class="TD_LIST" align="center"><%=bulkpick_flag == null ? "" : bulkpick_flag%></td>
	  <td class="TD_LIST" align="center"><%=part_flag == null ? "" : part_flag%></td>
	  <td class="TD_LIST2" align="center"><%=part_allocation_flag == null ? "" : part_allocation_flag%></td>
	</tr>
<%
 		}	
	 }
%>
   <tr>
     <td height="100%" colspan="15" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>
