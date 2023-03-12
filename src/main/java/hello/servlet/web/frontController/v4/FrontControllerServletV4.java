package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap= new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();//url
        ControllerV4 controllerV4 = controllerMap.get(requestURI);
        if(controllerV4 ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404인 경우 정의
            return;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(request);
        Map<String,Object> model=new HashMap<>();

        //view resolver: 물리 viewname을 논리로 바꿈
        String viewName = controllerV4.process(paramMap,model);
        MyView myView = viewResolver(viewName);
        myView.render(model,request,response);
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
