package helper.sqlDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.productEntities.Company;
import entities.productEntities.Category;
import entities.productEntities.Product;
import java.util.Date;

public class SqlProductsHandler {
	private ReadQuery database;
	
	public SqlProductsHandler() {
		this.database = new ReadQuery();
	}
	
	public ArrayList<Product> getDatabase(){
		 ArrayList<Product> listaProduse = new ArrayList<Product>();
		 
		this.database = new ReadQuery();
		ResultSet rs;
		final String interogation = "SELECT P.ProductName, P.Price, S.Quantity, C.CompanyName, D.CategoryName , D.CategoryId , C.CompanyAddress , S.ExpDate FROM Product AS P, Category AS D ,Sell AS S, Company AS C WHERE D.CategoryId=S.CategoryId AND S.ProductName=P.ProductName AND C.CompanyName = D.CompanyName GROUP BY P.ProductName, C.CompanyName, P.Price,S.Quantity, C.CompanyName,C.CompanyAddress,D.CategoryName;";
		database.prepareStatement(interogation);
		database.executeQuery();
		rs = database.getResult();
		try {
			while(rs.next()){
				Company i = new Company(rs.getString("CompanyName"),rs.getString("CompanyAddress"));
				Category d = new Category(rs.getInt("CategoryId"),rs.getString("CategoryName"),i);
				Product p = new Product(rs.getString("ProductName"),rs.getFloat("Price"), rs.getInt("Quantity"), d , (Date)rs.getObject("ExpDate"));
				listaProduse.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		return listaProduse;
	}
	public void addProdToDatabase(Product p){
		    ResultSet rs;
			int countComp = 10;
			int countCat = 10;
			int countProd = 10;
			/*
			final String checkProd = "SELECT COUNT(P.ProductName) AS Count FROM Product AS P WHERE P.ProductName = :p.getProdName();";
			final String checkDep = "SELECT COUNT(D.DepartmentNumber) AS Count FROM Department AS D WHERE D.DepartmentNumber = :p.getDep().getDepartmentNumber();";
			final String checkComp = "SELECT COUNT(C.CompanyName) AS Count FROM Company AS C WHERE C.CompanyName = :p.getDep().getCompany().getCompanyName()";
			
			*/
			//check item num
			final String checkProd = "SELECT COUNT(P.ProductName) AS Count FROM Product AS P WHERE P.ProductName = ?;";
			final String checkDep = "SELECT COUNT(D.CategoryId ) AS Count FROM Category AS D WHERE D.CategoryId = ?;";
			final String checkComp = "SELECT COUNT(C.CompanyName) AS Count FROM Company AS C WHERE C.CompanyName = ?;";
			
			database.prepareStatement(checkProd);
			database.setString(1, p.getProdName());
			database.executeQuery();
			rs = database.getResult();
			try {
				while(rs.next())
					countProd = rs.getInt("Count");
			} catch (SQLException e) {	
				System.out.println(e.getMessage());
			}
			
			database.prepareStatement(checkDep);
			database.setInteger(1, p.getCategory().getCategoryId());
			database.executeQuery();
			rs = database.getResult();
			try {
				while(rs.next())
					countCat = rs.getInt("Count");
			} catch (SQLException e) {	
				System.out.println(e.getMessage());
			}
			
			database.prepareStatement(checkComp);
			database.setString(1, p.getCategory().getCompany().getCompanyName());
			database.executeQuery();
			rs = database.getResult();
			try {
				while(rs.next())
					countComp  = rs.getInt("Count");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			final String interCompany = "INSERT INTO Company VALUES (?,?);";
			final String interDepartment = "INSERT INTO Category VALUES (?,?,?);";
			final String interProd = "INSERT INTO Product VALUES (?,?);";
			final String interSell = "INSERT INTO Sell VALUES (?,?,?,?);";
			
			if(countComp == 0){
				database.prepareStatement(interCompany);
				database.setString(1,p.getCategory().getCompany().getCompanyName());
				database.setString(2,p.getCategory().getCompany().getCompanyAddress());
				database.executeUpdate();
			}
			if(countCat == 0){
				database.prepareStatement(interDepartment);
				database.setInteger(1,p.getCategory().getCategoryId());
				database.setString(2,p.getCategory().getCategoryName());
				database.setString(3,p.getCategory().getCompany().getCompanyName());
				database.executeUpdate();
			}
			if(countProd == 0){
				database.prepareStatement(interProd);
				database.setString(1,p.getProdName());
				database.setFloat(2,p.getProdPrice());
				database.executeUpdate();
			}
			
			database.prepareStatement(interSell);
			database.setInteger(1,p.getCategory().getCategoryId());
			database.setString(2,p.getProdName());
			database.setInteger(3,p.getQuantity());
			database.setDate(4,p.getExpDate());
			database.executeUpdate();
			
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	}
	public void delProdFromDatabase(Product p){
		ResultSet rs;
		int countComp = 10;
		int countCat = 10;
		int countProd = 10;
		
		//check item num
		final String checkProd = "SELECT COUNT(S.ProductName) AS Count FROM Sell AS S WHERE S.ProductName = ?;";
		final String checkDep = "SELECT COUNT(S.CategoryId) AS Count FROM Sell AS S WHERE S.CategoryId = ?;";
		final String checkComp = "SELECT COUNT(D.CompanyName) AS Count FROM Category AS D WHERE D.CategoryName = ?;";
		
		database.prepareStatement(checkProd);
		database.setString(1,p.getProdName());
		database.executeQuery();
		rs = database.getResult();
		try {
			while(rs.next())
				countProd = rs.getInt(1);
		} catch (SQLException e) {	
			System.out.println(e.getMessage());
		}
		
		database.prepareStatement(checkDep);
		database.setInteger(1, p.getCategory().getCategoryId());
		database.executeQuery();
		rs = database.getResult();
		try {
			while(rs.next())
				countCat = rs.getInt("Count");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		database.prepareStatement(checkComp);
		database.setString(1, p.getCategory().getCompany().getCompanyName());
		database.executeQuery();
		rs = database.getResult();
		
		try {
			while(rs.next())
				countComp  =countCat*rs.getInt("Count");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		//delete elem
		final String interComp = "DELETE FROM Company WHERE CompanyName = ?;";
		final String interDepartment = "DELETE FROM Category WHERE CategoryId = ?;";
		final String interProduct = "DELETE FROM Product WHERE ProductName = ?";
		final String interSell = "DELETE FROM Sell WHERE CategoryId = ? AND ProductName = ? AND Quantity = ?;";
		
		database.prepareStatement(interSell);
		database.setInteger(1,p.getCategory().getCategoryId());
		database.setString(2,p.getProdName());
		database.setInteger(3,p.getQuantity());
		database.executeUpdate();
			
		if ( countProd == 1){
			database.prepareStatement(interProduct);
			database.setString(1,p.getProdName());
			database.executeUpdate();
		}		
		if ( countCat == 1){
			database.prepareStatement(interDepartment);
			database.setInteger(1,p.getCategory().getCategoryId());
			database.executeUpdate();
		}
		if ( countComp == 1){
			database.prepareStatement(interComp);
			database.setString(1,p.getCategory().getCompany().getCompanyName());
			database.executeUpdate();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void closeDatabase(){
		database.closeDatabase();
	}
}
