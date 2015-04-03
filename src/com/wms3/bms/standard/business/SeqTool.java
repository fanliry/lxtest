package com.wms3.bms.standard.business;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.tools.CommonBusiness;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.entity.base.BaseSeq;

/**
 * 
 * 描述: 生成ID工具
 * @author hug 2012-3-13
 * @since WMS 3.0
 */
public class SeqTool {
    
    /**
     * 功能:生成ID(根据序列号)
     * @author hug 2012-12-13 
     * @param strType   类型 如：RKD  CKD  ZY等要生成ID的类型
     * @param dao
     * @return
     */
    public static String getID(String strType, EntityDAO dao){
        String strID = null;
        //yyyyMMdd
        String currDate = StrTools.getCurrDate();
        //同步类型
        strType = strType.intern();
        synchronized(strType){
            List<BaseSeq> ls = dao.searchEntities("from BaseSeq as bseq where bseq.seqType='"+strType+"'", 0, 1);
            if(ls == null || ls.size()<1){ //默认4位长度
                strID = strType + currDate + getCode(1, 4);
                BaseSeq baseSeq = new BaseSeq();
                baseSeq.setSeqType(strType);  //序列类型
                baseSeq.setSeqValue(strID);   //值
                baseSeq.setSeqPrefix(strType);//前缀
                baseSeq.setIcodelength(4);    //位数
                dao.save(baseSeq);
            }else{
                BaseSeq baseSeq = ls.get(0);
                String strNo = baseSeq.getSeqPrefix();  //前缀
                int length = baseSeq.getIcodelength();  //长度
                String strValue = baseSeq.getSeqValue();//值
                
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
                //修改
                dao.updateSql("update BaseSeq  set seqValue='"+strID+"' where seqType='"+strType+"'");
            } 
        }
        return strID;
    }
    /**
     * 功能:根据ID生成详细ID
     * @author hug 2012-4-23 
     * @param strJobId  ID
     * @param num     后缀      
     * @param count   长度   
     * @return
     */
    public static String getDetailId(String strJobId,String num,int count){
        CommonBusiness cb = new CommonBusiness();
        return "D" + strJobId + cb.getFirstStringFromNum(count, num); 
    }
    /** 
     * 功能: 获得作业详细ID的最大ID号
     * @author hug 2012-12-17 
     * @param strJobId  作业ID
     * @param length    后面的位数
     * @param dao
     * @return
     */
    public static int getMaxJobDetailId(String strJobId, EntityDAO dao){
        List ls = dao.searchEntities("select max(jobd.jobdetailid) from InoutJobdetail as jobd where jobd.jobid='" + strJobId + "'");
        int length = 4; //4位
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
     * 功能:生成ID
     * @author hug 2012-3-13 
     * @param strType   类型 如：RKD  CKD  ZY等要生成ID的类型
     * @param strNo     ID的前缀
     * @param dao
     * @return
     */
    @Deprecated
    public static String getID(String strType, String strNo, EntityDAO dao)
    {
        String strID = null;
        CommonBusiness cb = new CommonBusiness();
        //同步类型
        synchronized(strType){
        	
            strID = cb.getPPNo(strNo,4,"select bseq.seqValue from BaseSeq as bseq where bseq.seqType='"+strType+"'",dao);
            
            dao.updateSql("update BaseSeq  set seqValue='"+strID+"' where seqType='"+strType+"'");
        }
        return strID;
    }
    /**
     * 功能:生成ID
     * @author hug 2012-11-22
     * @param strType   类型 如：RKD  CKD  ZY等要生成ID的类型
     * @param strNo     ID的前缀
     * @param dao
     * @return
     */
    @Deprecated
    public static String getNewID(String strType, String strNo , int length, EntityDAO dao)
    {
        String strID = null;
        CommonBusiness cb = new CommonBusiness();
        if(length ==0){ // 如果系列表中的记录的位数字段为0，则默认为4
        	length = 4;
        }
        if (strNo==null) {
			strNo="D";
		}
        //同步类型
        synchronized(strType){
            strID = cb.getPPNo(strNo,length,"select bseq.seqValue from BaseSeq as bseq where bseq.seqType='"+strType+"'",dao);
            
            dao.updateSql("update BaseSeq  set seqValue='"+strID+"' where seqType='"+strType+"'");
        }
        return strID;
    }
    /**
     * 功能:根据ID生成详细ID
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
     * 功能:返回下一个编号(前面补0)
     * @param num  		当前编号
     * @param count  	位数
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
