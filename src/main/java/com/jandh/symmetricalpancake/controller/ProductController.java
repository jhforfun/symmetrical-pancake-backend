package com.jandh.symmetricalpancake.controller;

import com.jandh.symmetricalpancake.controller.payload.CreateProductRequest;
import com.jandh.symmetricalpancake.controller.payload.CreateProductResponse;
import com.jandh.symmetricalpancake.usecase.CommandOutput;
import com.jandh.symmetricalpancake.usecase.product.CreateProductInput;
import com.jandh.symmetricalpancake.usecase.product.CreateProductUseCase;
import com.jandh.symmetricalpancake.usecase.product.create.CreateProductUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.LinkPermission;

@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    @PostMapping(path = "product")
    public ResponseEntity<Boolean> create(@RequestBody CreateProductRequest payload) {
        CreateProductInput request = new CreateProductUseCaseImpl.CreateProductInputImpl()
                .serialNumber(payload.getSerialNumber())
                .type(payload.getType())
                .name(payload.getName())
                .minimumOrderQuantity(payload.getMinimumOrderQuantity());
        CommandOutput response = new CreateProductResponse();
        createProductUseCase.execute(request, response);
        return ResponseEntity.ok(true);
    }
}