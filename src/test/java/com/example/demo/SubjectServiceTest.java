package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @Test
    public void SubjectServiceValid(){

        Long testId = 1L;
        String testName = "TestSubject";
        int testHours = 150;
        int testCredits = 3;

        SubjectEntity testEntity = new SubjectEntity();
        testEntity.setId(testId);
        testEntity.setSubjectName(testName);
        testEntity.setSubjectHours(testHours);
        testEntity.setSubjectCredits(testCredits);

        when(subjectRepository.save(any(SubjectEntity.class)))
                .thenReturn(testEntity);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(testId);
        subjectDto.setCredit(3);
        subjectDto.setHours(150);
        subjectDto.setName("TestSubject");

        Long id = subjectService.createSubject(subjectDto);

        assertEquals(testId, id);
    }
}
