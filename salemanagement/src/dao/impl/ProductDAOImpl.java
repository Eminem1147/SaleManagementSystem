package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IProductDAO;
import vo.Product;

public class ProductDAOImpl implements IProductDAO
{
	private Connection con;
	private PreparedStatement stat = null;

	public ProductDAOImpl(Connection con)
	{
		this.con = con;
	}

	@Override
	public boolean create(Product Product) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?);";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Product.getProductid());
		stat.setString(2, Product.getProductname());
		stat.setString(3, Product.getProducedate());
		stat.setFloat(4, Product.getInprice());
		stat.setFloat(5, Product.getSaleprice());
		stat.setInt(6, Product.getStoragetime());
		stat.setString(7, Product.getUnit());
		stat.setString(8, Product.getIndate());
		// System.out.println(stat);
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
	public List<Product> findAll() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT productid, productname, producedate, inprice, saleprice, storagetime, unit, indate FROM product;";
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		Product Product = null;
		List<Product> list = new ArrayList<Product>();
		while (rs.next())
		{
			Product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5),
					rs.getInt(6), rs.getString(7), rs.getString(8));
			list.add(Product);
		}
		return list;
	}

	@Override
	public Product findByProductname(String Productname) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT productid, productname, producedate, inprice, saleprice, storagetime, unit, indate FROM product WHERE productname=?;";
		stat = con.prepareStatement(sql);
		stat.setString(1, Productname);
		ResultSet rs = stat.executeQuery();
		Product Product = null;
		if (rs.next())
		{
			Product = new Product();
			Product.setProductid(rs.getInt(1));
			Product.setProductname(rs.getString(2));
			Product.setProducedate(rs.getString(3));
			Product.setInprice(rs.getFloat(4));
			Product.setSaleprice(rs.getFloat(5));
			Product.setStoragetime(rs.getInt(6));
			Product.setUnit(rs.getString(7));
			Product.setIndate(rs.getString(8));
		}
		return Product;
	}

	@Override
	public boolean alter(Product Product) throws Exception
	{
		String sql = "update product set productname=?, producedate=?, inprice=?, saleprice=?, storagetime=?, unit=?, indate=? where productid=?;";
		stat = con.prepareStatement(sql);
		stat.setString(1, Product.getProductname());
		stat.setString(2, Product.getProducedate());
		stat.setFloat(3, Product.getInprice());
		stat.setFloat(4, Product.getSaleprice());
		stat.setInt(5, Product.getStoragetime());
		stat.setString(6, Product.getUnit());
		stat.setString(7, Product.getIndate());
		stat.setInt(8, Product.getProductid());
		int update = stat.executeUpdate();
		System.out.println(stat);
		if (update > 0)
		{
			return true;
		} else
		{
			return false;
		}

	}

	@Override
	public boolean delete(Product Product) throws Exception
	{
		// TODO Auto-generated method stub
		Product acc = findByProductname(Product.getProductname());
		if (acc == null)
			return false;
		else
		{
			String sql = "delete from product where productid=?;";
			stat = con.prepareStatement(sql);
			stat.setInt(1, Product.getProductid());
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

	@Override
	public Product findByProductid(int Productid) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "SELECT productid, productname, producedate, inprice, saleprice, storagetime, unit, indate FROM product WHERE productid=?;";
		stat = con.prepareStatement(sql);
		stat.setInt(1, Productid);
		ResultSet rs = stat.executeQuery();
		Product Product = null;
		if (rs.next())
		{
			Product = new Product();
			Product.setProductid(rs.getInt(1));
			Product.setProductname(rs.getString(2));
			Product.setProducedate(rs.getString(3));
			Product.setInprice(rs.getFloat(4));
			Product.setSaleprice(rs.getFloat(5));
			Product.setStoragetime(rs.getInt(6));
			Product.setUnit(rs.getString(7));
			Product.setIndate(rs.getString(8));
		}
		return Product;
	}
}
