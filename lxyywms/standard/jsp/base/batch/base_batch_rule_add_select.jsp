<%@ page contentType="text/html; charset=GBK"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//��������Ƿ�Ϊ����
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	function IsCLetter(str)
	{
		var reNum=/^[A-Z]*$/; 
		return(reNum.test(str)); 
	} 
	function IsLLetter(str)
	{
		var reNum=/^[a-z]*$/; 
		return(reNum.test(str)); 
	}
	function IsRight(str)
	{
		if(str.indexOf("\\") == -1)
		{
			return true;
		}
		return false;
	} 
	var length = 0;
	//ȷ��
 	function OnOk()
 	{
		var backmsg = "";
		var check_ids = document.getElementsByName("check_id");
		var nums = document.getElementsByName("num");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				if(nums[i].value == null && nums[i].value.length < 1)
				{
					alert("��" + (i+1) + "��Χ����Ϊ�գ�");
					return;
				}
				else if(check_ids[i].value == "1")
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						var bem = aem[j].split("-");
						if(bem[0] == null || bem[1] == null || !IsNum(bem[0]) || !IsNum(bem[1]))
						{
							alert("�����֡���Χֵ����");
							return;
						}
						backmsg += "([" + aem[j] + "])|";
					}
				}
				else if(check_ids[i].value == "2")
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						var bem = aem[j].split("-");
						if(bem[0] == null || bem[1] == null || !IsCLetter(bem[0]) || !IsCLetter(bem[1]))
						{
							alert("����д��ĸ����Χֵ����");
							return;
						}
						backmsg += "([" + aem[j] + "])|";
					}
				}
				else if(check_ids[i].value == "3")
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						var bem = aem[j].split("-");
						if(bem[0] == null || bem[1] == null || !IsLLetter(bem[0]) || !IsLLetter(bem[1]))
						{
							alert("��Сд��ĸ����Χֵ����");
							return;
						}
						backmsg += "([" + aem[j] + "])|";
					}
				}
				else
				{
					var aem = nums[i].value.split(",");
					for(var j=0; j<aem.length; j++)
					{
						//if(aem[j].length != length)
						//{
						//	alert("�������ַ������Ȳ�����" + length + "��");
						//	return;
						//}
						//else 
						if(!IsRight(aem[j]))
						{
							alert("�������ַ����в��ܰ�����\\����");
							return;
						}
						backmsg += "" + aem[j] + "|";
					}
				}
			}
		}
		if(backmsg == "")
		{
			alert("��ѡ���¼��");
			return;
		}
		window.returnValue = "^(" + backmsg.substring(0, backmsg.length-1) + ")*$";
		window.close();	
	}
	function OnLoad()
	{
		length = window.dialogArguments;
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">��ǰ��������������</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
    <tr>
      <td class="TD_LIST_TITLE1"><div class="list_title">ѡ��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">��Χ</div></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="1"></td>
      <td class="TD_LIST" align="center">����</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15" value="1-9"></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="2"></td>
      <td class="TD_LIST" align="center">��д��ĸ</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15" value="A-Z"></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="3"></td>
      <td class="TD_LIST" align="center">Сд��ĸ</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15" value="a-z"></td>
    </tr>
    <tr>
      <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="4"></td>
      <td class="TD_LIST" align="center">�����ַ�</td>
      <td class="TD_LIST2" align="center"><input type="text" name="num" size="15"></td>
    </tr>
    <tr>
      <td align="center" colspan="3">
        <input onClick="OnOk()" type="button" name="save" value="ȷ��" class="BUTTON_STYLE1">
        <input onClick="window.close()" type="button" name="report" value="ȡ��" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="10px">
    <tr>
      <td><div style="color: red; font-size:12px;">˵������Χ���á�-���������硰1-9������Χ���ð�ǡ�,���Ÿ������硰1-3,5-7���������ַ��в��ܰ�����\�����硰\C����</div></td>
    </tr>
  </table>
 
</body>
</html>
