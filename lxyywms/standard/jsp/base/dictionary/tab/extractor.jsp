<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.Extractor"%>
<%@ page import="com.ricosoft.common.pagination.PagingTool"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
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
		//����  object ����� field ���б����ֶ�  url ��תҳ��
		function add()
		{
			var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=createCode&url=/standard/jsp/base/dictionary/tab/extractorAdd.jsp&object=Extractor&field=m_extractorNumber",'','dialogWidth=350px;dialogHeight=200px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		window.location.href="<%=request.getContextPath()%>/"+result;
		   	}
		}
			//��ѯ
		function search()
		{
		    	document.myform.action="<%=request.getContextPath()%>/BMSService?actionCode=extractor";	
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
						window.location.href='<%=request.getContextPath()%>/BMSService?actionCode=delete&deleteStr='+deleteName+'&object=Extractor&id=m_extractorId';
					}
		        }
				
		}
		//�޸�
		function update()
		{
			var checkRole =  getDelCheckName();
			if(checkRole != null && checkRole.length > 0)
			{
				var strRoleIdLeng = checkRole.split(",");
				if( strRoleIdLeng.length == 2)
				{
	 					alert("��Ϣ!");
				}else
				{
					alert("ֻ��ѡ��һ����Ϣ!");
				}
			}        
			else{
				alert("��ѡ����Ҫ�޸ĵ���Ϣ");
			}
		}
		function updateUser(param){
		   		var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/extractorUpd.jsp";
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
<form method="post" action="" name ="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="2%" height="27">&nbsp;</td>
     <td width="98%" class="font_006699_bold_12"><DIV class=font_006699_bold_12>��ǰλ�ã�������Ϣ -&gt; �ֵ����� -&gt; ���Ա����</DIV>        </td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_STYLE1">
   <tr>
     <td width="17%" height="25" class="TD_STYLE1"><div align="right">���Ա��ţ�</div></td>
     <td width="35%" class="TD_STYLE1"><div align="left">
       <input type="text" name="number" id="number"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
     <td width="11%" class="TD_STYLE1"><div align="right">���Ա��</div></td>
     <td width="37%" class="TD_STYLE1"><div align="left">
       <input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" class="TD_STYLE1"><div align="center">
       <input onclick="add()" type="submit" name="Submit" value="����" class="BUTTON_STYLE1">
       &nbsp; 
       <input onclick="deleteCheck()"type="button" name="button" value="ɾ��" class="BUTTON_STYLE1">
       &nbsp; 
       <input onclick="search()"type="button" name="button" value="��ѯ" class="BUTTON_STYLE1">
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
     <input  class="input_checkbox" type="checkbox" name="check_all" value="checkbox" onclick="selectAll();">ȫѡ
     </div></td>
     <td class="TD_title1"><div align="center">���Ա���</div></td>
     <td class="TD_title1"><div align="center">���Ա����</div></td>
     <td class="TD_title1"><div align="center">����</div></td>
   </tr>
    <%
   		List lsExt = (List)request.getAttribute("exResultList");
		if(lsExt!=null)
		{
	    	for(int i = 0; i < lsExt.size(); i++) 
	    	{
	    	Extractor ext = (Extractor)lsExt.get(i);
	    	String  strExtId     = ext.getM_extractorId();
	    	String  strExtName   = ext.getM_extractorName();
	    	String  strExtNumber  = ext.getM_extractorNumber();
	    	String param="id="+strExtId+"&name="+strExtName+"&number="+strExtNumber;
   %>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_STYLE1"><div align="center">
       <input name="roleId" type="checkbox" value="<%=strExtId%>"  class="input_checkbox">
     </div></td>
     <td class="TD_STYLE1"><div align="center"><%=strExtNumber%></div></td>
     <td class="TD_STYLE1"><div align="center"><%=strExtName%></div></td>
     <td class="TD_LIST"><div align="center"><input type="button" name="button3" value="�޸�" class="BUTTON_STYLE1" onclick="updateUser('<%=param%>');"></div></td>
   </tr><%}}%>
   <tr>
     <td height="100%" colspan="7" class="TD_LIST"></td>
   </tr>
 </table>
 </form>
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="98%" height="30" class="font_006699_12">
					<%PagingTool pt = null;
					int iCountRow = 0;
					int iMaxRow = 10;
					int iCurrentPage = 1;
					int iCountPage = 1;
					if (request.getAttribute("pagingTool") != null) {
						pt = (PagingTool) request.getAttribute("pagingTool");
					}
					if (pt != null) {
						iCountRow = pt.getM_iCountRow();
						iMaxRow = pt.getM_iMaxRow();
						iCurrentPage = pt.getM_iCurrentPage();
						iCountPage = pt.getM_iCountPage();
					}
					if (iCountPage > 1) {%>
					<div align="right">
						����[<%=iCountRow%>]�� ÿҳ[10]�� 
						��ǰ��[<%=iCurrentPage%>]ҳ
						��[<%=iCountPage%>]ҳ 
						�� <A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=1">��ҳ</A>����<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage - 1) > 0 ? (iCurrentPage - 1) : 1%>">��һҳ</A>��
						��<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage + 1) > iCountPage ? iCountPage : (iCurrentPage + 1)%>">��һҳ</A>��
						��<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%=iCountPage%>">ĩҳ</A>��������
						<input name="pageId" type=text class=input1 size=1>ҳ&nbsp;&nbsp;
						<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0 onclick="toPagingRow(<%=iCountPage%>);">&nbsp;&nbsp;
					</div>
					<%} else {%>
					<div align="right">
						����[<%=iCountRow%>]�� ÿҳ[10]�� 
						��ǰ��[1]ҳ ��[1]ҳ����ҳ��
						����һҳ������һҳ����ĩҳ��������
						<input name=page type=text class=input1 size=1>ҳ&nbsp;&nbsp;
						<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0>&nbsp;&nbsp;
					</div>
					<%}%>
				</td>
			</tr>
		</table>
	</body>
</html>
