package chiralsoftware.fcmspringjavascript.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("/fcm")
public class FcmRegister {

    private static final Logger LOG = Logger.getLogger(FcmRegister.class.getName());
    
    /** This isn't something to do in production code, but it simplifies this
     example and we don't need a database. */
    public static final Set<String> tokens = new HashSet<>();
    
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam String token) {
        LOG.info("this is the token: " + token);
        if(token.length() != 152) {
            LOG.info("Invalid token, must be 152 chars: " + token);
            return "INVALID-TOKEN";
        }
        tokens.add(token);
        return "OK";
    }
    
}
