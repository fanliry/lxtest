<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader" %>
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
	function Change(obj)
	{
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
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
		parent.document.getElementById("scid").innerHTML = id;
		//parent.detail.location.href = ac + "outBoundJobAction&flag=2&jobid=" + id;
	}
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//��ȡ��ϸ
	var oldobj = null;
    function getDetail(strId, obj)
	{
		oldobj = obj;
		var actionStr = "BMSService?actionCode=InBoundRequestAction&flag=2&instockid=" + strId;
		window.parent.tableheight2.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	
	function disaplayRecord(strId)
	{
	
	    var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  	    showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/scnew/inbound_bindrecord.jsp?&instockid="+strId,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=700px;dialogHeight=400px;");
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
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

<body  id="table_body" onload="javascript:OnLoad();">
 <table width="100%" height="100%" id="table"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�
     </div></td>
     <td width="80" class="TD_LIST_TITLE"><div class="list_title">������뵥��</div></td>
     <td width="80" class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td width="100" class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td width="40" class="TD_LIST_TITLE2"><div class="list_title">״̬</div></td>
     <td width="100" class="TD_LIST_TITLE"><div class="list_title">����������Ա</div></td>
     <td width="100" class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td width="100" class="TD_LIST_TITLE"><div class="list_title">�����</div></td>
     <td width="80" class="TD_LIST_TITLE"><div class="list_title">���ʱ��</div></td>
     <td width="80" class="TD_LIST_TITLE"><div class="list_title">�ر�ȷ��ʱ��</div></td>
     <td width="80" class="TD_LIST_TITLE"><div class="list_title">��ע</div></td>
     <td width="80" class="TD_LIST_TITLE"><div class="list_title">�󶨼�¼</div></td>
   </tr>
<%
	ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		InboundRequestInvoiceHeader inBound = null;  
		String instockid;      //������뵥��
        String invoicetypename;    //��������
        String createtime;         //����ʱ��
		String instatusname;      //״̬
        String createmanname;     //����������Ա
        String departname;    //��������
        String auditmanname ;   //�����
        String auditdate;     //���ʱ��
        String confirmdate;       //�ر�ȷ��ʱ��
        String remark; // ��ע

		for(int i = 0; i < ls.size(); i++)
		{
			inBound = (InboundRequestInvoiceHeader)ls.get(i);
			instockid = inBound.getInstockid();  
	        invoicetypename = inBound.getInvoicetypename();      
	        createtime = inBound.getCreatetime();        
			instatusname = inBound.getInstatusname();    
	        createmanname = inBound.getCreatemanname();  
	        departname = inBound.getDepartname(); 
	        auditmanname = inBound.getAuditmanname() ;
         	auditdate = inBound.getAuditdate();    
         	confirmdate = inBound.getConfirmdate();     
         	remark = inBound.getRemark();    
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="getDetail('<%=instockid%>', this);setSel(<%=i%>);">
     <td class="TD_LIST1" align="center"><input onClick="setSel(<%=i%>);" type="checkbox" name="check_id" class="input_checkbox" value="<%=instockid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=invoicetypename == null? "&nbsp" : invoicetypename%></td>
     <td class="TD_LIST" align="center"><%=createtime == null? "&nbsp" : createtime%></td>
     <td class="TD_LIST" align="center"><%=instatusname == null? "&nbsp" : instatusname%></td>
     <td class="TD_LIST" align="center"><%=createmanname == null? "&nbsp" : createmanname%></td>   
     <td class="TD_LIST" align="center"><%=departname == null? "&nbsp" : departname%></td>
     <td class="TD_LIST" align="center"><%=auditmanname == null? "&nbsp" : auditmanname%></td>   
     <td class="TD_LIST" align="center"><%=auditdate == null? "&nbsp" : auditdate%></td>
     <td class="TD_LIST" align="center"><%=confirmdate == null? "&nbsp" : confirmdate%></td>
     <td class="TD_LIST" align="center"><%=remark == null? "&nbsp" : remark%></td>
     <td class="TD_LIST" align="center"><input type="button" name="diaplayRecord" value="�����" onclick="disaplayRecord('<%=instockid%>')"  style="width:80px;"/></td>
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
	<tr>
      <td height="100%" colspan="12" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>

</body>
</html>
