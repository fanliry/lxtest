package com.wms3.bms.standard.action.base.ajaxTree;


import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.wms3.bms.standard.service.BMSService;


/**
 * 功能：树标签处理类
 * @author 
 */
public class BlueTreeTag  implements Tag {
	
  private PageContext pageContext;
  private Tag parent;
  
  public static String rootId; //根节点ID
  public static String blueClass; //根据节点ID获得所有该节点子节点的实现类
  public static String blueURL; //点击节点超连接---isUseDataURL为false时有用;当isUseDataURL="true"时blueURL表示字定义连接参数
  public static boolean isUseDataURL; //是否使用节点封装对象的节点超连接
  public static String rootName; //根节点名称
  public static String imageURL; //所有图片目录路径
  public static String rootImage; //根节点图片
  public static String folderImg; //有子节点关闭图片
  public static String folderOpenImg; //有子节点展开图片
  public static String fileImg; //无子节点图片
  public static String openImg; //节点展开图片
  public static String closeImg; //节点关闭图片
  public static String elementLineImg; //带有节点的线图片
  public static String lineImg; //无节点的线图片
  public static String endLineImg; //最后一个节点线图片
  public static String endFolderOpenImg; //最后一个有子节点展开图片
  public static String endFolderCloseImg; //最后一个有子节点关闭图片
  public static boolean isFolderURL; //是否给有子节点的节点加超连接---isUseDataURL为false时有用
  public static String rootURL; //根节点连接
  public static String target; //所有连接的target属性
  public static String linkClass; //超连接样式
  public static String styleClass; //总样式
  public static String imgClass; //图片样式
  public static String useDataImg; //节点图片是否使用数据类图片，no为不使用，first为仅第一级目录使用，all全部使用
  public static String js; //设置JS文件位置
  public static String onMouseOver; //鼠标经过每个节点时的样式
  public static String onMouseOut; //鼠标离开每个节点时的样式

  public BlueTreeTag() {
    super();
    rootId = "0";
    blueClass = null; //必须设置
    blueURL = "#";
    isUseDataURL = false;
    rootName = "货位目录树";
    rootImage = "rootImage.gif";
    imageURL = null; //必须设置
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
    js=null; //必须设置
    onMouseOver="onMouseOver";
    onMouseOut="onMouseOut";
  }

  /**
   * 设置标签的页面上下文
   * @param pageContext
   */
  public void setPageContext(PageContext pageContext) {
    this.pageContext = pageContext;
    
  }

  /**
   * 设置上一级标签
   * @param parent
   */
  public void setParent(Tag parent) {
    this.parent = parent;
  }

  public Tag getParent() {
    return this.parent;
  }

  /**
   * 开始标签时的操作
   * @return
   * @throws JspException
   */
  public int doStartTag() throws JspException {
	  

 

    if(blueClass==null){
      throw new JspException("树标签blueClass属性未设置！");
    }
    if(imageURL==null){
      throw new JspException("树标签imageURL属性未设置！");
    }
    if(js==null){
      throw new JspException("树标签js属性未设置！");
    }
    imageURL =BMSService.getm_strServerPath() + imageURL;
    js=BMSService.getm_strServerPath()+js;
    return SKIP_BODY; //返回SKIP_BODY，表示不计算标签体
  }

  /**
   * 结束标签时的操作
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
      throw new JspException("创建树错误:" + ex.getMessage());
    }

    return EVAL_PAGE;
  }

  /**
   * Release 用于释放标签程序占用的资源，比如使用了数据库连接，那么应该关闭这个连接
   */
  public void release() {
  }

/**
   * 构建树
   * @return 返回树HTML代码字符串
   */
  public String createTree() throws Exception {
	  StringBuffer result = new StringBuffer();
	  
    BlueTreeFactory treeFactory = new BlueTreeFactory();
    BlueTreeInterFace tree = (BlueTreeInterFace) treeFactory.create(blueClass);

    List elements = null;
    elements = tree.getTreeElements(rootId);
     
    //样式
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

    //开始创建树
    result.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"");
    result.append(styleClass);
    result.append("\">");

    //创建跟节点
    result.append("<tr onMouseOver=\"this.className='"); result.append(onMouseOver);
    result.append("'\" onMouseOut=\"this.className='");
    result.append(onMouseOut);
    result.append("'\">");
    result.append("<td>");
    //因根节点暂不需要链接，故先注释掉
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

    //创建第一级节点
    for (int i = 0; i < elements.size(); i++) {
      BlueTreeData treeData = new BlueTreeData();
      treeData = (BlueTreeData) elements.get(i);
      //加超连接头
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
          //当isUseDataURL="true"时blueURL表示字定义连接参数
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
      //添加节点单击事件
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

      //加节点展开关闭图片
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

      //加节点图片
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
      //加节点名字
      result.append(treeData.getName());
      //加超连接尾
      //===================================================
       result.append("</a>");
      //===================================================

      result.append("</td>");

      result.append("</tr>");
      //加下一级节点空间
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