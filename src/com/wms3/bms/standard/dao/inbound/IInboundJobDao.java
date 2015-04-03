package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * 描述: 入库作业数据库操作DAO类接口
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInboundJobDao extends IDao {

	/**
	 * 功能:新增作业和作业详细信息，新增库存,并且修改货位状态为满货位2
	 * @param cargospace	货位
	 * @param job			作业
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void addRKWHJob(BaseCargospace cargospace, InoutJob job, List lsJobDetail, List lsStorage) throws Exception;

	/**
	 * 功能:根据作业id查询作业
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception;
	
	/**
	 * 功能:修改作业
	 * @param job			作业
	 * @return 
	 * @throws Exception
	 */
	public void updateJob(InoutJob job) throws Exception;

	/**
	 * 功能:根据作业明细号查询作业明细
	 * @param jobdetailid			作业明细号
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception;
	
	/**
	 * 功能:修改作业明细
	 * @param jobdetail			作业明细
	 * @return 
	 * @throws Exception
	 */
	public void updateJobDetail(InoutJobdetail jobdetail) throws Exception;

	/**
	 * 功能:修改作业明细
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsStorage		库存列表
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace, List lsStorage) throws Exception;

	/**
	 * 功能:手动完成作业，添加库存,修改货位状态满空货位
	 * @param job			作业
	 * @param cargospace	货位
	 * @param lsJobDetail	作业详细的列表
	 * @param lsStorage		库存的列表
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage) throws Exception;

}
