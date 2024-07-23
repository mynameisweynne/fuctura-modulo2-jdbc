package dao;

import dao.impl.UsuarioDAOJDBC;
import database.DB;

public class DAOFactory {

	public static UsuarioDAO createUsuarioDAO() {
		return new UsuarioDAOJDBC(DB.getConnection());
	}
}
