package com.wms3.bms.standard.entity.base;


import java.io.Serializable;


/**
 * ˵�����ֵ���������ʵ��
 * @author xiaotao
 *
 */
public class Map implements Serializable {
	
	
	private String m_mapId;            //ֻ��Id
	private String m_mapNumber;        //�ֵ���
	private String m_mapName;          //�ֵ�����
	private String m_tableName;        //��Ӧ�����ݱ�����
	private String m_describe;         //��ע
	
	
	public Map(){}
	
	public String getM_describe() {
		return m_describe;
	}

	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}

	public String getM_mapId() {
		return m_mapId;
	}

	public void setM_mapId(String id) {
		m_mapId = id;
	}

	public String getM_mapName() {
		return m_mapName;
	}

	public void setM_mapName(String name) {
		m_mapName = name;
	}

	public String getM_mapNumber() {
		return m_mapNumber;
	}

	public void setM_mapNumber(String number) {
		m_mapNumber = number;
	}

	public String getM_tableName() {
		return m_tableName;
	}

	public void setM_tableName(String name) {
		m_tableName = name;
	}

}
