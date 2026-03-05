# viaceploading

Aplicação Java (Maven) que consulta a API ViaCEP para buscar informações de um CEP informado pelo usuário.

## Funcionalidades
- Solicita um CEP ao usuário
- Busca os dados na API ViaCEP
- Executa a busca em uma thread separada
- Exibe uma animação (spinner) enquanto aguarda a resposta
- Mostra logradouro, bairro, cidade e UF

## Tecnologias
- Java 21+
- Maven
- Gson
- VSCode

## API utilizada
- ViaCEP: `https://viacep.com.br/ws/{CEP}/json/`

## Exemplo de uso
CEP informado:
74735060

Exemplo de saída:
Logradouro: Rua Capauam, bairro: Jardim Califórnia, cidade: Goiânia e uf: GO.
