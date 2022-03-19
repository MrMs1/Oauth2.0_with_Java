package oauth.demoproject.infra.repository.users;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import oauth.demoproject.infra.entity.UserEntity;

public class UserSpecinfications {

    public static Specification<UserEntity> userIdList(Integer userid) {
        return new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("userId"), userid);
            }
        };

    }
}
