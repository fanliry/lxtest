<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCarton" %>
<html>
<head>
<title>客户信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//设置多选框是否选中
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
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">装箱代码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">装箱类型</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">装箱描述</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">长</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">宽</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">高</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">最大体积</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">最大重量</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">最大数量</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">自重</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">装箱百分比</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">备注</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		BaseCarton carton = null; 
		
		String cartonid;		//装箱代码
		String cartontype;		//装箱类型
		String descr;			//装箱描述
		double boxleng;			//长
		double boxwidth;		//宽
		double boxheight;		//高
		double maxcube;			//最大体积
		double maxweight;		//最大重量
		double maxcount;		//最大数量
		double selfweight;		//自重
		double cartonpercent;	//装箱百分比
		String remark;			//备注

		for(int i=0; i<ls.size(); i++){
			carton = (BaseCarton)ls.get(i); 
                        
			cartonid = carton.getCartonid();		//装箱代码	
			cartontype = carton.getCartontype();	//装箱类型
			descr = carton.getDescr();				//装箱描述
			boxleng = carton.getBoxleng();			//长
			boxwidth = carton.getBoxwidth();		//宽
			boxheight = carton.getBoxheight();		//高
			maxcube = carton.getMaxcube();			//最大体积
			maxweight = carton.getMaxweight();		//最大重量
			maxcount = carton.getMaxcount();		//最大数量
		    selfweight = carton.getSelfweight();	//自重
		    cartonpercent = carton.getCartonpercent();	//装箱百分比
		    remark = carton.getRemark();			//备注
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
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
 
</body>
</html>