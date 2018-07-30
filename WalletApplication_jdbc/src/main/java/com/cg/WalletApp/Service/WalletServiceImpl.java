package com.cg.WalletApp.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.WalletApp.Exception.BankException;
import com.cg.WalletApp.Exception.IBankException;
import com.cg.WalletApp.bean.Customer;
import com.cg.WalletApp.dao.IWalletDao;
import com.cg.WalletApp.dao.WalletDaoImpl;

public class WalletServiceImpl implements IWalletService {
	static IWalletDao iWalletDao = null;

	static {

		iWalletDao = new WalletDaoImpl();
	}

	public void checkName(String name) throws BankException {
		Pattern pattern = Pattern.compile("[a-zA-Z]{1,}");
		Matcher matcher = pattern.matcher(name);
		if (!(matcher.matches())) {
			throw new BankException(IBankException.nameException);
		}
	}

	public void checkMobileNumber(String mobileNumber) throws BankException {

		Pattern pattern = Pattern.compile("[7-9]{1}[0-9]{9}");
		Matcher matcher = pattern.matcher(mobileNumber);
		if (!(matcher.matches())) {
			throw new BankException(IBankException.mobileNumberException);
		}
	}

	public void checkPassword(String password) throws BankException {
		Pattern pattern = Pattern.compile(".*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*" + "");
		Matcher matcher = pattern.matcher(password);
		if (!(matcher.matches())) {
			throw new BankException(IBankException.passwordException);
		}

	}

	public void checkEmail(String emailId) throws BankException {
		Pattern pattern = Pattern.compile("[a-z]{1}[a-z0-9._]{1,}@[a-zA-Z0-9]{1,}.com");
		Matcher matcher = pattern.matcher(emailId);
		if (!(matcher.matches())) {
			throw new BankException(IBankException.emailIdException);
		}

	}

	public String addCustomer(Customer customer) throws BankException {
		String result = null;
		result = iWalletDao.addCustomer(customer);
		if(result==null)
		 throw new BankException(IBankException.ACCOUNTEXISTS);
		 
		return result;
	}

	public Customer showBalance(String mobileNum, String password) throws BankException, SQLException {
		Customer result = null;
		result = iWalletDao.showBalance(mobileNum, password);

		if (result == null)
			throw new BankException(IBankException.invalidDetails);

		return result;
	}

	public Customer check(String mobileNum, String password) throws BankException, SQLException {
		Customer result = null;
		result = iWalletDao.findCustomer(mobileNum, password);

		if (result == null)
			throw new BankException(IBankException.invalidDetails);
		return result;
	}

	public void deposit(Customer customer, BigDecimal amount)
			throws ClassNotFoundException, SQLException, BankException {
	
		iWalletDao.deposit(customer, amount);
	

	}

	public boolean withDraw(Customer customer, BigDecimal amount)
			throws BankException, ClassNotFoundException, SQLException {
		boolean result = false;
		
		result = iWalletDao.withdraw(customer, amount);
		
		if (result == false) {
			throw new BankException(IBankException.insufficientFunds);
		}
		return result;
	}

	public boolean isFound(String receiverMobile) throws BankException, SQLException {
		boolean result = false;
		
		if (iWalletDao.customerExists(receiverMobile)) {
			result = true;
		}
		
		if (result == false)
			throw new BankException(IBankException.mobileNumberNotExists);

		return result;
	}

	public Customer transfer(String senderMobile, String receiverMobile, BigDecimal amount)
			throws InterruptedException, BankException, ClassNotFoundException, SQLException {
		
		Customer result=null;
	
	
		result=iWalletDao.transfer(senderMobile,receiverMobile,amount);
		
		return result;
	}

	public String printTransactions(Customer customer) throws ClassNotFoundException, SQLException {
		
		
	    String builder = iWalletDao.printTransactions(customer);
	
		return builder;
	}



}
