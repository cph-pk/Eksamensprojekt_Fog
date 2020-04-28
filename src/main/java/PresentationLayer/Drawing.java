package PresentationLayer;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Drawing extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        Svg svg = new Svg(0, 0, 1000,1000,"0,0,1000,1000");

        Svg innerDrawing = new Svg(75, 20, 900,800,"0,0,900,800");

        //Ramme
        innerDrawing.addRect(-75,-20, 1000,1000);

        //Mål på hele carport
        innerDrawing.addRect(75,20,600.0,780.0);

        //Skur
        innerDrawing.addRect(599,21,400,250);

        //Skur stolper
        innerDrawing.addRect(599,21,9.7,10.0);
        innerDrawing.addRect(840,21,9.7,10.0);
        innerDrawing.addRect(599,410,9.7,10.0);
        innerDrawing.addRect(840,410,9.7,10.0);

        //Remme
        innerDrawing.addRect(75,55,4.5,780.0);
        innerDrawing.addRect(75,585,4.5,780.0);

        //Spær
        innerDrawing.addRect(75,20,600.0,4.5);
        innerDrawing.addRect(130,20,600.0,4.5);
        innerDrawing.addRect(185,20,600.0,4.5);
        innerDrawing.addRect(240,20,600.0,4.5);
        innerDrawing.addRect(295,20,600.0,4.5);
        innerDrawing.addRect(350,20,600.0,4.5);
        innerDrawing.addRect(405,20,600.0,4.5);
        innerDrawing.addRect(460,20,600.0,4.5);
        innerDrawing.addRect(515,20,600.0,4.5);
        innerDrawing.addRect(570,20,600.0,4.5);
        innerDrawing.addRect(625,20,600.0,4.5);
        innerDrawing.addRect(680,20,600.0,4.5);
        innerDrawing.addRect(735,20,600.0,4.5);
        innerDrawing.addRect(790,20,600.0,4.5);
        innerDrawing.addRect(850,20,600.0,4.5);

        //Lægter
        innerDrawing.addRect(75,20,4.5,780.0);
        innerDrawing.addRect(75,55,4.5,780.0);
        innerDrawing.addRect(75,81,4.5,780.0);
        innerDrawing.addRect(75,107,4.5,780.0);
        innerDrawing.addRect(75,133,4.5,780.0);
        innerDrawing.addRect(75,159,4.5,780.0);
        innerDrawing.addRect(75,185,4.5,780.0);
        innerDrawing.addRect(75,211,4.5,780.0);
        innerDrawing.addRect(75,237,4.5,780.0);
        innerDrawing.addRect(75,263,4.5,780.0);
        innerDrawing.addRect(75,289,4.5,780.0);
        // ----------------------------------------------------- //
        innerDrawing.addRect(75,325,4.5,780.0);
        innerDrawing.addRect(75,351,4.5,780.0);
        innerDrawing.addRect(75,377,4.5,780.0);
        innerDrawing.addRect(75,403,4.5,780.0);
        innerDrawing.addRect(75,429,4.5,780.0);
        innerDrawing.addRect(75,455,4.5,780.0);
        innerDrawing.addRect(75,481,4.5,780.0);
        innerDrawing.addRect(75,507,4.5,780.0);
        innerDrawing.addRect(75,533,4.5,780.0);
        innerDrawing.addRect(75,559,4.5,780.0);
        innerDrawing.addRect(75,585,4.5,780.0);
        innerDrawing.addRect(75,620,4.5,780.0);

        //Stolper
        innerDrawing.addRect(185,52,9.7,10.0);
        innerDrawing.addRect(495,52,9.7,10.0);
        innerDrawing.addRect(805,52,9.7,10.0);
        innerDrawing.addRect(185,582,9.7,10.0);
        innerDrawing.addRect(495,582,9.7,10.0);
        innerDrawing.addRect(805,582,9.7,10.0);

        //Kryds
        innerDrawing.addDotLine(135,20,625,620);
        innerDrawing.addDotLine(135,620,625,20);

        //Pile
        innerDrawing.addLine(40,20,40,620);
        innerDrawing.addLine(75,650,855,650);

        //Text
        innerDrawing.addLowerText(465,670, 780);
        innerDrawing.addUpperText(30,300, 600);

        request.setAttribute("svgInnerDrawing", innerDrawing.toString());

        //request.setAttribute("svgdrawing", svg.toString());

        return "drawing";
    }
}
