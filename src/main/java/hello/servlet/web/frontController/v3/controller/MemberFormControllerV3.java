package hello.servlet.web.frontController.v3.controller;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        System.out.println("success");
        return new ModelView("new-form");
    }
}
