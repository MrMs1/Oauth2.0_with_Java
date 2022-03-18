package oauth.demoproject.domain.dmainobject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataDomain {

	private int userDataId;

	private int userId;

	private String userSubdataType;

	public UserDataDomain create(int userDataId, int userId, String userSubdataType) {

		return UserDataDomain.builder().userDataId(userDataId).userId(userId).userSubdataType(userSubdataType).build();
	}
}
