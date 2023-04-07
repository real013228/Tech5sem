package com.real013228;

import com.real013228.entity.CatEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CatSpecification implements Specification<CatEntity> {
    private SearchCriteria criteria;

    public CatSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<CatEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equals(":")) {
            return criteriaBuilder.like(root.get(criteria.getKey()),"%" + criteria.getValue() + "%");
        }

        return null;
    }
}
