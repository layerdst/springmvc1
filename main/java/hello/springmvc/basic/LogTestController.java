package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);


        //고레벨에서 저레벨 순으로
        //application.properties 에서 log 레벨을 설정할 수 있으며
        // trace를 설정하면 고레벨까지 다 볼수 있음
        // debug는 개발서버 레벨 설정
        // info 는 운영서버 레벨 설정

        // 이런 방식의 로그작성 방식은 작동은 되나, 올바른 사용법이 아니다.
        // 이 방법은 + String 연산이 발생되고, 메모리 점유등의 문제가 있다.
        log.info("info log = " + name);


        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log = {} ", name);
        log.error("error log = {} ", name);

        return "ok";
    }

}
