package com.wms3.bms.standard.action.report.quality;


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
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.quality.impl.InventoryQualityBusImpl;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail;

public class changestatus_print_Excel extends DownReport {
	protected IInventoryBus inventoryBus;
	protected int maxLine = 20; // ��ҳ��ʾ��������

	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {

		String instockid = CommonTools.getStrToGbk(request
				.getParameter("instockid")); // ���ݺ�
		

		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // ÿҳ��ʾ����
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}

		try {
			
			InventoryQualityBusImpl inventoryQB= new InventoryQualityBusImpl(dao);
			
			InventoryQualityResult invoice= inventoryQB.getAdjustListToId(instockid);
			List ls = inventoryQB.getAdjustDetailListToId(instockid);
			

			if (fileType != null && fileType.trim().equals("excel")) // excel�ĵ�
			{
				excelReport(ls, invoice, filePath, request, response,
						strCompanyName);
			} else if (fileType != null && fileType.trim().equals("word")) // word�ĵ�
			{
				wordReport(ls, filePath, response, strCompanyName);
			} else if (fileType != null && fileType.trim().equals("html")) // html�ĵ�
			{
				htmlReport(ls, filePath, response, strCompanyName);
			} else if (fileType != null && fileType.trim().equals("pdf")) // PDF�ĵ�
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
	 * 
	 * @author liuxh 2013-2-4
	 *@param ls
	 *            ��ѯ����list����
	 *@param filePath
	 *            �ļ�·��������
	 *@param request
	 *@param response
	 *@param strCompanyName
	 *            ��˾����
	 * @throws Exception
	 */
	private void excelReport(List ls, InventoryQualityResult invoice,
			String filePath, HttpServletRequest request,
			HttpServletResponse response, String strCompanyName)
			throws Exception {

		try {

			int iLine = 0; // ��ʾ���������Ը���

			response.reset();
			String downFileName = "";
			downFileName = filePath.substring(filePath
					.lastIndexOf(File.separator) + 1, filePath.length());
			Date newDate = new Date();
			String strTime = StrTools.getCurrDateTime(newDate, 8);
			downFileName = downFileName.substring(0, downFileName.length() - 4)
					+ "_" + strTime + ".xls";

			downFileName = new String(downFileName.getBytes("GB2312"),
					"ISO-8859-1");
			response.setHeader("Content-disposition", "attachment;filename="
					+ downFileName);
			// �����������MIME����
			response.setContentType("application/txt;charset=gb2312");
			// ����������
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			// ����������
			WritableSheet wws = wwb.createSheet("��һҳ", 0);
			// �����������ɫ
			WritableFont wwf = new WritableFont(WritableFont
					.createFont("����_GB2312"), 18, WritableFont.NO_BOLD);
			WritableCellFormat wcf = new WritableCellFormat(wwf);
			wcf.setAlignment(Alignment.CENTRE);// ����ˮƽ����
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// ���ô�ֱ���뷽ʽ����
			wcf.setWrap(true);// �����Զ�����
			WritableFont wwf1 = new WritableFont(WritableFont
					.createFont("����_GB2312"), 10, WritableFont.NO_BOLD);
			WritableCellFormat wcf1 = new WritableCellFormat(wwf1);
			wcf1.setAlignment(Alignment.CENTRE);// ����ˮƽ����
			wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);// ���ô�ֱ���뷽ʽ����
			wcf1.setWrap(true);// �����Զ�����
			wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);// ����ϸ�߿�
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);// ����ϸ�߿�

			// ��ͷ
			wws.mergeCells(0, 0, 6, 1);// ��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			Label label = new Label(0, 0, "���м�¼��ѯ", wcf);
			wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			label = new Label(0, 1, "", wcf1);
			wws.addCell(label);
			
			label = new Label(0, 2, "���е���", wcf1);
			wws.addCell(label);
			label = new Label(1, 2, "������Ա", wcf1);
			wws.addCell(label);
			label = new Label(2, 2, "��������", wcf1);
			wws.addCell(label);
			label = new Label(3 + iLine, 2, "����", wcf1);
			wws.addCell(label);
			label = new Label(4 + iLine, 2, "����", wcf1);
			wws.addCell(label);
			label = new Label(5 + iLine, 2, "", wcf1);
			wws.addCell(label);
			label = new Label(6 + iLine, 2, "", wcf1);
			wws.addCell(label);
			
			
			if (invoice != null) {
				label = new Label(0, 3 , invoice.getM_strCheckResultId(), wcf1); 
				wws.addCell(label);
				label = new Label(1, 3 , invoice.getM_strOpManId(), wcf1); 
				wws.addCell(label);
				label = new Label(2, 3 , invoice.getM_strCreateDate(), wcf1); 
				wws.addCell(label);
				label = new Label(3 + iLine, 3 , invoice.getM_strLotNumber(), wcf1); 
				wws.addCell(label);
				label = new Label(4 + iLine, 3 , invoice.getM_strNum(), wcf1);
				wws.addCell(label);
				label = new Label(5 + iLine, 3 , "", wcf1);
				wws.addCell(label);
				label = new Label(6 + iLine, 3 , "", wcf1);
				wws.addCell(label);
				
				
			}
			label = new Label(0, 4, "", wcf1);
			wws.addCell(label);
			
			label = new Label(0, 5, "�к�", wcf1);
			wws.addCell(label);
			label = new Label(1, 5, "Ʒ��", wcf1);
			wws.addCell(label);
			label = new Label(2, 5, "��������", wcf1);
			wws.addCell(label);
			label = new Label(3 + iLine, 5, "��Ʒ��״̬", wcf1);
			wws.addCell(label);
			label = new Label(4 + iLine, 5, "��Ʒԭ״̬", wcf1);
			wws.addCell(label);
			label = new Label(5 + iLine, 5, "��λ��", wcf1);
			wws.addCell(label);
			label = new Label(6 + iLine, 5, "����", wcf1);
			wws.addCell(label);
			
			
			
			
			 if(ls!=null && !ls.isEmpty()){
				    
			    	//����С��2λ
					NumberFormat nbf=NumberFormat.getInstance();      
					nbf.setMinimumFractionDigits(2);
					nbf.setMaximumFractionDigits(2); 
				
					InventoryQualityResultDetail detail=null;
			        String strDId="";
			        String strProductName="";
			        String strCreatetime="";
			        String strNewProductStatus="";
			        String strOldProductStatus="";
			        String strClientName="";
			        String strCarspaceid="";
			        double iProductNum;
			        for(int i=0; i<ls.size(); i++)
			        {
			             detail = (InventoryQualityResultDetail)ls.get(i); 
			             strDId = detail.getM_strCheckResultId();        //״̬ת������
			        	 strProductName = detail.getM_strProductName();  //��Ʒ��
			        	 strCreatetime = detail.getM_strPrintDate();     //��������
			        	 strNewProductStatus = detail.getM_strNewProductStatusName();	 //����Ʒ״̬
			        	 strOldProductStatus = detail.getM_strOldProductStatusName();	 //ԭ��Ʒ״̬
			        	 strClientName = detail.getM_strLotNumber();    //����
			        	 strCarspaceid = detail.getM_strCarspaceid();    //��λ��
			        	 iProductNum = detail.getM_iProductNum();	     //����	
				
					label = new Label(0, 6 + i, String.valueOf(i + 1), wcf1);
					wws.addCell(label);
					label = new Label(1, 6 + i, strProductName, wcf1); // �ֿ�����
					wws.addCell(label);
					label = new Label(2, 6 + i, strCreatetime, wcf1); // ��Ʒ���
					wws.addCell(label);
					label = new Label(3, 6 + i, strNewProductStatus, wcf1); // Ʒ��
					wws.addCell(label);
					label = new Label(4 , 6 + i, strOldProductStatus, wcf1); // ��������
					wws.addCell(label);
					label = new Label(5 , 6 + i, strCarspaceid, wcf1);// ����
					wws.addCell(label);
					label = new Label(6 , 6 + i, String.valueOf(iProductNum) , wcf1);// ���̺�
					wws.addCell(label);
					
					
				}
				wwb.write();
				wwb.close();
			}
		} catch (Exception er) {
			Logger
					.error("��������==>���м�¼��ѯ==>����Excel�ļ�ʧ�ܣ�InventoryQueryExcel.excelReport��:"
							+ er.getMessage());
			throw new Exception("��������==>���м�¼��ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
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
