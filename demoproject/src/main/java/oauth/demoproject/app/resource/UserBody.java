package oauth.demoproject.app.resource;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UserBody {

    @NotNull
    private int userId;

    private String name;

    @NotNull
    private int age;
}
