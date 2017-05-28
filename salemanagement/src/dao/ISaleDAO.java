package dao;

import java.util.List;

import vo.Sale;

public interface ISaleDAO {
	public boolean create(Sale Sale) throws Exception;

	public List<Sale> findAll() throws Exception;

	public boolean delete(Sale Sale) throws Exception;
}
