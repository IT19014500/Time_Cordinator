package IT19216188;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class MyConnection {
    
  public static Connection c = null;
            
            
	public static Connection setConnection() throws Exception{
            
	Class.forName("com.mysql.jdbc.Driver");
	c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltdmgdb?zeroDateTimeBehavior=convertToNull","root","");
        
          return c;
	}
         
    public static void iud(String sql)throws Exception{
		if(c==null) {
			setConnection();
		}
		c.createStatement().executeUpdate(sql);
	}
    
    public static ResultSet search(String sql)throws Exception{
		if(c==null) {
			setConnection();
		}
		
		return c.createStatement().executeQuery(sql);
		
	}
    
}
