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
 * config����
 * @author hug
 *
 */
public  class WMSBMSConfig extends DBConfig
{
	protected String m_strActionFile;        //�����ļ���
	protected HashMap m_hsCurrDBInfo;       //��ǰ���ݿ���Ϣ
	protected HashMap m_hsClientParamInfo;
	protected HashMap m_hsUrl;		//����ϵͳIP��ַ
	protected HashMap m_hsServer;		//����ϵͳIP��ַ
	protected String m_strForwardUrl;	//��ת·����ַ
	
	public WMSBMSConfig(){
		
		//propfile = "wmsbmsconfig.xml";       //����������ļ�
		//propFilePath = "standard"+ File.separator + "wmsbmsconfig";       //���������·��
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
	 * ����:�����ת·�����ַ
	 * @return
	 */
	public String getM_strForwardUrl()
	{
		return m_strForwardUrl;
	}
	/**
	 * ����:������ת·�����ַ
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
	 * ���ܣ����������ļ�config.xml
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
			
			//�����ݿ�������Ϣ��
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
					if(m_workPoolName == null){//û���ر�ָ�����ĸ�����Դ,��ͼ򵥵�ѡ���һ��
						if (m_dsInfoMaps.size() > 0)
							m_workPoolName = (String)m_dsInfoMaps.keySet().toArray()[0];
						Logger.warn("û���ҵ�Hibernae�Լ�ʹ�õ����ݿ����ӳ���Ϣ,ʹ���˵�һ������Դ");
					}
					
					//ȡ��ǰ���ݿ���Ϣ
					m_hsCurrDBInfo = new HashMap();
					m_hsCurrDBInfo = (HashMap)m_dsInfoMaps.get(m_workPoolName);
				}
			}
			
			//���������ļ���
			nodeDom =  domConfig.selectSingleNode("/Config/configFile");
			nodeTemp = nodeDom.selectSingleNode("actionRelation");  //�����б���
			if(nodeTemp != null){
				m_strActionFile = nodeTemp.getStringValue();
				if(m_strActionFile ==null || m_strActionFile.equals("")){
					m_strActionFile = null;
					Logger.warn("û�ж��幦�ܴ����ļ�,���������ļ���");
				}
				else{
					setActionHashMap(m_strConfigPath + m_strActionFile); //���ý��״����б�
				}
			}
			else{
				Logger.warn("û�ж��幦�ܴ����ļ��ڵ�,���������ļ���");
			}		

			//����ϵͳIP��ַ
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
			
			//��������������Ϣ
			m_hsServer = new HashMap();
			//OPC�ͻ���
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
			//�м��
			nodeDom =  domConfig.selectSingleNode("/Config/outserver");
			if(nodeDom != null)
			{
				m_hsServer.put("out_ip", nodeDom.selectSingleNode("ip").getStringValue());
				m_hsServer.put("out_port", nodeDom.selectSingleNode("port").getStringValue());
				m_hsServer.put("out_dbname", nodeDom.selectSingleNode("dbname").getStringValue());
				m_hsServer.put("out_user", nodeDom.selectSingleNode("user").getStringValue());
				m_hsServer.put("out_password", nodeDom.selectSingleNode("password").getStringValue());	
			}
			
			//��ת·��
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
