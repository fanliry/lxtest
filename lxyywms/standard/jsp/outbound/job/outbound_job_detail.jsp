<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	
</SCRIPT>
</head>
<body>
 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">�к�</div>
     </td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƿ񸴺�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
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
	
		InoutJobdetail detail = null;
		String jobdetailid = null;	// ��ҵ��ϸID
		String productcode=null;//��Ʒ����
		String product = null;		// Ʒ��
		String isFH = null;			//�Ƿ񸴺�
		String owerner = null;      // ����
		String pack = null;			// ��װ
		String punit = null;		// ��λ
		String boxcode = null;		// ������
		double jobnum = 0;			// �������
		double jobfpnum = 0;			// ��������
		double volume = 0;         	// ���
 		double weight = 0;         	// ����
		double netweight = 0;		// ����
		String isinvoice;      		// �Ƿ������ɵ��� Y��  N��
		String printdate; //��������
		String lotinfo="";
		
		for(int i = 0; i < ls.size(); i++){
			detail = (InoutJobdetail)ls.get(i);
			
			jobdetailid = detail.getJobdetailid();		// ��ҵ��ϸID
			productcode = detail.getProductcode();
			product = detail.getM_strProductName();		// Ʒ��
			isFH = detail.getIsreview();	//�Ƿ񸴺�
			owerner = detail.getM_strOwnerName();   	// ����
			pack = detail.getM_strPackName();			// ��װ
			punit = detail.getM_strUnitName();			// ��λ
			boxcode = detail.getBoxcode();				// ������
			jobnum = detail.getJobnum();				// ����
			jobfpnum = detail.getAssignnum();           //������ҵ
			volume = detail.getVolume();         		// ���
 			weight = detail.getWeight();         		// ����
			netweight = detail.getNetweight();			// ����
			isinvoice = detail.getIsinvoice();			// �Ƿ������ɵ��� Y��  N��
			printdate= detail.getPrintdate();
			lotinfo = detail.getLotinfo();
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
     <td class="TD_LIST" align="center"><%=isFH==null?"":isFH%></td>
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td> 
     <td class="TD_LIST" align="center"><%=punit==null?"":punit%></td> 
     <td class="TD_LIST" align="center"><%=(int)jobnum%></td>
     <td class="TD_LIST" align="center"><%=(int)jobfpnum%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="9" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>