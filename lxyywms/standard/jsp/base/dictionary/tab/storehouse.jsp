<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.StoreHouseAdminster"%>
<%@ page import="com.ricosoft.common.pagination.PagingTool" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link href="<%=basePath%>standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>standard/style/css/table.css" rel="stylesheet" type="text/css">
<script  src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--


//���ѡ���ѡ����ֵ
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
	//ȫѡ
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
	 function toPagingRow(pageCount)
	{
		
		 var page;
		 page = 0;
		 page = document.getElementById("pageId").value;	 
		
		 if( 0< page && page <= pageCount&& numChar(page))
		{
			  alert('<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page='+page);
			window.location.href= '<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page='+page;
		}
	}

	//����  object ����� field ���б����ֶ�  url ��תҳ��
	function add()
	{
		var result = showModalDialog('<%=request.getContextPath()%>/BMSService?actionCode=createCode&url=/standard/jsp/base/dictionary/tab/storehouseAdd.jsp&object=StoreHouseAdminster&field=m_storeHouseNumber','','dialogWidth=300px;dialogHeight=150px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		window.location.href="<%=request.getContextPath()%>/"+result;
	   	}
	}
	//��ѯ
	function search()
	{
	    	document.myform.action="<%=basePath%>/BMSService?actionCode=storeHouseAdminster";	
	    	myform.submit();
	}
	
	//ɾ��
		function deleteCheck()
		{     
		        var deleteName = getDelCheckName();   
		        
		        if(deleteName == null||deleteName.length <1){
		        	alert("��ѡ����Ҫɾ������Ϣ");
		        }  
		        else{
					var del = confirm("ȷ��ɾ����ѡ��Ϣ")
					if(del){
							window.location.href='<%=request.getContextPath()%>/BMSService?actionCode=delete&deleteStr='+deleteName+'&object=StoreHouseAdminster&id=m_storeHouseAdminsterId';
					}
		        }
		}
	//�޸�
	 function updateUser(param){
	   		var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/storehouseUpd.jsp";
	   		strUrl = strUrl +"?" + param;	
	   		var  result = showModalDialog(strUrl,'','dialogWidth=300px;dialogHeight=150px');
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
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="2%" height="27">&nbsp;</td>
     <td width="98%" class="font_006699_bold_12">��ǰλ�ã�������Ϣ -&gt; �ֵ����� -&gt; �ֿ����Ա����</td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_STYLE1">
   <tr>
     <td height="25" class="TD_STYLE1"><div align="right">�ֹ�Ա��ţ�</div></td>
     <td class="TD_STYLE1"><div align="left">
       <input type="text" name="number" id="number"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
     <td class="TD_STYLE1"><div align="right">���ƣ�</div></td>
     <td class="TD_STYLE1"><div align="left">
       <input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" class="TD_STYLE1"><div align="center">
       <input onclick="add()" type="button" name="Submit" value="����" class="BUTTON_STYLE1">
       &nbsp; 
       <input onclick="deleteCheck()" type="button" name="Submit" value="ɾ��" class="BUTTON_STYLE1">
       &nbsp;  
       <input onclick="search()" type="button" name="button" value="��ѯ" class="BUTTON_STYLE1">
     </div></td>
    </tr>
 </table>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="20"></td>
   </tr>
 </table>
 
 <table width="98%" height="60%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">

   <tr>
     <td width="10%" class="TD_title1"><div align="center">
       <input type="checkbox" name="check_all" value="checkbox" onclick="selectAll();"  class="input_checkbox">ȫѡ
     </div></td>
     <td class="TD_title1"><div align="center">�ֹ�Ա���</div></td>
     <td class="TD_title1"><div align="center">�ֹ�Ա����</div></td>
     <td class="TD_title1"><div align="center">�� ��</div></td>
      <td class="TD_title1"><div align="center">����</div></td>
   </tr>
   
    <%
   		List lsSort = (List)request.getAttribute("exResultList");
		if(lsSort!=null)
		{
	    	for(int i = 0; i < lsSort.size(); i++) 
	    	{
	    	StoreHouseAdminster sort = (StoreHouseAdminster)lsSort.get(i);
	    	String  strStoreHouseId     = sort.getM_storeHouseAdminsterId();
	    	String  strStoreHouseNumber = sort.getM_storeHouseNumber();
	    	String  strStoreHouseName   = sort.getM_storeHouseName();
	    	String  strStoreUser  = sort.getM_describe();
	    	
	    	if(strStoreHouseId == null||strStoreHouseId.trim().length() < 1){
			 		strStoreHouseId = " ";}
			if(strStoreHouseNumber == null||strStoreHouseNumber.trim().length() < 1){
			 		strStoreHouseNumber = " ";}
			if(strStoreHouseName == null||strStoreHouseName.trim().length() < 1){
			 		strStoreHouseName = " ";}
			if(strStoreUser == null||strStoreUser.trim().length() < 1){
			 		strStoreUser = " ";}	
	    	String param="id="+strStoreHouseId+"&number="+strStoreHouseNumber+"&name="+strStoreHouseName+"&use="+strStoreUser;
   %>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST"><div align="center">
      <input name="roleId" type="checkbox" value="<%=strStoreHouseId%>"  class="input_checkbox">
     </div></td>
     <td class="TD_LIST"><div align="center"><%=strStoreHouseNumber%></div></td>
     <td class="TD_LIST"><div align="center"><%=strStoreHouseName%></div></td>
     <td class="TD_LIST"><div align="center"><%if(strStoreUser.equals("0")){%>������<%}else{%>����<%}%></div></td>
     <td class="TD_LIST"><div align="center"><input type="button" name="button3" value="�޸�" class="BUTTON_STYLE1" onclick="updateUser('<%=param%>');"></div></td>
   </tr>
   
   <%
     }
   }
   %>
   
   <tr>
     <td height="100%" colspan="7" class="TD_LIST"></td>
   </tr>
   
 </table>
  </form>
 <table width="98%" align="center"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="98%" height="30" class="font_006699_12">
<%
	PagingTool pt = null; 
	int iCountRow = 0;
	int iMaxRow = 10;
	int iCurrentPage = 1;
	int iCountPage = 1;
	if(request.getAttribute("pagingTool") != null)
	{
		pt = (PagingTool)request.getAttribute("pagingTool"); 
	}
	if(pt != null)
	{
		iCountRow = pt.getM_iCountRow();
		iMaxRow = pt.getM_iMaxRow();
		iCurrentPage = pt.getM_iCurrentPage();
		iCountPage = pt.getM_iCountPage();
	}
	if(iCountPage > 1)
	{
%>
		<div align="right">

����[<%=iCountRow%>]�� ÿҳ[10]�� ��ǰ��[<%=iCurrentPage%>]ҳ ��[<%=iCountPage%>]ҳ ��
<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=1">��ҳ</A>����<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage - 1) > 0 ? (iCurrentPage - 1) : 1%>">��һҳ</A>����<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage + 1) > iCountPage ? iCountPage : (iCurrentPage + 1)%>">��һҳ</A>�� 
��<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%=iCountPage%>">ĩҳ</A>��������<input name="pageId" type=text class=input1 size=1 > ҳ&nbsp;&nbsp;<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0 onclick="toPagingRow(<%=iCountPage%>);">&nbsp;&nbsp;</div>	
<%	
	}else
	{
%>
			<div align="right">
����[<%=iCountRow%>]�� ÿҳ[10]�� ��ǰ��[1]ҳ ��[1]ҳ����ҳ������һҳ������һҳ����ĩҳ��
������<input name=page type=text class=input1 size=1 > ҳ&nbsp;&nbsp;<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0>&nbsp;&nbsp;</div>
			
<%	
	}	
%>
     </td>
   </tr>
 </table>
</body>
</html>
