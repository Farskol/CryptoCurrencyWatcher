package cryptocurrency.demo.controller;

import cryptocurrency.demo.entity.Cryptocurrency;
import cryptocurrency.demo.entity.Users;
import cryptocurrency.demo.repo.CryptocurrencyRepo;
import cryptocurrency.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cryptocurrency")
public class CryptocurrencyController {
    @Autowired
    private CryptocurrencyRepo cryptocurrencyRepository;
    @Autowired
    private UserRepo userRepository;


    @GetMapping
    public List<Cryptocurrency> list(){
        return cryptocurrencyRepository.findAll();
    }

//    @GetMapping("{cryptocurrency_id}")
//    public Cryptocurrency getOneById(@PathVariable("cryptocurrency_id") Cryptocurrency cryptocurrency){
//        return cryptocurrency;
//    }

    public  Cryptocurrency findCryptoBySymbol(String symbol){
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        List<Cryptocurrency> cryptocurrencyList = cryptocurrencyRepository.findAll();
        for (Cryptocurrency crypt: cryptocurrencyList) {
            if (crypt.getSymbol().equals(symbol)) {
                cryptocurrency = crypt;
            }
        }
        return cryptocurrency;
    }
    @GetMapping("{symbol}")
    public Cryptocurrency getOneBySymbol(@PathVariable("symbol") String symbol){
        return findCryptoBySymbol(symbol);
    }

    @GetMapping("{symbol}/{username}")
    public Cryptocurrency notify(@PathVariable("symbol") String symbol,
                                 @PathVariable("username") String username){
        Cryptocurrency cryptocurrency = findCryptoBySymbol(symbol);

        Users user = new Users(username,cryptocurrency.getCryptocurrency_id(),cryptocurrency.getPrice_usd());
        userRepository.save(user);

        return cryptocurrency;
    }

}
