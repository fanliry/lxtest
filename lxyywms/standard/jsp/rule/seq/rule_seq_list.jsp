<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseSeq" %>
<html>
<head>
<title>巷道信息</title>
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
		
		var back_msg = "<%=request.getAttribute("back_msg")==null ? "" : (String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">序列号类型</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">序列号描述</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">前缀</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">位数</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">当前序列号</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
	
		BaseSeq seq = null; 
		String seqId;   		//序列ID
    	String seqType; 		//序列类型
    	String seqValue;		//值
    	String seqRemark;		//描述
    	String seqPrefix;		//前缀
    	int icodelength;		//位数
    	
		for(int i=0; i<ls.size(); i++){
			seq = (BaseSeq)ls.get(i); 
                        
			seqId = seq.getSeqId();	
			seqType = seq.getSeqType();		
			seqValue = seq.getSeqValue();	
			seqRemark = seq.getSeqRemark();
			seqPrefix = seq.getSeqPrefix();
			icodelength = seq.getIcodelength();
			
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateData()">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=seqId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=seqType == null ? "" : seqType%></td>
     <td class="TD_LIST" align="center"><%=seqRemark == null ? "" : seqRemark%></td>
     <td class="TD_LIST" align="center"><%=seqPrefix == null ? "" : seqPrefix%></td>
     <td class="TD_LIST" align="center"><%=icodelength%></td>
     <td class="TD_LIST2" align="center"><%=seqValue == null ? "" : seqValue%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="5" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>