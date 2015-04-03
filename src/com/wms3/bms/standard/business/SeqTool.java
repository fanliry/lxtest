package com.wms3.bms.standard.business;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.tools.CommonBusiness;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * 
 * ����: ����ID����
 * @author hug 2012-3-13
 * @since WMS 3.0
 */
public class SeqTool {
    
    /**
     * ����:����ID(�������к�)
     * @author hug 2012-12-13 
     * @param strType   ���� �磺RKD  CKD  ZY��Ҫ����ID������
     * @param dao
     * @return
     */
    public static String getID(String strType, EntityDAO dao){
        String strID = null;
        //yyyyMMdd
        String currDate = StrTools.getCurrDate();
        //ͬ������
        strType = strType.intern();
        synchronized(strType){
            List<BaseSeq> ls = dao.searchEntities("from BaseSeq as bseq where bseq.seqType='"+strType+"'", 0, 1);
            if(ls == null || ls.size()<1){ //Ĭ��4λ����
                strID = strType + currDate + getCode(1, 4);
                BaseSeq baseSeq = new BaseSeq();
                baseSeq.setSeqType(strType);  //��������
                baseSeq.setSeqValue(strID);   //ֵ
                baseSeq.setSeqPrefix(strType);//ǰ׺
                baseSeq.setIcodelength(4);    //λ��
                dao.save(baseSeq);
            }else{
                BaseSeq baseSeq = ls.get(0);
                String strNo = baseSeq.getSeqPrefix();  //ǰ׺
                int length = baseSeq.getIcodelength();  //����
                String strValue = baseSeq.getSeqValue();//ֵ
                
                if(strValue == null || strValue.trim().length()<1){
                    strID = strNo + currDate+getCode(1, length);
                }else{
                    if(strValue.substring(strNo.length(),strValue.length()-length).equals(currDate)){
                        strID = strNo + currDate + getCode((Integer.parseInt(strValue.substring(strValue.length()-length,strValue.length()))+1), length);
                    }
                    else{
                        strID = strNo + currDate+getCode(1, length);
                    }   
                }
                //�޸�
                dao.updateSql("update BaseSeq  set seqValue='"+strID+"' where seqType='"+strType+"'");
            } 
        }
        return strID;
    }
    /**
     * ����:����ID������ϸID
     * @author hug 2012-4-23 
     * @param strJobId  ID
     * @param num     ��׺      
     * @param count   ����   
     * @return
     */
    public static String getDetailId(String strJobId,String num,int count){
        CommonBusiness cb = new CommonBusiness();
        return "D" + strJobId + cb.getFirstStringFromNum(count, num); 
    }
    /** 
     * ����: �����ҵ��ϸID�����ID��
     * @author hug 2012-12-17 
     * @param strJobId  ��ҵID
     * @param length    �����λ��
     * @param dao
     * @return
     */
    public static int getMaxJobDetailId(String strJobId, EntityDAO dao){
        List ls = dao.searchEntities("select max(jobd.jobdetailid) from InoutJobdetail as jobd where jobd.jobid='" + strJobId + "'");
        int length = 4; //4λ
        if(ls == null || ls.size() < 1){
            return 0;
        }else{
            String maxVal = (String)ls.get(0);
            if(maxVal==null){
                return 0;
            }else{
                return Integer.parseInt(maxVal.substring(maxVal.length()-length,maxVal.length()));
            }
        }     
    }
    
    /**
     * ����:����ID
     * @author hug 2012-3-13 
     * @param strType   ���� �磺RKD  CKD  ZY��Ҫ����ID������
     * @param strNo     ID��ǰ׺
     * @param dao
     * @return
     */
    @Deprecated
    public static String getID(String strType, String strNo, EntityDAO dao)
    {
        String strID = null;
        CommonBusiness cb = new CommonBusiness();
        //ͬ������
        synchronized(strType){
        	
            strID = cb.getPPNo(strNo,4,"select bseq.seqValue from BaseSeq as bseq where bseq.seqType='"+strType+"'",dao);
            
            dao.updateSql("update BaseSeq  set seqValue='"+strID+"' where seqType='"+strType+"'");
        }
        return strID;
    }
    /**
     * ����:����ID
     * @author hug 2012-11-22
     * @param strType   ���� �磺RKD  CKD  ZY��Ҫ����ID������
     * @param strNo     ID��ǰ׺
     * @param dao
     * @return
     */
    @Deprecated
    public static String getNewID(String strType, String strNo , int length, EntityDAO dao)
    {
        String strID = null;
        CommonBusiness cb = new CommonBusiness();
        if(length ==0){ // ���ϵ�б��еļ�¼��λ���ֶ�Ϊ0����Ĭ��Ϊ4
        	length = 4;
        }
        if (strNo==null) {
			strNo="D";
		}
        //ͬ������
        synchronized(strType){
            strID = cb.getPPNo(strNo,length,"select bseq.seqValue from BaseSeq as bseq where bseq.seqType='"+strType+"'",dao);
            
            dao.updateSql("update BaseSeq  set seqValue='"+strID+"' where seqType='"+strType+"'");
        }
        return strID;
    }
    /**
     * ����:����ID������ϸID
     * @author hug 2012-4-23 
     * @param strJobId  ID
     * @param num           
     * @return
     */
    public static String getDetailId(String strJobId, String num){
        CommonBusiness cb = new CommonBusiness();
        return "D" + strJobId + cb.getFirstStringFromNum(4, num); 
    }
    
    
    /**
     * ����:������һ�����(ǰ�油0)
     * @param num  		��ǰ���
     * @param count  	λ��
     * @return
     */
    public static String getCode(int num, int count)
    {
        String nextNo = "";
        String temNo = String.valueOf(num);
        if(temNo.length() != count){
            for(int i=0; i<count-temNo.length(); i++){
                nextNo = nextNo + "0";
            }
        }
        nextNo = nextNo + temNo;
        return nextNo;
    }
}
