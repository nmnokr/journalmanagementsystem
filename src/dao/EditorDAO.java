package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import bean.DatabaseProperties;
import dto.Article;
import dto.Editor;;

public class EditorDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(EditorDAO.class);

	DatabaseProperties databaseProperties = null;

	public void init(Properties appProperties) {
		logger.debug("PersonelDAO init metodu calismasi");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("PersonelDAO init metodu calismasi bitti.");
	}

	public void addEditor(Editor  editor) throws Exception {
		logger.debug("PersonelDAO addPersonel metodu calismasi");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO editor (  name ,surname ,active ) ");
			query.append("VALUES (  ?,?,0 )");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setString(1, "sss");
			stmt.setString(2, "okur");
		
			 
			stmt.executeUpdate();
			conn.commit();
			 
		} catch (Exception e) {
			logger.error("PersonelDAO addPersonel metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("PersonelDAO addPersonel metodu calismasi  bitti.");
		}
	}
	public ArrayList<Article> getAllWaitingArticle() throws Exception {
		logger.debug("EditorDao getAllWaitingArticle metodu basladi");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Article article;
		ArrayList<Article> articles = new ArrayList<>();
		try {
			String query = "SELECT * FROM article WHERE status=0 ";
			logger.debug(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
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
			    articles.add(article);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("EditorDao getAllWaitingArticle metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("EditorDao getAllWaitingArticle metodu calismasi bitti.");
		}
		  return articles;
	}
       
	   
	 
	}