package work.gg3083.template.user.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import work.gg3083.template.base.exception.MyException;
import work.gg3083.template.base.model.vo.PageInfo;
import work.gg3083.template.user.entity.Permissions;
import work.gg3083.template.user.entity.RolePerm;
import work.gg3083.template.user.mapper.PermissionsMapper;
import work.gg3083.template.user.model.param.PermAddParam;
import work.gg3083.template.user.model.param.PermUpdateParam;
import work.gg3083.template.user.service.IPermissionsService;
import work.gg3083.template.user.service.IRolePermService;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Override
    public List<Permissions> findPermByLoginName(String loginName) {
        return permissionsMapper.findPermByLoginName(loginName);
    }

    @Override
    public PageInfo<Permissions> list(Integer pageNo, Integer pageSize, String searchKey) {
//        Page<Permissions> page = new Page<>(pageNo,pageSize);
        List<Permissions> list = permissionsMapper.list(searchKey);
        return new PageInfo<>(list);
    }

    @Override
    public int update(Integer id, PermUpdateParam param) {
        checkExistPerm(param.getPermAlias(), param.getParentId());
        Permissions permissions = new Permissions()
                .setId(id)
                .setIcon(param.getIcon())
                .setPermName(param.getPermName())
                .setParentId(param.getParentId())
                .setPermAlias(param.getPermAlias())
                .setUrl(param.getUrl());
        return permissionsMapper.updateById(permissions);
    }

    private void checkExistPerm(String permAlias, Integer parentId) {
        LambdaQueryChainWrapper<Permissions> eq = this.lambdaQuery().eq(Permissions::getPermAlias, permAlias);
        if (this.list(eq.getWrapper()).size() > 0){
            throw new MyException("重复的key值！");
        }
        final LambdaQueryChainWrapper<Permissions> parentIdEq = this.lambdaQuery().eq(Permissions::getId, parentId);
        if (CollectionUtils.isEmpty(this.list(parentIdEq.getWrapper()))){
            throw new MyException("不存在的父级id！");
        }
    }

    @Override
    public int add(PermAddParam param) {
        checkExistPerm(param.getPermAlias(), param.getParentId());
        Permissions permissions = new Permissions()
                .setIcon(param.getIcon())
                .setPermName(param.getPermName())
                .setParentId(param.getParentId())
                .setUrl(param.getUrl())
                .setPermAlias(param.getPermAlias());
        this.save(permissions);
        return permissions.getId();
    }

    @Override
    public Permissions get(Integer id) {
        return this.getById(id);
    }


    @Autowired
    private IRolePermService rolePermService;


    @Override
    public int delete(Integer id) {
        LambdaQueryChainWrapper<RolePerm> eq = rolePermService.lambdaQuery().eq(RolePerm::getPermId, id);
        if (rolePermService.list(eq.getWrapper()).size() > 0) {
            throw new MyException("该权限下绑定了角色，不允许删除！");
        }
        return permissionsMapper.deleteById(id);
    }
}
