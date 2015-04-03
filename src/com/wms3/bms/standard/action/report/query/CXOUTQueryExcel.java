package com.wms3.bms.standard.action.report.query;



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
import com.wms3.bms.standard.business.inbound.impl.InBoundJobBusImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

public class CXOUTQueryExcel extends DownReport {
	InBoundJobBusImpl inBoundJobBus = null;
	@Override
	public String createReport(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileType,
			EntityDAO dao, String strCompanyName) throws Exception {
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//巷道

		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//作业日期
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//作业来源
		
		String productid = CommonTools.getStrToGbk(request.getParameter("package_id"));		//品名
		String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));		    //货主
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));		//客户
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		
		String boundstockid = CommonTools.getStrToGbk(request.getParameter("boundstockid"));		//单据号
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));		//入库单据类型
		String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));		//出库单据类型
		
		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));              //批次ID
		
		//String inventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));   //库存ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
      
		String groupinfo = CommonTools.getStrToGbk(request.getParameter("groupinfo"));//分组信息
       
		inBoundJobBus = new InBoundJobBusImpl(dao);
        try {
        	List ls = inBoundJobBus.getInboundJobDetailsGroupListByOut(warehouseid, whAreaId, alleyId,  
				    indate_from, indate_to, invoicetype, productid, ownerid, traycode, 
					lotid,"",boundstockid,groupinfo,customer_id,outtype);
			
			
    		if(fileType!= null && fileType.trim().equals("excel"))		//excel文档
    		{	
    			excelReport(ls,groupinfo, filePath,request,response, strCompanyName);
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
	private void excelReport(List ls,String groupinfo, String filePath,HttpServletRequest request, HttpServletResponse response,String strCompanyName) throws Exception {
		
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
			 
			 
			 
			// --customerid|ownerId,1|2,客户|货主--
			    int a = 0;//字段的列数标识,用于标题
			    int b = 0;//用于内容
			   
			    String aem[] = null;
			    String bem[] = null;
		        if(groupinfo != null && groupinfo.trim().length()>0){ //获取字符字段
						
						aem = groupinfo.split(",");
						bem = aem[2].split("\\|");// 属性名
				}
				
				    //表中的列排序
			
			int len = 0;
			if(ls!=null && ls.size()>0){
			  len = ls.size();
			}
			 
			 
			 //表头
			if(bem != null && bem.length>0){
				wws.mergeCells(0, 0, 4+bem.length, 1);//第一列第一行到第二十五列第一行合并
			}else{
				wws.mergeCells(0, 0, 12, 1);//第一列第一行到第二十五列第一行合并
			}
			 
			 Label label = new Label(0, 0,"出库流水查询报表",wcf);
			 wws.addCell(label);
			// wws.mergeCells(0, 1, 24, 1);//第一列第二行到第二五列第二行合并
			 label = new Label(0, 1, "", wcf1);
			 wws.addCell(label);
			 
			 
			 label = new Label(0, 2, "行号", wcf1);
			 wws.addCell(label);
			if(bem != null && bem.length>0){ //获取分组字段
				for(int i=0; i<bem.length; i++){
					 label = new Label(i+1, 2, bem[i], wcf1);
					 wws.addCell(label);
					 a = a+1;
				}
			}else{
				 label = new Label(1, 2, "产品编码", wcf1);
				 wws.addCell(label);
				 label = new Label(2, 2, "品名", wcf1);
				 wws.addCell(label);
				 label = new Label(3, 2, "批号", wcf1);
				 wws.addCell(label);
				 label = new Label(4, 2, "生产日期", wcf1);
				 wws.addCell(label);
				 label = new Label(5, 2, "客户", wcf1);
				 wws.addCell(label);
				 label = new Label(6, 2, "单据编号", wcf1);
				 wws.addCell(label);
				 label = new Label(7, 2, "单据类型", wcf1);
				 wws.addCell(label);
				 label = new Label(8, 2, "作业创建日期", wcf1);
				 wws.addCell(label);
				 a=8;
			}
			 label = new Label(a+1, 2, "数量", wcf1);
			 wws.addCell(label);
			 label = new Label(a+2, 2, "体积", wcf1);
			 wws.addCell(label);
     		 label = new Label(a+3, 2, "重量", wcf1);
			 wws.addCell(label);
			 label = new Label(a+4, 2, "净重", wcf1);
			 wws.addCell(label);
			 
			 
			 
			 if(ls != null && ls.size() > 0)
				{
					Object[] ob = null;
					String createTime = null;   //创建日期
					double pnum;            	//数量
					double volume;          	//体积
					double pweight;         	//重量
			     	double pnetweight;      	//净重

			  	 	for(int i=0; i<ls.size(); i++) 
			  	 	{
						ob = (Object[])ls.get(i);
			  	 		if(bem != null && bem.length>0){ //分组之后
			  	 		    pnum = Double.parseDouble(ob[bem.length].toString());			    //数量
			  	 		    volume = Double.parseDouble(ob[bem.length+1].toString());		    //体积
			  	 		    pweight = Double.parseDouble(ob[bem.length+2].toString());        	//毛重
			  	 		    pnetweight = Double.parseDouble(ob[bem.length+3].toString());     	//净重
			  	 		}else{ //没有分组
			  	 			
			  	 		    pnum = Double.parseDouble(ob[8].toString());			//数量
			  	 		    volume = Double.parseDouble(ob[9].toString());		    //体积
			  	 		    pweight = Double.parseDouble(ob[10].toString());        	//毛重
			  	 		    pnetweight = Double.parseDouble(ob[11].toString());     	//净重
			  	 		}
						
						label = new Label(0, 3+i, String.valueOf(i+1), wcf1);
						wws.addCell(label);
						if(bem != null && bem.length>0){ //获取分组字段
							for(int j=0; j<bem.length; j++){
								label = new Label(j+1, 3+i, ob[j] == null ? "" : ob[j].toString(), wcf1);  
								wws.addCell(label);
								b=b+1;
							}
						}else{
							label = new Label(1, 3+i, ob[0] == null ? "" : ob[0].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(2, 3+i, ob[1] == null ? "" : ob[1].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(3, 3+i, ob[2] == null ? "" : ob[2].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(4, 3+i, ob[3] == null ? "" : ob[3].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(5, 3+i, ob[4] == null ? "" : ob[4].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(6, 3+i, ob[5] == null ? "" : ob[5].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(7, 3+i, ob[6] == null ? "" : ob[6].toString(), wcf1);  
							wws.addCell(label);
							label = new Label(8, 3+i, ob[7] == null ? "" : ob[7].toString(), wcf1);  
							wws.addCell(label);
							b=8;
						}
						
						label = new Label(b+1, 3+i, String.valueOf(pnum), wcf1);  //库区
						wws.addCell(label);
						label = new Label(b+2, 3+i, String.valueOf(volume), wcf1); //库位
						wws.addCell(label);
						label = new Label(b+3, 3+i, String.valueOf(pweight), wcf1);    //申请单号
						wws.addCell(label);
						 label = new Label(b+4, 3+i, String.valueOf(pnetweight), wcf1);	//入库单号	
						 wws.addCell(label);
						b=0; 
					}	
					wwb.write();
					wwb.close();
					
			 }
		} catch (Exception er) {
			Logger.error("查询统计==>出库流水查询==>导出Excel文件失败（CXOUTQueryExcel.excelReport）:" + er.getMessage());	
			throw new Exception("查询统计==>出库流水查询==>导出Excel文件失败:" + er.getMessage());
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
