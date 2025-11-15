# 🔍 Análise de Caixa Branca – Classe `User` (Java)

Este repositório reúne a documentação e os resultados obtidos durante a análise de **caixa branca** aplicada à classe `User`, responsável pelo processo de autenticação e pela conexão com o banco de dados de um sistema simples de login.

Todo o estudo foi realizado com foco na observação do fluxo interno do código, verificando pontos críticos, caminhos lógicos e aspectos estruturais importantes para garantir robustez, clareza e segurança.

---

## 📂 Arquivos do Projeto

- **users.java** – Implementação da classe analisada  
- **planilha.png** – Revisão estática respondida  
- **fluxo.png** – Fluxograma geral da classe  
- **fluxo do metodo.png** – Fluxograma dedicado ao método `verificarUsuario()`  
- **README.md** – Documento que descreve toda a análise  

---

## 🧭 1. Entendimento Inicial do Código

A classe avaliada possui dois comportamentos centrais:

1. **Abrir a conexão com o banco**  
   - Responsável por carregar o driver, montar a URL e tentar conectar ao MySQL.

2. **Validar login e senha**  
   - Recebe os dados do usuário  
   - Monta a consulta SQL  
   - Executa o comando  
   - Retorna se o usuário existe ou não

O objetivo da análise foi identificar:  
✔ erros potenciais  
✔ fragilidades na lógica  
✔ riscos de falha  
✔ pontos que exigem testes independentes  

---

## 📌 2. Revisão Estática (Planilha)

A verificação manual do código revelou problemas importantes relacionados à estrutura e qualidade:

- Falta de comentários explicativos nos métodos  
- Strings de conexão “hardcoded”  
- Risco de `NullPointerException` em `conn.createStatement()`  
- Ausência de fechamento dos objetos de conexão  
- Falta de tratamento adequado de exceções  
- Vulnerabilidade severa a SQL Injection  
- Bloco `catch` que engole erros sem relatar nada  

📎 Arquivo: **`planilha.png`**

---

## 🗺️ 3. Fluxos da Classe

Dois fluxogramas foram usados para mapear o comportamento interno:

### 🔹 Fluxograma geral  
Mostra a atuação conjunta entre `conectarBD()` e `verificarUsuario()`, desde o carregamento do driver até o retorno final.

📎 Arquivo: **`fluxo.png`**

### 🔹 Fluxo detalhado do método `verificarUsuario()`  
Representa apenas o caminho do login, destacando as decisões e possíveis desvios.

📎 Arquivo: **`fluxo do metodo.png`**

---

## 🕸️ 4. Grafo de Controle (CFG) – Método `verificarUsuario()`

A partir do segundo fluxograma, foi construído o grafo de fluxo que considera:

- Trecho protegido por `try-catch`
- Estrutura condicional `if (rs.next())`
- Possibilidade de erro tanto na conexão quanto na execução da consulta
- Desvios para autenticação verdadeira ou falsa

### **Nós principais identificados**
- Carregamento do driver  
- Conexão com o banco  
- Montagem da consulta  
- Execução da query  
- Verificação do resultado  
- Blocos de exceção  
- Finalização do método  

---

## 〽️ 5. Complexidade Ciclomática

A complexidade foi calculada com base nos pontos de decisão do método:

- 1º ponto: bloco `try-catch`  
- 2º ponto: condição `if (rs.next())`  

Aplicando a fórmula:

\[
\text{M} = \text{nº de decisões} + 1
\]

obtemos:

✔ **Complexidade Ciclomática = 3**

Ou seja:  
➡ São necessários **3 cenários independentes** para cobrir todo o fluxo lógico.

---

## 🛤️ 6. Caminhos Lógicos Necessários

Com base no grafo, estes são os três caminhos mínimos para garantir cobertura:

### **1. Autenticação bem-sucedida**
- Conexão ok  
- SQL executada  
- `rs.next()` retorna verdadeiro  
- Resultado final: `true`

### **2. Usuário inexistente**
- Conexão ok  
- SQL executada  
- `rs.next()` retorna falso  
- Resultado final: `false`

### **3. Qualquer erro no processo**
- Falha no driver, na conexão ou na execução da query  
- Fluxo desvia para o `catch`  
- Retorno final: `false`  

Esses três caminhos abrangem todos os comportamentos possíveis do método.

---

## 📘 7. Considerações Finais

O estudo mostrou que, apesar de funcional, o código apresenta diversos pontos que comprometem a qualidade e a segurança do sistema. O mapeamento dos fluxos e a análise de complexidade permitiram identificar exatamente onde a lógica se desvia, quais condições impactam o retorno do método e quais elementos exigem correção para um código mais confiável.

Esta documentação reúne tudo o que foi produzido na análise, servindo como material de entrega e consulta.

---

## 👩‍💻 Autora  
**Julia Carolina do Rosário Lopes**  
Centro Universitário FACENS – ADS  
Disciplina: Qualidade e Testes de Software

# 🔍 Análise de Caixa Branca – Classe `User` (Java)

Este repositório reúne a documentação e os resultados obtidos durante a análise de **caixa branca** aplicada à classe `User`, responsável pelo processo de autenticação e pela conexão com o banco de dados de um sistema simples de login.

Todo o estudo foi realizado com foco na observação do fluxo interno do código, verificando pontos críticos, caminhos lógicos e aspectos estruturais importantes para garantir robustez, clareza e segurança.

---

## 📂 Arquivos do Projeto

- **users.java** – Implementação da classe analisada  
- **planilha.png** – Revisão estática respondida  
- **fluxo.png** – Fluxograma geral da classe  
- **fluxo do metodo.png** – Fluxograma dedicado ao método `verificarUsuario()`  
- **README.md** – Documento que descreve toda a análise  

---

## 🧭 1. Entendimento Inicial do Código

A classe avaliada possui dois comportamentos centrais:

1. **Abrir a conexão com o banco**  
   - Responsável por carregar o driver, montar a URL e tentar conectar ao MySQL.

2. **Validar login e senha**  
   - Recebe os dados do usuário  
   - Monta a consulta SQL  
   - Executa o comando  
   - Retorna se o usuário existe ou não

O objetivo da análise foi identificar:  
✔ erros potenciais  
✔ fragilidades na lógica  
✔ riscos de falha  
✔ pontos que exigem testes independentes  

---

## 📌 2. Revisão Estática (Planilha)

A verificação manual do código revelou problemas importantes relacionados à estrutura e qualidade:

- Falta de comentários explicativos nos métodos  
- Strings de conexão “hardcoded”  
- Risco de `NullPointerException` em `conn.createStatement()`  
- Ausência de fechamento dos objetos de conexão  
- Falta de tratamento adequado de exceções  
- Vulnerabilidade severa a SQL Injection  
- Bloco `catch` que engole erros sem relatar nada  

📎 Arquivo: **`planilha.png`**

---

## 🗺️ 3. Fluxos da Classe

Dois fluxogramas foram usados para mapear o comportamento interno:

### 🔹 Fluxograma geral  
Mostra a atuação conjunta entre `conectarBD()` e `verificarUsuario()`, desde o carregamento do driver até o retorno final.

📎 Arquivo: **`fluxo.png`**

### 🔹 Fluxo detalhado do método `verificarUsuario()`  
Representa apenas o caminho do login, destacando as decisões e possíveis desvios.

📎 Arquivo: **`fluxo do metodo.png`**

---

## 🕸️ 4. Grafo de Controle (CFG) – Método `verificarUsuario()`

A partir do segundo fluxograma, foi construído o grafo de fluxo que considera:

- Trecho protegido por `try-catch`
- Estrutura condicional `if (rs.next())`
- Possibilidade de erro tanto na conexão quanto na execução da consulta
- Desvios para autenticação verdadeira ou falsa

### **Nós principais identificados**
- Carregamento do driver  
- Conexão com o banco  
- Montagem da consulta  
- Execução da query  
- Verificação do resultado  
- Blocos de exceção  
- Finalização do método  

---

## 〽️ 5. Complexidade Ciclomática

A complexidade foi calculada com base nos pontos de decisão do método:

- 1º ponto: bloco `try-catch`  
- 2º ponto: condição `if (rs.next())`  

Aplicando a fórmula:

\[
\text{M} = \text{nº de decisões} + 1
\]

obtemos:

✔ **Complexidade Ciclomática = 3**

Ou seja:  
➡ São necessários **3 cenários independentes** para cobrir todo o fluxo lógico.

---

## 🛤️ 6. Caminhos Lógicos Necessários

Com base no grafo, estes são os três caminhos mínimos para garantir cobertura:

### **1. Autenticação bem-sucedida**
- Conexão ok  
- SQL executada  
- `rs.next()` retorna verdadeiro  
- Resultado final: `true`

### **2. Usuário inexistente**
- Conexão ok  
- SQL executada  
- `rs.next()` retorna falso  
- Resultado final: `false`

### **3. Qualquer erro no processo**
- Falha no driver, na conexão ou na execução da query  
- Fluxo desvia para o `catch`  
- Retorno final: `false`  

Esses três caminhos abrangem todos os comportamentos possíveis do método.

---

## 📘 7. Considerações Finais

O estudo mostrou que, apesar de funcional, o código apresenta diversos pontos que comprometem a qualidade e a segurança do sistema. O mapeamento dos fluxos e a análise de complexidade permitiram identificar exatamente onde a lógica se desvia, quais condições impactam o retorno do método e quais elementos exigem correção para um código mais confiável.

Esta documentação reúne tudo o que foi produzido na análise, servindo como material de entrega e consulta.

---

## 👩‍💻 Autora  
**Julia Carolina do Rosário Lopes**  
Centro Universitário FACENS – ADS  
Disciplina: Qualidade e Testes de Software

# 🔍 Análise de Caixa Branca – Classe `User` (Java)

Este repositório reúne a documentação e os resultados obtidos durante a análise de **caixa branca** aplicada à classe `User`, responsável pelo processo de autenticação e pela conexão com o banco de dados de um sistema simples de login.

Todo o estudo foi realizado com foco na observação do fluxo interno do código, verificando pontos críticos, caminhos lógicos e aspectos estruturais importantes para garantir robustez, clareza e segurança.

---

## 📂 Arquivos do Projeto

- **users.java** – Implementação da classe analisada  
- **planilha.png** – Revisão estática respondida  
- **fluxo.png** – Fluxograma geral da classe  
- **fluxo do metodo.png** – Fluxograma dedicado ao método `verificarUsuario()`  
- **README.md** – Documento que descreve toda a análise  

---

## 🧭 1. Entendimento Inicial do Código

A classe avaliada possui dois comportamentos centrais:

1. **Abrir a conexão com o banco**  
   - Responsável por carregar o driver, montar a URL e tentar conectar ao MySQL.

2. **Validar login e senha**  
   - Recebe os dados do usuário  
   - Monta a consulta SQL  
   - Executa o comando  
   - Retorna se o usuário existe ou não

O objetivo da análise foi identificar:  
✔ erros potenciais  
✔ fragilidades na lógica  
✔ riscos de falha  
✔ pontos que exigem testes independentes  

---

## 📌 2. Revisão Estática (Planilha)

A verificação manual do código revelou problemas importantes relacionados à estrutura e qualidade:

- Falta de comentários explicativos nos métodos  
- Strings de conexão “hardcoded”  
- Risco de `NullPointerException` em `conn.createStatement()`  
- Ausência de fechamento dos objetos de conexão  
- Falta de tratamento adequado de exceções  
- Vulnerabilidade severa a SQL Injection  
- Bloco `catch` que engole erros sem relatar nada  

📎 Arquivo: **`planilha.png`**

---

## 🗺️ 3. Fluxos da Classe

Dois fluxogramas foram usados para mapear o comportamento interno:

### 🔹 Fluxograma geral  
Mostra a atuação conjunta entre `conectarBD()` e `verificarUsuario()`, desde o carregamento do driver até o retorno final.

📎 Arquivo: **`fluxo.png`**

### 🔹 Fluxo detalhado do método `verificarUsuario()`  
Representa apenas o caminho do login, destacando as decisões e possíveis desvios.

📎 Arquivo: **`fluxo do metodo.png`**

---

## 🕸️ 4. Grafo de Controle (CFG) – Método `verificarUsuario()`

A partir do segundo fluxograma, foi construído o grafo de fluxo que considera:

- Trecho protegido por `try-catch`
- Estrutura condicional `if (rs.next())`
- Possibilidade de erro tanto na conexão quanto na execução da consulta
- Desvios para autenticação verdadeira ou falsa

### **Nós principais identificados**
- Carregamento do driver  
- Conexão com o banco  
- Montagem da consulta  
- Execução da query  
- Verificação do resultado  
- Blocos de exceção  
- Finalização do método  

---

## 〽️ 5. Complexidade Ciclomática

A complexidade foi calculada com base nos pontos de decisão do método:

- 1º ponto: bloco `try-catch`  
- 2º ponto: condição `if (rs.next())`  

Aplicando a fórmula:

\[
\text{M} = \text{nº de decisões} + 1
\]

obtemos:

✔ **Complexidade Ciclomática = 3**

Ou seja:  
➡ São necessários **3 cenários independentes** para cobrir todo o fluxo lógico.

---

## 🛤️ 6. Caminhos Lógicos Necessários

Com base no grafo, estes são os três caminhos mínimos para garantir cobertura:

### **1. Autenticação bem-sucedida**
- Conexão ok  
- SQL executada  
- `rs.next()` retorna verdadeiro  
- Resultado final: `true`

### **2. Usuário inexistente**
- Conexão ok  
- SQL executada  
- `rs.next()` retorna falso  
- Resultado final: `false`

### **3. Qualquer erro no processo**
- Falha no driver, na conexão ou na execução da query  
- Fluxo desvia para o `catch`  
- Retorno final: `false`  

Esses três caminhos abrangem todos os comportamentos possíveis do método.

---

## 📘 7. Considerações Finais

O estudo mostrou que, apesar de funcional, o código apresenta diversos pontos que comprometem a qualidade e a segurança do sistema. O mapeamento dos fluxos e a análise de complexidade permitiram identificar exatamente onde a lógica se desvia, quais condições impactam o retorno do método e quais elementos exigem correção para um código mais confiável.

Esta documentação reúne tudo o que foi produzido na análise, servindo como material de entrega e consulta.

---

## 👩‍💻 Autora  
**Julia Carolina do Rosário Lopes**  
Centro Universitário FACENS – ADS  
Disciplina: Qualidade e Testes de Software
