<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%
        // --customerid|ownerId,1|2,�ͻ�|����--
	    String groupinfo = (String)request.getAttribute("groupinfo");
	    String aem[] = null;
	    String bem[] = null;
        if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
				
				aem = groupinfo.split(",");
				bem = aem[2].split("\\|");// ������
		}
		
	//���е�������
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//���е�������
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   //new tableSort('table',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%"  id="table" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
<%	
   if(bem != null && bem.length>0){ //��ȡ�����ֶ�
		for(int i=0; i<bem.length; i++){
%>
	     <td class="TD_LIST_TITLE"><div class="list_title"><%=bem[i]%></div></td>
<%
		}
	}else{
%>
		<td class="TD_LIST_TITLE" ><div class="list_title">��Ʒ����</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
          <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
         <td class="TD_LIST_TITLE"><div class="list_title">�ͻ�</div></td> 
         <td class="TD_LIST_TITLE" ><div class="list_title">���ݱ��</div></td> 
         <td class="TD_LIST_TITLE" ><div class="list_title">��������</div></td>
          <td class="TD_LIST_TITLE" ><div class="list_title">��ҵ��������</div></td>
<%	
    }		
%>
	
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
   </tr>
<%
	
	
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
		String createTime = null;   //��������
		double pnum;            	//����
		double volume;          	//���
		double pweight;         	//����
     	double pnetweight;      	//����

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
			ob = (Object[])ls.get(i);
  	 		if(bem != null && bem.length>0){ //����֮��
  	 		    pnum = Double.parseDouble(ob[bem.length].toString());			    //����
  	 		    volume = Double.parseDouble(ob[bem.length+1].toString());		    //���
  	 		    pweight = Double.parseDouble(ob[bem.length+2].toString());        	//ë��
  	 		    pnetweight = Double.parseDouble(ob[bem.length+3].toString());     	//����
  	 		}else{ //û�з���
  	 			
  	 		    pnum = Double.parseDouble(ob[8].toString());			//����
  	 		    volume = Double.parseDouble(ob[9].toString());		    //���
  	 		    pweight = Double.parseDouble(ob[10].toString());        	//ë��
  	 		    pnetweight = Double.parseDouble(ob[11].toString());     	//����
  	 		}
 		
			%>
			   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
			     <td class="TD_LIST1" align="center"><%=i+1%></td>
			<%	
			   if(bem != null && bem.length>0){ //��ȡ�����ֶ�
					for(int j=0; j<bem.length; j++){
			%>
				     <td class="TD_LIST"  align="center"><%=ob[j] == null ? "" : ob[j].toString()%></td>
			<%
					}
				}else{ //��ȡĬ��
			%>
			         <td class="TD_LIST"  align="center"><%=ob[0] == null ? "" : ob[0].toString()%></td>  
			         <td class="TD_LIST"  align="center"><%=ob[1] == null ? "" : ob[1].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[2] == null ? "" : ob[2].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[3] == null ? "" : ob[3].toString()%></td>  
			         <td class="TD_LIST"  align="center"><%=ob[4] == null ? "" : ob[4].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[5] == null ? "" : ob[5].toString()%></td> 
			          <td class="TD_LIST"  align="center"><%=ob[6] == null ? "" : ob[6].toString()%></td> 
			         <td class="TD_LIST"  align="center"><%=ob[7] == null ? "" : ob[7].toString()%></td>
			<%	
			    }		
			%>
				 
			     <td class="TD_LIST" align="center"><%=pnum%></td>
			     <td class="TD_LIST" align="center"><%=volume%></td>
			     <td class="TD_LIST" align="center"><%=pweight%></td>
			     <td class="TD_LIST" align="center"><%=pnetweight%></td>
			   </tr>
			<%
		}
     }else{
		session.removeAttribute("paging");
	 }
%>
   <tr>
     <td height="100%" colspan="<%=4 + (bem!=null&&bem.length>0?bem.length:3)%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
