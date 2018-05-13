package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>)userRepository.findAll();
	}
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(Integer userId) {
		return null;
	}
	@PutMapping("/api/user/{userId}")
	public User updateUser(@RequestBody User user) {
		Optional<User> optional = userRepository.findById(user.getId());
		if(optional.isPresent()) {
			User oldUser = optional.get();
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setPassword(user.getPassword());
			oldUser.setRole(user.getEmail());
			oldUser.setEmail(user.getEmail());
			return oldUser;
		}
		return null;
	}
	
}
