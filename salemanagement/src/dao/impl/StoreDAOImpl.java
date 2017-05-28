package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IStoreDAO;
import vo.Store;

public class StoreDAOImpl implements IStoreDAO
{
	private Connection con;
	private PreparedStatement stat = null;

	public StoreDAOImpl(Connection con)
	{
		this.con = con;
	}

	@Override
	public boolean create(Store Store) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO store VALUES(?,?,?,?,?,?);";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Store.getStoreid());
		stat.setInt(2, Store.getProductid());
		stat.setString(3, Store.getProductname());
		stat.setFloat(4, Store.getInprice());
		stat.setInt(5, Store.getNumber());
		stat.setString(6, Store.getUnit());

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
	public List<Store> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT storeid, productid, productname, inprice, number, unit FROM store;";
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		Store Store = null;
		List<Store> list = new ArrayList<Store>();
		while (rs.next())
		{
			Store = new Store(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getInt(5),
					rs.getString(6));
			list.add(Store);
		}
		return list;
	}

	@Override
	public boolean delete(Store Store) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "delete from store where storeid=?;";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Store.getStoreid());
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
