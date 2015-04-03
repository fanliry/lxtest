package com.wms3.bms.standard.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.ricosoft.common.config.DBConfig;
import com.ricosoft.common.dao.dataSource.DataSourceManager;
import com.ricosoft.common.logger.Logger;

/**
 * config配置
 * @author hug
 *
 */
public  class WMSBMSConfig extends DBConfig
{
	protected String m_strActionFile;        //交易文件名
	protected HashMap m_hsCurrDBInfo;       //当前数据库信息
	protected HashMap m_hsClientParamInfo;
	protected HashMap m_hsUrl;		//关联系统IP地址
	protected HashMap m_hsServer;		//关联系统IP地址
	protected String m_strForwardUrl;	//跳转路径地址
	
	public WMSBMSConfig(){
		
		//propfile = "wmsbmsconfig.xml";       //服务端配置文件
		//propFilePath = "standard"+ File.separator + "wmsbmsconfig";       //服务端配置路径
	}
	
	public HashMap getm_hsCurrDBInfo(){
		return m_hsCurrDBInfo;
	}
	
	public HashMap getm_hsClientParamInfo(){
		return m_hsClientParamInfo;
	}

	public HashMap getM_hsUrl() {
		return m_hsUrl;
	}

	public void setM_hsUrl(HashMap url) {
		m_hsUrl = url;
	}
	/**
	 * 功能:获得跳转路径类地址
	 * @return
	 */
	public String getM_strForwardUrl()
	{
		return m_strForwardUrl;
	}
	/**
	 * 功能:设置跳转路径类地址
	 * @param strForwardUrl
	 */
	public void setM_strForwardUrl(String strForwardUrl)
	{
		m_strForwardUrl = strForwardUrl;
	}
		
	public HashMap getM_hsServer() {
		return m_hsServer;
	}

	public void setM_hsServer(HashMap server) {
		m_hsServer = server;
	}

	/**
	 * 功能：解析配置文件config.xml
	 * @throws Exception
	 */
	public void InitParameter() throws Exception{

		FileInputStream  stream = null;
		try{						
			java.io.File file = new File(m_strConfigPath+propfile);
			stream = new java.io.FileInputStream(file);  		
			SAXReader saxReader = new SAXReader();
			Document domConfig = saxReader.read(stream);	
			Node nodeDom = null,nodeTemp = null;
			//String strTemp="";
			
			//读数据库连接信息；
			m_DsManager = new DataSourceManager();
			nodeDom = domConfig.selectSingleNode("/Config/DSInfo");  
			if(nodeDom != null){
				m_dsInfoMaps = m_DsManager.parseDsInfo(nodeDom);
				if(m_dsInfoMaps != null){
					nodeDom = domConfig.selectSingleNode("/Config/DSInfo/HiberDs");
					if(nodeDom != null)
						m_workPoolName = nodeDom.getText();
					if(m_workPoolName.equals(""))
						m_workPoolName = null;
					if(m_workPoolName == null){//没有特别指定是哪个数据源,则就简单地选择第一个
						if (m_dsInfoMaps.size() > 0)
							m_workPoolName = (String)m_dsInfoMaps.keySet().toArray()[0];
						Logger.warn("没有找到Hibernae自己使用的数据库连接池信息,使用了第一个数据源");
					}
					
					//取当前数据库信息
					m_hsCurrDBInfo = new HashMap();
					m_hsCurrDBInfo = (HashMap)m_dsInfoMaps.get(m_workPoolName);
				}
			}
			
			//关联配置文件名
			nodeDom =  domConfig.selectSingleNode("/Config/configFile");
			nodeTemp = nodeDom.selectSingleNode("actionRelation");  //交易列表处理
			if(nodeTemp != null){
				m_strActionFile = nodeTemp.getStringValue();
				if(m_strActionFile ==null || m_strActionFile.equals("")){
					m_strActionFile = null;
					Logger.warn("没有定义功能代码文件,请检查配置文件！");
				}
				else{
					setActionHashMap(m_strConfigPath + m_strActionFile); //设置交易代码列表
				}
			}
			else{
				Logger.warn("没有定义功能代码文件节点,请检查配置文件！");
			}		

			//关联系统IP地址
			m_hsUrl = new HashMap();
			nodeDom =  domConfig.selectSingleNode("/Config/url");
			if(nodeDom != null)
			{
				nodeTemp = nodeDom.selectSingleNode("dmmsurl");
				if(nodeTemp != null)
				{
					m_hsUrl.put("dmmsurl", nodeTemp.getStringValue());
				}		
			}
			
			//其他服务器的信息
			m_hsServer = new HashMap();
			//OPC客户端
			nodeDom =  domConfig.selectSingleNode("/Config/opcclient");
			if(nodeDom != null)
			{
				nodeTemp = nodeDom.selectSingleNode("ip");
				if(nodeTemp != null)
				{
					m_hsServer.put("opcclient_ip", nodeTemp.getStringValue());
				}
				nodeTemp = nodeDom.selectSingleNode("port");
				if(nodeTemp != null)
				{
					m_hsServer.put("opcclient_port", nodeTemp.getStringValue());
				}
				
			}
			//中间库
			nodeDom =  domConfig.selectSingleNode("/Config/outserver");
			if(nodeDom != null)
			{
				m_hsServer.put("out_ip", nodeDom.selectSingleNode("ip").getStringValue());
				m_hsServer.put("out_port", nodeDom.selectSingleNode("port").getStringValue());
				m_hsServer.put("out_dbname", nodeDom.selectSingleNode("dbname").getStringValue());
				m_hsServer.put("out_user", nodeDom.selectSingleNode("user").getStringValue());
				m_hsServer.put("out_password", nodeDom.selectSingleNode("password").getStringValue());	
			}
			
			//跳转路径
			nodeDom =  domConfig.selectSingleNode("/Config/forwardurl");
			if(nodeDom != null)
			{
				m_strForwardUrl = nodeDom.getStringValue();
			}
			
		}catch(Exception er)
		{
			er.printStackTrace();
		}
		finally{
			if(stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
	}

	
	
	public static void main(String[] args) 
	{
	
	}
}
