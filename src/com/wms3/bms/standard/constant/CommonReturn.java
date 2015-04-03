package com.wms3.bms.standard.constant;

import java.util.List;

/**
 * 描述: 通用返回结果类
 * @author hug 2012-12-17
 * @since WMS 3.0
 */
public class CommonReturn {
    protected int retCode = 0;         //   返回码 0：表示成功；-1：表示异常
    protected String retInfo = null;   //   返回异常信息
    protected List<?> lsReturn = null; //   返回结果列表
    protected Object retObj = null;    //   返回结果对象
    
    protected String strQueryHQL;   //返回分页查询的HQL语句
    protected String strCountHQL;   //返回分页查询总记录的HQL语句
    
    public CommonReturn()
    {}
    public CommonReturn(String retMsg)
    {
        retInfo = retMsg;
    }
    public CommonReturn(int retCode, String retInfo, List<?> lsReturn) {
        this.retCode = retCode;
        this.retInfo = retInfo;
        this.lsReturn = lsReturn;
    }

    /**
     * 功能:获得 返回码 0：表示成功；-1：表示异常
     * @author hug 2012-12-17 
     * @return
     */
    public int getRetCode() {
        return retCode;
    }
    /**
     * 功能:设置 返回码 0：表示成功；-1：表示异常
     * @author hug 2012-12-17 
     * @param retCode
     */
    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }
    /**
     * 功能:获得 返回异常信息
     * @author hug 2012-12-17 
     * @return
     */
    public String getRetInfo() {
        return retInfo;
    }
    /**
     * 功能:设置 返回异常信息
     * @author hug 2012-12-17 
     * @param retInfo
     */
    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
    /**
     * 功能:获得 返回结果列表
     * @author hug 2012-12-17 
     * @return
     */
    public List<?> getLsReturn() {
        return lsReturn;
    }
    /**
     * 功能:设置 返回结果列表
     * @author hug 2012-12-17 
     * @param lsReturn
     */
    public void setLsReturn(List<?> lsReturn) {
        this.lsReturn = lsReturn;
    }
    /**
     * 功能: 获得返回结果对象
     * @author hug 2013-1-6 
     * @return
     */
    public Object getRetObj() {
        return retObj;
    }
    /**
     * 功能: 设置返回结果对象
     * @author hug 2013-1-6 
     * @param retObj
     */
    public void setRetObj(Object retObj) {
        this.retObj = retObj;
    }
    /**
     * 功能: 获得返回分页查询总记录的HQL语句
     * @author hug 2013-2-4 
     * @return
     */
    public String getStrCountHQL() {
        return strCountHQL;
    }
    /**
     * 功能:设置返回分页查询总记录的HQL语句
     * @author hug 2013-2-4 
     * @param strCountHQL
     */
    public void setStrCountHQL(String strCountHQL) {
        this.strCountHQL = strCountHQL;
    }
    /**
     * 功能: 获得返回分页查询的HQL语句
     * @author hug 2013-2-4 
     * @return
     */
    public String getStrQueryHQL() {
        return strQueryHQL;
    }
    /**
     * 
     * 功能:设置返回分页查询的HQL语句
     * @author hug 2013-2-4 
     * @param strQueryHQL
     */
    public void setStrQueryHQL(String strQueryHQL) {
        this.strQueryHQL = strQueryHQL;
    }
    
    
    
    
}
