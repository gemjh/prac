package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.ControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap= new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//url
        ControllerV3 controllerV3 = controllerMap.get(requestURI);
        if(controllerV3 ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404인 경우 정의
            return;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controllerV3.process(paramMap);

        //view resolver: 물리 viewname을 논리로 바꿈
        String viewName=mv.getViewName();
        MyView myView = viewResolver(viewName);
        myView.render(mv.getModel(),request,response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName+ ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap=new HashMap<>();
        request.getParameterNames().asIterator().
                forEachRemaining(paramName->paramMap.put(paramName,request.getParameter(paramName)));

        return paramMap;
    }
}
