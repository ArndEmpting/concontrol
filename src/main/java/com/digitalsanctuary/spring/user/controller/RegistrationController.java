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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;



    @GetMapping("/main.html")
    public String main(Model model) {

        return "registration/main"; // view name
    }
    @GetMapping("/full-list.html")
    public String fullList(Model model) {
        return "registration/full-list"; // view name
    }
    @GetMapping("/registrations")
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
        List<String> alterOptions = new ArrayList<>();
        alterOptions.add("Option1");
        alterOptions.add("Option2");
        alterOptions.add("Option3");
        alterOptions.add("Option4");
        model.addAttribute("alterOptions", alterOptions);
        return "registration/new"; // view name
    }

    @PostMapping("/create")
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

    @PostMapping("/registration/update")
    public String updateRegistration(@Valid Registration registration) {
        registrationRepository.save(registration);
        return "redirect:/registrations";
    }

    @PostMapping("/registration/delete")
    public String deleteRegistration(@Valid Registration registration) {
        registrationRepository.delete(registration);
        return "redirect:/registrations";
    }


}