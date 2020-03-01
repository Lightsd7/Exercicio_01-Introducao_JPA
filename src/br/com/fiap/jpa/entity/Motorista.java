package br.com.fiap.jpa.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "T_MOTORISTA")

public class Motorista {

	@Id
	@Column(name = "NR_CARTEIRA")
	private int carteira;

	@Column(name = "NM_MOTORISTA")
	private String nome;

	@Column(name = "DT_NASCIMENTO")
	private Calendar dataNascimento;

	@Lob
	@Column(name = "FL_CARTEIRA")
	private byte[] foto;

	@Enumerated(EnumType.STRING)
	@Column(name = "DS_GENERO")
	private GeneroMotorista genero;

	public Motorista() {
		super();
	}

	public Motorista(int carteira, String nome, Calendar dataNascimento, byte[] foto, GeneroMotorista genero) {
		super();
		this.carteira = carteira;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.foto = foto;
		this.genero = genero;
	}

	public int getCarteira() {
		return carteira;
	}

	public void setCarteira(int carteira) {
		this.carteira = carteira;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public GeneroMotorista getGenero() {
		return genero;
	}

	public void setGenero(GeneroMotorista genero) {
		this.genero = genero;
	}

}
