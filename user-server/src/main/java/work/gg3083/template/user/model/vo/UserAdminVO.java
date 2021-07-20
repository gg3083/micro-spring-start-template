package work.gg3083.template.user.model.vo;

import lombok.Data;
import work.gg3083.template.user.entity.User;

/**
 * @author Gimi
 * @date 2021-07-03 17:04
 */
@Data
public class UserAdminVO extends User {
    private String roleName;
    private Integer roleId;
}
