package com.digitalsanctuary.spring.user.controller;

import com.digitalsanctuary.spring.user.persistence.model.Registration;
import com.digitalsanctuary.spring.user.persistence.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping
    public String getAllRegistrations(Model model) {
        model.addAttribute("registrations", registrationRepository.findAll());
        return "registration/list"; // view name
    }

    @GetMapping("/{id}")
    public String getRegistration(@PathVariable("id") Long id, Model model) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid registration Id:" + id));
        log.debug("RegistrationController.getRegistration:" + "registration: {}", registration);
        model.addAttribute("registration", registration);
        return "registration/show"; // view name
    }

    @GetMapping("/new")
    public String newRegistrationForm(Model model) {
        model.addAttribute("registration", new Registration());
        return "registration/new"; // view name
    }

    @PostMapping
    public String createRegistration(@Valid Registration registration, BindingResult result) {
        if (result.hasErrors()) {
            return "registration/new";
        }
        registrationRepository.save(registration);
        return "redirect:/registration";
    }

    @GetMapping("/edit/{id}")
    public String editRegistrationForm(@PathVariable("id") long id, Model model) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid registration Id:" + id));
        model.addAttribute("registration", registration);
        return "registration/edit"; // view name
    }

    @PostMapping("/update/{id}")
    public String updateRegistration(@PathVariable("id") long id, @Valid Registration registration,
                                     BindingResult result) {
        if (result.hasErrors()) {
            registration.setId(id);
            return "registration/edit";
        }
        registrationRepository.save(registration);
        return "redirect:/registration";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegistration(@PathVariable("id") long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid registration Id:" + id));
        registrationRepository.delete(registration);
        return "redirect:/registration";
    }

}