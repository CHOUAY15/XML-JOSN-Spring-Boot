package com.example.compte.controllers;

import com.example.compte.entities.Compte;
import com.example.compte.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banque")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;
    @GetMapping(value = "/comptes", produces = {"application/json", "application/xml"})
    public List<Compte> getComptes(){
        return compteRepository.findAll();
    }
    @GetMapping(value = "/comptes/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> getCompte(@PathVariable Long id){
        return compteRepository.findById(id).map(
         compte ->
        ResponseEntity.ok().body(compte)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/comptes" ,consumes ={"application/json", "application/xml"},produces = {"application/json", "application/xml"} )
    public Compte addCompte(@RequestBody Compte compte){
    return compteRepository.save(compte);
    }
    @PutMapping("comptes/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compteu){
    return compteRepository.findById(id).map(
     compte -> {
         compte.setSolde(compteu.getSolde());
         compte.setType(compteu.getType());
         compte.setDateCreation(compteu.getDateCreation());
         Compte updatedCompte = compteRepository.save(compte);
         return ResponseEntity.ok().body(updatedCompte);

     }
    ).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("comptes/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id){
        return compteRepository.findById(id).map(

                compte ->{
                    compteRepository.delete(compte);
                    return ResponseEntity.ok().<Void>build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }
}
