package bstorm.akimts.exohotel.web.servlets;

import bstorm.akimts.exohotel.exceptions.DuplicatePathException;
import bstorm.akimts.exohotel.web.annotations.Controller;
import bstorm.akimts.exohotel.web.annotations.GetRequest;
import bstorm.akimts.exohotel.web.controllers.BaseController;
import bstorm.akimts.exohotel.web.controllers.RoomController;
import bstorm.akimts.exohotel.web.utils.HttpMethod;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@WebServlet(name = "DispatcherServlet", urlPatterns = "/*")
public class DispatcherServlet extends HttpServlet {

    @Inject
    private BeanManager manager;
    @Inject @Any
    private Instance<BaseController> controllers;
    private final Table<HttpMethod, String, Method> mappings = HashBasedTable.create();
    private final Map<Class<? extends BaseController>, BaseController> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {

        Reflections reflections = new Reflections("bstorm.akimts.exohotel.web.controllers", Scanners.TypesAnnotated);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);



        for (Class<?> clazz : classes) {
            Controller controller = clazz.getAnnotation(Controller.class);
            for (Method method : clazz.getMethods()) {

                GetRequest request;
                if( (request= method.getAnnotation(GetRequest.class)) != null ){
                    String path = controller.basePath() + request.path();
                    if( mappings.row(HttpMethod.GET).containsKey( path ) )
                        throw new DuplicatePathException();
                    mappings.put(HttpMethod.GET,path, method);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        Method toExecute = mappings.row(HttpMethod.GET).get(path);
        try{
            BaseController controller = controllers.select((Class<? extends BaseController>) toExecute.getDeclaringClass()).get();
            String view = (String)toExecute.invoke( controller );
            request.getRequestDispatcher(view).forward(request, response);
        }
        catch (Exception ex){
            ex.printStackTrace();
            response.sendError(500, ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
