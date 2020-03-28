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
@WebServlet(name = "pizza-order-servlet", urlPatterns = "/PizzaOrder.do")
public class PizzaOrder extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pizzaSize = req.getParameter("pizza_size");
        req.getSession().setAttribute("pizzaSize", pizzaSize);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("deliveryInfo.html", webContext, resp.getWriter());
    }

}
