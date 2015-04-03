package com.wms3.bms.standard.action.rf;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: RF�ɼ�������ҵ
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public class RFJobAction extends AbstractAction{
    protected IJobBus jobBus;   //��ҵҵ����
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //�ջ���ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        


        try{

            
            
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("RF==>RF��ѯ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:RF������ҵ
     * @author hug 2012-9-10 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //��ǰ�û� 
        
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));// �ֿ�ID 
        String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));  //��������
        //����ID
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));//Ʒ�����
        String strPackId = CommonTools.getStrToGbk(request.getParameter("packid"));      //��װ   
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));        //��λ
        
        String strLotid = CommonTools.getStrToGbk(request.getParameter("lotid"));       //��������ID
        String strLotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));   //��������1
        String strLotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));   //��������2
        String strLotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));   //��������3
        String strLotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));   //��������4
        String strLotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));   //��������5
        String strLotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));   //��������6
        String strLotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));   //��������7
        String strLotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));   //��������8
        String strLotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));   //��������9
        String strLotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10")); //��������10
        String strLotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11")); //��������11
        String strLotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12")); //��������12
        
        String num = CommonTools.getStrToGbk(request.getParameter("num"));              //��ҵ����
        String weight = CommonTools.getStrToGbk(request.getParameter("weight"));        //��ҵë��
        String netweight = CommonTools.getStrToGbk(request.getParameter("netweight"));  //��ҵ����
        String volume = CommonTools.getStrToGbk(request.getParameter("volume"));        //��ҵ���
        
        String ownerId = CommonTools.getStrToGbk(request.getParameter("ownerid"));        //����
        
        jobBus = new JobBusImpl(dao);
        String strMsg = "";
        try{
            
            String submitFlag = (String)httpsession.getAttribute("submitFlag"); //�ж��Ƿ�Ϊ�ظ��ύ�ı�־����
            if(submitFlag == "begin")
            {
                InoutJob job = new InoutJob();
                job.setJobtype("1");        //ҵ����1-���2-����3-�ƿ�
                job.setOnLineType("1");     //����ģʽ 1.���� 2.�ѻ�
                job.setIsaccount("Y");      //�Ƿ���� Y.�� N.��
                job.setStatus("1");         //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
                job.setPriority(10);        //���ȼ�
                job.setCreatetime(StrTools.getCurrDateTime(2));//����ʱ��
                job.setJobpos("1");             //��ҵ���� 1.���� 2.�����
                //job.setShiftid(strShiftId);   //��ҵ���
                job.setTraycode(strTrayCode);   //��������
                job.setWarehouseid(warehouseid);//�ֿ�ID
                job.setTcargoWhareaId(whAreaId);//����ID
                job.setOpManId(strUserCode);    //������
                job.setInOutType("1");          //������� 1�ϼ���� 2.����
                job.setInvoicetype("1");        //1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
                //�����ҵID   �ջ���ҵSJZY ��ǰ׺ SJ
                String strID = SeqTool.getID("RKZY", "RK", dao); //��ҵID
                job.setJobid(strID);            //������ҵID
                
                InoutJobdetail jobDetail = new InoutJobdetail();
                //��ҵ��ϸID
                String strJobDetailId = SeqTool.getDetailId(strID, "1");
                jobDetail.setJobdetailid(strJobDetailId);   //��ҵ��ϸID
                jobDetail.setJobid(strID);                  //��ҵID
                jobDetail.setLinestatus("0");               //״̬0:�½�2���
                jobDetail.setProductid(strProductId);//Ʒ��
                jobDetail.setPackid(strPackId); //��װID
                jobDetail.setPunit(strEunit);   //��λ
                jobDetail.setLotid(strLotid);   //��������ID
                jobDetail.setJobnum(Double.parseDouble(num));           //����
                jobDetail.setNetweight(Double.parseDouble(netweight));  //����
                jobDetail.setWeight(Double.parseDouble(weight));        //ë��
                jobDetail.setVolume(Double.parseDouble(volume));        //���   
                jobDetail.setAssignnum(Double.parseDouble(num));             //��������
                jobDetail.setAssignnetweight(Double.parseDouble(netweight)); //���侻��
                jobDetail.setAssignweight(Double.parseDouble(weight));       //����ë��
                jobDetail.setAssignvolume(Double.parseDouble(volume));       //�������
                //jobDetail.setOwnerId(trans.getOwnerid());                  //����
                jobDetail.setIsinvoice("N");                //�Ƿ������ɵ���
                //jobDetail.setTransreceiptid(strTransId);    //�ջ���¼ID
    /*            jobDetail.setLotatt1(strLotatt1);   //��������1
                jobDetail.setLotatt2(strLotatt2);   //��������2
                jobDetail.setLotatt3(strLotatt3);   //��������3
                jobDetail.setLotatt4(strLotatt4);   //��������4
                jobDetail.setLotatt5(strLotatt5);   //��������5
                jobDetail.setLotatt6(strLotatt6);   //��������6
                jobDetail.setLotatt7(strLotatt7);   //��������7
                jobDetail.setLotatt8(strLotatt8);   //��������8
                jobDetail.setLotatt9(strLotatt9);   //��������9
                jobDetail.setLotatt10(strLotatt10); //��������10
                jobDetail.setLotatt11(strLotatt11); //��������11
                jobDetail.setLotatt12(strLotatt12); //��������12
*/                jobDetail.setOwnerId(ownerId);      //����
                strMsg = jobBus.addOneJob(job, jobDetail);
                
                // ���ñ�־����SubmitFlagֵΪ��finish������ʾ�Ѿ��ύ
                httpsession.setAttribute("submitFlag", "finish");
  
            }else{
                strMsg = "���ύ�ˣ������ظ��ύ��";
            }    
            //��תҳ��
            strUrl = "/rf/sc_inbound.jsp";   

            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("RF==>������ҵ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
