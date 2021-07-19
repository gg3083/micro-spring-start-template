package work.gg3083.template.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import work.gg3083.template.user.model.bo.UserBO;
import work.gg3083.template.user.service.IUserService;

import java.util.Arrays;

/***
 *
 * @author Gimi
 * @date 2021/7/19 21:29
 *
 ***/
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Override
    public UserBO selectUserByLoginName(String loginName) {
        log.info("用户{} 开始登录", loginName);
        UserBO userBO = new UserBO();
        userBO.setId(1);
        userBO.setLoginName(loginName);
        userBO.setUserName("张三1");
        userBO.setPassWord("pass");
        userBO.setRoleList(Arrays.asList("ROLE_ADMIN","ROLE_USER"));
        userBO.setPermList(Arrays.asList("system:add","system:update"));
        return userBO;
    }
}
