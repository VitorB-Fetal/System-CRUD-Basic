package com.prisma.cadastro_usuario.business;

import com.prisma.cadastro_usuario.infrastructure.entitys.Users;
import com.prisma.cadastro_usuario.infrastructure.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repository;

    public void saveUsers(Users usuario) {
        repository.saveAndFlush(usuario);
    }

    public Users returnUsersForEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void saveAndUpdateUserforEmail(String email, Users usuario) {
        Users usuarioEntity = returnUsersForEmail(email);

        // 2. Cria o objeto atualizado mantendo o ID original
        Users userUpdate = Users.builder()
                .id(usuarioEntity.getId())
                .email(email)
                .name(usuario.getName() != null ? usuario.getName() : usuarioEntity.getName())
                .build();
                repository.save(userUpdate);
    }
}
