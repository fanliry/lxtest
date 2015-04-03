package com.wms3.bms.standard.dao.allot;

import java.util.Hashtable;
import java.util.List;

/**
 * ����: ������DAO��ӿ�
 * @author yangwm
 * 
 */
public interface IInboundAllotDao {

	
	/**
	 * ��[Ŀ�����]�в��Һ��ʵĿ�λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromArea(Hashtable param) throws Exception;
	
	/**
	 * �ϼܵ�[Ŀ���λ]
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromTarget(Hashtable param) throws Exception;
	
	/**
	 * �ϼܵ�sku��ָ����[�ϼܿ�λ]
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSkuTarget(Hashtable param) throws Exception;
	
	/**
	 * ��sku��ָ����[�ϼܿ���]�в��Һ��ʵĿ�λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSkuArea(Hashtable param) throws Exception;
	
	/**
	 * ʹ��[�������λ]
	 * @param param
	 * @return
	 */
	public List getCsFromPieceCs(Hashtable param);
	
	
	/**
	 *  ʹ��[������λ]
	 * @param param
	 * @return
	 */
	public List getCsFromBoxCs(Hashtable param);
	
	/**
	 * ʹ��[��/������λ]
	 * @param param
	 * @return
	 */
	public List getCsFromPieceBoxCs(Hashtable param);
	
	/**
	 * �����������Ϊָ��[��������]�����ϼܵ�[Ŀ���λ]
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromBatchToTarget(Hashtable param) throws Exception;
	
	/**
	 * �����������Ϊָ��[��������]������[Ŀ�����]�в��Һ��ʵĿ�λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromBatchToArea(Hashtable param) throws Exception;
	
	/**
	 * ����ͬ���Ʋ�Ʒ���ڿտ�λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSameProductId(Hashtable param) throws Exception;
	
	/**
	 * ����ͬ���β�Ʒ���ڿտ�λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSameBatch(Hashtable param) throws Exception;
	
	/**
	 * �洢�����������λ
	 * @param param
	 * @return
	 */
	public List getCsFromStoragetype(Hashtable param);
		
	/**
	 * ����Ʒ�������ڸ�����������ȷֲ���ÿ�����
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsEvenProductDate(Hashtable param) throws Exception;
	
	/**
	 * ÿ�����������䣬�����������
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsAlleyByAlley(Hashtable param) throws Exception;
	
	/**
	 * �����������
	 * @param param
	 * @return
	 */
	public List getCsIncrease(Hashtable param);
}
