<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
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
		//parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
// 		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="180%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" align="center" width="50px"><div class="list_title">ѡ��</div></td>
     <td class="TD_LIST_TITLE" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ͻ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ʱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ⵥ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������뵥</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	if(ls != null && ls.size()>0){
		
		Object[] obj = null;
		InoutJob job = null;
		InoutJobdetail detail = null;
     	
     	String jobid;      	//��ҵ��
     	String jobtype;    //��ҵ���� ����������ҵ����Ϊ ������͵�hlֵ��
     	String jobtypeid;
     	String productCode;//��Ʒ����
     	String m_strProductName = null;	 //Ʒ��
		String lotinfo = null;	         //��������
		String printdate = null;	     //��������
		String m_strUnitName = null;	//��λ
		double jobnum = 0;			    //����
		String warehouseName;  	//�ֿ�
     	String alley;    		//���
     	String cargospace;     	//��λ
     	String traycode;     	//��������
     	String snumber;    		//���ͺ�
     	String createtime;  	//����ʱ��
     	String finishtime; 		//���ʱ��
     	String invoicetype;//1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
     	
     	String boundstockid="";
     	String boundrequeststockid="";
     	String alt="";
     	String key="";
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			detail = (InoutJobdetail)obj[1];
			
			jobid = job.getJobid();      			//��ҵ��
			jobtypeid = job.getJobtype();
     		jobtype = job.getJobtypeName();
			productCode = detail.getProductcode();
			m_strProductName = detail.getM_strProductName();
			lotinfo = detail.getLotinfo();
			printdate = detail.getPrintdate();
			m_strUnitName = detail.getM_strUnitName();
			jobnum = detail.getJobnum();
			
     		warehouseName = job.getWarehousename();	//�ֿ�
     		alley = job.getTcargoAlleyName();		//���
     		cargospace = job.getTcargoSpaceName(); 	//��λ
     		traycode = job.getTraycode();     		//��������
     		snumber = job.getSnumber();    			//���ͺ�
     		createtime = job.getCreatetime();  		//����ʱ��
     		finishtime = job.getFinishtime(); 		//���ʱ��
     		invoicetype = job.getInvoicetypename();
     		
     		boundstockid = job.getBoundstockid();
			boundrequeststockid = job.getBoundrequeststockid();
			
			alt = (i+1) + "|" +  jobtypeid;
			key = jobid;
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
   	 <td class="TD_LIST1" align="center" width="50px"><input type="checkbox" name="check_id" alt="<%=alt %>" value="<%=key %>" class="input_radio" onClick=""></td>
     <td class="TD_LIST" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td> 
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td>
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=warehouseName==null?"":warehouseName%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=snumber==null?"":snumber%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td>
     <td class="TD_LIST" align="center"><%=finishtime==null?"":finishtime%></td>
     <td class="TD_LIST2" align="center"><%=boundstockid==null?"":boundstockid%></td>  
     <td class="TD_LIST" align="center"><%=boundrequeststockid==null?"":boundrequeststockid%></td> 
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="18" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>