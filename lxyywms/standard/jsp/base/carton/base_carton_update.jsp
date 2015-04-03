<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCarton" %>
<%
	BaseCarton carton = (BaseCarton)request.getAttribute("carton");  
	String cartonid = carton.getCartonid();			//װ�����
	String cartontype = carton.getCartontype();		//װ������
	String descr = carton.getDescr();				//װ������
	double boxleng = carton.getBoxleng();			//��
	double boxwidth = carton.getBoxwidth();			//��
	double boxheight = carton.getBoxheight();		//��
	double maxcube = carton.getMaxcube();			//������
	double maxweight = carton.getMaxweight();		//�������
	double maxcount = carton.getMaxcount();			//�������
	double selfweight = carton.getSelfweight();		//����
	double cartonpercent = carton.getCartonpercent();//װ��ٷֱ�
	String remark = carton.getRemark();				//��ע
%>
<html>
<head>
<title>�޸���ת����Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
<!--
	//������ת����Ϣ
	function OnOk(){
		var cartonid = document.getElementById("cartonid").value;
		var descr = document.getElementById("descr").value;			//װ������
		var boxleng = document.getElementById("boxleng").value;		//��
		var boxwidth = document.getElementById("boxwidth").value;	//��
		var boxheight = document.getElementById("boxheight").value;	//��
		var maxcube = document.getElementById("maxcube").value;		//������
		var maxweight = document.getElementById("maxweight").value;	//�������
		var maxcount = document.getElementById("maxcount").value;	//�������
		var selfweight = document.getElementById("selfweight").value;	//����
	    var cartonpercent = document.getElementById("cartonpercent").value;	//װ��ٷֱ�
	    var remark = document.getElementById("remark").value;		// ��ע
		
		//װ������
		if(descr == null || descr.length <1)
		{
			alert("��װ������������Ϊ��!");
			return;
		}else{
			if(strlen(descr) > 100){
				alert("��װ������������!");
				return;
			}
		}
		//��
		if(boxleng != null && boxleng.length > 0 && boxleng != 0)
		{
			if(!isDig(boxleng))
			{
				alert("������ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��
		if(boxwidth != null && boxwidth.length > 0 && boxwidth != 0)
		{
			if(!isDig(boxwidth))
			{
				alert("����ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��
		if(boxheight != null && boxheight.length > 0 && boxheight != 0)
		{
			if(!isDig(boxheight))
			{
				alert("���ߡ�ֻ��Ϊ����������0��");
				return;
			}
		}
		//������
		if(maxcube != null && maxcube.length > 0 && maxcube != 0)
		{
			if(!isDig(maxcube))
			{
				alert("����������ֻ��Ϊ����������0��");
				return;
			}
		}
		//�������
		if(maxweight != null && maxweight.length > 0 && maxweight != 0)
		{
			if(!isDig(maxweight))
			{
				alert("�����������ֻ��Ϊ����������0��");
				return;
			}
		}
		//�������
		if(maxcount!=null && maxcount.length>0 && maxcount != 0)
		{
			if(!numChar(maxcount)){
				alert("���������������������!");
				return;
			}
		}
		//����
		if(selfweight != null && selfweight.length > 0 && selfweight != 0)
		{
			if(!isDig(selfweight))
			{
				alert("�����ء�ֻ��Ϊ����������0��");
				return;
			}
		}
		//װ��ٷֱ�
		if(cartonpercent != null && cartonpercent.length > 0 && cartonpercent != 0 && cartonpercent > 1)
		{
			if(!isDig(cartonpercent))
			{
				alert("��װ��ٷֱȡ�ֻ��ΪС��1������������0��");
				return;
			}
		}
		//��ע
		if(remark!=null && remark.length>0)
		{
			if(strlen(remark) > 50){
				alert("����ע������!");
				return;
			}
		}

		var back_msg = "&cartonid=" + cartonid + "&descr=" + descr + "&boxleng=" + boxleng + "&boxwidth=" + boxwidth + "&boxheight=" + boxheight
			 + "&maxcube=" + maxcube + "&maxweight=" + maxweight + "&maxcount=" + maxcount + "&selfweight=" + selfweight
			 + "&cartonpercent=" + cartonpercent + "&remark=" + remark;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	}

-->
</script>
</head>

<body>
 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��ת�� -&gt; �޸���ת����Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>װ��������</td>
      <td class="xx1" colspan="3">
      	<input type="hidden" id="cartonid" value="<%=cartonid%>"><input type="text" id="descr" maxlength="100" size="35" value="<%=descr%>">
      </td>
    </tr> 
    <tr>
      <td class="yx1" align="right">����</td>
      <td class=yx><input type="text" id="boxleng" maxlength="14" size="14" value="<%=boxleng%>"></td>
      <td width="100px" class="yx1" align="right">��������</td>
      <td class="xx1"><input type="text" id="maxcube" maxlength="14" size="14" value="<%=maxcube%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">��</td>
      <td class="yx"><input type="text" id="boxwidth" maxlength="14" size="14" value="<%=boxwidth%>"></td>
      <td class="yx1" align="right">���������</td>
      <td class="xx1"><input type="text" id="maxweight" maxlength="14" size="14" value="<%=maxweight%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�ߣ�</td>
      <td class="yx"><input type="text" id="boxheight" maxlength="14" size="14" value="<%=boxheight%>"></td>
      <td class="yx1" align="right">���������</td>
      <td class="xx1"><input type="text" id="maxcount" maxlength="14" size="14" value="<%=(int)maxcount%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">���أ�</td>
      <td class="yx"><input type="text" id="selfweight" maxlength="14" size="14" value="<%=selfweight%>"></td>
      <td class="yx1" align="right">װ��ٷֱȣ�</td>
      <td class="xx1"><input type="text" id="cartonpercent" maxlength="14" size="14" value="<%=cartonpercent%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">��ע��</td>
      <td class="xx1" colspan="3"><input type="text" id="remark" maxlength="50" size="35"value="<%=remark==null?"":remark%>"></td>
    </tr>
    <tr>
      <td class="TD_MGR_TOP" align="center" colspan="4">
       <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
       <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>