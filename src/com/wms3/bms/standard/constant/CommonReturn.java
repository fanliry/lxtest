package com.wms3.bms.standard.constant;

import java.util.List;

/**
 * ����: ͨ�÷��ؽ����
 * @author hug 2012-12-17
 * @since WMS 3.0
 */
public class CommonReturn {
    protected int retCode = 0;         //   ������ 0����ʾ�ɹ���-1����ʾ�쳣
    protected String retInfo = null;   //   �����쳣��Ϣ
    protected List<?> lsReturn = null; //   ���ؽ���б�
    protected Object retObj = null;    //   ���ؽ������
    
    protected String strQueryHQL;   //���ط�ҳ��ѯ��HQL���
    protected String strCountHQL;   //���ط�ҳ��ѯ�ܼ�¼��HQL���
    
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
     * ����:��� ������ 0����ʾ�ɹ���-1����ʾ�쳣
     * @author hug 2012-12-17 
     * @return
     */
    public int getRetCode() {
        return retCode;
    }
    /**
     * ����:���� ������ 0����ʾ�ɹ���-1����ʾ�쳣
     * @author hug 2012-12-17 
     * @param retCode
     */
    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }
    /**
     * ����:��� �����쳣��Ϣ
     * @author hug 2012-12-17 
     * @return
     */
    public String getRetInfo() {
        return retInfo;
    }
    /**
     * ����:���� �����쳣��Ϣ
     * @author hug 2012-12-17 
     * @param retInfo
     */
    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
    /**
     * ����:��� ���ؽ���б�
     * @author hug 2012-12-17 
     * @return
     */
    public List<?> getLsReturn() {
        return lsReturn;
    }
    /**
     * ����:���� ���ؽ���б�
     * @author hug 2012-12-17 
     * @param lsReturn
     */
    public void setLsReturn(List<?> lsReturn) {
        this.lsReturn = lsReturn;
    }
    /**
     * ����: ��÷��ؽ������
     * @author hug 2013-1-6 
     * @return
     */
    public Object getRetObj() {
        return retObj;
    }
    /**
     * ����: ���÷��ؽ������
     * @author hug 2013-1-6 
     * @param retObj
     */
    public void setRetObj(Object retObj) {
        this.retObj = retObj;
    }
    /**
     * ����: ��÷��ط�ҳ��ѯ�ܼ�¼��HQL���
     * @author hug 2013-2-4 
     * @return
     */
    public String getStrCountHQL() {
        return strCountHQL;
    }
    /**
     * ����:���÷��ط�ҳ��ѯ�ܼ�¼��HQL���
     * @author hug 2013-2-4 
     * @param strCountHQL
     */
    public void setStrCountHQL(String strCountHQL) {
        this.strCountHQL = strCountHQL;
    }
    /**
     * ����: ��÷��ط�ҳ��ѯ��HQL���
     * @author hug 2013-2-4 
     * @return
     */
    public String getStrQueryHQL() {
        return strQueryHQL;
    }
    /**
     * 
     * ����:���÷��ط�ҳ��ѯ��HQL���
     * @author hug 2013-2-4 
     * @param strQueryHQL
     */
    public void setStrQueryHQL(String strQueryHQL) {
        this.strQueryHQL = strQueryHQL;
    }
    
    
    
    
}
