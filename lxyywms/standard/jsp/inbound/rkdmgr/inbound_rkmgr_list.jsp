<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
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
	
		parent.detail.location.href = ac + "inBoundAction&method=ricoExecSearchRkd&flag=2&instockid=" + id;
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
	}

</script>

<body onLoad="OnLoad()">
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�
     </div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ݺ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">�Ƿ��ϴ�</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		InboundHeader inBound = null;  
		String instockid;      	//��ⵥ�ݱ��
     	String instatus;       	//����״̬
     	String intype;         	//�������
     	String createman;    	//����������Ա
     	String createtime;     	//��������ʱ��
     	String uploadflag;     	//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
     	String invoicesource;  	//������Դ

		for(int i = 0; i < ls.size(); i++)
		{
			inBound = (InboundHeader)ls.get(i);
			instockid = inBound.getInstockid();   		//��ⵥ�ݱ��
	        intype = inBound.getInvoicetypename();   	//�������
	        instatus = inBound.getInstatusname();		//��������
	        createman = inBound.getCreatemanid();		//����������Ա���
	        createtime = inBound.getCreatetime();   	//��������ʱ��
         	uploadflag = (inBound.getUploadflag()==null || inBound.getUploadflag().equals("N")) ?"N":"Y";    //�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center"><input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=instockid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=instatus%></td>
     <td class="TD_LIST" align="center"><%=intype%></td>
     <td class="TD_LIST" align="center"><%=createman%></td>   
     <td class="TD_LIST" align="center"><%=createtime%></td>
     <td class="TD_LIST2" align="center"><%=uploadflag%></td>
   </tr>

  
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="9" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>

</body>
</html>
