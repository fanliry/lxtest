package com.wms3.bms.standard.action.base.ajaxTree;

/**
 * ���ܣ������ڵ��б����dao����
 * @author hug
 */
public class BlueTreeFactory 
{

	public BlueTreeInterFace create(String className) throws Exception {
		
		BlueTreeInterFace tree = null;
		
		try {
			tree = (BlueTreeInterFace) Class.forName(className).newInstance();
		} catch (ClassNotFoundException ex) {
			throw ex;
		} catch (IllegalAccessException ex) {
			throw ex;
		} catch (InstantiationException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
		
		return tree;
	}

}