package chiralsoftware.fcmspringjavascript.controllers;

import static chiralsoftware.fcmspringjavascript.controllers.FcmRegister.tokens;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 */
@Controller
public class IndexController {
    
    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("tokens", tokens);
        return "/index";
    }
    
    @GetMapping("/fcm")
    public void getFcm() {
        
    }

}
