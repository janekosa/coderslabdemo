package com.example.coderslabdemo.persistance.dao;

import com.example.coderslabdemo.persistance.model.Address;
import com.example.coderslabdemo.persistance.model.Address_;
import com.example.coderslabdemo.persistance.model.Feedback;
import com.example.coderslabdemo.persistance.model.Feedback_;
import com.example.coderslabdemo.persistance.model.Property;
import com.example.coderslabdemo.persistance.model.PropertyFilter;
import com.example.coderslabdemo.persistance.model.Property_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.SetJoin;


public interface PropertyRepository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property> {

    class Specs {
        public static Specification<Property> getFromFilter(PropertyFilter filter) {
            Specification<Property> result = (root, query, criteriaBuilder) -> criteriaBuilder.and();
            if (filter.getAreaSqMFrom() != null) {
                result = result.and(minAreaSpec(filter.getAreaSqMFrom()));
            }
            if (filter.getAreaSqMTo() != null) {
                result = result.and(maxAreaSpec(filter.getAreaSqMTo()));
            }
            if (filter.getRoomsFrom() != null) {
                result = result.and(minRoomsSpec(filter.getRoomsFrom()));
            }
            if (filter.getRoomsTo() != null) {
                result = result.and(maxRoomsSpec(filter.getRoomsTo()));
            }
            if (filter.getCity() != null) {
                result = result.and(citySpec(filter.getCity()));
            }
            if (filter.getRatingsFrom() != null) {
                result = result.and(ratingsSpec(filter.getRatingsFrom()));
            }
            if (filter.getType() != null) {
                result = result.and(typeSpec(filter.getType()));
            }
            return result;
        }

        private static Specification<Property> minAreaSpec(Integer minAreaSqM) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Property_.areaSqM), minAreaSqM);
        }

        private static Specification<Property> maxAreaSpec(Integer maxAreaSqM) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(Property_.areaSqM), maxAreaSqM);
        }

        private static Specification<Property> minRoomsSpec(Integer minRooms) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Property_.rooms), minRooms));
        }

        private static Specification<Property> maxRoomsSpec(Integer maxRooms) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(Property_.rooms), maxRooms));
        }

        private static Specification<Property> citySpec(String city) {
            return (root, query, criteriaBuilder) -> {
                Join<Property, Address> addressJoin = root.join(Property_.address);
                return criteriaBuilder.equal(criteriaBuilder.lower(addressJoin.get(Address_.city)), city.toLowerCase());
            };
        }

        private static Specification<Property> ratingsSpec(Double minRating) {
            return (root, query, criteriaBuilder) -> {
                SetJoin<Property, Feedback> feedbackJoin = root.join(Property_.ratings);
                Expression<Double> avg = criteriaBuilder.avg(feedbackJoin.get(Feedback_.rating));
                return query.groupBy(root).having(criteriaBuilder.greaterThanOrEqualTo(avg, minRating)).getRestriction();
            };
        }

        private static Specification<Property> typeSpec(Property.Type type) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Property_.type), type);
        }

    }

}
