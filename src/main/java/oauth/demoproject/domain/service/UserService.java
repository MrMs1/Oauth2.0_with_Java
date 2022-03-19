package oauth.demoproject.domain.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oauth.demoproject.domain.dmainobject.UserDomain;
import oauth.demoproject.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository myRepository;

    public List<UserDomain> findAll() {
        return this.myRepository.findAll(Arrays.asList(1, 2, 3));
    }

    public List<UserDomain> findUser(List<Integer> searchList) {
        return this.myRepository.findAll(searchList);
    }

    public Integer findMax() {
        Integer max = this.myRepository.findMax();
        if (max == null) {
            max = 0;
        }
        return max;
    }

}
