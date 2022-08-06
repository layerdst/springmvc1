package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {


    private Logger log = LoggerFactory.getLogger(getClass());

    //다중 URL 설정이 가능하다.
    @RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("hellobasic");
        return "basic";
    }

    @GetMapping(value = "mapping-get-v2")
    public String helloGet() {
        log.info("hellobasic");
        return "basic";
    }


    /**
     * PathVariable 사용
     * 변수명이 같으면 생략가능
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */
    @GetMapping(value = "mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId) {
        log.info("mappingPath userId = {}", userId);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {

        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }


    /**
     * 특정파라미터 조건 매핑
     * <p>
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params={"mode=debug", "data=good"}
     */

    @GetMapping(value = "/mapping-param", params = "mode!=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정헤더로 추가 매핑
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeaders() {
        log.info("mappingHeaders");
        return "ok";
    }


    /**
     * content-Type 헤더 기반 추가 매핑 Media Type
     * consumes = "application/json"
     * consumes = "!application/json"
     * consumes = "application/*"
     * consumes = "*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }


    /**
     * Accept 헤더 기반 Media Type -> Header에 Accept 가 media-Type 값이고, postman 은 default가 *.* 이기 때문에 대부분 호출
     * produces = "text/html"
     * produces = "!test/html"
     * produces = "text/*"
     * produces = "*\/*"
     * 미디어 타입이 맞지 않으면 406 error 를 내뱉는다.
     * produce 는 서버에서 클라이언트로 보내는 데이터 타입으로, 클라이언트에서 받아들일수 있는 데이터 타입을 설정하는 것이다.
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
