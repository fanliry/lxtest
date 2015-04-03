package com.wms3.bms.standard.action.report.outbound;



import java.io.File;
import java.io.OutputStream;
import java.text.NumberFormat;
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
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

public class outbound_print_djmxExcel extends DownReport {
	protected IOutBoundBus outboundBus = null;
	 private IJobBus JobBus;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//���ⵥ�ݱ��
        try {
        	outboundBus = new OutBoundBusImpl(dao);
        	JobBus = new JobBusImpl(dao);
        	OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//���ⵥ
			//List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//���ⵥ��ϸ
			List ls = JobBus.getJobDetailByInvoiceid(outstockid);
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel�ĵ�
    		{	
    			excelReport(ls, outbound,filePath,request,response, strCompanyName);
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
	private void excelReport(List ls,OutboundInvoiceHeader invoice,String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
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
			 
			// ��ͷ
				wws.mergeCells(0, 0, 8  , 1);// ��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
				Label label = new Label(0, 0, "���ⵥ����ϸ", wcf);
				wws.addCell(label);
				// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
				label = new Label(0, 1, "", wcf1);
				wws.addCell(label);
				
				label = new Label(0, 2, "�ջ��ֿ�", wcf1);
				wws.addCell(label);
				label = new Label(1, 2, "�ջ���λ", wcf1);
				wws.addCell(label);
				label = new Label(2, 2, "��������", wcf1);
				wws.addCell(label);
				label = new Label(3 + iLine, 2, "�Ƶ���", wcf1);
				wws.addCell(label);
				label = new Label(4 + iLine, 2, "�Ƶ�����", wcf1);
				wws.addCell(label);
				label = new Label(5 + iLine, 2, "���ݱ��", wcf1);
				wws.addCell(label);
				label = new Label(6 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(7 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(8 + iLine, 2, "", wcf1);
				wws.addCell(label);
				
				
				if (invoice != null) {
					label = new Label(0, 3 , invoice.getWarehousename(), wcf1); 
					wws.addCell(label);
					label = new Label(1, 3 , invoice.getCustomername(), wcf1); 
					wws.addCell(label);
					label = new Label(2, 3 , invoice.getM_strOutTypeName(), wcf1); 
					wws.addCell(label);
					label = new Label(3 + iLine, 3 , invoice.getM_strOpManName(), wcf1); 
					wws.addCell(label);
					label = new Label(4 + iLine, 3 , invoice.getFormdate(), wcf1);
					wws.addCell(label);
					label = new Label(5 + iLine, 3 , invoice.getOutstockid(), wcf1);
					wws.addCell(label);
					label = new Label(6 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(7 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(8 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					
				}
				label = new Label(0, 4, "", wcf1);
				wws.addCell(label);
				
				label = new Label(0 , 5, "�к�", wcf1);
				wws.addCell(label);
				label = new Label(1 , 5, "��Ʒ����", wcf1);
				wws.addCell(label);
				label = new Label(2 , 5, "Ʒ��", wcf1);
				wws.addCell(label);
				label = new Label(3 , 5, "��������", wcf1);
				wws.addCell(label);
				label = new Label(4 , 5, "����", wcf1);
				wws.addCell(label);
				label = new Label(5 , 5, "���̺�", wcf1);
				wws.addCell(label);
				label = new Label(6 , 5, "��λ", wcf1);
				wws.addCell(label);
				label = new Label(7 , 5, "ʵ������", wcf1);
				wws.addCell(label);
				label = new Label(8 , 5, "��ע", wcf1);
				wws.addCell(label);
				
				
				
				if(ls!=null && !ls.isEmpty()){
				    
			    	//����С��2λ
					NumberFormat nbf=NumberFormat.getInstance();      
					nbf.setMinimumFractionDigits(2);
					nbf.setMaximumFractionDigits(2); 
				
					InoutJob job = null;	//��ҵ  
			        InoutJobdetail jobDetail = null;//��ҵ��ϸ
			        
			        String productid;//��ƷId
			      	String productCode;		//��Ʒ����
				  	String productName;		//Ʒ��
			      	String printdate;		//��������
			      	String lotinfo;			//����
			      	String traycode;//���̺�
			      	String unitName;		//��λ
			      	double plannum;	        //��������
			      	double sendnum;	        //��������
					String remark=null;          //��ע
			     	
					for(int i = 0; i < ls.size(); i++){
						Object[] obj = (Object[])ls.get(i);
						
						
						job = (InoutJob)obj[0];
						jobDetail =(InoutJobdetail)obj[1];
						
				      	productid = jobDetail.getProductid();	
				      	productCode = jobDetail.getProductcode();
				      	productName = jobDetail.getM_strProductName();   	
			         	printdate = jobDetail.getPrintdate(); 		 
			         	lotinfo = jobDetail.getLotinfo();       		
			         	unitName = jobDetail.getM_strUnitName();       		
				      	plannum = jobDetail.getJobnum();	
				      	traycode = job.getTraycode();
					  	sendnum = jobDetail.getAssignnum();	
					
						label = new Label(0, 6 + i, String.valueOf(i + 1), wcf1);
						wws.addCell(label);
						label = new Label(1, 6 + i, productCode==null?"":productCode, wcf1); // ��Ʒ���
						wws.addCell(label);
						label = new Label(2, 6 + i, productName==null?"":productName, wcf1); // Ʒ��
						wws.addCell(label);
						label = new Label(3, 6 + i, printdate==null?"":printdate, wcf1); // ��������
						wws.addCell(label);
						label = new Label(4 , 6 + i, lotinfo==null?"":lotinfo, wcf1); // ��������
						wws.addCell(label);
						label = new Label(5 , 6 + i, traycode==null?"":traycode, wcf1); // ����
						wws.addCell(label);
						label = new Label(6 , 6 + i, unitName==null?"":unitName, wcf1); // ����
						wws.addCell(label);
						label = new Label(7 , 6 + i, nbf.format(sendnum), wcf1);// ��λ
						wws.addCell(label);
						label = new Label(8 , 6 + i, remark==null?"":remark , wcf1);// �������
						wws.addCell(label);
						
						
					}
					wwb.write();
					wwb.close();
				}
			} catch (Exception er) {
				Logger
						.error("�������==>���ݹ���==>���ⵥ����ϸ==>����Excel�ļ�ʧ�ܣ�InventoryQueryExcel.excelReport��:"
								+ er.getMessage());
				throw new Exception("�������==>���ݹ���==>���ⵥ����ϸ==>����Excel�ļ�ʧ��:" + er.getMessage());
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
