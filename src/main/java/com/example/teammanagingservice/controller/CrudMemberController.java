package com.example.teammanagingservice.controller;

import com.example.teammanagingservice.model.Member;
import com.example.teammanagingservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class CrudMemberController {
    private final MemberService service;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = service.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable(name = "id") long id) {
        Member member = service.getMemberById(id);
        if (member == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member addedMember = service.addMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMemberById(@PathVariable long id, @RequestBody Member member) {
        Member updatedMember = service.updateMemberById(id, member);
        if (updatedMember == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMemberById(@PathVariable long id) {
        Member deletedMember = service.deleteMemberById(id);
        if (deletedMember == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(deletedMember);
    }
}
