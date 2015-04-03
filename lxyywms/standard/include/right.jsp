<%@ page language="java" pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>浙江刚玉自动化立体仓储物流管理系统</title>
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
          <div id="dqwz" class="f_l">当前位置：<span><a href="#">系统首页</a></span></div>
          
        </div>
        
        
        <div class="xtcd">
        <h3>基本维护 </h3>
        <ul>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_06.jpg"/></a> <p><a href="#">基本信息</a></p></li>
         <!--<li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_09.jpg"/></a> <p><a href="#">规则管理</a></p></li>-->
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_01.jpg"/></a> <p><a href="#">系统管理</a></p></li>
         <!--<li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_32.jpg"/></a> <p><a href="#">修改密码</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_34.jpg"/></a> <p><a href="#">用户手册</a></p></li>-->
        </ul>
        
        </div>
        <div class="xtbz-outer">
        <img src="../images/xtbz_bg.jpg" class="alpha">
        <div class="xtbz">
        <h3>业务操作 </h3>
        <ul>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_03.jpg"/></a> <p><a href="#">入库管理</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_12.jpg"/></a> <p><a href="#">出库管理</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_14.jpg"/></a> <p><a href="#">库存管理</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_21.jpg"/></a> <p><a href="#">质量管理</a></p></li>
         <li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_16.jpg"/></a> <p><a href="#">查询统计</a></p></li>
         
         <!--<li><a href="#"><img src="<%=request.getContextPath()%>/standard/images/index_18.jpg"/></a> <p><a href="#">车辆调度</a></p></li>-->
        </ul>
        </div>
        </div>

</body>
</html>

