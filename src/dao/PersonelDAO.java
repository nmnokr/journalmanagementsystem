package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import bean.DatabaseProperties;
import dto.Personel;

public class PersonelDAO extends DatabaseHelper {
	Logger logger = Logger.getLogger(PersonelDAO.class);

	DatabaseProperties databaseProperties = null;

	public void init(Properties appProperties) {
		logger.debug("PersonelDAO init metodu �al��maya ba�lad�.");
		DatabaseProperties databaseProperties = new DatabaseProperties();
		databaseProperties.setUsername(appProperties.getProperty("dbuser"));
		databaseProperties.setPassword(appProperties.getProperty("dbpassword"));
		databaseProperties.setDatabaseConnectionURL(appProperties.getProperty("database"));
		databaseProperties.setDatabaseDriver(appProperties.getProperty("databaseDriver"));
		databaseProperties.setJndiName(appProperties.getProperty("jndiName"));
		databaseProperties.setDataSource(Boolean.parseBoolean(appProperties.getProperty("isDataSource")));
		super.init(databaseProperties);
		logger.debug("PersonelDAO init metodu �al��mas� bitti.");
	}

	public Personel getPersonelDetailWithEmail(String email) throws Exception {
		logger.debug("PersonelDAO getPersonelDetailWithEmail metodu �al��maya ba�lad�.");
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		Connection conn = getConnection();
		ResultSet rs = null;
		ResultSet rs2 = null;
		Personel personel = new Personel();
		String query = "SELECT * FROM  personel WHERE  EMAIL =?";
		String query2 = "SELECT role FROM  personel_roles WHERE  EMAIL =?";
		logger.trace(query);
		try {
			pst = (PreparedStatement) conn.prepareStatement(query);
			pst2 = (PreparedStatement) conn.prepareStatement(query2);
			pst.setString(1, email);
			pst2.setString(1, email);
			rs = pst.executeQuery();
			rs2 = pst2.executeQuery();
			if (rs.next()) {
				personel.setAd(rs.getString("NAME"));
				personel.setSoyad(rs.getString("SURNAME"));
				personel.setSicilno(rs.getLong("SICILNO"));
				personel.setDepartment(rs.getLong("DEPARTMENT"));
				personel.setEmail(rs.getString("EMAIL"));
				personel.setPassword(rs.getString("PASSWORD"));
			  
			}
			ArrayList<String> personelRoles = new ArrayList<>();
			while(rs2.next()) {
				personelRoles.add(rs2.getString("ROLE"));
			}
			personel.setPersonelRoles(personelRoles);
			conn.commit();
		} catch (Exception e) {
			logger.error("PersonelDAO getPersonelDetailWithEmail metodu exeption = " + e);
			conn.rollback();
			throw e;
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pst);
			closeConnection(conn);
			logger.debug("PersonelDAO getPersonelDetailWithEmail metodu �al��mas� bitti.");
		}
		return personel;
	}
}