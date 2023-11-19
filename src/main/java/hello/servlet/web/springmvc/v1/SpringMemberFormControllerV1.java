package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberFormControllerV1 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        ModelAndView mv = new ModelAndView("save");
        mv.addObject(model);

        return mv;
    }

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        ModelAndView mv = new ModelAndView("members");
        mv.addObject(model);

        return mv;
    }
}
