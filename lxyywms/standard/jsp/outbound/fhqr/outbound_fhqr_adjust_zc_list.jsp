<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<%
%>

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

  
	function OnLoad(){
		parent.RemoveLoading();

		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
	
-->
</script>
</head>

<body onload="javascript:OnLoad();">
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%" height="100%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST" id="tb">
   <tr height="20">
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">ѡ��</div></td>
 	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
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
		InventoryStorage storage = null;   
		String strStorageId = null;		// ���ID
		String strTrayCode = null;		// ��������
		String strProductName = null;	// ��Ʒ�����
		double  fPNum =0;				// ����
		String lotinfo = null;		// ����	
		String printdate = null;	// ��������
		for(int i = 0; i < ls.size(); i++)
		{
	    	storage = (InventoryStorage)ls.get(i);  	
	    	strStorageId = storage.getInventoryid();		// ���ID
		 	strProductName = storage.getProductName();  	// ��Ʒ��
			strTrayCode = storage.getTraycode();			// ��������
		  	fPNum        = storage.getPnum() - storage.getAssignnum()- storage.getHoldnum();		// ����
			lotinfo = storage.getLotinfo();
			printdate = storage.getProductdate();
		 	

	    %>	
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" >
     <td class="TD_LIST" align="center"><%=i+1%><input type="radio" name="check_id" value="<%=strStorageId%>" class="input_radio" onClick="Change(this)"></td>
     <td class="TD_LIST" align="center"><%=strTrayCode==null?"":strTrayCode%></td>
     <td class="TD_LIST" align="center"><%=strProductName==null?"":strProductName%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(fPNum)%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
   </tr>
<%		
		}

	}
%>
   <tr>
     <td height="100%" colspan="6"  valign="top">
     <div style="color: red;font-size:12;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   
 </table>
 
</div>
</body>
</html>
