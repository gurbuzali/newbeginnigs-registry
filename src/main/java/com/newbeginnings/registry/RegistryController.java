package com.newbeginnings.registry;

import com.newbeginnings.registry.model.Participant;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegistryController {

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @PostMapping("/add")
    public String add(@RequestBody Participant participant) {
        return registryService.add(participant);
    }

    @GetMapping("/get")
    public Participant get(@NonNull String referenceNumber) {
        return registryService.get(referenceNumber);
    }

    @PostMapping("/updatePhoneNumber")
    public void updatePhoneNumber(@NonNull String referenceNumber, @NonNull String phoneNumber) {
        registryService.updatePhoneNumber(referenceNumber, phoneNumber);
    }

    @PostMapping("/updateAddress")
    public void updateAddress(@NonNull String referenceNumber, @NonNull String address) {
        registryService.updateAddress(referenceNumber, address);
    }

    @RequestMapping("/delete")
    public Participant delete(@NonNull String referenceNumber) {
        return registryService.delete(referenceNumber);
    }

}
