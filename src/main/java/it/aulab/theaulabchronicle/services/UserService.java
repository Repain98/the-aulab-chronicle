package it.aulab.theaulabchronicle.services;

import it.aulab.theaulabchronicle.dtos.UserDto;
import it.aulab.theaulabchronicle.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UserService {
    void saveUser(UserDto userDto, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response);

    User findUserByEmail(String email);
}