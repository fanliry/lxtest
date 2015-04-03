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
 * ����:�����ļ�����
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
		
		//1:�Ѵ��ڵ��ļ�����,2:�����ļ�����
		String strFlag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		//�ļ���
		String strName = CommonTools.getStrToGb2312(request.getParameter("name"));
		//�ļ�����excel word, pdf...
		String strFileType = request.getParameter("fileType");
		//��������
		String strClass = CommonTools.getStrToGb2312(request.getParameter("classname"));
		
		//����˾����
		String strCompanyName = constant.getName();
		
		if(strFlag != null && strFlag.trim().equals("1"))//�Ѵ����ļ�����
		{
			String path = strPath + "report\\"+strName;
			
			downFile(request, response, path); //����
					
		}else //��������
		{
			String path = strPath + "report\\"+strUserCode+"@"+strName;
				
			//���ݱ������ͣ�ȥ������Ӧ��ʽʵ�� 		
			judgeDownType(request, response, path, strFileType, dao, strClass, strCompanyName);
	
		}
		return null;
	}
	//��������
	public void judgeDownType(HttpServletRequest request, HttpServletResponse response, String filePath, String fileType, EntityDAO dao, String strClass, String strCompanyName)
	{
		try
		{	
			DownReport downReport = (DownReport)Class.forName(strClass).newInstance();
			downReport.createReport(request, response, filePath, fileType, dao, strCompanyName);

		}catch(Exception er)
		{
			Logger.error("���ɱ������:" + er.getMessage());
		}	
	}
	
	/**
	 * ����:�ļ�����
	 * @param req
	 * @param res
	 * @param fullName �ļ�·����·���� 
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
				
			//�����ļ������		
			FileInputStream fin = new FileInputStream(f);
			DataInputStream in = new DataInputStream(fin);		
				
			res.setHeader("Content-disposition","attachment;filename="+downFileName);
	      
			//�����������MIME����
			res.setContentType("application/txt;charset=gb2312");
			//ȷ���ļ�����
			String fileSize=String.valueOf(fileLength);
			//��������ļ��ĳ���
			res.setHeader("Content-Length",fileSize);		
			//ȡ�������
			ServletOutputStream servletOut=res.getOutputStream();		
			//�����ļ�����,ÿ��1024�ֽ�,��󵥶�����
				
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
				if(f.exists()){//���File.txt�Ƿ���� 
					f.delete();//ɾ��File.txt�ļ�   
				}
			}
			catch(Exception e){
				Logger.info("err="+e.getMessage());
			}

		}
	}
}
