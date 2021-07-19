package work.gg3083.template.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.gg3083.template.user.model.bo.UserBO;
import work.gg3083.template.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/***
 *
 * @author Gimi
 * @date 2021/7/18 14:50
 *
 ***/
@RestController
@RefreshScope
public class UserController {

    @Value("${current.profile:default}")
    private String currentProfile;

    @GetMapping("/profile")
    public String getCurrentProfile(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k, v) -> {
            System.err.println(String.format("key:%s value:%s", k, v));
        });
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = request.getHeader(headerNames.nextElement());
            System.err.println("header:" + header);
        }
        return this.currentProfile;
    }

    @Autowired
    IUserService userService;


    @GetMapping(value = "/selectUserByLoginName")
    public UserBO selectUserByLoginName(@RequestParam String loginName){
        return userService.selectUserByLoginName(loginName);
    }
}
