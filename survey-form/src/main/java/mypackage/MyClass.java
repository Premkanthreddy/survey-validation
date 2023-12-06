package mypackage;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class MyClass extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		int rating=Integer.parseInt(req.getParameter("rating"));
		String comments=req.getParameter("comments");
		String recomendation=req.getParameter("recommendation");
		String[] features=req.getParameterValues("features");
		String category=req.getParameter("productCategory");
		
		SurveyForm form=new SurveyForm();
		form.setCategory(category);
		form.setComments(comments);
		form.setEmail(email);
		form.setRecomendation(recomendation);
		form.setFeatures(features);
		form.setName(name);
		form.setRating(rating);
		
		
		EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(form);
		manager.getTransaction().commit();
		
		resp.getWriter().print("<h1>Form Submission Success</h1>");
		resp.getWriter().print("<a href='project.html'>Click here to submit new form</a>");
		
		
	}
}
