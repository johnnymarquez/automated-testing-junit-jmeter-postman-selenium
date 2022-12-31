package com.devops.dxc.devops.model;

import com.devops.dxc.devops.utils.Util;

import java.io.Serializable;

public class Dxc implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2988002029080131424L;
	
	private int dxc;
	private int saldo;
	private int impuesto;
	private int sueldo;
	private int ahorro;

	public Dxc(int ahorro, int sueldo){
		this.ahorro = ahorro;
		this.sueldo = sueldo;
	}

	public Dxc() {
	}

	public int getDxc() {
		return dxc;
	}

	public void setDxc(int dxc) {
		this.dxc = dxc;
	}

	public int getSaldo() {
		this.saldo = ahorro - dxc;
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public int getImpuesto() {
		this.impuesto = Util.getImpuesto(sueldo, dxc);
		return impuesto;
	}

	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getAhorro() {
		return ahorro;
	}

	public void setAhorro(int ahorro) {
		this.ahorro = ahorro;
	}
}
