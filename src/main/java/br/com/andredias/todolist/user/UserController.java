package br.com.andredias.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/* 
 * MODIFICADORES
 * public - todos podem acessar
 * private - somente pequenas coisas podem ser acessadas
 * protected - somente no mesmo pacote
 */

@RestController
@RequestMapping("/users") // rota raiz
public class UserController {
    @Autowired
    private  IUserRepository userRepository;

    @PostMapping("/") //rota do método
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            System.out.println("Usuário já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe"); // o método termina aqui
        }
        // criptografando a senha
        var passwordHash = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()); 

        userModel.setPassword(passwordHash);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}