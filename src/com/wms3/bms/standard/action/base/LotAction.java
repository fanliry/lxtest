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
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 描述:批次属性管理
 * @author gui
 *
 */
public class LotAction extends AbstractAction
{
	protected ILotBus lotBus;
	protected int maxLine = 20;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));			//批次属性Id
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));			//批次属性描述
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        lotBus = new LotBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //批次属性管理 查询列表
			{
				strUrl = "/standard/jsp/base/lot/base_lot_list.jsp";
				
				PagingTool pt = lotBus.getLotQuery(descr, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//批次属性管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/lot/base_lot_update.jsp";
				
				//批次属性
				BaseLot lot = lotBus.getLotById(lotid);
				request.setAttribute("lot", lot); 
				
				//批次详细信息
				List ls = lotBus.getListByLotId(lotid);
				
				request.setAttribute("lotDetail1", ls.get(0));
				request.setAttribute("lotDetail2", ls.get(1));
				request.setAttribute("lotDetail3", ls.get(2));
				request.setAttribute("lotDetail4", ls.get(3));
				
				request.setAttribute("lotDetail5", ls.get(4));
				request.setAttribute("lotDetail6", ls.get(5));
				request.setAttribute("lotDetail7", ls.get(6));
				request.setAttribute("lotDetail8", ls.get(7));
				
				request.setAttribute("lotDetail9", ls.get(8));
				request.setAttribute("lotDetail10", ls.get(9));
				request.setAttribute("lotDetail11", ls.get(10));
				request.setAttribute("lotDetail12", ls.get(11));
				
			}else if(flag != null && flag.equals("3")) //选择批次属性 查询列表
			{
				strUrl = "/standard/jsp/common/common_lot_list.jsp";
				
				PagingTool pt = lotBus.getLotQuery(descr, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性管理==>批次属性查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加批次属性
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
		
		/*批次属性*/
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));
		
		/*批次详细*/
		String attname1 = CommonTools.getStrToGb2312(request.getParameter("attname1"));				/*属性名称*/
		String attcode1 = CommonTools.getStrToGb2312(request.getParameter("attcode1"));				/*属性代码*/
		String lotatt_flag1 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag1"));		/*输入控制*/
		String lottype1 = CommonTools.getStrToGb2312(request.getParameter("lottype1"));				/*属性格式*/
		String lotsearch1 = CommonTools.getStrToGb2312(request.getParameter("lotsearch1"));			/*属性格式值*/
		String lottypevalue1 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue1"));	/*查询方式*/
		
		String attname2 = CommonTools.getStrToGb2312(request.getParameter("attname2"));				/*属性名称*/
		String attcode2 = CommonTools.getStrToGb2312(request.getParameter("attcode2"));				/*属性代码*/
		String lotatt_flag2 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag2"));		/*输入控制*/
		String lottype2 = CommonTools.getStrToGb2312(request.getParameter("lottype2"));				/*属性格式*/
		String lotsearch2 = CommonTools.getStrToGb2312(request.getParameter("lotsearch2"));			/*属性格式值*/
		String lottypevalue2 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue2"));	/*查询方式*/
		
		String attname3 = CommonTools.getStrToGb2312(request.getParameter("attname3"));				/*属性名称*/
		String attcode3 = CommonTools.getStrToGb2312(request.getParameter("attcode3"));				/*属性代码*/
		String lotatt_flag3 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag3"));		/*输入控制*/
		String lottype3 = CommonTools.getStrToGb2312(request.getParameter("lottype3"));				/*属性格式*/
		String lotsearch3 = CommonTools.getStrToGb2312(request.getParameter("lotsearch3"));			/*属性格式值*/
		String lottypevalue3 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue3"));	/*查询方式*/
		
		String attname4 = CommonTools.getStrToGb2312(request.getParameter("attname4"));				/*属性名称*/
		String attcode4 = CommonTools.getStrToGb2312(request.getParameter("attcode4"));				/*属性代码*/
		String lotatt_flag4 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag4"));		/*输入控制*/
		String lottype4 = CommonTools.getStrToGb2312(request.getParameter("lottype4"));				/*属性格式*/
		String lotsearch4 = CommonTools.getStrToGb2312(request.getParameter("lotsearch4"));			/*属性格式值*/
		String lottypevalue4 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue4"));	/*查询方式*/
		
		String attname5 = CommonTools.getStrToGb2312(request.getParameter("attname5"));				/*属性名称*/
		String attcode5 = CommonTools.getStrToGb2312(request.getParameter("attcode5"));				/*属性代码*/
		String lotatt_flag5 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag5"));		/*输入控制*/
		String lottype5 = CommonTools.getStrToGb2312(request.getParameter("lottype5"));				/*属性格式*/
		String lotsearch5 = CommonTools.getStrToGb2312(request.getParameter("lotsearch5"));			/*属性格式值*/
		String lottypevalue5 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue5"));	/*查询方式*/
		
		String attname6 = CommonTools.getStrToGb2312(request.getParameter("attname6"));				/*属性名称*/
		String attcode6 = CommonTools.getStrToGb2312(request.getParameter("attcode6"));				/*属性代码*/
		String lotatt_flag6 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag6"));		/*输入控制*/
		String lottype6 = CommonTools.getStrToGb2312(request.getParameter("lottype6"));				/*属性格式*/
		String lotsearch6 = CommonTools.getStrToGb2312(request.getParameter("lotsearch6"));			/*属性格式值*/
		String lottypevalue6 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue6"));	/*查询方式*/
		
		String attname7 = CommonTools.getStrToGb2312(request.getParameter("attname7"));				/*属性名称*/
		String attcode7 = CommonTools.getStrToGb2312(request.getParameter("attcode7"));				/*属性代码*/
		String lotatt_flag7 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag7"));		/*输入控制*/
		String lottype7 = CommonTools.getStrToGb2312(request.getParameter("lottype7"));				/*属性格式*/
		String lotsearch7 = CommonTools.getStrToGb2312(request.getParameter("lotsearch7"));			/*属性格式值*/
		String lottypevalue7 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue7"));	/*查询方式*/
		
		String attname8 = CommonTools.getStrToGb2312(request.getParameter("attname8"));				/*属性名称*/
		String attcode8 = CommonTools.getStrToGb2312(request.getParameter("attcode8"));				/*属性代码*/
		String lotatt_flag8 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag8"));		/*输入控制*/
		String lottype8 = CommonTools.getStrToGb2312(request.getParameter("lottype8"));				/*属性格式*/
		String lotsearch8 = CommonTools.getStrToGb2312(request.getParameter("lotsearch8"));			/*属性格式值*/
		String lottypevalue8 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue8"));	/*查询方式*/
		
		String attname9 = CommonTools.getStrToGb2312(request.getParameter("attname9"));				/*属性名称*/
		String attcode9 = CommonTools.getStrToGb2312(request.getParameter("attcode9"));				/*属性代码*/
		String lotatt_flag9 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag9"));		/*输入控制*/
		String lottype9 = CommonTools.getStrToGb2312(request.getParameter("lottype9"));				/*属性格式*/
		String lotsearch9 = CommonTools.getStrToGb2312(request.getParameter("lotsearch9"));			/*属性格式值*/
		String lottypevalue9 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue9"));	/*查询方式*/
		
		String attname10 = CommonTools.getStrToGb2312(request.getParameter("attname10"));			/*属性名称*/
		String attcode10 = CommonTools.getStrToGb2312(request.getParameter("attcode10"));			/*属性代码*/
		String lotatt_flag10 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag10"));	/*输入控制*/
		String lottype10 = CommonTools.getStrToGb2312(request.getParameter("lottype10"));			/*属性格式*/
		String lotsearch10 = CommonTools.getStrToGb2312(request.getParameter("lotsearch10"));		/*属性格式值*/
		String lottypevalue10 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue10"));	/*查询方式*/
		
		String attname11 = CommonTools.getStrToGb2312(request.getParameter("attname11"));			/*属性名称*/
		String attcode11 = CommonTools.getStrToGb2312(request.getParameter("attcode11"));			/*属性代码*/
		String lotatt_flag11 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag11"));	/*输入控制*/
		String lottype11 = CommonTools.getStrToGb2312(request.getParameter("lottype11"));			/*属性格式*/
		String lotsearch11 = CommonTools.getStrToGb2312(request.getParameter("lotsearch11"));		/*属性格式值*/
		String lottypevalue11 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue11"));	/*查询方式*/
		
		String attname12 = CommonTools.getStrToGb2312(request.getParameter("attname12"));			/*属性名称*/
		String attcode12 = CommonTools.getStrToGb2312(request.getParameter("attcode12"));			/*属性代码*/
		String lotatt_flag12 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag12"));	/*输入控制*/
		String lottype12 = CommonTools.getStrToGb2312(request.getParameter("lottype12"));			/*属性格式*/
		String lotsearch12 = CommonTools.getStrToGb2312(request.getParameter("lotsearch12"));		/*属性格式值*/
		String lottypevalue12 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue12"));	/*查询方式*/
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		lotBus = new LotBusImpl(dao);
		try
		{
        	//批次属性信息
			BaseLot lot = new BaseLot();
			lot.setM_strDescr(descr);
			lot.setM_strIsdefault("N");
			lot.setM_strRemark(remark);
			
			//批次详细信息
			BaseLotDetail lotDetail1 = new BaseLotDetail();
			lotDetail1.setM_strAttname(attname1);
			lotDetail1.setM_strAttcode(attcode1);
			lotDetail1.setM_strLotatt_flag(lotatt_flag1);
			lotDetail1.setM_strLottype(lottype1);
			lotDetail1.setM_strLotsearch(lotsearch1);
			lotDetail1.setM_strLottypevalue(lottypevalue1);
			
			BaseLotDetail lotDetail2 = new BaseLotDetail();
			lotDetail2.setM_strAttname(attname2);
			lotDetail2.setM_strAttcode(attcode2);
			lotDetail2.setM_strLotatt_flag(lotatt_flag2);
			lotDetail2.setM_strLottype(lottype2);
			lotDetail2.setM_strLotsearch(lotsearch2);
			lotDetail2.setM_strLottypevalue(lottypevalue2);
			
			BaseLotDetail lotDetail3 = new BaseLotDetail();
			lotDetail3.setM_strAttname(attname3);
			lotDetail3.setM_strAttcode(attcode3);
			lotDetail3.setM_strLotatt_flag(lotatt_flag3);
			lotDetail3.setM_strLottype(lottype3);
			lotDetail3.setM_strLotsearch(lotsearch3);
			lotDetail3.setM_strLottypevalue(lottypevalue3);
			
			BaseLotDetail lotDetail4 = new BaseLotDetail();
			lotDetail4.setM_strAttname(attname4);
			lotDetail4.setM_strAttcode(attcode4);
			lotDetail4.setM_strLotatt_flag(lotatt_flag4);
			lotDetail4.setM_strLottype(lottype4);
			lotDetail4.setM_strLotsearch(lotsearch4);
			lotDetail4.setM_strLottypevalue(lottypevalue4);
			
			BaseLotDetail lotDetail5 = new BaseLotDetail();
			lotDetail5.setM_strAttname(attname5);
			lotDetail5.setM_strAttcode(attcode5);
			lotDetail5.setM_strLotatt_flag(lotatt_flag5);
			lotDetail5.setM_strLottype(lottype5);
			lotDetail5.setM_strLotsearch(lotsearch5);
			lotDetail5.setM_strLottypevalue(lottypevalue5);
			
			BaseLotDetail lotDetail6 = new BaseLotDetail();
			lotDetail6.setM_strAttname(attname6);
			lotDetail6.setM_strAttcode(attcode6);
			lotDetail6.setM_strLotatt_flag(lotatt_flag6);
			lotDetail6.setM_strLottype(lottype6);
			lotDetail6.setM_strLotsearch(lotsearch6);
			lotDetail6.setM_strLottypevalue(lottypevalue6);
			
			BaseLotDetail lotDetail7 = new BaseLotDetail();
			lotDetail7.setM_strAttname(attname7);
			lotDetail7.setM_strAttcode(attcode7);
			lotDetail7.setM_strLotatt_flag(lotatt_flag7);
			lotDetail7.setM_strLottype(lottype7);
			lotDetail7.setM_strLotsearch(lotsearch7);
			lotDetail7.setM_strLottypevalue(lottypevalue7);
			
			BaseLotDetail lotDetail8 = new BaseLotDetail();
			lotDetail8.setM_strAttname(attname8);
			lotDetail8.setM_strAttcode(attcode8);
			lotDetail8.setM_strLotatt_flag(lotatt_flag8);
			lotDetail8.setM_strLottype(lottype8);
			lotDetail8.setM_strLotsearch(lotsearch8);
			lotDetail8.setM_strLottypevalue(lottypevalue8);
			
			BaseLotDetail lotDetail9 = new BaseLotDetail();
			lotDetail9.setM_strAttname(attname9);
			lotDetail9.setM_strAttcode(attcode9);
			lotDetail9.setM_strLotatt_flag(lotatt_flag9);
			lotDetail9.setM_strLottype(lottype9);
			lotDetail9.setM_strLotsearch(lotsearch9);
			lotDetail9.setM_strLottypevalue(lottypevalue9);
			
			BaseLotDetail lotDetail10 = new BaseLotDetail();
			lotDetail10.setM_strAttname(attname10);
			lotDetail10.setM_strAttcode(attcode10);
			lotDetail10.setM_strLotatt_flag(lotatt_flag10);
			lotDetail10.setM_strLottype(lottype10);
			lotDetail10.setM_strLotsearch(lotsearch10);
			lotDetail10.setM_strLottypevalue(lottypevalue10);
			
			BaseLotDetail lotDetail11 = new BaseLotDetail();
			lotDetail11.setM_strAttname(attname11);
			lotDetail11.setM_strAttcode(attcode11);
			lotDetail11.setM_strLotatt_flag(lotatt_flag11);
			lotDetail11.setM_strLottype(lottype11);
			lotDetail11.setM_strLotsearch(lotsearch11);
			lotDetail11.setM_strLottypevalue(lottypevalue11);
			
			BaseLotDetail lotDetail12 = new BaseLotDetail();
			lotDetail12.setM_strAttname(attname12);
			lotDetail12.setM_strAttcode(attcode12);
			lotDetail12.setM_strLotatt_flag(lotatt_flag12);
			lotDetail12.setM_strLottype(lottype12);
			lotDetail12.setM_strLotsearch(lotsearch12);
			lotDetail12.setM_strLottypevalue(lottypevalue12);

			lotBus.addLot(lot, lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
					lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12);
			
			Logger.info("用户" + strUserName + "添加了批次属性" + descr);

			strUrl = "/standard/jsp/base/lot/base_lot_list.jsp";
			
			PagingTool pt = lotBus.getLotQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性管理==>增加批次属性失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改批次属性
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
		
		/*批次属性信息*/
		String lotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));
		String isdefault = CommonTools.getStrToGb2312(request.getParameter("isdefault"));
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));
		
		/*批次明细信息*/
		String lotdetailid1 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid1"));		/*批次详细ID*/
		String attname1 = CommonTools.getStrToGb2312(request.getParameter("attname1"));				/*属性名称*/
		String attcode1 = CommonTools.getStrToGb2312(request.getParameter("attcode1"));				/*属性代码*/
		String lotatt_flag1 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag1"));		/*输入控制*/
		String lottype1 = CommonTools.getStrToGb2312(request.getParameter("lottype1"));				/*属性格式*/
		String lotsearch1 = CommonTools.getStrToGb2312(request.getParameter("lotsearch1"));		/*属性格式值*/
		String lottypevalue1 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue1"));	/*查询方式*/
		
		String lotdetailid2 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid2"));		/*批次详细ID*/
		String attname2 = CommonTools.getStrToGb2312(request.getParameter("attname2"));				/*属性名称*/
		String attcode2 = CommonTools.getStrToGb2312(request.getParameter("attcode2"));				/*属性代码*/
		String lotatt_flag2 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag2"));		/*输入控制*/
		String lottype2 = CommonTools.getStrToGb2312(request.getParameter("lottype2"));				/*属性格式*/
		String lotsearch2 = CommonTools.getStrToGb2312(request.getParameter("lotsearch2"));		/*属性格式值*/
		String lottypevalue2 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue2"));	/*查询方式*/
		
		String lotdetailid3 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid3"));		/*批次详细ID*/
		String attname3 = CommonTools.getStrToGb2312(request.getParameter("attname3"));				/*属性名称*/
		String attcode3 = CommonTools.getStrToGb2312(request.getParameter("attcode3"));				/*属性代码*/
		String lotatt_flag3 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag3"));		/*输入控制*/
		String lottype3 = CommonTools.getStrToGb2312(request.getParameter("lottype3"));				/*属性格式*/
		String lotsearch3 = CommonTools.getStrToGb2312(request.getParameter("lotsearch3"));		/*属性格式值*/
		String lottypevalue3 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue3"));	/*查询方式*/
		
		String lotdetailid4 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid4"));		/*批次详细ID*/
		String attname4 = CommonTools.getStrToGb2312(request.getParameter("attname4"));				/*属性名称*/
		String attcode4 = CommonTools.getStrToGb2312(request.getParameter("attcode4"));				/*属性代码*/
		String lotatt_flag4 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag4"));		/*输入控制*/
		String lottype4 = CommonTools.getStrToGb2312(request.getParameter("lottype4"));				/*属性格式*/
		String lotsearch4 = CommonTools.getStrToGb2312(request.getParameter("lotsearch4"));		/*属性格式值*/
		String lottypevalue4 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue4"));	/*查询方式*/
		
		String lotdetailid5 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid5"));		/*批次详细ID*/
		String attname5 = CommonTools.getStrToGb2312(request.getParameter("attname5"));				/*属性名称*/
		String attcode5 = CommonTools.getStrToGb2312(request.getParameter("attcode5"));				/*属性代码*/
		String lotatt_flag5 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag5"));		/*输入控制*/
		String lottype5 = CommonTools.getStrToGb2312(request.getParameter("lottype5"));				/*属性格式*/
		String lotsearch5 = CommonTools.getStrToGb2312(request.getParameter("lotsearch5"));		/*属性格式值*/
		String lottypevalue5 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue5"));	/*查询方式*/
		
		String lotdetailid6 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid6"));		/*批次详细ID*/
		String attname6 = CommonTools.getStrToGb2312(request.getParameter("attname6"));				/*属性名称*/
		String attcode6 = CommonTools.getStrToGb2312(request.getParameter("attcode6"));				/*属性代码*/
		String lotatt_flag6 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag6"));		/*输入控制*/
		String lottype6 = CommonTools.getStrToGb2312(request.getParameter("lottype6"));				/*属性格式*/
		String lotsearch6 = CommonTools.getStrToGb2312(request.getParameter("lotsearch6"));		/*属性格式值*/
		String lottypevalue6 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue6"));	/*查询方式*/
		
		String lotdetailid7 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid7"));		/*批次详细ID*/
		String attname7 = CommonTools.getStrToGb2312(request.getParameter("attname7"));				/*属性名称*/
		String attcode7 = CommonTools.getStrToGb2312(request.getParameter("attcode7"));				/*属性代码*/
		String lotatt_flag7 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag7"));		/*输入控制*/
		String lottype7 = CommonTools.getStrToGb2312(request.getParameter("lottype7"));				/*属性格式*/
		String lotsearch7 = CommonTools.getStrToGb2312(request.getParameter("lotsearch7"));		/*属性格式值*/
		String lottypevalue7 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue7"));	/*查询方式*/
		
		String lotdetailid8 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid8"));		/*批次详细ID*/
		String attname8 = CommonTools.getStrToGb2312(request.getParameter("attname8"));				/*属性名称*/
		String attcode8 = CommonTools.getStrToGb2312(request.getParameter("attcode8"));				/*属性代码*/
		String lotatt_flag8 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag8"));		/*输入控制*/
		String lottype8 = CommonTools.getStrToGb2312(request.getParameter("lottype8"));				/*属性格式*/
		String lotsearch8 = CommonTools.getStrToGb2312(request.getParameter("lotsearch8"));		/*属性格式值*/
		String lottypevalue8 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue8"));	/*查询方式*/
		
		String lotdetailid9 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid9"));		/*批次详细ID*/
		String attname9 = CommonTools.getStrToGb2312(request.getParameter("attname9"));				/*属性名称*/
		String attcode9 = CommonTools.getStrToGb2312(request.getParameter("attcode9"));				/*属性代码*/
		String lotatt_flag9 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag9"));		/*输入控制*/
		String lottype9 = CommonTools.getStrToGb2312(request.getParameter("lottype9"));				/*属性格式*/
		String lotsearch9 = CommonTools.getStrToGb2312(request.getParameter("lotsearch9"));		/*属性格式值*/
		String lottypevalue9 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue9"));	/*查询方式*/
		
		String lotdetailid10 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid10"));	/*批次详细ID*/
		String attname10 = CommonTools.getStrToGb2312(request.getParameter("attname10"));			/*属性名称*/
		String attcode10 = CommonTools.getStrToGb2312(request.getParameter("attcode10"));			/*属性代码*/
		String lotatt_flag10 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag10"));	/*输入控制*/
		String lottype10 = CommonTools.getStrToGb2312(request.getParameter("lottype10"));			/*属性格式*/
		String lotsearch10 = CommonTools.getStrToGb2312(request.getParameter("lotsearch10"));	/*属性格式值*/
		String lottypevalue10 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue10"));	/*查询方式*/
		
		String lotdetailid11 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid11"));	/*批次详细ID*/
		String attname11 = CommonTools.getStrToGb2312(request.getParameter("attname11"));			/*属性名称*/
		String attcode11 = CommonTools.getStrToGb2312(request.getParameter("attcode11"));			/*属性代码*/
		String lotatt_flag11 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag11"));	/*输入控制*/
		String lottype11 = CommonTools.getStrToGb2312(request.getParameter("lottype11"));			/*属性格式*/
		String lotsearch11 = CommonTools.getStrToGb2312(request.getParameter("lotsearch11"));	/*属性格式值*/
		String lottypevalue11 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue11"));	/*查询方式*/
		
		String lotdetailid12 = CommonTools.getStrToGb2312(request.getParameter("lotdetailid12"));	/*批次详细ID*/
		String attname12 = CommonTools.getStrToGb2312(request.getParameter("attname12"));			/*属性名称*/
		String attcode12 = CommonTools.getStrToGb2312(request.getParameter("attcode12"));			/*属性代码*/
		String lotatt_flag12 = CommonTools.getStrToGb2312(request.getParameter("lotatt_flag12"));	/*输入控制*/
		String lottype12 = CommonTools.getStrToGb2312(request.getParameter("lottype12"));			/*属性格式*/
		String lotsearch12 = CommonTools.getStrToGb2312(request.getParameter("lotsearch12"));	/*属性格式值*/
		String lottypevalue12 = CommonTools.getStrToGb2312(request.getParameter("lottypevalue12"));	/*查询方式*/

		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		lotBus = new LotBusImpl(dao);
		
		try
		{
			if(lotid != null && lotid.length()>0)
			{
				//批次属性信息
				BaseLot lot = lotBus.getLotById(lotid);
				lot.setM_strDescr(descr);
				lot.setM_strIsdefault(isdefault);
				lot.setM_strRemark(remark);
				
				//批次明细信息
				BaseLotDetail lotDetail1 = lotBus.getLotDetailByDetailId(lotdetailid1);
				lotDetail1.setM_strLotid(lotid);
				lotDetail1.setM_strAttname(attname1);
				lotDetail1.setM_strAttcode(attcode1);
				lotDetail1.setM_strLotatt_flag(lotatt_flag1);
				lotDetail1.setM_strLottype(lottype1);
				lotDetail1.setM_strLotsearch(lotsearch1);
				lotDetail1.setM_strLottypevalue(lottypevalue1);
				
				BaseLotDetail lotDetail2 = lotBus.getLotDetailByDetailId(lotdetailid2);
				lotDetail2.setM_strLotid(lotid);
				lotDetail2.setM_strAttname(attname2);
				lotDetail2.setM_strAttcode(attcode2);
				lotDetail2.setM_strLotatt_flag(lotatt_flag2);
				lotDetail2.setM_strLottype(lottype2);
				lotDetail2.setM_strLotsearch(lotsearch2);
				lotDetail2.setM_strLottypevalue(lottypevalue2);
				
				BaseLotDetail lotDetail3 = lotBus.getLotDetailByDetailId(lotdetailid3);
				lotDetail3.setM_strLotid(lotid);
				lotDetail3.setM_strAttname(attname3);
				lotDetail3.setM_strAttcode(attcode3);
				lotDetail3.setM_strLotatt_flag(lotatt_flag3);
				lotDetail3.setM_strLottype(lottype3);
				lotDetail3.setM_strLotsearch(lotsearch3);
				lotDetail3.setM_strLottypevalue(lottypevalue3);
				
				BaseLotDetail lotDetail4 = lotBus.getLotDetailByDetailId(lotdetailid4);
				lotDetail4.setM_strLotid(lotid);
				lotDetail4.setM_strAttname(attname4);
				lotDetail4.setM_strAttcode(attcode4);
				lotDetail4.setM_strLotatt_flag(lotatt_flag4);
				lotDetail4.setM_strLottype(lottype4);
				lotDetail4.setM_strLotsearch(lotsearch4);
				lotDetail4.setM_strLottypevalue(lottypevalue4);
				
				BaseLotDetail lotDetail5 = lotBus.getLotDetailByDetailId(lotdetailid5);
				lotDetail5.setM_strLotid(lotid);
				lotDetail5.setM_strAttname(attname5);
				lotDetail5.setM_strAttcode(attcode5);
				lotDetail5.setM_strLotatt_flag(lotatt_flag5);
				lotDetail5.setM_strLottype(lottype5);
				lotDetail5.setM_strLotsearch(lotsearch5);
				lotDetail5.setM_strLottypevalue(lottypevalue5);
				
				BaseLotDetail lotDetail6 = lotBus.getLotDetailByDetailId(lotdetailid6);
				lotDetail6.setM_strLotid(lotid);
				lotDetail6.setM_strAttname(attname6);
				lotDetail6.setM_strAttcode(attcode6);
				lotDetail6.setM_strLotatt_flag(lotatt_flag6);
				lotDetail6.setM_strLottype(lottype6);
				lotDetail6.setM_strLotsearch(lotsearch6);
				lotDetail6.setM_strLottypevalue(lottypevalue6);
				
				BaseLotDetail lotDetail7 = lotBus.getLotDetailByDetailId(lotdetailid7);
				lotDetail7.setM_strLotid(lotid);
				lotDetail7.setM_strAttname(attname7);
				lotDetail7.setM_strAttcode(attcode7);
				lotDetail7.setM_strLotatt_flag(lotatt_flag7);
				lotDetail7.setM_strLottype(lottype7);
				lotDetail7.setM_strLotsearch(lotsearch7);
				lotDetail7.setM_strLottypevalue(lottypevalue7);
				
				BaseLotDetail lotDetail8 = lotBus.getLotDetailByDetailId(lotdetailid8);
				lotDetail8.setM_strLotid(lotid);
				lotDetail8.setM_strAttname(attname8);
				lotDetail8.setM_strAttcode(attcode8);
				lotDetail8.setM_strLotatt_flag(lotatt_flag8);
				lotDetail8.setM_strLottype(lottype8);
				lotDetail8.setM_strLotsearch(lotsearch8);
				lotDetail8.setM_strLottypevalue(lottypevalue8);
				
				BaseLotDetail lotDetail9 = lotBus.getLotDetailByDetailId(lotdetailid9);
				lotDetail9.setM_strLotid(lotid);
				lotDetail9.setM_strAttname(attname9);
				lotDetail9.setM_strAttcode(attcode9);
				lotDetail9.setM_strLotatt_flag(lotatt_flag9);
				lotDetail9.setM_strLottype(lottype9);
				lotDetail9.setM_strLotsearch(lotsearch9);
				lotDetail9.setM_strLottypevalue(lottypevalue9);
				
				BaseLotDetail lotDetail10 = lotBus.getLotDetailByDetailId(lotdetailid10);
				lotDetail10.setM_strLotid(lotid);
				lotDetail10.setM_strAttname(attname10);
				lotDetail10.setM_strAttcode(attcode10);
				lotDetail10.setM_strLotatt_flag(lotatt_flag10);
				lotDetail10.setM_strLottype(lottype10);
				lotDetail10.setM_strLotsearch(lotsearch10);
				lotDetail10.setM_strLottypevalue(lottypevalue10);
				
				BaseLotDetail lotDetail11 = lotBus.getLotDetailByDetailId(lotdetailid11);
				lotDetail11.setM_strLotid(lotid);
				lotDetail11.setM_strAttname(attname11);
				lotDetail11.setM_strAttcode(attcode11);
				lotDetail11.setM_strLotatt_flag(lotatt_flag11);
				lotDetail11.setM_strLottype(lottype11);
				lotDetail11.setM_strLotsearch(lotsearch11);
				lotDetail11.setM_strLottypevalue(lottypevalue11);
				
				BaseLotDetail lotDetail12 = lotBus.getLotDetailByDetailId(lotdetailid12);
				lotDetail12.setM_strLotid(lotid);
				lotDetail12.setM_strAttname(attname12);
				lotDetail12.setM_strAttcode(attcode12);
				lotDetail12.setM_strLotatt_flag(lotatt_flag12);
				lotDetail12.setM_strLottype(lottype12);
				lotDetail12.setM_strLotsearch(lotsearch12);
				lotDetail12.setM_strLottypevalue(lottypevalue12);
				
				lotBus.updateLot(lot, lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
						lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12);
				Logger.info("用户" + strUserName + "修改了批次属性" + lotid);
			}
			
			strUrl = "/standard/jsp/base/lot/base_lot_list.jsp";
			
			PagingTool pt = lotBus.getLotQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性管理==>修改批次属性失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除批次属性
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
		lotBus = new LotBusImpl(dao);
		
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					lotBus.deleteLot(id[i]);	
					Logger.info("用户" + strUserName + "删除了批次属性" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/lot/base_lot_list.jsp";
			
			PagingTool pt = lotBus.getLotQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性管理==>批次属性删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}