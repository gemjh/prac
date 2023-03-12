package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);

}
