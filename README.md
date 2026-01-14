# 💱 Conversor de Moedas - Android App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green.svg" alt="Platform">
  <img src="https://img.shields.io/badge/Language-Java-orange.svg" alt="Language">
  <img src="https://img.shields.io/badge/Gradle-8.x-blue.svg" alt="Gradle">
  <img src="https://img.shields.io/badge/AndroidX-Yes-brightgreen.svg" alt="AndroidX">
</p>

Aplicativo Android nativo para conversão de moedas em tempo real, desenvolvido com as tecnologias mais modernas do ecossistema Android.

## 📱 Sobre o Projeto

O **Conversor de Moedas** é um aplicativo mobile que permite aos usuários realizar conversões entre diferentes moedas de forma rápida e intuitiva. Desenvolvido seguindo as melhores práticas e padrões de desenvolvimento Android.

## ✨ Funcionalidades

- ✅ Conversão entre múltiplas moedas
- ✅ Interface intuitiva e responsiva
- ✅ Design Material seguindo diretrizes do Google
- ✅ Performance otimizada
- ✅ Compatibilidade com versões modernas do Android

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Build System:** Gradle 8.x
- **SDK:** Android SDK
- **UI Framework:** AndroidX + Material Design
- **IDE Recomendada:** Android Studio

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- [Android Studio](https://developer.android.com/studio) (versão mais recente)
- JDK 11 ou superior
- Android SDK (API Level 21+)
- Gradle 8.x (incluído no wrapper)

## 🚀 Como Executar o Projeto

### 1. Clone o repositório
```bash
git clone [URL_DO_SEU_REPOSITORIO]
cd ConversorMoedasApp
```

### 2. Abra no Android Studio

1. Abra o Android Studio
2. Selecione `File > Open`
3. Navegue até a pasta do projeto e selecione-a
4. Aguarde o Gradle sincronizar as dependências

### 3. Execute o aplicativo

**Opção 1: Via Android Studio**
- Clique no botão `Run` (▶️) ou pressione `Shift + F10`
- Selecione um dispositivo (emulador ou físico)

**Opção 2: Via Linha de Comando**
```bash
# Linux/Mac
./gradlew installDebug

# Windows
gradlew.bat installDebug
```

## 📂 Estrutura do Projeto
```
ConversorMoedasApp/
├── app/                          # Módulo principal do aplicativo
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/            # Código-fonte Java
│   │   │   ├── res/             # Recursos (layouts, strings, etc.)
│   │   │   └── AndroidManifest.xml
│   │   └── test/                # Testes unitários
│   └── build.gradle             # Configurações do módulo
├── gradle/                      # Wrapper do Gradle
├── build.gradle                 # Configurações globais do projeto
├── settings.gradle              # Configurações de módulos
├── gradle.properties            # Propriedades do Gradle
└── README.md                    # Este arquivo
```

## ⚙️ Configurações do Gradle

O projeto está configurado com as seguintes otimizações:

- **JVM Args:** `-Xmx2048m` para melhor performance durante o build
- **AndroidX:** Habilitado para uso de bibliotecas modernas
- **Non-Transitive R Class:** Reduz o tamanho do APK

## 🔧 Build do APK

### Debug Build
```bash
./gradlew assembleDebug
```

O APK será gerado em: `app/build/outputs/apk/debug/`

### Release Build
```bash
./gradlew assembleRelease
```

O APK será gerado em: `app/build/outputs/apk/release/`

## 🧪 Executar Testes
```bash
# Testes unitários
./gradlew test

# Testes instrumentados
./gradlew connectedAndroidTest
```

## 📝 Licença

Este projeto está sob a licença [especifique sua licença]. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

**[Seu Nome]**

- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- LinkedIn: [Seu Perfil](https://linkedin.com/in/seu-perfil)

## 🤝 Contribuindo

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma Branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a Branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📧 Contato

Para dúvidas ou sugestões, entre em contato através de [seu-email@exemplo.com]

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!