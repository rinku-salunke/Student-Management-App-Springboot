package edu.cjc.sms.app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.cjc.sms.app.ServiceI.StudentServiceI;
import edu.cjc.sms.app.model.Student;

@Controller
public class AdminController {

	@Autowired
	StudentServiceI ssi;
	
	@RequestMapping("/")
	public String preLogin() {
		return "login";
		
	}
	
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username")String u, @RequestParam("password")String p,Model m) {
		if(u.equals("admin")&& p.equals("admin")){
	    m.addAttribute("data",ssi.getAllStudents());
		return "adminscreen";
		
	}
		else
		{
			return "login";
		}

}
	@RequestMapping("/enroll_student")
	public String addStudent(@ModelAttribute Student s, Model m) {
	List<Student> list=ssi.addStudent(s);
    m.addAttribute("data",list);
	return "adminscreen";	
	}
	
	@RequestMapping("/search")
	public String getBatchStudent(@RequestParam("batchNumber")String batchNumber,Model m) {
		
	List<Student> result=ssi.searchStudentByBatch(batchNumber);
	if(result.size()>0) {
		m.addAttribute("data",result);
	}
	else
	{
		List<Student> students=ssi.getAllStudents();
		m.addAttribute("data",students);
		m.addAttribute("message","No Record Available For That Batch  "+batchNumber);
	}
		return "adminscreen";
		
	}
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam("id") int id, Model m) {
		Student st=ssi.getStudent(id);
		m.addAttribute("st",st);
		return "fees";
	
	}
	
	@RequestMapping("/payfees")
	public String payFees(@RequestParam("studentid") int studentid,@RequestParam("ammount") float ammount,Model m ) {
		ssi.updateStudentFees(studentid,ammount);
		List<Student> students=ssi.getAllStudents();
		m.addAttribute("data",students);
		return "adminscreen";
	}
	
	@RequestMapping("/remove")
	public String removeStudent(@RequestParam("id") int id, Model m) {
		ssi.removeStudent(id);
		List<Student> list=ssi.getAllStudents();
		m.addAttribute("data",list);
		return "adminscreen";
	}
	
	@RequestMapping("/batchshift/")
	public String shiftBatch(@RequestParam("id") int id, Model m) {
		ssi.shiftBatch(id);
		List<Student> list=ssi.getAllStudents();
		m.addAttribute("data",list);
		return "adminscreen";
	}
}


