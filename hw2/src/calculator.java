import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class calculator
 */
@WebServlet("/calculator")
public class calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		outputHtml(response, null, null, null);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("#,###.00");
		double x = 0, y =0;
		String X = request.getParameter("x");
		String Y = request.getParameter("y");
		if (X == null || X == "") {
			outputHtml(response, X, Y, "X is missing");
			return;
		}
		if (Y == null || Y == "") {
			outputHtml(response, X, Y, "Y is missing");
			return;
		}
		try {
			x = Double.parseDouble(X);
		} catch(NumberFormatException e) {
			outputHtml(response, X, Y, "X is not a number");
			return;
		}
		try {
			y = Double.parseDouble(Y);
		} catch(NumberFormatException e) {
			outputHtml(response, X, Y, "Y is not a number");
			return;
		}
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			outputHtml(response, X, Y, "Operation is missing");
			return;
		}
		String res = null;
		if (action.equals("+")) {
			res = String.valueOf(df.format(x + y));
			
		} else if (action.equals("-")) {
			res = String.valueOf(df.format(x - y));
			
		} else if (action.equals("*")) {
			res = String.valueOf(df.format(x * y));
			
		} else if ((action.equals("/"))) {
			if (y == 0) {
				outputHtml(response, X, Y, "Cannot divide by 0");
				return;
			}
			res = String.valueOf(df.format(x / y));
		} else {
			outputHtml(response, X, Y, "It is not an operation");
			return;
		}
		res = df.format(x) + " " + action + " " + df.format(y) + " = " + res; 
		outputHtml(response, X, Y, res);
	}
	
	private void outputHtml(HttpServletResponse response, String x, String y, String res) 
			throws IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
		out.println("<title>calculator</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"format.css\">");
		out.println("</head>");
		out.println("<body>");

		if(res != null) {
			out.println("<h2 style=\"color:green\">");
			out.print(res);
			out.println("</h2>");
		}
		
		out.println("<p><form action=\"calculator\" method=\"POST\">");
		out.println("<hr />");
		if (x != null) {
			out.println("<div> X: <input type=\"text\" class=\"text\" name=\"x\" value=");
			out.print(x);
			out.println("> <br>");
		} else {
			out.println("<div> X: <input type=\"text\" class=\"text\" name=\"x\"> <br>");
		}
		if ( y!=null) {
			out.println("Y: <input type=\"text\" class=\"text\" name=\"y\" value=");
			out.print(y);
			out.println("> <br> </div>");
		} else {
			out.println("Y: <input type=\"text\" class=\"text\" name=\"y\"> <br> </div>");
		}
		out.println("<div> <input type=\"submit\" class=\"buttons\" name=\"action\" value=\"+\">");
		out.println("<input type=\"submit\" class=\"buttons\" name=\"action\" value=\"-\">");
		out.println("<input type=\"submit\" class=\"buttons\" name=\"action\" value=\"*\"> ");
		out.println("<input type=\"submit\" class=\"buttons\" name=\"action\" value=\"/\"> <br> </div>");
		out.println("</form> </p> </body> </html>");
	}
}
