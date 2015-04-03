<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryNeedcheck" %>
<%
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">

	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">异常单号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">发生时间</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">是否处理</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">处理时间</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">处理人</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">操作日志</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	
	if(ls != null && ls.size()>0){
		
		InventoryNeedcheck obj = null;
     	
     	String jobid;        	//作业号
     	String cargoSpaceId;     	//货位
     	String traycode;     	//托盘条码
     	String createtime;    		//生成时间
     	String handleflag;  	//是否处理
     	String handletime; 		//处理时间
     	String handlemanname;     //处理人
     	String handlecontent;     //操作日志
     	
     	String id;
     	
		for(int i = 0; i < ls.size(); i++){
			
			obj = (InventoryNeedcheck)ls.get(i);
			jobid = obj.getJobid();
			cargoSpaceId = obj.getCargoSpaceId();
			traycode = obj.getTraycode();
			createtime = obj.getCreatetime();
			handleflag = obj.getHandleflag();
			handletime = obj.getHandletime();
			handlemanname = obj.getHandlemanname();
			handlecontent = obj.getHandlecontent();
			id = obj.getNeedcheckid()+"|"+cargoSpaceId;
			
			
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
      <td class="TD_LIST1" align="center" width="50px"><input type="checkbox" name="check_id" value="<%=id%>" class="input_checkbox"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=obj.getNeedcheckid()==null?"":obj.getNeedcheckid()%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceId==null?"":cargoSpaceId%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td>
     <td class="TD_LIST" align="center"><%=handleflag==null?"":handleflag%></td>
     <td class="TD_LIST" align="center"><%=handletime==null?"":handletime%></td>
     <td class="TD_LIST" align="center"><%=handlemanname==null?"":handlemanname%></td>
     <td class="TD_LIST" align="center"><%=handlecontent==null?"":handlecontent%></td>
   </tr>
<%
		}
	}
%>  
   <tr>
     <td height="100%" colspan="9" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>