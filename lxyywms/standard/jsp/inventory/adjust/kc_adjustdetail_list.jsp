<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
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

<body   onload="javascript:OnLoad();">
<%
	List list = null;
    String inventoryNCId = (String)request.getAttribute("inventoryNCId");
	if(request.getAttribute("exResultList") != null) 
	{
		list = (List)request.getAttribute("exResultList");
	}
%>
<form id="myform" name="myform" method="post" action="">
  <table width="200%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr class="list_title_tr">
	  <%--<td width="40" class="TD_LIST_TITLE">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" 
	    	onclick="selectAll('myform','checkbox_all','checkbox_id')"></td>--%>
	  <td class="TD_LIST_TITLE" style="width: 50"> <div class="list_title">�к�</div></td> 
	  <td class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>
	 
	  <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Դ��Ʒ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�ֲ�Ʒ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Դ��λ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">�ֵ�λ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Դ��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Դ��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Դ������Ϣ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">��������Ϣ</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">Դ��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">ϵͳ����</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">ʵ������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();         
		nbf.setMinimumFractionDigits(2);
		InventoryAdjustDetail detail=null;
		String id;			    //��ϸid
		String status;			//״̬��1��δ���� 2���ѵ�����
		String cargo_space_id;	//��λID
		String wh_area_id;		//����ID
		String warehouseid;		//�ֿ�ID
		String spunit;			//Դ������λ
		String tpunit;			//�ּ�����λ
		String sproductid;		//Դ��ƷID
		String tproductid;		//�ֲ�ƷID
		String sprintdate;		//Դ��Ʒ��������
		String tprintdate;		//�ֲ�Ʒ��������
		String slotid;			//Դ��������
		String tlotid;			//����������
		String slotstr;		//Դ��������
		String tlotstr;		//����������
		String slotinfo;
		String tlotinfo;
		String straycode;		//Դ��������
		String ttraycode;		//����������
		double syspnum;			//ϵͳ����
		double realitypnum;		//ʵ������
		String jobid;			//��ҵID
		String createtime;		//����ʱ��
		String adjusttime;		//����ʱ��
		String inventoryid;		//�������͵�ID
		double adjustnum;       //��������
        for(int i=0; i<list.size(); i++)
        {
             detail = (InventoryAdjustDetail)list.get(i); 
             id = detail.getAdjustdetailid();
             status = detail.getStatusname();
        	 jobid = detail.getJobid();
        	 wh_area_id = detail.getWh_area_id();
        	 cargo_space_id = detail.getCargo_space_id();	
        	 sproductid = detail.getSproductname();
        	 tproductid = detail.getTproductname();
        	 spunit = detail.getSpunit();
        	 tpunit = detail.getTpunit();
        	 sprintdate = detail.getSprintdate();
        	 tprintdate = detail.getTprintdate();
        	 slotid = detail.getSlotid();
        	 tlotid = detail.getTlotid();
        	 slotstr = detail.getSlotName();
        	 tlotstr = detail.getTlotName();
        	 slotinfo = detail.getSlotinfo();
        	 tlotinfo =  detail.getTlotinfo();
        	 straycode = detail.getStraycode();
        	 ttraycode = detail.getTtraycode();
        	 syspnum = detail.getSyspnum();
        	 realitypnum = detail.getRealitypnum();
        	 adjustnum = Math.abs(syspnum-realitypnum);
        	 createtime = detail.getCreatetime(); 	//����ʱ��
        	 adjusttime = detail.getAdjusttime();
        	 inventoryid = detail.getInventoryid();//�������͵�ID
%>	
	<tr class="list_normal_tr" >
	  <td class="TD_LIST" align="center"><input type="checkbox" name="checkbox_id" value="<%=id%>" class="input_checkbox"><%=i+1%></td>
	  <td class="TD_LIST" align="center"><%=status == null ? "&nbsp;" : status %></td>
	  
	  <td class="TD_LIST" align="center"><%=wh_area_id == null ? "&nbsp;" : wh_area_id%></td>
	  <td class="TD_LIST" align="center"><%=cargo_space_id == null ? "&nbsp;" : cargo_space_id%></td>
	  <td class="TD_LIST" align="center"><%=sproductid == null ? "&nbsp;" : sproductid%></td>
	  <td class="TD_LIST" align="center"><%=tproductid == null ? "&nbsp;" : tproductid%></td>
	  <td class="TD_LIST" align="center"><%=spunit == null ? "&nbsp;" : spunit%></td>
	  <td class="TD_LIST" align="center"><%=tpunit == null ? "&nbsp;" : tpunit%></td>
	  <td class="TD_LIST" align="center"><%=sprintdate == null ? "&nbsp;" : sprintdate%></td>
	  <td class="TD_LIST" align="center"><%=tprintdate == null ? "&nbsp;" : tprintdate%></td>
	  <td class="TD_LIST" align="center"><%=slotstr == null ? "&nbsp;" : slotstr%></td>
	  <td class="TD_LIST" align="center"><%=tlotstr == null ? "&nbsp;" : tlotstr%></td>
	  <td class="TD_LIST" align="center"><%=slotinfo == null ? "&nbsp;" : slotinfo%></td>
	  <td class="TD_LIST" align="center"><%=tlotinfo == null ? "&nbsp;" : tlotinfo%></td>
	  <td class="TD_LIST" align="center"><%=straycode == null ? "&nbsp;" : straycode%></td>
	  <td class="TD_LIST" align="center"><%=ttraycode == null ? "&nbsp;" : ttraycode%></td>
	  <td class="TD_LIST" align="center"><%=syspnum%></td>
	  <td class="TD_LIST" align="center"><%=realitypnum%></td>
	  <td class="TD_LIST" align="center"><%=adjustnum%></td>
	  <td class="TD_LIST" align="center"><%=createtime == null ? "&nbsp;" : createtime%></td>
	  <td class="TD_LIST" align="center"><%=adjusttime == null ? "&nbsp;" : adjusttime%></td>
	</tr>
<%
        }
    }else
	{
		session.removeAttribute("page");
	}
%>	
	<tr height="100%">
	  <td height="100%" colspan="<%=13%>" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(list != null && list.size() == 0){%>��������ݣ�<%}%></div>
	  </td>
	</tr>
  </table>

</form>
</body>
</html>
