package hello.servlet.web.frontController.v5;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontController.v5.adapter.ConvertV3handlerAdapter;
import hello.servlet.web.frontController.v5.adapter.ConvertV4handlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet(name="/frontControllerServletsV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletsV5 extends HttpServlet {
    private final Map<String,Object> handlerMappingMap=new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters=new ArrayList<>();
    public FrontControllerServletsV5(){
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ConvertV3handlerAdapter());
        handlerAdapters.add(new ConvertV4handlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV3());    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler=getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404인 경우 정의
            return;
        }
        MyHandlerAdapter adapter=getHandlerAdapter(handler);
        ModelView mv=adapter.handle(request,response,handler);

        //view resolver: 물리 viewname을 논리로 바꿈
        String viewName = mv.getViewName();
        MyView myView = viewResolver(viewName);
        myView.render(mv.getModel(), request, response);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName+ ".jsp");
    }
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if(handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("No handlerAdapter. handler="+handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();//url
        return handlerMappingMap.get(requestURI);
    }

}
