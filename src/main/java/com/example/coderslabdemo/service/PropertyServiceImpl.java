package com.example.coderslabdemo.service;

import com.example.coderslabdemo.persistance.dao.PropertyRepository;
import com.example.coderslabdemo.persistance.model.Address;
import com.example.coderslabdemo.persistance.model.Feedback;
import com.example.coderslabdemo.persistance.model.Property;
import com.example.coderslabdemo.persistance.model.PropertyFilter;
import com.example.coderslabdemo.rest.dto.FeedbackDto;
import com.example.coderslabdemo.rest.dto.PropertyDto;
import com.example.coderslabdemo.rest.exception.NotFoundException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyDto get(Long id) {
        Optional<Property> dbObj = propertyRepository.findById(id);
        return dbObj.map(ModelMapper::toFullPropertyDto).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<PropertyDto> get(PropertyFilter filter) {
        List<Property> result = propertyRepository.findAll(PropertyRepository.Specs.getFromFilter(filter));
        result.sort(Property.ratingsComparator());
        return result.stream().map(ModelMapper::toSimplePropertyDto).collect(Collectors.toList());
    }

    @Override
    public PropertyDto add(PropertyDto dto) {
        verifyDto(dto);
        Property property = ModelMapper.toProperty(dto);
        return ModelMapper.toFullPropertyDto(propertyRepository.save(property));
    }

    @Override
    public PropertyDto modify(Long id, PropertyDto dto) {
        verifyDto(dto);
        Property dbProperty = propertyRepository.findById(id).orElseThrow(NotFoundException::new);
        ModelMapper.copyProperties(dto, dbProperty);
        return ModelMapper.toFullPropertyDto(propertyRepository.save(dbProperty));
    }

    @Override
    public FeedbackDto addFeedback(Long propertyId, FeedbackDto dto) {
        Preconditions.checkArgument(dto.getId() == null);
        Preconditions.checkNotNull(dto.getRating());
        Property property = propertyRepository.findById(propertyId).orElseThrow(NotFoundException::new);
        Feedback feedback = ModelMapper.toFeedback(dto);
        property.getRatings().add(feedback);
        propertyRepository.save(property);
        return ModelMapper.toFeedbackDto(feedback);
    }

    private void verifyDto(PropertyDto dto) {
        Preconditions.checkNotNull(dto.getAreaSqM());
        Preconditions.checkNotNull(dto.getAddressLine1());
        Preconditions.checkNotNull(dto.getCity());
        Preconditions.checkNotNull(dto.getPostcode());
        Preconditions.checkNotNull(dto.getRooms());
        Preconditions.checkArgument(dto.getType() != null &&
                Property.Type.stringValues().contains(dto.getType()));
        Preconditions.checkArgument(dto.getId() == null &&
                dto.getRatings() == null &&
                dto.getAvgRating() == null &&
                dto.getRatingsCount() == null);
    }

    private static class ModelMapper {

        private static void copyProperties(PropertyDto dto, Property property) {
            Address newAddress = Address.of(dto.getAddressLine1(), dto.getAddressLine2(), dto.getPostcode(), dto.getCity());
            property.setAreaSqM(property.getAreaSqM());
            property.setAdditionalInfo(dto.getAdditionalInfo());
            property.setAddress(newAddress);
            property.setRooms(dto.getRooms());
            property.setType(Property.Type.valueOf(dto.getType()));
        }

        private static PropertyDto toSimplePropertyDto(Property property) {
            Address addres = property.getAddress();
            return new PropertyDto(property.getId(), property.getAreaSqM(), property.getRooms(), addres.getAddressLine1(),
                addres.getAddressLine2(), addres.getPostcode(), addres.getCity(), property.getType().toString(),
                property.getAdditionalInfo(), property.getAvgRating(), property.getRatings().size());
        }

        private static PropertyDto toFullPropertyDto(Property property) {
            PropertyDto res = toSimplePropertyDto(property);

            List<FeedbackDto> meaningfulFeedback = property.getRatings().stream()
                    .map(ModelMapper::toMeaningfulFeedbackDto)
                    .filter(Optional::isPresent).map(Optional::get)
                    .collect(Collectors.toList());

            res.setRatings(meaningfulFeedback);
            return res;
        }

        private static Feedback toFeedback(FeedbackDto dto) {
            return Feedback.of(dto.getRating(), dto.getAdditionalInfo(), dto.getSignature());
        }

        private static Property toProperty(PropertyDto dto) {
            Address address = Address.of(dto.getAddressLine1(), dto.getAddressLine2(), dto.getPostcode(), dto.getCity());
            Property.Type type = Property.Type.valueOf(dto.getType());
            return Property.of(dto.getAreaSqM(), dto.getRooms(), address, type, dto.getAdditionalInfo(), new HashSet<>());
        }

        private static Optional<FeedbackDto> toMeaningfulFeedbackDto(Feedback feedback) {
            if (feedback.getAdditionalFeedback() == null) {
                return Optional.empty();
            }
            FeedbackDto res = new FeedbackDto(feedback.getId(), feedback.getRating(), feedback.getAdditionalFeedback(), feedback.getSignature());
            return Optional.of(res);
        }

        private static FeedbackDto toFeedbackDto(Feedback feedback) {
            return new FeedbackDto(feedback.getId(), feedback.getRating(), feedback.getAdditionalFeedback(), feedback.getSignature());
        }

    }


}
