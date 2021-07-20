package work.gg3083.template.oauth2.model.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import work.gg3083.template.oauth2.model.bo.UserBO;

import java.util.Collection;
import java.util.List;

/***
 *
 * @author Gimi
 * @date 2021/7/19 21:34
 *
 ***/
public class UserVO extends User {
    private Integer id;
    private String loginName;
    private String userName;
    private String passWord;
    private List<String> roleList;
    private List<String> permList;

    public UserVO(UserBO userBO, Collection<? extends GrantedAuthority> authorities){
        super(userBO.getLoginName(), userBO.getPassWord(), authorities);
        this.id = userBO.getId();
        this.loginName = userBO.getLoginName();
        this.userName = userBO.getUserName();
        this.passWord = userBO.getPassWord();
        this.roleList = userBO.getRoleList();
        this.permList = userBO.getPermList();
    }

    public UserVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserVO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getPermList() {
        return permList;
    }

    public void setPermList(List<String> permList) {
        this.permList = permList;
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", roleList=" + roleList +
                ", permList=" + permList +
                '}';
    }
}
