<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	List lsLot = (List)request.getAttribute("lsLot");
%>
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
  	var check_names = document.getElementsByName("names");
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
		var detailnames = "";
		var sorts = "";
		for(var i=0;i< check_ids.length;i++)   
		{
		    if(check_ids[i].checked)
		    {
			    detailids += check_ids[i].value + "|";		    //����ID + ","
			    detailnames += check_names[i].value + "|";		//���������� + ","
				sorts += newsorts[i].value + "|";			    //˳�� + ","
		    }
		}
		if(detailids!=null && detailids.length > 0){
		   detailids = detailids.substring(0, detailids.length-1)+ ",";
		}
		if(detailnames!=null && detailnames.length > 0){
		   detailnames = detailnames.substring(0, detailnames.length-1);
		}
		if(sorts!=null && sorts.length > 0){
		   sorts = sorts.substring(0, sorts.length-1)+ ",";
		}
		
        window.returnValue = detailids  + sorts + detailnames;
		window.close();
	}
  -->
</script>
</head>

<body>
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã���ѯͳ�� -&gt; �����ˮ��ѯ -&gt; ͳ�Ʒ�������</div></td>
    </tr>
  </table>
  
  <table><tr><td height="10"></td></tr></table> 

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
	<tr>
	  <td class="TD_LIST_TITLE1" width="30px"><div class="list_title">ѡ��</div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">����˳��</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">���Ա���</div></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="productcode"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=1%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="��Ʒ����" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="productcode" readonly class="input_readonly"></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="productid"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=2%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="Ʒ��" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="productid" readonly class="input_readonly"></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="lotinfo"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=3%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="����" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="lotinfo" readonly class="input_readonly"></td>
	</tr>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="printdate"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=4%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="��������" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="productDate" readonly class="input_readonly"></td>
	</tr>
	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="boundstockid"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=5%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="���ݱ��" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="boundstockid" readonly class="input_readonly"></td>
	</tr>
	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="jobtype"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=6%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="��������" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="jobtype" readonly class="input_readonly"></td>
	</tr>
	
	
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="createtime"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=7%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="��ҵ��������" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="createtime" readonly class="input_readonly"></td>
	</tr>
	
	
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);	  
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
	  <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=lotSet.getLotid()%>"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="newsort" value="<%=k+5%>" size="2" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="names" value="<%=lotSet.getLotname() == null ? "" : lotSet.getLotname()%>" readonly class="input_readonly"></td>
	  <td class="TD_LIST" align="center"><input type="text" name="namecode" value="<%=lotSet.getLotid()%>" readonly class="input_readonly"></td>
	</tr>
<%
 		}	
	 }
%>
  </table>
  <table><tr><td height="10"></td></tr></table> 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onClick="moveUp()" value="����" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onClick="moveDown()" value="����" class="BUTTON_STYLE1">&nbsp;
        <input type="button" onclick="saveNewSort()" id="add" value="&nbsp;&nbsp;&nbsp;ȷ��" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>

</body>
</html>
