package una.bolsadeempleo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

/*
* Marisol Quirós Víquez - 402430410
* Alcides Jiménez Carrillo - 402670686
* */

@SpringBootApplication
public class BolsaDeEmpleoApplication {

    public static void main(String[] args) {
        System.out.println(
                BCrypt.hashpw("1234", BCrypt.gensalt())
        );

        SpringApplication.run(BolsaDeEmpleoApplication.class, args);
    }

}
