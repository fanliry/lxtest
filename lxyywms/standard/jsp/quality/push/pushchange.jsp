<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime = StrTools.getCurrDateTime(8);
	
	HashMap hsPopedom = null;
	boolean pushChangeComfirm = false; 

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("pushChangeComfirm") != null){
			pushChangeComfirm = true;
		}
    }
%>

<html>
<head>
<title>����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/md5.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	function onload()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}
			else{
				alert(back_msg);
			}
		}
	}

	function OK()
	{
		var productId = document.getElementById("package_id").value;	//Ʒ��
		var lotinfo = document.getElementById("lotinfo").value;		//����
		var Qualityid = document.getElementById("Qualityid").value;		//�ʼ쵥��

		if(productId == null || productId.length <1)
		{
			alert("Ʒ������Ϊ��!");
			return;
		}
		
		if(lotinfo == null || lotinfo.length <1)
		{
			alert("���Ų���Ϊ��!");
			return;
		}
		if(Qualityid == null || Qualityid.length <1)
		{
			alert("�ʼ쵥�Ų���Ϊ��!");
			return;
		}
		
		window.location.href="<%=request.getContextPath()%>/BMSService?actionCode=QualityNewAction&method=ricoExecPush&productId="+productId+
							"&lotinfo="+lotinfo+"&Qualityid="+Qualityid;
	}
-->
</script>
</head>

<body onload="onload();">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="5">
   <div class="wz">
	  <div id="dqwz" class="f_l">��ǰλ�ã�<span>�������� &gt;&gt; ����</span></div>
	  </div>
</td>
   </tr>
  <tr>
    <td valign="top" colspan="3">
	
	
	<table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
      <tr><td></td></tr>
    </table>
	
	
	 
	 <!-- ======== �޸����� ======== -->
	 <form name="myform" method="post">
	   
		<table align="center" border="0" cellpadding="1" cellspacing="1" width="400" class="TABLE_ADD" >
		    <tr class="add_tr">
		    	<td height="30" class="xx1" colspan=2 ><div align="center">����</div></td>
		    </tr>
		    <tr>
     	  <td class="yx1" align="right">Ʒ����</td>
	      <td class="yx">
            	<input type="hidden" name="package_id"><input type="text" name="package_name" readonly>
          		<input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select"><font color="red"> *</font>
	      </td>
		    </tr>
			<tr>
			    <td class="yx1"><div align="right">���ţ�</div></td>
			    <td class="yx">
				<input type="text" name="lotinfo" >&nbsp;<font color="red">*&nbsp;</font></td>
			</tr>
			<tr>	
				<td class="yx1" ><div align="right">�ʼ쵥�ţ�</div></td>
			    <td class="yx" >
				<input type="text" name="Qualityid" >&nbsp;<font color="red">*&nbsp;</font></td>
		    </tr>
		    <tr>
			  <td class="xx1" align="center" colspan=2 >
				<div align="center">
		          <%if(pushChangeComfirm){%><input type="button" name="saveBtn" value="ȷ�� " class="button_style" onclick="OK();">&nbsp;&nbsp;&nbsp;
		          <input type="reset" name="resetBtn" value="����" class="button_style"><%}%>
        </div>
			  </td>
			</tr>
		    <tr>
			  <td  align="center" colspan=2 ><font color="red">ע:*Ϊ��������</font></td>
			</tr>
			
	    </table>													
	    
	 </form>
	 <!-- ======== �޸����� ======== -->
	 
	
	</td>
    <td width="9" background="<%=request.getContextPath()%>/standard/images/wds_main_03.gif"></td>
  </tr>
</table>
</body>
</html>
