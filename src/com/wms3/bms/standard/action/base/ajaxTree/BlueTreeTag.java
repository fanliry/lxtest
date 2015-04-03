package com.wms3.bms.standard.action.base.ajaxTree;


import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.wms3.bms.standard.service.BMSService;


/**
 * ���ܣ�����ǩ������
 * @author 
 */
public class BlueTreeTag  implements Tag {
	
  private PageContext pageContext;
  private Tag parent;
  
  public static String rootId; //���ڵ�ID
  public static String blueClass; //���ݽڵ�ID������иýڵ��ӽڵ��ʵ����
  public static String blueURL; //����ڵ㳬����---isUseDataURLΪfalseʱ����;��isUseDataURL="true"ʱblueURL��ʾ�ֶ������Ӳ���
  public static boolean isUseDataURL; //�Ƿ�ʹ�ýڵ��װ����Ľڵ㳬����
  public static String rootName; //���ڵ�����
  public static String imageURL; //����ͼƬĿ¼·��
  public static String rootImage; //���ڵ�ͼƬ
  public static String folderImg; //���ӽڵ�ر�ͼƬ
  public static String folderOpenImg; //���ӽڵ�չ��ͼƬ
  public static String fileImg; //���ӽڵ�ͼƬ
  public static String openImg; //�ڵ�չ��ͼƬ
  public static String closeImg; //�ڵ�ر�ͼƬ
  public static String elementLineImg; //���нڵ����ͼƬ
  public static String lineImg; //�޽ڵ����ͼƬ
  public static String endLineImg; //���һ���ڵ���ͼƬ
  public static String endFolderOpenImg; //���һ�����ӽڵ�չ��ͼƬ
  public static String endFolderCloseImg; //���һ�����ӽڵ�ر�ͼƬ
  public static boolean isFolderURL; //�Ƿ�����ӽڵ�Ľڵ�ӳ�����---isUseDataURLΪfalseʱ����
  public static String rootURL; //���ڵ�����
  public static String target; //�������ӵ�target����
  public static String linkClass; //��������ʽ
  public static String styleClass; //����ʽ
  public static String imgClass; //ͼƬ��ʽ
  public static String useDataImg; //�ڵ�ͼƬ�Ƿ�ʹ��������ͼƬ��noΪ��ʹ�ã�firstΪ����һ��Ŀ¼ʹ�ã�allȫ��ʹ��
  public static String js; //����JS�ļ�λ��
  public static String onMouseOver; //��꾭��ÿ���ڵ�ʱ����ʽ
  public static String onMouseOut; //����뿪ÿ���ڵ�ʱ����ʽ

  public BlueTreeTag() {
    super();
    rootId = "0";
    blueClass = null; //��������
    blueURL = "#";
    isUseDataURL = false;
    rootName = "��λĿ¼��";
    rootImage = "rootImage.gif";
    imageURL = null; //��������
    folderImg = "folder.gif";
    folderOpenImg = "folderOpen.gif";
    fileImg = "file.gif";
    openImg = "open.gif";
    closeImg = "close.gif";
    elementLineImg = "elementLine.gif";
    lineImg = "line.gif";
    endLineImg = "endLine.gif";
    endFolderOpenImg = "endFolderOpen.gif";
    endFolderCloseImg = "endFolderClose.gif";
    isFolderURL = true;
    rootURL = "#";
    target = "_blank";
    linkClass="";
    imgClass="";
    styleClass="";
    useDataImg="no";
    js=null; //��������
    onMouseOver="onMouseOver";
    onMouseOut="onMouseOut";
  }

  /**
   * ���ñ�ǩ��ҳ��������
   * @param pageContext
   */
  public void setPageContext(PageContext pageContext) {
    this.pageContext = pageContext;
    
  }

  /**
   * ������һ����ǩ
   * @param parent
   */
  public void setParent(Tag parent) {
    this.parent = parent;
  }

  public Tag getParent() {
    return this.parent;
  }

  /**
   * ��ʼ��ǩʱ�Ĳ���
   * @return
   * @throws JspException
   */
  public int doStartTag() throws JspException {
	  

 

    if(blueClass==null){
      throw new JspException("����ǩblueClass����δ���ã�");
    }
    if(imageURL==null){
      throw new JspException("����ǩimageURL����δ���ã�");
    }
    if(js==null){
      throw new JspException("����ǩjs����δ���ã�");
    }
    imageURL =BMSService.getm_strServerPath() + imageURL;
    js=BMSService.getm_strServerPath()+js;
    return SKIP_BODY; //����SKIP_BODY����ʾ�������ǩ��
  }

  /**
   * ������ǩʱ�Ĳ���
   * @return
   * @throws JspException
   */
  public int doEndTag() throws JspException {
    JspWriter out = this.pageContext.getOut();

    try {
      String tree = this.createTree();

      out.print(tree);
    }
    catch (Exception ex) {
      throw new JspException("����������:" + ex.getMessage());
    }

    return EVAL_PAGE;
  }

  /**
   * Release �����ͷű�ǩ����ռ�õ���Դ������ʹ�������ݿ����ӣ���ôӦ�ùر��������
   */
  public void release() {
  }

/**
   * ������
   * @return ������HTML�����ַ���
   */
  public String createTree() throws Exception {
	  StringBuffer result = new StringBuffer();
	  
    BlueTreeFactory treeFactory = new BlueTreeFactory();
    BlueTreeInterFace tree = (BlueTreeInterFace) treeFactory.create(blueClass);

    List elements = null;
    elements = tree.getTreeElements(rootId);
     
    //��ʽ
    result.append("<style>");
    result.append(".onClick{");
    result.append("background-color:#FFFFFF;");
    result.append("}");
    result.append(".onMouseOver{");
    result.append("background-color:#EDF2F9;");
    result.append("}");
    result.append(".onMouseOut{");
    result.append("}");
    result.append("</style>");

    //js
    result.append("<script src=\"");
    result.append(js);
    result.append("\">");
    result.append("</script>");

    //��ʼ������
    result.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"");
    result.append(styleClass);
    result.append("\">");

    //�������ڵ�
    result.append("<tr onMouseOver=\"this.className='"); result.append(onMouseOver);
    result.append("'\" onMouseOut=\"this.className='");
    result.append(onMouseOut);
    result.append("'\">");
    result.append("<td>");
    //����ڵ��ݲ���Ҫ���ӣ�����ע�͵�
    //================================
//  result.append("<a class=\"");
//  result.append(this.linkClass);
//  result.append("\" href=\"");
//  result.append(this.rootURL);
//  result.append("\" target=\"");
//  result.append(this.target);
//  result.append("\">");
    result.append("<img align=\"absmiddle\" class=\"");
    result.append(imgClass);
    result.append("\" src=\"");
    result.append(imageURL);
    result.append(rootImage);
    result.append("\">");
    result.append(rootName);
//  result.append("</a>");
    result.append("</td>");
    result.append("</tr>");

    //������һ���ڵ�
    for (int i = 0; i < elements.size(); i++) {
      BlueTreeData treeData = new BlueTreeData();
      treeData = (BlueTreeData) elements.get(i);
      //�ӳ�����ͷ
      //===================================================
      result.append("<tr onMouseOver=\"this.className='");
      result.append(onMouseOver);
      result.append("'\" onMouseOut=\"this.className='");
      result.append(onMouseOut);
      result.append("'\">");
      result.append("<td>");
      result.append("<a class=\"");
      result.append(linkClass);
      result.append("\" href=\"");
      //System.out.println(this.blueURL);
      if((treeData.isIschild() && this.isIsFolderURL()) || !treeData.isIschild()){
        if(this.isIsUseDataURL()){
          result.append(treeData.getUrl());
          //��isUseDataURL="true"ʱblueURL��ʾ�ֶ������Ӳ���
          if(blueURL.length()>0)
        	  result.append("&StuffID="+blueURL);
        }else{
          result.append(blueURL);
        }      
      }else{
        result.append("javascript:void(null);");
      }

      result.append("\" target=\"");
      result.append(target);
      //��ӽڵ㵥���¼�
      //===================================================
      if(treeData.isIschild()){
        result.append("\" onClick=\"clickTreeNode('");
        result.append(treeData.getId());
        result.append("',1,'");
        result.append(styleClass);
        result.append("','");
        result.append(imageURL);
        result.append("','");
        result.append(imgClass);
        result.append("','");
        result.append(lineImg);
        result.append("','");
        result.append(endLineImg);
        result.append("','");
        if(useDataImg.equalsIgnoreCase("first") || useDataImg.equalsIgnoreCase("all")){
          result.append(treeData.getOpenimg());
        }else{
          result.append(fileImg);
        }
        result.append("','");
        if ( (i + 1) != elements.size()) {
          result.append(openImg);
          result.append("','");
          result.append(closeImg);
        }else{
          result.append(endFolderOpenImg);
          result.append("','");
          result.append(endFolderCloseImg);
        }
        result.append("','");
        if(useDataImg.equalsIgnoreCase("first") || useDataImg.equalsIgnoreCase("all")){
          result.append(treeData.getOpenimg());
          result.append("','");
          result.append(treeData.getCloseimg());
        }else{
          result.append(folderOpenImg);
          result.append("','");
          result.append(folderImg);
        }
        result.append("');");
      }
      //===================================================
      result.append("\">");

      //===================================================

      //�ӽڵ�չ���ر�ͼƬ
      result.append("<img align=\"absmiddle\" class=\"");
      result.append(imgClass);
      result.append("\" id=\"sign_img_1_");
      result.append(treeData.getId());
      result.append("\" src=\"");

      result.append(imageURL);
      if((i+1)!=elements.size()){
        if (treeData.isIschild()) {
          result.append(closeImg);
        }
        else {
          result.append(elementLineImg);
        }
      }else{
        if (treeData.isIschild()) {
          result.append(endFolderCloseImg);
        }
        else {
          result.append(endLineImg);
        }
      }
      result.append("\">");

      //�ӽڵ�ͼƬ
      result.append("<img align=\"absmiddle\" class=\"");
      result.append(imgClass);
      result.append("\" id=\"node_img_1_");
      result.append(treeData.getId());
      result.append("\" src=\"");

      result.append(imageURL);
      if(useDataImg.equalsIgnoreCase("first") || useDataImg.equalsIgnoreCase("all")){
        result.append(treeData.getOpenimg());
      }else{
        if (treeData.isIschild()) {
          result.append(folderImg);
        }
        else {
          result.append(fileImg);
        }
      }
      result.append("\">&nbsp;");
      //�ӽڵ�����
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
        result.append("<td style=\"display:none;\" id=\"tree_1_");
        result.append(treeData.getId());
        result.append("\">&nbsp;");
        result.append("</td>");
        result.append("</tr>");
      }
    }

    result.append("</table>");
    //System.out.println(result.toString());
    return result.toString();
  }

  public String getRootId() {
    return rootId;
  }

  public void setRootId(String rootId1) {
    rootId = rootId1;
  }

  public String getBlueClass() {
    return blueClass;
  }

  public void setBlueClass(String blueClass1) {
    blueClass = blueClass1;
  }

  public String getBlueURL() {
    return blueURL;
  }

  public void setBlueURL(String blueURL1) {
    blueURL = blueURL1;
  }

  public boolean isIsUseDataURL() {
    return isUseDataURL;
  }

  public void setIsUseDataURL(boolean isUseDataURL1) {
    isUseDataURL = isUseDataURL1;
  }

  public String getRootName() {
    return rootName;
  }

  public void setRootName(String rootName1) {
    rootName = rootName1;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL1) {
    imageURL = imageURL1;
  }

  public String getRootImage() {
    return rootImage;
  }

  public void setRootImage(String rootImage1) {
    rootImage = rootImage1;
  }

  public String getFolderImg() {
    return folderImg;
  }

  public void setFolderImg(String folderImg1) {
    folderImg = folderImg1;
  }

  public String getFolderOpenImg() {
    return folderOpenImg;
  }

  public void setFolderOpenImg(String folderOpenImg1) {
    folderOpenImg = folderOpenImg1;
  }

  public String getFileImg() {
    return fileImg;
  }

  public void setFileImg(String fileImg1) {
    fileImg = fileImg1;
  }

  public String getOpenImg() {
    return openImg;
  }

  public void setOpenImg(String openImg1) {
    openImg = openImg1;
  }

  public String getCloseImg() {
    return closeImg;
  }

  public void setCloseImg(String closeImg1) {
    closeImg = closeImg1;
  }

  public String getElementLineImg() {
    return elementLineImg;
  }

  public void setElementLineImg(String elementLineImg1) {
    elementLineImg = elementLineImg1;
  }

  public String getLineImg() {
    return lineImg;
  }

  public void setLineImg(String lineImg1) {
    lineImg = lineImg1;
  }

  public String getEndLineImg() {
    return endLineImg;
  }

  public void setEndLineImg(String endLineImg1) {
    endLineImg = endLineImg1;
  }

  public String getEndFolderOpenImg() {
    return endFolderOpenImg;
  }

  public void setEndFolderOpenImg(String endFolderOpenImg1) {
    endFolderOpenImg = endFolderOpenImg1;
  }

  public String getEndFolderCloseImg() {
    return endFolderCloseImg;
  }

  public void setEndFolderCloseImg(String endFolderCloseImg1) {
    endFolderCloseImg = endFolderCloseImg1;
  }

  public boolean isIsFolderURL() {
    return isFolderURL;
  }

  public void setIsFolderURL(boolean isFolderURL1) {
    isFolderURL = isFolderURL1;
  }

  public String getRootURL() {
    return rootURL;
  }

  public void setRootURL(String rootURL1) {
    rootURL = rootURL1;
  }
  public String getTarget() {
    return target;
  }
  public void setTarget(String target1) {
    target = target1;
  }
  public String getLinkClass() {
    return linkClass;
  }
  public void setLinkClass(String linkClass1) {
    linkClass = linkClass1;
  }
  public String getStyleClass() {
    return styleClass;
  }
  public void setStyleClass(String styleClass1) {
    styleClass = styleClass1;
  }
  public String getImgClass() {
    return imgClass;
  }
  public void setImgClass(String imgClass1) {
    imgClass = imgClass1;
  }
  public String getUseDataImg() {
    return useDataImg;
  }
  public void setUseDataImg(String useDataImg1) {
    useDataImg = useDataImg1;
  }
  public String getJs() {
    return js;
  }
  public void setJs(String js1) {
    js = js1;
  }
  public String getOnMouseOver() {
    return onMouseOver;
  }
  public void setOnMouseOver(String onMouseOver1) {
    onMouseOver = onMouseOver1;
  }
  public String getOnMouseOut() {
    return onMouseOut;
  }
  public void setOnMouseOut(String onMouseOut1) {
    onMouseOut = onMouseOut1;
  }
}