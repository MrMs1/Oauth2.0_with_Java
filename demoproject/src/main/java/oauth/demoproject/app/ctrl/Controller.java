package oauth.demoproject.app.ctrl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oauth.demoproject.app.resource.UserBody;
import oauth.demoproject.domain.dmainobject.UserDataDomain;
import oauth.demoproject.domain.dmainobject.UserDomain;
import oauth.demoproject.domain.service.UserDataService;
import oauth.demoproject.domain.service.UserService;

@RestController
@RequiredArgsConstructor
public class Controller {

    @NonNull
    UserService userService;

    @NonNull
    UserDataService userDataService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDomain> users(@RequestBody List<UserBody> userBodyList) {

        List<Integer> searchList = userBodyList.stream().map(UserBody::getUserId).collect(Collectors.toList());

        return this.userService.findUser(searchList);
    }

    @GetMapping("/userdata")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDataDomain> userdata(@RequestBody @RequestParam("offset") int offset,
            @RequestParam("limit") int limit) {
        return this.userDataService.findUserData(offset, limit);
    }
}
