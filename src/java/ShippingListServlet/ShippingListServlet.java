
package ShippingListServlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wicncjjd
 */
public class ShippingListServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(); 
        String action = request.getParameter("action");
        String username = (String) session.getAttribute("username"); 
        
        if(username != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                    .forward(request, response);
        }
        else if(session.getAttribute("username") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
        else if(request.getParameter("logout") != null) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").
                    forward(request, response);   
        }
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                    .forward(request, response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); 
        
        String action = request.getParameter("action"); 
        String username = (String) session.getAttribute("username"); 
        session.setAttribute("username", username);
        
        if(action.equals("request")){
            if(username == null && username.isEmpty()) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").
                    forward(request, response);
            return;
        }
            else{
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                    .forward(request, response);
            }
        
        }
        else if(action.equals("add")){
            String item = request.getParameter("addeditem");
            ArrayList<String> itemList=(ArrayList<String>)session.getAttribute("itemlist");
            if (itemList == null) {
            itemList = new ArrayList<>();
            itemList.add(item);
            session.setAttribute("itemlist", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                    .forward(request, response);
            }
            else if (itemList != null) {
            itemList.add(item);
            session.setAttribute("itemlist", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                    .forward(request, response);
            }
            
        }
        else if (action.equals("DELETE")){
            ArrayList<String> itemList=(ArrayList<String>)session.getAttribute("itemlist");
            if (itemList!=null||!itemList.equals("")){
                String selectedItem = request.getParameter("itemSelected");
                itemList.remove(selectedItem);
                session.setAttribute("itemList",itemList);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                        .forward(request, response);

            }
            else {
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                        .forward(request, response);
            }
        }
        else{
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp")
                        .forward(request, response);
        }
    }

}
