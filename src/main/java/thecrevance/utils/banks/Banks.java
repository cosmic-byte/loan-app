package thecrevance.utils.banks;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
public final class Banks {
    private Map<Integer, Bank> banks;

    public Banks() {
        this.banks = new HashMap<>();
        loadFromJSON();
    }

    private void loadFromJSON() {
        InputStream in = Banks.class.getResourceAsStream("/banks.json");
        Reader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        Gson gson = new Gson();
        Bank[] bankArray = gson.fromJson(reader, Bank[].class);
        for (Bank bank: bankArray) {
            banks.put(bank.getId(), bank);
        }
    }

    public Map<Integer, Bank> getBanks() {
        return banks;
    }

    public Bank getBankById(Integer id){
        return banks.get(id);
    }

}
