package work.gg3083.template.user.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.base.exception.MyExceptionType;
import work.gg3083.template.base.model.vo.JsonBack;
import work.gg3083.template.user.model.param.UserAddParam;
import work.gg3083.template.user.model.param.UserUpdateParam;
import work.gg3083.template.user.model.vo.UserVO;
import work.gg3083.template.user.service.IUserService;


import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户控制器", tags = {"用户相关"})
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("list")
    public JsonBack list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                         String searchKey) {

        return JsonBack.buildSuccJson(userService.list4Page(pageNo, pageSize, searchKey));
    }

    @PostMapping("add")
    public JsonBack add(@RequestBody @Validated UserAddParam param) {
        userService.add(param);
        return JsonBack.buildSuccJson();
    }

    @PostMapping("/update/{id}")
    public JsonBack update(@PathVariable Integer id, @RequestBody @Validated UserUpdateParam param) {
        userService.update(id, param);
        return JsonBack.buildSuccJson();
    }

//    @GetMapping("/")
//    public JsonBack get(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        String userId = TokenUtil.parseToken(token);
//        UserVO userVo = userService.findUserVoByUserId(userId);
//        return JsonBack.buildSuccJson(userVo);
//    }
//
//    @GetMapping("/refreshToken")
//    public JsonBack refreshToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        TokenVerifyEnum verifyEnum = jwtHelper.validationToken(token);
//        if (verifyEnum == TokenVerifyEnum.EXPIRED) {
//            String userId = TokenUtil.parseToken(token);
//            UserVO userVo = userService.findUserVoByUserId(userId);
//            String newToken = jwtHelper.generateToken(userVo);
//            return JsonBack.buildSuccJson(newToken);
//        }
//        return JsonBack.buildErrorJson(MyExceptionType.TOKEN_FAIL);
//    }

    @PostMapping("/delete/{id}")
    public JsonBack delete(@PathVariable Integer id) {
        return JsonBack.buildSuccJson(userService.delete(id));
    }
}

