<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	HashMap<String, List> hsLot = null;		//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  		//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newin");	//�½��ջ���->��ʾ�ջ�����ϸ	
		}
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	function OnLoad(){
	
		parent.RemoveLoading();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("���ɵ��ݳɹ���");
			}
			else{
				alert(back_msg);
			}
			
			parent.UnLockButton();
		}
	}
	
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
		}
		
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width: 100%; height: 100%; overflow-x:scroll;overflow-y:auto;position:absolute;">
 <table width="120%" height="100%" border="0" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr style="position:relative;top:expression(this.offsetParent.scrollTop);">
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">ѡ��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��ҵ����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">��Դ���ݺ�</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%> 
     <td class="TD_LIST_TITLE"><div class="list_title">��ë��</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�ܾ���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">δ�������</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">δ��������</div></td>
    </tr> 
<%
	int iNum = 0;
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();     
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		Object[] obj = null;
		
		String productid = ""; 			//Ʒ��
		String productName = ""; 		//Ʒ��
		String jobtype = "";			//��ҵ����
		String jobtypeName = "";		//��ҵ����
		String ownerid = ""; 			//����
		String ownerName = ""; 			//����
		String sinvoiceid = ""; 		//��Դ���ݺ�
		String packid = "";      		//��װID
 		String punit = "";          	//��λ
 		String lotid = "";        		//��������ID
 		String invoicetype = "";		//��ҵ��Դ
 	
		Double iCountNum = 0.0;	 		//������
		Double iFinishNum = 0.0;		//�������
		Double iNoFinishNum = 0.0;		//δ�������
		Double iNoVoiceNum = 0.0;		//δ��������
		
		Double fGrossWeight = 0.0;		//ë��
		Double fNetWeight = 0.0;		//����
		Double fvolume = 0.0;			//���
		
		String strKey = "";	
		
		//С�ƺϼ���
		String tempProduct = null;		//��Ʒ
		String tempJobtype = null;		//��ҵ����	 
		
		int k=0;
		int m1=0,m2=0,m3=0,m4=0,n1=0,n2=0,n3=0,n4=0,a1=0,a2=0,a3=0,a4=0;
		double m5 = 0, m6 = 0,n5 = 0, n6 = 0,a5 = 0, a6 = 0;
		
		for(int i=0; i<ls.size(); i++)
		{
			obj = (Object[])ls.get(i);
			productid = obj[0] == null ? "" : obj[0].toString();	//Ʒ��
			productName = obj[1] == null ? "" : obj[1].toString();	//Ʒ��
			jobtype = obj[2] == null ? "" : obj[2].toString(); 		//��ҵ����
			jobtypeName = obj[3] == null ? "" : obj[3].toString(); 	//��ҵ����
			ownerid = obj[4] == null ? "" : obj[4].toString();		//����
			ownerName = obj[5] == null ? "" : obj[5].toString();	//����
			sinvoiceid = obj[6] == null ? "" : obj[6].toString();	//��Դ���ݺ�
			packid = obj[19] == null ? "" : obj[19].toString();     //��װID
 			punit = obj[20] == null ? "" : obj[20].toString();      //��λ
 			lotid = obj[21] == null ? "" : obj[21].toString();      //��������ID
			invoicetype = obj[22] == null ? "" : obj[22].toString();//��ҵ��Դ
			
			fvolume = (Double)obj[7];		// ���
			fGrossWeight = (Double)obj[8];	// ë��
		 	fNetWeight = (Double)obj[9];	// ����
			iCountNum       = (Double)obj[10];	//������
			iFinishNum      = (Double)obj[14];	//�������
			iNoFinishNum    = iCountNum-iFinishNum;	//δ�������
			iNoVoiceNum     = (Double)obj[18];	//δ��������	
			 
			strKey =  productid + "|" + jobtype + "|" + ownerid + "|" + sinvoiceid + "|" + packid + "|" + punit + "|" + lotid + "|" + invoicetype + "|";
			
			//��ͬ��ƷС��,��ͬ��ҵ���ͺϼ�
			if(tempProduct == null)
			{
				tempProduct = productName;
			}else if(!tempProduct.equals(productName))
			{
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">С��</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%>   
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=m4%></td>
   </tr>
<%
				m1 = m2 = m3 = m4 = 0;
				m5 = m6 = 0;
				tempProduct = productName;
	   		}
	   		if(tempJobtype == null)
	   		{
	   			tempJobtype = jobtypeName;
	   		}
	   		else if(tempProduct.equals(productName) && !tempJobtype.equals(jobtypeName))
	   		{
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">�ϼ�</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%>  
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=n4%></td>
   </tr>
<%
	   			n1 = n2 = n3 = n4 = 0;
	   			n5 = n6 = 0;
	   			tempJobtype = jobtypeName;
	   			
	   		}else if(!tempProduct.equals(productName) && !tempJobtype.equals(jobtypeName))
	   		{
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">С��</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%> 
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=m4%></td>
   </tr>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">�ϼ�</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%> 
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=n4%></td>
   </tr>
<%
	   			m1 = m2 = m3 = m4 = 0;
	   			n1 = n2 = n3 = n4 = 0;
	   			m5 = m6 = 0;
	   			n5 = n6 = 0;
	   			
				tempProduct = productName;
				tempJobtype = jobtypeName;
	   		}
	   		m1 += iCountNum.intValue();
			m2 += iFinishNum.intValue();
			m3 += iCountNum.intValue()-iFinishNum.intValue();
			m4 += iNoVoiceNum.intValue();
			m5 += fGrossWeight;
			m6 += fNetWeight;
			
			
			n1 += iCountNum.intValue();
			n2 += iFinishNum.intValue();
			n3 += iCountNum.intValue()-iFinishNum.intValue();
			n4 += iNoVoiceNum.intValue();
			n5 += fGrossWeight;
			n6 += fNetWeight;
			
			a1 += iCountNum.intValue();
			a2 += iFinishNum.intValue();
			a3 += iCountNum.intValue()-iFinishNum.intValue();
			a4 += iNoVoiceNum.intValue();
			a5 += fGrossWeight;
			a6 += fNetWeight;
%>

   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.searchDetail()">
   	 <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" class="input_checkbox" value="" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobtypeName%></td>
	 <td class="TD_LIST" align="center"><%=ownerName%></td>
	 <td class="TD_LIST" align="center"><%=sinvoiceid%></td>
     <td class="TD_LIST" align="center"><%=productName%></td>
<%
	if(lsLot != null){
		String lotatt =""; 
		for(k = 0; k < iLine; k++){
			lotatt = obj[23+k] == null ? "" : obj[23+k].toString();		//��������
			strKey += lotatt + "|";
%>
	 		 <td class="TD_LIST" align="center"><%=lotatt%></td>
<%
		}
		strKey += "end|";
	}
%>
     <td class="TD_LIST" align="center"><%=nbf.format(fGrossWeight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(fNetWeight)%></td>
     <td class="TD_LIST" align="center"><%=iCountNum.intValue()%></td>
     <td class="TD_LIST" align="center"><%=iFinishNum.intValue()%></td>
     <td class="TD_LIST" align="center"><%=iNoFinishNum.intValue()%></td>
     <td class="TD_LIST2" align="center"><%=iNoVoiceNum.intValue()%><input type="hidden" name="detail_key" value="<%=strKey%>"></td>
   </tr>		
<%
		}
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">С��</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(m6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=m4%></td>
   </tr>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">�ϼ�</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST"></td>
<%
		}
	}
%> 
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n5)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=nbf.format(n6)%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=n3%></td>
     <td class="TD_LIST2" align="center" style="color: red;"><%=n4%></td>
   </tr>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" style="color: red;">�ܼ�</td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
     <td class="TD_LIST"></td>
<%
	if(lsLot != null){
		for(k = 0; k < iLine; k++){
%>
	 		 <td class="TD_LIST" align="center"></td>
<%
		}
	}
%>
     <td align="center" class="TD_LIST" style="color: red;"><%=nbf.format(a5)%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=nbf.format(a6)%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=a1%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=a2%></td>
     <td align="center" class="TD_LIST" style="color: red;"><%=a3%></td>
     <td align="center" class="TD_LIST2" style="color: red;"><%=a4%></td>
   </tr>
<%
		iNum = a3;
	}
%>
   <tr>
     <td height="100%" colspan="<%=16 + iLine%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
 <input name="num" type="hidden" value="<%=iNum%>">
 
  <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
