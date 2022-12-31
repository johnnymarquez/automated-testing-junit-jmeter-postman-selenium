package com.devops.dxc.devops.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.service.IUfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/rest/msdxc")
@CrossOrigin("*")
public class RestData {

	@Autowired
	IUfService ufService;

	private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

	@GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dxc> getData(@RequestParam("sueldo") @Nullable String sueldo,
									   @RequestParam("ahorro") @Nullable String ahorro){
		
		LOGGER.log(Level.INFO, "< Trabajo DevOps - DXC > <Consultado Diez por ciento>");

		int iAhorro = 0;
		int iSueldo = 0;

		if(sueldo == null){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Parámetro Sueldo es obligatorio");
		}

		if(ahorro == null){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Parámetro Ahorro es obligatorio");
		}

		try{
			iSueldo = Integer.parseInt(sueldo);

			if(iSueldo<0){
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Parámetro Sueldo debe ser mayo a 0");
			}
		} catch (NumberFormatException nex){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Parámetro Sueldo debe ser numérico");
		}

		try{
			iAhorro = Integer.parseInt(ahorro);

			if(iAhorro<0){
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Parámetro Ahorro debe ser mayo a 0");
			}
		} catch (NumberFormatException nex){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Parámetro Ahorro debe ser numérico");
		}

		Dxc dxc = ufService.obtenerDiezxCiento(iAhorro, iSueldo);
		ResponseEntity<Dxc> response = new ResponseEntity<>(dxc, HttpStatus.OK);

		return response;
	}
}