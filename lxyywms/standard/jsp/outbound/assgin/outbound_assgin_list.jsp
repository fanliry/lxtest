<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
	OutboundInvoiceDetail outInvoiceDetail = (OutboundInvoiceDetail)request.getAttribute("outInvoiceDetail");
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
<title>�����ϸ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>

	function Change(obj, inum)
	{
	   
    	var pinum = parent.inum.innerHTML;//����
    	
    	var oldobj1 = obj.parentNode.parentNode.cells.item(10).getElementsByTagName('INPUT')[0];
    	
    	var oldobj2 = obj.parentNode.parentNode.cells.item(10).getElementsByTagName('INPUT')[1];
    	//var selectnum = 0;
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
			oldobj1.disabled="disabled";
    		//parent.inum.innerHTML = parseInt(selectnum) + parseInt(oldobj1.value)+ parseInt(pinum);//����
    		parent.inum.innerHTML =  parseFloat(pinum) + parseFloat(oldobj1.value);//����
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
			oldobj1.disabled="";
    		//parent.inum.innerHTML = parseInt(selectnum) - parseInt(oldobj1.value)+ parseInt(pinum);//����
    		parent.inum.innerHTML =  parseFloat(pinum) - parseFloat(oldobj1.value);//����
    		
		}
	}
	function Change1(obj, inum)
	{
    	var pinum = parent.inum.innerHTML;//����
    	var o = obj.parentNode.parentNode.cells.item(0).getElementsByTagName('INPUT')[0];
    	var oldobj = obj.parentNode.getElementsByTagName('INPUT')[1];
		if(o.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
    		parent.inum.innerHTML = parseFloat(pinum)-(parseFloat(oldobj.value)-parseFloat(obj.value));//����
    		parent.synum.innerHTML = (parent.a.innerHTML-parent.b.innerHTML) - (parseFloat(pinum)-(parseFloat(oldobj.value)-parseFloat(obj.value)));//δ��������
		}
		oldobj.value = obj.value;
	}

	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		var fpnums = document.getElementsByName("fpnum");
		
		var temp = 0;
		var pinum = parent.inum.innerHTML;//����
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			 
			//�ı䱳��ɫ
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
				fpnums[i].disabled="disabled";
				temp = parseFloat(temp) + parseFloat(fpnums[i].value);
				parent.inum.innerHTML = parseFloat(temp);
		    	
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
				fpnums[i].disabled="";
				parent.inum.innerHTML = "0";
				
			}
		}
	}
	
	function OnLoad(){
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){	
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
</script>

</head>

<body  onload="javascript:OnLoad();" >
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
<table width="100%" id="tbone" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�
     </div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title" width="100">����</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td> 
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ״̬</div></td>
   </tr>
<%
	/* ss */
	
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		Object[] obj = null;  //���ͳ����Ϣ
		
		InventoryStorage sto = null;
		
		String whAreaId;       //����ID
		String whAreaName;	   //��������
		
		String cargoSpaceId;   //��λID
		String cargoSpaceName; //��λ����
		String lotInfo; 	//����
		String traycode;       //��������
		
      	String productid;		//Ʒ��ID
      	String productname;		//Ʒ��
      	String productCode;	//��Ʒ����
      	String printdate;//��������
      	String eunit;			//��λID
      	String eunitname;		//��λ����
      	String indate;//���ʱ��
      	double pnum;				//�������  
      	double num;
      	double unassignnum;					//δ��������   =  �������� - �ѷ�������
		
      	
      	double invoicenum;          //��������
      	double preassignnum;        //Ԥ������
      	double assignnum;           //��������
      	double picknum;             //������� ����ҵ����ʱ ���˳ɹ� ���� ͬʱ�޸���������
      	double sendnum;             //��������

      	
	  	double weight;			//���ʣ������
      	double netweight;		//���ʣ�ྻ��
      	double volume;			//���ʣ�����
      	
      	String productStatus;//��Ʒ״̬
      	String productStatusName;//��Ʒ״̬��
      	
      	String ownerid;			//����ID
      	String ownername;       //����
      	String meg; 
      	String strKey = "";	
      	String inventoryId;
		for(int i = 0; i < ls.size(); i++)
		{
			 obj = (Object[])ls.get(i);
			 sto = (InventoryStorage)obj[0];
			 
			 whAreaId = sto.getWhAreaId();
			 whAreaName = sto.getWhAreaName();
			 cargoSpaceId = sto.getCargoSpaceId();//��λID
			 cargoSpaceName = sto.getCargoSpaceName();//��λ����
			 traycode = sto.getTraycode();//��������
			 productid = sto.getProductid();//Ʒ��ID
			 productname = sto.getProductName();//Ʒ��
			 eunit = sto.getPunit();//��λID
			 eunitname = sto.getPunitname();//��λ����

			 pnum = sto.getPnum();//�������  
			 num = sto.getPnum() - sto.getAssignnum(); //���ʣ���������ɷ���������  = ���̲�Ʒ���� - �ѷ�������
			

			
			 invoicenum = outInvoiceDetail.getInvoicenum();          //��������
			 preassignnum = outInvoiceDetail.getPreassignnum();        //Ԥ������
			 assignnum = outInvoiceDetail.getAssignnum();           //��������
			 picknum = outInvoiceDetail.getPicknum();             //������� ����ҵ����ʱ ���˳ɹ� ���� ͬʱ�޸���������
			 sendnum = outInvoiceDetail.getSendnum();             //��������
			 
		     unassignnum = invoicenum - assignnum;		  //δ��������   =  �������� - �ѷ�������
		    
			 netweight = sto.getPweight();//�������
			 volume = sto.getPvolume();//������  
			 inventoryId = sto.getInventoryid();//���ID
			 productCode = sto.getProductcode();//��Ʒ����
			 printdate = sto.getProductdate();//��������
			 ownerid = sto.getOwnerId();//����ID
			 ownername = sto.getOwnerName(); //����
			 lotInfo = sto.getLotinfo();//����
			 indate = sto.getIndate();//���ʱ��
			 productStatus =sto.getProductstatus();//��Ʒ״̬
			 productStatusName=sto.getProductStatusName();//��Ʒ״̬��
      	 	 strKey =  productid + "|" + ownerid + "|";
      	 		meg = cargoSpaceId+":"+traycode+"["+inventoryId+","+num;
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''" >
		     <td class="TD_LIST" align="center"><input onClick="Change(this,'<%=num%>')" type="checkbox" name="check_id" class="input_checkbox" value="<%=meg%>"><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=whAreaName%></td>
		     <td class="TD_LIST" align="center"><%=cargoSpaceName%></td>
		     <td class="TD_LIST" align="center"><%=traycode%></td>
		     <td class="TD_LIST" align="center"><%=lotInfo%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=productname%></td>
		     <td class="TD_LIST" align="center"><%=printdate%></td>
		     <td class="TD_LIST" align="center"><%=eunitname%></td>
		     <td class="TD_LIST" align="center" style="width:100px;"><input type='text' name='num' style="width:100px;text-align:center;" value='<%=nbf.format(num)%>' disabled></td>
			 
			 <td class="TD_LIST" align="center" style="width:100px;">
			 <input onchange="Change1(this,'<%=num%>')" type='text' name='fpnum' style="width:100px;text-align:center;" value='<%=num%>'>
			 <input type='hidden' name='oldnum'  value='<%=unassignnum%>'>
			 </td>
		     <td class="TD_LIST" align="center"><%=productStatusName==null?"":productStatusName%></td>	     		     
		   </tr>	       	 
<%	      	 
		}
	}
%>

   <tr>
     <td height="100%" colspan="8" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
   </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
