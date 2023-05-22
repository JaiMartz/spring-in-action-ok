package tacos.gc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class RedirectController {
    
    /*
     * ESTE ANDA
     */
    @GetMapping("/redirectWithRedirectView")
    public RedirectView redirectWithUsingRedirectView(
      RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("redirectedUrl");
    }
    
    /*
     * ESTE ANDA Y ES MEJOR
     */
    @GetMapping("/redirectWithRedirectPrefix")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/redirectedUrl", model);
    }
    
    /*
     * ESTE ES EL QUE NO ANDA. En ModelAndView solo se pone el path, no forward:
     */
    @GetMapping("/forwardWithForwardPrefix")
    public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/redirectedUrl", model);
    }
}

//curl -I http://localhost:8080/redirectWithRedirectView
//curl -I http://localhost:8080/forwardWithForwardPrefix
//curl -I http://localhost:8080/redirectWithRedirectPrefix