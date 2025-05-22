/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static Connection connect ;
	@SuppressWarnings("deprecation")
	public static Connection  GetConnect()  {
		try {
		//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		  return DriverManager.getConnection("jdbc:mysql://localhost:3306/ayacoffee","root","");
		  
       // Statement statement=con.createStatement();
       
		}catch(Exception e){
			e.printStackTrace();
		}
		 return null;
	}
}
