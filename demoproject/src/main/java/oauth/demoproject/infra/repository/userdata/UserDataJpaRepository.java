package oauth.demoproject.infra.repository.userdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import oauth.demoproject.infra.entity.UserDataEntity;

@Repository
public interface UserDataJpaRepository extends JpaRepository<UserDataEntity, Integer> {

    @Query(value = "SELECT * FROM user_data OFFSET :offset ROWS FETCH FIRST :limit ROWS ONLY", nativeQuery = true)
    List<UserDataEntity> findByUserData(@Param("offset") int offset, @Param("limit") int limit);
}
