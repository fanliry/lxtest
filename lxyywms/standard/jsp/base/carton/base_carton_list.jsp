<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCarton" %>
<html>
<head>
<title>�ͻ���Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

	//ȫѡ
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//�ı䱳��ɫ
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//���ö�ѡ���Ƿ�ѡ��
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		
	}
	
	function OnLoad(){
		parent.page.location.reload();
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr style="position:relative;top:expression(this.offsetParent.scrollTop);">
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">�к�</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">װ�����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">װ������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">װ������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">��</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">�������</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">װ��ٷֱ�</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">��ע</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		BaseCarton carton = null; 
		
		String cartonid;		//װ�����
		String cartontype;		//װ������
		String descr;			//װ������
		double boxleng;			//��
		double boxwidth;		//��
		double boxheight;		//��
		double maxcube;			//������
		double maxweight;		//�������
		double maxcount;		//�������
		double selfweight;		//����
		double cartonpercent;	//װ��ٷֱ�
		String remark;			//��ע

		for(int i=0; i<ls.size(); i++){
			carton = (BaseCarton)ls.get(i); 
                        
			cartonid = carton.getCartonid();		//װ�����	
			cartontype = carton.getCartontype();	//װ������
			descr = carton.getDescr();				//װ������
			boxleng = carton.getBoxleng();			//��
			boxwidth = carton.getBoxwidth();		//��
			boxheight = carton.getBoxheight();		//��
			maxcube = carton.getMaxcube();			//������
			maxweight = carton.getMaxweight();		//�������
			maxcount = carton.getMaxcount();		//�������
		    selfweight = carton.getSelfweight();	//����
		    cartonpercent = carton.getCartonpercent();	//װ��ٷֱ�
		    remark = carton.getRemark();			//��ע
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData()">
     <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" class="input_checkbox" value="<%=cartonid%>" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=cartonid == null ? "" : cartonid%></td>
     <td class="TD_LIST" align="center"><%=cartontype == null ? "" : cartontype%></td>
     <td class="TD_LIST" align="center"><%=descr == null ? "" : descr%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(boxleng)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(boxwidth)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(boxheight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(maxcube)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(maxweight)%></td>
     <td class="TD_LIST" align="center"><%=(int)maxcount%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(selfweight)%></td>
     <td class="TD_LIST" align="center"><%=nbf.format(cartonpercent)%></td>
     <td class="TD_LIST2" align="center"><%=remark == null ? "" : remark%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="13" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
 </table>
 
</body>
</html>