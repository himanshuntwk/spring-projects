package com.himanshu.delegate;

import com.himanshu.api.UserApiDelegate;
import com.himanshu.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsersApiImpl implements UserApiDelegate {

  @Override
  public Mono<ResponseEntity<Flux<UserDTO>>> getUserBySearch(String search, ServerWebExchange exchange) {
    System.out.println("Input received >> " + search);
    UserDTO userDTO = new UserDTO();
    userDTO.setFirstName("Himanshu");
    userDTO.setLastName("Sharma");
    UserDTO userDTO2 = new UserDTO();
    userDTO2.setFirstName("Himanshu2");
    userDTO2.setLastName("Sharma2");
    ResponseEntity<Flux<UserDTO>> result = ResponseEntity.ok(Flux.just(userDTO, userDTO2));
    return Mono.just(result);
  }
}
