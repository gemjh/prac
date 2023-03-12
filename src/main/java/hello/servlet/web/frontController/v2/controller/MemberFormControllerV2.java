package hello.servlet.web.frontController.v2.controller;

import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v1.ControllerV1;
import hello.servlet.web.frontController.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
//        String viewPath = "/WEB-INF/views/new-form.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);//forward: redirect와 비슷한데 화면변환이 없음

        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
