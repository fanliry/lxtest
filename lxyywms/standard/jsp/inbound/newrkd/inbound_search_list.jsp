<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
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
		//parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}
			else{
				alert(back_msg);
			}
		}
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
// 		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" align="center" width="50px"><div class="list_title">ѡ��</div></td>
     <td class="TD_LIST_TITLE" width="50px"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ֿ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�߼�����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">״̬</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">δ�������</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">δ��������</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	double num = 0;
	if(ls != null && ls.size()>0){
		
		Object[] obj = null;
     	
     	String jobtype = "";    //��ҵ���� ����������ҵ����Ϊ ������͵�hlֵ��
     	String jobtypeid = "";
     	String productid = "";//��Ʒ����
     	String m_strProductName = "";	 //Ʒ��
		String lotinfo = "";	         //��������
		String printdate = "";	     //��������
		String m_strUnitName = "";	//��λ
		String warehouseName = "";  	//�ֿ�
		String whArea = "";
		String Virtualwhid = "";//�߼�����
		String VirtualwhName = "";
		
		String productStatusName = "";
		String productStatus = "";
		
		String warehouseid = "";
		String whAreaid = "";
     	
		double jobnum = 0;			    //������
		double finishnum = 0;
		double nofinishnum = 0;
		double noinvoicenum = 0;
     	
     	String key="";
     	String alt="";
     	
     	String oldproductid = null;  //��һ��Ʒ��
     	String oldjobtypeid = null;  //��һ����ҵ����
     	
     	double unitJobum = 0;         //С������
     	double unitFinishnum = 0;     //С�������
     	double unitNofinishnum = 0;   //С��δ�����
     	double unitNoinvoicenum = 0;    //С��δ������
		
     	double addJobum = 0;         //�ϼ�����
     	double addFinishnum = 0;     //�ϼ������
     	double addNofinishnum = 0;   //�ϼ�δ�����
     	double addNoinvoicenum = 0;  //�ϼ�δ������
     	
     	double allJobum = 0;         //�ܼ�����
     	double allFinishnum = 0;     //�ܼ������
     	double allNofinishnum = 0;   //�ܼ�δ�����
     	double allNoinvoicenum = 0;    //�ܼ�δ������
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			jobtype = obj[1] == null ? "" : obj[1].toString();    //��ҵ���� ����������ҵ����Ϊ ������͵�hlֵ��
	     	jobtypeid = obj[0] == null ? "" : obj[0].toString();
	     	productid = obj[2] == null ? "" : obj[2].toString();//��Ʒ����
	     	m_strProductName = obj[3] == null ? "" : obj[3].toString();	 //Ʒ��
			printdate = obj[4] == null ? "" : obj[4].toString();	     //��������
			m_strUnitName = obj[6] == null ? "" : obj[6].toString();	//��λ
			warehouseName = obj[8] == null ? "" : obj[8].toString();  	//�ֿ�
			whArea = obj[10] == null ? "" : obj[10].toString();
			
			warehouseid = obj[7] == null ? "" : obj[7].toString();  	//�ֿ�id
			whAreaid = obj[9] == null ? "" : obj[9].toString();
	     	
			jobnum = Double.parseDouble(obj[11] == null ? "0" : obj[11].toString());			    //������
			finishnum = Double.parseDouble(obj[12] == null ? "0" : obj[12].toString());
			nofinishnum = jobnum - finishnum;
			noinvoicenum = Double.parseDouble(obj[13] == null ? "0" : obj[13].toString());
			lotinfo = obj[14] == null ? "" : obj[14].toString(); //�������
			Virtualwhid = obj[15] == null ? "" : obj[15].toString();  //�߼�����
			VirtualwhName = obj[16] == null ? "" : obj[16].toString();  //�߼�������
			productStatus = obj[17] == null ? "" : obj[17].toString();  
			productStatusName = obj[18] == null ? "" : obj[18].toString();  
			key = warehouseid + "|" + whAreaid + "|" + Virtualwhid+"|"+jobtypeid + "|" + productid + "|" + printdate+ "|" + lotinfo+ "|";
			alt = jobtypeid;
			
			if(oldproductid == null)
			{
				oldproductid = productid;
			}else if(!oldproductid.equals(productid))
			{
%>
			   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">С��</td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td> 
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNoinvoicenum%></td>
			   </tr>
<%
				
		     	unitJobum = 0;         //С������
		     	unitFinishnum = 0;     //С�������
		     	unitNofinishnum = 0;   //С��δ�����
		     	unitNoinvoicenum = 0;    //С��δ������
		     	
			}
			
			if(oldjobtypeid == null)
			{
				oldjobtypeid = jobtypeid;
			}else if(!oldproductid.equals(productid) && oldjobtypeid.equals(jobtypeid)){
			  	oldproductid = productid;
			}else if(!oldproductid.equals(productid) && !oldjobtypeid.equals(jobtypeid))
			{
%>
			
			   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">�ϼ�</td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td> 
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNoinvoicenum%></td>
			   </tr>
<%
		     	addJobum = 0;         //�ϼ�����
		     	addFinishnum = 0;     //�ϼ������
		     	addNofinishnum = 0;   //�ϼ�δ�����
		     	addNoinvoicenum = 0;  //�ϼ�δ������
		     	oldjobtypeid = jobtypeid;
		     	oldproductid = productid;
			}
			else if(oldproductid.equals(productid) && !oldjobtypeid.equals(jobtypeid))
			{
%>
				<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">С��</td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td> 
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=unitNoinvoicenum%></td>
			   </tr>
			   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
			   	 <td class="TD_LIST1" align="center" width="50px"></td>
			     <td class="TD_LIST" align="center" width="50px" style="color: red;">�ϼ�</td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td> 
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center"></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addJobum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addFinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNofinishnum%></td>
			     <td class="TD_LIST" align="center" style="color: red;"><%=addNoinvoicenum%></td>
			   </tr>
<%
		     	unitJobum = 0;         //С������
		     	unitFinishnum = 0;     //С�������
		     	unitNofinishnum = 0;   //С��δ�����
		     	unitNoinvoicenum = 0;    //С��δ������
		     	oldproductid = productid;
		     	
		     	addJobum = 0;         //�ϼ�����
		     	addFinishnum = 0;     //�ϼ������
		     	addNofinishnum = 0;   //�ϼ�δ�����
		     	addNoinvoicenum = 0;  //�ϼ�δ������
		     	oldjobtypeid = jobtypeid;
			}
			
			unitJobum += jobnum;         //С������
	     	unitFinishnum += finishnum;     //С�������
	     	unitNofinishnum += nofinishnum;   //С��δ�����
	     	unitNoinvoicenum += noinvoicenum;    //С��δ������
	     	
	     	addJobum += jobnum;         //�ϼ�����
	     	addFinishnum += finishnum;     //�ϼ������
	     	addNofinishnum += nofinishnum;   //�ϼ�δ�����
	     	addNoinvoicenum += noinvoicenum;  //�ϼ�δ������
	     	
	     	allJobum += jobnum;         //�ܼ�����
	     	allFinishnum += finishnum;     //�ܼ������
	     	allNofinishnum += nofinishnum;   //�ܼ�δ�����
	     	allNoinvoicenum += noinvoicenum;    //�ܼ�δ������
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
   	 <td class="TD_LIST1" align="center" width="50px"><input type="checkbox" name="check_id" alt="<%=alt %>" value="<%=key %>" class="input_radio" onClick=""></td>
     <td class="TD_LIST" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseName==null?"":warehouseName%></td>
     <td class="TD_LIST" align="center"><%=whArea==null?"":whArea%></td>
     <td class="TD_LIST" align="center"><%=VirtualwhName==null?"":VirtualwhName%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=productid==null?"":productid%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td> 
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td>
     <td class="TD_LIST" align="center"><%=productStatusName==null?"":productStatusName%></td>
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=finishnum%></td>
     <td class="TD_LIST" align="center"><%=nofinishnum%></td>
     <td class="TD_LIST" align="center"><%=noinvoicenum%></td>
   </tr>
<%
		}
%>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
	   	 <td class="TD_LIST1" align="center" width="50px"></td>
	     <td class="TD_LIST" align="center" width="50px" style="color: red;">С��</td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td> 
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitJobum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitFinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitNofinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=unitNoinvoicenum%></td>
	   </tr>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
	   	 <td class="TD_LIST1" align="center" width="50px"></td>
	     <td class="TD_LIST" align="center" width="50px" style="color: red;">�ϼ�</td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td> 
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addJobum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addFinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addNofinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=addNoinvoicenum%></td>
	   </tr>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
	   	 <td class="TD_LIST1" align="center" width="50px"></td>
	     <td class="TD_LIST" align="center" width="50px" style="color: red;">�ܼ�</td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td> 
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"></td>
	     <td class="TD_LIST" align="center"><br></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allJobum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allFinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allNofinishnum%></td>
	     <td class="TD_LIST" align="center" style="color: red;"><%=allNoinvoicenum%></td>
	   </tr>
<%
		num = allNofinishnum;
	}
%>  
   <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'>
<input type="hidden" name="keys" value="">
<input type="hidden" name="condition" value="">
</FORM>
<input name="num" type="hidden" value="<%=num%>">
</body>
</html>