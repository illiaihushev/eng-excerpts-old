package app.repository;

import app.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    @Override
    List<Member> findAll();

    Member findByLogin(String login);

    @Override
    Member save(Member member);

    Member findByLoginAndPassword(String login, String password);
}
