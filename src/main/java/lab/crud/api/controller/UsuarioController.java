package lab.crud.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.crud.api.model.Usuario;
import lab.crud.api.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	//curl -X POST http://localhost:8081/usuarios -H "Content-Type: application/json; Charset=utf-8" -d @usuario.json
	
	//@RequestMapping(method = RequestMethod.POST, path = "/usuario")
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> novo(
			@RequestBody Usuario usuario) {
			
		repository.save(usuario);
		
		System.out.println(usuario.toString());
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(usuario);
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<Iterable<Usuario>> obterTodos(){
		
		
		List<Usuario> listUsuarios = repository.findByNomeLike("%o%");
		
		
		
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(repository.findAll());
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Object> buscarPorId(
			@PathVariable Integer id){
	
		//Alt + SHIFT + L -> extrai variável local
		Optional<Usuario> usuarioEncontrado = repository.findById(id);
		
		//Empty = Vazio
		if(usuarioEncontrado.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Usúario não encontrado");
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(usuarioEncontrado.get());
	
	//curl -X POST http://localhost:8081/usuarios -H "Content-Type: application/json; Charset=utf-8" -d @usuario2.json
	}
	
	//curl -X PUT http://localhost:8081/usuarios/1 -H "Content-Type: application/json; Charset=utf-8" -d @usuario.json
			@PutMapping("/usuarios/{id}")
			public ResponseEntity<Object> atualizarUsuario(
					@PathVariable Integer id,
					@RequestBody Usuario usuar){
			
				Optional<Usuario> usuario = repository.findById(id);
				
				if (usuario.isEmpty()) {
					
					return ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body("Usúario não encontrado!");
				}
				
				usuar.setId(id);
				usuar.setDataNascimento(usuario.get().getDataNascimento());
				repository.save(usuar);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("Usúario atualizado com sucesso!");
			}
	
			//curl -X DELETE http://localhost:8081/usuarios/1
			@DeleteMapping("/usuarios/{id}")
			public ResponseEntity<Object> apagarUsuario(
					@PathVariable Integer id) {
				
				Optional<Usuario> usuario = repository.findById(id);
				
				if(usuario.isEmpty()) {
					
					return ResponseEntity
							.status(HttpStatus.NOT_FOUND)
							.body("Usúario não encontrado!");
				}
				
				Usuario usuar = usuario.get();
				repository.delete(usuar);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("Usúario apagado com sucesso!");
						
				
				
			}

}
