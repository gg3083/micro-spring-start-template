package work.gg3083.template.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.user.entity.User;
import work.gg3083.template.user.model.bo.UserBO;
import work.gg3083.template.user.model.param.UserAddParam;
import work.gg3083.template.user.model.param.UserUpdateParam;
import work.gg3083.template.user.model.vo.PageInfo;
import work.gg3083.template.user.model.vo.UserAdminVO;
import work.gg3083.template.user.model.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询密码，权限等
     * @param loginName
     * @return
     */
    UserBO selectUserByLoginName(String loginName);

    UserVO findUserVoByLoginName(String loginName);

    void register(String loginName,String password);

    PageInfo<UserAdminVO> list4Page(Integer pageNo, Integer pageSize, String searchKey);

    void add(UserAddParam param);

    void update(Integer id, UserUpdateParam param);

    User get(Integer id);

    int delete(Integer id);

    UserVO findUserVoByUserId(String id);
}
