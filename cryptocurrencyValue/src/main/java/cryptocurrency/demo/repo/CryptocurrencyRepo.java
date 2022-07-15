package cryptocurrency.demo.repo;

import cryptocurrency.demo.entity.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptocurrencyRepo extends JpaRepository<Cryptocurrency, Long> {
}
