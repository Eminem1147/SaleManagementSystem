package dao.proxy;

import java.util.List;

import dao.IGuestDAO;
import dao.impl.GuestDAOImpl;
import dbc.DatabaseConnection;
import vo.Guest;

public class GuestDAOProxy implements IGuestDAO{

	private DatabaseConnection dbc;
	private IGuestDAO dao = null;

	public GuestDAOProxy() throws Exception {
		dbc = new DatabaseConnection();
		dao = new GuestDAOImpl(dbc.getConnection());
	}

	@Override
	public boolean create(Guest Guest) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.findByGuestname(Guest.getGuestname())==null){
			flag=dao.create(Guest);
		}
		dbc.close();
		return flag;
	}

	@Override
	public List<Guest> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Guest>list =dao.findAll();
		dbc.close();
		return list;
	}

	@Override
	public Guest findByGuestname(String Guestname) throws Exception {
		// TODO Auto-generated method stub
		Guest Guest=dao.findByGuestname(Guestname);
		dbc.close();
		return Guest;
	}

	@Override
	public boolean alter(Guest Guest) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.alter(Guest)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

	@Override
	public boolean delete(Guest Guest) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.delete(Guest)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

	
}
