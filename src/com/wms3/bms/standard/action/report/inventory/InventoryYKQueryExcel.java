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
	protected int maxLine = 20;			//分页显示的行数；
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		//from
		String movementId = CommonTools.getStrToGbk(request.getParameter("movementId"));	//ID
		
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数 
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
        try {

        	InventoryMovementHeader movementHeader = new InventoryMovementHeader();
			InventoryMovementDetail movementDetail = new InventoryMovementDetail();
			
			InventoryMovementHeader moveHeader = movementHeader.getIMoveHeader(movementId,dao);
			
			List ls = movementDetail.getMoveDetailListToId(movementId, dao);
			
			
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel文档
    		{	
    			excelReport(moveHeader,ls, filePath,request,response, strCompanyName);
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
	private void excelReport(InventoryMovementHeader moveHeader,List ls, String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
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
			 wws.mergeCells(0, 0, 10+iLine, 1);//第一列第一行到第二十五列第一行合并
			 Label label = new Label(0, 0,"移库查询报表",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 
			 label = new Label(0, 2, "仓库名称", wcf1);
				wws.addCell(label);
				label = new Label(1, 2, "制单时间", wcf1);
				wws.addCell(label);
				label = new Label(2, 2, "单据编码", wcf1);
				wws.addCell(label);
				label = new Label(3 + iLine, 2, "制单人", wcf1);
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
				
			 label = new Label(0, 5, "行号", wcf1);
			 wws.addCell(label);
			 
			 label = new Label(1, 5, "产品编码", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 5, "品名", wcf1);
			 wws.addCell(label);
			 label = new Label(3+iLine, 5, "生产日期", wcf1);
			 wws.addCell(label);
			 label = new Label(4+iLine, 5, "批号", wcf1);
			 wws.addCell(label);
			 label = new Label(5+iLine, 5, "托盘号", wcf1);
			 wws.addCell(label);
			 label = new Label(6+iLine, 5, "单位", wcf1);
			 wws.addCell(label);
			
			 label = new Label(7+iLine, 5, "FM库区", wcf1);
			 wws.addCell(label);
     		 label = new Label(8+iLine, 5, "FM库位", wcf1);
			 wws.addCell(label);
			 label = new Label(9+iLine, 5, "TO库区", wcf1);
			 wws.addCell(label);
			 label = new Label(10+iLine, 5, "TO货位", wcf1);
			 wws.addCell(label);
			 
			 if(ls != null && ls.size() > 0){
					
					InventoryMovementDetail iDetail = null;
					
					String strProductCode = null; 	//产品编号
					String strProductName = null; 	//品名
					String strProductDate = null;	//生产日期
					String strLotNum = null;	//批号
					String strTrayNum = null;	//托盘号
					String strPunit = null; //单位
					
					String strFromWhare = null;	//FM库区
					String strFromCargospace = null; //FM货位
					
					String strToWhare = null;	//to货区
					String strToCargospace = null; //to货位
				
					
					
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
						
						label = new Label(1, 6+i, strProductCode, wcf1); //产品编号
						wws.addCell(label);
						label = new Label(2, 6+i, strProductName, wcf1);    //品名
						wws.addCell(label);
						 label = new Label(3+iLine, 6+i, strProductDate, wcf1);	//生产日期	
						 wws.addCell(label);
						 label = new Label(4+iLine, 6+i, strLotNum, wcf1);//批号
						 wws.addCell(label);
						 label = new Label(5+iLine, 6+i, strTrayNum, wcf1);//托盘号
						 wws.addCell(label);
						 label = new Label(6+iLine, 6+i, strPunit, wcf1);//单位
						 wws.addCell(label);
						
			     		 label = new Label(7+iLine, 6+i, strFromWhare , wcf1);//FM库区
						 wws.addCell(label);
						 label = new Label(8+iLine, 6+i, strFromCargospace, wcf1);//FM货位
						 wws.addCell(label);
						 label = new Label(9+iLine, 6+i, strToWhare, wcf1);//to货区
						 wws.addCell(label);
						 label = new Label(10+iLine, 6+i, strToCargospace, wcf1); //to货位
						 wws.addCell(label);
						 
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("库存管理==>移库查询==>导出Excel文件失败（InventoryQueryExcel.excelReport）:" + er.getMessage());	
			throw new Exception("库存管理==>移库查询==>导出Excel文件失败:" + er.getMessage());
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
