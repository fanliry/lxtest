<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.Meter"%>


<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--

//获得选择的选择框的值
		function getDelCheckName()
		{
			var strDel = '';
			var length = document.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(document.myform.elements[i].name!='check_all'&& document.myform.elements[i].type=='checkbox'&& document.myform.elements[i].checked== true){
			         strDel = strDel + document.myform.elements[i].value + ',';
			    }
			}
		return strDel;
	}
	//全选
	function selectAll()
	{
	    var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
		    if(document.myform.elements[i].name=='check_all'&& document.myform.elements[i].type=='checkbox'){
		         state = document.myform.elements[i].checked;
		         
		         break;
		    }
		}
		for(i=0;i<length;i++){
		     if(document.myform.elements[i].type=='checkbox'){
		     	   document.myform.elements[i].checked=state;
		     }
		}
	}
	//增加  object 表对象 field 表中编码字段  url 跳转页面
	function add()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=createCode&url=/standard/jsp/base/dictionary/tab/meterAdd.jsp&object=Meter&field=m_meterNumber",'','dialogWidth=350px;dialogHeight=200px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		window.location.href="<%=request.getContextPath()%>/"+result;
	   		window.close();
	   	}
	}
	//查询
	function search()
	{
	    	document.myform.action="<%=request.getContextPath()%>/BMSService?actionCode=meter";	
	    	myform.submit();
	}
	//删除
		function deleteCheck()
		{     
		        var deleteName = getDelCheckName();   
		        
		        if(deleteName == null||deleteName.length <1){
		        	alert("请选择所要删除的信息");
		        }  
		        else{
					var del = confirm("确定删除所选信息")
					if(del){
						window.location.href='<%=request.getContextPath()%>/BMSService?actionCode=delete&deleteStr='+deleteName+'&object=Meter&id=m_meterId';
					}
		        }
				
		}
	//修改
	function update()
	{
		var checkRole =  getDelCheckName();
		if(checkRole != null && checkRole.length > 0)
		{
			var strRoleIdLeng = checkRole.split(",");
			if( strRoleIdLeng.length == 2)
			{
 					alert("信息!");
			}else
			{
				alert("只能选择一条信息!");
			}
		}        
		else{
			alert("请选择你要修改的信息");
		}
	}
	 function updateUser(param){
	   		var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/meterUpd.jsp";
	   		strUrl = strUrl +"?" + param;	
	   		var  result = showModalDialog(strUrl,'','dialogWidth=350px;dialogHeight=200px');
 			if(result != null && result.length > 1)
 			{
 				window.location.href="<%=request.getContextPath()%>/"+result;
				window.close();
			}		
	   }
-->
</script>
</head>

<body>
<form name="myform" method="post" action="">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="2%" height="27"></td>
     <td width="98%" class="font_006699_bold_12">当前位置：基本信息 -&gt; 字典中心 -&gt; 计量单位</td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td width="24%" class="TD_MGR_TOP"><div align="right">计量单编码：</div></td>
     <td width="29%" class="TD_MGR_TOP"><div align="left">
       <input type="text" name="number" id="number"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
     <td width="17%" class="TD_MGR_TOP"><div align="right">计量单位名称：</div></td>
     <td width="30%" class="TD_MGR_TOP"><div align="left">
       <input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" class="TD_MGR_TOP"><div align="center">
       <input onclick="add()" type="button" name="button1" value="新增" class="BUTTON_STYLE1">&nbsp;
       <input onclick="deleteCheck()" type="button" name="button2" value="删除" class="BUTTON_STYLE1">&nbsp;
       <input onclick="search()" type="button" name="button4" value="查询" class="BUTTON_STYLE1"></div>
     </td>
   </tr>
 </table>
 
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="20"></td>
   </tr>
 </table>
 
 <table width="98%" height="60%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="10%" class="TD_LIST_TITLE"><div align="center">
       <input type="checkbox" name="check_all" value="checkbox" onclick="selectAll();"  class="input_checkbox">全选
     </div></td>
     <td class="TD_LIST_TITLE"><div align="center">计量单位编号</div></td>
     <td class="TD_LIST_TITLE"><div align="center">计量单位名称</div></td>
      <td class="TD_LIST_TITLE"><div align="center">操作</div></td>
   </tr>
    <%
     List ls = (List)request.getAttribute("exResultList");
		if(ls!=null)
		{
	    	for(int i = 0; i < ls.size(); i++) 
	    	{
	    	Meter meter = (Meter)ls.get(i);
	    	String strMeterId = meter.getM_meterId();
	    	String strMeterNumber =meter.getM_meterNumber(); 
	    	String strMeterName  = meter.getM_meterName();
	    	String strDesc     =meter.getM_describe();
	    	
	    	if(strMeterId == null||strMeterId.trim().length() < 1){
				 		strMeterId = " ";}
			if(strMeterNumber == null||strMeterNumber.trim().length() < 1){
				 		strMeterNumber = " ";}
			if(strMeterName == null||strMeterName.trim().length() < 1){
				 		strMeterName = " ";}
			if(strDesc == null||strDesc.trim().length() < 1){
				 		strDesc = " ";}
	    
	    	String param="id="+strMeterId+"&name="+strMeterName+"&number="+strMeterNumber;
   %>		
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST"><div align="center">
       <input type="checkbox" name="roleId" value="<%=strMeterId%>" class="input_checkbox">
     </div></td>
     <td class="TD_LIST"><div align="center"><%=strMeterNumber%></div></td>
     <td class="TD_LIST"><div align="center"><%=strMeterName%></div></td>
     <td class="TD_LIST"><div align="center"><input type="button" name="button3" value="修改" class="BUTTON_STYLE1" onclick="updateUser('<%=param%>');"></div></td>
    </tr>
   <%
   }
   }
   %>
   
   <tr>
     <td height="100%" colspan="7" class="TD_LIST"></td>
   </tr>
   
 </table>
 
 <table width="98%" align="center"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="98%" height="30" class="font_006699_12">
       <div align="right">总数[8]条&nbsp;&nbsp;每页显示[8]条&nbsp;&nbsp;当前第[1]页&nbsp;&nbsp;共[1]页&nbsp;&nbsp;&nbsp;&nbsp; 
         【<a href="#">首页</a>】【<a href="#">上一页</a>】【<a href="#">下一页</a>】【<a href="#">末页</a>】
          跳到第<input name=page type=text class=input1 size=1 > 页&nbsp;&nbsp;<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0>
       </div>
     </td>
   </tr>
 </table>
</form>
</body>
</html>
