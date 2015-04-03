<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseWarehouseAdd = false;    //���
	boolean baseWarehouseDelete = false; //ɾ��
	boolean baseWarehouseUpdate = false; //�޸�
	boolean baseWarehouseQuery = false;  //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseWarehouseAdd") != null){
			baseWarehouseAdd = true;
		}
		if(hsPopedom.get("baseWarehouseDelete") != null){
			baseWarehouseDelete = true;
		}
		if(hsPopedom.get("baseWarehouseUpdate") != null){
			baseWarehouseUpdate = true;
		}
		if(hsPopedom.get("baseWarehouseQuery") != null){
			baseWarehouseQuery = true;
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
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//��ѯ
	function Search(){
	
		var warehousename = document.getElementById("warehousename").value;
		var condition = "&warehousename=" + warehousename;

		list.location.href = ac + "baseWarehouseAction&flag=1" + condition;
	}
	
	//����
	function Add(){
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/warehouse/base_warehouse_add.jsp",'',
			'dialogWidth=600px; dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		list.location.href= ac + "baseWarehouseAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//�޸�
	function Update(){
	
		var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "baseWarehouseAction&flag=2&warehouseid="+id, "", "dialogWidth=600px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWarehouseAction&method=ricoExecEdit" + result;
		}
	}
	
	//ɾ��
	function Delete(){
	
		var id = getChkValue();
		if(id == ""){
			alert("��ѡ��һ����¼��");
			return;
		}
		if(confirm("ȷ��Ҫɾ���ü�¼��")){
			list.location.href = ac + "baseWarehouseAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	//����ѡ��ֵ
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		
		return(id);
	}
	
-->
</script>
</head>

<body>

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �ֿ����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseWarehouseQuery){%><li class="tubiao1"><a href="#" onclick="Search();">��ѯ</a></li><%}%>
			<%if(baseWarehouseUpdate){%><li class="tubiao2"><a href="#" onclick="Update();">�޸�</a></li><%}%>
			<%if(baseWarehouseDelete){%><li class="tubiao3"><a href="#" onclick="Delete();">ɾ��</a></li><%}%>
			<%if(baseWarehouseAdd){%><li class="tubiao4"><a href="#" onclick="Add();">���</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">  
	    <tr>
	      <td class="y1" width="100" ><div align="right">�ֿ����ƣ�</div></td>
	      <td class="x"><input type="text" id="warehousename" size="20"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
        <table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/warehouse/base_warehouse_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

</body>
</html>
