<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseSeq" %>
<%
	BaseSeq seq = (BaseSeq)request.getAttribute("seq");  
	String seqId = seq.getSeqId();   		//����ID
 	String seqType = seq.getSeqType(); 		//��������
  	String seqValue = seq.getSeqValue();	//ֵ
 	String seqRemark = seq.getSeqRemark();	//����
  	String seqPrefix = seq.getSeqPrefix();	//ǰ׺
  	int icodelength = seq.getIcodelength();	//λ��
%>
<html>
<head>
<title>�޸����к���Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	//�����޸����к���Ϣ
	function OnOk(){
		var seqType = document.getElementById("seqType").value;		//��������
		var seqRemark = document.getElementById("seqRemark").value;	//����
		var seqPrefix = document.getElementById("seqPrefix").value;	//ǰ׺
		var icodelength = document.getElementById("icodelength").value;	//λ��
		var seqValue = document.getElementById("seqValue").value;	//ֵ
		
		if(seqRemark!=null && seqRemark.length>0){
			if(strlen(seqRemark) > 32){
				alert("������������!");
				return;
			}
		}
		
		if(seqPrefix == null || seqPrefix.length <1){
			alert("��ǰ׺������Ϊ��!");
			return;
		}
		
		if(icodelength == null || icodelength.length <1){
			alert("��λ��������Ϊ��!");
			return;
		}else{
			if(!numChar(icodelength)){
				alert("��λ����ֻ��Ϊ��������");
				return;
			}
		}

		var back_msg = "&seqId=<%=seqId%>" + "&seqType=" + seqType + "&seqRemark=" + seqRemark + "&seqPrefix=" + seqPrefix
			 + "&icodelength=" + icodelength + "&seqValue=" + seqValue;
		
    	window.returnValue = back_msg;
    	window.close();
	}
-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; ���к� -&gt; �޸����к���Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>     
      <td width="120px" class="yx1" align="right">���ͣ�</td>
      <td class="xx1"><input type="text" id="seqType" value="<%=seqType==null?"":seqType%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right">������</td>
      <td class="xx1"><input type="text" id="seqRemark" value="<%=seqRemark==null?"":seqRemark%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>ǰ׺��</td>
      <td class="xx1">
        <input type="text" id="seqPrefix" value="<%=seqPrefix==null?"":seqPrefix%>">
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>λ����</td>
      <td class="xx1">
        <input type="text" id="icodelength" value="<%=icodelength%>">
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right">��ǰֵ��</td>
      <td class="xx1">
        <input type="text" id="seqValue" value="<%=seqValue==null?"":seqValue%>" class="input_readonly" readonly>
      </td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>