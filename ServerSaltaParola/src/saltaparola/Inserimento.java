package saltaparola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Inserimento
 */
@WebServlet("/Inserimento")
public class Inserimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int id ;
	Connection conn ;
	String url ;
	String dbName ;
	String username ;
	String password ;
	String driver ;
	String sql ;
	Statement sta ;
	Random ra ;
	JSONObject lista ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inserimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		url = "jdbc:mysql://localhost:3306/" ;
		dbName = "Sbardella" ;
		username = "Sbardella" ;
		password = "sbardella" ;
		driver = "com.mysql.jdbc.Driver" ;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,username,password);
			
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e){
			// TODO Auto-generated catch block  
			e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			lista = new JSONObject() ;
			sta = conn.createStatement();
			
			/** Inseriamo una dupla sulla tabella best10 nel database. */
			sql = "INSERT INTO best10(Nome, Punteggio) VALUES('" + request.getParameter("NOME") +
					"','" + request.getParameter("PUNTEGGIO") + "')"; 
			sta.executeUpdate(sql);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
	}

}
