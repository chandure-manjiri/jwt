package com.jwt.jwt.Controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class SchoolController{

    @GetMapping("/students")
    @RolesAllowed({"ROLE_STUDENT", "ROLE_TEACHER", "ROLE_OFFICEADMIN"})
    public ResponseEntity<String> getAllStudents(){
        String responceString = "These are all students!";
        return new ResponseEntity<>(responceString, HttpStatus.OK);
    }

    @GetMapping("/teachers")
    @RolesAllowed({"ROLE_TEACHER", "ROLE_OFFICEADMIN"})
    public ResponseEntity<String> getAllTeachers(){
        String responceString = "These are all teachers!";
        return new ResponseEntity<>(responceString, HttpStatus.OK);
    }

    @GetMapping("/admins")
    @RolesAllowed("ROLE_OFFICEADMIN")
    public ResponseEntity<String> getAllOfficeAdmins(){
        String responceString = "These are all office admins!";
        return new ResponseEntity<>(responceString, HttpStatus.OK);
    }
}
