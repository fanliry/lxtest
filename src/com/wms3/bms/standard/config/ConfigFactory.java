package com.wms3.bms.standard.config;

import java.util.HashMap;
import javax.servlet.ServletConfig;

import com.ricosoft.common.logger.Logger;



/**
 * @author hug
 *
 * ������CwesConfigӦ�����ö��󹤳���һ��Ҫ�ӱ������д���Ӧ�����ö���
 */
public class ConfigFactory 
{
	static class ConfigItem{		
		public ConfigItem() {
		}
		WMSBMSConfig config = null;
		int clients = 0;
	}
	private static HashMap m_FactoryMapByClass = new HashMap();
	private static HashMap m_FactoryMapByInst = new HashMap();
	
	private static WMSBMSConfig makeConfigInner(Class pa_className,String strPath)
	{
		ConfigItem item = (ConfigItem)m_FactoryMapByClass.get(pa_className);
		if (item == null)
		{
			item = new ConfigItem();
			try{
				item.config = (WMSBMSConfig)pa_className.newInstance();
			}
			catch(Exception e1){
				Logger.error("����"+pa_className.getName()+"��ʵ��ʧ��:"+e1.getMessage());
				return null;
			}
			try{
				item.config.InitConfigPath(strPath);
				item.config.InitParameter();
			}
			catch(Exception e2){
				item.config.m_status = -1;
				Logger.error("��ȡ�����ļ����ִ���:"+e2.getMessage());
				return null;
			}
			m_FactoryMapByClass.put(pa_className,item);
		}
		item.clients++;
		return item.config;
	}
	
	/**
	 * ���ܣ�����һ��config����,������,��ֱ�ӷ���.
	 * @param pa_className Class,ָ�����͵�config����;
	 */
	static synchronized public WMSBMSConfig makeConfig(Class pa_className)
	{
		return makeConfigInner(pa_className,null);
	}
	
	/**
	 * ����һ��config����,������,��ֱ�ӷ���.
	 * @param pa_className Class,ָ�����͵�config����;
	 * @param sConfig ServletConfig,����servlet����ʱ����config����,���԰�servletConfig�����Ա�õ���ȻwebӦ�õĸ�Ŀ¼.����Ϊnull.
	 */
	static synchronized public WMSBMSConfig makeConfig(Class pa_className,ServletConfig sConfig)
	{
		String strConfigPath = null;
		String strFileName = null;
		String strPropFile = null;
		if(sConfig == null)
			strConfigPath = null;
		else{
			String strFileSepa =  System.getProperties().getProperty("file.separator");
			strConfigPath = sConfig.getServletContext().getRealPath("/");
			strFileName = sConfig.getInitParameter("config");
			strPropFile = sConfig.getInitParameter("dbname");
		}
		
		ConfigItem item = (ConfigItem)m_FactoryMapByClass.get(sConfig);
		if (item == null)
		{
			item = new ConfigItem();
			try{
				item.config = (WMSBMSConfig)pa_className.newInstance();
			}
			catch(Exception e1){
				Logger.error("����"+pa_className.getName()+"��ʵ��ʧ��:"+e1.getMessage());
				return null;
			}
			try{
				//item.config.InitConfigPath(strConfigPath);
				item.config.setInitConfigPath(strConfigPath, strFileName, strPropFile);//���������ļ�·��
				item.config.InitParameter();
			}
			catch(Exception e2){
				item.config.m_status = -1;
				Logger.error("��ȡ�����ļ����ִ���:"+e2.getMessage());
				return null;
			}
			m_FactoryMapByClass.put(sConfig,item);
		}
		item.clients++;
		return item.config;
	}
	
	static public WMSBMSConfig getOneConfig()
	{
		try{
		Object[] values = m_FactoryMapByClass.values().toArray();
		if(values.length > 0){
			ConfigItem item = (ConfigItem)values[0];
			return item.config;
		}
		else
			return null;
		}
		catch(Exception e){
			Logger.error(e.getMessage());
			return null;
		}
	}
}
