<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%
    List  ls = (List)request.getAttribute("exResultList");
	int[] intArr = new int[10];
	if(ls!=null && ls.size()>0){
		for(int i=0;i<ls.size();i++){
		  Object[] obj   = (Object[])ls.get(i);
		    if(obj!=null&&obj[0]!=null&&obj[1]!=null){
		       int z = Integer.parseInt(obj[0].toString().substring(6));//获取巷道号
		       intArr[z-1]= Integer.parseInt(obj[1].toString());
		    }
		 }	
    }
	
	
	String[] strArr = new String[10];
	strArr[0]="堆垛机1";
	strArr[1]="堆垛机2";
	strArr[2]="堆垛机3";
	strArr[3]="堆垛机4";
	strArr[4]="堆垛机5";
	strArr[5]="堆垛机6";
	strArr[6]="堆垛机7";
	strArr[7]="堆垛机8";
	strArr[8]="堆垛机9";
	strArr[9]="堆垛机10";

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
//=============调用方法=====================
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
//table_x     整个表格的x坐标
//table_y     整个表格的y坐标
//thickness   单个柱体的长度
//table_width 单个柱体的宽度
//all_width   整个表格的宽度
//all_height  整个表格的高度
//table_type  表格类型 A类型 竖直方向 B类型 水平方向
// 注解：表格的高度值（虚拟值） 根据所给的数据值大小给出一个合理的划分
</script>
</body>
</html>