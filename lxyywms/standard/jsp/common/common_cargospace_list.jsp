<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace" %>
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
	
	function OnLoad(){
		parent.RemoveLoading();
	}
  -->
</script>
</head>

<body onLoad="OnLoad()">
  
  <table width="180%" height="90%"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50" nowrap><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">��λ</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">�����ֿ�</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">��λ״̬</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">��λʹ��</div></td>
      <td class="TD_LIST_TITLE" width="70" nowrap><div class="list_title">�洢����</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">�ϼ�����</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">��ѡ����</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">���</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">�߶�</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">���</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE" width="80" nowrap><div class="list_title">�ɷ�������</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE2" width="100" nowrap><div class="list_title">����</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		BaseCargospace cargospace = null; 
		
		String cargoSpaceId;		// ��λID
		String cargoSpaceName;		// ��λ����
		String warehousename;		// �ֿ�����
		String whAreaName;			// ��������
		String csstatus;			// ��λ״̬
		String usagetype;   		// ��λʹ��
		String storagetype;			// �洢����
		String puttype;				// �ϼ�����
		String picktype;			// ��ѡ����
		
		double length;				// ��
		double width;				// ��
		double height;				// ��
		double volume;				// ���
		double loadweight;			// ����
		Integer palletnum;			// �ɷ�������
		Integer layernum;			// ����
		String environment;			// ����
	    
	    String strId = "";
		
		for(int i=0; i<ls.size(); i++){
			cargospace = (BaseCargospace)ls.get(i); 
                        
			cargoSpaceId = cargospace.getCargoSpaceId();		// ��λID
			cargoSpaceName = cargospace.getCargoSpaceName();	// ��λ����
			warehousename = cargospace.getWarehousename();		// �ֿ�����
			whAreaName = cargospace.getWhAreaName();			// ��������
			csstatus = cargospace.getCsstatusname();			// ��λ״̬
			
			usagetype = cargospace.getUsagetypename();   		// ��λʹ��
			storagetype = cargospace.getStoragetypename();		// �洢����
			puttype = cargospace.getPuttypename();				// �ϼ�����
			picktype = cargospace.getPicktypename();			// ��ѡ����
		
			length = cargospace.getLength();					// ��
			width = cargospace.getWidth();						// ��
			height = cargospace.getHeight();					// ��
			volume = cargospace.getVolume();					// ���
			loadweight = cargospace.getLoadweight();			// ����
			palletnum = cargospace.getPalletnum();				// �ɷ�������
			layernum = cargospace.getLayernum();				// ����
			environment = cargospace.getEnvironment();			// ����
			
			strId = cargoSpaceId + "|"+ cargoSpaceName + "|";
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=strId%>');">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=strId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceName == null ? "" : cargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
     <td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
     <td class="TD_LIST" align="center"><%=csstatus == null ? "" : csstatus%></td>
     <td class="TD_LIST" align="center"><%=usagetype == null ? "" : usagetype%></td>
     <td class="TD_LIST" align="center"><%=storagetype == null ? "" : storagetype%></td>
     <td class="TD_LIST" align="center"><%=puttype == null ? "" : puttype%></td>
     <td class="TD_LIST" align="center"><%=picktype == null ? "" : picktype%></td>
     <td class="TD_LIST" align="center"><%=length%></td>
     <td class="TD_LIST" align="center"><%=width%></td>
     <td class="TD_LIST" align="center"><%=height%></td>
     <td class="TD_LIST" align="center"><%=volume%></td>
     <td class="TD_LIST" align="center"><%=loadweight%></td>
     <td class="TD_LIST" align="center"><%=palletnum%></td>
     <td class="TD_LIST" align="center"><%=layernum%></td>
     <td class="TD_LIST" align="center"><%=environment == null ? "" : environment%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </table>

 <!-- ======== ��ҳ��ǩ ======== -->

	<div style="height:25px">
	  <%@ include file="commpage.jsp"%>
    </div>

</body>
</html>
