# 📱 Softtek Mental Health App

**Aplicativo Android** desenvolvido como parte de um desafio da FIAP para oferecer suporte à saúde mental e psicossocial dos colaboradores da Softtek. Esta solução foi construída com base nas diretrizes atualizadas da NR1, promovendo bem-estar através de funcionalidades interativas e recursos de apoio digital.

---

## 🚀 Visão Geral

A proposta central do app é criar um ambiente acolhedor e funcional para:

- 🧠 Avaliação psicossocial periódica
- 📊 Visualização de histórico e dashboards personalizados
- 📘 Diário de humor
- 🗣 Canal de escuta e recursos de apoio emocional
- 🔔 Lembretes personalizados por push notification

---

## 🧩 Tecnologias Utilizadas

- **Kotlin**
- **Jetpack Compose**
- **Room Database**
- **MVVM Architecture**
- **Retrofit** (para consumo de API motivacional)
- **Android Notification Manager**

---

## 🏗 Estrutura do Projeto

- `MainActivity.kt`: ponto de entrada do app
- `screens/`: telas como `HomeScreen`, `AvaliacaoScreen`, `DashBoardScreen`, etc.
- `data/`: DAO, models e banco de dados local com Room
- `api/`: consumo de mensagens motivacionais via Retrofit
- `util/`: gerenciamento de notificações
- `ui/theme/`: personalização de temas e cores

---

## 🛠 Como Rodar Localmente

```bash
git clone https://github.com/seu-usuario/Softtek_FIAP.git
cd Softtek_FIAP
