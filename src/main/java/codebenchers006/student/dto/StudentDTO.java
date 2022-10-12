package codebenchers006.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	
	private String studentId;
	private String studentName;
	private String studentDepartment;
	private int semester;

}
