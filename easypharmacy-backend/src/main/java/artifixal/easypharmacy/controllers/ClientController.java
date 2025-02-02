package artifixal.easypharmacy.controllers;

import artifixal.easypharmacy.auth.AuthCredentialsDTO;
import artifixal.easypharmacy.auth.JWTAuthResponseDTO;
import artifixal.easypharmacy.auth.jwt.JwtService;
import artifixal.easypharmacy.dtos.ClientDTO;
import artifixal.easypharmacy.dtos.Page;
import artifixal.easypharmacy.entities.Client;
import artifixal.easypharmacy.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for clients v1.
 * 
 * @version 1.0
 * @author ArtiFixal
 */
@RestController
@RequestMapping("/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final JwtService JwtService;
    
    @PostMapping("/login")
    public Mono<ResponseEntity<JWTAuthResponseDTO>> login(@RequestBody AuthCredentialsDTO userCredentials){
        return clientService.login(userCredentials);
    }
    
    @GetMapping("/who")
    public Mono<ClientDTO> whoAmI(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        // Strip "Bearer " header
        return Mono.just(auth.substring(7))
            .map((token)->JwtService.getEmail(token))
            .flatMap((email)->clientService.getUserDtoByEmail(email));
    }
    
    @PostMapping("/register")
    public Mono<Client> registerClient(@RequestBody ClientDTO client){
        System.out.println(client.getEmail());
        return clientService.addEntity(client);
    }
    
    @GetMapping("/getClients")
    public Flux<ClientDTO> getClients(Page page){
        return clientService.getEntitiesDtoPage(page);
    }
    
    @GetMapping("/getClient/{id}")
    public Mono<ClientDTO> getClient(@PathVariable Long id){
        return clientService.getEntityDto(id);
    }
    
    @GetMapping("/count")
    public Mono<Long> getClientCount(){
        return clientService.getEntityCount();
    }
}
