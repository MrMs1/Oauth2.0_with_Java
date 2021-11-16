package oauth.demoproject.domain.repository;

import java.util.List;

import oauth.demoproject.domain.dmainobject.UserDataDomain;

public interface UserDataRepository {

    List<UserDataDomain> findByData(int offset, int limit);
}
