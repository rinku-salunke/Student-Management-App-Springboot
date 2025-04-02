package edu.cjc.sms.app.serviceimpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.cjc.sms.app.ServiceI.StudentServiceI;
import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentServiceI{
	
    @Autowired
	StudentRepository sr;
	
	@Override
	public List<Student> addStudent(Student s) {
		sr.save(s);
		return sr.findAll();
	}

	@Override
	public List<Student> getAllStudents() {
		return sr.findAll();
	}

	@Override
	public List<Student> searchStudentByBatch(String batchNumber) {
    List<Student> batchStudent=sr.findAllByBatchNumber(batchNumber);
		return batchStudent;
	}

	@Override
	public Student getStudent(int id) {
    Optional<Student> opStudent=sr.findById(id);
	return opStudent.get();
	}

	@Override
	public void updateStudentFees(int studentid, float ammount) {
	Optional<Student> opStudent=sr.findById(studentid);
	Student st=opStudent.get();
	st.setFeesPaid(st.getFeesPaid()+ammount);
	sr.save(st);
	}
	
	@Override
	public void removeStudent(int id) {
		sr.deleteById(id);
	}

	@Override
	public void shiftBatch(int id) {
	Optional<Student> opStudent=sr.findById(id);
    Student st=opStudent.get();
    st.setBatchNumber(st.getBatchNumber());
    sr.save(st);
	}
}
