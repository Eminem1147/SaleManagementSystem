package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IStorageDAO;
import vo.Storage;

public class StorageDAOImpl implements IStorageDAO
{
	private Connection con;
	private PreparedStatement stat = null;

	public StorageDAOImpl(Connection con)
	{
		this.con = con;
	}

	public boolean create(Storage Storage) throws Exception
	{
		// TODO Auto-generated method stub
		if (null != findByProductid(Storage.getProductid()))
		{
			return alterNum(Storage);
		} else
		{

			String sql = "INSERT INTO storage VALUES(?,?,?,?,?,?,?,?);";
			stat = con.prepareStatement(sql);
			stat.setString(1, Storage.getProductname());
			stat.setInt(2, Storage.getProductid());
			stat.setString(3, Storage.getUnit());
			stat.setFloat(4, Storage.getPrice());
			stat.setInt(5, Storage.getDownlimit());
			stat.setInt(6, Storage.getNumber());

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

	public List<Storage> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT productname, productid, unit, price, downlimit, number FROM storage;";
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		Storage Storage = null;
		List<Storage> list = new ArrayList<Storage>();
		while (rs.next())
		{
			Storage = new Storage(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getInt(5),
					rs.getInt(6));
			list.add(Storage);
		}
		return list;
	}

	public boolean delete(Storage Storage) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "delete from storage where productid=?;";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Storage.getProductid());
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

	// SELECT productname, productid, unit, price, downlimit, number FROM
	// storage;
	public Storage findByProductid(int productid) throws Exception
	{
		String sql = "SELECT productname, productid, unit, price, downlimit, number FROM storage where productid=?;";
		stat = con.prepareStatement(sql);
		stat.setInt(1, productid);
		ResultSet rs = stat.executeQuery();
		Storage store = null;
		if (rs.next())
		{
			store = new Storage();
			// store.setStorageid(rs.getInt(1));
			store.setProductname(rs.getString(1));
			store.setProductid(rs.getInt(2));
			store.setUnit(rs.getString(3));
			store.setPrice(rs.getFloat(4));
			store.setDownlimit(rs.getInt(5));
			store.setNumber(rs.getInt(6));
		}
		return store;

	}

	public boolean alterNum(Storage Storage) throws Exception
	{
		if (Storage == null)
		{
			return false;
		} else
		{
			String sql = "update storage set number=number+? where productid=?";
			stat = con.prepareStatement(sql);
			stat.setInt(1, Storage.getNumber());
			stat.setInt(2, Storage.getProductid());
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
}
