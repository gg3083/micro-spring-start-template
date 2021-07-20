package work.gg3083.template.user.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.gg3083.template.base.model.vo.JsonBack;
import work.gg3083.template.user.model.param.PermAddParam;
import work.gg3083.template.user.model.param.PermUpdateParam;
import work.gg3083.template.user.service.IPermissionsService;


import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Gimi
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/permission")
//@Validated
@Api(value="权限", tags={"用户相关"})
public class PermissionsController {

    @Autowired
    private IPermissionsService permissionsService;

    @PostMapping("getByName")
    public JsonBack getByName(String loginName){
        return JsonBack.buildSuccJson(permissionsService.findPermByLoginName(loginName));
    }

    @GetMapping("list")
    public JsonBack list(@RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                                                String searchKey){
        return JsonBack.buildSuccJson(permissionsService.list(pageNo,pageSize,searchKey));
    }

    @PostMapping("add")
    public JsonBack add(@RequestBody @Valid PermAddParam param){
        return JsonBack.buildSuccJson(permissionsService.add(param));
    }

    @PostMapping("/update/{id}")
    public JsonBack update(@PathVariable @Min(value = 0, message = "id必须大于0")   Integer id, @RequestBody @Valid PermUpdateParam param){
        return JsonBack.buildSuccJson(permissionsService.update(id, param));
    }

    @GetMapping("get")
    public JsonBack get(@RequestParam @Min(value = 0, message = "id必须大于0") Integer id){
        return JsonBack.buildSuccJson(permissionsService.get(id));
    }

    @PostMapping("/delete/{id}" )
    public JsonBack delete(@PathVariable @Min(value = 0, message = "id必须大于0")  Integer id){
        return JsonBack.buildSuccJson(permissionsService.delete(id));
    }
}

