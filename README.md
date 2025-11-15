# 🔍 Análise de Caixa Branca – Classe `User` (Java)

Este repositório documenta a análise de **caixa branca** realizada sobre a classe `User`, responsável pelos processos de conexão com o banco de dados e verificação de credenciais.  
A análise incluiu inspeção estática, fluxogramas, grafo de fluxo, complexidade ciclomática e identificação dos caminhos básicos.

---

## 🗂️ Arquivos incluídos na análise

| Arquivo | Função |
|--------|--------|
| `users.java` | Código-fonte analisado |
| `planilha.png` | Revisão estática |
| `fluxo.png` | Fluxograma geral da classe |
| `fluxodometodo.png` | Fluxograma + Grafo do método verificarUsuario() |
| `README.md` | Documento oficial da análise |

---

## 🔎 1. Código-base analisado

📄 **users.java** → :contentReference[oaicite:0]{index=0}

A classe possui:

### ✔ `conectarBD()`
- Carrega driver JDBC  
- Tenta conectar ao MySQL  
- Retorna uma conexão ou null  

### ✔ `verificarUsuario()`
- Recebe login e senha  
- Monta SQL  
- Executa a query  
- Verifica o resultado  
- Retorna true/false  

---

## 📋 2. Revisão Estática do Código

Principais pontos identificados:

- ausênca de comentários
- perigo de SQL Injection
- conexão pode retornar null
- risco de NullPointerException
- conexões e statements não são fechados
- bloco catch vazio (engole erros)
- credenciais hardcoded

📎 **Planilha:**
![Planilha](planilha.png)

---

## 🧭 3. Fluxograma Geral da Classe `User`

Representa o fluxo dos dois métodos da classe:

- Esquerda → `conectarBD()`
- Direita → `verificarUsuario()`

![Fluxo geral](fluxo.png)

---

## 🌸 4. Fluxograma Detalhado do Método `verificarUsuario()`

Este fluxograma foi usado para identificar nós, decisões e desvios lógicos.

![Fluxo do método](fluxodometodo.png)

---

## 🧠 5. Grafo de Fluxo (CFG)

No projeto, **o arquivo `fluxodometodo.png` também representa o grafo de fluxo**, pois contém:

- todos os nós (N1 a N15)
- decisões do método
- desvios lógicos
- caminho de exceção
- caminho verdadeiro e falso do `rs.next()`

✔ Não há outro arquivo de grafo — **este é o grafo oficial da análise.**

📎 **Grafo de Fluxo (CFG):**  
![Grafo do método](fluxodometodo.png)

---

## 📊 6. Complexidade Ciclomática

Foram identificados:

1. Desvio por exceção (`try-catch`)
2. Condição `if (rs.next())`

Fórmula:

\[
M = \text{decisões} + 1
\]

✔ **Complexidade = 3**

➡ São necessários **3 testes independentes**.

---

## 🛤️ 7. Caminhos Independentes

### ✔ Caminho 1 — Usuário encontrado
Fluxo normal → `rs.next() = true` → autenticação bem-sucedida.

### ✔ Caminho 2 — Usuário inexistente
Fluxo normal → `rs.next() = false`.

### ✔ Caminho 3 — Exceção
Erro na conexão ou execução da query → desvio para o catch.

---

## 📘 8. Conclusão

A análise mostrou que o método funciona, porém apresenta riscos importantes:

- SQL Injection
- erros silenciosos
- recursos não fechados
- falha caso a conexão seja nula  
- blocos catch vazios

O fluxograma e o grafo (`fluxodometodo.png`) permitiram entender exatamente os caminhos internos e definir a cobertura de testes necessária para o método.

---

## 👩‍💻 Autora

**Julia Carolina do Rosário Lopes**  
Análise e Desenvolvimento de Sistemas — FACENS  
Disciplina: Qualidade e Testes de Software
