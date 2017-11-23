package thecrevance.service;

import org.springframework.stereotype.Service;
import thecrevance.utils.banks.Bank;
import thecrevance.utils.banks.Banks;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private Banks supportedBanks;

    public BankServiceImpl() {
        supportedBanks = new Banks();
    }

    @Override
    public List<Bank> getBanks() {
        List<Bank> banks = new ArrayList<>(supportedBanks.getBanks().values());
        banks.forEach(x -> x.setName(x.getName().toUpperCase()));
        return banks;
    }

    @Override
    public Bank getBankById(Long id) {
        return supportedBanks.getBankById(Integer.parseInt(id+""));
    }
}
