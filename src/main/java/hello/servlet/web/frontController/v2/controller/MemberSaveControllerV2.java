package hello.servlet.web.frontController.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v1.ControllerV1;
import hello.servlet.web.frontController.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    public MemberRepository memberRepository=MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        request.setAttribute("member", member);

//        String viewPath = "/WEB-INF/views/save-result.jsp";
//        System.out.println("success");
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

        return new MyView("/WEB-INF/views/save-result.jsp");

    }
}
