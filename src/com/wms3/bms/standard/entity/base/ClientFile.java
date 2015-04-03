package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

//客户资料管理
public class ClientFile {
	
	private String m_clientFileId;
	private String m_clientNumber;
	private String m_userNumber; /*用友编号*/
	private String m_clientName;/*客户简称*/
	private String m_criterionSort;/*标准分类*/
	private String m_fullName; /*全称*/
	private String m_linkman;/*联系人*/
	private String m_phone;
	private String m_fax;
	private String m_address;
	private String m_accountBrank;
	private String m_accountNumber;/*银行账号*/
	private String m_ratePayingNumber;  //纳税号
	private String m_reveal; //是否启用
	private String m_describe;
	private String m_strOutId;			/*外部编码 双汇代码*/
		
	public ClientFile(){}

	
	public ClientFile(String clientNumber ,String clientFileId,String clientName,String userNumber ,String criterionSort,String linkman,String phone,String fax,String address,String accountBrank,String ratePayingNumber,String reveal,String describe )
	{
		this.m_clientFileId=clientFileId;
		this.m_clientNumber=clientNumber;
		this.m_clientName=clientName;
		this.m_userNumber =userNumber ;
		this.m_criterionSort=criterionSort;
		this.m_linkman=linkman;
		this.m_accountBrank=accountBrank;
		this.m_describe=describe;
		this.m_reveal=reveal;
		this.m_ratePayingNumber=ratePayingNumber;
		this.m_address=address;
		this.m_fax=fax;
		this.m_phone=phone;
		
	}

	public String getM_strOutId() {
		return m_strOutId;
	}

	public void setM_strOutId(String outId) {
		m_strOutId = outId;
	}

	public String getM_accountBrank() {
		return m_accountBrank;
	}

	public void setM_accountBrank(String brank) {
		m_accountBrank = brank;
	}


	public String getM_address() {
		return m_address;
	}


	public void setM_address(String m_address) {
		this.m_address = m_address;
	}


	public String getM_clientFileId() {
		return m_clientFileId;
	}


	public void setM_clientFileId(String fileId) {
		m_clientFileId = fileId;
	}


	public String getM_clientName() {
		return m_clientName;
	}


	public void setM_clientName(String name) {
		m_clientName = name;
	}


	public String getM_clientNumber() {
		return m_clientNumber;
	}


	public void setM_clientNumber(String number) {
		m_clientNumber = number;
	}


	public String getM_criterionSort() {
		return m_criterionSort;
	}


	public void setM_criterionSort(String sort) {
		m_criterionSort = sort;
	}


	public String getM_describe() {
		return m_describe;
	}


	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}


	public String getM_fax() {
		return m_fax;
	}


	public void setM_fax(String m_fax) {
		this.m_fax = m_fax;
	}


	public String getM_linkman() {
		return m_linkman;
	}


	public void setM_linkman(String m_linkman) {
		this.m_linkman = m_linkman;
	}


	public String getM_phone() {
		return m_phone;
	}


	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}


	public String getM_ratePayingNumber() {
		return m_ratePayingNumber;
	}


	public void setM_ratePayingNumber(String payingNumber) {
		m_ratePayingNumber = payingNumber;
	}


	public String getM_reveal() {
		return m_reveal;
	}


	public void setM_reveal(String m_reveal) {
		this.m_reveal = m_reveal;
	}


	public String getM_userNumber() {
		return m_userNumber;
	}


	public void setM_userNumber(String number) {
		m_userNumber = number;
	}


	public String getM_accountNumber() {
		return m_accountNumber;
	}


	public void setM_accountNumber(String number) {
		m_accountNumber = number;
	}


	public String getM_fullName() {
		return m_fullName;
	}


	public void setM_fullName(String name) {
		m_fullName = name;
	}
	
	/**
	 * 功能：根据Id获取模块对象
	 * @param strId
	 * @param dao
	 * @author xiaotao
	 * @throws Exception
	 */
	public ClientFile getClientInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			
			String strSql = "From ClientFile as m where m.m_clientFileId='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (ClientFile)ls.get(0);
			}
		}
		
		return null;
	}
	
	/**
	 * 功能:增加
	 * @param dao
	 * @author xiaotao
	 * @throws Exception
	 */
	public void addClientInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * 功能:更新
	 * @author xiaotao
	 * @param dao
	 * @throws Exception
	 */
	public void updateClientInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}
	
	/**
	 * 功能:获得查询的SQL语句
	 * @param clientNumber
	 * @param userNumber
	 * @param clientName
	 * @param criterionSort
	 * @author xiaotao
	 * @return
	 */
	public static String getClientSQL(String clientNumber, String userNumber ,String clientName ,String criterionSort) 
	{
		  String strSql="From ClientFile as m where 1=1";
		  
		  
		  if(clientNumber.equals("'" ) || userNumber.equals("'" )|| clientName.equals("'" )|| criterionSort.equals("'" ))
		  {
			  clientNumber="";
			  userNumber="";
			  clientName="";
			  criterionSort="";
			
		  }
		  if (clientNumber!=null && clientNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_clientNumber like '%"+clientNumber+"%'";	
		  }
		  if (userNumber!=null && userNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_userNumber like '%"+userNumber+"%'";	
		  }
		  if (clientName!=null && clientName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_clientName  like '%"+clientName+"%'";	
		  }
		  if (criterionSort!=null && criterionSort.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_criterionSort like '%"+criterionSort+"%'";	
		  }
		  
		  
		   strSql=strSql+" order by m.m_clientNumber";
		  return strSql;
	}
	/**
	 * 功能:根据条件查询客户档案列表
	 * @param dao
	 * @param clientNumber
	 * @param userNumber
	 * @param clientName
	 * @param criterionSort
	 * @author xiaotao
	 * @return
	 */
	public static List getClientFileList(EntityDAO dao, String clientNumber, String userNumber ,String clientName ,String criterionSort) 
	{
		  List ls = null;
		  String strSql="From ClientFile as m where 1=1";
		  
		  if(clientNumber.equals("'" ) || userNumber.equals("'" )|| clientName.equals("'" )|| criterionSort.equals("'" ))
		  {
			  clientNumber="";
			  userNumber="";
			  clientName="";
			  criterionSort="";
			
		  }
		  if (clientNumber!=null && clientNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_clientNumber like '%"+clientNumber+"%'";	
		  }
		  if (userNumber!=null && userNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_userNumber like '%"+userNumber+"%'";	
		  }
		  if (clientName!=null && clientName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_clientName  like '%"+clientName+"%'";	
		  }
		  if (criterionSort!=null && criterionSort.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_criterionSort like '%"+criterionSort+"%'";	
		  }
		  
		  
		  strSql=strSql+" order by m.m_clientNumber";
		  ls = dao.searchEntities(strSql);
		  return ls;
	}
	/**
	 *  功能:获得总数的SQL语句
	 * @param clientNumber
	 * @param userNumber
	 * @param clientName
	 * @param criterionSort
	 * @author xiaotao
	 * @return
	 */
	public static String getClientCountSQL(String clientNumber, String userNumber ,String clientName ,String criterionSort)
	{
		  String strSql="select count(m) From ClientFile as m where 1=1";
		  
		  if(clientNumber.equals("'" ) || userNumber.equals("'" )|| clientName.equals("'" )|| criterionSort.equals("'" ))
		  {
			  clientNumber="";
			  userNumber="";
			  clientName="";
			  criterionSort="";
			
		  }
		  if (clientNumber!=null && clientNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_clientNumber like '%"+clientNumber+"%'";	
		  }
		  if (userNumber!=null && userNumber.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_userNumber = '"+userNumber+"'";	
		  }
		  if (clientName!=null && clientName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_clientName  like '%"+clientName+"%'";	
		  }
		  if (criterionSort!=null && criterionSort.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_criterionSort like '%"+criterionSort+"%'";	
		  }
		  if(clientNumber=="'" || userNumber=="'" ||clientName=="'" || criterionSort=="'")
		  {
			  return strSql;
		  }
		  return strSql;
	}
	/**
	 * 功能:根据ID获取客户名称
	 * @author zhi
	 * @return
	 */
	public static String getCustomerName(EntityDAO dao, String customerId) 
	{
		String sql = "select m_clientName from ClientFile where clientFileId="+"'"+customerId+"'";
		String customerName = "";
		List ls = null;
		
		try
		{
			if(customerId!= null)
			{
				ls = dao.searchEntities(sql);
			}
			if(ls!=null && ls.size()>0)
			{
				customerName = (String)ls.get(0);
			}
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
		return customerName;	
	}
	/**
	 * 说明：根据条件查询客户档案的SQL  
	 * @param customerName
	 * @param fullName
	 * @param linkMan
	 * @param phone
	 * @param address
	 * @author yangxi
	 * @return
	 */
	public static String getCustomerInfoSQL(String customerName, String fullName, String linkMan, String phone, String address)
	{
		String strSql = "From ClientFile where 1=1";
		
		
		if(customerName.equals("'" ) || fullName.equals("'" )|| linkMan.equals("'" )|| phone.equals("'" )|| address.equals("'" ))
		  {
			customerName="";
			fullName="";
			linkMan="";
			phone="";
			address="";
			
		  }
		if (customerName!=null && customerName.trim().length()>0)
		{
			strSql=strSql+" and m_clientName like '%"+customerName+"%'";	
		}
		if (fullName!=null && fullName.trim().length()>0)
		{
			strSql=strSql+" and m_fullName like '%"+fullName+"%'";	
		}
		if (linkMan!=null && linkMan.trim().length()>0)
		{
			strSql=strSql+" and m_linkman like '%"+linkMan+"%'";	
		}
		if (phone!=null && phone.trim().length()>0)
		{
			strSql=strSql+" and m_phone like '%"+phone+"%'";	
		}
		if (address!=null && address.trim().length()>0)
		{
			strSql=strSql+" and m_address like '%"+address+"%'";	
		}
		
		
		strSql = strSql + " order by m_clientNumber";
		
		return strSql;
	}
	/**
	 * 根据条件查询客户档案总数的SQL  yangxi
	 * @param customerName
	 * @param fullName
	 * @param linkMan
	 * @param phone
	 * @param address
	 * @author xiaotao
	 * @return
	 */
	public static String getCustomerCountSQL(String customerName, String fullName, String linkMan, String phone, String address)
	{
		String strSql = "select count(*) From ClientFile where 1=1";
		
		
		if(customerName.equals("'" ) || fullName.equals("'" )|| linkMan.equals("'" )|| phone.equals("'" )|| address.equals("'" ))
		  {
			customerName="";
			fullName="";
			linkMan="";
			phone="";
			address="";
			
		  }
		if (customerName!=null && customerName.trim().length()>0)
		{
			strSql=strSql+" and m_clientName like '%"+customerName+"%'";	
		}
		if (fullName!=null && fullName.trim().length()>0)
		{
			strSql=strSql+" and m_fullName like '%"+fullName+"%'";	
		}
		if (linkMan!=null && linkMan.trim().length()>0)
		{
			strSql=strSql+" and m_linkman like '%"+linkMan+"%'";	
		}
		if (phone!=null && phone.trim().length()>0)
		{
			strSql=strSql+" and m_phone like '%"+phone+"%'";	
		}
		if (address!=null && address.trim().length()>0)
		{
			strSql=strSql+" and m_address like '%"+address+"%'";	
		}
		
		
		return strSql;
	}
	/**
	 * 功能：根据客户ID获取客户信息
	 * @param dao
	 * @param id
	 * @return
	 */
	public ClientFile getInfoById(EntityDAO dao, String id){
		ClientFile ta = null;
		String hql = "from ClientFile where m_clientFileId='" + id + "'";
		List ls = dao.searchEntities(hql);
		if(ls.size() == 1){
			ta = (ClientFile)ls.get(0);
		}
		return ta;
	}
	/**
	 * 功能：根据外部编码获取客户信息
	 * @param dao
	 * @param o_id
	 * @return
	 */
	public ClientFile getInfoByOId(EntityDAO dao, String o_id){
		ClientFile ta = null;
		String hql = "from ClientFile where m_strOutId='" + o_id + "'";
		List ls = dao.searchEntities(hql);
		if(ls.size() > 0){
			ta = (ClientFile)ls.get(0);
		}
		return ta;
	}
	/**
	 * 功能：根据客户简称客户信息
	 * @param dao
	 * @param o_id
	 * @return
	 */
	public ClientFile getInfoByShortName(EntityDAO dao, String short_name){
		ClientFile ta = null;
		String hql = "from ClientFile where m_clientName='" + short_name + "'";
		List ls = dao.searchEntities(hql);
		if(ls.size() > 0){
			ta = (ClientFile)ls.get(0);
		}
		return ta;
	}
	/**
	 * 功能：获取查询的HQL语句
	 * @param link_man 
	 * @param short_name 
	 * @return
	 */
	public String getQueryHQL(String short_name, String link_man, String address, String out_id)
	{
		String hql = "from ClientFile where 1=1";
		if(short_name != null && short_name.trim().length() > 0){
			hql += " and m_clientName like '%" + short_name + "%'";
		}
		if(link_man != null && link_man.trim().length() > 0){
			hql += " and m_linkman like '%" + link_man + "%'";
		}
		if(address != null && address.trim().length() > 0){
			hql += " and m_address like '%" + address + "%'";
		}
		if(out_id != null && out_id.trim().length() > 0){
			hql += " and m_strOutId='" + out_id + "'";
		}
		return hql;
	}
	/**
	 * 功能：获取总数的HQL语句
	 * @param link_man 
	 * @param short_name 
	 * @return
	 */
	public String getCountHQL(String short_name, String link_man, String address, String out_id){
		String hql = "select count(*) from ClientFile where 1=1";
		if(short_name != null && short_name.trim().length() > 0){
			hql += " and m_clientName like '%" + short_name + "%'";
		}
		if(link_man != null && link_man.trim().length() > 0){
			hql += " and m_linkman like '%" + link_man + "%'";
		}
		if(address != null && address.trim().length() > 0){
			hql += " and m_address like '%" + address + "%'";
		}
		if(out_id != null && out_id.trim().length() > 0){
			hql += " and m_strOutId='" + out_id + "'";
		}
		return hql;
	}
}

