 <%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%

	HashMap hsPopedom = null;
	boolean instoreQuery_query = true;   //��ѯ

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("instoreQuery_query") != null){
			instoreQuery_query = true;
		}
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	function Update(flag){
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("��ѡ��һ����¼��");
		}
		else{
			//get�ύ����Ϊ2056,�������������post�ύ
			//list.location.href = ac + "baseCargoSpaceAction&method=cargoSpaceLock&flag=" + flag + "&ids=" + ids.substring(0, ids.length-1);
			
			//��ΪPOST�ύ
			var subUrl = ac + "baseCargoSpaceAction&method=cargoSpaceLock&flag=" + flag;
			list.document.write("<form action='"+subUrl+"' method='post' name='formx1' style='display:none'>"); 
			list.document.write("<input type=hidden name='ids' value='"+ids.substring(0, ids.length-1)+"'>"); 
			list.document.write("</form>"); 
			list.document.formx1.submit(); 
		}
	}
	
	//��ѯ
	function Search(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value;
		var cargoAreaId = document.getElementById("cargoAreaId").value;
		var platoon = document.getElementById("platoon").value;
		var column = document.getElementById("column").value;
		var floor = document.getElementById("floor").value;
		var in_lock = document.getElementById("in_lock").value;
		var out_lock = document.getElementById("out_lock").value;
		
		if(warehouseid=="" || whAreaId==""){
			alert("��ѡ��ֿ�Ϳ�����");
			return;
		}

		if(platoon!=null && platoon!=""){
			if(!numChar(platoon) || platoon<=0){
				alert("���š������Ǵ����������!");
				return false;
			}
		}
		
		if(column!=null && column!=""){
			if(!numChar(column) || column<=0){
				alert("���С������Ǵ����������!");
				return false;
			}
		}
		
		if(floor!=null && floor!=""){
			if(!numChar(floor) || floor<=0){
				alert("���㡿�����Ǵ����������!");
				return false;
			}
		}
		
		//ÿҳ��ʾ����
		var linerow = list.document.getElementById("lineviewrow").value;
		
		condition = "&warehouseid=" +��warehouseid + "&whAreaId=" +��whAreaId + "&cargoAreaId=" +��cargoAreaId + "&platoon=" +��platoon 
				+ "&column=" +��column + "&floor=" +��floor + "&in_lock=" +��in_lock + "&out_lock=" +��out_lock + "&linerow=" + linerow;
		Loading();
		list.location.href = ac + "baseCargoSpaceAction&method=cargoSpaceQuery" + condition;
	}
	
	//���ݲֿ��ÿ������������б�
	function getAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
	}
	
	//ҳ���¼
	function OnLoad(){	
		//ͬ��
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" name="condition">
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��λ��������</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" width="100px" align="right"><div align="right">�ֿ⣺</div></td>
	  <td class="yx"><select id="warehouseid" onChange="getAreaList()" style="width:135px;"><option value=""></option></select></td>
	  <td class="yx1" width="100px" align="right"><div align="right">������</div></td>
      <td class="yx" width="150px"><select name="whAreaId" style="width:100px;"><option value=""></option></select></td>
	  <td class="yx1" width="100px" align="right">������</td>
	  <td class="xx1" width="150px"><select name="cargoAreaId" style="width:100px;"><option value=""></option></select></td> 
	</tr>
	<tr>
   	  <td class="yx1" align="right">��λ��</td>
   	  <td class="yx">
   	    <input type="text" name="platoon" size="3" maxlength="2">��
   	    <input type="text" name="column" size="3" maxlength="2">��
   	    <input type="text" name="floor" size="3" maxlength="2">��
   	  </td>
   	  <td class="yx1" align="right">�������</td>
	  <td class="yx">
	    <select name="in_lock" style="width:100px;"><option value="">--��ѡ��--</option><option value="Y">����</option><option value="N">δ��</option></select>
	  </td> 
	  <td class="yx1" align="right">��������</td>
	  <td class="xx1">
        <select name="out_lock" style="width:100px;"><option value="">--��ѡ��--</option><option value="Y">����</option><option value="N">δ��</option></select>
      </td> 
    </tr>	 
    <tr>
      <td height="35" colspan="6" align="center" valign="bottom">
        <input type="button" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search" onClick="Search()" <%if(instoreQuery_query==false){%>disabled<%}%>>
        <input type="button" value="&nbsp;&nbsp;������" class="button_edit" onClick="Update(1)">
        <input type="button" value="&nbsp;&nbsp;�������" class="button_edit" onClick="Update(2)">
        <input type="button" value="&nbsp;&nbsp;������" class="button_edit" onClick="Update(3)">
        <input type="button" value="&nbsp;&nbsp;�������" class="button_edit" onClick="Update(4)">
        <input type="button" value="&nbsp;&nbsp;��ջ�λ" class="button_edit" onClick="Update(5)">
        <input type="button" value="&nbsp;&nbsp;�����λ" class="button_edit" onClick="Update(6)">
        <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
      </td>
    </tr>
  </table>

    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
      	<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_lock_list.jsp"
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table> 

 <!-- ҳ����ز㣨start�� -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- ҳ����ز㣨end�� -->  
</body>
</html>
