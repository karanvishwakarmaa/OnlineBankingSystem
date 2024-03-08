package com.OnlineBankingSystem.Dao;

import com.OnlineBankingSystem.entity.Accountant;
import com.OnlineBankingSystem.entity.Customer;
import com.OnlineBankingSystem.exception.AccountantException;
import com.OnlineBankingSystem.exception.CustomerException;

public interface AccountantDao {
    public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountantException;

    public int addCustomer(String customerName , String customerEmail , String customerPassword , String customerMobile , String customerAddress) throws CustomerException;

    public String addAccount(int customerBalance, int cid) throws CustomerException;
 public String updateCustomer(int customerAccountNumber , String customerAddress) throws CustomerException;
 public String deleteAccount(int customerAccountNumber) throws CustomerException;
public Customer viewCustomer(String customerAccountNumber) throws CustomerException;
public Customer viewAllCustomer() throws CustomerException;
}
