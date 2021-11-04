package oauth.demoproject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('SCOPE_testscope')")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test!");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello!");
    }

}
