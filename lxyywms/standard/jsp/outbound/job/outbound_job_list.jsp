<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%
   
    //���е�������
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script>
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
  	
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
	//function setSel(i){

	//	var check_ids = document.getElementsByName("check_id");
	///	if(check_ids[i].checked){
	//		check_ids[i].checked = false;
	//		check_ids[i].parentNode.parentNode.style.backgroundColor = "";
	//	}else{
	//		check_ids[i].checked = true;
	//		check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
	//		
	//		queryDetail(check_ids[i].value);
	//	}
	//}
/* 	
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(ob){
	    var obj = ob.childNodes[0].childNodes[0];
		if(!obj.checked){
			obj.checked = false;
			obj.parentNode.parentNode.style.backgroundColor = "";
			queryDetail(obj.value);
		}else{
			obj.checked = true;
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
			queryDetail(obj.value);
		}
	} */
	

	//���ö�ѡ���Ƿ�ѡ��
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			
			queryDetail(check_ids[i].value);
		}
	}
	
	//��ѯ��ϸ
	function queryDetail(id){
		parent.document.getElementById("jobdid").innerHTML = id;
		parent.detail.location.href = ac + "outBoundJobAction&flag=2&jobid=" + id;
	}
	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}
			else{
				alert(back_msg);
			}
		}
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}

</script>

<body onLoad="OnLoad()">
 <table width="100%" height="100%" id="table"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�
     </div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ȼ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ͻ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">���ʱ��</div></td>
   </tr>
<%
	
	if(ls != null && ls.size() > 0) {
		InoutJob job = null;  
		String taskid;     		//��������ID
     	String whArea;  		//����
     	String alley;    		//���
     	String cargospace;     	//��λ
     	String traycode;     	//��������
     	int priority;  			//���ȼ�
     	String snumber;    		//���ͺ�
     	String jobid;      		//��ҵ��
     	String createtime;  	//����ʱ��
     	String finishtime; 		//���ʱ��
     	
     	String status;    //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ

		for(int i = 0; i < ls.size(); i++){
		
			job = (InoutJob)ls.get(i);
			taskid = job.getTaskid();				//��������ID
     		//whArea = job.getTcargoWhareaName();		
     		//alley = job.getTcargoAlleyName();		
     		//cargospace = job.getTcargoSpaceName(); 	
     		whArea = job.getScargoWhareaName();     //����
     		alley = job.getScargoAlleyName();       //���
     		cargospace = job.getScargoSpaceName();  //��λ
     		traycode = job.getTraycode();     		//��������
     		priority = job.getPriority()==null?0:job.getPriority();  		//���ȼ�
     		snumber = job.getSnumber();    			//���ͺ�
			jobid = job.getJobid();      			//��ҵ��
     		createtime = job.getCreatetime();  		//����ʱ��
     		finishtime = job.getFinishtime(); 		//���ʱ��
     		
     		status = job.getStatusName();
			
%>
     <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
     <input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=jobid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=taskid==null?"":taskid%></td>
     <td class="TD_LIST" align="center"><%=whArea==null?"":whArea%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=priority%></td>
     <td class="TD_LIST" align="center"><%=snumber==null?"":snumber%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td> 
     <td class="TD_LIST2" align="center"><%=finishtime==null?"":finishtime%></td>  
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="11" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>