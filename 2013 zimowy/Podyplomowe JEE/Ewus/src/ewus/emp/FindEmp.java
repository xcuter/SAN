package ewus.emp;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewus.Doctor;
import ewus.Employee;

@WebServlet("/emp/FindEmp")
public class FindEmp extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @PersistenceUnit(unitName = "Ewus")
  private EntityManagerFactory emFactory;

  public FindEmp() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    EntityManager em = null;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();
      // ginsQuery(em, request, response);
      empsQuery(em, request, response);
      em.getTransaction().commit();
    }
    finally {
      if (em != null) {
        em.close();
      }
    }

  }

  private void empsQuery(EntityManager em, HttpServletRequest request,
      HttpServletResponse response) {

    Query query = em.createQuery("Select emp from Employee emp where "
        + "emp.firstName = :firstName");
    query.setParameter("firstName", "Anna");
    
    @SuppressWarnings("unchecked")
    List<Employee> emps = query.getResultList();
    for (Employee emp : emps) {
      System.out.println(emp.getId() + " " + emp.getFirstName());
    }

  }

  private void ginsQuery(EntityManager em, HttpServletRequest request,
      HttpServletResponse response) {
    Query findGins = em.createNamedQuery("findDoctorsOfSpec");
    findGins.setParameter("spec", "Ginekologia");

    @SuppressWarnings("unchecked")
    List<Doctor> gins = findGins.getResultList();

    for (Doctor doc : gins) {
      System.out.println(doc.getId() + " " + doc.getFirstName() + "  "
          + doc.getLastName());
    }

  }

}
