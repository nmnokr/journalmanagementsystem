package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import bean.DatabaseProperties;
import dto.Article;
import dto.Editor;
import dto.Rfid;
 

public class RfidDao extends DatabaseHelper {
	Logger logger = Logger.getLogger(PersonelDAO.class);

	DatabaseProperties databaseProperties = null;

	public void init(Properties appProperties) {
		logger.debug("RfidDao init metodu calismasi basladi");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("RfidDao init metodu calismasi bitti.");
	}
	public void UpdateRfid(Rfid rfid) throws Exception {
		logger.debug("RfidDao UpdateRfid metodu çalýþmaya baþladý.");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = " UPDATE rfid set durum= CASE WHEN durum=1  THEN 0  ELSE 1  END  WHERE rfidno = ?";
			logger.trace(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setLong(1, rfid.getRfidno());
		 
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			logger.error("UpdateRfid   metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("UpdateRfid   metodu çalýþmasý bitti.");
		}
	}
	public ArrayList<Rfid> GetAllRfid() throws Exception {
		logger.debug("RfidDao GetAllRfid metodu basladi");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Rfid rfid;
		ArrayList<Rfid> rfids = new ArrayList<>();
		try {
			String query = "SELECT * FROM  rfid";
			logger.debug(query.toString());
			conn = getConnection();
			preparedStatement = (PreparedStatement) conn.prepareStatement(query.toString());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				rfid = new Rfid();
			 
				rfid.setRfidno(rs.getLong(1));
				rfid.setDepartman(rs.getString(3));
			    rfid.setDurum(rs.getInt(6));
			    rfids.add(rfid);
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("RfidDao GetAllRfid metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(conn);
			logger.debug("RfidDao GetAllRfid metodu calismasi bitti.");
		}
		return rfids;
	}
	
	public void addRfid(Rfid  rfid) throws Exception {
		logger.debug("RfidDao addRfid metodu calismasi");
		Connection conn = (Connection) getConnection();
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append(
					"INSERT INTO rfid ( rfidno ,ad ,departman  ,songiris ,sonsaat ,durum )  ");
			query.append("VALUES ( ?,'ss' ,'222' ,NOW() ,NOW() ,0 )");
			String queryString = query.toString();
			logger.trace(query.toString());
			stmt = (PreparedStatement) conn.prepareStatement(queryString);
			stmt.setLong(1, rfid.getRfidno());
		/*	stmt.setString(2, rfid.getAd());
			stmt.setString(3, rfid.getDepartman());*/
			 
			stmt.executeUpdate();
			conn.commit();
			 
		} catch (Exception e) {
			logger.error("RfidDao addRfid metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closePreparedStatement(stmt);
			closeConnection(conn);
			logger.debug("RfidDao addRfid metodu calismasi  bitti.");
		}
	}
}