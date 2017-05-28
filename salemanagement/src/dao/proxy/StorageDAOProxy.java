package dao.proxy;

import java.util.List;

import dao.IStorageDAO;
import dao.impl.StorageDAOImpl;
import dbc.DatabaseConnection;
import vo.Storage;

public class StorageDAOProxy implements IStorageDAO{
	private DatabaseConnection dbc;
	private IStorageDAO dao = null;

	public StorageDAOProxy() throws Exception {
		dbc = new DatabaseConnection();
		dao = new StorageDAOImpl(dbc.getConnection());
	}

	@Override
	public boolean create(Storage Storage) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.findByProductid(Storage.getProductid())==null){
			flag=dao.create(Storage);
		}
		dbc.close();
		return flag;
	}

	@Override
	public List<Storage> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Storage>list =dao.findAll();
		dbc.close();
		return list;
	}

	@Override
	public Storage findByProductid(int Storagename) throws Exception {
		// TODO Auto-generated method stub
		Storage Storage=dao.findByProductid(Storagename);
		dbc.close();
		return Storage;
	}

	@Override
	public boolean alterNum(Storage Storage) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.alterNum(Storage)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

	@Override
	public boolean delete(Storage Storage) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.delete(Storage)){
			flag=true;
		}
		dbc.close();
		return flag;
	}
}
