package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IAccountDAO;
import vo.Account;

public class AccountDAOImpl implements IAccountDAO
{
	private Connection con;
	private PreparedStatement stat = null;

	public AccountDAOImpl(Connection con)
	{
		this.con = con;
	}

	@Override
	public boolean create(Account Account) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO account VALUES(?,?,?);";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Account.getAccountid());
		stat.setString(2, Account.getAccountname());
		stat.setString(3, Account.getAccountpassword());
		int update = stat.executeUpdate();
		if (update > 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public List<Account> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT accountid,accountname,accountpassword FROM account;";
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		Account Account = null;
		List<Account> list = new ArrayList<Account>();
		while (rs.next())
		{
			Account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
			list.add(Account);
		}
		return list;
	}

	@Override
	public Account findByAccountname(String Accountname) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT accountid,accountname,accountpassword FROM account WHERE accountname=?;";
		stat = con.prepareStatement(sql);
		stat.setString(1, Accountname);
		ResultSet rs = stat.executeQuery();
		Account Account = null;
		if (rs.next())
		{
			// Account = new Account(rs.getInt(1), rs.getString(2),
			// rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(6));
			Account = new Account();
			Account.setAccountid(rs.getInt(1));
			Account.setAccountname(rs.getString(2));
			Account.setAccountpassword(rs.getString(3));
		}
		return Account;
	}

	@Override
	public boolean alter(Account Account) throws Exception
	{
		// TODO Auto-generated method stub
		Account acc = findByAccountname(Account.getAccountname());
		if (acc == null)
			return false;
		else
		{
			String sql = "update account set accountname=?,accountpassword=? where accountid=?;";
			stat = con.prepareStatement(sql);
			stat.setString(1, Account.getAccountname());
			stat.setString(2, Account.getAccountpassword());
			stat.setInt(3, Account.getAccountid());
			int update = stat.executeUpdate();
			if (update > 0)
			{
				return true;
			} else
			{
				return false;
			}

		}
	}

	@Override
	public boolean delete(Account Account) throws Exception
	{
		// TODO Auto-generated method stub
		Account acc = findByAccountname(Account.getAccountname());
		if (acc == null)
			return false;
		else
		{
			String sql = "delete from account where accountid=?;";
			stat = con.prepareStatement(sql);
			stat.setInt(1, Account.getAccountid());
			int update = stat.executeUpdate();
			System.out.println(update);
			if (update > 0)
			{
				return true;
			} else
			{
				return false;
			}

		}
	}

}
