package com.wms3.bms.standard.action.report.inventory;


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
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.action.report.DownReport;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.InventoryMoveBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryMoveBusImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader;

public class InventoryYKQueryExcel extends DownReport {
	protected IInventoryBus inventoryBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		//from
		String movementId = CommonTools.getStrToGbk(request.getParameter("movementId"));	//ID
		
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ���� 
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
        try {

        	InventoryMovementHeader movementHeader = new InventoryMovementHeader();
			InventoryMovementDetail movementDetail = new InventoryMovementDetail();
			
			InventoryMovementHeader moveHeader = movementHeader.getIMoveHeader(movementId,dao);
			
			List ls = movementDetail.getMoveDetailListToId(movementId, dao);
			
			
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel�ĵ�
    		{	
    			excelReport(moveHeader,ls, filePath,request,response, strCompanyName);
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
	private void excelReport(InventoryMovementHeader moveHeader,List ls, String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
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
			 wws.mergeCells(0, 0, 10+iLine, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			 Label label = new Label(0, 0,"�ƿ��ѯ����",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 
			 label = new Label(0, 2, "�ֿ�����", wcf1);
				wws.addCell(label);
				label = new Label(1, 2, "�Ƶ�ʱ��", wcf1);
				wws.addCell(label);
				label = new Label(2, 2, "���ݱ���", wcf1);
				wws.addCell(label);
				label = new Label(3 + iLine, 2, "�Ƶ���", wcf1);
				wws.addCell(label);
				label = new Label(4 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(5 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(6 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(7 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(8 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(9 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(10 + iLine, 2, "", wcf1);
				wws.addCell(label);
				
				if (moveHeader != null) {
					label = new Label(0, 3 , moveHeader.getWarehouseName(), wcf1); 
					wws.addCell(label);
					label = new Label(1, 3 , moveHeader.getCreateTime(), wcf1); 
					wws.addCell(label);
					label = new Label(2, 3 , moveHeader.getMovementid(), wcf1); 
					wws.addCell(label);
					label = new Label(3 + iLine, 3 , moveHeader.getCreateMan(), wcf1); 
					wws.addCell(label);
					label = new Label(4 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(5 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(6 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(7 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(8 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(9 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(10 + iLine, 3 , "", wcf1);
					wws.addCell(label);
				}
				label = new Label(0, 4, "", wcf1);
				wws.addCell(label);
				
			 label = new Label(0, 5, "�к�", wcf1);
			 wws.addCell(label);
			 
			 label = new Label(1, 5, "��Ʒ����", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 5, "Ʒ��", wcf1);
			 wws.addCell(label);
			 label = new Label(3+iLine, 5, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(4+iLine, 5, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(5+iLine, 5, "���̺�", wcf1);
			 wws.addCell(label);
			 label = new Label(6+iLine, 5, "��λ", wcf1);
			 wws.addCell(label);
			
			 label = new Label(7+iLine, 5, "FM����", wcf1);
			 wws.addCell(label);
     		 label = new Label(8+iLine, 5, "FM��λ", wcf1);
			 wws.addCell(label);
			 label = new Label(9+iLine, 5, "TO����", wcf1);
			 wws.addCell(label);
			 label = new Label(10+iLine, 5, "TO��λ", wcf1);
			 wws.addCell(label);
			 
			 if(ls != null && ls.size() > 0){
					
					InventoryMovementDetail iDetail = null;
					
					String strProductCode = null; 	//��Ʒ���
					String strProductName = null; 	//Ʒ��
					String strProductDate = null;	//��������
					String strLotNum = null;	//����
					String strTrayNum = null;	//���̺�
					String strPunit = null; //��λ
					
					String strFromWhare = null;	//FM����
					String strFromCargospace = null; //FM��λ
					
					String strToWhare = null;	//to����
					String strToCargospace = null; //to��λ
				
					
					
					for(int i = 0; i< ls.size(); i++){
						
						iDetail = (InventoryMovementDetail)ls.get(i);
						
						
						strProductCode = iDetail.getProductid();
						strProductName = iDetail.getProductName();
						strProductDate = iDetail.getProductDate();
						strLotNum = iDetail.getLotNum();
						strTrayNum = iDetail.getTrayCode();
						strPunit = iDetail.getMeter();
						
						strFromWhare = iDetail.getFromAreName();
						strFromCargospace = iDetail.getFromCargospace();
						strToWhare = iDetail.getToAreName();
						strToCargospace = iDetail.getToCargospace();
						
			  	 		
			  	 		
						
						label = new Label(0, 6+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						
						label = new Label(1, 6+i, strProductCode, wcf1); //��Ʒ���
						wws.addCell(label);
						label = new Label(2, 6+i, strProductName, wcf1);    //Ʒ��
						wws.addCell(label);
						 label = new Label(3+iLine, 6+i, strProductDate, wcf1);	//��������	
						 wws.addCell(label);
						 label = new Label(4+iLine, 6+i, strLotNum, wcf1);//����
						 wws.addCell(label);
						 label = new Label(5+iLine, 6+i, strTrayNum, wcf1);//���̺�
						 wws.addCell(label);
						 label = new Label(6+iLine, 6+i, strPunit, wcf1);//��λ
						 wws.addCell(label);
						
			     		 label = new Label(7+iLine, 6+i, strFromWhare , wcf1);//FM����
						 wws.addCell(label);
						 label = new Label(8+iLine, 6+i, strFromCargospace, wcf1);//FM��λ
						 wws.addCell(label);
						 label = new Label(9+iLine, 6+i, strToWhare, wcf1);//to����
						 wws.addCell(label);
						 label = new Label(10+iLine, 6+i, strToCargospace, wcf1); //to��λ
						 wws.addCell(label);
						 
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("������==>�ƿ��ѯ==>����Excel�ļ�ʧ�ܣ�InventoryQueryExcel.excelReport��:" + er.getMessage());	
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
