/**
 * 
 */
package com.clinakos.data.dao.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.clinakos.data.dao.IRoleBasedAuthenticationDAO;
import com.clinakos.data.model.core.SecurityRolePrivilege;

/**
 * @author SAURABH
 *
 */
public class RoleBasedAuthenticationDaoImpl extends BaseDaoImpl implements IRoleBasedAuthenticationDAO{

	public static final Logger logger = Logger.getLogger("RoleBasedAuthenticationDaoImpl.class");
	public void selectRoleBasedMenu(String role) {
		logger.info(":::::::::inside RoleBasedAuthenticationDaoImpl:::::::"); 
	}

	public void selectRoleBasedFunction(String role) {
		
	}
	
	
/*
 * *******added by saurabh
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IDashBoardDAO#selectRoleBasedPrivilegesMap()
 */
				public Map<String, Boolean> selectRoleBasedPrivilegesMap(int roleId) {
					logger.info(":::::::::inside selectRoleBasedPrivilegesMap:::::::"+roleId);
					Map<String,Boolean>rolePrivilegeMap=new LinkedHashMap<String, Boolean>();
					List<SecurityRolePrivilege> srpList= new ArrayList<SecurityRolePrivilege>();
					Criteria criteria= getSessionFactory().getCurrentSession().createCriteria(SecurityRolePrivilege.class);
					criteria.add(Restrictions.eq("roleId", roleId)); 
					srpList=criteria.list();
					System.out.println("::::::::::::::srpList size="+srpList.size());
					for (SecurityRolePrivilege s : srpList) {
						rolePrivilegeMap.put(s.getMasterFunctionMenu().getAction(), s.isPermission());
					}
					System.out.println(rolePrivilegeMap.size()+"::::::selectRoleBasedPrivilegesMap called in service:::::");
					System.out.println();
					return rolePrivilegeMap;
				}

}
