package PresentationLayer;

import FunctionLayer.CustomerQuote;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 The purpose of Login is to have a user role before you can access the admin page

 @author kasper
 */
public class Login extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response ) throws LoginSampleException, SQLException {
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        User user = LogicFacade.login( email, password );

        HttpSession session = request.getSession();

        session.setAttribute( "user", user );
        session.setAttribute( "role", user.getRole() );
        session.setAttribute("email", email);  // ellers skal man skrive  user.email på jsp siderne og det er sgu lidt mærkeligt at man har adgang til private felter. Men måske er det meget fedt , jeg ved det ikke

        //Initializers for display info on adminpage.jsp
        List<CustomerQuote> customerQuoteList = LogicFacade.getCustomerQuoteList();
        int userSum = LogicFacade.getUserSum();
        int quoteSum = LogicFacade.getQuoteSum();

        request.setAttribute("userquotelist", customerQuoteList);
        request.setAttribute("quotesum", quoteSum);
        request.setAttribute("usersum", userSum);

        return user.getRole() + "page";
    }

}
