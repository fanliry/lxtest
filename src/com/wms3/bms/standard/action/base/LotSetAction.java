package com.wms3.bms.standard.action.base;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.ILotSetBus;
import com.wms3.bms.standard.business.base.impl.LotSetBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;

/**
 * 描述:批次属性设置管理
 * @author gui
 *
 */
public class LotSetAction extends AbstractAction
{
	protected ILotSetBus lotsetBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));	//类型
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //批次属性设置管理 查询列表
			{
				strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
				
				List ls = lotsetBus.getLotsetType();
				
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")) //批次属性设置管理 查询详细列表
			{
				strUrl = "/standard/jsp/base/lot/base_lotset_detail.jsp";
				
				List ls = lotsetBus.getLotsetByType(lottype);
				
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("3"))//批次属性设置管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/lot/base_lotset_update.jsp";
				
				//批次详细信息
				List ls = lotsetBus.getLotsetByType(lottype);
				
				request.setAttribute("lotset1", ls.get(0));
				request.setAttribute("lotset2", ls.get(1));
				request.setAttribute("lotset3", ls.get(2));
				request.setAttribute("lotset4", ls.get(3));
				
				request.setAttribute("lotset5", ls.get(4));
				request.setAttribute("lotset6", ls.get(5));
				request.setAttribute("lotset7", ls.get(6));
				request.setAttribute("lotset8", ls.get(7));
				
				request.setAttribute("lotset9", ls.get(8));
				request.setAttribute("lotset10", ls.get(9));
				request.setAttribute("lotset11", ls.get(10));
				request.setAttribute("lotset12", ls.get(11));
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性设置管理==>批次属性设置查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加批次属性设置
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
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));	//类型
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));		//备注
		
		String lotname1 = CommonTools.getStrToGb2312(request.getParameter("lotname1"));	//批次属性名称
		String lotid1 = CommonTools.getStrToGb2312(request.getParameter("lotid1"));		//批次属性ID
		String islot1 = CommonTools.getStrToGb2312(request.getParameter("islot1"));		//是否显示或者是否分组统计 Y N
		String lotseq1 = CommonTools.getStrToGb2312(request.getParameter("lotseq1"));	//显示顺序或者分组统计的顺序
		
		String lotname2 = CommonTools.getStrToGb2312(request.getParameter("lotname2"));	//批次属性名称
		String lotid2 = CommonTools.getStrToGb2312(request.getParameter("lotid2"));		//批次属性ID
		String islot2 = CommonTools.getStrToGb2312(request.getParameter("islot2"));		//是否显示或者是否分组统计 Y N
		String lotseq2 = CommonTools.getStrToGb2312(request.getParameter("lotseq2"));	//显示顺序或者分组统计的顺序
		
		String lotname3 = CommonTools.getStrToGb2312(request.getParameter("lotname3"));	//批次属性名称
		String lotid3 = CommonTools.getStrToGb2312(request.getParameter("lotid3"));		//批次属性ID
		String islot3 = CommonTools.getStrToGb2312(request.getParameter("islot3"));		//是否显示或者是否分组统计 Y N
		String lotseq3 = CommonTools.getStrToGb2312(request.getParameter("lotseq3"));	//显示顺序或者分组统计的顺序
		
		String lotname4 = CommonTools.getStrToGb2312(request.getParameter("lotname4"));	//批次属性名称
		String lotid4 = CommonTools.getStrToGb2312(request.getParameter("lotid4"));		//批次属性ID
		String islot4 = CommonTools.getStrToGb2312(request.getParameter("islot4"));		//是否显示或者是否分组统计 Y N
		String lotseq4 = CommonTools.getStrToGb2312(request.getParameter("lotseq4"));	//显示顺序或者分组统计的顺序
		
		String lotname5 = CommonTools.getStrToGb2312(request.getParameter("lotname5"));	//批次属性名称
		String lotid5 = CommonTools.getStrToGb2312(request.getParameter("lotid5"));		//批次属性ID
		String islot5 = CommonTools.getStrToGb2312(request.getParameter("islot5"));		//是否显示或者是否分组统计 Y N
		String lotseq5 = CommonTools.getStrToGb2312(request.getParameter("lotseq5"));	//显示顺序或者分组统计的顺序
		
		String lotname6 = CommonTools.getStrToGb2312(request.getParameter("lotname6"));	//批次属性名称
		String lotid6 = CommonTools.getStrToGb2312(request.getParameter("lotid6"));		//批次属性ID
		String islot6 = CommonTools.getStrToGb2312(request.getParameter("islot6"));		//是否显示或者是否分组统计 Y N
		String lotseq6 = CommonTools.getStrToGb2312(request.getParameter("lotseq6"));	//显示顺序或者分组统计的顺序
		
		String lotname7 = CommonTools.getStrToGb2312(request.getParameter("lotname7"));	//批次属性名称
		String lotid7 = CommonTools.getStrToGb2312(request.getParameter("lotid7"));		//批次属性ID
		String islot7 = CommonTools.getStrToGb2312(request.getParameter("islot7"));		//是否显示或者是否分组统计 Y N
		String lotseq7 = CommonTools.getStrToGb2312(request.getParameter("lotseq7"));	//显示顺序或者分组统计的顺序
		
		String lotname8 = CommonTools.getStrToGb2312(request.getParameter("lotname8"));	//批次属性名称
		String lotid8 = CommonTools.getStrToGb2312(request.getParameter("lotid8"));		//批次属性ID
		String islot8 = CommonTools.getStrToGb2312(request.getParameter("islot8"));		//是否显示或者是否分组统计 Y N
		String lotseq8 = CommonTools.getStrToGb2312(request.getParameter("lotseq8"));	//显示顺序或者分组统计的顺序
		
		String lotname9 = CommonTools.getStrToGb2312(request.getParameter("lotname9"));	//批次属性名称
		String lotid9 = CommonTools.getStrToGb2312(request.getParameter("lotid9"));		//批次属性ID
		String islot9 = CommonTools.getStrToGb2312(request.getParameter("islot9"));		//是否显示或者是否分组统计 Y N
		String lotseq9 = CommonTools.getStrToGb2312(request.getParameter("lotseq9"));	//显示顺序或者分组统计的顺序
		
		String lotname10 = CommonTools.getStrToGb2312(request.getParameter("lotname10"));	//批次属性名称
		String lotid10 = CommonTools.getStrToGb2312(request.getParameter("lotid10"));		//批次属性ID
		String islot10 = CommonTools.getStrToGb2312(request.getParameter("islot10"));		//是否显示或者是否分组统计 Y N
		String lotseq10 = CommonTools.getStrToGb2312(request.getParameter("lotseq10"));		//显示顺序或者分组统计的顺序
		
		String lotname11 = CommonTools.getStrToGb2312(request.getParameter("lotname11"));	//批次属性名称
		String lotid11 = CommonTools.getStrToGb2312(request.getParameter("lotid11"));		//批次属性ID
		String islot11 = CommonTools.getStrToGb2312(request.getParameter("islot11"));		//是否显示或者是否分组统计 Y N
		String lotseq11 = CommonTools.getStrToGb2312(request.getParameter("lotseq11"));		//显示顺序或者分组统计的顺序
		
		String lotname12 = CommonTools.getStrToGb2312(request.getParameter("lotname12"));	//批次属性名称
		String lotid12 = CommonTools.getStrToGb2312(request.getParameter("lotid12"));		//批次属性ID
		String islot12 = CommonTools.getStrToGb2312(request.getParameter("islot12"));		//是否显示或者是否分组统计 Y N
		String lotseq12 = CommonTools.getStrToGb2312(request.getParameter("lotseq12"));		//显示顺序或者分组统计的顺序
		
		String strUserName = (String)httpsession.getAttribute("userName");

		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			String back_msg = "Y";
			//判断该批次类型是否已经存在
			if(lotsetBus.isLotsetExist(lottype)){
				
				back_msg = "这个类型的批次设置已经存在了！";
			}else{
				
				BaseLotSet lotset1 = new BaseLotSet();
				lotset1.setLottype(lottype);	//类型
				lotset1.setRemark(remark);		//备注
				lotset1.setLotname(lotname1);	//批次属性名称
				lotset1.setLotid(lotid1);		//批次属性ID
				lotset1.setIslot(islot1);		//是否显示或者是否分组统计 Y N
				lotset1.setLotseq(Integer.parseInt(lotseq1));		//显示顺序或者分组统计的顺序
	
				BaseLotSet lotset2 = new BaseLotSet();
				lotset2.setLottype(lottype);	//类型
				lotset2.setRemark(remark);		//备注
				lotset2.setLotname(lotname2);	//批次属性名称
				lotset2.setLotid(lotid2);		//批次属性ID
				lotset2.setIslot(islot2);		//是否显示或者是否分组统计 Y N
				lotset2.setLotseq(Integer.parseInt(lotseq2));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset3 = new BaseLotSet();
				lotset3.setLottype(lottype);	//类型
				lotset3.setRemark(remark);		//备注
				lotset3.setLotname(lotname3);	//批次属性名称
				lotset3.setLotid(lotid3);		//批次属性ID
				lotset3.setIslot(islot3);		//是否显示或者是否分组统计 Y N
				lotset3.setLotseq(Integer.parseInt(lotseq3));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset4 = new BaseLotSet();
				lotset4.setLottype(lottype);	//类型
				lotset4.setRemark(remark);		//备注
				lotset4.setLotname(lotname4);	//批次属性名称
				lotset4.setLotid(lotid4);		//批次属性ID
				lotset4.setIslot(islot4);		//是否显示或者是否分组统计 Y N
				lotset4.setLotseq(Integer.parseInt(lotseq4));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset5 = new BaseLotSet();
				lotset5.setLottype(lottype);	//类型
				lotset5.setRemark(remark);		//备注
				lotset5.setLotname(lotname5);	//批次属性名称
				lotset5.setLotid(lotid5);		//批次属性ID
				lotset5.setIslot(islot5);		//是否显示或者是否分组统计 Y N
				lotset5.setLotseq(Integer.parseInt(lotseq5));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset6 = new BaseLotSet();
				lotset6.setLottype(lottype);	//类型
				lotset6.setRemark(remark);		//备注
				lotset6.setLotname(lotname6);	//批次属性名称
				lotset6.setLotid(lotid6);		//批次属性ID
				lotset6.setIslot(islot6);		//是否显示或者是否分组统计 Y N
				lotset6.setLotseq(Integer.parseInt(lotseq6));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset7 = new BaseLotSet();
				lotset7.setLottype(lottype);	//类型
				lotset7.setRemark(remark);		//备注
				lotset7.setLotname(lotname7);	//批次属性名称
				lotset7.setLotid(lotid7);		//批次属性ID
				lotset7.setIslot(islot7);		//是否显示或者是否分组统计 Y N
				lotset7.setLotseq(Integer.parseInt(lotseq7));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset8 = new BaseLotSet();
				lotset8.setLottype(lottype);	//类型
				lotset8.setRemark(remark);		//备注
				lotset8.setLotname(lotname8);	//批次属性名称
				lotset8.setLotid(lotid8);		//批次属性ID
				lotset8.setIslot(islot8);		//是否显示或者是否分组统计 Y N
				lotset8.setLotseq(Integer.parseInt(lotseq8));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset9 = new BaseLotSet();
				lotset9.setLottype(lottype);	//类型
				lotset9.setRemark(remark);		//备注
				lotset9.setLotname(lotname9);	//批次属性名称
				lotset9.setLotid(lotid9);		//批次属性ID
				lotset9.setIslot(islot9);		//是否显示或者是否分组统计 Y N
				lotset9.setLotseq(Integer.parseInt(lotseq9));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset10 = new BaseLotSet();
				lotset10.setLottype(lottype);	//类型
				lotset10.setRemark(remark);		//备注
				lotset10.setLotname(lotname10);	//批次属性名称
				lotset10.setLotid(lotid10);		//批次属性ID
				lotset10.setIslot(islot10);		//是否显示或者是否分组统计 Y N
				lotset10.setLotseq(Integer.parseInt(lotseq10));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset11 = new BaseLotSet();
				lotset11.setLottype(lottype);	//类型
				lotset11.setRemark(remark);		//备注
				lotset11.setLotname(lotname11);	//批次属性名称
				lotset11.setLotid(lotid11);		//批次属性ID
				lotset11.setIslot(islot11);		//是否显示或者是否分组统计 Y N
				lotset11.setLotseq(Integer.parseInt(lotseq11));		//显示顺序或者分组统计的顺序
				
				BaseLotSet lotset12 = new BaseLotSet();
				lotset12.setLottype(lottype);	//类型
				lotset12.setRemark(remark);		//备注
				lotset12.setLotname(lotname12);	//批次属性名称
				lotset12.setLotid(lotid12);		//批次属性ID
				lotset12.setIslot(islot12);		//是否显示或者是否分组统计 Y N
				lotset12.setLotseq(Integer.parseInt(lotseq12));		//显示顺序或者分组统计的顺序
				
				lotsetBus.addLotset(lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, lotset7, lotset8, lotset9, lotset10, lotset11, lotset12);
				
				Logger.info("用户" + strUserName + "添加了批次属性设置，类型" + lottype);
                
                //更新显示的批次
                HashMap<String, List> hsLot = lotsetBus.getViewLot();//显示的批次设置  
                hsSysParam.put("viewLot", hsLot);
                httpsession.setAttribute("viewLot",hsLot );     //显示的批次
                
			}
			strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
			
			List ls = lotsetBus.getLotsetType();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", back_msg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性设置管理==>增加批次属性设置失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改批次属性设置
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
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));		//类型
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));			//备注
		
		String lotattid1 = CommonTools.getStrToGb2312(request.getParameter("lotattid1"));	//ID
		String lotname1 = CommonTools.getStrToGb2312(request.getParameter("lotname1"));		//批次属性名称
		String lotid1 = CommonTools.getStrToGb2312(request.getParameter("lotid1"));			//批次属性ID
		String islot1 = CommonTools.getStrToGb2312(request.getParameter("islot1"));			//是否显示或者是否分组统计 Y N
		String lotseq1 = CommonTools.getStrToGb2312(request.getParameter("lotseq1"));		//显示顺序或者分组统计的顺序
		
		String lotattid2 = CommonTools.getStrToGb2312(request.getParameter("lotattid2"));	//ID
		String lotname2 = CommonTools.getStrToGb2312(request.getParameter("lotname2"));		//批次属性名称
		String lotid2 = CommonTools.getStrToGb2312(request.getParameter("lotid2"));			//批次属性ID
		String islot2 = CommonTools.getStrToGb2312(request.getParameter("islot2"));			//是否显示或者是否分组统计 Y N
		String lotseq2 = CommonTools.getStrToGb2312(request.getParameter("lotseq2"));		//显示顺序或者分组统计的顺序
		
		String lotattid3 = CommonTools.getStrToGb2312(request.getParameter("lotattid3"));	//ID
		String lotname3 = CommonTools.getStrToGb2312(request.getParameter("lotname3"));		//批次属性名称
		String lotid3 = CommonTools.getStrToGb2312(request.getParameter("lotid3"));			//批次属性ID
		String islot3 = CommonTools.getStrToGb2312(request.getParameter("islot3"));			//是否显示或者是否分组统计 Y N
		String lotseq3 = CommonTools.getStrToGb2312(request.getParameter("lotseq3"));		//显示顺序或者分组统计的顺序
		
		String lotattid4 = CommonTools.getStrToGb2312(request.getParameter("lotattid4"));	//ID
		String lotname4 = CommonTools.getStrToGb2312(request.getParameter("lotname4"));		//批次属性名称
		String lotid4 = CommonTools.getStrToGb2312(request.getParameter("lotid4"));			//批次属性ID
		String islot4 = CommonTools.getStrToGb2312(request.getParameter("islot4"));			//是否显示或者是否分组统计 Y N
		String lotseq4 = CommonTools.getStrToGb2312(request.getParameter("lotseq4"));		//显示顺序或者分组统计的顺序
		
		String lotattid5 = CommonTools.getStrToGb2312(request.getParameter("lotattid5"));	//ID
		String lotname5 = CommonTools.getStrToGb2312(request.getParameter("lotname5"));		//批次属性名称
		String lotid5 = CommonTools.getStrToGb2312(request.getParameter("lotid5"));			//批次属性ID
		String islot5 = CommonTools.getStrToGb2312(request.getParameter("islot5"));			//是否显示或者是否分组统计 Y N
		String lotseq5 = CommonTools.getStrToGb2312(request.getParameter("lotseq5"));		//显示顺序或者分组统计的顺序
		
		String lotattid6 = CommonTools.getStrToGb2312(request.getParameter("lotattid6"));	//ID
		String lotname6 = CommonTools.getStrToGb2312(request.getParameter("lotname6"));		//批次属性名称
		String lotid6 = CommonTools.getStrToGb2312(request.getParameter("lotid6"));			//批次属性ID
		String islot6 = CommonTools.getStrToGb2312(request.getParameter("islot6"));			//是否显示或者是否分组统计 Y N
		String lotseq6 = CommonTools.getStrToGb2312(request.getParameter("lotseq6"));		//显示顺序或者分组统计的顺序
		
		String lotattid7 = CommonTools.getStrToGb2312(request.getParameter("lotattid7"));	//ID
		String lotname7 = CommonTools.getStrToGb2312(request.getParameter("lotname7"));		//批次属性名称
		String lotid7 = CommonTools.getStrToGb2312(request.getParameter("lotid7"));			//批次属性ID
		String islot7 = CommonTools.getStrToGb2312(request.getParameter("islot7"));			//是否显示或者是否分组统计 Y N
		String lotseq7 = CommonTools.getStrToGb2312(request.getParameter("lotseq7"));		//显示顺序或者分组统计的顺序
		
		String lotattid8 = CommonTools.getStrToGb2312(request.getParameter("lotattid8"));	//ID
		String lotname8 = CommonTools.getStrToGb2312(request.getParameter("lotname8"));		//批次属性名称
		String lotid8 = CommonTools.getStrToGb2312(request.getParameter("lotid8"));			//批次属性ID
		String islot8 = CommonTools.getStrToGb2312(request.getParameter("islot8"));			//是否显示或者是否分组统计 Y N
		String lotseq8 = CommonTools.getStrToGb2312(request.getParameter("lotseq8"));		//显示顺序或者分组统计的顺序
		
		String lotattid9 = CommonTools.getStrToGb2312(request.getParameter("lotattid9"));	//ID
		String lotname9 = CommonTools.getStrToGb2312(request.getParameter("lotname9"));		//批次属性名称
		String lotid9 = CommonTools.getStrToGb2312(request.getParameter("lotid9"));			//批次属性ID
		String islot9 = CommonTools.getStrToGb2312(request.getParameter("islot9"));			//是否显示或者是否分组统计 Y N
		String lotseq9 = CommonTools.getStrToGb2312(request.getParameter("lotseq9"));		//显示顺序或者分组统计的顺序
		
		String lotattid10 = CommonTools.getStrToGb2312(request.getParameter("lotattid10"));	//ID
		String lotname10 = CommonTools.getStrToGb2312(request.getParameter("lotname10"));	//批次属性名称
		String lotid10 = CommonTools.getStrToGb2312(request.getParameter("lotid10"));		//批次属性ID
		String islot10 = CommonTools.getStrToGb2312(request.getParameter("islot10"));		//是否显示或者是否分组统计 Y N
		String lotseq10 = CommonTools.getStrToGb2312(request.getParameter("lotseq10"));		//显示顺序或者分组统计的顺序
		
		String lotattid11 = CommonTools.getStrToGb2312(request.getParameter("lotattid11"));	//ID
		String lotname11 = CommonTools.getStrToGb2312(request.getParameter("lotname11"));	//批次属性名称
		String lotid11 = CommonTools.getStrToGb2312(request.getParameter("lotid11"));		//批次属性ID
		String islot11 = CommonTools.getStrToGb2312(request.getParameter("islot11"));		//是否显示或者是否分组统计 Y N
		String lotseq11 = CommonTools.getStrToGb2312(request.getParameter("lotseq11"));		//显示顺序或者分组统计的顺序
		
		String lotattid12 = CommonTools.getStrToGb2312(request.getParameter("lotattid12"));	//ID
		String lotname12 = CommonTools.getStrToGb2312(request.getParameter("lotname12"));	//批次属性名称
		String lotid12 = CommonTools.getStrToGb2312(request.getParameter("lotid12"));		//批次属性ID
		String islot12 = CommonTools.getStrToGb2312(request.getParameter("islot12"));		//是否显示或者是否分组统计 Y N
		String lotseq12 = CommonTools.getStrToGb2312(request.getParameter("lotseq12"));		//显示顺序或者分组统计的顺序
		
		String strUserName = (String)httpsession.getAttribute("userName");

		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			BaseLotSet lotset1 = lotsetBus.getLotsetById(lotattid1);
			lotset1.setLottype(lottype);	//类型
			lotset1.setRemark(remark);		//备注
			lotset1.setLotname(lotname1);	//批次属性名称
			lotset1.setLotid(lotid1);		//批次属性ID
			lotset1.setIslot(islot1);		//是否显示或者是否分组统计 Y N
			lotset1.setLotseq(Integer.parseInt(lotseq1));		//显示顺序或者分组统计的顺序

			BaseLotSet lotset2 = lotsetBus.getLotsetById(lotattid2);
			lotset2.setLottype(lottype);	//类型
			lotset2.setRemark(remark);		//备注
			lotset2.setLotname(lotname2);	//批次属性名称
			lotset2.setLotid(lotid2);		//批次属性ID
			lotset2.setIslot(islot2);		//是否显示或者是否分组统计 Y N
			lotset2.setLotseq(Integer.parseInt(lotseq2));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset3 = lotsetBus.getLotsetById(lotattid3);
			lotset3.setLottype(lottype);	//类型
			lotset3.setRemark(remark);		//备注
			lotset3.setLotname(lotname3);	//批次属性名称
			lotset3.setLotid(lotid3);		//批次属性ID
			lotset3.setIslot(islot3);		//是否显示或者是否分组统计 Y N
			lotset3.setLotseq(Integer.parseInt(lotseq3));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset4 = lotsetBus.getLotsetById(lotattid4);
			lotset4.setLottype(lottype);	//类型
			lotset4.setRemark(remark);		//备注
			lotset4.setLotname(lotname4);	//批次属性名称
			lotset4.setLotid(lotid4);		//批次属性ID
			lotset4.setIslot(islot4);		//是否显示或者是否分组统计 Y N
			lotset4.setLotseq(Integer.parseInt(lotseq4));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset5 = lotsetBus.getLotsetById(lotattid5);
			lotset5.setLottype(lottype);	//类型
			lotset5.setRemark(remark);		//备注
			lotset5.setLotname(lotname5);	//批次属性名称
			lotset5.setLotid(lotid5);		//批次属性ID
			lotset5.setIslot(islot5);		//是否显示或者是否分组统计 Y N
			lotset5.setLotseq(Integer.parseInt(lotseq5));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset6 = lotsetBus.getLotsetById(lotattid6);
			lotset6.setLottype(lottype);	//类型
			lotset6.setRemark(remark);		//备注
			lotset6.setLotname(lotname6);	//批次属性名称
			lotset6.setLotid(lotid6);		//批次属性ID
			lotset6.setIslot(islot6);		//是否显示或者是否分组统计 Y N
			lotset6.setLotseq(Integer.parseInt(lotseq6));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset7 = lotsetBus.getLotsetById(lotattid7);
			lotset7.setLottype(lottype);	//类型
			lotset7.setRemark(remark);		//备注
			lotset7.setLotname(lotname7);	//批次属性名称
			lotset7.setLotid(lotid7);		//批次属性ID
			lotset7.setIslot(islot7);		//是否显示或者是否分组统计 Y N
			lotset7.setLotseq(Integer.parseInt(lotseq7));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset8 = lotsetBus.getLotsetById(lotattid8);
			lotset8.setLottype(lottype);	//类型
			lotset8.setRemark(remark);		//备注
			lotset8.setLotname(lotname8);	//批次属性名称
			lotset8.setLotid(lotid8);		//批次属性ID
			lotset8.setIslot(islot8);		//是否显示或者是否分组统计 Y N
			lotset8.setLotseq(Integer.parseInt(lotseq8));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset9 = lotsetBus.getLotsetById(lotattid9);
			lotset9.setLottype(lottype);	//类型
			lotset9.setRemark(remark);		//备注
			lotset9.setLotname(lotname9);	//批次属性名称
			lotset9.setLotid(lotid9);		//批次属性ID
			lotset9.setIslot(islot9);		//是否显示或者是否分组统计 Y N
			lotset9.setLotseq(Integer.parseInt(lotseq9));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset10 = lotsetBus.getLotsetById(lotattid10);
			lotset10.setLottype(lottype);	//类型
			lotset10.setRemark(remark);		//备注
			lotset10.setLotname(lotname10);	//批次属性名称
			lotset10.setLotid(lotid10);		//批次属性ID
			lotset10.setIslot(islot10);		//是否显示或者是否分组统计 Y N
			lotset10.setLotseq(Integer.parseInt(lotseq10));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset11 = lotsetBus.getLotsetById(lotattid11);
			lotset11.setLottype(lottype);	//类型
			lotset11.setRemark(remark);		//备注
			lotset11.setLotname(lotname11);	//批次属性名称
			lotset11.setLotid(lotid11);		//批次属性ID
			lotset11.setIslot(islot11);		//是否显示或者是否分组统计 Y N
			lotset11.setLotseq(Integer.parseInt(lotseq11));		//显示顺序或者分组统计的顺序
			
			BaseLotSet lotset12 = lotsetBus.getLotsetById(lotattid12);
			lotset12.setLottype(lottype);	//类型
			lotset12.setRemark(remark);		//备注
			lotset12.setLotname(lotname12);	//批次属性名称
			lotset12.setLotid(lotid12);		//批次属性ID
			lotset12.setIslot(islot12);		//是否显示或者是否分组统计 Y N
			lotset12.setLotseq(Integer.parseInt(lotseq12));		//显示顺序或者分组统计的顺序
			
			lotsetBus.updateLotset(lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, lotset7, lotset8, lotset9, lotset10, lotset11, lotset12);
			
			Logger.info("用户" + strUserName + "修改了批次属性设置，类型" + lottype);
            
            //更新显示的批次
            HashMap<String, List> hsLot = lotsetBus.getViewLot();//显示的批次设置  
            hsSysParam.put("viewLot", hsLot);
            httpsession.setAttribute("viewLot",hsLot );     //显示的批次
            

			strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
			
			List ls = lotsetBus.getLotsetType();
			
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性设置管理==>修改批次属性设置失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除批次属性设置
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
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));	//类型
		String strUserName = (String)httpsession.getAttribute("userName");

		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			//删除
			lotsetBus.deleteLotset(lottype);	
			Logger.info("用户" + strUserName + "删除了批次属性设，类型：" + lottype);
            
            //更新显示的批次
            HashMap<String, List> hsLot = lotsetBus.getViewLot();//显示的批次设置  
            hsSysParam.put("viewLot", hsLot);
            httpsession.setAttribute("viewLot",hsLot );     //显示的批次
			
			strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
			
			List ls = lotsetBus.getLotsetType();
			
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次属性设置管理==>批次属性设置删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}