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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//货位

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype"));	//入库模式
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		String isback = CommonTools.getStrToGbk(request.getParameter("isback"));			//是否回流
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//作业来源
		
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));	//入库单号
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//批号信息
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
     
       
		//inventoryBus = new InventoryBusImpl(dao);
        try {
        	InoutJob job = new InoutJob();
			String inOutType = "1"; // 入库作业
			/*查询*/
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid,whAreaId,alleyId,cargospaceid,onlinetype,indate_from,indate_to,isback,invoicetype,instockid,productid,lotinfo,traycode,inOutType);
           // String strCountHQL = job.getQueryJobADetailHQLCount(warehouseid,whAreaId,alleyId,cargospaceid,onlinetype,indate_from,indate_to,isback,invoicetype,instockid,productid,lotinfo,traycode,inOutType);
            
			//PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);
			
			List ls = dao.searchEntities(strQueryHQL);
			
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel文档
    		{	
    			excelReport(ls, filePath,request,response, strCompanyName);
    		}else if(fileType!= null && fileType.trim().equals("word")) //word文档
    		{
    			wordReport(ls, filePath, response, strCompanyName);
    		}else if(fileType!= null && fileType.trim().equals("html"))	//html文档
    		{
    			htmlReport(ls, filePath, response, strCompanyName);
    		}else if(fileType!= null && fileType.trim().equals("pdf"))	//PDF文档
    		{
    			pdfReport(ls, filePath, response, strCompanyName);
    		}
		} catch (Exception er) {
			throw new Exception("库存管理==>库存查询==>创建Excel文件失败:" + er.getMessage());
		}
		return null;
	}
	/**
	 * 功能：生成EXCEL报表
	 *@author liuxh 2013-2-4
	 *@param ls 查询到的list集合
	 *@param filePath 文件路径和名称
	 *@param request
	 *@param response
	 *@param strCompanyName 公司名称
	 * @throws Exception 
	 */
	private void excelReport(List ls, String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
		try {
			
			 int iLine = 0;	//显示的批次属性个数
			 
			 response.reset();
			 String downFileName = "";
			 downFileName = filePath.substring(filePath.lastIndexOf(File.separator)+1,filePath.length());
			 
			 Date newDate = new Date();
			 String strTime = StrTools.getCurrDateTime(newDate, 8);
			 downFileName = downFileName.substring(0, downFileName.length() - 4)
				+ "_" + strTime + ".xls";
			 downFileName = new String(downFileName.getBytes("GB2312"),"ISO-8859-1");
			 response.setHeader("Content-disposition","attachment;filename="+downFileName); 
			 //设置输出流的MIME类型
			 response.setContentType("application/txt;charset=gb2312");
			 //创建工作薄
			 OutputStream out = response.getOutputStream();
			 WritableWorkbook wwb = Workbook.createWorkbook(out);
			 //创建工作表
			 WritableSheet wws = wwb.createSheet("第一页",0);
			 //设置字体和颜色
			 WritableFont wwf = new WritableFont(WritableFont.createFont("宋体_GB2312"),18,WritableFont.NO_BOLD);
			 WritableCellFormat wcf = new WritableCellFormat(wwf);
			 wcf.setAlignment(Alignment.CENTRE);//设置水平居中
			 wcf.setVerticalAlignment(VerticalAlignment.CENTRE);//设置垂直对齐方式居中
			 wcf.setWrap(true);//设置自动换行
			 WritableFont wwf1 = new WritableFont(WritableFont.createFont("宋体_GB2312"),10,WritableFont.NO_BOLD);
			 WritableCellFormat wcf1 = new WritableCellFormat(wwf1);
			 wcf1.setAlignment(Alignment.CENTRE);//设置水平居中
			 wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);//设置垂直对齐方式居中
			 wcf1.setWrap(true);//设置自动换行
			 wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);//设置细边框
			 wcf.setBorder(Border.ALL, BorderLineStyle.THIN);//设置细边框
			 
			 //表头
			 wws.mergeCells(0, 0, 16, 1);//第一列第一行到第二十五列第一行合并
			 Label label = new Label(0, 0,"入库查询",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 label = new Label(0, 2, "行号", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "作业号", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "作业类型", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "产品编码", wcf1);
			 wws.addCell(label);
			 label = new Label(4, 2, "品名", wcf1);
			 wws.addCell(label);
			 label = new Label(5, 2, "具体批号", wcf1);
			 wws.addCell(label);
			 label = new Label(6, 2, "生产日期", wcf1);
			 wws.addCell(label);
			 label = new Label(7, 2, "单位", wcf1);
			 wws.addCell(label);
			 label = new Label(8, 2, "数量", wcf1);
			 wws.addCell(label);
			 label = new Label(9, 2, "仓库", wcf1);
			 wws.addCell(label);
     		 label = new Label(10, 2, "巷道", wcf1);
			 wws.addCell(label);
			 label = new Label(11, 2, "货位", wcf1);
			 wws.addCell(label);
			 label = new Label(12, 2, "托盘条码", wcf1);
			 wws.addCell(label);
			 label = new Label(13, 2, "输送机号", wcf1);
			 wws.addCell(label);
			 label = new Label(14, 2, "生成日期", wcf1);
			 wws.addCell(label);
			 label = new Label(15, 2, "完成时间", wcf1);
			 wws.addCell(label);
			 label = new Label(16, 2, "作业来源", wcf1);
			 wws.addCell(label);
			 
			 if(ls != null && ls.size()>0){
					
					Object[] obj = null;
					InoutJob job = null;
					InoutJobdetail detail = null;
			     	
			     	String jobid;      	//作业号
			     	String jobtype;    //作业类型 （回流的作业类型为 入库类型的hl值）
			     	String productCode;//产品编码
			     	String m_strProductName = null;	 //品名
					String lotinfo = null;	         //具体批号
					String printdate = null;	     //生产日期
					String m_strUnitName = null;	//单位
					double jobnum = 0;			    //数量
					String warehouseName;  	//仓库
			     	String alley;    		//巷道
			     	String cargospace;     	//货位
			     	String traycode;     	//托盘条码
			     	String snumber;    		//输送号
			     	String createtime;  	//生成时间
			     	String finishtime; 		//完成时间
			     	String invoicetype;//1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
			     	
			     	
					
					for(int i = 0; i < ls.size(); i++){
						
						obj = (Object[])ls.get(i);
						job = (InoutJob)obj[0];
						detail = (InoutJobdetail)obj[1];
						
						jobid = job.getJobid();      			//作业号
			     		jobtype = job.getJobtypeName();
						productCode = detail.getProductcode();
						m_strProductName = detail.getM_strProductName();
						lotinfo = detail.getLotinfo();
						printdate = detail.getPrintdate();
						m_strUnitName = detail.getM_strUnitName();
						jobnum = detail.getJobnum();
						
			     		warehouseName = job.getWarehousename();	//仓库
			     		alley = job.getTcargoAlleyName();		//巷道
			     		cargospace = job.getTcargoSpaceName(); 	//货位
			     		traycode = job.getTraycode();     		//托盘条码
			     		snumber = job.getSnumber();    			//输送号
			     		createtime = job.getCreatetime();  		//生成时间
			     		finishtime = job.getFinishtime(); 		//完成时间
			     		invoicetype = job.getInvoicetypename();
			  	 		
			  	 		
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						label = new Label(1, 3+i, jobid==null?"":jobid, wcf1);  //作业号
						wws.addCell(label);
						label = new Label(2, 3+i, jobtype==null?"":jobtype, wcf1); //作业类型
						wws.addCell(label);
						label = new Label(3, 3+i, productCode==null?"":productCode, wcf1);    //产品编码
						wws.addCell(label);
						 label = new Label(4, 3+i, m_strProductName==null?"":m_strProductName, wcf1);	//品名
						 wws.addCell(label);
						 label = new Label(5, 3+i, lotinfo==null?"":lotinfo, wcf1);//批号
						 wws.addCell(label);
						 label = new Label(6, 3+i, printdate==null?"":printdate, wcf1);//生产日期
						 wws.addCell(label);
						 label = new Label(7, 3+i, m_strUnitName==null?"":m_strUnitName, wcf1);//单位
						 wws.addCell(label);
						 label = new Label(8, 3+i, String.valueOf(jobnum), wcf1);//数量
						 wws.addCell(label);
			     		 label = new Label(9, 3+i, warehouseName==null?"":warehouseName , wcf1);//仓库
						 wws.addCell(label);
						 label = new Label(10, 3+i, alley==null?"":alley, wcf1);//巷道
						 wws.addCell(label);
						 label = new Label(11, 3+i, cargospace==null?"":cargospace, wcf1);//货位
						 wws.addCell(label);
						 label = new Label(12, 3+i, traycode==null?"":traycode, wcf1); //托盘条码
						 wws.addCell(label);
						 label = new Label(13, 3+i, snumber==null?"":snumber, wcf1); //输送机号
						 wws.addCell(label);
						 label = new Label(14, 3+i, createtime==null?"":createtime, wcf1); //生成时间
						 wws.addCell(label);
						 label = new Label(15, 3+i, finishtime==null?"":finishtime, wcf1); //完成时间
						 wws.addCell(label);
						 label = new Label(16, 3+i, invoicetype==null?"":invoicetype, wcf1); //作业来源
						 wws.addCell(label);						 
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("入库管理==>入库查询==>导出Excel文件失败（InventoryQueryExcel.excelReport）:" + er.getMessage());	
			throw new Exception("入库管理==>入库查询==>导出Excel文件失败:" + er.getMessage());
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
