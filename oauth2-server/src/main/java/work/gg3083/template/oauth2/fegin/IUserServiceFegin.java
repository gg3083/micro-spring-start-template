package work.gg3083.template.oauth2.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.gg3083.template.oauth2.model.bo.UserBO;

/***
 *
 * @author Gimi
 * @date 2021/7/19 21:26
 *
 ***/
@FeignClient(name = "userServiceFegin", url = "http://127.0.0.1:7000/userApi")
public interface IUserServiceFegin {

    /**
     * 根据用户名查询密码，权限等
     * @param loginName
     * @return
     */
    @GetMapping(value = "/selectUserByLoginName")
    UserBO selectUserByLoginName(@RequestParam String loginName);
}
