package model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String tipo;
	
	public FormaPagamento() {
		
	}
	
	public FormaPagamento(Long id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
