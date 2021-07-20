package work.gg3083.template.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.gg3083.template.user.entity.UserRole;
import work.gg3083.template.user.mapper.UserRoleMapper;
import work.gg3083.template.user.service.IUserRoleService;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
