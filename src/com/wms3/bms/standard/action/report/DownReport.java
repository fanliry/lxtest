package com.wms3.bms.standard.action.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 描述:生成报表
 * @author gui
 *
 */
public abstract class DownReport 
{
	public abstract String createReport(HttpServletRequest request, HttpServletResponse response, String filePath, String fileType, EntityDAO dao, String strCompanyName) throws Exception;
}
