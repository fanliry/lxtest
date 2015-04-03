package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.IPackageBus;
import com.wms3.bms.standard.business.base.IPackageMastermesgBus;
import com.wms3.bms.standard.business.base.impl.PackageBusImpl;
import com.wms3.bms.standard.business.base.impl.PackageMastermesgBusImpl;
import com.wms3.bms.standard.entity.base.BasePackage;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 描述:包装管理
 * @author gui
 *
 */
public class PackageAction extends AbstractAction
{
	protected IPackageBus packageBus;
	protected IPackageMastermesgBus packageMastermesgBus;
	protected int maxLine = 20;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String packageid = CommonTools.getStrToGbk(request.getParameter("packageid"));	//包装Id
		String pkgdesc = CommonTools.getStrToGbk(request.getParameter("pkgdesc"));		//包装描述
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		packageBus = new PackageBusImpl(dao);
		packageMastermesgBus = new PackageMastermesgBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //包装管理 查询列表
			{
				strUrl = "/standard/jsp/base/package/base_package_list.jsp";
				
				PagingTool pt = packageBus.getPackageQuery(pkgdesc, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//包装管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/package/base_package_update.jsp";
				
				//包装
				BasePackage pk = packageBus.getPackageById(packageid);
				request.setAttribute("pk", pk); 
				
				//包装主信息-包装单位(EA-主单位,IP-内包装,CS-箱,PL-托盘,OT-其它)
				BasePackageMastermesg pkmaster1 = packageMastermesgBus.getPackageMastermesg(packageid, "EA");
				BasePackageMastermesg pkmaster2 = packageMastermesgBus.getPackageMastermesg(packageid, "IP");
				BasePackageMastermesg pkmaster3 = packageMastermesgBus.getPackageMastermesg(packageid, "CS");
				BasePackageMastermesg pkmaster4 = packageMastermesgBus.getPackageMastermesg(packageid, "PL");
				BasePackageMastermesg pkmaster5 = packageMastermesgBus.getPackageMastermesg(packageid, "OT");
				
				request.setAttribute("pkmaster1", pkmaster1); 
				request.setAttribute("pkmaster2", pkmaster2); 
				request.setAttribute("pkmaster3", pkmaster3); 
				request.setAttribute("pkmaster4", pkmaster4); 
				request.setAttribute("pkmaster5", pkmaster5); 
				 
				
			}else if(flag != null && flag.equals("3")) //选择包装 查询列表
			{
				strUrl = "/standard/jsp/common/common_package_list.jsp";
				
				PagingTool pt = packageBus.getPackageQuery(pkgdesc, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>包装管理==>包装查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加包装
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		/*包装*/
		String descr = CommonTools.getStrToGb2312(request.getParameter("pkgdesc"));
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));
		
		/*包装主信息*/
		String qty1 = CommonTools.getStrToGb2312(request.getParameter("qty1"));
		String descr1 = CommonTools.getStrToGb2312(request.getParameter("descr1"));
		String in_label1 = CommonTools.getStrToGb2312(request.getParameter("in_label1")).equals("true")?"Y":"N";
		String out_label1 = CommonTools.getStrToGb2312(request.getParameter("out_label1")).equals("true")?"Y":"N";
		String loc_usage1 = CommonTools.getStrToGb2312(request.getParameter("loc_usage1"));
		
		String qty2 = CommonTools.getStrToGb2312(request.getParameter("qty2"));
		String descr2 = CommonTools.getStrToGb2312(request.getParameter("descr2"));
		String in_label2 = CommonTools.getStrToGb2312(request.getParameter("in_label2")).equals("true")?"Y":"N";
		String out_label2 = CommonTools.getStrToGb2312(request.getParameter("out_label2")).equals("true")?"Y":"N";
		String loc_usage2 = CommonTools.getStrToGb2312(request.getParameter("loc_usage2"));
		
		String qty3 = CommonTools.getStrToGb2312(request.getParameter("qty3"));
		String descr3 = CommonTools.getStrToGb2312(request.getParameter("descr3"));
		String in_label3 = CommonTools.getStrToGb2312(request.getParameter("in_label3")).equals("true")?"Y":"N";
		String out_label3 = CommonTools.getStrToGb2312(request.getParameter("out_label3")).equals("true")?"Y":"N";
		String loc_usage3 = CommonTools.getStrToGb2312(request.getParameter("loc_usage3"));
		
		String qty4 = CommonTools.getStrToGb2312(request.getParameter("qty4"));
		String descr4 = CommonTools.getStrToGb2312(request.getParameter("descr4"));
		String in_label4 = CommonTools.getStrToGb2312(request.getParameter("in_label4")).equals("true")?"Y":"N";
		String out_label4 = CommonTools.getStrToGb2312(request.getParameter("out_label4")).equals("true")?"Y":"N";
		String loc_usage4 = CommonTools.getStrToGb2312(request.getParameter("loc_usage4"));
		
		String qty5 = CommonTools.getStrToGb2312(request.getParameter("qty5"));
		String descr5 = CommonTools.getStrToGb2312(request.getParameter("descr5"));
		String in_label5 = CommonTools.getStrToGb2312(request.getParameter("in_label5")).equals("true")?"Y":"N";
		String out_label5 = CommonTools.getStrToGb2312(request.getParameter("out_label5")).equals("true")?"Y":"N";
		String loc_usage5 = CommonTools.getStrToGb2312(request.getParameter("loc_usage5"));
		
		/*包装附加信息*/
		String lengthuom1 = CommonTools.getStrToGb2312(request.getParameter("lengthuom1"));
		String widthuom1 = CommonTools.getStrToGb2312(request.getParameter("widthuom1"));
		String heightuom1 = CommonTools.getStrToGb2312(request.getParameter("heightuom1"));
		String cubeuom1 = CommonTools.getStrToGb2312(request.getParameter("cubeuom1"));
		String weightuom1 = CommonTools.getStrToGb2312(request.getParameter("weightuom1"));
		
		String lengthuom2 = CommonTools.getStrToGb2312(request.getParameter("lengthuom2"));
		String widthuom2 = CommonTools.getStrToGb2312(request.getParameter("widthuom2"));
		String heightuom2 = CommonTools.getStrToGb2312(request.getParameter("heightuom2"));
		String cubeuom2 = CommonTools.getStrToGb2312(request.getParameter("cubeuom2"));
		String weightuom2 = CommonTools.getStrToGb2312(request.getParameter("weightuom2"));
		
		String lengthuom3 = CommonTools.getStrToGb2312(request.getParameter("lengthuom3"));
		String widthuom3 = CommonTools.getStrToGb2312(request.getParameter("widthuom3"));
		String heightuom3 = CommonTools.getStrToGb2312(request.getParameter("heightuom3"));
		String cubeuom3 = CommonTools.getStrToGb2312(request.getParameter("cubeuom3"));
		String weightuom3 = CommonTools.getStrToGb2312(request.getParameter("weightuom3"));
		
		String lengthuom4 = CommonTools.getStrToGb2312(request.getParameter("lengthuom4"));
		String widthuom4 = CommonTools.getStrToGb2312(request.getParameter("widthuom4"));
		String heightuom4 = CommonTools.getStrToGb2312(request.getParameter("heightuom4"));
		String cubeuom4 = CommonTools.getStrToGb2312(request.getParameter("cubeuom4"));
		String weightuom4 = CommonTools.getStrToGb2312(request.getParameter("weightuom4"));
		String palletwoodlength = CommonTools.getStrToGb2312(request.getParameter("palletwoodlength"));
		String palletwoodwidth = CommonTools.getStrToGb2312(request.getParameter("palletwoodwidth"));
		String palletwoodheight = CommonTools.getStrToGb2312(request.getParameter("palletwoodheight"));
		String palletti = CommonTools.getStrToGb2312(request.getParameter("palletti"));
		String pallethi = CommonTools.getStrToGb2312(request.getParameter("pallethi"));
		
		String lengthuom5 = CommonTools.getStrToGb2312(request.getParameter("lengthuom5"));
		String widthuom5 = CommonTools.getStrToGb2312(request.getParameter("widthuom5"));
		String heightuom5 = CommonTools.getStrToGb2312(request.getParameter("heightuom5"));
		String cubeuom5 = CommonTools.getStrToGb2312(request.getParameter("cubeuom5"));
		String weightuom5 = CommonTools.getStrToGb2312(request.getParameter("weightuom5"));
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		packageBus = new PackageBusImpl(dao);
		try
		{
        	//包装信息
			BasePackage pk = new BasePackage(descr, remark);
			
			//包装主信息
			BasePackageMastermesg pkmaster1 = new BasePackageMastermesg("", "EA", Integer.parseInt(qty1), descr1, in_label1, out_label1, loc_usage1, "");
			BasePackageMastermesg pkmaster2 = new BasePackageMastermesg("", "IP", Integer.parseInt(qty2), descr2, in_label2, out_label2, loc_usage2, "");
			BasePackageMastermesg pkmaster3 = new BasePackageMastermesg("", "CS", Integer.parseInt(qty3), descr3, in_label3, out_label3, loc_usage3, "");
			BasePackageMastermesg pkmaster4 = new BasePackageMastermesg("", "PL", Integer.parseInt(qty4), descr4, in_label4, out_label4, loc_usage4, "");
			BasePackageMastermesg pkmaster5 = new BasePackageMastermesg("", "OT", Integer.parseInt(qty5), descr5, in_label5, out_label5, loc_usage5, "");
			
			//包装附加信息
            pkmaster1.setBasePackageExtramesg(Double.parseDouble(lengthuom1), Double.parseDouble(widthuom1), 
					Double.parseDouble(heightuom1), Double.parseDouble(cubeuom1), Double.parseDouble(weightuom1), 0, 0, 0, 0, 0);
            pkmaster2.setBasePackageExtramesg(Double.parseDouble(lengthuom2), Double.parseDouble(widthuom2), 
					Double.parseDouble(heightuom2), Double.parseDouble(cubeuom2), Double.parseDouble(weightuom2), 0, 0, 0, 0, 0);
            pkmaster3.setBasePackageExtramesg(Double.parseDouble(lengthuom3), Double.parseDouble(widthuom3), 
					Double.parseDouble(heightuom3), Double.parseDouble(cubeuom3), Double.parseDouble(weightuom3), 0, 0, 0, 0, 0);
            pkmaster4.setBasePackageExtramesg(Double.parseDouble(lengthuom4), Double.parseDouble(widthuom4), 
					Double.parseDouble(heightuom4), Double.parseDouble(cubeuom4), Double.parseDouble(weightuom4), Double.parseDouble(palletwoodlength), 
					Double.parseDouble(palletwoodwidth), Double.parseDouble(palletwoodheight), Double.parseDouble(palletti), Double.parseDouble(pallethi));
            pkmaster5.setBasePackageExtramesg(Double.parseDouble(lengthuom5), Double.parseDouble(widthuom5), 
					Double.parseDouble(heightuom5), Double.parseDouble(cubeuom5), Double.parseDouble(weightuom5), 0, 0, 0, 0, 0);
			
			packageBus.addPackage(pk, pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5);
			
			Logger.info("用户" + strUserName + "添加了包装" + descr);

			strUrl = "/standard/jsp/base/package/base_package_list.jsp";
			
			PagingTool pt = packageBus.getPackageQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>包装管理==>增加包装失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改包装
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		/*包装信息*/
		String packid = CommonTools.getStrToGb2312(request.getParameter("packid"));
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));
		
		/*包装主信息*/
		String packmasterid1 = CommonTools.getStrToGb2312(request.getParameter("packmasterid1"));
		String qty1 = CommonTools.getStrToGb2312(request.getParameter("qty1"));
		String descr1 = CommonTools.getStrToGb2312(request.getParameter("descr1"));
		String in_label1 = CommonTools.getStrToGb2312(request.getParameter("in_label1")).equals("true")?"Y":"N";
		String out_label1 = CommonTools.getStrToGb2312(request.getParameter("out_label1")).equals("true")?"Y":"N";
		String loc_usage1 = CommonTools.getStrToGb2312(request.getParameter("loc_usage1"));
		
		String packmasterid2 = CommonTools.getStrToGb2312(request.getParameter("packmasterid2"));
		String qty2 = CommonTools.getStrToGb2312(request.getParameter("qty2"));
		String descr2 = CommonTools.getStrToGb2312(request.getParameter("descr2"));
		String in_label2 = CommonTools.getStrToGb2312(request.getParameter("in_label2")).equals("true")?"Y":"N";
		String out_label2 = CommonTools.getStrToGb2312(request.getParameter("out_label2")).equals("true")?"Y":"N";
		String loc_usage2 = CommonTools.getStrToGb2312(request.getParameter("loc_usage2"));
		
		String packmasterid3 = CommonTools.getStrToGb2312(request.getParameter("packmasterid3"));
		String qty3 = CommonTools.getStrToGb2312(request.getParameter("qty3"));
		String descr3 = CommonTools.getStrToGb2312(request.getParameter("descr3"));
		String in_label3 = CommonTools.getStrToGb2312(request.getParameter("in_label3")).equals("true")?"Y":"N";
		String out_label3 = CommonTools.getStrToGb2312(request.getParameter("out_label3")).equals("true")?"Y":"N";
		String loc_usage3 = CommonTools.getStrToGb2312(request.getParameter("loc_usage3"));
		
		String packmasterid4 = CommonTools.getStrToGb2312(request.getParameter("packmasterid4"));
		String qty4 = CommonTools.getStrToGb2312(request.getParameter("qty4"));
		String descr4 = CommonTools.getStrToGb2312(request.getParameter("descr4"));
		String in_label4 = CommonTools.getStrToGb2312(request.getParameter("in_label4")).equals("true")?"Y":"N";
		String out_label4 = CommonTools.getStrToGb2312(request.getParameter("out_label4")).equals("true")?"Y":"N";
		String loc_usage4 = CommonTools.getStrToGb2312(request.getParameter("loc_usage4"));
		
		String packmasterid5 = CommonTools.getStrToGb2312(request.getParameter("packmasterid5"));
		String qty5 = CommonTools.getStrToGb2312(request.getParameter("qty5"));
		String descr5 = CommonTools.getStrToGb2312(request.getParameter("descr5"));
		String in_label5 = CommonTools.getStrToGb2312(request.getParameter("in_label5")).equals("true")?"Y":"N";
		String out_label5 = CommonTools.getStrToGb2312(request.getParameter("out_label5")).equals("true")?"Y":"N";
		String loc_usage5 = CommonTools.getStrToGb2312(request.getParameter("loc_usage5"));
		
		/*包装附加信息*/
		String packextraid1 = CommonTools.getStrToGb2312(request.getParameter("packextraid1"));
		String lengthuom1 = CommonTools.getStrToGb2312(request.getParameter("lengthuom1"));
		String widthuom1 = CommonTools.getStrToGb2312(request.getParameter("widthuom1"));
		String heightuom1 = CommonTools.getStrToGb2312(request.getParameter("heightuom1"));
		String cubeuom1 = CommonTools.getStrToGb2312(request.getParameter("cubeuom1"));
		String weightuom1 = CommonTools.getStrToGb2312(request.getParameter("weightuom1"));
		
		String packextraid2 = CommonTools.getStrToGb2312(request.getParameter("packextraid2"));
		String lengthuom2 = CommonTools.getStrToGb2312(request.getParameter("lengthuom2"));
		String widthuom2 = CommonTools.getStrToGb2312(request.getParameter("widthuom2"));
		String heightuom2 = CommonTools.getStrToGb2312(request.getParameter("heightuom2"));
		String cubeuom2 = CommonTools.getStrToGb2312(request.getParameter("cubeuom2"));
		String weightuom2 = CommonTools.getStrToGb2312(request.getParameter("weightuom2"));
		
		String packextraid3 = CommonTools.getStrToGb2312(request.getParameter("packextraid3"));
		String lengthuom3 = CommonTools.getStrToGb2312(request.getParameter("lengthuom3"));
		String widthuom3 = CommonTools.getStrToGb2312(request.getParameter("widthuom3"));
		String heightuom3 = CommonTools.getStrToGb2312(request.getParameter("heightuom3"));
		String cubeuom3 = CommonTools.getStrToGb2312(request.getParameter("cubeuom3"));
		String weightuom3 = CommonTools.getStrToGb2312(request.getParameter("weightuom3"));
		
		String packextraid4 = CommonTools.getStrToGb2312(request.getParameter("packextraid4"));
		String lengthuom4 = CommonTools.getStrToGb2312(request.getParameter("lengthuom4"));
		String widthuom4 = CommonTools.getStrToGb2312(request.getParameter("widthuom4"));
		String heightuom4 = CommonTools.getStrToGb2312(request.getParameter("heightuom4"));
		String cubeuom4 = CommonTools.getStrToGb2312(request.getParameter("cubeuom4"));
		String weightuom4 = CommonTools.getStrToGb2312(request.getParameter("weightuom4"));
		String palletwoodlength = CommonTools.getStrToGb2312(request.getParameter("palletwoodlength"));
		String palletwoodwidth = CommonTools.getStrToGb2312(request.getParameter("palletwoodwidth"));
		String palletwoodheight = CommonTools.getStrToGb2312(request.getParameter("palletwoodheight"));
		String palletti = CommonTools.getStrToGb2312(request.getParameter("palletti"));
		String pallethi = CommonTools.getStrToGb2312(request.getParameter("pallethi"));
		
		String packextraid5 = CommonTools.getStrToGb2312(request.getParameter("packextraid5"));
		String lengthuom5 = CommonTools.getStrToGb2312(request.getParameter("lengthuom5"));
		String widthuom5 = CommonTools.getStrToGb2312(request.getParameter("widthuom5"));
		String heightuom5 = CommonTools.getStrToGb2312(request.getParameter("heightuom5"));
		String cubeuom5 = CommonTools.getStrToGb2312(request.getParameter("cubeuom5"));
		String weightuom5 = CommonTools.getStrToGb2312(request.getParameter("weightuom5"));

		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		packageBus = new PackageBusImpl(dao);
		packageMastermesgBus = new PackageMastermesgBusImpl(dao);
		
		try
		{
			if(packid != null && packid.length()>0)
			{
				//包装信息
				BasePackage pk = packageBus.getPackageById(packid);
				pk.setPkgdesc(descr);
				pk.setRemark(remark);
				
				//包装主信息
				BasePackageMastermesg pkmaster1 = packageMastermesgBus.getPackageMastermesgById(packmasterid1);
				pkmaster1.setQty(Integer.parseInt(qty1));
				pkmaster1.setPkgunitdesc(descr1);
				pkmaster1.setInused(in_label1);
				pkmaster1.setOutused(out_label1);
				pkmaster1.setCsused(loc_usage1);
                //包装附加信息
                pkmaster1.setLength(Float.parseFloat(lengthuom1));
                pkmaster1.setWidth(Float.parseFloat(widthuom1));
                pkmaster1.setHeight(Float.parseFloat(heightuom1));
                pkmaster1.setVolume(Float.parseFloat(cubeuom1));
                pkmaster1.setWeight(Float.parseFloat(weightuom1));
				
				BasePackageMastermesg pkmaster2 = packageMastermesgBus.getPackageMastermesgById(packmasterid2);
				pkmaster2.setQty(Integer.parseInt(qty2));
				pkmaster2.setPkgunitdesc(descr2);
				pkmaster2.setInused(in_label2);
				pkmaster2.setOutused(out_label2);
				pkmaster2.setCsused(loc_usage2);
                //包装附加信息
                pkmaster2.setLength(Float.parseFloat(lengthuom2));
                pkmaster2.setWidth(Float.parseFloat(widthuom2));
                pkmaster2.setHeight(Float.parseFloat(heightuom2));
                pkmaster2.setVolume(Float.parseFloat(cubeuom2));
                pkmaster2.setWeight(Float.parseFloat(weightuom2));
				
				BasePackageMastermesg pkmaster3 = packageMastermesgBus.getPackageMastermesgById(packmasterid3);
				pkmaster3.setQty(Integer.parseInt(qty3));
				pkmaster3.setPkgunitdesc(descr3);
				pkmaster3.setInused(in_label3);
				pkmaster3.setOutused(out_label3);
				pkmaster3.setCsused(loc_usage3);
                //包装附加信息
                pkmaster3.setLength(Float.parseFloat(lengthuom3));
                pkmaster3.setWidth(Float.parseFloat(widthuom3));
                pkmaster3.setHeight(Float.parseFloat(heightuom3));
                pkmaster3.setVolume(Float.parseFloat(cubeuom3));
                pkmaster3.setWeight(Float.parseFloat(weightuom3));
				
				BasePackageMastermesg pkmaster4 = packageMastermesgBus.getPackageMastermesgById(packmasterid4);
				pkmaster4.setQty(Integer.parseInt(qty4));
				pkmaster4.setPkgunitdesc(descr4);
				pkmaster4.setInused(in_label4);
				pkmaster4.setOutused(out_label4);
				pkmaster4.setCsused(loc_usage4);
                //包装附加信息
                pkmaster4.setLength(Float.parseFloat(lengthuom4));
                pkmaster4.setWidth(Float.parseFloat(widthuom4));
                pkmaster4.setHeight(Float.parseFloat(heightuom4));
                pkmaster4.setVolume(Float.parseFloat(cubeuom4));
                pkmaster4.setWeight(Float.parseFloat(weightuom4));
                pkmaster4.setPalletlength(Float.parseFloat(palletwoodlength));
                pkmaster4.setPalletwidth(Float.parseFloat(palletwoodwidth));
                pkmaster4.setPalletheight(Float.parseFloat(palletwoodheight));
                pkmaster4.setPalletti(Float.parseFloat(palletti));
                pkmaster4.setPallethi(Float.parseFloat(pallethi));
				
				BasePackageMastermesg pkmaster5 = packageMastermesgBus.getPackageMastermesgById(packmasterid5);
				pkmaster5.setQty(Integer.parseInt(qty5));
				pkmaster5.setPkgunitdesc(descr5);
				pkmaster5.setInused(in_label5);
				pkmaster5.setOutused(out_label5);
				pkmaster5.setCsused(loc_usage5);
                //包装附加信息
                pkmaster5.setLength(Float.parseFloat(lengthuom5));
                pkmaster5.setWidth(Float.parseFloat(widthuom5));
                pkmaster5.setHeight(Float.parseFloat(heightuom5));
                pkmaster5.setVolume(Float.parseFloat(cubeuom5));
                pkmaster5.setWeight(Float.parseFloat(weightuom5));
				//
				
				packageBus.updatePackage(pk, pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5);
				Logger.info("用户" + strUserName + "修改了包装" + packid);
			}
			
			strUrl = "/standard/jsp/base/package/base_package_list.jsp";
			
			PagingTool pt = packageBus.getPackageQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>包装管理==>修改包装失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除包装
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		packageBus = new PackageBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					packageBus.deletePackage(id[i]);	
					Logger.info("用户" + strUserName + "删除了包装" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/package/base_package_list.jsp";
			
			PagingTool pt = packageBus.getPackageQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>包装管理==>包装删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}