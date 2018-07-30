package com.cg.WalletApp.Exception;

public interface IBankException {

	String nameException ="Enter Valid Name";
	String mobileNumberException = "Enter Valid Mobile Number ";
	String passwordException ="Enter Valid Password";
	String emailIdException = " enter valid email id";
	String invalidDetails = "Given details are incorrect ";
	String insufficientFunds = "Insufficient account balance";
	String ACCOUNTEXISTS = "Account already exists with the given mobile number";
	String sqlException = "Cannot connect to database";
	String mobileNumberNotExists = "Account doesnt exists with given mobile number";
	String transFailed = "Network issue! Please try again";
}
