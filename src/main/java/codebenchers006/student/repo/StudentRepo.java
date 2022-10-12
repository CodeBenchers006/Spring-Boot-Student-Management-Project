package codebenchers006.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import codebenchers006.student.model.Student;

public interface StudentRepo extends JpaRepository<Student, String>{

}
