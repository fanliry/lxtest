<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%
	HashMap<String, List> hsLot = null;		//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  		//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newin");	//�½��ջ���->��ʾ�ջ�����ϸ	
		}
	}
%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
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
	function jobdisplay(instockdetailid){ 
		var actionStr =strUrl + "inBoundAction&method=ricoExecJobDdis&instockdetailid="+instockdetailid;
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
		window.open(actionStr,'newwindow','width=1000,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
	}
	function OnLoad(){
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
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
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">
     	<input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�</div>
     </td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ״̬</div></td> 
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
	
		InboundDetail detail = null;
		String instockdetailid = null;//��ⵥ��ϸID
		String product = null;		// Ʒ��
		String linestatus = null;   //������״̬0-�½� 5-����
		String pack = null;			// ��װ
		String eunit = null;		// ��λ
		double  invoicenum = 0;		// ����
		String reincoiceid = null;  // �ջ�����
		String productStatusName;    // ��Ʒ״̬����
		
		
		for(int i = 0; i < ls.size(); i++){
			detail = (InboundDetail)ls.get(i);
			
			instockdetailid = detail.getInstockdetailid();	// ��ⵥ��ϸID
			product = detail.getProductName();		// Ʒ��
			eunit = detail.getPunit();				// ��λ
			invoicenum = detail.getInnum();			// ����
			productStatusName = detail.getProductStatusName();
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)"  ondblclick="jobdisplay('<%=instockdetailid%>')">
     <td class="TD_LIST1" align="center" width="50px">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=instockdetailid%>"><%=i+1%>
     </td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=eunit==null?"":eunit%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(invoicenum)%></td>
      <td class="TD_LIST" align="center"><%=productStatusName==null?"":productStatusName%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="<%=8%>" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>

</body>
</html>