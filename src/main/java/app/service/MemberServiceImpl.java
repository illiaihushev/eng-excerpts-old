package app.service;

import app.model.Member;
import app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl extends GenericTrackableEntityServiceAbstr<Member, Member> implements MemberService  {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member findByLogin(String login) {
        return memberRepository.findByLogin(login);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member createMember(String login, String password, String email) {
        return new Member(login, password, email);
    }

    @Override
    public boolean isLoginPasswordValid(String login, String password) {
//        return memberRepository.findByLoginAndPassword(login, password) != null;
        return true;
    }

    public boolean isChangesValuable(Member oldMember, Member newMember) {
        return !oldMember.getLogin().equals(newMember.getLogin());
    }

    @Override
    public void cascadeUpdateLastModifiedProp(Member child, int depth, boolean isActualLastModified) {
        if (!isActualLastModified) {
            child.setLastModified(new Timestamp(System.currentTimeMillis()));
        }
    }

    @Override
    public int getOnUpdateDepth() {
        return -1;
    }

    @Override
    public int getOnCreateDepth() {
        return -1;
    }

    @Override
    public CrudRepository<Member, Long> getRepository() {
        return memberRepository;
    }

    @Override
    public GenericTrackableEntityService getParentService() {
        throw new UnsupportedOperationException("Member is root of hierarchy");
    }

    @Override
    public Member getParentEntity(Member child) {
        throw new UnsupportedOperationException("Member has no parent");
    }
}
