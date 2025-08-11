package com.mongo.service;

import com.mongo.dto.SearchDTO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterSpecificationService<T> {

    public Query getSearchSpecification(List<SearchDTO> searchDTOList) {
        if (searchDTOList == null || searchDTOList.isEmpty()) {
            return new Query(); // returns all results
        }

        if (searchDTOList.size() == 1) {
            SearchDTO dto = searchDTOList.get(0);
            return new Query(Criteria.where(dto.getColumnName()).is(dto.getColumnValue()));
        }

        List<Criteria> criteriaList = new ArrayList<>();
        for (SearchDTO searchDTO : searchDTOList) {
            criteriaList.add(Criteria.where(searchDTO.getColumnName()).is(searchDTO.getColumnValue()));
        }

        Criteria finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        return new Query(finalCriteria);
    }
}

