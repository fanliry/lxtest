<%@ page language="java" pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�㽭�����Զ�������ִ���������ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<style>
body{
	background:url(../images/beijing.jpg) no-repeat ;
	background-position:bottom;
	background-position:right;
}

h3{ font-size:14px; color:#000; line-height:30px; text-indent:20px;}
.xtcd{padding:10px 0; height:127px;}
.xtcd ul{ padding:0 20px;}
.xtcd ul li{ width:100px; text-align:center; margin:5px 0; float:left;}
.xtcd ul li p{ line-height:30px;}

.xtbz-outer{
	position:relative;
	height:167px; 
	padding-top:10px; 
}
.alpha{filter:alpha(opacity=30);}
.xtbz{
	position:absolute;
	left:0px;
	top:0px;
	height:167px; 
	padding-top:10px; 
}
.xtbz ul{ padding:0 20px;}
.xtbz ul li{ width:100px; text-align:center; margin:5px 0; float:left;}
.xtbz ul li p{ line-height:30px;}


</style>
</head>

<body>
        <div class="wz">
          <div id="dqwz" class="f_l">��ǰλ�ã�<span><a href="#">ϵͳ��ҳ</a></span></div>
          
        </div>
        
        
        <div class="xtcd">
        <h3>����ά�� </h3>
        <ul>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_06.jpg"/></a> <p><a href="#">������Ϣ</a></p></li>
         <!--<li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_09.jpg"/></a> <p><a href="#">�������</a></p></li>-->
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_01.jpg"/></a> <p><a href="#">ϵͳ����</a></p></li>
         <!--<li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_32.jpg"/></a> <p><a href="#">�޸�����</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_34.jpg"/></a> <p><a href="#">�û��ֲ�</a></p></li>-->
        </ul>
        
        </div>
        <div class="xtbz-outer">
        <img src="../images/xtbz_bg.jpg" class="alpha">
        <div class="xtbz">
        <h3>ҵ����� </h3>
        <ul>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_03.jpg"/></a> <p><a href="#">������</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_12.jpg"/></a> <p><a href="#">�������</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_14.jpg"/></a> <p><a href="#">������</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_21.jpg"/></a> <p><a href="#">��������</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_16.jpg"/></a> <p><a href="#">��ѯͳ��</a></p></li>
         
         <!--<li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_18.jpg"/></a> <p><a href="#">��������</a></p></li>-->
        </ul>
        </div>
        </div>

</body>
</html>

