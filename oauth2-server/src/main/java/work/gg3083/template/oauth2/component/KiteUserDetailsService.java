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
import work.gg3083.template.oauth2.model.vo.UserVO;

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
        log.info("当前想登录用户为: {}", username);
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
        String password = passwordEncoder.encode(userBO.getPassWord());
        userBO.setPassWord(password);
        return new UserVO(userBO, authorities);
    }
}