package com.picpaychallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaychallenge.dto.TransferDto;
import com.picpaychallenge.model.Transfer;
import com.picpaychallenge.services.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    
    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferDto dto) throws Exception{
        Transfer newTransfer = this.transferService.createTransfer(dto);
        return new ResponseEntity<>(newTransfer, HttpStatus.OK);
    }
}
