package dao;

import java.util.List;

import vo.Account;

public interface IAccountDAO {
	public boolean create(Account Account) throws Exception;

	public List<Account> findAll() throws Exception;

	public Account findByAccountname(String Accountname) throws Exception;
	
	public boolean alter(Account Account) throws Exception;
	
	public boolean delete(Account Account) throws Exception;
}
