package dto;

public class Author {
	 private Long aid ;
	  private String name;  
	  private String  surname; 
	  private String EMAIL; 
	  private String PASSWORD ; 
	  private int authoractive;  
	  private int refereestatus; 
	  private Long nace ;
	  
	  public Long getNace() {
		return nace;
	}
	  
	  public void setNace(Long nace) {
		this.nace = nace;
	}
	public Long getAid() {
		return aid;
	}
	public void setAid(Long aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public int getAuthoractive() {
		return authoractive;
	}
	public void setAuthoractive(int authoractive) {
		this.authoractive = authoractive;
	}
	public int getRefereestatus() {
		return refereestatus;
	}
	public void setRefereestatus(int refereestatus) {
		this.refereestatus = refereestatus;
	}
 
	  
	  
	  
}
