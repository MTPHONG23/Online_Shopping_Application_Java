
package Controller;

import Database.ConnectDB;
import Model.Bill;
import Model.Category;
import Model.Product;
import Model.User;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BillDao {
    private ConnectDB dB ;
    private Category category;
    private Connection connection;
    private Bill bill;
    public BillDao() {
        dB=new ConnectDB();
        this.connection=dB.GetConnect();
    }
    public boolean insertBill(Bill bill) throws FileNotFoundException {
        try {
            String sql = "insert into bill(time,date,total,status) values(?, ?,?,?)";
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, bill.getTime());
            statement.setString(2, bill.getDate());
            statement.setFloat(3, bill.getTotal());
            statement.setString(4, bill.getDate());
            statement.setString(4, bill.getStatus());
            statement.execute();
            return  true;
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return false;
    }
     public int getBillMax() throws SQLException {
        String sql="select MAX(id) as maxId from bill";
        ResultSet rs=connection.createStatement().executeQuery(sql);
        if(rs.next()){
           return rs.getInt("maxId");
        }
     return 0;
 }
       public ArrayList<Bill>getAllRows()throws Exception{
        ArrayList<Bill> lst=new ArrayList<Bill>();
        String sql="select * from bill";
        ResultSet rs=connection.createStatement().executeQuery(sql);
           
        while(rs.next()){
          
            bill=new Bill(rs.getInt("id"),
                    rs.getString("time"),
                    rs.getString("date"),
                    rs.getFloat("total"),
                    rs.getString("status")
                   );
            lst.add(bill);
        }
        return lst;
 }
       public boolean updateStatus(int billId, String newStatus) throws SQLException {
    	    String sql = "UPDATE bill SET status = ? WHERE id = ?";
    	    PreparedStatement ps = connection.prepareStatement(sql);
    	    ps.setString(1, newStatus);
    	    ps.setInt(2, billId);
    	    int rows = ps.executeUpdate();
    	    ps.close();
    	    return rows > 0;
    	}

       
       public ArrayList<Bill>getAllRowsDate(String date)throws Exception{
        ArrayList<Bill> lst=new ArrayList<Bill>();
        String sql="select * from bill WHERE  date=?";
         PreparedStatement stmt=connection.prepareStatement(sql);
           stmt.setString(1,date);
        try(ResultSet rs=stmt.executeQuery();) {
            while (rs.next()) {
                bill=new Bill(rs.getInt("id"),rs.getString("time"),rs.getString("date"),rs.getFloat("total"),rs.getString("status"));
                 lst.add(bill);
            }
        } catch (Exception e) {
        }
       
     return lst;
 }
}