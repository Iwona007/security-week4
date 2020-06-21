package pl.iwona.securityencoderweek4.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @GetMapping("/admin")
    public String forAll(Principal principal) {
        return "Hello admin" + principal.getName();
    }

    @GetMapping("/user")
    public String forUser(Principal principal) {
        return "Hello user: " + principal.getName() ;
    }

    @GetMapping("/stranger")
    public String forAdmin() {
        return "Hello stranger: ";
    }
}
