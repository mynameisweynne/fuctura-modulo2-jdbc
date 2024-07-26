package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDAO;
import database.DBException;
import entity.Usuario;

public class UsuarioDAOJDBC implements UsuarioDAO {

	private final Connection conn;

	public UsuarioDAOJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Usuario obj) {
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO usuario (cpf, idade) VALUES (?,?)",
				Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, obj.getCpf());
			pstmt.setInt(2, obj.getIdade());

			int rowsAffected = pstmt.executeUpdate();

			try (ResultSet rs = pstmt.getGeneratedKeys()) {

				if (rowsAffected > 0) {
					if (rs.next()) {
						obj.setId(rs.getInt(1));
					}
				} else {
					throw new DBException("Criação de usuário falhou!");
				}
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public void update(Usuario obj) {
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE usuario SET cpf = ?, idade = ? WHERE id = ?")) {

			pstmt.setString(1, obj.getCpf());
			pstmt.setInt(2, obj.getIdade());
			pstmt.setInt(3, obj.getId());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected == 0) {
				throw new DBException("Nenhum usuário atualizado. Verifique o ID.");
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM usuario WHERE id = ?")) {

			pstmt.setInt(1, id);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected == 0) {
				throw new DBException("Nenhum usuário encontrado para exclusão. Verifique o ID.");
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public Usuario findById(Integer id) {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT usuario.* FROM usuario WHERE id = ?")) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {
					return new Usuario(rs.getInt("id"), rs.getString("cpf"), rs.getInt("idade"));
				}
			}
			return null;
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public List<Usuario> findAll() {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT usuario.* FROM usuario")) {

			try (ResultSet rs = pstmt.executeQuery()) {
				List<Usuario> list = new ArrayList<>();
				while (rs.next()) {
					list.add(new Usuario(rs.getInt("id"), rs.getString("cpf"), rs.getInt("idade")));
				}
				return list;
			}
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
	}
}