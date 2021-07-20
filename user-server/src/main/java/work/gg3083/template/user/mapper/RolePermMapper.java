package work.gg3083.template.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import work.gg3083.template.user.entity.RolePerm;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface RolePermMapper extends BaseMapper<RolePerm> {


    List<String> queryPermStringByRoleId(Integer roleId);

}
