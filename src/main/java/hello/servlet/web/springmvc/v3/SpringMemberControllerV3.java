package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository=MemberRepository.getInstance();

    @GetMapping
    @RequestMapping(value = "/new-form")
    public String newForm() {
        return "new-form";
    }
    @RequestMapping
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "members";
    }
    @PostMapping
    @RequestMapping(value = "/save")
    public String save(String username, int age, Model model){
        Member member=new Member(username,age);
        memberRepository.save(member);
        model.addAttribute("member",member);
        return "save-result";
    }
}
