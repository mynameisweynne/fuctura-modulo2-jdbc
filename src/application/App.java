package application;

import java.util.List;
import java.util.Scanner;

import dao.DAOFactory;
import dao.UsuarioDAO;
import entity.Usuario;

public class App {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Usuario usuario;

		UsuarioDAO usuarioDAO = DAOFactory.createUsuarioDAO();

		System.out.println("==== TESTE 01: Inserção de Usuário ====");
		Usuario novoUsuario = new Usuario(null, "11122233344", 32);
		usuarioDAO.insert(novoUsuario);
		System.out.println("Inserido! Novo id = " + novoUsuario.getId());

		System.out.println("==== TESTE 02: Atualização de Usuário ====");
		usuario = usuarioDAO.findById(2);
		usuario.setCpf("00011122233");
		usuario.setIdade(30);
		usuarioDAO.update(usuario);
		System.out.println("Atualizado!");

		System.out.println("==== TESTE 03: Busca de Usuário por Id ====");
		usuario = usuarioDAO.findById(3);
		System.out.println(usuario);

		System.out.println("==== TESTE 04: Busca todos os Usuarios ====");
		List<Usuario> list = usuarioDAO.findAll();
		for (Usuario obj : list) {
			System.out.println(obj);
		}

		System.out.println("==== TESTE 05: Deleta Usuário por Id ====");
		System.out.print("Digite o id que será deletado: ");
		int id = sc.nextInt();
		usuarioDAO.deleteById(id);
		System.out.println("Deletado!");

		sc.close();
	}
}