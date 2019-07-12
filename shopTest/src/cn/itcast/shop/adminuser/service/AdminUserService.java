package cn.itcast.shop.adminuser.service;

import cn.itcast.shop.adminuser.vo.AdminUser;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;

@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
