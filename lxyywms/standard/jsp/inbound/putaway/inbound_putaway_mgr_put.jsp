<%@ page contentType="text/html; charset=GBK"%>
<%

	
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<style>
html { overflow-y:hidden; }
</style>
<script>
	function OnLoad(){
		parent.UnLockButton();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�ϼܳɹ���");
			}else{
				alert(back_msg);
			}
			//��ȡ�ջ���ID
			var invoiceid = "<%=request.getAttribute("invoiceid")==null?"":request.getAttribute("invoiceid")%>";
			if(invoiceid != "")
			{
				parent.myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction&flag=1&invoiceid=" + invoiceid;
			}
		}
	}
	
</script>
</head>

<body onload="javascript:OnLoad();">
 <table id="puttb" width="150%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST3">
   <tr height="20">
     <td class="TD_LIST_TITLE11" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">���ٺ�</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��װ</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">ë��</div></td> 
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����1</div></td> 
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����2</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����3</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����4</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����5</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����6</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����7</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����8</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����9</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����10</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����11</div></td>
     <td class="TD_LIST_TITLE3" align="center"><div class="list_title">����12</div></td>
     <td class="TD_LIST_TITLE21" align="center"><div class="list_title">����</div></td>
   </tr>


 </table>
 
<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
