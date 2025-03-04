package com.example.loanapproval.web;

import com.example.loanapproval.entities.Approval;
import com.example.loanapproval.entities.Compte;
import com.example.loanapproval.entities.LoanApproval;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("loanApproval")
public class LoanApprovalRest {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/comptes")
    public List<Compte> compteList(){
        ResponseEntity<Compte[]> comptes = restTemplate.getForEntity("http://localhost:1080/acc/banque/comptes", Compte[].class);
        return List.of(comptes.getBody());
    }

    @GetMapping("/approvals")
    public List<Approval> approvalList(){
        ResponseEntity<Approval[]> approvals = restTemplate.getForEntity("http://localhost:1080/app/Approval/approvals", Approval[].class);
        return List.of(approvals.getBody());
    }

    @PostMapping
    public String addLoanApproval(@RequestBody LoanApproval loanApproval){


        long id = loanApproval.getId();
        int somme = loanApproval.getSomme();

        ResponseEntity<Compte> compte = restTemplate.getForEntity("http://localhost:1080/acc/banque/comptes/{id}", Compte.class, id);
        Compte c = compte.getBody();
        String risk=c.getRisk();
        ResponseEntity<Approval> appro = restTemplate.getForEntity("http://localhost:1080/app/Approval/approvals/{id}", Approval.class, id);
        Approval a = appro.getBody();

        if (somme < 10000){

           
            if(risk.equals("low")){
                c.setAccount(c.getAccount() + somme);
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<Compte> requestBody = new HttpEntity<>(c,headers);
                restTemplate.put("http://localhost:1080/acc/banque/comptes/{id}", requestBody, id);

                HttpEntity<Approval> request = new HttpEntity<>(new Approval(c.getId(), c.getNom(),"accepted"));
                Approval approval = restTemplate.postForObject("http://localhost:1080/app/Approval/approvals", request, Approval.class);
                return "Risk => " + risk + "\nDemande de crédit acceptée";
            }
            else{
                if(a.getReponse().equals("refused")){
                    HttpEntity<Approval> request = new HttpEntity<>(new Approval(c.getId(), c.getNom(),"refused"));
                    Approval approval = restTemplate.postForObject("http://localhost:1080/app/Approval/approvals", request, Approval.class);
                    return "Risk => " + risk + "\n" + a.getReponse() + "\nDemande de crédit refusée";
                }
                else{
                    c.setAccount(c.getAccount() + somme);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                    HttpEntity<Compte> request = new HttpEntity<>(c,headers);
                    restTemplate.put("http://localhost:1080/acc/banque/comptes/{id}", request, id);
                    return "Risk =>" + risk + "\n" + a.getReponse() + "\nDemande de crédit acceptée";
                }

            }
        }
        else {


            if(a.getReponse().equals("refused")){
                HttpEntity<Approval> request = new HttpEntity<>(new Approval(c.getId(), c.getNom(),"refused"));
                Approval approval = restTemplate.postForObject("http://localhost:1080/app/Approval/approvals", request, Approval.class);
                return "Risk => " + risk + "\n" + a.getReponse() + "\nDemande de crédit refusée";
            }
            else{
                c.setAccount(c.getAccount() + somme);
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<Compte> request = new HttpEntity<>(c,headers);
                restTemplate.put("http://localhost:1080/acc/banque/comptes/{id}", request, id);
                return "Risk =>" + risk+ "\n" + a.getReponse() + "\nDemande de crédit acceptée";
            }
        }
    }
}
