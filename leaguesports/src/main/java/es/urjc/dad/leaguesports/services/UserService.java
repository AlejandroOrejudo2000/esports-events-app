package es.urjc.dad.leaguesports.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.dad.leaguesports.model.User;
import es.urjc.dad.leaguesports.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired private UserRepository userRepository;

    public void addUser(User user) {

        userRepository.save(user);
    }
    
}
