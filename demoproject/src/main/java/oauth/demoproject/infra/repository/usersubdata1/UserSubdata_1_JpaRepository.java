package oauth.demoproject.infra.repository.usersubdata1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import oauth.demoproject.infra.entity.UserSubdataKey;
import oauth.demoproject.infra.entity.UserSubdata_1_Entity;

public interface UserSubdata_1_JpaRepository
        extends JpaRepository<UserSubdata_1_Entity, UserSubdataKey>, JpaSpecificationExecutor<UserSubdata_1_Entity> {

}
