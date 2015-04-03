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
		
		String checkType = CommonTools.getStrToGbk(request.getParameter("checkType"));	//盘点类型
		String fmDate = CommonTools.getStrToGbk(request.getParameter("fmDate"));	//from时间
		String toDate = CommonTools.getStrToGbk(request.getParameter("toDate"));	//to时间
		IInventoryCheckBus iCheckBus = new InventoryCheckBusImpl(dao);
     
        try {
        	String strUrl = "/standard/jsp/inventory/checksearch/kc_checksearch_report.jsp";
			CommonReturn cReturn = iCheckBus.getCheckResultsByTimeAndType(checkType, fmDate, toDate);
			List ls = dao.searchEntities(cReturn.getStrQueryHQL());
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
			 downFileName = downFileName.substring(0, downFileName.length()-4) + "_" + strTime+".xls";
			 
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
			 wws.mergeCells(0, 0, 9, 1);//第一列第一行到第二十五列第一行合并
			 Label label = new Label(0, 0,"盘点查询报表",wcf);
			 wws.addCell(label);
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 
			 label = new Label(0, 2, "行号", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "仓库", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "单号", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "任务号", wcf1);
			 wws.addCell(label);
			 label = new Label(4, 2, "产品名", wcf1);
			 wws.addCell(label);
			 label = new Label(5, 2, "批号", wcf1);
			 wws.addCell(label);
			 label = new Label(6, 2, "库存数量", wcf1);
			 wws.addCell(label);
			 label = new Label(7, 2, "盘点数量", wcf1);
			 wws.addCell(label);
			 label = new Label(8, 2, "操作员", wcf1);
			 wws.addCell(label);
			 label = new Label(9, 2, "盘点时间", wcf1);
			 wws.addCell(label);
			 if(ls != null && ls.size()>0){ 
					
				    InventoryCheckTask checkTask = null;
					InventoryCheckResult checkResult = null;
					Object[] obj = new Object[2];
			   		String strWarehouseId = "";		    // 仓库
					String strCheckReq = "";			// 盘点申请
					String strCheckTask = "";			// 任务
					String strProductName = "";         //产品名
					String strLotinfo = "";				// 批号
					double strpnum =0.0;                 //库存数量
					double strCheckNum =0.0;            //盘点数量
					String strOpManName = "";			// 操作员名
					String strOpManTime = "";			// 操作时间
					
					
					for(int i = 0; i < ls.size(); i++){
						
						obj = (Object[])ls.get(i);
			   			checkResult = (InventoryCheckResult)obj[0];  //盘点结果
			   			checkTask = (InventoryCheckTask)obj[1];//盘点任务
			   	   		strWarehouseId = checkTask.getWarehouseid();		    // 仓库
			   			strCheckReq = checkResult.getRequestid();			// 盘点申请
			   			strCheckTask = checkResult.getTaskid();			// 任务
			   			strProductName = checkResult.getProductName();         //产品名
			   			strLotinfo = checkResult.getLotnumber();				// 批号
			   			strpnum =checkResult.getNum();                 //库存数量
			   			strCheckNum =checkResult.getChecknum();            //盘点数量
			   			strOpManName = checkResult.getCreateman();			// 操作员名
			   			strOpManTime =checkResult.getChecktime() ;			// 操作时间
			  	 		
			  	 		
						  //             列号     行号     显示数据     属性格式 
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
			Logger.error("库存管理==>盘点查询==>导出Excel文件失败（CheckInventoryQueryExcel.excelReport）:" + er.getMessage());	
			throw new Exception("库存管理==>盘点查询==>导出Excel文件失败:" + er.getMessage());
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
