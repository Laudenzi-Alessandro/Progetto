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
 * Servlet implementation class ServerPogetto
 */
@WebServlet("/Progetto")
public class ServerPogetto extends HttpServlet {
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
    public ServerPogetto() {
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);

		try {
			ra = new Random() ;
			lista = new JSONObject() ;
			sta = conn.createStatement();

			/** Creiamo una query ("interroghiamo" il database). */
			sql = "SELECT * FROM " + req.getParameter("LETTERA") ;
			/** Legge le tabelle sul database. */
			rs = sta.executeQuery(sql);

			id = ra.nextInt(10);
			/** For necessario per evitare comparsa di id = 0. */
			for(int i = 0 ; id == 0 ; i++){
				id = ra.nextInt(10);
			}

			while(rs.next()){
				if(id == rs.getInt("ID")){
					lista.put("DOMANDA", rs.getString("domanda")).put("RISPOSTA", rs.getString("risposta"));
				}
			}

			resp.getWriter().println(lista.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
