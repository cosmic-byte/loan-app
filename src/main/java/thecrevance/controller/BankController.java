package thecrevance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import thecrevance.service.BankService;
import thecrevance.utils.banks.Bank;

import java.util.List;


/**
 * Created by Greg on 03/10/17.
 */
@RestController
@RequestMapping("/v1/banks")
public class BankController {


    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Bank> getAllBanks() {
        return bankService.getBanks();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{bankId}")
    public Bank getBankById(@PathVariable("bankId") Long bankId) {
        return bankService.getBankById(bankId);
    }
}
