package oauth.demoproject.app.ctrl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

@Validated
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Controller {

    @NonNull
    UserService userService;

    @NonNull
    UserDataService userDataService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDomain> users(@RequestBody @Valid List<UserBody> userBodyList) {

        List<Integer> searchList = userBodyList.stream().map(UserBody::getUserId).collect(Collectors.toList());

        return this.userService.findUser(searchList);
    }

    @GetMapping("/userdata")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDataDomain> userdata(@RequestBody @Valid @NotNull @RequestParam("offset") Integer offset,
            @RequestParam("limit") @NotNull(message = "{validated.blank}") @Digits(integer = 3, fraction = 0) @Max(value = 100, message = "{UserData.limit.max}") Integer limit) {
        return this.userDataService.findUserData(offset, limit);
    }

    @GetMapping("/usersubdata")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDataDomain> usersubdata(@RequestBody @RequestParam("offset") int offset,
            @RequestParam("limit") int limit) {
        return this.userDataService.findUserData(offset, limit);
    }
}
