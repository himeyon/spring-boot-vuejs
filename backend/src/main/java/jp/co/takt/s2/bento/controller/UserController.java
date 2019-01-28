package jp.co.takt.s2.bento.controller;

import jp.co.takt.s2.bento.domain.User;
import jp.co.takt.s2.bento.domain.UserService;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class UserController {

	//private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	private static final Type USER_LIST_TYPE = new TypeToken<Iterable<UserForm>>() {
	}.getType();

	@RequestMapping(path = "/user", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody long insert(@RequestParam String name) {
		return userService.insert(name);
	}

	@RequestMapping(path = "/user", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody long update(UserForm form) {

		User user = this.modelMapper.map(form, User.class);
		user.setRegistered(LocalDateTime.now());
		return userService.update(user);
	}

	@GetMapping(path = "/user/{id}")
	public @ResponseBody UserForm getUserById(@PathVariable("id") long id) {

		return this.modelMapper.map(userService.getUserById(id), UserForm.class);
	}

	@GetMapping(path = "users")
	public @ResponseBody Iterable<UserForm> getUsers() {

		Iterable<User> users = userService.getUsers();

		Iterable<UserForm> userList = this.modelMapper.map(users, USER_LIST_TYPE);

		return userList;
	}

}
