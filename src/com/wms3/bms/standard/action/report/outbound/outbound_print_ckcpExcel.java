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
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

public class outbound_print_ckcpExcel extends DownReport {
	protected IOutBoundBus outboundBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//出库单据编号
        try {
        	outboundBus = new OutBoundBusImpl(dao);
        	OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//出库单
			List ls = outboundBus.getOutBoundDetailsById(outstockid);				//出库单详细
			//List ls = dao.searchEntities(strSql[1]);
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel文档
    		{	
    			excelReport(ls, outbound,filePath,request,response, strCompanyName);
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
	private void excelReport(List ls,OutboundInvoiceHeader invoice,String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
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
			 
			// 表头
				wws.mergeCells(0, 0, 7  , 1);// 第一列第一行到第二十五列第一行合并
				Label label = new Label(0, 0, "出库产品", wcf);
				wws.addCell(label);
				// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
				label = new Label(0, 1, "", wcf1);
				wws.addCell(label);
				
				label = new Label(0, 2, "收货仓库", wcf1);
				wws.addCell(label);
				label = new Label(1, 2, "收货单位", wcf1);
				wws.addCell(label);
				label = new Label(2, 2, "出库类型", wcf1);
				wws.addCell(label);
				label = new Label(3 + iLine, 2, "制单人", wcf1);
				wws.addCell(label);
				label = new Label(4 + iLine, 2, "制单日期", wcf1);
				wws.addCell(label);
				label = new Label(5 + iLine, 2, "单据编号", wcf1);
				wws.addCell(label);
				label = new Label(6 + iLine, 2, "", wcf1);
				wws.addCell(label);
				label = new Label(7 + iLine, 2, "", wcf1);
				wws.addCell(label);
				
				
				if (invoice != null) {
					label = new Label(0, 3 , invoice.getWarehousename(), wcf1); 
					wws.addCell(label);
					label = new Label(1, 3 , invoice.getCustomername(), wcf1); 
					wws.addCell(label);
					label = new Label(2, 3 , invoice.getM_strOutTypeName(), wcf1); 
					wws.addCell(label);
					label = new Label(3 + iLine, 3 , invoice.getM_strOpManName(), wcf1); 
					wws.addCell(label);
					label = new Label(4 + iLine, 3 , invoice.getFormdate(), wcf1);
					wws.addCell(label);
					label = new Label(5 + iLine, 3 , invoice.getOutstockid(), wcf1);
					wws.addCell(label);
					label = new Label(6 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					label = new Label(7 + iLine, 3 , "", wcf1);
					wws.addCell(label);
					
				}
				label = new Label(0, 4, "", wcf1);
				wws.addCell(label);
				
				label = new Label(0 , 5, "行号", wcf1);
				wws.addCell(label);
				label = new Label(1 , 5, "产品编码", wcf1);
				wws.addCell(label);
				label = new Label(2 , 5, "品名", wcf1);
				wws.addCell(label);
				label = new Label(3 , 5, "生产日期", wcf1);
				wws.addCell(label);
				label = new Label(4 , 5, "批号", wcf1);
				wws.addCell(label);
				label = new Label(5 , 5, "单位", wcf1);
				wws.addCell(label);
				label = new Label(6 , 5, "计划数量", wcf1);
				wws.addCell(label);
				label = new Label(7 , 5, "发货数量", wcf1);
				wws.addCell(label);
				
				
				
				
				 if(ls!=null && !ls.isEmpty()){
					    
				    	//保留小数2位
						NumberFormat nbf=NumberFormat.getInstance();      
						nbf.setMinimumFractionDigits(2);
						nbf.setMaximumFractionDigits(2); 
					
						OutboundInvoiceDetail detail = null;	//出库单详细  
				        
				      	String productid;		
				      	String productCode;//产品编码
					  	String productName;		//品名
				      	String printdate;		//生产日期
				      	String lotinfo;			//批号
				      	String unitName;		//单位
				      	double plannum;	        //开单数量
				      	double sendnum;	        //发货数量
						String remark;          //备注
				     	
						for(int i = 0; i < ls.size(); i++){
							detail = (OutboundInvoiceDetail)ls.get(i);
							
					      	productid = detail.getProductid();
					      	productCode = detail.getM_strProductCode();			
					      	productName = detail.getM_strProductName();   	
				         	printdate = detail.getPrintdate(); 		 
				         	lotinfo = detail.getLotinfo();       		
				         	unitName = detail.getM_strUnitName();       		
					      	plannum = detail.getInvoicenum();		
						  	sendnum = detail.getSendnum();	
					
						label = new Label(0, 6 + i, String.valueOf(i + 1), wcf1);
						wws.addCell(label);
						label = new Label(1, 6 + i, productCode==null?"":productCode, wcf1); // 产品编号
						wws.addCell(label);
						label = new Label(2, 6 + i, productName==null?"":productName, wcf1); // 品名
						wws.addCell(label);
						label = new Label(3, 6 + i, printdate==null?"":printdate, wcf1); // 生产日期
						wws.addCell(label);
						label = new Label(4 , 6 + i, lotinfo==null?"":lotinfo, wcf1); // 托盘条码
						wws.addCell(label);
						label = new Label(5 , 6 + i, unitName==null?"":unitName, wcf1); // 批号
						wws.addCell(label);
						
						label = new Label(6 , 6 + i, nbf.format(plannum), wcf1);// 单位
						wws.addCell(label);
						label = new Label(7 , 6 + i, nbf.format(sendnum) , wcf1);// 入库数量
						wws.addCell(label);
						
						
					}
					wwb.write();
					wwb.close();
				}
			} catch (Exception er) {
				Logger
						.error("出库管理==>单据管理==>出库产品打印==>导出Excel文件失败（InventoryQueryExcel.excelReport）:"
								+ er.getMessage());
				throw new Exception("出库管理==>单据管理==>出库产品打印==>导出Excel文件失败:" + er.getMessage());
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
