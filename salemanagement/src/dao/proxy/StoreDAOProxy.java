package dao.proxy;

import java.util.List;

import dao.IStoreDAO;
import dao.impl.StoreDAOImpl;
import dbc.DatabaseConnection;
import vo.Store;

public class StoreDAOProxy implements IStoreDAO{
	private DatabaseConnection dbc;
	private IStoreDAO dao = null;

	public StoreDAOProxy() throws Exception {
		dbc = new DatabaseConnection();
		dao = new StoreDAOImpl(dbc.getConnection());
	}

	@Override
	public boolean create(Store Store) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		//if(dao.findByStoragename(Storage.getStoragename())==null)
			flag=dao.create(Store);
		dbc.close();
		return flag;
	}

	@Override
	public List<Store> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Store>list =dao.findAll();
		dbc.close();
		return list;
	}

	@Override
	public boolean delete(Store Store) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.delete(Store)){
			flag=true;
		}
		dbc.close();
		return flag;
	}
}
