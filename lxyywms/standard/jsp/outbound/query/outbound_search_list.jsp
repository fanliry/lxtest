<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%

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

	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="130%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����Ա</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">����ģʽ</div></td>
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
		
		Object[] obj = null;
		InoutJob job = null;
		InoutJobdetail detail = null;
		
		String product = null;		// Ʒ��
		String owerner = null;      // ����
		String pack = null;			// ��װ
		String punit = null;		// ��λ
		String boxcode = null;		// ������
		double jobnum = 0;			// ����
		double volume = 0;         	// ���
 		double weight = 0;         	// ����
		double netweight = 0;		// ����
		

     	
     	String jobid;      		//��ҵ��
     	String status;       	//��ҵ״̬
     	String jobtype;         //��ҵ����
     	String whArea;  		//����
     	String alley;    		//���
     	String cargospace;     	//��λ
     	String traycode;     	//��������
     	String createtime;  	//����ʱ��
     	String opMan;  			//����Ա
     	String invoicetype;  	//��ҵ��Դ
     	String onLineType;  	//����ģʽ
     	
     	//С��
		double jobnum_sum = 0;			// ����
		double volume_sum = 0;         	// ���
 		double weight_sum = 0;         	// ����
		double netweight_sum = 0;		// ����
		
		String  lotinfo = "";
		String  printdate = "";
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			detail = (InoutJobdetail)obj[1];
			
			lotinfo = detail.getLotinfo();
			printdate = detail.getPrintdate();
			product = detail.getM_strProductName();		// Ʒ��
			owerner = detail.getM_strOwnerName();   	// ����
			pack = detail.getM_strPackName();			// ��װ
			punit = detail.getM_strUnitName();			// ��λ
			boxcode = detail.getBoxcode();				// ������
			jobnum = detail.getJobnum();				// ����
			volume = detail.getVolume();         		// ���
 			weight = detail.getWeight();         		// ����
			netweight = detail.getNetweight();			// ����
			
			
     	 	
     	 	jobid = job.getJobid();      			//��ҵ��
     		status = job.getStatusName();       	//��ҵ״̬
     		jobtype = job.getJobtypeName();     	//��ҵ����
     		whArea = job.getTcargoWhareaName();		//����
     		alley = job.getTcargoAlleyName();		//���
     		cargospace = job.getTcargoSpaceName(); 	//��λ
     		traycode = job.getTraycode();     		//��������
     		createtime = job.getCreatetime();  		//����ʱ��
     		opMan = job.getOpMan();  				//����Ա
     		invoicetype = job.getInvoicetype();  	//��ҵ��Դ
     		onLineType = job.getOnLineType();  		//����ģʽ
     		
     		//С��
			jobnum_sum += jobnum;			// ����
			volume_sum += volume;         	// ���
 			weight_sum += weight;         	// ����
			netweight_sum += netweight;		// ����
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=punit==null?"":punit%></td>
     <td class="TD_LIST" align="center"><%=(int)jobnum%></td>
     <td class="TD_LIST" align="center"><%=jobid%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=whArea==null?"":whArea%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=createtime%></td>   
     <td class="TD_LIST" align="center"><%=opMan%></td>
     <td class="TD_LIST2" align="center"><%=onLineType=="1"?"����":"�ѻ�"%></td>
   </tr>
<%
		}
%>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;">��ҳС��</td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=(int)jobnum_sum%></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center"></td>
   </tr>
<%
	}
%>  
   <tr>
     <td height="100%" colspan="<%=16%>" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>