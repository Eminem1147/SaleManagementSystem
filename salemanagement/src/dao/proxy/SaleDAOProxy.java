package dao.proxy;

import java.util.List;

import dao.ISaleDAO;
import dao.impl.SaleDAOImpl;
import dbc.DatabaseConnection;
import vo.Sale;

public class SaleDAOProxy implements ISaleDAO{
	private DatabaseConnection dbc;
	private ISaleDAO dao = null;

	public SaleDAOProxy() throws Exception {
		dbc = new DatabaseConnection();
		dao = new SaleDAOImpl(dbc.getConnection());
	}

	@Override
	public boolean create(Sale Sale) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		//if(dao.findBySalename(Sale.getSalename())==null)
			flag=dao.create(Sale);
		dbc.close();
		return flag;
	}

	@Override
	public List<Sale> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Sale>list =dao.findAll();
		dbc.close();
		return list;
	}

	@Override
	public boolean delete(Sale Sale) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.delete(Sale)){
			flag=true;
		}
		dbc.close();
		return flag;
	}
}
