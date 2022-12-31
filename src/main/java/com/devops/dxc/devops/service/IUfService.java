package com.devops.dxc.devops.service;

import com.devops.dxc.devops.entity.Uf;
import com.devops.dxc.devops.model.Dxc;

import java.util.List;

public interface IUfService {

	List<Uf> findAll();
	Uf save(Uf uf);
	Dxc obtenerDiezxCiento(Integer iAhorro, Integer iSueldo);
	Uf validaUf();
}
