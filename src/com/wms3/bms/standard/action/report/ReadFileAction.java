package com.wms3.bms.standard.action.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.ICustomerBus;
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.business.base.impl.CustomerBusImpl;
import com.wms3.bms.standard.business.base.impl.ProductBusImpl;
import com.wms3.bms.standard.entity.base.BaseCustomer;
import com.wms3.bms.standard.entity.base.BaseProduct;


/**
 * 描述:导入文件
 * @author fangjie 
 *
 */
public class ReadFileAction extends AbstractAction
{
	protected int maxLine = 20;		//分页显示的行数；
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");

		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		//1:客户 2:物品
		String strFlag = CommonTools.getStrToGbk(request.getParameter("flag"));
		//文件类型excel word, pdf...
		String strFileType = CommonTools.getStrToGbk(request.getParameter("fileType"));
		//文件
		String strPath = CommonTools.getStrToGbk(request.getParameter("path"));
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		if(strFlag!=null && strFlag.equals("1")){	//1:客户
			if(strFileType!=null && strFileType.equals("excel")){
				readCustomerExcel(request, response, strPath, dao); 
			}
		}else if(strFlag!=null && strFlag.equals("2")){	//2:物品
			if(strFileType!=null && strFileType.equals("excel")){
				readProductExcel(request, response, strPath, dao); 
			}
		}
		return null;
	}

	/** 
     * 导入客户信息的Excel文件
	 * @param response 
	 * @param request 
     * @param filePath 
	 * @param dao 
	 * 假设客户信息的Excel文件的列为：客户编号，客户名称，联系人，联系电话，地址
	 * @throws IOException 
	 * @throws ServletException 
     */  
    public void readCustomerExcel(HttpServletRequest request, HttpServletResponse response, String filePath, EntityDAO dao) throws Exception  
    {  
		String strUserCode = (String)request.getSession().getAttribute("userCode");
		String strUserName = (String)request.getSession().getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		ICustomerBus customerBus = new CustomerBusImpl(dao);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
        try  
        {  
            InputStream is = new FileInputStream(filePath);  
            Workbook rwb = Workbook.getWorkbook(is);  
            //Sheet st = rwb.getSheet("0")这里有两种方法获取sheet表,1为名字，而为下标，从0开始   
            Sheet st = rwb.getSheet("Sheet1");  
            
            int rs=st.getColumns();  
            int rows=st.getRows();  
            //System.out.println("列数===>"+rs+"行数："+rows);  //输出   
              
            for(int k=1; k<rows; k++){//行   
            	
            	List list = new ArrayList();     
            	for(int i=1; i<rs; i++){//列   
                           
            		Cell c00 = st.getCell(i,k);  
		            //通用的获取cell值的方式,返回字符串   
		            String strc00 = c00.getContents();  
		            //获得cell具体类型值的方式   
		            if(c00.getType() == CellType.LABEL)  
		            {  
		                LabelCell labelc00 = (LabelCell)c00;  
		                strc00 = labelc00.getString();  
		            }  
//		            //excel 类型为时间类型处理;   
//		            if(c00.getType()==CellType.DATE){  
//		                DateCell dc=(DateCell)c00;    
//		                strc00 = sdf.format(dc.getDate());  
//		                  
//		            }  
//		            //excel 类型为数值类型处理;   
//		            if(c00.getType()==CellType.NUMBER|| c00.getType()==CellType.NUMBER_FORMULA){ 
//		                NumberCell nc=(NumberCell)c00;  
//		                strc00=""+nc.getValue();  
//		            }
		              
		            list.add(strc00); 
		            //System.out.println(">第"+k+"行"+list.get(i-1));  //输出   

			        //列，行   
					//data2=String.valueOf(st.getCell(1,k).getContents());   
					//data2=data2.replace("/", "-");   
					//java.util.Date dt=sdf.parse(data2);       
					//System.out.println(sdf.format(dt));                                  
            	}  
            	
            	//新增客户信息
    			BaseCustomer customer = new BaseCustomer();
    			customer.setShortname("");							// 客户简称
    			customer.setCustomername(list.get(0).toString());	// 客户全称
    			customer.setCustomertype("");						// 客户分类
    			customer.setContact(list.get(1).toString());		// 联系人
    			customer.setPhone(list.get(2).toString());			// 联系电话
    			customer.setFax("");								// 传真
    			customer.setAddress(list.get(3).toString());		// 地址
    			customer.setBank("");					// 开户银行
    			customer.setCreatetime(currentTime);	// 创建时间
    			customer.setCreatemanid(strUserCode);	// 创建人
    			customer.setUpdatetime(currentTime);	// 最后修改时间
    			customer.setUpdatemanid(strUserCode);	// 最后修改人
    			customer.setUseflag("Y");				// 是否启用
    			customer.setPutawayid("");				// 上架规则ID
    			customer.setAllocationid("");			// 分配规则ID
    			customer.setReplenishid("");			// 补货规则ID
    			customer.setLinenumber("");				// 线路号
    			customerBus.addCustomer(customer);
    			Logger.info("用户:" + strUserName + "导入了客户:" + list.get(1).toString());
            }  
            //关闭
            rwb.close();  
            
            strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
            //页面刷新
            PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			((HttpSession)request.getSession(false)).setAttribute("paging", pt);
			
			String back_msg = "文件导入成功！";
			request.setAttribute("back_msg", back_msg);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
        }  
        catch(Exception er)  
        {  
        	Logger.error("基本信息==>客户管理==>导入客户信息的Excel文件失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
        }  
    }
    
	/** 
     * 导入物品信息的Excel文件
	 * @param response 
	 * @param request 
     * @param filePath 
	 * @param dao 
	 * 假设物品信息的Excel文件的列为：物品编号，物品名，规格，条码，包装规格
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IOException 
	 * @throws ServletException 
     */  
	private void readProductExcel(HttpServletRequest request, HttpServletResponse response, String filePath, EntityDAO dao) throws Exception {
		
		String strUserName = (String)request.getSession().getAttribute("userName");
		IProductBus productBus = new ProductBusImpl(dao);
		
        try  
        {  
            InputStream is = new FileInputStream(filePath);  
            Workbook rwb = Workbook.getWorkbook(is);  
            Sheet st = rwb.getSheet("Sheet1");  
            
            int rs=st.getColumns();  
            int rows=st.getRows();  
            //System.out.println("列数===>"+rs+"行数："+rows);  //输出   
              
            for(int k=1; k<rows; k++){//行   
            	
            	List list = new ArrayList();     
            	for(int i=1; i<rs; i++){//列   
                           
            		Cell c00 = st.getCell(i,k);  
		            //通用的获取cell值的方式,返回字符串   
		            String strc00 = c00.getContents();  
		            //获得cell具体类型值的方式   
		            if(c00.getType() == CellType.LABEL)  
		            {  
		                LabelCell labelc00 = (LabelCell)c00;  
		                strc00 = labelc00.getString();  
		            }  
		              
		            list.add(strc00); 
		            //System.out.println(">第"+k+"行"+list.get(i-1));  //输出                    
            	}  
            	
            	//新增物品信息	
    			BaseProduct product = new BaseProduct();
    			product.setProductname(list.get(0).toString());		// 物品名
    			product.setSpec(list.get(1).toString());			// 规格
				product.setBarcode(list.get(2).toString());			// 物品条码
				product.setPkspec(list.get(3).toString());			// 包装规格
    			productBus.addProduct(product);
    			Logger.info("用户:" + strUserName + "导入了物品:" + list.get(0).toString());
            }  
            //关闭   
            rwb.close();  
            
            
            //页面刷新
            strUrl = "/standard/jsp/base/product/base_product_list.jsp";
	        PagingTool pt = productBus.getProductQuery("","","", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			((HttpSession)request.getSession(false)).setAttribute("paging", pt);
			
			String back_msg = "文件导入成功！";
			request.setAttribute("back_msg", back_msg);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
        }  
        catch(Exception er)  
        {  
        	Logger.error("基本信息==>物品管理==>导入物品信息的Excel文件失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
        }  
		
	}

}
