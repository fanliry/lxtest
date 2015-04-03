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

public class InventoryWpQueryExcel extends DownReport {
	IInventoryBus inventoryBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区ID
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));		//逻辑库区id
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));//巷道ID
		String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//排
		String column = CommonTools.getStrToGbk(request.getParameter("column"));			//列
		String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//层
		
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));	//货主ID
		String package_id = CommonTools.getStrToGbk(request.getParameter("package_id"));	//品名ID
		String tray_code = CommonTools.getStrToGbk(request.getParameter("tray_code"));		//托盘条码
		
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//入库日期from
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//入库日期to
		
		String total_items = CommonTools.getStrToGbk(request.getParameter("total_items"));  //统计项目
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));      //批号
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));      //产品id
        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));      //进厂编号
        
        String status = CommonTools.getStrToGbk(request.getParameter("status"));            //库存状态
        String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));      //申请单号
        String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));      //入库单号
        String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); //产品状态
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));     //产品编码
       
		//String inventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));   //库存ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
     
       
		inventoryBus = new InventoryBusImpl(dao);
        try {
        	String strSql = inventoryBus.getInventoryWpQuerynew(warehouseid, whAreaId, Virtualwhid, lotinfo, productid, indate_from, indate_to, productstatus);
			List ls = dao.searchEntities(strSql);
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
			 wws.mergeCells(0, 0, 8+iLine, 1);//第一列第一行到第二十五列第一行合并
			 Label label = new Label(0, 0,"物品查询报表",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 label = new Label(0, 2, "行号", wcf1);
			 wws.addCell(label);
			 label = new Label(1, 2, "库区", wcf1);
			 wws.addCell(label);
			 label = new Label(2, 2, "逻辑库区", wcf1);
			 wws.addCell(label);
			 label = new Label(3, 2, "产品编码", wcf1);
			 wws.addCell(label);
			 label = new Label(4+iLine, 2, "品名", wcf1);
			 wws.addCell(label);
			 label = new Label(5+iLine, 2, "进厂编号", wcf1);
			 wws.addCell(label);
			 label = new Label(6+iLine, 2, "数量", wcf1);
			 wws.addCell(label);
			 label = new Label(7+iLine, 2, "生产日期", wcf1);
			 wws.addCell(label);
			 label = new Label(8+iLine, 2, "物品状态", wcf1);
			 wws.addCell(label);
				if(ls != null && ls.size() > 0)
				{
					Object[] ob = null;
					
					String warehouseid;    	//仓库ID
					String whArea;				//库区
			 	 	String product;				//产品
			 	 	String lotinfo;		    	//进厂编号
					double pnum;            	//库存数量
			        String productcode;         //产品编码
			        String printdate;         //生产日期
			        String productstatus;     //物品状态
			        
			        String Virtualwhname = "";
			  	 	for(int i=0; i<ls.size(); i++) 
			  	 	{
			  	 	    ob = (Object[])ls.get(i);
						//SUM(ISNULL(sto.pnum, 0)),sto.warehouseid,sto.whAreaId,sto.whAreaName,sto.Virtualwhid,sto.Virtualwhname,sto.lotinfo,sto.productid,sto.productName,sto.productcode,sto.productdate,sto.productstatus,sto.productStatusName
			  	 	    pnum		=   Double.parseDouble(ob[0].toString());		//库存数量
			  	 	    warehouseid =   (String)ob[1];		//仓库
			  	 		whArea = 		(String)ob[3];		//库区
			  	 	  	Virtualwhname = (String)ob[5];		//逻辑库区
			  	 		lotinfo = 		(String)ob[6];		//进厂编号
			  	 		product =       (String)ob[8];		//产品
			  	 		productcode =   (String)ob[9];		//产品编码		
			  	 		printdate =     (String)ob[10];		//生产日期
			  	 		productstatus = (String)ob[12];		//物品状态
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						label = new Label(1, 3+i, whArea, wcf1);  //库区
						wws.addCell(label);
						label = new Label(2, 3+i, Virtualwhname, wcf1); //逻辑库区
						wws.addCell(label);
						label = new Label(3, 3+i, productcode, wcf1);    //产品编码
						wws.addCell(label);
						label = new Label(4+iLine, 3+i, product, wcf1);	//品名
						wws.addCell(label);
						label = new Label(5+iLine, 3+i, lotinfo, wcf1);//进厂编号
						wws.addCell(label);
						label = new Label(6+iLine, 3+i, String.valueOf(pnum), wcf1);//数量
						wws.addCell(label);
						label = new Label(7+iLine, 3+i, printdate, wcf1);//生产日期
						wws.addCell(label);
						label = new Label(8+iLine, 3+i, productstatus, wcf1);//物品状态
						wws.addCell(label);
					}	
					wwb.write();
					wwb.close();
			 }
		} catch (Exception er) {
			Logger.error("库存管理==>物品查询==>导出Excel文件失败（InventoryQueryExcel.excelReport）:" + er.getMessage());	
			throw new Exception("库存管理==>物品查询==>导出Excel文件失败:" + er.getMessage());
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
