package com.example.teste.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.login.authentication.auth.model.AuthenticationDTO;

@RestController()
@RequestMapping("product")
public class ProductController {


    @PostMapping
    public ResponseEntity postProduct(@RequestBody AuthenticationDTO body){
        return ResponseEntity.ok().body("Novo produto criado");
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
    	   List<String> productList = new ArrayList<>();

           productList.add("Produto 1");
           productList.add("Produto 2");
           productList.add("Produto 3");

        return ResponseEntity.ok(productList);
    }
}