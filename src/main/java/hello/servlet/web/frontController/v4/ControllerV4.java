package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.ModelView;

import java.util.Map;

public interface ControllerV4 {
    String process(Map<String,String> paramMap,Map<String,Object> model);
}
