package PresentationLayer;


import FunctionLayer.Exceptions.LoginSampleException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Tables.ItemList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class that takes requests from adminItemList.jsp and choose witch query through LogicFacade to<br>
 * populate, and thereafter return updated Item List to jsp page.
 *
 * @author Alexander Pihl, Mick Larsen, Morten Rahbek, Per Kringelbach, Jean-Poul Leth-Møller
 */
public class AdminItemListDB extends Command {

    /**
     * Get request from jsp page and populate data to DB and return new item list
     *
     * @param request request for Http Servlet
     * @param response response for Http Servlet
     * @return adminItemList
     * @throws LoginSampleException LoginSampleException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException, ClassNotFoundException {
        // Initializing session variable with current session
        HttpSession session = request.getSession();


        // Get parameters from adminItemList.jsp
        int queryChoice = Integer.parseInt(request.getParameter("queryChoice"));
        String itemListId = request.getParameter("itemListId");
        String material_type = request.getParameter("material_type");
        String material = request.getParameter("material");
        String description = request.getParameter("description");
        String amounts = request.getParameter("amounts");
        String unit = request.getParameter("unit");
        String price_per_unit = request.getParameter("price_per_unit");

        int itemListIds = 0;
        int amountss = 0;
        double pricePerUnit = 0;

        if(itemListId != null) {
            itemListIds = Integer.parseInt(itemListId);
        }

        if(amounts != null) {
            amountss = Integer.parseInt(amounts);
        }

        if(price_per_unit != null) {
            pricePerUnit = Double.parseDouble(price_per_unit);
        }

        switch (queryChoice) {
            case 1: // insert
                //System.out.println("insert");
                //System.out.println("mt:"+material_type+" m:"+material+" d:"+description+" a:"+amountss+" u:"+unit+" ppu:"+pricePerUnit);
                LogicFacade.createItemList(material_type,material,description,amountss,unit,pricePerUnit);
                break;
            case 2: // update
                //System.out.println("update");
                //System.out.println("id:"+itemListIds+"mt:"+material_type+" m:"+material+" d:"+description+" a:"+amountss+" u:"+unit+" ppu:"+pricePerUnit);
                LogicFacade.updateItemList(itemListIds,material_type,material,description,amountss,unit,pricePerUnit);
                break;
            case 3: // delete
                //System.out.println("delete");
                //System.out.println("id:"+itemListIds);
                LogicFacade.deleteItemList(itemListIds);
                break;
            default:

        }


        // Initializing List with ItemList object
        List<ItemList> itemList = (List<ItemList>) session.getAttribute("itemList");


        // Singleton to initialize an instance of MeasurementUnits
        // if List is empty
        if (itemList == null) {
            itemList = LogicFacade.getItemListAdmin();
        } else {
            itemList = (List<ItemList>) session.getAttribute("itemList");
        }


        // Attribute to use on jsp site
        request.setAttribute("itemList", itemList);

        return "adminItemList";
    }
}
