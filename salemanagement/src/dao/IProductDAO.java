package dao;

import java.util.List;

import vo.Product;

public interface IProductDAO {
	public boolean create(Product Product) throws Exception;

	public List<Product> findAll() throws Exception;

	public Product findByProductname(String Productname) throws Exception;
	
	public Product findByProductid(int Productid) throws Exception;

	public boolean alter(Product Product) throws Exception;
	
	public boolean delete(Product Product) throws Exception;
}
