package com.mina.object;


/**
 * 
 * @author lenovo
 *
 */
public class User2 implements java.io.Serializable{
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String companyName;
	private String userName;
	private String password;

}
