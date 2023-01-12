# Resumo:
* Arquitetura MVVM com MultiModulos - Padrão Android Modularizado
  - Arquitetura MVVM com multimodulos para reaproveitamento do código, maior legibilidade e desacoplamento do módulo APP 
  - Modularização por feature e itens core / utils -> Cada feature é um modulo de library, itens core são modulos library e o módulo de design system é modulo library
  - Utilização de Hilt para Injeção de dependência, pois é mais seguro do que o koin, visto que é buildTime. único ponto negativo que encontro no hilt é a verbosidade, mais tempo de compilação e geração de arquivos, porém a segurança para não dar crash (koin é execute time) é melhor.
  - Testes unitario no viewModel do Equipamento e classe de imagem util (conversor de bitmap para base64) para termos maior assertividade e confiaça no código, sendo assim, reduzindo o número de bugs, mas não acabando com eles. Um software sempre terá bugs, basta reduzirmos o maior possível. Apenas clicar no botão executar para os testes rodarem
  ---
  
 # Arquitetura:
  * Embaixo demonstra a arquitetura do projeto em módulos:
  ![multimodule-arch](https://user-images.githubusercontent.com/72824080/212127312-9bfa6759-eb1d-4453-87b0-e383bc841ddd.jpeg)
  
   - as setas indicam as dependencias de um modulo para outro, lembrando que: os módulos features nao devem depender de outras features ou do módulo APP.
---


 * Não tenho total conhecimento em banco de dados e paginação. É um gap técnico meu que tenho que abater e infelizmente não cumpri todos os requisitos por esse motivo.
 
 * 
 
