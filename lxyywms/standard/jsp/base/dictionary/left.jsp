<%@ page contentType = "text/html;charset=gb2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.Map"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a{
font-size: 12px;
	color: #033d61;
text-decoration:none;
}
a linked{
text-decoration:none;
}
a hover{
text-decoration:none;
}
a visited{
font-size: 12px;
	color: #033d61;
}
.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}
.STYLE3 {
	font-size: 12px;
	color: #033d61;
}
-->
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #ffffff; POSITION: relative; TOP: 2px 
}
.menu_title2 SPAN {
	FONT-WEIGHT: bold; LEFT: 3px; COLOR: #FFCC00; POSITION: relative; TOP: 2px
}

</style>
</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23" background="<%=request.getContextPath()%>/standard/images/main_47.gif" id="imgmenu1" class="menu_title" onMouseOver="this.className='menu_title2';" onMouseOut="this.className='menu_title';" style="cursor:hand"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="18%">&nbsp;</td>
                <td width="82%" class="STYLE1">字典信息管理</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td background="<%=request.getContextPath()%>/standard/images/main_51.gif" id="submenu1">
			 <div>  
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
 
              <%
				   List lsMap = (List)request.getAttribute("exResultList");
				   System.out.println(lsMap);
					  if(lsMap!=null)
					{
	    	    	for(int i = 0; i < lsMap.size(); i++) 
	  	 	        {
	  	 	     	Map map = (Map)lsMap.get(i);
			        String strMapId =map.getM_mapId();
			        String strMapName=map.getM_mapName();
			        String strMapNumber=map.getM_mapNumber();
		    	    String strTableName=map.getM_tableName();
		    	   
           %>
                  <tr>
                    <td>
	                    <div align="center">
	                    	<img src="<%=request.getContextPath()%>/standard/images/left.gif" width="10" height="10" />
	                    </div>
					</td>
                    <td>
	                    <table width="95%" border="0" cellspacing="0" cellpadding="0">
	                    <a href="<%=request.getContextPath()%>/BMSService?actionCode=<%=strTableName%>" target="main">
	                        <tr>
	                          <td height="20" style="cursor:hand" onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "onmouseout="this.style.borderStyle='none'">
	                          	<span class="STYLE3"><%=strMapName%></span>
	                          </td>
	                         </tr>
	                     </a>
	                    </table>
                    </td>
                  </tr>
                  <%
                  }
                  }
                  %>
                  </table>
                  </td>
                  </tr>
              <tr>
                <td height="5"><img src="<%=request.getContextPath()%>/standard/images/main_52.gif" width="100%" height="5" /></td>
              </tr>
            </table></div></td>
          </tr>
          
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
 
</table>
<script>
</script>
</body>
</html>