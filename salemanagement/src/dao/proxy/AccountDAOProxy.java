package dao.proxy;

import java.util.List;

import dbc.DatabaseConnection;

import dao.IAccountDAO;
import dao.impl.AccountDAOImpl;
import vo.Account;

public class AccountDAOProxy implements IAccountDAO {
	private DatabaseConnection dbc;
	private IAccountDAO dao = null;

	public AccountDAOProxy() throws Exception {
		dbc = new DatabaseConnection();
		dao = new AccountDAOImpl(dbc.getConnection());
	}

	@Override
	public boolean create(Account Account) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.findByAccountname(Account.getAccountname())==null){
			flag=dao.create(Account);
		}
		dbc.close();
		return flag;
	}

	@Override
	public List<Account> findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Account>list =dao.findAll();
		dbc.close();
		return list;
	}

	@Override
	public Account findByAccountname(String Accountname) throws Exception {
		// TODO Auto-generated method stub
		Account Account=dao.findByAccountname(Accountname);
		dbc.close();
		return Account;
	}

	@Override
	public boolean alter(Account Account) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.alter(Account)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

	@Override
	public boolean delete(Account Account) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(dao.delete(Account)){
			flag=true;
		}
		dbc.close();
		return flag;
	}

}
