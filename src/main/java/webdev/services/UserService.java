package webdev.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User oldUser = optional.get();
			oldUser.setUsername(user.getUsername());
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			if (user.getPassword() != null) {
				oldUser.setPassword(user.getPassword());
			}
			oldUser.setRole(user.getRole());
			oldUser.setEmail(user.getEmail());
			oldUser.setDateOfBirth(user.getDateOfBirth());
			return userRepository.save(oldUser);
		}
		return null;
	}

	@GetMapping("/api/user?username={username}")
	public User findUserByUsername(@RequestParam("username") String username) {
		Optional<User> optional = userRepository.findUserByUsername(username);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			User user = new User();
			user.setUsername("");
			return user;
		}
	}

	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession httpSession) {
		Optional<User> optional = userRepository.findUserByUsername(user.getUsername());
		if (!optional.isPresent()) {
			User freshUser = new User();
			freshUser.setUsername(user.getUsername());
			freshUser.setPassword(user.getPassword());
			User persistedUser = this.createUser(freshUser);
			return persistedUser;
		}else {
			User dummy = new User();
			return dummy;
		}
	}

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		if (id >= 0)
			userRepository.deleteById(id);
	}

	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpServletResponse response) {
		Optional<User> optional = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (optional.isPresent()) {
			return optional.get();
		} else {
			try {
				response.sendError(404, "User not found");
			} catch (IOException io) {
				System.out.println("Class:" + this.getClass() + " Method: login");
			}
		}
		return null;
	}

	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user) {
		Optional<User> userById = userRepository.findById(user.getId());
		if (userById.isPresent()) {
			User updatedUser = userById.get();
			updatedUser.setUsername(user.getUsername());
			updatedUser.setPhone(user.getPhone());
			updatedUser.setRole(user.getRole());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setDateOfBirth(user.getDateOfBirth());
			return userRepository.save(updatedUser);
		}
		return null;
	}
}
