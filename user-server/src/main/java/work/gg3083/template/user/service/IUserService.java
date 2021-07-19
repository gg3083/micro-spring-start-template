package work.gg3083.template.user.service;

import work.gg3083.template.user.model.bo.UserBO;

/***
 *
 * @author Gimi
 * @date 2021/7/19 21:26
 *
 ***/
public interface IUserService {

    /**
     * 根据用户名查询密码，权限等
     * @param loginName
     * @return
     */
    UserBO selectUserByLoginName(String loginName);
}
