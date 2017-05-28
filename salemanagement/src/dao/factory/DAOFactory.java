package dao.factory;

import dao.IAccountDAO;
import dao.IProductDAO;
import dao.IGuestDAO;
import dao.ISaleDAO;
import dao.IStoreDAO;
import dao.IStorageDAO;
import dao.proxy.AccountDAOProxy;
import dao.proxy.ProductDAOProxy;
import dao.proxy.GuestDAOProxy;
import dao.proxy.SaleDAOProxy;
import dao.proxy.StoreDAOProxy;
import dao.proxy.StorageDAOProxy;


public class DAOFactory {
	public static IAccountDAO getAccount(){  
		IAccountDAO dao = null;
        try{  
            dao = new AccountDAOProxy();      
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        return dao;  
    }
	public static IGuestDAO getGuest(){  
		IGuestDAO dao = null;  
        try{  
            dao = new GuestDAOProxy();      
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        return dao;  
    }
	public static IProductDAO getProduct(){  
		IProductDAO dao = null;  
        try{  
            dao = new ProductDAOProxy();      
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        return dao;  
    }
	public static ISaleDAO getSale(){  
		ISaleDAO dao = null;  
        try{  
            dao = new SaleDAOProxy();      
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        return dao;  
    }
	public static IStoreDAO getStore(){  
		IStoreDAO dao = null;  
        try{  
            dao = new StoreDAOProxy();      
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        return dao;  
    }
	public static IStorageDAO getStorage(){  
		IStorageDAO dao = null;  
        try{  
            dao = new StorageDAOProxy();      
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        return dao;  
    }
}
