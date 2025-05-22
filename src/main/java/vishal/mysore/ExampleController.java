package vishal.mysore;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public content";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/secured")
    public String securedEndpoint() {
        return "Secured content";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin content";
    }
}