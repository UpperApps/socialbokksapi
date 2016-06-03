package com.algaworks.socialbooks.domain;



/**
 * @author Rodrigo Melo
 * Casse auxiliar para a construção das mensagens de erro do sistema.
 *
 */
public class DetalhesErro {
	
	private String titulo;
	
	private Long satus;
	
	private Long timestamp;
	
	private String mensagemDesenvolvedor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getSatus() {
		return satus;
	}

	public void setSatus(Long satus) {
		this.satus = satus;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

	public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}
}
