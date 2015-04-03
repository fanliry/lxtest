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
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.action.report.DownReport;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

public class InventoryQueryExcel extends DownReport {
	IInventoryBus inventoryBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));		//�߼�����id
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));//���ID
		String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//��
		String column = CommonTools.getStrToGbk(request.getParameter("column"));			//��
		String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//��
		
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));	//����ID
		String package_id = CommonTools.getStrToGbk(request.getParameter("package_id"));	//Ʒ��ID
		String tray_code = CommonTools.getStrToGbk(request.getParameter("tray_code"));		//��������
		
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//�������from
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//�������to
		
		String total_items = CommonTools.getStrToGbk(request.getParameter("total_items"));  //ͳ����Ŀ
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));      //����
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));      //��Ʒid
        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));      //�������
        
        String status = CommonTools.getStrToGbk(request.getParameter("status"));            //���״̬
        String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));      //���뵥��
        String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));      //��ⵥ��
        String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); //��Ʒ״̬
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));     //��Ʒ����
        String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));     //��Ʒ���
       
		//String inventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));   //���ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
     
       
		inventoryBus = new InventoryBusImpl(dao);
        try {
        	String[] strSql = inventoryBus.getInventoryQuerynew(warehouseid, whAreaId,Virtualwhid, lotinfo, producttype, cargoAlleyId, platoon, 
					column, floor, lotnumber, productid, indate_from, indate_to, tray_code, status, requestid, instockid, productstatus, productcode);
			List ls = dao.searchEntities(strSql[0]);
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
			 wws.mergeCells(0, 0, 12+iLine, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			 Label label = new Label(0, 0,"����ѯ����",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 label = new Label(0, 2, "�к�", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "��λ", wcf1);
			 wws.addCell(label);
			 label = new Label(3+iLine, 2, "��Ʒ����", wcf1);
			 wws.addCell(label);
			 label = new Label(4+iLine, 2, "Ʒ��", wcf1);
			 wws.addCell(label);
			 label = new Label(5+iLine, 2, "�������", wcf1);
			 wws.addCell(label);
			 label = new Label(6+iLine, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(7+iLine, 2, "����", wcf1);
			 wws.addCell(label);
     		 label = new Label(8+iLine, 2, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(9+iLine, 2, "���״̬", wcf1);
			 wws.addCell(label);
			 label = new Label(10+iLine, 2, "��Ʒ״̬", wcf1);
			 wws.addCell(label);
			 label = new Label(11+iLine, 2, "��ⵥ��", wcf1);
			 wws.addCell(label);
			 if(ls != null && ls.size()>0){ 
					
				 	Object[] ob = null;
				 	InventoryStorage storage = null;
				    BaseCargospace space = null;
				    
				    String whArea;				//����
			 		String cargoSpace;			//��λ
			 	 	String product;				//��Ʒ
			 	 	String lotnumber;		    //����
			 	 	String instockid;           //��ⵥ
			 	 	String requestid;           //���뵥
			 	 	String traycode;        	//��������
					String punit;				//��λ
					double pnum;            	//�������
					String indate;             	//���ʱ��
					String intype;             	//��ⷽʽ 1.���� 2.�ѻ�
			        String productcode;         //��Ʒ����
			        String printdate;         //��������
			        String csstauts;         //���״̬
			        String productstatus;     //��Ʒ״̬
					
					
					for(int i = 0; i < ls.size(); i++){
						
						ob = (Object[])ls.get(i);
						storage = (InventoryStorage)ob[0];
			  	 		space = (BaseCargospace)ob[1];
			  	 		whArea = storage.getWhAreaName();		//����
			  	 		cargoSpace = storage.getCargoSpaceName();//��λ
			  	 		requestid = storage.getRequestid();	//���뵥��
			  	 		instockid = storage.getInstockid();	//��ⵥ��
			  	 		productcode = storage.getProductcode(); //��Ʒ����		
			  	 		product = storage.getProductName();		//��Ʒ
			  	 		indate = storage.getIndate();       //���ʱ��
			  	 		lotnumber = storage.getLotinfo();		//����
			  	 		pnum = storage.getPnum();           //�������
			  	 		printdate = storage.getProductdate(); //��������
			  	 		csstauts = space.getCsstatusname(); //���״̬
			  	 		productstatus = storage.getProductstatus(); //��Ʒ״̬
//			  	 		traycode = storage.getTraycode();		//��������
//			  	 		punit = storage.getPunitname();			//��λ
						
			  	 		intype = storage.getIntype();		//��ⷽʽ 1.���� 2.�ѻ�
			  	 		
			  	 		
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						label = new Label(1, 3+i, whArea, wcf1);  //����
						wws.addCell(label);
						label = new Label(2, 3+i, cargoSpace, wcf1); //��λ
						wws.addCell(label);
						label = new Label(3+iLine, 3+i, productcode, wcf1);//��Ʒ����
						wws.addCell(label);
						label = new Label(4+iLine, 3+i, product, wcf1);//Ʒ��
						wws.addCell(label);
						label = new Label(5+iLine, 3+i, indate, wcf1);//���ʱ��
						wws.addCell(label);
						label = new Label(6+iLine, 3+i, lotnumber, wcf1);//����
						wws.addCell(label);
			     		label = new Label(7+iLine, 3+i, String.valueOf(pnum) , wcf1);//�������
						wws.addCell(label);
						label = new Label(8+iLine, 3+i, printdate, wcf1);//��������
						wws.addCell(label);
						label = new Label(9+iLine, 3+i, csstauts, wcf1);//���״̬
						wws.addCell(label);
						label = new Label(10+iLine, 3+i, productstatus, wcf1); //��Ʒ״̬
						wws.addCell(label);
						label = new Label(11+iLine, 3+i, instockid, wcf1); //��ⵥ��
						wws.addCell(label);
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("������==>����ѯ==>����Excel�ļ�ʧ�ܣ�InventoryQueryExcel.excelReport��:" + er.getMessage());	
			throw new Exception("������==>����ѯ==>����Excel�ļ�ʧ��:" + er.getMessage());
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
