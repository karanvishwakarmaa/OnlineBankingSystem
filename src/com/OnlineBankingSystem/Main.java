package com.OnlineBankingSystem;

import com.OnlineBankingSystem.Dao.AccountantDao;
import com.OnlineBankingSystem.Dao.AccountantDaoImplementation;
import com.OnlineBankingSystem.entity.Accountant;
import com.OnlineBankingSystem.entity.Customer;
import com.OnlineBankingSystem.exception.AccountantException;
import com.OnlineBankingSystem.exception.CustomerException;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws AccountantException, CustomerException {
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        while (f) {
            System.out.println("--------------------------------Welcome To Online Banking System-----------------------------");
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("1. ADMIN LOGIN PORTAL\r\n" + " 2. CUSTOMER LOGIN PORTAL\r\n");
            System.out.println("Choose Your Option ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("----------Admin Login Credentials----------Accountant");
                    System.out.println("Enter Username :  ");
                    String Username = sc.next();
                    System.out.println("Enter Password :  ");
                    String Password = sc.next();

                    AccountantDao ad = new AccountantDaoImplementation();
                    try {
                        Accountant a = ad.LoginAccountant(Username, Password);
                        if (a == null) {
                            System.out.println("Wrong Credentials");
                            break;
                        }
                        System.out.println("Login Successfully!...");
                        System.out.println("Welcome! To : " + a.getAccountantUsername() + " as Admin Of Online Banking System ");
                        boolean y = true;
                        while (y) {
                            System.out.println("---------------------------------------------------\r\n" + "1. Add New Customer \r\n"
                                    + "2. Update And edit Customer Address \r\n" +"3. Remove/Delete The Account By Account Number \r\n"
                              +"4. View Particular Account Details By Given Account Number \r\n"
                             +"5. View All Account/Customer List \r\n"
                            +"6. Account Logout\r\n");
                            int x = sc.nextInt();
                            if (x == 1) {
                                System.out.println("----------New Account----------");
                                System.out.println("Enter Customer Name : ");
                                String a1 = sc.next();
                                System.out.println("Enter Account Opening Balance");
                                int a2 = sc.nextInt();
                                System.out.println("Enter Customer Email : ");
                                String a3 = sc.next();
                                System.out.println("Enter Customer Password : ");
                                String a4 = sc.next();
                                System.out.println("Enter Customer Mobile Number : ");
                                String a5 = sc.next();
                                System.out.println("Enter Customer Address : ");
                                String a6 = sc.next();
                                int s1 = -1;
                                try {
                                    s1 = ad.addCustomer(a1, a3, a4, a5, a6);
                                    try {
                                        ad.addAccount(a2, s1);
                                    } catch (CustomerException e) {
                                        e.printStackTrace();
                                    }
                                } catch (CustomerException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("Customer Added Successfully");
                                System.out.println("---------------------------------------------");
                            }

                            if (x == 2) {
                                System.out.println("Update Customer Address.....");
                                System.out.println("Enter Customer Account Number : .....");
                                int u = sc.nextInt();
                                System.out.println("Enter New Address: ");
                                String u2 = sc.next();
                             try{
                                 String mes = ad.updateCustomer(u, u2);
                             } catch (CustomerException e) {
                                 e.printStackTrace();
                             }
                            }
                            if (x == 3) {
                                System.out.println("---------------Remove Account---------------");
                                System.out.println("Enter Account Number :   ");
                                int ac = sc.nextInt();
                                String s = null;
                                try{
                                   s = ad.deleteAccount(ac);
                                } catch (CustomerException e) {
                                    e.printStackTrace();
                                }
                                if(s!=null) {
                                    System.out.println(s);
                                }
                            }
                            if (x == 4) {
                                System.out.println("---------------Customers Details---------------");
                                System.out.println("Enter Customer Account Number :  ");
                                String ac = sc.next();
                                try{
                                    Customer cus = ad.viewCustomer(ac);
                                    if (cus!=null) {
                                        System.out.println("********************************");
                                        System.out.println("Account Number : "+cus.getCustomerAccountNumber());
                                        System.out.println("Name : "+cus.getCustomerName());
                                        System.out.println("Account Balance : "+cus.getCustomerBalance());
                                        System.out.println("Account Password : "+cus.getCustomerPassword());
                                        System.out.println("Account Email : "+cus.getCustomerEmail());
                                        System.out.println("Account Mobile Number : "+cus.getCustomerMobile());
                                        System.out.println("Address : "+cus.getCustomerAddress());
                                        System.out.println("-------------------------------------------------------------------------------------");
                                    } else {
                                        System.out.println("Account Does Not Exists......");
                                        System.out.println("-----------------------------------");
                                    }
                                } catch (CustomerException e) {
                                    e.printStackTrace();
                                }

                            }
                            if (x == 5) {
                                try{
                                    System.out.println("------------All Customer List--------------");
                                    Customer cus = ad.viewAllCustomer();
                                } catch (CustomerException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (x == 6) {
                                System.out.println("---------------------Account Logout Successfully-------------------------");
                                y = false;
                            }
                        }
                    } catch (Exception e) {

                    }

            }
        }
    }
}





