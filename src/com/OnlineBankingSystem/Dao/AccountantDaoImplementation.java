package com.OnlineBankingSystem.Dao;

import com.OnlineBankingSystem.databaseConnection.DatabaseConnection;
import com.OnlineBankingSystem.entity.Accountant;
import com.OnlineBankingSystem.entity.Customer;
import com.OnlineBankingSystem.exception.AccountantException;
import com.OnlineBankingSystem.exception.CustomerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountantDaoImplementation implements AccountantDao{
    @Override
    public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountantException {
        Accountant acc = null;
        try (Connection con = DatabaseConnection.provideConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from accountant where accountantUsername = ? and accountantPassword = ? ");
            ps.setString(1, accountantUsername);
            ps.setString(2, accountantPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String n = rs.getString("accountantUsername");
                String e = rs.getString("accountantEmail");
                String p = rs.getString("accountantPassword");

                acc = new Accountant(n, e, p);
            }
        } catch(SQLException e) {
            throw  new AccountantException("Invalid Username and Password");
        }
        return acc;
    }

    @Override
    public int addCustomer(String customerName, String customerEmail, String customerPassword, String customerMobile, String customerAddress) throws CustomerException {
        int cid = -1;
        try(Connection conn= DatabaseConnection.provideConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO customerinformation (customerName, customerEmail, customerPassword, customerMobile, customerAddress) values (?,?,?,?,?) ");
            ps.setString(1, customerName);
            ps.setString(2, customerEmail);
            ps.setString(3, customerPassword);
            ps.setString(4, customerMobile);
            ps.setString(5, customerAddress);
            int x = ps.executeUpdate();
if (x > 0) {
    PreparedStatement ps2 = conn.prepareStatement("SELECT cid FROM customerinformation WHERE customerEmail = ? AND customerMobile = ? ");
    ps2.setString(1, customerEmail);
    ps2.setString(2, customerMobile);

    ResultSet rs = ps2.executeQuery();
    if (rs.next()) {
        cid = rs.getInt("cid");
    } else {
        System.out.println("Inserted Data is Incorrect, Please try again");
    }
}
        } catch (SQLException e) {
            System.out.println("SQL Quary Related Error!!! " + e.getMessage());
        }
        return cid;
    }

    @Override
    public String addAccount(int customerBalance, int cid) throws CustomerException {
       String message = null;
       try(Connection conn = DatabaseConnection.provideConnection()) {
           PreparedStatement ps = conn.prepareStatement("INSERT INTO account (customerBalance, cid) VALUES (?,?) ");
           ps.setInt(1, customerBalance);
           ps.setInt(2, cid);
           int x = ps.executeUpdate();
           if (x > 0) {
               message = "Account Added Successfully...";
           } else {
              message = "Inserted Data Not Added Successfully...";
           }
       } catch (SQLException e) {
           System.out.println("SQL Related Exception: " + e.getMessage());
       }
        return message;
    }

    @Override
    public String updateCustomer(int customerAccountNumber, String customerAddress) throws CustomerException {
        String message = null;
        try(Connection conn = DatabaseConnection.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("update customerinformation i inner join account a on i.cid = a.cid and a.customerAccountNumber = ? set i.customerAddress = ?");
            ps.setInt(1, customerAccountNumber);
            ps.setString(2, customerAddress);
            int x = ps.executeUpdate();
            if(x>0) {
                System.out.println("Address Updated Successfully....");
            } else{
                System.out.println("Customer Updation is not Successfully!!!");
                System.out.println("--------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String deleteAccount(int customerAccountNumber) throws CustomerException {
      String message = null;
      try(Connection conn = DatabaseConnection.provideConnection()){
          PreparedStatement ps = conn.prepareStatement("delete i from customerinformation i inner join account a on i.cid = a.cid where a.customerAccountNumber = ?");
          ps.setInt(1, customerAccountNumber);
          int x= ps.executeUpdate();
          if (x>0) {
              System.out.println("Account Deleted Successfully");
          } else{
              System.out.println("Deletion Failed...........Account not Found");
              System.out.println("-------------------------------------------------");
          }

      } catch (SQLException e) {
          e.printStackTrace();
          message = e.getMessage();
      }
        return message;
    }

    @Override
    public Customer viewCustomer(String customerAccountNumber) throws CustomerException {
       Customer cu = null;
       try(Connection conn = DatabaseConnection.provideConnection()) {
           PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid where customerAccountNumber = ?");
           ps.setString(1, customerAccountNumber);
           ResultSet rs = ps.executeQuery();

           if (rs.next()) {
               int a = rs.getInt("customerAccountNumber");
               String n = rs.getString("customerName");
               int b = rs.getInt("customerBalance");
               String e = rs.getString("customerPassword");
               String p = rs.getString("customerEmail");
               String m = rs.getString("customerMobile");
               String v = rs.getString("customerAddress");
               cu = new Customer(a,n,b,e,p,m,v);
           }
           else{
               throw new CustomerException("Invalid Account Number...");
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }

        return cu;
    }

    @Override
    public Customer viewAllCustomer() throws CustomerException {
        Customer cu = null;
        try(Connection conn = DatabaseConnection.provideConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from customerinformation i ineer join account a on a.cid = i.cid");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int a = rs.getInt("customerAccountNumber");
                String n = rs.getString("customerName");
                int b = rs.getInt("customerBalance");
                String e = rs.getString("customerPassword");
                String p = rs.getString("customerEmail");
                String m = rs.getString("customerMobile");
                String v = rs.getString("customerAddress");
                System.out.println("**********************************");
                System.out.println("Account Number :  " + a);
                System.out.println("Customer Name :  "+ n);
                System.out.println("Customer Balance :  " + b);
                System.out.println("Customer Password :  " + p);
                System.out.println("Customer Email :  " + e);
                System.out.println("Customer Mobile Number :  "+ m);
                System.out.println("Customer Address :  " + v);
                System.out.println("**********************************************");
                cu = new Customer(a,n,b,p,e,m,v);
            }


        } catch (SQLException e) {
            throw new CustomerException("Invalid Account Number!!!!");

        }
        return cu;
    }


}
