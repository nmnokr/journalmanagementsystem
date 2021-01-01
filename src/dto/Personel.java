package dto;

import java.util.ArrayList;

public class Personel {
	private long sicilno;
	private String ad;
	private String soyad;
	private String email;
	private String password;
 
	private long departmentId;
 
	private ArrayList<String> personelRoles;
 
	public long getSicilno() {
		return sicilno;
	}

	public void setSicilno(long sicilno) {
		this.sicilno = sicilno;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

 

	public long getDepartment() {
		return departmentId;
	}

	public void setDepartment(long departmanId) {
		this.departmentId = departmanId;
	}

 

 

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getPersonelRoles() {
		return personelRoles;
	}

	public void setPersonelRoles(ArrayList<String> personelRoles) {
		this.personelRoles = personelRoles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Personel [sicilno=" + sicilno + ", ad=" + ad + ", soyad=" + soyad + ", email=" + email + ", password="
				+ password + ",   departman=" + departmentId + " , personelRoles=" + personelRoles + " ]";
	}

}
