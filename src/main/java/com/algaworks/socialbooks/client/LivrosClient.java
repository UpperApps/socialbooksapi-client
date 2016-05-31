package com.algaworks.socialbooks.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.domain.Livro;

public class LivrosClient {
	
	private RestTemplate restTemplate;
	
	private String URI_BASE;
	
	private String URN_BASE = "/livros";
	
	private String credencial;
	
	public LivrosClient(String url, String usuario, String senha){
		restTemplate = new RestTemplate();
		
		URI_BASE = url.concat(URN_BASE);
		
		String credencialAux = usuario + ":" + senha;
		
		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}

	public List<Livro> listar() {

		// Configura o request para a o recurso /livros que retorna um array de
		// livros.
		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE))
				.header("Authorization", credencial).build();

		// O objeto restTemplate executa o request e retorna para o response o
		// array de livors.
		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);

		// Transforma o array em uma lista.
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro){

		// Configura o request para a o recurso /livros que retorna um array de
		// livros.
		RequestEntity<Livro> request = RequestEntity.post(URI.create(URI_BASE))
				.header("Authorization", credencial).body(livro);

		// O objeto restTemplate executa o request e retorna para o response o
		// array de livors.
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		// Transforma o array em uma lista.
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscar(String uri){
		
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri))
				.header("Authorization", credencial).build();
		
		ResponseEntity<Livro> response = restTemplate.exchange(request, Livro.class);
		
		return response.getBody();
		
	}
}
