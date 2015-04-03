<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseProductAdd = false;    //���
	boolean baseProductDelete = false; //ɾ��
	boolean baseProductUpdate = false; //�޸�
	boolean baseProductQuery = false;  //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseProductAdd") != null){
			baseProductAdd = true;
		}
		if(hsPopedom.get("baseProductDelete") != null){
			baseProductDelete = true;
		}
		if(hsPopedom.get("baseProductUpdate") != null){
			baseProductUpdate = true;
		}
		if(hsPopedom.get("baseProductQuery") != null){
			baseProductQuery = true;
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
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

	//��ѯ
	function searchData()
	{
		var productname = document.getElementById("productname").value;
		var productcode = document.getElementById("productcode").value;
		var producttype = document.getElementById("producttype").value;
		
		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
		var condition = "&productcode=" + productcode +"&productname=" + productname +"&producttype=" + producttype + "&linerow=" + linerow;
		list.location.href = ac + "baseProductAction&flag=1" + condition;
	}
		
	//����
	function addData(){
		var time = new Date(); 
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/product/base_product_add.jsp",
			'','dialogWidth=750px;dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
	   		list.location.href = ac + "baseProductAction&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   	}
	}
	
	//����ѡ��ֵ
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id += check_ids[i].value + ",";
			}
		}
		
		return(id);
	}
	
	//�޸�
	function updateData(){
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				k++;
				id = check_ids[i].value
			}
		}
		if(k != 1){
			alert("��ѡ��һ����¼��");
			return;
		}
		
		var result = showModalDialog(ac + "baseProductAction&flag=2&id="+id, "", "dialogWidth=750px;dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			list.location.href = ac + "baseProductAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	//ɾ��
	function deleteData(){
		var ids = getChkValue();
		if(ids == ""){
			alert("������ѡ��һ����¼��");
			return;
		}
		if(confirm("����Ŀ�����Ƿ���ڸò�Ʒ ���������ȡ�� ������Ե��ȷ��ɾ����")){
			var linerow = page.document.getElementById("lineviewrow").value;	//ÿҳ��ʾ����	
			list.location.href = ac + "baseProductAction&method=ricoExecDelete&ids=" + ids.substring(0, ids.length-1) + "&linerow=" + linerow;
		}
	}	
	
	//----ת����Ʒ������----
	function goType()
	{
		var strUrl = '<%=request.getContextPath()%>/standard/jsp/base/producttype/base_producttype.jsp';
		window.location.href = strUrl;
	}
	
	//ҳ���¼
	function OnLoad(){
		
		selectType("", "producttype", "producttype");
		
	}
-->
</script>
</head>
<body onLoad="OnLoad()"> 

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; ��Ʒ����</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseProductQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">��ѯ</a></li><%}%>
			<%if(baseProductUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">�޸�</a></li><%}%>
			<%if(baseProductDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">ɾ��</a></li><%}%>
			<%if(baseProductAdd){%><li class="tubiao4"><a href="#" onclick="addData()">���</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100px" align="right">��Ʒ���ƣ�</td>
	      <td><input type="text" id="productname" size="21"></td>
	      <td class="y1" width="100px" align="right">��Ʒ���룺</td>
	      <td><input type="text" id="productcode" size="21"></td>
	      <td class="yx1" align="right">��Ʒ���</td>
      		<td class="yx"><select id="producttype" style="width:117px;">
      			<option value="">---��ѡ��---</option>
      		</select></td>
	     <!-- <td class="y1" width="100px" align="right">EXCEL�ļ���</td>
	      <td><input type="file" name="filename" class="input_readonly"></td> --> 
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/product/base_product_list.jsp"
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
 
</body>
</html>
