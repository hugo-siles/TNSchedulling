/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnscheduling.services;

import com.tn.tnscheduling.entities.JpaClass;
import com.tn.tnscheduling.entities.JpaStudent;
import com.tn.tnscheduling.exceptions.DaoException;
import com.tn.tnscheduling.model.Class;
import com.tn.tnscheduling.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import org.hamcrest.core.IsAnything;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hugo.siles
 */
public class SchedullingClassServiceTest {
    
    private final EntityManager mockManager;
    
    public SchedullingClassServiceTest() {
        
        mockManager = EasyMock.createNiceMock(EntityManager.class);
    }
    
    @Test
    public void testCreateClass_checkRightCallsAreMadeToEntityManager() throws Exception {
        System.out.println("testCreate_checkRightCallsAreMadeToEntityManager");
        
        Class aClass = new Class();
        aClass.setCode("ABC");
        aClass.setTitle("ABC title");
        aClass.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        mockManager.persist(jpaClass);
        EasyMock.expectLastCall().once();
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.createClass(aClass);
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    (expected = DaoException.class)
    public void testCreateClass_checkExceptionInDBIsCaught() throws Exception {
        System.out.println("testCreateClass_checkExceptionInDBIsCaught");
        
        Class aClass = new Class();
        aClass.setCode("ABC");
        aClass.setTitle("ABC title");
        aClass.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        mockManager.persist(jpaClass);
        EasyMock.expectLastCall().andThrow(new PersistenceException("test exception"));
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.createClass(aClass);
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    public void testEditClass_checkRightCallsAreMadeToEntityManager() throws Exception {
        System.out.println("testEditClass_checkRightCallsAreMadeToEntityManager");
        
        Class aClass = new Class();
        aClass.setCode("ABC");
        aClass.setTitle("ABC title");
        aClass.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        expect(mockManager.merge(jpaClass)).andReturn(jpaClass).once();
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.editClass("ABC", aClass);
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    (expected = DaoException.class)
    public void testEditClass_checkExceptionInDBIsCaught() throws Exception {
        System.out.println("testEditClass_checkExceptionInDBIsCaught");
        
        Class aClass = new Class();
        aClass.setCode("ABC");
        aClass.setTitle("ABC title");
        aClass.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        expect(mockManager.merge(jpaClass)).andThrow(new TransactionRequiredException("test exception"));
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.editClass("ABC", aClass);
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    public void testRemoveClass_checkRightCallsAreMadeToEntityManager() throws Exception {
        System.out.println("testRemoveClass_checkRightCallsAreMadeToEntityManager");
        
        Class aClass = new Class();
        aClass.setCode("ABC");
        aClass.setTitle("ABC title");
        aClass.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        expect(mockManager.find(JpaClass.class, "ABC")).andReturn(jpaClass).once();
        expect(mockManager.merge(jpaClass)).andReturn(jpaClass);
        
        mockManager.remove(jpaClass);
        EasyMock.expectLastCall().once();
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.removeClass("ABC");
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    (expected = DaoException.class)
    public void testRemoveClass_checkExceptionInDBIsCaught() throws Exception {
        System.out.println("testRemoveClass_checkExceptionInDBIsCaught");
        
        Class aClass = new Class();
        aClass.setCode("ABC");
        aClass.setTitle("ABC title");
        aClass.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        expect(mockManager.find(JpaClass.class, "ABC")).andReturn(jpaClass).once();
        expect(mockManager.merge(jpaClass)).andReturn(jpaClass).anyTimes();
        
        mockManager.remove(jpaClass);        
        EasyMock.expectLastCall().andThrow(new TransactionRequiredException("test exception"));
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.removeClass("ABC");
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    public void testFindClassByCode_checkRightCallsAreMadeToEntityManager() throws Exception {
        System.out.println("testFindClassByCode_checkRightCallsAreMadeToEntityManager");
        
        Class expectedResult = new Class();
        expectedResult.setCode("ABC");
        expectedResult.setTitle("ABC title");
        expectedResult.setDescription("ABC description");
        
        JpaClass jpaClass = new JpaClass("ABC", "ABC title", "ABC description");
        
        expect(mockManager.find(JpaClass.class, "ABC")).andReturn(jpaClass).once();
          
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        Class result = instance.findClassByCode("ABC");
        assertEquals(expectedResult, result);
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    (expected = DaoException.class)
    public void testFindClassByCode_checkObjectNotFoundInDB() throws Exception {
        System.out.println("testFindClassByCode_checkExceptionInDBIsCaught");
        
        expect(mockManager.find(JpaClass.class, "ABC")).andReturn(null).once();
     
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.findClassByCode("ABC");
        
        EasyMock.verify(mockManager);
    }
    
    @Test
    public void testFindStudentsInClass_checkRightCallsAreMadeToEntityManager() throws Exception {
        System.out.println("testfindStudentsInClass_checkRightCallsAreMadeToEntityManager");
        
           
        JpaStudent jpaStudent1 = new JpaStudent(1, "firstName", "lastName");
        JpaStudent jpaStudent2 = new JpaStudent(2, "firstName", "lastName");
        JpaStudent jpaStudent3 = new JpaStudent(3, "firstName", "lastName");
      
        
        List<JpaStudent> studentsList = new ArrayList<>();
        studentsList.add(jpaStudent1);
        studentsList.add(jpaStudent2);
        studentsList.add(jpaStudent3);
        
        Query mockQuery = EasyMock.createNiceMock(Query.class);
        String query = "select s.* from students s, students_classes sc "
                    + "where sc.class_code = ?1 and s.id = sc.student_id";
        
        expect(mockManager.createNativeQuery(query, JpaStudent.class)).andReturn(mockQuery);
        expect(mockQuery.setParameter(1, "ABC")).andReturn(mockQuery).once();
        expect(mockQuery.getResultList()).andReturn(studentsList);
        
        EasyMock.replay(mockManager, mockQuery);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        List<Student> result = instance.findStudentsInClass("ABC");
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        
        EasyMock.verify(mockManager, mockQuery);
    }
    
    @Test
    (expected = DaoException.class)
    public void testFindStudentsInClass_checkExceptionInDBIsCaught() throws Exception {
        System.out.println("testFindStudentsInClass_checkExceptionInDBIsCaught");
        
        String query = "select s.* from students s, students_classes sc "
                    + "where sc.class_code = ?1 and s.id = sc.student_id";
        
        expect(mockManager.createNativeQuery(query, JpaStudent.class)).andThrow(new PersistenceException("test message"));
        
        EasyMock.replay(mockManager);
        
        SchedullingClassService instance = new SchedullingClassService(mockManager);
        instance.findStudentsInClass("ABC");
        
        EasyMock.verify(mockManager);
    }

    
    
}
