package br.com.joaovittor.todolist.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.joaovittor.todolist.users.exception.UserException;
import br.com.joaovittor.todolist.users.model.User;
import br.com.joaovittor.todolist.users.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthUtils extends OncePerRequestFilter {

    private final UserRepository repository;

    public static UUID getLoggedUser(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");

        return (UUID) userId;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getServletPath().startsWith("/h2-console")) {
            String authorization = request.getHeader("Authorization");
            String userInformationEncoded = authorization.substring("Basic".length()).trim();
            byte[] userInformationDecoded = Base64.getDecoder().decode(userInformationEncoded);
            String userInformation = new String(userInformationDecoded);
            String[] credentials = userInformation.split(":");
            String username = credentials[0];
            String password = credentials[1];

            Optional<User> entity = repository.findByUserName(username);

            if (entity.isEmpty()) {
                throw new UserException("Only valid user are allowed to access this endpoint");
            } else {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), entity.get().getPassword());
                if (result.verified) {
                    request.setAttribute("userId", entity.get().getId());
                    filterChain.doFilter(request, response);
                }
                else
                    throw new UserException("Invalid user or password");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
