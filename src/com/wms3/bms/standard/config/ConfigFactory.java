package com.wms3.bms.standard.config;

import java.util.HashMap;
import javax.servlet.ServletConfig;

import com.ricosoft.common.logger.Logger;



/**
 * @author hug
 *
 * 描述：CwesConfig应用配置对象工厂，一定要从本工厂中创建应用配置对象。
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
				Logger.error("创建"+pa_className.getName()+"类实例失败:"+e1.getMessage());
				return null;
			}
			try{
				item.config.InitConfigPath(strPath);
				item.config.InitParameter();
			}
			catch(Exception e2){
				item.config.m_status = -1;
				Logger.error("读取配置文件出现错误:"+e2.getMessage());
				return null;
			}
			m_FactoryMapByClass.put(pa_className,item);
		}
		item.clients++;
		return item.config;
	}
	
	/**
	 * 功能：创建一个config对象,若存在,则直接返回.
	 * @param pa_className Class,指定类型的config对象;
	 */
	static synchronized public WMSBMSConfig makeConfig(Class pa_className)
	{
		return makeConfigInner(pa_className,null);
	}
	
	/**
	 * 创建一个config对象,若存在,则直接返回.
	 * @param pa_className Class,指定类型的config对象;
	 * @param sConfig ServletConfig,当在servlet启动时创建config对象,可以把servletConfig传入以便得到当然web应用的根目录.可以为null.
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
				Logger.error("创建"+pa_className.getName()+"类实例失败:"+e1.getMessage());
				return null;
			}
			try{
				//item.config.InitConfigPath(strConfigPath);
				item.config.setInitConfigPath(strConfigPath, strFileName, strPropFile);//设置配置文件路径
				item.config.InitParameter();
			}
			catch(Exception e2){
				item.config.m_status = -1;
				Logger.error("读取配置文件出现错误:"+e2.getMessage());
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
