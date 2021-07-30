package com.ssafy.commb.controller;

import com.ssafy.commb.jwt.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Map;

// 소스 코드 합친 후 해당 컨트롤러는 삭제

@RestController
@RequestMapping("/security")
@Api("Security Controller API V1")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @Value("${security.accesstoken}")
    private String accessToken;

    @Value("${security.refreshtoken}")
    private String refreshToken;

    // 로그인 후 토큰 발행
    @Transactional
    @GetMapping("/create/token")
    public ResponseEntity createToken(@RequestParam int userId){
        Map<String, Object> map = securityService.createToken(userId);

        HttpHeaders resHeader = new HttpHeaders();

        resHeader.set(accessToken, (String) map.get("acToken"));
        resHeader.set(refreshToken, (String) map.get("rfToken"));

        return ResponseEntity.ok().headers(resHeader).build();
    }

    // dummy test용
    @GetMapping("/valid/access")
    public String validAccessToken(@RequestParam String acToken, HttpServletRequest req) {
        System.out.println(req.getAttribute("userId"));

        return acToken;
    }
}