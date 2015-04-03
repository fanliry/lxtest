<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
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
			
			var key = check_ids[i].value.split("|");
			parent.document.getElementById("checknum").value = key[1];
		}
	}
</SCRIPT>
</head>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px">
     	<div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox"></div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">����ʱ��</div></td>
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
	
		InventoryCheckTask checktask = null;
		String taskid;			//ID
		String status;			//״̬
		String cargospace;		//��λ
		String wharea;			//����
		String product;			//��Ʒ
		String lotnumber;	    //����
		String tyaycode;		//��������
    	String productcode;     //��Ʒ����
		String createtime;		//����ʱ��
		double num;				//�������
		
		String param = "";		//ID+�������
		
		for(int i = 0; i < ls.size(); i++){
			checktask = (InventoryCheckTask)ls.get(i);
			taskid = checktask.getTaskid();				//ID
			status = checktask.getStatusName();			//״̬
			cargospace = checktask.getCargoSpaceName();	//��λ
			wharea = checktask.getWhAreaName();			//����
			product = checktask.getProductName();		//��Ʒ
			lotnumber = checktask.getLotinfo();	    //����
			tyaycode = checktask.getTraycode();			//��������
    		productcode = checktask.getProductcode(); 	//��Ʒ����
			createtime = checktask.getCreatetime();		//����ʱ��
			num = checktask.getNum();					//�������
			
			param = taskid + "|" + (int)num + "|end";
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center" width="50px">
     	<input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=param%>">&nbsp;<%=i+1%></td>
     <td class="TD_LIST" align="center"><%=taskid==null?"":taskid%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=wharea==null?"":wharea%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=product==null?"":product%></td>
     <td class="TD_LIST" align="center"><%=productcode==null?"":productcode%></td>
	 <td class="TD_LIST" align="center"><%=tyaycode==null?"":tyaycode%></td>
	 <td class="TD_LIST" align="center"><%=lotnumber==null?"":lotnumber%></td>  
     <td class="TD_LIST" align="center"><%=(int)num%></td>
     <td class="TD_LIST2" align="center"><%=createtime==null?"":createtime%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="11" valign="top" class="TD_last_LIST">
     	<div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
</body>
</html>