<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>
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
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
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
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="120%" id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ч��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ/���״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ����</div></td>
   </tr>
<%!

	/**
	 * ���ܣ������Ʒ�Ƿ����
	 * @param printdate
	 * @param losdate
	 * @return 
	 */
	public static long isWarningDate(String printdate , double losdate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try
		{
			Date date = df.parse(printdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, (int)losdate);
			long time= System.currentTimeMillis();
			Date d1=new Date(time);
		    Date d3 = df.parse(df.format(cal.getTime()));
		    long diff = d3.getTime() - d1.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
		    return days;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return 0;
	}

%>
<%

	TreeMap<String, Double> treeMap = new TreeMap<String, Double>();
	//��Ʒ��Ч��
	List ls2 = (List)request.getAttribute("exResultList2");
	if(ls2 != null){
		BaseProduct product = null; 
		
		String productid;		//��ƷID
		String productCode;			// ��Ʒ����
		String productname2;			// ��Ʒ��2
	
	    double validityterm;		// ��Ч��
	    String validitytypename;	// ��Ч��������
	
		
		//�Ѳ�Ʒ  ����ʽ����Ʒ������Ч�ڣ����뼯��          ע����Ч������ Ĭ��Ϊ�졣
	
	
		for(int i=0; i<ls2.size(); i++){
			product = (BaseProduct)ls2.get(i); 
		    
			productid = product.getProductid();	//	��ƷD
			productCode = product.getProductCode();			// ��Ʒ����
			productname2 = product.getProductname();		// ��Ʒ��2
		    validityterm = product.getValidityterm();			// ��Ч��
		    validitytypename = product.getValiditytypename();	// ��Ч������
		    //String valdiyty = new String(Double.toString(validityterm));
		    //String valdiyty = validityterm;
		    treeMap.put(productname2, validityterm);
		    
		}
	}
	
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
	    InventoryStorage storage = null;
	    BaseCargospace space = null;
		String whArea;				//����
 		String cargoSpace;			//��λ����
 		String cargoSpaceName;		//��λ��
 	 	String product;				//��Ʒ
 	 	String lotnumber;		    //����
 	 	String instockid;           //��ⵥ
 	 	String requestid;           //���뵥
 	 	String traycode;        	//��������
		String punit;				//��λ
		double pnum;            	//�������
		String indate;             	//���ʱ��
		String intype;             	//��ⷽʽ 1.���� 2.�ѻ�
        String productcode;         //��Ʒ����
        String printdate;         //��������
        double losdate;         //��Ч��
        long warningdate;         //ʣ��ʱ��
        String csstauts;         //���״̬
        String productstatus;     //��Ʒ״̬
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 	    ob = (Object[])ls.get(i);
  	 		storage = (InventoryStorage)ob[0];
  	 		space = (BaseCargospace)ob[1];
  	 		whArea = storage.getWhAreaName();		//����
  	 		cargoSpace = storage.getCargoSpaceId(); //��λ����
  	 		cargoSpaceName = storage.getCargoSpaceName();//��λ��
  	 		product = storage.getProductName();		//��Ʒ
  	 		lotnumber = storage.getLotinfo();		//����
  	 		traycode = storage.getTraycode();		//��������
  	 		punit = storage.getPunitname();			//��λ
  	 		productcode = storage.getProductcode(); //��Ʒ����		
			pnum = storage.getPnum();           //�������
			indate = storage.getIndate();       //���ʱ��
  	 		intype = storage.getIntype();		//��ⷽʽ 1.���� 2.�ѻ�
  	 		requestid = storage.getRequestid();
  	 		instockid = storage.getInstockid();
  	 		
  	 		printdate = storage.getProductdate(); //��������
  	 		csstauts = space.getCsstatusname()+"/"+storage.getStatusName();
  	 		losdate = treeMap.get(product);  //��Ч��
  	 		
  	 		warningdate = isWarningDate(printdate , losdate);  //ʣ��ʱ��
  	 		
  	 		productstatus = storage.getProductStatusName();
  	 		traycode = storage.getTraycode();
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceName == null ? "" : cargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=indate == null ? "" : indate%></td>
     <td class="TD_LIST" align="center"><%=lotnumber == null ? "" : lotnumber%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
     <td class="TD_LIST" align="center"><%=losdate%>��</td>
     
     <td class="TD_LIST" align="center">
    <%      
    out.println("<span style=\"color:" +(warningdate < 50 ? "red;font-weight: bold" : "black") + ";\">" + warningdate +"��"+ "</span>");
    %>
    </td>
     <td class="TD_LIST" align="center"><%=csstauts == null ? "" : csstauts%></td>
     <td class="TD_LIST" align="center"><%=productstatus == null ? "" : productstatus%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
      <td class="TD_LIST" align="center"><%=cargoSpace == null ? "" : cargoSpace%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=13%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
