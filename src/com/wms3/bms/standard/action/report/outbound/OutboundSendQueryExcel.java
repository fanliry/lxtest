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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String senddate_from = CommonTools.getStrToGbk(request.getParameter("senddate_from"));	//发货日期
		String senddate_to = CommonTools.getStrToGbk(request.getParameter("senddate_to"));		//发货日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//品名
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//客户
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));              //批次ID
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//作业号
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		int maxLine = 20;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
     
       
        outboundBus = new OutBoundBusImpl(dao);
        try {
        	String[] strSql = outboundBus.getOutboundSend(warehouseid, whAreaId, senddate_from, senddate_to, shiftid, productid, customerid, traycode, 
					lotinfo);
			List ls = dao.searchEntities(strSql[1]);
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
			 wws.mergeCells(0, 0, 11+iLine, 1);//第一列第一行到第二十五列第一行合并
			 Label label = new Label(0, 0,"发货查询报表",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 label = new Label(0, 2, "行号", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "单据编号", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "客户", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "车牌号", wcf1);
			 wws.addCell(label);
			 label = new Label(4+iLine, 2, "车位", wcf1);
			 wws.addCell(label);
			 label = new Label(5+iLine, 2, "品名", wcf1);
			 wws.addCell(label);
			 label = new Label(6+iLine, 2, "托盘条码", wcf1);
			 wws.addCell(label);
			 label = new Label(7+iLine, 2, "货位", wcf1);
			 wws.addCell(label);
			 label = new Label(8+iLine, 2, "数量", wcf1);
			 wws.addCell(label);
			 label = new Label(9+iLine, 2, "毛重", wcf1);
			 wws.addCell(label);
     		 label = new Label(10+iLine, 2, "净重", wcf1);
			 wws.addCell(label);
			 label = new Label(11+iLine, 2, "体积", wcf1);
			 wws.addCell(label);
			 
			 if(ls != null && ls.size()>0){
					
					//保留小数2位
					NumberFormat nbf=NumberFormat.getInstance();      
					nbf.setMinimumFractionDigits(2);
					nbf.setMaximumFractionDigits(2); 
					
					Object[] obj = null;
					
					String invoiceid = null;	// 单据编号
					String customer = null;     // 客户
					String vehicleno = null;	// 车牌号
					String vehiclepos = null;	// 车位
					String product = null;		// 产品
				 	String traycode = null;     // 托盘条码
				 	String cargospace = null;   // 货位
				 	String jobid = null;		// 作业号
					double jobnum = 0;			// 数量
					double volume = 0;         	// 体积
						double weight = 0;         	// 重量
					double netweight = 0;		// 净重
					
					for(int i = 0; i < ls.size(); i++){
						
						obj = (Object[])ls.get(i);
						invoiceid = obj[0] == null ? "" : obj[0].toString();	// 单据编号
						customer = obj[1] == null ? "" : obj[1].toString();		// 客户
						vehicleno = obj[2] == null ? "" : obj[2].toString();	// 车牌号
						vehiclepos = obj[3] == null ? "" : obj[3].toString();	// 车位
						product = obj[4] == null ? "" : obj[4].toString();		// 产品
						traycode = obj[5] == null ? "" : obj[5].toString();		// 托盘条码
						cargospace = obj[6] == null ? "" : obj[6].toString();	// 货位
						
						//jobid = obj[7] == null ? "" : obj[7].toString();		// 作业号
						
						jobnum = Double.parseDouble(obj[7].toString().trim());			// 数量
						//boxnum = Long.parseLong(obj[9].toString());				// 箱数
						//boxnum =(Long)obj[9];
						volume = Double.parseDouble(obj[8].toString());      	// 体积
						weight = Double.parseDouble(obj[9].toString());     	// 重量
						netweight = Double.parseDouble(obj[10].toString());		// 净重
						
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						label = new Label(1, 3+i, invoiceid, wcf1);  //单据编号
						wws.addCell(label);
						label = new Label(2, 3+i, customer, wcf1); //客户
						wws.addCell(label);
						label = new Label(3, 3+i, vehicleno, wcf1);    //车牌号
						wws.addCell(label);
						 label = new Label(4+iLine, 3+i, vehiclepos, wcf1);	//车位	
						 wws.addCell(label);
						 label = new Label(5+iLine, 3+i, product, wcf1);//产品
						 wws.addCell(label);
						 label = new Label(6+iLine, 3+i, traycode, wcf1);//托盘条码
						 wws.addCell(label);
						 label = new Label(7+iLine, 3+i, cargospace, wcf1);//货位
						 wws.addCell(label);
						 label = new Label(8+iLine, 3+i, String.valueOf((int)jobnum), wcf1);//数量
						 wws.addCell(label);
			     		 label = new Label(9+iLine, 3+i, String.valueOf(volume) , wcf1);//体积
						 wws.addCell(label);
						 label = new Label(10+iLine, 3+i, String.valueOf(weight), wcf1);//重量
						 wws.addCell(label);
						 label = new Label(11+iLine, 3+i, String.valueOf(netweight), wcf1);//净重
						 wws.addCell(label);
						 
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("出库管理==>发货查询==>导出Excel文件失败（InventoryQueryExcel.excelReport）:" + er.getMessage());	
			throw new Exception("出库管理==>发货查询==>导出Excel文件失败:" + er.getMessage());
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
