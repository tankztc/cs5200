import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class testServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		List<Actor> actors = new ArrayList<Actor>();
		Actor actor = new Actor("4","zhuang","tancheng",null);
		ActorManager mgr = new ActorManager();
//		actors = mgr.readAllActors();
//		System.out.println(actors);
		mgr.createActor(actor);
		
		CommentManager commentManager = new CommentManager();
		Comment comment = new Comment("123", "asf", "123", "asdfasdf", null);
		commentManager.createComment(comment);
		commentManager.updateComment("123", "qwerqer");
		
	}

}
