package dao.proxy;

import java.util.List;

import dao.IProductDAO;
import dao.impl.ProductDAOImpl;
import dbc.DatabaseConnection;
import vo.Product;

public class ProductDAOProxy implements IProductDAO{
	private DatabaseConnection dbc;
	private IProductDAO dao = null;

	public ProductDAOProxy() throws Exception {
		dbc = new DatabaseConnection();
		dao = new ProductDAOImpl(dbc.getConnection());
	}

	@Override
	public boolean create(Product Product) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.findByProductname(Product.getProductname())==null){
			flag=dao.create(Product);
		}
		dbc.close();
		return flag;
	}

	@Override
	public List<Product> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Product>list =dao.findAll();
		dbc.close();
		return list;
	}

	@Override
	public Product findByProductname(String Productname) throws Exception {
		// TODO Auto-generated method stub
		Product Product=dao.findByProductname(Productname);
		dbc.close();
		return Product;
	}

	@Override
	public boolean alter(Product Product) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.alter(Product)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

	@Override
	public boolean delete(Product Product) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.delete(Product)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

	@Override
	public Product findByProductid(int Productid) throws Exception {
		// TODO Auto-generated method stub
		Product Product=dao.findByProductid(Productid);
		dbc.close();
		return Product;
	}
}
