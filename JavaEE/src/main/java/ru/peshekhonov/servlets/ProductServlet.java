package ru.peshekhonov.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.peshekhonov.data.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);
    private final List<Product> productList = new CopyOnWriteArrayList<>();

    @Override
    public void init() {
        int id = 1;
        productList.add(new Product(id++, "Milk", 75));
        productList.add(new Product(id++, "Cheese", 750));
        productList.add(new Product(id++, "Black bread", 45));
        productList.add(new Product(id++, "White bread", 50));
        productList.add(new Product(id++, "Tomatoes", 200));
        productList.add(new Product(id++, "Cucumber", 170));
        productList.add(new Product(id++, "Cabbage", 87));
        productList.add(new Product(id++, "Potatoes", 75));
        productList.add(new Product(id++, "Eggplants", 210));
        productList.add(new Product(id++, "Courgette", 160));
        productList.add(new Product(id++, "Red Bulgarian pepper", 375));
        productList.add(new Product(id++, "Yellow Bulgarian pepper", 420));
        productList.add(new Product(id++, "Apples", 100));
        productList.add(new Product(id++, "Pears", 170));
        productList.add(new Product(id++, "Grape", 250));
        productList.add(new Product(id++, "Strawberry", 350));
        productList.add(new Product(id++, "Cherry", 500));
        productList.add(new Product(id++, "Onion", 85));
        productList.add(new Product(id++, "Carrot", 50));
        productList.add(new Product(id++, "Beetroot", 96));
        logger.info("List of products was created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 10) {
            set.add(random.nextInt(productList.size()));
        }
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        out.printf("<!DOCTYPE html><html><style>li{font-size: 25px;font-weight: bold;color: #1A5AD7;}</style><head></head><body><ol>");
        set.stream().forEach(value -> {
            out.printf("<li><pre>%s</pre></li>", productList.get(value));
        });
        out.printf("</ol></body></html>");
        out.close();
    }
}
