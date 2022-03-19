package oauth.demoproject.domain.repository;

import java.util.List;

import oauth.demoproject.domain.dmainobject.UserDomain;

public interface UserRepository {

    List<UserDomain> findAll(List<Integer> targets);

    Integer findMax();
}
