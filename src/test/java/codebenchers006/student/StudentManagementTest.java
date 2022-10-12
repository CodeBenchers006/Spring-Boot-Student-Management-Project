package codebenchers006.student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import codebenchers006.student.controller.StudentController;
import codebenchers006.student.dto.StudentDTO;
import codebenchers006.student.model.Student;
import codebenchers006.student.repo.StudentRepo;
import codebenchers006.student.service.StudentService;


@SpringBootTest
class StudentManagementTest {

	@Autowired
	private StudentService service;
	
	@MockBean
	private StudentRepo repo;
	
	@InjectMocks
	StudentController controller;
	
	@Test
	public void getAllStudentsTest() {
		
		when(repo.findAll()).thenReturn(Stream.of(new Student("DC2016BTE0002","Johny","CSE",1)).collect(Collectors.toList()));
		assertEquals(1, service.getAllStudent().size());
	}
	
	@Test
	public void findByIdTest() {
		
		String id = "DC2016BTE0002";
		Student studentActual = new Student(id,"Johny","CSE",1);
		
		when(repo.findById(id)).thenReturn(Optional.of(studentActual));
		
		assertEquals(id, service.findByStudentId(id).getStudentId());
		
	}
	
	@Test
	public void createStudentTest() {
		
		String id = "DC2016BTE0002";
		Student studentActual = new Student(id,"Johny","CSE",1);
		StudentDTO studentExpected = new StudentDTO(id,"Johny","CSE",1);
		
		when(repo.save(studentActual)).thenReturn(studentActual);
		assertEquals(studentActual.getStudentName(), service.addStudentDetails(studentExpected).getStudentName());
	}

	
	@Test
	public void deleteStudentTest() {
		
		String id = "DC2016BTE0002";
		Student studentActual = new Student(id,"Johny","CSE",1);
		
		when(repo.findById(id)).thenReturn(Optional.of(studentActual));
		service.deleteStudent(id);
		verify(repo).deleteById(studentActual.getStudentId());
		
		
	}
	
	@Test
	public void updateStudentDetails() {
		String id = "DC2016BTE0002";
		Student studentActual = new Student(id,"Johny","CSE",1);
		when(repo.findById(id)).thenReturn(Optional.of(studentActual));
		
		StudentDTO studentExpected = new StudentDTO(id,"Johny","CSE",2);
		service.updateStudent(id, studentExpected);
		assertEquals(studentExpected.getSemester(), 2);
	}
}
