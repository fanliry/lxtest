<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult" %>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
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
			
			var key = check_ids[i].value.split("|");
			parent.document.getElementById("checknum").value = key[1];
		}
	}
	
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
				parent.document.getElementById("checknum").value="0";
			}
			else{
				alert(back_msg);
			}
		}
		
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px">
     	<div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox"></div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�̵�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�̵�ʱ��</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">״̬</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InventoryCheckResult checkresult = null;
		String checkid;			//ID
		String product;			//��Ʒ
		String lotnumber;	    //����
		String traycode;		//��������
		double num;				//�������
		double checknum;		//�̵�����
		String checktime;		//�̵�ʱ��
		String createman;		//������
		String statusName;		//״̬
		
		String param = "";		//ID+�̵�����+�̵�����
		
		for(int i = 0; i < ls.size(); i++){
		
			checkresult = (InventoryCheckResult)ls.get(i);
			checkid = checkresult.getCheckid();			//ID
			product = checkresult.getProductName();		//��Ʒ
			lotnumber = checkresult.getLotnumber();	    //����
    		traycode = checkresult.getTraycode(); 		//��������
			num = checkresult.getNum();					//�������
			checknum = checkresult.getChecknum();		//�̵�����
			checktime = checkresult.getChecktime();		//�̵�ʱ��
			createman = checkresult.getCreateman();		//������
			statusName = checkresult.getStatusName();
			
			param = checkid + "|" + (int)checknum + "|end";
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center" width="50px">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=param%>">&nbsp;<%=i+1%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>  
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=(int)num%></td>
     <td class="TD_LIST" align="center"><%=(int)checknum%></td>
     <td class="TD_LIST" align="center"><%=checktime==null?"":checktime%></td>
     <td class="TD_LIST2" align="center"><%=createman==null?"":createman%></td>
     <td class="TD_LIST2" align="center"><%=statusName==null?"":statusName%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="9" valign="top" class="TD_last_LIST">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
     </td>
   </tr>
   </tbody> 
 </table>
 
</body>
</html>