package com.wms3.bms.standard.action.report.inbound;



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
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

public class inbound_print_rkQueryExcel extends DownReport {
	IInventoryBus inventoryBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//��λ

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype"));	//���ģʽ
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		String isback = CommonTools.getStrToGbk(request.getParameter("isback"));			//�Ƿ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//��ҵ��Դ
		
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));	//��ⵥ��
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//������Ϣ
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
     
       
		//inventoryBus = new InventoryBusImpl(dao);
        try {
        	InoutJob job = new InoutJob();
			String inOutType = "1"; // �����ҵ
			/*��ѯ*/
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid,whAreaId,alleyId,cargospaceid,onlinetype,indate_from,indate_to,isback,invoicetype,instockid,productid,lotinfo,traycode,inOutType);
           // String strCountHQL = job.getQueryJobADetailHQLCount(warehouseid,whAreaId,alleyId,cargospaceid,onlinetype,indate_from,indate_to,isback,invoicetype,instockid,productid,lotinfo,traycode,inOutType);
            
			//PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);
			
			List ls = dao.searchEntities(strQueryHQL);
			
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
			 downFileName = downFileName.substring(0, downFileName.length() - 4)
				+ "_" + strTime + ".xls";
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
			 wws.mergeCells(0, 0, 16, 1);//��һ�е�һ�е��ڶ�ʮ���е�һ�кϲ�
			 Label label = new Label(0, 0,"����ѯ",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//��һ�еڶ��е��ڶ����еڶ��кϲ�
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 label = new Label(0, 2, "�к�", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "��ҵ��", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "��ҵ����", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "��Ʒ����", wcf1);
			 wws.addCell(label);
			 label = new Label(4, 2, "Ʒ��", wcf1);
			 wws.addCell(label);
			 label = new Label(5, 2, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(6, 2, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(7, 2, "��λ", wcf1);
			 wws.addCell(label);
			 label = new Label(8, 2, "����", wcf1);
			 wws.addCell(label);
			 label = new Label(9, 2, "�ֿ�", wcf1);
			 wws.addCell(label);
     		 label = new Label(10, 2, "���", wcf1);
			 wws.addCell(label);
			 label = new Label(11, 2, "��λ", wcf1);
			 wws.addCell(label);
			 label = new Label(12, 2, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(13, 2, "���ͻ���", wcf1);
			 wws.addCell(label);
			 label = new Label(14, 2, "��������", wcf1);
			 wws.addCell(label);
			 label = new Label(15, 2, "���ʱ��", wcf1);
			 wws.addCell(label);
			 label = new Label(16, 2, "��ҵ��Դ", wcf1);
			 wws.addCell(label);
			 
			 if(ls != null && ls.size()>0){
					
					Object[] obj = null;
					InoutJob job = null;
					InoutJobdetail detail = null;
			     	
			     	String jobid;      	//��ҵ��
			     	String jobtype;    //��ҵ���� ����������ҵ����Ϊ ������͵�hlֵ��
			     	String productCode;//��Ʒ����
			     	String m_strProductName = null;	 //Ʒ��
					String lotinfo = null;	         //��������
					String printdate = null;	     //��������
					String m_strUnitName = null;	//��λ
					double jobnum = 0;			    //����
					String warehouseName;  	//�ֿ�
			     	String alley;    		//���
			     	String cargospace;     	//��λ
			     	String traycode;     	//��������
			     	String snumber;    		//���ͺ�
			     	String createtime;  	//����ʱ��
			     	String finishtime; 		//���ʱ��
			     	String invoicetype;//1��������ջ��������ɵ���ҵ��2�������ⵥ���ɵ���ҵ��3�� ����ֱ�����ɵ���ҵ�����ݴ浽����⣩
			     	
			     	
					
					for(int i = 0; i < ls.size(); i++){
						
						obj = (Object[])ls.get(i);
						job = (InoutJob)obj[0];
						detail = (InoutJobdetail)obj[1];
						
						jobid = job.getJobid();      			//��ҵ��
			     		jobtype = job.getJobtypeName();
						productCode = detail.getProductcode();
						m_strProductName = detail.getM_strProductName();
						lotinfo = detail.getLotinfo();
						printdate = detail.getPrintdate();
						m_strUnitName = detail.getM_strUnitName();
						jobnum = detail.getJobnum();
						
			     		warehouseName = job.getWarehousename();	//�ֿ�
			     		alley = job.getTcargoAlleyName();		//���
			     		cargospace = job.getTcargoSpaceName(); 	//��λ
			     		traycode = job.getTraycode();     		//��������
			     		snumber = job.getSnumber();    			//���ͺ�
			     		createtime = job.getCreatetime();  		//����ʱ��
			     		finishtime = job.getFinishtime(); 		//���ʱ��
			     		invoicetype = job.getInvoicetypename();
			  	 		
			  	 		
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						label = new Label(1, 3+i, jobid==null?"":jobid, wcf1);  //��ҵ��
						wws.addCell(label);
						label = new Label(2, 3+i, jobtype==null?"":jobtype, wcf1); //��ҵ����
						wws.addCell(label);
						label = new Label(3, 3+i, productCode==null?"":productCode, wcf1);    //��Ʒ����
						wws.addCell(label);
						 label = new Label(4, 3+i, m_strProductName==null?"":m_strProductName, wcf1);	//Ʒ��
						 wws.addCell(label);
						 label = new Label(5, 3+i, lotinfo==null?"":lotinfo, wcf1);//����
						 wws.addCell(label);
						 label = new Label(6, 3+i, printdate==null?"":printdate, wcf1);//��������
						 wws.addCell(label);
						 label = new Label(7, 3+i, m_strUnitName==null?"":m_strUnitName, wcf1);//��λ
						 wws.addCell(label);
						 label = new Label(8, 3+i, String.valueOf(jobnum), wcf1);//����
						 wws.addCell(label);
			     		 label = new Label(9, 3+i, warehouseName==null?"":warehouseName , wcf1);//�ֿ�
						 wws.addCell(label);
						 label = new Label(10, 3+i, alley==null?"":alley, wcf1);//���
						 wws.addCell(label);
						 label = new Label(11, 3+i, cargospace==null?"":cargospace, wcf1);//��λ
						 wws.addCell(label);
						 label = new Label(12, 3+i, traycode==null?"":traycode, wcf1); //��������
						 wws.addCell(label);
						 label = new Label(13, 3+i, snumber==null?"":snumber, wcf1); //���ͻ���
						 wws.addCell(label);
						 label = new Label(14, 3+i, createtime==null?"":createtime, wcf1); //����ʱ��
						 wws.addCell(label);
						 label = new Label(15, 3+i, finishtime==null?"":finishtime, wcf1); //���ʱ��
						 wws.addCell(label);
						 label = new Label(16, 3+i, invoicetype==null?"":invoicetype, wcf1); //��ҵ��Դ
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
