package com.devops.dxc.devops.service;

import com.devops.dxc.devops.dao.IUfDao;
import com.devops.dxc.devops.entity.Uf;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UfServiceImpl implements IUfService {

	private final static String IND_UF = "uf";

	@Autowired
	IUfDao ufDao;

	@Override
	public List<Uf> findAll() {
		return (List<Uf>) ufDao.findAll();
	}

	@Override
	public Uf save(Uf uf) {
		return ufDao.save(uf);
	}

	@Override
	public Dxc obtenerDiezxCiento(Integer iAhorro, Integer iSueldo) {
		Uf uf = validaUf();
		Dxc dxc = new Dxc(iAhorro, iSueldo);
		dxc.setDxc(Util.getDxc(dxc.getAhorro(), uf.getValor()));
		dxc.setImpuesto(Util.getImpuesto(dxc.getSueldo(), dxc.getDxc()));
		return dxc;
	}

	@Override
	public Uf validaUf() {
		Uf uf = new Uf();
		if (findAll().isEmpty()) {
			uf.setFecha(new Date());
			uf.setValor(Util.getIndicadorDiario(IND_UF).getSerie().get(0).getValor());
			save(uf);
		} else {
			uf = findAll().get(0);
		}
		return uf;
	}

}
