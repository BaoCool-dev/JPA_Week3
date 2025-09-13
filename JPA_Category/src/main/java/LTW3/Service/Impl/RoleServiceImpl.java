package LTW3.Service.Impl;

import java.util.List;

import LTW3.Dao.RoleDao;
import LTW3.Dao.Impl.RoleDaoImpl;
import LTW3.Entity.Role;
import LTW3.Service.RoleService;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
