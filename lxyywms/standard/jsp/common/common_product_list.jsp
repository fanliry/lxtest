<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//���õ�ѡ���Ƿ�ѡ��
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		
		//�ı䱳��ɫ
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
-->
</script>
</head>

<body >
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td valign="top" height="100%">
	
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" nowrap><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">Ʒ��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">���</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��װ���</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��λ</div></td>
      <!--<td class="TD_LIST_TITLE" nowrap><div class="list_title">������λ</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ʒ���</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">Ƥ��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">���</div></td>-->
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">�洢����</div></td>
      <!--<td class="TD_LIST_TITLE" nowrap><div class="list_title">��װ����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">�洢����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">Ԥ�����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ת����</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��������</div></td>-->
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ч��</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��Ч������</div></td>
      <!--<td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע2</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע3</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע4</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע5</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע6</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">��ע7</div></td>
      <td class="TD_LIST_TITLE2" nowrap><div class="list_title">�Ƿ�����</div></td>-->
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		BaseProduct product = null; 
		
		String productid;			// ��ƷID
		String productCode;			//��Ʒ����
	    String productname;			// ��Ʒ��
	    String spec;				// ���
	    String barcode;				// ��Ʒ����
	    String pkspec;				// ��װ���
	    String recunit;				// �ջ���λ
	    String sendunit;			// ������λ
	    String pttypename;			// ��Ʒ���
	    double length;				// ��
	    double width;				// ��
	    double height;				// ��
	    double upperlimit;			// �������
	    double lowerlimit;			// �������
	    double weight;				// ����
	    double netweight;			// ����
	    double tareweight;			// Ƥ��
	    double volume;				// ���
	    String storageareaname;		// �洢����
        String pkgdesc;				// ��װ����
	    String putawayid;		// �洢����ID
	    String allocationid;		// �������ID
	    String preallocationid;		// Ԥ�����ID
	    String replenishid;	// ��������ID
	    String turnoverruleid;		// ��ת����ID
	    String producedate;			// ��������
	    double validityterm;		// ��Ч��
	    String validitytypename;	// ��Ч��������
	    String remark1;				// ��ע
	    String remark2;				// ��ע
	    String remark3;				// ��ע
	    String remark5;				// ��ע
	    String remark4;				// ��ע
	    String remark6;				// ��ע
	    String remark7;				// ��ע
	    String useflag;				// ���ñ�־
	    
	    String pakageid;		    // ��װID 
        String lotid;               // ����ID
        
        String lotdesc;				// ������������
     	String pakagedesc;			// ��װ����
	    
	    String strId = "";
		
		for(int i=0; i<ls.size(); i++){
			product = (BaseProduct)ls.get(i); 
                        
			productid = product.getProductid();			// ��ƷID
			productCode=product.getProductCode();		//��Ʒ����
		    productname = product.getProductname();		// ��Ʒ��
		    spec = product.getSpec();					// ���
		    barcode = product.getBarcode();				// ��Ʒ����
		    pkspec = product.getPkspec();				// ��װ���

		    recunit = product.getRecunit();				// ȱʡ�ջ���λ
		    sendunit = product.getSendunit();			// ȱʡ������λ
		    pttypename = product.getPttypename();		// ��Ʒ���
		    length = product.getLength();				// ��
		    width = product.getWidth();					// ��
		    height = product.getHeight();				// ��
		    upperlimit = product.getUpperlimit();		// �������
		    lowerlimit = product.getLowerlimit();		// �������
		    weight = product.getWeight();				// ����
		    netweight = product.getNetweight();			// ����
		    tareweight = product.getTareweight();		// Ƥ��
		    volume = product.getVolume();				// ���
		    storageareaname = product.getStorageareaname();		// �洢����
		    pkgdesc = product.getPkgdesc();						// ��װ����
		    putawayid = product.getPutawayid();			// �洢����ID
		    allocationid = product.getAllocationid();	// �������ID
		    replenishid = product.getReplenishid();		// ��������ID
		    producedate = product.getProducedate();				// ��������
		    validityterm = product.getValidityterm();			// ��Ч��
		    validitytypename = product.getValiditytypename();	// ��Ч������
		    remark1 = product.getRemark1();				// ��ע
		    remark2 = product.getRemark2();				// ��ע
		    remark3 = product.getRemark3();				// ��ע
		    remark5 = product.getRemark4();				// ��ע
		    remark4 = product.getRemark5();				// ��ע
		    remark6 = product.getRemark6();				// ��ע
		    remark7 = product.getRemark7();				// ��ע
		    useflag = product.getUseflag();				// ���ñ�־
		    
		    pakageid = product.getPakageid();		    // ��װID 
            lotid = product.getLotid();                 // ����ID
            
            lotdesc = product.getLotdesc();				// ������������
     	 	pakagedesc = product.getPkgdesc();			// ��װ����
			
			strId = productid + "|"+ productname + "|" + (pakageid == null? " " : pakageid) + "|" + (pakagedesc == null ? " " : pakagedesc) + "|" + (lotid == null ? " " : lotid) + "|" + (lotdesc == null ? " " : lotdesc) + "|" + recunit + "|"+ (productCode == null ? " " : productCode) + "|"+(spec == null ? " " : spec) + "|";
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=strId%>');">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" value="<%=strId%>" class="input_checkbox"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=productname == null ? "" : productname%></td>
     <td class="TD_LIST" align="center"><%=spec == null ? "" : spec%></td>
     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
     <td class="TD_LIST" align="center"><%=pkspec == null ? "" : pkspec%></td>
  
     <td class="TD_LIST" align="center"><%=recunit == null ? "" : recunit%></td>
     <!--<td class="TD_LIST" align="center"><%=sendunit == null ? "" : sendunit%></td>
     <td class="TD_LIST" align="center"><%=pttypename == null ? "" : pttypename%></td>
     <td class="TD_LIST" align="center"><%=length%></td>
     <td class="TD_LIST" align="center"><%=width%></td>
     <td class="TD_LIST" align="center"><%=height%></td>
     <td class="TD_LIST" align="center"><%=upperlimit%></td>
     <td class="TD_LIST" align="center"><%=lowerlimit%></td>
     <td class="TD_LIST" align="center"><%=weight%></td>
     <td class="TD_LIST" align="center"><%=netweight%></td>
     <td class="TD_LIST" align="center"><%=tareweight%></td>
     <td class="TD_LIST" align="center"><%=volume%></td>-->
     <td class="TD_LIST" align="center"><%=storageareaname == null ? "" : storageareaname%></td>
     <!--<td class="TD_LIST" align="center"><%=pkgdesc == null ? "" : pkgdesc%></td>
     <td class="TD_LIST" align="center"><%=putawayid == null ? "" : putawayid%></td>
     <td class="TD_LIST" align="center"><%=allocationid == null ? "" : allocationid%></td>
     <td class="TD_LIST" align="center"><%=replenishid == null ? "" : replenishid%></td>
     <td class="TD_LIST" align="center"><%=producedate == null ? "" : producedate%></td>-->
     <td class="TD_LIST" align="center"><%=validityterm%></td>
     <td class="TD_LIST" align="center"><%=validitytypename == null ? "" : validitytypename%></td>
     <!--<td class="TD_LIST" align="center"><%=remark1 == null ? "" : remark1%></td>
     <td class="TD_LIST" align="center"><%=remark2 == null ? "" : remark2%></td>
     <td class="TD_LIST" align="center"><%=remark3 == null ? "" : remark3%></td>
     <td class="TD_LIST" align="center"><%=remark4 == null ? "" : remark4%></td>
     <td class="TD_LIST" align="center"><%=remark5 == null ? "" : remark5%></td>
     <td class="TD_LIST" align="center"><%=remark6 == null ? "" : remark6%></td>
     <td class="TD_LIST" align="center"><%=remark7 == null ? "" : remark7%></td>
     <td class="TD_LIST" align="center"><%=useflag == null ? "" : useflag%></td>-->
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="18" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </table>
 <!-- ======== ��ҳ��ǩ ======== -->
 </td><tr>
 <tr><td>
   <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td height="25px">
	  <%@ include file="commpage.jsp"%>
    </td><tr>
  </table>
</body>
</html>