package com.wms3.bms.standard.action.report.otherinbound;


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
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

public class inbound_print_shdExcel extends DownReport {
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
			InboundHeader invoiceHeader = new InboundHeader();
			InboundDetail invoicedetail = new InboundDetail();
			/*��ѯ*/
			InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
			List ls = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));

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
	private void excelReport(List ls, InboundHeader invoice,
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
			wws.mergeCells(0, 0, 9  , 1);// ��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			Label label = new Label(0, 0, "�����ջ���", wcf);
			wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			label = new Label(0, 1, "", wcf1);
			wws.addCell(label);
			
			label = new Label(0, 2, "������λ", wcf1);
			wws.addCell(label);
			label = new Label(1, 2, "�ջ��ֿ�", wcf1);
			wws.addCell(label);
			label = new Label(2, 2, "�������", wcf1);
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
			label = new Label(9 + iLine, 2, "", wcf1);
			wws.addCell(label);
			
			if (invoice != null) {
				label = new Label(0, 3 , invoice.getDeliveryunits(), wcf1); 
				wws.addCell(label);
				label = new Label(1, 3 , invoice.getWarehousename(), wcf1); 
				wws.addCell(label);
				label = new Label(2, 3 , invoice.getInvoicetypename(), wcf1); 
				wws.addCell(label);
				label = new Label(3 + iLine, 3 , invoice.getCreatemanname(), wcf1); 
				wws.addCell(label);
				label = new Label(4 + iLine, 3 , invoice.getInvoicedate(), wcf1);
				wws.addCell(label);
				label = new Label(5 + iLine, 3 , invoice.getInstockid(), wcf1);
				wws.addCell(label);
				label = new Label(6 + iLine, 3 , "", wcf1);
				wws.addCell(label);
				label = new Label(7 + iLine, 3 , "", wcf1);
				wws.addCell(label);
				label = new Label(8 + iLine, 3 , "", wcf1);
				wws.addCell(label);
				label = new Label(9 + iLine, 3 , "", wcf1);
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
			label = new Label(4 , 5, "��������", wcf1);
			wws.addCell(label);
			label = new Label(5 , 5, "����", wcf1);
			wws.addCell(label);
			label = new Label(6 , 5, "��λ", wcf1);
			wws.addCell(label);
			label = new Label(7 , 5, "�������", wcf1);
			wws.addCell(label);
			label = new Label(8 , 5, "ʵ������", wcf1);
			wws.addCell(label);
			label = new Label(9 , 5, "��ע", wcf1);
			wws.addCell(label);
			
			
			
			if (ls != null && ls.size() > 0) {
				
				//����С��2λ
				NumberFormat nbf=NumberFormat.getInstance();      
				nbf.setMinimumFractionDigits(2);
				nbf.setMaximumFractionDigits(2); 
			
				InboundDetail inDetail = null;//��ⵥ��ϸ 
				String instockdetailid;	//��ⵥ��ϸid
		      	String inid;		   //��ⵥ��
		      	String productid;		//Ʒ��id
		      	String productcode;//��Ʒ����
		      	String printdate;		//��������
			  	double innum;			//�������
		      	double getnum;		    //ʵ������
		      	String lotinfo;         //������Ϣ
		      	String productName;     //��Ʒ����
		      	String traycode;        //��������
		      	String statusname;          //״̬
		      	String obtainmanname;          //�ջ���
		      	String obtaintime;          //�ջ�ʱ��
		      	String bandrecordid;          //�󶨼�¼id
		        String m_strUnitName=""; //��λ
		        String remark ;
		      	
				for(int i = 0; i < ls.size(); i++)
				{
					 inDetail = (InboundDetail)ls.get(i);
					 instockdetailid = inDetail.getInstockdetailid();	
					 inid = inDetail.getInstockid();	
					 productid = inDetail.getProductid();	
					 productcode = inDetail.getProductCode();
					 printdate = inDetail.getPrintdate();	
					 innum = inDetail.getInnum();	
					 getnum = inDetail.getGetnum();	
					 lotinfo = inDetail.getLotinfo();	
					 productName = inDetail.getProductName();	
					 traycode = inDetail.getTraycode();
					 m_strUnitName = inDetail.getPunit();
					 remark = inDetail.getRemark();
					 statusname = inDetail.getStatusname();
					 obtainmanname = inDetail.getObtainmanname();
					 obtaintime = inDetail.getObtaintime();
					 bandrecordid = inDetail.getBandrecordid();
				
					label = new Label(0, 6 + i, String.valueOf(i + 1), wcf1);
					wws.addCell(label);
					label = new Label(1, 6 + i, productcode, wcf1); // ��Ʒ���
					wws.addCell(label);
					label = new Label(2, 6 + i, productName, wcf1); // Ʒ��
					wws.addCell(label);
					label = new Label(3, 6 + i, printdate, wcf1); // ��������
					wws.addCell(label);
					label = new Label(4 , 6 + i, traycode, wcf1); // ��������
					wws.addCell(label);
					label = new Label(5 , 6 + i, lotinfo, wcf1); // ����
					wws.addCell(label);
					
					label = new Label(6 , 6 + i, m_strUnitName, wcf1);// ��λ
					wws.addCell(label);
					label = new Label(7 , 6 + i, String.valueOf(innum) , wcf1);// �������
					wws.addCell(label);
					label = new Label(8 , 6 + i, String.valueOf(getnum), wcf1);// ʵ������
					wws.addCell(label);
					label = new Label(9 , 6 + i, remark, wcf1);// ��ע
					wws.addCell(label);
					
				}
				wwb.write();
				wwb.close();
			}
		} catch (Exception er) {
			Logger
					.error("������==>�ƿ��ѯ==>����Excel�ļ�ʧ�ܣ�InventoryQueryExcel.excelReport��:"
							+ er.getMessage());
			throw new Exception("������==>�ƿ��ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
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
