<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%
    List  ls = (List)request.getAttribute("exResultList");
	int[] intArr = new int[10];
	if(ls!=null && ls.size()>0){
		for(int i=0;i<ls.size();i++){
		  Object[] obj   = (Object[])ls.get(i);
		    if(obj!=null&&obj[0]!=null&&obj[1]!=null){
		       int z = Integer.parseInt(obj[0].toString().substring(6));//��ȡ�����
		       intArr[z-1]= Integer.parseInt(obj[1].toString());
		    }
		 }	
    }
	
	
	String[] strArr = new String[10];
	strArr[0]="�Ѷ��1";
	strArr[1]="�Ѷ��2";
	strArr[2]="�Ѷ��3";
	strArr[3]="�Ѷ��4";
	strArr[4]="�Ѷ��5";
	strArr[5]="�Ѷ��6";
	strArr[6]="�Ѷ��7";
	strArr[7]="�Ѷ��8";
	strArr[8]="�Ѷ��9";
	strArr[9]="�Ѷ��10";

%>
<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office">
<!--[if !mso]>
<style>
v\:*         { behavior: url(#default#VML) }
o\:*         { behavior: url(#default#VML) }
.shape       { behavior: url(#default#VML) }
</style>
<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<style>
TD { FONT-SIZE: 9pt}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/Shape.js"></script>
</head>
<body topmargin=5 leftmargin=0 scroll=AUTO>
<script language=javascript>
//=============���÷���=====================
var dataArray = new Array()
dataArray[0]=<%=intArr[0]%>
dataArray[1]=<%=intArr[1]%>
dataArray[2]=<%=intArr[2]%>
dataArray[3]=<%=intArr[3]%>
dataArray[4]=<%=intArr[4]%>
dataArray[5]=<%=intArr[5]%>
dataArray[6]=<%=intArr[6]%>
dataArray[7]=<%=intArr[7]%>
dataArray[8]=<%=intArr[8]%>
dataArray[9]=<%=intArr[9]%>
var nameArray = new Array()
nameArray[0]="<%=strArr[0]%>"
nameArray[1]="<%=strArr[1]%>"
nameArray[2]="<%=strArr[2]%>"
nameArray[3]="<%=strArr[3]%>"
nameArray[4]="<%=strArr[4]%>"
nameArray[5]="<%=strArr[5]%>"
nameArray[6]="<%=strArr[6]%>"
nameArray[7]="<%=strArr[7]%>"
nameArray[8]="<%=strArr[8]%>"
nameArray[9]="<%=strArr[9]%>"
var total= new Array(dataArray,nameArray)
table1(total,100,20,20,30,650,300,"A");
//function table1(total,table_x,table_y,thickness,table_width,all_width,all_height,table_type)  
//table_x     ��������x����
//table_y     ��������y����
//thickness   ��������ĳ���
//table_width ��������Ŀ��
//all_width   �������Ŀ��
//all_height  �������ĸ߶�
//table_type  ������� A���� ��ֱ���� B���� ˮƽ����
// ע�⣺���ĸ߶�ֵ������ֵ�� ��������������ֵ��С����һ������Ļ���
</script>
</body>
</html>