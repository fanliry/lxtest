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
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;

public class OutboundSendQueryExcel extends DownReport {
	protected IOutBoundBus outboundBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String senddate_from = CommonTools.getStrToGbk(request.getParameter("senddate_from"));	//��������
		String senddate_to = CommonTools.getStrToGbk(request.getParameter("senddate_to"));		//��������
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//Ʒ��
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//�ͻ�
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));              //����ID
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//��ҵ��
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		int maxLine = 20;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
     
       
        outboundBus = new OutBoundBusImpl(dao);
        try {
        	String[] strSql = outboundBus.getOutboundSend(warehouseid, whAreaId, senddate_from, senddate_to, shiftid, productid, customerid, traycode, 
					lotinfo);
			List ls = dao.searchEntities(strSql[1]);
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel�ĵ�
    		{	
    			excelReport(ls, filePath,request,response, strCompanyName);
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
	private void excelReport(List ls, String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
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
			 
			 //��ͷ
			 wws.mergeCells(0, 0, 11+iLine, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			 Label label = new Label(0, 0,"������ѯ����",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 label = new Label(0, 2, "�к�", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "���ݱ��", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "�ͻ�", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "���ƺ�", wcf1);
			 wws.addCell(label);
			 label = new Label(4+iLine, 2, "��λ", wcf1);
			 wws.addCell(label);
			 label = new Label(5+iLine, 2, "Ʒ��", wcf1);
			 wws.addCell(label);
			 label = new Label(6+iLine, 2, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(7+iLine, 2, "��λ", wcf1);
			 wws.addCell(label);
			 label = new Label(8+iLine, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(9+iLine, 2, "ë��", wcf1);
			 wws.addCell(label);
     		 label = new Label(10+iLine, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(11+iLine, 2, "���", wcf1);
			 wws.addCell(label);
			 
			 if(ls != null && ls.size()>0){
					
					//����С��2λ
					NumberFormat nbf=NumberFormat.getInstance();      
					nbf.setMinimumFractionDigits(2);
					nbf.setMaximumFractionDigits(2); 
					
					Object[] obj = null;
					
					String invoiceid = null;	// ���ݱ��
					String customer = null;     // �ͻ�
					String vehicleno = null;	// ���ƺ�
					String vehiclepos = null;	// ��λ
					String product = null;		// ��Ʒ
				 	String traycode = null;     // ��������
				 	String cargospace = null;   // ��λ
				 	String jobid = null;		// ��ҵ��
					double jobnum = 0;			// ����
					double volume = 0;         	// ���
						double weight = 0;         	// ����
					double netweight = 0;		// ����
					
					for(int i = 0; i < ls.size(); i++){
						
						obj = (Object[])ls.get(i);
						invoiceid = obj[0] == null ? "" : obj[0].toString();	// ���ݱ��
						customer = obj[1] == null ? "" : obj[1].toString();		// �ͻ�
						vehicleno = obj[2] == null ? "" : obj[2].toString();	// ���ƺ�
						vehiclepos = obj[3] == null ? "" : obj[3].toString();	// ��λ
						product = obj[4] == null ? "" : obj[4].toString();		// ��Ʒ
						traycode = obj[5] == null ? "" : obj[5].toString();		// ��������
						cargospace = obj[6] == null ? "" : obj[6].toString();	// ��λ
						
						//jobid = obj[7] == null ? "" : obj[7].toString();		// ��ҵ��
						
						jobnum = Double.parseDouble(obj[7].toString().trim());			// ����
						//boxnum = Long.parseLong(obj[9].toString());				// ����
						//boxnum =(Long)obj[9];
						volume = Double.parseDouble(obj[8].toString());      	// ���
						weight = Double.parseDouble(obj[9].toString());     	// ����
						netweight = Double.parseDouble(obj[10].toString());		// ����
						
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						label = new Label(1, 3+i, invoiceid, wcf1);  //���ݱ��
						wws.addCell(label);
						label = new Label(2, 3+i, customer, wcf1); //�ͻ�
						wws.addCell(label);
						label = new Label(3, 3+i, vehicleno, wcf1);    //���ƺ�
						wws.addCell(label);
						 label = new Label(4+iLine, 3+i, vehiclepos, wcf1);	//��λ	
						 wws.addCell(label);
						 label = new Label(5+iLine, 3+i, product, wcf1);//��Ʒ
						 wws.addCell(label);
						 label = new Label(6+iLine, 3+i, traycode, wcf1);//��������
						 wws.addCell(label);
						 label = new Label(7+iLine, 3+i, cargospace, wcf1);//��λ
						 wws.addCell(label);
						 label = new Label(8+iLine, 3+i, String.valueOf((int)jobnum), wcf1);//����
						 wws.addCell(label);
			     		 label = new Label(9+iLine, 3+i, String.valueOf(volume) , wcf1);//���
						 wws.addCell(label);
						 label = new Label(10+iLine, 3+i, String.valueOf(weight), wcf1);//����
						 wws.addCell(label);
						 label = new Label(11+iLine, 3+i, String.valueOf(netweight), wcf1);//����
						 wws.addCell(label);
						 
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("�������==>������ѯ==>����Excel�ļ�ʧ�ܣ�InventoryQueryExcel.excelReport��:" + er.getMessage());	
			throw new Exception("�������==>������ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
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
