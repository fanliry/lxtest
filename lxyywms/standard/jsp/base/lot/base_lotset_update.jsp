<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%
	BaseLotSet lotset1 = (BaseLotSet)request.getAttribute("lotset1");
	BaseLotSet lotset2 = (BaseLotSet)request.getAttribute("lotset2");
	BaseLotSet lotset3 = (BaseLotSet)request.getAttribute("lotset3");
	BaseLotSet lotset4 = (BaseLotSet)request.getAttribute("lotset4");
	BaseLotSet lotset5 = (BaseLotSet)request.getAttribute("lotset5");
	BaseLotSet lotset6 = (BaseLotSet)request.getAttribute("lotset6");
	BaseLotSet lotset7 = (BaseLotSet)request.getAttribute("lotset7");
	BaseLotSet lotset8 = (BaseLotSet)request.getAttribute("lotset8");
	BaseLotSet lotset9 = (BaseLotSet)request.getAttribute("lotset9");
	BaseLotSet lotset10 = (BaseLotSet)request.getAttribute("lotset10");
	BaseLotSet lotset11 = (BaseLotSet)request.getAttribute("lotset11");
	BaseLotSet lotset12 = (BaseLotSet)request.getAttribute("lotset12");
	
	String lottype = lotset1.getLottype();
	String lottypename = lotset1.getLottypename();
	String remark = lotset1.getRemark();
	
	String lotattid1 = lotset1.getLotattid();	//ID
	String lotid1 = lotset1.getLotid();			//��������ID
	String lotname1 = lotset1.getLotname();		//������������
	String islot1 = lotset1.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq1 = lotset1.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid2 = lotset2.getLotattid();	//ID
	String lotid2 = lotset2.getLotid();			//��������ID
	String lotname2 = lotset2.getLotname();		//������������
	String islot2 = lotset2.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq2 = lotset2.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid3 = lotset3.getLotattid();	//ID
	String lotid3 = lotset3.getLotid();			//��������ID
	String lotname3 = lotset3.getLotname();		//������������
	String islot3 = lotset3.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq3 = lotset3.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid4 = lotset4.getLotattid();	//ID
	String lotid4 = lotset4.getLotid();			//��������ID
	String lotname4 = lotset4.getLotname();		//������������
	String islot4 = lotset4.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq4 = lotset4.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid5 = lotset5.getLotattid();	//ID
	String lotid5 = lotset5.getLotid();			//��������ID
	String lotname5 = lotset5.getLotname();		//������������
	String islot5 = lotset5.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq5 = lotset5.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid6 = lotset6.getLotattid();	//ID
	String lotid6 = lotset6.getLotid();			//��������ID
	String lotname6 = lotset6.getLotname();		//������������
	String islot6 = lotset6.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq6 = lotset6.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid7 = lotset7.getLotattid();	//ID
	String lotid7 = lotset7.getLotid();			//��������ID
	String lotname7 = lotset7.getLotname();		//������������
	String islot7 = lotset7.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq7 = lotset7.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid8 = lotset8.getLotattid();	//ID
	String lotid8 = lotset8.getLotid();			//��������ID
	String lotname8 = lotset8.getLotname();		//������������
	String islot8 = lotset8.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq8 = lotset8.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid9 = lotset9.getLotattid();	//ID
	String lotid9 = lotset9.getLotid();			//��������ID
	String lotname9 = lotset9.getLotname();		//������������
	String islot9 = lotset9.getIslot();			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq9 = lotset9.getLotseq();			//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid10 = lotset10.getLotattid();	//ID
	String lotid10 = lotset10.getLotid();		//��������ID
	String lotname10 = lotset10.getLotname();	//������������
	String islot10 = lotset10.getIslot();		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq10 = lotset10.getLotseq();		//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid11 = lotset11.getLotattid();	//ID
	String lotid11 = lotset11.getLotid();		//��������ID
	String lotname11 = lotset11.getLotname();	//������������
	String islot11 = lotset11.getIslot();		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq11 = lotset11.getLotseq();		//��ʾ˳����߷���ͳ�Ƶ�˳��
	
	String lotattid12 = lotset12.getLotattid();	//ID
	String lotid12 = lotset12.getLotid();		//��������ID
	String lotname12 = lotset12.getLotname();	//������������
	String islot12 = lotset12.getIslot();		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
	int lotseq12 = lotset12.getLotseq();		//��ʾ˳����߷���ͳ�Ƶ�˳��
%>
<html>
<head>
<title>����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	var condition = "";
	//�����Ƿ�Ϊ�����ַ�
	function CheckC(str)
	{
		var ts, tscode;
		for(var i=0; i<str.length; i++) 
		{ 
			ts = str.substring(i); 
			tscode = str.charCodeAt(i); 
			if(tscode >= 19968) 
			{
				return false;
			}
		}
		return true;
	}
	
	//����
	function Save(){
	
		var remark = document.getElementById("remark").value;	//��ע
		
		var lotattid1 = document.getElementById("lotattid1").value;		//ID
		var lotname1 = document.getElementById("lotname1").value;		//������������
		var lotid1 = document.getElementById("lotid1").value;			//��������ID
		var islot1 = document.getElementById("islot1").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq1 = document.getElementById("lotseq1").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid2 = document.getElementById("lotattid2").value;		//ID
		var lotname2 = document.getElementById("lotname2").value;		//������������
		var lotid2 = document.getElementById("lotid2").value;			//��������ID
		var islot2 = document.getElementById("islot2").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq2 = document.getElementById("lotseq2").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid3 = document.getElementById("lotattid3").value;		//ID
		var lotname3 = document.getElementById("lotname3").value;		//������������
		var lotid3 = document.getElementById("lotid3").value;			//��������ID
		var islot3 = document.getElementById("islot3").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq3 = document.getElementById("lotseq3").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid4 = document.getElementById("lotattid4").value;		//ID
		var lotname4 = document.getElementById("lotname4").value;		//������������
		var lotid4 = document.getElementById("lotid4").value;			//��������ID
		var islot4 = document.getElementById("islot4").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq4 = document.getElementById("lotseq4").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid5 = document.getElementById("lotattid5").value;		//ID
		var lotname5 = document.getElementById("lotname5").value;		//������������
		var lotid5 = document.getElementById("lotid5").value;			//��������ID
		var islot5 = document.getElementById("islot5").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq5 = document.getElementById("lotseq5").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid6 = document.getElementById("lotattid6").value;		//ID
		var lotname6 = document.getElementById("lotname6").value;		//������������
		var lotid6 = document.getElementById("lotid6").value;			//��������ID
		var islot6 = document.getElementById("islot6").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq6 = document.getElementById("lotseq6").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid7 = document.getElementById("lotattid7").value;		//ID
		var lotname7 = document.getElementById("lotname7").value;		//������������
		var lotid7 = document.getElementById("lotid7").value;			//��������ID
		var islot7 = document.getElementById("islot7").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq7 = document.getElementById("lotseq7").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid8 = document.getElementById("lotattid8").value;		//ID
		var lotname8 = document.getElementById("lotname8").value;		//������������
		var lotid8 = document.getElementById("lotid8").value;			//��������ID
		var islot8 = document.getElementById("islot8").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq8 = document.getElementById("lotseq8").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid9 = document.getElementById("lotattid9").value;		//ID
		var lotname9 = document.getElementById("lotname9").value;		//������������
		var lotid9 = document.getElementById("lotid9").value;			//��������ID
		var islot9 = document.getElementById("islot9").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq9 = document.getElementById("lotseq9").value;			//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid10 = document.getElementById("lotattid10").value;	//ID
		var lotname10 = document.getElementById("lotname10").value;		//������������
		var lotid10 = document.getElementById("lotid10").value;			//��������ID
		var islot10 = document.getElementById("islot10").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq10 = document.getElementById("lotseq10").value;		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid11 = document.getElementById("lotattid11").value;	//ID
		var lotname11 = document.getElementById("lotname11").value;		//������������
		var lotid11 = document.getElementById("lotid11").value;			//��������ID
		var islot11 = document.getElementById("islot11").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq11 = document.getElementById("lotseq11").value;		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		var lotattid12 = document.getElementById("lotattid12").value;	//ID
		var lotname12 = document.getElementById("lotname12").value;		//������������
		var lotid12 = document.getElementById("lotid12").value;			//��������ID
		var islot12 = document.getElementById("islot12").value;			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		var lotseq12 = document.getElementById("lotseq12").value;		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		if(remark != null && remark.length > 0)
		{
			if(strlen(remark) > 200)
			{
				alert("����ע��������");
				return;
			}
		}
		
		if(lotid1 != null && lotid1.length > 0){
			if(!CheckC(lotid1))
			{
				alert("�����Դ���1�������������ģ�");
				return;
			}
			
		}
		if(lotseq1 == null || lotseq1.length < 1){
			alert("��˳��1������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq1)){
				alert("��˳��1��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid2 != null && lotid2.length > 0){
			if(!CheckC(lotid2))
			{
				alert("�����Դ���2�������������ģ�");
				return;
			}
			
		}
		if(lotseq2 == null || lotseq2.length < 1){
			alert("��˳��2������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq2)){
				alert("��˳��2��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid3 != null && lotid3.length > 0){
			if(!CheckC(lotid3))
			{
				alert("�����Դ���3�������������ģ�");
				return;
			}
			
		}
		if(lotseq3 == null || lotseq3.length < 1){
			alert("��˳��3������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq3)){
				alert("��˳��3��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid4 != null && lotid4.length > 0){
			if(!CheckC(lotid4))
			{
				alert("�����Դ���4�������������ģ�");
				return;
			}
			
		}
		if(lotseq4 == null || lotseq4.length < 1){
			alert("��˳��4������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq4)){
				alert("��˳��4��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid5 != null && lotid5.length > 0){
			if(!CheckC(lotid5))
			{
				alert("�����Դ���5�������������ģ�");
				return;
			}
			
		}
		if(lotseq5 == null || lotseq5.length < 1){
			alert("��˳��5������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq5)){
				alert("��˳��5��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid6 != null && lotid6.length > 0){
			if(!CheckC(lotid6))
			{
				alert("�����Դ���6�������������ģ�");
				return;
			}
			
		}
		if(lotseq6 == null || lotseq6.length < 1){
			alert("��˳��6������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq6)){
				alert("��˳��6��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid7 != null && lotid7.length > 0){
			if(!CheckC(lotid7))
			{
				alert("�����Դ���7�������������ģ�");
				return;
			}
			
		}
		if(lotseq7 == null || lotseq7.length < 1){
			alert("��˳��7������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq7)){
				alert("��˳��7��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid8 != null && lotid8.length > 0){
			if(!CheckC(lotid8))
			{
				alert("�����Դ���8�������������ģ�");
				return;
			}
			
		}
		if(lotseq8 == null || lotseq8.length < 1){
			alert("��˳��8������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq8)){
				alert("��˳��8��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid9 != null && lotid9.length > 0){
			if(!CheckC(lotid9))
			{
				alert("�����Դ���9�������������ģ�");
				return;
			}
			
		}
		if(lotseq9 == null || lotseq9.length < 1){
			alert("��˳��9������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq9)){
				alert("��˳��9��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid10 != null && lotid10.length > 0){
			if(!CheckC(lotid10))
			{
				alert("�����Դ���10�������������ģ�");
				return;
			}
			
		}
		if(lotseq10 == null || lotseq10.length < 1){
			alert("��˳��10������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq10)){
				alert("��˳��10��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid11 != null && lotid11.length > 0){
			if(!CheckC(lotid11))
			{
				alert("�����Դ���11�������������ģ�");
				return;
			}
			
		}
		if(lotseq11 == null || lotseq11.length < 1){
			alert("��˳��11������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq11)){
				alert("��˳��11��ֻ���������֣�");
				return;
			}
		}
		
		if(lotid12 != null && lotid12.length > 0){
			if(!CheckC(lotid12))
			{
				alert("�����Դ���12�������������ģ�");
				return;
			}
			
		}
		if(lotseq12 == null || lotseq12.length < 1){
			alert("��˳��12������Ϊ�գ�");
			return;
		}else{
			if(!numChar(lotseq12)){
				alert("��˳��12��ֻ���������֣�");
				return;
			}
		}

		condition = "&lottype=<%=lottype%>&remark=" + remark 
			 + "&lotattid1=" + lotattid1 + "&lotname1=" + lotname1 + "&lotid1=" + lotid1 + "&islot1=" + islot1 + "&lotseq1=" + lotseq1 
			 + "&lotattid2=" + lotattid2 + "&lotname2=" + lotname2 + "&lotid2=" + lotid2 + "&islot2=" + islot2 + "&lotseq2=" + lotseq2
			 + "&lotattid3=" + lotattid3 + "&lotname3=" + lotname3 + "&lotid3=" + lotid3 + "&islot3=" + islot3 + "&lotseq3=" + lotseq3 
			 + "&lotattid4=" + lotattid4 + "&lotname4=" + lotname4 + "&lotid4=" + lotid4 + "&islot4=" + islot4 + "&lotseq4=" + lotseq4 
			 + "&lotattid5=" + lotattid5 + "&lotname5=" + lotname5 + "&lotid5=" + lotid5 + "&islot5=" + islot5 + "&lotseq5=" + lotseq5
			 + "&lotattid6=" + lotattid6 + "&lotname6=" + lotname6 + "&lotid6=" + lotid6 + "&islot6=" + islot6 + "&lotseq6=" + lotseq6
			 + "&lotattid7=" + lotattid7 + "&lotname7=" + lotname7 + "&lotid7=" + lotid7 + "&islot7=" + islot7 + "&lotseq7=" + lotseq7
			 + "&lotattid8=" + lotattid8 + "&lotname8=" + lotname8 + "&lotid8=" + lotid8 + "&islot8=" + islot8 + "&lotseq8=" + lotseq8
			 + "&lotattid9=" + lotattid9 + "&lotname9=" + lotname9 + "&lotid9=" + lotid9 + "&islot9=" + islot9 + "&lotseq9=" + lotseq9
			 + "&lotattid10=" + lotattid10 + "&lotname10=" + lotname10 + "&lotid10=" + lotid10 + "&islot10=" + islot10 + "&lotseq10=" + lotseq10
			 + "&lotattid11=" + lotattid11 + "&lotname11=" + lotname11 + "&lotid11=" + lotid11 + "&islot11=" + islot11 + "&lotseq11=" + lotseq11
			 + "&lotattid12=" + lotattid12 + "&lotname12=" + lotname12 + "&lotid12=" + lotid12 + "&islot12=" + islot12 + "&lotseq12=" + lotseq12;
	
		window.returnValue = condition;
		window.close();
	}
	
	//ѡ���������Ժ�
	function getLotAtts(obj){
		lotTool.getLotDetails(obj.value, {callback:function(data){
			var tempstr;
			for(var i=0; i<data.length; i++){
				tempstr = "lotname" + (i+1);
				document.getElementById(tempstr).value = data[i].m_strAttname;		//������������
			}
		}});
		
	}
	
	//��½ҳ��
	function OnLoad(){
		//ͬ��
		DWREngine.setAsync(false);
		selectLot("", "lot");				//��������
		DWREngine.setAsync(true);
		
		document.getElementById("remark").value = '<%=remark==null?"":remark%>';
		
		document.getElementById("lotattid1").value = '<%=lotattid1%>';
		document.getElementById("lotname1").value = '<%=lotname1%>';
		document.getElementById("lotid1").value = '<%=lotid1%>';
		document.getElementById("islot1").value = "<%=islot1%>";
		document.getElementById("lotseq1").value = "<%=lotseq1%>";
		
		document.getElementById("lotattid2").value = '<%=lotattid2%>';
		document.getElementById("lotname2").value = '<%=lotname2%>';
		document.getElementById("lotid2").value = '<%=lotid2%>';
		document.getElementById("islot2").value = "<%=islot2%>";
		document.getElementById("lotseq2").value = "<%=lotseq2%>";
		
		document.getElementById("lotattid3").value = '<%=lotattid3%>';
		document.getElementById("lotname3").value = '<%=lotname3%>';
		document.getElementById("lotid3").value = '<%=lotid3%>';
		document.getElementById("islot3").value = "<%=islot3%>";
		document.getElementById("lotseq3").value = "<%=lotseq3%>";
		
		document.getElementById("lotattid4").value = '<%=lotattid4%>';
		document.getElementById("lotname4").value = '<%=lotname4%>';
		document.getElementById("lotid4").value = '<%=lotid4%>';
		document.getElementById("islot4").value = "<%=islot4%>";
		document.getElementById("lotseq4").value = "<%=lotseq4%>";
		
		document.getElementById("lotattid5").value = '<%=lotattid5%>';
		document.getElementById("lotname5").value = '<%=lotname5%>';
		document.getElementById("lotid5").value = '<%=lotid5%>';
		document.getElementById("islot5").value = "<%=islot5%>";
		document.getElementById("lotseq5").value = "<%=lotseq5%>";
		
		document.getElementById("lotattid6").value = '<%=lotattid6%>';
		document.getElementById("lotname6").value = '<%=lotname6%>';
		document.getElementById("lotid6").value = '<%=lotid6%>';
		document.getElementById("islot6").value = "<%=islot6%>";
		document.getElementById("lotseq6").value = "<%=lotseq6%>";
		
		document.getElementById("lotattid7").value = '<%=lotattid7%>';
		document.getElementById("lotname7").value = '<%=lotname7%>';
		document.getElementById("lotid7").value = '<%=lotid7%>';
		document.getElementById("islot7").value = "<%=islot7%>";
		document.getElementById("lotseq7").value = "<%=lotseq7%>";
		
		document.getElementById("lotattid8").value = '<%=lotattid8%>';
		document.getElementById("lotname8").value = '<%=lotname8%>';
		document.getElementById("lotid8").value = '<%=lotid8%>';
		document.getElementById("islot8").value = "<%=islot8%>";
		document.getElementById("lotseq8").value = "<%=lotseq8%>";
		
		document.getElementById("lotattid9").value = '<%=lotattid9%>';
		document.getElementById("lotname9").value = '<%=lotname9%>';
		document.getElementById("lotid9").value = '<%=lotid9%>';
		document.getElementById("islot9").value = "<%=islot9%>";
		document.getElementById("lotseq9").value = "<%=lotseq9%>";
		
		document.getElementById("lotattid10").value = '<%=lotattid10%>';
		document.getElementById("lotname10").value = '<%=lotname10%>';
		document.getElementById("lotid10").value = '<%=lotid10%>';
		document.getElementById("islot10").value = "<%=islot10%>";
		document.getElementById("lotseq10").value = "<%=lotseq10%>";
		
		document.getElementById("lotattid11").value = '<%=lotattid11%>';
		document.getElementById("lotname11").value = '<%=lotname11%>';
		document.getElementById("lotid11").value = '<%=lotid11%>';
		document.getElementById("islot11").value = "<%=islot11%>";
		document.getElementById("lotseq11").value = "<%=lotseq11%>";
		
		document.getElementById("lotattid12").value = '<%=lotattid12%>';
		document.getElementById("lotname12").value = '<%=lotname12%>';
		document.getElementById("lotid12").value = '<%=lotid12%>';
		document.getElementById("islot12").value = "<%=islot12%>";
		document.getElementById("lotseq12").value = "<%=lotseq12%>";
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã��������� -&gt; �޸���������</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right">���ͣ�</td>
     <td class="yx"><input type="text" id="lottype" value="<%=lottypename%>" readonly class="input_readonly"></td>
     <td class="yx1" width="100px" align="right">�������ԣ�</td>
     <td class="xx1"><select id="lot" onchange="getLotAtts(this)"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="y2" align="right">��ע��</td>
     <td colspan="3"><textarea id="remark" cols="70" rows="3"></textarea></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center">��������</td>
     <td class="yx1" align="center">���Դ���</td>
     <td class="yx1" align="center">�Ƿ�ʹ��</td>
     <td class="x1" align="center">˳��</td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid1" value=""><input type="text" id="lotname1" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid1" size="15" maxlength="15" value="lotatt1" disabled></td>
     <td class="yx" align="center"><select id="islot1" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq1" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid2" value=""><input type="text" id="lotname2" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid2" size="15" maxlength="15" value="lotatt2" disabled></td>
     <td class="yx" align="center"><select id="islot2" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq2" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid3" value=""><input type="text" id="lotname3" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid3" size="15" maxlength="15" value="lotatt3" disabled></td>
     <td class="yx" align="center"><select id="islot3" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq3" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid4" value=""><input type="text" id="lotname4" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid4" size="15" maxlength="15" value="lotatt4" disabled></td>
     <td class="yx" align="center"><select id="islot4" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq4" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid5" value=""><input type="text" id="lotname5" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid5" size="15" maxlength="15" value="lotatt5" disabled></td>
     <td class="yx" align="center"><select id="islot5" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq5" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid6" value=""><input type="text" id="lotname6" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid6" size="15" maxlength="15" value="lotatt6" disabled></td>
     <td class="yx" align="center"><select id="islot6" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq6" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid7" value=""><input type="text" id="lotname7" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid7" size="15" maxlength="15" value="lotatt7" disabled></td>
     <td class="yx" align="center"><select id="islot7" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq7" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid8" value=""><input type="text" id="lotname8" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid8" size="15" maxlength="15" value="lotatt8" disabled></td>
     <td class="yx" align="center"><select id="islot8" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq8" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid9" value=""><input type="text" id="lotname9" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid9" size="15" maxlength="15" value="lotatt9" disabled></td>
     <td class="yx" align="center"><select id="islot9" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq9" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid10" value=""><input type="text" id="lotname10" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid10" size="15" maxlength="15" value="lotatt10" disabled></td>
     <td class="yx" align="center"><select id="islot10" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq10" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid11" value=""><input type="text" id="lotname11" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid11" size="15" maxlength="15" value="lotatt11" disabled></td>
     <td class="yx" align="center"><select id="islot11" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq11" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid12" value=""><input type="text" id="lotname12" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid12" size="15" maxlength="15" value="lotatt12" disabled></td>
     <td class="yx" align="center"><select id="islot12" style="width:100px;"><option value="Y">��</option><option value="N">��</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq12" size="15" maxlength="2" value=""></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="Save()" id="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>