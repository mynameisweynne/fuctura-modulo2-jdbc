package entity;

import java.util.Objects;

public class Usuario {

	private Integer id;
	private String cpf;
	private Integer idade;

	public Usuario() {
	}

	public Usuario(Integer id, String cpf, Integer idade) {
		this.id = id;
		this.cpf = cpf;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "\nUsuário " + id + "\ncpf = " + cpf + "\nidade = " + idade;
	}
}