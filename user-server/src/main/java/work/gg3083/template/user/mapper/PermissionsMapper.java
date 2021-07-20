package work.gg3083.template.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import work.gg3083.template.user.entity.Permissions;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
public interface PermissionsMapper extends BaseMapper<Permissions> {

    List<Permissions> findPermByLoginName(String loginName);

    List<Permissions> list(@Param("searchKey") String searchKey);
}
