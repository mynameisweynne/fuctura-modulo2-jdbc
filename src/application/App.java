package application;

import java.util.List;
import java.util.Scanner;

import dao.DAOFactory;
import dao.UsuarioDAO;
import entity.Usuario;

public class App {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
        UsuarioDAO usuarioDAO = DAOFactory.createUsuarioDAO();

        System.out.println("\n==== TESTE 01: Inserção de Usuário ====");
        Usuario novoUsuario = obterDadosUsuario(sc, "novo");
        usuarioDAO.insert(novoUsuario);
        System.out.println("Inserido! Novo id = " + novoUsuario.getId());
        
        System.out.println("\n==== TESTE 02: Atualização de Usuário ====");
        System.out.print("Digite o Id do usuário a ser atualizado: ");
        int idAtualizar = sc.nextInt();
        sc.nextLine(); // Limpa o buffer do scanner
        Usuario usuarioAtualizar = usuarioDAO.findById(idAtualizar);
        if (usuarioAtualizar != null) {
            Usuario dadosAtualizados = obterDadosUsuario(sc, "a atualizar");
            usuarioAtualizar.setCpf(dadosAtualizados.getCpf());
            usuarioAtualizar.setIdade(dadosAtualizados.getIdade());
            usuarioDAO.update(usuarioAtualizar);
            System.out.println("Atualizado!");
        } else {
            System.out.println("Usuário não encontrado.");
        }

        System.out.println("\n==== TESTE 03: Busca de Usuário por Id ====");
        System.out.print("Digite o id: ");
        int idBusca = sc.nextInt();
        sc.nextLine(); // Limpa o buffer do scanner
        Usuario usuarioEncontrado = usuarioDAO.findById(idBusca);
        System.out.println(usuarioEncontrado != null ? usuarioEncontrado : "Usuário não encontrado.");

        System.out.println("\n==== TESTE 04: Busca todos os Usuarios ====");
        List<Usuario> listaUsuarios = usuarioDAO.findAll();
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            listaUsuarios.forEach(System.out::println);
        }
 
        System.out.println("\n==== TESTE 05: Deleta Usuário por Id ====");
        System.out.print("Digite o id que será deletado: ");
        int idDeletar = sc.nextInt();
        sc.nextLine(); // Limpa o buffer do scanner
        usuarioDAO.deleteById(idDeletar);
        System.out.println("Deletado!");

        sc.close();
    }

    // Método auxiliar para obter dados do usuário
    private static Usuario obterDadosUsuario(Scanner sc, String posfixo) {
        System.out.print("Digite o CPF do usuário " + posfixo + ": ");
        String cpf = sc.nextLine();
        System.out.print("Digite a idade do usuário " + posfixo +": ");
        int idade = sc.nextInt();
        sc.nextLine(); // Limpa o buffer do scanner
        return new Usuario(null, cpf, idade);
    }
}