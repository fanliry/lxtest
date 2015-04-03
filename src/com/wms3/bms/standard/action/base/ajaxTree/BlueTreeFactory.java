package com.wms3.bms.standard.action.base.ajaxTree;

/**
 * 功能：创建节点列表类的dao工厂
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