package com.jet.TestJet.controller;

import com.jet.TestJet.dtos.DealerDto;
import com.jet.TestJet.model.Dealer;
import com.jet.TestJet.services.DealerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/dealers")
@Tag(name = "Dealers Endpoints")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @GetMapping("/{id}")
    @Operation(summary = "getDealerById", description = "Get dealer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerDto.class)) })
    })
    public ResponseEntity<DealerDto> getDealerById(@PathVariable String id) {
        DealerDto dealerDto = dealerService.getDealerById(id);
        if (Objects.nonNull(dealerDto)) {
            return new ResponseEntity<>(dealerDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    @Operation(summary = "createDealer", description = "Create dealer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerDto.class)) })
    })
    public ResponseEntity<DealerDto> createDealer(@RequestBody DealerDto dealerDto) {
        DealerDto createdDealerDto = dealerService.createDealer(dealerDto);
        return new ResponseEntity<>(createdDealerDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    @Operation(summary = "getAllDealers", description = "Get all dealers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            array =  @ArraySchema(schema = @Schema(implementation = DealerDto.class))) })
    })
    public ResponseEntity<List<DealerDto>> getAllDealers() {
        List<DealerDto> dealerDtos = dealerService.getAllDealers();
        return new ResponseEntity<>(dealerDtos, HttpStatus.OK);
    }

    @PutMapping("")
    @Operation(summary = "updateDealer", description = "Update dealer information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerDto.class)) })
    })
    public ResponseEntity<DealerDto> updateDealer(@RequestBody DealerDto dealerDto) {
        DealerDto updatedDealerDto = dealerService.updateDealer(dealerDto);
        if (Objects.nonNull(updatedDealerDto)) {
            return new ResponseEntity<>(updatedDealerDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deleteDealer", description = "Delete dealer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DealerDto.class)) })
    })
    public ResponseEntity<DealerDto> deleteDealer(@PathVariable String id) {
        dealerService.deleteDealer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
