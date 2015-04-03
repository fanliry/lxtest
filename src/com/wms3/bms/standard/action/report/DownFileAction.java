package com.wms3.bms.standard.action.report;



import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream; 
import java.util.Hashtable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.constant.BMSConstant;


/**
 * 描述:报表文件下载
 * @author gui
 *
 */
public class DownFileAction extends AbstractAction
{
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		String strPath = (String)hsSysParam.get("path");
		String strUserCode = (String)request.getSession().getAttribute("userCode");
        BMSConstant constant = (BMSConstant)hsSysParam.get("ClassURL");
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		//1:已存在的文件下载,2:生成文件下载
		String strFlag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		//文件名
		String strName = CommonTools.getStrToGb2312(request.getParameter("name"));
		//文件类型excel word, pdf...
		String strFileType = request.getParameter("fileType");
		//报表类名
		String strClass = CommonTools.getStrToGb2312(request.getParameter("classname"));
		
		//报表公司名称
		String strCompanyName = constant.getName();
		
		if(strFlag != null && strFlag.trim().equals("1"))//已存在文件下载
		{
			String path = strPath + "report\\"+strName;
			
			downFile(request, response, path); //下载
					
		}else //报表下载
		{
			String path = strPath + "report\\"+strUserCode+"@"+strName;
				
			//根据报表类型，去调用相应方式实现 		
			judgeDownType(request, response, path, strFileType, dao, strClass, strCompanyName);
	
		}
		return null;
	}
	//报表类型
	public void judgeDownType(HttpServletRequest request, HttpServletResponse response, String filePath, String fileType, EntityDAO dao, String strClass, String strCompanyName)
	{
		try
		{	
			DownReport downReport = (DownReport)Class.forName(strClass).newInstance();
			downReport.createReport(request, response, filePath, fileType, dao, strCompanyName);

		}catch(Exception er)
		{
			Logger.error("生成报错出错:" + er.getMessage());
		}	
	}
	
	/**
	 * 功能:文件下载
	 * @param req
	 * @param res
	 * @param fullName 文件路径及路径名 
	 */
	public void downFile(HttpServletRequest req,	HttpServletResponse res, String fullName)
	{
	    long totalSize=0;
		//	String downFileName="",fullName="";
	    String downFileName = "";
		File f = new File(fullName);
		try{
			//fullName="E:/project1/c.doc";
				  		    
			     
			downFileName = fullName.substring(fullName.lastIndexOf(File.separator)+1,fullName.length());
			downFileName = new String(downFileName.getBytes("GB2312"),"ISO-8859-1");
			//System.out.println("downFileName=" +downFileName);
			    
			long fileLength=f.length();
			byte[] b= new byte[1024];
				
			//设置文件输出流		
			FileInputStream fin = new FileInputStream(f);
			DataInputStream in = new DataInputStream(fin);		
				
			res.setHeader("Content-disposition","attachment;filename="+downFileName);
	      
			//设置输出流的MIME类型
			res.setContentType("application/txt;charset=gb2312");
			//确定文件长度
			String fileSize=String.valueOf(fileLength);
			//设置输出文件的长度
			res.setHeader("Content-Length",fileSize);		
			//取得输出流
			ServletOutputStream servletOut=res.getOutputStream();		
			//发送文件数据,每次1024字节,最后单独计算
				
			while(totalSize<fileLength){
				totalSize+=1024;
				if(totalSize>fileLength){
					byte[]leftPart=new byte[1024-(int)(totalSize-fileLength)];
					in.readFully(leftPart);
					servletOut.write(leftPart);
				}
				else{
					in.readFully(b);
					servletOut.write(b);
				}
			}
		    servletOut.close();
		    res.setStatus(HttpServletResponse.SC_OK ); 
			res.flushBuffer();
			in.close();
			fin.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				if(f.exists()){//检查File.txt是否存在 
					f.delete();//删除File.txt文件   
				}
			}
			catch(Exception e){
				Logger.info("err="+e.getMessage());
			}

		}
	}
}
