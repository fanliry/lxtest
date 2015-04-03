<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest"%>

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
	
	//��ѯ�̵�������Ϣ
	function queryDetail(id){
	
		parent.detail.location.href = ac + "inventoryCheckAction&flag=2&requestid=" + id;
	}
	
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}
			else{
				alert(back_msg);
			}
		}
		
		parent.RemoveLoading();
		parent.page.location.reload();
	}

</script>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1">
     	<div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox"></div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">������</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) {
	
		InventoryCheckRequest req = null;  
		
		String requestid;		//ID
		String type;			//����
		String status;			//״̬
		int priority;			//���ȼ�
		String strPriority;
		String warehouseid;		//�ֿ�
		String wharea;			//����
		String product;			//��Ʒ
		String lotnumber;	    //����
		String traycode;		//��������
    	String productcode;     //��Ʒ����
		String requesttime;		//����ʱ��
		String starttime;		//��ʼʱ��
		String endtime;			//����ʱ��
		String createman;		//������

		for(int i = 0; i < ls.size(); i++){
		
			req = (InventoryCheckRequest)ls.get(i);
			
			requestid = req.getRequestid();		//ID
			type = req.getTypeName();			//����
			status = req.getStatusName();		//״̬
			priority = req.getPriority();		//���ȼ�
			warehouseid = req.getWarehouseid();	//��λ
			wharea = req.getWhAreaName();		//����
			product = req.getProductName();		//��Ʒ
			lotnumber = req.getLotinfo();		//����
			traycode = req.getTraycode();		//��������
    		productcode = req.getProductcode(); //��Ʒ����
			requesttime = req.getRequesttime();	//����ʱ��
			starttime = req.getStarttime();		//��ʼʱ��
			endtime = req.getEndtime();			//����ʱ��
			createman = req.getCreateman();		//������
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData();">
     <td class="TD_LIST1" align="center">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=requestid%>">&nbsp;<%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseid==null?"":warehouseid%></td>
     <td class="TD_LIST" align="center"><%=wharea==null?"":wharea%></td>
     <td class="TD_LIST" align="center"><%=type==null?"":type%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>   
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
     <td class="TD_LIST" align="center"><%=requesttime==null?"":requesttime%></td>
     <td class="TD_LIST2" align="center"><%=createman==null?"":createman%></td>  
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="15 + iLine" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>
