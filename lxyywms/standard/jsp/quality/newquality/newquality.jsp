<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>�㽭���������ֿ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script>
    var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
   //��������Ƿ�Ϊ����
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
   function add()
	{
		//��ȡ�������Ϣ
		var result = "";
		var i=-1;
		var j=-1;
		var rows = list.document.getElementById("ty").rows;
		var lineValue;
		var cellValue;
        var inum;
		//��ѭ��,���ڶ���һ�У����Լ�1	
		for (i = 1; i < rows.length-1; i++)
		{
			lineValue = "";
			cellValue = list.ty.rows.item(i).cells.item(0).getElementsByTagName('input')[0].value;
			lineValue = lineValue + cellValue+ ",";
					//�������					
					cellValue = list.ty.rows.item(i).cells.item(8).getElementsByTagName('INPUT')[0].value;
					//�������
					inum = list.ty.rows.item(i).cells.item(7).innerText;
					if(cellValue == null || cellValue.length <1 || !IsNum(cellValue))
					{
						aelrt("��죺"+inum);
	                    aelrt("��棺"+cellValue);
						alert("�ڡ�"+i+"���г����������Ϊ�ջ�ֻ��Ϊ���֣�");
						return false;
					}
					else if(cellValue<1)
					{
						
						alert("�ڡ�"+i+"���г����������С��1��");
						return false;
					}
					else if(inum < cellValue)
					{
         					
					    alert("�ڡ�"+i+"���г���������ڿ��������");
					    return false;					    				
					}
				 lineValue = lineValue + cellValue+ "," ;
		       	 result = result + lineValue+ "|";
			
		}
		var warehouseId = document.getElementById("warehouseid").value;
		var ownerId = document.getElementById("owner_id").value;
		var departId = document.getElementById("departid").value;
		var warehouseManId = document.getElementById("warehousemanid").value;
		var customerId = document.getElementById("customer_id").value;
		var creatTime = document.getElementById("formdate").value;		
		var url =  "&warehouseId="+warehouseId+
		           "&ownerId="+ownerId+
				   "&departId="+departId+
				   "&warehouseManId="+warehouseManId+
				   "&customerId="+customerId+
				   "&creatTime="+creatTime+
				   "&detail="+result;
		if(result.length > 0)
		{
			list.location.href = strUrl+"qualityAction&method=ricoExecAdd" + url;
		}
		else
		{
			alert("��ѡ�����!");
		}
	}
	//��ѯ��ӳ���Ʒ
	function Select(){
	   var warehouseId = document.getElementById("warehouseid").value;
       if(warehouseId == ""){
         alert("��ѡ��ֿ⣡");
         return;
       }
	   var tableObj = list.document.getElementById("ty");
	   result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/quality/newquality/quality_kc_select.jsp?warehouseId='+warehouseId,'','dialogWidth=1000px;dialogHeight=600px');
	   if(result!=null&&result.length>0){
	       for(var i = 0; i < result.length; i++)
	           {
	        		var cs = result[i];
	        		//����һ��
	        		var newRowObj = tableObj.insertRow(tableObj.rows.length-1); 
	        		//ѡ���
	        		var newCheck=newRowObj.insertCell(newRowObj.cells.length);
				   	newCheck.setAttribute( "className", "TD_LIST");
	        		//����
				   	var newWhAreaName=newRowObj.insertCell(newRowObj.cells.length);
				   	newWhAreaName.setAttribute( "className", "TD_LIST");
				   	//��λ
				    var newCargoSpaceName=newRowObj.insertCell(newRowObj.cells.length);
				    newCargoSpaceName.setAttribute( "className", "TD_LIST");
				    //��Ʒ����
				   	var newProductname=newRowObj.insertCell(newRowObj.cells.length);
				   	newProductname.setAttribute( "className", "TD_LIST");
				   	//�������
				    var newInDate=newRowObj.insertCell(newRowObj.cells.length);
				    newInDate.setAttribute( "className", "TD_LIST"); 
				    //��Ʒ״̬
				    var newSstatus=newRowObj.insertCell(newRowObj.cells.length);
				    newSstatus.setAttribute( "className", "TD_LIST");
				    //��λ
				    var newPunit=newRowObj.insertCell(newRowObj.cells.length);
				    newPunit.setAttribute( "className", "TD_LIST");
				    //����
				    var newPnum=newRowObj.insertCell(newRowObj.cells.length);
				    newPnum.setAttribute( "className", "TD_LIST");
				    //�������
				    var newChecknum=newRowObj.insertCell(newRowObj.cells.length);
				    newChecknum.setAttribute( "className", "TD_LIST");
				    	 
				    	        //��ʽ:����,         ��λ����,      ��Ʒ����,      ���ʱ��,  ��Ʒ״̬,       ��λ,         ����,        ��λID         ���ID
				    var checkvaleid = cs[0] + "," + cs[1] + "," + cs[2]+ "," + cs[3]+ "," + cs[4] + "," + cs[5] + "," + cs[6]+ "," +  cs[7] + ","+  cs[8] ;	  
				   	 
				    newCheck.innerHTML		  = 
				    		"<div align='center'><input type='checkbox' class='input_checkbox' name='check_id' value='"+checkvaleid+"'>"+(tableObj.rows.length-2)+"</div>";
				    //newLine.innerHTML         = "<div align='center'>"+(tableObj.rows.length-2)+"</div>"; 
				    newWhAreaName.innerHTML  = "<div align='center'>"+cs[0]+"</div>";
				    newCargoSpaceName.innerHTML = "<div align='center'>"+cs[1]+"</div>";
				    newProductname.innerHTML  = "<div align='center'>"+cs[2]+"</div>";
					newInDate.innerHTML    = "<div align='center'>"+cs[3]+"</div>";
					newSstatus.innerHTML      = "<div align='center'>"+cs[4]+"</div>";
					newPunit.innerHTML      = "<div align='center'>"+cs[5]+"</div>";
					newPnum.innerHTML        = "<div align='center'>"+cs[6]+"</div>";
					newChecknum.innerHTML     = "<div align='center'><input type='text' size='15'></div>";
	        	}
	   }
	}
   //ɾ����
	function DeleteRow()
	{	
		var tableObj = list.document.getElementById("ty");
		if(tableObj.rows.length > 2)
		{
			var obj = list.document.getElementsByName("check_id");
			
			for(var i=obj.length-1;i>=0;i--)
			{
				if(obj[i].checked==true)
				{				
					tableObj.deleteRow(i+1);
					
				}
			}
 			
		}else
		{
			alert("��ѡ����!");
		}
 	}	
	function OnLoad(){
	        //�ֿ�
		    DWREngine.setAsync(false);
		    selectObject("", "warehouseid", "1");	
		    DWREngine.setAsync(true);
			var typevalue = "bmlx";		//�������͵�ֵ	
			selectType("", "departid", typevalue);
			selectType("", "warehousemanid", "ckgly");
		}
	</script>
	
	</head>
	
	<body  onload="javascript:OnLoad();">
	<div class="con_bk" >
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td>
		     <div class="wz" >
		      <div id="dqwz" class="f_l">��ǰλ�ã�<span>�ʼ����</span> &gt;&gt; <span>���</span></div>
		      <div  class="f_r" id="caozuo">
		        <ul>        	          
		          <li class="tubiao3"><a href="#" onclick="DeleteRow();">ɾ��</a></li>
		          <li class="tubiao4"><a href="#" onclick="add();">����</a></li>
		        </ul>
		      </div>
		    </div>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td height="5"></td>
	   </tr>
	 </table>	 			
			<table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
	             <tr>
	             <td class="xx1" colspan="6"><input onClick="Select()" type="button" name="button1" value="ѡ�������" class="BUTTON_STYLE1">
	             </td>
	             </tr>
	             <tr>
	             <td class="yx1" width="100" align="right">�ֿ⣺</td>
	            <td class="yx"> <select name="warehouseid" style="width:117px;">
	               <option value=""></option></select></td>
	                <td class="yx1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</div></td>
	                <td class="yx">
	          	     <input type="text" name="owner_name"  readonly="readonly" class="input_readonly"  style="height:18px;width:100px;"/>
	                 <input type="hidden" name="owner_id" />
	                 <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=1','800','520','owner_id','owner_name');" />
	               </td>
	                <td   class="yx1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</div></td>
	                <td  class="xx1"><div align="left">
	                  <select id="departid" style="width:100px;">
	                    <option value="">--��ѡ��--</option>
	                  </select></div>
	                </td>    
	              </tr>
	                <tr>
	             	<td   class="y1"><div align="right">�ֹ�Ա��</div></td>
	                <td  class="x"><div align="left">
	                  <select id="warehousemanid" style="width:100px;">
	                    <option value="">--��ѡ��--</option>
	                  </select></div>
	                </td>
	                <td   class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</div></td>
	                <td class="x">
	          		<input type="text" name="customer_name"  readonly="readonly" class="input_readonly"  style="height:18px;width:100px;"/>
	                <input type="hidden" name="customer_id" />
	                <input name="moreBtn" type="button" class="button_select" value="��" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=4','800','520','customer_id','customer_name','address');" />
	                </td>
	                <td   class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ڣ�</div></td>
	                 <td>
	          	<input name="formdate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
	                 </td> 
	              </tr>
	            </table> 
	            
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td height="5"></td>
	   </tr>
	 </table>
	 
	     </td>
	   </tr>
	   <tr>
	     <td height="100%"> 
	 
	 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr>
	           <td height="5" class="title">��쵥��Ϣ
	           </td>
	       </tr>
		   <tr>
		     <td height="180">
			   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
		               src="<%=request.getContextPath()%>/standard/jsp/quality/newquality/newquality_list.jsp"></iframe>
			 </td>
		   </tr>
		 </table>
	     </td>
	   </tr>
	 </table>
	
	</div>
	
	<!-- ҳ����ز㣨start�� -->
	 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
	   <div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
	 </div>
	 <!-- ҳ����ز㣨end�� -->  
	
	</body>
	</html>
