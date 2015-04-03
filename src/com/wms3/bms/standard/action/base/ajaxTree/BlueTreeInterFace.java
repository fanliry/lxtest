package com.wms3.bms.standard.action.base.ajaxTree;

import java.util.List;

/**
 * 功能：获取子列表功能接口
 * @author gui
 */
public interface BlueTreeInterFace {
	
	//根据父节点id获取子节点列表
	public List getTreeElements(String pid);
  
  
}