<%@page import="com.wms3.bms.standard.entity.outerInterface.SaleForm"%>
<%@page import="com.wms3.bms.standard.entity.outerInterface.SaleFormDetail"%>
<%@page import="java.util.List"%>
<%@ page import="java.lang.reflect.Field" %>
<%@ page contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>���ⶩ����Ϣ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />


  </head>
  <script type="text/javascript">
  var num=0; //������
  var flag;//Y��ʾ���������ϼ� 
  //���ö�ѡ���Ƿ�ѡ��
	function setSel(obj,i){	
		if(obj.value == undefined){		
			var check_ids = document.getElementsByName("check_id");
			var sotypes = document.getElementsByName("sotype");
			if(check_ids[i].checked){
				check_ids[i].checked = false;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
				num--;
			}else{
				
				check_ids[i].checked = true;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
/* 				if(sotypes[i].value=="2"){
				if(num==0){
				  if("Y"==check_ids[i].alt){
					flag="Y";
				
				  }else{
					flag="N";
					
				  }
				}else{
					 if(flag!=check_ids[i].alt){
						 alert("����ϸ���Ͳ�ͬ��");
						 check_ids[i].checked = false;
						 check_ids[i].parentNode.parentNode.style.backgroundColor = "";
						 num--;
					 }
				}} */
				num++;
			}

		}else{
			if(obj.checked){
				obj.checked = false;
			}else{
				obj.checked = true;
			}
		}
	}
  </script>
  <body>
	<table width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
		<tr>
			<td class="TD_LIST_TITLE" width="50"><div class="list_title">�к�</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">״̬ </div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">��Ʒ</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">Ʒ��</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">���</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">��λ</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">��ʹ������</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">��������</div></td>
		</tr>
<%
	List<SaleFormDetail> ls = (List<SaleFormDetail>)request.getAttribute("exResultList");
		SaleForm soHeader = (SaleForm)request.getAttribute("header");
			
			String m_strSaleFormId = "";	//���۶�����
			String m_strSaleFormTypeName = "";	//��������
			String m_strCustomerid = "";		//�ͻ�
			String m_strCustomername = "";		//�ͻ�����
			String m_strDownTime = "";		//��������ʱ��
			String m_strIsOut = "";			//�Ƿ������
			String m_strIsFinish = "";		//�Ƿ���ɳ���
			String m_strIsInvalid = "";		//�Ƿ���Ч
		
		if(ls != null && ls.size() > 0 && soHeader != null) {

			m_strSaleFormId = soHeader.getM_strSaleFormId();
			m_strSaleFormTypeName = soHeader.getM_strSaleFormTypeName();
			m_strCustomerid = soHeader.getM_strCustomerId();
			m_strCustomername = soHeader.getM_strCustomerName();
			m_strDownTime = soHeader.getM_strDownTime();
			m_strIsOut = soHeader.getM_strIsOut();
			m_strIsFinish = soHeader.getM_strIsFinish();
			m_strIsInvalid = soHeader.getM_strIsInvalid();
			
			//--------------------------------//
			
			SaleFormDetail soDetail = null;
			String m_strSaleFormDetailId;
			//String m_strSaleFormId; 		//���۵�id
			String m_strPackageId;  		//��Ʒ����
			String m_strPrintDate;  		//��ӡ����
			String m_strBatch;  			//����
			String m_strProductStatus;  	//״̬
			double m_iSaleNum;					//����
			double m_iOutNum;					//��������
			double nouseponum;
			
			String m_strPackageName;		//��Ʒ����
			String m_strUnit;				//��λ
			String m_strProductStatusName;	//��Ʒ״̬
			String spec;					//��Ʒ���
			
			String spaceid = "0000"; //(ռλ)
			
			String strValue="";	//������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ
			
			for(int i=0;i<ls.size();i++){
				soDetail = (SaleFormDetail)ls.get(i);
			    
				m_strSaleFormDetailId = soDetail.getM_strSaleFormDetailId();
				//m_strSaleFormId = soDetail.getM_strSaleFormId(); 					//���۵�id
				m_strPackageId = soDetail.getM_strPackageId();  					//��Ʒ����
				m_strPrintDate = soDetail.getM_strPrintDate();  					//��ӡ����
				m_strBatch = soDetail.getM_strBatch();  							//����
				m_strProductStatus = soDetail.getM_strProductStatus();  			//״̬
				
				m_strPackageName = soDetail.getM_strPackageName();					//��Ʒ����
				m_strUnit = soDetail.getM_strUnit();								//��λ
				m_strProductStatusName = soDetail.getM_strProductStatusName();		//��Ʒ״̬
				spec = soDetail.getSpec();
				
			    m_iSaleNum = soDetail.getM_iSaleNum();
			    m_iOutNum = soDetail.getM_iOutNum();
				nouseponum = m_iSaleNum - m_iOutNum;
				//������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ
				strValue =m_strSaleFormDetailId+"&"+m_strSaleFormId+"&"+m_strCustomerid+"&"+m_strCustomername+"&"+spaceid+"&"+m_strPackageName+"&"+m_strPackageId+"&"+m_strUnit+"&"+spec;
				
%>
		<tr   onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(this,<%=i%>)">
			<td class="TD_LIST" align="center"><input  type="checkbox"  onclick="setSel(this,<%=i%>)" name="check_id" class="input_checkbox"  value="<%=strValue%>" ><%=i+1%></td>
			<td class="TD_LIST"><%=m_strProductStatusName %></td>
			<td class="TD_LIST"><%=m_strPackageName %></td>
			<td class="TD_LIST"><%=m_strPackageId %></td>
			<td class="TD_LIST"><%=spec %></td>
			<td class="TD_LIST"><%=m_strUnit %></td>
			<td class="TD_LIST"><%=m_iSaleNum %></td>
			<td class="TD_LIST"><input type="hidden" name="nouseNum" value="<%=m_iOutNum %>"/><%=m_iOutNum %></td>
			<td class="TD_LIST"><input type="text" name="checkNum" value="<%=nouseponum %>" ></input></td>
	  </tr>			
<%			}
		}else{
			session.removeAttribute("commpaging");
			}
%>
   <tr>
     <td height="100%" colspan="<%=8 %>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%></div>
     </td>
   </tr>
	
		
	</table>
</body>
</html>
