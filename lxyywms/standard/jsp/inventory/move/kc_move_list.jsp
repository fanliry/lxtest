<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
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
		parent.RemoveLoading();
		parent.page.location.reload();
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="30"><div class="list_title">ѡ��</div></td>
     
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���״̬</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ë��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��澻��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����ë��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���Ά��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���ʱ��</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">��ⷽʽ</div></td>
   </tr>
<%
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
	   // Object[] ob = null;
		InventoryStorage storage = null;
		//BaseCargospace cargospace = null;
		String whArea;				//����
		String status;//���״̬
 		String cargoSpace;			//��λ
 		String productcode;//��Ʒ����
 	 	String product;				//��Ʒ
 	 	String owner;				//����
 	 	String traycode;        	//��������
		String punit;				//��λ
		double pnum;            	//�������
		double pweight;         	//���ë��
     	double pnetweight;      	//��澻��
     	double pvolume;         	//������
		double holdnum;         	//��������
		double holdweight;      	//����ë��
		double holdnetweight;      	//���Ά��
		double holdvolume;         	//�������
		String indate;             	//���ʱ��
		String intype;             	//��ⷽʽ 1.���� 2.�ѻ�
		
		String lotatt;  			//��������
		
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{ 	 	  
 	 	    //ob = (Object[])ls.get(i);
 	 	    storage = (InventoryStorage)ls.get(i);
 	 	   // BaseCargospace base=(BaseCargospace)ob[1];
  	 		whArea = storage.getWhAreaName();			//����
  	 		cargoSpace = storage.getCargoSpaceName();		//��λ
  	 		productcode = storage.getProductcode();//��Ʒ����
  	 		product = storage.getProductName();		//��Ʒ
  	 		owner = storage.getOwnerName();			//����
  	 		traycode = storage.getTraycode();		//��������
  	 		punit = storage.getPunitname();			//��λ
  	 		status = storage.getStatusName();//���״̬
  	 		pnum = storage.getPnum();			//�������
  	 		pweight = storage.getPweight();        //���ë��
  	 		pnetweight = storage.getPnetweight();     //��澻��
  	 		pvolume = storage.getPvolume();        //������
  	 		holdnum = storage.getHoldnum();		//��������
  	 		holdweight = storage.getHoldweight();     //����ë��
			holdnetweight = storage.getHoldnetweight();  //���Ά��
			holdvolume = storage.getHoldvolume();     //�������
			
			indate = storage.getIndate();       //���ʱ��
  	 		intype = storage.getIntype();		//��ⷽʽ 1.���� 2.�ѻ�
  	 		if(intype=="1"){
  	 		    intype="����";
  	 		}else{
  	 		     intype="�ѻ�";
  	 		}
  	 		
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=storage.getInventoryid()%>"/></td>
     
     <td class="TD_LIST" align="center"><%=whArea%></td>
      <td class="TD_LIST" align="center"><%=status%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=productcode%></td>
     <td class="TD_LIST" align="center"><%=product%></td>
     <td class="TD_LIST" align="center"><%=traycode%></td>
     <td class="TD_LIST" align="center"><%=punit%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
     <td class="TD_LIST" align="center"><%=pweight%></td>
     <td class="TD_LIST" align="center"><%=pnetweight%></td>
     <td class="TD_LIST" align="center"><%=pvolume%></td>
     <td class="TD_LIST" align="center"><%=holdnum%></td>
     <td class="TD_LIST" align="center"><%=holdweight%></td>
     <td class="TD_LIST" align="center"><%=holdnetweight%></td>
     <td class="TD_LIST" align="center"><%=holdvolume%></td>
     <td class="TD_LIST" align="center"><%=indate%></td>
     <td class="TD_LIST2" align="center"><%=intype%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
