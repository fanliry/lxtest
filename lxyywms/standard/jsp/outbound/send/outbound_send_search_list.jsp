<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
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
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
	
	function openDetail(jobid){
		
		condition = "&jobid=" + jobid; 
		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=ricoExecSendSearch&flag=3" + condition, 
					'', "dialogWidth=1000px; dialogHeight=600px; scroll=no");
		
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ݱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ͻ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ƺ�</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	
	if(ls != null && ls.size()>0){
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		Object[] obj = null;
		
		String invoiceid = null;	// ���ݱ��
		String customer = null;     // �ͻ�
		String vehicleno = null;	// ���ƺ�
		String vehiclepos = null;	// ��λ
		String product = null;		// ��Ʒ
     	String traycode = null;     // ��������
     	String cargospace = null;   // ��λ
     	String jobid = null;		// ��ҵ��
		double jobnum = 0;			// ����
		double volume = 0;         	// ���
 		double weight = 0;         	// ����
		double netweight = 0;		// ����
		
		String lotinfo="";
		String printdate = "";
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			invoiceid = obj[0] == null ? "" : obj[0].toString();	// ���ݱ��
			customer = obj[1] == null ? "" : obj[1].toString();		// �ͻ�
			vehicleno = obj[2] == null ? "" : obj[2].toString();	// ���ƺ�
			vehiclepos = obj[3] == null ? "" : obj[3].toString();	// ��λ
			product = obj[4] == null ? "" : obj[4].toString();		// ��Ʒ
			traycode = obj[5] == null ? "" : obj[5].toString();		// ��������
			cargospace = obj[6] == null ? "" : obj[6].toString();	// ��λ
			
			//jobid = obj[7] == null ? "" : obj[7].toString();		// ��ҵ��
			
			jobnum = Double.parseDouble(obj[7].toString().trim());			// ����
			//boxnum = Long.parseLong(obj[9].toString());				// ����
			//boxnum =(Long)obj[9];
			volume = Double.parseDouble(obj[8].toString());      	// ���
 			weight = Double.parseDouble(obj[9].toString());     	// ����
			netweight = Double.parseDouble(obj[10].toString());		// ����
			
			lotinfo = obj[11] == null ? "" : obj[11].toString();     	// ��������
			printdate = obj[12] == null ? "" : obj[12].toString();		// ��������
			
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ><!-- onDblClick="openDetail('<%=jobid%>');" -->
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=invoiceid%></td>
     <td class="TD_LIST" align="center"><%=customer%></td>
     <td class="TD_LIST" align="center"><%=vehicleno%></td>
     <td class="TD_LIST" align="center"><%=vehiclepos%></td>
     <td class="TD_LIST" align="center"><%=product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo%></td>
     <td class="TD_LIST" align="center"><%=printdate%></td>
     <td class="TD_LIST" align="center"><%=traycode%></td>
     <td class="TD_LIST" align="center"><%=cargospace%></td>
     <td class="TD_LIST" align="center"><%=(int)jobnum%></td>
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
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>