package oauth.demoproject.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oauth.demoproject.domain.dmainobject.UserDataDomain;
import oauth.demoproject.domain.repository.UserDataRepository;

@Service
@RequiredArgsConstructor
public class UserDataService {

    @NonNull
    private final UserDataRepository userDataRepository;

    public List<UserDataDomain> findUserData(int offset, int limit) {
        return this.userDataRepository.findByData(offset, limit);
    }
}
