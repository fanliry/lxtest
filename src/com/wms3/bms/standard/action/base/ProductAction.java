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
 * ����:��Ʒ����
 * @author gui
 *
 */
public class ProductAction extends AbstractAction
{
	protected IProductBus productBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String productname = CommonTools.getStrToGbk(request.getParameter("productname"));	//��Ʒ����
        String productcode= CommonTools.getStrToGbk(request.getParameter("productcode"));    //�ͻ�ID
        String producttype= CommonTools.getStrToGbk(request.getParameter("producttype"));    //��Ʒ���
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//��ƷId
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //��Ʒ���� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/product/base_product_list.jsp";
				
				PagingTool pt = productBus.getProductQuery(productname,productcode,producttype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//��Ʒ���� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/product/base_product_update.jsp";
				
				BaseProduct product = productBus.getProductById(id);
				request.setAttribute("product", product); 
			}else if(flag != null && flag.equals("3")) //ѡ����Ʒ ��ѯ�б�
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
			Logger.error("������Ϣ==>��Ʒ����==>��Ʒ��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:������Ʒ
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
		
		String productname = CommonTools.getStrToGbk(request.getParameter("productname"));	// ��Ʒ��׼��
	    String spec = CommonTools.getStrToGbk(request.getParameter("spec"));				// ���
	    String barcode = CommonTools.getStrToGbk(request.getParameter("barcode"));			// ��Ʒ��׼��
	    String pkspec = CommonTools.getStrToGbk(request.getParameter("pkspec"));			// ��Ʒ��׼��
	    String recunit = CommonTools.getStrToGbk(request.getParameter("recunit"));			// ������λ
	    String length = CommonTools.getStrToGbk(request.getParameter("length"));			// ��
	    String width = CommonTools.getStrToGbk(request.getParameter("width"));				// ��
	    String height = CommonTools.getStrToGbk(request.getParameter("height"));			// ��
	    String upperlimit = CommonTools.getStrToGbk(request.getParameter("upperlimit"));	// ��������
	    String lowerlimit = CommonTools.getStrToGbk(request.getParameter("lowerlimit"));	// ��������
	    String pttypeid = CommonTools.getStrToGbk(request.getParameter("pttypeid"));		// ��Ʒ���ID
	    String storageareaid = CommonTools.getStrToGbk(request.getParameter("storageareaid"));	// �洢����ID
	    String storagespaceid = CommonTools.getStrToGbk(request.getParameter("storagespaceid"));// �洢��λID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));	// �������ID
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));					// ��������IDID
	    String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));			// ��װID
	    String validityterm = CommonTools.getStrToGbk(request.getParameter("validityterm"));	// ��Ч��
	    String validitytype = CommonTools.getStrToGbk(request.getParameter("validitytype"));	// ��Ч������
	    String productCode = CommonTools.getStrToGbk(request.getParameter("productCode"));				// ��Ʒ����
	    String remark2 = CommonTools.getStrToGbk(request.getParameter("remark2"));				// ��ע
	    String remark3 = CommonTools.getStrToGbk(request.getParameter("remark3"));				// ��ע
	    String remark4 = CommonTools.getStrToGbk(request.getParameter("remark4"));				// ��ע
	    String useflag = CommonTools.getStrToGbk(request.getParameter("useflag"));				// ���ñ�־
		String strUserName = (String)httpsession.getAttribute("userName");
	    String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));				// ���ñ�־
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		String back_msg = "";
		try
		{
			List ls1 = productBus.getProductByProductCode(productCode);
			if(ls1!=null && ls1.size()==0){
				//������Ʒ��Ϣ
				BaseProduct product = new BaseProduct();
				product.setProductname(productname);					// ��Ʒ��
				product.setSpec(spec);									// ���
				product.setBarcode(barcode);							// ��Ʒ����
				product.setPkspec(pkspec);								// ��װ���
				product.setRecunit(recunit);							// ȱʡ�ջ���λ
				product.setLength(Double.parseDouble(length));			// ��
				product.setWidth(Double.parseDouble(width));			// ��
			    product.setHeight(Double.parseDouble(height));			// ��
			    product.setUpperlimit(Double.parseDouble(upperlimit));	// �������
			    product.setLowerlimit(Double.parseDouble(lowerlimit));
			    product.setPttypeid(pttypeid);							// ��Ʒ���ID
			    product.setStorageareaid(storageareaid);				// �洢����ID
			    product.setStoragespaceid(storagespaceid);				// �洢��λID
				product.setAllocationid(allocationid);					// �������ID
			    product.setPakageid(pakageid);		    				// ��װID 
			    product.setLotid(lotid);               					// ����ID
			    product.setValidityterm(Double.parseDouble(validityterm));// ��Ч��
			    product.setValiditytype(validitytype);					// ��Ч������
			    product.setProductCode(productCode);					// ��Ʒ����
			    product.setRemark2(remark2);							// ��ע
			    product.setRemark3(remark3);							// ��ע
			    product.setRemark4(remark4);							// ��ע
			    product.setIsproductmixed("N");      		             // �����Ʒ���
			    product.setUseflag(useflag);							// ���ñ�־
			    product.setProducttype(producttype);

				productBus.addProduct(product);
				
				Logger.info("�û�" + strUserName + "�������Ʒ" + productname);
			}else{
				back_msg = "����Ʒ����"+productCode+"�Ѿ����� ��Ӳ�Ʒʧ��";
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
			Logger.error("������Ϣ==>��Ʒ����==>������Ʒʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸���Ʒ
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));						// ��ƷId
		String productname = CommonTools.getStrToGbk(request.getParameter("productname"));		// ��Ʒ��
	    String spec = CommonTools.getStrToGbk(request.getParameter("spec"));					// ���
	    String barcode = CommonTools.getStrToGbk(request.getParameter("barcode"));				// ��Ʒ����
	    String pkspec = CommonTools.getStrToGbk(request.getParameter("pkspec"));				// ��װ���
	    String recunit = CommonTools.getStrToGbk(request.getParameter("recunit"));				// �ջ���λ
//	    String sendunit = CommonTools.getStrToGbk(request.getParameter("sendunit"));			// ������λ
	    String length = CommonTools.getStrToGbk(request.getParameter("length"));				// ��
	    String width = CommonTools.getStrToGbk(request.getParameter("width"));					// ��
	    String height = CommonTools.getStrToGbk(request.getParameter("height"));				// ��
	    String upperlimit = CommonTools.getStrToGbk(request.getParameter("upperlimit"));		// �������
	    String lowerlimit = CommonTools.getStrToGbk(request.getParameter("lowerlimit"));		// �������
//	    String weight = CommonTools.getStrToGbk(request.getParameter("weight"));				// ����
//	    String netweight = CommonTools.getStrToGbk(request.getParameter("netweight"));			// ����
//	    String tareweight = CommonTools.getStrToGbk(request.getParameter("tareweight"));		// Ƥ��
//	    String volume = CommonTools.getStrToGbk(request.getParameter("volume"));				// ���
	    String pttypeid = CommonTools.getStrToGbk(request.getParameter("pttypeid"));			// ��Ʒ���ID
	    String storageareaid = CommonTools.getStrToGbk(request.getParameter("storageareaid"));	// �洢����ID
	    String storagespaceid = CommonTools.getStrToGbk(request.getParameter("storagespaceid"));// �洢��λID
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));			// �ϼܹ���ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));	// �������ID
	    String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));		// ��������ID
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));					// ��������IDID
	    String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));			// ��װID
	    String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));		// �ͻ�ID
	    String producedate = CommonTools.getStrToGbk(request.getParameter("producedate"));		// ��������
	    String validityterm = CommonTools.getStrToGbk(request.getParameter("validityterm"));	// ��Ч��
	    String validitytype = CommonTools.getStrToGbk(request.getParameter("validitytype"));	// ��Ч������
	    String productCode = CommonTools.getStrToGbk(request.getParameter("productCode"));				// ��ע
	    String remark2 = CommonTools.getStrToGbk(request.getParameter("remark2"));				// ��ע
	    String remark3 = CommonTools.getStrToGbk(request.getParameter("remark3"));				// ��ע
	    String remark5 = CommonTools.getStrToGbk(request.getParameter("remark4"));				// ��ע
	    String remark4 = CommonTools.getStrToGbk(request.getParameter("remark5"));				// ��ע
	    String remark6 = CommonTools.getStrToGbk(request.getParameter("remark6"));				// ��ע
	    String remark7 = CommonTools.getStrToGbk(request.getParameter("remark7"));				// ��ע
	    String isexcess = CommonTools.getStrToGbk(request.getParameter("isexcess"));			// �������ջ�
	    String isproductmixed = CommonTools.getStrToGbk(request.getParameter("isproductmixed"));// �����Ʒ���
	    String isbatchmixed = CommonTools.getStrToGbk(request.getParameter("isbatchmixed"));	// �������λ��
	    String useflag = CommonTools.getStrToGbk(request.getParameter("useflag"));				// ���ñ�־
	    String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));				// ���ñ�־

		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		productBus = new ProductBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseProduct product = productBus.getProductById(id);
				product.setProductname(productname);					// ��Ʒ��
				product.setSpec(spec);									// ���
				product.setBarcode(barcode);							// ��Ʒ����
				product.setPkspec(pkspec);								// ��װ���
				product.setRecunit(recunit);							// �ջ���λ
//				product.setSendunit(sendunit);							// ������λ
				product.setLength(Double.parseDouble(length));			// ��
				product.setWidth(Double.parseDouble(width));			// ��
				product.setHeight(Double.parseDouble(height));			// ��
				product.setUpperlimit(Double.parseDouble(upperlimit));	// �������
				product.setLowerlimit(Double.parseDouble(lowerlimit));	// �������
//				product.setWeight(Double.parseDouble(weight));			// ����
//				product.setNetweight(Double.parseDouble(netweight));	// ����
//				product.setTareweight(Double.parseDouble(tareweight));	// Ƥ��
//				product.setVolume(Double.parseDouble(volume));			// ���
				product.setPttypeid(pttypeid);							// ��Ʒ���ID
				product.setStorageareaid(storageareaid);				// �洢����ID
			    product.setStoragespaceid(storagespaceid);				// �洢��λID
				product.setPutawayid(putawayid);						// �ϼܹ���ID
				product.setAllocationid(allocationid);					// �������ID
				product.setReplenishid(replenishid);					// ��������ID
				product.setPakageid(pakageid);							// ��װID
				product.setLotid(lotid);               					// ����ID
				product.setCustomerid(customerid);						// �ͻ�ID
				product.setProducedate(producedate);					// ��������
				product.setValidityterm(Double.parseDouble(validityterm));// ��Ч��
				product.setValiditytype(validitytype);					// ��Ч������
				product.setProductCode(productCode);							// ��Ʒ����
				product.setRemark2(remark2);							// ��ע
				product.setRemark3(remark3);							// ��ע
				product.setRemark5(remark4);							// ��ע
				product.setRemark4(remark5);							// ��ע
				product.setRemark6(remark6);							// ��ע
				product.setRemark7(remark7);							// ��ע
				product.setIsexcess(isexcess);            				// �Ƿ����ջ�
			    product.setIsproductmixed(isproductmixed);      		// �����Ʒ���
			    product.setIsbatchmixed(isbatchmixed);        			// �������λ��
				product.setUseflag(useflag);							// ���ñ�־
				product.setProducttype(producttype);
				
				productBus.updateProduct(product);
				Logger.info("�û�" + strUserName + "�޸�����Ʒ" + id);
			}
			
			strUrl = "/standard/jsp/base/product/base_product_list.jsp";
			PagingTool pt = productBus.getProductQuery("","","", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��Ʒ����==>�޸���Ʒʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ����Ʒ
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
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
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
					//ɾ��
					if(id[i]!=null && id[i].length()>0){
						IInventoryBus inventoryBus = new InventoryBusImpl(dao);
						String  sql = inventoryBus.getInventoryHQL("", "", "", "", "", "", id[i], "", "", "", "");
						List ls = dao.searchEntities(sql);
						if(ls!=null && ls.size()==0){
							productBus.deleteProduct(id[i]);	
							Logger.info("�û�" + strUserName + "ɾ������Ʒ" + id[i]);
						}else{
							z++;
						}
						
					}
				}
				if(z>0){
					back_msg = "��"+z+"����Ʒɾ��ʧ��";
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
			Logger.error("������Ϣ==>��Ʒ����==>��Ʒɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}