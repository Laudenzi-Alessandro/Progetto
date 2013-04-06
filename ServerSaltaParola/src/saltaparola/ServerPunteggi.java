package saltaparola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ServerPunteggi
 */
@WebServlet("/ServerPunteggi")
public class ServerPunteggi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int id ;
	Connection conn ;
	String url ;
	String dbName ;
	String username ;
	String password ;
	String driver ;
	ResultSet rs ;
	String sql ;
	Statement sta ;
	Random ra ;
	JSONObject lista ;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerPunteggi() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest richiesta, HttpServletResponse risposta) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			lista = new JSONObject() ;
			sta = conn.createStatement();

			/** Creiamo una query ("interroghiamo" il database). */
			sql = "SELECT * FROM best10 WHERE ID=" + richiesta.getParameter("ID") ;
			rs = sta.executeQuery(sql);

			 while(rs.next()){
				lista.put("NOME", rs.getString("Nome")).put("PUNTEGGIO", rs.getInt("Punteggio"));
			}

			risposta.getWriter().println(lista.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
