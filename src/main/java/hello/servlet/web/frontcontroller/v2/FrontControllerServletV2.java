package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListServletControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/members/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controller = new HashMap<>();

    public FrontControllerServletV2() {
        controller.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controller.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controller.put("/front-controller/v2/members/members", new MemberListServletControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = req.getRequestURI();

        ControllerV2 controller = this.controller.get(requestURI);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(req, resp);

        view.render(req, resp);
    }
}
