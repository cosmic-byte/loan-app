package thecrevance.service;


import thecrevance.utils.banks.Bank;

import java.util.List;

public interface BankService {

    List<Bank> getBanks();
    Bank getBankById(Long id);
}
