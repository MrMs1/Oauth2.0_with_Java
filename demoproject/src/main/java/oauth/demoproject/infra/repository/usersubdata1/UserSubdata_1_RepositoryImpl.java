package oauth.demoproject.infra.repository.usersubdata1;

import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oauth.demoproject.domain.repository.UserSubdata_1_Repository;

@Repository
@RequiredArgsConstructor
public class UserSubdata_1_RepositoryImpl implements UserSubdata_1_Repository {

    @NonNull
    private final UserSubdata_1_JpaRepository userSubdata_1_JpaRepository;
}
