package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String homaPage() {
        return (new Date()).toString();
    }
}
