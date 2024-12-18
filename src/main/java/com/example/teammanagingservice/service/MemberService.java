package com.example.teammanagingservice.service;

import com.example.teammanagingservice.model.Member;
import com.example.teammanagingservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member getMemberById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Member addMember(Member member) {
        member.setId(null);
        return repository.save(member);
    }

    public Member updateMemberById(long id, Member member) {
        Member result = null;
        if (isMemberWithIdExists(id)) {
            member.setId(id);
            result = repository.saveAndFlush(member);
        }
        return result;
    }

    public Member deleteMemberById(long id) {
        Member member = getMemberById(id);
        if (member != null) {
            repository.deleteById(id);
        }
        return member;
    }

    private boolean isMemberWithIdExists(long id) {
        return repository.existsById(id);
    }
}
