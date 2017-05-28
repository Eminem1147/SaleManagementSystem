package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ISaleDAO;
import vo.Sale;

public class SaleDAOImpl implements ISaleDAO
{
	private Connection con;
	private PreparedStatement stat = null;

	public SaleDAOImpl(Connection con)
	{
		this.con = con;
	}

	@Override
	public boolean create(Sale Sale) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO sale VALUES(?,?,?,?,?,?,?,?);";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Sale.getSaleid());
		stat.setInt(2, Sale.getProductid());
		stat.setString(3, Sale.getProductname());
		stat.setInt(4, Sale.getGuestid());
		stat.setString(5, Sale.getSelltime());
		stat.setString(6, Sale.getSellplace());
		stat.setInt(7, Sale.getNumber());
		stat.setFloat(8, Sale.getMoney());
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
	public List<Sale> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT saleid, productid, productname, guestid, selltime, sellplace, number, money FROM sale;";
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		Sale Sale = null;
		List<Sale> list = new ArrayList<Sale>();
		while (rs.next())
		{
			Sale = new Sale(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
					rs.getInt(7), rs.getFloat(8));
			list.add(Sale);
		}
		return list;
	}

	@Override
	public boolean delete(Sale Sale) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "delete from sale where saleid=?;";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Sale.getSaleid());
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
