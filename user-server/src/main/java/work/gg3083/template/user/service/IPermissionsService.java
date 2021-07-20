package work.gg3083.template.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.gg3083.template.base.model.vo.PageInfo;
import work.gg3083.template.user.entity.Permissions;
import work.gg3083.template.user.model.param.PermAddParam;
import work.gg3083.template.user.model.param.PermUpdateParam;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface IPermissionsService extends IService<Permissions> {

    List<Permissions> findPermByLoginName(String loginName);

    PageInfo<Permissions> list(Integer pageNo, Integer pageSize, String searchKey);

    int update(Integer id, PermUpdateParam param);

    int add(PermAddParam param);

    Permissions get(Integer id);

    int delete(Integer id);
}
