package jp.co.takt.s2.bento.repository;

import jp.co.takt.s2.bento.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByName(@Param("name") String name);
}
