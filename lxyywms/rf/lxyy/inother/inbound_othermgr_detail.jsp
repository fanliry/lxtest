<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail" %>
<%@ page import="java.lang.reflect.Field" %>

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
			//check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			//check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}

	function OnLoad(){
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
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
	 <td class="TD_LIST_TITLE" width="75px"><div class="list_title">��Ʒ</div></td>
	 <td class="TD_LIST_TITLE" width="70px"><div class="list_title">�������</div></td>
	 <td class="TD_LIST_TITLE" width="50px"><div class="list_title">����</div></td>
   </tr>
   </thead>  
   <tbody>
<%
	List<InboundPoDetail> ls = (List<InboundPoDetail>)request.getAttribute("exResultList");
		if(ls != null && ls.size() > 0) {
			InboundPoDetail poDetail = null;
			String podetailid="";//PO�к�
			String poid="";//POid
			String polinestatus="";//PO��״̬ 0-����
			String productid="";// ��ƷID
			String productName="";// ��Ʒ��
			String packid="";//��װID
			
			String eunit="";// ��λ
			String productCode = "";//ERP���ϱ���
			double ponum=0.0;//������
			double useponum=0.0;//�Ѿ�ʹ������
			double nouseponum=0.0;//δʹ������
			double price = 0.0;//����
			String strvalue="";
			String eunitName = "";
            String spec="";
            
            String lotinfo = "";
            String lotinfo2 = "";
            String checkid = "";
            
            String StrValue = "";
            
			for(int i=0;i<ls.size();i++){
				poDetail = ls.get(i);
				poid = poDetail.getPoid();
				podetailid = poDetail.getPodetailid();
				polinestatus = poDetail.getPolinestatusName();
				productid = poDetail.getProductid();
				productName = poDetail.getM_strProductName();
				eunit = poDetail.getEunit();
				ponum = poDetail.getPonum();
				price = poDetail.getPrice();
				useponum = poDetail.getUseponum();
				eunitName = poDetail.getM_strUnitName();
				productCode = poDetail.getProductCode();
				spec=poDetail.getM_spec();
				lotinfo = poDetail.getLotinfo();
				lotinfo2 = poDetail.getLotinfo2();
				checkid = poDetail.getCheckid();
				
				StrValue = poid +"|"+ podetailid +"|"+ productid +"|"+ productName +"|"+ productCode +"|"+ eunitName +"|"+ lotinfo +"|"+ lotinfo2 +"|"+ ponum;
				
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''"  onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=StrValue %>');" >
     <td class="TD_LIST1" align="center">
     	<input onclick="setSel(<%=i%>)" type="radio" name="check_id" class="input_checkbox" value=""><%=i+1%></td>
	 <td class="TD_LIST" align="center"><%=productName == null ? "" : productName %></td>
	 <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo %></td>
	 <td class="TD_LIST" align="center"><%=ponum %></td>
   </tr>
<%
		}
	}
%>  

	<tr>
      <td height="100%" colspan="4" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
   </tbody> 
 </table>
 </div>
</body>
</html>