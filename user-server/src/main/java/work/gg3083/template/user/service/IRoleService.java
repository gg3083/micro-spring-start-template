package work.gg3083.template.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.base.model.vo.PageInfo;
import work.gg3083.template.user.entity.Role;
import work.gg3083.template.user.model.param.RoleAddParam;
import work.gg3083.template.user.model.param.RoleUpdateParam;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface IRoleService extends IService<Role> {

    int add(RoleAddParam param);

    int update(Integer id, RoleUpdateParam param);

    Role get(Integer id);

    PageInfo<Role> list4Page(Integer pageNo, Integer pageSize, String searchKey);

    int delete(Integer id);
}
