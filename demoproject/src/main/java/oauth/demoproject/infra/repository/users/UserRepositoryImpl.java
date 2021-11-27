package oauth.demoproject.infra.repository.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oauth.demoproject.domain.dmainobject.UserDomain;
import oauth.demoproject.domain.repository.UserRepository;
import oauth.demoproject.infra.entity.UserEntity;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @NonNull
    private final UserJpaRepository userJpaRepository;

    @Override
    public List<UserDomain> findAll(List<Integer> targets) {
        // TODO 自動生成されたメソッド・スタブ
        final Specification<UserEntity> zero = Specification.where((Specification<UserEntity>) null);

        final Specification<UserEntity> spec = targets.stream()
                .map(UserSpecinfications::userIdList)
                .reduce(zero, Specification<UserEntity>::or);

        return this.userJpaRepository.findAll(spec).stream()
                .map(UserEntity::toDomain).collect(Collectors.toList());
    }

}
