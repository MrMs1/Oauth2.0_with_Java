package oauth.demoproject.app.resource;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserBody {

    @Min(value = 1, message = "{UserBody.userId.min}")
    @Max(value = 3, message = "{UserBody.userId.max}")
    @NotNull(message = "{UserBody.userId.blank}")
    private Integer userId;

    @Size(min = 1, max = 6, message = "{UserBody.name.size}")
    @NotBlank(message = "{UserBody.name.blank}")
    private String name;

    @NotNull(message = "{UserBody.age.blank}")
    private Integer age;
}
