package sapoCasaPrincesas;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@ServletComponentScan   // 👈 Esto activa el escaneo de @WebServlet
public class RegistroLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistroLoginApplication.class, args);
    }
}






