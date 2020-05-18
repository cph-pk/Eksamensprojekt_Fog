package PresentationLayer;

import FunctionLayer.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CarportCalcPage extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {



        /**
         * 1. Hent alle user-input fra carportcustomerize.jsp til variabler
         * 2. Lav if/else eller switch-case og brug tag-type til at finde users valg
         * 3. Indsæt først user info til createUserQuote igennem logicFacade til DataMapper
         *    som returnere users_id
         * 4. Indsæt derefter alle de valgte mål samt user_id fra user til createQuoteOrder
         *    igennem logicFacede til DataMapper som returnere order_id
         * 5. ?????? lav beregning og indsæt de enkelte linier sammen med order_id til
         *    createQuoteOrderline ??????
         * 6. return til 'Tak for ordreforspørgelse' jsp-side
         */

        // User info
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String zipcodeCity = request.getParameter("zipcodeCity");
        int telephone = Integer.parseInt(request.getParameter("telephone"));
        String email = request.getParameter("email");
        String comments = request.getParameter("comments");
        String commentsTrimmed = comments.trim(); // trims the trailing and leading spaces from comments

        // Carport measurements
        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        String roofFlat = request.getParameter("roofFlat");
        String roofRaised = request.getParameter("roofRaised");
        String roofOptionDegrees = request.getParameter("roofOptionDegrees");
        String shedWidth = request.getParameter("shedWidth");
        String shedLength = request.getParameter("shedLength");

        // Roof option
        int roofOption = Integer.parseInt(request.getParameter("roofOption"));

        // insert user proposition and return userId
        int userId = LogicFacade.createUserQuote(name,address,zipcodeCity,telephone,email,commentsTrimmed);


        // initialize variables
        String roofType = null;
        int pitch;
        int orderId = 0;
        int roofDegrees = 0;
        int shedW = 0;
        int shedL = 0;

        // check if roofOptionDegrees is not empty and parse to int
        if(!roofOptionDegrees.isEmpty()) {
            roofDegrees = Integer.parseInt(roofOptionDegrees);
        }

        // check if shedWidth is not empty and parse to int
        if(!shedWidth.isEmpty()) {
            shedW = Integer.parseInt(shedWidth);
        }

        // check if shedLength is not empty and parse to int
        if(!shedLength.isEmpty()) {
            shedL = Integer.parseInt(shedLength);
        }
        
        // insert into order using switch case to choose between flat or raised roof
        switch (roofOption) {
            case 0:
                pitch = 0;
                roofType = "fladt";
                orderId = LogicFacade.createQuoteOrder(userId,carportWidth,carportLength,shedW,shedL,roofType,roofFlat,pitch);
                break;
            case 1:
                roofType = "rejst";
                orderId = LogicFacade.createQuoteOrder(userId,carportWidth,carportLength,shedW,shedL,roofType,roofRaised,roofDegrees);
                break;
        }

        CarportCalculation cpCalc = new CarportCalculation(orderId);

        try {
            PriceCalculator pcCalc = new PriceCalculator(cpCalc);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Create date for proposition receipt
        Locale dk = new Locale("da","DK");
        LocalDateTime receiptDate = LocalDateTime.now();
        DateTimeFormatter receiptFormatDate = DateTimeFormatter.ofPattern("d. MMM yyyy", dk);
        String formattedDate = receiptDate.format(receiptFormatDate);

        // User info for receipt
        request.setAttribute("name",name);
        request.setAttribute("address",address);
        request.setAttribute("zipcodeCity",zipcodeCity);
        request.setAttribute("telephone",telephone);
        request.setAttribute("email",email);
        request.setAttribute("commentsTrimmed",commentsTrimmed);
        request.setAttribute("date",formattedDate);
        
        // Carport measurements for receipt
        request.setAttribute("carportWidth",carportWidth);
        request.setAttribute("carportLength",carportLength);
        request.setAttribute("roofFlat",roofFlat);
        request.setAttribute("roofRaised",roofRaised);
        request.setAttribute("roofOptionDegrees",roofOptionDegrees);
        request.setAttribute("shedWidth",shedWidth);
        request.setAttribute("shedLength",shedLength);
        request.setAttribute("roofType",roofType);
        request.setAttribute("roofOption",roofOption);
        
        return "receipt";
    }
}
