package com.digitalsanctuary.spring.user.controller;

import com.digitalsanctuary.spring.user.persistence.model.User;
import com.digitalsanctuary.spring.user.persistence.repository.RoleRepository;
import com.digitalsanctuary.spring.user.persistence.repository.UserRepository;
import com.digitalsanctuary.spring.user.service.DSUserDetails;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    /**
     * The role repository.
     */
    private final RoleRepository roleRepository;

    @GetMapping("/admin/admin.html")
    public String load(@AuthenticationPrincipal DSUserDetails userDetails, HttpSession session, ModelMap model) {
        log.debug("AdminController.load:" + "userDetails: {}", userDetails);
        if (session != null && session.getAttribute("error.message") != null) {
            model.addAttribute("errormessage", session.getAttribute("error.message"));
            session.removeAttribute("error.message");
        }
        List<User> all = userRepository.findAll();
        log.debug("AdminController.load:" + "users: {}", all);
        model.addAttribute("users", all);
        return "admin/admin";
    }


    @GetMapping("/admin/update-user-action/{id}")
    public String getUserForUpdate(@AuthenticationPrincipal DSUserDetails userDetails, HttpSession session, ModelMap model,@PathVariable("id") Long id) {
        log.debug("AdminController.getUserForUpdate:" + "userDetails: {}", userDetails);
        if (session != null && session.getAttribute("error.message") != null) {
            model.addAttribute("errormessage", session.getAttribute("error.message"));
            session.removeAttribute("error.message");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        log.debug("AdminController.load:" + "user: {}", user);
        model.addAttribute("user", user);
        //return "admin/add-user";
        return "admin/edit-user";
    }

    @GetMapping("/admin/signup")
    public String showSignUpForm(User user) {
        return "admin/add-user";
    }

    @PostMapping("/admin/adduser")
    public String addUser(@Valid User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "admin/add-user";
        }
        String role = user.isAdmin()? "ROLE_ADMIN" :"ROLE_USER";
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        userRepository.save(user);
        return "redirect:/admin/admin.html";
    }



    @PostMapping("/admin/save/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "admin/update-user";
        }
        String role = user.isAdmin()? "ROLE_ADMIN" :"ROLE_USER";
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        userRepository.save(user);

        return "redirect:/admin/admin.html";

    }

    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        return "redirect:/admin/admin.html";
    }
}
