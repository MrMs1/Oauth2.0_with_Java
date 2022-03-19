package oauth.demoproject.app.resource;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserBody {

    @Min(value = 1)
    @Max(value = 3)
    @NotNull
    private Integer userId;

    @Size(min = 1, max = 6)
    @NotBlank
    private String name;

    @NotNull
    private Integer age;
}
