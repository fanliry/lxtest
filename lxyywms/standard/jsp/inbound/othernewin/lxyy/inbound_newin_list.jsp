<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#33FF33";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	var sqid = "";
	var i=0; //��ѡ����ѡ�и���
	function msgtes(sqidx,obj){
	    if(i==0){
	       sqid = "";
		}
	    if(obj.checked){
	       if(sqid!=""){
		       if(sqid==sqidx){
		         i++;
		       }else{
		          obj.checked = false;
		          alert("��ѡ�еİ󶨼�¼����Ϊͬһ�����");
			   }
	       }else{
	           i++;
	           sqid = sqidx;
	       }
	     }else{
	           i--;
	     }
	 }
	
	function OnLoad(){
	    parent.RemoveLoading();
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){		
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}
</script>

</head>

<body onload="javascript:OnLoad();">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%" id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">���뵥��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����״̬</div></td>
   </tr>
<%
	
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		BindingRecord detail = null;	//������뵥��ϸ 
		String instockid;		//���뵥��
        
      	String productid;		//
      	String productCode; 	//��Ʒ����
	  	String productName;		//Ʒ��
      	String printdate;		//��������
      	String lotinfo;			//����
      	String unitName;		//��λ
      	double num;	            //����
		String traycode;        //��������
		String bandmanname;     //����
      	String statusname;      //״̬��1�������ɵ��ݣ�2��δ���ɵ��ݣ�
      	
		for(int i = 0; i < ls.size(); i++)
		{
			detail = (BindingRecord)ls.get(i);
			
			instockid = detail.getInstockid();
	      	productid = detail.getProductid();
	      	
	      	productCode	= detail.getProductCode();		
	      	productName = detail.getProductName();   	
         	printdate = detail.getPrintdate(); 		 
         	lotinfo = detail.getLotinfo();       		
         	unitName = detail.getM_strUnitName();       		
	      	num = detail.getNum();		
		  	traycode = detail.getTraycode();		
		  	bandmanname = detail.getBandmanname();	
		  	statusname = detail.getStatusname();
		  	
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" onclick="msgtes('<%=instockid%>',this)" value="<%=detail.getBandrecordid()%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=instockid==null?"":instockid%></td>
		     
		     
		      <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
		     <td class="TD_LIST" align="center"><%=productName==null?"":productName%></td>
		     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
		     
		     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=unitName==null?"":unitName%></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td>
		     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
		     <td class="TD_LIST" align="center"><%=bandmanname==null?"":bandmanname%></td>	
		     <td class="TD_LIST" align="center"><%=statusname==null?"":statusname%></td>	 	     
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
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
