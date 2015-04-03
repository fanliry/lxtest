<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace" %>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
  <!--
  	//设置单选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		
		//改变背景色
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	function OnLoad(){
		parent.RemoveLoading();
	}
  -->
</script>
</head>

<body onLoad="OnLoad()">
  
  <table width="180%" height="90%"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50" nowrap><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">库位</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">所属仓库</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">所属库区</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">货位状态</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">库位使用</div></td>
      <td class="TD_LIST_TITLE" width="70" nowrap><div class="list_title">存储类型</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">上架类型</div></td>
      <td class="TD_LIST_TITLE" width="100" nowrap><div class="list_title">拣选类型</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">长度</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">宽度</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">高度</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">体积</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">承重</div></td>
      <td class="TD_LIST_TITLE" width="80" nowrap><div class="list_title">可放托盘数</div></td>
      <td class="TD_LIST_TITLE" width="50" nowrap><div class="list_title">层数</div></td>
      <td class="TD_LIST_TITLE2" width="100" nowrap><div class="list_title">环境</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		BaseCargospace cargospace = null; 
		
		String cargoSpaceId;		// 货位ID
		String cargoSpaceName;		// 货位名称
		String warehousename;		// 仓库名称
		String whAreaName;			// 库区名称
		String csstatus;			// 货位状态
		String usagetype;   		// 库位使用
		String storagetype;			// 存储类型
		String puttype;				// 上架类型
		String picktype;			// 拣选类型
		
		double length;				// 长
		double width;				// 宽
		double height;				// 高
		double volume;				// 体积
		double loadweight;			// 承重
		Integer palletnum;			// 可放托盘数
		Integer layernum;			// 层数
		String environment;			// 环境
	    
	    String strId = "";
		
		for(int i=0; i<ls.size(); i++){
			cargospace = (BaseCargospace)ls.get(i); 
                        
			cargoSpaceId = cargospace.getCargoSpaceId();		// 货位ID
			cargoSpaceName = cargospace.getCargoSpaceName();	// 货位名称
			warehousename = cargospace.getWarehousename();		// 仓库名称
			whAreaName = cargospace.getWhAreaName();			// 库区名称
			csstatus = cargospace.getCsstatusname();			// 货位状态
			
			usagetype = cargospace.getUsagetypename();   		// 库位使用
			storagetype = cargospace.getStoragetypename();		// 存储类型
			puttype = cargospace.getPuttypename();				// 上架类型
			picktype = cargospace.getPicktypename();			// 拣选类型
		
			length = cargospace.getLength();					// 长
			width = cargospace.getWidth();						// 宽
			height = cargospace.getHeight();					// 高
			volume = cargospace.getVolume();					// 体积
			loadweight = cargospace.getLoadweight();			// 承重
			palletnum = cargospace.getPalletnum();				// 可放托盘数
			layernum = cargospace.getLayernum();				// 层数
			environment = cargospace.getEnvironment();			// 环境
			
			strId = cargoSpaceId + "|"+ cargoSpaceName + "|";
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=strId%>');">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=strId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=cargoSpaceName == null ? "" : cargoSpaceName%></td>
     <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
     <td class="TD_LIST" align="center"><%=whAreaName == null ? "" : whAreaName%></td>
     <td class="TD_LIST" align="center"><%=csstatus == null ? "" : csstatus%></td>
     <td class="TD_LIST" align="center"><%=usagetype == null ? "" : usagetype%></td>
     <td class="TD_LIST" align="center"><%=storagetype == null ? "" : storagetype%></td>
     <td class="TD_LIST" align="center"><%=puttype == null ? "" : puttype%></td>
     <td class="TD_LIST" align="center"><%=picktype == null ? "" : picktype%></td>
     <td class="TD_LIST" align="center"><%=length%></td>
     <td class="TD_LIST" align="center"><%=width%></td>
     <td class="TD_LIST" align="center"><%=height%></td>
     <td class="TD_LIST" align="center"><%=volume%></td>
     <td class="TD_LIST" align="center"><%=loadweight%></td>
     <td class="TD_LIST" align="center"><%=palletnum%></td>
     <td class="TD_LIST" align="center"><%=layernum%></td>
     <td class="TD_LIST" align="center"><%=environment == null ? "" : environment%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="20" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </table>

 <!-- ======== 分页标签 ======== -->

	<div style="height:25px">
	  <%@ include file="commpage.jsp"%>
    </div>

</body>
</html>
