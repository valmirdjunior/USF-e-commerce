package br.com.dpaulla.mail.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dpaulla.mail.server.model.dto.UserBanco;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserBanco, Integer> {
	
	@Query(value = "select * from user o where o.user_id = ?1", nativeQuery = true)
	public UserBanco findByUserId(int idUser);
	
}