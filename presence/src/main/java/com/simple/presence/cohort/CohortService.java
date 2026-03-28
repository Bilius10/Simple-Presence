package com.simple.presence.cohort;

import com.simple.presence.infrastrcuture.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.simple.presence.infrastrcuture.exception.ExceptionMessages.COHORT_NOT_FOUND;

@Service
public class CohortService {

    private final CohortRepository cohortRepository;

    public CohortService(CohortRepository cohortRepository) {
        this.cohortRepository = cohortRepository;
    }

    public Cohort findById(Integer id) {
        return cohortRepository.findById(id)
                .orElseThrow(() -> new ServiceException(COHORT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
