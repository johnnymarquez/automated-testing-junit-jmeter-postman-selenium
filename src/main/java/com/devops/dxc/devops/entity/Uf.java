package com.devops.dxc.devops.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Uf implements Serializable{

	private static final long serialVersionUID = 1549710836175127713L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
}
