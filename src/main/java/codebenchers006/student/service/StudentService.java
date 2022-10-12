package codebenchers006.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codebenchers006.student.dto.StudentDTO;
import codebenchers006.student.model.Student;
import codebenchers006.student.repo.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	StudentRepo studentRepo;
	
	
	
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}

	public Student findByStudentId(String studentId) {
		Student student = studentRepo.findById(studentId).get();
		return student;
	}

	public void addStudentDetails(StudentDTO studentDTO) {
		
		Student newStudent = new Student();
		
		newStudent.setStudentId(studentDTO.getStudentId());
		newStudent.setStudentName(studentDTO.getStudentName());
		newStudent.setStudentDepartment(studentDTO.getStudentDepartment());
		newStudent.setSemester(studentDTO.getSemester());
		
		studentRepo.save(newStudent);
		
	}

	public Student updateStudent(String studentId, StudentDTO studentDto) {
		
		if(studentRepo.findById(studentId).isPresent()) {
			Student s = new Student();
			s.setStudentId(studentDto.getStudentId());
			s.setStudentName(studentDto.getStudentName());
			s.setStudentDepartment(studentDto.getStudentDepartment());
			s.setSemester(studentDto.getSemester());
			
			
			studentRepo.save(s);
			return s;
		}
		else
			return null;
		
		
	}

	public String deleteStudent(String studentId) {
		
		if(studentRepo.findById(studentId).isPresent()) {
			studentRepo.deleteById(studentId);
			return "Student details deleted successfully";
		}
		else
			return "Failed to delete";
		
	}

}
