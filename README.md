# Projeto Account

# Requires

* Java 11
* Docker - https://docs.docker.com/install/
* Docker compose - https://docs.docker.com/compose/install/
* Gradle - https://gradle.org/install/


# Extras:

* Adicionei o campo balance na entidade Account para controle de saldo.
* Coloquei a regra para não subtrair caso não haja saldo suficiente.
* Adicionei um hadler para tratamento de argumentos inválidos.
* Incluí um endpoint para consulta do saldo da Account. Ex: /accounts/balance/1.
* Foi Implementado para as Operações o *Padrão de Projeto STRATEGY*, intencionalmente, pois caso futuramente haja regras específicas de bônus, taxa ou impostos para cada uma das Operações será facimente implementada, sem muita alteração de código.
* Implementação de teste para a camada Service como modelo.
* Também é possível baixa e buildar a imagem diretamente do DockerHub, versão latest.

---

# Rodado o profile de prod (POSTGRES)


* Na raiz do projeto buildar o projeto:
```bash
$ gradle clean build
```

* Executar o seguinte comando para criar a imagem:
```bash
$ docker build -t byamada/account-api:latest .
```

* Primeiramente vamos subir o banco de dados. Na raiz do projeto execute o comando:

```bash
$ docker-compose up -d
```

:: Obs: como o banco está sendo criado, para não correr falha no carregamendo do APP
foi adicionada a regra de restart on failure no docker-compose.yml, aguardar pelo menos 30 segundos,
até que esteja no ar.

# ( Opcional )

* Caso queira acessar o banco via pgadmin:

* Para testar se o banco está funcionando, no navegador acesse o pgadmin:
http://localhost:15432

  * usuário: postgres@email.com
  * password: postgres

* No Pgadmin via browser: 
* Server > clique com direito do mouse > Create > Server... > aba General > coloque 'account' no campo Name > aba Connections > coloque 'postgres-compose' no campo Host > coloque 'postgres' no campo Password > clique em Save

---

# Rodado o profile de dev (H2 banco em memória)

* Importar o projeto no IntelliJ
* Instalar no Intellij o plugin do lombok, caso ainda esteja instalado.
* Adicionar configuração de execução com java 11 e executar.

# ( Opcional )

* Caminho do banco em memória/arquivo: http://localhost:8080/h2-console
  * driver: org.h2.Driver
  * jdbc url: jdbc:h2:file:~/test
  * user: sa
  * sem senha deixar o campo password em branco


---


# Testes:

* Importar no postman o arquivo 'ACCOUNT-API.postman_collection.json' que se encontra na pasta /postma-test

---
