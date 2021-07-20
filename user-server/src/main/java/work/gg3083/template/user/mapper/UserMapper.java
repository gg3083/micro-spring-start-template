package work.gg3083.template.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import work.gg3083.template.user.entity.User;
import work.gg3083.template.user.model.vo.UserAdminVO;
import work.gg3083.template.user.model.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface UserMapper extends BaseMapper<User> {

    UserVO findUserVoByLoginName(String loginName);

    UserVO findUserVoByUserId(String userId);

    List<UserAdminVO> list4Page(@Param("searchKey") String searchKey, Page<UserAdminVO> page);
}
