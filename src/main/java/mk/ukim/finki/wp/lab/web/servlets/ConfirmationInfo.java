package mk.ukim.finki.wp.lab.web.servlets;

import org.springframework.context.annotation.Profile;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("servlets")
@WebServlet(name = "confirmation-info-servlet", urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfo(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("clientName", clientName);
        webContext.setVariable("clientAddress", clientAddress);
        webContext.setVariable("ipAddress", req.getRemoteAddr());
        webContext.setVariable("userAgent", req.getHeader("User-Agent"));
        springTemplateEngine.process("confirmationInfo.html", webContext, resp.getWriter());
    }
}
