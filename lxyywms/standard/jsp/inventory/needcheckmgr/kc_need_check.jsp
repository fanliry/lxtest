<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var outinvoiceid = "";
	var condition = null;
	
	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	//��ѯ
	function searchData(){

		var check_type = document.getElementById("check_type").value;//�̵�����
		var start_time = document.getElementById("dateoff_from").value;//��ʼʱ��
		var end_time = document.getElementById("dateoff_to").value;  //����ʱ��
		var is_do = document.getElementById("is_do").checked==true?"Y":"N"; //�Ƿ���
		condition = "&inouttype=" +��check_type + "&createtimeform=" +��start_time
			 + "&createtimeto=" + end_time + "&handleflag=" +��is_do;
		//ÿҳ��ʾ����
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;					
		list.location.href = strUrl + "inventotyNeedCheckAction" + condition;	
		Loading();	
	}
	
   //�������ɾ��
	function updateData(N){
	
		var checkbox_ids = list.document.getElementsByName("check_id");
		var ids = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			ids += checkbox_ids[i].value + ",";
	  		}
  		} 		
  		if(ids == ""){
			alert("������ѡ��һ��������Ϣ!");
		  	return false;
		}
		if(N == "1"){//����
		  if(confirm("ȷ����Ϊ����λ��")){				
			 list.location.href = strUrl + "inventotyNeedCheckAction&method=ricoExecUpdate&flag=1&ids=" + ids;			
		    }
		}
		if(N == "2"){//����
		   if(confirm("ȷ����Ϊ�ջ�λ��")){				
			  list.location.href = strUrl + "inventotyNeedCheckAction&method=ricoExecUpdate&flag=2&ids=" + ids;			
			}
		}	
	}
	//��ӡ
	function printData(){
      if(condition == null){
        alert("���Ȳ�ѯ��");
        return;
          }
  	 window.open(strUrl+ "inventotyNeedCheckAction&method=ricoExecPrint"+condition,'newwindow','width=1200,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
		}
	//ҳ���¼
	function OnLoad(){

	}
	
	

-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ���̵����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <li class="tubiao2"><a href="#" onClick="printData()">��ӡ</a></li>
		    <li class="tubiao3"><a href="#" onClick="updateData(2)">����</a></li>
		    <li class="tubiao4"><a href="#" onClick="updateData(1)">����</a></li>
			<li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" align="right">���̵����ͣ�</td>
     	  <td class="x"><select id="check_type" style="width:100px;"><option value="">--��ѡ��--</option><option value="1">���</option><option value="2">����</option></select></td>
		  <td class="y1" align="right">ʱ�䣺</td>
     	  <td class="x" style="width:230px;">
     	  	<input id="dateoff_from" type="text" onFocus="calendar()" class="Wdate" style="height:21px;width:100px;" value='<%=strTime%>'>-<input id="dateoff_to" type="text" onfocus="calendar();" class="Wdate" style="height:21px;width:100px;" value='<%=strTime%>'>
		  </td>
		  <td class="y1" align="right">�Ƿ���</td>
	      <td class="x" style="width:230px;">
	        <input type="checkbox" name="is_do"></td>
	         <td class="x" style="width:230px;">
	      </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/needcheckmgr/kc_need_check_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
</body>
</html>
