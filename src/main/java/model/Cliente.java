package model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cliente {

	private String cpf;
	private String nome;
	private String email;
	private float limite_de_credito;
	private LocalDate dt_nascimento;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getLimite_de_credito() {
		return limite_de_credito;
	}

	public void setLimite_de_credito(float limite_de_credito) {
		this.limite_de_credito = limite_de_credito;
	}

	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	@Override
	public String toString() {
		return cpf + " - " + nome + " - " + email + " - " + limite_de_credito + " - " + dt_nascimento;

	}
}
