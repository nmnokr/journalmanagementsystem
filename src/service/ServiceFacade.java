package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import dao.ArticleDao;
import dao.EditorDAO;
import dao.PersonelDAO;
import dao.RefereeDao;
import dao.RfidDao;
import dto.Article;
import dto.Author;
import dto.Editor;

import dto.Personel;
import dto.Rfid;

public class ServiceFacade {
	private static ServiceFacade serviceFacade;

	final static Logger logger = Logger.getLogger(ServiceFacade.class);

	private EditorDAO editorDAO = null;
	private PersonelDAO personelDAO = null;
	private ArticleDao articleDao = null;
	private RfidDao rfidDao = null;
	private RefereeDao refereeDao  = null;
	private ServiceFacade() {
		logger.info("ServiceFacade nesnesi oluï¿½turuldu.");
	}

	public static ServiceFacade getInstance() {
		logger.info("ServiceFacade getInstance  metodu calismaya basladi");
		if (serviceFacade == null) {
			serviceFacade = new ServiceFacade();
		}
		return serviceFacade;
	}

	public void initialize(Properties appProperties) throws Exception {
		logger.info("ServiceFacade initialize  metodu calismaya basladi");
		editorDAO = new EditorDAO();
		personelDAO = new PersonelDAO();
		articleDao = new ArticleDao();
		rfidDao = new RfidDao();
		refereeDao=new RefereeDao();
		personelDAO.init(appProperties);
		editorDAO.init(appProperties);
		articleDao.init(appProperties);
		rfidDao.init(appProperties);
		refereeDao.init(appProperties);
		logger.debug("ServiceFacade initialize metodu  calismaya basladi");
	}

	public void shutdown() {
		logger.info("ServiceFacade shutdown  metodu  calismaya basladi.");
	}

	public void addEditor(Editor editor) throws Exception {
		editorDAO.addEditor(editor);
	}

	public Personel getPersonelDetailWithEmail(String email) throws Exception {
		return personelDAO.getPersonelDetailWithEmail(email);
	}

	public void addMakale(Article article) throws Exception {
		articleDao.addarticle(article);
	}

	public ArrayList<Article> getAllArticle(Long Aid) throws Exception {
		return articleDao.getAllArticle(Aid);
	}


	public ArrayList<Article> Getallrefereesapproval(Long Aid) throws Exception {
		return articleDao.Getallrefereesapproval(Aid);
	}

	  
	public ArrayList<Article> searchMakaleaid(Long id) throws Exception {
		return articleDao.searchMakaleaid(id);
	}

	public Article searchMakaleArid(Long Arid) throws Exception {
		return articleDao.searchMakaleArid(Arid);
	}

	public List<Author> searchRefereeArid(Long nace) throws Exception {
		return articleDao.searchRefereeArid(nace);
	}
	
	
	public ArrayList<Article> getAllWaitingArticle() throws Exception {
		return editorDAO.getAllWaitingArticle();
	}

	public void onayMakaleEditor(Article article) throws Exception {
		articleDao.onayMakaleEditor(article);
	}

	public void redMakaleEditor(Article article) throws Exception {
		articleDao.redMakaleEditor(article);
	}

	public void onayMakaleHakem(Long Aid) throws Exception {
		refereeDao.onayMakaleHakem(Aid);
	}

	public void redMakaleHakem(Long Aid) throws Exception {
		refereeDao.redMakaleHakem(Aid);
	}
	/////////////// rfid günceleme
	public void UpdateRfid(Rfid rfid) throws Exception {
		rfidDao.UpdateRfid(rfid);
	}

	/////////////// rfid ekleme
	public ArrayList<Rfid> GetAllRfid() throws Exception {
		return rfidDao.GetAllRfid();
	}

	public void addRfid(Rfid rfid) throws Exception {
		rfidDao.addRfid(rfid);
	}
}
