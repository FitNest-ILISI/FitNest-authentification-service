package fitnest.auth_service.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class demoController {
    @GetMapping("/hi")
    public String Hi()
    {
        return "hi";
    }
}
