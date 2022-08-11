package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello");
        return mav;
    }
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello");
        return "/response/hello";
    }
    @RequestMapping("/response/hello") // 메서드 반환값이 없고 경로의 이름이 requestmapping 지정값과 일치하면 해당 경로에 모델과 함께 전송된다.
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
