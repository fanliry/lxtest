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
  	var check_ids = document.getElementsByName("check_id");
	var newsorts = document.getElementsByName("newsort");
	
	//���õ�ѡ���Ƿ�ѡ��
	function setSel(i){

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
	
	//��ȡ��ѡ�����
	function isChecked()   
  	{   
  		for(var i=0; i<check_ids.length; i++)
  		{
	  		if(check_ids[i].checked)
	  		{
	  			return true;
	  		}
  		}
  		alert("��ѡ��һ�����ݣ�");
  		return false;
   }
   
   	//��������
	function doSort(){
		for(var i=0; i<check_ids.length; i++){
			newsorts[i].value = i+1;
		}
	}
	
	//����
	function moveUp(){
		
		if(isChecked()){
		
			for(var i=0; i<check_ids.length; i++){
	  		
		  		if(check_ids[i].checked){

		  			//��ȡ����е�����
		  			var _row = check_ids[i].parentNode.parentNode;  
		  			
		  			//������ǵ�һ�У�������һ�н���˳�� 
					if(_row.previousSibling && i!=0){
						
						swapNode(_row, _row.previousSibling); 
						doSort();		//��������
					}
					
		  			break;
		  		}
	  		}
  		}
	}
	
	
	//����
	function moveDown(){ 
	
		if(isChecked()){
			for(var i=0; i<check_ids.length; i++){
	  		
		  		if(check_ids[i].checked){

		  			//��ȡ����е�����
		  			var _row = check_ids[i].parentNode.parentNode;  
		  			
		  			//����������һ�У�������һ�н���˳�� 
					if(_row.nextSibling && i!=check_ids.length-1){
						
						swapNode(_row, _row.nextSibling); 
						doSort();		//��������
					}
					
		  			break;
		  		}
	  		}
		}
	}
	
	//����ͨ�õĺ���������������λ�� 
	function swapNode(node1,node2){ 
		
		//��ȡ����� 
		var _parent=node1.parentNode; 		
		
		//��ȡ�����������λ�� 
		var _t1=node1.nextSibling; 
		var _t2=node2.nextSibling; 
		
		//��node2���뵽ԭ��node1��λ�� 
		if(_t1){
			_parent.insertBefore(node2,_t1);
		}else {
			_parent.appendChild(node2);
		} 
		
		//��node1���뵽ԭ��node2��λ�� 
		if(_t2){
			_parent.insertBefore(node1,_t2); 
		}else {
			_parent.appendChild(node1)
		}; 
	} 
	
	
	//����
	function saveNewSort()
	{
		var detailids = "";
		var sorts = "";
		
		for(var i=0; i<check_ids.length; i++){
			detailids += check_ids[i].value + ",";		//�ϼܹ�����ϸID + ","
			sorts += newsorts[i].value + ",";			//˳�� + ","
		}
		
        window.returnValue = "&detailids=" + detailids + "&sorts=" + sorts;
		window.close();
	}
  -->
</script>
</head>

<body>
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ҵ����� -&gt; ������� -&gt; �޸�����˳λ</div></td>
    </tr>
  </table>
  
  <table><tr><td height="10"></td></tr></table> 

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
	<tr>
	  <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">ѡ��</div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">����˳λ</div></td>
	  <!--<td width="60px" class="TD_LIST_TITLE"><div class="list_title">�Ƿ���Ч</div></td>-->
	  <td class="TD_LIST_TITLE"><div class="list_title">���򷽷�</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���λ��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�Զ�������������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�洢λ��ѡ</div></td>
	  <td class="TD_LIST_TITLE2"><div class="list_title">���</div></td>
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
  	 	    String clearloc_flag = allocationDetail.getClearloc_flag();			//���
  	 	    String apart_flag = allocationDetail.getApart_flag();				//����
  	 	    String over_flag = allocationDetail.getOver_flag();					//���λ��������
  	 	    String auto_flag = allocationDetail.getAuto_flag();					//�Զ�������������
  	 	    String bulkpick_flag = allocationDetail.getBulkpick_flag();			//�洢λ��ѡ
  	 	    String part_flag = allocationDetail.getPart_flag();					//���
  	 	    
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(this.rowIndex-1)">
	  <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=allocationdetailid%>"></td>
      <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=sort%>" size="2" readonly class="input_readonly"></td>
	  <!--<td class="TD_LIST" align="center"><%=enableflag == null ? "" : enableflag%></td>-->
	  <td class="TD_LIST" align="center"><%=ruleconfigname == null ? "" : ruleconfigname%></td>
	  <td class="TD_LIST" align="center"><%=clearloc_flag == null ? "" : clearloc_flag%></td>
	  <td class="TD_LIST" align="center"><%=apart_flag == null ? "" : apart_flag%></td>
	  <td class="TD_LIST" align="center"><%=over_flag == null ? "" : over_flag%></td>
	  <td class="TD_LIST" align="center"><%=auto_flag == null ? "" : auto_flag%></td>
	  <td class="TD_LIST" align="center"><%=bulkpick_flag == null ? "" : bulkpick_flag%></td>
	  <td class="TD_LIST2" align="center"><%=part_flag == null ? "" : part_flag%></td>
	</tr>
<%
 		}	
	 }
%>
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
  </table>
  <table><tr><td height="10"></td></tr></table> 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onClick="moveUp()" value="����" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onClick="moveDown()" value="����" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onclick="saveNewSort()" id="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>

</body>
</html>
