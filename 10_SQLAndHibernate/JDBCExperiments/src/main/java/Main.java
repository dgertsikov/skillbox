import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.hibernate.id.PersistentIdentifierGenerator.PK;

public class Main {
    public static void main(String[] args) {

        int studentId = 0;
        int courseId = 0;
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 1);
        System.out.println("Курс: " + course.getName() + "Учитель: " + course.getTeacher().getName()+", количество студентов: "+
                course.getSubscription().size());

        List<Subscription> subscriptionList = course.getSubscription();
        subscriptionList.forEach(subscription -> {
            System.out.println("Дата подписки: " + subscription.getSubscriptionDate()+", студент: "+subscription.getStudent().getName());
        });

        List<Student> studentList = course.getStudents();
        studentList.forEach(student -> {
            System.out.println(student.getName());
        });

        Teacher teacher = session.get(Teacher.class, 1);
        System.out.println("Учитель: " + teacher.getName() + ", зарплата: " + teacher.getSalary());

        //Задача 10.4
        Transaction transaction = session.beginTransaction();

        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();

        String hql = "From " + LinkedPurchaseList.class.getSimpleName();
        List<LinkedPurchaseList> linkedPurchaseLists = session.createQuery(hql).getResultList();
        for (LinkedPurchaseList l: linkedPurchaseLists){
            session.delete(l);
        }

        hql = "From " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseListList = session.createQuery(hql).getResultList();
        for(PurchaseList list: purchaseListList){
            String hqlCourse = "From " + Course.class.getSimpleName() + " Where name = '"+list.getCourseName()+"'";
            List<Course> courses = session.createQuery(hqlCourse).getResultList();
            for(Course c1: courses){
                courseId = c1.getId();
                break;
            }
            String hqlStudent = "From " + Student.class.getSimpleName() + " Where name = '" + list.getStudentName() + "'";
            List<Student> students = session.createQuery(hqlStudent).getResultList();
            for(Student s1: students){
                studentId = s1.getId();
                break;
            }
            linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(new LinkedKey(studentId, courseId));
            session.save(linkedPurchaseList);
            System.out.println(list.getCourseName()+" - "+ list.getStudentName()+" - "+studentId+"-"+courseId);
        }

        System.out.println("Ok");


        transaction.commit();
        sessionFactory.close();
    }
}
