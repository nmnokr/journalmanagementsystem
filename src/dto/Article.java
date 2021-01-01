package dto;

import java.util.List;

public class Article {
	private long arid;

	private long nace;
	private String title;
	private long aid;
	private long eid;
	private String article;
	private int status;
	private int ok;
	private String comment;

	private List<Integer> referes;

	public List<Integer> getReferes() {
		return referes;
	}

	public void setReferes(List<Integer> referes) {
		this.referes = referes;
	}

	public String getComment() {
		return comment;
	}

	public long getNace() {
		return nace;
	}

	public void setNace(long nace) {
		this.nace = nace;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getArid() {
		return arid;
	}

	public void setArid(long arid) {
		this.arid = arid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOk() {
		return ok;
	}

	public void setOk(int ok) {
		this.ok = ok;
	}

}
