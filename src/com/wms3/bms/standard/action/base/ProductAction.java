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
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.business.base.impl.ProductBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * 描述:物品管理
 * @author gui
 *
 */
public class ProductAction extends AbstractAction
{
	protected IProductBus productBus;
	protected int maxLine = 20;			//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String productname = CommonTools.getStrToGbk(request.getParameter("productname"));	//物品名称
        String productcode= CommonTools.getStrToGbk(request.getParameter("productcode"));    //客户ID
        String producttype= CommonTools.getStrToGbk(request.getParameter("producttype"));    //产品类别
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//物品Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //物品管理 查询列表
			{
				strUrl = "/standard/jsp/base/product/base_product_list.jsp";
				
				PagingTool pt = productBus.getProductQuery(productname,productcode,producttype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//物品管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/product/base_product_update.jsp";
				
				BaseProduct product = productBus.getProductById(id);
				request.setAttribute("product", product); 
			}else if(flag != null && flag.equals("3")) //选择物品 查询列表
			{
				strUrl = "/standard/jsp/common/common_product_list.jsp";
				
				PagingTool pt = productBus.getProductQuery(productname,productcode,producttype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品管理==>物品查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加物品
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
		
		String productname = CommonTools.getStrToGbk(request.getParameter("productname"));	// 产品标准名
	    String spec = CommonTools.getStrToGbk(request.getParameter("spec"));				// 规格
	    String barcode = CommonTools.getStrToGbk(request.getParameter("barcode"));			// 产品标准名
	    String pkspec = CommonTools.getStrToGbk(request.getParameter("pkspec"));			// 产品标准名
	    String recunit = CommonTools.getStrToGbk(request.getParameter("recunit"));			// 计量单位
	    String length = CommonTools.getStrToGbk(request.getParameter("length"));			// 长
	    String width = CommonTools.getStrToGbk(request.getParameter("width"));				// 宽
	    String height = CommonTools.getStrToGbk(request.getParameter("height"));			// 高
	    String upperlimit = CommonTools.getStrToGbk(request.getParameter("upperlimit"));	// 托盘上限
	    String lowerlimit = CommonTools.getStrToGbk(request.getParameter("lowerlimit"));	// 托盘下限
	    String pttypeid = CommonTools.getStrToGbk(request.getParameter("pttypeid"));		// 物品类别ID
	    String storageareaid = CommonTools.getStrToGbk(request.getParameter("storageareaid"));	// 存储环境ID
	    String storagespaceid = CommonTools.getStrToGbk(request.getParameter("storagespaceid"));// 存储货位ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));	// 分配规则ID
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));					// 批次属性IDID
	    String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));			// 包装ID
	    String validityterm = CommonTools.getStrToGbk(request.getParameter("validityterm"));	// 有效期
	    String validitytype = CommonTools.getStrToGbk(request.getParameter("validitytype"));	// 有效期类型
	    String productCode = CommonTools.getStrToGbk(request.getParameter("productCode"));				// 物品编码
	    String remark2 = CommonTools.getStrToGbk(request.getParameter("remark2"));				// 备注
	    String remark3 = CommonTools.getStrToGbk(request.getParameter("remark3"));				// 备注
	    String remark4 = CommonTools.getStrToGbk(request.getParameter("remark4"));				// 备注
	    String useflag = CommonTools.getStrToGbk(request.getParameter("useflag"));				// 可用标志
		String strUserName = (String)httpsession.getAttribute("userName");
	    String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));				// 可用标志
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		String back_msg = "";
		try
		{
			List ls1 = productBus.getProductByProductCode(productCode);
			if(ls1!=null && ls1.size()==0){
				//新增物品信息
				BaseProduct product = new BaseProduct();
				product.setProductname(productname);					// 物品名
				product.setSpec(spec);									// 规格
				product.setBarcode(barcode);							// 物品条码
				product.setPkspec(pkspec);								// 包装规格
				product.setRecunit(recunit);							// 缺省收货单位
				product.setLength(Double.parseDouble(length));			// 长
				product.setWidth(Double.parseDouble(width));			// 宽
			    product.setHeight(Double.parseDouble(height));			// 高
			    product.setUpperlimit(Double.parseDouble(upperlimit));	// 库存上限
			    product.setLowerlimit(Double.parseDouble(lowerlimit));
			    product.setPttypeid(pttypeid);							// 物品类别ID
			    product.setStorageareaid(storageareaid);				// 存储环境ID
			    product.setStoragespaceid(storagespaceid);				// 存储货位ID
				product.setAllocationid(allocationid);					// 分配规则ID
			    product.setPakageid(pakageid);		    				// 包装ID 
			    product.setLotid(lotid);               					// 批次ID
			    product.setValidityterm(Double.parseDouble(validityterm));// 有效期
			    product.setValiditytype(validitytype);					// 有效期类型
			    product.setProductCode(productCode);					// 物品编码
			    product.setRemark2(remark2);							// 备注
			    product.setRemark3(remark3);							// 备注
			    product.setRemark4(remark4);							// 备注
			    product.setIsproductmixed("N");      		             // 允许产品混放
			    product.setUseflag(useflag);							// 可用标志
			    product.setProducttype(producttype);

				productBus.addProduct(product);
				
				Logger.info("用户" + strUserName + "添加了物品" + productname);
			}else{
				back_msg = "该物品编码"+productCode+"已经存在 添加产品失败";
			}
        	

	        strUrl = "/standard/jsp/base/product/base_product_list.jsp";
	        PagingTool pt = productBus.getProductQuery("","","", strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("back_msg", back_msg);
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品管理==>增加物品失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改物品
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));						// 物品Id
		String productname = CommonTools.getStrToGbk(request.getParameter("productname"));		// 物品名
	    String spec = CommonTools.getStrToGbk(request.getParameter("spec"));					// 规格
	    String barcode = CommonTools.getStrToGbk(request.getParameter("barcode"));				// 物品条码
	    String pkspec = CommonTools.getStrToGbk(request.getParameter("pkspec"));				// 包装规格
	    String recunit = CommonTools.getStrToGbk(request.getParameter("recunit"));				// 收货单位
//	    String sendunit = CommonTools.getStrToGbk(request.getParameter("sendunit"));			// 发货单位
	    String length = CommonTools.getStrToGbk(request.getParameter("length"));				// 长
	    String width = CommonTools.getStrToGbk(request.getParameter("width"));					// 宽
	    String height = CommonTools.getStrToGbk(request.getParameter("height"));				// 高
	    String upperlimit = CommonTools.getStrToGbk(request.getParameter("upperlimit"));		// 库存上限
	    String lowerlimit = CommonTools.getStrToGbk(request.getParameter("lowerlimit"));		// 库存下限
//	    String weight = CommonTools.getStrToGbk(request.getParameter("weight"));				// 重量
//	    String netweight = CommonTools.getStrToGbk(request.getParameter("netweight"));			// 净重
//	    String tareweight = CommonTools.getStrToGbk(request.getParameter("tareweight"));		// 皮重
//	    String volume = CommonTools.getStrToGbk(request.getParameter("volume"));				// 体积
	    String pttypeid = CommonTools.getStrToGbk(request.getParameter("pttypeid"));			// 物品类别ID
	    String storageareaid = CommonTools.getStrToGbk(request.getParameter("storageareaid"));	// 存储环境ID
	    String storagespaceid = CommonTools.getStrToGbk(request.getParameter("storagespaceid"));// 存储货位ID
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));			// 上架规则ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));	// 分配规则ID
	    String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));		// 补货规则ID
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));					// 批次属性IDID
	    String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));			// 包装ID
	    String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));		// 客户ID
	    String producedate = CommonTools.getStrToGbk(request.getParameter("producedate"));		// 生产日期
	    String validityterm = CommonTools.getStrToGbk(request.getParameter("validityterm"));	// 有效期
	    String validitytype = CommonTools.getStrToGbk(request.getParameter("validitytype"));	// 有效期类型
	    String productCode = CommonTools.getStrToGbk(request.getParameter("productCode"));				// 备注
	    String remark2 = CommonTools.getStrToGbk(request.getParameter("remark2"));				// 备注
	    String remark3 = CommonTools.getStrToGbk(request.getParameter("remark3"));				// 备注
	    String remark5 = CommonTools.getStrToGbk(request.getParameter("remark4"));				// 备注
	    String remark4 = CommonTools.getStrToGbk(request.getParameter("remark5"));				// 备注
	    String remark6 = CommonTools.getStrToGbk(request.getParameter("remark6"));				// 备注
	    String remark7 = CommonTools.getStrToGbk(request.getParameter("remark7"));				// 备注
	    String isexcess = CommonTools.getStrToGbk(request.getParameter("isexcess"));			// 允许超量收货
	    String isproductmixed = CommonTools.getStrToGbk(request.getParameter("isproductmixed"));// 允许产品混放
	    String isbatchmixed = CommonTools.getStrToGbk(request.getParameter("isbatchmixed"));	// 允许批次混放
	    String useflag = CommonTools.getStrToGbk(request.getParameter("useflag"));				// 可用标志
	    String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));				// 可用标志

		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseProduct product = productBus.getProductById(id);
				product.setProductname(productname);					// 物品名
				product.setSpec(spec);									// 规格
				product.setBarcode(barcode);							// 物品条码
				product.setPkspec(pkspec);								// 包装规格
				product.setRecunit(recunit);							// 收货单位
//				product.setSendunit(sendunit);							// 发货单位
				product.setLength(Double.parseDouble(length));			// 长
				product.setWidth(Double.parseDouble(width));			// 宽
				product.setHeight(Double.parseDouble(height));			// 高
				product.setUpperlimit(Double.parseDouble(upperlimit));	// 库存上限
				product.setLowerlimit(Double.parseDouble(lowerlimit));	// 库存下限
//				product.setWeight(Double.parseDouble(weight));			// 重量
//				product.setNetweight(Double.parseDouble(netweight));	// 净重
//				product.setTareweight(Double.parseDouble(tareweight));	// 皮重
//				product.setVolume(Double.parseDouble(volume));			// 体积
				product.setPttypeid(pttypeid);							// 物品类别ID
				product.setStorageareaid(storageareaid);				// 存储环境ID
			    product.setStoragespaceid(storagespaceid);				// 存储货位ID
				product.setPutawayid(putawayid);						// 上架规则ID
				product.setAllocationid(allocationid);					// 分配规则ID
				product.setReplenishid(replenishid);					// 补货规则ID
				product.setPakageid(pakageid);							// 包装ID
				product.setLotid(lotid);               					// 批次ID
				product.setCustomerid(customerid);						// 客户ID
				product.setProducedate(producedate);					// 生产日期
				product.setValidityterm(Double.parseDouble(validityterm));// 有效期
				product.setValiditytype(validitytype);					// 有效期类型
				product.setProductCode(productCode);							// 产品编码
				product.setRemark2(remark2);							// 备注
				product.setRemark3(remark3);							// 备注
				product.setRemark5(remark4);							// 备注
				product.setRemark4(remark5);							// 备注
				product.setRemark6(remark6);							// 备注
				product.setRemark7(remark7);							// 备注
				product.setIsexcess(isexcess);            				// 是否超量收货
			    product.setIsproductmixed(isproductmixed);      		// 允许产品混放
			    product.setIsbatchmixed(isbatchmixed);        			// 允许批次混放
				product.setUseflag(useflag);							// 可用标志
				product.setProducttype(producttype);
				
				productBus.updateProduct(product);
				Logger.info("用户" + strUserName + "修改了物品" + id);
			}
			
			strUrl = "/standard/jsp/base/product/base_product_list.jsp";
			PagingTool pt = productBus.getProductQuery("","","", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品管理==>修改物品失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除物品
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
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		String back_msg = "";
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				int z =0;
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					if(id[i]!=null && id[i].length()>0){
						IInventoryBus inventoryBus = new InventoryBusImpl(dao);
						String  sql = inventoryBus.getInventoryHQL("", "", "", "", "", "", id[i], "", "", "", "");
						List ls = dao.searchEntities(sql);
						if(ls!=null && ls.size()==0){
							productBus.deleteProduct(id[i]);	
							Logger.info("用户" + strUserName + "删除了物品" + id[i]);
						}else{
							z++;
						}
						
					}
				}
				if(z>0){
					back_msg = "有"+z+"个产品删除失败";
				}
			}
			strUrl = "/standard/jsp/base/product/base_product_list.jsp";
			PagingTool pt = productBus.getProductQuery("","","", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("back_msg", back_msg);
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>物品管理==>物品删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}