Onklsjfklsdjfklsdjfsdkljfksd

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp4.ems.util.EMSConnector;


/**
 * Servlet implementation class ajaxserv
 */
public class ajaxserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection connector=EMSConnector.createConnection("jdbc:mysql://localhost:3306/emsphase2","root","password");
		try {
			Statement stmt=connector.createStatement();
			String query="SELECT SubDepartment_name FROM subdepartment,department WHERE (department.Department_id=subdepartment.Department_id) AND department.Department_name="+request.getParameter("dept")+";";
			ResultSet rs=stmt.executeQuery(query);
			List<String> subdept_list=new ArrayList<String>();
			while(rs.next()){
				subdept_list.add(rs.getString(0));
			}
			PrintWriter out=response.getWriter();
			for(String s:subdept_list){
				out.print(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
