package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;

/**
 * ����:�ɹ�������ҵ�������
 * @author liuxh 2014-1-20
 * @since wmsBMS3.0
 */
public interface IInBoundPoBus {
	/**
	 * ���ܣ����ݲֿ⣬�ͻ�������ʱ���ѯ����
	 * @author liuxh 2014-1-20
	 * @param warehouseid
	 * @param customerid
	 * @param createdate
	 * @param url
	 * @param maxrow
	 * @return
	 * @throws Exception
	 */
	public PagingTool getInboundPoHeaders(String warehouseid,String customerid,String createdate,String poflag,String poid, String potype, String url,int maxrow)
			throws Exception;
	
	/**
	 * ���ܣ����ݲֿ⣬�ͻ���ʱ���ѯ����
	 * @param warehouseid
	 * @param customerid
	 * @param createdate
	 * @param poflag
	 * @param poid
	 * @param potype
	 * @param strUrl
	 * @param maxrow
	 * @return
	 * @throws Exception
	 */
	public PagingTool getInboundPoHeader(String warehouseid,
			String customerid, String start_time,
			String end_time,String poflag,String poid, String potype, String strUrl,int maxrow) throws Exception;
	
	/**
	 * ���ܣ����ݶ���id
	 * @author yao 2014-2-28
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public InboundPoHeader getInboundPoHeaders(String poid) throws Exception;
	/**
	 * ���ܣ����ݶ�����ϸid
	 * @author yao 2015-3-10
	 * @param podetailid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String podetailid) throws Exception;
	/**
	 * ���ܣ����ݶ���id
	 * @author yao 2014-2-28
	 * @param poid
	 * @param detailid
	 * @return
	 * @throws Exception
	 */
	public InboundPoDetail getInboundPoHeaders(String poid,String detailid) throws Exception;
	/**
	 * ���ܣ����ݲɹ�������ö�����ϸ
	 * @author liuxh 2014-1-20
	 * @param poid  
	 * @param strUrl
	 * @param maxrow
	 * @return
	 * @throws Exception
	 */
	public PagingTool getInboundPoDelsByPoId(String poid,String strUrl,int maxrow) throws Exception;
	/**
	 * ���ܣ����ݲɹ���id����������
	 * @author liuxh 2014-1-22
	 * @param poid
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public CommonReturn createAsnByPoId(String poid,String ids,String asntype,String warehouseid,String userCode) throws Exception;
	/**
	 * 
	 * ���ܣ����ݶ�����ϸid��ö�����ϸ
	 * @author liuxh 2014-3-11
	 * @param podelid
	 * @return
	 * @throws Exception
	 */
	public InboundPoDetail getPoDetailByDelId(String podelid)throws Exception;
	
	/**
	 * 
	 * ���ܣ����ݶ���id��ö�����ϸ
	 * @author 
	 * @param podelid
	 * @return
	 * @throws Exception
	 */
	public List<InboundPoDetail> getInboundPoDelByPoId(String poid) throws Exception;
	
	public InboundPoHeader getInboundPoHeaderById(String poid) throws Exception;
	
}
