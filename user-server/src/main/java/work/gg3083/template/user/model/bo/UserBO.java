package work.gg3083.template.user.model.bo;

import lombok.Data;

import java.util.List;

/***
 *
 * @author Gimi
 * @date 2021/7/19 21:34
 *
 ***/
@Data
public class UserBO {
    private Integer id;
    private String loginName;
    private String userName;
    private String passWord;
    private List<String> roleList;
    private List<String> permList;

}
