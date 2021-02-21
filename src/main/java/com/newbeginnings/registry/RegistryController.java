package com.newbeginnings.registry;

import com.newbeginnings.registry.model.Participant;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The registry logic is abstracted away to {@link RegistryService}
 * which helps to add/modify the user facing API without touching
 * the logic.
 */
@RestController
@RequestMapping("/api/v1")
public class RegistryController {

    private final RegistryService registryService;

    public RegistryController(RegistryService registryService) {
        this.registryService = registryService;
    }

    /**
     * See {@link RegistryService#add(Participant)}.
     */
    @PostMapping("/add")
    public @ResponseBody
    String add(@RequestBody @NonNull Participant participant) {
        return registryService.add(participant);
    }

    /**
     * See {@link RegistryService#get(String)}.
     */
    @GetMapping("/get/{refNo}")
    public @ResponseBody
    Participant get(@PathVariable(value = "refNo") @NonNull String referenceNumber) {
        return registryService.get(referenceNumber);
    }

    /**
     * See {@link RegistryService#updatePhoneNumber(String, String)}.
     */
    @PostMapping("/updatePhoneNumber/{refNo}")
    public @ResponseBody
    String updatePhoneNumber(
            @PathVariable(value = "refNo") @NonNull String referenceNumber,
            @RequestBody @NonNull String phoneNumber
    ) {
        return registryService.updatePhoneNumber(referenceNumber, phoneNumber);
    }

    /**
     * See {@link RegistryService#updateAddress(String, String)}.
     */
    @PostMapping("/updateAddress/{refNo}")
    public @ResponseBody
    String updateAddress(
            @PathVariable(value = "refNo") @NonNull String referenceNumber,
            @RequestBody @NonNull String address
    ) {
        return registryService.updateAddress(referenceNumber, address);
    }

    /**
     * See {@link RegistryService#delete(String)}.
     */
    @PostMapping("/delete/{refNo}")
    public @ResponseBody
    Participant delete(@PathVariable(value = "refNo") @NonNull String referenceNumber) {
        return registryService.delete(referenceNumber);
    }

}
