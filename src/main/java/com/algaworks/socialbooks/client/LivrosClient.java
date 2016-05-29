package com.algaworks.socialbooks.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.domain.Livro;

public class LivrosClient {

	public List<Livro> listar() {

		RestTemplate restTemplate = new RestTemplate();

		// Configura o request para a o recurso /livros que retorna um array de
		// livros.
		RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/livros"))
				.header("Authorization", "Basic YWxnYXdvcmtzOnMzbmg0").build();

		// O objeto restTemplate executa o request e retorna para o response o
		// array de livors.
		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);

		// Transforma o array em uma lista.
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro){
		RestTemplate restTemplate = new RestTemplate();

		// Configura o request para a o recurso /livros que retorna um array de
		// livros.
		RequestEntity<Livro> request = RequestEntity.post(URI.create("http://localhost:8080/livros"))
				.header("Authorization", "Basic YWxnYXdvcmtzOnMzbmg0").body(livro);

		// O objeto restTemplate executa o request e retorna para o response o
		// array de livors.
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		// Transforma o array em uma lista.
		return response.getHeaders().getLocation().toString();
	}
}
