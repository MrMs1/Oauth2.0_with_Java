package oauth.demoproject.infra.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import oauth.demoproject.infra.entity.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    @Query(value = "SELECT MAX(user_id) FROM users", nativeQuery = true)
    Integer findMax();
}
