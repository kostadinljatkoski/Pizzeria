package mk.ukim.finki.wp.lab.web.servlets;


import mk.ukim.finki.wp.lab.service.PizzaService;
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
@WebServlet(name = "show-pizza-servlet", urlPatterns = "")
public class ShowPizza extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final PizzaService pizzaService;

    public ShowPizza(SpringTemplateEngine springTemplateEngine, PizzaService pizzaService) {
        this.springTemplateEngine = springTemplateEngine;
        this.pizzaService = pizzaService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzas", pizzaService.getAllPizzas());
        springTemplateEngine.process("listPizzas.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pizzaType = req.getParameter("pizza");
        req.getSession().setAttribute("pizzaType", pizzaType);
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaType", pizzaType);
        if(pizzaType.equals("Margherita"))
            resp.sendRedirect("");
        springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());
    }
}
