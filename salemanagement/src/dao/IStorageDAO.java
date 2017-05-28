package dao;

import java.util.List;

import vo.Storage;

public interface IStorageDAO {

		public boolean create(Storage Storage) throws Exception;

		public List<Storage> findAll() throws Exception;
		
		public boolean delete(Storage Storage) throws Exception;
		
		public Storage findByProductid(int Storageid)throws Exception;
		
		public boolean alterNum(Storage Storage) throws Exception;
}
