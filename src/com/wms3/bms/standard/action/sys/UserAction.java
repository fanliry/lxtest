package com.wms3.bms.standard.action.sys;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.business.competenceMgr.ModuleOpMgr;
import com.ricosoft.business.competenceMgr.RoleMgr;
import com.ricosoft.business.competenceMgr.UserMgr;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.entity.competenceMgr.UserInfo;
import com.ricosoft.entity.competenceMgr.UserModuleOpInfo;
import com.ricosoft.entity.competenceMgr.UserRoleInfo;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAction extends AbstractAction
{
  public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");
    HttpSession httpsession = request.getSession(false);

    String strUserCode = CommonTools.getStrToGb2312(request.getParameter("userCode"));

    String strUserName = CommonTools.getStrToGb2312(request.getParameter("userName"));

    String strUnitCode = CommonTools.getStrToGb2312(request.getParameter("unitCode"));
    
    String strUsersfFlag = CommonTools.getStrToGb2312(request.getParameter("usersflag")); //选择多个值班人员标志

    String strFlag = CommonTools.getStrToGb2312(request.getParameter("flag"));
    String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));	//每页显示行数
    int maxLine = 25;		//分页显示的行数；
    if(linerow != null && linerow.length()>0){
    	maxLine = Integer.parseInt(linerow);
    }
    try
    {
      List ls = null;

      String strQuerySQL = UserMgr.getUserInfoSql(strUserName, strUserCode, strUnitCode, strFlag);

      String strCountSQL = UserMgr.getUserInfoCountSQL(strUserName, strUserCode, strUnitCode, strFlag);

      String strUrl = "/standard/jsp/common/common_user_list.jsp";
      if(strUsersfFlag != null && strUsersfFlag.equals("1")){
    	  strUrl = "/standard/jsp/common/common_users_list.jsp";
      }

      PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl, 1, maxLine);

      ls = pt.getLsResult();

      request.setAttribute("exResultList", ls);
      httpsession.setAttribute("commpaging", pt);
      request.setAttribute("pagingTool", pt);

      request.getRequestDispatcher(strUrl).forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("系统管理==>用户管理==>用户查询失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    return null;
  }

  public String ricoExecAdd(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");
    HttpSession httpsession = request.getSession(false);

    String strUserCode = CommonTools.getStrToGb2312(request.getParameter("userCode"));
    String strPassword = CommonTools.getStrToGb2312(request.getParameter("password"));
    String strUserName = CommonTools.getStrToGb2312(request.getParameter("userName"));
    String strUnitCode = CommonTools.getStrToGb2312(request.getParameter("unitCode"));
    String strTel = CommonTools.getStrToGb2312(request.getParameter("tel"));
    String strEmail = CommonTools.getStrToGb2312(request.getParameter("email"));
    String strHandset = CommonTools.getStrToGb2312(request.getParameter("handset"));
    String strAddress = CommonTools.getStrToGb2312(request.getParameter("address"));
    String strUserType = CommonTools.getStrToGb2312(request.getParameter("userType"));
    String strCreateUser = CommonTools.getStrToGb2312(request.getParameter("createUser"));
    String strRemark = CommonTools.getStrToGb2312(request.getParameter("remark"));
    String strFlag = CommonTools.getStrToGb2312(request.getParameter("flag"));
    String strUserGroup = CommonTools.getStrToGb2312(request.getParameter("userGroup"));
    try
    {
      UserMgr userMgr = new UserMgr(dao);

      UserInfo user = new UserInfo(strUserCode, strPassword, strUserName, 
        strUnitCode, strTel, strEmail, 
        strHandset, strAddress, strUserType, 
        strCreateUser, strRemark);
      user.setM_strUsableness(strFlag);
      user.setM_strUserGroup(strUserGroup);
      userMgr.addUserInfo(user);

      PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
      List ls = null;
      String strUrl = null;
      if (pt != null)
      {
        int rows = pt.getM_iCountRow() + 1;
        pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
        ls = CommonPagination.getPagingList(dao, pt);

        strUrl = pt.getM_strUrl();
      }
      if (strUrl == null)
      {
        strUrl = "/standard/jsp/selectPages/user_list.jsp";
      }
      request.setAttribute("exResultList", ls);
      request.setAttribute("pagingTool", pt);
      httpsession.setAttribute("paging", pt);

      request.getRequestDispatcher(strUrl).forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("系统管理==>用户管理==>增加用户失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
    return null;
  }

  public String ricoExecDelete(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");
    HttpSession httpsession = request.getSession(false);
    try
    {
      String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));

      UserMgr userMgr = new UserMgr(dao);
      PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
      List ls = null;
      String strUrl = null;
      if ((strDeleteStr != null) && (strDeleteStr.length() > 0))
      {
        String[] roleIds = strDeleteStr.split(",");
        for (int i = 0; i < roleIds.length; ++i)
        {
          String strTemp = roleIds[i];
          if ((strTemp != null) && (!(strTemp.trim().equals("root"))))
          {
            userMgr.deleteUserInfoToId(strTemp);
          }
        }

        if (pt != null)
        {
          int rows = pt.getM_iCountRow() - roleIds.length;
          pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());

          ls = CommonPagination.getPagingList(dao, pt);

          strUrl = pt.getM_strUrl();
        }
      }
      if (strUrl == null)
      {
        strUrl = "/standard/jsp/selectPages/user_list.jsp";
      }

      request.setAttribute("exResultList", ls);
      request.setAttribute("pagingTool", pt);
      httpsession.setAttribute("paging", pt);

      request.getRequestDispatcher(strUrl).forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("系统管理==>用户管理==>删除用户失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
    return null;
  }

  public String ricoExecUpdate(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");
    HttpSession httpsession = request.getSession(false);

    String strUserId = CommonTools.getStrToGb2312(request.getParameter("userId"));
    String strPassword = CommonTools.getStrToGb2312(request.getParameter("password"));
    String strUserName = CommonTools.getStrToGb2312(request.getParameter("userName"));
    String strUnitCode = CommonTools.getStrToGb2312(request.getParameter("unitCode"));
    String strTel = CommonTools.getStrToGb2312(request.getParameter("tel"));
    String strEmail = CommonTools.getStrToGb2312(request.getParameter("email"));
    String strHandset = CommonTools.getStrToGb2312(request.getParameter("handset"));
    String strAddress = CommonTools.getStrToGb2312(request.getParameter("address"));
    String strUserType = CommonTools.getStrToGb2312(request.getParameter("userType"));
    String strRemark = CommonTools.getStrToGb2312(request.getParameter("remark"));
    String strUserGroup = CommonTools.getStrToGb2312(request.getParameter("userGroup"));
    String strFlag = CommonTools.getStrToGb2312(request.getParameter("flag"));
    try
    {
      UserMgr userMgr = new UserMgr(dao);
      UserInfo user = userMgr.getUserInfoFromId(strUserId);
      if (user != null)
      {
        user.setM_strPassword(strPassword);
        user.setM_strUsername(strUserName);
        user.setM_strUnitCode(strUnitCode);
        user.setM_strTel(strTel);
        user.setM_strEmail(strEmail);
        user.setM_strHandset(strHandset);
        user.setM_strAddress(strAddress);
        user.setM_strUserType(strUserType);
        user.setM_strRemark(strRemark);
        user.setM_strUserGroup(strUserGroup);
        user.setM_strUsableness(strFlag);
        userMgr.updateUserInfo(user);
      }

      PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
      List ls = null;
      String strUrl = null;
      if (pt != null)
      {
        ls = CommonPagination.getPagingList(dao, pt);

        strUrl = pt.getM_strUrl();
      }
      if (strUrl == null)
      {
        strUrl = "/standard/jsp/selectPages/user_list.jsp";
      }
      request.setAttribute("exResultList", ls);
      request.setAttribute("pagingTool", pt);
      httpsession.setAttribute("paging", pt);

      request.getRequestDispatcher(strUrl).forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("系统管理==>用户管理==>修改用户失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
    return null;
  }

  public String ricoExecRole(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");

    String strUserId = CommonTools.getStrToGb2312(request.getParameter("userId"));
    try
    {
      UserMgr userMgr = new UserMgr(dao);
      UserInfo user = userMgr.getUserInfoFromId(strUserId);

      List lsRole = RoleMgr.getRoleInfoList(dao);

      List lsUserRole = userMgr.getUserRoleInfoRoleList(strUserId);

      request.setAttribute("user", user);
      request.setAttribute("lsRole", lsRole);
      request.setAttribute("lsUserRole", lsUserRole);

      request.getRequestDispatcher("/jsp/competenceMgr/user_RoleAssign.jsp").forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("查询用户的角色列表失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    return null;
  }

  public String ricoExecRoleAdd(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");
    HttpSession httpsession = request.getSession(false);

    String strUserId = CommonTools.getStrToGb2312(request.getParameter("userId"));
    String strRoles = CommonTools.getStrToGb2312(request.getParameter("assignRole"));
    try
    {
      UserMgr userMgr = new UserMgr(dao);

      userMgr.deletUserRoleInfoToUserID(strUserId);

      if ((strRoles.length() > 0) && (strUserId != null) && (strUserId.trim().length() > 0))
      {
        String[] roleIds = strRoles.split(",");
        for (int i = 0; i < roleIds.length; ++i)
        {
          String StrTemp = roleIds[i];
          UserRoleInfo userRole = new UserRoleInfo(strUserId, StrTemp);
          userMgr.addRoleUserInfo(userRole);
        }
      }

      PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
      List ls = null;
      String strUrl = null;
      if (pt != null)
      {
        ls = CommonPagination.getPagingList(dao, pt);

        strUrl = pt.getM_strUrl();
      }
      if (strUrl == null)
      {
        strUrl = "/standard/jsp/selectPages/user_list.jsp";
      }
      request.setAttribute("exResultList", ls);
      request.setAttribute("pagingTool", pt);
      httpsession.setAttribute("paging", pt);

      request.getRequestDispatcher(strUrl).forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("增加用户角色失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    return null;
  }

  public String ricoExecModule(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");

    String strUserId = CommonTools.getStrToGb2312(request.getParameter("userId"));
    try
    {
      UserMgr userMgr = new UserMgr(dao);
      ModuleOpMgr moduleMgr = new ModuleOpMgr(dao);
      UserInfo user = userMgr.getUserInfoFromId(strUserId);

      List lsModule = moduleMgr.getModuleOpInfoList();

      String modTree = ModuleOpMgr.toLsModuleMgrNodeString(lsModule);

      String strHasPopedom = userMgr.getModulePopedomFromId(strUserId);

      request.setAttribute("user", user);
      request.setAttribute("modTree", modTree);
      request.setAttribute("popedom", strHasPopedom);

      request.getRequestDispatcher("/jsp/competenceMgr/user_PopedomAssign.jsp").forward(request, response);
    }
    catch (Exception er) {
      Logger.error("查询角色权限失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    return null;
  }

  public String ricoExecModuleAdd(HashMap hsSysParam, HashMap hsCurrentParam)
    throws Exception
  {
    HttpServletRequest request = (HttpServletRequest)(HttpServletRequest)hsCurrentParam.get("request");
    HttpServletResponse response = (HttpServletResponse)(HttpServletResponse)hsCurrentParam.get("response");
    EntityDAO dao = (EntityDAO)(EntityDAO)hsSysParam.get("dao");
    HttpSession httpsession = request.getSession(false);

    String strUserId = CommonTools.getStrToGb2312(request.getParameter("userId"));
    String strPopedom = CommonTools.getStrToGb2312(request.getParameter("assignPopedom"));
    try
    {
      UserMgr userMgr = new UserMgr(dao);

      userMgr.deleteUserModuleOpInfoFromUserId(strUserId);

      if ((strPopedom.length() > 0) && (strUserId != null) && (strUserId.trim().length() > 0))
      {
        String[] modOpCodes = strPopedom.split(",");
        for (int i = 0; i < modOpCodes.length; ++i) {
          String strTemp = modOpCodes[i];
          UserModuleOpInfo userMod = new UserModuleOpInfo(strUserId, strTemp);
          userMgr.addUserModuleOpInfo(userMod);
        }

      }

      PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
      List ls = null;
      String strUrl = null;
      if (pt != null)
      {
        ls = CommonPagination.getPagingList(dao, pt);

        strUrl = pt.getM_strUrl();
      }
      if (strUrl == null)
      {
        strUrl = "/standard/jsp/selectPages/user_list.jsp";
      }
      request.setAttribute("exResultList", ls);
      request.setAttribute("pagingTool", pt);
      httpsession.setAttribute("paging", pt);

      request.getRequestDispatcher(strUrl).forward(request, response);
    }
    catch (Exception er)
    {
      Logger.error("增加用户权限失败:" + er.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    return null;
  }
}