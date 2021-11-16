package oauth.demoproject.infra.repository.userdata;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import oauth.demoproject.domain.dmainobject.UserDataDomain;
import oauth.demoproject.domain.repository.UserDataRepository;
import oauth.demoproject.infra.entity.UserDataEntity;

@Repository
public class UserDataJpaRepositoryImpl implements UserDataRepository {

    private final UserDataJpaRepository userDataJpaRepository;

    @Autowired
    public UserDataJpaRepositoryImpl(@Lazy UserDataJpaRepository userDataJpaRepository) {
        this.userDataJpaRepository = userDataJpaRepository;
    }

    @Override
    public List<UserDataDomain> findByData(int offset, int limit) {

        return this.userDataJpaRepository.findByUserData(offset, limit)
                .stream()
                .map(UserDataEntity::toDomain)
                .collect(Collectors.toList());
    }
}
