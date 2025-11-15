# 🔍 Análise de Caixa Branca – Classe `User` (Java)

Este repositório apresenta a análise completa de **teste de caixa branca** aplicada à classe `User`, utilizada para autenticação e conexão com banco de dados em Java.  
A avaliação incluiu: revisão estática, fluxogramas, grafo de fluxo, cálculo da complexidade ciclomática e identificação dos caminhos básicos do método `verificarUsuario()`.

---

## 📂 Arquivos do Repositório

- 📄 **Código-fonte analisado:**  
  [`users.java`](users.java)

- 📊 **Planilha de Revisão Estática:**  
  [`planilha.png`](planilha.png)

- 🧭 **Fluxograma Geral da Classe (`conectarBD` + `verificarUsuario`):**  
  [`fluxo.png`](fluxo.png)

- 🌸 **Fluxograma + Grafo do Método `verificarUsuario()`:**  
  [`fluxodometodo.png`](fluxodometodo.png)

---

## 🔎 1. Código Base Analisado

O arquivo [`users.java`](users.java) contém dois métodos principais:

### ✔ `conectarBD()`
- Carrega o driver JDBC  
- Tenta conectar ao MySQL  
- Retorna um objeto `Connection` ou `null` em caso de erro  

### ✔ `verificarUsuario()`
- Recebe login e senha  
- Monta a instrução SQL  
- Executa a query  
- Verifica se o usuário existe  
- Retorna **true** ou **false** conforme o resultado  

---

## 📋 2. Revisão Estática

Principais problemas identificados:

- Vulnerabilidade severa a **SQL Injection**  
- `Connection`, `Statement` e `ResultSet` não são fechados  
- Bloco `catch` silencioso (não mostra erros)  
- Risco de `NullPointerException` se a conexão falhar  
- Credenciais e URL do banco **hardcoded**  
- Código sem documentação técnica  

📎 Planilha completa:  
👉 [`planilha.png`](planilha.png)

---

## 🧭 3. Fluxograma Geral da Classe

Mostra a execução completa dos métodos:

- **Esquerda:** `conectarBD()`  
- **Direita:** `verificarUsuario()`  

![Fluxograma da Classe](fluxo.png)

---

## 🌸 4. Fluxograma + Grafo de Fluxo do Método `verificarUsuario()`

O arquivo abaixo representa **tanto o fluxograma quanto o grafo de fluxo (CFG)**, contendo todos os nós (N1 a N15), decisões e desvios:

👉 ![Fluxograma do Método](fluxodometodo.png)

---

## 🔢 5. Complexidade Ciclomática

Foram identificados **2 pontos de decisão**:

1. **Bloco try-catch**  
   - Fluxo normal  
   - Fluxo de exceção  

2. **Condicional `if (rs.next())`**  
   - Usuário encontrado  
   - Usuário não encontrado  

### Cálculo

\[
M = \text{decisões} + 1
\]

\[
M = 2 + 1 = 3
\]

✔ **Complexidade Ciclomática = 3**  
➡ São necessários **3 testes independentes**.

---

## 🛤️ 6. Caminhos Básicos (Detalhados de N1 a N15)

A partir do grafo contido em `fluxodometodo.png`, foram identificados 3 caminhos independentes:

---

### ✔ Caminho Básico 1 – Usuário encontrado

**Fluxo normal sem erros; `rs.next()` retorna true.**

**Sequência de nós:**

N1 → N2 → N3 → N4 → N6 → N7 → N8 → N9 → N10 → N11(true) → N12 → N15

---

### ✔ Caminho Básico 2 – Usuário não encontrado

**Fluxo normal sem erros; `rs.next()` retorna false.**

**Sequência de nós:**

N1 → N2 → N3 → N4 → N6 → N7 → N8 → N9 → N10 → N11(false) → N14 → N15

---

### ✔ Caminho Básico 3 – Fluxo de exceção (erro na conexão ou na query)

Este é **um único caminho básico**, pois todo erro desvia para o bloco `catch`.

#### 🔹 Variação A – Erro ao conectar
N1 → N2 → N3 → N4(erro) → N5 → N15

#### 🔹 Variação B – Erro ao executar a query
N1 → N2 → N3 → N4 → N6 → N7 → N8 → N9 → N10(erro) → N13 → N15

> Ambas as variações pertencem ao mesmo caminho básico porque representam o desvio gerado pelo `try-catch`.

---

## 📘 7. Conclusão

A análise confirmou que o método funciona, porém apresenta fragilidades importantes:

- Vulnerabilidade a SQL Injection  
- Ausência de fechamento de recursos  
- Possíveis erros silenciosos por `catch` vazio  
- Conexão e consulta sem tratamento adequado  
- Variável global desnecessária  

Ainda assim, o fluxograma e o grafo permitiram mapear completamente o método, facilitando a definição da cobertura de testes necessária.

---

## 👩‍💻 Autora

**Julia Carolina do Rosário Lopes**  
Curso de Análise e Desenvolvimento de Sistemas – FACENS  
Disciplina: Qualidade e Testes de Software
