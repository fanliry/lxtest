<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--

	//�ı䱳��
    function Change(obj) {
		var check_ids = document.getElementsByName("check_id");
		for (var i=0; i<check_ids.length; i++) {
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}
		obj.parentNode.parentNode.style.backgroundColor = "#AFEF6F";
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
	function OnLoad(){	
	
		var back_msg = "<%=request.getAttribute("back_msg") == null ? "" : (String)request.getAttribute("back_msg")%>";
		if (back_msg != "") 
		{
			if(back_msg == "Y")
			{
				alert("ȷ�ϳɹ�!");
			}else
			{
				alert(back_msg);
			}
		}
	}
	
-->
</script>
</head>

<body onload="OnLoad();">
<div style="width: 100%; height: 100%; overflow:auto;">
 <table width="100%" height="100%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST" id="tb">
   <tr height="20">
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">ѡ��</div></td>
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��ҵ��</div></td>
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ״̬</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();     
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
	 
		InoutJobdetail jobDetail = null; 
		InoutJob job = null;  
		int iBoxNum = 0;				// ���
		
		String strJobDetailId = null;

		String strProductName = null;	// ��Ʒ�����
		String strBatchId = null;		// ����ID
		String strSstatusName = null;   // ��Ʒ���
		String strMstatusName = null;  	// ��Ʒ״̬
		String strBatch = null;			// ����
		String strPrintDate = null;		// ����
		
		String strBoxCode = null;		// ��������
		
		double  fPNum =0;				// ����
		String fGrossWeight = "0";		// ë��
		String fNetWeight = "0";		// ����
		
		double  fAssPNum =0;				// ����
		String fAssGrossWeight = "0";		// ë��
		String fAssNetWeight = "0";		// ����
		
		String strCustomerName = null; // �ͻ�
		String strCustomerId = null; // �ͻ�
		
		String jobid = "";
		String traycode = "";
		Object[] ob =null;
		for(int i = 0; i < ls.size(); i++)
		{
	    	ob = (Object[])ls.get(i);  
	    	jobDetail = (InoutJobdetail)ob[1];
	    	job = (InoutJob)ob[0];
	    	
	    	jobid = job.getJobid();
	    	traycode = job.getTraycode();
	    	
	    	strJobDetailId = jobDetail.getJobdetailid();	// ��ҵ��ϸID
		 	strProductName 	  = jobDetail.getM_strProductName();  // ��Ʒ�����
		 	strBoxCode = jobDetail.getBoxcode();			// ��������	 	
		 	fGrossWeight = nbf.format(jobDetail.getWeight());	// ë��
			fNetWeight   = nbf.format(jobDetail.getNetweight());// ����
		  	fPNum        = jobDetail.getJobnum();				// ����	
		  	fAssPNum = jobDetail.getAssignnum();							// ����
		    fAssGrossWeight = nbf.format(jobDetail.getAssignweight());		// ë��
		    fAssNetWeight = nbf.format(jobDetail.getAssignnetweight());		// ����
	    	  
	    	strCustomerName = jobDetail.getM_strCustomerName();
	    	strCustomerId = jobDetail.getCustomerid();
	    	
	    	strMstatusName = jobDetail.getProductStatusName();
	    	strPrintDate = jobDetail.getPrintdate();
	    	strBatch = jobDetail.getLotinfo();
	    	
	    	
	
	    %>	
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" >
    <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" value="<%=strJobDetailId%>" class="input_radio" onClick="Change(this)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=strProductName==null?"":strProductName%></td>
     <td class="TD_LIST" align="center"><%=strMstatusName==null?"":strMstatusName%></td>
     <td class="TD_LIST" align="center"><%=strBatch==null?"":strBatch%></td>    
     <td class="TD_LIST" align="center"><%=strPrintDate==null?"":strPrintDate%></td>
     <td class="TD_LIST" align="center" <%=fAssPNum != fPNum ? "style='background-color:#AABB45' " : ""%>><%=(int)fPNum%></td>
     <td class="TD_LIST" align="center" <%=fAssPNum != fPNum ? "style='background-color:#AABB45' " : ""%>><%=(int)fAssPNum%></td>
    <tr>
<%		
		}

	}
%>
   
     <td height="100%" colspan="9"  valign="top">
     <div style="color: red;font-size:12;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   
 </table>
 
</div>
</body>
</html>
