package work.gg3083.template.user.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.base.model.vo.JsonBack;
import work.gg3083.template.user.model.param.RoleAddParam;
import work.gg3083.template.user.model.param.RoleUpdateParam;
import work.gg3083.template.user.service.IRoleService;
import work.gg3083.template.user.service.impl.RoleServiceImpl;


/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/role")
@Api(value="角色", tags={"用户相关"})
public class RoleController {

    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService){
        this.roleService = roleService;
    }


    @GetMapping("list")
    public JsonBack list(@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                         String searchKey){
        return JsonBack.buildSuccJson(roleService.list4Page(pageNo,pageSize,searchKey));
    }

    @PostMapping("add")
    public JsonBack add(@RequestBody @Validated RoleAddParam param){
        return JsonBack.buildSuccJson(roleService.add(param));
    }

    @PostMapping("/update/{id}")
    public JsonBack update(@PathVariable Integer id, @RequestBody @Validated RoleUpdateParam param){
        return JsonBack.buildSuccJson(roleService.update(id, param));
    }

    @GetMapping("get")
    public JsonBack get(@RequestParam Integer id){
        return JsonBack.buildSuccJson(roleService.get(id));
    }

    @PostMapping("/delete/{id}")
    public JsonBack delete(@PathVariable Integer id){
        return JsonBack.buildSuccJson(roleService.delete(id));
    }
}

