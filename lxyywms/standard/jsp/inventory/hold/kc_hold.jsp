 <%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.HashMap" %>
<%

    HashMap hsPopedom = null;
	boolean kcKwlockRK = false; 
	boolean kcKwlockCK = false; 
	boolean kcKwlockUNRK = false; 
	boolean kcKwlockUNCK = false; 
	boolean kcKwlockQuery = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kcKwlockRK") != null){
			kcKwlockRK = true;
		}
		if(hsPopedom.get("kcKwlockCK") != null){
			kcKwlockCK = true;
		}
		if(hsPopedom.get("kcKwlockUNRK") != null){
			kcKwlockUNRK = true;
		}
		if(hsPopedom.get("kcKwlockUNCK") != null){
			kcKwlockUNCK = true;
		}
		if(hsPopedom.get("kcKwlockQuery") != null){
			kcKwlockQuery = true;
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
		var linerow = page.document.getElementById("lineviewrow").value;
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAreaId=" + cargoAreaId + "&platoon=" + platoon 
				+ "&column=" + column + "&floor=" + floor + "&in_lock=" + in_lock + "&out_lock=" + out_lock + "&linerow=" + linerow;
		Loading();
		list.location.href = ac + "baseCargoSpaceAction&method=cargoLockSpaceQuery" + condition;
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
		//selectAreaList("", "cargoAreaId", warehouseid, "2");
		selectAreaListNotTem('001001', 'whAreaId', warehouseid, "1");// �������
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" name="condition">
 <div class="con_bk">
   <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ����/�ͷ�</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(kcKwlockQuery){%><li class="tubiao1"><a href="#" onclick="Search()">��ѯ</a></li><%}%>
			<%if(kcKwlockUNCK){%><li class="tubiao2" style="width:85px;"><a href="#" onclick="Update(4)">�����ͷ�</a></li><%}%>
			<%if(kcKwlockUNRK){%><li class="tubiao2" style="width:85px;"><a href="#" onclick="Update(3)">����ͷ�</a></li><%}%>
			<%if(kcKwlockCK){%><li class="tubiao2" style="width:85px;"><a href="#" onclick="Update(2)">���ⶳ��</a></li><%}%>
			<%if(kcKwlockRK){%><li class="tubiao2" style="width:85px;"><a href="#" onclick="Update(1)">��ⶳ��</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>

     <!-- ======== ��ѯ���� ======== -->
	  <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="yx1" width="100px" align="right"><div align="right">�ֿ⣺</div></td>
		  <td class="yx"><select id="warehouseid" onChange="getAreaList()" style="width:135px;"><option value=""></option></select></td>
		  <td class="yx1" width="100px" align="right"><div align="right">������</div></td>
	      <td class="yx" width="150px"><select name="whAreaId" style="width:100px;"><option value=""></option></select></td>
		  <td class="yx1" width="100px" align="right">������</td>
		  <td class="xx1" width="150px"><select name="cargoAreaId" style="width:100px;"><option value=""></option></select></td> 
		</tr>
		<tr>
	   	  <td class="y1" align="right">��λ��</td>
	   	  <td class="x">
	   	    <input type="text" name="platoon" size="3" maxlength="2">��
	   	    <input type="text" name="column" size="3" maxlength="2">��
	   	    <input type="text" name="floor" size="3" maxlength="2">��
	   	  </td>
	   	  <td class="y1" align="right">�������</td>
		  <td class="x">
		    <select name="in_lock" style="width:100px;"><option value="">--��ѡ��--</option><option value="Y">����</option><option value="N">δ��</option></select>
		  </td> 
		  <td class="y1" align="right">��������</td>
		  <td>
	        <select name="out_lock" style="width:100px;"><option value="">--��ѡ��--</option><option value="Y">����</option><option value="N">δ��</option></select>
	      </td> 
	    </tr>	 
	  </table>

    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
      	<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_lock_list.jsp"
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
