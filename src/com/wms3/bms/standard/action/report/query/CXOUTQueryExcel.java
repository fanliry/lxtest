package com.wms3.bms.standard.action.report.query;



import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.action.report.DownReport;
import com.wms3.bms.standard.business.inbound.impl.InBoundJobBusImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

public class CXOUTQueryExcel extends DownReport {
	InBoundJobBusImpl inBoundJobBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//���

		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//��ҵ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//��ҵ��Դ
		
		String productid = CommonTools.getStrToGbk(request.getParameter("package_id"));		//Ʒ��
		String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));		    //����
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));		//�ͻ�
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		
		String boundstockid = CommonTools.getStrToGbk(request.getParameter("boundstockid"));		//���ݺ�
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));		//��ⵥ������
		String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));		//���ⵥ������
		
		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));              //����ID
		
		//String inventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));   //���ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
      
		String groupinfo = CommonTools.getStrToGbk(request.getParameter("groupinfo"));//������Ϣ
       
		inBoundJobBus = new InBoundJobBusImpl(dao);
        try {
        	List ls = inBoundJobBus.getInboundJobDetailsGroupListByOut(warehouseid, whAreaId, alleyId,  
				    indate_from, indate_to, invoicetype, productid, ownerid, traycode, 
					lotid,"",boundstockid,groupinfo,customer_id,outtype);
			
			
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel�ĵ�
    		{	
    			excelReport(ls,groupinfo, filePath,request,response, strCompanyName);
    		}else if(fileType!= null && fileType.trim().equals("word")) //word�ĵ�
    		{
    			wordReport(ls, filePath, response, strCompanyName);
    		}else if(fileType!= null && fileType.trim().equals("html"))	//html�ĵ�
    		{
    			htmlReport(ls, filePath, response, strCompanyName);
    		}else if(fileType!= null && fileType.trim().equals("pdf"))	//PDF�ĵ�
    		{
    			pdfReport(ls, filePath, response, strCompanyName);
    		}
		} catch (Exception er) {
			throw new Exception("������==>����ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
		}
		return null;
	}
	/**
	 * ���ܣ�����EXCEL����
	 *@author liuxh 2013-2-4
	 *@param ls ��ѯ����list����
	 *@param filePath �ļ�·��������
	 *@param request
	 *@param response
	 *@param strCompanyName ��˾����
	 * @throws Exception 
	 */
	private void excelReport(List ls,String groupinfo, String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
		try {
			
			 int iLine = 0;	//��ʾ���������Ը���
			 
			 response.reset();
			 String downFileName = "";
			 downFileName = filePath.substring(filePath.lastIndexOf(File.separator)+1,filePath.length());
			 
			 Date newDate = new Date();
			 String strTime = StrTools.getCurrDateTime(newDate, 8);
			 downFileName = downFileName.substring(0, downFileName.length()-4) + "_" + strTime+".xls";
			 
			 downFileName = new String(downFileName.getBytes("GB2312"),"ISO-8859-1");
			 response.setHeader("Content-disposition","attachment;filename="+downFileName); 
			 //�����������MIME����
			 response.setContentType("application/txt;charset=gb2312");
			 //����������
			 OutputStream out = response.getOutputStream();
			 WritableWorkbook wwb = Workbook.createWorkbook(out);
			 //����������
			 WritableSheet wws = wwb.createSheet("��һҳ",0);
			 //�����������ɫ
			 WritableFont wwf = new WritableFont(WritableFont.createFont("����_GB2312"),18,WritableFont.NO_BOLD);
			 WritableCellFormat wcf = new WritableCellFormat(wwf);
			 wcf.setAlignment(Alignment.CENTRE);//����ˮƽ����
			 wcf.setVerticalAlignment(VerticalAlignment.CENTRE);//���ô�ֱ���뷽ʽ����
			 wcf.setWrap(true);//�����Զ�����
			 WritableFont wwf1 = new WritableFont(WritableFont.createFont("����_GB2312"),10,WritableFont.NO_BOLD);
			 WritableCellFormat wcf1 = new WritableCellFormat(wwf1);
			 wcf1.setAlignment(Alignment.CENTRE);//����ˮƽ����
			 wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);//���ô�ֱ���뷽ʽ����
			 wcf1.setWrap(true);//�����Զ�����
			 wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);//����ϸ�߿�
			 wcf.setBorder(Border.ALL, BorderLineStyle.THIN);//����ϸ�߿�
			 
			 
			 
			// --customerid|ownerId,1|2,�ͻ�|����--
			    int a = 0;//�ֶε�������ʶ,���ڱ���
			    int b = 0;//��������
			   
			    String aem[] = null;
			    String bem[] = null;
		        if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
						
						aem = groupinfo.split(",");
						bem = aem[2].split("\\|");// ������
				}
				
				    //���е�������
			
			int len = 0;
			if(ls!=null && ls.size()>0){
			  len = ls.size();
			}
			 
			 
			 //��ͷ
			if(bem != null && bem.length>0){
				wws.mergeCells(0, 0, 4+bem.length, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			}else{
				wws.mergeCells(0, 0, 12, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			}
			 
			 Label label = new Label(0, 0,"������ˮ��ѯ����",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 
			 
			 label = new Label(0, 2, "�к�", wcf1);
			 wws.addCell(label);
			if(bem != null && bem.length>0){ //��ȡ�����ֶ�
				for(int i=0; i<bem.length; i++){
					 label = new Label(i+1, 2, bem[i], wcf1);
					 wws.addCell(label);
					 a = a+1;
				}
			}else{
				 label = new Label(1, 2, "��Ʒ����", wcf1);
				 wws.addCell(label);
				 label = new Label(2, 2, "Ʒ��", wcf1);
				 wws.addCell(label);
				 label = new Label(3, 2, "����", wcf1);
				 wws.addCell(label);
				 label = new Label(4, 2, "��������", wcf1);
				 wws.addCell(label);
				 label = new Label(5, 2, "�ͻ�", wcf1);
				 wws.addCell(label);
				 label = new Label(6, 2, "���ݱ��", wcf1);
				 wws.addCell(label);
				 label = new Label(7, 2, "��������", wcf1);
				 wws.addCell(label);
				 label = new Label(8, 2, "��ҵ��������", wcf1);
				 wws.addCell(label);
				 a=8;
			}
			 label = new Label(a+1, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(a+2, 2, "���", wcf1);
			 wws.addCell(label);
     		 label = new Label(a+3, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(a+4, 2, "����", wcf1);
			 wws.addCell(label);
			 
			 
			 
			 if(ls != null && ls.size() > 0)
				{
					Object[] ob = null;
					String createTime = null;   //��������
					double pnum;            	//����
					double volume;          	//���
					double pweight;         	//����
			     	double pnetweight;      	//����

			  	 	for(int i=0; i<ls.size(); i++) 
			  	 	{
						ob = (Object[])ls.get(i);
			  	 		if(bem != null && bem.length>0){ //����֮��
			  	 		    pnum = Double.parseDouble(ob[bem.length].toString());			    //����
			  	 		    volume = Double.parseDouble(ob[bem.length+1].toString());		    //���
			  	 		    pweight = Double.parseDouble(ob[bem.length+2].toString());        	//ë��
			  	 		    pnetweight = Double.parseDouble(ob[bem.length+3].toString());     	//����
			  	 		}else{ //û�з���
			  	 			
			  	 		    pnum = Double.parseDouble(ob[8].toString());			//����
			  	 		    volume = Double.parseDouble(ob[9].toString());		    //���
			  	 		    pweight = Double.parseDouble(ob[10].toString());        	//ë��
			  	 		    pnetweight = Double.parseDouble(ob[11].toString());     	//����
			  	 		}
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						if(bem != null && bem.length>0){ //��ȡ�����ֶ�
							for(int j=0; j<bem.length; j++){
								label = new Label(j+1, 3+i, ob[j] == null ? "" : ob[j].toString(), wcf1);  
								wws.addCell(label);
								b=b+1;
							}
						}else{
							label = new Label(1, 3+i, ob[0] == null ? "" : ob[0].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(2, 3+i, ob[1] == null ? "" : ob[1].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(3, 3+i, ob[2] == null ? "" : ob[2].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(4, 3+i, ob[3] == null ? "" : ob[3].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(5, 3+i, ob[4] == null ? "" : ob[4].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(6, 3+i, ob[5] == null ? "" : ob[5].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(7, 3+i, ob[6] == null ? "" : ob[6].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(8, 3+i, ob[7] == null ? "" : ob[7].toString(), wcf1);  
							wws.addCell(label);
							b=8;
						}
						
						label = new Label(b+1, 3+i, String.valueOf(pnum), wcf1);  //����
						wws.addCell(label);
						label = new Label(b+2, 3+i, String.valueOf(volume), wcf1); //��λ
						wws.addCell(label);
						label = new Label(b+3, 3+i, String.valueOf(pweight), wcf1);    //���뵥��
						wws.addCell(label);
						 label = new Label(b+4, 3+i, String.valueOf(pnetweight), wcf1);	//��ⵥ��	
						 wws.addCell(label);
						b=0; 
					}	
					wwb.write();
					wwb.close();
					
			 }
		} catch (Exception er) {
			Logger.error("��ѯͳ��==>������ˮ��ѯ==>����Excel�ļ�ʧ�ܣ�CXOUTQueryExcel.excelReport��:" + er.getMessage());	
			throw new Exception("��ѯͳ��==>������ˮ��ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
		}
	}

	private void wordReport(List ls, String filePath,
			HttpServletResponse response, String strCompanyName) {
		// TODO Auto-generated method stub
		  
	}

	private void htmlReport(List ls, String filePath,
			HttpServletResponse response, String strCompanyName) {
		// TODO Auto-generated method stub
		
	}

	private void pdfReport(List ls, String filePath,
			HttpServletResponse response, String strCompanyName) {
		// TODO Auto-generated method stub
		
	}

}
