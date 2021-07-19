package work.gg3083.template.oauth2.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import work.gg3083.template.oauth2.fegin.IUserServiceFegin;
import work.gg3083.template.oauth2.model.bo.UserBO;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component(value = "kiteUserDetailsService")
public class KiteUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserServiceFegin userServiceFegin;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("usernameis:" + username);
        if (Strings.isEmpty(username)){
            throw new UsernameNotFoundException("用户不能为空");
        }
        UserBO userBO = userServiceFegin.selectUserByLoginName(username);

        if (userBO == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userBO.getRoleList().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role));
        });
        // 线上环境应该通过用户名查询数据库获取加密后的密码
        String password = passwordEncoder.encode(userBO.getPassWord());
        return new org.springframework.security.core.userdetails.User(username,password, authorities);
    }
}