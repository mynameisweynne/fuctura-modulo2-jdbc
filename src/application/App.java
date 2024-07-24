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
		System.out.print("Digite o CPF: ");
		String cpf = sc.next();
		System.out.print("Digite a idade: ");
		int idade = sc.nextInt();
		Usuario novoUsuario = new Usuario(null, cpf, idade);
		usuarioDAO.insert(novoUsuario);
		System.out.println("Inserido! Novo id = " + novoUsuario.getId());
		
		System.out.println();

		System.out.println("==== TESTE 02: Atualização de Usuário ====");
		System.out.print("Digite o Id: ");
		int id = sc.nextInt();
		usuario = usuarioDAO.findById(id);
		System.out.print("Digite o CPF correto: ");
		cpf = sc.next();
		usuario.setCpf(cpf);
		System.out.print("Digite a idade correta: ");
		idade = sc.nextInt();
		usuario.setIdade(idade);
		usuarioDAO.update(usuario);
		System.out.println("Atualizado!");
		
		System.out.println();

		System.out.println("==== TESTE 03: Busca de Usuário por Id ====");
		System.out.print("Digite o id: ");
		id = sc.nextInt();
		usuario = usuarioDAO.findById(id);
		System.out.println(usuario);
		
		System.out.println();

		System.out.println("==== TESTE 04: Busca todos os Usuarios ====");
		List<Usuario> list = usuarioDAO.findAll();
		for (Usuario obj : list) {
			System.out.println(obj);
		}
		
		System.out.println();

		System.out.println("==== TESTE 05: Deleta Usuário por Id ====");
		System.out.print("Digite o id que será deletado: ");
		id = sc.nextInt();
		usuarioDAO.deleteById(id);
		System.out.println("Deletado!");

		sc.close();
	}
}