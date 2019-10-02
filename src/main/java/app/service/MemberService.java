package app.service;

import app.model.Member;

import java.util.List;

public interface MemberService extends GenericTrackableEntityService<Member, Member> {
    Member findByLogin(String login);

    Member createMember(String login, String password, String email);

    boolean isLoginPasswordValid(String login, String password);

    Member save(Member member);
}
