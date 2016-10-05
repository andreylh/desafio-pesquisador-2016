# desafio-pesquisador-2016

Para executar os cenários do desafio foi implementado dois projetos:
- coap-server: Servidor CoAP. Para levantar o servidor basta executar o método ```main``` da classe ```Server```.
- mqtt-coap-client: Esse projeto contém implementações dos clientes CoAP e MQTT. O protocolo é definido em tempo de execução dependendo da URL do servidor. O MQTT é utilizando quando a URL inicia em "tcp". O CoAP é utilizando quando a URL inicia em "coap". São três argumentos necessários para executar a aplicação:
 - serverUrl: Endereço de conexão com o servidor;
 - limit: A quantidade de registros a ser transmitido do cliente;
 - qos: Qualidade do serviço, utilizado apenas para o protocolo MQTT.
