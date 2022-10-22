package com.schoolManagement.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionImplementation implements SessionInterface {

	private UESQL ue_sql;
	private SessionSQL session_sql;
	private ClasseSQL cl_sql;

	public void initDatabase() throws ClassNotFoundException {
		this.ue_sql = new UESQL();
		this.session_sql = new SessionSQL();
		
		Connection conn=null;
		String url = "jdbc:sqlite:data.db";
		try {

			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		String sql = "CREATE TABLE IF NOT EXISTS UniteEnseignement(ID INTEGER,code TEXT,intitule TEXT)";
		String sqlSession = "CREATE TABLE IF NOT EXISTS Session(ID_UE INTEGER,ID_classe INTEGER,ID_creneau INTEGER)";
		String sqlClasse = "CREATE TABLE IF NOT EXISTS Classe(ClasseId INTEGER,section TEXT,promotion INTEGER)";
		
		try (Statement stmt = conn.createStatement()) {
			// create a new table
			stmt.execute(sql);
			System.out.println("Ue table created");
			stmt.execute(sqlSession);
			System.out.println("Session table created");
			stmt.execute(sqlClasse);
			System.out.println("Classe table created");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void createUE(int id,String code, String intitule) throws SQLException {
		UE ue = new UE(id, code, intitule);	
		this.ue_sql.save(ue);
		System.out.println("Ue created.");
	}

	@Override
	public void deleteUE(int id) {
		UE ue = this.ue_sql.getUEById(id);
		this.ue_sql.delete(ue);
		System.out.println("Ue deleted.");
	}

	@Override
	public String getUE() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String listEU() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createSession(int id_ue, int id_classe, ArrayList<Integer> ids_creneaux) throws SQLException {
		UE ue = this.ue_sql.getUEById(id_ue);
		Classe classe = null;
		ArrayList<Creneau> creneaux = new ArrayList<Creneau>();
		for (int id : ids_creneaux) {
//			creneaux.add(this.creneau_sql.getCreneauById(id));
		}
			
		Session session = new Session(classe, ue, creneaux);	
		this.session_sql.save(session);
		System.out.println("Session created.");
	}

	@Override
	public void deleteSession(int id) {
		Session session = this.session_sql.getSessionById(id);
		this.session_sql.delete(session);
		System.out.println("Session deleted.");
	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setSession() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void createClasse(int classeid, String section, int promotion) throws SQLException {
		Classe cl = new Classe(classeid, section, promotion);	
		this.cl_sql.save(cl);
		System.out.println("Classe created.");
	}

	@Override
	public void deleteClasse(int classeid) {
		Classe cl= this.cl_sql.getById(classeid);
		this.cl_sql.delete(cl);
		System.out.println("Classe deleted.");
	}
	@Override
	public Classe getClasse(int classeid) {
		// TODO Auto-generated method stub
		return this.cl_sql.getById(classeid);
	}

}