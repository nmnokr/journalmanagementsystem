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
public class RefereeDao extends DatabaseHelper {

 

 
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

	public void onayMakaleHakem(Long Aid) throws Exception {
		logger.debug("ArticleDao onayMakaleHakem metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = " UPDATE article SET  status = 3 ,ok=1  WHERE referee1 = ? ";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			 
			preparedStatement.setLong(1,Aid);
	 
	 
			preparedStatement.executeUpdate();
			System.out.println();
		 
			conn.commit();
		} catch (Exception e) {
			logger.error("onayMakaleHakem   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("onayMakaleHakem   metodu çalýþmasý bitti.");
		}
	}

	public void redMakaleHakem(Long Aid) throws Exception {
		logger.debug("ArticleDao redMakaleEditor metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = " UPDATE article SET  status = 4 ,ok=0  WHERE referee1 = ? ";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setLong(1, Aid);
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