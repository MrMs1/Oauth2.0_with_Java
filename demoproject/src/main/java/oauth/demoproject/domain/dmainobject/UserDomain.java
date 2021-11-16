package oauth.demoproject.domain.dmainobject;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDomain {

    private int userId;

    private String name;

    private int age;

    private List<UserDataDomain> userDataDmainList;

}
