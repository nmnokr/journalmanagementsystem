package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import bean.DatabaseProperties;
import dto.Article;
import dto.Author;
import dto.Editor;

import dto.Personel;

public class ArticleDao extends DatabaseHelper {
	Logger logger = Logger.getLogger(EditorDAO.class);

	DatabaseProperties databaseProperties = null;

	public void init(Properties appProperties) {
		logger.debug("ArticleDao init metodu calismasi");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("ArticleDao init metodu calismasi bitti.");
	}

	public void addarticle(Article article) throws Exception {
		logger.debug("ArticleDao addarticle metodu basladi");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {

			query.append("	INSERT INTO article (  nace ,title   ,article ,aid ,ok )");
			query.append("VALUES (  ?,?,?,?,0)");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);

			stmt.setInt(1, (int) article.getNace());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getArticle());
			stmt.setLong(4, article.getAid());
			stmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			logger.error("ArticleDao addarticle metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("ArticleDao addarticl metodu calismasi  bitti.");
		}
	}

	public ArrayList<Article> getAllArticle(Long Aid) throws Exception {
		logger.debug("ArticleDao getAllArticle metodu basladi");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Article article;

		ArrayList<Article> articles = new ArrayList<>();
		try {
			String query = "SELECT * FROM article where aid=? ";
			logger.debug(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			 preparedStatement.setLong(1, Aid);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				article = new Article();

				article.setArid(rs.getLong(1));
				System.out.println(rs.getLong(1));
				article.setNace(rs.getLong(2));
				article.setTitle(rs.getString(3));
				article.setAid(rs.getLong(4));
				article.setEid(rs.getLong(5));
				article.setArticle(rs.getString(6));
				article.setStatus(rs.getShort(7));
				article.setOk(rs.getShort(8));
				article.setComment(rs.getString(9));
				articles.add(article);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("ArticleDao getAllArticle metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("ArticleDao getAllArticle metodu calismasi bitti.");
		}
		return articles;
	}

	// yazar aid yazar numarasina göre sýralama
	public ArrayList<Article> searchMakaleaid(Long Id) throws Exception {
		logger.debug("ArticleDao searchMakaleaid metodu çalýþmaya baþladý.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Article> articles2 = new ArrayList<>();
		Article article22;
		Connection conn = (Connection) getConnection();
		String query = "SELECT * FROM article WHERE aid=?";

		pst = (PreparedStatement) conn.prepareStatement(query);
		pst.setLong(1, Id);

		rs = pst.executeQuery();
		try {

			while (rs.next()) {
				article22 = new Article();
				article22.setArid(rs.getLong(1));
				article22.setNace(rs.getLong(2));
				article22.setTitle(rs.getString(3));
				article22.setAid(rs.getLong(4));
				article22.setEid(rs.getLong(5));
				article22.setArticle(rs.getString(6));
				article22.setStatus(rs.getShort(7));
				article22.setOk(rs.getShort(8));
				article22.setComment(rs.getString(9));
				articles2.add(article22);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("searchMakaleaid   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("  searchMakale metodu çalýþmasý bitti.");
		}
		return articles2;
	}

	public Article searchMakaleArid(Long Arid) throws Exception {
		logger.debug("ArticleDao searchMakaleArid metodu çalýþmaya baþladý.");
		PreparedStatement pst = null;
		ResultSet rs = null;

		Article article22 = new Article();
		Connection conn = (Connection) getConnection();
		String query = "SELECT * FROM article WHERE arid=?";

		pst = (PreparedStatement) conn.prepareStatement(query);
		pst.setLong(1, Arid);

		rs = pst.executeQuery();
		try {

			while (rs.next()) {

				article22.setArid(rs.getLong(1));
				article22.setNace(rs.getLong(2));
				article22.setTitle(rs.getString(3));
				article22.setAid(rs.getLong(4));
				article22.setEid(rs.getLong(5));
				article22.setArticle(rs.getString(6));

				article22.setStatus(rs.getShort(7));
				article22.setOk(rs.getShort(8));
				article22.setComment(rs.getString(9));

			}
			conn.commit();
		} catch (Exception e) {
			logger.error("searchMakaleArid   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("  searchMakaleArid metodu çalýþmasý bitti.");
		}
		return article22;
	}
	public  ArrayList<Article>  Getallrefereesapproval(Long aid) throws Exception {
		logger.debug("ArticleDao Getallrefereesapproval metodu çalýþmaya baþladý.");
		PreparedStatement pst = null;
		ResultSet rs = null;

		Article article22 ;
		ArrayList<Article> articles = new ArrayList<>();
		Connection conn = (Connection) getConnection();
		String query = "SELECT * FROM article WHERE referee1=?  AND status=1";

		pst = (PreparedStatement) conn.prepareStatement(query);
		pst.setLong(1, aid);

		rs = pst.executeQuery();
		try {

			while (rs.next()) {
				article22=new Article();
				article22.setArid(rs.getLong(1));
				article22.setNace(rs.getLong(2));
				article22.setTitle(rs.getString(3));
				article22.setAid(rs.getLong(4));
				article22.setEid(rs.getLong(5));
				article22.setArticle(rs.getString(6));
                article22.setStatus(rs.getShort(7));
				article22.setOk(rs.getShort(8));
				article22.setComment(rs.getString(9));
				articles.add(article22);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("Getallrefereesapproval   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("  Getallrefereesapproval metodu çalýþmasý bitti.");
		}
		return articles;
	}
	public List<Author> searchRefereeArid(Long nace) throws Exception {
		logger.debug("ArticleDao searchRefereeArid metodu çalýþmaya baþladý.");
		PreparedStatement pst = null;
		ResultSet rs = null;
		System.out.println("<<<" + nace);
		ArrayList<Author> author22 = new ArrayList<>();

		Author author;
		Connection conn = (Connection) getConnection();
		String query = "SELECT author.aid,name,surname FROM author  INNER JOIN    article ON  author.nace=?  WHERE author.refereestatus=1   ORDER BY RAND()  LIMIT 3\r\n"
				+ " ";
		logger.debug("A...." + query);
		pst = (PreparedStatement) conn.prepareStatement(query);
		pst.setLong(1, nace);
		rs = pst.executeQuery();
		try {

			while (rs.next()) {
				System.out.println(":>:>" + rs.getLong(1));
				author = new Author();
				author.setAid(rs.getLong(1));
				author.setName(rs.getString(2));
				author.setSurname(rs.getString(3));
				author.setNace(nace);
				author22.add(author);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("searchRefereeArid   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("  searchRefereeArid metodu çalýþmasý bitti.");
		}
		return author22;
	}

	public void onayMakaleEditor(Article article) throws Exception {
		logger.debug("ArticleDao confirmedPermissionFirstManager metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = " UPDATE article SET  status =1  ,comment = ? ,referee1 = ? ,referee2 = ? ,referee3 = ? 		 WHERE arid = ? ";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, article.getComment());
			preparedStatement.setLong(2, article.getReferes().get(0));
			preparedStatement.setLong(3, article.getReferes().get(1));
			preparedStatement.setLong(4, article.getReferes().get(2));
			preparedStatement.setLong(5, article.getArid());
			preparedStatement.executeUpdate();
			System.out.println();
		 
			conn.commit();
		} catch (Exception e) {
			logger.error("onayMakaleEditor   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("onayMakaleEditor   metodu çalýþmasý bitti.");
		}
	}

	public void redMakaleEditor(Article article) throws Exception {
		logger.debug("ArticleDao redMakaleEditor metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "UPDATE article SET status =2 , comment = ? WHERE arid =?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, article.getComment());
			preparedStatement.setLong(2, article.getArid());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("redMakaleEditor   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("redMakaleEditor   metodu çalýþmasý bitti.");
		}
	}

}