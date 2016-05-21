package com.algaworks.socialbooks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.services.LivrosService;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

	@Autowired
	private LivrosService livrosService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		//O método save sempre retorna o objeto salvo. 
		livro = livrosService.salvar(livro);
		
		// Monta o URI que será retornada para o cliente, no cabeçalho HTTP. 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(livro.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		//A classe ResponseEntity é um builder que permite retornar respostas
		//ao cliente para seu devido tratamento.
		Livro livro = null;
		try {
			livro = livrosService.buscar(id);	
		} catch (LivroNaoEncontradoException e) {
			//Retorna o erro 404 Not Found.
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		try {
			livrosService.deletar(id);	
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		//Retorna a resposta 204, mostrando que tudo foi processado normalmente e
		//que nada foi retornado.
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
		livro.setId(id);
		try {
			livrosService.salvar(livro);	
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
				
		return ResponseEntity.noContent().build();
	}
}
