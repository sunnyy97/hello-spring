package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
   private final MemberService memberService;
   @Autowired
    public MemberController(MemberService memberService) {
       this.memberService = memberService;
    }

    @GetMapping(value = "/members/new") // GetMapping는 데이터를 조회할 떄 많이 사용
    public String createForm() {
       return "members/createMemeberForm";
    }

    @PostMapping(value = "/members/new") // PostMapping는 데이터를 전달할 때 사용
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 홈화면으로 보냄
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
