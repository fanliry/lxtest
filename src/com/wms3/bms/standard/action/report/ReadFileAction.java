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
 * ����:�����ļ�
 * @author fangjie 
 *
 */
public class ReadFileAction extends AbstractAction
{
	protected int maxLine = 20;		//��ҳ��ʾ��������
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");

		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		//1:�ͻ� 2:��Ʒ
		String strFlag = CommonTools.getStrToGbk(request.getParameter("flag"));
		//�ļ�����excel word, pdf...
		String strFileType = CommonTools.getStrToGbk(request.getParameter("fileType"));
		//�ļ�
		String strPath = CommonTools.getStrToGbk(request.getParameter("path"));
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		if(strFlag!=null && strFlag.equals("1")){	//1:�ͻ�
			if(strFileType!=null && strFileType.equals("excel")){
				readCustomerExcel(request, response, strPath, dao); 
			}
		}else if(strFlag!=null && strFlag.equals("2")){	//2:��Ʒ
			if(strFileType!=null && strFileType.equals("excel")){
				readProductExcel(request, response, strPath, dao); 
			}
		}
		return null;
	}

	/** 
     * ����ͻ���Ϣ��Excel�ļ�
	 * @param response 
	 * @param request 
     * @param filePath 
	 * @param dao 
	 * ����ͻ���Ϣ��Excel�ļ�����Ϊ���ͻ���ţ��ͻ����ƣ���ϵ�ˣ���ϵ�绰����ַ
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
            //Sheet st = rwb.getSheet("0")���������ַ�����ȡsheet��,1Ϊ���֣���Ϊ�±꣬��0��ʼ   
            Sheet st = rwb.getSheet("Sheet1");  
            
            int rs=st.getColumns();  
            int rows=st.getRows();  
            //System.out.println("����===>"+rs+"������"+rows);  //���   
              
            for(int k=1; k<rows; k++){//��   
            	
            	List list = new ArrayList();     
            	for(int i=1; i<rs; i++){//��   
                           
            		Cell c00 = st.getCell(i,k);  
		            //ͨ�õĻ�ȡcellֵ�ķ�ʽ,�����ַ���   
		            String strc00 = c00.getContents();  
		            //���cell��������ֵ�ķ�ʽ   
		            if(c00.getType() == CellType.LABEL)  
		            {  
		                LabelCell labelc00 = (LabelCell)c00;  
		                strc00 = labelc00.getString();  
		            }  
//		            //excel ����Ϊʱ�����ʹ���;   
//		            if(c00.getType()==CellType.DATE){  
//		                DateCell dc=(DateCell)c00;    
//		                strc00 = sdf.format(dc.getDate());  
//		                  
//		            }  
//		            //excel ����Ϊ��ֵ���ʹ���;   
//		            if(c00.getType()==CellType.NUMBER|| c00.getType()==CellType.NUMBER_FORMULA){ 
//		                NumberCell nc=(NumberCell)c00;  
//		                strc00=""+nc.getValue();  
//		            }
		              
		            list.add(strc00); 
		            //System.out.println(">��"+k+"��"+list.get(i-1));  //���   

			        //�У���   
					//data2=String.valueOf(st.getCell(1,k).getContents());   
					//data2=data2.replace("/", "-");   
					//java.util.Date dt=sdf.parse(data2);       
					//System.out.println(sdf.format(dt));                                  
            	}  
            	
            	//�����ͻ���Ϣ
    			BaseCustomer customer = new BaseCustomer();
    			customer.setShortname("");							// �ͻ����
    			customer.setCustomername(list.get(0).toString());	// �ͻ�ȫ��
    			customer.setCustomertype("");						// �ͻ�����
    			customer.setContact(list.get(1).toString());		// ��ϵ��
    			customer.setPhone(list.get(2).toString());			// ��ϵ�绰
    			customer.setFax("");								// ����
    			customer.setAddress(list.get(3).toString());		// ��ַ
    			customer.setBank("");					// ��������
    			customer.setCreatetime(currentTime);	// ����ʱ��
    			customer.setCreatemanid(strUserCode);	// ������
    			customer.setUpdatetime(currentTime);	// ����޸�ʱ��
    			customer.setUpdatemanid(strUserCode);	// ����޸���
    			customer.setUseflag("Y");				// �Ƿ�����
    			customer.setPutawayid("");				// �ϼܹ���ID
    			customer.setAllocationid("");			// �������ID
    			customer.setReplenishid("");			// ��������ID
    			customer.setLinenumber("");				// ��·��
    			customerBus.addCustomer(customer);
    			Logger.info("�û�:" + strUserName + "�����˿ͻ�:" + list.get(1).toString());
            }  
            //�ر�
            rwb.close();  
            
            strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
            //ҳ��ˢ��
            PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			((HttpSession)request.getSession(false)).setAttribute("paging", pt);
			
			String back_msg = "�ļ�����ɹ���";
			request.setAttribute("back_msg", back_msg);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
        }  
        catch(Exception er)  
        {  
        	Logger.error("������Ϣ==>�ͻ�����==>����ͻ���Ϣ��Excel�ļ�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
        }  
    }
    
	/** 
     * ������Ʒ��Ϣ��Excel�ļ�
	 * @param response 
	 * @param request 
     * @param filePath 
	 * @param dao 
	 * ������Ʒ��Ϣ��Excel�ļ�����Ϊ����Ʒ��ţ���Ʒ����������룬��װ���
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
            //System.out.println("����===>"+rs+"������"+rows);  //���   
              
            for(int k=1; k<rows; k++){//��   
            	
            	List list = new ArrayList();     
            	for(int i=1; i<rs; i++){//��   
                           
            		Cell c00 = st.getCell(i,k);  
		            //ͨ�õĻ�ȡcellֵ�ķ�ʽ,�����ַ���   
		            String strc00 = c00.getContents();  
		            //���cell��������ֵ�ķ�ʽ   
		            if(c00.getType() == CellType.LABEL)  
		            {  
		                LabelCell labelc00 = (LabelCell)c00;  
		                strc00 = labelc00.getString();  
		            }  
		              
		            list.add(strc00); 
		            //System.out.println(">��"+k+"��"+list.get(i-1));  //���                    
            	}  
            	
            	//������Ʒ��Ϣ	
    			BaseProduct product = new BaseProduct();
    			product.setProductname(list.get(0).toString());		// ��Ʒ��
    			product.setSpec(list.get(1).toString());			// ���
				product.setBarcode(list.get(2).toString());			// ��Ʒ����
				product.setPkspec(list.get(3).toString());			// ��װ���
    			productBus.addProduct(product);
    			Logger.info("�û�:" + strUserName + "��������Ʒ:" + list.get(0).toString());
            }  
            //�ر�   
            rwb.close();  
            
            
            //ҳ��ˢ��
            strUrl = "/standard/jsp/base/product/base_product_list.jsp";
	        PagingTool pt = productBus.getProductQuery("","","", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			((HttpSession)request.getSession(false)).setAttribute("paging", pt);
			
			String back_msg = "�ļ�����ɹ���";
			request.setAttribute("back_msg", back_msg);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
        }  
        catch(Exception er)  
        {  
        	Logger.error("������Ϣ==>��Ʒ����==>������Ʒ��Ϣ��Excel�ļ�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
        }  
		
	}

}
