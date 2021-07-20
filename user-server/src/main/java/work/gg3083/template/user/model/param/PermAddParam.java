package work.gg3083.template.user.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * @author Gimi
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="权限添加类", description="权限添加参数")
public class PermAddParam implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "权限名称")
    @NotBlank(message = "权限名称不能为空")
    private String permName;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "权限简称例如 system:index:main")
//    @NotBlank(message = "权限简称不能为空")
    private String permAlias;

    @ApiModelProperty(value = "父级Id")
    @NotBlank(message = "父级Id不能为空")
    @Min(value = 0)
    private Integer parentId;

    @ApiModelProperty(value = "图标")
    private String icon;
    


}
