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
	protected int maxLine = 20; // 分页显示的行数；

	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {

		String instockid = CommonTools.getStrToGbk(request
				.getParameter("instockid")); // 单据号
		

		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // 每页显示行数
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}

		try {
			
			InventoryQualityBusImpl inventoryQB= new InventoryQualityBusImpl(dao);
			
			InventoryQualityResult invoice= inventoryQB.getAdjustListToId(instockid);
			List ls = inventoryQB.getAdjustDetailListToId(instockid);
			

			if (fileType != null && fileType.trim().equals("excel")) // excel文档
			{
				excelReport(ls, invoice, filePath, request, response,
						strCompanyName);
			} else if (fileType != null && fileType.trim().equals("word")) // word文档
			{
				wordReport(ls, filePath, response, strCompanyName);
			} else if (fileType != null && fileType.trim().equals("html")) // html文档
			{
				htmlReport(ls, filePath, response, strCompanyName);
			} else if (fileType != null && fileType.trim().equals("pdf")) // PDF文档
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
	 * 
	 * @author liuxh 2013-2-4
	 *@param ls
	 *            查询到的list集合
	 *@param filePath
	 *            文件路径和名称
	 *@param request
	 *@param response
	 *@param strCompanyName
	 *            公司名称
	 * @throws Exception
	 */
	private void excelReport(List ls, InventoryQualityResult invoice,
			String filePath, HttpServletRequest request,
			HttpServletResponse response, String strCompanyName)
			throws Exception {

		try {

			int iLine = 0; // 显示的批次属性个数

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
			// 设置输出流的MIME类型
			response.setContentType("application/txt;charset=gb2312");
			// 创建工作薄
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			// 创建工作表
			WritableSheet wws = wwb.createSheet("第一页", 0);
			// 设置字体和颜色
			WritableFont wwf = new WritableFont(WritableFont
					.createFont("宋体_GB2312"), 18, WritableFont.NO_BOLD);
			WritableCellFormat wcf = new WritableCellFormat(wwf);
			wcf.setAlignment(Alignment.CENTRE);// 设置水平居中
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 设置垂直对齐方式居中
			wcf.setWrap(true);// 设置自动换行
			WritableFont wwf1 = new WritableFont(WritableFont
					.createFont("宋体_GB2312"), 10, WritableFont.NO_BOLD);
			WritableCellFormat wcf1 = new WritableCellFormat(wwf1);
			wcf1.setAlignment(Alignment.CENTRE);// 设置水平居中
			wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);// 设置垂直对齐方式居中
			wcf1.setWrap(true);// 设置自动换行
			wcf1.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置细边框
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);// 设置细边框

			// 表头
			wws.mergeCells(0, 0, 6, 1);// 第一列第一行到第二十五列第一行合并
			Label label = new Label(0, 0, "放行记录查询", wcf);
			wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
			label = new Label(0, 1, "", wcf1);
			wws.addCell(label);
			
			label = new Label(0, 2, "放行单号", wcf1);
			wws.addCell(label);
			label = new Label(1, 2, "操作人员", wcf1);
			wws.addCell(label);
			label = new Label(2, 2, "单据日期", wcf1);
			wws.addCell(label);
			label = new Label(3 + iLine, 2, "批号", wcf1);
			wws.addCell(label);
			label = new Label(4 + iLine, 2, "数量", wcf1);
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
			
			label = new Label(0, 5, "行号", wcf1);
			wws.addCell(label);
			label = new Label(1, 5, "品名", wcf1);
			wws.addCell(label);
			label = new Label(2, 5, "创建日期", wcf1);
			wws.addCell(label);
			label = new Label(3 + iLine, 5, "物品新状态", wcf1);
			wws.addCell(label);
			label = new Label(4 + iLine, 5, "物品原状态", wcf1);
			wws.addCell(label);
			label = new Label(5 + iLine, 5, "货位号", wcf1);
			wws.addCell(label);
			label = new Label(6 + iLine, 5, "数量", wcf1);
			wws.addCell(label);
			
			
			
			
			 if(ls!=null && !ls.isEmpty()){
				    
			    	//保留小数2位
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
			             strDId = detail.getM_strCheckResultId();        //状态转换单号
			        	 strProductName = detail.getM_strProductName();  //产品名
			        	 strCreatetime = detail.getM_strPrintDate();     //创建日期
			        	 strNewProductStatus = detail.getM_strNewProductStatusName();	 //新物品状态
			        	 strOldProductStatus = detail.getM_strOldProductStatusName();	 //原物品状态
			        	 strClientName = detail.getM_strLotNumber();    //批号
			        	 strCarspaceid = detail.getM_strCarspaceid();    //货位号
			        	 iProductNum = detail.getM_iProductNum();	     //数量	
				
					label = new Label(0, 6 + i, String.valueOf(i + 1), wcf1);
					wws.addCell(label);
					label = new Label(1, 6 + i, strProductName, wcf1); // 仓库名称
					wws.addCell(label);
					label = new Label(2, 6 + i, strCreatetime, wcf1); // 产品编号
					wws.addCell(label);
					label = new Label(3, 6 + i, strNewProductStatus, wcf1); // 品名
					wws.addCell(label);
					label = new Label(4 , 6 + i, strOldProductStatus, wcf1); // 生产日期
					wws.addCell(label);
					label = new Label(5 , 6 + i, strCarspaceid, wcf1);// 批号
					wws.addCell(label);
					label = new Label(6 , 6 + i, String.valueOf(iProductNum) , wcf1);// 托盘号
					wws.addCell(label);
					
					
				}
				wwb.write();
				wwb.close();
			}
		} catch (Exception er) {
			Logger
					.error("质量管理==>放行记录查询==>导出Excel文件失败（InventoryQueryExcel.excelReport）:"
							+ er.getMessage());
			throw new Exception("质量管理==>放行记录查询==>导出Excel文件失败:" + er.getMessage());
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
