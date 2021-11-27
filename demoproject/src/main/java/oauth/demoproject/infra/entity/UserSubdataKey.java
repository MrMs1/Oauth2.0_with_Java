package oauth.demoproject.infra.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSubdataKey implements Serializable {

    private int userDataId;

    private int userSubdataId;
}
