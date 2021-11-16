package oauth.demoproject.domain.dmainobject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataDomain {

    private int userDataId;

    private int userId;

    private String userSubdataType;
}
