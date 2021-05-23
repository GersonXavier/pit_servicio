package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {

	@Autowired
	public AlumnoService alumnoser;
	
	@GetMapping
	public ResponseEntity<List<Alumno>>lista(){
		return ResponseEntity.ok(alumnoser.listaAlumno());
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		return ResponseEntity.ok(alumnoser.insertaActualizaAlumno(obj));
	}
	
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){
		Optional<Alumno> optAlumno= alumnoser.obtienePorId(obj.getIdAlumno());
		
		if(optAlumno.isPresent()) {
		return ResponseEntity.ok(alumnoser.insertaActualizaAlumno(obj));
	}else {
		return ResponseEntity.notFound().build();
	}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> elimina(@PathVariable("id") int idAlumno){
		Optional<Alumno> optAlumno= alumnoser.obtienePorId(idAlumno);
		
		if(optAlumno.isPresent()) {
		alumnoser.eliminaAlumno(idAlumno);
		return ResponseEntity.ok(optAlumno.get());
	}else {
		return ResponseEntity.notFound().build();
	}
	}
	
}
