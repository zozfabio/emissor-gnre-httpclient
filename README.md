## Acessando o Projeto

Basta clonar o projeto no seu computador e abrir na IDE de sua preferência.<br/>
Existem 4 classes main que devem ser executadas separadamente para cada finalidade.<br/>
Para facilitar a criação e execução das rotinas eu optei por fazer a configuração com constantes.<br/>

## Configuração

> O projeto necessita de um KeyStore PKCS12 (.pfx) com o certificado do cliente e um KeyStore JKS com o certificado do WebService.<br/>

> O KeyStore do WebService pode conter o certificado do ambiente de Produção (www.gnre.pe.gov.br) e do ambiente de Homologação (www.testegnre.pe.gov.br).

A configuração dos certificados deve ser feita nesta classe: `me.zozfabio.emissorgnre.ClientFactory`.<br/>

Para definir o caminho do KeyStore do cliente e senha:<br/> 
`private static final String CERTIFICADO_CLIENTE_PATH = "";`<br/>
`private static final String CERTIFICADO_CLIENTE_PASSWD = "";`<br/>

Para definir o caminho do KeyStore do WebService e senha:<br/> 
`private static final String KEYSTORE_SERVICO_PATH = "";`<br/>
`private static final String KEYSTORE_SERVICO_PASSDW = "";`<br/>

## ConfigUF WebService

`me.zozfabio.emissorgnre.configuf.ConfigUFApplication`<br/>

Rotina para consulta da configuração por UF. Após a configuração do certificado, esta rotina pode ser executada diretamente pela IDE para testar se a comunicação com o WebService está OK.

Esta Rotina já esta configurada para rodar no ambiente de homologação, com uma UF fixa (mas que pode ser alterada facilmente no código da classe).

Ela simplesmente mostra a mensagem Soap que é enviada e também o retorno no console.

## LoteRecepcao WebService

`me.zozfabio.emissorgnre.loterecepcao.LoteRecepcaoApplication`

Rotina para envio da guia. Originalmente esse WebService pode receber um lote de guias por chamada, mas eu optei por mandar só uma, para exemplo.

Esta Rotina também já esta configurada para rodar no ambiente de homologação, com alguns dados fixos que podem ser alterados.

Ela monstra a mensagem Soap enviada e o resultado no console. Para a próxima rotina é necessário copiar o Número do Recibo da resposta do WebService.

## ResultadoLote WebService

`me.zozfabio.emissorgnre.resultadolote.ResultadoLoteApplication`

Rotina para consulta do resultado do processamento da guia. Aqui é necessário informar no local indicado por `NUMERO_RECIBO`, o Número do Recibo obtido do WebService LoteRecepcao.

Esta Rotina também já esta configurada para rodar no ambiente de homologação.

Ela monstra a mensagem Soap enviada e o resultado no console. Para a próxima rotina é necessário copiar o Resultado da resposta do WebService.

> Fique atento aos espaços no final da String e as quebras de linha, eles devem ser copiados junto com a string para a execução correta da próxima rotina.

## Emissão da Guia

A partir da execução dos três WebServices, você terá a string do resultado do processamento da guia pelo sistema do governo.

Agora basta colar a string do resultado no código, no local indicado por `RESULTADO`, para emitir a guia no formato PDF.

Esta rotina gera o arquivo em um diretório temporário e apresenta o caminho no console. Uma dica é copiar este caminho e colar no navegador, se ele tiver suporte a PDF, irá apresentar a guia.

## Referência e Agradecimento

Eu pude desenvolver este exemplo graças a outro projeto aqui do github (https://github.com/helderklemp/GnreWebService).

Gostaria de agradeçer ao proprietário deste repositório, pois foi a única coisa que me ajudou a implementar essa integração. 
