package app.web;

import app.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    MemberService memberService;
    @GetMapping
    public ResponseEntity<?> testFindAll(){
//        memberService.findAll();
        memberService.findByLogin("igushev12");
        memberService.createMember("igushev12", "hoffd7yFd", "greg@gmail.com");
        return ResponseEntity.ok("ok");
    }
}
