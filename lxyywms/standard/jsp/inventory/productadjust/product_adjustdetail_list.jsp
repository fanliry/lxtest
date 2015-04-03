<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryTransferDetail" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.dao.base.impl.BasePackageDaoImpl" %>
<%@ page import="com.ricosoft.common.dao.dataSource.EntityDAO" %>
<%@ page import="com.wms3.bms.standard.service.BMSService" %>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackage" %>
<%@ page import="com.wms3.bms.standard.dao.base.impl.BasePackageMastermesgDaoImpl" %>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackageMastermesg" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%
	HashMap<String, List> hsLot = (HashMap<String, List>)session.getAttribute("viewLot");
	List lsLot = hsLot.get("hptzkc");
	EntityDAO dao = BMSService.getm_EntityDAO();
%>
<html>
<head>
  <title>�ִ����͹���ϵͳ</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/commonHandle.js"></script>
  <script type="text/javascript">
  <!--
	 function OnLoad(){
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}
			else{
				alert(back_msg);
			}
		}
	}
  -->
  </script>
</head>

<body onload="javascript:OnLoad();">
<%
	List list = null;
	if(request.getAttribute("exResultList") != null) 
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr class="list_title_tr">
	  <td width="40" class="TD_LIST_TITLE">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll('myform','checkbox_all','checkbox_id')"></td>
	   <td width="40" class="TD_LIST_TITLE"><div class="list_title">�к�</div></td> 
	   <td width="60" class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>	

	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">ë��</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">���</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">������</div></td>
	  <td width="80" class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
	  
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM�ͻ�ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM��װ</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM������λ</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM����ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM��ƷID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">FM��������</div></td>
	  
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO�ͻ�ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO��װID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO������λ</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO����ID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO��ƷID</div></td>
	  <td width="60" class="TD_LIST_TITLE"><div class="list_title">TO��������</div></td>
	  
	    <%
		int iLine = 0;	//��ʾ���������Ը���
		BaseLotSet lotSet = null;
		if(lsLot != null){
			iLine = lsLot.size();
			for(int k = 0; k < iLine; k++){
				lotSet = (BaseLotSet)lsLot.get(k);			
		%>
			 <td class="TD_LIST_TITLE"><div class="list_title">FM<%=lotSet.getLotname()%></div></td>
			 <td class="TD_LIST_TITLE"><div class="list_title">TO<%=lotSet.getLotname()%></div></td>
		<%
				}
			}
		%> 

	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();         
		nbf.setMinimumFractionDigits(2);
		InventoryTransferDetail detail=null;
        String FMlotatt;  			//��������
        String TOlotatt;  			//��������
        String strDId="";
        String strStatus="";
        String strLocId="";
        double qty;
        String fGrossweight="";
        String fCubic="";
        String creatman="";
        String strEditTime="";
        String strStatusName="";
        
        String fmcustomerid="";
        String fmcustomername="";
        String tocustomername="";
        String fmpackid="";
        String fmpackname="";
        String fmpunit="";
        String fmpunitname="";
        String fmlotid="";
        String fmlotName="";
        String fmproductid="";
        String fmproductname="";
        String fmtraycode="";
        
        String tocustomerid="";
        String topackid="";
        String topackname="";
        String topunit="";
        String topunitname="";
        String tolotid="";
        String tolotname="";
        String toproductid="";
        String toproductname="";
        String totraycode="";
        BasePackageDaoImpl BasePackageIml= new BasePackageDaoImpl(dao);
        BasePackage basepackage=null;
        
        BasePackageMastermesgDaoImpl BasePackageMastermesgIml= new BasePackageMastermesgDaoImpl(dao);
        BasePackageMastermesg basemesg=null;
        
        
        for(int i=0; i<list.size(); i++)
        {
        
             detail = (InventoryTransferDetail)list.get(i); 
             strDId = detail.getTransferdetailid();//��ϸID
        	 strStatus = detail.getStatus();  //״̬
        	 strStatusName = strStatus.trim().equals("0")?"����":"���";
        	 strLocId = detail.getCargo_space_id();	 //��λ
        	 qty = (double)detail.getPnum();	//�������
        	 fGrossweight = nbf.format(detail.getPnetweight());//ë��
        	 fCubic = nbf.format(detail.getPvolume());		//���
        	 creatman = detail.getCreatemanid();	 //������
        	 strEditTime = detail.getCreatetime(); 	//����ʱ��
        	 
        	 fmcustomerid = detail.getFmcustomerid(); //FM�ͻ�id
        	 fmpackid = detail.getFmpackid();//FM��װ
        	 basepackage = BasePackageIml.getPackageById(fmpackid);
        	 if(basepackage!=null){
        	    fmpackname = basepackage.getPkgdesc();
        	 }
        	 
        	 
        	 
        	 fmpunit = detail.getFmpunit();//FM��λ
        	 basemesg = BasePackageMastermesgIml.getPackageMastermesg(fmpackid,fmpunit);
        	 if(basemesg!=null){
        	    fmpunitname = basemesg.getPkgunitdesc();
        	 }
        	 
        	 fmlotid = detail.getFmlotid();//FM����id
        	 fmlotName = detail.getFmlotname();
        	 
        	 fmproductid = detail.getFmproductid();//FM��Ʒid
        	 fmproductname = detail.getFmproductname();
        	 fmtraycode = detail.getFmtraycode();//FM��������
        	 
        	 tocustomerid = detail.getTocustomerid(); //TO�ͻ�id
        	 tocustomername = detail.getTocustomername(); //TO�ͻ�id
        	 topackid = detail.getTopackid();//TO��װ
        	 basepackage = BasePackageIml.getPackageById(topackid);
        	 if(basepackage!=null){
        	    topackname = basepackage.getPkgdesc();
        	 }
        	 topunit = detail.getTopunit();//TO��λ
        	 basemesg = BasePackageMastermesgIml.getPackageMastermesg(topackid,topunit);
        	 if(basemesg!=null){
        	    topunitname = basemesg.getPkgunitdesc();
        	 }
        	 tolotid = detail.getTolotid();//TO����id
        	 tolotname = detail.getToproductname();
        	 toproductid = detail.getToproductid();//TO��Ʒid
        	 toproductname = detail.getToproductname();
        	 totraycode = detail.getTotraycode();//TO��������
       	 
%>	
	<tr class="list_normal_tr" >
	  <td class="TD_LIST">
	    <input type="checkbox" name="checkbox_id" value="<%=strDId%>" class="input_checkbox">
	  </td>
	  <td class="TD_LIST"><%=i+1%></td>
	  <td class="TD_LIST"><%=strStatusName == null ? "&nbsp;" : strStatusName%></td>
	  <td class="TD_LIST"><%=strLocId == null ? "&nbsp;" : strLocId%></td>
	  <td class="TD_LIST"><%=qty%></td>
	  <td class="TD_LIST"><%=fGrossweight%></td>
	  <td class="TD_LIST"><%=fCubic%></td>
	  <td class="TD_LIST"><%=creatman == null ? "&nbsp;" : creatman%></td>
	  <td class="TD_LIST"><%=strEditTime == null ? "&nbsp;" : strEditTime%></td>
	  
	  <td class="TD_LIST"><%=fmcustomerid == null ? "&nbsp;" : fmcustomerid%></td>
	  <td class="TD_LIST"><%=fmpackname == null ? "&nbsp;" : fmpackname%></td>
	  <td class="TD_LIST"><%=fmpunitname == null ? "&nbsp;" : fmpunitname%></td>
	  <td class="TD_LIST"><%=fmlotName == null ? "&nbsp;" : fmlotName%></td>
	  <td class="TD_LIST"><%=fmproductname == null ? "&nbsp;" : fmproductname%></td>
	  <td class="TD_LIST"><%=fmtraycode == null ? "&nbsp;" : fmtraycode%></td>
	  
	  <td class="TD_LIST"><%=tocustomerid == null ? "&nbsp;" : tocustomername%></td>
	  <td class="TD_LIST"><%=topackname == null ? "&nbsp;" : topackname%></td>
	  <td class="TD_LIST"><%=topunitname == null ? "&nbsp;" : topunitname%></td>
	  <td class="TD_LIST"><%=tolotname == null ? "&nbsp;" : tolotname%></td>
	  <td class="TD_LIST"><%=toproductname == null ? "&nbsp;" : toproductname%></td>
	  <td class="TD_LIST"><%=totraycode == null ? "&nbsp;" : totraycode%></td>
 <%
	if(lsLot != null){
		for(int k = 0; k < iLine; k++){
			FMlotatt = "";		//��������
			TOlotatt = "";		//��������
			lotSet = (BaseLotSet)lsLot.get(k);
			if(lotSet!=null && lotSet.getLotid()!=null && lotSet.getLotid().length()>=7){
			    switch(Integer.parseInt(lotSet.getLotid().substring(6, lotSet.getLotid().length())))
				{
					 case 1:FMlotatt=detail.getFmlotatt1();break;
					 case 2:FMlotatt=detail.getFmlotatt2();break;
					 case 3:FMlotatt=detail.getFmlotatt3();break;
					 case 4:FMlotatt=detail.getFmlotatt4();break;
					 case 5:FMlotatt=detail.getFmlotatt5();break;
					 case 6:FMlotatt=detail.getFmlotatt6();break;
					 case 7:FMlotatt=detail.getFmlotatt7();break;
					 case 8:FMlotatt=detail.getFmlotatt8();break;
					 case 9:FMlotatt=detail.getFmlotatt9();break;
					 case 10:FMlotatt=detail.getFmlotatt10();break;
					 case 11:FMlotatt=detail.getFmlotatt11();break;
					 case 12:FMlotatt=detail.getFmlotatt12();break;
				}
				switch(Integer.parseInt(lotSet.getLotid().substring(6, lotSet.getLotid().length())))
				{
					 case 1:TOlotatt=detail.getTolotatt1();break;
					 case 2:TOlotatt=detail.getTolotatt2();break;
					 case 3:TOlotatt=detail.getTolotatt3();break;
					 case 4:TOlotatt=detail.getTolotatt4();break;
					 case 5:TOlotatt=detail.getTolotatt5();break;
					 case 6:TOlotatt=detail.getTolotatt6();break;
					 case 7:TOlotatt=detail.getTolotatt7();break;
					 case 8:TOlotatt=detail.getTolotatt8();break;
					 case 9:TOlotatt=detail.getTolotatt9();break;
					 case 10:TOlotatt=detail.getTolotatt10();break;
					 case 11:TOlotatt=detail.getTolotatt11();break;
					 case 12:TOlotatt=detail.getTolotatt12();break;
				}
			}			
%>
	 		 <td class="TD_LIST" align="center"><%=FMlotatt!=null?FMlotatt:""%></td>
	 		 <td class="TD_LIST" align="center"><%=TOlotatt!=null?TOlotatt:""%></td>
<%
		}
	}
%>
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("paging");
	}
%>	
	<tr height="100%">
	  <td height="100%" colspan="<%=21 + 2*iLine%>" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>
</form>
</body>
</html>
