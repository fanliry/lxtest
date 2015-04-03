package com.wms3.bms.standard.action.base.ajaxTree;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���ܣ���������
 * @author 
 */
public class TreeNodes extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=GBK";
//  private BlueTreeTag BlueTreeTag=new BlueTreeTag();

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

	    PrintWriter out = response.getWriter();
	    String node_id=request.getParameter("node");
	    String level=request.getParameter("level");
	    
	    if(node_id==null || "".equals(node_id)){
	    	throw new ServletException("��ȡ�ڵ�idʧ��");
	    }
	    if(level==null || "".equals(level)){
	    	throw new ServletException("��ȡ�ڵ���ʧ��");
	    }
	    
	    try {
	    	out.println(this.createNodeTree(node_id, Integer.parseInt(level)+1));
	    }catch (Exception ex) {
	    	ex.printStackTrace();
	    	throw new ServletException("����ڵ���ʧ��"+ex.getMessage());
	    }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}

	public String createNodeTree(String node_id,int level)throws Exception {
	    StringBuffer result=new StringBuffer();
	
	    BlueTreeFactory treeFactory = new BlueTreeFactory();
	    BlueTreeInterFace tree = (BlueTreeInterFace) treeFactory.create(BlueTreeTag.blueClass);
	    
	    List elements = null;
	    elements = tree.getTreeElements(node_id);
	    
	    //��ʼ������
	    result.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"");
	    result.append(BlueTreeTag.styleClass);
	    result.append("\">");
	
	    //�����ڵ�
	    for (int i = 0; i < elements.size(); i++) {
	    	BlueTreeData treeData = new BlueTreeData();
	    	treeData = (BlueTreeData) elements.get(i);
	    	//���ϳ�����ͷ
	    	//===================================================
	    	result.append("<tr onMouseOver=\"this.className='");
	    	result.append(BlueTreeTag.onMouseOver);
	    	result.append("'\" onMouseOut=\"this.className='");
	    	result.append(BlueTreeTag.onMouseOut);
	    	result.append("'\">");
	    	result.append("<td>");
	
	    	//�ӽڵ�չ���ر�ͼƬ
	    	//===================================================
	    	for(int j=1;j<level;j++){
		        result.append("<img align=\"absmiddle\" src=\"");
		        result.append(BlueTreeTag.imageURL);
		        result.append(BlueTreeTag.lineImg);
		        result.append("\" class=\"");
		        result.append(BlueTreeTag.imgClass);
		        result.append("\">");
	    	}
	    	//===================================================
	
	    	result.append("<a class=\"");
	    	result.append(BlueTreeTag.linkClass);
	    	result.append("\" href=\"");
	
	    	if((treeData.isIschild() && BlueTreeTag.isFolderURL) || !treeData.isIschild()){
	    		if(BlueTreeTag.isUseDataURL){
	    			result.append(treeData.getUrl());
	    			//��isUseDataURL="true"ʱblueURL��ʾ�ֶ������Ӳ���
	    			if(BlueTreeTag.blueURL.length()>0)
	    				result.append("&StuffID="+BlueTreeTag.blueURL);
	    		}else{
	    			result.append(BlueTreeTag.blueURL);
	    		}
	    		//
	       
	    	}else{
	    		result.append("javascript:void(null);");
	    	}
	    	result.append("\" target=\"");
	    	result.append(BlueTreeTag.target);
	    	//������ӵ���¼�
	    	//===================================================
	    	if(treeData.isIschild()){
		        result.append("\" onClick=\"clickTreeNode('");
		        result.append(treeData.getId());
		        result.append("','");
		        result.append(level);
		        result.append("','");
		        result.append(BlueTreeTag.styleClass);
		        result.append("','");
		        result.append(BlueTreeTag.imageURL);
		        result.append("','");
		        result.append(BlueTreeTag.imgClass);
		        result.append("','");
		        result.append(BlueTreeTag.lineImg);
		        result.append("','");
		        result.append(BlueTreeTag.endLineImg);
		        result.append("','");
		        if(BlueTreeTag.useDataImg.equalsIgnoreCase("all")){
		        	result.append(treeData.getOpenimg());
		        }else{
		        	result.append(BlueTreeTag.fileImg);
		        }
		        result.append("','");
		        if ( (i + 1) != elements.size()) {
		        	result.append(BlueTreeTag.openImg);
		        	result.append("','");
		        	result.append(BlueTreeTag.closeImg);
		        }else{
		        	result.append(BlueTreeTag.endFolderOpenImg);
		        	result.append("','");
		        	result.append(BlueTreeTag.endFolderCloseImg);
		        }
		        result.append("','");
		        if(BlueTreeTag.useDataImg.equalsIgnoreCase("all")){
		        	result.append(treeData.getOpenimg());
		        	result.append("','");
		        	result.append(treeData.getCloseimg());
		        }else{
		        	result.append(BlueTreeTag.folderOpenImg);
		        	result.append("','");
		        	result.append(BlueTreeTag.folderImg);
		        }
		        result.append("');");
	    	}
	    	//���û���ӽڵ㣬�����һ��onclick�¼�
	    	//===================================================
	    	else{
	    		result.append("\" onClick=\"setIdAndLevel('");
		        result.append(treeData.getId());
		        result.append("','");
		        result.append(treeData.getName());
		        result.append("','");
		        result.append(level);
		        result.append("');");
	    	}
	    	//===================================================
	
	    	result.append("\">");
	
	    	//===================================================
	
	    	//���չ���ر�ͼƬ
	    	result.append("<img align=\"absmiddle\" class=\"");
	    	result.append(BlueTreeTag.imgClass);
	    	result.append("\" id=\"sign_img_");
	    	result.append(level);
	    	result.append("_");
	    	result.append(treeData.getId());
	    	result.append("\" src=\"");
	
	    	result.append(BlueTreeTag.imageURL);
	    	if((i+1)!=elements.size()){
	    		if (treeData.isIschild()) {
	    			result.append(BlueTreeTag.closeImg);
	    		}
	    		else {
	    			result.append(BlueTreeTag.elementLineImg);
	    		}
	    	}else{
	    		if (treeData.isIschild()) {
	    			result.append(BlueTreeTag.endFolderCloseImg);
	    		}
	    		else {
	    			result.append(BlueTreeTag.endLineImg);
	    		}
	    	}
	    	result.append("\">");
	
	    	//�ӽڵ�ͼƬ
	    	result.append("<img align=\"absmiddle\" class=\"");
	    	result.append(BlueTreeTag.imgClass);
	    	result.append("\" id=\"node_img_");
	    	result.append(level);
	    	result.append("_");
	    	result.append(treeData.getId());
	    	result.append("\" src=\"");
	
	    	result.append(BlueTreeTag.imageURL);
	    	if(BlueTreeTag.useDataImg.equalsIgnoreCase("all")){
	    		result.append(treeData.getOpenimg());
	    	}else{
	    		if (treeData.isIschild()) {
	    			result.append(BlueTreeTag.folderImg);
	    		}
	    		else {
	    			result.append(BlueTreeTag.fileImg);
	    		}
	    	}
	    	result.append("\">&nbsp;");
	    	//�ӽڵ���ʾ����
	    	result.append(treeData.getName());
	    	//�ӳ�����β
	    	//===================================================
	    	result.append("</a>");
	    	//===================================================
	
	    	result.append("</td>");
	
	    	result.append("</tr>");
	    	//����һ���ڵ�ռ�
	    	if(treeData.isIschild()){
	    		result.append("<tr>");
	    		result.append("<td style=\"display:none;\" id=\"tree_");
	    		result.append(level);
	    		result.append("_");
	    		result.append(treeData.getId());
	    		result.append("\">&nbsp;");
	    		result.append("</td>");
	    		result.append("</tr>");
	    	}
	    }
	    
	    result.append("</table>");
	    
	    return result.toString();
	}
}
