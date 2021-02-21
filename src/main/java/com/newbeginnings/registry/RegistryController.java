package com.newbeginnings.registry;

import com.newbeginnings.registry.model.Participant;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegistryController {

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    @PostMapping("/add")
    public @ResponseBody String add(@RequestBody @NonNull Participant participant) {
        return registryService.add(participant);
    }

    @GetMapping("/get")
    public @ResponseBody Participant get(@NonNull String referenceNumber) {
        return registryService.get(referenceNumber);
    }

    @PostMapping("/updatePhoneNumber")
    public @ResponseBody String updatePhoneNumber(@NonNull String referenceNumber, @RequestBody @NonNull String phoneNumber) {
        return registryService.updatePhoneNumber(referenceNumber, phoneNumber);
    }

    @PostMapping("/updateAddress")
    public @ResponseBody String updateAddress(@NonNull String referenceNumber, @RequestBody @NonNull String address) {
        return registryService.updateAddress(referenceNumber, address);
    }

    @PostMapping("/delete")
    public @ResponseBody Participant delete(@NonNull String referenceNumber) {
        return registryService.delete(referenceNumber);
    }

}
