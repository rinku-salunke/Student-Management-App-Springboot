package edu.cjc.sms.app.ServiceI;

import java.util.List;
import edu.cjc.sms.app.model.Student;

public interface StudentServiceI {

	List<Student> addStudent(Student s);

	List<Student> getAllStudents();
	 
	public List<Student> searchStudentByBatch(String batchNumber);

	Student getStudent(int id);
	
	public void updateStudentFees(int studentid, float ammount);

	public void removeStudent(int id);

	public void shiftBatch(int id);
}
