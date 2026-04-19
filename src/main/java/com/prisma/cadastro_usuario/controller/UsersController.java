package com.prisma.cadastro_usuario.controller;

import com.prisma.cadastro_usuario.business.UsersService;
import com.prisma.cadastro_usuario.infrastructure.entitys.Users;
import com.prisma.cadastro_usuario.infrastructure.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController
{

    private final UsersService usuarioService;

    @PostMapping
    public ResponseEntity<Void> saveUsers (@RequestBody Users usuario)
    {usuarioService.saveUsers(usuario);return ResponseEntity.ok().build();}

    @GetMapping
    public ResponseEntity<Users> scannerUsersForEmail(@RequestParam String email)
    { return ResponseEntity.ok(usuarioService.returnUsersForEmail(email));}

    @PutMapping
    public ResponseEntity<Void> putUsers (@RequestBody Users usuario, @RequestBody String email)
    {usuarioService.saveAndUpdateUserforEmail(email, usuario);return ResponseEntity.ok().build();}

    @DeleteMapping
    public ResponseEntity<Void> deleteUsers (@RequestParam String email)
    {usuarioService.deleteByEmail(email); return  ResponseEntity.ok().build();}



}
