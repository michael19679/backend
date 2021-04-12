package io.modulrfinance.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class EmailController {

    @GetMapping("hello")
    public String helloWorld() {
        return "Hello From Modulr";
    }
}
