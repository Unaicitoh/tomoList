package tfc.tomolist.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TomoErrorController implements ErrorController  {

    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView m = new ModelAndView();
        m.setViewName("error");
        return m;
    }

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
}