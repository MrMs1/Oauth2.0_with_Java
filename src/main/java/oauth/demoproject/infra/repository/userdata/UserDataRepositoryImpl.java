package oauth.demoproject.infra.repository.userdata;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oauth.demoproject.domain.dmainobject.UserDataDomain;
import oauth.demoproject.domain.repository.UserDataRepository;
import oauth.demoproject.infra.entity.UserDataEntity;

@Repository
@RequiredArgsConstructor
public class UserDataRepositoryImpl implements UserDataRepository {

    @NonNull
    private final UserDataJpaRepository userDataJpaRepository;

    @Override
    public List<UserDataDomain> findByData(int offset, int limit) {

        return this.userDataJpaRepository.findByUserData(offset, limit)
                .stream()
                .map(UserDataEntity::toDomain)
                .collect(Collectors.toList());
    }
}
