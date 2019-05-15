package com.lucascolaco.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucascolaco.curso.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly=true) // ela não se envolve com o banco de dado, isso vai ficar mais rapido
	Cliente findByEmail(String email); // buscar o email no banco de dados para saber se ja existe

}
