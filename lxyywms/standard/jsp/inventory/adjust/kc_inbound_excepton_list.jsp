<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryNeedcheck" %>
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
		parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�쳣����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�Ƿ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������־</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	
	if(ls != null && ls.size()>0){
		
		InventoryNeedcheck obj = null;
     	
     	String jobid;        	//��ҵ��
     	String cargoSpaceId;     	//��λ
     	String traycode;     	//��������
     	String createtime;    		//����ʱ��
     	String handleflag;  	//�Ƿ���
     	String handletime; 		//����ʱ��
     	String handlemanname;     //������
     	String handlecontent;     //������־
     	
     	String id;
     	
		for(int i = 0; i < ls.size(); i++){
			
			obj = (InventoryNeedcheck)ls.get(i);
			jobid = obj.getJobid();
			cargoSpaceId = obj.getCargoSpaceId();
			traycode = obj.getTraycode();
			createtime = obj.getCreatetime();
			handleflag = obj.getHandleflag();
			handletime = obj.getHandletime();
			handlemanname = obj.getHandlemanname();
			handlecontent = obj.getHandlecontent();
			id = obj.getNeedcheckid()+"|"+cargoSpaceId;
			
			
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
      <td class="TD_LIST1" align="center" width="50px"><input type="checkbox" name="check_id" value="<%=id%>" class="input_checkbox"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=obj.getNeedcheckid()==null?"":obj.getNeedcheckid()%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceId==null?"":cargoSpaceId%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td>
     <td class="TD_LIST" align="center"><%=handleflag==null?"":handleflag%></td>
     <td class="TD_LIST" align="center"><%=handletime==null?"":handletime%></td>
     <td class="TD_LIST" align="center"><%=handlemanname==null?"":handlemanname%></td>
     <td class="TD_LIST" align="center"><%=handlecontent==null?"":handlecontent%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="9" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>