<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script>
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
  	
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//设置多选框是否选中
	//function setSel(i){

	//	var check_ids = document.getElementsByName("check_id");
	///	if(check_ids[i].checked){
	//		check_ids[i].checked = false;
	//		check_ids[i].parentNode.parentNode.style.backgroundColor = "";
	//	}else{
	//		check_ids[i].checked = true;
	//		check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
	//		
	//		queryDetail(check_ids[i].value);
	//	}
	//}
/* 	
	//设置多选框是否选中
	function setSel(ob){
	    var obj = ob.childNodes[0].childNodes[0];
		if(!obj.checked){
			obj.checked = false;
			obj.parentNode.parentNode.style.backgroundColor = "";
			queryDetail(obj.value);
		}else{
			obj.checked = true;
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
			queryDetail(obj.value);
		}
	} */
	

	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			
			queryDetail(check_ids[i].value);
		}
	}
	
	//查询详细
	function queryDetail(id){
		parent.document.getElementById("jobdid").innerHTML = id;
		parent.detail.location.href = ac + "outBoundJobAction&flag=2&jobid=" + id;
	}
	
	function OnLoad(){
	
		parent.RemoveLoading();
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
			}
		}
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE',true);
		}
	}

</script>

<body onLoad="OnLoad()">
 <table width="100%" height="100%" id="table"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td width="50" class="TD_LIST_TITLE1"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">调度任务</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">巷道</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">优先级</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">输送机号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生成时间</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">完成时间</div></td>
   </tr>
<%
	
	if(ls != null && ls.size() > 0) {
		InoutJob job = null;  
		String taskid;     		//调度任务ID
     	String whArea;  		//库区
     	String alley;    		//巷道
     	String cargospace;     	//货位
     	String traycode;     	//托盘条码
     	int priority;  			//优先级
     	String snumber;    		//输送号
     	String jobid;      		//作业号
     	String createtime;  	//生成时间
     	String finishtime; 		//完成时间
     	
     	String status;    //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业

		for(int i = 0; i < ls.size(); i++){
		
			job = (InoutJob)ls.get(i);
			taskid = job.getTaskid();				//调度任务ID
     		//whArea = job.getTcargoWhareaName();		
     		//alley = job.getTcargoAlleyName();		
     		//cargospace = job.getTcargoSpaceName(); 	
     		whArea = job.getScargoWhareaName();     //库区
     		alley = job.getScargoAlleyName();       //巷道
     		cargospace = job.getScargoSpaceName();  //货位
     		traycode = job.getTraycode();     		//托盘条码
     		priority = job.getPriority()==null?0:job.getPriority();  		//优先级
     		snumber = job.getSnumber();    			//输送号
			jobid = job.getJobid();      			//作业号
     		createtime = job.getCreatetime();  		//生成时间
     		finishtime = job.getFinishtime(); 		//完成时间
     		
     		status = job.getStatusName();
			
%>
     <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onClick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center">
     <input onclick="setSel(<%=i%>)" type="checkbox" name="check_id" class="input_checkbox" value="<%=jobid%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=taskid==null?"":taskid%></td>
     <td class="TD_LIST" align="center"><%=whArea==null?"":whArea%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=priority%></td>
     <td class="TD_LIST" align="center"><%=snumber==null?"":snumber%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=status==null?"":status%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td> 
     <td class="TD_LIST2" align="center"><%=finishtime==null?"":finishtime%></td>  
   </tr>
<%			
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>

	<tr>
      <td height="100%" colspan="11" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>
