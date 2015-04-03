package com.wms3.bms.standard.dao.allot;

import java.util.Hashtable;
import java.util.List;



/**
 * ����: �������DAO��ӿ�
 * @author yangwm
 * 
 */
public interface IOutboundAllotDao {
	
	/**
	 * ָ����Ʒ���ڽ��з���
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsEvenChannel(Hashtable param) throws Exception;
	
	/**
	 * �������Ⱥ�˳�����������з����λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFastInFastOutEvenChannel(Hashtable param) throws Exception;
	
	/**
	 * �������Ⱥ�˳����з����λ
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFastInFastOut(Hashtable param) throws Exception;
	
	/**
	 * �˹�ָ����λ
	 * @param param
	 * @return
	 */
	public List getCsAssign(Hashtable param);
}
