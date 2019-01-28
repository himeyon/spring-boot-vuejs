package jp.co.takt.s2.bento.domain;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.takt.s2.bento.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Iterable<User> getUsers() {
		return this.userRepository.findAll(new Sort(Direction.ASC, "id"));
	}

	public User getUserById(long id) {
		return this.userRepository.findById(id).get();
	}

	public long insert(String name) {
		User user = new User(name, "normal", "valid", LocalDateTime.now());
		this.userRepository.save(user);

		return user.getId();
	}

	public long update(User user) {
		// lock
		User findUser = this.userRepository.findById(user.getId()).get();
		this.modelMapper.map(user, findUser);
		this.userRepository.save(findUser);

		return user.getId();
	}
}
