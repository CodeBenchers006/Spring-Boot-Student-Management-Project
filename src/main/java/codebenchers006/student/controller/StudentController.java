package codebenchers006.student.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import codebenchers006.student.dto.StudentDTO;
import codebenchers006.student.model.Student;
import codebenchers006.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	/*
	 *  To view all the students
	 */
	
	@GetMapping("/view")
	public ResponseEntity<?> getAllStudents(){
		if(studentService.getAllStudent().size()<=0) {
			log.info("List is empty");
			return new ResponseEntity<String>("List is empty", HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			log.info("Viewing all the students");
			log.info("Total Students : {}",studentService.getAllStudent().size());
			return new ResponseEntity<List<Student>>(studentService.getAllStudent(), HttpStatus.OK);
		}
		
	}
	
	/*
	 *  To add a new student
	 */
	
	@PostMapping("/register")
	public ResponseEntity<?> addNewStudent(@RequestBody StudentDTO studentDTO){
		if(studentDTO.getStudentId() == "" || studentDTO.getStudentName() == "" || studentDTO.getStudentDepartment() == "" || studentDTO.getSemester() <=0 ) {
			return new ResponseEntity<String>("Data cannot be null", HttpStatus.NOT_ACCEPTABLE);
			
		}
		else
		{
			this.studentService.addStudentDetails(studentDTO);
			return new ResponseEntity<String>("New Student is added", HttpStatus.CREATED);
		}
	}
	
	
	/*
	 *  To view student details as per the ID
	 */
	
	@GetMapping("/view/{studentId}")
	public ResponseEntity<?> findByStudentID(@PathVariable String studentId) {
		Student s = new Student();
		try {
			s = studentService.findByStudentId(studentId);
			log.info("Student found");
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Student not found");
			return new ResponseEntity<String>("Student Not Found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Student>(s,HttpStatus.OK);
	}
	
	/*
	 *  To update student details as per the ID
	 */
	
	@PutMapping("/update/{studentId}")
	public ResponseEntity<?> updateStudentDetails(@PathVariable String studentId, @RequestBody StudentDTO studentDto){
		Student s = this.studentService.updateStudent(studentId, studentDto);
		
		if(s == null) {
			return new ResponseEntity<String>("Failed to update",HttpStatus.NOT_FOUND);
		}
		else {
			log.info("Student Details updated");
			return new ResponseEntity<String>("Update completed",HttpStatus.OK);
		}
			
	}
	
	
	/*
	 *  To remove student details as per the ID
	 */
	
	@DeleteMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable String studentId){
		String s = studentService.deleteStudent(studentId);
		
		return s;
	}

}
