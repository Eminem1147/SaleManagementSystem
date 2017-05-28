package dao;

import java.util.List;

import vo.Store;

public interface IStoreDAO {
	public boolean create(Store Store) throws Exception;

	public List<Store> findAll() throws Exception;
	
	public boolean delete(Store Store) throws Exception;
}
