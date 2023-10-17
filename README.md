# Padrões de projeto com java puro.

## Padrão de Projeto Singleton:

O padrão Singleton é um padrão de design que garante que uma classe tenha apenas uma instância e fornece um ponto global
para essa instância. Ele é útil quando exatamente uma instância de uma classe é necessária para coordenar ações em todo
o sistema. O Singleton é implementado geralmente com um método de instância privada, uma variável de instância privada
para armazenar a instância única e um método público que retorna essa instância.

**Exemplo Prático em Java:**

Vamos criar uma implementação simples do padrão Singleton para uma classe `Logger` que registra mensagens no console.

```java
public class Logger {
    // A instância única da classe Logger
    private static Logger instance;

    // Um construtor privado para evitar a criação de instâncias fora da classe
    private Logger() {
        // Pode incluir inicializações necessárias aqui
    }

    // Método público para obter a instância única
    public static Logger getInstance() {
        // Se a instância ainda não foi criada, criá-la
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Método para registrar uma mensagem no console
    public void log(String message) {
        System.out.println(message);
    }
}
```

Agora, você pode usar o Singleton `Logger` em outros lugares do seu código da seguinte maneira:

```java
public class Main {
    public static void main(String[] args) {
        // Obtendo a instância única do Logger
        Logger logger = Logger.getInstance();

        // Usando o logger para registrar mensagens
        logger.log("Mensagem 1");
        logger.log("Mensagem 2");
        logger.log("Mensagem 3");
    }
}
```

**Explicação do Código:**

1. **Variável Estática Privada (`instance`):**
    - A instância única da classe é armazenada como uma variável estática privada.

2. **Construtor Privado:**
    - O construtor da classe é privado para evitar que outras classes instanciem diretamente.

3. **Método Público (`getInstance`):**
    - O método público `getInstance` é usado para obter a única instância da classe. Se a instância ainda não foi
criada, ela é criada; caso contrário, a instância existente é retornada.

4. **Método `log`:**
    - Um método simples para registrar mensagens. Este método poderia ter lógica mais complexa dependendo dos
requisitos do sistema.

**Nota:**
- Este exemplo usa uma verificação simples de nulidade para criar a instância única. Em ambientes multi-threaded,
considerações de concorrência devem ser tratadas usando técnicas como o bloqueio (`synchronized`) ou inicialização
preguiçosa dupla (`double-checked locking`).

- Em Java, a partir da versão 5, a criação preguiçosa pode ser feita de forma segura usando a classe `AtomicReference`
ou inicialização estática interna. A classe `Enum` também é uma maneira segura e eficiente de implementar o Singleton.

#

## Padrão de Projeto Strategy:

O padrão Strategy é um padrão de design comportamental que define uma família de algoritmos, encapsula cada um deles e
torna-os intercambiáveis. Ele permite que o cliente escolha o algoritmo a ser utilizado durante a execução do programa,
proporcionando flexibilidade e separação de responsabilidades.

**Exemplo Prático em Java:**

Vamos criar um exemplo prático de um sistema de pagamento que utiliza o padrão Strategy. Vamos definir uma interface
`PaymentStrategy` que representará os diferentes métodos de pagamento, e então implementaremos duas estratégias
concretas: `CreditCardPayment` e `PayPalPayment`. O cliente, representado por uma classe `ShoppingCart`, poderá
escolher a estratégia de pagamento desejada.

```java
// Interface Strategy
public interface PaymentStrategy {
    void pay(int amount);
}

// Implementações específicas
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using credit card: " + cardNumber);
    }
}

public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal with email: " + email);
    }
}

// Contexto que utiliza a estratégia
public class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
```

**Explicação do Código:**

1. **Interface `PaymentStrategy`:**
   - Define a interface comum para as estratégias de pagamento.

2. **Implementações Concretas (`CreditCardPayment` e `PayPalPayment`):**
   - Implementam a interface `PaymentStrategy` com algoritmos específicos de pagamento.

3. **`ShoppingCart` (Contexto):**
   - Representa o cliente e mantém uma referência para a estratégia de pagamento escolhida.
   - O método `checkout` utiliza a estratégia de pagamento para realizar o pagamento.

**Utilizando o Código:**

```java
public class Main {
    public static void main(String[] args) {
        // Criando instâncias das estratégias de pagamento
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9012-3456", "John Doe");
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com");

        // Criando um carrinho de compras
        ShoppingCart shoppingCart = new ShoppingCart();

        // Configurando a estratégia de pagamento no carrinho
        shoppingCart.setPaymentStrategy(creditCardPayment);

        // Realizando o pagamento
        shoppingCart.checkout(100);

        // Mudando a estratégia de pagamento
        shoppingCart.setPaymentStrategy(payPalPayment);

        // Realizando outro pagamento
        shoppingCart.checkout(50);
    }
}
```

**Resultado Esperado:**
```
Paying 100 using credit card: 1234-5678-9012-3456
Paying 50 using PayPal with email: john.doe@example.com
```

Neste exemplo, o cliente (`ShoppingCart`) pode escolher entre diferentes estratégias de pagamento, como cartão de
crédito ou PayPal, e a lógica de pagamento específica é encapsulada em cada estratégia, seguindo o princípio do padrão
Strategy. Isso permite que novas estratégias sejam adicionadas sem modificar o cliente.

#

## Padrão de Projeto Facade:

O padrão de projeto Facade é um padrão estrutural que fornece uma interface unificada para um conjunto de interfaces em
um subsistema. Ele define uma interface de nível mais alto que facilita o uso do sistema, proporcionando uma camada
simplificada para interagir com componentes mais complexos.


**Exemplo Prático em Java:**

Vamos criar um exemplo prático usando o padrão Facade para um sistema de áudio, onde temos várias partes complexas,
como um amplificador, um player de CD e um sistema de alto-falantes. A classe `HomeTheaterFacade` servirá como uma
fachada para simplificar o uso desses componentes.

```java
// Subsistema complexo
class Amplifier {
    void on() {
        System.out.println("Amplifier is on");
    }

    void setVolume(int volume) {
        System.out.println("Setting volume to " + volume);
    }

    void off() {
        System.out.println("Amplifier is off");
    }
}

class CDPlayer {
    void play() {
        System.out.println("CD Player is playing");
    }

    void stop() {
        System.out.println("CD Player stopped");
    }
}

class Speakers {
    void on() {
        System.out.println("Speakers are on");
    }

    void off() {
        System.out.println("Speakers are off");
    }
}

// Facade
class HomeTheaterFacade {
    private Amplifier amplifier;
    private CDPlayer cdPlayer;
    private Speakers speakers;

    public HomeTheaterFacade(Amplifier amplifier, CDPlayer cdPlayer, Speakers speakers) {
        this.amplifier = amplifier;
        this.cdPlayer = cdPlayer;
        this.speakers = speakers;
    }

    // Método para ligar o sistema de home theater
    public void turnOn() {
        amplifier.on();
        cdPlayer.play();
        speakers.on();
    }

    // Método para desligar o sistema de home theater
    public void turnOff() {
        amplifier.off();
        cdPlayer.stop();
        speakers.off();
    }
}
```

**Explicação do Código:**

1. **Subsistema Complexo (Amplifier, CDPlayer, Speakers):**
   - Representa componentes individuais do sistema de áudio.

2. **`HomeTheaterFacade` (Fachada):**
   - Fornece uma interface simplificada para ligar e desligar o sistema de home theater.
   - Internamente, gerencia a interação com os subsistemas complexos.

**Utilizando o Código:**

```java
public class Main {
    public static void main(String[] args) {
        // Criando instâncias dos subsistemas
        Amplifier amplifier = new Amplifier();
        CDPlayer cdPlayer = new CDPlayer();
        Speakers speakers = new Speakers();

        // Criando a fachada
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, cdPlayer, speakers);

        // Utilizando a fachada para ligar e desligar o sistema de home theater
        homeTheater.turnOn();
        System.out.println("----- Watching a movie -----");
        homeTheater.turnOff();
    }
}
```

**Resultado Esperado:**
```
Amplifier is on
CD Player is playing
Speakers are on
----- Watching a movie -----
Amplifier is off
CD Player stopped
Speakers are off
```

Neste exemplo, a fachada (`HomeTheaterFacade`) simplifica a interação com os subsistemas complexos
(`Amplifier`, `CDPlayer`, `Speakers`). O cliente pode ligar e desligar o sistema de home theater sem se preocupar com
os detalhes de como cada componente é manipulado internamente. O padrão Facade é particularmente útil quando você
deseja fornecer uma interface mais simples e coesa para um sistema complexo.

#
# Padrões de projeto com Spring Framework.
#
## Padrão de projeto Singleton com Spring Boot

Em um ambiente Spring Boot, o padrão Singleton é gerenciado pelo próprio framework, que utiliza o padrão Injeção de
Dependência para criar e gerenciar instâncias de beans. Portanto, em muitos casos, você não precisa implementar
manualmente o Singleton, pois o Spring cuidará disso para você.

No entanto, se você precisar garantir que uma classe seja um Singleton específico (não dependendo apenas do
gerenciamento de beans do Spring), você pode usar a anotação `@Singleton` do Spring. Esta anotação é uma alternativa à
anotação `@Component`, que é amplamente usada para definir beans gerenciados pelo Spring.

**Exemplo Prático:**

1. **Classe de Serviço (ExemploService.java):**
   ```java
   import org.springframework.stereotype.Service;

   @Service
   public class ExemploService {

       public void realizarAcao() {
           System.out.println("Ação realizada por " + this.hashCode());
       }
   }
   ```

   Neste exemplo, a classe `ExemploService` é marcada como `@Service`, uma anotação específica do Spring para indicar
que ela é um bean gerenciado. Isso significa que o Spring garantirá que haja apenas uma instância desta classe no
contexto da aplicação.


2. **Classe de Controle (ExemploController.java):**
   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   public class ExemploController {

       private final ExemploService exemploService;

       @Autowired
       public ExemploController(ExemploService exemploService) {
           this.exemploService = exemploService;
       }

       @GetMapping("/realizar-acao")
       public String realizarAcao() {
           exemploService.realizarAcao();
           return "Ação realizada. Verifique o console para a saída.";
       }
   }
   ```

   Neste exemplo, a classe `ExemploController` possui uma dependência da classe `ExemploService`, e essa dependência é
injetada pelo Spring no construtor da classe.


3. **Aplicação Spring Boot (Application.java):**
   ```java
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class Application {

       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```


   A classe principal da aplicação Spring Boot.

4. **Executando a Aplicação:**
   - Execute a aplicação Spring Boot.
   - Acesse `http://localhost:8080/realizar-acao` em um navegador.

Ao acessar a URL, você verá a mensagem de que a ação foi realizada, e o console da aplicação mostrará a hash da
instância da classe `ExemploService`. Mesmo que você acesse a URL várias vezes, a mesma instância de `ExemploService`
será usada, demonstrando o comportamento Singleton gerenciado pelo Spring.

#
## Padrão de projeto Strategy com Spring Boot

O padrão de projeto Strategy pode ser implementado em um aplicativo Spring Boot através do uso de interfaces e injeção
de dependência. Vamos criar um exemplo prático usando o padrão Strategy para uma aplicação de pagamento, onde
diferentes estratégias de pagamento podem ser escolhidas.

**Passos para implementar o padrão Strategy em um aplicativo Spring Boot:**

1. **Definir a Interface Strategy (PaymentStrategy.java):**
   ```java
   public interface PaymentStrategy {
       void processPayment(double amount);
   }
   ```

   Esta é a interface que todas as estratégias de pagamento devem implementar.

2. **Implementar Estratégias Concretas (CreditCardPayment.java e PayPalPayment.java):**
   ```java
   import org.springframework.stereotype.Component;

   @Component
   public class CreditCardPayment implements PaymentStrategy {
       @Override
       public void processPayment(double amount) {
           System.out.println("Processing credit card payment of amount: " + amount);
           // Lógica de processamento de pagamento com cartão de crédito
       }
   }
   ```

   ```java
   import org.springframework.stereotype.Component;

   @Component
   public class PayPalPayment implements PaymentStrategy {
       @Override
       public void processPayment(double amount) {
           System.out.println("Processing PayPal payment of amount: " + amount);
           // Lógica de processamento de pagamento com PayPal
       }
   }
   ```

   Cada estratégia concreta implementa a interface `PaymentStrategy` e fornece sua própria lógica de processamento de
pagamento.


3. **Criar a Classe Contexto (PaymentContext.java):**
   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Service;

   @Service
   public class PaymentContext {
       private final PaymentStrategy paymentStrategy;

       @Autowired
       public PaymentContext(PaymentStrategy paymentStrategy) {
           this.paymentStrategy = paymentStrategy;
       }

       public void processPayment(double amount) {
           paymentStrategy.processPayment(amount);
       }
   }
   ```

   A classe `PaymentContext` é o contexto que utiliza a estratégia de pagamento. Ela possui uma referência à interface
`PaymentStrategy` e delega o processamento para a estratégia atual.


4. **Utilizar a Estratégia no Controlador (PaymentController.java):**
   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   @RequestMapping("/payment")
   public class PaymentController {

       private final PaymentContext paymentContext;

       @Autowired
       public PaymentController(PaymentContext paymentContext) {
           this.paymentContext = paymentContext;
       }

       @GetMapping("/process/{amount}/{method}")
       public String processPayment(@PathVariable double amount, @PathVariable String method) {
           switch (method.toLowerCase()) {
               case "creditcard":
                   paymentContext.processPayment(amount);
                   return "Credit card payment processed.";
               case "paypal":
                   paymentContext.processPayment(amount);
                   return "PayPal payment processed.";
               default:
                   return "Invalid payment method.";
           }
       }
   }
   ```

   O controlador expõe um endpoint que permite escolher a estratégia de pagamento (método de pagamento) e processar o
pagamento com base nessa estratégia.


5. **Executar a Aplicação Spring Boot:**
   - Execute a aplicação Spring Boot.
   - Acesse `http://localhost:8080/payment/process/100/creditcard` ou `http://localhost:8080/payment/process/50/paypal`
   para ver a saída no console.

Neste exemplo, o padrão Strategy é utilizado para processar pagamentos de diferentes maneiras com base na estratégia
escolhida no momento da solicitação. Cada estratégia é uma implementação separada da interface `PaymentStrategy`, e a
escolha da estratégia é delegada ao contexto (`PaymentContext`). Isso proporciona uma maneira flexível de adicionar ou
alterar estratégias de pagamento sem modificar o código do cliente.

#
## Padrão de projeto Facade com Spring Boot

O padrão de projeto Facade em um aplicativo Spring Boot pode ser aplicado para simplificar a interação com um conjunto
de serviços ou subsistemas complexos. Vamos criar um exemplo prático utilizando o padrão Facade para um sistema de
pedidos, onde a fachada facilitará o processo de criação e rastreamento de pedidos.

**Passos para implementar o padrão Facade em um aplicativo Spring Boot:**

1. **Definir as Classes de Serviço (ProductService.java, OrderService.java, ShippingService.java):**
   ```java
   import org.springframework.stereotype.Service;

   @Service
   public class ProductService {
       public String getProductDetails(String productId) {
           // Lógica para obter detalhes do produto
           return "Details for product ID " + productId;
       }
   }
   ```

   ```java
   import org.springframework.stereotype.Service;

   @Service
   public class OrderService {
       public String createOrder(String productId, int quantity) {
           // Lógica para criar um pedido
           return "Order created for product ID " + productId + " with quantity " + quantity;
       }
   }
   ```

   ```java
   import org.springframework.stereotype.Service;

   @Service
   public class ShippingService {
       public String shipOrder(String orderId) {
           // Lógica para enviar o pedido
           return "Order shipped for order ID " + orderId;
       }
   }
   ```

   Cada classe de serviço representa um subsistema ou serviço específico relacionado ao processo de pedidos.

2. **Criar a Classe Facade (OrderFacade.java):**
   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Service;

   @Service
   public class OrderFacade {
       private final ProductService productService;
       private final OrderService orderService;
       private final ShippingService shippingService;

       @Autowired
       public OrderFacade(ProductService productService, OrderService orderService, ShippingService shippingService) {
           this.productService = productService;
           this.orderService = orderService;
           this.shippingService = shippingService;
       }

       public String processOrder(String productId, int quantity) {
           String productDetails = productService.getProductDetails(productId);
           String orderDetails = orderService.createOrder(productId, quantity);
           String shippingDetails = shippingService.shipOrder(orderDetails);

           return "Order processed successfully:\n" +
                  productDetails + "\n" +
                  orderDetails + "\n" +
                  shippingDetails;
       }
   }
   ```

   A classe `OrderFacade` serve como a fachada para simplificar a interação com os serviços relacionados a pedidos. Ela
coordena as chamadas aos serviços internos e retorna um resultado consolidado.


3. **Utilizar a Fachada no Controlador (OrderController.java):**
   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   @RequestMapping("/order")
   public class OrderController {

       private final OrderFacade orderFacade;

       @Autowired
       public OrderController(OrderFacade orderFacade) {
           this.orderFacade = orderFacade;
       }

       @GetMapping("/process/{productId}/{quantity}")
       public String processOrder(@PathVariable String productId, @PathVariable int quantity) {
           return orderFacade.processOrder(productId, quantity);
       }
   }
   ```

   O controlador expõe um endpoint que utiliza a fachada para processar um pedido com base no produto e quantidade
fornecidos.


4. **Executar a Aplicação Spring Boot:**
   - Execute a aplicação Spring Boot.
   - Acesse `http://localhost:8080/order/process/123/5` em um navegador.

Neste exemplo, a fachada (`OrderFacade`) simplifica a interação com os serviços internos relacionados a pedidos. O
controlador (`OrderController`) utiliza a fachada para processar um pedido, ocultando os detalhes internos dos serviços.
Isso proporciona uma interface mais fácil de usar e mantém a complexidade interna encapsulada na fachada. O padrão
Facade é particularmente útil quando você deseja fornecer uma interface mais simples e coesa para um conjunto de
serviços ou subsistemas.


> Reprodução do projeto através da aula.
> 
> Utilização Java 17
> 
> Versão do Spring Boot - 3.1.4
> 
> Dependencia para inicialização do Swagger com o OpenApi.
> 
> Banco de dados em memória H2