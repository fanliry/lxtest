<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>
<html>
<head>
<title>��Ʒ��Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

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
	
	//�޸�˫����
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseProductAction&flag=2&id="+id, "", "dialogWidth=750px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = parent.page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			location.href = ac + "baseProductAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	function OnLoad1(){
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad1()">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" nowrap width="50px"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">�к�</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ��׼��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ���</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��λ</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ���</div></td>

      <td class="TD_LIST_TITLE" nowrap><div class="list_title">�洢����</div></td>
      <td class="TD_LIST_TITLE" nowrap width="50px"><div class="list_title">��Ч��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ч������</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע2</div></td>
      <td class="TD_LIST_TITLE2" nowrap><div class="list_title">�Ƿ�����</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseProduct product = null; 
		
		String productid;		//��ƷID
		String productCode;			// ��Ʒ����
	    String productname;			// ��Ʒ��׼��
	    String spec;				// ��Ʒ���
	    String barcode;				// ��Ʒ����
	    String pkspec;				// ��Ʒ������
	    String recunit;				// ������λ
	    //String sendunit;			// ȱʡ������λ
	    String pttypename;			// ��Ʒ���
	    double length;				// ��
	    double width;				// ��
	    double height;				// ��
	    double upperlimit;			// �������
	    double lowerlimit;			// �������
	    //double weight;				// ����
	    //double netweight;			// ����
	    //double tareweight;			// Ƥ��
	    //double volume;				// ���
	    String storageareaname;		// �洢����
	    String storagespacename;	// �洢��λ
	   // String putaway;				// �ϼܹ���
	    String allocation;			// �������
	   // String replenish;			// ��������
	    String lotdesc;				// ������������
        String pkgdesc;				// ��װ����
       // String customername;		// �ͻ�����
	   // String producedate;			// ��������
	    double validityterm;		// ��Ч��
	    String validitytypename;	// ��Ч��������
	    //String remark1;				// ��ע
	    String remark2;				// ��ע
	    String remark3;				// ��ע
	  //  String remark5;				// ��ע
	    String remark4;				// ��ע
	    //String remark6;				// ��ע
	   // String remark7;				// ��ע
	   // String isexcess;            // �Ƿ����ջ�
		//String isproductmixed;      // �Ƿ������Ʒ���
		//String isbatchmixed;        // �Ƿ��������λ��
	    String useflag;				// ���ñ�־
	    String producttype;
	    String producttypename="";

		for(int i=0; i<ls.size(); i++){
			product = (BaseProduct)ls.get(i); 
            
			productid = product.getProductid();	//	��ƷD
			productCode = product.getProductCode();			// ��Ʒ����
		    productname = product.getProductname();		// ��Ʒ��
		    spec = product.getSpec();					// ���
		    barcode = product.getBarcode();				// ��Ʒ����
		    pkspec = product.getPkspec();				// ��װ���
		    recunit = product.getRecunit();				// ȱʡ�ջ���λ
		  //  sendunit = product.getSendunit();			// ȱʡ������λ
		    //pttypename = product.getPttypename();		// ��Ʒ���
		    length = product.getLength();				// ��
		    width = product.getWidth();					// ��
		    height = product.getHeight();				// ��
		    upperlimit = product.getUpperlimit();		// �������
		    lowerlimit = product.getLowerlimit();		// �������
		    //weight = product.getWeight();				// ����
		    //netweight = product.getNetweight();			// ����
		    //tareweight = product.getTareweight();		// Ƥ��
		    //volume = product.getVolume();				// ���
		    storageareaname = product.getStorageareaname();		// �洢����
		    storagespacename = product.getStoragespacename();	// �洢��λ
		   //putaway = product.getPutawayname();			// �ϼܹ���
		    allocation = product.getAllocationname();	// �������
		    //replenish = product.getReplenishname();		// ��������
		    lotdesc = product.getLotdesc();						// ������������
		    pkgdesc = product.getPkgdesc();						// ��װ����
		    //customername = product.getCustomername();			// �ͻ�����
		    //producedate = product.getProducedate();				// ��������
		    validityterm = product.getValidityterm();			// ��Ч��
		    validitytypename = product.getValiditytypename();	// ��Ч������
		    //remark1 = product.getRemark1();				// ��ע
		    remark2 = product.getRemark2();				// ��ע
		    remark3 = product.getRemark3();				// ��ע
		   // remark5 = product.getRemark4();				// ��ע
		    remark4 = product.getRemark5();				// ��ע
		    //remark6 = product.getRemark6();				// ��ע
		    //remark7 = product.getRemark7();				// ��ע
		    //isexcess = product.getIsexcess();			// �Ƿ����ջ�
			//isproductmixed = product.getIsproductmixed();  // �Ƿ������Ʒ���
			//isbatchmixed = product.getIsbatchmixed();   // �Ƿ��������λ��
		    useflag = product.getUseflag();				// ���ñ�־
		    producttype = product.getProducttype();
		    producttypename = product.getProducttypename();

%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST" align="center">
     	<input type="checkbox" name="check_id" class="input_checkbox" value="<%=productid%>" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
     <td class="TD_LIST" align="center"><%=productname == null ? "" : productname%></td>
     <td class="TD_LIST" align="center"><%=spec == null ? "" : spec%></td>
     <td class="TD_LIST" align="center"><%=barcode == null ? "" : barcode%></td>
     <td class="TD_LIST" align="center"><%=recunit == null ? "" : recunit%></td>
     <td class="TD_LIST" align="center"><%=producttypename == null ? "" : producttypename%></td>
     
     <td class="TD_LIST" align="center"><%=storageareaname == null ? "" : storageareaname%></td>
     <td class="TD_LIST" align="center"><%=validityterm%></td>
     <td class="TD_LIST" align="center"><%=validitytypename == null ? "" : validitytypename%></td>
     <td class="TD_LIST" align="center"><%=remark2 == null ? "" : remark2%></td>
     <td class="TD_LIST2" align="center"><%=useflag == null ? "" : useflag.equals("Y")?"��":"��"%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="39" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>