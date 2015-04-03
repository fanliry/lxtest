package com.wms3.bms.standard.action.report.inventory;


import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

public class CheckInventoryQueryExcel extends DownReport {
	IInventoryBus inventoryBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String checkType = CommonTools.getStrToGbk(request.getParameter("checkType"));	//�̵�����
		String fmDate = CommonTools.getStrToGbk(request.getParameter("fmDate"));	//fromʱ��
		String toDate = CommonTools.getStrToGbk(request.getParameter("toDate"));	//toʱ��
		IInventoryCheckBus iCheckBus = new InventoryCheckBusImpl(dao);
     
        try {
        	String strUrl = "/standard/jsp/inventory/checksearch/kc_checksearch_report.jsp";
			CommonReturn cReturn = iCheckBus.getCheckResultsByTimeAndType(checkType, fmDate, toDate);
			List ls = dao.searchEntities(cReturn.getStrQueryHQL());
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
			 wws.mergeCells(0, 0, 9, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			 Label label = new Label(0, 0,"�̵��ѯ����",wcf);
			 wws.addCell(label);
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 
			 label = new Label(0, 2, "�к�", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "�ֿ�", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "�����", wcf1);
			 wws.addCell(label);
			 label = new Label(4, 2, "��Ʒ��", wcf1);
			 wws.addCell(label);
			 label = new Label(5, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(6, 2, "�������", wcf1);
			 wws.addCell(label);
			 label = new Label(7, 2, "�̵�����", wcf1);
			 wws.addCell(label);
			 label = new Label(8, 2, "����Ա", wcf1);
			 wws.addCell(label);
			 label = new Label(9, 2, "�̵�ʱ��", wcf1);
			 wws.addCell(label);
			 if(ls != null && ls.size()>0){ 
					
				    InventoryCheckTask checkTask = null;
					InventoryCheckResult checkResult = null;
					Object[] obj = new Object[2];
			   		String strWarehouseId = "";		    // �ֿ�
					String strCheckReq = "";			// �̵�����
					String strCheckTask = "";			// ����
					String strProductName = "";         //��Ʒ��
					String strLotinfo = "";				// ����
					double strpnum =0.0;                 //�������
					double strCheckNum =0.0;            //�̵�����
					String strOpManName = "";			// ����Ա��
					String strOpManTime = "";			// ����ʱ��
					
					
					for(int i = 0; i < ls.size(); i++){
						
						obj = (Object[])ls.get(i);
			   			checkResult = (InventoryCheckResult)obj[0];  //�̵���
			   			checkTask = (InventoryCheckTask)obj[1];//�̵�����
			   	   		strWarehouseId = checkTask.getWarehouseid();		    // �ֿ�
			   			strCheckReq = checkResult.getRequestid();			// �̵�����
			   			strCheckTask = checkResult.getTaskid();			// ����
			   			strProductName = checkResult.getProductName();         //��Ʒ��
			   			strLotinfo = checkResult.getLotnumber();				// ����
			   			strpnum =checkResult.getNum();                 //�������
			   			strCheckNum =checkResult.getChecknum();            //�̵�����
			   			strOpManName = checkResult.getCreateman();			// ����Ա��
			   			strOpManTime =checkResult.getChecktime() ;			// ����ʱ��
			  	 		
			  	 		
						  //             �к�     �к�     ��ʾ����     ���Ը�ʽ 
			   			 label = new Label(0, 3+i, (i+1)+"", wcf1);
						 wws.addCell(label);
						 label = new Label(1, 3+i, strWarehouseId, wcf1);
						 wws.addCell(label);
						 label = new Label(2, 3+i, strCheckReq, wcf1);
						 wws.addCell(label);
						 label = new Label(3, 3+i, strCheckTask, wcf1);
						 wws.addCell(label);
						 label = new Label(4, 3+i, strProductName, wcf1);
						 wws.addCell(label);
						 label = new Label(5, 3+i, strLotinfo, wcf1);
						 wws.addCell(label);
						 label = new Label(6, 3+i, strpnum+"", wcf1);
						 wws.addCell(label);
						 label = new Label(7, 3+i, strCheckNum+"", wcf1);
						 wws.addCell(label);
						 label = new Label(8, 3+i, strOpManName, wcf1);
						 wws.addCell(label);
						 label = new Label(9, 3+i, strOpManTime==null?"":strOpManTime, wcf1);
						 wws.addCell(label);
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("������==>�̵��ѯ==>����Excel�ļ�ʧ�ܣ�CheckInventoryQueryExcel.excelReport��:" + er.getMessage());	
			throw new Exception("������==>�̵��ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
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
