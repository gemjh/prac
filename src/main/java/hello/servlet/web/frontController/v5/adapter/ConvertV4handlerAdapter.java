package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v4.ControllerV4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConvertV4handlerAdapter implements hello.servlet.web.frontController.v5.MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controllerV4 = (ControllerV4) handler;
        Map<String,String> paramMap=createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();
        String viewName=controllerV4.process(paramMap,model);
        ModelView view = new ModelView(viewName);
        view.setModel(model);
        return view;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap=new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
