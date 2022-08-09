package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        log.info("user={}, age ={} " , username , age);
        res.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int age

    ) {
        log.info("username={}, age={}", memberName, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 변수랑 파라미터 이름이 같으면 생략가능
            @RequestParam int age

    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age)  // 어노테이션 생략이 가능
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestRequired(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age)  // required false 로 지정하면 null 값이 들어가는데, int는 널null 이 들어갈수 없으므로 ,
                                                        // Integer를 쓰는게 좋다.
                                                        // 다른 방법으로는 defaultValue 를 사용하며
                                                        // 빈문자의 경우에도 defaultValue 가 적용된다.
    {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap)
    {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /*
    MultiValueMap(key=[value1, value2]... ex) key=userIds, valuer[id1, id2..]
    ?userIds=id1&userIds=id2
     */
    @ResponseBody
    @RequestMapping("/request-multiValue")
    public String requestMultiValue(
            @RequestParam Map<String, Object> paramMap)
    {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }


}
