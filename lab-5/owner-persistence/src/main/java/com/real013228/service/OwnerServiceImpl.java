package com.real013228.service;

import com.real013228.Mapper;
import com.real013228.dto.OwnerDto;
import com.real013228.entity.OwnerEntity;
import com.real013228.event.CatOwnedEvent;
import com.real013228.model.CatModel;
import com.real013228.model.OwnerModel;
import com.real013228.repository.OwnerRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final WebClient webClient;
    private final KafkaTemplate<String, CatOwnedEvent> kafkaTemplate;

    public OwnerServiceImpl(OwnerRepository ownerRepository, WebClient webClient, KafkaTemplate kafkaTemplate) {
        this.ownerRepository = ownerRepository;
        this.webClient = webClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<OwnerDto> findAllOwners() {
        List<OwnerEntity> owners = ownerRepository.findAll();
        if (owners != null)
            return owners.stream().map(Mapper::asOwnerDto).toList();
        return new ArrayList<>();
    }

    @Override
    public void saveOwner(OwnerModel owner) {
        ownerRepository.save(asOwnerEntity(owner));
    }

    @Override
    public void ownCat(Long owner, Long cat) {
        OwnerEntity ownerEntity = ownerRepository.findById(owner).orElse(null);
        CatModel res = webClient.get()
                .uri("http://localhost:8080/api/v1/cat/get/by-id",
                        uriBuilder -> uriBuilder.queryParam("id", cat).build())
                .retrieve()
                .bodyToMono(CatModel.class)
                .block();
        if (res != null && ownerEntity != null) {
            ownerEntity.getCats().add(Mapper.asCatEntity(res, cat, owner));
            ownerRepository.save(ownerEntity);
            kafkaTemplate.send("notificationTopic", new CatOwnedEvent(owner, cat));
        }
        // call cat-persistence service to know is there a cat
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    private OwnerEntity asOwnerEntity(OwnerModel ownerModel) {
        return OwnerEntity.builder()
                .birthDate(ownerModel.birthDate())
                .name(ownerModel.name())
                .userAccount(ownerModel.userAccountId())
                .build();
    }
}
